##  Dockerfile编写问题记录



#### 配置镜像加速

````
vim /etc/dockerd/daemon.json

{
"registry-mirrors": [
"https://kfwkfulq.mirror.aliyuncs.com",
"https://2lqq34jg.mirror.aliyuncs.com",
"https://pee6w651.mirror.aliyuncs.com",
"https://registry.docker-cn.com",
"http://hub-mirror.c.163.com"
]
}


## 加载配置
sudo systemctl daemon-reload
## 重启
sudo systemctl restart docker

````



#### CMD命令不执行

```shell

## 表现：tomcat在启动后，并不执行dockerfile中写的CMD命令

## 原因：在DockerFile中写入的CMD后面的命令不执行主要是因为启动的时候指定了参数

## 解决办法：不指定各项启动参数
docker run -it 容器名:TAG 

## 成功


```



#### EXPOSE暴露端口不生效

```shell
## 原本在dockerfike中写明了暴露8080端口，但是在启动后，并不能通过8080端口去访问

## 解决办法：在启动的时候设置好端口映射参数
docker run -d -p 8080:8080 容器id 

## 2022-03-07 补充确认
EXPOSE：功能为暴漏容器运行时的监听端口给外部，但是EXPOSE并不会使容器访问主机的端口，如果想使得容器与主机的端口有映射关系，必须在容器启动的时候加上 -P参数

```



###  无法从中央仓库拉取镜像的情况下，创建镜像

```shell

## 保存镜像为文件
docker save -o file_name image_name

## 载入镜像
docker load --input file_name

docker load < file_name


```



#### 无法用http登录harbor仓库

```shell
错误信息:dial tcp xxxxx:port getsocket connection refused

-- 修改配置文件
vim /etc/sysconfig/docker

OPTIONS中添加 --insecure-registry=10.7.1.216/24

-- 重启docker服务
systemctl restart docker 

-- 登录私库
docker login -u admin -p Harbor12345


-- 2022-01-26
以上这种配置方式，极其不推荐，来自于破坏了整个配置结构的层级的处理办法
正确的办法应该是直接去新建/etc/docker/daemon.json
然后在这个文件中写明
{
"insecure-registries":["10.7.1.216/24"]
}

-- 据沟通交流得知，无法直接默认使用某个私库而越过docker.io的步骤


-- 总结：
1.一般普通的配置写在/etc/docker/daemon.json中，可以考虑使用域名的配置动态调整私服地址
2./usr/lib/systemd/system/docker.service 配置了基本的环境配置参数
这里有个问题，docker原始的配置文件和目录是哪些？


```



#### 普通用户无法使用docker命令

```shell
方法1：给与普通用户root权限

方法2：
-- 添加docker用户组
groupadd docker

-- 将某个用户添加到docker用户组
gpasswd -a $user docker

-- 更新docker用户组
newgrp docker

-- 重启docker
systemctl restart docker

-- 切换对应用户后，执行docker尝试
docker ps -a

```



#### 使用了expose配置dockerfile仍旧无法访问

```shell
## 只是设置了暴露端口，而没有设置内外网映射端口的关系
## 在启动时，需要使用-p 参数设置映射关系

docker run -p 8081:8081 -d image:tag 

```



#### docker stop 容器没反应

```
## 当使用stop命令显示up状态，kill又显示不在执行中时
## 重启docker服务
systemctl restart docker 

## 重启所有容器
docker restart $(docker ps -aq)

```



#### 从官方仓库拉取的镜像配置启动命令和环境

```

#### 查看镜像的启动命令
docker ps -a --no-trunc

#### 修改镜像启动命令
nvidia-docker run -it images:v1 /bin/sh

```



####  镜像容器中，数据编码乱码问题解决

```shell
## 设置容器内对应的字符编码为utf8
ENV LC_ALL=zh_CN.utf8
ENV LANG=zh_CN.utf8
ENV LANGUAGE=zh_CN.utf8
RUN localedef -c -f UTF-8 -i zh_CN zh_CN.utf8
```



#### docker0网卡删除

```sql
-- 有时候注册到注册中心的地址会显示172.17.0.1这种明显的虚拟ip地址，可以尝试查看本地的ip清单
-- 如果来自于docker0 可以清理掉docker0网卡的身份，从而解决地址不正确的问题

sudo ip link delete docker0
```







