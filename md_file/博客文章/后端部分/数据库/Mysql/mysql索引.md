## MYSQL索引



#### 首先，mysql的文件分别存储了什么

引擎一：Mysiam

* frm:表定义文件
* myi：索引文件
* myd:数据文件

引擎二：Innodb

* frm：表定义文件
* ibd：MYD+MYI的合集，即数据信息与索引记录





#### 什么是索引？
* 简单的说，类似一本书的目录页，可以快速定位和查找内容
* 更专业的说，索引是帮助MySQL高效获取数据的，排好序的，数据结构。



聚集索引的概念：数据和索引分开存储，就是非聚集索引，数据和索引存储在一起，就是聚集索引。



#### 索引种类

* hash索引：由于利用hash算法，对于单个数据的存取十分高效，但是内存会不连续，浪费严重，并且有个致命的问题,对于范围查找的支持，相当得差

* Btree索引：由于默认由自增的主键组织和建立存储，在B+树，从左到右满足依次递增的关系，每层树高都是如此，并且由于，最后一级的叶子节点存在相邻数据的指针，对于范围查找的支持相当高效。
* 组合索引：多个字段，按照优先级逐个排序，就是通过三个影响因素排序。



为什么非主键索引结构叶子节点存储的是主键值？

一致性和节省存储空间



#### 索引的建立与删除，修改，更新

```sql
-- 显示表格的索引信息
show index from person; \G

-- 建立索引
alter table table_name add index index_name(column_name); 

-- 删除索引
drop index index_name on table_name;

```



#### 常见索引类型的建立与删除

```sql
-- 创建为唯一索引
create unique index index_name(column_name) on table_name;

-- 修改表结构新建唯一索引
alter table table_name add unique index_name(column_name);

-- 新建主键索引
alter table table_name add primary key (column_name);

-- 新建全文索引
alter table tb_name add fulltext index_name(column); 

-- 删除索引
alter table tb_name drop index index_name;

```











