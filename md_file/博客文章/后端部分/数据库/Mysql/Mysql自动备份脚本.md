创建加密登录信息

mysql_config_editor set -G name -h ip地址 -p -P 端口 -u 用户名 --skip-warn

使用加密信息登录

mysql --login-path=创建时-G后面的名称

查看配置

mysql_config_editor print --all

移除配置

mysql_config_editor reset

mysql_config_editor remove --login-path=test

脚本使用方法

sh backup.sh db loginPathName

备份脚本

```
#！/bin/bash
back_home=/sxapp/sxappopt/backup/$(date '%F')
log=${back_home}/Mysql_dump.log
mkdir -p $back_home

db=$1
loginPath=$2

echo "back_home: $back_home"
echo "back_db: $db"
echo "use loginPath: $loginPath"

echo "[$(date '+%F %H:%M:%S')] start excute $db backup shell." >> $log
mysqldump --login-path=$loginPath --single-transaction --add-drop-trigger --set-gtid-purged=OFF -E $db|gzip > ${back_home}/$loginPath_$(date "+%Y%m%d_%H%M%S").zip
echo -n "[$(date '+%F %H:%M:%S')] " >> $log
ls -lh ${back_home}/*.zip >> $log
echo "[$(date '+%F %H:%M:%S')] end excute $db backup shell." >> $log
```

