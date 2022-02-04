## MySQL删除的效率问题



*  当操作几百万，几千万，甚至几亿的大表时，如果需要删除相互关联的数据，通常很多人都会写出一种如下的sql语句：

```sql
DELETE from tb_a where INTERNAL_KEY NOT in (select  substring(IRL_INTERNAL_KEY, 3) INTERNAL_KEY from tb_c);
DELETE from tb_b where INTERNAL_KEY NOT in (select  substring(IRL_INTERNAL_KEY, 3) INTERNAL_KEY from tb_c);
COMMIT;
```

  上面的语句，作用很显然：表a,b同时关联了表c的数据，如果表a，b存在表c中没有的数据，那么可以认为其中的数据有误，需要删除。

  看起来没有什么大问题，但是这里存在一个极大的问题：如果表c的数据非常大，首先就要查询表c的总数据量n_c,并且由于条件时表a的数据要不能在表c中，那么表a的数据总量n_a，为了条件判定会重复去比对表c的结果，于是这里需要执行的判断次数为n_c乘以n_a！！！

  当表a与表c的数据不大时，这样的操作也没有什么很大的问题，但是当表a与表c数据量为100万时，需要执行的次数时100万乘以100万！！！ 这是很恐怖的数量！！！

  而更大的，当其中一张表数据量超过一亿时，这几乎是个灾难！



#### 以上问题的总结：

* 首先where条件的设定是很关键的影响因素

```sql

-- 注：由于innodb默认主键为索引，并以此为B+数排列依据，但为了简单说明并记录这种情况，默认此时的id不走索引。
-- 方法1：直接指定删除条件，重复N条语句
delete from tb_a where id=xxx;

-- 方法2：设定条件范围
delete from tb_a where id > 1000 and id < 9999;

-- 从逻辑来看，以上两种方法是一样的效果，但是有一个潜在的原因，删除数据首先需要确定这个数据在哪里，也就是需要找到数据，即数据的查找。
-- 也就是说，删除数据总是会默认执行数据的查找操作，以确定需要删除数据的位置。
-- 直接指定了条件，那么查找起来就会相当简单，只需要根据索引对应读取即可，几乎不消耗查找时间
-- 但是如果设定了某个条件，而这个条件又没有走索引，这样就会产生巨量的数据查找时间消耗！！

-- 这对我们以后操作巨量数据时，设定各种条件需要引起相当的重视！！
```



#### 在海量数据的大表中删除数据的高效率办法

##### 改良后的执行步骤

```sql
-- 默认此时工具为：navicat
-- 步骤1：先将需要操作的特定数据查出来
select * from tb_a where INTERNAL_KEY NOT in (select  substring(IRL_INTERNAL_KEY, 3) INTERNAL_KEY from tb_c);

--  步骤2：在结果中，全选然后删除
```

#####  对比分析原语句

```sql
DELETE from tb_a where INTERNAL_KEY NOT in (select  substring(IRL_INTERNAL_KEY, 3) INTERNAL_KEY from tb_c);
DELETE from tb_b where INTERNAL_KEY NOT in (select  substring(IRL_INTERNAL_KEY, 3) INTERNAL_KEY from tb_c);
COMMIT;
```



​	原语句就是最开始提到的那种数量积爆炸的情况，这样会导致超大量的无效操作！

​	而改良后的办法，由于在结果中，直接绑定了各种清晰的条件，这样会使得所有的数据查找时间消耗直接归0，只需要执行最关键的删除操作，会带来海量的效率提升。



#### 千万级别的大表，如何提升效率



```sql
-- 删除语句
-- 只删除数据，保留表状态
drop table tb_name;
-- 清掉所有的表状态与信息，仅仅保留表结构
truncate table tb_name;

```



​	一般来说，直接删掉整个表，会导致IO直接拉满。

​	因为，删除操作涉及到两个层面，一个是mysql本身的逻辑，一个是linux系统的文件系统；同时在两个层次上做千万级别的大表删除，涉及到的缓存，文件块，信息记录，回滚，这必然导致效率极端低下，又抢占了绝大部分的资源，如果是生产环境，几乎等同于宕机，所以需要某些特殊的办法来进行负载减轻和效率提升。

实施步骤：

```sql
-- 1.对数据进行硬链接
ln xxx.ibd xxx.ibd.link

-- 2.执行drop命令
drop table tb_name

-- 3.删掉产生的硬链接文件
rm xxx.ibd.link
```



​	经过实际的测试，这样对于删除大表的效率，会有相当显著的提升，但目前没有做IO效率和资源的测试分析。而其中的原理就是linux文件系统如果某个文件块的链接数大于1，那么删除对应文件块的文件名并不会真的去删除文件块的内容，而仅仅只会删除链接；这样一来，linux系统的文件io速率会有超强的提升，因为不需要真的执行删除数据的操作。



#### 改表名删除某个表的方法

```sql
-- 据说改表名是毫秒级别的操作，但是我去测试，为什么并不是这样的




```

















