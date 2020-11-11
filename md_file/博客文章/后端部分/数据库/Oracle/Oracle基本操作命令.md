## Oracle基本操作命令



## 用户操作



```sql
-- 用户新增
-- 第一步：创建临时表空间(创建用户之前要创建"临时表空间"，若不创建则默认的临时表空间为temp。)
CREATE TEMPORARY TABLESPACE reader_temp
         TEMPFILE 'D:\datacore\oracle18\oradata\XE\Temp\reader_temp.dbf'
         SIZE 32M
         AUTOEXTEND ON
         NEXT 32M MAXSIZE UNLIMITED
         EXTENT MANAGEMENT LOCAL;
         
-- 第二步：创建数据表空间(创建用户之前先要创建数据表空间，若没有创建则默认永久性表空间是system。)
CREATE TABLESPACE reader_data
         LOGGING
         DATAFILE 'D:\datacore\oracle18\oradata\XE\reader_data.dbf'
         SIZE 32M
         AUTOEXTEND ON
         NEXT 32M MAXSIZE UNLIMITED
         EXTENT MANAGEMENT LOCAL;
         
-- 第三步：创建用户并指定表空间(现在建好了名为'reader_data'的表空间，下面就可以创建用户了)
CREATE USER reader IDENTIFIED BY reader
         ACCOUNT UNLOCK
         DEFAULT TABLESPACE reader_data
         TEMPORARY TABLESPACE reader_temp;
         
-- 给用户授予权限
GRANT CONNECT,RESOURCE TO reader;  --表示把 connect,resource权限授予reader用户
/**
  Connect用户能登录数据库的权限

  Resource用户能创建一些数据库对像的权限，表、视图，存储过程，一般是授予开发人员的
*/

GRANT DBA TO reader;  --表示把 dba权限授予给reader用户

-- OK! 数据库用户创建完成，现在你就可以使用该用户创建数据表了

-- 用户删除(使用cascade参数可以删除该用户的全部objects)
Drop  User user_name cascade;

-- 用户修改密码

-- 用户设置登录地址



-- 操作权限
-- 赋予表空间使用权限
alter user user_name quota unlimited on user_tablespace quota unlimited on user_tablespace;


-- 查看用户下所有对象
select * from user_objects;
select * from dba_objects;

-- 查询系统所有的权限
select * from system_privilege_map;

-- 解锁用户
alter user scott account unlock;


```



#### 数据库启动

```sql
-- 本地连接数据库
-- 第一步进入sqlplus(这里一般要设置Oracle的环境变量)
sqlplus /nolog

-- 第二步，以DBA身份连接
SQL> connect sys as sysdba
Enter password: 
Connected.

-- nomount:读参数文件，启动实例和后台进程
sql> startup nomount
--查看数据库状态
sql>select status from v$instance;

-- mount:打开控制文件
sql>alter database mount;

-- 这里顺便讲一下checkpoint_change#(检查点),主要是系统检查点、数据文件检查点以及数据文件头检查点，这些检查点都是记录在控制文件中，因此数据库必须是mount状态，否则查询不了便会出现ORA-01507错误,因此，在查看checkpoint_change#时，应将数据库启动成mount状态

-- 三.open:数据库打开

sql> alter database open

/*
  当系统、数据文件以及数据文件头这三个checkpoint_change#一致(只读、脱机表空间除外)时，数据库才能正常打开。正常关库时，会生成新的检查点，写入上述三个checkpoint_change#，同时数据文件中的last_change#也会记录下该检查点，也就是说三个checkpoint_change#与last_change#记录着同一个值。

      数据库打开前，先确定是否介质恢复，再确定是否实例恢复。介质恢复主要是更新旧的文件，而实例恢复主要是更新内存。如果last_change#值为空，则说明需要进行实例恢复，恢复后的数据库才能打开。
*/

-- 1.系统检查点

sql>select checkpoint_change# from v$database;

2.数据文件检查点

sql> select file#,checkpoint_change#,last_change# from v$datafile;

3.数据文件头检查点

sql> select file#,checkpoint_change# from v$datafile_header;

-- 这三个检查点的值都一致，因此数据库可以正常打开。

4. 打开数据库

sql>alter database open;
--查看数据库状态。
sql>select status from v$instance;

```



#### 数据库停止

```sql
---登录sqlplus

sqlplus  /nolog

conn / as sysdba

shutdown immediate

/**
数据库停止过程和数据库打开过程相反。数据库停止有几种方式，每一种都对应不同的处理过程：

a、shutdown normal  
正常关闭数据库。不允许新的连接，等待会话结束，等待事务结束，做一个检查点并关闭数据文件。启动时不需要实例恢复。

b、shutdown transactional
不允许新的连接，不等待会话结束，等待事务结束，做一个检查点并关闭数据文件。启动时不需要实例恢复。  

c、shutdown immediate
立即方式关闭数据库。不允许新的连接，不等待会话结束，等待事务结束，做一个检查点并关闭数据文件。没有结束的事务自动回滚，启动时不需要实例恢复。

d、shutdown abort  
   直接关闭数据库，正在访问数据库的会话会被突然终止。如果数据库中有大量操作正在执行，这时执行shutdown abort后，重新启动数据库需要很长时间 
   
*/



```





#### 数据库操作

```sql
-- 新建数据库

-- 新建表

-- 添加字段

-- 修改字段

-- 索引操作


```



#### 数据库备份与恢复





















