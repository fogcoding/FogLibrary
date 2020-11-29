## linux系统配置各组件的环境



#### java

```shell
## 下载安装解压到某个目录

## 修改环境变量文件/etc/profile
export JAVA_HOME=/home/software/jdk1.8.0_65
export JRE_HOME=/home/software/jdk1.8.0_65/jre
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH

## 使配置生效
source /etc/profile
```

##### 

#### kafak

```shell
export KAFKA_HOME=/home/wusong/software/kafka_2.12-2.1.0
PATH=\$PATH:\$JAVA_HOME/bin:\$JRE_HOME/bin:\$ZOOKEEPER_HOME/bin:\$KAFKA_HOME/bin
```

