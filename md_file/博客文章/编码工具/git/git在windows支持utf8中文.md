

```properties
git config --global core.quotepath false 
git config --global gui.encoding utf-8
git config --global i18n.commit.encoding utf-8 
git config --global i18n.logoutputencoding utf-8 
# bash 环境下
export LESSCHARSET=utf-8
# cmd环境下：
set LESSCHARSET=utf-8
```

注：以上失效



#### 不解决根本问题的办法

```sql
-- 一次性输出所有的日志，可以直接看到所有的日志
git --no-pager log -n 3
```



