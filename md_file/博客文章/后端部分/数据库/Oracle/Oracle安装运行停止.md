## Oracle基础工具

* Plsql
* oracle数据库



#### 如何配置plsql连接数据库

##### 	连接远程数据库

​		

##### 	连接本地数据库

​		[连接本地数据库的配置](https://www.cnblogs.com/fengjunming/p/7966110.html)





#### 如何安装并控制oracle数据库的状态

* 安装
* 停止
* 启动
* 配置





#### ORACLE实例启动与停止

```shell
SQL> startup

ORA-01081: 无法启动已在运行的 ORACLE - 请首先关闭它


SQL> shutdown immediate

数据库已经关闭。

已经卸载数据库。

ORACLE 例程已经关闭。

SQL> startup nomount

ORACLE 例程已经启动。



Total System Global Area 1586708480 bytes

Fixed Size     2213736 bytes

Variable Size   922749080 bytes

Database Buffers   654311424 bytes

Redo Buffers     7434240 bytes

SQL> 
```





#### 安装运行总结

1.通过窗口化工具去安装设置数据库

2.本地调通能连上

3.数据库mount,open,新建用户，授权登录

4.打开监听服务

5.测试连接



