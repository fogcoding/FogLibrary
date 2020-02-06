```sql
-- 配置部分：

-- 启动入口：

-- * 启动目录需要设置bin目录下的git.exe为启动程序，不是gitcmd.exe
-- * 为了能够在任何目录使用git命令，需要在环境变量里面设置git.exe的路径。
-- * 配置完使用cmd窗口可以任何目录使用git命令，如果在Android stduio的terminal使用却无法识别，此时重启电脑一下就好了

--  用于提供自己的名字和email地址

* git config --global user.name [name]
* git config --global user.email []

-- 保存登陆的用户密码，避免每次都要重新输入

git config --global credential.helper store

-- 查看配置信息列表

git config --list

-- 克隆项目

 git clone URL

-- 拉取项目变动

 git pull res_name 

-- 上传更改

 git commit -m "上传说明"
 git push


-- 撤销上传（版本回退）

 git reset --hard HEAD~N (到退N次提交前)
 git commit 
 git push -f  （强制传送到远程respostiries）

-- 注意：-f 是一个强制推送的操作，是危险操作！设置倒退N个版本之前，那么在N个版本之前到最新版本的内容会全部消失，若是同时有人想该respostories上传了文件，也会一并消失


-- 直接由本地，上传文件到指定respostories

 git init
 git remote add res_name URL
 git add .
 git commit -m "上传说明"
 git pull res_name master
 git push res_name master

-- 添加文件的追踪

 git add file_name
 git status    (可以看见文件追踪的变动情况)
 git commit -m "提交说明"   (这一步特别注意，不知道为什么，使用 git commit 是无法生效的，必须按照左边的格式提交)
 git push res_name branch_name 

-- 删除远程分支

 git origin --delete branch_name


-- 拉取特定分支到本地

git fetch remote_name remote_branch_name:local_branch_name

-- 一次拉取所有远程仓库的分支到本地，再创建本地的对应分支
git init 
git remote add remote_name xxx.git
git pull -all
-- 查看远程分支
git branch -r
-- 创建本地分支并切换到对应分支且对应远程分支
git checkout -b local_branch_name remote_name/remote_branch_name











```

