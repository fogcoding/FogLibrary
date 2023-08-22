## docker笔记



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









