

操作步骤：

```sql

-- 1.备份Oracle/Middleware/user_projects/domains/base_domain/security/DefaultAuthenticatorInit.ldift,避免出错

-- 2.cd  D:\Weblogic\Middleware\user_projects\domains\base_domain\security执行
java -classpath ~/Oracle/Middleware/wlserver_10.3/server/lib/weblogic.jar weblogic.security.utils.AdminAccount weblogic weblogic .

-- 3.备份数据目录
Oracle/Middleware/user_projects/domains/base_domain/servers/AdminServer/data 

-- 4.修改服务器的boot.properties文件，设置新密码
-- 修改其中的用户名与密码（用明文，第一次启动服务器时明文将被加密），要和上面命令中的用户名密码一致。
Oracle/Middleware/user_projects/domains/base_domain/servers/AdminServer/security /boot.properties

-- 5.重启weblogic服务即可



```





