##  Dockerfile编写问题记录



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
```



###  无法从中央仓库拉取镜像的情况下，创建镜像

```shell

## 保存镜像为文件
docker save -o file_name image_name


## 载入镜像
docker load --input file_name

docker load < file_name


```









