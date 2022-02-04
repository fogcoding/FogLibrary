



```shell
## 远程新增加的分支
git remote show origin | grep  "next fetch will store in remotes/origin" | awk '{print $1}'

## 远程有更新
git remote show origin | grep "local out of date"

## 远程分支被删除，本地有的分支(需要截取最后一个"/"字符左边的字符才是分支名)
## (git remote prune origin 可以强制刷新本地仓库到与远程仓库一致)
git remote show origin | grep "git remote prune" |awk '{print $1}'

## 远程有回滚操作的分支
git remote show origin | grep "fast-forwardable" |awk '{print $1}'


new_branches=$(git remote show origin | grep  "next fetch will store in remotes/origin" | awk '{print $1}')
update_branches=$(git remote show origin | grep "local out of date")
deleted_branches=$(git remote show origin | grep "git remote prune" |awk '{print $1}')
roll_branches=$(git remote show origin | grep "fast-forwardable" |awk '{print $1}')

## 假如存在有回滚操作的分支，先改名本地分支做备份
git branch -d xxxxx

## 假如存在本地有，远程无的情况,将本地分支改名做备份用
git remote prune

(以上两步改名字的时候，要避免重复加尾缀，使得分支名长度逐渐变得超长)

## 拉取远程分支变动到本地
git pull --all

## 本地新建分支关联缺失的远程分支


## 更新有更新的分支
```

