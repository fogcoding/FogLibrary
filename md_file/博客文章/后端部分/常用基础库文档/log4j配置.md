log4j2.xml文件的配置大致如下：

- Configuration 
  - properties
  - Appenders 
    - Console 
      - PatternLayout
    - File
    - RollingRandomAccessFile
    - Async
  - Loggers 
    - Logger
    - Root 
      - AppenderRef

------

- **Configuration：**为根节点，有status和monitorInterval等多个属性

  - status的值有 “trace”, “debug”, “info”, “warn”, “error” and “fatal”，用于控制log4j2日志框架本身的日志级别，如果将stratus设置为较低的级别就会看到很多关于log4j2本身的日志，如加载log4j2配置文件的路径等信息
  - monitorInterval，含义是每隔多少秒重新读取配置文件，可以不重启应用的情况下修改配置

- **Appenders：**输出源，用于定义日志输出的地方 
  log4j2支持的输出源有很多，有控制台Console、文件File、RollingRandomAccessFile、MongoDB、Flume 等

  - **Console：**控制台输出源是将日志打印到控制台上，开发的时候一般都会配置，以便调试
  - **File：**文件输出源，用于将日志写入到指定的文件，需要配置输入到哪个位置（例如：D:/logs/mylog.log）
  - ***\*RollingRandomAccessFile:\**** 该输出源也是写入到文件，不同的是比File更加强大，可以指定当文件达到一定大小（如20MB）时，另起一个文件继续写入日志，另起一个文件就涉及到新文件的名字命名规则，因此需要配置文件命名规则 
    这种方式更加实用，因为你不可能一直往一个文件中写，如果一直写，文件过大，打开就会卡死，也不便于查找日志。
    - fileName 指定当前日志文件的位置和文件名称
    - filePattern 指定当发生Rolling时，文件的转移和重命名规则
    - SizeBasedTriggeringPolicy 指定当文件体积大于size指定的值时，触发Rolling
    - DefaultRolloverStrategy 指定最多保存的文件个数
    - TimeBasedTriggeringPolicy 这个配置需要和filePattern结合使用，注意filePattern中配置的文件重命名规则是${FILE_NAME}-%d{yyyy-MM-dd HH-mm}-%i，最小的时间粒度是mm，即分钟
    - TimeBasedTriggeringPolicy指定的size是1，结合起来就是每1分钟生成一个新文件。如果改成%d{yyyy-MM-dd HH}，最小粒度为小时，则每一个小时生成一个文件
  - **NoSql：**MongoDb, 输出到MongDb数据库中
  - **Flume：**输出到Apache Flume（Flume是Cloudera提供的一个高可用的，高可靠的，分布式的海量日志采集、聚合和传输的系统，Flume支持在日志系统中定制各类数据发送方，用于收集数据；同时，Flume提供对数据进行简单处理，并写到各种数据接受方（可定制）的能力。）
  - **Async：**异步，需要通过AppenderRef来指定要对哪种输出源进行异步（一般用于配置RollingRandomAccessFile）

  **PatternLayout：**控制台或文件输出源（Console、File、RollingRandomAccessFile）都必须包含一个PatternLayout节点，用于指定输出文件的格式（如 日志输出的时间 文件 方法 行数 等格式），例如 pattern=”%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n”

  ```properties
  %d{HH:mm:ss.SSS} 表示输出到毫秒的时间
  
  %t 输出当前线程名称
  
  %-5level 输出日志级别，-5表示左对齐并且固定输出5个字符，如果不足在右边补0
  
  %logger 输出logger名称，因为Root Logger没有名称，所以没有输出
  
  %msg 日志文本
  
  %n 换行
  
  其他常用的占位符有：
  
  %F 输出所在的类文件名，如Log4j2Test.java
  
  %L 输出行号
  
  %M 输出所在方法名
  
  %l 输出语句所在的行数, 包括类名、方法名、文件名、行数
  ```

- **Loggers：**日志器 
  日志器分根日志器Root和自定义日志器，当根据日志名字获取不到指定的日志器时就使用Root作为默认的日志器，自定义时需要指定每个Logger的名称name（对于命名可以以包名作为日志的名字，不同的包配置不同的级别等），日志级别level，相加性additivity（是否继承下面配置的日志器）， 对于一般的日志器（如Console、File、RollingRandomAccessFile）一般需要配置一个或多个输出源AppenderRef；

  每个logger可以指定一个level（TRACE, DEBUG, INFO, WARN, ERROR, ALL or OFF），不指定时level默认为ERROR

  additivity指定是否同时输出log到父类的appender，缺省为true。

```html
<Logger name="rollingRandomAccessFileLogger" level="trace" additivity="true">  
    <AppenderRef ref="RollingRandomAccessFile" />  
</Logger>
```

- **properties:** 属性 
  使用来定义常量，以便在其他配置的时候引用，该配置是可选的，例如定义日志的存放位置 
  D:/logs

