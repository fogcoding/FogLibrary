



#### Weblogic启动慢或者运行慢解决办法



```shell

# 1.JVM参数设置
修改setDomainEnv.sh里面的Xmx  Xms参数

# 2.随机数问题
修改$JAVA_HOME/jre/lib/security/java.security

将securerandom=file:/dev/random修改成securerandom=file:/dev/./urandom

# 3.配置hosts
本机ip地址   hostname


```





