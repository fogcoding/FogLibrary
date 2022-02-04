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



#### group by一列来统计数据

```sql
-- 两个select语句的结果想要结合起来，需要在第一个select 语句前加一次select * from ，以表示从临时表查询时做结合操作 
SELECT
	* 
FROM
	( SELECT xqid FROM season1 s1 GROUP BY xqid ) s1
	LEFT JOIN season1 s2 ON s1.xqid = s2.xqid;
```



#### MySQL 行列互转

```sql
-- 按月份成列的每行不同数据，整理成分月份为列的结果
select
  d.id,
  sum(if(d.month = 'Jan',d.revenue,null)) as Jan_Revenue,
  sum(if(d.month = 'Feb',d.revenue,null)) as Feb_Revenue,
  sum(if(d.month = 'Mar',d.revenue,null)) as Mar_Revenue,
  sum(if(d.month = 'Apr',d.revenue,null)) as Apr_Revenue,
  sum(if(d.month = 'May',d.revenue,null)) as May_Revenue,
  sum(if(d.month = 'Jun',d.revenue,null)) as Jun_Revenue,
  sum(if(d.month = 'Jul',d.revenue,null)) as Jul_Revenue,
  sum(if(d.month = 'Aug',d.revenue,null)) as Aug_Revenue,
  sum(if(d.month = 'Sep',d.revenue,null)) as Sep_Revenue,
  sum(if(d.month = 'Oct',d.revenue,null)) as Oct_Revenue,
  sum(if(d.month = 'Nov',d.revenue,null)) as Nov_Revenue,
  sum(if(d.month = 'Dec',d.revenue,null)) as Dec_Revenue
from Department d
  group by d.id;

```



#### MySQL排名函数

```sql

select *,
   -- 值相同则排名相同，后一名的排名取前面有多少人的总数
   rank() over (order by 成绩 desc) as ranking,
   -- 值相同则排名相同，后一名的排名在上一名基础上加一
   dense_rank() over (order by 成绩 desc) as dese_rank,
   -- 值相同排名也可以不同，直接顺序递增而不考虑相同值
   row_number() over (order by 成绩 desc) as row_num
from 班级

-- 据说排名函数在8版本的MySQL才有，那么不通过这个排名函数，如何实现排名统计呢？


```







