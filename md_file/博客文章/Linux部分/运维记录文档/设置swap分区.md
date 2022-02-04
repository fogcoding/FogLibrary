## 设置SWAP分区

https://www.cnblogs.com/pipci/p/11413433.html

https://help.aliyun.com/knowledge_detail/42534.html



在Linux下，swap的作用类似Windows系统下的“虚拟内存”。当物理内存不足时，拿出部分硬盘空间当SWAP分区（虚拟成内存）使用，从而解决内存容量不足的情况。Linux下的swap有两种实现形式，一种是通过某个物理磁盘分区来实现swap，另一种是通过虚拟内存文件来实现swap。

**一、物理磁盘分区来实现swap**

**1、通过分区软件创建一个分区**，分区类型fdisk下为82，gdisk下为8200，partep分区标记没有定义swap，其实分区后不更改分区类型也行测试也可以用，但为了在分区工具下方便查看最好还是把分区类型改成swap对应的分区类型，本人理解分区类型就是分区软件下用来描述分区所要承载的文件系统的类型，比如分区类型为83表示为linxu分区，用于格式化成ext3 ext4等linux文件系统，分区创建的方法这里就不写了前面有分区创建的方法。

**2、使用mkswap命令把分区格式化为swap分区**
\-------------------------------------------------------------------
root@debian:~# mkswap /dev/sdb1
Setting up swapspace version 1, size = 1024 MiB (1073737728 bytes)　　　　 #交换分区的容量为1G
no label, UUID=47fe2c83-c4b9-4ee5-ad91-4a9642f66df5
root@debian:~#
\-------------------------------------------------------------------

**3、使用swapon命令使交换分区/dev/sdb1生效**
\-------------------------------------------------------------------
root@debian:~# free -h　　　　　　　　　　　　#查看没加前swap容量
　　　　   total 　　 used　　 free 　　 shared 　　 buff/cache　　 available
Mem: 　　 2.0G 　　 250M 　　 1.4G　　8.5M 　　　 311M 　　　　 1.6G
Swap: 　 2.0G 　　  0B 　　    2.0G　　　　　　 #没加前容量为2G

root@debian:~# swapon /dev/sdb1 　　　　　　 #使新添加的swap生效
root@debian:~# free -h　　　　　　　　　　　　 #查看添加完后的容量
　　　   total 　　 used 　　 free 　　 shared　　 buff/cache　　 available
Mem: 　　 2.0G　　  250M 　  1.4G　　 8.5M　　    311M 　　　　 1.6G
Swap:　　 3.0G　　  0B　　   3.0G　　　　　　 #添加完容量变成了3G
root@debian:~#
\-------------------------------------------------------------------

**4、Linux swapon命令参数及用法**
swapon 是开启swap，相对的便有一个关闭swap的指令,swapoff

swapon [选项]  [设备]

-h 　　显示帮助信息
-V 　　显示版本信息
-v　　 显示详细模式
-s　　 显示swap的使用情况，可以查看所有激活的swap
-a　　 将/etc/fstab文件中所有设置为swap的设备启用
-p 　　设定优先权，你可以在0到32767中间选一个数字给他。或是在 /etc/fstab 里面加上 pri=[value] ([value]就是0~32767中间一个数字)，然后你就可以很方便的直接使用 swapon -a 来启动他们，而且有优先权设定。
　　
举例：显示swap的使用情况
\---------------------------------------------------------------------
root@debian:~# swapon -s
Filename 　　 Type　　　　 Size 　　　　Used　　 Priority
/dev/sda5　　 partition 　　2095100 　　　0　　　　 -1 　　　　 #随系统安装一起创建的swap优先级为1
/dev/sdb1 　  partition 　　1048572 　　   0　　　　 -2　　　　 #后创建的swap优先级为2，类型为分区，大小为1G
root@debian:~#
\---------------------------------------------------------------------

**5、Linux swapoff命令参数及用法**

swapon [选项]  [设备]

-a 　　将/etc/fstab文件中所有设置为swap的设备关闭

举例：
\---------------------------------------------------------------------
root@debian:~# swapoff /dev/sdb1 　　　　# 关闭swap分区/dev/sdb1
root@debian:~# swapon -s
Filename 　　 Type　　 　　 Size 　　　　Used　　 Priority
/dev/sda5　　 partition 　　2095100 　　　0　　　　 -1
root@debian:~#
\---------------------------------------------------------------------

**6、将新添加的交换分区添加到/etc/fstab文件中使之开机启动**

根据不同的发行版将下面的内容添加到/etc/fstab中，添加完后最好用swapon -a命令查看添加的是否有问题

UUID=47fe2c83-c4b9-4ee5-ad91-4a9642f66df5　　 none 　　 swap 　　 sw　　 0 　　 0 #Debian9.5默认的格式，UUID为对应的交换分区UUID

UUID=47fe2c83-c4b9-4ee5-ad91-4a9642f66df5 　　 swap　　swap　　 defaults 　　 0 　　0 #CentOS7-1810与openSUSE15默认的格式，UUID为对应的交换分区UUID

**二、使用文件来实现swap**
当系统内没有剩余可用的分区时我们可以用在Linux的某个目录下创建一个空白文件，通过把这个文件格式化成swap从而实现创建swap的过程。

**1、通过dd命令在/tmp/目录下新增加一个1G大小的空文件**。
\---------------------------------------------------------------------
root@debian:~# dd if=/dev/zero of=/tmp/swap bs=1G count=1　　 #增加一个1G大小的空文件
记录了1+0 的读入
记录了1+0 的写出
1073741824 bytes (1.1 GB, 1.0 GiB) copied, 5.46136 s, 197 MB/s
root@debian:~# chmod 600 /tmp/swap 　　　　　　 #修改文件权限，只有root读写
root@debian:~# ls -lh /tmp/swap 　　　　　　　　　 #查看创建的文件大小和权限
-rw------- 1　　 root 　　root　　 1.0G　　8月 26 14:25 　　/tmp/swap
root@debian:~#
\---------------------------------------------------------------------

**2、使用mkswap命令把创建的文件格式化为swap**
\---------------------------------------------------------------------
root@debian:~# mkswap /tmp/swap
Setting up swapspace version 1, size = 1024 MiB (1073737728 bytes)
no label, UUID=a0aa03d4-406d-4ecd-af04-12998e408192
root@debian:~#
\---------------------------------------------------------------------

**3、使用swapon命令使swap生效**
\---------------------------------------------------------------------
root@debian:~# swapon  /tmp/swap
root@debian:~# swapon -s
Filename 　　 Type 　　 　　Size 　　Used 　　Priority
/dev/sda5　　 partition 　　2095100 　　0　　　 -1
/tmp/swap　　 file　　　　 1048572 　　 0　　　 -2 　　　　 #创建成功
root@debian:~#
\---------------------------------------------------------------------

**4、将新添加的交换分区添加到/etc/fstab文件中使之开机启动**

根据不同的发行版将下面的内容添加到/etc/fstab中，添加完后最好用swapon -a命令查看添加的是否有问题
\---------------------------------------------------------------------
/tmp/swap 　　 none 　　 swap 　　 sw 　　 0 　　 0　　　　#Debian9.5默认的格式，这里不要用UUID，这是因为系统只会查询块设备，不会查询文件

/tmp/swap 　　 swap 　　swap　　 defaults 　　 0　　 0　　　　 #CentOS7-1810与openSUSE15默认的格式



