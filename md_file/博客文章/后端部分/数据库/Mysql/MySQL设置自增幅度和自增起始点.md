## MySQL自增长配置



两个值：

* auto_increment_increment：每次增加多少
* auto_increment_offset：空表主键起始值

* 在Linux中需要在my.cnf中配置，重启服务后生效。

* windows输入如下指令即可：

```sql

SET @@auto_increment_increment=n;
SET @@auto_increment_offset=n;

-- 通过如下语句可查看当前的配置
SHOW VARIABLES LIKE ‘auto_inc%’;
```






```sql
-- 设置自增起始值
alter table table_name AUTO_INCREMENT=10000;

-- 设置自增幅度
alter table table_name auto_increment_offset=2;

```

