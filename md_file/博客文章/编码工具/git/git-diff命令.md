



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



#### 比较文件变动中文乱码

```shell
-- 执行如下命令，修改core.quotepath参数即可。

git config --global core.quotepath false
```





