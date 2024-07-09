



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



####  Weblogic控制台提示禁止操作

```shell
## 
## 1.可能是宿主机器的域名映射不匹配，导致远程无法操作，从而显示无法操作，设置域名映射即可
## 2.也可能是设置了console-enabled 的值，操作是否可以操作控制台，这种情况则沟通项目组

```



