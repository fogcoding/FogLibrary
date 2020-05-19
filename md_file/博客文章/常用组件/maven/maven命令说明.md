## Maven命令



* clean
* validate
* compile
* test：测试命令,或执行src/test/java/下junit的测试用例

* package：将项目打包（jar/war），将打包结果放到项目下的 **target** 目录下

* verify：
* install ： **1.** 将项目打包（jar/war），将打包结果放到项目下的 **target** 目录下 **2.** 同时将上述打包结果放到**本地仓库**的相应目录中，供其他项目或模块引用

* site

* deploy：发布命令 将打包的文件发布到远程参考,提供其他人员进行下载依赖 ,一般是发布到公司的私服



#### 参考：https://www.cnblogs.com/endv/p/11204704.html



#### 命令创建mvn项目

```properties
-- 如果使用默认的settings.xml那么只需要使用下面的命令
mvn archetype:generate 

-- 如果使用了自定义的settings.xml，可以使用这样的命令
mvn archetype:generate -s path/setting.xml

```



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

-- 注意，不能从本地仓库直接deploy，要在没有关联的目录进行上传，否则会报错


-- 以上命令，要配合权限的验证的话，需要在servers中声明，否则会出现授权
-- 命令一定要输入正确，否则失效都无法排查错误

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



#### 指定setting文件编译

```properties
mvn -s "D:\maven\conf\settings-1.xml" clean install -Dmaven.test.skip=true 
```



#### 简化编译命令

```sql
-- windows
-- 新建一个文件,后缀为.bat，内容为：
mvn -s "d:\maven\conf\settings-1.xml" clean install -Dmaven.test.skip=true 
goto:EOF
-- 放到运行目录里，即可简化编译


-- linux，原理同上，不过是编写脚本变成了shell
mvn -s "/home/fogcoding/maven/conf/settings-1.xml" clean install -Dmaven.test.skip=true

-- 通过这样的方式，可以给不同的编译方式设定不同的配置，从而实现各个环境的分离，达到SIT,UAT互相不干扰的效果
```



#### 配置deploy到私服

```sql
-- pom文件设置远程url
<!-- 这里配置地址，setting配置用户密码 -->
  <distributionManagement>
        <repository>
            <id>deploy</id>
            <name>Internal Releases</name>
            <url>http://localhost:8081/repository/fogcoding/</url>
        </repository>
        <snapshotRepository>
            <id>deploy</id>
            <name>Internal Releases</name>
            <url>http://localhost:8081/repository/fogcoding/</url>
        </snapshotRepository>
    </distributionManagement>


-- setting文件设置账户密码
   <server>
      <id>deploy</id>
      <username>admin</username>  
      <password>admin123</password>  
    </server>

-- 注：仓库设置为快照版本，那么版本号一定要带SNAPSHOT后缀，否则会报400错误。
```



#### 设置拉取私服的代码

```xml
-- 1.配置setting的镜像

<!-- 如果要拉取私服的代码，需要配置镜像源 -->
<mirror>  
    <id>nexus-fogcoding</id>  
    <name>internal nexus repository</name>  
    <url>http://localhost:8081/repository/fogcoding/</url>  
    <mirrorOf>*</mirrorOf>  
</mirror>  
	 
-- 2.配置镜像登录的账号密码
<server>
    <id>nexus-fogcoding</id>
    <username>admin</username>  
    <password>admin123</password>  
</server>

```



#### 设置快照更新策略

方法一：

```xml
<!-- 这里设置了远程仓库的源 -->
  <repositories>
    <repository>
      <id>fogcoding</id>
      <name>test</name>
      <url>http://localhost:8081/repository/fogcoding/</url>
      <releases>
        <!-- 这里使设置是否在本仓库拉取代码 -->
        <enabled>true</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
        <!--  更新策略就是在这一行  -->
        <updatePolicy>always</updatePolicy>
      </snapshots>
    </repository>
  </repositories>
  <pluginRepositories>
    <pluginRepository>
      <id>fogcoding</id>
      <name>test</name>
      <url>http://localhost:8081/repository/fogcoding/</url>
      <releases>
        <enabled>true</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
         <!--  更新策略就是在这一行  -->
        <updatePolicy>always</updatePolicy>
      </snapshots>    
    </pluginRepository>
```

方法二：

```sql
-- 通过命令强制更新
mvn clean install-U
```



#### 如何启动激活几种不同的开发环境dev/sit/uat

```

https://www.cnblogs.com/codestory/p/11271016.html

https://www.cnblogs.com/raphael5200/p/6677549.html

-- 理论上来说，可以同时打包很多个环境的生产包



```



#### 自动化部署

https://ayayui.gitbooks.io/tutorialspoint-maven/content/book/maven_deployment_automation.html





#### 取消从maven中央仓库拉取代码

```xml
<repositories>
    <repository>
        <id>central</id>
        <url>http://host:port/content/groups/public</url>
    </repository>
</repositories>

<pluginRepositories>
    <pluginRepository>
        <id>central</id>
        <url>http://host:port/content/groups/public</url>
    </pluginRepository>
</pluginRepositories>
```



ref:https://blog.csdn.net/qq_40369435/article/details/96881611?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task



## classifier

通常用于区分从同一POM构建的具有不同内容的构件（artifact）。它是可选的，它可以是任意的字符串，附加在版本号之后。

### 使用场景

**场景一：**区分基于不同JDK版本的jar包

如果项目依赖，json-lib-2.2.2-jdk13.jar。则XML配置内容如下：

```xml
<dependency>  

    <groupId>net.sf.json-lib</groupId>   
    <artifactId>json-lib</artifactId>   
    <version>2.2.2</version>  
    <classifier>jdk13</classifier>    

</dependency>  
```

如果项目依赖，json-lib-2.2.2-jdk15.jar。则XML配置内容如下：

```xml
<dependency>  
    <groupId>net.sf.json-lib</groupId>   
    <artifactId>json-lib</artifactId>   
    <version>2.2.2</version>  
    <classifier>jdk15</classifier>   

</dependency>  
```



​	注意，如果json-lib没有提供，json-lib-2.2.2.jar。那么，设置依赖的时候，必须使用 `classifier` ，否则会报错，因为找不到指定的jar包。













