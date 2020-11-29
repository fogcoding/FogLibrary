



windows下的安装版本

https://docs.gitlab.com/runner/install/windows.html



#### 目前似乎没有window版本



```shell
# gitlab-ce  依赖了pythoncoreutils,postfix这两种基础服务
```



```sql
-- gitlab-ctl是gitlab的控制命令入口，直接用-h即可看到命令清单

-- 执行gitlab-ctl reconfig等待时执行这个命令
sudo /opt/gitlab/embedded/bin/runsvdir-start

-- 官网安装是有效的，但直接用清华大学的开源包是更有效的，直接使用rpm即可

-- 安装完之后会在/var/opt/路径生成gitlab目录，这里是真实使用的各种配置信息路径


-- 不能启动gitlab
-- Solution
sudo systemctl start gitlab-runsvdir
-- then
sudo gitlab-ctl restart

```



```sql
-- 修改root密码
a、 切换目录：cd /opt/gitlab/bin

b、执行 ：sudo gitlab-rails console -e production 命令 开始初始化密码

c、在irb(main):001:0> 后面通过 u=User.where(id:1).first 来查找与切换账号（User.all 可以查看所有用户）

d、通过u.password='12345678'设置密码为12345678(这里的密码看自己喜欢)：

e、通过u.password_confirmation='12345678' 再次确认密码

f、通过 u.save!进行保存（切记切记 后面的 !）

g、如果看到上面截图中的true ，恭喜你已经成功了，执行 exit 退出当前设置流程即可。

h、回到gitlab ,可以通过 root/12345678 这一超级管理员账号登录了
```



#### gitlab的url地址修改

```sql
-- 找到对应目录
cd /opt/gitlab/embedded/service/gitlab-rails/config  

-- 修改里面的host配置
vim gitlab.yml  

-- 然后重启即可
```



#### 配置邮件服务

```sql
安装Postfix以发送通知电子邮件
--安装命令
yum install postfix

-- 激活命令
systemctl enable postfix

-- 启用命令
systemctl start postfix

-- 启动时如果显示有libmysqlcient.so.18的包缺失
yum install mysql-libs



-- 配置邮件信息
-- 在external_url 'http://192.168.1.107'  下面添加
gitlab_rails['gitlab_email_from'] = 'zjjszdfq01@126.com'
gitlab_rails['smtp_enable'] = true
gitlab_rails['smtp_address'] = "smtp.126.com"
gitlab_rails['smtp_port'] = 25
gitlab_rails['smtp_user_name'] = "zjjszdfq01@126.com"
gitlab_rails['smtp_password'] = "*******"
gitlab_rails['smtp_domain'] = "126.com"
gitlab_rails['smtp_authentication'] = "login"
gitlab_rails['smtp_enable_starttls_auto'] = true
user['git_user_email'] = "zjjszdfq01@126.com"


```

