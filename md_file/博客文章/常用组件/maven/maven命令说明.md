## Maven命令



* clean

  >

* validate

  >

* compile

  >

* test

  >测试命令,或执行src/test/java/下junit的测试用例

* package

  >将项目打包（jar/war），将打包结果放到项目下的 **target** 目录下

* verify

  >

* install

  >**1.** 将项目打包（jar/war），将打包结果放到项目下的 **target** 目录下
  >
  >**2.** 同时将上述打包结果放到**本地仓库**的相应目录中，供其他项目或模块引用

* site

  >

* deploy

  >发布命令 将打包的文件发布到远程参考,提供其他人员进行下载依赖 ,一般是发布到公司的私服



## 参考：https://www.cnblogs.com/endv/p/11204704.html





#### 特殊操作

* 本地jar包安装到本地

```properties
mvn install:install-file -Dfile=/tmp/bak_local/galaxy-cache/1.5.17/galaxy-cache-1.5.17.jar  -DgroupId=com.dcits.galaxy -DartifactId=galaxy-cache -Dversion=1.5.17 -Dpackaging=jar
  
-- 命令解释：

-DgroupId=sxd.jar　　　　 　　　　　　　　　　　　　　　　　　　　　 自定义
-DartifactId=jacob　　　 　　　　　　　　　　　　　　　　　　　　　 自定义
-Dversion=1.18　　　　　　 　　　　　　　　　　　　　　　　　　　　　自定义  三个自定义，构成pom.xml文件中的坐标
-Dpackaging=jar　　　　　　　　　　　　　　　　　　　　　　　　　　 上传的类型是jar类型
-Dfile=G:\jar\jacob-1.18.jar　　　　　　　　　　　　　　　　　　　jar的本地磁盘位置
-Durl=http://localhost:8081/repository/myself_hosted/　　　　hosted资源库的地址，下图中
-DrepositoryId=myself_hosted　　　　　　　　　　　　　　　　　　　setting.xml文件中配置的ID
  
```

  

* 本地jar包发布到私服

```properties
mvn deploy:deploy-file -DgroupId=sxd.jar -DartifactId=jacob -Dversion=1.18 -Dpackaging=jar -Dfile=G:\jar\jacob-1.18.jar -Durl=http://localhost:8081/repository/myself_hosted/ -DrepositoryId=myself_hosted


mvn deploy:deploy-file -DgroupId=com.xy.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar -Dfile=E:\ojdbc14.jar -Durl=http://127.0.0.1:8081/nexus/content/repositories/thirdparty/ -DrepositoryId=thirdparty

```





#### 本地有jar包，但是识别不到

```properties
maven本地仓库有jar包，maven install还是报错识别不到
去本地仓库对应jar的目录下看下，有一个 _remote.repositories 的文件
打开

***.pom>xxx=
***.jar>xxx=

这个 xxx 就是你maven的setting.xml里配的mirror的id，如果这个xxx和你目前使用的setting文件里的mirror不一样，那maven install时会不认它。
很简单
在这个文件后面追加两行
***.pom>yyy=
***.jar>yyy=
这个yyy就是你当前setting里的mirror的id，告诉maven，这个jar是你亲生的，不要不认了。
```





