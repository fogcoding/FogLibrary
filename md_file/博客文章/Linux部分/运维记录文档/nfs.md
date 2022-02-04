## NFS 挂载



```shell
#umount  /mnt/nfs 可能会出现device is busy的问题。

解决方法：

1.      首先查找谁在占用：#fuser /mnt/nfs 得到进程号。

2.      查找进程：#ps –ef|grep 进程号。

3.      杀死进程:#kill -9 进程号

4.      然后umount ,如不行 umount –f /mnt/nfs强行卸载。

5.      再不行重新启动nfsd，再执行上述命令umount文件系统。
```





