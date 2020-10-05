## JVM状况诊断与分析



#### 查看基本的JVM参数，当前内存，线程，CPU，类

```sql
-- 配置好java环境后，可以jps找到对应的程序，

jps

-- 然后jconsole直接连接查看
jconsole pid

-- 在VM概要栏可以查看JVM的各项设置和参数

```



#### 堆内存分析

```sql
-- 查看java进程里面线程的执行情况
-- 找到进程id
jps 

-- 直接打印进程id里面的线程信息
jstack -l pid


```



#### 堆空间分析

```powershell
## 通过jmap查看堆空间设置和状况

C:\Users\notes>jmap -heap 17792
Attaching to process ID 17792, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.201-b09

using thread-local object allocation.
Parallel GC with 10 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 0
   MaxHeapFreeRatio         = 100
   MaxHeapSize              = 2126512128 (2028.0MB)
   NewSize                  = 44564480 (42.5MB)
   MaxNewSize               = 708837376 (676.0MB)
   OldSize                  = 89653248 (85.5MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
PS Young Generation
Eden Space:
   capacity = 520617984 (496.5MB)
   used     = 38151408 (36.38401794433594MB)
   free     = 482466576 (460.11598205566406MB)
   7.328100290903512% used
From Space:
   capacity = 24641536 (23.5MB)
   used     = 24619520 (23.47900390625MB)
   free     = 22016 (0.02099609375MB)
   99.91065492021276% used
To Space:
   capacity = 32505856 (31.0MB)
   used     = 0 (0.0MB)
   free     = 32505856 (31.0MB)
   0.0% used
PS Old Generation
   capacity = 106430464 (101.5MB)
   used     = 54975520 (52.428741455078125MB)
   free     = 51454944 (49.071258544921875MB)
   51.65393246805726% used

34603 interned Strings occupying 3943080 bytes.
```





### JVM工作的警示

  JVM的各项操作一般都来自于定位问题或者做性能优化点，那么此时需要心里有完整的执行逻辑和计划，避免在JVM繁杂的信息里丢失自己本来的目的，千万记住只找寻自己需要找的内容。

  程序的运行一般不可改，而其中的中间信息一般没有关注的必要。

  而限制程序的性能或者导致程序出错的地方往往并不只是代码和JVM，还有机器，网络等。

  一般最容易导致性能瓶颈的因素是：代码性能，数据库性能，sql语句性能

  还有其他的因素：服务器性能，JVM性能，网络带宽



