# Mysql的操作类型记录

###  1.创建表部分

### 2.修改表部分
##### 2.1 表名，列名的修改，增删
```
alter table test rename test1; --修改表名

alter table test change  column address address1 varchar(30)--修改表列名

alter table test add  column name varchar(10); --添加表列

alter table test drop  column name; --删除表列

alter table test modify address char(10) --修改表列类型
||alter table test change address address  char(40)

```

### 3.插入数据部分
###### 3.1一张表的数据插入到另一张表中
```
类别一、 如果两张张表（导出表和目标表）的字段一致，并且希望插入全部数据，可以用这种方法：

INSERT INTO  目标表  SELECT  * FROM  来源表 ;

例如，要将 articles 表插入到 newArticles 表中，则可以通过如下SQL语句实现：

INSERT INTO  newArticles  SELECT  * FROM  articles ;

类别二、 如果只希望导入指定字段，可以用这种方法：

INSERT INTO  目标表 (字段1, 字段2, ...)  SELECT   字段1, 字段2, ...   FROM  来源表 ;

```

### 4.删除数据部分

### 5.数据回滚部分

### 6.数据备份恢复部分
