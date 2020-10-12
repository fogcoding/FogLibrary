#### 常用的JAVA进程分析指令



#### 启动远程监控端口

```shell
java -jar -Djava.rmi.server.hostname=192.168.0.174 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9192 -Dcom.sun.management.jmxremote.rmi.port=9193 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false xxx.jar

```



* jps:查找java进程
* jinfo -flags pid   查看java进程的参数设置
* jmap    查看java进程的堆栈信息
* jstack  查看java进程的线程状态
* jvisualvm 可视化分析工具
* jstat 查看堆内存各部分的使用量，以及加载类的数量





#### jstat 查看gc参数

```
S0C：第一个幸存区的大小
S1C：第二个幸存区的大小
S0U：第一个幸存区的使用大小
S1U：第二个幸存区的使用大小
EC：伊甸园区的大小
EU：伊甸园区的使用大小
OC：老年代大小
OU：老年代使用大小
MC：方法区大小
MU：方法区使用大小
CCSC:压缩类空间大小
CCSU:压缩类空间使用大小
YGC：年轻代垃圾回收次数
YGCT：年轻代垃圾回收消耗时间
FGC：老年代垃圾回收次数
FGCT：老年代垃圾回收消耗时间
GCT：垃圾回收消耗总时间

参考博客：https://www.cnblogs.com/sxdcgaq8080/p/11089841.html
```













