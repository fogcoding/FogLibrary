## NOSQL的四大分类



#### 键值数据库

  相关产品：Redis、Riak、SimpleDB、Chordless、Scalaris、Memcached

  应用：内容缓存

  优点：扩展性好、灵活性好、大量写操作时性能高

  缺点：无法存储结构化信息、条件查询效率较低

  使用者：百度云（Redis）、GitHub（Riak）、BestBuy（Riak）、Twitter（Ridis和Memcached）

#### 列族数据库

  相关产品：BigTable、HBase、Cassandra、HadoopDB、GreenPlum、PNUTS

  应用：分布式数据存储与管理

  优点：查找速度快、可扩展性强、容易进行分布式扩展、复杂性低

  使用者：Ebay（Cassandra）、Instagram（Cassandra）、NASA（Cassandra）、Facebook（HBase）

#### 文档数据库

  相关产品：MongoDB、CouchDB、ThruDB、CloudKit、Perservere、Jackrabbit

  应用：存储、索引并管理面向文档的数据或者类似的半结构化数据

  优点：性能好、灵活性高、复杂性低、数据结构灵活

  缺点：缺乏统一的查询语言

  使用者：百度云数据库（MongoDB）、SAP（MongoDB）

#### 图形数据库

  相关产品：Neo4J、OrientDB、InfoGrid、GraphDB

  应用：大量复杂、互连接、低结构化的图结构场合，如社交网络、推荐系统等

  优点：灵活性高、支持复杂的图形算法、可用于构建复杂的关系图谱

  缺点：复杂性高、只能支持一定的数据规模

  使用者：Adobe（Neo4J）、Cisco（Neo4J）、T-Mobile（Neo4J）