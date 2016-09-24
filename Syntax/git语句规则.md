## git命令学习资源
* [git官方教程翻译](https://git-scm.com/book/zh/v2/%E8%B5%B7%E6%AD%A5-%E5%85%B3%E4%BA%8E%E7%89%88%E6%9C%AC%E6%8E%A7%E5%88%B6)
* [git官方教程英文版原文](https://git-scm.com/doc)
* [git常用命令总结 - 圣骑士windy](http://www.cnblogs.com/mengdd/p/4153773.html)

---

## 重点摘要

### git操作的三个区域
![](https://git-scm.com/book/en/v2/book/02-git-basics/images/lifecycle.png)

---

###  文件的状态控制

* git staus查看状态，git staus -s / --short 显示简洁版的文件信息

* 简洁版信息的代号：

> * ?? 未追踪文件

> * A 新添加在暂存区的文件

> * MM 修改过的文件 ，出现右边的M表示被修改过未放入暂存区，左边的M表示已经修改并放入暂存区，MM同时出现表示该文件修改了并放入暂存区后，又修改过

* 忽略某些文件: 在.gitignore文件中查看

* 查看工作区域和暂存区域的文件之间的区别 git diff --staged(只显示暂存的改动，与提交过的文件无关)

* 提交更新 commit:

> * 附带提交说明的指令： git commit -m "提交操作的说明信息"

> * 跳过暂存区域提交 git commit -a -m "提交操作的说明信息"   (此方法会越过git add file的操作，直接将所有被追踪的文件暂存起来一并提交)



* 删除文件 git rm:

> * 文件彻底删除：使用git rm file_name删除了某个文件，下一次提交后该文件不再被追踪，但删除前曾将该文件放入暂存区，则必须使用 -f 来强制删除

> * 在git中删除，但文件保留在本地：git rm --cached file_name

> * git rm dir/\*.log 删除dir文件目录下所有的.log文件

> * git rm \*~ 删除~结尾的所有文件

* 文件改名: git mv file_from file_to (将文件名字从file_from改为file_to)

---

### 查看提交历史 
 * 基本操作： git log
 * 查看最近N次的提交： git log -p -N
 * 查看每次提交的简略统计信息： git log --stat
 * 自行设定显示信息的格式： git log --pretty=format"%h - %an,%ar : %s"

> * %H - 提交对象（commit）的完整哈希字串

> * %h - 提交对象的简短哈希字串

> * %T - 树对象（tree）的完整哈希字串

> * %t - 树对象的简短哈希字串

> * %P - 父对象（parent）的完整哈希字串

> * %p - 父对象的简短哈希字串

> * %an - 作者（author）的名字

> * %ae - 作者的电子邮件地址

> * %ad - 作者修订日期（可以用 --date= 选项定制格式）

> * %ar - 作者修订日期，按多久以前的方式显示

> * %cn - 提交者(committer)的名字

> * %ce - 提交者的电子邮件地址

> * %cd - 提交日期

> * %cr - 提交日期，按多久以前的方式显示

> * %s - 提交说明
 
 * [更多的历史纪录查询信息](https://git-scm.com/book/zh/v2/Git-%E5%9F%BA%E7%A1%80-%E6%9F%A5%E7%9C%8B%E6%8F%90%E4%BA%A4%E5%8E%86%E5%8F%B2#limit_options) 
 
---

### 撤销操作
 * 添加忘记提交的文件 git commit --amend
 * 取消文件添加进暂存区的操作 git reset HEAD file_name
 
 >  * 添加--hard 后，该指令成为一个危险指令（可能使本地的相关文件也消除）， git reset --hard HEAD file_name
 
 >  * 也可以倒退回上N次提交前的状态，git reset --hard HEAD~N
 
 * 撤销对文件的修改 git checkout -- file 

> * 这是一个危险操作，因为你对该文件做的所有修改都会消失，而系统执行的任务就是重新从远程仓库拷贝一份该文件来覆盖他

---

### 远程仓库的使用
* 查看所有远程仓库信息： git remote
* 查看远程仓库的简写及其URL： git remote -v
* 添加远程仓库： git remote add res_name URL
*  从远程仓库领取： 

> * git fetch res_name   仅仅拉取远程仓库有，而自己没有的文件，并且不会执行合并或修改当前的工作内容，需要手动合并

> * git pull res_name   通常会从最初克隆的服务器上抓取数据并自动尝试合并到当前所在的分支。

* 推送到远程仓库 git push res_name branch_name

> * 只有当该远程仓库在此之前没有人推送过，且自己有对该仓库的推送权限时才可以，否则会被拒绝。

> * 若自己有权限，别人在此之前推送过一次，那么需要拉去一次变化后，再行推送

* 查看远程仓库 git remote show res_name
* 远程仓库的重命名和移除
> * git remote rename name_from name_to
> * git remote rm res_name

---

### 打标签
* 查看标签： git tag
* 搜索制定的标签： git tag -1 'v1.8.5*'
* 创建附注标签： git tag -a v1.8 -m "标签说明内容" 
* 创建轻量标签： git tag note_name
* 查看标签信息与提交信息： git show v1.8
* 后期追加标签： git tag -a v1.8 commit_hash_values
* 共享标签： git push res_name tag_name / git push res_name --tags

> * 附注标签会保存提交者相对的完整信息，轻量标签信息更少

> * 共享标签是因为push操作默认不提交标签内容

--

### git指令修改
* 更改指令方法: git config --global alias.修改后的内容 修改的指令
