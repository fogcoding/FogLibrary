# [设置Oracle tnslsnr监听器口令](https://www.cnblogs.com/raym/p/10297393.html)

Oracle tnslsnr 监听器，加密主要为了防止监听被恶意远程关闭。关于这个安全问题的详细说明参见文字结尾转载的说明《Oracle的监听口令及监听器安全》

**主要用到以下几个命令：**

**1）lsnrctl 进入监听模式；**

**2）set password设置密码；**

**3）change_password 修改密码；**

**4）save_config 保存配置；**

**5）exit 退出监听；**

 

**#1：命令行输入： lsnrctl 回车**

C:\Documents and Settings\Administrator>lsnrctl

LSNRCTL for 32-bit Windows: Version 9.2.0.1.0 - Production on 21-1月 -2018 09:33:30

欢迎来到LSNRCTL，请键入"help"以获得信息。

 

**#2：输入 change_password 回车**

LSNRCTL> change_password

Old password:  **# 输入原密码，空直接回车**

New password: **# 输入新密码**

Reenter new password:  **# 重复输入新密码**

正在连接到 (DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=xxxx-94b9880xr5)(PORT=1521)))

LISTENER的口令已更改

命令执行成功

 

**#3：保存配置**

LSNRCTL> set password  **# 因为更改过密码，需要先设置新密码**

Password:

命令执行成功

LSNRCTL> save_config

正在连接到 (DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=xxxx-94b9880xr5)(PORT=1521)))

保存的LISTENER配置参数。

监听器参数文件 E:\oracle\ora92\network\admin\listener.ora

旧的参数文件E:\oracle\ora92\network\admin\listener.bak

命令执行成功

 

LSNRCTL> save_config **# 如果没有设置密码保存，就会提示错误**

正在连接到 (DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=rdlg-94b9880xr5)(PORT=1521)))))

TNS-01169: 监听器尚未识别口令

 

**#4：退出**

LSNRCTL> exit

 

**#5：验证**

打开文件 E:\oracle\ora92\network\admin\listener.ora

新增一下内容

\#----ADDED BY TNSLSNR 21-1月 -2018 09:34:41---

PASSWORDS_LISTENER = 6336EEA3D5E41DD9

\#---------------------------------------------

 

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
Oracle的监听口令及监听器安全
作者：eygle |English 【转载时请标明出处和作者信息】|【恩墨学院 OCM培训传DBA成功之道】
链接：http://www.eygle.com/archives/2007/11/listener_security.html
Oracle的监听器一直以来都存在一个严重的安全问题，那就是：
如果不设置安全措施，那么能够访问的用户就可以远程关闭监听器。

类似如下操作：

    D:\>lsnrctl stop eygle

    LSNRCTL for 32-bit Windows: Version 10.2.0.3.0 - Production on 28-11月-2007 10:02:40

    Copyright (c) 1991, 2006, Oracle.  All rights reserved.

    正在连接到 (DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=172.16.33.11)(PORT=1521))
    (CONNECT_DATA=(SERVICE_NAME=eygle)))
    命令执行成功


而此时缺省的监听器的日志还无法记录操作地址：

    No longer listening on: (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=172.16.33.11)(PORT=1521)))
    28-NOV-2007 09:59:20 * (CONNECT_DATA=(CID=(PROGRAM=)(HOST=)(USER=Administrator))(COMMAND=stop)
    (ARGUMENTS=64)(SERVICE=eygle)(VERSION=169870080)) * stop * 0


这个问题由来已久，为了保证监听器的安全，最好为监听设置密码：

    [oracle@jumper log]$ lsnrctl     

    LSNRCTL for Linux: Version 9.2.0.4.0 - Production on 28-NOV-2007 10:18:17

    Copyright (c) 1991, 2002, Oracle Corporation.  All rights reserved.

    Welcome to LSNRCTL, type "help" for information.

    LSNRCTL> set current_listener listener
    Current Listener is listener
    LSNRCTL> change_password
    Old password:
    New password:
    Reenter new password:
    Connecting to (DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=172.16.33.11)(PORT=1521)))
    Password changed for listener
    The command completed successfully
    LSNRCTL> set password
    Password:
    The command completed successfully
    LSNRCTL> save_config
    Connecting to (DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=172.16.33.11)(PORT=1521)))
    Saved LISTENER configuration parameters.
    Listener Parameter File  /opt/oracle/product/9.2.0/network/admin/listener.ora
    Old Parameter File  /opt/oracle/product/9.2.0/network/admin/listener.bak
    The command completed successfully


设置密码之后，远程操作将会因确实密码而失败：

    D:\>lsnrctl stop eygle

    LSNRCTL for 32-bit Windows: Version 10.2.0.3.0 - Production on 28-11月-2007 10:22:57
    Copyright (c) 1991, 2006, Oracle.  All rights reserved.

    正在连接到 (DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=172.16.33.11)
    (PORT=1521))(CONNECT_DATA=(SERVICE_NAME=eygle)))
    TNS-01169: 监听程序尚未识别口令


此时在服务器端或客户端，都需要通过密码来起停监听器：

    LSNRCTL> set password
    Password:
    The command completed successfully
    LSNRCTL> stop
    Connecting to (DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=172.16.33.11)(PORT=1521)))
    The command completed successfully
    LSNRCTL> start
    Starting /opt/oracle/product/9.2.0/bin/tnslsnr: please wait...

    TNSLSNR for Linux: Version 9.2.0.4.0 - Production
    System parameter file is /opt/oracle/product/9.2.0/network/admin/listener.ora
    Log messages written to /opt/oracle/product/9.2.0/network/log/listener.log
    Trace information written to /opt/oracle/product/9.2.0/network/trace/listener.trc
    Listening on: (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=172.16.33.11)(PORT=1521)))

    Connecting to (DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=172.16.33.11)(PORT=1521)))
    STATUS of the LISTENER
    ------------------------
    Alias                    LISTENER
    Version                  TNSLSNR for Linux: Version 9.2.0.4.0 - Production
    Start Date                28-NOV-2007 10:22:23
    Uptime                    0 days 0 hr. 0 min. 0 sec
    Trace Level              support
    Security                  ON
    SNMP                      OFF
    Listener Parameter File  /opt/oracle/product/9.2.0/network/admin/listener.ora
    Listener Log File        /opt/oracle/product/9.2.0/network/log/listener.log
    Listener Trace File      /opt/oracle/product/9.2.0/network/trace/listener.trc
    Listening Endpoints Summary...
      (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=172.16.33.11)(PORT=1521)))
    Services Summary...
    Service "eygle" has 1 instance(s).
      Instance "eygle", status UNKNOWN, has 1 handler(s) for this service...
    Service "julia" has 1 instance(s).
      Instance "eygle", status UNKNOWN, has 1 handler(s) for this service...
    The command completed successfully


此外，ADMIN_RESTRICTIONS参数也是一个重要的安全选项，我们可以在 listener.ora 文件中设置 ADMIN_RESTRICTIONS_<listener name> 为 ON，此后所有在运行时对监听器的修改都将被阻止，所有对监听器的修改都必须通过手工修改 listener.ora 文件来完成。

关于监听器安全参考文档：
Integrigy_Oracle_Listener_TNS_Security.pdf

-The End-
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

 