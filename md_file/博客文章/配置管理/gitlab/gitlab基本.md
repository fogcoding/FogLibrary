



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

```



```sql
-- 修改root密码
a、 切换目录：cd /opt/gitlab/bin

b、执行 ：sudo gitlab-rails console production 命令 开始初始化密码

c、在irb(main):001:0> 后面通过 u=User.where(id:1).first 来查找与切换账号（User.all 可以查看所有用户）

d、通过u.password='12345678'设置密码为12345678(这里的密码看自己喜欢)：

e、通过u.password_confirmation='12345678' 再次确认密码

f、通过 u.save!进行保存（切记切记 后面的 !）

g、如果看到上面截图中的true ，恭喜你已经成功了，执行 exit 退出当前设置流程即可。

h、回到gitlab ,可以通过 root/12345678 这一超级管理员账号登录了
```

