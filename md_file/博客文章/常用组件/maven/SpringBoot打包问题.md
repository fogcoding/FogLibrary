

## SoringBoot打包问题



#### 启动application.x的路径位置

```Java
// 可以直接通过java代码设置，而properties文件可以通过IO读取
application.setDefaultProperties(application_properties);

```



#### 所有依赖打包进一个jar包



````xml

--  如果父工程是Spring-boot，只需要添加spring-boot编译插件

<plugin>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>


-- 如果是独立的Spring项目
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                 <mainClass>com.szmirren.Main</mainClass>　　<!-- 你的主类全路径 -->
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>


-- 对编译添加各种设置
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.2</version>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
        <encoding>UTF-8</encoding>
    </configuration>
</plugin>
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <skipTests>true</skipTests>    <!--默认关掉单元测试 -->
    </configuration>
</plugin>

````

