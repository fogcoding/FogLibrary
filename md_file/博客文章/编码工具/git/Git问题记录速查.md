## Git问题记录速查



#### 文件路径过长

```sql
-- 设置长路径支持，再添加
git config core.longpaths true
```





####  error: The following untracked working tree files would be overwritten by

```sql
-- git忽略大小写导致的

git config --add core.ignorecase true
```

