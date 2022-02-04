## SpringBoot集成Jsp页面与打包编译



#### 开启springboot工程





#### 集成mybatis







#### 集成jsp页面

```java

    //要避免瞎逼依赖，搞了一整天，解决方案确是取消一个依赖项即可正确运行，简直不知所谓！！！！
    //以后看别人的博客要学会擦亮眼睛！！！
    // springboot 自己原本依赖了一份org.thymeleaf的依赖，不要再去自己另外搞一份依赖

    // 再build标签里，添加资源目录，就是表示打包时要把这个目录打包进来，
    // 还有一个targetPath的标签，可以设置将这个目录的文件打包到某个特定的目录
    //相互配合起来，可以实现超多目录的统一打包

	//为了本地打包能够将webapp的文件包含进来，需要进行如下配置

	<build>
        <finalName>server</finalName>
        <plugins>
        	<!--  打包时需要使用到的插件  -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>1.4.2.RELEASE</version>
            </plugin>
        	<!--  打包时webapp需要使用到的插件  -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <configuration>
                    <encoding>UTF-8</encoding>
                    <useDefaultDelimiters>true</useDefaultDelimiters>
                </configuration>
            </plugin>
        </plugins>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>mybatis-config.xml</include>
                    <include>**/**.properties</include>
                    <include>**/**.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
            </resource>
            <resource>
                <directory>src/main/webapp</directory>
                <targetPath>META-INF/resources</targetPath>
                <includes>
                    <include>**/*.*</include>
                </includes>
            </resource>
        </resources>
    </build>






```





#### 纯mvn命令打包并jar执行

```powershell
# mvn打包
mvn clean package

# mvn运行springboot应用
mvn spring-boot:run

# java直接执行jar包
java -jar xxx.jar

# jar包解压查看打包情况
jar xvf xxx.jar



```






