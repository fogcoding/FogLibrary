## psotgreSQL 基本使用



#### 1.下载安安装，一路是到底！



#### 2.把postgreSQL安装为windows服务

* pg_ctl.exe register -N "postgresql" -D  D:\datacore\postgreSQL\data 
* services.msc查看服务，并设置为手动启动



#### 3.连接使用

```sql
-- 以用户名Administrator连接到postgres数据库
psql –U Administrator –d postgres
```



#### 4.基本操作

```sql
-- 查看操作帮助
\?
```

