



#### 类似DBLINK功能实现

```

#### 开启对应功能支持配置文件补充
[mysqld]
federated 


## 直接查看是否支持对应引擎
show engines


### 对应建表语句
create table (...) engine=federated connection='mysql://username:password@hostname:port/database/tablename'




```

