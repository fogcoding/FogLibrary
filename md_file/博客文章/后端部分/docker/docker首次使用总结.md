## docker首次使用总结



#### 如何下载，安装，开始使用？

[下载地址](https://download.docker.com/linux/static/stable/) : https://download.docker.com/linux/static/stable/ 

[安装，使用说明](https://docs.docker.com/install/linux/docker-ce/binaries/) : https://docs.docker.com/install/linux/docker-ce/binaries/



#### 关键点

* 如何运行镜像并映射端口

  ```properties
  docker run -p 3306:3306 --name mysql_costomer_name -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
  -- 注：对于多个mysql实例，需要映射到固定的内部端口3306，否则会访问不到，暂且不知道为什么，记录待研究
  ```



* 如何进入对应的容器内部

  ```properties
  docker exec -it mysql_costomer_name bash
  ```



* 如何更新或修改容器内部的文件

  ```properties
  在宿主机器中修改完毕，再使用docker cp 命令更新进去
  ```

  



##### REF:

[[使用Docker搭建MySQL服务](https://www.cnblogs.com/sablier/p/11605606.html)](https://www.cnblogs.com/sablier/p/11605606.html)

