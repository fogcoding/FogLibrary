## Git无法用http协议拉取代码



#### 解决步骤

```sql
-- 安装curl-devel
yum install curl-devel

-- 配置路径执行
将 /usr/libexec/git-core 纳入 PATH，至少在使用 git 之前，设置一下PATH。

$ PATH=$PATH:/usr/libexec/git-core

git-core下面有git-remote-https等



```

