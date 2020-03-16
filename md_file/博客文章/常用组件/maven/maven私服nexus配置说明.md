



#### maven仓库类型说明

nexus私服仓库类型有hosted、proxy、group,分别适用的场景如下

| 项目   | 具体说明                                   |
| ------ | ------------------------------------------ |
| hosted | 本地存储。像官方仓库一样提供本地私库功能   |
| proxy  | 提供代理其它仓库的类型                     |
| group  | 组类型，能够组合多个仓库为一个地址提供服务 |

1.pom文件设置远程仓库和远程依赖

2.pom文件设置远程发布仓库

3.快照版本代码的发布



Nexus列出了默认的几个仓库：

　　　　Public Repositories：仓库组，将所有策略为Release的仓库聚合并通过一致的地址提供服务。

　　　　3rd party：一个策略为Release的宿主类型仓库，用来部署无法从公共仓库获得的第三方发布版本构件。

　　　　Apache Snapshots：策略为Snapshots的代理仓库，用来代理Apache Maven仓库的快照版本构件。

　　　　Central：该仓库代理Maven的中央仓库，策略为Release，只会下载和缓存中央仓库中的发布版本构件。

　　　　Central M1 shadow：maven1格式的虚拟类型仓库。

　　　　Codehaus Snapshots：代理Codehaus Maven仓库快照版本的代理仓库。

　　　　Release：策略为Release的宿主类型仓库，用来部署组织内部的发布版本构件。

　　　　Snapshots：策略为Snapshots的宿主类型仓库，用来部署组织内部的快照版本构件。