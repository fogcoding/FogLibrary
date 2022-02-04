## Jenkins插件开发基础



参考文档

官方wiki：https://wiki.jenkins.io/display/JENKINS/Plugin+tutorial#Plugintutorial-CreatingaNewPlugin

关键博客：https://blog.csdn.net/xlyrh/article/details/78366240

[起源资料](https://github.com/guacai/jenkins/blob/master/jenkins插件开发)



#### 步骤总结：

```xml
-- 1.安装JDK8+,MAVEN 3+，并配置运行环境

-- 2.替换maven的默认setting。xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <pluginGroups>
  	<pluginGroup>org.jenkins-ci.tools</pluginGroup>
  </pluginGroups>
  <mirrors>
  	 <mirror>
      <id>repo.jenkins-ci.org</id>
      <url>https://repo.jenkins-ci.org/public/</url>
      <mirrorOf>m.g.o-public</mirrorOf>
   </mirror>
  </mirrors>
  <profiles>
      <profile>
      <id>jenkins</id>
      <activation>
        <activeByDefault>true</activeByDefault> <!-- change this to false, if you don't like to have it on per default -->
      </activation>
      <repositories>
        <repository>
          <id>repo.jenkins-ci.org</id>
          <url>https://repo.jenkins-ci.org/public/</url>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <id>repo.jenkins-ci.org</id>
          <url>https://repo.jenkins-ci.org/public/</url>
        </pluginRepository>
      </pluginRepositories>
	  </profile>
   </profiles>
</settings>

-- 3.使用mvn命令生成示例工程，开启项目开发
 原命令：mvn hpi:create或者mvn -U org.jenkins-ci.tools:maven-hpi-plugin:create
 
 
 -- 4.若3步的命令失败，则使用以下命令，更新后使用的最新命令如下，首次创建需要Download必须的依赖，需要一些时间
mvn archetype:generate -Dfilter=io.jenkins.archetypes:plugin

-- 5.等下载好之后，输入对应的数字即可创建样板工程


-- 6.使用ide打开工程，进行代码编写后，直接打包，即可得到hpi文件，通过插件高级选项，上传插件即可安装自己开发的插件
mvn package 

```

