



#### 比较同分支文件，当前版本与上一版本的变化情况

```shell
git diff --name-status HEAD~ HEAD
```



#### 比较两个分支的不同

```shell
git diff --name-only boot boot-vue-ele
```



#### 筛选文件变动情况

```shell
git diff --diff-filter=RMC
```



