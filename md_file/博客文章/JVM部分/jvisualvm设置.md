## JvisualVM配置





#### 配置堆大小，避免内存不足

```
修改JAVA_HOME/lib/visualvm/etc/visualvm.conf文件中visualvm_default_options="-J-client -J-Xms24 -J-Xmx256m",把256改为2048，然后重启jvisualVM即可
```





#### 看到的内存对象不够精细，需要安装其插件



