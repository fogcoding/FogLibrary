## docker笔记



#### 由全量包直接安装docker

```properties
## 1、下载docker 离线安装包 ,下载地址如下：
 https://download.docker.com/linux/static/stable/x86_64/

## 2、将下载的包上传至服务器上
## 3、解压文件
tar -zxvf docker-20.10.9.tgz

## 4、复制文件
cd docker
cp ./* /usr/bin

## 5、创建docker.service文件
cd /etc/systemd/system/
touch docker.service

## 6、编辑docker.service 文件
vim docker.service
##################################################################################
[Unit]
Description=Docker Application Container Engine
Documentation=https://docs.docker.com
After=network-online.target firewalld.service
Wants=network-online.target
 
[Service]
Type=notify
# the default is not to use systemd for cgroups because the delegate issues still
# exists and systemd currently does not support the cgroup feature set required
# for containers run by docker
ExecStart=/usr/bin/dockerd --selinux-enabled=false
ExecReload=/bin/kill -s HUP $MAINPID
# Having non-zero Limit*s causes performance problems due to accounting overhead
# in the kernel. We recommend using cgroups to do container-local accounting.
LimitNOFILE=infinity
LimitNPROC=infinity
LimitCORE=infinity
# Uncomment TasksMax if your systemd version supports it.
# Only systemd 226 and above support this version.
#TasksMax=infinity
TimeoutStartSec=0
# set delegate yes so that systemd does not reset the cgroups of docker containers
Delegate=yes
# kill only the docker process, not all processes in the cgroup
KillMode=process
# restart the docker process if it exits prematurely
Restart=on-failure
StartLimitBurst=3
StartLimitInterval=60s
 
[Install]
WantedBy=multi-user.target

##################################################################################


## 7、 添加可执行权限
chmod +x docker.service

## 8、加载docker.service
## 注意，若修改了docker.service文件，则要重新加载该文件。
systemctl daemon-reload

## 9、启动docker
systemctl start docker
## 10、查看docker
systemctl status docker


## 11、查看docker版本
docker -v
[root@0001 system]# docker -v
Docker version 20.10.9, build c2ea9bc
```









#### 更换docker的默认工作目录

```
## 查看docker默认工作目录
du -sh /var/lib/docker

## 
systemctl stop docker 

## 
mkdir /sxapp/sxappopt/docker/lib
## 
rsync -avz /var/lib/docker/  /sxapp/sxappopt/docker/lib/

## 
vim /etc/docker/daemon.json
{
  "graph":"/data/docker/lib/docker"
}

## 重新加载docker 并重启docker
systemctl daemon-reload 
systemctl restart docker


## 检查目录是否修改成功
docker info


## 解决无法删除/var/lib/docker目录的问题
## 直接通过df -hl   把挂载目录 直接umount 即可


```









