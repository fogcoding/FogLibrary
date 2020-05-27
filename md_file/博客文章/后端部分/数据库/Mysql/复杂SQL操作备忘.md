## 复杂SQL操作备忘



#### 查询临时表来修改原表

```sql
-- 数据先前不是日期格式，想要格式化日期
select plan_sit,IFNULL(sit_time,date(now())) as sit_time from project_list;

-- 先将需要替换的数据查出来
select _id,plan_sit,t_id,t.new from project_list p inner jion (select _id tid,date(plan_sit) new from project_list) as t on p._id = t.t_id;

-- 拿以上的查询结果，更新原数据
update project_list p inner join (select _id t_id,date(plan_sit) new from project_list) as t on p._id = t.t_id set plan_sit = new;

-- 计算时间差
-- TIMESTAMPDIFF('[DAY,HOUR,MINUTE,SECOND]',plan_sit,date(now())) from project_list

-- update 配合select 使用
update A inner join(select id,name from B) c on A.id = c.id set A.name = c.name;



```



