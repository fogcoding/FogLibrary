## Zookeeper基本使用



## 引用文章

https://www.cnblogs.com/expiator/p/9853378.html







```shel
-- zk如何远程链接

zkCli.sh -server x.x.x.x:2181



## 添加权限
addauth digest ifp:ifp123
setAcl / auth:ifp:crwda

## 取消权限
setAcl / world:anyone:cdrwa



```



