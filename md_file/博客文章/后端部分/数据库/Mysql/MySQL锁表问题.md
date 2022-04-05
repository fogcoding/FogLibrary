## MySQL锁表问题



```sql
show OPEN TABLES where In_use > 0; -- 查询是否锁表
show processlist; -- 查询到相对应的进程===然后killid
SELECT * FROM INFORMATION_SCHEMA.innodb_trx; -- 当前运行的所有事务
SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCKS; -- 查看正在锁的事务
SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCK_WAITS; -- 查看等待锁的事务



-- 查看trx_rows_locked  这一列 如果是大于0的话，说明是堵塞住了， 然后查对应的trx_mysql_thread_id 列

-- trx_mysql_thread_id就是被锁的进程id
select trx_id,trx_state,trx_mysql_thread_id from INFORMATION_SCHEMA.innodb_trx;



```





+