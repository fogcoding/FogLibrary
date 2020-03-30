## sonar基本使用



#### sonar与mysql

新版本的sonarQube不支持mysql，所以需要选择旧版本，目前7.3版本经过测试是支持MySQL的。



#### 安装，配置，运行，登录

* 安装

  sonar是免安装的，直接下载好之后，解压即可。

* 配置

  在conf/目录下，sonar.properties配置数据库和登录账户密码。

  （注:配置之前需要装好mysql，并创建sonar数据库，赋予所有权限。）

  ```properties
  # Property values can:
  # - reference an environment variable, for example sonar.jdbc.url= ${env:SONAR_JDBC_URL}
  # - be encrypted. See https://redirect.sonarsource.com/doc/settings-encryption.html
  
  #--------------------------------------------------------------------------------------------------
  # DATABASE
  #
  # IMPORTANT:
  # - The embedded H2 database is used by default. It is recommended for tests but not for
  #   production use. Supported databases are MySQL, Oracle, PostgreSQL and Microsoft SQLServer.
  # - Changes to database connection URL (sonar.jdbc.url) can affect SonarSource licensed products.
  
  # User credentials.
  # Permissions to create tables, indices and triggers must be granted to JDBC user.
  # The schema must be created first.
  #sonar.jdbc.username=
  #sonar.jdbc.password=
  
  #----- Embedded Database (default)
  # H2 embedded database server listening port, defaults to 9092
  #sonar.embeddedDatabase.port=9092
  
  #----- DEPRECATED 
  #----- MySQL >=5.6 && <8.0
  # Support of MySQL is dropped in Data Center Editions and deprecated in all other editions
  # Only InnoDB storage engine is supported (not myISAM).
  # Only the bundled driver is supported. It can not be changed.
  #sonar.jdbc.url=jdbc:mysql://localhost:3306/sonar?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useConfigs=maxPerformance&useSSL=false
  
  sonar.jdbc.url=jdbc:mysql://localhost:3306/sonar?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useConfigs=maxPerformance&useSSL=false
  sonar.jdbc.username=root
  sonar.jdbc.password=123456
  sonar.sorceEncoding=UTF-8
  sonar.login=admin
  sonar.password=admin
  
  
  #----- Oracle 11g/12c
  # The Oracle JDBC driver must be copied into the directory extensions/jdbc-driver/oracle/.
  # Only the thin client is supported, and only the versions 11.2.x or 12.2.x must be used. See
  # https://jira.sonarsource.com/browse/SONAR-9758 for more details.
  # If you need to set the schema, please refer to http://jira.sonarsource.com/browse/SONAR-5000
  #sonar.jdbc.url=jdbc:oracle:thin:@localhost:1521/XE
  
  
  #----- PostgreSQL 9.3 or greater
  # If you don't use the schema named "public", please refer to http://jira.sonarsource.com/browse/SONAR-5000
  #sonar.jdbc.url=jdbc:postgresql://localhost/sonar
  
  
  #----- Microsoft SQLServer 2014/2016 and SQL Azure
  # A database named sonar must exist and its collation must be case-sensitive (CS) and accent-sensitive (AS)
  # Use the following connection string if you want to use integrated security with Microsoft Sql Server
  # Do not set sonar.jdbc.username or sonar.jdbc.password property if you are using Integrated Security
  # For Integrated Security to work, you have to download the Microsoft SQL JDBC driver package from
  # https://www.microsoft.com/en-us/download/details.aspx?id=55539
  # and copy sqljdbc_auth.dll to your path. You have to copy the 32 bit or 64 bit version of the dll
  # depending upon the architecture of your server machine.
  #sonar.jdbc.url=jdbc:sqlserver://localhost;databaseName=sonar;integratedSecurity=true
  
  # Use the following connection string if you want to use SQL Auth while connecting to MS Sql Server.
  # Set the sonar.jdbc.username and sonar.jdbc.password appropriately.
  #sonar.jdbc.url=jdbc:sqlserver://localhost;databaseName=sonar
  
  
  #----- Connection pool settings
  # The maximum number of active connections that can be allocated
  # at the same time, or negative for no limit.
  # The recommended value is 1.2 * max sizes of HTTP pools. For example if HTTP ports are
  # enabled with default sizes (50, see property sonar.web.http.maxThreads)
  # then sonar.jdbc.maxActive should be 1.2 * 50 = 60.
  #sonar.jdbc.maxActive=60
  
  # The maximum number of connections that can remain idle in the
  # pool, without extra ones being released, or negative for no limit.
  #sonar.jdbc.maxIdle=5
  
  # The minimum number of connections that can remain idle in the pool,
  # without extra ones being created, or zero to create none.
  #sonar.jdbc.minIdle=2
  
  # The maximum number of milliseconds that the pool will wait (when there
  # are no available connections) for a connection to be returned before
  # throwing an exception, or <= 0 to wait indefinitely.
  #sonar.jdbc.maxWait=5000
  
  #sonar.jdbc.minEvictableIdleTimeMillis=600000
  #sonar.jdbc.timeBetweenEvictionRunsMillis=30000
  
  
  
  #--------------------------------------------------------------------------------------------------
  # WEB SERVER
  # Web server is executed in a dedicated Java process. By default heap size is 512Mb.
  # Use the following property to customize JVM options.
  #    Recommendations:
  #
  #    The HotSpot Server VM is recommended. The property -server should be added if server mode
  #    is not enabled by default on your environment:
  #    http://docs.oracle.com/javase/8/docs/technotes/guides/vm/server-class.html
  #
  #    Startup can be long if entropy source is short of entropy. Adding
  #    -Djava.security.egd=file:/dev/./urandom is an option to resolve the problem.
  #    See https://wiki.apache.org/tomcat/HowTo/FasterStartUp#Entropy_Source
  #
  #sonar.web.javaOpts=-Xmx512m -Xms128m -XX:+HeapDumpOnOutOfMemoryError
  
  # Same as previous property, but allows to not repeat all other settings like -Xmx
  #sonar.web.javaAdditionalOpts=
  
  # Binding IP address. For servers with more than one IP address, this property specifies which
  # address will be used for listening on the specified ports.
  # By default, ports will be used on all IP addresses associated with the server.
  #sonar.web.host=0.0.0.0
  
  # Web context. When set, it must start with forward slash (for example /sonarqube).
  # The default value is root context (empty value).
  #sonar.web.context=
  # TCP port for incoming HTTP connections. Default value is 9000.
  #sonar.web.port=9000
  
  
  # The maximum number of connections that the server will accept and process at any given time.
  # When this number has been reached, the server will not accept any more connections until
  # the number of connections falls below this value. The operating system may still accept connections
  # based on the sonar.web.connections.acceptCount property. The default value is 50.
  #sonar.web.http.maxThreads=50
  
  # The minimum number of threads always kept running. The default value is 5.
  #sonar.web.http.minThreads=5
  
  # The maximum queue length for incoming connection requests when all possible request processing
  # threads are in use. Any requests received when the queue is full will be refused.
  # The default value is 25.
  #sonar.web.http.acceptCount=25
  
  # By default users are logged out and sessions closed when server is restarted.
  # If you prefer keeping user sessions open, a secret should be defined. Value is
  # HS256 key encoded with base64. It must be unique for each installation of SonarQube.
  # Example of command-line:
  # echo -n "type_what_you_want" | openssl dgst -sha256 -hmac "key" -binary | base64
  #sonar.auth.jwtBase64Hs256Secret=
  
  # The inactivity timeout duration of user sessions, in minutes. After the configured
  # period of time, the user is logged out.
  # The default value is set to 3 days (4320 minutes)
  # and cannot be greater than 3 months. Value must be strictly positive.
  #sonar.web.sessionTimeoutInMinutes=4320
  
  # A passcode can be defined to access some web services from monitoring
  # tools without having to use the credentials of a system administrator.
  # Check the Web API documentation to know which web services are supporting this authentication mode.
  # The passcode should be provided in HTTP requests with the header "X-Sonar-Passcode".
  # By default feature is disabled.
  #sonar.web.systemPasscode=
  
  
  #--------------------------------------------------------------------------------------------------
  # SSO AUTHENTICATION
  
  # Enable authentication using HTTP headers
  #sonar.web.sso.enable=false
  
  # Name of the header to get the user login.
  # Only alphanumeric, '.' and '@' characters are allowed
  #sonar.web.sso.loginHeader=X-Forwarded-Login
  
  # Name of the header to get the user name
  #sonar.web.sso.nameHeader=X-Forwarded-Name
  
  # Name of the header to get the user email (optional)
  #sonar.web.sso.emailHeader=X-Forwarded-Email
  
  # Name of the header to get the list of user groups, separated by comma (optional).
  # If the sonar.sso.groupsHeader is set, the user will belong to those groups if groups exist in SonarQube.
  # If none of the provided groups exists in SonarQube, the user will only belong to the default group.
  # Note that the default group will always be set.
  #sonar.web.sso.groupsHeader=X-Forwarded-Groups
  
  # Interval used to know when to refresh name, email and groups.
  # During this interval, if for instance the name of the user is changed in the header, it will only be updated after X minutes.
  #sonar.web.sso.refreshIntervalInMinutes=5
  
  #--------------------------------------------------------------------------------------------------
  # LDAP CONFIGURATION
  
  # Enable the LDAP feature
  # sonar.security.realm=LDAP
  
  # Set to true when connecting to a LDAP server using a case-insensitive setup.
  # sonar.authenticator.downcase=true
  
  # URL of the LDAP server. Note that if you are using ldaps, then you should install the server certificate into the Java truststore.
  # ldap.url=ldap://localhost:10389
  
  # Bind DN is the username of an LDAP user to connect (or bind) with. Leave this blank for anonymous access to the LDAP directory (optional)
  # ldap.bindDn=cn=sonar,ou=users,o=mycompany
  
  # Bind Password is the password of the user to connect with. Leave this blank for anonymous access to the LDAP directory (optional)
  # ldap.bindPassword=secret
  
  # Possible values: simple | CRAM-MD5 | DIGEST-MD5 | GSSAPI See http://java.sun.com/products/jndi/tutorial/ldap/security/auth.html (default: simple)
  # ldap.authentication=simple
  
  # See :
  #   * http://java.sun.com/products/jndi/tutorial/ldap/security/digest.html
  #   * http://java.sun.com/products/jndi/tutorial/ldap/security/crammd5.html
  # (optional)
  # ldap.realm=example.org
  
  # Context factory class (optional)
  # ldap.contextFactoryClass=com.sun.jndi.ldap.LdapCtxFactory
  
  # Enable usage of StartTLS (default : false)
  # ldap.StartTLS=true
  
  # Follow or not referrals. See http://docs.oracle.com/javase/jndi/tutorial/ldap/referral/jndi.html (default: true)
  # ldap.followReferrals=false
  
  # USER MAPPING
  
  # Distinguished Name (DN) of the root node in LDAP from which to search for users (mandatory)
  # ldap.user.baseDn=cn=users,dc=example,dc=org
  
  # LDAP user request. (default: (&(objectClass=inetOrgPerson)(uid={login})) )
  # ldap.user.request=(&(objectClass=user)(sAMAccountName={login}))
  
  # Attribute in LDAP defining the user’s real name. (default: cn)
  # ldap.user.realNameAttribute=name
  
  # Attribute in LDAP defining the user’s email. (default: mail)
  # ldap.user.emailAttribute=email
  
  # GROUP MAPPING
  
  # Distinguished Name (DN) of the root node in LDAP from which to search for groups. (optional, default: empty)
  # ldap.group.baseDn=cn=groups,dc=example,dc=org
  
  # LDAP group request (default: (&(objectClass=groupOfUniqueNames)(uniqueMember={dn})) )
  # ldap.group.request=(&(objectClass=group)(member={dn}))
  
  # Property used to specifiy the attribute to be used for returning the list of user groups in the compatibility mode. (default: cn)
  # ldap.group.idAttribute=sAMAccountName
  
  #--------------------------------------------------------------------------------------------------
  # COMPUTE ENGINE
  # The Compute Engine is responsible for processing background tasks.
  # Compute Engine is executed in a dedicated Java process. Default heap size is 512Mb.
  # Use the following property to customize JVM options.
  #    Recommendations:
  #
  #    The HotSpot Server VM is recommended. The property -server should be added if server mode
  #    is not enabled by default on your environment:
  #    http://docs.oracle.com/javase/8/docs/technotes/guides/vm/server-class.html
  #
  #sonar.ce.javaOpts=-Xmx512m -Xms128m -XX:+HeapDumpOnOutOfMemoryError
  
  # Same as previous property, but allows to not repeat all other settings like -Xmx
  #sonar.ce.javaAdditionalOpts=
  
  
  #--------------------------------------------------------------------------------------------------
  # ELASTICSEARCH
  # Elasticsearch is used to facilitate fast and accurate information retrieval.
  # It is executed in a dedicated Java process. Default heap size is 512Mb.
  #
  # --------------------------------------------------
  # Word of caution for Linux users on 64bits systems
  # --------------------------------------------------
  # Please ensure Virtual Memory on your system is correctly configured for Elasticsearch to run properly
  # (see https://www.elastic.co/guide/en/elasticsearch/reference/5.5/vm-max-map-count.html for details).
  #
  # When SonarQube runs standalone, a warning such as the following may appear in logs/es.log:
  #      "max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]"
  # When SonarQube runs as a cluster, however, Elasticsearch will refuse to start.
  #
  
  # JVM options of Elasticsearch process
  #sonar.search.javaOpts=-Xms512m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError
  
  # Same as previous property, but allows to not repeat all other settings like -Xmx
  #sonar.search.javaAdditionalOpts=
  
  # Elasticsearch port. Default is 9001. Use 0 to get a free port.
  # As a security precaution, should be blocked by a firewall and not exposed to the Internet.
  #sonar.search.port=9001
  
  # Elasticsearch host. The search server will bind this address and the search client will connect to it.
  # Default is loopback address.
  # As a security precaution, should NOT be set to a publicly available address.
  #sonar.search.host=
  
  
  #--------------------------------------------------------------------------------------------------
  # UPDATE CENTER
  
  # Update Center requires an internet connection to request https://update.sonarsource.org
  # It is enabled by default.
  #sonar.updatecenter.activate=true
  
  # HTTP proxy (default none)
  #http.proxyHost=
  #http.proxyPort=
  # HTTPS proxy (defaults are values of http.proxyHost and http.proxyPort)
  #https.proxyHost=
  #https.proxyPort=
  
  # NT domain name if NTLM proxy is used
  #http.auth.ntlm.domain=
  
  # SOCKS proxy (default none)
  #socksProxyHost=
  #socksProxyPort=
  
  # Proxy authentication (used for HTTP, HTTPS and SOCKS proxies)
  #http.proxyUser=
  #http.proxyPassword=
  
  
  #--------------------------------------------------------------------------------------------------
  # LOGGING
  
  # SonarQube produces logs in 4 logs files located in the same directory (see property sonar.path.logs below),
  # one per process:
  #   Main process (aka. App) logs in sonar.log
  #   Web Server (aka. Web) logs in web.log
  #   Compute Engine (aka. CE) logs in ce.log
  #   Elasticsearch (aka. ES) logs in es.log
  #
  # All 4 files follow the same rolling policy (see sonar.log.rollingPolicy and sonar.log.maxFiles) but it applies
  # individually (eg. if sonar.log.maxFiles=4, there can be at most 4 of each files, ie. 16 files in total).
  #
  # All 4 files have logs in the same format:
  #           1           2    3           4                       5                                                   6
  # |-----------------| |---| |-|--------------------||------------------------------| |------------------------------------------------------------------------------------------------------------------------------|
  # 2016.11.16 16:47:00 INFO  ce[AVht0dNXFcyiYejytc3m][o.s.s.c.t.CeWorkerCallableImpl] Executed task | project=org.sonarqube:example-java-maven | type=REPORT | id=AVht0dNXFcyiYejytc3m | submitter=admin | time=1699ms
  #
  # 1: timestamp. Format is YYYY.MM.DD HH:MM:SS
  #    YYYY: year on 4 digits
  #    MM: month on 2 digits
  #    DD: day on 2 digits
  #    HH: hour of day on 2 digits in 24 hours format
  #    MM: minutes on 2 digits
  #    SS: seconds on 2 digits
  # 2: log level.
  #    Possible values (in order of descending criticality): ERROR, WARN, INFO, DEBUG and TRACE
  # 3: process identifier. Possible values: app (main), web (Web Server), ce (Compute Engine) and es (Elasticsearch)
  # 4: SQ thread identifier. Can be empty.
  #    In the Web Server, if present, it will be the HTTP request ID.
  #    In the Compute Engine, if present, it will be the task ID.
  # 5: logger name. Usually a class canonical name.
  #    Package names are truncated to keep the whole field to 20 characters max
  # 6: log payload. Content of this field does not follow any specific format, can vary in length and include line returns.
  #    Some logs, however, will follow the convention to provide data in payload in the format " | key=value"
  #    Especially, log of profiled pieces of code will end with " | time=XXXXms".
  
  # Global level of logs (applies to all 4 processes).
  # Supported values are INFO (default), DEBUG and TRACE
  #sonar.log.level=INFO
  
  # Level of logs of each process can be controlled individually with their respective properties.
  # When specified, they overwrite the level defined at global level.
  # Supported values are INFO, DEBUG and TRACE
  #sonar.log.level.app=INFO
  #sonar.log.level.web=INFO
  #sonar.log.level.ce=INFO
  #sonar.log.level.es=INFO
  
  # Path to log files. Can be absolute or relative to installation directory.
  # Default is <installation home>/logs
  #sonar.path.logs=logs
  
  # Rolling policy of log files
  #    - based on time if value starts with "time:", for example by day ("time:yyyy-MM-dd")
  #      or by month ("time:yyyy-MM")
  #    - based on size if value starts with "size:", for example "size:10MB"
  #    - disabled if value is "none".  That needs logs to be managed by an external system like logrotate.
  #sonar.log.rollingPolicy=time:yyyy-MM-dd
  
  # Maximum number of files to keep if a rolling policy is enabled.
  #    - maximum value is 20 on size rolling policy
  #    - unlimited on time rolling policy. Set to zero to disable old file purging.
  #sonar.log.maxFiles=7
  
  # Access log is the list of all the HTTP requests received by server. If enabled, it is stored
  # in the file {sonar.path.logs}/access.log. This file follows the same rolling policy as other log file
  # (see sonar.log.rollingPolicy and sonar.log.maxFiles).
  #sonar.web.accessLogs.enable=true
  
  # Format of access log. It is ignored if sonar.web.accessLogs.enable=false. Possible values are:
  #    - "common" is the Common Log Format, shortcut to: %h %l %u %user %date "%r" %s %b
  #    - "combined" is another format widely recognized, shortcut to: %h %l %u [%t] "%r" %s %b "%i{Referer}" "%i{User-Agent}"
  #    - else a custom pattern. See http://logback.qos.ch/manual/layouts.html#AccessPatternLayout.
  # The login of authenticated user is not implemented with "%u" but with "%reqAttribute{LOGIN}" (since version 6.1).
  # The value displayed for anonymous users is "-".
  # The SonarQube's HTTP request ID can be added to the pattern with "%reqAttribute{ID}" (since version 6.2).
  # If SonarQube is behind a reverse proxy, then the following value allows to display the correct remote IP address:
  #sonar.web.accessLogs.pattern=%i{X-Forwarded-For} %l %u [%t] "%r" %s %b "%i{Referer}" "%i{User-Agent}" "%reqAttribute{ID}"
  # Default value (which was "combined" before version 6.2) is equivalent to "combined + SQ HTTP request ID":
  #sonar.web.accessLogs.pattern=%h %l %u [%t] "%r" %s %b "%i{Referer}" "%i{User-Agent}" "%reqAttribute{ID}"
  
  
  #--------------------------------------------------------------------------------------------------
  # OTHERS
  
  # Delay in seconds between processing of notification queue. Default is 60 seconds.
  #sonar.notifications.delay=60
  
  # Paths to persistent data files (embedded database and search index) and temporary files.
  # Can be absolute or relative to installation directory.
  # Defaults are respectively <installation home>/data and <installation home>/temp
  #sonar.path.data=data
  #sonar.path.temp=temp
  
  # Telemetry - Share anonymous SonarQube statistics
  # By sharing anonymous SonarQube statistics, you help us understand how SonarQube is used so we can improve the product to work even better for you.
  # We don't collect source code or IP addresses. And we don't share the data with anyone else.
  # To see an example of the data shared: login as a global administrator, call the WS api/system/info and check the Statistics field.
  #sonar.telemetry.enable=true
  
  
  #--------------------------------------------------------------------------------------------------
  # DEVELOPMENT - only for developers
  # The following properties MUST NOT be used in production environments.
  
  # Elasticsearch HTTP connector
  #sonar.search.httpPort=-1
  
  ```

  

  wrapper.conf配置不用动

