



#### JAVA调试方法



```sql
-- 开启远程调试的参数
JPDA_OPTS='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'
```



#### Spring-Boot 开启调试

```xml
<project>
 ...
 <build>
  ...
  <plugins>
   ...
   <plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <version>1.1.12.RELEASE</version>
    <configuration>
     <jvmArguments>
      -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005
     </jvmArguments>
    </configuration>
    ...
   </plugin>
   ...
  </plugins>
  ...
 </build>
 ...
</project>
```



![](D:\workspace\fog_projects\FogLibrary\md_file\博客文章\java基础\images\idea_debug.png)





#### 断点技巧

断点信息，类，行等各种方式



执行技巧

单步，跳出，光标，方法完毕等



条件断点，线程断点





调试层级

只调试工程代码，调试依赖jar包，调试jdk底层甚至更底层





