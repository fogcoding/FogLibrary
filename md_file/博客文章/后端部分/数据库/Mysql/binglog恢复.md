



```sql


--执行语句
mysqlbinlog --stop-datetime="2021-06-21 20:09:55" --database=test d:\datacore\mysql-5.7.17-winx64\data\mysql_bin.00000118 | mysql -u root -p123456 -v test


-- 带正则的语句

cat recover_ivr.sql | grep  -A1 -B3 -i -E '^insert|^update|^delete|^replace|^alter' | grep -A1 -B3 mytable  > mytable.sql
```

