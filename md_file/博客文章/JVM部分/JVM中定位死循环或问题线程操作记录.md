## 定位问题操作记录



#### 测试代码

```java
@Controller
public class TestController {

    @GetMapping("/error")
    public String error(String[] args) {

        new Thread(new Runnable() {
            public void run() {
                while (true){
                    //下面一行为21行，用于标注位置
                    System.out.println("搞个死循环...");
                }
            }
        }).start();

        return "started~!";
    }

}
```



#### 定位查找过程

```shell
## 调用对应的xxxx:8080/error接口，创造死循环

## top 查看进程CPU暴涨
top - 23:12:42 up 22 days,  5:28,  1 user,  load average: 1.31, 0.55, 0.48
Tasks:  85 total,   1 running,  84 sleeping,   0 stopped,   0 zombie
%Cpu0  : 23.2 us, 38.8 sy,  0.0 ni, 37.3 id,  0.0 wa,  0.0 hi,  0.7 si,  0.0 st
%Cpu1  : 19.9 us, 32.5 sy,  0.0 ni, 47.2 id,  0.0 wa,  0.0 hi,  0.4 si,  0.0 st
KiB Mem :  1798936 total,   734624 free,   649120 used,   415192 buff/cache
KiB Swap:        0 total,        0 free,        0 used.   999500 avail Mem 
  PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND                                                               
24083 root      20   0 2947636 121444  13672 S 140.2  6.8   1:21.36 java                                                                 
12876 root      10 -10  213340  20452   5760 S   2.7  1.1 302:58.84 AliYunDunMonito                                                       
 8153 fogcodi+  20   0 1820460 371272   3872 S   0.7 20.6  61:45.91 mysqld                                                               
12865 root      10 -10  113128   6152   4068 S   0.7  0.3  54:43.38 AliYunDun       

## 显然上图显示id为24083的进程，导致了CPU暴涨

## 通过TOP命令，根据进程找到占用的线程编号
top -Hp 24083

%Cpu(s): 18.5 us, 40.7 sy,  0.0 ni, 40.7 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
KiB Mem :  1798936 total,   227356 free,   649116 used,   922464 buff/cache
KiB Swap:        0 total,        0 free,        0 used.   993808 avail Mem 
  PID USER      PR  NI    VIRT    RES    SHR S %CPU %MEM     TIME+ COMMAND                                                               
24159 root      20   0 2947636 121444  13672 R 86.7  6.8   2:24.62 Thread-3                                                               
24160 root      20   0 2947636 121444  13672 R 60.0  6.8   2:21.54 Thread-4                                                               
24097 root      20   0 2947636 121444  13672 S  6.7  6.8   0:00.06 Catalina-utilit                                                       
24083 root      20   0 2947636 121444  13672 S  0.0  6.8   0:00.00 java                                                                   
24084 root      20   0 2947636 121444  13672 S  0.0  6.8   0:05.26 java                                                                   
24085 root      20   0 2947636 121444  13672 S  0.0  6.8   0:01.12 VM Thread                                                             
24086 root      20   0 2947636 121444  13672 S  0.0  6.8   0:00.00 Reference Handl                                                       
24087 root      20   0 2947636 121444  13672 S  0.0  6.8   0:00.01 Finalizer        

## 显然线程编号为24159的线程，导致了CPU的暴涨
## 由于当前展示的是10进制的线程编号，所以需要通过printf命令将进程编号转变为16进制
## printf "%x\n" 24159
[root@izf8z258a1o9m3s11z37lhz ~]# printf "%x\n" 24159
5e5f
[root@izf8z258a1o9m3s11z37lhz ~]# 

## 再通过jstack命令查看堆栈信息,由于不好直接查看，所以多是输出到文件中再看
jstack 24083
[root@izf8z258a1o9m3s11z37lhz ~]# jstack 24083 > stack.txt
[root@izf8z258a1o9m3s11z37lhz ~]# view stack.txt
#### 通过查找线程编号为5e5f，信息如下所示
"Thread-3" #29 daemon prio=5 os_prio=0 tid=0x00007f03e401a000 nid=0x5e5f waiting for monitor entry [0x00007f04053b4000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at java.io.PrintStream.println(PrintStream.java:805)
        - waiting to lock <0x00000000edb50518> (a java.io.PrintStream)
        #### 问题就是这一行
        at com.fogcoding.controller.TestController$1.run(TestController.java:21)
        at java.lang.Thread.run(Thread.java:750)

"DestroyJavaVM" #28 prio=5 os_prio=0 tid=0x00007f0428009800 nid=0x5e14 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

#### 显然经过查看，直接定位到了TestController类的21行，与测试代码成功对应，定位完成。


######################################总结步骤#######################################
1. top                      查看问题进程pid
2. top -Hp 24083            打印资源占用信息，并找到占用最高的线程id
3. printf "%x\n" 24159      输出10进制对应16进制的线程编号
4. jstack 24083 > stack.txt 输出堆栈信息到文本
5. 通过文本文件查找步骤3得到的线程编号，由堆栈信息得到调用步骤
######################################总结步骤#######################################


```



