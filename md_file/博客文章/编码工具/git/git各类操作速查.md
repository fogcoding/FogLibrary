## git各类基本操作速查



#### 1.对缓存区的操作

```sql
-- 添加一个文件的追踪
git add file_name

-- 取消一个文件的追踪，并删除远程和本地文件
git rm file_name

-- 取消一个文件的追踪，删除远程，保留本地文件
git rm --cached file_name

-- 清除所有被手动删除文件的追踪
git rm $(git ls-files -d)

-- 重命名追踪文件
git mv file_a file_b

-- 不提交而将文件变动添加进入缓存区
git stash

-- 不提交且将文件变动添加进入缓存区，并为之命名
git stash save -a name_info

-- 查看储藏区列表
git stash list 

-- 查看本次缓存内容，-u表示查看具体内容
git stash show

-- 恢复储藏的内容,可指定，pop恢复后会删除恢复的stash
git stash apply

-- 清除stash,可指定
git stash drop

-- 清除所有stash
git stash clear

```



#### 2.对配置内容的操作

```sql
-- 针对当前用户，全局生效的配置
git config --global 

-- 针对当前项目生效的配置，覆盖 global 的配置
git config 

-- 取消生效
git config --global -unset-all

-- 常用的配置参数
-- 帐户名
user.name = your_username

-- 账户邮箱
user.email = your_email

-- 设置别名，例如执行以下命令，此后可用co代替checkout
alias.co checkout

-- 设置代理
git config --global http.proxy 10.167.32.133:8080

--取消代理
git config --system (或 --global 或 --local) --unset http.proxy

-- 设置当前分支的log提交模板
git config commit.template template_name

-- 设置全局的log提交模板
git config –global commit.template template_name

-- 设置文本编辑器，例如vi
git config –global core.editor name_vi

-- 设置命令的别名示例
git config --global alias.lg "log --graph"

```



#### 3.如何取消设置git config的设置

```sql
--  法一：git的config一般是隐藏在 .git/config，所以可以去用户目录下去找到对应的文件修改配置

-- 法二：unset 命令解除设置
git config --global --unset user.name

-- git config --global --edit

```





#### 4.对git工程项目的操作

* 初始化项目

```sql
-- 初始化工程的git相关配置（在本地由非git工程根目录，将其初始化为git工程）
git init

-- 克隆已存在的git工程
git clone http://url.git

-- 添加远程仓库
git remote add remote_name remote_url

-- 删除远程仓库
git remote rm remote_name

-- 重命名远程仓库
git remote rename old_name new_name

-- 更改远程仓库的地址
git remote set-url remote_name url_info 

-- 查看远程仓库信息
git remote -v

-- 初始化分支
-- 对于新建或者克隆项目，其分支一般默认为master分支
-- 而对于分支相关的具体操作，由于涉及到项目变动和提交操作，请在提交操作之后的分支部分，具体查看。

```



* 项目提交操作

```sql
-- 将指定文件的修改添加进入缓存区
git add fine_name

-- 将所有文件的修改添加进入缓存区
-- 可带参数：-V 表示列出变动文件，-p表示列出修改内容
git add .

-- 将缓存区的所有内容创建一次提交，并说明提交信息与修改内容，以后可以回滚到此时的状态
-- 可带参数：-V 表示显示diff信息，-a表示跳过暂存步骤，相当于add与commit一同执行
-- --amend表示覆盖上一次的提交，一般用于提交出错或者修改同一次提交，
-- 但同时带--no-edit参数后，不改变上一次提交信息
git commit -m "update_info"

-- 修改上一次提交的信息内容，文件不做修改
git commit --amend

-- 反向撤回提交，即新提交一个版本覆盖原来的提交
git revert commit_id
--or覆盖当前版本
git revert HEAD

-- 回退到n个版本之前状态，--hard表示会删除在0～n之间的变动与文件--soft则不会删除文件仅仅回退git信息
-- HEAD之后的提交都会消失，因为使用HEAD而消失的提交可以使用git reflog查看到，或者是git log --all
git reset --hard HEAD^n

-- 撤销对某个文件的修改，暂存区有就回到暂存区状态，没有就回到版本库状态
git checkout file_name

```



* 项目版本切换操作

```sql
-- 回退版本
-- 回退到n个版本之前状态，--hard表示会删除在0～n之间的变动与文件--soft则不会删除文件仅仅回退git信息
-- HEAD之后的提交都会消失，因为使用HEAD而消失的提交可以使用git reflog查看到，或者是git log --all
git reset --hard HEAD^n

```



* 分支与标签操作

```sql
-- 查看分支
git branch -v

-- 切换分支
git checkout branch_name

-- 新建分支
git branch new_branch_name

-- 新建一个从零开始没有提交历史的分支
git checkout --orphan branch_name

-- 拉取远程仓库的指定分支
git fetch remote_name branch_name

-- 拉去所有远程仓库的分支
git fetch --all

-- 重命名分支
git branch rename old_name new_name

-- 删除本地分支
git branch -d

-- 删除远程分支
git branch -r
-- or,意为推送一个空的分支到远程分支，即清空某个分支
git push remote_name blank_branch:remote_branch
-- or 删除远程远程仓库的分支，同时会删除本地的对应远程分支，-f参数可以选择强行覆盖，而不解决冲突
git push remote_name --delete branch_name

-- 将本地新建的分支推送到远程仓库
git push remote_name new_branch:new_branch

-- 拉取远程分支的文件,但不合并
git fetch remote_name branch_name

-- 拉取远程分支的文件，并与当前分支合并
git pull remote_name branch_name

-- 合并某个分支到当前分支
git merge current_branch merged_branch

-- 变基操作 rebase 是做代码重构和管理的高端操作，暂时不写




```



## 合并毫无关系的两个仓库或者分支

```sql
-- 拉取非配置分支的代码
git clone origin1 code

-- 合并无历史关联的配置分支
git pull origin2 cfg  --allow-unrelated-histories 

```





