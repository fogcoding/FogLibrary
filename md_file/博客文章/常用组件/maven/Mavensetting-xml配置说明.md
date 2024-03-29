详细说明：

​	https://www.cnblogs.com/xiaobaizhiqian/p/8214035.html

​	https://segmentfault.com/a/1190000015077021

​	https://www.cnblogs.com/heyanan/p/10328653.html 配置三种仓库





### 核心问题：

1.如何配置多个远程仓库？

2.如何指定settings.xml的生效文件？

3.如何指定本地仓库目录？

4.如何配置代码远程依赖仓库？

5.如何设置模块间的依赖传递？

6.如何设置模块间的编译顺序？

7.如何在pom中设置单独对一个项目生效的远程代码仓库？

8.多个模块的依赖如何关联？

9.编译过一次的代码，需要更新到远程仓库，以保证代码的更新时效性



目标：

1.将互金的多个项目的依赖整理清楚，pom配置梳理清晰，然后统计配置文件，将其从代码工程中抽离出来，固定为配置分支

2.形成标准的pom文件，供所有的项目人员使用

3.分支策略与需求，代码提交，CI/CD流程，测试流程要形成比较可行的方案





#### setting.xml的配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"    xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
            http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <localRepository/>
    <interactiveMode/>
    <offline/>
    <pluginGroups/>
    <servers/>
    <mirrors/>
    <proxies/>
    <profiles/>
    <activeProfiles/>
</settings>

```



#### 配置文件的生效范围

```java
/**
1.用户级别
${user.home}/.m2/settings.xml
可以通过指令 -s /path/to/user/settings.xml

2.全局级别
${maven.home}/conf/settings.xml.
可以通过指令 -gs /path/to/global/settings.xml
**/
```



#### 配置本地仓库路径

```xml
   <!-- Default: ${user.home}/.m2/repository -->
  <localRepository>/path/to/local/repo</localRepository>
```



#### 互动配置

```properties
interactiveMode 用于决定maven是否在需要输出的时候提示你，默认true。如果是false，它将使用合理的默认值，或者基于一些设置
```



#### OFFLINE

```properties
决定maven是否在构建的时候进行网络传输。
默认false，表示联网状态，true为取消联网。
在某些情况下设置为true是很有用的，比如jar无法从网上下载等
```



#### pluginGroups 插件组

```xml
<pluginGroups>
    <pluginGroup>org.mortbay.jetty</pluginGroup>
 </pluginGroups>
```

这样Maven可以使用简单的命令执行org.morbay.jetty:jetty-maven-plugin:run

```powershell
mvn jetty run
```



#### 设置http代理（proxies）

```xml
<proxies>
    <proxy>
      <!-- id：proxy的唯一标识，用来区别proxy元素。 -->
      <id>optional</id>
      <!-- active：表示是否激活代理，如果配置多个，默认是第一个生效 -->
      <active>true</active>
        
      <protocol>http</protocol><!--代理协议-->
        <!-- username，password：提供连接代理服务器时的认证。 -->
      <username>proxyuser</username>
      <password>proxypass</password>
      <!-- host，port：主机地址，端口号 -->
      <host>proxy.host.net</host>
      <!-- 端口 -->
      <port>80</port>
	  <!-- nonProxyHosts：用来表示哪些主机名不需要代理，可以用|来分割多个，此外也支持通配符  -->
      <nonProxyHosts>local.net|some.host.com</nonProxyHosts>
    </proxy>
  </proxies>
```



####  maven连接远程服务

```xml
<servers>
    <!--使用登录方式-->
    <server>
          <id>deploymentRepo</id>
          <username>repouser</username>
          <password>repopwd</password>
        </server>

        <!-- 使用秘钥认证 -->
        <server>
          <id>siteServer</id>
          <privateKey>/path/to/private/key</privateKey>
          <passphrase>可空</passphrase>
        </server>
</servers>
```

这是一个认证配置的列表,根据系统中使用的server-id控制。认证配置在maven连接到远程服务时使用。



#### maven镜像，用于远程下载资源

```xml
<mirrors>
    <mirror>
      <!-- id：用于继承和直接查找，唯一 -->
      <id>mirrorId</id>
      <!-- mirrorOf：镜像所包含的仓库的Id -->
      <mirrorOf>repositoryId</mirrorOf>
      <!-- name：唯一标识，用于区分镜像站 -->
      <name>Human Readable Name for this Mirror.</name>
      <!-- url：镜像路径 -->
      <url>http://my.repository.com/repo/path</url>
    </mirror>
