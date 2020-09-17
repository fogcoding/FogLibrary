## MySQL备份与恢复



# 备份的恢复会默认删掉原来的表，所以如果原来的数据还需要存在，千万不能用备份恢复的方法。





### 备份与备份文件恢复

#### 获取备份

```sql
--  本地备份所有数据库
mysqldump -u root -p --all-databases>/home/fogcoding/xxx.sql

-- 远程备份所有数据库
mysqldump -u root -p --all-databases>/home/fogcoding/xxx.sql

-- 设置备份某个特定的数据库
mysqldump -h 127.0.0.1 -P 3306 -u root -p 123456 --databases db_name > /home/fogcoding/xxx.sql

-- 备份压缩(导出的数据有可能比较大，不好备份到远程，这时候就需要进行压缩)
mysqldump -h 127.0.0.1 -P 3306 -u root -p 123456 --databases db_name | gzip > /home/fogcoding/X.sql.gz

-- 备份同个库多个表
mysqldump -h 127.0.0.1 -P 3306 -u root -p 123456 --databases db_name --tables table1 table2 .... > /home/fogcoding/xxx.sql

-- 同时备份多个库
mysqldump -h 127.0.0.1 -P 3306 -u root -p 123456 --databases db_1 db_2 db_3 > /home/fogcoding/xx.sql

-- 备份数据库结构，不备份数据
mysqldump -h 127.0.0.1 -P 3306 -u root -p 123456 --no-data db_1 db_2 db_3 > /home/fogcoding/xx.sql

-- 备份数据出带删除数据库或者表的sql备份(没搞懂这是个什么逻辑，记录一下，没时间仔细搞明白)
mysqldump -h主机名 -P端口 -u用户名 -p密码 --add-drop-table --add-drop-database 数据库名 > 文件名.sql

```



#### 恢复备份数据到数据库

```sql
-- 直接执行命令即可
source /home/fogcoding/xxx.sql
```



#### 仅有data目录文件的恢复

```sql
-- https://www.cnblogs.com/qcfeng/p/6077955.html

-- 删除本地的数据存储，仅保留数据结构
ALTER TABLE batch DISCARD TABLESPACE;

-- 将原本的.ibd文件移动到现在的文件夹中

-- 由数据结构导入数据
ALTER TABLE batch IMPORT TABLESPACE;

-- 另外还有一个ibdata的文件，如果这个没有丢，则可以直接拷贝即可实现恢复

```





#### 单表同库备份

```sql
-- 法1：复制表结构到新表
create table tb_name_new select * from tb_name_old where 1=2

-- 法2：复制表结构到新表
create table tb_name_new like tb_name_old 

-- 复制表结构及数据到新表
create table tb_name_new select * from tb_name_old 
```





#### 压缩备份恢复

```sql
-- mysqldump 备份并压缩sql文件

mysqldump -h主机ip -u用户名 -p密码（也可不输入） 数据库名   | gzip > 压缩后文件位置/xxx_backupfile.sql.gz


-- mysql直接用压缩文件恢复(windows需要安装gunzip软件)

-- 方法一（linux无障碍运行）：
gunzip < xxx_backupfile.sql.gz | mysql -u用户名 -p密码（也可不输入） 数据库名

-- 方法二（windows下的变通）：
-- 解压时提示unknown suffix -- ignored，是因为直接解压文件是默认解压.gz文件，需要文件后缀是.gz
-- 但mysql的备份可以设置 -c 输出到标准输出，然后管道命令到mysql执行，也可以-c 然后定向到某个txt文件
gzip -d d:\\zip1.gz -c | mysql -u root -p -P3307



```



### 日志回滚恢复

