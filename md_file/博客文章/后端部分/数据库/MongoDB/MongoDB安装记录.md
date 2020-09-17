



#### Linux下MongoDB安装



```sql
-- 官方安装指导地址
https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu-tarball/


-- install
apt-get install libcurl4 openssl liblzma5


-- liblzma.s0.0
yum install xz-compat-libs
-- CURL_OPENSSL_3
yum install libcurl3


-- 直接启动
./mongod  -dbpath=/sxapp/sxappopt/mongodb/mongodb/data -logpath=/sxapp/sxappopt/mongodb/mongodb/logs/mongo.log

-- 后台启动
./mongod -f /etc/mongodb.cnf

--配置文件
dbpath=/sxapp/sxappopt/mongodb/mongodb/data
logpath=/ sxapp/sxappopt/mongodb/mongodb/logs/mongo.log
logappend=true
journal=true
quiet=true
port=27017
fork=true      # 后台运行
bind_ip=0.0.0.0    # 允许所有IP连接
auth=false      #是否授权连接


-- 验证启动
./mongo

-- 命令尝试
show dbs /show databases






```