</mirrors>
```



#### profiles

1. settings.xml中时意味着该profile是全局的，所以只能配置范围宽泛一点配置信息，比如远程仓库等。而一些比较细致一点的需要定义在项目的pom.xml中。
2. profile可以让我们定义一系列的配置信息，然后指定其激活条件。
   根据每个profile对应不同的激活条件和配置信息，从而达到不同环境使用不同配置。
3. 例子：通过profile定义jdk1.5以上使用一套配置，jdk1.5以下使用另外一套配置；或者通过操作系统来使用不同的配置信息。
4. settings.xml中的信息有repositories、pluginRepositories和properties。定义在properties的值可以在pom.xml中使用。

```xml
<profiles>
    <profile>
              <id>test</id>
              <activation>
                 <activeByDefault>false</activeByDefault>
                  <!-- jdk：检测到对应jdk版本就激活 -->
                 <jdk>1.5</jdk>
                  <!-- os：针对不同操作系统 -->
                 <os>
                     <name>Windows XP</name>
                     <family>Windows</family>
                     <arch>x86</arch>
                     <version>5.1.2600</version>
                 </os>
                  <!-- property：当maven检测到property（pom中如${name}这样的）profile将被激活 -->
                 <property>
                     <name>mavenVersion</name>
                     <value>2.0.3</value>
                 </property>
                  <!-- file：如果存在文件，激活，不存在文件激活 -->
                 <file>
                	<exists>${basedir}/file2.properties</exists>
              		<missing>${basedir}/file1.properties</missing>
                </file>
             </activation>
         </profile>
</profiles>
```

通过以下命令查看哪些profile将生效

```
mvn help:active-profiles
```



**properites**

Maven的属性是值占位符，就像Ant中的一样。如果X是一个属性的话，在POM中可以使用${X}来进行任意地方的访问。他们来自于五种不同的风格，所有都可以从settings.xml文件中访问到。

1. env.x：“env.”前缀会返回当前的环境变量。如${env.PATH}就是使用了$path环境变量（windosws中的%PATH%）。
2. project.x：一个点“.”分割的路径，在POM中就是相关的元素的值。例如：<project><version>1.0</version></project>就可以通过${project.version}来访问。
3. settings.x：一个点“.”分割的路径，在settings.xml中就是相对应的元素的值，例如：<settings><offline>false</offline></settings>就可以通过${settings.offline}来访问。
4. Java系统属性：通过java.lang.System.getProperties()来访问的属性都可以像POM中的属性一样访问，例如：${java.home}
5. x：被<properties/>或者外部文件定义的属性，值可以这样访问${someVar}

```xml
 <profiles>
    <profile>
      ...
      <properties>
        <user.install>${user.home}/our-project</user.install>
      </properties>
      ...
    </profile>
  </profiles>
<!-- 上面这个profile如果被激活，那么在pom中${user.install}就可以被访问了。 -->
```



**Repositories**

​	Repositories是远程项目集合maven用来移植到本地仓库用于构建系统。如果来自本地仓库，Maven调用它的插件和依赖关系。不同的远程仓库可能包含不同的项目，当profile被激活，他们就会需找匹配的release或者snapshot构件。

```xml
<profiles>
    <profile>
      <repositories>
        <repository>
          <id>codehausSnapshots</id>
          <name>Codehaus Snapshots</name>
          <releases>
            <enabled>false</enabled>
            <updatePolicy>always</updatePolicy>
            <checksumPolicy>warn</checksumPolicy>
          </releases>
          <snapshots>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
            <checksumPolicy>fail</checksumPolicy>
          </snapshots>
          <url>http://snapshots.maven.codehaus.org/maven2</url>
          <layout>default</layout>
        </repository>
      </repositories>
      <pluginRepositories>
        
      </pluginRepositories>
    </profile>
  </profiles>
```

1. releases，snapshots：这是各种构件的策略，release或者snapshot。这两个集合，POM就可以根据独立仓库任意类型的依赖改变策略。如：一个人可能只激活下载snapshot用来开发。
2. enable：true或者false，决定仓库是否对于各自的类型激活(release 或者 snapshot)。
3. updatePolicy: 这个元素决定更新频率。maven将比较本地pom的时间戳（存储在仓库的maven数据文件中）和远程的. 有以下选择: always, daily (默认), interval:X (x是代表分钟的整型) ， never.
4. checksumPolicy：当Maven向仓库部署文件的时候，它也部署了相应的校验和文件。可选的为：ignore，fail，warn，或者不正确的校验和。
5. layout：在上面描述仓库的时候，提到他们有统一的布局。Maven 2有它仓库默认布局。然而，Maven 1.x有不同布局。使用这个元素来表明它是default还是legacy。



#### activeProfiles

```xml
<activeProfiles>
    <activeProfile>alwaysActiveProfile</activeProfile>
    <activeProfile>anotherAlwaysActiveProfile</activeProfile>
</activeProfiles>
```

每个activeProfile元素对应一个profile id的值，任何profile id被定义到activeProfile的profile将被激活。











