## 克隆项目
* git clone URL

## 拉取项目变动
* git pull res_name 

## 上传更改
* git commit -m "上传说明"
* git push


## 撤销上传（版本回退）
* git reset --hard HEAD~N (到退N次提交前)
* git commit 
* git push -f  （强制传送到远程respostiries）

> 注意：-f 是一个强制推送的操作，是危险操作！设置倒退N个版本之前，那么在N个版本之前到最新版本的内容会全部消失，若是同时有人想该respostories上传了文件，也会一并消失


## 直接由本地，上传文件到指定respostories
* git init
* git remote add res_name URL
* git add .
* git commit -m "上传说明"
* git pull res_name master
* git push res_name master
