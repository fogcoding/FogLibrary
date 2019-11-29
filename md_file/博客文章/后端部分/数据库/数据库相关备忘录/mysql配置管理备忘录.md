## MySQL配置管理备忘录

#### 用户权限控制部分

```mysql

/*
	用户创建
*/

-- 查询用户相关信息
select * from mysql.user

-- 创建用户命令
-- 企业中一般为授权内网登录权限，最常见的写法为：
-- 1.172.16.1.% （%通配符意为匹配所有内容）
-- 172.16.1.0 / 255.255.255.0 （但是不能使用172.16.0.1/24是个小遗憾）
create user 'user_name'@'localhost' identified by 'password';

-- 删除用户语法
drop user 'user_name'@'localhost';

-- 特殊的删除方法（慎用，万不得已不要直接修改表）
delete from mysql.user where user = 'user_name' and host = 'localhost';
flush privileges;

-- 查看用户权限
show grants  for 'user_name'@'localhost';

/*
	用户授权
*/

-- 查看用户权限
show grants  for 'user_name'@'localhost';

-- 授权所有权限给某个用户
grant all on *.* to 'user_name'@'localhost';

-- 创建用户的同时授权
grant insert,delete,select,update,drop,create on fog.test to 'user_name'@'localhost' identified by 'password'；
flush privileges;   ## <== 可以不用

-- 创建用户，然后授权
create user 'user_name'@'localhost' identified by 'password';
grant all on 'db_name'@'table_name' to 'user_name'@'localhost';

-- 授权和root一样的权限
grant all on *.* to system@'localhost' identified by 'user_fog' with grant option;

-- 可以授权的权限种类
/*
INSERT,SELECT, UPDATE, DELETE, CREATE, DROP, RELOAD, SHUTDOWN, 
PROCESS, FILE, REFERENCES, INDEX, ALTER, SHOW DATABASES, SUPER, 
CREATE TEMPORARY TABLES, LOCK TABLES, EXECUTE, REPLICATION SLAVE, 
REPLICATION CLIENT, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER 
ROUTINE, CREATE USER, EVENT, TRIGGER, CREATE TABLESPACE
*/
```



#### 表操作部分

```mysql
-- 新建表
create table table1(
	_id int primary key auto_increment comment '编号',
	_name varchar(30) unique not null comment '一个外键约束的索引列'
)engine=innodb,charset=utf8;

create table table_name(
	_id int primary key auto_increment comment '编号+主键约束+自增约束',
	_name varchar(30) unique not null comment '姓名+唯一约束+非空',
	_time timestamp default now() comment '时间类型+默认约束+系统函数',
	index(_name),
	foreign key (_name) references table1(_name)
)engine=innodb,charset=utf8;

-- 删除表
drop table table_name;

-- 增加一列字段
alter table table_name column add column_name int;

-- 删除一列字段
alter table table_name drop column_name;

-- 调整一列字段位置


-- 修改一列字段的类型
alter table table_name modify culomn

-- 修改表的编码类型

-- 修改数据库的编码类型

-- 添加约束

-- 删除约束

-- 级联更新，联级删除
-- 作用：这里一旦学生所在表的数据有更新或删除的操作，那么课程所在表也会对应的进行更新和删除的操作
-- 事件触发器限制：cascade(跟随外键改动),restrict（限制表中的外键改动），set null(设空值)，no action（默认）



```





#### 配置文件部分

```

```

