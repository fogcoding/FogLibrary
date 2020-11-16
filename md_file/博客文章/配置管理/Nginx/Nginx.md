## Nginx



#### 安装依赖，编译，启动

```sql
-- 安装依赖
yum -y install gcc-c++  pcre pcre-devel  zlib zlib-devel openssl openssl-devel --setopt=protected_multilib=false 

-- 配置安装路径
./configure --prefix=/home/fogcoding/nginx

-- 编译
make

-- 安装
make install

```



#### 三方模块

下载地址：https://www.nginx.com/resources/wiki/modules/

```sql
-- 安装时添加一个模块
./configure --with-http_ssl_module --with-http_v2_module --with-http_realip_module --with-http_gunzip_module --with-http_gzip_static_module --add-module=${absolutPath}/nginx-sticky-module-ng

-- 注意，编译源码时有可能会提示找不到.h头文件，那么需要添加--with-http_ssl_module这样的参数提供源码依赖

```





