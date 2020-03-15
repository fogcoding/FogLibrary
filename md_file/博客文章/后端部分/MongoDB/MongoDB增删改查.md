## MongoDB增删改查



```sql
-- 创建数据库
use db_name;

-- 删除数据库,先切换到对应的数据库，再执行drop指令
use db_name
db.dropDatabase()

-- 查看所有数据库
show dbs

-- 创建集合,name: 要创建的集合名称,options: 可选参数, 指定有关内存大小及索引的选项
/*
options 可以是如下参数：
字段	       类型	     描述
capped	     布尔	  （可选）如果为 true，则创建固定集合。固定集合是指有着固定大小的集合，当达到最大值时，它会自动覆盖最早的文档。当该值为 true 时，必须指定 size 参数。
autoIndexId	 布尔	  （可选）如为 true，自动在 _id 字段创建索引。默认为 false。
size	     数值	  （可选）为固定集合指定一个最大值，以千字节计（KB）。如果capped 为 true，也需要指定该字段。
max	         数值	  （可选）指定固定集合中包含文档的最大数量。
在插入文档时，MongoDB 首先检查固定集合的 size 字段，然后检查 max 字段。
*/
db.createCollection(name, options)

-- 例如
db.createCollection("mycol", { capped : true, autoIndexId : true, size : 6142800, max : 10000 } )


-- 查看已有集合
show collections
show tables

-- 删除集合
db.collection_name.drop()

-- 插入文档
db.COLLECTION_NAME.insert(document)

-- 查看已插入的文档
db.collection_name.find()

--



```



入门参考网站：https://www.runoob.com/mongodb/mongodb-query.html



总结：这是一个专门为json形式的数据准备的数据库，对标的都是json数据类型，完全不属于关系型行列式数据库。

优点很显然，自带对于json形式的各种操作支持，拥有很大的可操作性，比较自由；缺点是太依赖json数据格式，没有关系型数据库那样标准化，可以支撑任何场景的再加工和再处理