* 运行

  bin目录中，选择系统，执行StartSonar.bat。

* 登录界面

  localhost:9000 ,有看到网页即表示运行成功，一开始会比较慢，但不会超过10分钟。



#### 配合mvn命令使用

* Token的获取

  一般进去会给一个账户一个token，假如遗失可以去账户中心，安全界面是配置新的token

* mvn命令直接使用sonar

  ```java
  mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=7609e1677ac1b6333c49ff3af913aee211b01f5f
  ```

  说明： login表示以token登录连接。

* 设置在maven的setting.xml中的profile，即可简单通过sonar指令执行sonar扫描

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  
  <!--
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at
  
      http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
  -->
  
  <!--
   | This is the configuration file for Maven. It can be specified at two levels:
   |
   |  1. User Level. This settings.xml file provides configuration for a single user,
   |                 and is normally provided in ${user.home}/.m2/settings.xml.
   |
   |                 NOTE: This location can be overridden with the CLI option:
   |
   |                 -s /path/to/user/settings.xml
   |
   |  2. Global Level. This settings.xml file provides configuration for all Maven
   |                 users on a machine (assuming they're all using the same Maven
   |                 installation). It's normally provided in
   |                 ${maven.conf}/settings.xml.
   |
   |                 NOTE: This location can be overridden with the CLI option:
   |
   |                 -gs /path/to/global/settings.xml
   |
   | The sections in this sample file are intended to give you a running start at
   | getting the most out of your Maven installation. Where appropriate, the default
   | values (values used when the setting is not specified) are provided.
   |
   |-->
  <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <!-- localRepository
     | The path to the local repository maven will use to store artifacts.
     |
     | Default: ${user.home}/.m2/repository
    <localRepository>/path/to/local/repo</localRepository>
    -->
  
    <!-- interactiveMode
     | This will determine whether maven prompts you when it needs input. If set to false,
     | maven will use a sensible default value, perhaps based on some other setting, for
     | the parameter in question.
     |
     | Default: true
    <interactiveMode>true</interactiveMode>
    -->
  
    <!-- offline
     | Determines whether maven should attempt to connect to the network when executing a build.
     | This will have an effect on artifact downloads, artifact deployment, and others.
     |
     | Default: false
    <offline>false</offline>
    -->
  
    <!-- pluginGroups
     | This is a list of additional group identifiers that will be searched when resolving plugins by their prefix, i.e.
     | when invoking a command line like "mvn prefix:goal". Maven will automatically add the group identifiers
     | "org.apache.maven.plugins" and "org.codehaus.mojo" if these are not already contained in the list.
     |-->
    <pluginGroups>
      <!-- pluginGroup
       | Specifies a further group identifier to use for plugin lookup.
      <pluginGroup>com.your.plugins</pluginGroup>
      -->
    </pluginGroups>
  
    <!-- proxies
     | This is a list of proxies which can be used on this machine to connect to the network.
     | Unless otherwise specified (by system property or command-line switch), the first proxy
     | specification in this list marked as active will be used.
     |-->
    <proxies>
      <!-- proxy
       | Specification for one proxy, to be used in connecting to the network.
       |
      <proxy>
        <id>optional</id>
        <active>true</active>
        <protocol>http</protocol>
        <username>proxyuser</username>
        <password>proxypass</password>
        <host>proxy.host.net</host>
        <port>80</port>
        <nonProxyHosts>local.net|some.host.com</nonProxyHosts>
      </proxy>
      -->
    </proxies>
  
    <!-- servers
     | This is a list of authentication profiles, keyed by the server-id used within the system.
     | Authentication profiles can be used whenever maven must make a connection to a remote server.
     |-->
    <servers>
      <!-- server
       | Specifies the authentication information to use when connecting to a particular server, identified by
       | a unique name within the system (referred to by the 'id' attribute below).
       |
       | NOTE: You should either specify username/password OR privateKey/passphrase, since these pairings are
       |       used together.
       |
      <server>
        <id>deploymentRepo</id>
        <username>repouser</username>
        <password>repopwd</password>
      </server>
      -->
  	
  	<server>
        <id>nexus-fog</id>
        <username>admin</username>
        <password>123456</password>
      </server>
  
      <!-- Another sample, using keys to authenticate.
      <server>
        <id>siteServer</id>
        <privateKey>/path/to/private/key</privateKey>
        <passphrase>optional; leave empty if not used.</passphrase>
      </server>
      -->
    </servers>
  
    <!-- mirrors
     | This is a list of mirrors to be used in downloading artifacts from remote repositories.
     |
     | It works like this: a POM may declare a repository to use in resolving certain artifacts.
     | However, this repository may have problems with heavy traffic at times, so people have mirrored
     | it to several places.
     |
     | That repository definition will have a unique id, so we can create a mirror reference for that
     | repository, to be used as an alternate download site. The mirror site will be the preferred
     | server for that repository.
     |-->
    <mirrors>
      <!-- mirror
       | Specifies a repository mirror site to use instead of a given repository. The repository that
       | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used
       | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.
       |
      <mirror>
        <id>mirrorId</id>
        <mirrorOf>repositoryId</mirrorOf>
        <name>Human Readable Name for this Mirror.</name>
        <url>http://my.repository.com/repo/path</url>
      </mirror>
       -->
  	 
  	 <mirror>
  		<id>nexus-aliyun</id>
  		<mirrorOf>central</mirrorOf>
  		<name>Nexus aliyun</name>
  		<url>http://maven.aliyun.com/nexus/content/groups/public</url>
  	</mirror>
  	 
  	  <mirror>
  		<id>nexus-fog</id>
  		<mirrorOf>central</mirrorOf>
  		<name>Nexus fog</name>
  		<url>http://127.0.0.1:9999/repository/maven-public/</url>
  	</mirror>
  	 
  	 
  	 
    </mirrors>
  
    <!-- profiles
     | This is a list of profiles which can be activated in a variety of ways, and which can modify
     | the build process. Profiles provided in the settings.xml are intended to provide local machine-
     | specific paths and repository locations which allow the build to work in the local environment.
     |
     | For example, if you have an integration testing plugin - like cactus - that needs to know where
     | your Tomcat instance is installed, you can provide a variable here such that the variable is
     | dereferenced during the build process to configure the cactus plugin.
     |
     | As noted above, profiles can be activated in a variety of ways. One way - the activeProfiles
     | section of this document (settings.xml) - will be discussed later. Another way essentially
     | relies on the detection of a system property, either matching a particular value for the property,
     | or merely testing its existence. Profiles can also be activated by JDK version prefix, where a
     | value of '1.4' might activate a profile when the build is executed on a JDK version of '1.4.2_07'.
     | Finally, the list of active profiles can be specified directly from the command line.
     |
     | NOTE: For profiles defined in the settings.xml, you are restricted to specifying only artifact
     |       repositories, plugin repositories, and free-form properties to be used as configuration
     |       variables for plugins in the POM.
     |
     |-->
    <profiles>
      <!-- profile
       | Specifies a set of introductions to the build process, to be activated using one or more of the
       | mechanisms described above. For inheritance purposes, and to activate profiles via <activatedProfiles/>
       | or the command line, profiles have to have an ID that is unique.
       |
       | An encouraged best practice for profile identification is to use a consistent naming convention
       | for profiles, such as 'env-dev', 'env-test', 'env-production', 'user-jdcasey', 'user-brett', etc.
       | This will make it more intuitive to understand what the set of introduced profiles is attempting
       | to accomplish, particularly when you only have a list of profile id's for debug.
       |
       | This profile example uses the JDK version to trigger activation, and provides a JDK-specific repo.
      <profile>
        <id>jdk-1.4</id>
  
        <activation>
          <jdk>1.4</jdk>
        </activation>
  
        <repositories>
          <repository>
            <id>jdk14</id>
            <name>Repository for JDK 1.4 builds</name>
            <url>http://www.myhost.com/maven/jdk14</url>
            <layout>default</layout>
            <snapshotPolicy>always</snapshotPolicy>
          </repository>
        </repositories>
      </profile>
      -->
  
      <!--
       | Here is another profile, activated by the system property 'target-env' with a value of 'dev',
       | which provides a specific path to the Tomcat instance. To use this, your plugin configuration
       | might hypothetically look like:
       |
       | ...
       | <plugin>
       |   <groupId>org.myco.myplugins</groupId>
       |   <artifactId>myplugin</artifactId>
       |
       |   <configuration>
       |     <tomcatLocation>${tomcatPath}</tomcatLocation>
       |   </configuration>
       | </plugin>
       | ...
       |
       | NOTE: If you just wanted to inject this configuration whenever someone set 'target-env' to
       |       anything, you could just leave off the <value/> inside the activation-property.
       |
      <profile>
        <id>env-dev</id>
  
        <activation>
          <property>
            <name>target-env</name>
            <value>dev</value>
          </property>
        </activation>
  
        <properties>
          <tomcatPath>/path/to/tomcat/instance</tomcatPath>
        </properties>
      </profile>
      -->
  	
  	<!-- 配置sonar指令的参数直接使用sonar扫描代码 -->
  	<profile>
        <id>sonar</id>
        <activation>
          <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
          <sonar.jdbc.url>jdbc:mysql://127.0.0.1:3306/sonar</sonar.jdbc.url>
          <sonar.jdbc.driver>com.mysql.jdbc.Driver</sonar.jdbc.driver>
          <sonar.jdbc.username>admin</sonar.jdbc.username>
          <sonar.jdbc.password>admin</sonar.jdbc.password>
          <!-- SERVER ON A REMOTE HOST -->
          <sonar.host.url>http://127.0.0.1:9000</sonar.host.url>
  		<!-- <sonar.java.binaries>classes/**</sonar.java.binaries> 记录自己的SX操作，既然是mvn项目就不用设置目录，否则设置了不能用mvn！  -->
  		<sonar.inclusions>src/main/java/**</sonar.inclusions>
  		<!--配置sonar的扫描路径，适用于非maven项目 -->
  		<sonar.inclusions>src/**</sonar.inclusions>
  		<sonar.exclusions>src/main/java/com/demo/sap1/**,
              src/main/webapp/static/**       
  		</sonar.exclusions>
        </properties>
      </profile>
    </profiles>
  
    <!-- activeProfiles
     | List of profiles that are active for all builds.
     |
    <activeProfiles>
      <activeProfile>alwaysActiveProfile</activeProfile>
      <activeProfile>anotherAlwaysActiveProfile</activeProfile>
    </activeProfiles>
    -->
  </settings>
  
  ```
  
  配置然后更新setting之后，仅仅需要执行
  
  ```shell
  mvn sonar:sonar
  ```
  
  即可执行sonar的代码扫描
  
* 插件的安装

  中文化插件和PDF报告插件是必须的，网络条件很好的情况下，可以直接去网站的市场下载，但是网速难以下载，则直接去下载对应的jar包，放在extensions目录下，再重启sonar即可生效。



#### 非maven工程的解决方案

​	借助工具sonar-runner来解决。

* sonar-runner的安装和配置

  下载好之后，进入cnf目录，配置sonar-runner.properties文件，用于关联sonar服务器

  ```properties
  #Configure here general information about the environment, such as SonarQube DB details for example
  #No information about specific project should appear here
  
  #----- Default SonarQube server
  #sonar.host.url=http://localhost:9000
  sonar.host.url=http://localhost:9000
  
  #----- PostgreSQL
  #sonar.jdbc.url=jdbc:postgresql://localhost/sonar
  
  #----- MySQL
  #sonar.jdbc.url=jdbc:mysql://localhost:3306/sonar?useUnicode=true&amp;characterEncoding=utf8
  sonar.jdbc.url=jdbc:mysql://localhost:3306/sonar?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true
  sonar.jdbc.driver=com.mysql.jdbc.Driver
  
  #----- Oracle
  #sonar.jdbc.url=jdbc:oracle:thin:@localhost/XE
  
  #----- Microsoft SQLServer
  #sonar.jdbc.url=jdbc:jtds:sqlserver://localhost/sonar;SelectMethod=Cursor
  
  #----- Global database settings
  #sonar.jdbc.username=sonar
  #sonar.jdbc.password=sonar
  
  #----- Default source code encoding
  #sonar.sourceEncoding=UTF-8
  sonar.sourceEncoding=UTF-8
  
  #----- Security (when 'sonar.forceAuthentication' is set to 'true')
  #sonar.login=admin
  #sonar.password=admin
  sonar.jdbc.username=root
  sonar.jdbc.password=123456
  
  ```

* 配置path路径，使得跳到非maven工程的路径下依然能够执行sonar-runner

* 在需要扫描工程的根目录下，新建sonar-project.properties文件，用以说明sonar需要的参数

```properties
# Required metadata
sonar.projectKey=my:project
sonar.projectName=java_core_skills_1
sonar.projectVersion=1.0

# Paths to source directories.
# Paths are relative to the sonar-project.properties file. Replace "\" by "/" on Windows.
# Do not put the "sonar-project.properties" file in the same directory with the source code.
# (i.e. never set the "sonar.sources" property to ".")
sonar.sources=D:/workspace/java/java_core_skills_1/src
sonar.java.binaries=D:/workspace/java/java_core_skills_1/classes

# 上面的路径windows用/分割,linux用\分割，否则会读不到。

# The value of the property must be the key of the language.
sonar.language=java

# Encoding of the source code
sonar.sourceEncoding=UTF-8

# Additional parameters
sonar.my.property=value

# note by: fogcoding
# sonar.projectKey和sonar.projectName内容无硬性规定，最好不要有中文
# 根据sonar.sources上方的注释可知在windows系统中，应该写"/"而不是"\",且不能和源代码文件放在同一目录下
# 而且经过测试我发现这里要写绝对路径。java_core_skills_1就是我的目标项目名
```

* 执行sonar-runner命令，即可启动扫描。



###### 注：非maven工程未找到办法直接在本地生成PDF报告的方法，可以去服务器下载。





