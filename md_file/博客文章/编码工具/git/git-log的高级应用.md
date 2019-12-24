## git-log的高级应用



#### log命令参数

---



#### 基础知识

* 基本操作： git log

* 查看最近N次的提交： git log -p -N

* 查看每次提交的简略统计信息： git log --stat

* 自行设定显示信息的格式： git log --pretty=format"%h - %an,%ar : %s"

| 格式符 |                    意义                     |
| ------ | :-----------------------------------------: |
| %H     |      提交对象（commit）的完整哈希字串       |
| %h     |           提交对象的简短哈希字串            |
| %T     |        树对象（tree）的完整哈希字串         |
| %t     |            树对象的简短哈希字串             |
| %P     |       父对象（parent）的完整哈希字串        |
| %p     |            父对象的简短哈希字串             |
| %an    |            作者（author）的名字             |
| %ae    |             作者的电子邮件地址              |
| %ad    | 作者修订日期（可以用 --date= 选项定制格式） |
| %ar    |     作者修订日期，按多久以前的方式显示      |
| %cn    |           提交者(committer)的名字           |
| %ce    |            提交者的电子邮件地址             |
| %cd    |                  提交日期                   |
| %cr    |       提交日期，按多久以前的方式显示        |
| %s     |                  提交说明                   |

 * [更多的历史纪录查询信息](https://git-scm.com/book/zh/v2/Git-%E5%9F%BA%E7%A1%80-%E6%9F%A5%E7%9C%8B%E6%8F%90%E4%BA%A4%E5%8E%86%E5%8F%B2#limit_options)



```sql

-- 最基本的log命令
git log

-- log 可以存在的参数：
-- 没有时间继续研究了，得空再来进行log信息格式化和抓取的具体实现


```





#### 如何通过代码获取log信息

> 目前已知，可以自动导出为txt文件和excel文件。



#### log信息格式化和对象化

* 如何规范日志提交格式，以及实现提交代码的权限控制功能？

1. 在项目根目录新建commit_template模板文件,内容如下：

```powershell
[部门][项目]:

问题原因:

解决方法:

变更类别:

适用机型:

验证建议:

关联变更项：

任务 Id:
```

2. 设置当前分支的提交模板

```sql
-- 当前分支
git config commit.template [模板文件名]   
-- 全局
git config --global commit.template [模板文件名]   
```

3. 文本编辑器

```sql
-- 设置文本编辑器为vim
git config -global core.editor vim
```

4. 使用commit即可触发模板

5. 打开.git文件夹里的hooks文件夹中的commit-msg.sample文件，编写脚本并改名为commit-msg，完成后每次提交操作都会触发此脚本的执行，实现格式的检查




* 如何将对应格式的信息进行处理？

1. 目前已知git-log命令的文本可以直接输出为txt和csv格式，剩下的处理是代码问题了。

```sql
-- windows中示例
-- 导出为txt格式
git log -v >d:\log.txt
-- 导出为csv格式
git log -v >d:\log.csv

```





#### 存储在数据库中，形成再开发的基础



