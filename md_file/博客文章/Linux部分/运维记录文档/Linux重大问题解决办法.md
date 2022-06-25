## Linux重大问题解决办法



#### 搞坏了环境变量

```powershell
## 切换到/usr/bin目录
cd /usr/bin
## 直接使用vi命令，修改/etc/profile,注释掉坏了的环境变量，即可恢复
vi /etc/profile

```

##### 修复原理：

*  原因：环境变量是告知linux系统去哪些目录去寻找这些命令，然后去执行。那么搞坏了环境变量，linux系统会找不到绝大多数的命令执行路径，自然也就环境坏了。
* 以上方法能够解决的原因：直接去对应命令存在的目录，然后就不用去找这个命令的路径了，因为默认在当前目录执行，所以vi命令就会恢复效果，然后就能修改文件，以恢复环境变量。



#### 释放buff/cache的内存份额

```
sync：将所有未写的系统缓冲区写到磁盘中
echo 1 > /proc/sys/vm/drop_caches：清除page cache
echo 2 > /proc/sys/vm/drop_caches：清除回收slab分配器中的对象（包括目录项缓存和inode缓存）。slab分配器是内核中管理内存的一种机制，其中很多缓存数据实现都是用的pagecache。
echo 3 > /proc/sys/vm/drop_caches：清除pagecache和slab分配器中的缓存对象。
/proc/sys/vm/drop_caches的值,默认为0

```







#### 无法清理系统日志messages

```shell
二、解决方法

[root@u05mix02 log]## lsattr messages
-----a-------e- messages                //查看messages的扩展权限
-----a---------- messages
[root@u05mix02 log]## chattr -a messages    //清除a权限
[root@u05mix02 log]## lsattr messages     //再次查看a权限已经清除
---------------- messages
[root@u05mix02 log]## echo /dev/null > messages    //使用echo追加空到messages
[root@u05mix02 log]## ls -lash messages      //查看messages大小
4.0K -rw------- 1 root root 2.0K Nov 28 12.58 messages
[root@u05mix02 log]## chattr +a messages   //谨慎起见再次把a权限加回去
[root@u05mix02 log]## lsattr messages     //a权限添加成功

```

