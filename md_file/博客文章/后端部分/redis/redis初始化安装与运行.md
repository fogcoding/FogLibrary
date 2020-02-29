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
apt-get install make,gcc.ctl

## CD到解压目录执行命令
make & make install

## 切换到src目录直接执行即可运行redis服务器
./redis-server

## 执行命令连接redis服务器
./redis-cli

```



* 特别备注：网上很多的博客都是不怎么起作用的，只帮助搞清楚了编译命令需要安装哪些依赖，真正的教程还得是在官网上直接放出来的教程。在[Redis官网](https://redis.io/download)的最下面。

