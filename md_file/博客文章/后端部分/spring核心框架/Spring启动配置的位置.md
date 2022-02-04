SpringBoot启动会扫描以下位置的application.yml或者 application.properties文件作为SpringBoot的默认配置文件。

-file:./config/   

-file:./

-classpath:/config/

-classpath:/

即根目录下的config目录下，然后是 根目录下，然后是classpath路径下的config目录下，最后是classpath路径下。

优先级由高到低，高优先级的配置会覆盖低优先级的配置。

eg.假如：根目录下的config目录下定义端口为8084，  根目录下定义端口为8083 ，classpath路径下的config目录定义端口为8082，classpath路径下定义端口为8081，最后启动，启动的端口为8084 ，高优先级会覆盖低优先级。

4.外部化配置（配置方式与优先级）
      Spring Boot 允许外化配置，Spring Boot使用了一个特别的PropertySource次序来允许对值进行覆盖，覆盖的优先级顺序如下：
  （1）Devtools全局设置主目录（~ /.spring-boot-devtools.properties 为活跃的）。
  （2）@TestPropertySource注解在Test。
  （3）@SpringBootTest#properties 注解在Test。
  （4）命令行参数。
  （5）从SPRING_APPLICATION_JSON属性(内联JSON嵌入在一个环境变量或系统属性)。
  （6）ServletConfig init参数。
  （7）ServletContext init参数。
  （8）JNDI属性java:comp/env。
  （9）Java系统属性(System.getProperties())。
  （10）操作系统环境变量。
  （11）RandomValuePropertySource配置的random.*属性值
  （12）打包在jar以外的application-{profile}.properties或application.yml配置文件
  （13）打包在jar以内的application-{profile}.properties或application.yml配置文件
  （14）打包在jar以外的application.properties或application.yml配置文件
  （15）打包在jar以内的application.properties或application.yml配置文件
  （16）@configuration注解类上的@PropertySource。
  （17）默认的属性(使用SpringApplication.setDefaultProperties指定)。

a) 通过命令行来修改默认参数，例如：
     启动命令：java -jar *.jar --name="chengli"
     以上的意思是，将name值修改为：chengli
b) 通过命令行来设置加载properties 例如：
     java -jar *.jar --spring.profiles.active=dev
     这里如果不了解profile的话，后面的文章中会介绍到。

5.application.properties文件按优先级，优先级高的会覆盖优先级低的
   优先级顺序如下：
  （1）当前目录下的一个/config子目录
  （2）当前目录
  （3）一个classpath下的/config包
  （4）classpath根目录



```shell

## 还有一个另外的方法 就是通用过InputStream直接通过Set方法设置好，直接无视任何的相对信息

```



