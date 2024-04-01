## 青云kubesphere手册





#### 当前主要工作任务

```
1.概念扫盲
2.基础环境清单整理，整体系统架构梳理
3.常规操作梳理
4.标准化流程操作
5.对外发布梳理
```





#### 搭建心得

```
官方的安装步骤:
1.确定安装版本后，梳理对应得镜像清单，确认正确得版本号，不能少，也不能错
2.通过export KKZONE=cn  && ./kk artifact export -m manifest-sample.yaml -o kubesphere.tar.gz命令到处对应的镜像资源
  ps:
   1.有专门的镜像清单，可以直接下载，但是可能会漏掉一部分
   2.下载的命令文件，当执行export KKZONE=cn命令后，会尝试从文件系统直接读取，所以缺什么可以直接下什么放在对应目录，镜像也是一样的道理
3.通过./kk init registry -f config-sample.yaml -a kubesphere.tar.gz和对应的镜像资源初始化私服，再执行create_project_harbor.sh初始化项目名称
  ps:
   1.配置文件初始化的时候必须是dockerhub.kubekey.local，且没有账号密码，但是第四步的时候又需要填好账户密码
   2.create_project_harbor.sh脚本后面要加-k （小写k）

4.通过./kk create cluster -f config-sample.yaml -a kubesphere.tar.gz --with-packages初始化集群
  ps:
   1.千万记得password不要写成passwd
   2.初始化集群的时候要是干净的机器，不要多个集群共存
   3.daemon.json需要配置私服地址,hosts需要配置私服的DNS映射，并且一定要通过systemctl daemon-reload加载过，否则会出现各种奇怪的问题
   
   警示1：k8s对linux机器的内核版本有需求，如果过低，无法兼容更高的版本
   警示2：docker版本本身和kubesphere存在版本支持范围，需要特别注意
```

