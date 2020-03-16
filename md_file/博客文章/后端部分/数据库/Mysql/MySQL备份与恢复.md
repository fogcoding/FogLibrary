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



### 日志回滚恢复

