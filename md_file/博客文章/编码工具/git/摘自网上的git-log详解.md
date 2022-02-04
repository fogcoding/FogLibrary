# [Git系列之git log高级命令](https://segmentfault.com/a/1190000008039809)

[git](https://segmentfault.com/t/git)[版本控制](https://segmentfault.com/t/版本控制)[版本管理](https://segmentfault.com/t/版本管理)[版本管理工具](https://segmentfault.com/t/版本管理工具)

 阅读约 13 分钟

[原文地址](https://www.atlassian.com/git/tutorials/git-log)

使用任何版本控制工具的目的都在于记录你代码的变化。这可以给予你查看项目历史的能力，去发现谁做出了贡献，弄清楚何时产生了bug，回滚到错误的修改。但是，如果你无法定位，获取这些历史记录将变得毫无意义。这也是`git log`命令存在的理由。

我想你因该能用基本的`git log`命令来显示提交。但是，你可以通过向其传入不同的参数来控制输出不同的结果。

`git log`的高级命令可以被分为两类：格式化每条commit的展示与过滤展示出的commit。总之，这两项技能让你回到项目的任意位置，获取到任何你可能需要的信息。

## 格式化日志输出

首先，本文将会介绍一些格式化*git*输出的方法。多数都以标志的形式出现，让你能从git日志中获取更多／更少的信息。

如果你喜欢默认的git日志输出格式，你可以使用git config的同名函数为下面讨论的任意一种格式化选项创建缩写。请参阅`git config`命令如何设置别名。

### Oneline

**--oneline** 标记的作用是把每一个提交信息压缩为一行。默认情况下只会展示提交 ID与提交信息的首行。`git log --oneline`的结果如下：

```
0e25143 Merge branch 'feature'
ad8621a Fix a bug in the feature
16b36c6 Add a new feature
23ad9ad Add the initial code base
```

这是一个得到你项目高度概述的有效方法。

### Decorating

多数情况下，了解每条提交与与那个分支／标签关联是很有用的。**--decorate** 标记让`git log`展示所有指向每个提交引用（如分支，标签等）。

这可以与别的控制选项一起使用。如执行`git log --oneline --decorate`命令建辉得到下面格式的提交历史：

```
0e25143 (HEAD, master) Merge branch 'feature'
ad8621a (feature) Fix a bug in the feature
16b36c6 Add a new feature
23ad9ad (tag: v0.9) Add the initial code base
```

由此我们可以获悉顶部commit被检出（用HEAD表示）同时它也位于主分支的末端。feature分支指向第二个提交，第四个提交被标记为v0.9。

分支，标记，HEAD与提交历史几乎涵盖了你Git仓库中的所有信息，因此这样可以将你仓库的结构以一个更有逻辑的方式展示出来。

### Diffs

`git log`命令包含了多个用于展示每个提交差异的选项。其中最常用的两个选项是 **--stat** 与 **-p**。

**--stat**选项通过比较每个提交展示了插入与删除的数量（请注意，修改将被表示为1行插入与1行删除）。这在你想要获得每个提交中变化的摘要时很有用。例如，下面的提交向*hello.py*文件添加了67行并移除了38行：

```
commit f2a238924e89ca1d4947662928218a06d39068c3
Author: John <john@example.com>
Date:   Fri Jun 25 17:30:28 2014 -0500

    Add a new feature

 hello.py | 105 ++++++++++++++++++++++++-----------------
 1 file changed, 67 insertion(+), 38 deletions(-)
```

其中在文件名边上的*-*与*+*记号代表经过比较后，相关变化的数量。他让你知晓每个提交的变化能在那里找到。

如果你想要查看每个提交实际的变化，你可以使用带**-p** 选项的`git log`命令，来展示所有描述该提交的差异：

```
commit 16b36c697eb2d24302f89aa22d9170dfe609855b
Author: Mary <mary@example.com>
Date:   Fri Jun 25 17:31:57 2014 -0500

    Fix a bug in the feature

diff --git a/hello.py b/hello.py
index 18ca709..c673b40 100644
--- a/hello.py
+++ b/hello.py
@@ -13,14 +13,14 @@ B
-print("Hello, World!")
+print("Hello, Git!")
```

对于有大量改变的提交，结果将变的冗长。通常，如果你需要展示所有的改变，你可能在找寻一个具体的变化。为此，你需要使用 **pickaxe** 。

### 短日志

`git shortlog`是用于创建发布公告的一种特殊的`git log`命令。按作者对每个提交分组，并展示每个提交信息的第一行。这种方式能很容易看出谁参与了工作。

例如，两个开发者向一个项目贡献了五次提交，`git shortlog`的输出会像下面这样：

```
Mary (2):
      Fix a bug in the feature
      Fix a serious security hole in our framework

John (3):
      Add the initial code base
      Add a new feature
      Merge branch 'feature'
```

通常，`git shortlog`会按照作者的名字来排序，但你也可以通过**-n**选项来按照每个作者的提交数量排序。

### Graphs

添加**--graph** 选项将会绘制一幅表示分支结构提交历史的ASCII图。该选项通常会结合**--oneline**与**--decorate**一起使用，使得能更加容易地看出提交所属的分支：

```
git log --graph --oneline --decorate
```

对于含有两条分支的简单仓库，使用上述命令可得到一下结果：

```
*   0e25143 (HEAD, master) Merge branch 'feature'
|\  
| * 16b36c6 Fix a bug in the new feature
| * 23ad9ad Start a new feature
* | ad8621a Fix a critical security issue
|/  
* 400e4b7 Fix typos in the documentation
* 160e224 Add the initial code base
```

星号表示分支中的一条提交，因此上表可以看出23ad9ad与16b36c6位于一个分支上，而其他提交位于主分支上。

虽然对于小仓库这是一个不错的选择，但是你可能需要在拥有繁杂分支的项目中使用更加全面的可视化工具如**gitk**或**SourceTree**。

### 自定义格式

你所有其他的`git log`格式的需求，都可以使用**--pretty=format:""**选项来实现。这可以让你使用 *printf* 风格的占位符来展示每条提交。

例如，以下命令中分别使用 *%cn，%h* 和 *%cd* 字符来替代提交者姓名，提交哈希的缩写以及提交的时间。

```
git log --pretty=format:"%cn committed %h on %cd"
```

上述命令将会得到以下格式的结果：

```
John committed 400e4b7 on Fri Jun 24 12:30:04 2014 -0500
John committed 89ab2cf on Thu Jun 23 17:09:42 2014 -0500
Mary committed 180e223 on Wed Jun 22 17:21:19 2014 -0500
John committed f12ca28 on Wed Jun 22 13:50:31 2014 -0500
```

关于完整的占位符列表可以参阅 *git log 手册 美化样式 一节*

除了能让你查看你感兴趣的信息外，**--pretty=format:""** 还有特别的用途即能将结果作为参数传给另一个git命令。

## 过滤提交历史

学会如何格式化每个提交的显示方式我们已经成功了一半。另一半是要学会如何控制提交历史。在本文的余下部分将会介绍一些从项目历史中筛选出特定提交的`git log`高级方法。所有这些方法都可以和上述的格式化选项结合使用。

### 按数量输出

最基础的`git log`过滤选项就是限制输出提交的数量。当你只在意最近的一些提交时，它可以帮你解决浏览所有提交的困扰。

通过使用 **-** 选项可以限制日志的输出数量。例如下面命令将会只展示最近三条提交：

```
git log -3
```

### 按日期

如果想要从某个特定的日期开始显示提交，你可以使用 **--after** 或 **--before** 来按日期过滤提交。该参数接收多种日期格式。例如下面命令只显示了2014年七月一日（含本日）之后创建的提交：

```
git log --after="2014-7-1"
```

你也可以传入像“1 week ago”或“ yesterday”这样格式的参数：

```
get log --after="yesterday"
```

要搜索两个时间点之间创建的提交，可以同时提供 **--before** 与 **--after** 日期。比如要展示在2014年七月一日到2014年七月四日之间的提交你可以使用：

```
git log --after="2014-7-1" --before="2014-7-4"
```

另外，**--since** 与 **--after** ， **--until** 与 **--before** 用法一致。

### 按作者

当你只查看特定提交者的工作时，使用 **--author** 选项来过滤。该选项接受一个正则表达式，并返回所有匹配到格式的作者的提交。如果你明确地知道你需要查看谁的提交，你可以使用简单的传统的字符串而不必使用正则：

```
git log --author="John"
```

这将展示所有作者名字中含“John”的提交。作者名字并非需要完全一致，仅需要包含即可。

通过正则表达式可以搜索更复杂的结果。例如下面的代码将搜索出“Mary”或“John”的提交。

```
git log --author="John\|Mary"
```

值得注意的是，作者的邮箱也被包含在作者名字中，所以也可以使用该方式来按邮箱搜索。

如果您的工作流将提交者与作者分开，则需要使用 **--committer** 选项来到达到筛选的目的。

### 按消息

使用 **--grep** 选项来按提交信息筛选。该方法与上述 **--author** 一致，不过搜索内容换成了提交信息。

如果你的团队在每个提交信息中都包含了相关问题的编号，那么你可以使用下面命令来输出关于该问题的提交：

```
git log --grep="JRA-224:"
```

通过 **-i** 参数可以在筛选中忽略大小写。

### 按文件

有些时候，你只对发生在某个特定文件中的变化感兴趣时。为了展示关于这个文件的历史你所需要做的就是传入文件路径。例如，下面命令会返回影响foo.py／bar.py文件的提交：

```
git log -- foo.py bar.py
```

**--** 标记用于告知`git log`命令随后的参数是文件名而非分支名。如果命令中并不包含分支名，该标记也可省略。

### 按内容

也可以搜索对特定代码的添加或删除的提交。这种采用 **-S""** 格式的方式被称为 **pickaxe** ，例如，你想要知道字符串“Hello, World!”被添加的提交你可以使用以下命令：

```
git log -S"Hello, World!"
```

也可以使用正则表达式，只需要用 **-G""** 代替上述格式即可。

这是一个极其强大的纠错工具，因其可以定位所有影响特定代码的提交。甚至可以显示复制或移动到另一个文件的提交。

### 按范围

你可以通过传入一个范围来查询该范围内的提交。该范围由以下格式指定，其中<since>和<until>是提交引用：

```
git log <since>..<until>
```

当使用分支引用作为参数时，此命令特别有用。 这是一个简单的方法来显示两个分支之间的差异。 考虑以下命令：

```
git log master..feature
```

**master..feature** 包含所有不在主分支中的功能分支的提交。换句话说，这显示了特性分支从主分支分离后的历史进程。可以用下图理解：

![图片描述](https://segmentfault.com/img/bVHTGi?w=1402&h=1192)

注意如果你变换范围种的顺序（feature..master），你将得到所有在主分支而不在特性分支上的提交。如果日志输出了这两个版本的提交，则意味着你的历史线有了分叉。

### 筛选合并提交

默认情况下，日志会输出合并提交。但是如果你的团队使用的是 **always-merge** 策略，那么你的项目历史中将会包含大量无用的提交。
（注： **always-merge** 策略指对于上游的更改合并（merge）到主题分支而非rebase）

你可以通过 **--no-merges** 选项来过滤掉日志中的合并提交：

```
git log --no-merges
```

另外，当你只对合并提交感兴趣时，可以使用 **--merges** 选项只输出合并提交：

```
git log --merges
```

## 总结

现在你应该具备使用`git log`高级命令去按需展示日志提交记录的能力了。

该新技能将成为你Git工具包中的重要组成部分，请记住`git log`命令常与其他Git命令联用。通过使用`git log`命令来找到你需要的提交后，通过`git checkout`，`git revert`或其他工具控制提交历史。所以你要持续学习git高级命令。