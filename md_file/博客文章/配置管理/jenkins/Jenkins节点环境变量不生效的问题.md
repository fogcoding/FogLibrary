## Jenkins节点环境变量不生效的问题



#### 背景

  在/etc/profile还有用户级别的~/.bash_profile均已经配置maven环境变量，但是jenkins在执行pipline时依然读取不到mvn命令，提示找不到。



#### 原因

  Jenkins 通过shell脚本调用mvn 命令的时候，是从/usr/bin 文件夹中找命令的，是个死目录！



解决办法

  在/usr/bin/目录做个软链接执行下 ln -s  被链接文件  链接文件

```shell
ln -s /sxapp/sxappopt/apache-maven/bin/mvn mvn
```

