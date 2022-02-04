## Select语句



#### 查询拼接

```plsql
-- 用来在查询结果两端拼接内容，形成新的sql语句
select 'grant select on ' || object_name || ' to itreport;' from user_objects
```

