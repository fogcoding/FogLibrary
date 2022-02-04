

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


--20211022 补充
-- 特别注意：username 和  password  相互对应好  一旦填错了就会报账户密码验证不过，启动都会失败

-- 账户密码输入失败五次，则会暂时锁定账户30分钟，即使输入正确也无法进入


```





