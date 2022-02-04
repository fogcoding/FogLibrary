# [Git Commit 标准化](https://www.cnblogs.com/wubaiqing/p/10307605.html)

 ![img](https://img2018.cnblogs.com/blog/328599/201901/328599-20190123103809513-443527750.jpg)

 

**1 前言**
Git Commit Message 应该清晰明了，要用精简的语言说明本次提交的目的，其主要作用是为
了后续的搜索、版本的回滚、合并冲突的追溯等操作。 

我们在开发时一直以来对 Git Commit 格式有个约定俗称的要求，所以就没落实明确的规范。
因为没有明确的规范，就会导致提交的消息较为随意。甚至出现「“.”、”Update”」这样的消息。

直到我在 GitHub 上发现了这条 [Commits](https://github.com/kvenux/kvenux.github.io/commits/master) 时，才意识到提交信息也该规范起来。 

以下图举例，当代码出现 Bug 时，应该回滚到哪个版本？
回滚到 “朕与将军解战袍，芙蓉暖帐度春宵” 吗？
这条记录所变更的内容是啥，看概要我一概不知。 ︿(￣︶￣)︿

![img](https://img2018.cnblogs.com/blog/328599/201901/328599-20190123103755402-696694406.jpg)


为了解决规范问题，我参考了一些的开源项目，当发现 [commitizen](https://github.com/commitizen/cz-cli) 库时，才知道好多大型开
源（[AngularJS](https://github.com/angular/angular)、[VueJS](https://github.com/vuejs/vue/commits/dev)）项目早已使用了它。所以在接下来我会介绍一下 commitizen 工具所
使用 Google AngularJS 规范。


**2 规范介绍**
这次主要介绍 AngularJS 的规范，它是由 Google 推出的一套提交消息规范标准，也是目前使
用范围最广的规范。有一套合理的[手册](https://docs.google.com/document/d/1QrDFcIiPjSLDn3EL15IJygNPiHORgU1_OOAqWjiDU5Y/edit)也较为[系统化](https://github.com/angular/angular.js/blob/master/CONTRIBUTING.md#toc10)；并且还有配套的工具可以供我们使用。

说白了，规范就是用工具进行强约束。单看规范比较简单，所以先让大家先看看面，知道他的
大体规则后，在来讲细节。

规范执行方案如下：　　

![img](https://img2018.cnblogs.com/blog/328599/201901/328599-20190123104101160-845368160.png)


既然有了方案，就会按照某些规则执行，以下是 Google AnguarJS 规范的要求：

**规范目标**
\- 允许通过脚本生成 CHANGELOG.md
\- 可以通过范围的关键词，快速的搜索到指定版本

```
git log HEAD --grep feat(package.json) # 在package.json文件里新增的特性。 
```

**格式要求**

```
<type>(<scope>): <subject>
<BLANK LINE>
<body>
<BLANK LINE>
<footer>
```

\- 消息只占用一行，任何行都不能超过 100 个字符
\- 允许使用 GitHub 以及各种 Git 工具阅读消息
\- 提交消息由页眉、正文和页脚组成，由空行分隔

<type>
代表某次提交的类型，比如是修复一个 bug 或是增加一个 feature，类型如下：
类型 描述

![img](https://img2018.cnblogs.com/blog/328599/201901/328599-20190123104458539-2041029483.png)
<scope>
范围可以是指定提交更改位置的任何内容，如：
\- 对 package.json 文件新增依赖库，chore(package.json): 新增依赖库
\- 或对代码进行重构，refacto(weChat.vue): 重构微信进件

<subject>
如果没有更合适的范围，可以直接写提交内容

 

**Commit 实战**
提交一条依赖库变更，type 为 chore（增加依赖库）；等提交完成后，使用 Git 工具进行搜索。
此时搜索类型是 chore(package.json)，所以就能知道 package.json 文件所有的历史变更。

```
# 新增一条 Commit 记录
git commit -m 'chore(package.json): 新增 AngularJS 规范，Commit 时会自动调用钩子（GitHook）来判断 Message 是否有效'

# 搜索跟 package.json 文件相关的历史记录
git log HEAD --grep chore(package.json)
```


**3 工具介绍**
因为是 Google AngularJS 的标准规范，所以提供了多种工具。如生成 CHANGELOG.md，提
交工具，检查工具。

工具列表：
\1. 提交工具 [commitizen](https://github.com/commitizen/cz-cli)，如果是初学者，可以使用 commitizen 帮助我们生成消息
\2. 生成 [CHANGELOG.md](https://github.com/conventional-changelog/standard-version)，把 Git Commit Message 的消息自动生成 CHANGELOG.md
\3. Message [检查](https://github.com/marionebl/commitlint)，是否有 “不符合” 规范的内容，可以在 GitHook 中使用

提交以及检查工具相对来说简单，大家自学即可，所以我以生成 CHANGELOG.md 举例。

```
# 安装 CHANGELOG 生成器
yarn global add standard-version

# 生成文档 
standard-version --first-release
```

文档生成后，当前目录下就有 CHANGELOG.md 文件了，如果是 Node 项目，也会自动更新
package.json version 的版本号

![img](https://img2018.cnblogs.com/blog/328599/201901/328599-20190123104719936-72719728.png)

这是根据 Git Commit Message 历史记录所生成的 CHANGELOG.md，在也不用手写了。(￣▽￣)"

![img](https://img2018.cnblogs.com/blog/328599/201901/328599-20190123104805995-1084449485.jpg)

 

**4 参考链接**
**commitizen**
https://github.com/commitizen/cz-cli
https://github.com/conventional-changelog/conventional-changelog
https://github.com/marionebl/commitlint

**中文规范**
https://github.com/feflow/git-commit-style-guide

**AngularJS 规范**
https://docs.google.com/document/d/1QrDFcIiPjSLDn3EL15IJygNPiHORgU1_OOAqWjiDU5Y/edit#heading=h.greljkmo14y0
https://github.com/angular/angular.js/blob/master/CONTRIBUTING.md#toc10