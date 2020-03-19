## MySQL默认函数解析

* AVG()
* COUNT()
* FIRST()
* LAST()
* MAX()
* MIN()
* SUM()
* GROUP BY
* HAVING
* UCASE()
* LCASE()
* MID()
* LEN()
* ROUND()
* NOW()
* FORMAT()



#### MySQL帮助查看

```sql
-- 帮助查看命令
?

-- 查看mysql支持的函数
? functions

```





 



## 聚合函数原来能够在select语句中随时运用

```sql
-- 来源灵感
select concat("alter table '",TABLE_SCHEMA,"' discard tablespace") from `TABLES` a where a.TABLE_SCHEMA = 'fog';
```



#### 特殊的sql函数

```
-- 替换更新某一列的值
UPDATE test_env set system=REPLACE(system,'UAT2_','') where system like 'UAT2_%';
```









