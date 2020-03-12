# 前言

本文可以帮助你加深对Maven的整体认识，不是一篇基础文章。如果你现在还没有用 Maven 跑过 HelloWorld，那么本文可能不适合你。

# 一、Maven简介

> Maven 官网：[https://maven.apache.org](https://maven.apache.org/)
> Maven 3.3.9版本文档：[http://maven.apache.org/ref/3...](http://maven.apache.org/ref/3.3.9/maven-core/lifecycles.html)

Maven是一个项目管理工具，它包含了一个项目对象模型 (Project Object Model)，一组标准集合，一个项目生命周期(Project Lifecycle)，一个依赖管理系统(Dependency Management System)，和用来运行定义在生命周期阶段(phase)中插件(plugin)目标(goal)的逻辑。

1. 版本：maven有自己的版本定义和规则
2. 构建：可以完成编译，打包，部署等。
3. 依赖管理：方便引入所需依赖 Jar 包，不需手动下载
4. 文档生成：maven的site命令支持各种文档信息的发布，包括构建过程的各种输出，javadoc，产品文档等。
5. 项目关系：一个大型的项目通常有几个小项目或者模块组成，用maven可以很方便地管理

# 二、Maven生命周期

maven把项目的构建划分为不同的生命周期(lifecycle)。粗略一点的话，它这个过程(phase)包括：

1. 验证（validate）:验证项目是否正确
2. 编译（compile）: 编译项目的源代码
3. 测试（test）：使用合适的单元测试框架测试编译的源代码。这些测试不应该要求代码被打包或部署
4. 打包（package）
5. 验证（verify）
6. 安装（install）
7. 部署（deploy）

maven中所有的执行动作(goal)都需要指明自己在这个过程中的执行位置，然后maven执行的时候，就依照过程的发展依次调用这些goal进行各种处理。

这个也是maven的一个基本调度机制。一般来说，位置稍后的过程都会依赖于之前的过程。当然，maven同样提供了配置文件，可以依照用户要求，跳过某些阶段。

# 三、Maven 版本规范

maven使用如下几个要素来唯一定位某一个输出物： groupId:artifactId:packaging:version 。比如org.springframework:spring:2.5 。每个部分的解释如下：

1. groupId 公司，团体等。如Apache Software的项目有以org.apache开头的groupId。
2. artifactId 项目的唯一标识符。比如一个helloworld项目就叫helloworld。
3. packaging 项目打包输出的类型，默认是jar。类型为war的项目产生一个web应用。
4. version 一个项目的特定版本。发布的项目有一个固定的版本标识来指向该项目的某一个特定的版本。而正在开发中的项目可以用一个特殊的标识，这种标识给版本加上一个"SNAPSHOT"的标记。
   - maven有自己的版本规范，一般是如下定义` ..-` ，比如1.2.3-beta-01。要说明的是，maven自己判断版本的算法是major,minor,incremental部分用数字比较，qualifier部分用字符串比较，所以要小心 alpha-2和alpha-15的比较关系，最好用 alpha-02的格式。
   - maven在版本管理时候可以使用几个特殊的字符串 SNAPSHOT ,LATEST ,RELEASE 。比如"1.0-SNAPSHOT"。各个部分的含义和处理逻辑如下说明：
     - SNAPSHOT 如果一个版本包含字符串"SNAPSHOT"，Maven就会在安装或发布这个组件的时候将该符号展开为一个日期和时间值，转换为UTC时间。例如，"1.0-SNAPSHOT"会在2010年5月5日下午2点10分发布时候变成1.0-20100505-141000-1。这个词只能用于开发过程中，因为一般来说，项目组都会频繁发布一些版本，最后实际发布的时候，会在这些snapshot版本中寻找一个稳定的，用于正式发布，比如1.4版本发布之前，就会有一系列的1.4-SNAPSHOT，而实际发布的1.4，也是从中拿出来的一个稳定版。
     - LATEST 指某个特定构件的最新发布，这个发布可能是一个发布版，也可能是一个snapshot版，具体看哪个时间最后。
     - RELEASE 指最后一个发布版。

# 四、Maven 项目依赖

## 1. 多模块依赖和继承

项目结构图：

```xml
parent
    ├─childA(model层)
    │  └─pom.xml(jar)
    ├─childB(web层)
    │  └─pom.xml(war)
    └─pom.xml(pom)
```

1. parent中执行`mvn install`就能将 childA和childB 一起编译

   parent的pom.xml做如下配置：

   ```xml
   <groupId>com.song</groupId>
   <artifactId>parent</artifactId>
   <version>1.0-SNAPSHOT</version>
   <packaging>pom</packaging>    <!-- pom表示它是一个被继承的模块 -->
   
   <modules>
       <module>childA</module>   <!-- 不加module则不会被联合编译 -->
       <module>childB</module>
   </modules>
   ```

   childA和childB的pom.xml都需要配置parent，防止引入的包冲突(如果不加parent，会分别去编译他们引入的依赖，会重复引入包):

   ```xml
   <!-- childA 的 pom.xml-->
   <parent>
       <artifactId>parent</artifactId>
       <groupId>com.song</groupId>
       <version>1.0-SNAPSHOT</version>
   </parent>
   <modelVersion>4.0.0</modelVersion>
   <artifactId>childA</artifactId>
   <packaging>jar</packaging>
   
   <!-- childB 的 pom.xml-->
   <parent>
       <artifactId>parent</artifactId>
       <groupId>com.song</groupId>
       <version>1.0-SNAPSHOT</version>
   </parent>
   <modelVersion>4.0.0</modelVersion>
   <artifactId>childB</artifactId>
   <packaging>war</packaging>
   ```

2. 子pom间存在引用关系，比如childB引用到了childA的jar包

```xml
<dependency>
   <groupId>com.module</groupId>
   <artifactId>childA</artifactId>       <!--加上childA的依赖-->
   <version>1.0-SNAPSHOT</version>
</dependency>
```

## 2. 子项目继承父项目的依赖

parent中加上``，child项目就可以继承parent项目的依赖，并且在child中可以不用加version了。

```xml
<dependencyManagement>
   <dependencies>
      <dependency>
           <groupId>javax.servlet</groupId>
          <artifactId>servlet-api</artifactId>
          <version>2.5</version>
      </dependency>
   </dependencies>
</dependencyManagement>
```

## 3. 依赖范围

如果不显示执行 <scope> 属性时，默认` compile`。
scope 属性包括：

1. **compile**（编译范围）：编译范围的``在所有的classpath中可用，同时它们也会被打包
2. **provided**（已提供范围）：表示部署的环境当中有某容器已经提供了该jar包，只在编译classpath（不是运行时）可用。它们不是传递性的，也不会被打包。例如，如果你开发了一个web应用，你可能在编译classpath中需要可用的Servlet API来编译一个servlet，但是你不会想要在打包好的WAR中包含这个Servlet API；这个Servlet API JAR由你的servlet容器（Tomcat）提供。
3. **runtime**（运行时范围）：只在运行和测试系统的时候需要，但在编译的时候不需要。
4. **test**（测试范围）：在一般的 编译和运行时都不需要，它们只有在测试编译和测试运行阶段可用。
5. **system** （系统范围）：该范围不推荐使用（你应该一直尽量去从公共或定制的Maven仓库中引用依赖）

> 详细可参考：[https://maven.apache.org/guid...](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Dependency_Scope)

## 4. 可选依赖（Optional Dependencies）和依赖排除（Dependency Exclusions）

maven的依赖关系是有传递性的。如：A-->B，B-->C。但有时候，项目A可能不是必需依赖C，因此需要在项目A中排除对A的依赖。在maven的依赖管理中，有两种方式可以对依赖关系进行，分别是可选依赖（Optional Dependencies）以及依赖排除（Dependency Exclusions）。

### 4.1 可选依赖 optional

当一个项目A依赖另一个项目B时，项目A可能很少一部分功能用到了项目B，此时就可以在A中配置对B的可选依赖。举例来说，一个类似hibernate的项目，它支持对mysql、oracle等各种数据库的支持，但是在引用这个项目时，我们可能只用到其对mysql的支持，此时就可以在这个项目中配置可选依赖。

配置可选依赖的原因：1、节约磁盘、内存等空间；2、避免license许可问题；3、避免类路径问题，等等。

```xml
<dependency>
  <groupId>sample.ProjectB</groupId>
  <artifactId>Project-B</artifactId>
  <version>1.0</version>
  <scope>compile</scope>
  <optional>true</optional>
</dependency>
```

假设以上配置是项目A的配置，即：Project-A --> Project-B。在编译项目A时，是可以正常通过的。

如果有一个新的项目X依赖A，即：Project-X -> Project-A。此时项目X就不会依赖项目B了。如果项目X用到了涉及项目B的功能，那么就需要在pom.xml中重新配置对项目B的依赖。

### 4.2 依赖排除 exclusions

当一个项目A依赖项目B，而项目B同时依赖项目C，如果项目A中因为各种原因不想引用项目C，在配置项目B的依赖时，可以排除对C的依赖。

示例（假设配置的是A的pom.xml，依赖关系为：A --> B; B --> C）：

```xml
<dependency>
  <groupId>sample.ProjectB</groupId>
  <artifactId>Project-B</artifactId>
  <version>1.0</version>
  <scope>compile</scope>
  <exclusions>
    <exclusion>
      <groupId>sample.ProjectC</groupId>
      <artifactId>Project-C</artifactId>
    </exclusion>
  </exclusions> 
</dependency>
```

当然，对于多重依赖，配置也很简单，参考如下示例：

```xml
Project-A
   -> Project-B
        -> Project-D 
              -> Project-E <! -- This dependency should be excluded -->
              -> Project-F
   -> Project C
```

A对于E相当于有多重依赖，我们在排除对E的依赖时，只需要在配置B的依赖中进行即可：

```xml
<dependency>
  <groupId>sample.ProjectB</groupId>
  <artifactId>Project-B</artifactId>
  <version>1.0-SNAPSHOT</version>
  <exclusions>
    <exclusion>
      <groupId>sample.ProjectE</groupId>
      <artifactId>Project-E</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```



## 如何配置本地jar包引用

```xml
<dependency>
  <groupId>sample.ProjectB</groupId>
  <artifactId>Project-B</artifactId>
  <version>1.0-SNAPSHOT</version>
  <scope>system</scope>
  <systemPath>${basedir}/lib/xxx.jar</systemPath>
</dependency>
```



## 定向下载某一个jar包

```properties
install-file -Dfile=c:/db2jcc.jar -DgroupId=db2 -DartifactId=db2jcc -Dversion=1.0  -Dpackaging=db2jcc-1.0.jar
```



## 指定编译使用得本地仓库路径

```properties
mvn package -Dmaven.repo.local=C:\Users\Administrator\.m2\repository -Dmaven.test.skip=true
```

# 五、Maven插件机制

## 1. Maven默认插件

不配置Plugin时，Maven默认会使用以下插件。如果针对各个 plugin 有特殊配置的话，需要显示指定 plugin 和 属性配置。

| plugin                 | function                                     | life cycle phase        |
| ---------------------- | -------------------------------------------- | ----------------------- |
| maven-clean-plugin     | 清理上一次执行创建的target文件               | clean                   |
| maven-resources-plugin | 处理资源文件                                 | resources,testResources |
| maven-compiler-plugin  | 编译Java代码                                 | compile、testCompile    |
| maven-surefire-plugin  | 执行单元测试文件                             | test                    |
| maven-jar-plugin       | 创建 jar                                     | package                 |
| maven-install-plugin   | 拷贝jar到本地的maven仓库 .m2/repository 下面 | install                 |
| maven-deploy-plugin    | 发布 jar                                     | deploy                  |
| maven-site-plugin      | 生成文档                                     | site                    |

> maven-site-plugin：将工程所有文档生成网站，生成的网站界面默认和apache的项目站点类似，但是其文档用doxia格式写的，目前不支持docbook，需要用其他插件配合才能支持。需要指出的是，在maven 2.x系列中和maven3.x的site命令处理是不同的，在旧版本中，用 mvn site 命令可以生成reporting节点中的所有报表，但是在maven3中，reporting过时了，要把这些内容作为 maven-site-plugin的configuration的内容才行。详细内容可以参考[http://www.wakaleo.com/blog/2...](http://www.wakaleo.com/blog/292-site-generation-in-maven-3)

```xml
<build>
    <!-- resources 是 maven-resources-plugin 的-->
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <filtering>true</filtering> <!-- filtering 用来表示资源文件中的占位符是否需要被profiles中的属性动态替换，true为需要替换。 -->
            <includes>
                <include>**/*.xml</include>
            </includes>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
            <includes>
                <include>**/*.properties</include>
                <include>*.xml</include>
                <include>*.dic</include>
                <include>*.txt</include>
            </includes>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>false</filtering>
            <includes>
                <include>*.p12</include>
            </includes>
        </resource>
    </resources>



    <plugins>
        <!-- 
            maven-compiler-plugin编译源代码。
            指定maven编译的jdk版本,如果不指定,maven3默认用jdk 1.5 maven2默认用jdk1.3 
            windows默认使用GBK编码，java项目经常编码为utf8，也需要在compiler插件中指出，否则中文乱码可能会出现编译错
        -->
        <plugin>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
                <encoding>UTF-8</encoding>
            </configuration>
        </plugin>
        
        <!--
            maven-resources-plugin用来处理资源文件，默认的主资源文件目录是src/main/resources。
        -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-resources-plugin</artifactId>
            <version>2.6</version>
            <configuration>
                <encoding>UTF-8</encoding>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## 2. Maven常用插件

### 2.0 spring-boot-maven-plugin

当使用SpringBoot开发项目的时候，会使用到`spring-boot-maven-plugin`插件

> 官方文档：[https://docs.spring.io/spring...](https://docs.spring.io/spring-boot/docs/current/maven-plugin/index.html)

Spring Boot Maven plugin有5个Goals：

| 命令                   | 说明                                                         |
| ---------------------- | ------------------------------------------------------------ |
| spring-boot:repackage  | 默认goal。在mvn package之后，再次打包可执行的jar/war，<br/>并将mvn package生成的软件包重命名为*.original |
| spring-boot:run        | 运行Spring Boot应用                                          |
| spring-boot:start      | 在mvn integration-test阶段，进行Spring Boot应用生命周期的管理 |
| spring-boot:stop       | 在mvn integration-test阶段，进行Spring Boot应用生命周期的管理 |
| spring-boot:build-info | 生成Actuator使用的构建信息文件build-info.properties          |

其中比较重要的命令是：

```shell
mvn package spring-boot:repackage
```

执行后会看到生成的两个jar文件，一个是`*.jar`，另一个是`*.jar.original`。
这是由于在执行上述命令的过程中，Maven首先在`package`阶段打包生成`*.jar`文件；然后执行`spring-boot:repackage`重新打包

**我们也可以跳过`repackage`阶段**：

```properties
clean deploy -D spring-boot.repackage.skip=true
```

加上`-D spring-boot.repackage.skip=true`参数即可，此时只会生成一个普通的jar包

### 2.1 maven-source-plugin

maven-source-plugin提供项目自动将源码打包并发布的功能，在需要发布源码项目的pom.xml文件中添加如下代码即可：

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-source-plugin</artifactId>
    <configuration>
    </configuration>
    <executions>
        <execution>
            <id>attach-sources</id>
            <goals>
                <goal>jar</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

执行 `mvn install`，maven会自动将source install到repository 。
执行 `mvn deploy`，maven会自动将source deploy到remote-repository 。
执行 `mvn source:jar`，单独打包源码。

> **注意**：在多项目构建中，将source-plugin置于顶层或parent的pom中并不会发挥作用，必须置于具体项目的pom中。

### 2.2 Tomcat插件

tomcat插件有两种：tomcat-maven-plugin 和 tomcat7-maven-plugin，使用方式基本相同。

- tomcat-maven-plugin 插件是org.codehaus.mojo组织提供的，里面的tomcat是6.0.29版本，没有更新了。
- tomcat7-maven-plugin 插件是apache提供的，官网：[http://tomcat.apache.org/mave...](http://tomcat.apache.org/maven-plugin.html)

1. **tomcat7-maven-plugin 插件使用**

   ```xml
   <plugin>
       <groupId>org.apache.tomcat.maven</groupId>
       <artifactId>tomcat7-maven-plugin</artifactId>
       <version>2.2</version>
          <configuration>
             <path>/</path>
             <port>8080</port>
             <uriEncoding>UTF-8</uriEncoding>
             <server>tomcat7</server>
          </configuration>
           <executions>
               <execution>
                   <phase>package</phase>
                   <goals>
                       <goal>run</goal>
                   </goals>
               </execution>
           </executions>
   </plugin>
   ```

   命令：

   ```properties
   tomcat7:deploy  --部署一个web war包
   tomcat7:reload  --重新加载web war包
   tomcat7:start    --启动tomcat
   tomcat7:stop    --停止tomcat
   tomcat7:undeploy --停止一个war包
   tomcat7:run --启动嵌入式tomcat ，并运行当前项目
   ```

2. **tomcat-maven-plugin 插件使用**

   ```xml
   <plugin>
       <groupId>org.codehaus.mojo</groupId>
       <artifactId>tomcat-maven-plugin</artifactId>
       <version>1.1</version>
       <configuration>
           <path>/helloworld</path>
           <port>8080</port>
           <uriEncoding>UTF-8</uriEncoding>
           <url>http://localhost:8080/manager/html</url>
           <server>tomcat6</server>
           <systemProperties>
               <JAVA_OPTS>-Xms256m -Xmx512m -XX:MaxPermSize=512m</JAVA_OPTS>
             </systemProperties>
       </configuration>
   </plugin>
   ```

   命令：

   ```properties
   tomcat:deploy   --部署一个web war包
   tomcat:reload   --重新加载web war包
   tomcat:start    --启动tomcat
   tomcat:stop    --停止tomcat
   tomcat:undeploy --停止一个war包
   tomcat:run  --启动嵌入式tomcat ，并运行当前项目
   ```

配置参数：
**path**：是访问应用的路径
**port**：是tomcat 的端口号
**uriEncoding**：URL按UTF-8进行编码，这样就解决了中文参数乱码。
**Server**：指定tomcat名称。

### 2.3 自动部署插件wagon

自动部署包含三个步骤：
编译打包、上传到服务器、在服务器上执行linux命令

#### 2.3.1 文件上传到服务器

Maven项目可使用 mvn install 指令打包，打包完成后包位于target目录下，要想在远程服务器上部署，首先要将包上传到服务器。

首先在本地的setting.xml中配置server的信息，包括id，用户名，密码。（当然也可以在pom.xml里面配置）

```xml
<servers>
    <server>
      <id>linux_server</id>
      <username>user</username>
      <password>password</password>
    </server>
</servers>
```

pom.xml

```xml
<build>
    <extensions>
        <extension>
            <groupId>org.apache.maven.wagon</groupId>
            <artifactId>wagon-ssh</artifactId>
            <version>2.8</version>
        </extension>
    </extensions>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>wagon-maven-plugin</artifactId>
            <version>1.0</version>
            <configuration>
                <serverId>linux_server</serverId>
                <fromFile>target/test.war</fromFile>
                <url>scp://user:password@192.168.20.128/home/tomcat7/webapps</url>
            </configuration>
        </plugin>
    </plugins>
</build>
```

`linux_server`： 在setting.xml中配置的server的id名字
`target/test.war`：是要上传到服务器的文件，一般来说是jar或者war包 `scp://user:password@192.168.20.128/home/tomcat7/webapps`：配置服务器的用户、密码、地址以及文件上传的目录

命令：

```
# 对项目进行打包和上传操作
mvn clean install wagon:upload-single 
```

如果觉的wagon命令太长，可以设置 excutions 来配置phase和goals来简化命令。

#### 2.3.2 在服务器上执行linux命令

##### 1. 运行jar文件

```xml
<!-- 
如果运行的命令中没有“-D maven.deploy.skip=true”不配置distributionManagement 则会报错：-DaltDeploymentRepository=id::layout::url parameter 因为不加maven.deploy.skip=true,则会使用maven的deploy，又使用wagon的deploy。而maven的deploy是需要配置distributionManagement的
<distributionManagement>
    <repository>
        <id>${serverid}</id>
        <url>scp://192.168.20.128/home/java/exe</url>
    </repository>
</distributionManagement>
-->

<build>
    <extensions>
        <extension>
            <groupId>org.apache.maven.wagon</groupId>
            <artifactId>wagon-ssh</artifactId>
            <version>2.8</version>
        </extension>
    </extensions>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>wagon-maven-plugin</artifactId>
            <version>1.0</version>
            
            <configuration>
                <fromFile>target/test.jar</fromFile>
                <url>scp://user:password@192.168.20.128/home/java/exe</url>
                <commands>
                    <command>pkill -f /home/java/exe/test.jar</command>  <!-- 杀死原来的进程，-f 是用来查找命令参数，禁止使用pkill java来杀死进程,也可以是 pkill -f test.jar -->
                    <command>nohup java -jar /home/java/exe/test.jar > /home/java/exe/$(date +%Y%m%d-%H%M%S).log &amp; </command> <!-- 重新启动test.jar，&amp; 是xml的转义字符，表示 &-->
                </commands>
                <displayCommandOutputs>true</displayCommandOutputs> <!-- 显示运行命令的输出结果 -->
            </configuration>
        </plugin>
    </plugins>
</build>
```

命令

```
<!--install、wagon:upload-single、wagon:sshexec 分别对项目进行打包、上传、运行command命令的操作-->
mvn clean install wagon:upload-single wagon:sshexec
```

##### 2. 上传war包并启动Tomcat

```xml
<build>
   <extensions>
       <extension>
           <groupId>org.apache.maven.wagon</groupId>
           <artifactId>wagon-ssh</artifactId>
           <version>2.8</version>
       </extension>
   </extensions>
   <plugins>
       <plugin>
           <groupId>org.codehaus.mojo</groupId>
           <artifactId>wagon-maven-plugin</artifactId>
           <version>1.0</version>
           
           <configuration>
               <fromFile>target/javawebdeploy.war</fromFile>
               <url>scp://user:password@192.168.20.128/home/tomcat7/webapps</url>
               <commands>
                   <!-- 重启Tomcat -->
                   <command>sh /home/tomcat7/bin/shutdown.sh</command>
                   <command>rm -rf /home/tomcat7/webapps/test</command>
                   <command>sh /home/tomcat7/bin/startup.sh</command>
               </commands>
               <displayCommandOutputs>true</displayCommandOutputs>
           </configuration>
       </plugin>
   </plugins>
</build>
```

命令

```
mvn clean install wagon:upload-single wagon:sshexec
```

#### 2.3.3 配置execution

如果觉得 `mvn clean package wagon:upload-single wagon:sshexec` 命令太长了不好记，那么可以配置execution，在运行deploy的同时运行upload-single和sshexec。

```xml
<build>
    <extensions>
        <extension>
            <groupId>org.apache.maven.wagon</groupId>
            <artifactId>wagon-ssh</artifactId>
            <version>2.8</version>
        </extension>
    </extensions>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>wagon-maven-plugin</artifactId>
            <version>1.0</version>
            <executions>
                <execution>
                    <id>upload-deploy</id>
                    <!-- 运行deploy命令时同时运行upload-single和sshexec -->
                    <phase>deploy</phase>
                    <goals>
                        <goal>upload-single</goal>
                        <goal>sshexec</goal>
                    </goals>
                    
                    <configuration>
                        <fromFile>target/test.war</fromFile>
                        <url>scp://user:password@192.168.20.128/home/tomcat7/webapps</url>
                        <commands>
                            <command>sh /home/tomcat7/bin/shutdown.sh</command>
                            <command>rm -rf /coder/tomcat7/webapps/test</command>
                            <command>sh /coder/tomcat7/bin/startup.sh</command>
                        </commands>
                        <displayCommandOutputs>true</displayCommandOutputs>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

部署命令

```xml
<!-- 即可使用mvn clean package来代替mvn clean package wagon:upload-single wagon:sshexec -->

<!--  必须加 -D maven.deploy.skip=true ，表示跳过maven自身的deploy使用wagon的deploy。否则报错“-DaltDeploymentRepository=id::layout::url parameter”  -->
mvn clean deploy -D maven.deploy.skip=true
```

#### 2.3.4. 完整示例配置

首先在本地的setting.xml中配置server的信息，包括id，用户名，密码。（当然也可以在pom.xml里面配置）

```xml
<servers>
    <server>
      <id>linux_server</id>
      <username>user</username>
      <password>password</password>
    </server>
</servers>
```

pom.xml

```xml
<build>
    <extensions>
        <extension>
            <groupId>org.apache.maven.wagon</groupId>
            <artifactId>wagon-ssh</artifactId>
            <version>2.8</version>
        </extension>
    </extensions>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>wagon-maven-plugin</artifactId>
            <version>1.0</version>
            
            <configuration>
                <serverId>linux_server</serverId>
                <url>scp://192.168.20.128/home/tomcat7/webapps</url>
                <displayCommandOutputs>true</displayCommandOutputs> <!-- 显示运行命令的输出结果 -->
            </configuration>
            
            <executions>
                <!-- 将war包上传到服务器并重启服务器 -->
                <execution>
                    <id>upload-war-to-server</id>
                    <phase>deploy</phase>
                    <goals>
                        <goal>upload-single</goal>
                        <goal>sshexec</goal>
                    </goals>
                    <configuration>
                        <fromFile>target/test.war</fromFile>
                        <commands>
                            <command>datefilename=$(date +%Y%m%d-%H%M%S);cp /home/tomcat7/webapps/test.war /home/tomcat7/webapps/test.war.$datefilename</command>  <!--备份旧的war包-->
                            <command>ps -ef | grep /home/tomcat7/ | grep -v grep | awk {'print $2'} | sed -e "s/^/kill -9 /g" | sh</command> <!-- 关闭tomcat7 -->
                            <command>rm -rf /home/tomcat7/webapps/test</command> <!-- 删除test项目 -->
                            <command>export JAVA_HOME=/home/jdk/jdk1.8.0_91;sh /home/tomcat7/bin/startup.sh</command> <!-- 启动tomcat -->
                        </commands>
                    </configuration>
                </execution>
        </plugin>
    </plugins>
</build>
```

部署命令

```xml
# 必须加 -D maven.deploy.skip=true ，表示跳过maven自身的deploy使用wagon的deploy。否则报错“-DaltDeploymentRepository=id::layout::url parameter”
mvn clean deploy -D maven.deploy.skip=true
```

### 2.4 maven-war-plugin

Maven打包时可以对`web.xml`中的`spring.profiles.active`值进行替换。

先`web.xml`中配置一个占位符`${profiles.active}`:

```xml
<context-param>
    <param-name>spring.profiles.active</param-name>
    <param-value>${profiles.active}</param-value>
</context-param>
```

在`pom.xml`配置`maven-war-plugin`：

```xml
<!-- 打war包时替换占位符 -->
<build>
  <plugin>
    <artifactId>maven-war-plugin</artifactId>
    <version>3.2.2</version>
    <configuration>
      <!-- 过滤Deployment Descriptor并将文件中的占位符替换为pom.xml中对应的<properties>值 -->
      <filteringDeploymentDescriptors>true</filteringDeploymentDescriptors>
    </configuration>
  </plugin>
</build>

<!-- 默认的maven profile -->
<properties>
  <profiles.active>dev</profiles.active>
</properties>

<profiles>
  <profile>
    <id>dev</id>
    <properties>
      <profiles.active>dev</profiles.active>
    </properties>
  </profile>
  <profile>
    <id>release</id>
    <properties>
      <profiles.active>release</profiles.active>
    </properties>
  </profile>
</profiles>
```

以上配置完成后，再通过`mvn package -Pdev`或`mvn package -Preelase`打包后，`${profiles.active}<`占位符就被替换为`dev`或`release`

# 六、Maven控制测试环境和正式环境

## 6.1 Maven profiles切换测试环境和正式环境

```xml
<profiles>
    <profile>
        <!-- 本地开发环境 -->
        <id>dev</id>
        <properties>
            <profiles.active>dev</profiles.active>
        </properties>
        <activation>
            <!-- 设置默认激活这个配置 -->
            <activeByDefault>true</activeByDefault>
        </activation>
    </profile>
    <profile>
        <!-- 发布环境 -->
        <id>release</id>
        <properties>
            <profiles.active>release</profiles.active>
        </properties>
    </profile>
    <profile>
        <!-- 测试环境 -->
        <id>beta</id>
        <properties>
            <profiles.active>beta</profiles.active>
        </properties>
    </profile>
</profiles> 



<build>
    <finalName>helloworld</finalName>
    <resources>
        <!-- 先全部不包含 -->
        <resource>
            <directory>src/main/resources</directory>
            <excludes>
                <exclude>config/</exclude>
            </excludes>
        </resource>
        <!-- 再指定需要包含的文件夹 -->
        <resource>
            <directory>src/main/resources/config/${profiles.active}</directory>
            <targetPath>.</targetPath>
        </resource>
    </resources>
</build>
```

id代表这个环境的唯一标识，在 mvn install -Pdev 来指定。
此properties定义了三个环境，分别是dev（开发环境）、beta（测试环境）、release（发布环境）
activeByDefault=true代表如果不指定某个固定id的profile，那么就使用这个环境

**使用 `mvn install -Pdev`会将 id 为 dev 的 profile 中的``定义的属性`profiles.active`自动替换`${profiles.active}`占位符的变量。最终build到classpath的资源文件由maven-resources-plugin来指定，为src/main/resources/config/dev文件下的所有文件。**

## 6.2 Spring Framework profile整合Maven profile

如果想要整合Maven profile和Spring Framework profile，Maven打包时可以对`web.xml`中的`spring.profiles.active`值进行替换。

先`web.xml`中配置一个占位符`${profiles.active}`:

```xml
<context-param>
    <param-name>spring.profiles.active</param-name>
    <param-value>${profiles.active}</param-value>
</context-param>
```

在`pom.xml`配置`maven-war-plugin`：

```xml
<!-- 打war包时替换占位符 -->
<build>
  <plugin>
    <artifactId>maven-war-plugin</artifactId>
    <version>3.2.2</version>
    <configuration>
      <!-- 过滤Deployment Descriptor并将文件中的占位符替换为pom.xml中对应的<properties>值 -->
      <filteringDeploymentDescriptors>true</filteringDeploymentDescriptors>
    </configuration>
  </plugin>
</build>

<!-- 默认的maven profile -->
<properties>
  <profiles.active>dev</profiles.active>
</properties>

<profiles>
  <profile>
    <id>dev</id>
    <properties>
      <profiles.active>dev</profiles.active>
    </properties>
  </profile>
  <profile>
    <id>release</id>
    <properties>
      <profiles.active>release</profiles.active>
    </properties>
  </profile>
</profiles>
```

以上配置完成后，再通过`mvn package -Pdev`或`mvn package -Preelase`打包后，`${profiles.active}<`占位符就被替换为`dev`或`release`

# 七、Maven 变量

```properties
Maven内置变量说明：

${basedir} 项目根目录
${project.build.directory} 构建目录，缺省为target
${project.build.outputDirectory} 构建过程输出目录，缺省为target/classes
${project.build.finalName} 产出物名称，缺省为${project.artifactId}-${project.version}
${project.packaging} 打包类型，缺省为jar
${project.xxx} 当前pom文件的任意节点的内容
```



## 1. 内置属性

- **${basedir}** represents the directory containing pom.xml
- **{project.version } or ${pom.version }

## 2. Pom/Project properties

所有pom中的元素都可以用 project. 前缀进行引用,以下是部分常用的

- **{pom.project.build.directory}
- **${project.build.outputDirectory}** results in the path to your "target/classes" dir
- **${project.name}** refers to the name of the project.
- **${project.version}** refers to the version of the project.
- **${project.build.finalName}** refers to the final name of the file created when the built project is packaged

## 3. 本地用户设定

所有用的的 settings.xml 中的设定都可以通过 settings. 前缀进行引用

- **${settings.localRepository}** refers to the path of the user's local repository.
- **${maven.repo.local}** also works for backward compatibility with maven1

## 4. 环境变量

系统的环境变量通过 env. 前缀引用

- **${env.M2_HOME}** returns the Maven2 installation path.
- **{java.home}../bin/java.exe</jvm>

## 5. java系统属性

所有JVM中定义的java系统属性.

## 6. pom.xml自定义变量

```xml
<project> 
    ... 
    <properties> 
        <project.build.finalName>hellowolrld</project.build.finalName> 
    </properties> 
    ... 
</project> 
```

则引用 ${project.build.finalName} 就会得到值 hellowolrld

## 7. parent 工程的变量

parent工程的pom.xml中的变量用前缀 `${project.parent}` 引用. 上级工程的版本也可以这样引用: `${parent.version }`.

> 参考文章：
> [https://blog.csdn.net/wwbmyos...](https://blog.csdn.net/wwbmyos/article/details/10520063)
> [https://blog.csdn.net/j080624...](https://blog.csdn.net/j080624/article/details/67639259)
> [http://xxgblog.com/2015/10/23...](http://xxgblog.com/2015/10/23/wagon-maven-plugin)
> [https://blog.csdn.net/java_an...](https://blog.csdn.net/java_and_json/article/details/79358764)
> [https://blog.csdn.net/xiao__g...](https://blog.csdn.net/xiao__gui/article/details/88189429)

阅读 5.7k更新于 2019-07-01