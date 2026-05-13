



#### tomcat 如何只监听ipv4

```shell

## 临时禁用ipv6
sysctl -w net.ipv6.conf.all.disable_ipv6=1
sysctl -w net.ipv6.conf.default.disable_ipv6=1


## server.xml增加
<Connector port="8080" protocol="HTTP/1.1" address="0.0.0.0" connectionTimeout="20000" />


```

