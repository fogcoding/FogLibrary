## git统计所有代码行数



#### 显示所有文件内容及行数

```
git ls-files | xargs cat | wc -l
```



#### 显示所有文件的所有内容加行数

```
git ls-files| xargs wc -l
```

