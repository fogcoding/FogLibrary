## Linux用户系统操作备忘录



```shell
## 增加一个用户
useradd user_name

## 删除一个用户

## 增加一个用户组

## 删除一个用户组

## 设置用户密码
passwd user_name




```



#### 设置免密登录

```sql
-- 选择ssh无密码远程登陆
-- 1.首先在客户端生成ssh密钥，打开命令行工具，我直接使用的git的git bash
 ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
 
-- 如果已经生成了，那么进入cd ~/.ssh cat id_rsa.pub查看
-- ssh-copy-id 你的用户名@你的IP
-- 例如 ssh-copy-id username@192.168.1.1
-- 会自动把公钥传输到服务端的$HOME/.ssh/authorized_keys文件夹里
```



