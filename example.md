# 环境搭建手册

## 一、Jenkins平台：

(1)、Jenkins服务器：10.7.12.32   用户名/密码：[sxappopt/sxappopt@123](mailto:sxappopt/sxappopt@123)([root/root@12345](mailto:root/root@12345))

运行环境：jdk1.8.0_162 ，apache-tomcat-7.0.94

部署路径：/usr/local/jdk1.8.0_162 ，/usr/local/apache-tomcat-9.0.8

前台URL:http://10.7.12.32:8080/jenkins/  管理员：admin/admin

通过Tomcat进程运行Jenkins相关服务，插件安装通过代理可自动安装相关插件，Jenkins相关文件路径：/sxapp/sxappopt/.jenkins

 

（2）Mavne编译服务器：10.7.12.33   用户名/密码：[sxappopt/sxappopt@123](mailto:sxappopt/sxappopt@123)([root/root@12345](mailto:root/root@12345))

运行环境：jdk1.8.0_162 ，apache-maven-3.5.3、nexus-3.12.1-01

部署路径：/usr/local/jdk1.8.0_162 ，/usr/local/apache-maven-3.5.3 、/usr/local/nexus-3.12.1-01

nexus私服前台URL:[http://10.7.12.33:8081](http://10.7.12.33:8081/)  用户名/密码:admin/admin123

行方私服地址：http://10.7.10.19:8082/nexus/ 用户名/密码:admin/admin123

 

（3）SonarQube代码核查服务器：10.7.12.34 用户名/密码：[sxappopt/sxappopt@123](mailto:sxappopt/sxappopt@123)([root/root@12345](mailto:root/root@12345))

运行环境：jdk1.8.0_162 ，mysql5.7.25、sonarqube-6.7.4

部署路径：/usr/local/jdk1.8.0_162 ，/usr/local/mysql，/usr/local/sonarqube-6.7.4

数据库信息：库名sonar，用户名：sonar，密码：sonar@123

前台URL:http://10.7.12.34:9000/sonar/  管理员：admin/sxadmin

 

（4）Gitlab版本服务器：10.7.12.35  用户名/密码：[sxappopt/sxappopt@123](mailto:sxappopt/sxappopt@123)([root/root@12345](mailto:root/root@12345))  

运行环境：jdk1.8.0_162 ，gitlab-ce-10.4.0

部署路径：/usr/local/jdk1.8.0_162 ，/opt/gitlab

前台URL:http://10.7.12.35/users/sign_in  管理员用户密码：root/admin@123登录前注册。

 

## 二、源码库信息：

服务器地址：10.16.70.245

网贷系统：https://10.16.70.245/svn/网贷系统/code/

信贷系统：https://10.16.70.245/svn/新信贷系统/19-版本管理/开发库/trunk

互金核心：https://10.16.70.245/svn/互金核心/19-版本管理/生产库/trunk/互金核心

 

 

## 三、Confluence相关信息：

1、部署服务器：10.7.12.34

运行环境：jdk1.8.0_162 ，mysql5.7.25、atlassian-confluence-6.6.14

部署路径：/usr/local/jdk1.8.0_162 ，/usr/local/mysql，/usr/local/atlassian-confluence-6.6.14

数据库信息：库名：confluence，用户名：confluence，密码：confluence

前台URL:http://10.7.12.34:8090/welcome.action  管理员：admin/admin 注册新用户。

 

## 四、Jira相关信息：

1、部署服务器：10.7.5.17

运行环境：5.5.64-MariaDB、atlassian-jira-software-8.2.1

部署路径：/sxapp/JIRA/Mariadb/mysql，/opt/atlassian/jira

数据库信息：库名：jiradb，用户名：jira，密码：jira

服务启停：service mysqld start/stop/status(数据库); 

sh /opt/atlassian/jira/bin/start-jira.sh/sh /opt/atlassian/jira/bin/stop-jira.sh

前台URL:http://10.7.5.17:8081/ 

管理员账号： 密码zhouyh1027

 

## 五、禅道：

安装服务器：10.7.12.63

访问地址：http://10.7.12.63:9001/zentao/

安装路径：/opt/zbox

数据库端口7001

执行/opt/zbox/zbox start/stop/restart 命令启动/停止/重启Apache和Mysql。

用户名/密码：admin/admin@123

 

 

 

 

 

## 六、测试管理平台

1、服务器：10.7.2.13(WindowsServer12)  用户名/密码administrator/sxbank88!@# 在用。

 服务器：10.7.12.63(Linux RHEL6)  程序目录：/sxapp/sxappopt/app/SXBank_TMP_v3.0。 

用户名/密码root/root@12345,sxappopt/sxappopt@123

2、安装路径：D:\python\SXBank_TMP_v3.0

3、启动脚本：D:\python\SXBank_TMP_v3.0\start.bat

4、数据备份目录：D:\python\SXBank_TMP_v3.0\BACKUP，备份脚本：backup.bat

 

## 7、SVN服务器：

1、服务器：10.7.3.40(WindowsServer12)  用户名/密码administrator/sxbank88!@# 在用。

2、服务器：10.7.3.39(WindowsServer12)  用户名/密码administrator/sxbank88!@# 备份。

3、服务器：10.7.4.135(WindowsServer12)  用户名/密码administrator/sxbank88!@# 在用。

4、服务器：10.7.4.136(WindowsServer12)  用户名/密码administrator/sxbank88!@# 备份。

 

## 8、笔记

1、蚂蚁金服登录地址：https://auth.cloud.alipay.com/#/cloudauth/login [zhou_youheng@hoperun.com](mailto:zhou_youheng@hoperun.com) 密码：[zhouyh@123](mailto:zhouyh@123)

 

2、Devops项目信息：

系统登录：http://10.7.12.27:32002/#/Login zhouyouheng/123/1234

环境信息：

开发：10.7.10.43/44

SIT1：10.7.12.27/28

 

3、三湘邮箱：https://mail.csxbank.com/ zlgl_pzscm@csxbank.com Sxappopt@123 mail.csxbank.com 993

 

4、CI/CD相关脚本仓库地址。

https://10.7.4.231/svn/RECORD/cicd/trunk git工程对应的jenkins脚本地址

https://10.7.3.40/svn/conf/10-TMP/liuyan/script/library/trunk/resources svn工程（网贷、信贷）对应的jenkins脚本地址

 