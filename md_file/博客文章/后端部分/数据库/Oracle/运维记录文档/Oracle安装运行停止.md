## Oracle基础工具

* Plsql
* oracle数据库



#### LInux下安装Oracle数据库

ref: https://www.oracle.com/ocom/groups/public/@otn/documents/webcontent/229016_zhs.htm#i

```shell
1.	
在这一安装中，您需要使用 DVD 或下载 DVD 版本。在本教程中，将从已下载的版本进行安装。在 DVD 文件解压缩的目录中，打开终端窗口终入以下内容：

./runInstaller.sh 

2.	
安装的产品为 Oracle Database 11g。选定该产品并单击 Next。

2.	
您将通过一个入门级数据库来执行基本安装。输入 orcl 作为 Global Database Name 以及 oracle 作为 Database Password 并确认口令。然后单击 Next。

3.	
您需要指定 Inventory 目录。位置应该设置为 /u01/app/oracle/oraInventory。接受默认的 Operating System 级名 oinstall。然后单击 Next。

4.	
安装程序现在将验证系统是否满足安装和配置所选产品的最低要求。继续前改正任何报告的错误。检查成功完成后，单击 Next。

5.	
Oracle Configuration Manager 允许您将配置信息与 Metalink 帐户相关联。您可在该窗口上选择启用它。然后单击 Next。

5.	
查看 Summary 窗口，验证要安装的内容。然后单击 Install。

6.	
进度窗口显示。

7.	
Configuration Assistants 窗口显示。

8.	
正在创建数据库。

9.	
创建了数据库之后，可以解锁您要使用的用户。单击 OK。

10.	
您需要以 root 用户身份执行 orainstRoot.sh 和 root.sh

11.	
打开终端窗口输入以下命令。按提示输入内容。

su -
<rootpassword>
cd /u01/app/oracle/oraInventory
./orainstRoot.sh
cd ../product/11.1.0/db_1
./root.sh
exit
exit

12.	
切换回 Universal Installer 并单击 OK。

13.	
单击 Exit。单击 Yes 确认退出。


```



#### Oracle环境变量

```shell
## 安装完毕oracle以后，需要创建oracle系统用户，并在/home/oracle下面的.bash_profile添加几个环境变量：ORACLE_SID,ORACLE_BASE,ORACLE_HOME。比如：

export ORACLE_SID=test 
export ORACLE_BASE=oracle_install_dir 
export ORACLE_HOME=xxx
```





#### 如何配置plsql连接数据库

##### 	连接远程数据库

​		

##### 	连接本地数据库

​		[连接本地数据库的配置](https://www.cnblogs.com/fengjunming/p/7966110.html)



##### 命令行直连数据库

```sql
-- cmd链接oracle数据库报错ORA-12560：TNS:protocol adapter error

-- 解决方法之一：以完整格式“sqlplus 用户名/密码@127.0.0.1/数据库服务名”登录时提示成功。
-- 使用示例
sqlplus c##reader/reader@127.0.0.1:1521/orcl
```









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



**命令解释：**

- shutdown命令： 相当于以下三个命令

```
normal;
shutdown immediate;
shutdown abort;
```

- startup命令： 等于以下三个命令

```
startup nomount;
alter database mount;
alter database open;
```



#### 安装运行总结

1.通过窗口化工具去安装设置数据库

2.本地调通能连上

3.数据库mount,open,新建用户，授权登录

4.打开监听服务

5.测试连接



