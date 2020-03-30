zabbix自动发现java的监听端口

1.自动发现脚本内容，需要传到对应的agent机器

[sxappopt@uat1bmpifpfn tmp]$  discoverJAVA.sh

```shell
\#!/bin/sh
port_arry=(`ss -tnlp|grep -v grep|grep java|awk '{print $4}'|awk -F ':' '{print $NF}'|sort|uniq`)

length=${#port_arry[@]}

echo "{"
echo "  \"data\":["
for ((i=0;i<$length;i++))
do
  if [ $i -lt $[$length-1] ];then
   echo "   {\"{#JAVA_PORT}\":\"${port_arry[$i]}\"},"
  else
   echo "   {\"{#JAVA_PORT}\":\"${port_arry[$i]}\"}"
  fi
done
echo "  ]"
echo "}"
```

2.脚本增加执行权限
chmod +x discoverJAVA.sh

3.修改/etc/zabbix/zabbix_agentd.conf增加以下参数，然后重启zabbix_agent
AllowRoot=1
UserParameter=JAVAdiscover,/tmp/discoverJAVA.sh

4.在服务器测试返回数据
zabbix_get -s 10.7.24.103 -k JAVAdiscover
{
  "data":[
    {"{#JAVA_PORT}":"18890"},
    {"{#JAVA_PORT}":"21553"},
    {"{#JAVA_PORT}":"21555"},
    {"{#JAVA_PORT}":"21777"},
    {"{#JAVA_PORT}":"2181"},
    {"{#JAVA_PORT}":"38060"},
    {"{#JAVA_PORT}":"6662"},
    {"{#JAVA_PORT}":"8005"},
    {"{#JAVA_PORT}":"8009"},
    {"{#JAVA_PORT}":"8080"},
    {"{#JAVA_PORT}":"8089"},
    {"{#JAVA_PORT}":"9001"},
    {"{#JAVA_PORT}":"9002"},
    {"{#JAVA_PORT}":"9010"}
  ]
}

5. 在zabbix前台创建自动发现规则

5.1 创建模板或在已有模板中创建自动发现规则，发现规则的键值用配置好的JAVAdiscover
![](images\zabbix_discover1.png)
![](images\zabbix_discover2.png)

5.2 添加监控原型，在对象被发现后会自动创建监控项
![](images\zabbix_discover3.png)

5.3 添加触发器原型
![](images\zabbix_discover4.png)