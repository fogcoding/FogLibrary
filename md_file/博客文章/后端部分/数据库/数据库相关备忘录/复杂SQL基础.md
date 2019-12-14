## 复杂SQL基础

> 什么叫复杂SQL？
>
> 说明起来很简单，看下面！

```mysql
-- 查询如下课程平均成绩和及格率的百分数(用’1行’显示): 企业管理（001），马克思（002），OO&UML （003），数据库（004）

SELECT SUM(CASE WHEN C# =’001’ THEN score ELSE 0 END)/SUM(CASE C# WHEN ‘001’ THEN 1 ELSE 0 END) AS 企业管理平均分
,100 * SUM(CASE WHEN C# = ‘001’ AND score >= 60 THEN 1 ELSE 0 END)/SUM(CASE WHEN C# = ‘001’ THEN 1 ELSE 0 END) AS 企业管理及格百分数
,SUM(CASE WHEN C# = ‘002’ THEN score ELSE 0 END)/SUM(CASE C# WHEN ‘002’ THEN 1 ELSE 0 END) AS 马克思平均分
,100 * SUM(CASE WHEN C# = ‘002’ AND score >= 60 THEN 1 ELSE 0 END)/SUM(CASE WHEN C# = ‘002’ THEN 1 ELSE 0 END) AS 马克思及格百分数
,SUM(CASE WHEN C# = ‘003’ THEN score ELSE 0 END)/SUM(CASE C# WHEN ‘003’ THEN 1 ELSE 0 END) AS UML平均分
,100 * SUM(CASE WHEN C# = ‘003’ AND score >= 60 THEN 1 ELSE 0 END)/SUM(CASE WHEN C# = ‘003’ THEN 1 ELSE 0 END) AS UML及格百分数
,SUM(CASE WHEN C# = ‘004’ THEN score ELSE 0 END)/SUM(CASE C# WHEN ‘004’ THEN 1 ELSE 0 END) AS 数据库平均分
,100 * SUM(CASE WHEN C# = ‘004’ AND score >= 60 THEN 1 ELSE 0 END)/SUM(CASE WHEN C# = ‘004’ THEN 1 ELSE 0 END) AS 数据库及格百分数
FROM SC
```



#### 复杂语句的基础知识点

* 数据过滤关键字 

  * order by

    按照某种规则排序，desc/asc

  * group by

    过滤相同值，结果只出现一次
    
    

* 通配符与模糊查询

  * like

  * *

  * %

    

* 连接（jion）

  * jion   JOIN 子句用于把来自两个或多个表的行结合起来，基于这些表之间的共同字段
  * inner jion   如果表中有至少一个匹配，则返回行
  * left jion   即使右表中没有匹配，也从左表返回所有的行
  * right jion   即使左表中没有匹配，也从右表返回所有的行
  * full jion   只要其中一个表中存在匹配，则返回行

![连接示意图](https://www.runoob.com/wp-content/uploads/2019/01/sql-join.png)







* 联合（union）
  
* > 作用为将两个查询语句的结果组合起来，条件是列数一致且对应列的类型一致
  
  * 不带重复值 union
  * 带重复值 union all
  
  





