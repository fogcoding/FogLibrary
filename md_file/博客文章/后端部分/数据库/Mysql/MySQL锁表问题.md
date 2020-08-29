## MySQL锁表问题



```sql
show OPEN TABLES where In_use > 0; -- 查询是否锁表
show processlist; -- 查询到相对应的进程===然后killid
SELECT * FROM INFORMATION_SCHEMA.innodb_trx; -- 当前运行的所有事务
SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCKS; -- 查看正在锁的事务
SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCK_WAITS; -- 查看等待锁的事务
```





