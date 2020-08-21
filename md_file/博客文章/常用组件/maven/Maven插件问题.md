## Maven插件问题







#### 引用jre的包需要显式声明

```
Windows下：分隔符为分号
<bootclasspath>${java.home}/lib/rt.jar;${java.home}/lib/jce.jar</bootclasspath>

Linux下：分隔符为冒号

<bootclasspath>${java.home}/lib/rt.jar:${java.home}/lib/jce.jar</bootclasspath>
```





