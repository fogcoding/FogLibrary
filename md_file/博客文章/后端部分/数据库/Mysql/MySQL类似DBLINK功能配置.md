



#### 类似DBLINK功能实现

```

#### 开启对应功能支持配置文件补充
[mysqld]
federated 


## 直接查看是否支持对应引擎
show engines


### 对应建表语句
create table (...) engine=federated connection='mysql://username:password@hostname:port/database/tablename'


## 缺点
1.表结构必须完全一样
2.不支持事务
3.不支持表结构修改操作
4.删除本地表，远程表不会删除
5.远程服务器必须是mysql服务器



```

