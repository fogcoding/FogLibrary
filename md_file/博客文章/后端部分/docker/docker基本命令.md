## Docker基本命令



#### docker 主程序操作

* 启动Docker   systemctl start docker
* 停止Docker   systemctl stop docker
* 重启Docker   systemctl restart docker
* 开机启动Docker   systemctl enable docker
* 查看Docker概要信息   systemctl info
* 查看Docker帮助文档   systemctl --help
* 查看Docker版本信息   systemctl version



#### docker 镜像操作

* 列出所有的镜像 docker images
* 搜索镜像 docker search
* 下载镜像 docker pull
* 删除镜像 docker rmi



#### docker 容器操作

* 创建并启动容器 docker run [-参数] image   

  -i :以交互模式运行容器，通常加-t一起使用

  -t:为容器重新分配一个伪输入终端，通常与-i同时使用

* 列出容器 docker ps

* 退出容器 exit

  exit：容器停止并退出

  Ctrl+Q:容器不停止退出

* 进入容器 docker attach 容器ID 或者 容器名

* 启动容器 docker start 容器ID 或者 容器名

* 重启容器 docker restart 容器ID 或者 容器名

* 停止容器 docker stop 容器ID 或者 容器名

* 删除容器 docker rm 容器ID    

  -f:表示强制删除容器，如果在运行会先停止运行容器再删除容器

* 删除多个容器 docker rm c1 c2 c3 (中间空格隔开)

* 删除所有容器 docker rm -f $(docker ps -qa)

* 以守护方式启动一个容器（即启动后并不直接进入容器） docker run -di --name 容器别名 镜像ID

* docker端口映射命令 

  docker run -it -p 8888(宿主机的端口，暴露给外网的端口):8080（容器里的端口，容器内部执行的端口） tomcat

  docker run -it -p  tomcat


















