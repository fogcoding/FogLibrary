# [Gitignore的规则定义](https://www.cnblogs.com/zhihang/p/10611475.html)

.gitignore 文件用于配置git需要管理文件的范围。

官方文档 ：https://git-scm.com/docs/gitignore

匹配符的解释：

\# 　　　　用于注释

\* 　　　　匹配除'\'之外的多个所有字符 #例：*.txt 所有的txt文件

/test/ 　　 代表不管理 test目录下的所有文件，不包含test目录

/test 　　 代表不管test 此目录，包含test目录文件

! 　　　　 代表取反，需要管理 #例；因为gitignore是用于排除的git 文件管理，当你在一个目录下要排除的文件远远大于管理的文件时，你可以搭配文件排除和！使用,实例：/root/test/ !/root/test/except.txt 注：在父级目录（root）没有被全部排除下，except.txt 才会被管理

? 　　　　匹配除'\'之外的一个所有字符 #例： test?.txt 可以匹配到 testa.txt、testk.txt等文件

[] 　　　　匹配数组中指定指定的字符 #例：test[k,l] 可以匹配到 testk.txt、testl.txt， 之外都不行

** 　　　　用于匹配多层目录 #例: root/**/test 可以匹配到 root/a/b/c/test 、root/fd/gg/test等目录


忽略规则优先级

1.从命令行中读取可用的忽略规则
2.当前目录定义的规则
3.父级目录定义的规则，依次递推
4.$GIT_DIR/info/exclude 文件中定义的规则
5.core.excludesfile中定义的全局规则

警告：
gitignore 的忽略规则只适用还没管理的文件，假如你有忽略规则在你添加之前被git 管理，那添加的忽略规则将无法适用已经管理的文件