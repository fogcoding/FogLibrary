## redis启动与连接



### Windows下安装与运行Redis

* windows下启动

```sql
redis-server.exe redis.windows.conf
```



* windows下连接

```sql
redis-cli.exe -p 6379
```

* windows安装版本比较滞后，在redis官网没有提供下载渠道，只有在github上有下载连接，由微软提供。

  [Redis的Windows安装包下载](https://github.com/MicrosoftArchive/redis/tags)



### windows子系统下安装rRedis

```shell
## 1.配置好apt-get的源，然后执行执行安装命令
sudo apt-get install redis-server

## 2.编辑/etc/redis/redis.conf并将以下行bind 127.0.0.1,改为0.0.0.0

## 3.启动服务
sudo service redis-server restart

## 4.连接验证是否成功运行
redis-cli

```



### 源码编译安装



```shell
## 环境准备,安装相关基础依赖make,gcc,tcl
apt-get install make,gcc,tcl

## CD到解压目录执行命令
make & make install

## 切换到src目录直接执行即可运行redis服务器
./redis-server

## 执行命令连接redis服务器
./redis-cli

```



* 特别备注：网上很多的博客都是不怎么起作用的，只帮助搞清楚了编译命令需要安装哪些依赖，真正的教程还得是在官网上直接放出来的教程。在[Redis官网](https://redis.io/download)的最下面。
* 以下是官网的源码编译安装，关键内容：

```makefile
How to verify files for integrity
The Github repository redis-hashes contains a README file with SHA1 digests of released tarball archives. Note: the generic redis-stable.tar.gz tarball does not match any hash because it is modified to untar to the redis-stable directory.

Installation
Download, extract and compile Redis with:

$ wget http://download.redis.io/releases/redis-5.0.8.tar.gz
$ tar xzf redis-5.0.8.tar.gz
$ cd redis-5.0.8
$ make
The binaries that are now compiled are available in the src directory. Run Redis with:

$ src/redis-server
You can interact with Redis using the built-in client:

$ src/redis-cli
redis> set foo bar
OK
redis> get foo
"bar"
Are you new to Redis? Try our online, interactive tutorial.
```





## 三种启动方式

```properties
安装后进入到src目录看到：

redis-server  ------------>启动redis服务器

redis-cli-------------------->Redis命令客户端

redis-benchmark-------->Redis测试工具

redis-check-aof----------->AOF文件修复工具

redis-check-dump-------->RDB文件检查工具

redis-sentinel-------------->Sentinel服务器

三种启动方法:

最简启动:redis-server,按照默认配置启动

动态参数启动:redis-server --port 6380(6379是默认端口)

配置文件启动：redis-server configPath(配置文件)

因为redis是单线程的，所以可以在一台机器上布置多个redis，用端口区分开

```



## 设置Redis的登录密码

```sql
-- 查看redis的密码状态
-- #redis没有设置登陆密码时
redis-cli> config get requirepass
1) "requirepass"
2) ""
 
-- #redis设置了登陆密码，密码为old_password
redis-cli> config get requirepass
1) "requirepass"
2) "old_password"

-- 方法1：修改配置文件
-- #requirepass foobared   去掉前面的注释，并修改为所需要的密码：
-- 此方法需要重启redis才能生效
requirepass new_pass_word

-- 方法2：命令行修改
-- 密码没有刷新到配置文件中，redis重启，密码会丢失，持久生效需要继续使用config的rewrite命令，这个命令会将当前的修改刷新到配置文件中，执行完这个命令后断开和重启都不会丢失这个密码了。
config set requirepass "new_password"

config rewrite

```



## 设置远程连接权限

```sql
-- 修改redis服务器的配置文件
vi redis.conf
-- 执行一下步骤,箭头左边是本地连接配置，箭头右边是开始远程连接的配置
bind  127.0.0.1 -> 0.0.0.0
protected-mode   yes -> no
```



## redis的数据文件在哪里

```sql
-- redis.cnf中一个配置项为dbfilename dump.rdb，这个配置用来声明数据文件叫什么
-- 另一个配置项 dir ./  用来声明redis的数据文件存在什么位置（一般选择显式声明绝对路径，避免歧义，方便查找）
 
```



## 如何备份和恢复redis的数据

```sql
-- 直接拷贝dump.rdb，然后存放在对应的目录即可，redis会自动读取
```









