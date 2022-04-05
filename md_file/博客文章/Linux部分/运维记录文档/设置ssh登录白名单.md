## 设置ssh登录白名单



```shell
linux 服务器配置ssh白名单 只允许堡垒机登陆ssh
vi /etc/hosts.allow

sshd:192.168.7.x:allow   #单个ip(堡垒机)

sshd:192.168.7.0/255.255.248.0:allow #一个网段的

vi /etc/hosts.deny

sshd:all:deny

systemctl restart sshd
```

