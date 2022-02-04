## Linux下安装Oracle客户端SQLPLUS的方法



官方下载地址：https://www.oracle.com/database/technologies/instant-client/linux-x86-64-downloads.html#ic_x64_inst



```shell
-- 1.下载basic包，sqlplus包
basic包包含了基础的依赖**.so文件
sqlplus包含了sqlplus的执行文件
以上两个包，单独来说都无法发挥作用，配合使用就能执行sqlplus命令

-- 2.解压到同一目录（也可以分开安装，就是环境变量需要注意对应）

-- 3.配置环境变量
## sqlplus
export SQLPLUS_HOME=/fogcoding/OracleClient/instantclient_21_1
export LD_LIBRARY_PATH=$SQLPLUS_HOME
export LD_LIBRARY_PATH=$ORACLE_HOME:$LD_LIBRARY_PATH
export PATH=$PATH:$SQLPLUS_HOME



```



