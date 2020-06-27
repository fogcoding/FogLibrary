| 第8章优化                          |      |                                   |
| :--------------------------------- | ---- | --------------------------------- |
| [上一个](backup-and-recovery.html) |      | [下一个](language-structure.html) |

------

# 第8章优化

**目录**

- [8.1优化概述](optimization.html#optimize-overview)

- [8.2优化SQL语句](optimization.html#statement-optimization)

  [8.2.1优化SELECT语句](optimization.html#select-optimization)[8.2.2优化子查询，派生表和视图引用](optimization.html#subquery-optimization)[8.2.3优化INFORMATION_SCHEMA查询](optimization.html#information-schema-optimization)[8.2.4优化数据更改语句](optimization.html#data-change-optimization)[8.2.5优化数据库特权](optimization.html#permission-optimization)[8.2.6其他优化技巧](optimization.html#miscellaneous-optimization-tips)

- [8.3优化和索引](optimization.html#optimization-indexes)

  [8.3.1 MySQL如何使用索引](optimization.html#mysql-indexes)[8.3.2主键优化](optimization.html#primary-key-optimization)[8.3.3外键优化](optimization.html#foreign-key-optimization)[8.3.4列索引](optimization.html#column-indexes)[8.3.5多列索引](optimization.html#multiple-column-indexes)[8.3.6验证索引使用情况](optimization.html#verifying-index-usage)[8.3.7 InnoDB和MyISAM索引统计信息收集](optimization.html#index-statistics)[8.3.8 B树和哈希索引的比较](optimization.html#index-btree-hash)[8.3.9索引扩展的使用](optimization.html#index-extensions)[8.3.10优化器对生成的列索引的使用](optimization.html#generated-column-index-optimizations)[8.3.11从TIMESTAMP列进行索引查找](optimization.html#timestamp-lookups)

- [8.4优化数据库结构](optimization.html#optimizing-database-structure)

  [8.4.1优化数据大小](optimization.html#data-size)[8.4.2优化MySQL数据类型](optimization.html#optimize-data-types)[8.4.3优化许多表](optimization.html#optimize-multi-tables)[8.4.4 MySQL中内部临时表的使用](optimization.html#internal-temporary-tables)[8.4.5数据库和表数限制](optimization.html#database-count-limit)[8.4.6表格大小限制](optimization.html#table-size-limit)[8.4.7表列数和行大小的限制](optimization.html#column-count-limit)

- [8.5优化InnoDB表](optimization.html#optimizing-innodb)

  [8.5.1优化InnoDB表的存储布局](optimization.html#optimizing-innodb-storage-layout)[8.5.2优化InnoDB事务管理](optimization.html#optimizing-innodb-transaction-management)[8.5.3优化InnoDB只读事务](optimization.html#innodb-performance-ro-txn)[8.5.4优化InnoDB重做日志](optimization.html#optimizing-innodb-logging)[8.5.5 InnoDB表的批量数据加载](optimization.html#optimizing-innodb-bulk-data-loading)[8.5.6优化InnoDB查询](optimization.html#optimizing-innodb-queries)[8.5.7优化InnoDB DDL操作](optimization.html#optimizing-innodb-ddl-operations)[8.5.8优化InnoDB磁盘I / O](optimization.html#optimizing-innodb-diskio)[8.5.9优化InnoDB配置变量](optimization.html#optimizing-innodb-configuration-variables)[8.5.10为具有多个表的系统优化InnoDB](optimization.html#optimizing-innodb-many-tables)

- [8.6优化MyISAM表](optimization.html#optimizing-myisam)

  [8.6.1优化MyISAM查询](optimization.html#optimizing-queries-myisam)[8.6.2 MyISAM表的批量数据加载](optimization.html#optimizing-myisam-bulk-data-loading)[8.6.3优化REPAIR TABLE语句](optimization.html#repair-table-optimization)

- [8.7优化内存表](optimization.html#optimizing-memory-tables)

- [8.8了解查询执行计划](optimization.html#execution-plan-information)

  [8.8.1使用EXPLAIN优化查询](optimization.html#using-explain)[8.8.2说明输出格式](optimization.html#explain-output)[8.8.3扩展的EXPLAIN输出格式](optimization.html#explain-extended)[8.8.4获取命名连接的执行计划信息](optimization.html#explain-for-connection)[8.8.5估计查询性能](optimization.html#estimating-performance)

- [8.9控制查询优化器](optimization.html#controlling-optimizer)

  [8.9.1控制查询计划评估](optimization.html#controlling-query-plan-evaluation)[8.9.2可切换的优化](optimization.html#switchable-optimizations)[8.9.3优化器提示](optimization.html#optimizer-hints)[8.9.4索引提示](optimization.html#index-hints)[8.9.5优化器成本模型](optimization.html#cost-model)

- [8.10缓冲和缓存](optimization.html#buffering-caching)

  [8.10.1 InnoDB缓冲池优化](optimization.html#innodb-buffer-pool-optimization)[8.10.2 MyISAM密钥缓存](optimization.html#myisam-key-cache)[8.10.3 MySQL查询缓存](optimization.html#query-cache)[8.10.4缓存准备好的语句和存储的程序](optimization.html#statement-caching)

- [8.11优化锁定操作](optimization.html#locking-issues)

  [8.11.1内部锁定方法](optimization.html#internal-locking)[8.11.2表锁定问题](optimization.html#table-locking)[8.11.3并发插入](optimization.html#concurrent-inserts)[8.11.4元数据锁定](optimization.html#metadata-locking)[8.11.5外部锁定](optimization.html#external-locking)

- [8.12优化MySQL服务器](optimization.html#optimizing-server)

  [8.12.1系统因素](optimization.html#system-optimization)[8.12.2优化磁盘I / O](optimization.html#disk-issues)[8.12.3使用符号链接](optimization.html#symbolic-links)[8.12.4优化内存使用](optimization.html#optimizing-memory)

- [8.13评估效果（基准测试）](optimization.html#optimize-benchmarking)

  [8.13.1测量表达式和函数的速度](optimization.html#select-benchmarking)[8.13.2使用自己的基准](optimization.html#custom-benchmarks)[8.13.3使用performance_schema衡量绩效](optimization.html#monitoring-performance-schema)

- [8.14检查线程信息](optimization.html#thread-information)

  [8.14.1线程命令值](optimization.html#thread-commands)[8.14.2通用线程状态](optimization.html#general-thread-states)[8.14.3查询缓存线程状态](optimization.html#query-cache-thread-states)[8.14.4复制主线程状态](optimization.html#master-thread-states)[8.14.5复制从属I / O线程状态](optimization.html#slave-io-thread-states)[8.14.6复制从SQL线程状态](optimization.html#slave-sql-thread-states)[8.14.7复制从设备连接线程状态](optimization.html#slave-connection-thread-states)[8.14.8 NDB群集线程状态](optimization.html#mysql-cluster-thread-states)[8.14.9事件调度程序线程状态](optimization.html#event-scheduler-thread-states)



本章说明如何优化MySQL性能并提供示例。优化涉及多个级别的配置，调整和测量性能。根据您的工作角色（开发人员，DBA或两者的组合），您可以在单个SQL语句，整个应用程序，单个数据库服务器或多个联网数据库服务器的级别进行优化。有时，您可以主动并提前计划性能，而有时，您可能会在问题发生后对配置或代码问题进行故障排除。优化CPU和内存使用率还可以提高可伸缩性，从而使数据库能够处理更多负载而不会降低速度。

## 8.1优化概述

数据库性能取决于数据库级别的几个因素，例如表，查询和配置设置。这些软件构造导致在硬件级别执行CPU和I / O操作，您必须将这些操作最小化并使其尽可能高效。在研究数据库性能时，首先要学习软件方面的高级规则和准则，并使用时钟时间来衡量性能。成为专家后，您将了解有关内部情况的更多信息，并开始测量诸如CPU周期和I / O操作之类的东西。

典型的用户旨在从其现有的软件和硬件配置中获得最佳的数据库性能。高级用户寻找机会改进MySQL软件本身，或开发自己的存储引擎和硬件设备以扩展MySQL生态系统。

- [在数据库级别进行优化](optimization.html#optimize-database-level)
- [在硬件级别进行优化](optimization.html#optimize-hardware-level)
- [平衡便携性和性能](optimization.html#optimize-portability-performance)

### 在数据库级别进行优化

使数据库应用程序快速运行的最重要因素是其基本设计：

- 表格的结构是否正确？特别是，这些列是否具有正确的数据类型，并且每个表是否都具有适用于工作类型的适当列？例如，执行频繁更新的应用程序通常具有许多表而具有很少的列，而分析大量数据的应用程序通常具有很少的表而具有很多列。

- 是否安装了正确的 [索引](optimization.html#optimization-indexes)以提高查询效率？

- 您是否为每个表使用了适当的存储引擎，并利用了所使用的每个存储引擎的优势和功能？特别地，对于`InnoDB` 诸如`MyISAM` 性能或可伸缩性之类的事务性存储引擎或诸如非 事务性存储引擎的选择 可能非常重要。

  注意

  `InnoDB`是新表的默认存储引擎。实际上，高级 `InnoDB`性能功能意味着 `InnoDB`表通常要比简单`MyISAM`表好，尤其是对于繁忙的数据库。

- 每个表都使用适当的行格式吗？该选择还取决于表使用的存储引擎。特别是，压缩表使用较少的磁盘空间，因此需要较少的磁盘I / O来读写数据。压缩适用于带有`InnoDB`表的所有工作负载 以及只读 `MyISAM`表。

- 应用程序是否使用适当的 [锁定策略](optimization.html#locking-issues)？例如，通过在可能的情况下允许共享访问，以便数据库操作可以同时运行，并在适当的时候请求独占访问，以使关键操作获得最高优先级。同样，存储引擎的选择很重要。该`InnoDB`存储引擎处理大部分锁定问题，而不需要您的参与，允许在数据库更好的并发，减少试验和调整的金额，让您的代码。

- [用于缓存的](optimization.html#buffering-caching) 所有[内存区域](optimization.html#buffering-caching)大小是否正确？也就是说，足够大以容纳经常访问的数据，但又不能太大以至于它们会使物理内存过载并导致分页。要配置的主要内存区域是`InnoDB`缓冲池，`MyISAM`键高速缓存和MySQL查询高速缓存。

### 在硬件级别进行优化

随着数据库变得越来越繁忙，任何数据库应用程序最终都会达到硬件极限。DBA必须评估是否有可能调整应用程序或重新配置服务器以避免这些 [瓶颈](glossary.html#glos_bottleneck)，或者是否需要更多的硬件资源。系统瓶颈通常来自以下来源：

- 磁盘搜寻。磁盘查找数据需要花费时间。对于现代磁盘，此操作的平均时间通常小于10毫秒，因此理论上我们可以执行约100秒钟的搜索。这段时间随着新磁盘的使用而缓慢改善，并且很难为单个表进行优化。优化寻道时间的方法是将数据分发到多个磁盘上。
- 磁盘读写。当磁盘位于正确的位置时，我们需要读取或写入数据。使用现代磁盘，一个磁盘至少可以提供10–20MB / s的吞吐量。与查找相比，优化起来更容易，因为您可以从多个磁盘并行读取。
- CPU周期。当数据位于主存储器中时，我们必须对其进行处理以获得结果。与内存量相比，拥有较大的表是最常见的限制因素。但是对于小桌子，速度通常不是问题。
- 内存带宽。当CPU需要的数据超出CPU缓存的容量时，主内存带宽将成为瓶颈。对于大多数系统来说，这是一个不常见的瓶颈，但要意识到这一点。

### 平衡便携性和性能



要在便携式MySQL程序中使用面向性能的SQL扩展，可以在`/*! */`注释定界符中的语句中包装特定于MySQL的关键字。其他SQL Server忽略注释的关键字。有关编写注释的信息，请参见[第9.6节“注释语法”](language-structure.html#comments)。

## 8.2优化SQL语句

- [8.2.1优化SELECT语句](optimization.html#select-optimization)
- [8.2.2优化子查询，派生表和视图引用](optimization.html#subquery-optimization)
- [8.2.3优化INFORMATION_SCHEMA查询](optimization.html#information-schema-optimization)
- [8.2.4优化数据更改语句](optimization.html#data-change-optimization)
- [8.2.5优化数据库特权](optimization.html#permission-optimization)
- [8.2.6其他优化技巧](optimization.html#miscellaneous-optimization-tips)



数据库应用程序的核心逻辑是通过SQL语句执行的，无论是通过解释程序直接发出还是通过API在后台提交。本节中的调整准则有助于加快各种MySQL应用程序的速度。该准则涵盖了读写数据的SQL操作，一般SQL操作的幕后开销以及在特定情况下使用的操作，例如数据库监视。

### 8.2.1优化SELECT语句

- [8.2.1.1 WHERE子句优化](optimization.html#where-optimization)
- [8.2.1.2范围优化](optimization.html#range-optimization)
- [8.2.1.3索引合并优化](optimization.html#index-merge-optimization)
- [8.2.1.4发动机状态下推优化](optimization.html#condition-pushdown-optimization)
- [8.2.1.5索引条件下推优化](optimization.html#index-condition-pushdown-optimization)
- [8.2.1.6嵌套循环联接算法](optimization.html#nested-loop-joins)
- [8.2.1.7嵌套联接优化](optimization.html#nested-join-optimization)
- [8.2.1.8外部联接优化](optimization.html#outer-join-optimization)
- [8.2.1.9外部联接简化](optimization.html#outer-join-simplification)
- [8.2.1.10多范围读取优化](optimization.html#mrr-optimization)
- [8.2.1.11块嵌套循环和批处理密钥访问联接](optimization.html#bnl-bka-optimization)
- [8.2.1.12条件过滤](optimization.html#condition-filtering)
- [8.2.1.13 IS NULL优化](optimization.html#is-null-optimization)
- [8.2.1.14优化排序](optimization.html#order-by-optimization)
- [8.2.1.15按优化分组](optimization.html#group-by-optimization)
- [8.2.1.16 DISTINCT优化](optimization.html#distinct-optimization)
- [8.2.1.17 LIMIT查询优化](optimization.html#limit-optimization)
- [8.2.1.18函数调用优化](optimization.html#function-optimization)
- [8.2.1.19行构造器表达式优化](optimization.html#row-constructor-optimization)
- [8.2.1.20避免全表扫描](optimization.html#table-scan-avoidance)



查询以[`SELECT`](sql-statements.html#select) 语句的形式执行数据库中的所有查找操作。无论是实现动态网页的亚秒级响应时间，还是缩短时间以生成大量的夜间报告，调整这些语句都是当务之急。

此外[`SELECT`](sql-statements.html#select)语句，进行查询调谐技术也适用于结构，如 [`CREATE TABLE...AS SELECT`](sql-statements.html#create-table-select)， [`INSERT INTO...SELECT`](sql-statements.html#insert-select)和`WHERE`在条款 [`DELETE`](sql-statements.html#delete)的语句。这些语句还有其他性能方面的考虑，因为它们将写操作与面向读取的查询操作结合在一起。

NDB Cluster支持联接下推优化，从而将符合条件的联接完整地发送到NDB Cluster数据节点，在该节点之间可以将其分布并并行执行。有关此优化的更多信息，请参见 [NDB下推连接的条件](mysql-cluster.html#ndb_join_pushdown-conditions)。

优化查询的主要考虑因素是：

- 为了使慢速`SELECT ... WHERE`查询更快，首先要检查的是是否可以添加 [索引](glossary.html#glos_index)。在`WHERE`子句中使用的列上设置索引，以加快评估，过滤和最终检索结果的速度。为避免浪费磁盘空间，请构建一小组索引，以加快应用程序中使用的许多相关查询的速度。

  对于使用[联接](glossary.html#glos_join)和 [外键之类的](glossary.html#glos_foreign_key)功能引用不同表的查询，索引尤其重要 。您可以使用该[`EXPLAIN`](sql-statements.html#explain)语句来确定用于的索引 [`SELECT`](sql-statements.html#select)。请参见 [第8.3.1节“ MySQL如何使用索引”](optimization.html#mysql-indexes)和 [第8.8.1节“使用EXPLAIN优化查询”](optimization.html#using-explain)。

- 隔离和调整查询中花费过多时间的任何部分，例如函数调用。根据查询的结构方式，可以对结果集中的每一行调用一次函数，甚至可以对表中的每一行调用一次函数，从而极大地提高了效率。

- 最小化 查询中[全表扫描](glossary.html#glos_full_table_scan)的次数 ，特别是对于大表。

- 通过[`ANALYZE TABLE`](sql-statements.html#analyze-table)定期使用该语句来使表统计信息保持最新 ，因此优化器具有构造有效执行计划所需的信息。

- 了解特定于每个表的存储引擎的调整技术，索引技术和配置参数。双方`InnoDB`并 `MyISAM`有两套准则的实现和维持查询高性能。有关详细信息，请参见[第8.5.6节“优化InnoDB查询”](optimization.html#optimizing-innodb-queries)和 [第8.6.1节“优化MyISAM查询”](optimization.html#optimizing-queries-myisam)。

- 您可以`InnoDB`使用[第8.5.3节“优化InnoDB只读事务”中](optimization.html#innodb-performance-ro-txn)的技术[优化](optimization.html#innodb-performance-ro-txn)表的 单查询事务 。

- 避免以难以理解的方式转换查询，尤其是在优化程序自动执行某些相同转换的情况下。

- 如果使用基本准则之一不能轻松解决性能问题，请通过阅读[`EXPLAIN`](sql-statements.html#explain)计划并调整索引，`WHERE`子句，连接子句等来调查特定查询的内部详细信息 。（当您达到一定的专业水平时，阅读 [`EXPLAIN`](sql-statements.html#explain)计划可能是每个查询的第一步。）

- 调整MySQL用于缓存的内存区域的大小和属性。通过有效地使用 `InnoDB` [缓冲池](glossary.html#glos_buffer_pool)， `MyISAM`键高速缓存和MySQL查询高速缓存，重复查询的运行速度更快，因为第二次及以后都从内存中检索了结果。

- 即使对于使用缓存区域快速运行的查询，您仍可能会进一步优化，以使它们需要更少的缓存，从而使您的应用程序更具可伸缩性。可伸缩性意味着您的应用程序可以处理更多的并发用户，更大的请求等，而不会导致性能大幅下降。

- 处理锁定问题，其中其他会话同时访问表可能会影响查询速度。

#### 8.2.1.1 WHERE子句优化



本节讨论可以对处理`WHERE`子句进行的优化。这些示例使用 [`SELECT`](sql-statements.html#select)语句，但是相同的优化适用`WHERE`于[`DELETE`](sql-statements.html#delete)和 [`UPDATE`](sql-statements.html#update)语句中的子句 。

注意

由于正在进行MySQL优化程序的工作，因此此处未记录MySQL执行的所有优化。

您可能会想重写查询以使算术运算更快，同时又牺牲了可读性。由于MySQL自动进行类似的优化，因此您通常可以避免这项工作，而将查询保留为更易于理解和维护的形式。MySQL执行的一些优化如下：

- 删除不必要的括号：

  ```
     （（（a AND b）AND c OR（（（a AND b）AND（c AND d）））））
  ->（a AND b AND c）或（a AND b AND c AND d）
  ```

- 恒定折叠：

  ```
     （a <b AND b = c）AND a = 5
  -> b> 5 AND b = c AND a = 5
  ```

- 恒定条件消除：

  ```
     （b> = 5 AND b = 5）或（b = 6 AND 5 = 5）或（b = 7 AND 5 = 6）
  -> b = 5或b = 6
  ```

- 索引使用的常量表达式仅计算一次。

- [`COUNT(*)`](functions.html#function_count)上没有一个单一的表`WHERE`是从该表信息直接检索`MyISAM` 和`MEMORY`表。`NOT NULL`当仅与一个表一起使用时，对于任何表达式也可以执行此操作。

- 早期检测无效的常量表达式。MySQL快速检测到某些 [`SELECT`](sql-statements.html#select)语句是不可能的，并且不返回任何行。

- `HAVING``WHERE`如果您不使用`GROUP BY`或汇总功能（[`COUNT()`](functions.html#function_count)， [`MIN()`](functions.html#function_min)等），则与合并 。

- 对于联接中的每个表，`WHERE`构造一个更简单 `WHERE`的表以获得表的快速 评估，并尽快跳过行。

- 在查询中的任何其他表之前，首先读取所有常量表。常量表可以是以下任意一个：

  - 空表或具有一行的表。
  - 与a 或 索引`WHERE` 上的子句一起使用的表，其中所有索引部分都与常量表达式进行比较，并定义为。 `PRIMARY KEY``UNIQUE``NOT NULL`

  以下所有表均用作常量表：

  ```
  SELECT * FROM t WHERE primary_key= 1;
  选择*从t1，t2
    在t1。primary_key= 1 AND t2。primary_key= t1.id;
  ```

- 通过尝试所有可能的方法，找到用于联接表的最佳联接组合。如果`ORDER BY`and `GROUP BY`子句中的所有列 都来自同一表，则在连接时优先使用该表。

- 如果有一个`ORDER BY`子句和另一个`GROUP BY`子句，或者如果 `ORDER BY`或`GROUP BY` 包含联接队列中第一个表以外的表中的列，则会创建一个临时表。

- 如果使用`SQL_SMALL_RESULT` 修饰符，MySQL将使用内存中的临时表。

- 查询每个表索引，并使用最佳索引，除非优化程序认为使用表扫描更有效。一次使用扫描是基于最佳索引是否跨越了表的30％以上，但是固定百分比不再决定使用索引还是扫描。现在，优化器更加复杂，其估计基于其他因素，例如表大小，行数和I / O块大小。

- 在某些情况下，MySQL甚至可以在不查询数据文件的情况下从索引中读取行。如果索引中使用的所有列都是数字，则仅索引树用于解析查询。

- 在输出每一行之前，`HAVING`将跳过不匹配该子句的那些行 。

快速查询的一些示例：

```
选择COUNT（*）FROM tbl_name;

SELECT MIN（key_part1），MAX（key_part1）FROM tbl_name;

SELECT MAX（key_part2）FROM tbl_name
  WHERE key_part1= constant;

SELECT ... FROM tbl_name
  ORDER BY key_part1，key_part2... LIMIT 10;

SELECT ... FROM tbl_name
  DEDER BY key_part1DESC，key_part2DESC，... LIMIT 10;
```

假设索引列是数字，MySQL仅使用索引树来解析以下查询：

```
SELECT key_part1，key_part2FROM tbl_nameWHERE key_part1= val;

从tbl_name
  WHERE key_part1= val1AND key_part2= 选择COUNT（*）val2;
key_part2从tbl_nameGROUP BY中
选择key_part1;
```

以下查询使用索引来按排序顺序检索行，而无需单独的排序遍历：

```
SELECT ... FROM tbl_name
  ORDER BY key_part1，key_part2...;

SELECT ... FROM tbl_name
  DEDER BY key_part1DESC，key_part2DESC，...;
```

#### 8.2.1.2范围优化

的[`range`](optimization.html#jointype_range)访问方法使用单个索引来检索包含一个或若干个索引值的时间间隔内表行的子集。它可以用于单部分或多部分索引。以下各节描述了优化器使用范围访问的条件。

- [单部分索引的范围访问方法](optimization.html#range-access-single-part)
- [多部分索引的范围访问方法](optimization.html#range-access-multi-part)
- [多值比较的等距范围优化](optimization.html#equality-range-optimization)
- [行构造函数表达式的范围优化](optimization.html#row-constructor-range-optimization)
- [限制内存使用以进行范围优化](optimization.html#range-optimization-memory-use)

##### 单部分索引的范围访问方法

对于单部分索引，索引值间隔可以方便地由条款中的相应条件 `WHERE`表示，称为 范围条件， 而不是“ 间隔”。”

单部分索引的范围条件的定义如下：

- 对于这两种`BTREE`和 `HASH`索引，使用时具有恒定值的关键部分的比较是一个范围条件 [`=`](functions.html#operator_equal)， [`<=>`](functions.html#operator_equal-to)， [`IN()`](functions.html#operator_in)，[`IS NULL`](functions.html#operator_is-null)，或[`IS NOT NULL`](functions.html#operator_is-not-null)运营商。
- 另外，对于`BTREE`索引，当使用具有恒定值的关键部分的比较是一个范围条件 [`>`](functions.html#operator_greater-than)， [`<`](functions.html#operator_less-than)， [`>=`](functions.html#operator_greater-than-or-equal)， [`<=`](functions.html#operator_less-than-or-equal)， [`BETWEEN`](functions.html#operator_between)， [`!=`](functions.html#operator_not-equal)，或 [`<>`](functions.html#operator_not-equal) 运营商，或者[`LIKE`](functions.html#operator_like) 比较，如果参数 [`LIKE`](functions.html#operator_like)是一个常数字符串不与通配符开始。
- 对于所有索引类型，多个范围条件组合[`OR`](functions.html#operator_or)或 [`AND`](functions.html#operator_and)形成一个范围条件。

前面的描述中的“ 恒定值 ”表示以下之一：

- 查询字符串中的常量
- 来自同一联接 的[`const`](optimization.html#jointype_const) 或[`system`](optimization.html#jointype_system)表的 列
- 不相关子查询的结果
- 任何完全由上述类型的子表达式组成的表达式

以下是该`WHERE`子句中具有范围条件的查询示例：

```
选择*从t1
  在哪里key_col> 1
  AND key_col<10;

选择*从t1
  在哪里key_col= 1
  或key_col（15,18,20）;

选择*从t1
  WHERE key_colLIKE 'AB％'
  或在key_col“ bar”和“ foo”之间；
```

在优化程序常数传播阶段，某些非常数值可以转换为常数。

MySQL尝试从`WHERE`子句中为每个可能的索引提取范围条件 。在提取过程中，删除了不能用于构建范围条件的条件，合并了产生重叠范围的条件，并删除了产生空范围的条件。

考虑下面的语句，其中 `key1`是索引列， `nonkey`而没有索引：

```
选择*从t1在哪里
  （key1 <'abc'AND（key1 LIKE'abcde％'或key1 LIKE'％b'））或
  （key1 <'bar'AND nonkey = 4）或
  （key1 <'uux'AND key1>'z'）;
```

密钥的提取过程`key1`如下：

1. 从原始`WHERE`子句开始：

   ```
   （key1 <'abc'AND（key1 LIKE'abcde％'或key1 LIKE'％b'））或
   （key1 <'bar'AND nonkey = 4）或
   （key1 <'uux'AND key1>'z'）
   ```

2. 删除`nonkey = 4`，`key1 LIKE '%b'`因为它们不能用于范围扫描。删除它们的正确方法是将它们替换为`TRUE`，这样在进行范围扫描时我们不会丢失任何匹配的行。用`TRUE`产量代替它们：

   ```
   （key1 <'abc'AND（key1 Like'abcde％'或TRUE））或
   （key1 <'bar'AND TRUE）或
   （key1 <'uux'AND key1>'z'）
   ```

3. 崩溃条件始终为true或false：

   - `(key1 LIKE 'abcde%' OR TRUE)` 永远是真的
   - `(key1 < 'uux' AND key1 > 'z')` 永远是假的

   用常量替换这些条件将产生：

   ```
   （key1 <'abc'AND TRUE）或（key1 <'bar'AND TRUE）或（FALSE）
   ```

   去除不必要的`TRUE`和 `FALSE`常数的产率：

   ```
   （key1 <'abc'）或（key1 <'bar'）
   ```

4. 将重叠的间隔合并为一个会产生用于范围扫描的最终条件：

   ```
   （key1 <'bar'）
   ```

一般而言（如前面的示例所示），范围扫描所使用的条件比该`WHERE`子句的限制要少。MySQL执行附加检查以过滤出满足范围条件但不包括full `WHERE`子句的行。

范围条件提取算法可以处理 任意深度的嵌套 [`AND`](functions.html#operator_and)/ [`OR`](functions.html#operator_or)构造，并且其输出不取决于条件在`WHERE`子句中出现的顺序 。

MySQL不支持[`range`](optimization.html#jointype_range)为空间索引的访问方法合并多个范围 。要解决此限制，可以将a [`UNION`](sql-statements.html#union)与相同的[`SELECT`](sql-statements.html#select)语句一起 使用，只是将每个空间谓词放在不同的中 [`SELECT`](sql-statements.html#select)。

##### 多部分索引的范围访问方法

多部分索引的范围条件是单部分索引的范围条件的扩展。多部分索引上的范围条件将索引行限制在一个或几个键元组间隔内。使用从索引开始的顺序，在一组键元组上定义键元组间隔。

例如，考虑定义为的多部分索引 ，并按键顺序列出以下一组键元组： `key1(*`key_part1`*, *`key_part2`*, *`key_part3`*)`

```
key_part1  key_part2  key_part3
  NULL 1'abc'
  NULL 1'xyz'
  NULL 2'foo'
   1 1'abc'
   1 1'xyz'
   1 2'abc'
   2 1'aaa'
```

条件`*`key_part1`* = 1`定义了此间隔：

```
（1，-INF，-INF）<=（ ，，key_part1 ）<（1，+ INF，+ INF）
key_part2key_part3
```

该间隔覆盖了先前数据集中的第4，第5和第6个元组，并且可以由范围访问方法使用。

相反，该条件 `*`key_part3`* = 'abc'`未定义单个间隔，并且不能被范围访问方法使用。

以下描述更详细地说明了范围条件如何作用于多部分索引。

- 对于`HASH`索引，可以使用包含相同值的每个间隔。这意味着只能针对以下形式的条件生成间隔：

  ```
      key_part1 cmp const1
  和 key_part2 cmp const2
  与...
  AND ;
  key_partN cmp constN
  ```

  这里*`const1`*， *`const2`*...是常数，*`cmp`*是一个 [`=`](functions.html#operator_equal)， [`<=>`](functions.html#operator_equal-to)或者[`IS NULL`](functions.html#operator_is-null)比较运营商，以及条件覆盖所有指数部分。（也就是说，*`N`* 有条件，*`N`*-part索引的每个部分都有一个 条件。）例如，以下是三部分`HASH`索引的范围条件 ：

  ```
  key_part1= 1 AND key_part2IS NULL AND key_part3='foo'
  ```

  有关定义为常量的定义，请参见 [单部分索引的范围访问方法](optimization.html#range-access-single-part)。

- 对于一个`BTREE`索引，以一定间隔可能是可用于条件组合 [`AND`](functions.html#operator_and)，其中每个状态具有恒定值使用一个关键部分进行比较 [`=`](functions.html#operator_equal)， [`<=>`](functions.html#operator_equal-to)， [`IS NULL`](functions.html#operator_is-null)， [`>`](functions.html#operator_greater-than)， [`<`](functions.html#operator_less-than)， [`>=`](functions.html#operator_greater-than-or-equal)， [`<=`](functions.html#operator_less-than-or-equal)， [`!=`](functions.html#operator_not-equal)， [`<>`](functions.html#operator_not-equal)， [`BETWEEN`](functions.html#operator_between)，或 （其中 [`LIKE '*`pattern`*'`](functions.html#operator_like)`'*`pattern`*'` 不以通配符开头）。只要可以确定包含所有与条件匹配的行的单个键元组，就可以使用一个间隔（如果使用[`<>`](functions.html#operator_not-equal) 或，[`!=`](functions.html#operator_not-equal) 则使用两个间隔 ）。

  只要比较运算符为，或[`=`](functions.html#operator_equal)， 优化器就会尝试使用其他关键部分来确定间隔 。如果操作是 ， ， ， ， ， ， ，或者 ，优化器使用它，但认为没有更多的关键部分。对于以下表达式，优化器使用 第一个比较中的值。它也使用 [`<=>`](functions.html#operator_equal-to)[`IS NULL`](functions.html#operator_is-null)[`>`](functions.html#operator_greater-than)[`<`](functions.html#operator_less-than)[`>=`](functions.html#operator_greater-than-or-equal)[`<=`](functions.html#operator_less-than-or-equal)[`!=`](functions.html#operator_not-equal)[`<>`](functions.html#operator_not-equal)[`BETWEEN`](functions.html#operator_between)[`LIKE`](functions.html#operator_like)[`=`](functions.html#operator_equal)[`>=`](functions.html#operator_greater-than-or-equal) 根据第二次比较，但不考虑其他关键部分，并且不将第三次比较用于区间构造：

  ```
  key_part1='foo'AND key_part2> = 10 AND key_part3> 10
  ```

  单个间隔为：

  ```
  （ '富'，10，-INF）<（ ，，key_part1 ）<（ '富'，+ INF，+ INF）
  key_part2key_part3
  ```

  创建的间隔可能包含比初始条件更多的行。例如，前面的时间间隔包含`('foo', 11, 0)`不满足原始条件的值。

- 如果将覆盖间隔中包含的行集合的条件与组合[`OR`](functions.html#operator_or)，则它们将形成覆盖间隔中的并 集中包含的行集合的条件。如果条件与组合 [`AND`](functions.html#operator_and)，则它们将形成一个条件，该条件覆盖其间隔的交点内包含的一组行。例如，对于由两部分组成的索引的这种情况：

  ```
  （key_part1= 1 AND key_part2<2）或（key_part1> 5）
  ```

  间隔为：

  ```
  （1，-inf）<（key_part1，key_part2）<（1,2）
  （5，-inf）<（key_part1，key_part2）
  ```

  在此示例中，第一行的间隔使用一个关键部分作为左边界，使用两个关键部分作为右边界。第二行的间隔仅使用一个关键部分。输出中的`key_len`列[`EXPLAIN`](sql-statements.html#explain)表示所使用的密钥前缀的最大长度。

  在某些情况下，`key_len`可能表明已使用了关键部件，但这可能不是您期望的。假设 *`key_part1`*和 *`key_part2`*可以是 `NULL`。然后，该 `key_len`列显示以下条件的两个关键零件长度：

  ```
  key_part1> = 1 AND key_part2<2
  ```

  但是，实际上，条件已转换为：

  ```
  key_part1> = 1并且key_part2不为空
  ```

有关如何执行优化以合并或消除单部分索引上范围条件的间隔的描述，请参见单部分索引的 [范围访问方法](optimization.html#range-access-single-part)。对多部分索引的范围条件执行类似的步骤。

##### 多值比较的等距范围优化

考虑以下表达式，其中 *`col_name`*是索引列：

```
col_nameIN（val1，...，valN）
 col_name= val1OR ... OR col_name=valN
```

如果*`col_name`*等于多个值中的任何一个，则每个表达式为true 。这些比较是相等范围比较（其中“ range ”是单个值）。优化器估算相等范围比较的读取合格行的成本，如下所示：

- 如果在上有唯一索引 *`col_name`*，则每个范围的行估计为1，因为最多一行可以具有给定值。
- 否则，任何索引 *`col_name`*都不是唯一的，优化器可以使用对索引或索引统计的深入估算来估计每个范围的行数。



使用索引潜水时，优化器在范围的每个末端进行潜水，并将范围中的行数用作估计值。例如，表达式 `*`col_name`* IN (10, 20, 30)`具有三个相等范围，并且优化器对每个范围进行两次下潜以生成行估计。每对潜水都会得出具有给定值的行数的估计值。

索引潜水可提供准确的行估计，但是随着表达式中比较值数量的增加，优化器将花费更长的时间来生成行估计。使用索引统计数据的准确性不及使用索引潜水的准确性，但允许对大型值列表进行更快的行估计。

使用 [`eq_range_index_dive_limit`](server-administration.html#sysvar_eq_range_index_dive_limit) 系统变量，您可以配置优化程序从一种行估计策略切换到另一种行估计策略的值的数量。要允许使用索引潜水进行最多*`N`* 等于范围的比较，请设置 [`eq_range_index_dive_limit`](server-administration.html#sysvar_eq_range_index_dive_limit) 为*`N`*+1。要禁用统计信息，并且始终使用索引潜水而不管 *`N`*，将其设置 [`eq_range_index_dive_limit`](server-administration.html#sysvar_eq_range_index_dive_limit) 为0。

要更新表索引统计信息以获得最佳估计值，请使用 [`ANALYZE TABLE`](sql-statements.html#analyze-table)。

即使在本应使用索引潜水的条件下，对于满足所有这些条件的查询也将跳过它们：

- 存在单索引`FORCE INDEX`索引提示。这样的想法是，如果强制使用索引，那么执行潜入索引的额外开销将无济于事。
- 索引不是唯一索引，不是 `FULLTEXT`索引。
- 没有子查询。
- 没有`DISTINCT`，`GROUP BY`或`ORDER BY`子句存在。

这些跳水条件仅适用于单表查询。对于多表查询（联接），不会跳过索引潜水。

##### 行构造函数表达式的范围优化

优化器可以将范围扫描访问方法应用于以下形式的查询：

```
从t1位置选择...（col_1，col_2）IN（（（'a'，'b'），（'c'，'d'））;
```

以前，要使用范围扫描，必须将查询编写为：

```
SELECT ... FROM t1 WHERE（col_1 ='a'AND col_2 ='b'）
或（col_1 ='c'AND col_2 ='d'）;
```

为了使优化器使用范围扫描，查询必须满足以下条件：

- 仅使用[`IN()`](functions.html#operator_in)谓词，不使用[`NOT IN()`](functions.html#operator_not-in)。
- 在[`IN()`](functions.html#operator_in)谓词的左侧 ，行构造器仅包含列引用。
- 在[`IN()`](functions.html#operator_in)谓词的右侧 ，行构造器仅包含运行时常量，这些常量是在执行期间绑定到常量的文字或本地列引用。
- 在[`IN()`](functions.html#operator_in)谓词的右侧 ，有多个行构造函数。

有关优化器和行构造器的更多信息，请参见 [第8.2.1.19节“行构造器表达式优化”。](optimization.html#row-constructor-optimization)

##### 限制内存使用以进行范围优化

要控制范围优化器可用的内存，请使用 [`range_optimizer_max_mem_size`](server-administration.html#sysvar_range_optimizer_max_mem_size) 系统变量：

- 值0表示“ 无限制”。”

- 值大于0时，优化器将在考虑范围访问方法时跟踪消耗的内存。如果将要超过指定的限制，则将放弃范围访问方法，而应考虑其他方法，包括全表扫描。这可能不是最佳选择。如果发生这种情况，则会发生以下警告（ *`N`*当前 [`range_optimizer_max_mem_size`](server-administration.html#sysvar_range_optimizer_max_mem_size) 值为）：

  ```
  警告3170的N字节存储容量
                     超出了“ range_optimizer_max_mem_size”。范围
                     此查询未完成优化。
  ```

- 对于[`UPDATE`](sql-statements.html#update)和 [`DELETE`](sql-statements.html#delete)语句，如果优化器退回到全表扫描并且[`sql_safe_updates`](server-administration.html#sysvar_sql_safe_updates)启用了 系统变量，则会发生错误而不是警告，因为实际上，没有键用于确定要修改的行。有关更多信息，请参见 [使用安全更新模式（--safe-updates）](programs.html#safe-updates)。

对于超出可用范围优化内存的单个查询，并且对于该查询，优化器将退回至次优计划，增加 [`range_optimizer_max_mem_size`](server-administration.html#sysvar_range_optimizer_max_mem_size) 值可以提高性能。

若要估计处理范围表达式所需的内存量，请使用以下准则：

- 对于诸如以下的简单查询，其中有一个用于范围访问方法的候选关键字，与组合[`OR`](functions.html#operator_or) 使用的每个谓词大约使用230个字节：

  ```
  从t选择COUNT（*）
  其中a = 1或a = 2或a = 3或..。a = N;
  ```

- 类似地，对于以下查询，每个谓词组合[`AND`](functions.html#operator_and) 使用大约125个字节：

  ```
  从t选择COUNT（*）
  其中a = 1 AND b = 1 AND c = 1 ... N;
  ```

- 对于带有[`IN()`](functions.html#operator_in) 谓词的查询：

  ```
  从t选择COUNT（*）
  a IN（1,2，...，M）和b IN（1,2，...，N）;
  ```

  [`IN()`](functions.html#operator_in)列表 中的每个文字值都 算作与组合的谓词[`OR`](functions.html#operator_or)。如果有两个[`IN()`](functions.html#operator_in) 列表，则与组合的谓词 [`OR`](functions.html#operator_or)数量是每个列表中文字值数量的乘积。因此，[`OR`](functions.html#operator_or)在前一种情况下组合的谓词数 为 *`M`*× *`N`*。

在5.7.11之前，与每个谓词组合的字节数[`OR`](functions.html#operator_or)更高，大约为700字节。

#### 8.2.1.3索引合并优化



该指数合并与多址接入方式检索行 [`range`](optimization.html#jointype_range)扫描并合并他们的结果为一体。此访问方法仅合并来自单个表的索引扫描，而不合并多个表的扫描。合并可以产生其基础扫描的并集，相交或相交。

可能使用索引合并的示例查询：

```
SELECT * FROM tbl_nameWHERE key1= 10 OR key2= 20;

SELECT * FROM tbl_name
  WHERE（key1= 10 OR key2= 20）AND non_key= 30;

选择*从t1，t2
  在哪里（t1。IN key1（1,2）或t1。key2喜欢' value％'）
  和t2。key1= t1。some_col;

选择*从t1，t2
  在t1。key1= 1
  AND（t2。key1= t1。OR some_colt2。key2= t1。some_col2）；
```

注意

索引合并优化算法具有以下已知限制：

- 如果您的查询包含`WHERE` 带有深度[`AND`](functions.html#operator_and)/ [`OR`](functions.html#operator_or) 嵌套的复杂子句， 而MySQL没有选择最佳计划，请尝试使用以下身份转换来分发术语：

  ```
  （xAND y）OR z=>（xOR z）AND（yOR z）
  （xOR y）AND z=>（xAND z）OR（yAND z）
  ```

- 索引合并不适用于全文索引。

在[`EXPLAIN`](sql-statements.html#explain)输出中，索引合并方法出现 [`index_merge`](optimization.html#jointype_index_merge)在 `type`列中。在这种情况下，该 `key`列包含使用的索引列表，并`key_len`包含这些索引的最长键部分的列表。

索引合并访问方法具有几种算法，这些算法显示在输出`Extra`字段中 [`EXPLAIN`](sql-statements.html#explain)：

- `Using intersect(...)`
- `Using union(...)`
- `Using sort_union(...)`

以下各节将更详细地描述这些算法。优化程序根据各种可用选项的成本估算，在可能的索引合并算法和其他访问方法之间进行选择。

索引合并的使用是受价值 `index_merge`， `index_merge_intersection`， `index_merge_union`，和 `index_merge_sort_union`该旗 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)系统变量。请参见[第8.9.2节“可切换的优化”](optimization.html#switchable-optimizations)。默认情况下，所有这些标志均为`on`。要仅启用某些算法，请将设置`index_merge`为 `off`，并仅启用应允许的其他算法。

- [索引合并路口访问算法](optimization.html#index-merge-intersection)
- [索引合并联合访问算法](optimization.html#index-merge-union)
- [索引合并排序联合访问算法](optimization.html#index-merge-sort-union)

##### 索引合并路口访问算法

当`WHERE`子句在与键组合在一起的不同键上转换为多个范围条件 [`AND`](functions.html#operator_and)，并且每个条件是以下条件之一时，此访问算法适用 ：

- *`N`*此形式 的-part表达式，其中索引具有完全相同的 *`N`*部分（即，所有索引部分均被覆盖）：

  ```
  key_part1= const1AND key_part2= const2... AND key_partN=constN
  ```

- `InnoDB`表 主键上的任何范围条件 。

例子：

```
选择* FROM innodb_table
  WHERE primary_key<10 AND key_col1= 20;

SELECT * FROM tbl_name
  WHERE key1_part1= 1 AND key1_part2= 2 AND key2= 2;
```

索引合并交集算法对所有使用的索引执行同时扫描，并生成从合并索引扫描中接收到的行序列的交集。

如果查询中使用的所有列都被使用的索引覆盖，则不会检索完整的表行（ 在这种情况下，[`EXPLAIN`](sql-statements.html#explain)输出包含 `Using index`在`Extra`字段中）。这是此类查询的示例：

```
从t1选择COUNT（*），其中key1 = 1 AND key2 = 1;
```

如果使用的索引未覆盖查询中使用的所有列，则仅在满足所有使用的键的范围条件时才检索完整行。

如果合并的条件之一是`InnoDB`表主键上的条件，则该条件不用于行检索，而是用于过滤出使用其他条件检索的行。

##### 索引合并联合访问算法

此算法的标准类似于索引合并交集算法的标准。当表的`WHERE` 子句在与键组合在一起的不同键上转换为多个范围条件[`OR`](functions.html#operator_or)，并且每个条件为以下条件之一时，该算法适用：

- *`N`*此形式 的-part表达式，其中索引具有完全相同的 *`N`*部分（即，所有索引部分均被覆盖）：

  ```
  key_part1= const1AND key_part2= const2... AND key_partN=constN
  ```

- `InnoDB`表 主键上的任何范围条件 。

- 索引合并交集算法适用的条件。

例子：

```
选择*从t1
  WHERE key1= 1 OR key2= 2 OR key3= 3;

SELECT * FROM innodb_table
  WHERE（key1= 1 AND key2= 2）
     或（key3='foo'AND key4='bar'）AND key5= 5;
```

##### 索引合并排序联合访问算法

当将`WHERE`子句转换为由组合的多个范围条件 时，此访问算法适用 [`OR`](functions.html#operator_or)，但索引合并并集算法不适用。

例子：

```
SELECT * FROM tbl_name
  WHERE key_col1<10 OR key_col2<20;

SELECT * FROM tbl_name
  WHERE（key_col1> 10 OR key_col2= 20）AND nonkey_col= 30;
```

sort-union算法和union算法之间的区别在于，sort-union算法必须首先获取所有行的行ID，然后对它们进行排序，然后再返回任何行。

#### 8.2.1.4发动机状态下推优化



这种优化提高了非索引列和常量之间直接比较的效率。在这种情况下，条件被“ 下推 ”到存储引擎进行评估。此优化只能由[`NDB`](mysql-cluster.html)存储引擎使用。

对于NDB群集，此优化可以消除在群集的数据节点和发出查询的MySQL服务器之间通过网络发送不匹配的行的需求，并且可以将查询的使用速度提高5到10倍（在某些情况下）可以但不使用条件下推的地方。

假设NDB群集表定义如下：

```
创建表t1（
    一个INT，
    b INT，
    密钥（a）
）ENGINE = NDB;
```

条件下推可用于查询，例如此处显示的查询，其中包括未索引列和常量之间的比较：

```
从t1处选择a，b，b = 10;
```

条件下推的使用可以在以下输出中看到 [`EXPLAIN`](sql-statements.html#explain)：

```
mysql> EXPLAIN SELECT a,b FROM t1 WHERE b = 10\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表：t1
         类型：全部
可能的键：NULL
          键：NULL
      key_len：NULL
          参考：NULL
         行数：10
        附加：在有推动条件的地方使用
```

但是，条件下推*不能*与以下两个查询之一一起使用：

```
从t1选择a，b，其中a = 10;
从t1处选择a，b，其中b + 1 = 10;
```

条件下推不适用于第一个查询，因为column上存在索引`a`。（索引访问方法将更有效，因此将优先选择条件下推。）条件下推不能用于第二个查询，因为涉及非索引列的比较 `b`是间接的。（但是，如果`b + 1 = 10`要`b = 9`在 `WHERE`条款中减少到条件，则可以应用条件下推。）

使用`>`或 `<`运算符将索引列与常量进行比较时，也可以使用条件下推：

```
mysql> EXPLAIN SELECT a, b FROM t1 WHERE a < 2\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表：t1
         类型：范围
Possible_Keys：一个
          关键：一个
      key_len：5
          参考：NULL
         行数：2
        附加：在有推动条件的地方使用
```

条件下推的其他受支持比较包括：

- `*`column`* [NOT] LIKE *`pattern`*`

  *`pattern`*必须是包含要匹配模式的字符串文字；有关语法，请参见[第12.7.1节“字符串比较函数和运算符”](functions.html#string-comparison-functions)。

- `*`column`* IS [NOT] NULL`

- `*`column`* IN (*`value_list`*)`

  中的每个项目都*`value_list`* 必须是恒定的文字值。

- `*`column`* BETWEEN *`constant1`* AND *`constant2`*`

  *`constant1`*并且 *`constant2`*每个值都必须是恒定的文字值。

在上述列表中的所有情况下，都有可能将条件转换为列与常量之间的一个或多个直接比较的形式。

默认情况下，引擎状态下推处于启用状态。要在服务器启动时禁用它，请设置 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)系统变量。例如，在`my.cnf`文件中，使用以下几行：

```
[mysqld]
optimizer_switch =引擎条件_推送=关闭
```

在运行时，禁用条件下推，如下所示：

```
SET Optimizer_switch ='engine_condition_pushdown = off';
```

**局限性。** 引擎状态下推受以下限制：

- 条件下推仅受[`NDB`](mysql-cluster.html)存储引擎支持 。
- 列只能与常量进行比较；但是，这包括计算结果为常数的表达式。
- 比较中使用的列不能是[`BLOB`](data-types.html#blob)或 [`TEXT`](data-types.html#blob)类型的任何列 。此排除范围也扩展到[`JSON`](data-types.html#json)， [`BIT`](data-types.html#bit-type)和 [`ENUM`](data-types.html#enum)列。
- 要与列进行比较的字符串值必须使用与列相同的排序规则。
- 不直接支持联接；涉及多个表的条件将在可能的情况下分别推送。使用扩展[`EXPLAIN`](sql-statements.html#explain)输出来确定实际按下了哪些条件。请参见 [第8.8.3节“扩展的EXPLAIN输出格式”](optimization.html#explain-extended)。

#### 8.2.1.5索引条件下推优化

索引条件下推（ICP）是MySQL使用索引从表中检索行的情况的优化。如果没有ICP，则存储引擎将遍历索引以在基表中定位行，并将其返回给MySQL服务器，该MySQL服务器将评估`WHERE`行的条件。启用ICP后，如果`WHERE`可以仅使用索引中的列来评估部分 条件，则MySQL服务器会将这部分条件压入`WHERE`条件下降到存储引擎。然后，存储引擎通过使用索引条目来评估推送的索引条件，并且只有在满足此条件的情况下，才从表中读取行。ICP可以减少存储引擎必须访问基表的次数以及MySQL服务器必须访问存储引擎的次数。

索引条件下推优化的适用性取决于以下条件：

- ICP用于 [`range`](optimization.html#jointype_range)， [`ref`](optimization.html#jointype_ref)， [`eq_ref`](optimization.html#jointype_eq_ref)，和 [`ref_or_null`](optimization.html#jointype_ref_or_null)访问方法时，有一个需要访问的全部表行。
- ICP可用于[`InnoDB`](innodb-storage-engine.html) 和[`MyISAM`](storage-engines.html#myisam-storage-engine)表，包括分区表`InnoDB`和 `MyISAM`表。
- 对于`InnoDB`表，ICP仅用于二级索引。ICP的目标是减少全行读取的次数，从而减少I / O操作。对于 `InnoDB`聚集索引，完整的记录已被读入`InnoDB` 缓冲区。在这种情况下使用ICP不会减少I / O。
- 在虚拟生成的列上创建的二级索引不支持ICP。`InnoDB` 支持虚拟生成的列上的二级索引。
- 不能将引用子查询的条件下推。
- 涉及存储功能的条件不能下推。存储引擎无法调用存储的功能。
- 触发条件不能下推。（有关触发条件的信息，请参见 [第8.2.2.3节“使用EXISTS策略优化子查询”）](optimization.html#subquery-optimization-with-exists)。

要了解此优化的工作原理，请首先考虑在不使用“索引条件下推”的情况下如何进行索引扫描：

1. 获取下一行，首先读取索引元组，然后使用索引元组查找并读取整个表行。
2. 测试`WHERE`适用于此表的部分条件。根据测试结果接受或拒绝该行。

使用“索引条件下推”，扫描将像这样进行：

1. 获取下一行的索引元组（而不是整个表行）。
2. 测试`WHERE`适用于此表的部分条件，并且只能使用索引列进行检查。如果不满足条件，请转到下一行的索引元组。
3. 如果满足条件，请使用索引元组来定位和读取整个表行。
4. 测试`WHERE` 适用于此表的条件的其余部分。根据测试结果接受或拒绝该行。

[`EXPLAIN`](sql-statements.html#explain)当使用“索引条件下推”时，输出将显示 `Using index condition`在 `Extra`列中。它不会显示，`Using index` 因为在必须读取整个表行时，该方法不适用。

假设一个表包含有关人员及其地址的信息，并且该表的索引定义为 `INDEX (zipcode, lastname, firstname)`。如果我们知道一个人的`zipcode`价值，但不确定姓氏，可以这样搜索：

```
选择*来自人
  邮政编码='95054'
  AND姓氏，例如'％etrunia％'
  AND地址，如“％Main Street％”；
```

MySQL可以使用索引扫描具有的人员 `zipcode='95054'`。第二部分（`lastname LIKE '%etrunia%'`）不能用于限制必须扫描的行数，因此，如果没有“索引条件下推”，此查询必须为所有具有的人员检索完整的表行 `zipcode='95054'`。

通过“索引条件下推”，MySQL `lastname LIKE '%etrunia%'`在读取整个表行之前会检查该 部分。这样可以避免读取与`zipcode`条件而不是 `lastname`条件匹配的索引元组对应的完整行 。

默认情况下，索引条件下推处于启用状态。可以[`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)通过设置`index_condition_pushdown`标志使用系统变量 进行控制 ：

```
SET Optimizer_switch ='index_condition_pushdown = off';
SET Optimizer_switch ='index_condition_pushdown = on';
```

请参见[第8.9.2节“可切换的优化”](optimization.html#switchable-optimizations)。

#### 8.2.1.6嵌套循环联接算法



MySQL使用嵌套循环算法或其上的变体在表之间执行联接。

- [嵌套循环联接算法](optimization.html#nested-loop-join-algorithm)
- [块嵌套循环加入算法](optimization.html#block-nested-loop-join-algorithm)

##### 嵌套循环联接算法

一个简单的嵌套循环联接（NLJ）算法一次从一个循环中的第一个表中读取行，然后将每一行传递给一个嵌套循环，该循环处理联接中的下一个表。重复此过程的次数与要连接的表的次数相同。

假设将使用以下联接类型执行三个表`t1`，`t2`和 之间的 `t3`联接：

```
表联接类型
t1范围
t2参考
t3全部
```

如果使用简单的NLJ算法，则按以下方式处理联接：

```
对于t1匹配范围内的每一行{
  对于t2中匹配参考关键字{
    对于t3中的每一行{
      如果行满足加入条件，则发送给客户端
    }
  }
}
```

因为NLJ算法一次将行从外循环传递到内循环，所以它通常读取多次在内循环中处理的表。

##### 块嵌套循环加入算法

块嵌套循环（BNL）嵌套算法使用对在外部循环中读取的行的缓冲来减少必须读取内部循环中的表的次数。例如，如果将10行读入缓冲区并将缓冲区传递到下一个内部循环，则可以将内部循环中读取的每一行与缓冲区中的所有10行进行比较。这将内部表必须读取的次数减少了一个数量级。

MySQL连接缓冲具有以下特征：

- 当连接的类型为[`ALL`](optimization.html#jointype_all)或时 [`index`](optimization.html#jointype_index)（换句话说，无法使用可能的键，并且分别对数据行或索引行进行完全扫描 时），可以使用连接缓冲 [`range`](optimization.html#jointype_range)。缓冲的使用也适用于外部联接，如[第8.2.1.11节“阻止嵌套循环和批处理键访问联接”中所述](optimization.html#bnl-bka-optimization)。
- 连接缓冲区永远不会分配给第一个非恒定表，即使它的类型是 [`ALL`](optimization.html#jointype_all)或 [`index`](optimization.html#jointype_index)。
- 联接中只有感兴趣的列存储在其联接缓冲区中，而不是整个行。
- 的[`join_buffer_size`](server-administration.html#sysvar_join_buffer_size) 系统变量来确定每个的大小联接缓冲液用于处理查询。
- 为每个可以缓冲的连接分配一个缓冲区，因此可以使用多个连接缓冲区来处理给定查询。
- 在执行连接之前分配连接缓冲区，并在查询完成后释放连接缓冲区。

对于先前为NLJ算法（无缓冲）描述的示例连接，使用连接缓冲按如下方式进行连接：

```
对于t1匹配范围内的每一行{
  对于t2中匹配参考关键字{
    将来自t1，t2的已使用列存储在连接缓冲区中
    如果缓冲区已满{
      对于t3中的每一行{
        对于连接缓冲区{中的每个t1，t2组合，{
          如果行满足加入条件，则发送给客户端
        }
      }
      空连接缓冲区
    }
  }
}

如果缓冲区不为空{
  对于t3中的每一行{
    对于连接缓冲区{中的每个t1，t2组合，{
      如果行满足加入条件，则发送给客户端
    }
  }
}
```

如果*`S`*是每个所存储的大小 `t1`，`t2`在组合联接缓冲液和*`C`*在缓冲器中的组合的数量，次表的数量`t3`被扫描的是：

```
（S* C）/ join_buffer_size +1
```

数`t3`扫描降低为价值[`join_buffer_size`](server-administration.html#sysvar_join_buffer_size) 时增加，最高可达点 [`join_buffer_size`](server-administration.html#sysvar_join_buffer_size)是大到足以容纳所有上一行组合。那时，通过增大它无法获得任何速度。

#### 8.2.1.7嵌套联接优化

表达联接的语法允许嵌套联接。以下讨论参考[第13.2.9.2节“ JOIN子句”中](sql-statements.html#join)描述的[联接](sql-statements.html#join)语法 。

*`table_factor`*与SQL标准相比，的 语法得到了扩展。后者仅接受*`table_reference`*，而不接受一对括号内的列表。如果我们将*`table_reference`*项目列表中的每个逗号都视为等效于内部联接，则这是一个保守的扩展 。例如：

```
选择*从t1左联接（t2，t3，t4）
                 开（t2.a = t1.a AND t3.b = t1.b AND t4.c = t1.c）
```

等效于：

```
选择*从t1左联接（t2交叉联接t3交叉联接t4）
                 开（t2.a = t1.a AND t3.b = t1.b AND t4.c = t1.c）
```

在MySQL中，`CROSS JOIN`在语法上等效于`INNER JOIN`;; 他们可以互相替换。在标准SQL中，它们不是等效的。 `INNER JOIN`与`ON`子句一起使用 ；`CROSS JOIN`否则使用。

通常，在仅包含内部联接操作的联接表达式中可以忽略括号。考虑以下联接表达式：

```
t1左联接（t2左联接t3在t2.b = t3.b或t2.b为空）
   开启t1.a = t2.a
```

在除去括号和左侧的分组操作之后，该join表达式将转换为该表达式：

```
（t1左连接t2在t1.a = t2.a上）左连接t3
    开启t2.b = t3.b或t2.b为空
```

但是，这两种表达方式并不相等。看到这一点，假设表`t1`， `t2`以及`t3`具有以下状态：

- 表格`t1`包含行 `(1)`，`(2)`
- 表`t2`包含行 `(1,101)`
- 表`t3`包含行 `(101)`

在这种情况下，第一个表达式返回结果集包括行`(1,1,101,101)`， `(2,NULL,NULL,NULL)`，而第二表达式返回的行`(1,1,101,101)`， `(2,NULL,NULL,101)`：

```
mysql> SELECT *
       FROM t1
            LEFT JOIN
            (t2 LEFT JOIN t3 ON t2.b=t3.b OR t2.b IS NULL)
            ON t1.a=t2.a;
+ ------ + ------ + ------ + ------ +
| 一个| 一个| b | b |
+ ------ + ------ + ------ + ------ +
| 1 | 1 | 101 | 101 |
| 2 | NULL | NULL | NULL |
+ ------ + ------ + ------ + ------ +

mysql> SELECT *
       FROM (t1 LEFT JOIN t2 ON t1.a=t2.a)
            LEFT JOIN t3
            ON t2.b=t3.b OR t2.b IS NULL;
+ ------ + ------ + ------ + ------ +
| 一个| 一个| b | b |
+ ------ + ------ + ------ + ------ +
| 1 | 1 | 101 | 101 |
| 2 | NULL | NULL | 101 |
+ ------ + ------ + ------ + ------ +
```

在以下示例中，将外部联接操作与内部联接操作一起使用：

```
t1左联接（t2，t3）在t1.a = t2.a上
```

该表达式不能转换为以下表达式：

```
t1左联接t2 ON t1.a = t2.a，t3
```

对于给定的表状态，两个表达式返回不同的行集：

```
mysql> SELECT *
       FROM t1 LEFT JOIN (t2, t3) ON t1.a=t2.a;
+ ------ + ------ + ------ + ------ +
| 一个| 一个| b | b |
+ ------ + ------ + ------ + ------ +
| 1 | 1 | 101 | 101 |
| 2 | NULL | NULL | NULL |
+ ------ + ------ + ------ + ------ +

mysql> SELECT *
       FROM t1 LEFT JOIN t2 ON t1.a=t2.a, t3;
+ ------ + ------ + ------ + ------ +
| 一个| 一个| b | b |
+ ------ + ------ + ------ + ------ +
| 1 | 1 | 101 | 101 |
| 2 | NULL | NULL | 101 |
+ ------ + ------ + ------ + ------ +
```

因此，如果我们在使用外部联接运算符的联接表达式中省略括号，则可能会更改原始表达式的结果集。

更确切地说，我们不能忽略左外部联接操作的右操作数和右联接操作的左操作数的括号。换句话说，我们不能忽略外部联接操作的内部表表达式的括号。另一个操作数（外部表的操作数）的括号可以忽略。

下面的表达式：

```
（t1，t2）在P（t2.b，t3.b）上左加入t3
```

对于任何表`t1,t2,t3`以及`P`属性`t2.b` 和条件上的任何条件 ，该表达式均等效于此表达式 `t3.b`：

```
t1，t2左联接t3 ON P（t2.b，t3.b）
```

每当连接表达式（*`joined_table`*）中的连接操作执行顺序不是从左到右时，我们都在谈论嵌套连接。考虑以下查询：

```
SELECT * FROM t1左联接（t2左联接t3 ON t2.b = t3.b）ON t1.a = t2.a
  其中t1.a> 1

选择*从t1左联接（t2，t3）到t1.a = t2.a
  其中（t2.b = t3.b或t2.b为NULL）并且t1.a> 1
```

这些查询被认为包含以下嵌套联接：

```
t2左联接t3在t2.b = t3.b
t2，t3
```

在第一个查询中，嵌套联接是通过左联接操作形成的。在第二个查询中，它是通过内部联接操作形成的。

在第一个查询中，可以省略括号：联接表达式的语法结构将规定联接操作的执行顺序。对于第二个查询，尽管可以在没有括号的情况下明确地解释此处的联接表达式，但是不能省略括号。在我们的扩展语法中，`(t2, t3)`第二个查询的括号是必需的，尽管从理论上讲可以在没有括号的情况下对其进行解析：由于查询`LEFT JOIN`并`ON` 在表达式的左右定界符中扮演着角色，因此查询仍然具有明确的语法结构`(t2,t3)`。

前面的示例说明了以下几点：

- 对于仅涉及内部联接（而不涉及外部联接）的联接表达式，可以删除括号并从左到右评估联接。实际上，可以按任何顺序评估表。
- 通常，对于外部联接或与内部联接混合的外部联接，情况并非如此。删除括号可能会改变结果。



具有嵌套外部联接的查询的执行方式与具有内部联接的查询的执行方式相同。更确切地说，利用了嵌套循环联接算法的一种变体。调用[嵌套循环联接](optimization.html#nested-loop-joins)执行查询的算法（请参见[第8.2.1.6节“嵌套循环联接算法”](optimization.html#nested-loop-joins)）。假设对3个表的联接查询`T1,T2,T3`具有以下形式：

```
选择*从T1内连接T2到P1（T1，T2）
                 在P2（T2，T3）上的内联接T3
  在哪里P（T1，T2，T3）
```

在这里，`P1(T1,T2)`和 `P2(T3,T3)`是一些连接条件（在表达式上），而是`P(T1,T2,T3)`在table的列上的条件`T1,T2,T3`。

嵌套循环联接算法将以以下方式执行此查询：

```
对于T1中的每一行t1 {
  对于T2中的每一行t2，使得P1（t1，t2）{
    对于T3中的每一行t3，使得P2（t2，t3）{
      如果P（t1，t2，t3）{
         t：= t1 || t2 || t3; 输出t;
      }
    }
  }
}
```

符号`t1||t2||t3`表示通过连接的行的列构成的行 `t1`，`t2`和 `t3`。在以下某些示例中， `NULL`出现表名的地方表示该表`NULL`的每一列都使用一行。例如，`t1||t2||NULL` 表示通过将行`t1`和的列`t2`以及 `NULL`的每一列 串联而构成的行`t3`。据说这样的行是 `NULL`互补的。



现在考虑带有嵌套外部联接的查询：

```
选择*从T1左联接
              （在P2（T2，T3）上的T2左联接T3）
              在P1（T1，T2）上
  在哪里P（T1，T2，T3）
```

对于此查询，修改嵌套循环模式以获得：

```
对于T1中的每一行t1 {
  BOOL f1：= FALSE;
  对于T2中的每一行t2，使得P1（t1，t2）{
    BOOL f2：= FALSE;
    对于T3中的每一行t3，使得P2（t2，t3）{
      如果P（t1，t2，t3）{
        t：= t1 || t2 || t3; 输出t;
      }
      f2 =真；
      f1 = TRUE;
    }
    IF（！f2）{
      如果P（t1，t2，NULL）{
        t：= t1 || t2 || NULL; 输出t;
      }
      f1 = TRUE;
    }
  }
  IF（！f1）{
    如果P（t1，NULL，NULL）{
      t：= t1 || NULL || NULL; 输出t;
    }
  }
}
```

通常，对于外部联接操作中第一个内部表的任何嵌套循环，都会引入一个标志，该标志在循环前关闭并在循环后检查。当针对外部表中的当前行找到表示内部操作数的表中的匹配项时，将打开该标志。如果在循环周期结束时该标志仍处于关闭状态，则未找到外部表的当前行的匹配项。在这种情况下，该行由`NULL`内部表的列的值补充 。结果行将传递到输出的最终检查项或下一个嵌套循环，但前提是该行满足所有嵌入式外部联接的联接条件。

在该示例中，嵌入了以下表达式表示的外部联接表：

```
（在P2（T2，T3）上的T2左联接T3）
```

对于具有内部联接的查询，优化器可以选择不同顺序的嵌套循环，例如：

```
对于T3中的每一行t3 {
  对于T2中的每一行t2，使得P2（t2，t3）{
    对于T1中的每一行t1使得P1（t1，t2）{
      如果P（t1，t2，t3）{
         t：= t1 || t2 || t3; 输出t;
      }
    }
  }
}
```

对于具有外部联接的查询，优化器只能选择以下顺序：外部表的循环优先于内部表的循环。因此，对于带有外部联接的查询，只能使用一个嵌套顺序。对于以下查询，优化器将评估两个不同的嵌套。在这两个嵌套中， `T1`必须在外部循环中进行处理，因为它在外部联接中使用。`T2`和 `T3`在内部联接中使用，因此联接必须在内部循环中处理。但是，由于联接是内部联接，`T2`因此 `T3`可以按任何顺序进行处理。

```
SELECT * T1左联接（T2，T3）在P1（T1，T2）和P2（T1，T3）上
  在哪里P（T1，T2，T3）
```

一个嵌套计算`T2`，然后 `T3`：

```
对于T1中的每一行t1 {
  BOOL f1：= FALSE;
  对于T2中的每一行t2，使得P1（t1，t2）{
    对于T3中的每一行t3，使得P2（t1，t3）{
      如果P（t1，t2，t3）{
        t：= t1 || t2 || t3; 输出t;
      }
      f1：= TRUE
    }
  }
  IF（！f1）{
    如果P（t1，NULL，NULL）{
      t：= t1 || NULL || NULL; 输出t;
    }
  }
}
```

另一个嵌套计算`T3`，则 `T2`：

```
对于T1中的每一行t1 {
  BOOL f1：= FALSE;
  对于T3中的每一行t3，使得P2（t1，t3）{
    对于T2中的每一行t2，使得P1（t1，t2）{
      如果P（t1，t2，t3）{
        t：= t1 || t2 || t3; 输出t;
      }
      f1：= TRUE
    }
  }
  IF（！f1）{
    如果P（t1，NULL，NULL）{
      t：= t1 || NULL || NULL; 输出t;
    }
  }
}
```

在讨论内部联接的嵌套循环算法时，我们省略了一些细节，这些细节对查询执行性能的影响可能很大。我们没有提到所谓的 “ 下推 ”条件。假设我们的 `WHERE`条件 `P(T1,T2,T3)`可以用一个联合公式表示：

```
P（T1，T2，T2）= C1（T1）和C2（T2）和C3（T3）。
```

在这种情况下，MySQL实际上使用以下嵌套循环算法通过内部联接执行查询：

```
对于T1中的每一行t1，使得C1（t1）{
  对于T2中的每一行t2，使得P1（t1，t2）和C2（t2）{
    对于T3中的每一行t3，使得P2（t2，t3）和C3（t3）{
      如果P（t1，t2，t3）{
         t：= t1 || t2 || t3; 输出t;
      }
    }
  }
}
```

你看，每个合取的`C1(T1)`， `C2(T2)`，`C3(T3)`是最内环的推到最外环的地方进行评估。如果`C1(T1)`是非常严格的条件，则此条件下推可能会大大减少表中`T1` 传递给内部循环的行数。结果，查询的执行时间可以大大改善。

对于具有外部联接的查询，`WHERE` 仅在发现外部表的当前行在内部表中具有匹配项之后，才检查条件。因此，将条件从内部嵌套循环中推出的优化不能直接应用于具有外部联接的查询。在这里，我们必须引入条件下推谓词，该条件下推谓词由遇到匹配时打开的标志保护。

回想一下带有外部联接的示例：

```
P（T1，T2，T3）= C1（T1）和C（T2）和C3（T3）
```

对于该示例，使用受保护的下推条件的嵌套循环算法如下所示：

```
对于T1中的每一行t1，使得C1（t1）{
  BOOL f1：= FALSE;
  对于T2中的每一行t2
      这样P1（t1，t2）AND（f1？C2（t2）：TRUE）{
    BOOL f2：= FALSE;
    对于T3中的每一行t3
        这样P2（t2，t3）AND（f1 && f2？C3（t3）：TRUE）{
      IF（f1 && f2？TRUE：（C2（t2）AND C3（t3）））{
        t：= t1 || t2 || t3; 输出t;
      }
      f2 =真；
      f1 = TRUE;
    }
    IF（！f2）{
      IF（f1？TRUE：C2（t2）&& P（t1，t2，NULL））{
        t：= t1 || t2 || NULL; 输出t;
      }
      f1 = TRUE;
    }
  }
  IF（！f1 && P（t1，NULL，NULL））{
      t：= t1 || NULL || NULL; 输出t;
  }
}
```

通常，可以从诸如`P1(T1,T2)`和的 连接条件中提取下推谓词`P(T2,T3)`。在这种情况下，下推谓词也由一个标志来保护，该标志防止检查谓词中是否存在`NULL`由相应外部联接操作生成的-补行。

如果是由`WHERE`条件谓词引起的，则禁止通过一个键在同一个嵌套联接中从一个内部表访问另一个内部表。

#### 8.2.1.8外部联接优化



外部联接包括`LEFT JOIN`和 `RIGHT JOIN`。

MySQL实现如下： `*`A`* LEFT JOIN *`B`* *`join_specification`*`

- 表*`B`*被设置为依赖于表*`A`*以及所依赖的所有表 *`A`*。
- 表格*`A`*被设置为取决于条件*`B`*中使用的所有表格（除外）`LEFT JOIN`。
- 该`LEFT JOIN`条件用于确定如何从table中检索行 *`B`*。（换句话说，`WHERE`不使用该子句中的任何条件。）
- 执行所有标准的连接优化，不同之处在于始终在表所依赖的所有表之后读取该表。如果存在循环依赖关系，则会发生错误。
- `WHERE`执行 所有标准优化。
- 如果一行中*`A`*有匹配该`WHERE`子句的行，但其中没有一行*`B`*与`ON`条件匹配的行，则将 *`B`*生成一个额外的 行，并将所有列都设置为`NULL`。
- 如果`LEFT JOIN`用于查找某张表中不存在的行，并且进行了以下测试：`*`col_name`* IS NULL`在该`WHERE`部分中，*`col_name`*声明为的列在哪里 `NOT NULL`，MySQL在找到后停止搜索更多行（针对特定的键组合）符合`LEFT JOIN`条件的一行。

该`RIGHT JOIN`实现类似于`LEFT JOIN`表角色相反的实现。如[第8.2.1.9节“简化外部连接”](optimization.html#outer-join-simplification)所述，将右连接转换为等效的左连接。

对于`LEFT JOIN`，如果 `WHERE`条件对于生成的`NULL`行始终为false ，则将`LEFT JOIN`其更改为内部联接。例如， `WHERE`如果条款是在下面的查询错误的`t2.column1`是 `NULL`：

```
SELECT * FROM t1左联接t2 ON（第1列），其中t2.column2 = 5;
```

因此，将查询转换为内部联接是安全的：

```
选择*从t1，t2到t2.column2 = 5 AND t1.column1 = t2.column1;
```



现在，如果这样做会导致更好的查询计划，那么优化器可以先使用表，`t2`然后使用表`t1`。要提供有关表连接顺序的提示，请使用`STRAIGHT_JOIN`；请参见 [第13.2.9节“ SELECT语句”](sql-statements.html#select)。但是，`STRAIGHT_JOIN`由于它禁用了半联接转换，因此 可能会阻止使用索引。请参见 [第8.2.2.1节“使用半联接转换优化子查询，派生表和视图引用”](optimization.html#semijoins)。

#### 8.2.1.9外部联接简化

`FROM`在许多情况下，查询子句中的 表表达式都得到了简化。

在解析器阶段，具有右外部联接操作的查询将转换为仅包含左联接操作的等效查询。在一般情况下，执行转换时要进行以下右连接：

```
（T1，...）右联接（T2，...）ON P（T1，...，T2，...）
```

成为以下等效的左联接：

```
（T2，...）左联接（T1，...）ON P（T1，...，T2，...）
```

形式的所有内部联接表达式`T1 INNER JOIN T2 ON P(T1,T2)`都由list替换 `T1,T2`，`P(T1,T2)`作为`WHERE`条件（或嵌入联接的联接条件，如果有）的联接而联接。

当优化程序评估外部联接操作的计划时，它仅考虑计划，其中对于每个此类操作，在访问内部表之前访问外部表。由于只有这样的计划才能使用嵌套循环算法执行外部联接，因此优化器的选择受到限制。

考虑这种形式的查询，其中`R(T2)` 大大缩小了table中匹配行的数量 `T2`：

```
选择* T1左联接T2到P1（T1，T2）
  P（T1，T2）和R（T2）
```

如果查询以书面形式执行，则优化器别无选择，只能在限制程度`T1`更高的表之前访问限制程度较小的 表 `T2`，这可能会产生效率很低的执行计划。

相反，如果`WHERE`条件为空，则MySQL将查询转换为无外部联接操作的查询。（也就是说，它将外部联接转换为内部联接。）如果条件求值`FALSE`或`UNKNOWN`为该`NULL`操作生成的任何 互补行，则条件被认为是null拒绝 的。



因此，对于此外部联接：

```
T1左联接T2在T1.A = T2.A上
```

诸如此类的条件将被拒绝为null，因为它们对于任何`NULL`补行（ `T2`列设置为`NULL`）都无法成立：

```
T2.B不为空
T2.B> 3
T2.C <= T1.C
T2.B <2或T2.C> 1
```

诸如此类的条件不能为空，因为它们对于`NULL`-补行可能是正确的：

```
T2.B IS NULL
T1.B <3或T2.B不为空
T1.B <3或T2.B> 3
```

检查外部联接操作的条件是否为空的通用规则很简单：

- 它的形式为`A IS NOT NULL`，其中哪里 `A`是任何内部表的属性
- 这是一个谓词，包含对内部表的引用，该内部表的求值是`UNKNOWN`何时其参数之一为`NULL`
- 它是一个包含空值拒绝条件作为连接词的连接词
- 它是零值拒绝条件的析取

对于查询中的一个外部联接操作，条件可以为null拒绝，而对于另一项则不能为null。在此查询中，`WHERE`第二个外部联接操作的条件为空，但第一个条件的条件为空：

```
选择*从T1左联接T2到T2.A = T1.A
                 在T3.B = T1.B上左加入T3
  T3.C> 0
```

如果`WHERE`查询中的外部联接操作拒绝该条件为空，则将外部联接操作替换为内部联接操作。

例如，在前面的查询中，第二个外部联接被拒绝为空，并且可以由内部联接代替：

```
选择*从T1左联接T2到T2.A = T1.A
                 T3.B = T1.B上的内联接T3
  T3.C> 0
```

对于原始查询，优化器仅评估与单个表访问顺序兼容的计划 `T1,T2,T3`。对于重写的查询，它还会考虑访问顺序 `T3,T1,T2`。

一种外部联接操作的转换可能触发另一种外部联接操作的转换。因此，查询：

```
选择*从T1左联接T2到T2.A = T1.A
                 在T3.B = T2.B上左加入T3
  T3.C> 0
```

首先转换为查询：

```
选择*从T1左联接T2到T2.A = T1.A
                 T3.B = T2.B上的内联接T3
  T3.C> 0
```

相当于查询：

```
SELECT * FROM（T1左T2在T2.A = T1.A上），T3
  T3.C> 0 AND T3.B = T2.B
```

其余的外部联接操作也可以由内部联接代替，因为该条件`T3.B=T2.B` 被拒绝为空。这导致根本没有外部联接的查询：

```
SELECT * FROM（T1内连接T2在T2.A = T1.A上），T3
  T3.C> 0 AND T3.B = T2.B
```

有时，优化器成功替换了嵌入的外部联接操作，但是无法转换嵌入的外部联接。以下查询：

```
选择*从T1左联接
              （在T3.B = T2.B上的T2左联接T3）
              开启T2.A = T1.A
  T3.C> 0
```

转换为：

```
选择*从T1左联接
              （T2内连接T3在T3.B = T2.B上）
              开启T2.A = T1.A
  T3.C> 0
```

只能将其重写为仍包含嵌入外部联接操作的表单：

```
选择*从T1左联接
              （T2，T3）
              开启（T2.A = T1.A和T3.B = T2.B）
  T3.C> 0
```

在查询中转换嵌入式外部联接操作的任何尝试都必须考虑将外部联接嵌入条件的联接 `WHERE`条件。在此查询中，`WHERE`嵌入的外部联接的 条件不为空，但嵌入外部联接的联接条件`T2.A=T1.A AND T3.C=T1.C`为空：

```
选择*从T1左联接
              （在T3.B = T2.B上的T2左联接T3）
              开启T2.A = T1.A和T3.C = T1.C
  T3.D> 0或T1.D> 0
```

因此，查询可以转换为：

```
选择*从T1左联接
              （T2，T3）
              在T2.A = T1.A和T3.C = T1.C和T3.B = T2.B
  T3.D> 0或T1.D> 0
```

#### 8.2.1.10多范围读取优化



当表较大且未存储在存储引擎的高速缓存中时，在辅助索引上使用范围扫描来读取行会导致对表的许多随机磁盘访问。通过磁盘扫描多范围读取（MRR）优化，MySQL尝试通过首先仅扫描索引并收集相关行的键来减少用于范围扫描的随机磁盘访问次数。然后对键进行排序，最后使用主键的顺序从基表中检索行。磁盘扫描MRR的动机是减少随机磁盘访问的次数，而是对基表数据进行更顺序的扫描。

多范围读取优化具有以下优点：

- MRR使基于索引元组的数据行可以顺序访问，而不是以随机顺序访问。服务器获取一组满足查询条件的索引元组，并根据数据行ID顺序对它们进行排序，然后使用排序后的元组按顺序检索数据行。这使得数据访问更加高效且成本更低。
- MRR支持对需要通过索引元组访问数据行的操作（例如范围索引扫描和使用索引作为联接属性的等联接）进行键访问请求的批处理。MRR在一系列索引范围内进行迭代以获得合格的索引元组。随着这些结果的累积，它们将用于访问相应的数据行。开始读取数据行之前不必获取所有索引元组。

在虚拟生成的列上创建的二级索引不支持MRR优化。 `InnoDB`支持虚拟生成的列上的二级索引。

以下方案说明了MRR优化何时可以发挥优势：

方案A：MRR可用于`InnoDB`和 `MyISAM`索引范围扫描和表相等联接的操作。

1. 索引元组的一部分累积在缓冲区中。
2. 缓冲区中的元组按其数据行ID排序。
3. 根据排序的索引元组序列访问数据行。

方案B：MRR可用于 [`NDB`](mysql-cluster.html)表以进行多范围索引扫描，或在按属性执行等值联接时使用。

1. 一部分范围（可能是单键范围）累积在提交查询的中心节点上的缓冲区中。
2. 范围被发送到访问数据行的执行节点。
3. 被访问的行被打包到程序包中并发送回中心节点。
4. 收到的带有数据行的数据包将放置在缓冲区中。
5. 从缓冲区读取数据行。

使用MRR时`Extra`，[`EXPLAIN`](sql-statements.html#explain)输出中的列 显示 `Using MRR`。

`InnoDB``MyISAM`如果不需要访问整个表行以产生查询结果，则不要使用MRR。如果可以完全根据索引元组中的信息（通过[覆盖索引](glossary.html#glos_covering_index)）产生结果，则为这种情况；MRR没有任何好处。

两个[`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)系统变量标志提供了使用MRR优化的接口。该`mrr`标志控制是否启用MRR。如果`mrr`启用（`on`），则该`mrr_cost_based` 标志控制优化程序是尝试在使用MRR与不使用MRR之间做出基于成本的选择（`on`）还是在可能的情况下使用MRR（`off`）。默认情况下，`mrr` is `on`和`mrr_cost_based` is `on`。请参见 [第8.9.2节“可切换的优化”](optimization.html#switchable-optimizations)。

对于MRR，存储引擎将[`read_rnd_buffer_size`](server-administration.html#sysvar_read_rnd_buffer_size)系统变量的值 用作可为其缓冲区分配多少内存的准则。引擎最多使用 [`read_rnd_buffer_size`](server-administration.html#sysvar_read_rnd_buffer_size)字节，并确定一次处理要处理的范围数。

#### 8.2.1.11块嵌套循环和批处理密钥访问联接



在MySQL中，可以使用批处理键访问（BKA）联接算法，该算法同时使用对联接表的索引访问和联接缓冲区。BKA算法支持内部联接，外部联接和半联接操作，包括嵌套的外部联接。BKA的好处包括由于更高效的表扫描而提高了连接性能。此外，以前仅用于内部联接的块嵌套循环（BNL）联接算法得到了扩展，可以用于外部联接和半联接操作，包括嵌套的外部联接。

以下各节讨论基于原始BNL算法，扩展的BNL算法和BKA算法的扩展的连接缓冲区管理。有关半联接策略的信息，请参见[第8.2.2.1节“使用半联接转换优化子查询，派生表和视图引用”](optimization.html#semijoins)

- [用于块嵌套循环和批处理密钥访问算法的联接缓冲区管理](optimization.html#join-buffer-management)
- [外部联接和半联接的块嵌套循环算法](optimization.html#bnl-optimization)
- [批量密钥访问联接](optimization.html#bka-optimization)
- [块嵌套循环和批处理密钥访问算法的优化器提示](optimization.html#bnl-bka-optimizer-hints)

##### 用于块嵌套循环和批处理密钥访问算法的联接缓冲区管理

MySQL可以使用联接缓冲区来执行内部联接，而无需内部索引访问内部表，还可以执行在子查询展平后出现的外部联接和半联接。此外，当对内部表进行索引访问时，可以有效地使用连接缓冲区。

在存储感兴趣的行列的值时，连接缓冲区管理代码可以更有效地利用连接缓冲区空间：如果行列的值为，则不会在缓冲区中为行列分配其他字节 `NULL`，并且为的任何值分配最小字节数。该 [`VARCHAR`](data-types.html#char)类型。

该代码支持两种类型的缓冲区：常规缓冲区和增量缓冲区。假设使用了连接缓冲区`B1` 来连接表`t1`， `t2`并且`t3`使用连接缓冲区将该操作的结果与表连接 `B2`：

- 常规连接缓冲区包含每个连接操作数中的列。如果`B2`是常规联接缓冲区，则*`r`*放入的 每一行`B2`都由*`r1`*from `B1`中的一行的列*`r2`*和table中 匹配的行的有趣的列组成`t3`。
- 增量连接缓冲区仅包含第二个连接操作数产生的表行中的列。也就是说，它从第一个操作数缓冲区递增到一行。如果`B2`是增量连接缓冲区，则它包含该行的有趣列以及从 *`r2`* 到该行的链接 。 *`r1`*`B1`

增量连接缓冲区始终相对于来自较早连接操作的连接缓冲区是增量的，因此来自第一次连接操作的缓冲区始终是常规缓冲区。在刚才的例子中，缓冲`B1` 用来连接表`t1`和 `t2`必须是常规缓冲区。

用于联接操作的增量缓冲区的每一行仅包含要联接表中一行的有趣列。这些列通过引用第一个连接操作数产生的表中匹配行的有趣列来进行扩充。*`r`*只要所有这些行都与row匹配，增量缓冲区中的几行就可以引用同一行，该行 的列存储在先前的join缓冲区中 *`r`*。

增量缓冲区使从以前的联接操作所使用的缓冲区中复制列的频率降低。这样可以节省缓冲区空间，因为通常情况下，第一个连接操作数产生的行可以与第二个连接操作数产生的几行匹配。不必从第一个操作数复制一行。由于减少了复制时间，因此增量缓冲区还可以节省处理时间。

系统变量 的`block_nested_loop`和 `batched_key_access`标志 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)控制优化器如何使用“块嵌套循环”和“批处理键访问”联接算法。默认情况下，`block_nested_loop`is `on`和 `batched_key_access`is `off`。请参见 [第8.9.2节“可切换的优化”](optimization.html#switchable-optimizations)。优化程序提示也可能适用；请参阅 [块嵌套循环和批处理密钥访问算法的优化器提示](optimization.html#bnl-bka-optimizer-hints)。

有关半联接策略的信息，请参见 [第8.2.2.1节“使用半联接转换优化子查询，派生表和视图引用”](optimization.html#semijoins)

##### 外部联接和半联接的块嵌套循环算法



MySQL BNL算法的原始实现已扩展为支持外部联接和半联接操作。

当使用连接缓冲区执行这些操作时，放入缓冲区的每一行都将提供一个匹配标志。

如果使用联接缓冲区执行外部联接操作，则检查第二个操作数产生的表的每一行是否与联接缓冲区中的每一行匹配。找到匹配项后，将形成一个新的扩展行（原始行加上第二个操作数的列），并通过其余的join操作发送以进行进一步扩展。另外，启用缓冲区中匹配行的匹配标志。在检查了要连接的表的所有行之后，将扫描连接缓冲区。缓冲区中未启用匹配标志的每一行都以`NULL`补码（`NULL` 第二个操作数中每一列的值），并通过其余的联接操作发送以进行进一步扩展。

系统变量 的`block_nested_loop`标志 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)控制优化器如何使用块嵌套循环算法。默认情况下 `block_nested_loop`为 `on`。请参见 [第8.9.2节“可切换的优化”](optimization.html#switchable-optimizations)。优化程序提示也可能适用；请参阅 [块嵌套循环和批处理密钥访问算法的优化器提示](optimization.html#bnl-bka-optimizer-hints)。

在[`EXPLAIN`](sql-statements.html#explain)输出端，为表使用BNL的当被所指`Extra` 值包含`Using join buffer (Block Nested Loop)`与所述`type`值是 [`ALL`](optimization.html#jointype_all)， [`index`](optimization.html#jointype_index)，或 [`range`](optimization.html#jointype_range)。

在某些情况下，涉及一个或多个子查询与一个或多个左联接的组合，尤其是返回许多行的联接，可能会使用BNL，即使在这种情况下并不理想。这是一个已知问题，已在MySQL 8.0中修复。如果升级MySQL对您来说不是立即可行的，那么您可能希望同时通过设置[`optimizer_switch='block_nested_loop=off'`](server-administration.html#sysvar_optimizer_switch) 或使用[`NO_BNL`](optimization.html#optimizer-hints-table-level) 优化程序提示来禁用BNL，以 使用一个或多个索引提示让优化程序选择更好的计划（请参见 [第8.9.4节“索引”提示”](optimization.html#index-hints)）或这些的某种组合，以提高此类查询的性能。

有关半联接策略的信息，请参见 [第8.2.2.1节“使用半联接转换优化子查询，派生表和视图引用”](optimization.html#semijoins)

##### 批量密钥访问联接



MySQL实现了一种联接表的方法，称为批处理键访问（BKA）联接算法。当对第二个连接操作数产生的表进行索引访问时，可以应用BKA。像BNL连接算法一样，BKA连接算法采用连接缓冲区来累加连接操作的第一个操作数所产生的行的感兴趣的列。然后，BKA算法构建键以访问要为缓冲区中的所有行连接的表，并将这些键批量提交给数据库引擎以进行索引查找。密钥通过多范围读取（MRR）接口提交给引擎（请参见 [第8.2.1.10节“多范围读取优化”）](optimization.html#mrr-optimization)）。提交密钥后，MRR引擎函数以最佳方式在索引中执行查找，以获取由这些密钥找到的联接表的行，并开始向BKA联接算法提供匹配的行。每个匹配的行与联接缓冲区中对行的引用耦合。

使用BKA时，的值 [`join_buffer_size`](server-administration.html#sysvar_join_buffer_size)定义对存储引擎的每个请求中的批量密钥。缓冲区越大，连接操作右侧表的顺序访问就越多，这可以显着提高性能。

要使用BKA，必须将系统变量的 `batched_key_access`标志 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)设置为`on`。BKA使用MRR，因此`mrr`标记也必须为 `on`。当前，MRR的成本估算过于悲观。因此，也有必要对 `mrr_cost_based`要 `off`用于要使用的BKA。以下设置启用BKA：

```
mysql> SET optimizer_switch='mrr=on,mrr_cost_based=off,batched_key_access=on';
```

执行MRR功能有两种方案：

- 第一种方案用于传统的基于磁盘的存储引擎，例如 [`InnoDB`](innodb-storage-engine.html)和 [`MyISAM`](storage-engines.html#myisam-storage-engine)。对于这些引擎，通常将来自连接缓冲区的所有行的键一次提交到MRR接口。特定于引擎的MRR函数对提交的键执行索引查找，从它们中获取行ID（或主键），然后根据BKA算法的请求为所有这些选定的行ID逐个获取行。返回的每一行都有一个关联引用，该关联引用允许访问连接缓冲区中的匹配行。通过MRR函数以最佳方式获取行：按照行ID（主键）顺序获取行。由于读取是按磁盘顺序而不是随机顺序进行的，因此可以提高性能。
- 第二种情况用于远程存储引擎，例如[`NDB`](mysql-cluster.html)。MySQL服务器（SQL节点）将来自连接缓冲区的一部分行的键及其相关联的密钥包发送到NDB Cluster数据节点。作为回报，SQL节点接收匹配行的包（或几个包）以及相应的关联。BKA联接算法采用这些行并构建新的联接行。然后，将一组新的密钥发送到数据节点，并将返回的包中的行用于构建新的联接行。该过程一直持续到将来自联接缓冲区的最后一个键发送到数据节点，并且SQL节点已接收并联接了与这些键匹配的所有行。

在第一种情况下，保留了一部分连接缓冲区以存储由索引查找选择并作为参数传递给MRR函数的行ID（主键）。

没有特殊的缓冲区来存储为连接缓冲区中的行构建的键。而是将为缓冲区中的下一行建立键的函数作为参数传递给MRR函数。

在[`EXPLAIN`](sql-statements.html#explain)输出中，当`Extra` 值包含`Using join buffer (Batched Key Access)`且`type`值为 [`ref`](optimization.html#jointype_ref)或 时，表示对表使用BKA [`eq_ref`](optimization.html#jointype_eq_ref)。

##### 块嵌套循环和批处理密钥访问算法的优化器提示

除了在整个[`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)会话范围内使用 系统变量来控制优化器对BNL和BKA算法的使用之外，MySQL还支持优化器提示，以针对每个语句影响优化器。请参见 [第8.9.3节“优化器提示”](optimization.html#optimizer-hints)。

若要使用BNL或BKA提示为外部联接的任何内部表启用联接缓冲，必须为外部联接的所有内部表启用联接缓冲。

#### 8.2.1.12条件过滤

在联接处理中，前缀行是从联接中的一个表传递到下一个表的那些行。通常，优化程序会尝试在连接顺序的早期放置前缀计数较低的表，以防止行组合的数量迅速增加。在某种程度上，优化器可以使用有关从一个表中选择并传递到下一个表的行的条件的信息，它可以更准确地计算行估计并选择最佳执行计划。

如果不使用条件过滤，则表的前缀行数将基于`WHERE`子句根据优化程序选择的访问方法估算的行数 。条件过滤使优化器可以`WHERE`在访问方法未考虑的子句中使用其他相关条件 ，从而改善其前缀行数估计。例如，即使可能存在基于索引的访问方法，该方法可用于从联接中的当前表中选择行，但对于表中的表，也可能存在其他条件。`WHERE` 子句可以过滤（进一步限制）传递给下一张表的合格行的估计值。

仅在以下情况下，条件才有助于过滤估计：

- 它引用当前表。
- 它取决于连接序列中一个或多个常量值。
- 访问方法尚未考虑它。

在[`EXPLAIN`](sql-statements.html#explain)输出中，该 `rows`列指示所选访问方法的行估计，并且该`filtered` 列反映条件过滤的效果。 `filtered`值以百分比表示。最大值为100，这表示未过滤行。值从100减小表示过滤量增加。

前缀行数（估计从当前表通过联接传递到下一个表的行数）是`rows`和 `filtered`值的乘积。即，前缀行数是估计的行数，该估计的行数由于估计的滤波效果而减少。例如，如果`rows`为1000且`filtered`为20％，则条件过滤会将估算的行数1000减少为前缀行数1000×20％= 1000×.2 = 200。

考虑以下查询：

```
选择 *
  从员工加入部门开始到employee.dept_no = department.dept_no
  WHERE employee.first_name ='约翰'
  AND employee.hire_date在'2018-01-01'和'2018-06-01'之间;
```

假设数据集具有以下特征：

- 该`employee`表有1024行。

- 该`department`表有12行。

- 两个表在上都有一个索引`dept_no`。

- 该`employee`表的索引为 `first_name`。

- 8行满足以下条件 `employee.first_name`：

  ```
  employee.first_name ='约翰'
  ```

- 150行满足以下条件 `employee.hire_date`：

  ```
  employee.hire_date在'2018-01-01'和'2018-06-01'之间
  ```

- 1行满足以下两个条件：

  ```
  employee.first_name ='约翰'
  AND employee.hire_date在'2018-01-01'和'2018-06-01'之间
  ```

没有条件过滤，将 [`EXPLAIN`](sql-statements.html#explain)产生如下输出：

```
+ ---- + ------------ + -------- + ------------------ + --- ------ + --------- + ------ + ---------- +
| id | 桌子| 类型 可能的钥匙| 关键 参考| 行| 过滤
+ ---- + ------------ + -------- + ------------------ + --- ------ + --------- + ------ + ---------- +
| 1 | 员工| 参考| 名称，日期，部门| 名称| const | 8 | 100.00 |
| 1 | 部门| eq_ref | 主要| 主要| dept_no | 1 | 100.00 |
+ ---- + ------------ + -------- + ------------------ + --- ------ + --------- + ------ + ---------- +
```

为此`employee`，`name`索引上的访问方法将 拾取与名称匹配的8行`'John'`。没有进行任何过滤（`filtered`为100％），因此所有行都是下一张表的前缀行：前缀行计数为 `rows`× `filtered`= 8×100％= 8。

通过条件过滤，优化器还可以考虑`WHERE` 访问方法未考虑的子句中的条件。在这种情况下，优化器使用启发式方法估计[`BETWEEN`](functions.html#operator_between) 条件为的16.31％的过滤效果`employee.hire_date`。结果，[`EXPLAIN`](sql-statements.html#explain)产生如下输出：

```
+ ---- + ------------ + -------- + ------------------ + --- ------ + --------- + ------ + ---------- +
| id | 桌子| 类型 可能的钥匙| 关键 参考| 行| 过滤
+ ---- + ------------ + -------- + ------------------ + --- ------ + --------- + ------ + ---------- +
| 1 | 员工| 参考| 名称，日期，部门| 名称| const | 8 | 16.31 |
| 1 | 部门| eq_ref | 主要| 主要| dept_no | 1 | 100.00 |
+ ---- + ------------ + -------- + ------------------ + --- ------ + --------- + ------ + ---------- +
```

现在，前缀行计数为`rows`× `filtered`= 8×16.31％= 1.3，它更紧密地反映了实际数据集。

通常，优化器不会为最后一个联接表计算条件过滤效果（减少前缀行数），因为没有下一个表可以将行传递给该表。发生以下异常 [`EXPLAIN`](sql-statements.html#explain)：为了提供更多信息，将为所有联接的表（包括最后一个表）计算过滤效果。

要控制优化器是否考虑其他过滤条件，请使用系统变量的 `condition_fanout_filter`标志 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)（请参见[第8.9.2节“可切换的优化”](optimization.html#switchable-optimizations)）。默认情况下，此标志是启用的，但可以禁用它以抑制条件过滤（例如，如果发现特定查询不使用它会产生更好的性能）。

如果优化器高估了条件过滤的效果，则性能可能会比不使用条件过滤的情况差。在这种情况下，这些技术可能会帮助：

- 如果未对列进行索引，请对其进行索引，以便优化程序获得有关列值分布的一些信息，并可以改善其行估计。

- 更改加入顺序。完成此操作的方法包括紧接在和 之后的 [联接](optimization.html#optimizer-hints)顺序优化器提示（请参见 [第8.9.3节“优化器提示”](optimization.html#optimizer-hints)） 。 `STRAIGHT_JOIN``SELECT``STRAIGHT_JOIN`

- 禁用会话的条件过滤：

  ```
  SET Optimizer_switch ='condition_fanout_filter = off';
  ```

#### 8.2.1.13 IS NULL优化



MySQL能够执行在相同的优化 ，它可以使用 。例如，MySQL可以使用索引和范围来搜索 with 。 *`col_name`* [`IS NULL`](functions.html#operator_is-null)*`col_name`* `=` *`constant_value`*`NULL`[`IS NULL`](functions.html#operator_is-null)

例子：

```
SELECT * FROM tbl_nameWHERE key_colIS NULL;

SELECT * FROM tbl_nameWHERE key_col<=> NULL;

SELECT * FROM tbl_name
  WHERE key_col= const1OR key_col= const2OR key_colIS NULL;
```

如果`WHERE`子句包含声明为的列的 条件，则 该表达式将被优化。如果该列仍然可能产生（例如，如果它来自a右侧的表），则 不会进行此优化。 *`col_name`* [`IS NULL`](functions.html#operator_is-null)`NOT NULL``NULL``LEFT JOIN`

MySQL还可以优化组合 ，这种形式在已解决的子查询中很常见。 显示 何时使用此优化。 `*`col_name`* = *`expr`* OR *`col_name`* IS NULL`[`EXPLAIN`](sql-statements.html#explain)[`ref_or_null`](optimization.html#jointype_ref_or_null)

此优化可以处理[`IS NULL`](functions.html#operator_is-null)任何关键部分。

假设在列`a`和 `b`表上都有索引，则对查询进行一些优化的示例`t2`：

```
选择*从t1到t1.a = exprOR t1.a为NULL;

SELECT * FROM t1，t2其中t1.a = t2.a或t2.a为NULL；

选择*从t1，t2
  其中（t1.a = t2.a或t2.a为NULL）AND t2.b = t1.b;

选择*从t1，t2
  其中t1.a = t2.a AND（t2.b = t1.b或t2.b为NULL）;

选择*从t1，t2
  在哪里（t1.a = t2.a AND t2.a为NULL AND ...）
  或（t1.a = t2.a AND t2.a为NULL AND ...）;
```

[`ref_or_null`](optimization.html#jointype_ref_or_null)通过首先读取参考键，然后单独搜索具有`NULL`键值的行来工作。

优化只能处理一个[`IS NULL`](functions.html#operator_is-null)级别。在以下查询中，MySQL仅在表达式上使用键查找`(t1.a=t2.a AND t2.a IS NULL)`，而不能在上使用键部分 `b`：

```
选择*从t1，t2
  在哪里（t1.a = t2.a AND t2.a为NULL）
  或（t1.b = t2.b AND t2.b为NULL）;
```

#### 8.2.1.14优化排序



本节描述了MySQL何时可以使用索引满足`ORDER BY`子句，无法使用索引时使用的 `filesort`操作以及优化器（）提供的执行计划信息`ORDER BY`。

一个`ORDER BY`有和没有 `LIMIT`可能以不同的顺序返回行，在讨论[第8.2.1.17，“LIMIT查询优化”](optimization.html#limit-optimization)。

- [使用索引满足ORDER BY](optimization.html#order-by-index-use)
- [使用文件排序满足ORDER BY](optimization.html#order-by-filesort)
- [通过优化影响订单](optimization.html#order-by-optimizer-control)
- [ORDER BY执行计划信息可用](optimization.html#order-by-diagnostic-information)

##### 使用索引满足ORDER BY

在某些情况下，MySQL可以使用索引来满足 `ORDER BY`子句，并避免执行`filesort` 操作时涉及的额外排序。

即使`ORDER BY`索引与索引不完全匹配，也可以使用索引，只要索引的所有未使用部分和所有额外的 `ORDER BY`列在`WHERE`子句中都是常量即可 。如果索引不包含查询访问的所有列，则仅当索引访问比其他访问方法便宜时才使用索引。

假设在上有一个索引 ，以下查询可以使用该索引来解析 零件。优化程序是否实际这样做取决于如果还必须读取索引中没有的列，则读取索引是否比表扫描更有效。 `(*`key_part1`*, *`key_part2`*)``ORDER BY`

- 在此查询中，索引on 使优化器避免排序： `(*`key_part1`*, *`key_part2`*)`

  ```
  选择*从t1
    ORDER BY key_part1，key_part2；
  ```

  但是，查询使用`SELECT *`，它可能会选择比*`key_part1`*和 多的列 *`key_part2`*。在这种情况下，扫描整个索引并查找表行以查找索引中未包含的列可能比扫描表并对结果进行排序要昂贵。如果是这样，优化器可能不会使用索引。如果 `SELECT *`仅选择索引列，则将使用索引并避免排序。

  如果`t1`是`InnoDB` 表，则表主键隐式属于索引的一部分，并且该索引可用于解析 `ORDER BY`此查询：

  ```
  SELECT pk，key_part1，key_part2从T1
    ORDER BY key_part1，key_part2；
  ```

- 在此查询中，它*`key_part1`*是常量，因此通过索引访问的所有行都是 *`key_part2`*有序的，并且如果子句具有足够的选择性以使索引范围扫描比表扫描便宜，则on的索引可以避免排序： `(*`key_part1`*, *`key_part2`*)``WHERE`

  ```
  选择*从t1
    在哪里key_part1= constant
    ORDER BY key_part2;
  ```

- 在接下来的两个查询中，是否使用索引与`DESC`前面没有显示的相同查询类似 ：

  ```
  选择*从t1
    按key_part1DESC排序，key_part2DESC；
  
  选择*从t1
    WHERE key_part1=按constant
    订单key_part2排序；
  ```

- 在接下来的两个查询中， *`key_part1`*将其与常量进行比较。如果 `WHERE`子句的选择性足以使索引范围扫描比表扫描便宜，那么将使用索引：

  ```
  选择*从t1
    在哪里key_part1>按ASC constant
    订购key_part1；
  
  选择*从t1
    在哪里key_part1<按constant
    订单key_part1排序;
  ```

- 在下一个查询中，`ORDER BY`不会命名*`key_part1`*，但是所选的所有行都有一个常 *`key_part1`*量值，因此仍可以使用索引：

  ```
  选择*从t1
    WHERE key_part1= constant1AND key_part2> constant2
    ORDER BY key_part2;
  ```

在某些情况下，MySQL *无法*使用索引来解析`ORDER BY`，尽管它仍然可以使用索引来查找与该`WHERE`子句匹配的行 。例子：

- 该查询用于`ORDER BY`不同的索引：

  ```
  SELECT * FROM T1 ORDER BY key1，key2;
  ```

- 该查询`ORDER BY`对索引的非连续部分使用：

  ```
  SELECT * FROM T1 WHERE key2= constantORDER BY key1_part1，key1_part3;
  ```

- 查询混合`ASC`和 `DESC`：

  ```
  SELECT * FROM t1按key_part1DESC，key_part2ASC排序；
  ```

- 用于获取行的索引与在中使用的索引不同`ORDER BY`：

  ```
  SELECT * FROM t1 WHERE key2= constantORDER BY key1;
  ```

- 该查询使用`ORDER BY`的表达式包含除索引列名称以外的术语：

  ```
  SELECT * FROM t1 ORDER BY ABS（key）;
  选择* FROM t1 ORDER BY- key;
  ```

- 该查询联接了许多表，并且中的列 `ORDER BY`并非全部来自用于检索行的第一个非恒定表。（这是[`EXPLAIN`](sql-statements.html#explain)输出中没有[`const`](optimization.html#jointype_const)联接类型的第一个表 。）

- 查询具有`ORDER BY`和 `GROUP BY`表达式。

- 仅在`ORDER BY`子句中命名的列的前缀上存在索引。在这种情况下，索引不能用于完全解析排序顺序。例如，如果仅[`CHAR(20)`](data-types.html#char)索引一列的前10个字节，则索引无法区分第10个字节之后的 值，`filesort`因此需要a。

- 索引不按顺序存储行。例如，对于表中的`HASH`索引来说 就是这样`MEMORY`。

排序别名的可用性可能会受到列别名的使用的影响。假设该列 `t1.a`已建立索引。在此语句中，选择列表中列的名称为 `a`。它指的是`t1.a`，如同在参考`a`在 `ORDER BY`，所以上的索引 `t1.a`可用于：

```
从t1中选择a
```

在此语句中，选择列表中列的名称也为`a`，但它是别名。它指的是`ABS(a)`，如同在参考`a`在`ORDER BY`，所以上的索引`t1.a`不能使用：

```
从a到t1的顺序中选择abs（a）作为a;
```

在下面的语句中，`ORDER BY` 引用的名称不是选择列表中列的名称。但是`t1` named中有一个列`a`，因此可以使用`ORDER BY`refer `t1.a`和on上的索引`t1.a`。（当然，最终的排序顺序可能与的排序顺序完全不同 `ABS(a)`。）

```
从t1顺序中按a选择aBS（a）AS b；
```



默认情况下，MySQL对查询进行排序，就好像您也包含在查询中一样。如果您包含一个 包含相同列列表的显式子句，则MySQL会对其进行优化，而不会造成任何速度损失，尽管排序仍然会发生。 `GROUP BY *`col1`*, *`col2`*, ...``ORDER BY *`col1`*, *`col2`*, ...``ORDER BY`

如果查询包括`GROUP BY`但希望避免对结果进行排序的开销，则可以通过指定来禁止排序`ORDER BY NULL`。例如：

```
插入foo
从栏GROUP BY ORDER BY NULL中选择COUNT（*）；
```

优化器可能仍选择使用排序来实现分组操作。`ORDER BY NULL` 禁止对结果进行排序，而不是通过分组操作确定结果的先前排序。



注意

`GROUP BY`默认情况下隐式排序（即在没有`ASC`或 列`DESC`指定`GROUP BY`符的情况下）。但是，不建议使用隐式`GROUP BY`排序（即在不存在`ASC`或 `DESC`指示符的情况下进行排序）或显式排序`GROUP BY`（即 对列使用显式`ASC`或`DESC`指示符`GROUP BY`）的依赖 。要产生给定的排序顺序，请提供一个 `ORDER BY`子句。

##### 使用文件排序满足ORDER BY



如果不能使用索引来满足`ORDER BY`子句，则MySQL执行`filesort`读取表行并对它们进行排序的 操作。A `filesort`构成查询执行中的额外排序阶段。

为了获得用于`filesort`操作的内存，优化器会预先分配固定数量的 [`sort_buffer_size`](server-administration.html#sysvar_sort_buffer_size)字节。各个会话可以根据需要更改此变量的会话值，以避免过多的内存使用，或根据需要分配更多的内存。

一个`filesort`操作使用临时磁盘文件作为必要的，如果结果集是太大，无法在内存中。某些类型的查询特别适合完全内存`filesort`操作。例如，优化器可用于 `filesort`高效地在内存中处理`ORDER BY` 以下形式的查询（和子查询）操作，而无需使用临时文件：

```
SELECT ... FROM single_table... ORDER BY non_index_column[DESC] LIMIT [ M，] N;
```

此类查询在Web应用程序中很常见，这些Web应用程序仅显示较大结果集中的几行。例子：

```
SELECT col1，... FROM t1 ... ORDER BY名称限制10;
SELECT col1，... FROM t1 ... ORDER BY RAND（）LIMIT 15;
```

##### 通过优化影响订单

对于未使用的慢`ORDER BY`查询 `filesort`，请尝试将[`max_length_for_sort_data`](server-administration.html#sysvar_max_length_for_sort_data) 系统变量降低 为适合触发的值 `filesort`。（将此变量的值设置得太高的症状是磁盘活动过多和CPU活动较低的组合。）

为了提高`ORDER BY`速度，请检查是否可以让MySQL使用索引而不是额外的排序阶段。如果这不可能，请尝试以下策略：

- 增加 [`sort_buffer_size`](server-administration.html#sysvar_sort_buffer_size) 变量值。理想情况下，该值应足够大以使整个结果集适合排序缓冲区（以避免写入磁盘和合并过程），但该值至少必须足够大以容纳15个元组。（最多合并15个临时磁盘文件，并且每个文件中至少必须有一个元组在内存中。）

  考虑到存储在排序缓冲区中的列值的大小受 [`max_sort_length`](server-administration.html#sysvar_max_sort_length)系统变量值的影响。例如，如果元组存储长字符串列的值，而您增加的值 [`max_sort_length`](server-administration.html#sysvar_max_sort_length)，则排序缓冲区元组的大小也会增加，并且可能需要您增加 [`sort_buffer_size`](server-administration.html#sysvar_sort_buffer_size)。对于由字符串表达式（例如调用字符串值函数的结果）计算出的列值，该`filesort`算法无法确定表达式值的最大长度，因此必须分配 [`max_sort_length`](server-administration.html#sysvar_max_sort_length) 每个元组的字节数。

  要监视合并次数（合并临时文件），请检查 [`Sort_merge_passes`](server-administration.html#statvar_Sort_merge_passes) 状态变量。

- 增加 [`read_rnd_buffer_size`](server-administration.html#sysvar_read_rnd_buffer_size) 变量值，以便一次读取更多行。

- 更改[`tmpdir`](server-administration.html#sysvar_tmpdir) 系统变量，使其指向具有大量可用空间的专用文件系统。变量值可以列出以循环方式使用的几个路径。您可以使用此功能将负载分散到多个目录中。`:`在Unix上，路径用冒号（）和分号（`;`）分隔。路径应命名位于不同*物理*磁盘上的文件系统中的目录 ，而不是同一磁盘上的不同分区。

##### ORDER BY执行计划信息可用

使用 [`EXPLAIN`](sql-statements.html#explain) （请参见[第8.8.1节“使用EXPLAIN优化查询”](optimization.html#using-explain)），可以检查MySQL是否可以使用索引来解析`ORDER BY`子句：

- 如果输出的`Extra`列 [`EXPLAIN`](sql-statements.html#explain)不包含`Using filesort`，则使用索引，`filesort`而不执行a。
- 如果输出`Extra`列 [`EXPLAIN`](sql-statements.html#explain)包含 `Using filesort`，则不使用索引并`filesort`执行a。

另外，如果`filesort`执行a，则优化器跟踪输出将包含一个 `filesort_summary`块。例如：

```
“ filesort_summary”：{
  “行”：100，
  “ examined_rows”：100，
  “ number_of_tmp_files”：0，
  “ sort_buffer_size”：25192，
  “ sort_mode”：“ <sort_key，打包的附加字段>”
}
```

该`sort_mode`值提供有关排序缓冲区中元组内容的信息：

- `<sort_key, rowid>`：这表明排序缓冲区元组是对，包含原始表行的排序键值和行ID。元组按排序键值排序，并且行ID用于从表中读取行。
- `<sort_key, additional_fields>`：这表明排序缓冲区元组包含排序键值和查询所引用的列。元组通过排序键值进行排序，并且列值直接从元组中读取。
- `<sort_key, packed_additional_fields>`：与以前的变体一样，但其他列紧密地打包在一起，而不是使用固定长度的编码。

[`EXPLAIN`](sql-statements.html#explain)不区分优化器是否`filesort`在内存中执行 。`filesort`在优化器跟踪输出中可以看到内存的使用 。寻找 `filesort_priority_queue_optimization`。有关优化程序跟踪的信息，请参见 [MySQL内部：跟踪优化程序](https://dev.mysql.com/doc/internals/en/optimizer-tracing.html)。

#### 8.2.1.15按优化分组



满足`GROUP BY` 子句的最通用方法是扫描整个表并创建一个新的临时表，其中每个组中的所有行都是连续的，然后使用该临时表发现组并应用聚合函数（如果有）。在某些情况下，MySQL可以做得更好，并且可以避免使用索引访问来创建临时表。

使用索引的最重要先决条件 `GROUP BY`是所有`GROUP BY`列均引用同一索引的属性，并且索引按顺序存储其键（例如，对于`BTREE`索引而言，这是正确的，但对于索引而言， 这是正确的`HASH`）。临时表的使用是否可以用索引访问代替，还取决于查询中使用索引的哪些部分，为这些部分指定的条件以及所选的聚合函数。

有两种`GROUP BY` 通过索引访问执行查询的方法，以下各节将详细介绍。第一种方法将分组操作与所有范围谓词（如果有）一起应用。第二种方法首先执行范围扫描，然后对所得的元组进行分组。

在MySQL中，`GROUP BY`用于排序，因此服务器也可以将`ORDER BY` 优化应用于分组。但是，不建议依赖隐式或显式`GROUP BY`排序。请参见[第8.2.1.14节“按优化排序”](optimization.html#order-by-optimization)。

- [松散索引扫描](optimization.html#loose-index-scan)
- [紧密索引扫描](optimization.html#tight-index-scan)

##### 松散索引扫描



最有效的处理方法`GROUP BY`是使用索引直接检索分组列。通过这种访问方法，MySQL使用键排序的某些索引类型的属性（例如`BTREE`）。使用此属性，可以在索引中使用查找组，而不必考虑索引中满足所有`WHERE`条件的所有键 。此访问方法仅考虑索引中的一部分键，因此称为“ 松散索引扫描”。如果没有`WHERE` 子句，则“松散索引扫描”将读取与组数一样多的键，这可能比所有键的数目小得多。如果`WHERE`子句包含范围谓词（请参见[第8.8.1节“使用EXPLAIN优化查询”](optimization.html#using-explain)中对[`range`](optimization.html#jointype_range)连接类型 的讨论 ），[“](optimization.html#using-explain)松散索引扫描”将查找满足范围条件的每个组的第一个键，并再次读取最小的可能值按键数。在以下情况下可以这样做：

- 查询是在单个表上。
- 该`GROUP BY`唯一名称是构成该指数并没有其他列的最左边的前缀列。（如果`GROUP BY`查询具有`DISTINCT`子句，而不是查询，则所有不同的属性均引用构成索引最左前缀的列。）例如，如果表 `t1`的索引位于 `(c1,c2,c3)`，则松散索引扫描适用于查询`GROUP BY c1, c2`。如果查询具有`GROUP BY c2, c3`（列不是最左边的前缀）或`GROUP BY c1, c2, c4`（`c4`不在索引中），则不适用 。
- 选择列表中使用的唯一聚合函数（如果有）是[`MIN()`](functions.html#function_min)和 [`MAX()`](functions.html#function_max)，并且它们全部引用同一列。该列必须在索引中，并且必须紧跟在中的列之后 `GROUP BY`。
- 索引中除`GROUP BY`查询中所引用部分以外的任何其他部分都 必须是常量（即必须与常量相等地引用），除了[`MIN()`](functions.html#function_min)或 [`MAX()`](functions.html#function_max)函数的参数外 。
- 对于索引中的列，必须为完整的列值建立索引，而不仅仅是前缀。例如，使用 `c1 VARCHAR(20), INDEX (c1(10))`，索引仅使用`c1`值的前缀，而不能用于宽松索引扫描。

如果“松散索引扫描”适用于查询，则 [`EXPLAIN`](sql-statements.html#explain)输出将显示 `Using index for group-by`在该 `Extra`列中。

假设`idx(c1,c2,c3)`table上 有一个索引 `t1(c1,c2,c3,c4)`。松散索引扫描访问方法可用于以下查询：

```
从t1 GROUP BY c1，c2中选择c1，c2;
SELECT DISTINCT c1，c2 from t1;
从t1 GROUP BY c1中选择c1，MIN（c2）;
从t1处选择c1，c2，其中c1 < constGROUP BY c1，c2;
从t1的SELECT MAX（c3），MIN（c3），c1，c2那里c2> constGROUP BY c1，c2;
从t1中选择c2，其中c1 < constGROUP BY c1，c2;
从t1处选择c1，c2，其中c3 = constGROUP BY c1，c2;
```

由于给出的原因，无法使用此快速选择方法执行以下查询：

- 除[`MIN()`](functions.html#function_min)或 之外，还有其他聚合函数 [`MAX()`](functions.html#function_max)：

  ```
  从t1 GROUP BY c1中选择c1，SUM（c2）;
  ```

- `GROUP BY`子句中 的列不构成索引的最左前缀：

  ```
  从t1 GROUP BY c2，c3中选择c1，c2;
  ```

- 该查询指的是键的`GROUP BY`一部分，该部分位于该部分之后，并且该 部分与常量不相等：

  ```
  从t1 GROUP BY c1，c2中选择c1，c3;
  ```

  如果查询包含，则可以使用松散索引扫描。 `WHERE c3 = *`const`*`

除了已经支持的[`MIN()`](functions.html#function_min)和 [`MAX()`](functions.html#function_max)引用之外，松散索引扫描访问方法还可以应用于选择列表中的其他形式的聚合函数引用：

- [`AVG(DISTINCT)`](functions.html#function_avg)， [`SUM(DISTINCT)`](functions.html#function_sum)和 [`COUNT(DISTINCT)`](functions.html#function_count)支持。[`AVG(DISTINCT)`](functions.html#function_avg) 并[`SUM(DISTINCT)`](functions.html#function_sum)接受一个论点。 [`COUNT(DISTINCT)`](functions.html#function_count)可以有多个列参数。
- 查询中 不能有`GROUP BY`or `DISTINCT`子句。
- 先前描述的松散索引扫描限制仍然适用。

假设`idx(c1,c2,c3)`table上 有一个索引 `t1(c1,c2,c3,c4)`。松散索引扫描访问方法可用于以下查询：

```
从t1中选择COUNT（DISTINCT c1），SUM（DISTINCT c1）;

从t1选择COUNT（DISTINCT c1，c2），COUNT（DISTINCT c2，c1）;
```

##### 紧密索引扫描

紧密索引扫描可以是全索引扫描，也可以是范围索引扫描，具体取决于查询条件。

当不满足宽松索引扫描的条件时，仍然有可能避免创建用于`GROUP BY`查询的临时表。如果`WHERE`子句中有范围条件，则此方法仅读取满足这些条件的键。否则，它将执行索引扫描。由于此方法读取该`WHERE`子句定义的每个范围内的所有键 ，或者在没有范围条件的情况下扫描整个索引，因此称为“ 紧索引扫描”。对于紧密索引扫描，仅在找到所有满足范围条件的键之后才执行分组操作。

为了使这种方法起作用，对于查询中所有引用键部分之前或之间的`GROUP BY`键的部分，要有一个恒定的相等条件就足够了。来自相等条件的常量会填充 搜索键中的所有“ 间隙 ”，从而可以形成索引的完整前缀。这些索引前缀然后可以用于索引查找。如果`GROUP BY` 结果需要排序，并且有可能形成作为索引前缀的搜索关键字，MySQL还避免了额外的排序操作，因为在有序索引中使用前缀搜索已经按顺序检索了所有关键字。

假设`idx(c1,c2,c3)`table上 有一个索引 `t1(c1,c2,c3,c4)`。以下查询不适用于前面所述的“松散索引扫描”访问方法，但仍适用于“紧索引扫描”访问方法。

- 中存在一个缺口`GROUP BY`，但由以下条件覆盖`c2 = 'a'`：

  ```
  从t1那里选择c1，c2，c3 c2 ='a'GROUP BY c1，c3;
  ```

- 的`GROUP BY`开头不是键的第一部分，但是有一个条件为该部分提供常数：

  ```
  从t1处选择c1，c2，c3 c1 ='a'GROUP BY c2，c3;
  ```

#### 8.2.1.16 DISTINCT优化



`DISTINCT``ORDER BY`在许多情况下，结合需要一个临时表。

因为`DISTINCT`可能使用`GROUP BY`，所以了解MySQL如何处理 不属于所选列的`ORDER BY`or `HAVING`子句中的列。请参见 [第12.20.3节“ MySQL BY GROUP BY”](functions.html#group-by-handling)。

在大多数情况下，`DISTINCT`子句可以视为的特殊情况`GROUP BY`。例如，以下两个查询是等效的：

```
从t1中选择DISTINCT c1，c2，c3
在哪里c1> const;

从t1中选择c1，c2，c3
c1> constGROUP BY c1，c2，c3;
```

由于这种等效性，适用于`GROUP BY`查询的优化 也可以应用于带有`DISTINCT`子句的查询。因此，有关`DISTINCT`查询优化可能性的更多详细信息 ，请参见 [第8.2.1.15节“按优化组”](optimization.html#group-by-optimization)。

与 结合使用时 ，MySQL一旦找到唯一的行，就会停止运行 。 `LIMIT *`row_count`*``DISTINCT`*`row_count`*

如果您不使用查询中命名的所有表中的列，则MySQL会在找到第一个匹配项后立即停止扫描任何未使用的表。在以下情况下，假设 `t1`以前使用过`t2` （您可以通过进行检查 [`EXPLAIN`](sql-statements.html#explain)），则MySQL `t2`在`t1`找到以下内容的第一行时将停止从（对于中的任何特定行） 进行 读取`t2`：

```
从t1，t2中选择DISTINCT t1.a，其中t1.a = t2.a;
```

#### 8.2.1.17 LIMIT查询优化



如果从结果集中只需要指定数量的行，请`LIMIT`在查询中使用子句，而不是获取整个结果集并丢弃多余的数据。

MySQL有时会优化具有子句和无 子句的查询： `LIMIT *`row_count`*``HAVING`

- 如果仅使用来选择几行 `LIMIT`，则在通常情况下，MySQL倾向于使用全表扫描，因此有时会使用索引。

- 如果与结合使用 ，MySQL会在找到排序结果的第一行后立即停止排序 ，而不是对整个结果进行排序。如果通过使用索引进行排序，这将非常快。如果必须执行文件排序，则在找到第一个之前，将选择所有与查询匹配的不带子句的行，并对其中的大多数或全部进行排序 。找到初始行后，MySQL不会对结果集的其余部分进行排序。 `LIMIT *`row_count`*``ORDER BY`*`row_count`*`LIMIT`*`row_count`*

  此行为的一种体现是，`ORDER BY`带有和不带有 查询的查询 `LIMIT`可能以不同顺序返回行，如本节后面所述。

- 如果与结合使用，MySQL 将在 找到唯一行后立即停止。 `LIMIT *`row_count`*``DISTINCT`*`row_count`*

- 在某些情况下，`GROUP BY`可以通过按顺序读取索引（或对索引进行排序），然后计算汇总直到索引值更改来解决a。在这种情况下，不计算任何不必要的 值。 `LIMIT *`row_count`*``GROUP BY`

- MySQL一旦向客户端发送了所需的行数，它将立即终止查询，除非您正在使用 `SQL_CALC_FOUND_ROWS`。在这种情况下，可以使用检索行数`SELECT FOUND_ROWS()`。请参见 [第12.15节“信息功能”](functions.html#information-functions)。

  

- `LIMIT 0`快速返回一个空集。这对于检查查询的有效性很有用。它也可用于获取使用MySQL API的应用程序中结果列元数据的类型的结果列的类型。在 [**mysql**](programs.html#mysql)客户端程序中，您可以使用该 [`--column-type-info`](programs.html#option_mysql_column-type-info)选项显示结果列类型。

- 如果服务器使用临时表来解析查询，则它将使用该子句来计算所需的空间。 `LIMIT *`row_count`*`

- 如果未使用索引，`ORDER BY` 但`LIMIT`也存在子句，则优化器可以避免使用合并文件，并使用内存中`filesort`操作对内存中的行进行排序 。

如果多行在列中具有相同的值`ORDER BY`，则服务器可以自由以任何顺序返回这些行，并且根据整体执行计划，这样做的方式可能有所不同。换句话说，这些行的排序顺序相对于无序列是不确定的。

影响执行计划的一个因素是 `LIMIT`，因此`ORDER BY` 带有和不带有查询的查询`LIMIT`可能以不同顺序返回行。考虑以下查询，该查询按`category`列排序，但对于`id`和 `rating`列不确定：

```
mysql> SELECT * FROM ratings ORDER BY category;
+ ---- + ---------- + -------- +
| id | 类别| 评级|
+ ---- + ---------- + -------- +
| 1 | 1 | 4.5 |
| 5 | 1 | 3.2 |
| 3 | 2 | 3.7 |
| 4 | 2 | 3.5 |
| 6 | 2 | 3.5 |
| 2 | 3 | 5.0 |
| 7 | 3 | 2.7 |
+ ---- + ---------- + -------- +
```

包含`LIMIT`可能会影响每个`category`值中的行顺序。例如，这是一个有效的查询结果：

```
mysql> SELECT * FROM ratings ORDER BY category LIMIT 5;
+ ---- + ---------- + -------- +
| id | 类别| 评级|
+ ---- + ---------- + -------- +
| 1 | 1 | 4.5 |
| 5 | 1 | 3.2 |
| 4 | 2 | 3.5 |
| 3 | 2 | 3.7 |
| 6 | 2 | 3.5 |
+ ---- + ---------- + -------- +
```

在每种情况下，行均按`ORDER BY`列排序，这是SQL标准所需的全部。

如果重要的是要确保带和不带相同的行顺序，请`LIMIT`在`ORDER BY`子句中包括其他列以使顺序确定。例如，如果`id`值是唯一的，则可以通过如下排序使给定`category`值的行 按`id`顺序显示 ：

```
mysql> SELECT * FROM ratings ORDER BY category, id;
+ ---- + ---------- + -------- +
| id | 类别| 评级|
+ ---- + ---------- + -------- +
| 1 | 1 | 4.5 |
| 5 | 1 | 3.2 |
| 3 | 2 | 3.7 |
| 4 | 2 | 3.5 |
| 6 | 2 | 3.5 |
| 2 | 3 | 5.0 |
| 7 | 3 | 2.7 |
+ ---- + ---------- + -------- +

mysql> SELECT * FROM ratings ORDER BY category, id LIMIT 5;
+ ---- + ---------- + -------- +
| id | 类别| 评级|
+ ---- + ---------- + -------- +
| 1 | 1 | 4.5 |
| 5 | 1 | 3.2 |
| 3 | 2 | 3.7 |
| 4 | 2 | 3.5 |
| 6 | 2 | 3.5 |
+ ---- + ---------- + -------- +
```

#### 8.2.1.18函数调用优化



MySQL函数在内部被标记为确定性或不确定性。如果给定参数固定值的函数可以为不同的调用返回不同的结果，则它是不确定的。不确定函数的示例： [`RAND()`](functions.html#function_rand)， [`UUID()`](functions.html#function_uuid)。

如果某个函数被标记为不确定的，则将`WHERE`针对每一行（从一个表中选择时）或行的组合（从多表联接中选择时）评估子句中对该函数的引用。

MySQL还根据参数的类型（参数是表列还是常量值）确定何时评估函数。每当表列更改值时，都必须评估将表列作为参数的确定性函数。

非确定性函数可能会影响查询性能。例如，某些优化可能不可用，或者可能需要更多锁定。以下讨论使用 [`RAND()`](functions.html#function_rand)但也适用于其他不确定性函数。

假设一个表`t`具有以下定义：

```
创建表t（id INT NOT NULL PRIMARY KEY，col_a VARCHAR（100））;
```

考虑以下两个查询：

```
选择*从t其中id = POW（1,2）;
选择*从t其中id = FLOOR（1 + RAND（）* 49）;
```

由于与主键的相等性比较，两个查询似乎都使用了主键查找，但这仅适用于第一个查询：

- 第一个查询始终最多产生一行，因为[`POW()`](functions.html#function_pow)带有常量参数的常量是一个常量值，并用于索引查找。
- 第二个查询包含一个使用非确定性函数的表达式，该表达式 [`RAND()`](functions.html#function_rand)在查询中不是常数，但实际上对于表的每一行都有一个新值`t`。因此，查询读取表的每一行，评估每一行的谓词，并输出主键与随机值匹配的所有行。根据`id`列值和[`RAND()`](functions.html#function_rand)序列中的值， 它可以是零行，一行或多行 。

非确定性的影响不仅限于 [`SELECT`](sql-statements.html#select)陈述。该 [`UPDATE`](sql-statements.html#update)语句使用非确定性函数来选择要修改的行：

```
更新t SET col_a = some_exprWHERE id = FLOOR（1 + RAND（）* 49）;
```

大概的目的是最多更新主键与表达式匹配的一行。但是，它可能会更新零，一或多个行，具体取决于 `id`列值和[`RAND()`](functions.html#function_rand)序列中的值 。

刚刚描述的行为对性能和复制有影响：

- 由于不确定函数不会产生恒定值，因此优化器无法使用其他可能适用的策略，例如索引查找。结果可能是表扫描。
- `InnoDB` 可能升级为范围键锁，而不是为一个匹配的行获取单行锁。
- 无法确定执行的更新对于复制是不安全的。

困难源于[`RAND()`](functions.html#function_rand)对表的每一行都对函数进行一次评估的事实 。为了避免进行多功能评估，请使用以下技术之一：

- 将包含不确定性函数的表达式移到单独的语句，将值保存在变量中。在原始语句中，用对变量的引用替换表达式，优化器可以将该变量视为常量值：

  ```
  SET @keyval = FLOOR（1 + RAND（）* 49）;
  更新t SET col_a = some_exprWHERE id = @keyval;
  ```

- 将随机值分配给派生表中的变量。此技术使变量在`WHERE`子句中的比较中使用之前被分配一个值 ：

  ```
  SET Optimizer_switch ='derived_merge = off';
  更新t，（SELECT @keyval：= FLOOR（1 + RAND（）* 49））AS dt
  SET col_a = some_exprWHERE id = @keyval;
  ```

如前所述，`WHERE`子句中的不确定性表达式 可能会阻止优化并导致表扫描。但是，`WHERE`如果其他表达式是确定性的，则可以部分优化该子句。例如：

```
SELECT * FROM t WHERE part_key = 5 AND some_column = RAND（）;
```

如果优化器可以`partial_key`用来减少选择的行的集合， [`RAND()`](functions.html#function_rand)则执行的次数更少，这可以减少不确定性对优化的影响。

#### 8.2.1.19行构造器表达式优化



行构造函数允许同时比较多个值。例如，以下两个语句在语义上是等效的：

```
选择*从t1位置（第1列，第2列）=（1,1）;
SELECT * FROM t1 WHERE column1 = 1 AND column2 = 1;
```

另外，优化器以相同的方式处理两个表达式。

如果行构造器的列不覆盖索引的前缀，则优化器不太可能使用可用索引。考虑下表，该表具有一个主键 `(c1, c2, c3)`：

```
创建表t1（
  c1 INT，c2 INT，c3 INT，c4 CHAR（100），
  主键（c1，c2，c3）
）;
```

在此查询中，该`WHERE`子句使用索引中的所有列。但是，行构造器本身不包含索引前缀，因此优化器仅使用`c1`（`key_len=4`，大小为`c1`）：

```
mysql> EXPLAIN SELECT * FROM t1
       WHERE c1=1 AND (c2,c3) > (1,1)\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表：t1
   分区：NULL
         类型：参考
可能的键：PRIMARY
          关键：主要
      key_len：4
          参考：const
         行数：3
     已过滤：100.00
        额外：在哪里使用
```

在这种情况下，使用等效的非构造函数表达式重写行构造函数表达式可能会导致更完整的索引使用。对于给定的查询，行构造函数和等效的非构造函数表达式为：

```
（c2，c3）>（1,1）
c2> 1或（（c2 = 1）AND（c3> 1））
```

使用索引（`key_len=12`）中的所有三列重写查询以使用非构造函数表达式在优化器中导致结果：

```
mysql> EXPLAIN SELECT * FROM t1
       WHERE c1 = 1 AND (c2 > 1 OR ((c2 = 1) AND (c3 > 1)))\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表：t1
   分区：NULL
         类型：范围
可能的键：PRIMARY
          关键：主要
      key_len：12
          参考：NULL
         行数：3
     已过滤：100.00
        额外：在哪里使用
```

因此，为获得更好的结果，请避免将行构造函数与[`AND`](functions.html#operator_and)/ [`OR`](functions.html#operator_or) 表达式混合使用 。使用一个或另一个。

在某些条件下，优化器可以将范围访问方法应用于[`IN()`](functions.html#operator_in)具有行构造函数参数的表达式。请参见 [行构造函数表达式的范围优化](optimization.html#row-constructor-range-optimization)。

#### 8.2.1.20避免全表扫描



当MySQL使用[全表扫描](glossary.html#glos_full_table_scan)解决查询时， 列中 的输出[`EXPLAIN`](sql-statements.html#explain)显示 。这通常在以下情况下发生： [`ALL`](optimization.html#jointype_all)`type`

- 该表是如此之小，以至于执行表扫描要比打扰键查找要快得多。对于少于10行且行长较短的表，这很常见。
- 索引列 的`ON`or `WHERE`子句中没有可用的限制 。
- 您正在将索引列与常量值进行比较，并且MySQL已计算（基于索引树）（常量覆盖了表的很大一部分）并且表扫描会更快。请参见 [第8.2.1.1节“ WHERE子句优化”](optimization.html#where-optimization)。
- 您正在通过另一列使用基数较低的键（许多行与键值匹配）。在这种情况下，MySQL假定通过使用键，它可能会执行许多键查找，并且表扫描会更快。

对于小型表，表扫描通常是合适的，并且对性能的影响可以忽略不计。对于大表，请尝试以下技术，以避免优化器错误地选择表扫描：

- 使用更新的扫描表的键分布。请参见 [第13.7.2.1节“ ANALYZE TABLE语句”](sql-statements.html#analyze-table)。 `ANALYZE TABLE *`tbl_name`*`

- 使用`FORCE INDEX`的扫描表告诉MySQL该表扫描是非常昂贵相比，使用给定的指标：

  ```
  SELECT * FROM t1，t2 FORCE INDEX（index_for_column）
    在t1。col_name= t2。col_name;
  ```

  请参见[第8.9.4节“索引提示”](optimization.html#index-hints)。

- 使用该 选项 启动[**mysqld**](programs.html#mysqld)[`--max-seeks-for-key=1000`](server-administration.html#sysvar_max_seeks_for_key)或使用`SET max_seeks_for_key=1000`告诉优化器假定没有键扫描导致超过1000个键查找。请参见[第5.1.7节“服务器系统变量”](server-administration.html#server-system-variables)。

### 8.2.2优化子查询，派生表和视图引用

- [8.2.2.1使用半联接转换优化子查询，派生表和视图引用](optimization.html#semijoins)
- [8.2.2.2通过实现来优化子查询](optimization.html#subquery-materialization)
- [8.2.2.3使用EXISTS策略优化子查询](optimization.html#subquery-optimization-with-exists)
- [8.2.2.4通过合并或实现来优化派生表和视图引用](optimization.html#derived-table-optimization)



MySQL查询优化器具有可用于评估子查询的不同策略：

- 对于`IN`（或`=ANY`）子查询，优化器具有以下选择：
  - 半连接
  - 物化
  - `EXISTS` 战略
- 对于`NOT IN`（或 `<>ALL`）子查询，优化器具有以下选择：
  - 物化
  - `EXISTS` 战略

对于派生表，优化器具有以下选择（这也适用于视图引用）：

- 将派生表合并到外部查询块中
- 将派生表具体化为内部临时表

以下讨论提供了有关前面的优化策略的更多信息。

注意

使用子查询修改单个表的[`UPDATE`](sql-statements.html#update)和 [`DELETE`](sql-statements.html#delete)语句 的限制是，优化器不使用半联接或实现子查询优化。解决方法是，尝试将它们重写为使用联接而不是子查询的多表 [`UPDATE`](sql-statements.html#update)和 [`DELETE`](sql-statements.html#delete)语句。

#### 8.2.2.1使用半联接转换优化子查询，派生表和视图引用



半联接是准备时间的转换，它启用多种执行策略，例如表提取，重复删除，首次匹配，松散扫描和实现。如本节所述，优化器使用半联接策略来改善子查询的执行。

对于两个表之间的内部联接，该联接从一个表返回一行的次数是另一表中存在匹配项的次数。但是对于某些问题，唯一重要的信息是是否存在匹配项，而不是匹配数。假设在课程表和班级花名册（每个班级中都有学生）中分别有命名的表 `class`和`roster`列出的班级。要列出实际招收学生的课程，您可以使用以下联接：

```
选择class.class_num，class.class_name
从班级INNER JOIN名册
其中class.class_num = roster.class_num;
```

但是，结果为每个注册学生列出一次每个班级。对于所提出的问题，这是不必要的信息重复。

假设它`class_num`是`class`表中的主键，则可以通过使用来抑制重复 [`SELECT DISTINCT`](sql-statements.html#select)，但是首先生成所有匹配的行效率很低，只能稍后消除重复。

可以使用子查询获得相同的无重复结果：

```
SELECT class_num，class_name
从类
在哪里class_num IN（从花名册中选择class_num）;
```

在这里，优化器可以识别出该 `IN`子句要求子查询仅返回`roster`表中每个类编号的一个实例 。在这种情况下，查询可以使用半联接；也就是说，该操作仅返回的每一行的一个实例，该实例 `class`与的行匹配 `roster`。

外部查询规范中允许使用外部联接和内部联接语法，并且表引用可以是基表，派生表或视图引用。

在MySQL中，子查询必须满足以下条件才能作为半联接处理：

- 它必须是出现在or 子句最顶层的`IN`（或 `=ANY`）子查询，可能是表达式中的一个 词。例如： `WHERE``ON`[`AND`](functions.html#operator_and)

  ```
  选择 ...
  从ot1，...
  WHERE（oe1，...）IN（SELECT ie1，... FROM it1，... WHERE ...）;
  ```

  在这里， 和 代表查询的外部和内部部分中的表，并且 和 表示引用外部和内部表中的列的表达式。 `ot_*`i`*``it_*`i`*``oe_*`i`*``ie_*`i`*`

- 它必须是[`SELECT`](sql-statements.html#select) 没有[`UNION`](sql-statements.html#union)构造的单个。

- 它不能包含`GROUP BY`or `HAVING`子句。

- 不能将其隐式分组（不能包含任何聚合函数）。

- 它一定不能`ORDER BY`与 `LIMIT`。

- 该语句不得`STRAIGHT_JOIN`在外部查询中使用 联接类型。

  

- 该`STRAIGHT_JOIN`修改必须不存在。

  

- 外部表和内部表的总数必须小于联接中允许的最大表数。

子查询可以是相关的或不相关的。 `DISTINCT`允许使用， `LIMIT`除非`ORDER BY`同样使用。

如果子查询满足上述条件，MySQL会将其转换为半联接，并从以下策略中进行基于成本的选择：

- 将子查询转换为联接，或使用表提取，并将查询作为子查询表与外部表之间的内部联接运行。表提取将表从子查询中拉出到外部查询。

  

- 重复删除：像运行连接一样运行半连接，并使用临时表删除重复记录。

  

- FirstMatch：当扫描内部表中的行组合并且给定值组有多个实例时，请选择一个而不是全部返回。这种“快捷方式”扫描可以消除不必要行的产生。

  

- LooseScan：使用索引扫描子查询表，该索引允许从每个子查询的值组中选择一个值。

  

- 将子查询具体化到用于执行联接的索引临时表中，在该临时表中，索引用于删除重复项。当将临时表与外部表连接时，该索引以后也可能用于查找。如果不是，则扫描表。有关实现的更多信息，请参见 [第8.2.2.2节“通过实现来优化子查询”](optimization.html#subquery-materialization)。

  

可以使用以下[`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) 系统变量标志来启用或禁用这些策略中的每一个：

- 该`semijoin`标志控制是否使用半联接。
- 如果`semijoin`使能， `firstmatch`， `loosescan`， `duplicateweedout`，和 `materialization`标志enable更好地控制在允许的半连接策略。
- 如果`duplicateweedout`禁用了半连接策略，则除非所有其他适用的策略也都被禁用，否则将不使用它。
- 如果`duplicateweedout`已禁用，则有时优化器可能会生成一个远非最佳的查询计划。发生这种情况的原因是贪婪搜索期间的启发式修剪，可以通过设置来避免 [`optimizer_prune_level=0`](server-administration.html#sysvar_optimizer_prune_level)。

默认情况下启用这些标志。请参见 [第8.9.2节“可切换的优化”](optimization.html#switchable-optimizations)。

优化器将视图和派生表的处理差异最小化。这会影响使用 `STRAIGHT_JOIN`修饰符的`IN`查询以及带有可转换为半联接的子查询的视图 。以下查询说明了这一点，因为处理中的更改导致转换中的更改，从而导致了不同的执行策略：

```
创建视图v AS
选择 *
从t1开始
在IN（选择b
           从t2开始）;

SELECT STRAIGHT_JOIN *
从t3 JOIN v ON t3.x = va;
```

优化器首先查看视图，然后将 `IN`子查询转换为半联接，然后检查是否有可能将视图合并到外部查询中。因为`STRAIGHT_JOIN`外部查询中的修饰符防止半联接，所以优化器拒绝合并，从而导致使用物化表进行派生表评估。

[`EXPLAIN`](sql-statements.html#explain) 输出表明使用了半连接策略，如下所示：

- 半联接表显示在外部选择中。对于扩展[`EXPLAIN`](sql-statements.html#explain)输出，以下内容显示的文本显示 [`SHOW WARNINGS`](sql-statements.html#show-warnings)了重写的查询，该查询显示了半联接结构。（请参见[第8.8.3节“扩展的EXPLAIN输出格式”](optimization.html#explain-extended)。）由此，您可以了解哪些表从半联接中被拉出。如果将子查询转换为半联接，则将看到该子查询谓词已消失，并且其表和`WHERE`子句已合并到外部查询联接列表和 `WHERE`子句中。
- 对于重复Weedout临时表的使用是由指示 `Start temporary`和`End temporary`在`Extra` 列。那些没有拉出表是在范围内[`EXPLAIN`](sql-statements.html#explain)所涵盖的输出行`Start temporary`，并 `End temporary`有自己 `rowid`的临时表。
- `FirstMatch(*`tbl_name`*)` 在`Extra`列表示加入shortcutting。
- `LooseScan(*`m`*..*`n`*)` 在`Extra`列指示使用LooseScan策略。*`m`*和 *`n`*是关键零件号。
- 用于实现的临时表由`select_type`值为的 `MATERIALIZED`行和的`table`值为的 行 指示。 `<subquery*`N`*>`

#### 8.2.2.2通过实现来优化子查询



优化器使用实现来启用更有效的子查询处理。物化通过生成子查询结果作为临时表（通常在内存中）来加快查询的执行速度。MySQL第一次需要子查询结果时，会将结果具体化为临时表。任何随后的需要结果的时间，MySQL都会再次引用临时表。优化器可以使用哈希索引为表建立索引，以使查找快速，廉价。索引包含唯一值，以消除重复项并使表更小。

子查询实现在可能的情况下使用内存中的临时表，如果表太大，则会退回到磁盘上的存储。请参见 [第8.4.4节“ MySQL中的内部临时表使用”](optimization.html#internal-temporary-tables)。

如果未使用实现，则优化器有时会将不相关的子查询重写为相关的子查询。例如，以下`IN`子查询是不相关的（*`where_condition`* 仅涉及from `t2`和not中的 列`t1`）：

```
选择*从t1
t1.a IN（从t2 WHERE中选择t2.b where_condition）;
```

优化器可能将此重写为 `EXISTS`相关子查询：

```
选择*从t1
存在的地方（从t2位置where_condition和t1.a = t2.b中选择t2.b）；
```

使用临时表的子查询实现避免了这样的重写，并使得只可能执行一次子查询，而不是对外部查询的每一行执行一次。

为了使子查询实现在MySQL中使用， 必须启用[`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)系统变量`materialization`标志。（见[第8.9.2节，“切换优化”](optimization.html#switchable-optimizations)）。随着`materialization`启用的标志，物化适用于任何地方出现子查询谓词（在选择列表中，`WHERE`， `ON`，`GROUP BY`， `HAVING`，或`ORDER BY`），对于属于任何这些用例谓词：

- 当没有外部表达式*`oe_i`*或内部表达式 *`ie_i`*可为空时，谓词具有这种形式 。 *`N`*为1或更大。

  ```
  （oe_1，oe_2...，oe_N）[NOT] IN（SELECT ie_1，i_2，...，ie_N...）
  ```

- 当存在单个外部表达式*`oe`*和内部表达式时，谓词具有这种形式*`ie`*。表达式可以为空。

  ```
  oe[NOT] IN（选择ie...）
  ```

- 谓词为`IN`或，`NOT IN`并且`UNKNOWN` （`NULL`）的结果与的结果具有相同的含义`FALSE`。

以下示例说明了等价`UNKNOWN`和 `FALSE`谓词求值的要求如何影响是否可以使用子查询实现。假定 *`where_condition`*只涉及来自`t2`而不涉及的列`t1` ，以使子查询不相关。

此查询需要具体实现：

```
选择*从t1
t1.a IN（从t2 WHERE中选择t2.b where_condition）;
```

在此，`IN` 谓词返回`UNKNOWN`还是 都没有关系`FALSE`。无论哪种方式，from的行 `t1`都不包含在查询结果中。

以下查询是不使用子查询实现的示例，其中`t2.b`的列为可空：

```
选择*从t1
（t1.a，t1.b）不在的位置（从t2选择t2.a，t2.b
                          在哪里where_condition）;
```

以下限制适用于子查询实现的使用：

- 内部和外部表达式的类型必须匹配。例如，如果两个表达式都是整数或两个都是十进制，那么优化器可能可以使用实现，但是如果一个表达式是整数而另一个表达式是十进制，则优化器不能使用实现。
- 内部表达式不能是 [`BLOB`](data-types.html#blob)。

[`EXPLAIN`](sql-statements.html#explain)与查询一起 使用可提供有关优化器是否使用子查询实现的某种指示：

- 与不使用实现的查询执行相比，`select_type`可以从更改`DEPENDENT SUBQUERY`为 `SUBQUERY`。这表明，对于将对每个外行执行一次的子查询，实现将使子查询仅执行一次。
- 对于扩展[`EXPLAIN`](sql-statements.html#explain) 输出，以下内容显示的文本 [`SHOW WARNINGS`](sql-statements.html#show-warnings)包括 `materialize`和 `materialized-subquery`。

#### 8.2.2.3使用EXISTS策略优化子查询



某些优化适用于使用`IN`（或`=ANY`）运算符测试子查询结果的比较。本节讨论这些优化，尤其是关于`NULL`价值所面临的挑战。讨论的最后部分提出了如何帮助优化器的建议。

考虑以下子查询比较：

```
outer_exprIN（inner_expr从...处选择subquery_where）
```

MySQL的评估查询“ 从外到内。” 即，首先获得外表达式的值 *`outer_expr`*，然后运行子查询，并且捕获的行，它产生。

一个非常有用的优化是“ 通知 ”子查询仅感兴趣的行是内部表达式*`inner_expr`*等于的行*`outer_expr`*。这是通过将适当的等式推入子查询的`WHERE`子句以使其更具限制性来完成的。转换后的比较如下所示：

```
存在（从...位置subquery_whereAND outer_expr=中选择1 inner_expr）
```

转换后，MySQL可以使用下推式相等性来限制评估子查询必须检查的行数。

更一般而言，将*`N`* 值与返回*`N`*-value行的子查询 进行比较将进行相同的转换。如果*`oe_i`*和 *`ie_i`*代表相应的外部和内部表达式值，则此子查询比较：

```
（oe_1，...，oe_N）IN
  （选择ie_1，...，ie_N从...位置subquery_where）
```

成为：

```
存在（从...位置subquery_where
                          和oe_1=中选择1ie_1
                          与...
                          AND oe_N= ie_N）
```

为简单起见，下面的讨论假定使用一对外部表达式值和内部表达式值。

刚刚描述的转换有其局限性。仅当我们忽略可能的`NULL`值时才有效。也就是说，只要满足以下两个条件，“ 下推 ”策略就可以起作用：

- *`outer_expr`*而且 *`inner_expr`*不能 `NULL`。

- 你不必区分`NULL`从 `FALSE`子查询结果。如果子查询是子句中[`OR`](functions.html#operator_or)or [`AND`](functions.html#operator_and)表达式 的一部分`WHERE`，则MySQL认为您不在乎。在优化程序注意到另一个实例`NULL`和`FALSE` 子查询结果不需要区分是这样的结构：

  ```
  ... outer_expr在（subquery）中
  ```

  在这种情况下，该`WHERE`子句拒绝该行，无论是return 还是。 `IN (*`subquery`*)``NULL``FALSE`

当这些条件中的任何一个或两个都不满足时，优化会更加复杂。

假设*`outer_expr`*已知这是一个非`NULL`值，但子查询不会产生诸如*`outer_expr`*= 的行 *`inner_expr`*。然后 `*`outer_expr`* IN (SELECT ...)`评估如下：

- `NULL`中，如果 [`SELECT`](sql-statements.html#select)产生任何行，其中*`inner_expr`*是 `NULL`
- `FALSE`，如果 [`SELECT`](sql-statements.html#select)仅产生非`NULL`值或什么都不产生

在这种情况下，使用查找行的方法 不再有效。有必要查找这样的行，但是如果没有找到，则还要在is中 查找行 。粗略地说，子查询可以转换为如下形式： `*`outer_expr`* = *`inner_expr`*`*`inner_expr`*`NULL`

```
存在（从...位置subquery_where和中选择1
        （outer_expr= inner_exprOR inner_exprIS NULL））
```

需要评估额外[`IS NULL`](functions.html#operator_is-null)条件是MySQL具有 [`ref_or_null`](optimization.html#jointype_ref_or_null)访问方法的原因：

```
mysql> EXPLAIN
       SELECT outer_expr IN (SELECT t2.maybe_null_key
                             FROM t2, t3 WHERE ...)
       FROM t1;
*************************** 1.行******************** *******
           编号：1
  select_type：主要
        表：t1
...
*************************** 2.行******************** *******
           id：2
  select_type：DEPENDENT子查询
        表：t2
         类型：ref_or_null
Possible_Keys：maybe_null_key
          键：mayly_null_key
      key_len：5
          参考：func
         行数：2
        额外：在哪里使用；使用索引
...
```

在[`unique_subquery`](optimization.html#jointype_unique_subquery)和 [`index_subquery`](optimization.html#jointype_index_subquery) 子查询，具体的访问方法也有“ 或 `NULL` ”变种。

附加`OR ... IS NULL`条件使查询执行稍微复杂一些（并且子查询中的某些优化变得不适用），但是通常这是可以容忍的。

当*`outer_expr`*可以的 时候情况要糟得多 `NULL`。根据SQL解释`NULL`为“ 未知值 ”， 应评估为： `NULL IN (SELECT *`inner_expr`* ...)`

- `NULL`，如果 [`SELECT`](sql-statements.html#select)产生任何行
- `FALSE`，如果不 [`SELECT`](sql-statements.html#select)产生任何行

为了进行适当的评估，必须能够检查是否[`SELECT`](sql-statements.html#select)已产生任何行，因此 不能将其下推到子查询中。这是一个问题，因为许多现实世界中的子查询会变得非常缓慢，除非可以降低相等性。 `*`outer_expr`* = *`inner_expr`*`

本质上，取决于的值，必须有不同的方法来执行子查询 *`outer_expr`*。

优化选择超速SQL合规性，所以它占的可能性 *`outer_expr`*可能是 `NULL`：

- 如果*`outer_expr`*为 `NULL`，则要评估以下表达式，必须执行 [`SELECT`](sql-statements.html#select)以确定它是否产生任何行：

  ```
  NULL IN（inner_expr从...中选择subquery_where）
  ```

  必须在[`SELECT`](sql-statements.html#select)这里执行原始文件 ，而没有前面提到的那种下推式等价物。

- 另一方面，当*`outer_expr`*不是 时 `NULL`，此比较绝对必要：

  ```
  outer_exprIN（inner_expr从...处选择subquery_where）
  ```

  转换为使用下推条件的表达式：

  ```
  存在（从...位置subquery_whereAND outer_expr=中选择1 inner_expr）
  ```

  没有这种转换，子查询将很慢。

为了解决是否将条件下推到子查询中的难题，将条件包装在 “ 触发器 ”函数中。因此，以下形式的表达式：

```
outer_exprIN（inner_expr从...处选择subquery_where）
```

转换为：

```
存在（从...中选择1，在哪里subquery_where
                          以及trigcond（outer_expr= inner_expr））
```

更一般而言，如果子查询比较基于几对外部和内部表达式，则转换将采用以下比较：

```
（oe_1，...，oe_N）IN（SELECT ie_1，...，ie_NFROM ... WHERE subquery_where）
```

并将其转换为以下表达式：

```
存在（从...中选择1，在哪里subquery_where
                          以及trigcond（oe_1= ie_1）
                          与...
                          AND trigcond（oe_N= ie_N）
       ）
```

每个 函数都是一个特殊函数，其结果为以下值： `trigcond(*`X`*)`

- *`X`*当 “ 链接的 ”外部表达 *`oe_i`*不是 `NULL`
- `TRUE`当“ 链接的 ” 外部表达*`oe_i`*是 `NULL`

注意

触发器函数*不是*您使用创建的那种触发器[`CREATE TRIGGER`](sql-statements.html#create-trigger)。

```
trigcond()`函数 中包装的等式 不是查询优化器的第一类谓词。大多数优化不能处理可能在查询执行时打开和关闭的谓词，因此它们假定任何谓词 都是未知函数，而忽略它。那些优化可以使用触发的等式： `trigcond(*`X`*)
```

- 参考优化： 可用于构建 ， 或 表访问。 `trigcond(*`X`*=*`Y`* [OR *`Y`* IS NULL])`[`ref`](optimization.html#jointype_ref)[`eq_ref`](optimization.html#jointype_eq_ref)[`ref_or_null`](optimization.html#jointype_ref_or_null)
- 基于索引查找的子查询执行引擎： 可用于构造 或 访问。 `trigcond(*`X`*=*`Y`*)`[`unique_subquery`](optimization.html#jointype_unique_subquery)[`index_subquery`](optimization.html#jointype_index_subquery)
- 表条件生成器：如果子查询是多个表的联接，则将尽快检查触发条件。

当优化器使用触发条件创建某种基于索引查找的访问时（对于前面列表的前两项），对于条件关闭的情况，优化器必须具有回退策略。此后备策略始终相同：执行全表扫描。在 [`EXPLAIN`](sql-statements.html#explain)输出中，回退显示`Full scan on NULL key`在 `Extra`列中：

```
mysql> EXPLAIN SELECT t1.col1,
       t1.col1 IN (SELECT t2.key1 FROM t2 WHERE t2.col2=t1.col2) FROM t1\G
*************************** 1.行******************** *******
           编号：1
  select_type：主要
        表：t1
        ...
*************************** 2.行******************** *******
           id：2
  select_type：DEPENDENT子查询
        表：t2
         类型：index_subquery
可能的键：key1
          键：key1
      key_len：5
          参考：func
         行数：2
        额外：在哪里使用；完全扫描NULL键
```

如果你运行[`EXPLAIN`](sql-statements.html#explain)之后 [`SHOW WARNINGS`](sql-statements.html#show-warnings)，你可以看到触发条件：

```
*************************** 1.行******************** *******
  级别：注意
   编号：1003
消息：选择“测试”。“ t1”。“ col1”作为“ col1”，
         <in_optimizer>（`test`.`t1`.`col1`，
         t2中的<exists>（<index_lookup>（<cache>（`test`.`t1`.`col1`））
         在key1上检查NULL
         其中（`test`.`t2`.`col2` =`test`.`t1`.`col2`）有
         trigcond（<is_not_null_test>（（test..t2.key1）））））））AS
         t1.col1 IN（从t2中选择t2.key1，其中t2.col2 = t1.col2）
         来自`test`.`t1`
```

使用触发条件会影响性能。甲`NULL IN (SELECT ...)` 现在表达可能会导致全表扫描（这是慢）时，它以前没有。这是为获得正确结果而付出的代价（触发条件策略的目标是提高合规性，而不是速度）。

对于多表子查询，执行`NULL IN (SELECT ...)`特别慢，因为联接优化器不会针对外部表达式为的情况进行优化`NULL`。它假定`NULL`左侧的子查询评估非常少见，即使有统计数据表明并非如此。另一方面，如果外部表达式可能是 `NULL`但实际上不是，则不会影响性能。

为了帮助查询优化器更好地执行查询，请使用以下建议：

- 声明一列，就`NOT NULL`好像它确实是一样。通过简化色谱柱的条件测试，这也有助于优化程序的其他方面。

- 如果您不需要区分`NULL`来自 `FALSE`子查询的结果，你可以很容易地避免慢的执行路径。替换如下所示的比较：

  ```
  outer_exprIN（inner_expr从...中选择）
  ```

  具有以下表达式：

  ```
  （outer_expr不为空）和（outer_exprIN（inner_expr从...中选择））
  ```

  然后`NULL IN (SELECT ...)`永远不会进行评估，因为[`AND`](functions.html#operator_and)一旦表达式结果明确，MySQL就会停止评估 零件。

  另一种可能的重写：

  ```
  存在（inner_expr从...中选择）
          在哪里inner_expr= outer_expr）
  ```

  当你需要分不清这将适用于 `NULL`从`FALSE` 子查询结果，在这种情况下，你可能真的想 `EXISTS`。

通过 系统变量的`subquery_materialization_cost_based` 标志，[`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)可以控制子查询实现和`IN`-to- `EXISTS`subquery转换之间的选择 。请参见 [第8.9.2节“可切换的优化”](optimization.html#switchable-optimizations)。

#### 8.2.2.4通过合并或实现来优化派生表和视图引用



优化器可以使用两种策略来处理派生表引用（这也适用于视图引用）：

- 将派生表合并到外部查询块中
- 将派生表具体化为内部临时表

范例1：

```
SELECT * FROM（SELECT * FROM t1）AS派生_t1;
```

通过合并派生表 `derived_t1`，该查询的执行类似于：

```
选择*从t1;
```

范例2：

```
选择 *
  从t1加入（从t2选择t2.f1）AS派生_t2开t1.f2 = derived_t2.f1
  t1.f1> 0;
```

通过合并派生表 `derived_t2`，该查询的执行类似于：

```
选择t1。*，t2.f1
  从t1加入t2到t1.f2 = t2.f1
  t1.f1> 0;
```

有了物化后，`derived_t1`和 `derived_t2`分别在各自的查询中被视为一个单独的表。

优化器以相同的方式处理派生表和视图引用：尽可能避免不必要的实现，从而可以将条件从外部查询下推到派生表，并产生更有效的执行计划。（有关示例，请参见 [第8.2.2.2节“通过实现来优化子查询”）](optimization.html#subquery-materialization)。

如果合并将导致一个外部查询块引用超过61个基本表，则优化程序将选择实现。

`ORDER BY`如果满足以下所有条件，则 优化器将派生子句在派生表或视图引用中传播到外部查询块：

- 外部查询未分组或聚合。
- 外部查询不指定 `DISTINCT`，`HAVING`或 `ORDER BY`。
- 外部查询将此派生表或视图引用作为`FROM`子句中的唯一源。

否则，优化器将忽略该`ORDER BY`子句。

以下方法可用来影响优化器是否尝试将派生表和视图引用合并到外部查询块中：

- 假设没有其他规则阻止合并，则可以使用系统变量 的`derived_merge`标志 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)。请参见[第8.9.2节“可切换的优化”](optimization.html#switchable-optimizations)。默认情况下，该标志启用以允许合并。禁用该标志可防止合并并避免 [`ER_UPDATE_TABLE_USED`](error-handling.html#error_er_update_table_used) 错误。

  该`derived_merge`标志还适用于不包含任何`ALGORITHM`子句的视图。因此，如果 [`ER_UPDATE_TABLE_USED`](error-handling.html#error_er_update_table_used)使用与子查询等效的表达式的视图引用发生错误，则添加 `ALGORITHM=TEMPTABLE`到视图定义将防止合并，并优先于该 `derived_merge`值。

- 可以通过在子查询中使用任何阻止合并的构造来禁用合并，尽管这些构造对实现的影响不那么明显。防止合并的构造与派生表和视图引用相同：

  

  - 聚合函数（[`SUM()`](functions.html#function_sum)， [`MIN()`](functions.html#function_min)， [`MAX()`](functions.html#function_max)， [`COUNT()`](functions.html#function_count)，等等）
  - `DISTINCT`
  - `GROUP BY`
  - `HAVING`
  - `LIMIT`
  - [`UNION`](sql-statements.html#union) 要么 [`UNION ALL`](sql-statements.html#union)
  - 选择列表中的子查询
  - 分配给用户变量
  - 仅引用文字值（在这种情况下，没有基础表）

该`derived_merge`标志还适用于不包含任何`ALGORITHM`子句的视图。因此，如果 [`ER_UPDATE_TABLE_USED`](error-handling.html#error_er_update_table_used)使用与子查询等效的表达式的视图引用发生错误，则添加`ALGORITHM=TEMPTABLE` 到视图定义将防止合并，并优先于当前`derived_merge`值。

如果优化器选择实现策略而不是合并派生表，则它将按以下方式处理查询：

- 优化器将派生表的实现推迟到查询执行期间需要其内容之前。这会提高性能，因为延迟实现可能会导致根本不必这样做。考虑一个将派生表的结果连接到另一个表的查询：如果优化器首先处理该另一个表并发现它不返回任何行，则不需要进一步执行联接，并且优化器可以完全跳过具体化派生表。
- 在查询执行期间，优化器可以将索引添加到派生表中，以加快从中获取行的速度。

[`EXPLAIN`](sql-statements.html#explain) 对于[`SELECT`](sql-statements.html#select)包含派生表的查询， 请考虑以下语句：

```
解释选择*从（选择*从t1）AS派生_t1;
```

优化器通过将派生表延迟到[`SELECT`](sql-statements.html#select)执行期间需要结果之前来避免实现该表 。在这种情况下，查询不会执行（因为它发生在 [`EXPLAIN`](sql-statements.html#explain)语句中），因此永远不需要结果。

即使对于已执行的查询，派生表实现的延迟也可以使优化程序完全避免实现。发生这种情况时，查询执行将比实现实现所需的时间更快。考虑以下查询，该查询将派生表的结果连接到另一个表：

```
选择 *
  从t1加入（从t2选择t2.f1）AS派生_t2
          开启t1.f2 = derived_t2.f1
  t1.f1> 0;
```

如果优化过程`t1`首先进行且`WHERE`子句产生空结果，则联接必须一定为空，并且派生表不必实现。

对于派生表需要实现的情况，优化器可以向实现表添加索引以加快对其的访问。如果使用这样的索引可以 [`ref`](optimization.html#jointype_ref)访问表，则可以大大减少查询执行期间读取的数据量。考虑以下查询：

```
选择 *
 从t1加入（从t2选择DISTINCT f1）AS派生_t2
         开启t1.f1 = derived_t2.f1;
```

如果这样做 ，优化器将构造列上的索引 `f1`，`derived_t2`如果这样做，则可以将 [`ref`](optimization.html#jointype_ref)访问用于最低成本的执行计划。添加索引之后，优化器可以将物化派生表与具有索引的常规表相同，并且它从生成的索引中同样受益。与没有索引的查询执行成本相比，索引创建的开销可以忽略不计。如果 [`ref`](optimization.html#jointype_ref)访问会比其他访问方法带来更高的成本，则优化器不会创建索引，也不会丢失任何内容。

对于优化程序跟踪输出，合并的派生表或视图引用未显示为节点。仅其基础表出现在顶部查询的计划中。

### 8.2.3优化INFORMATION_SCHEMA查询



监视数据库的应用程序可能会频繁使用 `INFORMATION_SCHEMA`表。`INFORMATION_SCHEMA`可以优化某些类型的表查询以更快地执行。目标是最大程度地减少文件操作（例如，扫描目录或打开表文件）以收集构成这些动态表的信息。

注意

`INFORMATION_SCHEMA`查询中 数据库名称和表名称的比较行为 可能与您期望的有所不同。有关详细信息，请参见 [第10.8.7节“在INFORMATION_SCHEMA搜索中使用归类”](charset.html#charset-collation-information-schema)。

**1）尝试在`WHERE` 子句中对数据库和表名使用常量查找值**

您可以利用以下原理：

- 要查找数据库或表，请使用计算结果为常量的表达式，例如文字值，返回常量的函数或标量子查询。
- 避免使用非恒定数据库名称查找值（或不使用查找值）的查询，因为它们需要扫描数据目录以查找匹配的数据库目录名称。
- 在数据库内，请避免使用非恒定表名查找值（或不使用查找值）的查询，因为它们需要扫描数据库目录才能找到匹配的表文件。

此原理适用于下 `INFORMATION_SCHEMA`表中显示的表，该表显示了其常量查找值使服务器能够避免目录扫描的列。例如，如果从中进行选择，则 在 子句中[`TABLES`](information-schema.html#tables-table)使用for的常量查找值可以避免进行数据目录扫描。 `TABLE_SCHEMA``WHERE`

| 表                                                           | 指定要避免数据目录扫描的列 | 指定要避免数据库目录扫描的列 |
| :----------------------------------------------------------- | :------------------------- | :--------------------------- |
| [`COLUMNS`](information-schema.html#columns-table)           | `TABLE_SCHEMA`             | `TABLE_NAME`                 |
| [`KEY_COLUMN_USAGE`](information-schema.html#key-column-usage-table) | `TABLE_SCHEMA`             | `TABLE_NAME`                 |
| [`PARTITIONS`](information-schema.html#partitions-table)     | `TABLE_SCHEMA`             | `TABLE_NAME`                 |
| [`REFERENTIAL_CONSTRAINTS`](information-schema.html#referential-constraints-table) | `CONSTRAINT_SCHEMA`        | `TABLE_NAME`                 |
| [`STATISTICS`](information-schema.html#statistics-table)     | `TABLE_SCHEMA`             | `TABLE_NAME`                 |
| [`TABLES`](information-schema.html#tables-table)             | `TABLE_SCHEMA`             | `TABLE_NAME`                 |
| [`TABLE_CONSTRAINTS`](information-schema.html#table-constraints-table) | `TABLE_SCHEMA`             | `TABLE_NAME`                 |
| [`TRIGGERS`](information-schema.html#triggers-table)         | `EVENT_OBJECT_SCHEMA`      | `EVENT_OBJECT_TABLE`         |
| [`VIEWS`](information-schema.html#views-table)               | `TABLE_SCHEMA`             | `TABLE_NAME`                 |

限于特定的常量数据库名称的查询的好处是只需要对命名数据库目录进行检查。例：

```
从INFORMATION_SCHEMA.TABLES中选择TABLE_NAME
WHERE TABLE_SCHEMA ='测试';
```

使用文字数据库名称`test`可使服务器仅检查`test`数据库目录，而不管可能存在多少个数据库。相比之下，以下查询效率较低，因为它需要扫描数据目录以确定哪些数据库名称与模式匹配`'test%'`：

```
从INFORMATION_SCHEMA.TABLES中选择TABLE_NAME
WHERE TABLE_SCHEMA喜欢'test％';
```

对于限于特定常量表名称的查询，仅需要检查相应数据库目录中的命名表。例：

```
从INFORMATION_SCHEMA.TABLES中选择TABLE_NAME
WHERE TABLE_SCHEMA ='test'和TABLE_NAME ='t1';
```

使用文字表名`t1`可使服务器仅检查`t1` 表文件，而不管`test`数据库中可能有多少个表 。相比之下，以下查询需要扫描`test`数据库目录以确定哪些表名称与模式匹配 `'t%'`：

```
从INFORMATION_SCHEMA.TABLES中选择TABLE_NAME
WHERE TABLE_SCHEMA ='test'和TABLE_NAME像't％';
```

以下查询需要扫描数据库目录以确定该模式的匹配数据库名称 `'test%'`，对于每个匹配的数据库，它都需要扫描数据库目录以确定该模式的匹配表名称`'t%'`：

```
从INFORMATION_SCHEMA.TABLES中选择TABLE_NAME
WHERE TABLE_SCHEMA ='test％'和TABLE_NAME喜欢't％';
```

**2）编写查询以最小化必须打开的表文件的数量**

对于引用某些`INFORMATION_SCHEMA`表列的查询，可以进行 多种优化，以最大程度地减少必须打开的表文件的数量。例：

```
从INFORMATION_SCHEMA.TABLES中选择TABLE_NAME，引擎
WHERE TABLE_SCHEMA ='测试';
```

在这种情况下，服务器扫描数据库目录以确定数据库中表的名称之后，这些名称将可用，而无需进行进一步的文件系统查找。因此，`TABLE_NAME`不需要打开任何文件。的`ENGINE`（存储引擎）值可以通过打开表的确定 `.frm`文件，而不接触其它表文件，例如`.MYD`或 `.MYI`文件。

某些值（例如`INDEX_LENGTH`用于 `MyISAM`表的值）也需要打开 `.MYD`或`.MYI`文件。

文件打开优化类型表示为：

- `SKIP_OPEN_TABLE`：不需要打开表文件。通过扫描数据库目录，该信息已在查询中可用。
- `OPEN_FRM_ONLY`：仅`.frm`需要打开表的 文件。
- `OPEN_TRIGGER_ONLY`：仅`.TRG`需要打开表的 文件。
- `OPEN_FULL_TABLE`：未优化的信息查找。的`.frm`， `.MYD`和`.MYI` 文件必须被打开。



以下列表指示了前面的优化类型如何应用于`INFORMATION_SCHEMA`表列。对于未命名的表和列，没有任何优化适用。

- [`COLUMNS`](information-schema.html#columns-table)： `OPEN_FRM_ONLY`适用于所有列

- [`KEY_COLUMN_USAGE`](information-schema.html#key-column-usage-table)： `OPEN_FULL_TABLE`适用于所有列

- [`PARTITIONS`](information-schema.html#partitions-table)： `OPEN_FULL_TABLE`适用于所有列

- [`REFERENTIAL_CONSTRAINTS`](information-schema.html#referential-constraints-table)： `OPEN_FULL_TABLE`适用于所有列

- [`STATISTICS`](information-schema.html#statistics-table)：

  | 柱              | 优化类型          |
  | :-------------- | :---------------- |
  | `TABLE_CATALOG` | `OPEN_FRM_ONLY`   |
  | `TABLE_SCHEMA`  | `OPEN_FRM_ONLY`   |
  | `TABLE_NAME`    | `OPEN_FRM_ONLY`   |
  | `NON_UNIQUE`    | `OPEN_FRM_ONLY`   |
  | `INDEX_SCHEMA`  | `OPEN_FRM_ONLY`   |
  | `INDEX_NAME`    | `OPEN_FRM_ONLY`   |
  | `SEQ_IN_INDEX`  | `OPEN_FRM_ONLY`   |
  | `COLUMN_NAME`   | `OPEN_FRM_ONLY`   |
  | `COLLATION`     | `OPEN_FRM_ONLY`   |
  | `CARDINALITY`   | `OPEN_FULL_TABLE` |
  | `SUB_PART`      | `OPEN_FRM_ONLY`   |
  | `PACKED`        | `OPEN_FRM_ONLY`   |
  | `NULLABLE`      | `OPEN_FRM_ONLY`   |
  | `INDEX_TYPE`    | `OPEN_FULL_TABLE` |
  | `COMMENT`       | `OPEN_FRM_ONLY`   |

- [`TABLES`](information-schema.html#tables-table)：

  | 柱                | 优化类型          |
  | :---------------- | :---------------- |
  | `TABLE_CATALOG`   | `SKIP_OPEN_TABLE` |
  | `TABLE_SCHEMA`    | `SKIP_OPEN_TABLE` |
  | `TABLE_NAME`      | `SKIP_OPEN_TABLE` |
  | `TABLE_TYPE`      | `OPEN_FRM_ONLY`   |
  | `ENGINE`          | `OPEN_FRM_ONLY`   |
  | `VERSION`         | `OPEN_FRM_ONLY`   |
  | `ROW_FORMAT`      | `OPEN_FULL_TABLE` |
  | `TABLE_ROWS`      | `OPEN_FULL_TABLE` |
  | `AVG_ROW_LENGTH`  | `OPEN_FULL_TABLE` |
  | `DATA_LENGTH`     | `OPEN_FULL_TABLE` |
  | `MAX_DATA_LENGTH` | `OPEN_FULL_TABLE` |
  | `INDEX_LENGTH`    | `OPEN_FULL_TABLE` |
  | `DATA_FREE`       | `OPEN_FULL_TABLE` |
  | `AUTO_INCREMENT`  | `OPEN_FULL_TABLE` |
  | `CREATE_TIME`     | `OPEN_FULL_TABLE` |
  | `UPDATE_TIME`     | `OPEN_FULL_TABLE` |
  | `CHECK_TIME`      | `OPEN_FULL_TABLE` |
  | `TABLE_COLLATION` | `OPEN_FRM_ONLY`   |
  | `CHECKSUM`        | `OPEN_FULL_TABLE` |
  | `CREATE_OPTIONS`  | `OPEN_FRM_ONLY`   |
  | `TABLE_COMMENT`   | `OPEN_FRM_ONLY`   |

- [`TABLE_CONSTRAINTS`](information-schema.html#table-constraints-table)： `OPEN_FULL_TABLE`适用于所有列

- [`TRIGGERS`](information-schema.html#triggers-table)： `OPEN_TRIGGER_ONLY`适用于所有列

- [`VIEWS`](information-schema.html#views-table)：

  | 柱                     | 优化类型          |
  | :--------------------- | :---------------- |
  | `TABLE_CATALOG`        | `OPEN_FRM_ONLY`   |
  | `TABLE_SCHEMA`         | `OPEN_FRM_ONLY`   |
  | `TABLE_NAME`           | `OPEN_FRM_ONLY`   |
  | `VIEW_DEFINITION`      | `OPEN_FRM_ONLY`   |
  | `CHECK_OPTION`         | `OPEN_FRM_ONLY`   |
  | `IS_UPDATABLE`         | `OPEN_FULL_TABLE` |
  | `DEFINER`              | `OPEN_FRM_ONLY`   |
  | `SECURITY_TYPE`        | `OPEN_FRM_ONLY`   |
  | `CHARACTER_SET_CLIENT` | `OPEN_FRM_ONLY`   |
  | `COLLATION_CONNECTION` | `OPEN_FRM_ONLY`   |

**3） [`EXPLAIN`](sql-statements.html#explain)用于确定服务器是否可以`INFORMATION_SCHEMA` 对查询使用优化**

这尤其适用 `INFORMATION_SCHEMA`于从多个数据库中搜索信息的查询，这可能需要很长时间并影响性能。输出中的`Extra`值[`EXPLAIN`](sql-statements.html#explain)指示服务器可以使用哪些（如果有的话）前面所述的优化来评估`INFORMATION_SCHEMA` 查询。以下示例演示了您期望在`Extra`值中看到的各种信息 。

```
mysql> EXPLAIN SELECT TABLE_NAME FROM INFORMATION_SCHEMA.VIEWS WHERE
       TABLE_SCHEMA = 'test' AND TABLE_NAME = 'v1'\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表格：VIEWS
         类型：全部
可能的键：NULL
          键：TABLE_SCHEMA，TABLE_NAME
      key_len：NULL
          参考：NULL
         行：NULL
        额外：在哪里使用；Open_frm_only; 已扫描0个数据库
```

使用恒定的数据库和表查找值可使服务器避免目录扫描。对于的引用 `VIEWS.TABLE_NAME`，仅 `.frm`需要打开文件。

```
mysql> EXPLAIN SELECT TABLE_NAME, ROW_FORMAT FROM INFORMATION_SCHEMA.TABLES\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表格：TABLES
         类型：全部
可能的键：NULL
          键：NULL
      key_len：NULL
          参考：NULL
         行：NULL
        额外：Open_full_table; 扫描所有数据库
```

没有提供查找值（没有 `WHERE`子句），因此服务器必须扫描数据目录和每个数据库目录。对于这样标识的每个表，选择表名和行格式。 `TABLE_NAME`不需要打开其他表文件（`SKIP_OPEN_TABLE`适用优化）。`ROW_FORMAT`要求打开所有表文件（`OPEN_FULL_TABLE`适用）。 [`EXPLAIN`](sql-statements.html#explain)报告说， `OPEN_FULL_TABLE`因为它比贵`SKIP_OPEN_TABLE`。

```
mysql> EXPLAIN SELECT TABLE_NAME, TABLE_TYPE FROM INFORMATION_SCHEMA.TABLES
       WHERE TABLE_SCHEMA = 'test'\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表格：TABLES
         类型：全部
可能的键：NULL
          键：TABLE_SCHEMA
      key_len：NULL
          参考：NULL
         行：NULL
        额外：在哪里使用；Open_frm_only; 扫描了1个数据库
```

没有提供表名查找值，因此服务器必须扫描`test`数据库目录。对于 `TABLE_NAME`和`TABLE_TYPE` 列，分别使用`SKIP_OPEN_TABLE`和 `OPEN_FRM_ONLY`优化。[`EXPLAIN`](sql-statements.html#explain)报告， `OPEN_FRM_ONLY`因为它更昂贵。

```
mysql> EXPLAIN SELECT B.TABLE_NAME
       FROM INFORMATION_SCHEMA.TABLES AS A, INFORMATION_SCHEMA.COLUMNS AS B
       WHERE A.TABLE_SCHEMA = 'test'
       AND A.TABLE_NAME = 't1'
       AND B.TABLE_NAME = A.TABLE_NAME\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表：A
         类型：全部
可能的键：NULL
          键：TABLE_SCHEMA，TABLE_NAME
      key_len：NULL
          参考：NULL
         行：NULL
        额外：在哪里使用；Skip_open_table; 已扫描0个数据库
*************************** 2.行******************** *******
           编号：1
  select_type：简单
        表：B
         类型：全部
可能的键：NULL
          键：NULL
      key_len：NULL
          参考：NULL
         行：NULL
        额外：在哪里使用；Open_frm_only; 扫描所有数据库；
               使用联接缓冲区
```

对于第一[`EXPLAIN`](sql-statements.html#explain)输出行：常量数据库和表查找值使服务器可以避免目录扫描`TABLES`值。引用`TABLES.TABLE_NAME`不需要其他表文件。

对于第二个[`EXPLAIN`](sql-statements.html#explain)输出行：所有[`COLUMNS`](information-schema.html#columns-table)表值都是 `OPEN_FRM_ONLY`查找，因此 `COLUMNS.TABLE_NAME`需要打开 `.frm`文件。

```
mysql> EXPLAIN SELECT * FROM INFORMATION_SCHEMA.COLLATIONS\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        桌子：COLLATIONS
         类型：全部
可能的键：NULL
          键：NULL
      key_len：NULL
          参考：NULL
         行：NULL
        额外：
```

在这种情况下，由于[`COLLATIONS`](information-schema.html#collations-table)没有`INFORMATION_SCHEMA`可用的优化表之一，因此不 应用 优化。

### 8.2.4优化数据更改语句

- [8.2.4.1优化INSERT语句](optimization.html#insert-optimization)
- [8.2.4.2优化UPDATE语句](optimization.html#update-optimization)
- [8.2.4.3优化DELETE语句](optimization.html#delete-optimization)



这部分解释了如何加快数据更改语句： [`INSERT`](sql-statements.html#insert)， [`UPDATE`](sql-statements.html#update)，和 [`DELETE`](sql-statements.html#delete)。传统的OLTP应用程序和现代Web应用程序通常会执行许多小的数据更改操作，而这些操作对并发至关重要。数据分析和报告应用程序通常运行会同时影响许多行的数据更改操作，其中主要考虑因素是I / O以写入大量数据并保持索引为最新。为了插入和更新大量数据（在业界称为ETL，即 “ extract-transform-load ”），有时您可以使用其他SQL语句或外部命令来模拟 [`INSERT`](sql-statements.html#insert)， [`UPDATE`](sql-statements.html#update)和 [`DELETE`](sql-statements.html#delete)语句。

#### 8.2.4.1优化INSERT语句



为了优化插入速度，请将许多小操作合并为一个大操作。理想情况下，您进行单个连接，一次发送许多新行的数据，并将所有索引更新和一致性检查延迟到最后。

插入行所需的时间由以下因素决定，其中数字表示近似比例：

- 连接：（3）
- 向服务器发送查询：（2）
- 解析查询：（2）
- 插入行：（1×行大小）
- 插入索引：（1×索引数）
- 闭幕：（1）

这没有考虑打开表的初始开销，对于每个并发运行的查询，该开销只需要执行一次即可。

*`N`*假设B树索引， 表的大小会减慢按log插入索引的速度。

您可以使用以下方法来加快插入速度：

- 如果要同时从同一客户端插入许多行，请使用[`INSERT`](sql-statements.html#insert) 具有多个`VALUES`列表的语句一次插入几行。这比使用单独的单行[`INSERT`](sql-statements.html#insert) 语句要快得多（某些情况下要快很多倍）。如果要将数据添加到非空表，则可以调整 [`bulk_insert_buffer_size`](server-administration.html#sysvar_bulk_insert_buffer_size) 变量以使数据插入更快。请参见 [第5.1.7节“服务器系统变量”](server-administration.html#server-system-variables)。
- 从文本文件加载表格时，请使用 [`LOAD DATA`](sql-statements.html#load-data)。这通常比使用[`INSERT`](sql-statements.html#insert)语句快20倍 。请参见 [第13.2.6节“ LOAD DATA语句”](sql-statements.html#load-data)。
- 利用列具有默认值的事实。仅当要插入的值与默认值不同时才明确插入值。这减少了MySQL必须执行的解析，并提高了插入速度。
- 请参阅[第8.5.5节“为InnoDB表加载大数据”，](optimization.html#optimizing-innodb-bulk-data-loading) 以获取特定于`InnoDB`表的提示。
- 有关 特定于表的提示[，](optimization.html#optimizing-myisam-bulk-data-loading)请参见[第8.6.2节“为MyISAM表批量加载数据”](optimization.html#optimizing-myisam-bulk-data-loading)`MyISAM`。

#### 8.2.4.2优化UPDATE语句



优化了更新语句[`SELECT`](sql-statements.html#select)，使其像 查询一样具有额外的写操作开销。写入速度取决于要更新的数据量和要更新的索引数。不变的索引不会更新。

获得快速更新的另一种方法是延迟更新，然后在以后连续进行许多更新。如果锁定表，一起执行多个更新要比一次执行一次更新快得多。

对于`MyISAM`使用动态行格式的表，将行更新为更长的总长度可能会拆分该行。如果您经常这样做，那么[`OPTIMIZE TABLE`](sql-statements.html#optimize-table)偶尔使用非常重要 。请参见[第13.7.2.4节“ OPTIMIZE TABLE语句”](sql-statements.html#optimize-table)。

#### 8.2.4.3优化DELETE语句



删除`MyISAM`表中单个行所需的时间 与索引数成正比。若要更快地删除行，可以通过增加[`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)系统变量来增加键高速缓存的大小 。请参见[第5.1.1节“配置服务器”](server-administration.html#server-configuration)。

要删除`MyISAM`表中的所有行， 速度比快 。截断操作不是事务安全的；在活动事务或活动表锁定过程中尝试执行一个错误时发生错误。请参见[第13.1.34节“ TRUNCATE TABLE语句”](sql-statements.html#truncate-table)。 `TRUNCATE TABLE *`tbl_name`*``DELETE FROM *`tbl_name`*`

### 8.2.5优化数据库特权



权限设置越复杂，所有SQL语句的开销就越大。简化由[`GRANT`](sql-statements.html#grant)语句建立的特权 可使MySQL减少客户端执行语句时的权限检查开销。例如，如果您不授予任何表级或列级特权，则服务器无需检查`tables_priv`和 `columns_priv`表的内容。同样，如果您没有对任何帐户设置资源限制，则服务器不必执行资源计数。如果您有很高的语句处理负载，请考虑使用简化的授权结构以减少权限检查的开销。

### 8.2.6其他优化技巧



本节列出了许多提高查询处理速度的技巧：

- 如果您的应用程序发出多个数据库请求以执行相关更新，则将这些语句组合到存储的例程中可以提高性能。同样，如果您的应用程序根据多个列值或大量数据计算单个结果，则将计算结果合并到UDF（用户定义的函数）中可以提高性能。然后，产生的快速数据库操作可用于其他查询，应用程序，甚至可以用不同的编程语言编写的代码重用。有关更多信息[，](stored-objects.html#stored-routines)请参见 [第23.2节“使用存储的例程”](stored-objects.html#stored-routines)和 [第28.4节“向MySQL添加函数”](extending-mysql.html#adding-functions)。

- 要解决`ARCHIVE`表出现的任何压缩问题 ，请使用 [`OPTIMIZE TABLE`](sql-statements.html#optimize-table)。请参见 [第15.5节“ ARCHIVE存储引擎”](storage-engines.html#archive-storage-engine)。

- 如果可能，将报告分类为“ 实时 ”或 “ 统计 ”，其中统计报告所需的数据仅从根据实时数据定期生成的汇总表中创建。

- 如果您的数据与行和列的表结构不一致，则可以将数据打包并将其存储到[`BLOB`](data-types.html#blob)列中。在这种情况下，您必须在应用程序中提供代码以打包和解压缩信息，但这可能会节省I / O操作以读取和写入相关值集。

- 对于Web服务器，将图像和其他二进制资产存储为文件，路径名存储在数据库中，而不是文件本身。大多数Web服务器在缓存文件方面比在数据库内容上更好，因此使用文件通常更快。（尽管在这种情况下，您必须自己处理备份和存储问题。）

- 如果您确实需要很高的速度，请查看底层的MySQL接口。例如，通过直接访问MySQL [`InnoDB`](innodb-storage-engine.html)或 [`MyISAM`](storage-engines.html#myisam-storage-engine)存储引擎，与使用SQL接口相比，可以大大提高速度。

  同样，对于使用[`NDBCLUSTER`](mysql-cluster.html)存储引擎的数据库 ，您可能希望研究NDB API的可能用法（请参阅《 [MySQL NDB Cluster API开发人员指南》](https://dev.mysql.com/doc/ndbapi/en/)）。

- 复制可以为某些操作提供性能优势。您可以在复制服务器之间分配客户端检索，以分散负载。为避免在备份时降低主服务器的速度，可以使用从服务器进行备份。请参见[第16章，*复制*](replication.html)。

## 8.3优化和索引

- [8.3.1 MySQL如何使用索引](optimization.html#mysql-indexes)
- [8.3.2主键优化](optimization.html#primary-key-optimization)
- [8.3.3外键优化](optimization.html#foreign-key-optimization)
- [8.3.4列索引](optimization.html#column-indexes)
- [8.3.5多列索引](optimization.html#multiple-column-indexes)
- [8.3.6验证索引使用情况](optimization.html#verifying-index-usage)
- [8.3.7 InnoDB和MyISAM索引统计信息收集](optimization.html#index-statistics)
- [8.3.8 B树和哈希索引的比较](optimization.html#index-btree-hash)
- [8.3.9索引扩展的使用](optimization.html#index-extensions)
- [8.3.10优化器对生成的列索引的使用](optimization.html#generated-column-index-optimizations)
- [8.3.11从TIMESTAMP列进行索引查找](optimization.html#timestamp-lookups)



改善操作性能的最佳方法 [`SELECT`](sql-statements.html#select)是在查询中测试的一个或多个列上创建索引。索引条目的作用类似于指向表行的指针，从而使查询可以快速确定哪些行与`WHERE`子句中的条件匹配，并检索这些行的其他列值。所有MySQL数据类型都可以建立索引。

尽管可能会为查询中使用的每个可能的列创建索引，但是不必要的索引会浪费空间和时间，使MySQL难以确定要使用的索引。索引还会增加插入，更新和删除的成本，因为必须更新每个索引。您必须找到适当的平衡，才能使用最佳索引集来实现快速查询。

### 8.3.1 MySQL如何使用索引



索引用于快速查找具有特定列值的行。没有索引，MySQL必须从第一行开始，然后通读整个表以找到相关的行。桌子越大，花费越多。如果表中有相关列的索引，MySQL可以快速确定要在数据文件中间查找的位置，而不必查看所有数据。这比顺序读取每一行要快得多。

大多数MySQL索引（`PRIMARY KEY`， `UNIQUE`，`INDEX`和 `FULLTEXT`）存储在 [B树](glossary.html#glos_b_tree)。例外：空间数据类型的索引使用R树；`MEMORY` 表还支持[哈希索引](glossary.html#glos_hash_index) ; `InnoDB`对`FULLTEXT`索引使用倒排列表。

通常，如以下讨论中所述使用索引。[第8.3.8节“ B树和哈希索引的比较”](optimization.html#index-btree-hash)`MEMORY`中介绍了哈希索引特有的特性（如表中所用 ） 。

MySQL使用索引进行以下操作：

- `WHERE`快速 查找与子句匹配的行。

- 从考虑中消除行。如果可以在多个索引之间进行选择，MySQL通常会使用找到最少行数的索引（最具 [选择性的](glossary.html#glos_selectivity)索引）。

- 如果表具有多列索引，那么优化器可以使用索引的任何最左前缀来查找行。举例来说，如果你有一个三列的索引 `(col1, col2, col3)`，你有索引的搜索功能`(col1)`， `(col1, col2)`以及`(col1, col2, col3)`。有关更多信息，请参见 [第8.3.5节“多列索引”](optimization.html#multiple-column-indexes)。

- 执行联接时从其他表中检索行。如果声明相同的类型和大小，MySQL可以更有效地在列上使用索引。在这种情况下， [`VARCHAR`](data-types.html#char)与 [`CHAR`](data-types.html#char)被认为是相同的，如果它们被声明为相同的大小。例如， `VARCHAR(10)`和 `CHAR(10)`是相同的大小，但是 `VARCHAR(10)`和 `CHAR(15)`不是。

  为了在非二进制字符串列之间进行比较，两个列应使用相同的字符集。例如，将一`utf8`列与一 `latin1`列进行比较会排除使用索引。

  如果无法不通过转换直接比较值，则比较不同的列（例如，将字符串列与时间或数字列进行比较）可能会阻止使用索引。对于给定的值，如`1` 在数值列，它可能比较等于在字符串列，例如任何数量的值 `'1'`，`' 1'`， `'00001'`，或`'01.e1'`。这排除了对字符串列使用任何索引的可能性。

- 查找特定索引列的[`MIN()`](functions.html#function_min)或 [`MAX()`](functions.html#function_max)值*`key_col`*。这由预处理器优化，该预处理器检查您是否正在 索引中之前出现的所有关键部分上使用。在这种情况下，MySQL为每个表达式或 表达式执行一次键查找，并将其替换为常量。如果所有表达式都用常量替换，查询将立即返回。例如： `WHERE *`key_part_N`* = *`constant`*`*`key_col`*[`MIN()`](functions.html#function_min)[`MAX()`](functions.html#function_max)

  ```
  选择MIN（key_part2），MAX（key_part2）
    从tbl_name哪里key_part1= 10;
  ```

- 如果排序或分组是在可用索引的最左前缀（例如）上完成的，则对表进行排序或分组 。如果所有关键部分后面都有，则按相反的顺序读取密钥。请参见 [第8.2.1.14节“按优化](optimization.html#order-by-optimization)[排序”](optimization.html#group-by-optimization)和 [第8.2.1.15节“按优化分组”](optimization.html#group-by-optimization)。 `ORDER BY *`key_part1`*, *`key_part2`*``DESC`

- 在某些情况下，可以优化查询以检索值而无需查询数据行。（为查询提供所有必要结果的[索引](glossary.html#glos_covering_index)称为 [覆盖索引](glossary.html#glos_covering_index)。）如果查询仅从表中使用某些索引中包含的列，则可以从索引树中检索所选值以提高速度：

  ```
  key_part3从tbl_name
    哪里选择key_part1= 1
  ```

对于报表查询处理大多数或所有行的小型表或大型表，索引的重要性不那么重要。当查询需要访问大多数行时，顺序读取要比处理索引快。顺序读取可以最大程度地减少磁盘查找，即使查询不需要所有行。有关详细信息[，](optimization.html#table-scan-avoidance)请参见[第8.2.1.20节“避免全表扫描”](optimization.html#table-scan-avoidance)。

### 8.3.2主键优化



表的主键代表您在最重要的查询中使用的一列或几列。它具有关联的索引，可提高查询性能。查询性能可从`NOT NULL`优化中受益，因为它不能包含任何`NULL`值。使用`InnoDB`存储引擎，可以对表数据进行物理组织，以根据一个或多个主键列进行超快速查找和排序。

如果您的表又大又重要，但没有明显的列或一组列用作主键，则可以创建一个单独的列，并使用自动增量值作为主键。当您使用外键联接表时，这些唯一的ID可用作指向其他表中相应行的指针。

### 8.3.3外键优化



如果一个表有许多列，并且您查询了许多不同的列组合，将不常用的数据拆分成单独的表（每个表包含几列），然后通过复制数字ID将它们关联回主表可能会比较有效。主表中的列。这样，每个小表都可以具有用于快速查找其数据的主键，并且您可以使用联接操作仅查询所需的一组列。根据相关数据的分布方式，查询可能执行较少的I / O并占用较少的缓存，因为相关的列在磁盘上打包在一起。（为了最大化性能，查询尝试从磁盘读取尽可能少的数据块；

### 8.3.4列索引



索引的最常见类型涉及单个列，该列将来自该列的值的副本存储在数据结构中，从而允许快速查找具有相应列值的行。B树数据结构可以让索引快速查找特定值，一组值，或值的范围，对应于运营商，如`=`， `>`，`≤`， `BETWEEN`，`IN`，等等，一在`WHERE`子句。

每个存储引擎定义每个表的最大索引数和最大索引长度。请参阅 [第14章，*InnoDB存储引擎*](innodb-storage-engine.html)和 [第15章，*备用存储引擎*](storage-engines.html)。所有存储引擎每个表至少支持16个索引，并且总索引长度至少为256个字节。大多数存储引擎都有更高的限制。

有关列索引的更多信息，请参见 [第13.1.14节“ CREATE INDEX语句”](sql-statements.html#create-index)。

- [索引前缀](optimization.html#column-indexes-prefix)
- [全文索引](optimization.html#column-indexes-fulltext)
- [空间指数](optimization.html#column-indexes-spatial)
- [MEMORY存储引擎中的索引](optimization.html#column-indexes-memory-storage-engine)

#### 索引前缀



使用 字符串列的索引规范中的语法，您可以创建仅使用列首字符的索引 。以这种方式仅索引列值的前缀可以使索引文件小得多。为a 或 column 编制索引时 ， *必须*为索引指定前缀长度。例如： `*`col_name`*(*`N`*)`*`N`*[`BLOB`](data-types.html#blob)[`TEXT`](data-types.html#blob)

```
创建表测试（blob_col BLOB，INDEX（blob_col（10）））;
```

前缀最长可以为1000个字节（`InnoDB`表中为767个字节 ，除非已 [`innodb_large_prefix`](innodb-storage-engine.html#sysvar_innodb_large_prefix)设置）。

注意

前缀限制以字节为单位，而在前缀长度[`CREATE TABLE`](sql-statements.html#create-table)， [`ALTER TABLE`](sql-statements.html#alter-table)和 [`CREATE INDEX`](sql-statements.html#create-index)语句被解释为非二进制串类型的字符数（[`CHAR`](data-types.html#char)， [`VARCHAR`](data-types.html#char)， [`TEXT`](data-types.html#blob)对于二进制串类型），并且字节数（[`BINARY`](data-types.html#binary-varbinary)， [`VARBINARY`](data-types.html#binary-varbinary)， [`BLOB`](data-types.html#blob)）。为使用多字节字符集的非二进制字符串列指定前缀长度时，请考虑到这一点。

如果搜索词超过索引前缀长度，则使用索引排除不匹配的行，然后检查其余行是否可能匹配。

有关索引前缀的更多信息，请参见 [第13.1.14节“ CREATE INDEX语句”](sql-statements.html#create-index)。

#### 全文索引



`FULLTEXT`索引用于全文搜索。只有[`InnoDB`](innodb-storage-engine.html)和 [`MyISAM`](storage-engines.html#myisam-storage-engine)存储引擎支持 `FULLTEXT`索引和仅适用于 [`CHAR`](data-types.html#char)， [`VARCHAR`](data-types.html#char)和 [`TEXT`](data-types.html#blob)列。索引始终在整个列上进行，并且不支持列前缀索引。有关详细信息，请参见 [第12.9节“全文搜索功能”](functions.html#fulltext-search)。

优化适用于`FULLTEXT`针对单个`InnoDB`表的某些类型的 查询 。具有以下特征的查询特别有效：

- `FULLTEXT` 仅返回文档ID或文档ID和搜索等级的查询。
- `FULLTEXT`查询以分数的降序对匹配行进行排序，并应用一个 `LIMIT`子句以获取前N个匹配行。为了应用此优化，必须没有 `WHERE`子句，只有一个 `ORDER BY`子句按降序排列。
- `FULLTEXT`仅检索`COUNT(*)`与搜索词匹配的行的 值的查询，没有其他`WHERE` 子句。将该`WHERE`子句编码为 ，没有任何比较运算符。 `WHERE MATCH(*`text`*) AGAINST ('*`other_text`*')``> 0`

对于包含全文表达式的查询，MySQL在查询执行的优化阶段评估这些表达式。优化器不仅查看全文表达式并进行估计，而且还在制定执行计划的过程中对它们进行评估。

这种行为的含义是， [`EXPLAIN`](sql-statements.html#explain)与在优化阶段未进行任何表达式求值的非全文查询相比，全文查询通常要慢。

[`EXPLAIN`](sql-statements.html#explain)由于优化期间发生匹配`Select tables optimized away`，因此全文查询可能会显示在该`Extra`列中；在这种情况下，在以后的执行期间不需要进行表访问。

#### 空间指数



您可以在空间数据类型上创建索引。 `MyISAM`并`InnoDB` 支持有关空间类型的R树索引。其他存储引擎使用B树来索引空间类型（除外 `ARCHIVE`，不支持空间类型索引）。

#### MEMORY存储引擎中的索引



该`MEMORY`存储引擎使用 `HASH`默认的索引，而且还支持 `BTREE`索引。

### 8.3.5多列索引



MySQL可以创建复合索引（即，多列上的索引）。一个索引最多可以包含16列。对于某些数据类型，您可以为列的前缀编制索引（请参见 [第8.3.4节“列索引”](optimization.html#column-indexes)）。

MySQL可以将多列索引用于测试索引中所有列的查询，或者仅测试第一列，前两列，前三列等等的查询。如果以正确的顺序在索引定义中指定列，则单个复合索引可以加快对同一表的几种查询。

多列索引可以被认为是排序数组，其行包含通过串联索引列的值而创建的值。

注意

作为复合索引的替代方法，您可以根据其他列中的信息引入被“ 哈希化 ”的列。如果此列短，合理地唯一并且已建立索引，则它可能比许多列上的“ 宽 ”索引快。在MySQL中，使用此额外的列非常容易：

```
SELECT * FROM tbl_name
  WHERE hash_col= MD5（CONCAT（val1，val2））
  AND col1= val1AND col2= val2;
```

假设一个表具有以下规范：

```
创建表测试（
    id INT NOT NULL，
    last_name CHAR（30）NOT NULL，
    first_name CHAR（30）NOT NULL，
    主键（id），
    索引名称（姓，名）
）;
```

该`name`指数是在一个索引 `last_name`和`first_name` 列。该索引可用于查询中的查找，这些查询指定在已知范围内的`last_name`和`first_name` 值组合的 值。它也可以用于仅指定`last_name`值的查询， 因为该列是索引的最左前缀（如本节稍后所述）。因此，该`name`索引用于以下查询中的查找：

```
SELECT * FROM test WHERE last_name ='Jones';

选择*从测试
  其中last_name ='Jones'和first_name ='John';

选择*从测试
  WHERE last_name ='琼斯'
  AND（first_name ='John'或first_name ='Jon'）;

选择*从测试
  WHERE last_name ='琼斯'
  AND first_name> ='M'AND first_name <'N';
```

但是，在以下查询中，`name`索引 *不*用于查找：

```
SELECT * FROM test WHERE first_name ='John';

选择*从测试
  WHERE last_name ='Jones'或first_name ='John';
```

假设您发出以下 [`SELECT`](sql-statements.html#select)语句：

```
SELECT * FROM tbl_name
  WHERE col1 = val1AND col2 = val2;
```

如果`col1`和上 存在多列索引`col2`，则可以直接获取相应的行。如果`col1`和上存在单独的单列索引 `col2`，那么优化器将尝试使用索引合并优化（请参见 [第8.2.1.3节“索引合并优化”](optimization.html#index-merge-optimization)），或者尝试通过确定哪个索引排除更多行并使用来查找限制性最强的索引。该索引以获取行。



如果表具有多列索引，那么优化器可以使用索引的任何最左前缀来查找行。举例来说，如果你有一个三列的索引`(col1, col2, col3)`，你有索引的搜索功能 `(col1)`，`(col1, col2)`以及 `(col1, col2, col3)`。

如果列不构成索引的最左前缀，则MySQL无法使用索引执行查找。假设您具有以下[`SELECT`](sql-statements.html#select)所示的语句：

```
SELECT * FROM tbl_nameWHERE col1 = val1;
SELECT * FROM tbl_nameWHERE col1 = val1AND col2 = val2;

SELECT * FROM tbl_nameWHERE col2 = val2;
SELECT * FROM tbl_nameWHERE col2 = val2AND col3 = val3;
```

如果存在索引`(col1, col2, col3)`，则仅前两个查询使用索引。第三个查询和第四个查询确实涉及索引列，但是不使用索引来执行查找，因为`(col2)`和 `(col2, col3)`不是的最左前缀 `(col1, col2, col3)`。

### 8.3.6验证索引使用情况

始终检查所有查询是否真的使用您在表中创建的索引。使用[第8.8.1节“使用EXPLAIN优化查询”中](optimization.html#using-explain)[`EXPLAIN`](sql-statements.html#explain)所述的 语句。

### 8.3.7 InnoDB和MyISAM索引统计信息收集

存储引擎收集有关表的统计信息，以供优化器使用。表统计信息基于值组，其中值组是一组具有相同键前缀值的行。出于优化目的，重要的统计数据是平均值组的大小。

MySQL通过以下方式使用平均值组大小：

- 估算每次[`ref`](optimization.html#jointype_ref)访问 必须读取多少行

- 估计部分联接将产生多少行；也就是说，这种形式的操作将产生的行数：

  ```
  （...）JOIN tbl_nameON tbl_name。key=expr
  ```

随着索引的平均值组大小的增加，该索引在这两个用途中的用处不大，因为每次查找的平均行数增加：为了使索引更好地用于优化目的，最好将每个索引值作为目标表中的行数。当给定的索引值产生大量的行时，该索引的作用较小，而MySQL不太可能使用该索引。

平均值组的大小与表基数有关，表基数是值组的数目。该 [`SHOW INDEX`](sql-statements.html#show-index)语句显示基于的基数值*`N/S`*，其中 *`N`*是表中的行数，并且*`S`*是平均值组的大小。该比率在表中产生大约数量的值组。

对于基于联接`<=>`比较运营商，`NULL`没有从任何其它值区别对待：`NULL <=> NULL`，就像任何其他 。 `*`N`* <=> *`N`*`*`N`*

但是，对于基于`=`运算符的联接， `NULL`它与非`NULL`值是不同的： 当或 （或两者）均为 时不为真 。这会影响 以下形式的比较访问：如果的当前值是，MySQL将不会访问表 ，因为比较不能为真。 `*`expr1`* = *`expr2`*`*`expr1`**`expr2`*`NULL`[`ref`](optimization.html#jointype_ref)`*`tbl_name.key`* = *`expr`*`*`expr`*`NULL`

为了`=`进行比较，`NULL`表中有多少个值都没有关系。为了优化目的，相关值是非`NULL`值组的平均大小。但是，MySQL当前不支持收集或使用该平均大小。

对于`InnoDB`和`MyISAM` 表，您分别可以通过[`innodb_stats_method`](innodb-storage-engine.html#sysvar_innodb_stats_method)和 [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method)系统变量来控制表统计信息的收集 。这些变量具有三个可能的值，其区别如下：

- 当变量设置为时`nulls_equal`，所有`NULL`值都被视为相同（即，它们全部形成一个值组）。

  如果`NULL`值组大小比平均非`NULL`值组大小大得多，则此方法会使平均值组大小向上倾斜。这使得索引在优化器中似乎没有那么有用，而对于查找非`NULL`值的联接而言，索引的作用实际上不那么有用。因此，该 `nulls_equal`方法可能导致优化器[`ref`](optimization.html#jointype_ref)在应该使用索引时不使用索引进行 访问。

- 当变量设置为时 `nulls_unequal`，`NULL` 值将被认为是不同的。而是，每个 `NULL`值构成一个单独的大小为1的值组。

  如果您有很多`NULL`值，则此方法会使平均值组的大小向下倾斜。如果平均非`NULL`值组大小很大，则将`NULL`每个值作为一组大小1进行计数会导致优化器高估查找非`NULL` 值的联接的索引值。因此，当其他方法可能更好时，该`nulls_unequal` 方法可能会导致优化器将此索引用于 [`ref`](optimization.html#jointype_ref)查找。

- 将变量设置为时 `nulls_ignored`，`NULL` 将忽略值。

如果您倾向于使用许多使用`<=>`而不是的联接 `=`，则 `NULL`值在比较中并不特殊，一个`NULL`等于另一个。在这种情况下，`nulls_equal`是适当的统计方法。

该[`innodb_stats_method`](innodb-storage-engine.html#sysvar_innodb_stats_method)系统变量具有全局值; 该 [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method)系统变量有全局和会话值。设置全局值会影响从相应存储引擎收集表的统计信息。设置会话值只会影响当前客户端连接的统计信息收集。这意味着您可以通过将会话值设置为来强制使用给定的方法重新生成表的统计信息，而不会影响其他客户端 [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method)。

要重新生成`MyISAM`表统计信息，可以使用以下任何一种方法：

- 执行[**myisamchk --stats_method = \*`method_name`\* --analyze**](programs.html#myisamchk)
- 更改表以使其统计信息过时（例如，插入一行然后将其删除），然后设置 [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method)并发出一条[`ANALYZE TABLE`](sql-statements.html#analyze-table) 语句

关于使用的一些注意事项 [`innodb_stats_method`](innodb-storage-engine.html#sysvar_innodb_stats_method)和 [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method)：

- 如前所述，您可以强制显式收集表统计信息。但是，MySQL可能还会自动收集统计信息。例如，如果在执行表语句的过程中，其中一些语句修改了表，则MySQL可能会收集统计信息。（例如，这可能发生在批量插入或删除操作或某些 [`ALTER TABLE`](sql-statements.html#alter-table)语句中。）如果发生这种情况，则使用任何值 [`innodb_stats_method`](innodb-storage-engine.html#sysvar_innodb_stats_method)或 [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method)当时有。因此，如果您使用一种方法收集统计信息，但是稍后稍后自动收集表的统计信息时，系统变量设置为另一种方法，则将使用另一种方法。
- 无法确定使用哪种方法为给定表生成统计信息。
- 这些变量仅适用于`InnoDB`和 `MyISAM`表。其他存储引擎只有一种收集表统计信息的方法。通常它更接近该`nulls_equal`方法。

### 8.3.8 B树和哈希索引的比较



了解B树和哈希数据结构可以帮助预测不同查询如何对在索引中使用这些数据结构的不同存储引擎执行不同的查询，特别是对于`MEMORY`允许您选择B树或哈希索引的存储引擎。

- [B树索引特征](optimization.html#btree-index-characteristics)
- [哈希指数特征](optimization.html#hash-index-characteristics)

#### B树索引特征



A B树索引可以在使用表达式中使用的对列的比较 [`=`](functions.html#operator_equal)， [`>`](functions.html#operator_greater-than)， [`>=`](functions.html#operator_greater-than-or-equal)， [`<`](functions.html#operator_less-than)， [`<=`](functions.html#operator_less-than-or-equal)，或[`BETWEEN`](functions.html#operator_between)运营商。[`LIKE`](functions.html#operator_like) 如果to的参数[`LIKE`](functions.html#operator_like)是不以通配符开头的常量字符串，则索引也可以用于比较 。例如，以下[`SELECT`](sql-statements.html#select)语句使用索引：

```
SELECT * FROM tbl_nameWHERE key_colLIKE'Patrick％';
SELECT * FROM tbl_nameWHERE key_collike'Pat％_ck％';
```

在第一条语句中，仅考虑带有的行。在第二条语句中，仅考虑带有的行。 `'Patrick' <= *`key_col`* < 'Patricl'``'Pat' <= *`key_col`* < 'Pau'`

以下[`SELECT`](sql-statements.html#select)语句不使用索引：

```
SELECT * FROM tbl_nameWHERE key_colLIKE'％Patrick％';
SELECT * FROM tbl_nameWHERE key_colLIKE other_col;
```

在第一个语句中，该[`LIKE`](functions.html#operator_like) 值以通配符开头。在第二条语句中，该[`LIKE`](functions.html#operator_like)值不是常数。

如果使用且 长度超过三个字符，则MySQL使用Turbo Boyer-Moore算法初始化字符串的模式，然后使用该模式更快地执行搜索。 `... LIKE '%*`string`*%'`*`string`*



如果使用`*`col_name`* IS NULL`索引，则 使用的搜索会使用*`col_name`*索引。

没有覆盖子句中所有[`AND`](functions.html#operator_and)级别的 任何索引都 `WHERE`不会用于优化查询。换句话说，为了能够使用索引，必须在每个[`AND`](functions.html#operator_and)组中使用索引的前缀 。

以下`WHERE`子句使用索引：

```
...其中index_part1= 1 AND index_part2= 2 AND other_column= 3

    / * index= 1或index= 2 * /
...其中index= 1或A = 10 AND index= 2

    / *像“ index_part1='hello'” 一样优化* /
... WHERE index_part1='hello'AND index_part3= 5

    / *可以在index1但不可以在索引上使用索引，index2或者index3* /
...其中index1= 1 AND index2= 2 OR index1= 3 AND index3= 3;
```

这些`WHERE`子句 *不*使用索引：

```
    / * index_part1不使用* /
...其中index_part2= 1 AND index_part3= 2

    / * WHERE子句的两个部分均未使用索引* /
...其中index= 1或A = 10

    / *没有索引跨越所有行* /
...其中index_part1= 1或index_part2= 10
```

有时，即使索引可用，MySQL也不使用索引。发生这种情况的一种情况是，优化器估计使用索引将需要MySQL访问表中很大比例的行。（在这种情况下，表扫描可能会更快，因为它需要更少的查找。）但是，如果这样的查询`LIMIT`仅用于检索某些行，则MySQL仍会使用索引，因为它可以更快地找到索引。几行返回结果。

#### 哈希指数特征



哈希索引与刚刚讨论的索引具有一些不同的特征：

- 它们仅用于使用`=`or `<=>` 运算符的相等比较 （但*非常*快）。它们不用于比较运算符，例如`<`查找值范围的运算符 。依赖于这种单值查找类型的系统称为“ 键值存储 ”；要将MySQL用于此类应用程序，请尽可能使用哈希索引。
- 优化器无法使用哈希索引来加快`ORDER BY`操作速度 。（此索引类型不能用于按顺序搜索下一个条目。）
- MySQL无法确定两个值之间大约有多少行（范围优化器使用它来决定要使用哪个索引）。如果将`MyISAM`或 `InnoDB`表更改为哈希索引 `MEMORY`表，这可能会影响某些查询。
- 仅整个键可用于搜索行。（对于B树索引，键的任何最左边的前缀都可用于查找行。）

### 8.3.9索引扩展的使用



[`InnoDB`](innodb-storage-engine.html)通过将主键列附加到辅助索引来自动扩展每个辅助索引。考虑此表定义：

```
创建表t1（
  i1 INT NOT NULL默认值0，
  i2 INT NOT NULL DEFAULT 0，
  d DATE DEFAULT NULL，
  主键（i1，i2），
  索引k_d（d）
）ENGINE = InnoDB;
```

该表在列上定义了主键`(i1, i2)`。它还`k_d`在列上定义了辅助索引 `(d)`，但是在内部`InnoDB`扩展了该索引并将其视为列`(d, i1, i2)`。

在确定如何以及是否使用该索引时，优化器会考虑扩展二级索引的主键列。这可以导致更有效的查询执行计划和更好的性能。

优化器可以将扩展的辅助索引用于 `ref`，`range`和 `index_merge`索引访问，松散索引扫描访问，联接和排序优化以及 [`MIN()`](functions.html#function_min)/ [`MAX()`](functions.html#function_max) 优化。

以下示例显示了优化程序是否使用扩展二级索引如何影响执行计划。假设`t1`用以下行填充：

```
插入t1值
（1，1，'1998-01-01'），（1,2，'1999-01-01'），
（1，3，'2000-01-01'），（1，4，'2001-01-01'），
（1，5，'2002-01-01'），（2，1，'1998-01-01'），
（2，2，'1999-01-01'），（2，3，'2000-01-01'），
（2，4，'2001-01-01'），（2，5，'2002-01-01'），
（3，1，'1998-01-01'），（3，2，'1999-01-01'），
（3，3，'2000-01-01'），（3，4，'2001-01-01'），
（3，5，'2002-01-01'），（4，1，'1998-01-01'），
（4，2，'1999-01-01'），（4，3，'2000-01-01'），
（4，4，'2001-01-01'），（4，5，'2002-01-01'），
（5，1，'1998-01-01'），（5，2，'1999-01-01'），
（5，3，'2000-01-01'），（5，4，'2001-01-01'），
（5，5，'2002-01-01'）;
```

现在考虑以下查询：

```
从t1解释选择计数（*），其中i1 = 3 AND d ='2000-01-01'
```

执行计划取决于是否使用扩展索引。

当优化器不考虑索引扩展时，它将索引`k_d`视为`(d)`。 [`EXPLAIN`](sql-statements.html#explain)对于查询产生以下结果：

```
mysql> EXPLAIN SELECT COUNT(*) FROM t1 WHERE i1 = 3 AND d = '2000-01-01'\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表：t1
         类型：参考
可能的键：PRIMARY，k_d
          键：k_d
      key_len：4
          参考：const
         行数：5
        额外：在哪里使用；使用索引
```

当优化需要索引扩展到帐户，它把`k_d`作为`(d, i1, i2)`。在这种情况下，它可以使用最左边的索引前缀`(d, i1)`来产生更好的执行计划：

```
mysql> EXPLAIN SELECT COUNT(*) FROM t1 WHERE i1 = 3 AND d = '2000-01-01'\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表：t1
         类型：参考
可能的键：PRIMARY，k_d
          键：k_d
      key_len：8
          参考：const，const
         行数：1
        额外：使用索引
```

在这两种情况下，都`key`表明优化器将使用二级索引，`k_d`但是[`EXPLAIN`](sql-statements.html#explain)输出显示了使用扩展索引的这些改进：

- `key_len`从4个字节到8个字节去，表明键查找中使用的列`d` 和`i1`，而不仅仅是`d`。
- 该`ref`值从改变 `const`到`const,const` ，因为键查找使用两个关键部分，没有之一。
- 的`rows`计数降低从5到1，表明`InnoDB`应该需要检查更少的行，以产生结果。
- 该`Extra`值从变化 `Using where; Using index`到 `Using index`。这意味着可以仅使用索引读取行，而无需查阅数据行中的列。

使用扩展索引的优化器行为也可以通过以下方式看到[`SHOW STATUS`](sql-statements.html#show-status)：

```
冲洗表t1;
冲洗状态
从t1选择COUNT（*），其中i1 = 3 AND d ='2000-01-01';
显示状态，例如“ handler_read％”
```

前面的语句包括[`FLUSH TABLES`](sql-statements.html#flush-tables)和[`FLUSH STATUS`](sql-statements.html#flush-status) 刷新表缓存并清除状态计数器。

没有索引扩展名，将[`SHOW STATUS`](sql-statements.html#show-status)产生以下结果：

```
+ ----------------------- + ------- +
| 变量名| 价值|
+ ----------------------- + ------- +
| Handler_read_first | 0 |
| Handler_read_key | 1 |
| Handler_read_last | 0 |
| Handler_read_next | 5 |
| Handler_read_prev | 0 |
| Handler_read_rnd | 0 |
| Handler_read_rnd_next | 0 |
+ ----------------------- + ------- +
```

使用索引扩展，可[`SHOW STATUS`](sql-statements.html#show-status)产生此结果。该 [`Handler_read_next`](server-administration.html#statvar_Handler_read_next)值从5减少到1，表示可以更有效地使用索引：

```
+ ----------------------- + ------- +
| 变量名| 价值|
+ ----------------------- + ------- +
| Handler_read_first | 0 |
| Handler_read_key | 1 |
| Handler_read_last | 0 |
| Handler_read_next | 1 |
| Handler_read_prev | 0 |
| Handler_read_rnd | 0 |
| Handler_read_rnd_next | 0 |
+ ----------------------- + ------- +
```

系统变量 的`use_index_extensions`标志 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)允许控制在确定如何使用`InnoDB`表的二级索引时优化器是否考虑主键列 。默认情况下`use_index_extensions`启用。要检查禁用索引扩展的使用是否可以提高性能，请使用以下语句：

```
SET Optimizer_switch ='use_index_extensions = off';
```

优化程序对索引扩展的使用受制于对索引中关键部分的数量（16）和最大密钥长度（3072字节）的通常限制。

### 8.3.10优化器对生成的列索引的使用

MySQL支持在生成的列上建立索引。例如：

```
创建表t1（f1 INT，gc INT AS（f1 +1）存储，索引（gc））;
```

生成的列`gc`定义为表达式`f1 + 1`。该列也被索引，优化器可以在执行计划构建期间考虑该索引。在以下查询中，该 `WHERE`子句引用`gc` 并且优化器考虑该列上的索引是否产生更有效的计划：

```
选择*从t1到gc> 9;
```

即使在查询中没有按名称对这些列进行直接引用的情况下，优化器也可以使用生成的列上的索引来生成执行计划。会发生此如果 `WHERE`，`ORDER BY`或 `GROUP BY`条款是指一些索引生成列的定义相匹配的表达式。以下查询未直接引用，`gc` 但使用与以下定义匹配的表达式 `gc`：

```
选择*从t1到f1 + 1> 9;
```

优化器认识到表达式`f1 + 1`与的定义匹配`gc`并且`gc`被索引，因此它在执行计划构建期间会考虑该索引。您可以使用[`EXPLAIN`](sql-statements.html#explain)以下命令查看 ：

```
mysql> EXPLAIN SELECT * FROM t1 WHERE f1 + 1 > 9\G
*************************** 1.行******************** *******
           编号：1
  select_type：简单
        表：t1
   分区：NULL
         类型：范围
可能的键：gc
          键：gc
      key_len：5
          参考：NULL
         行数：1
     已过滤：100.00
        附加：使用索引条件
```

实际上，优化器已将表达式替换为与表达式`f1 + 1`匹配的已生成列的名称。在[`EXPLAIN`](sql-statements.html#explain) 由[`SHOW WARNINGS`](sql-statements.html#show-warnings)以下命令显示的扩展信息中可用的重写查询中也很明显：

```
mysql> SHOW WARNINGS\G
*************************** 1.行******************** *******
  级别：注意
   编号：1003
消息：/ * select＃1 * /选择`test`.t1.f1作为f1，test.t1.gc
         作为来自`test`.`t1`的`gc`的地方（`test`.`t1`.`gc`> 9）
```

以下限制和条件适用于优化器对生成的列索引的使用：

- 为了使查询表达式与生成的列定义匹配，该表达式必须相同并且其结果类型必须相同。例如，如果生成的列表达式为`f1 + 1`，如果查询使用`1 + f1`，或者`f1 + 1` （整数表达式）与字符串进行比较，则优化器将无法识别匹配项 。

- 优化适用于这些操作符： [`=`](functions.html#operator_equal)， [`<`](functions.html#operator_less-than)， [`<=`](functions.html#operator_less-than-or-equal)， [`>`](functions.html#operator_greater-than)， [`>=`](functions.html#operator_greater-than-or-equal)， [`BETWEEN`](functions.html#operator_between)，和 [`IN()`](functions.html#operator_in)。

  对于[`BETWEEN`](functions.html#operator_between)和 以外的运算符 [`IN()`](functions.html#operator_in)，可以用匹配的生成列替换任一个操作数。对于 [`BETWEEN`](functions.html#operator_between)和 [`IN()`](functions.html#operator_in)，只有第一个参数可以由匹配的生成的列替换，其他参数必须具有相同的结果类型。 [`BETWEEN`](functions.html#operator_between)并且 [`IN()`](functions.html#operator_in)尚不支持涉及JSON值的比较。

- 必须将生成的列定义为至少包含一个函数调用或前一项中提到的运算符之一的表达式。该表达式不能包含对另一列的简单引用。例如，`gc INT AS (f1) STORED`仅由列引用组成，因此`gc`不考虑索引on 。

- 为了将字符串与索引生成的列进行比较，索引生成的列从返回带引号的字符串的JSON函数计算值，[`JSON_UNQUOTE()`](functions.html#function_json-unquote)因此在列定义中需要从函数值中删除多余的引号。（为了将字符串直接与函数结果进行比较，JSON比较器会处理引号删除，但是对于索引查找不会发生这种情况。）例如，与其编写这样的列定义：

  ```
  doc_name TEXT AS（JSON_EXTRACT（jdoc，'$ .name'））已存储
  ```

  这样写：

  ```
  doc_name TEXT AS（JSON_UNQUOTE（JSON_EXTRACT（jdoc，'$ .name'）））已存储
  ```

  使用后一个定义，优化器可以为以下两个比较检测到匹配：

  ```
  ... JSON_EXTRACT（jdoc，'$ .name'）=' some_string'...
  ...哪里JSON_UNQUOTE（JSON_EXTRACT（jdoc，'$ .name'））=' some_string'...
  ```

  如果不在[`JSON_UNQUOTE()`](functions.html#function_json-unquote)列定义中，则优化器仅针对这些比较中的第一个比较检测到匹配项。

- 如果优化器无法选择所需的索引，则可以使用索引提示来强制优化器做出其他选择。

### 8.3.11从TIMESTAMP列进行索引查找



时间值[`TIMESTAMP`](data-types.html#datetime)作为UTC值存储在 列中，插入到[`TIMESTAMP`](data-types.html#datetime)列中或从列中检索的值 在会话时区和UTC之间转换。（这与[`CONVERT_TZ()`](functions.html#function_convert-tz)函数执行的转换类型相同 。如果会话时区为UTC，则实际上没有时区转换。）



由于诸如夏令时（DST）等本地时区更改的约定，UTC与非UTC时区之间的转换在两个方向上都不一对一。不同的UTC值在另一个时区可能不会不同。以下示例显示了不同的UTC值，它们在非UTC时区中变得相同：

```
mysql> CREATE TABLE tstable (ts TIMESTAMP);
mysql> SET time_zone = 'UTC'; -- insert UTC values
mysql> 
mysql>INSERT INTO tstable VALUES
       ('2018-10-28 00:30:00'),
       ('2018-10-28 01:30:00');SELECT ts FROM tstable;
+ --------------------- +
| ts |
+ --------------------- +
| 2018-10-28 00:30:00 |
| 2018-10-28 01:30:00 |
+ --------------------- +
mysql> SET time_zone = 'MET'; -- retrieve non-UTC values
mysql>SELECT ts FROM tstable;
+ --------------------- +
| ts |
+ --------------------- +
| 2018-10-28 02:30:00 |
| 2018-10-28 02:30:00 |
+ --------------------- +
```

注意

要使用诸如`'MET'`或的 命名时区`'Europe/Amsterdam'`，必须正确设置时区表。有关说明，请参见 [第5.1.13节“ MySQL服务器时区支持”](server-administration.html#time-zone-support)。

您会看到两个不同的UTC值在转换`'MET'`为时区时是相同的。对于特定的[`TIMESTAMP`](data-types.html#datetime)列查询，此现象可能导致不同的结果 ，具体取决于优化器是否使用索引来执行查询。

假设查询使用`WHERE`子句从前面显示的表中选择值，以在该`ts`列中搜索 单个特定值，例如用户提供的时间戳文字：

```
从tstable选择ts
ts =' literal';
```

进一步假设查询在以下条件下执行：

- 会话时区不是UTC，并且具有DST偏移。例如：

  ```
  SET time_zone ='MET';
  ```

- [`TIMESTAMP`](data-types.html#datetime)由于DST偏移 ，该列中存储的唯一UTC值在 会话时区中不是唯一的。（前面显示的示例说明了这种情况的发生。）

- 该查询指定了在会话时区中输入DST小时内的搜索值。

在这种情况下，`WHERE`对于未建立索引和建立索引的查找，子句中的比较以 不同的方式发生，并导致不同的结果：

- 如果没有索引或优化器无法使用它，则会在会话时区中进行比较。优化器执行表扫描，在其中检索每个 `ts`列值，将其从UTC转换为会话时区，然后将其与搜索值（也在会话时区中解释）进行比较：

  ```
  mysql> SELECT ts FROM tstable
         WHERE ts = '2018-10-28 02:30:00';
  + --------------------- +
  | ts |
  + --------------------- +
  | 2018-10-28 02:30:00 |
  | 2018-10-28 02:30:00 |
  + --------------------- +
  ```

  由于已存储的`ts`值已转换为会话时区，因此查询有可能返回两个时间戳值，这些时间戳值与UTC值不同，但在会话时区中相等：当时钟更改时，一个值发生在DST移位之前， DST移位后出现的一个值。

- 如果有可用的索引，则以UTC进行比较。优化器执行索引扫描，首先将搜索值从会话时区转换为UTC，然后将结果与UTC索引条目进行比较：

  ```
  mysql> ALTER TABLE tstable ADD INDEX (ts);
  mysql>SELECT ts FROM tstable
         WHERE ts = '2018-10-28 02:30:00';
  + --------------------- +
  | ts |
  + --------------------- +
  | 2018-10-28 02:30:00 |
  + --------------------- +
  ```

  在这种情况下，（转换后的）搜索值仅与索引条目匹配，并且由于不同存储的UTC值的索引条目也不同，因此搜索值只能匹配其中之一。

由于针对非索引和索引查找的优化器操作不同，因此在每种情况下查询都会产生不同的结果。非索引查找的结果将返回会话时区中所有匹配的值。索引查找不能这样做：

- 它在仅了解UTC值的存储引擎内执行。
- 对于映射到相同UTC值的两个不同的会话时区值，索引查找仅匹配相应的UTC索引条目，并且仅返回单行。

在前面的讨论中，存储在其中的数据集 `tstable`恰好由不同的UTC值组成。在这种情况下，所示形式的所有使用索引的查询最多匹配一个索引条目。

如果索引不是`UNIQUE`，则表（和索引）可以存储给定UTC值的多个实例。例如，该`ts`列可能包含UTC value的多个实例 `'2018-10-28 00:30:00'`。在这种情况下，使用索引的查询将返回它们中的每一个（转换为`'2018-10-28 02:30:00'`结果集中的MET值）。仍然使用索引的查询将转换后的搜索值与UTC索引条目中的单个值进行匹配，而不是将在会话时区中转换为搜索值的多个UTC值进行匹配。

如果返回`ts`在会话时区中匹配的所有值很重要，则解决方法是禁止使用带有`IGNORE INDEX`提示的索引：

```
mysql> SELECT ts FROM tstable
       IGNORE INDEX (ts)
       WHERE ts = '2018-10-28 02:30:00';
+ --------------------- +
| ts |
+ --------------------- +
| 2018-10-28 02:30:00 |
| 2018-10-28 02:30:00 |
+ --------------------- +
```

在其他情况下（例如使用[`FROM_UNIXTIME()`](functions.html#function_from-unixtime)和 [`UNIX_TIMESTAMP()`](functions.html#function_unix-timestamp)功能执行的转换），在两个方向上也都缺少时区转换的一对一映射 。请参见 [第12.6节“日期和时间函数”](functions.html#date-and-time-functions)。

## 8.4优化数据库结构

- [8.4.1优化数据大小](optimization.html#data-size)
- [8.4.2优化MySQL数据类型](optimization.html#optimize-data-types)
- [8.4.3优化许多表](optimization.html#optimize-multi-tables)
- [8.4.4 MySQL中内部临时表的使用](optimization.html#internal-temporary-tables)
- [8.4.5数据库和表数限制](optimization.html#database-count-limit)
- [8.4.6表格大小限制](optimization.html#table-size-limit)
- [8.4.7表列数和行大小的限制](optimization.html#column-count-limit)

在担任数据库设计师的角色中，寻找最有效的方式来组织架构，表和列。与调整应用程序代码时一样，您可以最大程度地减少I / O，将相关项目放在一起，并提前进行计划，以便随着数据量的增加而保持较高的性能。从高效的数据库设计开始，团队成员可以更轻松地编写高性能的应用程序代码，并使数据库很可能随着应用程序的发展和重写而持久。

### 8.4.1优化数据大小



设计表以最小化它们在磁盘上的空间。通过减少写入磁盘和从磁盘读取的数据量，这可以带来巨大的改进。较小的表通常需要较少的主内存，而在查询执行期间对它们的内容进行主动处理时。表数据的任何空间减少也会导致较小的索引，可以更快地对其进行处理。

MySQL支持许多不同的存储引擎（表类型）和行格式。对于每个表，您可以决定使用哪种存储和索引方法。为您的应用程序选择适当的表格式可以大大提高性能。请参阅 [第14章，*InnoDB存储引擎*](innodb-storage-engine.html)和 [第15章，*备用存储引擎*](storage-engines.html)。

通过使用此处列出的技术，可以提高表的性能，并最大程度地减少存储空间：

- [表格栏](optimization.html#data-size-table-columns)
- [行格式](optimization.html#data-size-row-format)
- [指标](optimization.html#data-size-indexes)
- [加入](optimization.html#data-size-joins)
- [正常化](optimization.html#data-size-normalization)

#### 表格栏

- 尽可能使用最有效（最小）的数据类型。MySQL具有许多专门的类型，可以节省磁盘空间和内存。例如，如果可能，使用较小的整数类型以获得较小的表。 [`MEDIUMINT`](data-types.html#integer-types)通常比[`INT`](data-types.html#integer-types)由于 [`MEDIUMINT`](data-types.html#integer-types)列占用的空间少25％更好。
- `NOT NULL`尽可能 声明列。通过更好地使用索引并消除测试每个值是否为的开销，可以使SQL操作更快`NULL`。您还节省了一些存储空间，每列一位。如果您确实需要`NULL`表中的值，请使用它们。只要避免使用默认设置，该默认设置允许 `NULL`每一列中的值。

#### 行格式

- `InnoDB`表格`DYNAMIC`默认使用行格式创建 。要使用以外的行格式`DYNAMIC`，请配置 [`innodb_default_row_format`](innodb-storage-engine.html#sysvar_innodb_default_row_format)，或`ROW_FORMAT`在[`CREATE TABLE`](sql-statements.html#create-table)or [`ALTER TABLE`](sql-statements.html#alter-table)语句中显式指定选项。

  紧凑的行格式系列（包括 `COMPACT`，`DYNAMIC`和`COMPRESSED`）减少了行存储空间，但增加了某些操作的CPU使用率。如果您的工作量是典型的工作，并且受缓存命中率和磁盘速度的限制，则可能会更快。如果在极少数情况下受CPU速度的限制，则它可能会变慢。

  [`CHAR`](data-types.html#char)当使用可变长度字符集（例如`utf8mb3`或）时 ，紧凑的行格式系列还可以优化 列存储 `utf8mb4`。使用`ROW_FORMAT=REDUNDANT`， 占×字符集的最大字节长度。许多语言可以主要使用单字节字符编写 ，因此固定的存储长度通常会浪费空间。使用紧凑的行格式系列，可以 在到的范围内分配可变的存储量 `CHAR(*`N`*)`*`N`*`utf8``InnoDB`*`N`**`N`*×通过删除尾随空格来删除这些列的字符集的最大字节长度。*`N`*在典型情况下，最小存储长度为 字节，以方便就地更新。有关更多信息，请参见 [第14.11节“ InnoDB行格式”](innodb-storage-engine.html#innodb-row-format)。

- 通过以压缩形式存储表数据来进一步减少空间，请`ROW_FORMAT=COMPRESSED`在创建`InnoDB`表时 指定 ，或在现有表上运行 [**myisampack**](programs.html#myisampack)命令 `MyISAM`。（`InnoDB`压缩表是可读写的，而`MyISAM`压缩表是只读的。）

- 对于`MyISAM`表中，如果没有任何可变长度列（[`VARCHAR`](data-types.html#char)， [`TEXT`](data-types.html#blob)，或 [`BLOB`](data-types.html#blob)列），一个固定大小的行格式被使用。这样比较快，但可能会浪费一些空间。请参见[第15.2.3节“ MyISAM表存储格式”](storage-engines.html#myisam-table-formats)。即使您有[`VARCHAR`](data-types.html#char)带有[`CREATE TABLE`](sql-statements.html#create-table)选项的 列，也可以暗示您希望具有固定长度的行`ROW_FORMAT=FIXED`。

#### 指标

- 表的主索引应尽可能短。这使得识别每一行变得容易且有效。对于`InnoDB`表，主键列在每个辅助索引条目中重复，因此如果您有许多辅助索引，则较短的主键可节省大量空间。
- 仅创建提高查询性能所需的索引。索引很适合检索，但是会降低插入和更新操作的速度。如果您主要通过搜索列的组合来访问表，请在列上创建单个复合索引，而不是为每个列创建单独的索引。索引的第一部分应该是最常用的列。如果从表中选择时*总是*使用许多列，则索引中的第一列应该是重复次数最多的列，以更好地压缩索引。
- 如果长字符串列很可能在第一个字符数上具有唯一的前缀，则最好使用MySQL支持在该列的最左侧创建索引来仅对此前缀进行索引（请参阅[第13.1.14节） ，“ CREATE INDEX语句”](sql-statements.html#create-index)）。索引越短越快，这不仅是因为它们需要较少的磁盘空间，而且还因为它们还会使索引缓存中的命中次数增加，从而减少了磁盘寻道。请参见 [第5.1.1节“配置服务器”](server-administration.html#server-configuration)。

#### 加入

- 在某些情况下，将经常扫描的表分成两部分可能会有所帮助。如果它是动态格式表，并且可以使用较小的静态格式表（在扫描表时可以用来查找相关行），则尤其如此。
- 在具有相同数据类型的不同表中声明具有相同信息的列，以加快基于相应列的联接。
- 使列名保持简单，以便您可以在不同的表中使用相同的名称并简化联接查询。例如，在名为的表中`customer`，使用列名`name`代替 `customer_name`。为了使您的名称可移植到其他SQL Server中，请考虑使名称短于18个字符。

#### 正常化

- 通常，请尝试使所有数据都保持非冗余状态（遵守数据库理论中所谓的 第三范式）。不必重复冗长的值（例如名称和地址），而是给它们分配唯一的ID，在多个较小的表中根据需要重复这些ID，然后通过引用join子句中的ID在查询中联接这些表。
- 如果速度比磁盘空间和保留多个数据副本的维护成本更为重要，例如在商业智能场景中，您分析大型表中的所有数据，则可以放宽规范化规则，复制信息或创建汇总表以提高速度。

### 8.4.2优化MySQL数据类型

- [8.4.2.1优化数值数据](optimization.html#optimize-numeric)
- [8.4.2.2优化字符和字符串类型](optimization.html#optimize-character)
- [8.4.2.3优化BLOB类型](optimization.html#optimize-blob)
- [8.4.2.4使用过程分析](optimization.html#procedure-analyse)

#### 8.4.2.1优化数值数据



- 对于可以表示为字符串或数字的唯一ID或其他值，与字符串列相比，首选数字列。由于大数值可以比相应字符串存储在更少的字节中，因此它传输速度更快，并且占用更少的内存来进行比较。
- 如果使用的是数字数据，则在许多情况下（通过实时连接）从数据库访问信息的访问要比访问文本文件更快。数据库中的信息可能以比文本文件更紧凑的格式存储，因此访问它涉及的磁盘访问较少。您还可以将代码保存在应用程序中，因为可以避免解析文本文件来查找行边界和列边界。

#### 8.4.2.2优化字符和字符串类型



对于字符和字符串列，请遵循以下准则：

- 当您不需要特定于语言的排序规则功能时，请使用二进制排序规则顺序进行快速比较和排序操作。您可以使用 [`BINARY`](functions.html#operator_binary)运算符在特定查询中使用二进制排序规则。
- 比较来自不同列的值时，请尽可能使用相同的字符集和排序规则声明这些列，以避免在运行查询时进行字符串转换。
- 对于小于8KB的列值，请使用二进制 `VARCHAR`而不是 `BLOB`。该`GROUP BY` 和`ORDER BY`条款可以生成临时表，这些临时表可以使用的 `MEMORY`存储引擎，如果原来的表不包含任何`BLOB` 列。
- 如果表包含名称和地址之类的字符串列，但是许多查询未检索到这些列，请考虑将字符串列拆分为单独的表，并在必要时使用带有外键的联接查询。当MySQL从一行中检索任何值时，它将读取包含该行所有列（可能还有其他相邻行）的数据块。保持每行较小，仅使用最常使用的列，可使更多行适合每个数据块。这样的紧凑表减少了常见查询的磁盘I / O和内存使用量。
- 当您将随机生成的值用作`InnoDB`表中的主键时，请尽可能在其前面加上一个升值，例如当前日期和时间。当连续的主值在物理上彼此靠近存储时，`InnoDB`可以更快地插入和检索它们。
- 有关为什么数字列通常比等效的字符串列更可取的原因[，](optimization.html#optimize-numeric)请参见[第8.4.2.1节“优化数字数据”](optimization.html#optimize-numeric)。

#### 8.4.2.3优化BLOB类型



- 当存储包含文本数据的大blob时，请考虑首先对其进行压缩。当整个表由`InnoDB`或压缩时，请勿使用此技术 `MyISAM`。
- 对于具有多个列的表，为了减少不使用BLOB列的查询的内存需求，请考虑将BLOB列拆分为一个单独的表，并在需要时使用联接查询对其进行引用。
- 由于检索和显示BLOB值的性能要求可能与其他数据类型有很大不同，因此可以将特定于BLOB的表放在不同的存储设备上，甚至放在单独的数据库实例上。例如，检索BLOB可能需要读取较大的顺序磁盘，这比传统的[SSD](glossary.html#glos_ssd)更适合传统硬盘驱动 [器](glossary.html#glos_ssd)。
- 有关为什么有时有时使用二进制列优于等效的BLOB列的原因[，](optimization.html#optimize-character)请参见[第8.4.2.2节“优化字符和字符串类型”](optimization.html#optimize-character)`VARCHAR`。
- 您可以将列值的哈希存储在单独的列中，对该列进行索引并在查询中测试该哈希值，而不是针对非常长的文本字符串测试是否相等。（使用`MD5()`或 `CRC32()`函数来生成哈希值。）由于哈希函数可以为不同的输入生成重复的结果，因此您仍然在查询中包括一个子句 以防止错误匹配。性能优势来自较小，易于扫描的哈希值索引。 `AND *`blob_column`* = *`long_string_value`*`

#### 8.4.2.4使用过程分析

```
ANALYSE([*`max_elements`*[,*`max_memory`*]])
```



注意

`PROCEDURE ANALYSE()` 自MySQL 5.7.18起已弃用，并在MySQL 8.0中删除。

`ANALYSE()`检查查询的结果并返回结果分析，该分析建议每列的最佳数据类型，这可能有助于减小表的大小。要获得此分析，`PROCEDURE ANALYSE`请在[`SELECT`](sql-statements.html#select)语句末尾 附加：

```
选择...从...位置...过程分析（[ max_elements，[ max_memory]]）
```

例如：

```
从表1 PROCEDURE ANALYSE（10，2000）中选择SELECT col1，col2;
```

结果显示查询返回的值的一些统计信息，并为列提供最佳数据类型。这对于检查现有表或导入新数据后会很有帮助。您可能需要为参数尝试不同的设置，以便在不合适时`PROCEDURE ANALYSE()`不建议 [`ENUM`](data-types.html#enum)数据类型。

参数是可选的，并按如下方式使用：

- *`max_elements`*（默认值为256）是`ANALYSE()`每列中可注意的最大不重复值数量 。用于`ANALYSE()`检查最佳数据类型是否为类型 [`ENUM`](data-types.html#enum); 如果有多个*`max_elements`*不同的值，则[`ENUM`](data-types.html#enum)不是建议的类型。
- *`max_memory`*（默认值8192）是`ANALYSE()`在尝试查找所有不同值时应为每列分配的最大内存量 。

`PROCEDURE`语句中不允许 使用子句 [`UNION`](sql-statements.html#union)。

### 8.4.3优化许多表

- [8.4.3.1 MySQL如何打开和关闭表](optimization.html#table-cache)
- [8.4.3.2在同一数据库中创建多个表的缺点](optimization.html#creating-many-tables)



一些用于保持单个查询快速运行的技术涉及在多个表之间拆分数据。当表的数量达到数千甚至数百万时，处理所有这些表的开销将成为新的性能考虑因素。

#### 8.4.3.1 MySQL如何打开和关闭表



当执行[**mysqladmin status**](programs.html#mysqladmin) 命令时，应该看到类似以下的内容：

```
正常运行时间：426个运行线程：1个问题：11082
重新加载：1张开放桌子：12张
```

`Open tables`如果表少于12个，则12 的值可能会令人困惑。

MySQL是多线程的，因此可能有许多客户端同时为给定表发出查询。为了最大程度地减少同一张表上具有不同状态的多个客户端会话的问题，每个并发会话会独立打开该表。这会使用额外的内存，但通常会提高性能。对于`MyISAM`表，每个打开表的客户端的数据文件都需要一个额外的文件描述符。（相比之下，索引文件描述符在所有会话之间共享。）

该[`table_open_cache`](server-administration.html#sysvar_table_open_cache)和 [`max_connections`](server-administration.html#sysvar_max_connections)系统变量影响服务器保持打开的文件的最大数量。如果增加这两个值中的一个或两个，则可能会遇到操作系统对打开文件描述符的每个进程数施加的限制。许多方法允许您增加打开文件的限制，尽管方法因系统而异。请查阅您的操作系统文档，以确定是否可以增加限制以及如何增加限制。

[`table_open_cache`](server-administration.html#sysvar_table_open_cache)与有关[`max_connections`](server-administration.html#sysvar_max_connections)。例如，对于200个并发运行的连接，指定的表缓存大小至少为，其中 是您执行的任何查询中每个联接的最大表数。您还必须为临时表和文件保留一些额外的文件描述符。 `200 * *`N`*`*`N`*

确保您的操作系统可以处理该[`table_open_cache`](server-administration.html#sysvar_table_open_cache)设置所隐含的打开文件描述符的数量 。如果 [`table_open_cache`](server-administration.html#sysvar_table_open_cache)设置得太高，MySQL可能会用尽文件描述符，并表现出诸如拒绝连接或无法执行查询之类的症状。

还应考虑到`MyISAM` 存储引擎对于每个唯一的打开表都需要两个文件描述符。对于分区`MyISAM`表，打开的表的每个分区都需要两个文件描述符。（`MyISAM`打开分区表时，无论是否实际使用给定的分区，它都会打开该表的每个分区。请参见 [MyISAM和分区文件描述符的用法](partitioning.html#partitioning-limitations-myisam-file-descriptors)。）要增加可用于MySQL的文件描述符的数量，请设置[`open_files_limit`](server-administration.html#sysvar_open_files_limit) 系统变量。请参见 [第B.4.2.17节“找不到文件和类似错误”](error-handling.html#not-enough-file-handles)。

打开表的缓存保持在[`table_open_cache`](server-administration.html#sysvar_table_open_cache)条目级别 。服务器在启动时自动调整缓存大小。要显式设置大小，请[`table_open_cache`](server-administration.html#sysvar_table_open_cache)在启动时设置 系统变量。MySQL可能会临时打开更多表来执行查询，如本节后面所述。

在以下情况下，MySQL关闭未使用的表并将其从表缓存中删除：

- 当缓存已满并且线程尝试打开不在缓存中的表时。
- 当高速缓存包含多个[`table_open_cache`](server-administration.html#sysvar_table_open_cache)条目并且高速缓存中 的表不再被任何线程使用时。
- 当进行表刷新操作时。当有人发出[`FLUSH TABLES`](sql-statements.html#flush-tables)语句或执行 [**mysqladmin flush-tables**](programs.html#mysqladmin)或 [**mysqladmin refresh**](programs.html#mysqladmin)命令时，会发生这种情况。

当表高速缓存填满时，服务器将使用以下过程找到要使用的高速缓存条目：

- 从最近最少使用的表开始，释放当前未使用的表。
- 如果必须打开一个新表，但是缓存已满，并且无法释放任何表，则根据需要临时扩展缓存。当缓存处于临时扩展状态并且表从使用状态变为未使用状态时，表将关闭并从缓存中释放。

`MyISAM`将为每个并发访问打开 一个表。这意味着如果两个线程访问同一个表，或者如果一个线程在同一查询中两次访问该表（例如，通过将表自身连接），则该表需要打开两次。每个并发打开都需要在表缓存中有一个条目。任何`MyISAM`表的第一次打开都 需要两个文件描述符：一个用于数据文件，一个用于索引文件。该表的其他每次使用仅占用数据文件的一个文件描述符。索引文件描述符在所有线程之间共享。

如果要使用该语句打开表，则会为该线程分配专用的表对象。该表对象不与其他线程共享，并且在线程调用或线程终止之前不会关闭。发生这种情况时，会将表放回表高速缓存中（如果高速缓存未满）。请参见 [第13.2.4节“ HANDLER语句”](sql-statements.html#handler)。 `HANDLER *`tbl_name`* OPEN``HANDLER *`tbl_name`* CLOSE`

要确定表缓存是否太小，请检查 [`Opened_tables`](server-administration.html#statvar_Opened_tables)状态变量，该变量指示自服务器启动以来表打开操作的数量：

```
mysql> SHOW GLOBAL STATUS LIKE 'Opened_tables';
+ --------------- ++ -------- +
| 变量名| 价值|
+ --------------- ++ -------- +
| Opened_tables | 2741 |
+ --------------- ++ -------- +
```

如果该值很大或迅速增加，即使您没有发出很多[`FLUSH TABLES`](sql-statements.html#flush-tables)语句，也请[`table_open_cache`](server-administration.html#sysvar_table_open_cache)在服务器启动时增加该 值。

#### 8.4.3.2在同一数据库中创建多个表的缺点



如果`MyISAM`同一数据库目录中有许多表，则打开，关闭和创建操作的速度很慢。如果[`SELECT`](sql-statements.html#select) 在许多不同的表上执行语句，则表高速缓存已满时会产生一些开销，因为对于必须打开的每个表，必须关闭另一个表。您可以通过增加表缓存中允许的条目数来减少此开销。

### 8.4.4 MySQL中内部临时表的使用



在某些情况下，服务器在处理语句时会创建内部临时表。用户无法直接控制何时发生这种情况。

服务器在以下条件下创建临时表：

- [`UNION`](sql-statements.html#union) 语句的 评估，但稍后会有一些例外。
- 评估某些视图，例如使用`TEMPTABLE`算法 [`UNION`](sql-statements.html#union)或聚合的视图 。
- 派生表的评估（请参见 [第13.2.10.8节“派生表”](sql-statements.html#derived-tables)）。
- 为子查询或半联接实现创建的表（请参见 [第8.2.2节“优化子查询，派生表和视图引用”](optimization.html#subquery-optimization)）。
- 评估包含一个`ORDER BY`子句和一个不同`GROUP BY`子句，或者其中的`ORDER BY`或`GROUP BY`包含联接队列中第一个表以外的表中的列的语句。
- 评价`DISTINCT`结合 `ORDER BY`可能需要一个临时表。
- 对于使用`SQL_SMALL_RESULT` 修饰符的查询，MySQL使用内存中临时表，除非查询还包含需要磁盘存储的元素（稍后描述）。
- 为了评估 [`INSERT ... SELECT`](sql-statements.html#insert-select)从同一表中选择并插入到同一表中的语句，MySQL创建了一个内部临时表来保存中的行 [`SELECT`](sql-statements.html#select)，然后将这些行插入目标表中。请参见 [第13.2.5.1节“ INSERT ... SELECT语句”](sql-statements.html#insert-select)。
- 评估多表 [`UPDATE`](sql-statements.html#update)语句。
- [`GROUP_CONCAT()`](functions.html#function_group-concat) 或[`COUNT(DISTINCT)`](functions.html#function_count) 表达式的 评估。

要确定一条语句是否需要一个临时表，请使用 [`EXPLAIN`](sql-statements.html#explain)并检查该 `Extra`列是否显示 `Using temporary`（请参见 [第8.8.1节“使用EXPLAIN优化查询”](optimization.html#using-explain)）。对于派生或具体化的临时表，`EXPLAIN` 不一定会说`Using temporary`。

一些查询条件阻止使用内存中的临时表，在这种情况下，服务器将使用磁盘上的表：

- 表格中 存在[`BLOB`](data-types.html#blob)或 [`TEXT`](data-types.html#blob)列。这包括具有字符串值的用户定义变量，因为它们分别被视为二进制或非二进制字符串，因此将其视为 [`BLOB`](data-types.html#blob)或 [`TEXT`](data-types.html#blob)列。
- [`SELECT`](sql-statements.html#select)如果使用[`UNION`](sql-statements.html#union)或 ，则列表中 存在最大长度大于512（字符串为二进制字符串，非二进制为字符）的任何字符串列[`UNION ALL`](sql-statements.html#union) 。
- 的[`SHOW COLUMNS`](sql-statements.html#show-columns)和 [`DESCRIBE`](sql-statements.html#describe)语句中使用 `BLOB`作为用于某些列的类型，从而用于结果的临时表是磁盘上的表。

服务器不会将临时表用于[`UNION`](sql-statements.html#union)满足某些条件的 语句。而是从临时表创建中仅保留执行结果列类型转换所需的数据结构。该表未完全实例化，并且没有向其写入或读取任何行；行直接发送到客户端。结果是减少了内存和磁盘需求，并减少了将第一行发送给客户端之前的延迟，因为服务器不必等到最后一个查询块执行完毕。[`EXPLAIN`](sql-statements.html#explain)优化器跟踪输出反映了这种执行策略： `UNION RESULT` 查询块不存在，因为该块对应于从临时表中读取的部分。

这些条件`UNION`不带临时表即可进行评估：

- 工会是`UNION ALL`，不是 `UNION`或`UNION DISTINCT`。
- 没有全局`ORDER BY`子句。
- 联合不是`{INSERT | REPLACE} ... SELECT ...` 语句的顶级查询块 。

#### 内部临时表存储引擎

内部临时表可以保存在内存中并由`MEMORY`存储引擎处理，或者由`InnoDB`或 `MyISAM`存储引擎存储在磁盘上。

如果内部临时表被创建为内存表，但是又太大了，MySQL会自动将其转换为磁盘表。内存中临时表的最大大小由[`tmp_table_size`](server-administration.html#sysvar_tmp_table_size)或 [`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size)值定义 ，以较小者为准。这与使用`MEMORY`显式创建的表 不同 [`CREATE TABLE`](sql-statements.html#create-table)。对于此类表，仅[`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size) 变量确定表可以增长到多大，并且不转换为磁盘格式。

该 [`internal_tmp_disk_storage_engine`](server-administration.html#sysvar_internal_tmp_disk_storage_engine) 变量定义服务器用于管理磁盘内部临时表的存储引擎。允许的值为 `INNODB`（默认值）和 `MYISAM`。

注意

使用时 [`internal_tmp_disk_storage_engine=INNODB`](server-administration.html#sysvar_internal_tmp_disk_storage_engine)，生成超过[`InnoDB`行或列限制](innodb-storage-engine.html#innodb-limits)的磁盘内部临时表的查询 [将](innodb-storage-engine.html#innodb-limits)返回行大小太大或列太多 错误。解决方法是设置 [`internal_tmp_disk_storage_engine`](server-administration.html#sysvar_internal_tmp_disk_storage_engine) 为`MYISAM`。

在内存或磁盘中创建内部临时表时，服务器将增加该 [`Created_tmp_tables`](server-administration.html#statvar_Created_tmp_tables)值。在磁盘上创建内部临时表时，服务器将增加该 [`Created_tmp_disk_tables`](server-administration.html#statvar_Created_tmp_disk_tables) 值。如果磁盘上创建了太多内部临时表，请考虑增加 [`tmp_table_size`](server-administration.html#sysvar_tmp_table_size)和 [`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size)设置。

#### 内部临时表存储格式

内存中临时表由`MEMORY`存储引擎管理，该 引擎使用固定长度的行格式。`VARCHAR`和 `VARBINARY`列值被填充为最大列长度，实际上将它们存储为 `CHAR`和`BINARY`列。

磁盘上的临时表由`InnoDB`或`MyISAM`存储引擎管理 （取决于 [`internal_tmp_disk_storage_engine`](server-administration.html#sysvar_internal_tmp_disk_storage_engine) 设置）。两个引擎都使用动态宽度行格式存储临时表。列仅占用所需的存储空间，与使用固定长度行的磁盘表相比，这减少了磁盘I / O，空间需求和处理时间。

对于最初在内存中创建内部临时表然后将其转换为磁盘上表的语句，跳过转换步骤并在磁盘上开始创建表可能会获得更好的性能。该 [`big_tables`](server-administration.html#sysvar_big_tables)变量可用于强制内部临时表进行磁盘存储。

### 8.4.5数据库和表数限制



MySQL对数据库的数量没有限制。基础文件系统可能对目录数量有所限制。

MySQL对表的数量没有限制。基础文件系统可能会对表示表的文件数量有所限制。各个存储引擎可能会强加特定于引擎的约束。`InnoDB`最多允许40亿张桌子。

### 8.4.6表格大小限制



MySQL数据库的有效最大表大小通常由操作系统对文件大小的限制来确定，而不是由MySQL内部限制来确定。有关操作系统文件大小限制的最新信息，请参阅特定于您的操作系统的文档。

Windows用户，请注意，FAT和VFAT（FAT32）都 *没有*被考虑用于MySQL的生产使用。请改用NTFS。

如果遇到全表错误，则可能由于多种原因导致它发生：

- 磁盘可能已满。

- 您正在使用`InnoDB`表，并且`InnoDB`表空间文件中的空间已用完。最大表空间大小也是表的最大大小。有关表空间大小的限制，请参见 [第14.23节“ InnoDB限制”](innodb-storage-engine.html#innodb-limits)。

  通常，对于大于1TB的表，建议将表划分为多个表空间文件。

- 您已达到操作系统文件大小限制。例如，您`MyISAM`在操作系统上使用的表仅支持最大2GB的文件，而数据文件或索引文件已达到此限制。

- 您正在使用一个`MyISAM`表，并且该表所需的空间超出了内部指针大小所允许的空间。`MyISAM`允许数据和索引文件默认增加到256TB，但是此限制可以更改为最大允许大小65,536TB（256 7 − 1字节）。

  如果您需要一个`MyISAM`大于默认限制的表，并且您的操作系统支持大文件，则该[`CREATE TABLE`](sql-statements.html#create-table) 语句支持`AVG_ROW_LENGTH`和 `MAX_ROWS`选项。请参见 [第13.1.18节“ CREATE TABLE语句”](sql-statements.html#create-table)。服务器使用这些选项来确定允许表的大小。

  如果指针大小对于现有表而言太小，则可以使用更改选项[`ALTER TABLE`](sql-statements.html#alter-table)以增加表的最大允许大小。请参见[第13.1.8节“ ALTER TABLE语句”](sql-statements.html#alter-table)。

  ```
  ALTER TABLE tbl_nameMAX_ROWS = 1000000000 AVG_ROW_LENGTH = nnn;
  ```

  您`AVG_ROW_LENGTH`只需为带有[`BLOB`](data-types.html#blob)或 [`TEXT`](data-types.html#blob)列的表指定；在这种情况下，MySQL无法仅根据行数来优化所需的空间。

  要更改`MyISAM`表的默认大小限制 ，请设置 [`myisam_data_pointer_size`](server-administration.html#sysvar_myisam_data_pointer_size)，它设置用于内部行指针的字节数。如果未指定`MAX_ROWS` 选项，则该值用于设置新表的指针大小。的值 [`myisam_data_pointer_size`](server-administration.html#sysvar_myisam_data_pointer_size) 可以是2到7。值4允许最大4GB的表；值6允许最多256TB的表。

  您可以使用以下语句检查最大数据和索引大小：

  ```
  从db_name“ tbl_name”开始显示表状态；
  ```

  您也可以使用[**myisamchk -dv / path / to / table-index-file**](programs.html#myisamchk)。请参见 [第13.7.5节“ SHOW语句”](sql-statements.html#show)或[第4.6.3节“ **myisamchk** -MyISAM表维护实用程序”](programs.html#myisamchk)。

  解决`MyISAM`表的文件大小限制的其他方法 如下：

  - 如果您的大表是只读的，则可以使用 [**myisampack对其**](programs.html#myisampack)进行压缩。 [**myisampack**](programs.html#myisampack)通常会将表压缩至少50％，因此实际上您可以拥有更大的表。[**myisampack**](programs.html#myisampack)还可以将多个表合并为一个表。请参见 [第4.6.5节“ **myisampack-**生成压缩的只读MyISAM表”](programs.html#myisampack)。
  - MySQL包含一个`MERGE`库，使您能够处理`MyISAM`具有与单个`MERGE`表相同结构的表的集合 。请参见[第15.7节“ MERGE存储引擎”](storage-engines.html#merge-storage-engine)。

- 您正在使用`MEMORY` （`HEAP`）存储引擎；在这种情况下，您需要增加[`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size)系统变量的值 。请参见[第5.1.7节“服务器系统变量”](server-administration.html#server-system-variables)。

### 8.4.7表列数和行大小的限制

本节描述对表中的列数和单个行的大小的限制。

- [列数限制](optimization.html#column-count-limits)
- [行大小限制](optimization.html#row-size-limits)

#### 列数限制



MySQL对每个表有4096列的硬限制，但是对于给定的表，有效最大值可能会更少。确切的列限制取决于几个因素：

- 一个表的最大行大小限制了列的数量（可能还有大小），因为所有列的总长度不能超过该大小。请参阅 [行大小限制](optimization.html#row-size-limits)。
- 单个列的存储要求限制了给定最大行大小内的列数。某些数据类型的存储要求取决于存储引擎，存储格式和字符集等因素。请参见[第11.7节“数据类型存储要求”](data-types.html#storage-requirements)。
- 存储引擎可能会施加其他限制表列计数的限制。例如， [`InnoDB`](innodb-storage-engine.html)每个表的限制为1017列。请参见[第14.23节“ InnoDB限制”](innodb-storage-engine.html#innodb-limits)。有关其他存储引擎的信息，请参见 [第15章，*备用存储引擎*](storage-engines.html)。
- 每个表都有一个`.frm`包含表定义的文件。该定义以可能影响表中允许的列数的方式影响此文件的内容。请参见 [.frm文件结构施加的限制](sql-statements.html#limits-frm-file)。

#### 行大小限制



给定表的最大行大小由几个因素决定：

- MySQL表的内部表示具有65,535字节的最大行大小限制，即使存储引擎能够支持更大的行。 [`BLOB`](data-types.html#blob)和 [`TEXT`](data-types.html#blob)列仅有助于朝向行大小限制9〜12字节，因为它们的内容是从该行的其余部分分开存储。

- `InnoDB` 对于4KB，8KB，16KB和32KB [`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size) 设置，表 的最大行大小（适用于本地存储在数据库页面内的数据）略小于页面的一半 。例如，对于默认的16KB `InnoDB`页面大小，最大行大小略小于8KB 。对于64KB页面，最大行大小略小于16KB。请参见 [第14.23节“ InnoDB限制”](innodb-storage-engine.html#innodb-limits)。

  如果包含 [可变长度列](glossary.html#glos_variable_length_type)的`InnoDB` 行超过最大行大小，请`InnoDB`选择可变长度列进行外部页外存储，直到该行适合`InnoDB` 行大小限制。对于行外存储的变长列，本地存储的数据量因行格式而异。有关更多信息，请参见 [第14.11节“ InnoDB行格式”](innodb-storage-engine.html#innodb-row-format)。

- 不同的存储格式使用不同数量的页面标题和尾部数据，这会影响行可用的存储量。

  - 有关`InnoDB`行格式的信息，请参见[第14.11节“ InnoDB行格式”](innodb-storage-engine.html#innodb-row-format)。
  - 有关`MyISAM` 存储格式的信息，请参见 [第15.2.3节“ MyISAM表存储格式”](storage-engines.html#myisam-table-formats)。

##### 行大小限制示例

- 的65,535字节MySQL的最大行大小限制证明在下面`InnoDB` 和`MyISAM`实施例。不管存储引擎如何，都会强制执行该限制，即使存储引擎可能能够支持更大的行。

  ```
  mysql> CREATE TABLE t (a VARCHAR(10000), b VARCHAR(10000),
         c VARCHAR(10000), d VARCHAR(10000), e VARCHAR(10000),
         f VARCHAR(10000), g VARCHAR(6000)) ENGINE=InnoDB CHARACTER SET latin1;
  错误1118（42000）：行大小太大。已使用的最大行大小
  表类型（不计BLOB）为65535。这包括存储开销，
  检查手册。您必须将某些列更改为TEXT或BLOB
  ```

  ```
  mysql> CREATE TABLE t (a VARCHAR(10000), b VARCHAR(10000),
         c VARCHAR(10000), d VARCHAR(10000), e VARCHAR(10000),
         f VARCHAR(10000), g VARCHAR(6000)) ENGINE=MyISAM CHARACTER SET latin1;
  错误1118（42000）：行大小太大。已使用的最大行大小
  表类型（不计BLOB）为65535。这包括存储开销，
  检查手册。您必须将某些列更改为TEXT或BLOB
  ```

  在以下`MyISAM`示例中，更改列可[`TEXT`](data-types.html#blob) 避免行数限制为65,535字节，并允许操作成功，因为 [`BLOB`](data-types.html#blob)和 [`TEXT`](data-types.html#blob)列仅对行大小贡献9到12个字节。

  ```
  mysql> CREATE TABLE t (a VARCHAR(10000), b VARCHAR(10000),
         c VARCHAR(10000), d VARCHAR(10000), e VARCHAR(10000),
         f VARCHAR(10000), g TEXT(6000)) ENGINE=MyISAM CHARACTER SET latin1;
  查询正常，受影响的0行（0.02秒）
  ```

  该`InnoDB` 表的操作成功，因为更改列 [`TEXT`](data-types.html#blob)可以避免MySQL 65,535字节的行大小限制，而`InnoDB` 变长列的页外存储可以避免 `InnoDB`行大小限制。

  ```
  mysql> CREATE TABLE t (a VARCHAR(10000), b VARCHAR(10000),
         c VARCHAR(10000), d VARCHAR(10000), e VARCHAR(10000),
         f VARCHAR(10000), g TEXT(6000)) ENGINE=InnoDB CHARACTER SET latin1;
  查询正常，受影响的0行（0.02秒）
  ```

- 可变长度列的存储包括长度字节，该长度字节计入行大小。例如，一 [`VARCHAR(255) CHARACTER SET utf8mb3`](data-types.html#char)列需要两个字节来存储值的长度，因此每个值最多可以占用767个字节。

  创建表的语句`t1` 成功，因为列需要32,765 + 2字节和32,766 + 2字节，这在最大行大小65,535字节之内：

  ```
  mysql> CREATE TABLE t1
         (c1 VARCHAR(32765) NOT NULL, c2 VARCHAR(32766) NOT NULL)
         ENGINE = InnoDB CHARACTER SET latin1;
  查询正常，受影响的0行（0.02秒）
  ```

  创建表的语句`t2`失败，因为尽管列长度在最大长度65,535字节以内，但仍需要两个额外的字节来记录该长度，这会导致行大小超过65,535字节：

  ```
  mysql> CREATE TABLE t2
         (c1 VARCHAR(65535) NOT NULL)
         ENGINE = InnoDB CHARACTER SET latin1;
  错误1118（42000）：行大小太大。已使用的最大行大小
  表类型（不计BLOB）为65535。这包括存储开销，
  检查手册。您必须将某些列更改为TEXT或BLOB
  ```

  将列长度减少到65,533或更短将使该语句成功。

  ```
  mysql> CREATE TABLE t2
         (c1 VARCHAR(65533) NOT NULL)
         ENGINE = InnoDB CHARACTER SET latin1;
  查询正常，受影响的0行（0.01秒）
  ```

- 对于[`MyISAM`](storage-engines.html#myisam-storage-engine)表， `NULL`列在行中需要额外的空间以记录其值是否为 `NULL`。每`NULL` 列多花一位，四舍五入到最接近的字节。

  创建表的语句`t3`失败，因为除可变长度列长度字节所需的空间外，还[`MyISAM`](storage-engines.html#myisam-storage-engine)需要`NULL`列空间，从而导致行大小超过65,535字节：

  ```
  mysql> CREATE TABLE t3
         (c1 VARCHAR(32765) NULL, c2 VARCHAR(32766) NULL)
         ENGINE = MyISAM CHARACTER SET latin1;
  错误1118（42000）：行大小太大。已使用的最大行大小
  表类型（不计BLOB）为65535。这包括存储开销，
  检查手册。您必须将某些列更改为TEXT或BLOB
  ```

  有关列存储的信息，请参见 [第14.11节“ InnoDB行格式”](innodb-storage-engine.html#innodb-row-format)。 [`InnoDB`](innodb-storage-engine.html) `NULL`

- `InnoDB`对于4KB，8KB，16KB和32KB [`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size) 设置，将行大小（用于数据库页面中本地存储的数据）限制为数据库页面的一半以下 ，而对于64KB页面，则限制为小于16KB。

  创建表的语句`t4`失败，因为定义的列超出了16KB `InnoDB`页面的行大小限制。

  ```
  mysql> CREATE TABLE t4 (
         c1 CHAR(255),c2 CHAR(255),c3 CHAR(255),
         c4 CHAR(255),c5 CHAR(255),c6 CHAR(255),
         c7 CHAR(255),c8 CHAR(255),c9 CHAR(255),
         c10 CHAR(255),c11 CHAR(255),c12 CHAR(255),
         c13 CHAR(255),c14 CHAR(255),c15 CHAR(255),
         c16 CHAR(255),c17 CHAR(255),c18 CHAR(255),
         c19 CHAR(255),c20 CHAR(255),c21 CHAR(255),
         c22 CHAR(255),c23 CHAR(255),c24 CHAR(255),
         c25 CHAR(255),c26 CHAR(255),c27 CHAR(255),
         c28 CHAR(255),c29 CHAR(255),c30 CHAR(255),
         c31 CHAR(255),c32 CHAR(255),c33 CHAR(255)
         ) ENGINE=InnoDB ROW_FORMAT=COMPACT DEFAULT CHARSET latin1;
  错误1118（42000）：行大小太大（> 8126）。将某些列更改为TEXT或BLOB或使用
  ROW_FORMAT = DYNAMIC或ROW_FORMAT = COMPRESSED可能会有所帮助。在当前行格式中，BLOB前缀为768
  字节以内联方式存储。
  ```

## 8.5优化InnoDB表

- [8.5.1优化InnoDB表的存储布局](optimization.html#optimizing-innodb-storage-layout)
- [8.5.2优化InnoDB事务管理](optimization.html#optimizing-innodb-transaction-management)
- [8.5.3优化InnoDB只读事务](optimization.html#innodb-performance-ro-txn)
- [8.5.4优化InnoDB重做日志](optimization.html#optimizing-innodb-logging)
- [8.5.5 InnoDB表的批量数据加载](optimization.html#optimizing-innodb-bulk-data-loading)
- [8.5.6优化InnoDB查询](optimization.html#optimizing-innodb-queries)
- [8.5.7优化InnoDB DDL操作](optimization.html#optimizing-innodb-ddl-operations)
- [8.5.8优化InnoDB磁盘I / O](optimization.html#optimizing-innodb-diskio)
- [8.5.9优化InnoDB配置变量](optimization.html#optimizing-innodb-configuration-variables)
- [8.5.10为具有多个表的系统优化InnoDB](optimization.html#optimizing-innodb-many-tables)



[`InnoDB`](innodb-storage-engine.html)是MySQL客户通常在对可靠性和并发性很重要的生产数据库中使用的存储引擎。 `InnoDB`是MySQL中的默认存储引擎。本节说明如何优化`InnoDB`表的数据库操作 。

### 8.5.1优化InnoDB表的存储布局

- 一旦数据达到稳定的大小，或者正在增长的表增加了几十或几百兆字节，请考虑使用该`OPTIMIZE TABLE`语句重新组织表并压缩所有浪费的空间。重组后的表需要较少的磁盘I / O来执行全表扫描。这是一种直接的技术，可以在其他技术（如改善索引使用率或调整应用程序代码）不可行时提高性能。

  `OPTIMIZE TABLE`复制表的数据部分并重建索引。好处来自改善索引内数据的打包，并减少了表空间和磁盘内的碎片。好处因每个表中的数据而异。您可能会发现其中一些收益显着，而其他收益则不大，或者收益随着时间的流逝而减少，直到您下次优化表格为止。如果表很大或正在重建的索引不适合缓冲池，则此操作可能会很慢。向表中添加大量数据后的第一次运行通常比以后的运行要慢得多。

- 在中`InnoDB`，拥有一个较长的值`PRIMARY KEY`（一个具有长值的列，或者形成一个较长的复合值的几列）会浪费大量的磁盘空间。在指向同一行的所有辅助索引记录中，行的主键值均重复。（请参见[第14.6.2.1节“聚集索引和二级索引”](innodb-storage-engine.html#innodb-index-types)。）`AUTO_INCREMENT`如果主键很长，则创建一列作为主键，或者为长`VARCHAR`列的前缀而不是整个列建立索引。

- 使用[`VARCHAR`](data-types.html#char)数据类型代替[`CHAR`](data-types.html#char)存储可变长度的字符串或用于具有许多`NULL`值的列 。甲 列总是占据字符来存储数据，即使该字符串是较短，或者其值 。较小的表更适合缓冲池并减少磁盘I / O。 [`CHAR(*`N`*)`](data-types.html#char)*`N`*`NULL`

  当使用`COMPACT`行格式（默认`InnoDB`格式）和可变长度字符集（例如 `utf8`或）时`sjis`， 列占据可变的空间量，但仍至少保留字节。 [`CHAR(*`N`*)`](data-types.html#char)*`N`*

- 对于大表或包含大量重复文本或数字数据的表，请考虑使用 `COMPRESSED`行格式。将数据带入缓冲池或执行全表扫描所需的磁盘I / O更少。在做出永久性决定之前，请先评估使用`COMPRESSED`vs `COMPACT`行格式可以实现的压缩量 。

### 8.5.2优化InnoDB事务管理

要优化`InnoDB`事务处理，请在事务功能的性能开销与服务器的工作负载之间找到理想的平衡。例如，如果一个应用程序每秒提交数千次，则可能会遇到性能问题；如果仅每2-3小时提交一次，则可能会遇到不同的性能问题。

- 默认的MySQL设置`AUTOCOMMIT=1` 可能会对繁忙的数据库服务器造成性能限制。在可行的情况下，通过发出`SET AUTOCOMMIT=0`或`START TRANSACTION`声明，`COMMIT`将所有相关的数据更改操作包装到单个事务中 ，然后在进行所有更改后添加一个 语句。

  `InnoDB`如果该事务对数据库进行了修改，则必须在每次事务提交时将日志刷新到磁盘。每次更改后都进行提交（与默认的自动提交设置一样）时，存储设备的I / O吞吐量将限制每秒可能进行的操作的数量。

- 另外，对于仅包含一个[`SELECT`](sql-statements.html#select)语句的事务，打开它`AUTOCOMMIT`有助于 `InnoDB`识别只读事务并优化它们。有关要求[，](optimization.html#innodb-performance-ro-txn)请参见 [第8.5.3节“优化InnoDB只读事务”](optimization.html#innodb-performance-ro-txn)。

- 避免在插入，更新或删除大量行之后执行回滚。如果大事务减慢了服务器性能，则回滚它会使问题变得更糟，执行时间可能是原始数据更改操作的几倍。终止数据库进程无济于事，因为回滚会在服务器启动时再次开始。

  为了最大程度地减少发生此问题的可能性：

  - 增加[缓冲池](glossary.html#glos_buffer_pool)的大小， 以便可以缓存所有数据更改更改，而不是立即将它们写入磁盘。
  - 进行设置， [`innodb_change_buffering=all`](innodb-storage-engine.html#sysvar_innodb_change_buffering) 以便除了插入操作外还缓冲更新和删除操作。
  - 考虑`COMMIT`在大数据更改操作期间定期发布语句，可能将单个删除或更新分解为对较少行数进行操作的多个语句。

  要消除一旦发生的失控回滚，请增加缓冲池，以使回滚变得受CPU限制并快速运行，或者[终止](innodb-storage-engine.html#innodb-recovery)服务器并重新启动 [`innodb_force_recovery=3`](innodb-storage-engine.html#sysvar_innodb_force_recovery)，如[第14.19.2节“ InnoDB恢复”中所述](innodb-storage-engine.html#innodb-recovery)。

  默认设置预计不会出现此问题，该默认设置 [`innodb_change_buffering=all`](innodb-storage-engine.html#sysvar_innodb_change_buffering)允许将更新和删除操作缓存在内存中，从而使它们首先可以更快地执行，并且在需要时可以更快地回滚。确保在处理具有许多插入，更新或删除操作的长期事务的服务器上使用此参数设置。

- 如果发生崩溃时，如果您可以承担一些最近提交的事务的损失，则可以将[`innodb_flush_log_at_trx_commit`](innodb-storage-engine.html#sysvar_innodb_flush_log_at_trx_commit) 参数设置 为0。`InnoDB`尽管不能保证刷新，但还是尝试每秒刷新一次日志。另外，将值设置 [`innodb_support_xa`](innodb-storage-engine.html#sysvar_innodb_support_xa)为0，由于在磁盘数据和二进制日志上进行同步，这将减少磁盘刷新的次数。

  注意

  [`innodb_support_xa`](innodb-storage-engine.html#sysvar_innodb_support_xa)已弃用，并将在以后的版本中删除。从MySQL 5.7.10开始，`InnoDB`始终启用对XA事务中两阶段提交的支持，并且[`innodb_support_xa`](innodb-storage-engine.html#sysvar_innodb_support_xa)不再允许禁用 。

- 修改或删除行时，不会立即删除行和关联的 [撤消日志](glossary.html#glos_undo_log)，甚至不会在提交事务后立即删除。保留旧数据，直到更早或同时开始的事务完成为止，以便那些事务可以访问已修改或已删除行的先前状态。因此，长时间运行的事务可以防止`InnoDB`清除由其他事务更改的数据。

- 如果在长时间运行的事务中修改或删除行，则使用[`READ COMMITTED`](innodb-storage-engine.html#isolevel_read-committed)和 [`REPEATABLE READ`](innodb-storage-engine.html#isolevel_repeatable-read)隔离级别的其他事务 必须读取旧的数据才能做得更多，以重建较旧的数据。

- 当长时间运行的事务修改表时，来自其他事务的对该表的查询不使用[覆盖索引](glossary.html#glos_covering_index)技术。通常可以从二级索引检索所有结果列，而从表数据中查找适当值的查询。

  如果发现二级索引页面的索引 `PAGE_MAX_TRX_ID`太新，或者二级索引中的记录被删除标记，则 `InnoDB`可能需要使用聚集索引来查找记录。

### 8.5.3优化InnoDB只读事务

```
InnoDB`可以避免与为已知为只读的[事务](glossary.html#glos_transaction_id)设置[事务ID](glossary.html#glos_transaction_id)（`TRX_ID`字段）相关的开销。只有可能执行写入操作或 [锁定读取](glossary.html#glos_locking_read)的[事务（](glossary.html#glos_transaction)例如） 需要事务ID 。消除不必要的事务ID可以减少每次查询或数据更改语句构造一个[读取视图](glossary.html#glos_read_view)时都要查询的内部数据结构的大小。 `SELECT ... FOR UPDATE
```

`InnoDB` 在以下情况下检测到只读事务：

- 事务从[`START TRANSACTION READ ONLY`](sql-statements.html#commit)语句开始 。在这种情况下，尝试对数据库（针对`InnoDB`， `MyISAM`或其他类型的表）进行更改会导致错误，并且事务将继续以只读状态运行：

  ```
  错误1792（25006）：无法在只读事务中执行语句。
  ```

  您仍然可以在只读事务中对特定于会话的临时表进行更改，或对其发出锁定查询，因为这些更改和锁定对于其他任何事务都不可见。

- [`autocommit`](server-administration.html#sysvar_autocommit)启用 该设置后，保证事务是单个语句，组成事务的单个语句是“ 非锁定 ” [`SELECT`](sql-statements.html#select)语句。也就是说， `SELECT`不使用`FOR UPDATE`or `LOCK IN SHARED MODE` 子句的a。

- 事务在没有该`READ ONLY`选项的情况下开始，但是尚未执行任何更新或显式锁定行的语句。在需要更新或显式锁之前，事务将保持只读模式。

因此，对于诸如报告生成器之类的读取密集型应用程序，您可以`InnoDB` 通过在[`START TRANSACTION READ ONLY`](sql-statements.html#commit)和中 对它们进行分组来调整查询序列[`COMMIT`](sql-statements.html#commit)，或者通过[`autocommit`](server-administration.html#sysvar_autocommit) 在运行`SELECT`语句之前打开设置来调整查询 序列，或者简单地避免任何数据更改语句散布在查询中。

有关信息 [`START TRANSACTION`](sql-statements.html#commit)，并 [`autocommit`](server-administration.html#sysvar_autocommit)请参见 [13.3.1节，“START TRANSACTION，COMMIT和ROLLBACK语句”](sql-statements.html#commit)。

注意

符合自动提交，非锁定和只读（AC-NL-RO）资格的事务将保留在某些内部 `InnoDB`数据结构之外，因此不在[`SHOW ENGINE INNODB STATUS`](sql-statements.html#show-engine)输出中列出 。

### 8.5.4优化InnoDB重做日志

请考虑以下准则以优化重做日志记录：

- 使重做日志文件变大，甚至与[缓冲池](glossary.html#glos_buffer_pool)一样大 。当 `InnoDB`写重做日志文件的完整，就必须写缓冲池到磁盘的修改内容的 [检查点](glossary.html#glos_checkpoint)。小的重做日志文件会导致许多不必要的磁盘写入。尽管历史上大的重做日志文件导致恢复时间很长，但是现在恢复速度要快得多，您可以放心地使用大的重做日志文件。

  使用[`innodb_log_file_size`](innodb-storage-engine.html#sysvar_innodb_log_file_size) 和 [`innodb_log_files_in_group`](innodb-storage-engine.html#sysvar_innodb_log_files_in_group) 配置选项配置重做日志文件的大小和数量。有关修改现有重做日志文件配置的信息，请参阅 [更改InnoDB重做日志文件的数量或大小](innodb-storage-engine.html#innodb-redo-log-file-reconfigure)。

- 考虑增加[日志缓冲区](glossary.html#glos_log_buffer)的大小 。较大的日志缓冲区使大型 [事务](glossary.html#glos_transaction)可以运行，而无需在事务[提交](glossary.html#glos_commit)之前将日志写入磁盘。因此，如果您有更新，插入或删除许多行的事务，则使日志缓冲区更大可以节省磁盘I / O。使用[`innodb_log_buffer_size`](innodb-storage-engine.html#sysvar_innodb_log_buffer_size) 配置选项配置日志缓冲区大小 。

- 配置 [`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size) 配置选项以避免“ read-on-write ”。此选项定义重做日志的预写块大小。设置 [`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size) 为与操作系统或文件系统缓存块大小匹配。当由于重做日志的预写块大小与操作系统或文件系统缓存块大小不匹配而导致重做日志块未完全缓存到操作系统或文件系统时，发生写时读取。

  有效值为 日志文件块大小（2 n）的[`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size) 倍数。最小值是日志文件块大小（512）。指定最小值时，不会发生预写。最大值等于该 值。如果为其指定的值 大于该 值，则该 设置将被截断为该 值。 `InnoDB``InnoDB`[`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size)[`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size)[`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size)[`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size)[`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size)

  [`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size) 相对于操作系统或文件系统缓存块大小， 将该值设置 得太低会导致写时读取。将该值设置得太高可能会`fsync`由于一次写入多个块而对日志文件写入的性能产生轻微影响 。

### 8.5.5 InnoDB表的批量数据加载



这些性能提示补充了[第8.2.4.1节“优化INSERT语句”中](optimization.html#insert-optimization)有关快速插入的一般准则。

- 将数据导入时`InnoDB`，请关闭自动提交模式，因为它会在每次插入时对磁盘执行日志刷新。要在导入操作期间禁用自动提交，请在其周围加上 [`SET autocommit`](sql-statements.html#commit)and [`COMMIT`](sql-statements.html#commit)语句：

  ```
  SET autocommit = 0;
  ... SQL import statements ...
  承诺;
  ```

  该[**mysqldump的**](programs.html#mysqldump)选项 [`--opt`](programs.html#option_mysqldump_opt)创建这样的快速导入到转储文件`InnoDB` 表，即使没有与他们包装 [`SET autocommit`](sql-statements.html#commit)和 [`COMMIT`](sql-statements.html#commit)报表。

- 如果您`UNIQUE`对辅助键有限制，则可以通过在导入会话期间暂时关闭唯一性检查来加快表的导入：

  ```
  SET unique_checks = 0;
  ... SQL import statements ...
  SET unique_checks = 1;
  ```

  对于大表，这可以节省大量磁盘I / O，因为 `InnoDB`可以使用其更改缓冲区批量写入辅助索引记录。确保数据不包含重复的键。

- 如果`FOREIGN KEY`表中有约束，可以通过在导入会话的持续时间内关闭外键检查来加快表的导入：

  ```
  SET foreign_key_checks = 0;
  ... SQL import statements ...
  SET foreign_key_checks = 1;
  ```

  对于大表，这可以节省大量磁盘I / O。

- [`INSERT`](sql-statements.html#insert) 如果需要插入许多行， 请使用多行语法来减少客户端和服务器之间的通信开销：

  ```
  将值插入（1,2），（5,5），...;
  ```

  该技巧适用于插入到任何表中，而不仅仅是 `InnoDB`表。

- 在具有自动递增列的表中进行批量插入时，请设置 [`innodb_autoinc_lock_mode`](innodb-storage-engine.html#sysvar_innodb_autoinc_lock_mode)为2而不是默认值1。有关详细信息[，](innodb-storage-engine.html#innodb-auto-increment-handling)请参见 [第14.6.1.6节“ InnoDB中的AUTO_INCREMENT处理”](innodb-storage-engine.html#innodb-auto-increment-handling)。

- 执行批量插入时，按`PRIMARY KEY`顺序插入行会更快 。 `InnoDB`表使用 [聚集索引](glossary.html#glos_clustered_index)，这使得使用数据顺序相对快`PRIMARY KEY`。`PRIMARY KEY`对于不能完全放入缓冲池中的表，按顺序执行批量插入尤为重要。

- 为了在将数据加载到`InnoDB` `FULLTEXT`索引中时获得最佳性能 ，请遵循以下步骤：

  1. `FTS_DOC_ID`在创建表时 定义类型为的列`BIGINT UNSIGNED NOT NULL`，并使用名为的唯一索引 `FTS_DOC_ID_INDEX`。例如：

     ```
     创建表t1（
     FTS_DOC_ID BIGINT无符号NOT NULL AUTO_INCREMENT，
     title varchar（255）NOT NULL DEFAULT''，
     文本文字text NOT NULL，
     主键（`FTS_DOC_ID`）
     ）ENGINE = InnoDB DEFAULT CHARSET = latin1;
     在t1上创建唯一索引FTS_DOC_ID_INDEX（FTS_DOC_ID）；
     ```

  2. 将数据加载到表中。

  3. `FULLTEXT`加载数据后 创建索引。

  注意

  `FTS_DOC_ID`在创建表 时添加列时，请确保在 `FTS_DOC_ID`更新`FULLTEXT`索引列时更新该 列，因为`FTS_DOC_ID`必须与[`INSERT`](sql-statements.html#insert)或 单调增加 [`UPDATE`](sql-statements.html#update)。如果您选择不在`FTS_DOC_ID`表创建时添加，并`InnoDB`为您管理DOC ID，`InnoDB`则会`FTS_DOC_ID`在下一次[`CREATE FULLTEXT INDEX`](sql-statements.html#create-index)调用时将其添加 为隐藏列。但是，此方法需要重建表，这将影响性能。

### 8.5.6优化InnoDB查询

要调整`InnoDB`表查询，请在每个表上创建一组适当的索引。有关详细信息[，](optimization.html#mysql-indexes)请参见 [第8.3.1节“ MySQL如何使用索引”](optimization.html#mysql-indexes)。请遵循以下`InnoDB`索引准则：

- 因为每个`InnoDB`表都有一个 [主键](glossary.html#glos_primary_key)（无论您是否请求一个[主键](glossary.html#glos_primary_key)），所以请为每个表指定一组主键列，这些列用于最重要且时间紧迫的查询中。
- 不要在主键中指定太多或太长的列，因为这些列值在每个辅助索引中都是重复的。当索引包含不必要的数据时，用于读取此数据的I / O和用于对其进行缓存的内存将降低服务器的性能和可伸缩性。
- 不要 为每一列创建单独的 [二级索引](glossary.html#glos_secondary_index)，因为每个查询只能使用一个索引。很少测试的列或只有几个不同值的列上的索引可能对任何查询都没有帮助。如果您对同一个表有很多查询，测试不同的列组合，请尝试创建少量的 [串联索引，](glossary.html#glos_concatenated_index)而不是大量的单列索引。如果索引包含结果集所需的所有列（称为 [覆盖索引](glossary.html#glos_covering_index)），则查询可能完全避免读取表数据。
- 如果索引列不能包含任何 `NULL`值，请`NOT NULL`在创建表时将其声明为。当优化器知道每一列是否包含`NULL`值时，它可以更好地确定哪个索引对查询最有效 。
- 您可以`InnoDB`使用[第8.5.3节“优化InnoDB只读事务”中](optimization.html#innodb-performance-ro-txn)的技术[优化](optimization.html#innodb-performance-ro-txn)表的 单查询事务 。

### 8.5.7优化InnoDB DDL操作

- 对表和索引（很多DDL操作`CREATE`，`ALTER`和 `DROP`语句）可以在网上进行。有关详细信息[，](innodb-storage-engine.html#innodb-online-ddl)请参见[第14.13节“ InnoDB和在线DDL”](innodb-storage-engine.html#innodb-online-ddl)。
- 在线DDL支持添加二级索引意味着您通常可以通过以下方式来创建和加载表及相关索引：创建不具有二级索引的表，然后在数据加载后添加二级索引，从而加快创建和加载表及关联索引的过程。
- 使用[`TRUNCATE TABLE`](sql-statements.html#truncate-table)空表，不。外键约束可以使一条语句像常规语句一样工作，在这种情况下，一系列命令可能会更快，例如 和 。 `DELETE FROM *`tbl_name`*``TRUNCATE``DELETE`[`DROP TABLE`](sql-statements.html#drop-table)[`CREATE TABLE`](sql-statements.html#create-table)
- 因为主键对于每个`InnoDB`表的存储布局都是必不可少的，所以更改主键的定义涉及重新组织整个表，因此始终将主键设置为[`CREATE TABLE`](sql-statements.html#create-table)语句的一部分 ，并预先进行计划，这样您就不必 `ALTER`或`DROP`之后的主键。

### 8.5.8优化InnoDB磁盘I / O



如果您遵循数据库设计的最佳实践和SQL操作的调优技术，但是由于磁盘I / O活动繁重，数据库仍然很慢，请考虑这些磁盘I / O优化。如果Unix `top`工具或Windows任务管理器显示您的工作负载中的CPU使用百分比小于70％，则您的工作负载可能是磁盘绑定的。

- 增加缓冲池大小

  当表数据缓存在`InnoDB` 缓冲池中时，可以通过查询重复访问它，而无需任何磁盘I / O。使用[`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size) 选项指定缓冲池的大小 。此内存区域非常重要，因此通常建议将 [`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size)其配置为系统内存的50％到75％。有关更多信息，请参见[第8.12.4.1节“ MySQL如何使用内存”](optimization.html#memory-use)。

- 调整冲洗方法

  在某些版本的GNU / Linux和Unix中，使用Unix `fsync()`调用（ `InnoDB`默认使用）和类似方法将文件刷新到磁盘上的过程令人惊讶地缓慢。如果存在数据库写入性能问题，请使用[`innodb_flush_method`](innodb-storage-engine.html#sysvar_innodb_flush_method) 参数设置为进行基准测试 `O_DSYNC`。

- 在Linux上将noop或截止日期I / O调度程序与本机AIO结合使用

  `InnoDB`在Linux上使用异步I / O子系统（本机AIO）来执行对数据文件页面的预读和写请求。此行为由[`innodb_use_native_aio`](innodb-storage-engine.html#sysvar_innodb_use_native_aio) 配置选项控制，该选项默认情况下处于 启用状态。使用本机AIO，I / O调度程序的类型对I / O性能有更大的影响。通常，建议使用noop和截止日期I / O调度程序。进行基准测试，以确定哪个I / O调度程序为您的工作负载和环境提供最佳结果。有关更多信息，请参见 [第14.8.7节“在Linux上使用异步I / O”](innodb-storage-engine.html#innodb-linux-native-aio)。

- 在Solaris 10上对x86_64体系结构使用直接I / O

  `InnoDB`在Solaris 10的x86_64体系结构（AMD Opteron）上 使用存储引擎时，请对`InnoDB`相关文件使用直接I / O，以避免性能下降`InnoDB`。要对用于存储`InnoDB`相关文件的整个UFS文件系统使用直接I / O，请使用 `forcedirectio`选件将其挂载 。见 `mount_ufs(1M)`。（在Solaris 10 / x86_64上的默认设置是*不*使用此选项。）要仅将直接I / O应用于`InnoDB`文件操作而不是整个文件系统，请设置 [`innodb_flush_method = O_DIRECT`](innodb-storage-engine.html#sysvar_innodb_flush_method)。使用此设置， `InnoDB`呼叫 `directio()`代替 `fcntl()` 用于数据文件的I / O（不适用于日志文件的I / O）。

- 在Solaris 2.6或更高版本中将原始存储用于数据和日志文件

  在任何Solaris 2.6和更高版本以及任何平台（sparc / x86 / x64 / amd64）上`InnoDB`使用具有高[`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size)价值 的存储引擎时，请 `InnoDB`对原始设备或单独的直接I / O UFS上的数据文件和日志文件进行基准测试 文件系统，使用`forcedirectio`如前所述的安装选项。（[`innodb_flush_method`](innodb-storage-engine.html#sysvar_innodb_flush_method)如果要为日志文件直接I / O ，则必须使用安装选项，而不是设置 。）Veritas文件系统VxFS的用户应使用 `convosync=direct`安装选项。

  不要将其他MySQL数据文件（例如 `MyISAM`表的数据）放置在直接I / O文件系统上。可执行文件或库*不得*放置在直接I / O文件系统上。

- 使用其他存储设备

  其他存储设备可用于设置RAID配置。有关相关信息，请参见 [第8.12.2节“优化磁盘I / O”](optimization.html#disk-issues)。

  或者，`InnoDB`可以将表空间数据文件和日志文件放置在不同的物理磁盘上。有关更多信息，请参考以下部分：

  - [第14.8.1节“ InnoDB启动配置”](innodb-storage-engine.html#innodb-init-startup-configuration)
  - [第14.6.1.2节“在外部创建表”](innodb-storage-engine.html#innodb-create-table-external)
  - [创建通用表空间](innodb-storage-engine.html#general-tablespaces-creating)
  - [第14.6.1.4节“移动或复制InnoDB表”](innodb-storage-engine.html#innodb-migration)

- 考虑非旋转存储

  非旋转存储通常为随机I / O操作提供更好的性能。以及用于顺序I / O操作的旋转存储。在旋转式和非旋转式存储设备上分布数据和日志文件时，请考虑主要在每个文件上执行的I / O操作的类型。

  面向随机I / O的文件通常包括 [每表文件](glossary.html#glos_file_per_table) 和[常规表空间](glossary.html#glos_general_tablespace)数据文件， [撤消表空间](glossary.html#glos_undo_tablespace) 文件以及 [临时表空间](glossary.html#glos_temporary_tablespace)文件。面向I / O的顺序文件包括`InnoDB` [系统表空间](glossary.html#glos_system_tablespace)文件（归因于 [doublewrite缓冲](glossary.html#glos_doublewrite_buffer)和 [更改缓冲](glossary.html#glos_change_buffer)）和日志文件，例如[二进制日志](glossary.html#glos_binary_log)文件和[重做日志](glossary.html#glos_redo_log)文件。

  使用非旋转存储时，请查看以下配置选项的设置：

  - [`innodb_checksum_algorithm`](innodb-storage-engine.html#sysvar_innodb_checksum_algorithm)

    该`crc32`选项使用更快的校验和算法，建议用于快速存储系统。

  - [`innodb_flush_neighbors`](innodb-storage-engine.html#sysvar_innodb_flush_neighbors)

    优化旋转存储设备的I / O。禁止将其用于非旋转存储或旋转与非旋转存储的混合。

  - [`innodb_io_capacity`](innodb-storage-engine.html#sysvar_innodb_io_capacity)

    对于较低端的非旋转存储设备，默认设置200通常就足够了。对于高端的，总线连接的设备，请考虑更高的设置，例如1000。

  - [`innodb_io_capacity_max`](innodb-storage-engine.html#sysvar_innodb_io_capacity_max)

    默认值2000适用于使用非旋转存储的工作负载。对于高端的，总线连接的非旋转存储设备，请考虑更高的设置，例如2500。

  - [`innodb_log_compressed_pages`](innodb-storage-engine.html#sysvar_innodb_log_compressed_pages)

    如果重做日志位于非循环存储上，请考虑禁用此选项以减少日志记录。请参阅 [禁用压缩页面的日志记录](optimization.html#innodb-disable-log-compressed-pages)。

  - [`innodb_log_file_size`](innodb-storage-engine.html#sysvar_innodb_log_file_size)

    如果重做日志位于非循环存储中，请配置此选项以最大化缓存和写入组合。

  - [`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size)

    考虑使用与磁盘的内部扇区大小匹配的页面大小。早期的SSD设备通常具有4KB的扇区大小。一些较新的设备具有16KB的扇区大小。默认`InnoDB` 页面大小为16KB。使页面大小接近存储设备块大小可以最大程度地减少重写到磁盘的未更改数据量。

  - [`binlog_row_image`](replication.html#sysvar_binlog_row_image)

    如果二进制日志位于非循环存储上，并且所有表都具有主键，请考虑设置此选项`minimal`以减少日志记录。

  确保为您的操作系统启用了TRIM支持。通常默认情况下启用它。

- 增加I / O容量以避免积压

  如果由于`InnoDB` [检查点](glossary.html#glos_checkpoint) 操作导致吞吐量周期性下降 ，请考虑增加[`innodb_io_capacity`](innodb-storage-engine.html#sysvar_innodb_io_capacity) 配置选项的值 。较高的值会导致更频繁的 [刷新](glossary.html#glos_flush)，避免积压的工作量而导致工作量下降。

- 如果不落后冲洗，则I / O容量会降低

  如果系统没有因`InnoDB` [刷新](glossary.html#glos_flush)操作而落后 ，请考虑降低[`innodb_io_capacity`](innodb-storage-engine.html#sysvar_innodb_io_capacity) 配置选项的值 。通常，您将此选项值保持尽可能低，但又不要太低，以致导致周期性的吞吐量下降，如前面的项目符号所述。在可能降低选项值的典型情况下，您可能会在以下输出中看到类似的组合 [`SHOW ENGINE INNODB STATUS`](sql-statements.html#show-engine)：

  - 历史记录列表长度很低，低于几千。
  - 插入缓冲区合并到插入的行附近。
  - 缓冲池中的已修改页面始终远低于 [`innodb_max_dirty_pages_pct`](innodb-storage-engine.html#sysvar_innodb_max_dirty_pages_pct) 缓冲池。（在服务器不执行批量插入时测量；在批量插入过程中，修改后的页面百分比显着增加是正常的。）
  - `Log sequence number - Last checkpoint` 小于`InnoDB` [日志文件](glossary.html#glos_log_file)总大小的7/8，或者理想情况下小于[日志文件](glossary.html#glos_log_file)总大小的6/8 。

- 将系统表空间文件存储在Fusion-io设备上

  通过在支持原子写入的Fusion-io设备上存储系统表空间文件（“ ibdata文件 ”），可以利用与双写缓冲区相关的I / O优化。在这种情况下，双写缓冲（[`innodb_doublewrite`](innodb-storage-engine.html#sysvar_innodb_doublewrite)）将自动禁用，并且Fusion-io原子写操作将用于所有数据文件。此功能仅在Fusion-io硬件上受支持，并且仅在Linux上的Fusion-io NVMFS中启用。要充分利用此功能， 建议[`innodb_flush_method`](innodb-storage-engine.html#sysvar_innodb_flush_method)设置`O_DIRECT`为。

  注意

  由于doublewrite缓冲区设置是全局的，因此对于非Fusion-io硬件上驻留的数据文件，还将禁用doublewrite缓冲。

- 禁用压缩页面的日志记录

  使用`InnoDB`表 [压缩](glossary.html#glos_compression)功能时，对压缩数据进行更改时，将重新压缩的[页面的](glossary.html#glos_page)图像 写入 [重做日志](glossary.html#glos_redo_log)。此行为由控制 [`innodb_log_compressed_pages`](innodb-storage-engine.html#sysvar_innodb_log_compressed_pages)，默认情况下启用，以防止`zlib` 在恢复过程中使用不同版本的压缩算法时发生损坏。如果确定`zlib`版本不会更改，请禁用此选项 [`innodb_log_compressed_pages`](innodb-storage-engine.html#sysvar_innodb_log_compressed_pages) 以减少修改压缩数据的工作负载的重做日志生成。

### 8.5.9优化InnoDB配置变量

与始终保持接近满负荷运行或遇到高峰活动的服务器相比，不同的设置最适合负载轻且可预测的服务器。

由于`InnoDB`存储引擎会自动执行许多优化，因此许多性能调整任务涉及监视以确保数据库运行良好，并在性能下降时更改配置选项。有关详细性能监控的信息[，](innodb-storage-engine.html#innodb-performance-schema)请参见 [第14.17节“ InnoDB与MySQL性能模式的集成”](innodb-storage-engine.html#innodb-performance-schema)`InnoDB`。

您可以执行的主要配置步骤包括：

- `InnoDB`在包含它们的系统上 启用高性能内存分配器。请参见 [第14.8.4节“为InnoDB配置内存分配器”](innodb-storage-engine.html#innodb-performance-use_sys_malloc)。
- 控制数据更改操作的类型，以 `InnoDB`缓存更改的数据，以避免频繁的小磁盘写入。请参阅 [配置变更缓冲](innodb-storage-engine.html#innodb-change-buffer-configuration)。因为默认设置是缓冲所有类型的数据更改操作，所以仅在需要减少缓冲量时才更改此设置。
- 使用该[`innodb_adaptive_hash_index`](innodb-storage-engine.html#sysvar_innodb_adaptive_hash_index) 选项打开和关闭自适应哈希索引功能 。有关更多信息[，](innodb-storage-engine.html#innodb-adaptive-hash)请参见[第14.5.3节“自适应哈希索引”](innodb-storage-engine.html#innodb-adaptive-hash)。您可以在异常活动期间更改此设置，然后将其恢复为原始设置。
- `InnoDB`如果上下文切换是瓶颈，请 设置对处理的并发线程数的 限制。请参见 [第14.8.5节“为InnoDB配置线程并发”](innodb-storage-engine.html#innodb-performance-thread_concurrency)。
- 控制`InnoDB`与其预读操作有关的预取量 。当系统具有未使用的I / O容量时，更多的预读可以提高查询的性能。过多的预读可能会导致负载较重的系统上的性能定期下降。请参见 [第14.8.3.4节“配置InnoDB缓冲池预取（预读）”](innodb-storage-engine.html#innodb-performance-read_ahead)。
- 如果您具有默认值未完全利用的高端I / O子系统，则增加用于读取或写入操作的后台线程的数量。请参见 [第14.8.6节“配置后台InnoDB I / O线程数”](innodb-storage-engine.html#innodb-performance-multiple_io_threads)。
- 控制`InnoDB`在后台执行多少I / O。请参见 [第14.8.8节“配置InnoDB I / O容量”](innodb-storage-engine.html#innodb-configuring-io-capacity)。如果观察到性能周期性下降，则可以缩减此设置。
- 控制确定何时`InnoDB`执行某些类型的后台写入的算法 。请参见 [第14.8.3.5节“配置缓冲池刷新”](innodb-storage-engine.html#innodb-buffer-pool-flushing)。该算法适用于某些类型的工作负载，但不适用于其他类型的工作负载，因此如果您观察到性能周期性下降，则可能会关闭此设置。
- 利用多核处理器及其高速缓存存储器配置来最大程度地减少上下文切换中的延迟。请参见 [第14.8.9节“配置自旋锁定轮询”](innodb-storage-engine.html#innodb-performance-spin_lock_polling)。
- 防止诸如表扫描之类的一次性操作干扰存储在`InnoDB`缓冲区高速缓存中的频繁访问的数据 。请参见 [第14.8.3.3节“使缓冲池扫描具有抵抗力”](innodb-storage-engine.html#innodb-performance-midpoint_insertion)。
- 将日志文件调整为对可靠性和崩溃恢复有意义的大小。`InnoDB` 日志文件通常保持较小，以避免崩溃后启动时间过长。MySQL 5.5中引入的优化加快了崩溃[恢复](glossary.html#glos_crash_recovery)过程的某些步骤 。特别是，由于改进了内存管理算法，因此扫描 [重做日志](glossary.html#glos_redo_log)和应用重做日志的速度更快。如果您人为地减少了日志文件的大小以避免启动时间过长，现在可以考虑增加日志文件的大小，以减少由于重做日志记录的回收而产生的I / O。
- 配置`InnoDB`缓冲池实例的大小和数量，这 对于具有数千兆字节缓冲池的系统而言尤其重要。请参见 [第14.8.3.2节“配置多个缓冲池实例”](innodb-storage-engine.html#innodb-multiple-buffer-pools)。
- 增加并发事务的最大数量，从而极大地提高了最繁忙数据库的可伸缩性。请参见[第14.6.7节“撤消日志”](innodb-storage-engine.html#innodb-undo-logs)。
- 将清除操作（一种垃圾收集）移动到后台线程中。请参见 [第14.8.10节“清除配置”](innodb-storage-engine.html#innodb-purge-configuration)。为了有效地测量此设置的结果，请首先调整其他与I / O相关的配置和与线程相关的配置设置。
- 减少`InnoDB`在并发线程之间进行的切换量 ，以使繁忙的服务器上的SQL操作不会排队并形成“ 流量阻塞 ”。为该[`innodb_thread_concurrency`](innodb-storage-engine.html#sysvar_innodb_thread_concurrency) 选项设置一个值，对于一个高性能的现代系统，最多可以设置为 32。增加[`innodb_concurrency_tickets`](innodb-storage-engine.html#sysvar_innodb_concurrency_tickets) 选项的值 ，通常到5000左右。选项的这种组合设置了线程数量的上限 `InnoDB` 进程可以随时进行处理，并允许每个线程在被换出之前做大量工作，从而使等待线程的数量保持较低水平，并且无需过多的上下文切换即可完成操作。

### 8.5.10为具有多个表的系统优化InnoDB

- 如果您已配置 [的非持久性优化统计](innodb-storage-engine.html#innodb-statistics-estimation)（非默认配置）， `InnoDB`计算指标 [基数](glossary.html#glos_cardinality)值表中的第一次表，启动后访问的，而不是存储在表这样的值。在将数据划分为多个表的系统上，此步骤可能会花费大量时间。由于此开销仅适用于初始表打开操作，因此要“ 预热 ” 表以供以后使用，请在启动后立即通过发出诸如的语句来访问该表。 `SELECT 1 FROM *`tbl_name`* LIMIT 1`

  默认情况下，优化器统计信息会保留在磁盘上，并由[`innodb_stats_persistent`](innodb-storage-engine.html#sysvar_innodb_stats_persistent) 配置选项启用 。有关持久性优化器统计信息，请参见 [第14.8.11.1节“配置持久性优化器统计参数”](innodb-storage-engine.html#innodb-persistent-stats)。

## 8.6优化MyISAM表

- [8.6.1优化MyISAM查询](optimization.html#optimizing-queries-myisam)
- [8.6.2 MyISAM表的批量数据加载](optimization.html#optimizing-myisam-bulk-data-loading)
- [8.6.3优化REPAIR TABLE语句](optimization.html#repair-table-optimization)



该[`MyISAM`](storage-engines.html#myisam-storage-engine)存储引擎执行最好的读数据或低并发操作，因为表锁限制进行同步更新的能力。在MySQL中，[`InnoDB`](innodb-storage-engine.html)是默认的存储引擎，而不是`MyISAM`。

### 8.6.1优化MyISAM查询

一些加快`MyISAM`表查询速度的一般技巧 ：

- 为了帮助MySQL更好地优化查询，[**请**](programs.html#myisamchk)在对表加载数据后对表使用 [`ANALYZE TABLE`](sql-statements.html#analyze-table)或运行 [**myisamchk --analyze**](programs.html#myisamchk)。这将为每个索引部分更新一个值，该值指示具有相同值的平均行数。（对于唯一索引，该值始终为1。）当基于非恒定表达式联接两个表时，MySQL使用它来决定选择哪个索引。您可以通过使用和检查值来检查表分析的结果 。[**myisamchk --description --verbose**](programs.html#myisamchk)显示索引分布信息。 `SHOW INDEX FROM *`tbl_name`*``Cardinality`

- 要根据索引对索引和数据进行排序，请使用 [**myisamchk --sort-index --sort-records = 1**](programs.html#myisamchk) （假设您要对索引1进行排序）。如果您有唯一的索引，要根据该索引按顺序读取所有行，这是使查询更快的一种好方法。第一次以这种方式对大表进行排序可能会花费很长时间。

- 尝试避免[`SELECT`](sql-statements.html#select) 对`MyISAM`频繁更新的表进行复杂的查询，以避免由于读取器和写入器之间的争用而发生的表锁定问题。

- `MyISAM`支持并发插入：如果表在数据文件中间没有空闲块，则可以[`INSERT`](sql-statements.html#insert)在其他线程从表读取数据的同时向其中添加新行。如果能够做到这一点很重要，请考虑以避免删除行的方式使用表。另一种可能性是运行[`OPTIMIZE TABLE`](sql-statements.html#optimize-table)到整理表你已经删除了很多这行之后。通过设置[`concurrent_insert`](server-administration.html#sysvar_concurrent_insert)变量可以更改此行为 。您甚至可以在已删除行的表中强制添加新行（并因此允许并发插入）。请参见[第8.11.3节“并发插入”](optimization.html#concurrent-inserts)。

- 对于`MyISAM`表经常变化的，尽量避免所有变长列（[`VARCHAR`](data-types.html#char)， [`BLOB`](data-types.html#blob)，和 [`TEXT`](data-types.html#blob)）。如果该表甚至包含单个可变长度列，则使用动态行格式。请参阅[第15章，*备用存储引擎*](storage-engines.html)。

- 通常，仅由于行变大而将表拆分为不同的表是没有用的。在访问一行时，最大的性能损失是找到该行第一个字节所需的磁盘搜索。找到数据后，大多数现代磁盘可以以足够快的速度读取整个行，以适合大多数应用程序。拆分表的唯一情况是`MyISAM`，可以更改为固定行大小的表是使用动态行格式的 表，还是经常需要扫描表但不需要大多数列的表。请参阅[第15章，*备用存储引擎*](storage-engines.html)。

- 通常用于按顺序检索行时 使用。通过在对该表进行大量更改之后使用此选项，您可能可以获得更高的性能。 `ALTER TABLE ... ORDER BY *`expr1`*, *`expr2`*, ...``*`expr1`*, *`expr2`*, ...`

- 如果您经常需要基于许多行中的信息来计算结果（例如计数），则最好引入一个新表并实时更新计数器。以下表格的更新非常快：

  ```
  UPDATE tbl_nameSET count_col= count_col+1 WHERE key_col= constant;
  ```

  当您使用MySQL存储引擎（例如`MyISAM`仅具有表级锁定（具有单个编写器的多个读取器））时，这非常重要。这也使大多数数据库系统具有更好的性能，因为在这种情况下，行锁定管理器的工作量减少了。

- [`OPTIMIZE TABLE`](sql-statements.html#optimize-table) 定期 使用以避免动态格式`MyISAM`表的碎片化 。请参见 [第15.2.3节“ MyISAM表存储格式”](storage-engines.html#myisam-table-formats)。

- `MyISAM`使用 `DELAY_KEY_WRITE=1`table选项 声明表可以使索引更新更快，因为在关闭表之前它们不会刷新到磁盘。不利的一面是，如果在打开此类表的过程中某人杀死了服务器，则必须通过[`myisam_recover_options`](server-administration.html#sysvar_myisam_recover_options) 设置系统变量集运行服务器或在重新启动服务器之前运行 [**myisamchk**](programs.html#myisamchk)来确保该表正常 。（但是，即使在这种情况下，也不要通过使用来丢失任何内容`DELAY_KEY_WRITE`，因为密钥信息始终可以从数据行中生成。）

- 字符串会在`MyISAM`索引中自动压缩前缀和结尾空间。请参见 [第13.1.14节“ CREATE INDEX语句”](sql-statements.html#create-index)。

- 您可以通过在应用程序中缓存查询或答案，然后一起执行许多插入或更新操作来提高性能。在此操作期间锁定表可确保索引缓存在所有更新后仅刷新一次。您还可以利用MySQL的查询缓存来获得相似的结果。请参见 [第8.10.3节“ MySQL查询缓存”](optimization.html#query-cache)。

### 8.6.2 MyISAM表的批量数据加载



这些性能提示补充了[第8.2.4.1节“优化INSERT语句”中](optimization.html#insert-optimization)有关快速插入的一般准则。

- 对于`MyISAM`表，[`SELECT`](sql-statements.html#select)如果数据文件中间没有删除的行，则可以在语句运行的同时使用并发插入来添加行 。请参见[第8.11.3节“并发插入”](optimization.html#concurrent-inserts)。

- 通过做一些额外的工作，可以在表具有多个索引的情况下使 表的[`LOAD DATA`](sql-statements.html#load-data)运行速度更快`MyISAM`。使用以下过程：

  1. 执行一条[`FLUSH TABLES`](sql-statements.html#flush-tables) 语句或一个[**mysqladmin flush-tables**](programs.html#mysqladmin)命令。
  2. 使用[**myisamchk --keys-used = 0 -rq \*`/path/to/db/tbl_name`\***](programs.html#myisamchk) 删除表的所有索引使用。
  3. 使用将数据插入表中 [`LOAD DATA`](sql-statements.html#load-data)。这不会更新任何索引，因此非常快。
  4. 如果将来只打算从表中读取，请使用[**myisampack对其**](programs.html#myisampack)进行压缩。请参见 [第15.2.3.3节“压缩表特性”](storage-engines.html#compressed-format)。
  5. 使用[**myisamchk -rq \*`/path/to/db/tbl_name`\***](programs.html#myisamchk)重新创建索引。这样可以在将索引树写入磁盘之前在内存中创建索引树，这比更新索引树期间快得多，[`LOAD DATA`](sql-statements.html#load-data)因为它避免了很多磁盘寻道。生成的索引树也完美平衡。
  6. 执行一条[`FLUSH TABLES`](sql-statements.html#flush-tables) 语句或一个[**mysqladmin flush-tables**](programs.html#mysqladmin)命令。

  [`LOAD DATA`](sql-statements.html#load-data)如果`MyISAM`要向其中插入数据的表为空，则会自动执行上述优化 。自动优化与显式使用该过程之间的主要区别在于，与让服务器在执行语句时为索引重新创建分配的空间相比，可以使 [**myisamchk**](programs.html#myisamchk)为索引创建分配的临时内存要多得多 [`LOAD DATA`](sql-statements.html#load-data)。

  您还可以`MyISAM`使用以下语句而不是[**myisamchk**](programs.html#myisamchk)来禁用或启用表的非唯一索引 。如果使用这些语句，则可以跳过以下 [`FLUSH TABLES`](sql-statements.html#flush-tables)操作：

  ```
  更改表tbl_name禁用键；
  更改表tbl_name启用键；
  ```

- 要加快[`INSERT`](sql-statements.html#insert)对非事务性表使用多个语句执行的操作，请锁定表：

  ```
  锁定表写；
  插入值（1,23），（2,34），（4,33）;
  插入值（8,26），（6,29）;
  ...
  解锁表；
  ```

  这将提高性能，因为在所有[`INSERT`](sql-statements.html#insert)语句完成后，索引缓冲区仅刷新一次到磁盘 。通常，索引缓冲区刷新与[`INSERT`](sql-statements.html#insert) 语句数量一样多。如果您可以使用插入所有行，则不需要显式锁定语句 [`INSERT`](sql-statements.html#insert)。

  锁定还减少了多连接测试的总时间，尽管单个连接的最大等待时间可能会增加，因为它们会等待锁定。假设五个客户端尝试同时执行插入，如下所示：

  - 连接1可插入1000次
  - 连接2、3和4做1插入
  - 连接5可插入1000次

  如果不使用锁定，则连接2、3和4会在1和5之前完成。如果使用锁定，则连接2、3和4可能不会在1或5之前完成，但是总时间应为40％快点。

  [`INSERT`](sql-statements.html#insert)，， [`UPDATE`](sql-statements.html#update)和 [`DELETE`](sql-statements.html#delete)操作在MySQL中非常快，但是您可以通过在执行大约五个以上的连续插入或更新的所有操作周围添加锁来获得更好的整体性能。如果您执行了很多连续的插入操作，则可以[`LOCK TABLES`](sql-statements.html#lock-tables)不时进行 [`UNLOCK TABLES`](sql-statements.html#lock-tables)一次之后的操作（每行1,000行左右），以允许其他线程访问表。这仍然会带来不错的性能提升。

  [`INSERT`](sql-statements.html#insert)[`LOAD DATA`](sql-statements.html#load-data)即使使用上述策略，加载数据的速度仍然比慢得多。

- 为了提高`MyISAM` 表的性能，对于[`LOAD DATA`](sql-statements.html#load-data) 和[`INSERT`](sql-statements.html#insert)，都可以通过增加[`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)系统变量来扩大键缓存 。请参见[第5.1.1节“配置服务器”](server-administration.html#server-configuration)。

### 8.6.3优化REPAIR TABLE语句



[`REPAIR TABLE`](sql-statements.html#repair-table)用于 `MyISAM`表的操作类似于使用 [**myisamchk**](programs.html#myisamchk)进行修复操作，并且应用了一些相同的性能优化：

- [**myisamchk**](programs.html#myisamchk)具有控制内存分配的变量。您可以通过设置这些变量来提高性能，如 [第4.6.3.6节“ myisamchk内存使用情况”中所述](programs.html#myisamchk-memory)。
- 对于[`REPAIR TABLE`](sql-statements.html#repair-table)，应用相同的原理，但是由于修复是由服务器完成的，因此您可以设置服务器系统变量而不是 [**myisamchk**](programs.html#myisamchk)变量。此外，除了设置内存分配变量外，增加 [`myisam_max_sort_file_size`](server-administration.html#sysvar_myisam_max_sort_file_size) 系统变量还增加了修复将使用较快的文件排序方法的可能性，并避免了通过键缓存方法进行较慢的修复。在检查以确保有足够的可用空间来保存表文件的副本之后，将变量设置为系统的最大文件大小。可用空间在包含原始表文件的文件系统中必须可用。

假设使用以下选项来设置[**myisamchk**](programs.html#myisamchk)表修复操作，以设置其内存分配变量：

```
--key_buffer_size = 128M --myisam_sort_buffer_size = 256M
--read_buffer_size = 64M --write_buffer_size = 64M
```

其中一些[**myisamchk**](programs.html#myisamchk)变量对应于服务器系统变量：

| [**myisamchk**](programs.html#myisamchk)变量 | 系统变量                                                     |
| :------------------------------------------- | :----------------------------------------------------------- |
| `key_buffer_size`                            | [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) |
| `myisam_sort_buffer_size`                    | [`myisam_sort_buffer_size`](server-administration.html#sysvar_myisam_sort_buffer_size) |
| `read_buffer_size`                           | [`read_buffer_size`](server-administration.html#sysvar_read_buffer_size) |
| `write_buffer_size`                          | 没有                                                         |

每个服务器系统变量都可以在运行时进行设置，其中一些（[`myisam_sort_buffer_size`](server-administration.html#sysvar_myisam_sort_buffer_size)，[`read_buffer_size`](server-administration.html#sysvar_read_buffer_size)）除了全局值外， 还具有会话值。设置会话值将限制更改对当前会话的影响，并且不会影响其他用户。更改全局唯一的变量（[`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)， [`myisam_max_sort_file_size`](server-administration.html#sysvar_myisam_max_sort_file_size)）会影响其他用户也是如此。对于 [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)，您必须考虑与这些用户共享缓冲区。例如，如果将[**myisamchk**](programs.html#myisamchk) `key_buffer_size`变量设置为128MB，则可以设置相应的 [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)大于此值的系统变量（如果尚未将其设置得更大），以允许其他会话中的活动使用键缓冲区。但是，更改全局密钥缓冲区的大小会使缓冲区无效，从而导致磁盘I / O增加和其他会话速度降低。避免此问题的另一种方法是使用单独的键高速缓存，将要修复的表中的索引分配给它，并在修复完成后将其释放。请参见 [第8.10.2.2节“多键缓存”](optimization.html#multiple-key-caches)。

根据以上说明，[`REPAIR TABLE`](sql-statements.html#repair-table)可以使用类似于[**myisamchk**](programs.html#myisamchk)命令的设置进行以下操作。这里分配了一个单独的128MB密钥缓冲区，并且假定文件系统允许文件大小至少为100GB。

```
设置会话myisam_sort_buffer_size = 256 * 1024 * 1024;
SET SESSION read_buffer_size = 64 * 1024 * 1024;
设置全局myisam_max_sort_file_size = 100 * 1024 * 1024 * 1024;
设置全局repair_cache.key_buffer_size = 128 * 1024 * 1024;
CACHE INDEX tbl_nameIN repair_cache;
加载索引缓存tbl_name;
维修台tbl_name;
设置全局repair_cache.key_buffer_size = 0;
```

如果您打算更改全局变量，但只希望在操作过程中进行更改，[`REPAIR TABLE`](sql-statements.html#repair-table)以最小程度地影响其他用户，则将其值保存在用户变量中，然后再进行恢复。例如：

```
SET @old_myisam_sort_buffer_size = @@ GLOBAL.myisam_max_sort_file_size;
设置全局myisam_max_sort_file_size = 100 * 1024 * 1024 * 1024;
维修台tbl_name;
SET GLOBAL myisam_max_sort_file_size = @old_myisam_max_sort_file_size;
```

[`REPAIR TABLE`](sql-statements.html#repair-table)如果希望这些值在默认情况下有效，则可以在服务器启动时全局设置 影响的系统变量。例如，将这些行添加到服务器`my.cnf`文件中：

```
[mysqld]
myisam_sort_buffer_size = 256M
key_buffer_size = 1G
myisam_max_sort_file_size = 100G
```

这些设置不包括 [`read_buffer_size`](server-administration.html#sysvar_read_buffer_size)。[`read_buffer_size`](server-administration.html#sysvar_read_buffer_size)全局设置 为所有会话都使用较大的值，并且由于为具有多个同时会话的服务器分配过多的内存，可能会导致性能下降。

## 8.7优化内存表



考虑将`MEMORY`表用于经常访问，只读或很少更新的非关键数据。在实际工作负载下，将应用程序与等效表`InnoDB`或基准进行基准测试 `MyISAM`，以确认任何额外的性能值得承担丢失数据的风险，或者值得在应用程序启动时从基于磁盘的表复制数据的开销。

为了使`MEMORY`表获得最佳性能，请检查针对每个表的查询类型，并指定用于每个关联索引（B树索引或哈希索引）的类型。在[`CREATE INDEX`](sql-statements.html#create-index) 语句上，使用`USING BTREE`或 子句`USING HASH`。对于通过诸如`>`或的运算符进行大于或小于比较的查询，B树索引的速度很快`BETWEEN`。哈希索引仅适用于通过`=`运算符查找单个值或通过运算符查找一组受限值的查询`IN`。有关为什么 `USING BTREE`通常比默认选项更好的选择`USING HASH`，请参见 [第8.2.1.20节“避免全表扫描”](optimization.html#table-scan-avoidance)。有关不同类型`MEMORY`索引的实现细节，请参见 [第8.3.8节“ B树和哈希索引的比较”](optimization.html#index-btree-hash)。

## 8.8了解查询执行计划

- [8.8.1使用EXPLAIN优化查询](optimization.html#using-explain)
- [8.8.2说明输出格式](optimization.html#explain-output)
- [8.8.3扩展的EXPLAIN输出格式](optimization.html#explain-extended)
- [8.8.4获取命名连接的执行计划信息](optimization.html#explain-for-connection)
- [8.8.5估计查询性能](optimization.html#estimating-performance)

根据表，列，索引的详细信息以及`WHERE`子句中的条件，MySQL优化器考虑了许多技术来有效执行SQL查询中涉及的查找。无需读取所有行即可执行对巨大表的查询；可以在不比较行的每个组合的情况下执行涉及多个表的联接。优化器选择执行最有效查询的一组操作称为“ 查询执行计划 ”，也称为 [`EXPLAIN`](sql-statements.html#explain)计划。您的目标是认识到 [`EXPLAIN`](sql-statements.html#explain) 表示查询优化的计划，如果发现一些低效的操作，请学习SQL语法和索引技术以改进计划。

### 8.8.1使用EXPLAIN优化查询



该[`EXPLAIN`](sql-statements.html#explain)语句提供有关MySQL如何执行语句的信息：

- [`EXPLAIN`](sql-statements.html#explain)作品有 [`SELECT`](sql-statements.html#select)， [`DELETE`](sql-statements.html#delete)， [`INSERT`](sql-statements.html#insert)， [`REPLACE`](sql-statements.html#replace)，和 [`UPDATE`](sql-statements.html#update)语句。
- 当[`EXPLAIN`](sql-statements.html#explain)与可解释的语句一起使用时，MySQL将显示来自优化器的有关语句执行计划的信息。也就是说，MySQL解释了它将如何处理该语句，包括有关如何连接表以及以何种顺序连接表的信息。有关 [`EXPLAIN`](sql-statements.html#explain)用于获取执行计划信息的信息，请参见[第8.8.2节“ EXPLAIN输出格式”](optimization.html#explain-output)。
- 当[`EXPLAIN`](sql-statements.html#explain)与 而不是可解释的语句一起使用时，它将显示在命名连接中执行的语句的执行计划。请参见[第8.8.4节“获取命名连接的执行计划信息”](optimization.html#explain-for-connection)。 `FOR CONNECTION *`connection_id`*`
- 对于[`SELECT`](sql-statements.html#select)语句， [`EXPLAIN`](sql-statements.html#explain)生成可使用来显示的其他执行计划信息 [`SHOW WARNINGS`](sql-statements.html#show-warnings)。请参见 [第8.8.3节“扩展的EXPLAIN输出格式”](optimization.html#explain-extended)。
- [`EXPLAIN`](sql-statements.html#explain)对于检查涉及分区表的查询很有用。请参见 [第22.3.5节“获取有关分区的信息”](partitioning.html#partitioning-info)。
- 该`FORMAT`选项可用于选择输出格式。`TRADITIONAL`以表格格式显示输出。如果不`FORMAT`存在任何选项，则为默认设置 。 `JSON`format以JSON格式显示信息。

在的帮助下[`EXPLAIN`](sql-statements.html#explain)，您可以看到应该在表中添加索引的位置，以便通过使用索引查找行来使语句更快地执行。您还可以 [`EXPLAIN`](sql-statements.html#explain)用来检查优化器是否以最佳顺序联接表。要提示优化器使用连接顺序，该连接顺序与[`SELECT`](sql-statements.html#select)语句中表的命名顺序相对应，请以 `SELECT STRAIGHT_JOIN`而不是just 开头[`SELECT`](sql-statements.html#select)。（请参见 [第13.2.9节“ SELECT语句”](sql-statements.html#select)。）但是，`STRAIGHT_JOIN`由于它禁用了半联接转换，因此 可能会阻止使用索引。看到 [第8.2.2.1节“使用半联接转换优化子查询，派生表和视图引用”](optimization.html#semijoins)。

优化程序跟踪有时可能提供与补充的信息[`EXPLAIN`](sql-statements.html#explain)。但是，优化程序的跟踪格式和内容在版本之间可能会发生变化。有关详细信息，请参见 [MySQL内部：跟踪优化器](https://dev.mysql.com/doc/internals/en/optimizer-tracing.html)。

如果您在认为应该使用索引时遇到问题，请运行[`ANALYZE TABLE`](sql-statements.html#analyze-table)以更新表统计信息，例如键的基数，这可能会影响优化器的选择。请参见 [第13.7.2.1节“ ANALYZE TABLE语句”](sql-statements.html#analyze-table)。

注意

[`EXPLAIN`](sql-statements.html#explain)也可以用于获取有关表中列的信息。 是和的 同义词。有关更多信息，请参见[第13.8.1节“ DESCRIBE语句”](sql-statements.html#describe)和 [第13.7.5.5节“ SHOW COLUMNS语句”](sql-statements.html#show-columns)。 [`EXPLAIN *`tbl_name`*`](sql-statements.html#explain)`DESCRIBE *`tbl_name`*``SHOW COLUMNS FROM *`tbl_name`*`

### 8.8.2说明输出格式

该[`EXPLAIN`](sql-statements.html#explain)语句提供有关MySQL如何执行语句的信息。 [`EXPLAIN`](sql-statements.html#explain)作品有 [`SELECT`](sql-statements.html#select)， [`DELETE`](sql-statements.html#delete)， [`INSERT`](sql-statements.html#insert)， [`REPLACE`](sql-statements.html#replace)，和 [`UPDATE`](sql-statements.html#update)语句。

[`EXPLAIN`](sql-statements.html#explain)为[`SELECT`](sql-statements.html#select)语句中使用的每个表返回一行信息 。它按照MySQL在处理语句时读取它们的顺序列出了输出中的表。MySQL使用嵌套循环连接方法解析所有连接。这意味着MySQL从第一个表中读取一行，然后在第二个表，第三个表中找到匹配的行，依此类推。处理完所有表后，MySQL将通过表列表输出选定的列和回溯，直到找到一个表，其中存在更多匹配的行。从该表中读取下一行，然后继续下一个表。

[`EXPLAIN`](sql-statements.html#explain)输出包括分区信息。此外，对于[`SELECT`](sql-statements.html#select) 语句，[`EXPLAIN`](sql-statements.html#explain)产生可与被显示扩展信息 [`SHOW WARNINGS`](sql-statements.html#show-warnings)之后的 [`EXPLAIN`](sql-statements.html#explain)（见 [第8.8.3，“扩展EXPLAIN输出格式”](optimization.html#explain-extended)）。

注意

在较早的MySQL版本中，分区和扩展信息是使用 [`EXPLAIN PARTITIONS`](sql-statements.html#explain)和生成的 [`EXPLAIN EXTENDED`](sql-statements.html#explain)。仍然可以识别这些语法的向后兼容性，但是默认情况下现在启用了分区和扩展输出，因此`PARTITIONS` 和`EXTENDED`关键字已不再使用。使用它们会导致警告，并且[`EXPLAIN`](sql-statements.html#explain)在将来的MySQL版本中会将其从语法中删除。

你不能使用已弃用`PARTITIONS` ，并`EXTENDED`在相同的关键字共同 [`EXPLAIN`](sql-statements.html#explain)声明。此外，这些关键字都不能与该`FORMAT`选项一起使用 。

注意

MySQL Workbench具有视觉解释功能，可提供[`EXPLAIN`](sql-statements.html#explain)输出的视觉表示 。请参见 [教程：使用解释来提高查询性能](https://dev.mysql.com/doc/workbench/en/wb-tutorial-visual-explain-dbt3.html)。

- [解释输出列](optimization.html#explain-output-columns)
- [说明联接类型](optimization.html#explain-join-types)
- [了解更多信息](optimization.html#explain-extra-information)
- [解释输出解释](optimization.html#explain-output-interpretation)

#### 解释输出列

本节介绍产生的输出列 [`EXPLAIN`](sql-statements.html#explain)。后面的部分提供有关[`type`](optimization.html#explain-join-types) 和 [`Extra`](optimization.html#explain-extra-information) 列的其他信息 。

的每个输出行[`EXPLAIN`](sql-statements.html#explain) 提供有关一个表的信息。每行包含[表8.1“ EXPLAIN输出列”中](optimization.html#explain-output-column-table)概述的值 ，并在表后更详细地描述。列名显示在表的第一列中；第二列提供`FORMAT=JSON`使用时输出中显示的等效属性名称 。



**表8.1 EXPLAIN输出列**

| 柱                                                         | JSON名称        | 含义                   |
| :--------------------------------------------------------- | :-------------- | :--------------------- |
| [`id`](optimization.html#explain_id)                       | `select_id`     | 该`SELECT`标识符       |
| [`select_type`](optimization.html#explain_select_type)     | 没有            | 该`SELECT`类型         |
| [`table`](optimization.html#explain_table)                 | `table_name`    | 输出行表               |
| [`partitions`](optimization.html#explain_partitions)       | `partitions`    | 匹配的分区             |
| [`type`](optimization.html#explain_type)                   | `access_type`   | 联接类型               |
| [`possible_keys`](optimization.html#explain_possible_keys) | `possible_keys` | 可能的索引选择         |
| [`key`](optimization.html#explain_key)                     | `key`           | 实际选择的索引         |
| [`key_len`](optimization.html#explain_key_len)             | `key_length`    | 所选键的长度           |
| [`ref`](optimization.html#explain_ref)                     | `ref`           | 与索引比较的列         |
| [`rows`](optimization.html#explain_rows)                   | `rows`          | 估计要检查的行         |
| [`filtered`](optimization.html#explain_filtered)           | `filtered`      | 按表条件过滤的行百分比 |
| [`Extra`](optimization.html#explain_extra)                 | 没有            | 附加信息               |



注意

JSON属性`NULL`不会显示在JSON格式的`EXPLAIN` 输出中。

- `id`（JSON名： `select_id`）

  的[`SELECT`](sql-statements.html#select)标识符。这是[`SELECT`](sql-statements.html#select)查询中的序号 。`NULL`如果该行引用其他行的并集结果，则该值为。在这种情况下，该 `table`列显示的值类似于 表明该行引用的行的并 集是和的值 。 `<union*`M`*,*`N`*>``id`*`M`**`N`*

- `select_type` （JSON名称：无）

  的类型[`SELECT`](sql-statements.html#select)，可以是下表中显示的任何类型。JSON格式`EXPLAIN`将`SELECT`类型公开 为a的属性 `query_block`，除非它为 `SIMPLE`或`PRIMARY`。表格中还会显示JSON名称（如果适用）。

  | `select_type` 值                                         | JSON名称                     | 含义                                                         |
  | :------------------------------------------------------- | :--------------------------- | :----------------------------------------------------------- |
  | `SIMPLE`                                                 | 没有                         | 简单[`SELECT`](sql-statements.html#select)（不使用 [`UNION`](sql-statements.html#union)或子查询） |
  | `PRIMARY`                                                | 没有                         | 最外层 [`SELECT`](sql-statements.html#select)                |
  | [`UNION`](sql-statements.html#union)                     | 没有                         | 第二个或之后的[`SELECT`](sql-statements.html#select)陈述 [`UNION`](sql-statements.html#union) |
  | `DEPENDENT UNION`                                        | `dependent`（`true`）        | 中的第二个或更高版本的[`SELECT`](sql-statements.html#select)语句 [`UNION`](sql-statements.html#union)，取决于外部查询 |
  | `UNION RESULT`                                           | `union_result`               | 的结果[`UNION`](sql-statements.html#union)。                 |
  | [`SUBQUERY`](optimization.html#optimizer-hints-subquery) | 没有                         | 首先[`SELECT`](sql-statements.html#select)在子查询           |
  | `DEPENDENT SUBQUERY`                                     | `dependent`（`true`）        | 首先[`SELECT`](sql-statements.html#select)在子查询中，取决于外部查询 |
  | `DERIVED`                                                | 没有                         | 派生表                                                       |
  | `MATERIALIZED`                                           | `materialized_from_subquery` | 物化子查询                                                   |
  | `UNCACHEABLE SUBQUERY`                                   | `cacheable`（`false`）       | 子查询，其结果无法缓存，必须针对外部查询的每一行重新进行评估 |
  | `UNCACHEABLE UNION`                                      | `cacheable`（`false`）       | [`UNION`](sql-statements.html#union) 属于不可缓存子查询的中的第二个或更高版本的选择（请参阅参考资料 `UNCACHEABLE SUBQUERY`） |

  `DEPENDENT`通常表示使用相关子查询。请参见 [第13.2.10.7节“相关子查询”](sql-statements.html#correlated-subqueries)。

  `DEPENDENT SUBQUERY`评估不同于`UNCACHEABLE SUBQUERY`评估。对于`DEPENDENT SUBQUERY`，子查询对于来自其外部上下文的变量的每个不同值集仅重新评估一次。对于 `UNCACHEABLE SUBQUERY`，将为外部上下文的每一行重新评估子查询。

  子查询的可缓存性与查询结果在查询缓存中的缓存不同（[第8.10.3.1节“查询缓存的操作方式”中](optimization.html#query-cache-operation)对此进行了描述 ）。子查询缓存在查询执行期间发生，而查询缓存仅在查询执行完成后才用于存储结果。

  当您指定`FORMAT=JSON`with时 `EXPLAIN`，输出没有直接等同于`select_type`;的单个属性 。该 `query_block`属性对应于给定`SELECT`。与`SELECT`刚刚显示的大多数子查询类型等效的属性可用（一个示例 `materialized_from_subquery`针对 `MATERIALIZED`），并在适当时显示。没有`SIMPLE`或的JSON等效项 `PRIMARY`。

  `select_type`非[`SELECT`](sql-statements.html#select)语句 的值显示受影响表的语句类型。例如，`select_type`是 `DELETE`对 [`DELETE`](sql-statements.html#delete)报表。

- `table`（JSON名： `table_name`）

  输出行所引用的表的名称。这也可以是以下值之一：

  - `<union*`M`*,*`N`*>`：该行指的是具有和`id`值的行 的 *`M`*并集 *`N`*。
  - `<derived*`N`*>`：该行是指用于与该行的派生表结果`id`的值 *`N`*。派生表可能来自（例如）`FROM`子句中的子查询 。
  - `<subquery*`N`*>`：该行是指该行的物化子查询的结果，其`id` 值为*`N`*。请参见 [第8.2.2.2节“通过实现来优化子查询”](optimization.html#subquery-materialization)。

- `partitions`（JSON名： `partitions`）

  查询将从中匹配记录的分区。该值适用`NULL`于未分区的表。请参见 [第22.3.5节“获取有关分区的信息”](partitioning.html#partitioning-info)。

- `type`（JSON名： `access_type`）

  联接类型。有关不同类型的描述，请参见 [`EXPLAIN` 连接类型](optimization.html#explain-join-types)。

- `possible_keys`（JSON名： `possible_keys`）

  该`possible_keys`列指示MySQL可以选择从中查找表中各行的索引。请注意，此列完全独立于表的顺序，如的输出所示 [`EXPLAIN`](sql-statements.html#explain)。这意味着`possible_keys`在实践中，某些键可能无法与生成的表顺序一起使用。

  如果此列是`NULL`（或在JSON格式的输出中未定义），则没有相关的索引。在这种情况下，您可以通过检查该`WHERE` 子句以检查它是否引用了某些适合索引的列，从而提高查询性能。如果是这样，请创建一个适当的索引并[`EXPLAIN`](sql-statements.html#explain)再次检查查询 。请参见 [第13.1.8节“ ALTER TABLE语句”](sql-statements.html#alter-table)。

  要查看表具有哪些索引，请使用。 `SHOW INDEX FROM *`tbl_name`*`

- `key`（JSON名：`key`）

  该`key`列指示MySQL实际决定使用的键（索引）。如果MySQL决定使用`possible_keys` 索引之一来查找行，则将该索引列为键值。

  可能`key`会命名该值中不存在的索引 `possible_keys`。如果没有一个`possible_keys`索引适合查找行，但是查询选择的所有列都是某个其他索引的列，则可能发生这种情况。也就是说，命名索引覆盖了选定的列，因此尽管不使用索引来确定要检索的行，但是索引扫描比数据行扫描更有效。

  对于`InnoDB`，即使查询也选择了主键，辅助索引也可能覆盖选定的列，因为`InnoDB`主键值与每个辅助索引一起存储。如果 `key`为`NULL`，则MySQL没有找到可用于更有效地执行查询的索引。

  要强制MySQL使用或忽略列出的索引 `possible_keys`列，使用 `FORCE INDEX`，`USE INDEX`或`IGNORE INDEX`在您的查询。请参见[第8.9.4节“索引提示”](optimization.html#index-hints)。

  对于`MyISAM`表，运行 [`ANALYZE TABLE`](sql-statements.html#analyze-table)有助于优化器选择更好的索引。对于 `MyISAM`表，[**myisamchk --analyze也是**](programs.html#myisamchk)如此。请参见 [第13.7.2.1节“ ANALYZE TABLE语句”](sql-statements.html#analyze-table)和 [第7.6节“ MyISAM表维护和崩溃恢复”](backup-and-recovery.html#myisam-table-maintenance)。

- `key_len`（JSON名： `key_length`）

  该`key_len`列指示MySQL决定使用的密钥的长度。的值 `key_len`使您能够确定MySQL实际使用的多部分键的多少部分。如果该`key`列显示 `NULL`，则该`key_len` 列也显示`NULL`。

  由于密钥存储格式的原因，一列可以使用的密钥长度`NULL` 比一`NOT NULL`列大。

- `ref`（JSON名：`ref`）

  该`ref`列显示将哪些列或常量与该`key`列中命名的索引进行比较，以 从表中选择行。

  如果值为`func`，则使用的值是某些函数的结果。要查看哪个功能，请使用 [`SHOW WARNINGS`](sql-statements.html#show-warnings)以下 [`EXPLAIN`](sql-statements.html#explain)命令查看扩展 [`EXPLAIN`](sql-statements.html#explain)输出。该函数实际上可能是算术运算符之类的运算符。

- `rows`（JSON名： `rows`）

  该`rows`列指示MySQL认为执行查询必须检查的行数。

  对于[`InnoDB`](innodb-storage-engine.html)表，此数字是估计值，可能并不总是准确的。

- `filtered`（JSON名： `filtered`）

  该`filtered`列指示将被表条件过滤的表行的估计百分比。最大值为100，这表示未过滤行。值从100减小表示过滤量增加。 `rows`显示了检查的估计行数，`rows`× `filtered`显示了将与下表连接的行数。例如，如果 `rows`为1000且 `filtered`为50.00（50％），则与下表连接的行数为1000×50％= 500。

- `Extra` （JSON名称：无）

  此列包含有关MySQL如何解析查询的其他信息。有关不同值的说明，请参见“ [`EXPLAIN` 其他信息”](optimization.html#explain-extra-information)。

  该`Extra`列没有对应的JSON属性 ；但是，此列中可能出现的值显示为JSON属性或该`message`属性的文本。

#### 说明联接类型

该`type`列 [`EXPLAIN`](sql-statements.html#explain)输出介绍如何联接表。在JSON格式的输出中，这些作为`access_type`属性的值找到。以下列表描述了连接类型，从最佳类型到最差类型：

- [`system`](optimization.html#jointype_system)

  

  

  

  该表只有一行（=系统表）。这是[`const`](optimization.html#jointype_const)联接类型的特例 。

- [`const`](optimization.html#jointype_const)

  

  

  

  该表最多具有一个匹配行，该行在查询开始时读取。因为只有一行，所以优化器的其余部分可以将这一行中列的值视为常量。 [`const`](optimization.html#jointype_const)表非常快，因为它们只能读取一次。

  [`const`](optimization.html#jointype_const)在将a `PRIMARY KEY`或 `UNIQUE`index的所有部分与常数值进行比较时使用。在以下查询中，*`tbl_name`*可以用作[`const`](optimization.html#jointype_const) 表：

  ```
  SELECT * FROM tbl_nameWHERE primary_key= 1;
  
  SELECT * FROM tbl_name
    WHERE primary_key_part1= 1 AND primary_key_part2= 2;
  ```

- [`eq_ref`](optimization.html#jointype_eq_ref)

  

  

  对于先前表中的每行组合，从此表中读取一行。除了 [`system`](optimization.html#jointype_system)和 [`const`](optimization.html#jointype_const)类型，这是最好的联接类型。当连接使用索引的所有部分并且索引为a `PRIMARY KEY`或`UNIQUE NOT NULL`index时使用。

  [`eq_ref`](optimization.html#jointype_eq_ref)可用于使用`=`运算符进行比较的索引列 。比较值可以是常量，也可以是使用在此表之前读取的表中列的表达式。在以下示例中，MySQL可以使用 [`eq_ref`](optimization.html#jointype_eq_ref)联接进行处理 *`ref_table`*：

  ```
  SELECT * FROM ref_table，other_table
    WHERE ref_table。key_column= other_table。column;
  
  SELECT * FROM ref_table，other_table
    WHERE ref_table。key_column_part1= other_table。column
    与ref_table。key_column_part2= 1;
  ```

- [`ref`](optimization.html#jointype_ref)

  

  

  对于先前表中的每个行组合，将从该表中读取具有匹配索引值的所有行。[`ref`](optimization.html#jointype_ref)如果联接仅使用键的最左前缀，或者如果键不是a `PRIMARY KEY`或 `UNIQUE`索引（换句话说，如果联接无法根据键值选择单个行），则使用。如果使用的键仅匹配几行，则这是一种很好的联接类型。

  [`ref`](optimization.html#jointype_ref)可以用于使用`=`或`<=>` 运算符进行比较的索引列 。在以下示例中，MySQL可以使用 [`ref`](optimization.html#jointype_ref)联接进行处理 *`ref_table`*：

  ```
  SELECT * FROM ref_tableWHERE key_column= expr;
  
  SELECT * FROM ref_table，other_table
    WHERE ref_table。key_column= other_table。column;
  
  SELECT * FROM ref_table，other_table
    WHERE ref_table。key_column_part1= other_table。column
    与ref_table。key_column_part2= 1;
  ```

- [`fulltext`](optimization.html#jointype_fulltext)

  

  

  使用`FULLTEXT` 索引执行联接。

- [`ref_or_null`](optimization.html#jointype_ref_or_null)

  

  

  这种连接类型类似于 [`ref`](optimization.html#jointype_ref)，但是除了MySQL会额外搜索包含`NULL`值的行。此联接类型优化最常用于解析子查询。在以下示例中，MySQL可以使用 [`ref_or_null`](optimization.html#jointype_ref_or_null)联接进行处理*`ref_table`*：

  ```
  SELECT * FROM ref_table
    WHERE key_column= exprOR key_columnIS NULL;
  ```

  请参见[第8.2.1.13节“ IS NULL优化”](optimization.html#is-null-optimization)。

- [`index_merge`](optimization.html#jointype_index_merge)

  

  

  此联接类型指示使用索引合并优化。在这种情况下，`key`输出行中的列包含使用的索引列表，并`key_len`包含使用的索引 的最长键部分的列表。有关更多信息，请参见 [第8.2.1.3节“索引合并优化”](optimization.html#index-merge-optimization)。

- [`unique_subquery`](optimization.html#jointype_unique_subquery)

  

  

  此类型替换 以下形式的[`eq_ref`](optimization.html#jointype_eq_ref)某些 `IN`子查询：

  ```
  valueIN（primary_key从single_table此处选择some_expr）
  ```

  [`unique_subquery`](optimization.html#jointype_unique_subquery) 只是一个索引查找函数，它完全替代了子查询以提高效率。

- [`index_subquery`](optimization.html#jointype_index_subquery)

  

  

  此连接类型类似于 [`unique_subquery`](optimization.html#jointype_unique_subquery)。它替换`IN`子查询，但适用于以下形式的子查询中的非唯一索引：

  ```
  valueIN（key_column从single_table此处选择some_expr）
  ```

- [`range`](optimization.html#jointype_range)

  

  

  使用索引选择行，仅检索给定范围内的行。的`key` 输出行中的列指示使用哪个索引。将`key_len`包含已使用的时间最长的关键部分。该`ref`列 `NULL`适用于此类型。

  [`range`](optimization.html#jointype_range)当一个键列使用任何的相比于恒定可使用 [`=`](functions.html#operator_equal)， [`<>`](functions.html#operator_not-equal)， [`>`](functions.html#operator_greater-than)， [`>=`](functions.html#operator_greater-than-or-equal)， [`<`](functions.html#operator_less-than)， [`<=`](functions.html#operator_less-than-or-equal)， [`IS NULL`](functions.html#operator_is-null)， [`<=>`](functions.html#operator_equal-to)， [`BETWEEN`](functions.html#operator_between)， [`LIKE`](functions.html#operator_like)，或 [`IN()`](functions.html#operator_in)运营商：

  ```
  SELECT * FROM tbl_name
    WHERE key_column= 10;
  
  SELECT * FROM tbl_name
    WHERE key_column10和20之间;
  
  选择*从tbl_name
    哪里key_column（10,20,30）;
  
  SELECT * FROM tbl_name
    WHERE key_part1= 10 AND key_part2IN（10,20,30）;
  ```

- [`index`](optimization.html#jointype_index)

  

  

  该`index`联接类型是一样的 [`ALL`](optimization.html#jointype_all)，只是索引树被扫描。这发生两种方式：

  - 如果索引是查询的覆盖索引，并且可用于满足表中所需的所有数据，则仅扫描索引树。在这种情况下，`Extra`列为 `Using index`。仅索引扫描通常比索引扫描更快， [`ALL`](optimization.html#jointype_all)因为索引的大小通常小于表数据。
  - 使用对索引的读取执行全表扫描，以按索引顺序查找数据行。 `Uses index`没有出现在 `Extra`列中。

  当查询仅使用属于单个索引一部分的列时，MySQL可以使用此联接类型。

- [`ALL`](optimization.html#jointype_all)

  

  

  对来自先前表的行的每个组合进行全表扫描。如果该表是未标记的第一个表 [`const`](optimization.html#jointype_const)，则通常不好，在其他所有情况下通常 *非常*糟糕。通常，可以[`ALL`](optimization.html#jointype_all)通过添加索引来避免这种情况，这些 索引允许基于早期表中的常量值或列值从表中检索行。

#### 了解更多信息

该`Extra`列 [`EXPLAIN`](sql-statements.html#explain)输出包含MySQL解决查询的额外信息。以下列表说明了可以在此列中显示的值。每个项目还针对JSON格式的输出指示哪个属性显示`Extra`值。对于其中一些，有一个特定的属性。其他显示为`message` 属性的文本。

如果你想使你的查询尽可能快，看出来`Extra`的列值`Using filesort`和`Using temporary`，或在JSON格式的`EXPLAIN`输出，用于 `using_filesort`和 `using_temporary_table`性能等于 `true`。

- `Child of '*`table`*' pushed join@1`（JSON：`message` 文本）

  该表是*`table`*可以向下推到NDB内核的联接中的子级引用 。启用下推联接时，仅适用于NDB群集。有关[`ndb_join_pushdown`](mysql-cluster.html#sysvar_ndb_join_pushdown)更多信息和示例，请参见服务器系统变量的描述 。

- `const row not found`（JSON属性： `const_row_not_found`）

  对于诸如之类的查询，该表为空。 `SELECT ... FROM *`tbl_name`*`

- `Deleting all rows`（JSON属性： `message`）

  对于[`DELETE`](sql-statements.html#delete)，某些存储引擎（如[`MyISAM`](storage-engines.html#myisam-storage-engine)）支持一种处理程序方法，该方法以一种简单而快速的方式删除所有表行。`Extra`如果引擎使用此优化，则显示此值。

- `Distinct`（JSON属性： `distinct`）

  MySQL正在寻找不同的值，因此在找到第一个匹配行之后，它将停止为当前行组合搜索更多行。

- `FirstMatch(*`tbl_name`*)` （JSON属性：`first_match`）

  半连接FirstMatch连接快捷方式策略用于*`tbl_name`*。

- `Full scan on NULL key`（JSON属性： `message`）

  当优化器无法使用索引查找访问方法时，这会作为子查询优化的后备策略而发生。

- `Impossible HAVING`（JSON属性： `message`）

  该`HAVING`子句始终为false，无法选择任何行。

- `Impossible WHERE`（JSON属性： `message`）

  该`WHERE`子句始终为false，无法选择任何行。

- `Impossible WHERE noticed after reading const tables`（JSON属性： `message`）

  MySQL已经读取了所有 [`const`](optimization.html#jointype_const)（和 [`system`](optimization.html#jointype_system)）表，并注意到该`WHERE`子句始终为false。

- `LooseScan(*`m`*..*`n`*)` （JSON属性：`message`）

  使用半连接的LooseScan策略。 *`m`*和 *`n`*是关键零件号。

- `No matching min/max row`（JSON属性： `message`）

  没有行满足查询的条件，例如 。 `SELECT MIN(...) FROM ... WHERE *`condition`*`

- `no matching row in const table`（JSON属性：`message`）

  对于具有联接的查询，存在一个空表或一个表中没有满足唯一索引条件的行。

- `No matching rows after partition pruning`（JSON属性： `message`）

  对于[`DELETE`](sql-statements.html#delete)或 [`UPDATE`](sql-statements.html#update)，优化器在分区修剪后找不到要删除或更新的内容。它的含义类似于`Impossible WHERE` for [`SELECT`](sql-statements.html#select)语句。

- `No tables used`（JSON属性： `message`）

  查询没有`FROM`子句，或者有 `FROM DUAL`子句。

  对于[`INSERT`](sql-statements.html#insert)或 [`REPLACE`](sql-statements.html#replace)语句， [`EXPLAIN`](sql-statements.html#explain)在没有任何[`SELECT`](sql-statements.html#select) 部分时显示此值。例如，出现的`EXPLAIN INSERT INTO t VALUES(10)`原因是因为等同于 `EXPLAIN INSERT INTO t SELECT 10 FROM DUAL`。

- `Not exists`（JSON属性： `message`）

  MySQL能够对`LEFT JOIN` 查询进行优化，并且在找到符合`LEFT JOIN`条件的一行后，不检查该表中的更多行是否为上一行。这是可以通过这种方式优化的查询类型的示例：

  ```
  选择* FROM t1左联接t2 ON t1.id = t2.id
    t2.id为NULL；
  ```

  假设`t2.id`定义为 `NOT NULL`。在这种情况下，MySQL 使用的值 扫描 `t1`并查找行 。如果MySQL在中找到匹配的行 ，它将知道它 永远不会是 ，并且不会扫描具有相同值的其余行。换句话说，对于in中的每一行，MySQL 实际上只需进行一次查找，无论in中实际匹配多少行。 `t2``t1.id``t2``t2.id``NULL``t2``id``t1``t2``t2`

- `Plan isn't ready yet` （JSON属性：无）

  [`EXPLAIN FOR CONNECTION`](optimization.html#explain-for-connection)当优化器尚未完成为在命名连接中执行的语句创建执行计划时， 就会出现此值。如果执行计划输出包含多行，则`Extra`取决于优化程序确定完整执行计划的进度，其中任何一行或所有行都可以具有此 值。

- `Range checked for each record (index map: *`N`*)`（JSON属性： `message`）

  MySQL找不到很好的索引来使用，但是发现一些索引可以在已知先前表中的列值之后使用。对于上表中的每个行组合，MySQL检查是否可以使用[`range`](optimization.html#jointype_range)或 [`index_merge`](optimization.html#jointype_index_merge)访问方法来检索行。这不是很快，但是比完全没有索引的连接要快。适用标准如 [第8.2.1.2节“范围优化”](optimization.html#range-optimization)和 [第8.2.1.3节“索引合并优化”中所述](optimization.html#index-merge-optimization)，除了上表的所有列值都是已知的并且被视为常量。

  索引从1开始编号，其顺序[`SHOW INDEX`](sql-statements.html#show-index)与表中显示的顺序相同。索引图值 *`N`*是指示哪些索引为候选的位掩码值。例如，值`0x19`（二进制11001）表示将考虑索引1、4和5。

- `Scanned *`N`* databases`（JSON属性： `message`）

  这表示在处理`INFORMATION_SCHEMA`表查询时服务器执行了多少目录扫描 ，如[第8.2.3节“优化INFORMATION_SCHEMA查询”中所述](optimization.html#information-schema-optimization)。的值*`N`*可以是0、1或 `all`。

- `Select tables optimized away`（JSON属性：`message`）

  优化器确定1）最多应返回一行，以及2）要生成该行，必须读取确定的行集。当在优化阶段可以读取要读取的行时（例如，通过读取索引行），则在查询执行期间无需读取任何表。

  当查询被隐式分组（包含聚合函数但没有`GROUP BY`子句）时，满足第一个条件 。当每个使用的索引执行一次行查找时，满足第二个条件。读取的索引数决定了要读取的行数。

  考虑以下隐式分组查询：

  ```
  从t1中选择MIN（c1），MIN（c2）；
  ```

  假设`MIN(c1)`可以通过读取一个索引行`MIN(c2)` 来检索，并且可以通过从另一索引中读取一行来进行检索。即，对于每一列`c1`和 `c2`，存在其中列是索引的第一列的索引。在这种情况下，将通过读取两个确定性行来返回一行。

  `Extra`如果要读取的行不确定，则不会出现 此值。考虑以下查询：

  ```
  在t1处选择MIN（c2），其中c1 <= 10;
  ```

  假设这`(c1, c2)`是一个覆盖指数。使用此索引，`c1 <= 10`必须扫描所有具有的行以找到最小值 `c2`。相比之下，请考虑以下查询：

  ```
  在t1处选择MIN（c2），其中c1 = 10;
  ```

  在这种情况下，第一个索引行`c1 = 10`包含最小值`c2` 。仅一行必须读取才能产生返回的行。

  对于每个表都具有精确行数的存储引擎（例如`MyISAM`，但不是 `InnoDB`），对于缺少该子句或始终为true且没有 子句的查询，`Extra` 可能会出现此值。（这是一个隐式分组查询的实例，其中存储引擎影响是否可以读取确定数量的行。） `COUNT(*)``WHERE``GROUP BY`

- `Skip_open_table`， `Open_frm_only`， `Open_full_table`（JSON属性： `message`）

  这些值指示适用于`INFORMATION_SCHEMA` 表查询的文件打开优化，如 [第8.2.3节“优化INFORMATION_SCHEMA查询”中所述](optimization.html#information-schema-optimization)。

  - `Skip_open_table`：不需要打开表文件。通过扫描数据库目录，该信息已在查询中可用。
  - `Open_frm_only`：仅`.frm`需要打开表的文件。
  - `Open_full_table`：未优化的信息查找。的`.frm`， `.MYD`和 `.MYI`文件必须被打开。

- `Start temporary`，`End temporary`（JSON属性： `message`）

  这表明临时表用于半联接重复淘汰策略。

- `unique row not found`（JSON属性： `message`）

  对于诸如的查询，没有行满足 索引或表中的条件。 `SELECT ... FROM *`tbl_name`*``UNIQUE``PRIMARY KEY`

- `Using filesort`（JSON属性： `using_filesort`）

  MySQL必须额外进行一遍，以找出如何按排序顺序检索行。排序是通过根据联接类型遍历所有行并存储与该`WHERE`子句匹配的所有行的排序键和指向该行的指针来完成的。然后对键进行排序，并按排序顺序检索行。请参见 [第8.2.1.14节“按优化排序”](optimization.html#order-by-optimization)。

- `Using index`（JSON属性： `using_index`）

  仅使用索引树中的信息从表中检索列信息，而不必进行其他查找以读取实际行。当查询仅使用属于单个索引的列时，可以使用此策略。

  对于`InnoDB`具有用户定义的聚集索引的表，即使列中`Using index`不存在 该索引也可以使用`Extra`。如果`type`is [`index`](optimization.html#jointype_index)和 `key`is 就是这种情况 `PRIMARY`。

- `Using index condition`（JSON属性： `using_index_condition`）

  通过访问索引元组并首先对其进行测试以确定是否读取完整的表行来读取表。这样，除非必要，否则索引信息将用于延迟（“ 下推 ”）读取整个表行。请参见 [第8.2.1.5节“索引条件下推优化”](optimization.html#index-condition-pushdown-optimization)。

- `Using index for group-by`（JSON属性：`using_index_for_group_by`）

  与`Using index`表访问方法类似，`Using index for group-by` 表示MySQL找到了一个索引，该索引可用于检索a `GROUP BY`或 `DISTINCT`查询的所有列，而无需对实际表进行任何额外的磁盘访问。此外，以最有效的方式使用索引，因此对于每个组，仅读取少数索引条目。有关详细信息，请参见 [第8.2.1.15节“通过优化组”](optimization.html#group-by-optimization)。

- `Using join buffer (Block Nested Loop)`， `Using join buffer (Batched Key Access)` （JSON属性：`using_join_buffer`）

  来自较早联接的表被部分读取到联接缓冲区中，然后从缓冲区中使用它们的行来执行与当前表的联接。 `(Block Nested Loop)`表示使用块嵌套循环算法，并`(Batched Key Access)`表示使用批处理密钥访问算法。即，[`EXPLAIN`](sql-statements.html#explain)缓冲输出的前一行中的表中的键 ，并从出现行所在的表中批量提取匹配的行 `Using join buffer`。

  在JSON格式的输出中，的值 `using_join_buffer`始终为`Block Nested Loop`或之一 `Batched Key Access`。

  有关这些算法的更多信息，请参见 [块嵌套循环联接算法](optimization.html#block-nested-loop-join-algorithm)和 [批量密钥访问联接](optimization.html#bka-optimization)。

- `Using MRR`（JSON属性： `message`）

  使用多范围读取优化策略读取表。请参见[第8.2.1.10节“多范围读取优化”](optimization.html#mrr-optimization)。

- `Using sort_union(...)`，`Using union(...)`，`Using intersect(...)`（JSON属性： `message`）

  这些指示了特定算法，该算法显示了如何针对[`index_merge`](optimization.html#jointype_index_merge)联接类型合并索引扫描 。请参见[第8.2.1.3节“索引合并优化”](optimization.html#index-merge-optimization)。

- `Using temporary`（JSON属性： `using_temporary_table`）

  为了解决该查询，MySQL需要创建一个临时表来保存结果。如果查询包含`GROUP BY`和 `ORDER BY`子句以不同的方式列出列，通常会发生这种情况。

- `Using where`（JSON属性： `attached_condition`）

  甲`WHERE`子句用于限制来匹配下一个表或发送到客户端的行。除非您特别打算从表中获取或检查所有行，否则如果查询中的`Extra`值不是 `Using where`并且表连接类型为[`ALL`](optimization.html#jointype_all)或 ，则 查询中可能会出错[`index`](optimization.html#jointype_index)。

  `Using where`在JSON格式的输出中没有直接对应的内容；该 `attached_condition`属性包含使用的任何`WHERE`条件。

- `Using where with pushed condition`（JSON属性：`message`）

  此产品适用于[`NDB`](mysql-cluster.html) 表*只*。这意味着NDB Cluster正在使用条件下推优化来提高在非索引列和常量之间进行直接比较的效率。在这种情况下，条件被“ 下推 ”到群集的数据节点，并同时在所有数据节点上进行评估。这样就无需通过网络发送不匹配的行，并且在可以但不使用条件下推的情况下，可以将此类查询的速度提高5到10倍。有关更多信息，请参见 [第8.2.1.4节“发动机状况下推优化”](optimization.html#condition-pushdown-optimization)。

- `Zero limit`（JSON属性： `message`）

  该查询有一个`LIMIT 0`子句，不能选择任何行。

#### 解释输出解释

通过获取输出`rows` 列中值的乘积，可以很好地表明联接的良好程度[`EXPLAIN`](sql-statements.html#explain)。这应该大致告诉您MySQL必须检查多少行才能执行查询。如果使用[`max_join_size`](server-administration.html#sysvar_max_join_size)系统变量限制查询，则 此行乘积还用于确定[`SELECT`](sql-statements.html#select) 执行哪些多表语句以及中止哪个多表语句。请参见 [第5.1.1节“配置服务器”](server-administration.html#server-configuration)。

以下示例显示了如何根据提供的信息逐步优化多表联接 [`EXPLAIN`](sql-statements.html#explain)。

假设您在[`SELECT`](sql-statements.html#select)此处显示了该 语句，并计划使用进行检查 [`EXPLAIN`](sql-statements.html#explain)：

```
解释选择tt.TicketNumber，tt.TimeIn，
               tt.ProjectReference，tt.EstimatedShipDate，
               tt.ActualShipDate，tt.ClientID，
               tt.ServiceCodes，tt.RepetitiveID，
               tt.CurrentProcess，tt.CurrentDPPerson，
               tt.RecordVolume，tt.DPPrinted等，COUNTRY，
               et_1.COUNTRY，do.CUSTNAME
        从tt等et et et_1开始
        tt.SubmitTime是NULL
          AND tt.ActualPC = et.EMPLOYID
          AND tt.AssignedPC = et_1.EMPLOYID
          AND tt.ClientID = do.CUSTNMBR;
```

对于此示例，进行以下假设：

- 被比较的列已声明如下。

  | 表   | 柱           | 数据类型   |
  | :--- | :----------- | :--------- |
  | `tt` | `ActualPC`   | `CHAR(10)` |
  | `tt` | `AssignedPC` | `CHAR(10)` |
  | `tt` | `ClientID`   | `CHAR(10)` |
  | `et` | `EMPLOYID`   | `CHAR(15)` |
  | `do` | `CUSTNMBR`   | `CHAR(15)` |

- 这些表具有以下索引。

  | 表   | 指数                      |
  | :--- | :------------------------ |
  | `tt` | `ActualPC`                |
  | `tt` | `AssignedPC`              |
  | `tt` | `ClientID`                |
  | `et` | `EMPLOYID` （首要的关键） |
  | `do` | `CUSTNMBR` （首要的关键） |

- 这些`tt.ActualPC`值分布不均。

最初，在执行任何优化之前，该 [`EXPLAIN`](sql-statements.html#explain)语句会产生以下信息：

```
表类型Possible_keys键key_len参考行Extra
等ALL PRIMARY NULL NULL NULL 74
第2135章
et_1 ALL PRIMARY NULL NULL NULL 74
tt ALL AssignedPC，NULL NULL NULL 3872
           客户编号，
           实际电脑
      检查每个记录的范围（索引图：0x23）
```

因为`type`是 [`ALL`](optimization.html#jointype_all)针对每个表的，所以此输出表明MySQL正在生成所有表的笛卡尔积；也就是说，行的每种组合。这需要相当长的时间，因为必须检查每个表中的行数的乘积。对于当前情况，此乘积为74×2135×74×3872 = 45,268,558,720行。如果桌子更大，您只能想象需要多长时间。

这里的一个问题是，如果将索引声明为相同的类型和大小，则MySQL可以更有效地在列上使用索引。在这种情况下，[`VARCHAR`](data-types.html#char)与 [`CHAR`](data-types.html#char)被认为是相同的，如果它们被声明为相同的大小。 `tt.ActualPC`声明为 `CHAR(10)`和`et.EMPLOYID` 是`CHAR(15)`，因此长度不匹配。

要解决此列长度之间的差异，请使用 从10个字符[`ALTER TABLE`](sql-statements.html#alter-table)延长 `ActualPC`到15个字符：

```
mysql> ALTER TABLE tt MODIFY ActualPC VARCHAR(15);
```

现在`tt.ActualPC`和 `et.EMPLOYID`都是 `VARCHAR(15)`。[`EXPLAIN`](sql-statements.html#explain)再次执行该 语句将产生以下结果：

```
表类型Possible_keys键key_len参考行Extra
tt ALL AssignedPC，NULL NULL NULL 3872使用
             ClientID，在哪里
             实际电脑
第2135章
      检查每个记录的范围（索引图：0x1）
et_1 ALL PRIMARY NULL NULL NULL 74
      检查每个记录的范围（索引图：0x1）
et eq_ref PRIMARY PRIMARY 15 tt.ActualPC 1
```

这不是完美的，但是更好：`rows`值的乘积 少了74倍。此版本在几秒钟内执行。

可以进行第二种更改以消除`tt.AssignedPC = et_1.EMPLOYID`和`tt.ClientID = do.CUSTNMBR`比较的列长不匹配：

```
mysql> ALTER TABLE tt MODIFY AssignedPC VARCHAR(15),
                      MODIFY ClientID   VARCHAR(15);
```

修改之后， [`EXPLAIN`](sql-statements.html#explain)产生如下所示的输出：

```
表类型Possible_keys键key_len参考行Extra
等ALL PRIMARY NULL NULL NULL 74
tt ref AssignedPC，ActualPC 15等EMPLOYID 52使用
             ClientID，在哪里
             实际电脑
et_1 eq_ref主PRIMARY 15 tt.AssignedPC 1
做eq_ref PRIMARY PRIMARY 15 tt.ClientID 1
```

在这一点上，查询尽可能地被优化。剩下的问题是，默认情况下，MySQL假定该`tt.ActualPC` 列中的值是均匀分布的，而表则不是这种情况`tt`。幸运的是，很容易告诉MySQL分析密钥分布：

```
mysql> ANALYZE TABLE tt;
```

使用其他索引信息，联接是完美的，并 [`EXPLAIN`](sql-statements.html#explain)产生以下结果：

```
表类型Possible_keys键key_len参考行Extra
tt ALL AssignedPC NULL NULL NULL 3872使用
             ClientID，在哪里
             实际电脑
et eq_ref PRIMARY PRIMARY 15 tt.ActualPC 1
et_1 eq_ref主PRIMARY 15 tt.AssignedPC 1
做eq_ref PRIMARY PRIMARY 15 tt.ClientID 1
```

在`rows`从输出列 [`EXPLAIN`](sql-statements.html#explain)是一个受过教育的猜测从MySQL联接优化。通过将`rows`乘积与查询返回的实际行数进行比较，检查数字是否接近真实 值。如果数字完全不同，则可以通过`STRAIGHT_JOIN`在 [`SELECT`](sql-statements.html#select)语句中使用并尝试在`FROM`子句中以不同顺序列出表来 获得更好的性能 。（但是，`STRAIGHT_JOIN`由于它禁用了半[联接转换](optimization.html#semijoins)， 可能会阻止使用索引。请参见[第8.2.2.1节“](optimization.html#semijoins)使用半 [联接转换优化子查询，派生表和视图引用”）](optimization.html#semijoins)）

在某些情况下，可能会执行[`EXPLAIN SELECT`](sql-statements.html#explain)与子查询一起使用时会修改数据的语句。有关更多信息，请参见[第13.2.10.8节“派生表”](sql-statements.html#derived-tables)。

### 8.8.3扩展的EXPLAIN输出格式

对于[`SELECT`](sql-statements.html#select)语句，该 [`EXPLAIN`](sql-statements.html#explain)语句会产生额外的（“ 扩展的 ”）信息，这些信息不是[`EXPLAIN`](sql-statements.html#explain)输出的一部分， 但可以通过在[`SHOW WARNINGS`](sql-statements.html#show-warnings) 以下语句发出来查看[`EXPLAIN`](sql-statements.html#explain)。输出中的 `Message`值[`SHOW WARNINGS`](sql-statements.html#show-warnings)显示优化器如何限定[`SELECT`](sql-statements.html#select)语句 中的表名和列名， [`SELECT`](sql-statements.html#select)应用重写和优化规则后的外观以及有关优化过程的其他注释。

可在[`SHOW WARNINGS`](sql-statements.html#show-warnings)语句后面 显示的扩展信息 [`EXPLAIN`](sql-statements.html#explain)仅针对 [`SELECT`](sql-statements.html#select)语句生成。 [`SHOW WARNINGS`](sql-statements.html#show-warnings)显示其他可解释语句空结果（[`DELETE`](sql-statements.html#delete)， [`INSERT`](sql-statements.html#insert)， [`REPLACE`](sql-statements.html#replace)，和 [`UPDATE`](sql-statements.html#update)）。

注意

在较早的MySQL版本中，使用产生了扩展信息[`EXPLAIN EXTENDED`](sql-statements.html#explain)。仍然可以识别该语法以实现向后兼容性，但是默认情况下现在启用了扩展输出，因此该`EXTENDED`关键字是多余的并且已弃用。它的使用会导致警告，并且它将[`EXPLAIN`](sql-statements.html#explain) 在将来的MySQL版本中从语法中删除。

这是扩展[`EXPLAIN`](sql-statements.html#explain)输出的示例 ：

```
mysql> EXPLAIN
       SELECT t1.a, t1.a IN (SELECT t2.a FROM t2) FROM t1\G
*************************** 1.行******************** *******
           编号：1
  select_type：主要
        表：t1
         类型：索引
可能的键：NULL
          关键：主要
      key_len：4
          参考：NULL
         行数：4
     已过滤：100.00
        额外：使用索引
*************************** 2.行******************** *******
           id：2
  select_type：SUBQUERY
        表：t2
         类型：索引
Possible_Keys：一个
          关键：一个
      key_len：5
          参考：NULL
         行数：3
     已过滤：100.00
        额外：使用索引
集合中有2行，有1条警告（0.00秒）

mysql> SHOW WARNINGS\G
*************************** 1.行******************** *******
  级别：注意
   编号：1003
消息：/ *选择＃1 * /选择`test`.`t1`.`a` AS`a`，
         <in_optimizer>（`test`.`t1`.`a`，`test`.`t1`.`a`在
         （<materialize>（/ * select＃2 * /选择`test`.`t2`.`a`
         来自`test`.`t2`，其中1具有1），
         <primary_index_lookup>（`test`.`t1`.`a`在
         <auto_key>上的<temporary table>
         其中（（`test`.`t1`.`a` =`物化子查询`.`a`））））））AS`t1.a
         从（test..t1）输入（从t2选择t2.a）
设置1行（0.00秒）
```

由于显示的语句[`SHOW WARNINGS`](sql-statements.html#show-warnings)可能包含特殊标记以提供有关查询重写或优化程序操作的信息，因此该语句不一定是有效的SQL，也不打算执行。输出中可能还包含带有`Message`值的行，这些 值提供有关优化程序采取的操作的其他非SQL解释性说明。

以下列表描述了特殊的标记，这些标记可以出现在由[`SHOW WARNINGS`](sql-statements.html#show-warnings)下列显示的扩展输出中：

- `<auto_key>`

  自动生成的临时表密钥。

- `<cache>(*`expr`*)`

  表达式（例如标量子查询）执行一次，结果值保存在内存中以备后用。对于包含多个值的结果，可能会创建一个临时表，您将会看到`<temporary table>`。

- `<exists>(*`query fragment`*)`

  子查询谓词被转换为 `EXISTS`谓词，并且子查询被转换为可以与`EXISTS`谓词一起使用 。

- `<in_optimizer>(*`query fragment`*)`

  这是一个内部优化器对象，对用户没有任何意义。

- `<index_lookup>(*`query fragment`*)`

  使用索引查找来处理查询片段以查找合格的行。

- `<if>(*`condition`*, *`expr1`*, *`expr2`*)`

  如果条件为true，则求值为 *`expr1`*，否则为 *`expr2`*。

- `<is_not_null_test>(*`expr`*)`

  验证表达式不等于的测试 `NULL`。

- `<materialize>(*`query fragment`*)`

  使用子查询实现。

- ``materialized-subquery`.*`col_name`*`

  实现了*`col_name`*对内部临时表中列的引用， 以保存评估子查询的结果。

- `<primary_index_lookup>(*`query fragment`*)`

  使用主键查找来处理查询片段以查找合格的行。

- `<ref_null_helper>(*`expr`*)`

  这是一个内部优化器对象，对用户没有任何意义。

- `/* select#*`N`* */ *`select_stmt`*`

  将`SELECT`与在非扩展的行相关联[`EXPLAIN`](sql-statements.html#explain)，其具有一输出`id`的值 *`N`*。

- `*`outer_tables`* semi join (*`inner_tables`*)`

  半联接操作。 *`inner_tables`*显示未拉出的表。请参见[第8.2.2.1节“使用半联接转换优化子查询，派生表和视图引用”](optimization.html#semijoins)。

- `<temporary table>`

  这表示为缓存中间结果而创建的内部临时表。

当某些表属于[`const`](optimization.html#jointype_const) 或[`system`](optimization.html#jointype_system)类型时，涉及这些表中的列的表达式将由优化器尽早评估，并且不属于所显示语句的一部分。但是，使用时`FORMAT=JSON`，某些 [`const`](optimization.html#jointype_const)表访问将显示为[`ref`](optimization.html#jointype_ref)使用const值的访问。

### 8.8.4获取命名连接的执行计划信息

要获得在命名连接中执行的可解释语句的执行计划，请使用以下语句：

```
解释[ options]用于连接connection_id;
```

[`EXPLAIN FOR CONNECTION`](optimization.html#explain-for-connection)返回[`EXPLAIN`](sql-statements.html#explain)当前在给定连接中用于执行查询的信息。由于数据（和支持统计数据）的更改，它可能会产生与[`EXPLAIN`](sql-statements.html#explain)在等效查询文本上运行不同的结果 。行为上的这种差异对于诊断更多瞬时性能问题很有用。例如，如果您在一个会话中运行需要很长时间才能完成的语句，则[`EXPLAIN FOR CONNECTION`](optimization.html#explain-for-connection)在另一个会话中使用该语句可能会产生有关延迟原因的有用信息。

*`connection_id`*是从`INFORMATION_SCHEMA` [`PROCESSLIST`](information-schema.html#processlist-table)表或 [`SHOW PROCESSLIST`](sql-statements.html#show-processlist)语句获得的连接标识符 。如果您有[`PROCESS`](security.html#priv_process)特权，则可以为任何连接指定标识符。否则，您只能为自己的连接指定标识符。

如果命名连接未执行语句，则结果为空。否则，`EXPLAIN FOR CONNECTION` 仅当在命名连接中执行的语句是可解释的时才适用。这包括 [`SELECT`](sql-statements.html#select)， [`DELETE`](sql-statements.html#delete)， [`INSERT`](sql-statements.html#insert)， [`REPLACE`](sql-statements.html#replace)，和 [`UPDATE`](sql-statements.html#update)。（但是， `EXPLAIN FOR CONNECTION`不适用于预备语句，甚至不适用于这些类型的预备语句。）

如果命名连接正在执行一条可解释的语句，则输出将是您`EXPLAIN`在语句本身上使用所获得的结果 。

如果命名连接正在执行不可解释的语句，则会发生错误。例如，由于`EXPLAIN`无法解释，因此无法命名当前会话的连接标识符 ：

```
mysql> SELECT CONNECTION_ID();
+ ----------------- +
| CONNECTION_ID（）|
+ ----------------- +
| 373 |
+ ----------------- +
设置1行（0.00秒）

mysql> EXPLAIN FOR CONNECTION 373;
错误1889（HY000）：支持连接说明命令
仅用于SELECT / UPDATE / INSERT / DELETE / REPLACE
```

该`Com_explain_other`状态变量表示的数 [`EXPLAIN FOR CONNECTION`](sql-statements.html#explain)执行的语句。

### 8.8.5估计查询性能



在大多数情况下，您可以通过计算磁盘搜索次数来估计查询性能。对于小型表，通常可以在一个磁盘搜索中找到一行（因为索引可能已缓存）。对于更大的表，您可以估计，使用B树索引，您需要进行许多查找才能找到行： 。 `log(*`row_count`*) / log(*`index_block_length`* / 3 * 2 / (*`index_length`* + *`data_pointer_length`*)) + 1`

在MySQL中，索引块通常为1,024字节，数据指针通常为四个字节。对于500,000行的键值长度为三个字节（的大小 [`MEDIUMINT`](data-types.html#integer-types)）的表，该公式表示 `log(500,000)/log(1024/3*2/(3+4)) + 1`=搜索 `4`。

该索引将需要大约500,000 * 7 * 3/2 = 5.2MB的存储空间（假设典型的索引缓冲区填充率为2/3），因此您可能在内存中拥有很多索引，因此只需要一个或两个调用即可读取数据以查找行。

但是，对于写操作，您需要四个搜索请求以查找在何处放置新索引值，通常需要两个搜索来更新索引并写入行。

前面的讨论并不意味着您的应用程序性能会因log缓慢下降 *`N`*。只要所有内容都由OS或MySQL服务器缓存，随着表的增大，事情只会变得稍微慢一些。在数据变得太大而无法缓存之后，事情开始变得缓慢得多，直到您的应用程序仅受磁盘搜索约束（随日志增长 *`N`*）。为避免这种情况，请随着数据的增长而增加密钥缓存的大小。对于`MyISAM` 表，键缓存大小由[`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)系统变量控制 。请参见[第5.1.1节“配置服务器”](server-administration.html#server-configuration)。

## 8.9控制查询优化器

- [8.9.1控制查询计划评估](optimization.html#controlling-query-plan-evaluation)
- [8.9.2可切换的优化](optimization.html#switchable-optimizations)
- [8.9.3优化器提示](optimization.html#optimizer-hints)
- [8.9.4索引提示](optimization.html#index-hints)
- [8.9.5优化器成本模型](optimization.html#cost-model)



MySQL通过系统变量提供优化器控制，这些系统变量会影响如何评估查询计划，可切换的优化，优化器和索引提示以及优化器成本模型。

### 8.9.1控制查询计划评估



查询优化器的任务是找到执行SQL查询的最佳计划。因为“ 好 ”和“ 坏 ”之间的性能差异计划可能是一个数量级（即几秒钟相对于几小时甚至几天），大多数查询优化器（包括MySQL）在所有可能的查询评估计划中或多或少地穷举搜索最佳计划。对于联接查询，MySQL优化器调查的可能计划的数量与查询中引用的表的数量成指数增长。对于少量表（通常少于7到10），这不是问题。但是，提交较大的查询时，花在查询优化上的时间可能很容易成为服务器性能的主要瓶颈。

一种更灵活的查询优化方法，使用户可以控制优化程序在搜索最佳查询评估计划时的详尽程度。通常的想法是，优化器调查的计划越少，则编译查询所花费的时间就越少。另一方面，由于优化器跳过了一些计划，因此可能会找不到最佳计划。

可以使用两个系统变量来控制优化器相对于评估的计划数量的行为：

- 该[`optimizer_prune_level`](server-administration.html#sysvar_optimizer_prune_level) 变量告诉优化器根据对每个表访问的行数的估计来跳过某些计划。我们的经验表明，这种“有根据的猜测 ”很少会错过最佳计划，并且可能会大大减少查询的编译时间。这就是为什么此选项`optimizer_prune_level=1`默认为（）的原因。但是，如果您认为优化器错过了更好的查询计划，则可以关闭此选项（`optimizer_prune_level=0`），可能会导致查询编译花费更长的时间。请注意，即使使用这种启发式方法，优化器仍会探索大约指数级的计划。
- 该[`optimizer_search_depth`](server-administration.html#sysvar_optimizer_search_depth) 变量告诉优化器应该评估每个不完整计划的“ 未来 ”有多远，以评估是否应进一步扩展它。较小的值 [`optimizer_search_depth`](server-administration.html#sysvar_optimizer_search_depth)可能会导致查询编译时间缩短几个数量级。例如，如果[`optimizer_search_depth`](server-administration.html#sysvar_optimizer_search_depth)查询的表数接近12个，13个或更多，则很容易需要几个小时甚至几天来进行编译 。同时，如果用 [`optimizer_search_depth`](server-administration.html#sysvar_optimizer_search_depth) 等于3或4，对于同一查询，优化器可以在不到一分钟的时间内完成编译。如果不确定什么是合理的值 [`optimizer_search_depth`](server-administration.html#sysvar_optimizer_search_depth)，可以将此变量设置为0，以告知优化器自动确定该值。

### 8.9.2可切换的优化



使用[`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)系统变量可以控制优化程序的行为。它的值是一组标志，每个标志的值都为`on` 或`off`指示相应的优化器行为是启用还是禁用。此变量具有全局值和会话值，可以在运行时更改。可以在服务器启动时设置全局默认值。

要查看当前的优化器标志集，请选择变量值：

```
mysql> SELECT @@optimizer_switch\G
*************************** 1.行******************** *******
@@ optimizer_switch：index_merge = on，index_merge_union = on，
                    index_merge_sort_union = on，
                    index_merge_intersection = on，
                    engine_condition_pushdown = on，
                    index_condition_pushdown = on，
                    mrr = on，mrr_cost_based = on，
                    block_nested_loop = on，batched_key_access = off，
                    实现，打开，半连接，打开，松散，打开，
                    firstmatch = on，duplicateweedout = on，
                    subquery_materialization_cost_based = on，
                    use_index_extensions = on，
                    condition_fanout_filter = on，derived_merge = on
```

要更改的值 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)，请分配一个值，该值由一个或多个命令的逗号分隔列表组成：

```
SET [GLOBAL | SESSION] optimizer_switch =' command[，command] ...';
```

每个*`command`*值应具有下表中显示的格式之一。

| 命令语法               | 含义                       |
| :--------------------- | :------------------------- |
| `default`              | 将每个优化重置为其默认值   |
| `*`opt_name`*=default` | 将命名的优化设置为其默认值 |
| `*`opt_name`*=off`     | 禁用命名优化               |
| `*`opt_name`*=on`      | 启用命名的优化             |

值中命令的顺序无关紧要，尽管`default`如果存在则先执行命令。设置*`opt_name`*标志以 `default`将其 设置为默认值`on`或`off`任意值。*`opt_name`* 不允许在值中多次指定任何给定值，这会导致错误。值中的任何错误都会导致分配失败并显示错误，而值 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)保持不变。

下表描述了*`opt_name`*按优化策略分组的允许 标志名称：

- 批处理密钥访问标志

  - `batched_key_access`（默认 `off`）

    控制BKA连接算法的使用。

  为`batched_key_access`使设置为有效`on`，该 `mrr`标记还必须为 `on`。当前，MRR的成本估算过于悲观。因此，也有必要对 `mrr_cost_based`要 `off`用于要使用的BKA。

  有关更多信息，请参见 [第8.2.1.11节“阻止嵌套循环和批处理键访问联接”](optimization.html#bnl-bka-optimization)。

- 块嵌套循环标志

  - `block_nested_loop`（默认 `on`）

    控制BNL连接算法的使用。

  有关更多信息，请参见 [第8.2.1.11节“阻止嵌套循环和批处理键访问联接”](optimization.html#bnl-bka-optimization)。

- 条件过滤标志

  - `condition_fanout_filter`（默认 `on`）

    控制条件过滤的使用。

  有关更多信息，请参见 [第8.2.1.12节“条件过滤”](optimization.html#condition-filtering)。

- 派生表合并标志

  - `derived_merge`（默认 `on`）

    控制派生表和视图合并到外部查询块中。

  `derived_merge`假设没有其他规则阻止合并，则 该标志控制优化器是否尝试将派生表和视图引用合并到外部查询块中。例如，`ALGORITHM`视图的 指令优先于`derived_merge` 设置。默认情况下，该标志`on`用于启用合并。

  有关更多信息，请参见 [第8.2.2.4节“通过合并或实现来优化派生表和视图引用”](optimization.html#derived-table-optimization)。

- 发动机状态下推标志

  - `engine_condition_pushdown`（默认 `on`）

    控制发动机状态下推。

  有关更多信息，请参见 [第8.2.1.4节“引擎状态下推优化”](optimization.html#condition-pushdown-optimization)。

- 索引条件下推标志

  - `index_condition_pushdown`（默认 `on`）

    控制索引条件下推。

  有关更多信息，请参见 [第8.2.1.5节“索引条件下推优化”](optimization.html#index-condition-pushdown-optimization)。

- 索引扩展标志

  - `use_index_extensions`（默认 `on`）

    控制索引扩展的使用。

  有关更多信息，请参见 [第8.3.9节“索引扩展的使用”](optimization.html#index-extensions)。

- 索引合并标志

  - `index_merge`（默认 `on`）

    控制所有索引合并优化。

  - `index_merge_intersection`（默认 `on`）

    控制索引合并路口访问优化。

  - `index_merge_sort_union`（默认 `on`）

    控制索引合并排序联盟访问优化。

  - `index_merge_union`（默认 `on`）

    控制索引合并联合访问优化。

  有关更多信息，请参见 [第8.2.1.3节“索引合并优化”](optimization.html#index-merge-optimization)。

- 多范围读取标志

  - `mrr`（默认`on`）

    控制多范围读取策略。

  - `mrr_cost_based`（默认 `on`）

    如果，则控制基于成本的MRR的使用 `mrr=on`。

  有关更多信息，请参见 [第8.2.1.10节“多范围读取优化”](optimization.html#mrr-optimization)。

- 半连接标志

  - `semijoin`（默认 `on`）

    控制所有半联接策略。

  - `duplicateweedout`（默认 `on`）

    控制半联接重复除草策略。

  - `firstmatch`（默认 `on`）

    控制半联接的FirstMatch策略。

  - `loosescan`（默认 `on`）

    控制半联接的LooseScan策略（不要与Loose Index Scan for混淆`GROUP BY`）。

  在`semijoin`， `firstmatch`，`loosescan`，和`duplicateweedout`标志启用过的半连接策略控制。该`semijoin` 标志控制是否使用半联接。如果将其设置为 `on`，则`firstmatch`和 `loosescan`标志可对允许的半联接策略进行更好的控制。

  如果`duplicateweedout`禁用了半连接策略，则除非所有其他适用的策略也都被禁用，否则将不使用它。

  如果`semijoin`和 `materialization`均为 `on`，则半联接在适用的情况下也使用物化。这些标志是`on`默认设置。

  有关更多信息，请参见[第8.2.2.1节“使用半联接转换优化子查询，派生表和视图引用”](optimization.html#semijoins)。

- 子查询实现标志

  - `materialization`（默认 `on`）

    控制实现（包括半联接实现）。

  - `subquery_materialization_cost_based` （默认`on`）

    使用基于成本的物化选择。

  该`materialization`标志控制是否使用子查询实现。如果 `semijoin`和 `materialization`均为 `on`，则半联接在适用的情况下也使用物化。这些标志是`on`默认设置。

  该`subquery_materialization_cost_based` 标志使您可以控制子查询实现和`IN`-to- `EXISTS`subquery转换之间的选择 。如果该标志是`on`（缺省值），则优化器将在子查询实现和`IN`-to- `EXISTS`subquery转换之间执行基于成本的选择（ 如果可以使用这两种方法）。如果标志是`off`，优化器选择子查询物化了 `IN`-到- `EXISTS`子查询的转变。

  有关更多信息，请参见 [第8.2.2节“优化子查询，派生表和视图引用”](optimization.html#subquery-optimization)。

当您为分配值时 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)，未提及的标志将保留其当前值。这样就可以在单个语句中启用或禁用特定的优化器行为，而不会影响其他行为。该语句不取决于存在其他优化器标志以及它们的值是什么。假设所有索引合并优化均已启用：

```
mysql> SELECT @@optimizer_switch\G
*************************** 1.行******************** *******
@@ optimizer_switch：index_merge = on，index_merge_union = on，
                    index_merge_sort_union = on，
                    index_merge_intersection = on，
                    engine_condition_pushdown = on，
                    index_condition_pushdown = on，
                    mrr = on，mrr_cost_based = on，
                    block_nested_loop = on，batched_key_access = off，
                    实现，打开，半连接，打开，松散，打开，
                    firstmatch = on，
                    subquery_materialization_cost_based = on，
                    use_index_extensions = on，
                    condition_fanout_filter =开
```

如果服务器对某些查询使用索引合并联合或索引合并排序联合访问方法，并且您要检查优化器在没有它们的情况下是否会更好地执行，请按如下所示设置变量值：

```
mysql> SET optimizer_switch='index_merge_union=off,index_merge_sort_union=off';

mysql> SELECT @@optimizer_switch\G
*************************** 1.行******************** *******
@@ optimizer_switch：index_merge = on，index_merge_union = off，
                    index_merge_sort_union = off，
                    index_merge_intersection = on，
                    engine_condition_pushdown = on，
                    index_condition_pushdown = on，
                    mrr = on，mrr_cost_based = on，
                    block_nested_loop = on，batched_key_access = off，
                    实现，打开，半连接，打开，松散，打开，
                    firstmatch = on，
                    subquery_materialization_cost_based = on，
                    use_index_extensions = on，
                    condition_fanout_filter =开
```

### 8.9.3优化器提示



控制优化器策略的一种方法是设置 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)系统变量（请参见[第8.9.2节“可切换的优化”](optimization.html#switchable-optimizations)）。对该变量的更改会影响所有后续查询的执行；为了使一个查询与另一个查询有不同的影响，必须[`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)在每个查询之前进行更改 。

控制优化器的另一种方法是使用优化器提示，该提示可以在单个语句中指定。由于优化程序提示是基于每个语句应用的，因此它们提供了比使用更好的控制语句执行计划 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)。例如，您可以在语句中为一个表启用优化，而对另一表禁用优化。语句中的提示优先于 [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)标志。

例子：

```
SELECT / * + NO_RANGE_OPTIMIZATION（t3主，f2_idx）* / f1
  从t3开始，其中f1> 30并且f1 <33;
SELECT / * + BKA（t1）NO_BKA（t2）* / *从t1内连接t2在哪里...;
SELECT / * + NO_ICP（t1，t2）* / *从t1内连接t2在哪里...;
SELECT / * + SEMIJOIN（FIRSTMATCH，LOOSESCAN）* / *从t1 ...;
解释选择/ * + NO_ICP（t1）* / *从t1位置...;
```

注意

默认情况下 ，[**MySQL**](programs.html#mysql)客户端会剥离发送到服务器的SQL语句中的注释（包括优化程序提示），直到MySQL 5.7.7更改为将优化程序提示传递给服务器为止。如果使用的是较旧版本的[**mysql**](programs.html#mysql)客户端和理解优化器提示的服务器版本，请确保不剥离 优化器提示，并使用 选项调用 [**mysql**](programs.html#mysql)[`--comments`](programs.html#option_mysql_comments)。

此处描述的优化器提示与[第8.9.4节“索引提示”中](optimization.html#index-hints)描述的[索引提示不同](optimization.html#index-hints)。优化程序和索引提示可以单独使用，也可以一起使用。

- [优化程序提示概述](optimization.html#optimizer-hints-overview)
- [优化程序提示语法](optimization.html#optimizer-hints-syntax)
- [表级优化器提示](optimization.html#optimizer-hints-table-level)
- [索引级优化器提示](optimization.html#optimizer-hints-index-level)
- [子查询优化器提示](optimization.html#optimizer-hints-subquery)
- [语句执行时间优化器提示](optimization.html#optimizer-hints-execution-time)
- [用于命名查询块的优化器提示](optimization.html#optimizer-hints-query-block-naming)

#### 优化程序提示概述

优化器提示适用于不同的作用域级别：

- 全局：提示会影响整个语句
- 查询块：提示会影响语句中的特定查询块
- 表级别：提示会影响查询块中的特定表
- 索引级：提示会影响表中的特定索引

下表总结了可用的优化器提示，它们影响的优化器策略以及它们应用的范围。稍后给出更多细节。



**表8.2可用的优化程序提示**

| 提示名称                                                     | 描述                                             | 适用范围   |
| :----------------------------------------------------------- | :----------------------------------------------- | :--------- |
| [`BKA`](optimization.html#optimizer-hints-table-level)， [`NO_BKA`](optimization.html#optimizer-hints-table-level) | 影响批量密钥访问联接处理                         | 查询块，表 |
| [`BNL`](optimization.html#optimizer-hints-table-level)， [`NO_BNL`](optimization.html#optimizer-hints-table-level) | 影响块嵌套循环连接处理                           | 查询块，表 |
| [`MAX_EXECUTION_TIME`](optimization.html#optimizer-hints-execution-time) | 限制语句执行时间                                 | 全球       |
| [`MRR`](optimization.html#optimizer-hints-index-level)， [`NO_MRR`](optimization.html#optimizer-hints-index-level) | 影响多范围读取优化                               | 表，索引   |
| [`NO_ICP`](optimization.html#optimizer-hints-index-level)    | 影响索引条件下推式优化                           | 表，索引   |
| [`NO_RANGE_OPTIMIZATION`](optimization.html#optimizer-hints-index-level) | 影响范围优化                                     | 表，索引   |
| [`QB_NAME`](optimization.html#optimizer-hints-query-block-naming) | 为查询块分配名称                                 | 查询块     |
| [`SEMIJOIN`](optimization.html#optimizer-hints-subquery)， [`NO_SEMIJOIN`](optimization.html#optimizer-hints-subquery) | 影响半联接策略                                   | 查询块     |
| [`SUBQUERY`](optimization.html#optimizer-hints-subquery)     | 影响物化， `IN`至- `EXISTS` 子查询配置的对策探讨 | 查询块     |



禁用优化会阻止优化器使用它。启用优化意味着，如果优化器适用于语句执行，则它可以自由使用该策略，而不是优化器必然会使用它。

#### 优化程序提示语法

MySQL支持SQL语句中的注释，如 [第9.6节“注释语法”中所述](language-structure.html#comments)。必须在`/*+ ... */`注释中指定优化程序提示。也就是说，优化程序提示使用`/* ... */` C样式注释语法的变体，并`+`在`/*`注释打开序列之后添加一个字符。例子：

```
/ * + BKA（t1）* /
/ * + BNL（t1，t2）* /
/ * + NO_RANGE_OPTIMIZATION（t4主）* /
/ * + QB_NAME（qb2）* /
```

`+` 字符 后允许有空格。

解析器承认优化的初始关键字后暗示的意见[`SELECT`](sql-statements.html#select)， [`UPDATE`](sql-statements.html#update)， [`INSERT`](sql-statements.html#insert)， [`REPLACE`](sql-statements.html#replace)，和 [`DELETE`](sql-statements.html#delete)语句。在以下情况下允许提示：

- 在查询和数据更改语句的开头：

  ```
  选择/ * + ... * / ...
  插入/ * + ... * / ...
  替换/ * + ... * / ...
  更新/ * + ... * / ...
  删除/ * + ... * / ...
  ```

- 在查询块的开头：

  ```
  （选择/ * + ... * / ...）
  （选择...）联合（选择/ * + ... * / ...）
  （选择/ * + ... * / ...）UNION（选择/ * + ... * / ...）
  UPDATE ... WHERE x IN（SELECT / * + ... * / ...）
  插入...选择/ * + ... * / ...
  ```

- 在以开头的暗示性声明中 [`EXPLAIN`](sql-statements.html#explain)。例如：

  ```
  解释选择/ * + ... * / ...
  解释更新... WHERE x IN（SELECT / * + ... * / ...）
  ```

  这意味着您可以 [`EXPLAIN`](sql-statements.html#explain)用来查看优化器提示如何影响执行计划。[`SHOW WARNINGS`](sql-statements.html#show-warnings)之后立即使用 [`EXPLAIN`](sql-statements.html#explain)以查看提示的使用方式。`EXPLAIN` 以下[`SHOW WARNINGS`](sql-statements.html#show-warnings)显示的扩展输出指示使用了哪些提示。不显示忽略的提示。

提示注释可以包含多个提示，但是查询块不能包含多个提示注释。这是有效的：

```
选择/ * + BNL（t1）BKA（t2）* / ...
```

但这是无效的：

```
选择/ * + BNL（t1）* / / * BKA（t2）* / ...
```

当提示注释包含多个提示时，存在重复和冲突的可能性。以下一般准则适用。对于特定的提示类型，可能会应用其他规则，如提示说明中所述。

- 重复提示：对于诸如的提示`/*+ MRR(idx1) MRR(idx1) */`，MySQL使用第一个提示并发出有关重复提示的警告。
- 冲突的提示：对于诸如的提示`/*+ MRR(idx1) NO_MRR(idx1) */`，MySQL使用第一个提示，并发出有关第二个冲突的提示的警告。

查询块名称是标识符，并遵循关于哪些名称有效以及如何引用它们的常规规则（请参见 [第9.2节“模式对象名称”](language-structure.html#identifiers)）。

提示名称，查询块名称和策略名称不区分大小写。对表和索引名称的引用遵循通常的标识符区分大小写规则（请参见 [第9.2.3节“标识符区分大小写”](language-structure.html#identifier-case-sensitivity)）。

#### 表级优化器提示

表级提示影响块嵌套循环（BNL）和批处理键访问（BKA）[联接](optimization.html#bnl-bka-optimization)处理算法的使用（请参见 [第8.2.1.11节“块嵌套环和批处理键访问联接”](optimization.html#bnl-bka-optimization)）。这些提示类型适用于特定表或查询块中的所有表。

表级提示的语法：

```
hint_name（[@ query_block_name] [ tbl_name[，tbl_name] ...]）
 hint_name（[ tbl_name@ query_block_name[，tbl_name@ query_block_name] ...]）
```

语法涉及以下术语：

- *`hint_name`*：允许这些提示名称：

  - [`BKA`](optimization.html#optimizer-hints-table-level)， [`NO_BKA`](optimization.html#optimizer-hints-table-level)：为指定的表启用或禁用BKA。
  - [`BNL`](optimization.html#optimizer-hints-table-level)， [`NO_BNL`](optimization.html#optimizer-hints-table-level)：为指定的表启用或禁用BNL。

  注意

  若要使用BNL或BKA提示为外部联接的任何内部表启用联接缓冲，必须为外部联接的所有内部表启用联接缓冲。

- *`tbl_name`*：语句中使用的表的名称。提示适用于它命名的所有表。如果提示未命名表，则该提示将应用于出现该查询的查询块的所有表。

  如果表具有别名，则提示必须引用别名，而不是表名称。

  提示中的表名不能用架构名称限定。

- *`query_block_name`*：提示适用于的查询块。如果提示中不包含前导 ，则该提示适用于出现该查询的查询块。对于 语法，该提示适用于命名查询块中的命名表。要将名称分配给查询块，请参阅 [命名查询块的优化器提示](optimization.html#optimizer-hints-query-block-naming)。 `@*`query_block_name`*``*`tbl_name`*@*`query_block_name`*`

例子：

```
选择/ * + NO_BKA（t1，t2）* / t1。* FROM t1内部联接t2内部联接t3;
SELECT / * + NO_BNL（）BKA（t1）* / t1。* FROM t1内部联接t2内部联接t3;
```

表级提示适用于从先前的表而非发送方表接收记录的表。考虑以下语句：

```
选择/ * + BNL（t2）* /从t1，t2;
```

如果优化器选择首先处理`t1` ，它将在开始读取之前 `t2`对行进行缓冲，从而将“块嵌套循环”联接应用于 该行 。如果优化程序选择先处理，则该提示无效，因为它是发送方表。 `t1``t2``t2``t2`

#### 索引级优化器提示

索引级提示会影响优化器针对特定表或索引使用的索引处理策略。这些提示类型会影响索引条件下推（ICP），多范围读取（MRR）和范围优化的使用（请参见 [第8.2.1节“优化SELECT语句”](optimization.html#select-optimization)）。

索引级提示的语法：

```
hint_name（[@ query_block_name] tbl_name[ index_name[，index_name] ...]）
 hint_name（tbl_name@ query_block_name[ index_name[，index_name] ...]）
```

语法涉及以下术语：

- *`hint_name`*：允许这些提示名称：

  - [`MRR`](optimization.html#optimizer-hints-index-level)， [`NO_MRR`](optimization.html#optimizer-hints-index-level)：启用或禁用指定表或索引的MRR。MRR提示仅适用于`InnoDB`和 `MyISAM`表。

  - [`NO_ICP`](optimization.html#optimizer-hints-index-level)：对指定的表或索引禁用ICP。默认情况下，ICP是一种候选优化策略，因此没有启用它的提示。

  - [`NO_RANGE_OPTIMIZATION`](optimization.html#optimizer-hints-index-level)：禁用指定表或索引的索引范围访问。此提示还禁用了表或索引的索引合并和松散索引扫描。默认情况下，范围访问是一种候选优化策略，因此没有启用它的提示。

    当范围数可能很高并且范围优化将需要许多资源时，此提示可能会很有用。

- *`tbl_name`*：提示适用的表格。

- *`index_name`*：命名表中索引的名称。提示适用于它命名的所有索引。如果提示未命名索引，则它将应用于表中的所有索引。

  要引用主键，请使用名称 `PRIMARY`。要查看表的索引名称，请使用[`SHOW INDEX`](sql-statements.html#show-index)。

- *`query_block_name`*：提示适用于的查询块。如果提示中不包含前导 ，则该提示适用于出现该查询的查询块。对于 语法，该提示适用于命名查询块中的命名表。要将名称分配给查询块，请参阅 [命名查询块的优化器提示](optimization.html#optimizer-hints-query-block-naming)。 `@*`query_block_name`*``*`tbl_name`*@*`query_block_name`*`

例子：

```
选择/ * + MRR（t1）* / *从t1那里f2 <= 3 AND 3 <= f3;
SELECT / * + NO_RANGE_OPTIMIZATION（t3主，f2_idx）* / f1
  从t3开始，其中f1> 30并且f1 <33;
插入t3（f1，f2，f3）
  （SELECT / * + NO_ICP（t2）* / t2.f1，t2.f2，t2.f3来自t1，t2
   t1.f1之间的t1.f1 = t2.f1和t2.f2
   AND t1.f2 AND t2.f2 + 1> = t1.f1 +1）;
```

#### 子查询优化器提示

子查询提示会影响是否使用半联接转换以及允许使用的半联接策略，以及在不使用半联接时，是否使用子查询实现或 `IN`-to- `EXISTS` 转换。有关这些优化的更多信息，请参见[第8.2.2节“优化子查询，派生表和视图引用”](optimization.html#subquery-optimization)。

影响半联接策略的提示语法：

```
hint_name（[@ query_block_name] [ strategy[，strategy] ...]）
```

语法涉及以下术语：

- *`hint_name`*：允许这些提示名称：

  - [`SEMIJOIN`](optimization.html#optimizer-hints-subquery)， [`NO_SEMIJOIN`](optimization.html#optimizer-hints-subquery)：启用或禁用命名的半联接策略。

- *`strategy`*：要启用或禁用的半连接策略。这些策略名允许：`DUPSWEEDOUT`， `FIRSTMATCH`， `LOOSESCAN`， `MATERIALIZATION`。

  对于[`SEMIJOIN`](optimization.html#optimizer-hints-subquery)提示，如果未命名策略，则根据[`optimizer_switch`](server-administration.html#sysvar_optimizer_switch)系统变量启用的策略（如果可能）使用半联接 。如果策略已命名但不适用于该语句，`DUPSWEEDOUT`则使用。

  对于[`NO_SEMIJOIN`](optimization.html#optimizer-hints-subquery)提示，如果未命名策略，则不使用半联接。如果命名策略，则排除该语句的所有适用策略 `DUPSWEEDOUT`。

如果一个子查询嵌套在另一个子查询中，并且两个子查询都合并到外部查询的半联接中，则将忽略最内部查询的任何半联接策略规范。 [`SEMIJOIN`](optimization.html#optimizer-hints-subquery)并且 [`NO_SEMIJOIN`](optimization.html#optimizer-hints-subquery)提示仍然可以用于启用或禁用此类嵌套子查询的半联接转换。

如果`DUPSWEEDOUT`已禁用，则有时优化器可能会生成一个远非最佳的查询计划。发生这种情况的原因是贪婪搜索期间的启发式修剪，可以通过设置来避免 [`optimizer_prune_level=0`](server-administration.html#sysvar_optimizer_prune_level)。

例子：

```
SELECT / * + NO_SEMIJOIN（@ subq1 FIRSTMATCH，LOOSESCAN）* / *从t2
  t2.a IN（SELECT / * + QB_NAME（subq1）* / a FROM t3）;
SELECT / * + SEMIJOIN（@ subq1 MATERIALIZATION，DUPSWEEDOUT）* / *从t2
  t2.a IN（SELECT / * + QB_NAME（subq1）* / a FROM t3）;
```

影响是否使用子查询实现或`IN`-to- `EXISTS` 转换的提示的语法 ：

```
子查询（[@ query_block_name] strategy）
```

提示名称始终为 [`SUBQUERY`](optimization.html#optimizer-hints-subquery)。

对于[`SUBQUERY`](optimization.html#optimizer-hints-subquery)提示，可以使用以下 *`strategy`*值： `INTOEXISTS`， `MATERIALIZATION`。

例子：

```
SELECT id，一个IN（SELECT / * + SUBQUERY（MATERIALIZATION）* /一个FROM t1）FROM t2;
SELECT * FROM t2在t2.a IN（SELECT / * + SUBQUERY（INTOEXISTS）* / a FROM t1）中;
```

对于半联接和[`SUBQUERY`](optimization.html#optimizer-hints-subquery) 提示，前导 指定提示适用于的查询块。如果提示中不包含前导 ，则该提示适用于出现该查询的查询块。要将名称分配给查询块，请参阅 [命名查询块的优化器提示](optimization.html#optimizer-hints-query-block-naming)。 `@*`query_block_name`*``@*`query_block_name`*`

如果提示注释包含多个子查询提示，则使用第一个。如果还有其他以下类型的提示，则会产生警告。其他类型的以下提示将被忽略。

#### 语句执行时间优化器提示

该[`MAX_EXECUTION_TIME`](optimization.html#optimizer-hints-execution-time)提示只允许用于[`SELECT`](sql-statements.html#select) 语句。它*`N`*为服务器终止该语句之前允许执行一条语句设置了一个限制（超时值（以毫秒为单位））：

```
MAX_EXECUTION_TIME（N）
```

超时为1秒（1000毫秒）的示例：

```
SELECT / * + MAX_EXECUTION_TIME（1000）* / *从t1内连接t2处...
```

该 提示设置的语句执行超时 毫秒。如果此选项不存在或为0，则应用系统变量建立的语句超时 。 [`MAX_EXECUTION_TIME(*`N`*)`](optimization.html#optimizer-hints-execution-time)*`N`**`N`*[`max_execution_time`](server-administration.html#sysvar_max_execution_time)

该[`MAX_EXECUTION_TIME`](optimization.html#optimizer-hints-execution-time)提示适用于以下情况：

- 对于具有多个`SELECT` 关键字的语句，例如并集或带有子查询的语句， [`MAX_EXECUTION_TIME`](optimization.html#optimizer-hints-execution-time) 将应用于整个语句，并且必须出现在第一个之后[`SELECT`](sql-statements.html#select)。
- 它适用于只读 [`SELECT`](sql-statements.html#select)语句。非只读的语句是那些调用存储函数修改数据的副作用的语句。
- 它不适用于[`SELECT`](sql-statements.html#select) 存储程序中的语句，将被忽略。

#### 用于命名查询块的优化器提示

表级，索引级和子查询优化器提示允许将特定查询块命名为其参数语法的一部分。要创建这些名称，请使用 [`QB_NAME`](optimization.html#optimizer-hints-query-block-naming)提示，该提示将名称分配给出现该名称的查询块：

```
QB_NAME（name）
```

[`QB_NAME`](optimization.html#optimizer-hints-query-block-naming)提示可用于以明确的方式使之明确，哪些查询将阻止其他提示应用于该查询。它们还允许在单个提示注释中指定所有非查询块名称提示，以便于理解复杂的语句。考虑以下语句：

```
选择 ...
  从（选择...
  FROM（选择... FROM ...））...
```

[`QB_NAME`](optimization.html#optimizer-hints-query-block-naming) 提示为语句中的查询块分配名称：

```
SELECT / * + QB_NAME（qb1）* / ...
  FROM（选择/ * + QB_NAME（qb2）* / ...
  FROM（选择/ * + QB_NAME（qb3）* / ... FROM ...））...
```

然后其他提示可以使用这些名称来引用适当的查询块：

```
SELECT / * + QB_NAME（qb1）MRR（@ qb1 t1）BKA（@ qb2）NO_MRR（@ qb3t1 idx1，id2）* / ...
  FROM（选择/ * + QB_NAME（qb2）* / ...
  FROM（选择/ * + QB_NAME（qb3）* / ... FROM ...））...
```

产生的效果如下：

- [`MRR(@qb1 t1)`](optimization.html#optimizer-hints-index-level)适用`t1`于查询块中的 表`qb1`。
- [`BKA(@qb2)`](optimization.html#optimizer-hints-table-level)适用于查询块`qb2`。
- [`NO_MRR(@qb3 t1 idx1, id2)`](optimization.html#optimizer-hints-index-level)适用于索引`idx1` 和 查询块`idx2`中的表。 `t1``qb3`

查询块名称是标识符，并遵循关于哪些名称有效以及如何引用它们的常规规则（请参见 [第9.2节“模式对象名称”](language-structure.html#identifiers)）。例如，必须引用包含空格的查询块名称，这可以使用反引号来完成：

```
SELECT / * + BKA（@`我的提示名称`）* / ...
  FROM（SELECT / * + QB_NAME（`我的提示名称`）* / ...）...
```

如果[`ANSI_QUOTES`](server-administration.html#sqlmode_ansi_quotes)启用了SQL模式，则还可以在双引号中用引号引起来的查询块名称：

```
SELECT / * + BKA（@“我的提示名称”）* / ...
  FROM（SELECT / * + QB_NAME（“我的提示名称”）* / ...）...
```

### 8.9.4索引提示



索引提示为优化器提供有关在查询处理期间如何选择索引的信息。此处描述的索引提示与[第8.9.3节“优化器提示”中](optimization.html#optimizer-hints)描述的 [优化器提示不同](optimization.html#optimizer-hints)。索引和优化器提示可以单独使用，也可以一起使用。

索引提示仅适用于[`SELECT`](sql-statements.html#select) 和[`UPDATE`](sql-statements.html#update)语句。

在表名后指定索引提示。（有关在语句中指定表的一般语法 [`SELECT`](sql-statements.html#select)，请参见 [第13.2.9.2节“ JOIN子句”](sql-statements.html#join)。）引用单个表（包括索引提示）的语法如下所示：

```
tbl_name[[AS] alias] [ index_hint_list]

index_hint_list：
     index_hint[ index_hint] ...

index_hint：
    使用{INDEX | KEY}
      [FOR {JOIN | ORDER BY | GROUP BY}]（[index_list ]）
  | {IGNORE | FORCE} {INDEX | KEY}
      [FOR {JOIN | ORDER BY | GROUP BY}]（index_list）

index_list：
     index_name[，index_name] ...
```

该提示告诉MySQL仅使用命名索引之一来查找表中的行。替代语法告诉MySQL不要使用某些特定的索引。如果显示MySQL使用的索引可能不正确，则这些提示很有用。 `USE INDEX (*`index_list`*)``IGNORE INDEX (*`index_list`*)`[`EXPLAIN`](sql-statements.html#explain)

该`FORCE INDEX`提示的作用就像，增加表扫描被认为是 *非常*昂贵的。换句话说，仅当无法使用命名索引之一在表中查找行时才使用表扫描。 `USE INDEX (*`index_list`*)`

每个提示都需要索引名称，而不是列名称。要引用主键，请使用名称`PRIMARY`。要查看表的索引名称，请使用[`SHOW INDEX`](sql-statements.html#show-index)语句或 [`INFORMATION_SCHEMA.STATISTICS`](information-schema.html#statistics-table) 表。

的*`index_name`*价值不一定是完整的索引名。它可以是索引名称的明确前缀。如果前缀不明确，则会发生错误。

例子：

```
SELECT * FROM table1 USE INDEX（col1_index，col2_index）
  其中col1 = 1 AND col2 = 2 AND col3 = 3;

SELECT * FROM table1 IGNORE INDEX（col3_index）
  其中col1 = 1 AND col2 = 2 AND col3 = 3;
```

索引提示的语法具有以下特征：

- 省略语法上是有效 *`index_list`*的`USE INDEX`，这意味着“不使用索引。” 省略*`index_list`*的 `FORCE INDEX`或者`IGNORE INDEX`是一个语法错误。

- 您可以通过`FOR`在提示中添加一个子句来指定索引提示的范围 。这为查询处理的各个阶段提供了对执行计划的优化器选择的更细粒度控制。要仅影响MySQL决定如何在表中查找行以及如何处理联接时使用的索引，请使用`FOR JOIN`。要影响索引对行进行排序或分组的用法，请使用`FOR ORDER BY`或 `FOR GROUP BY`。

- 您可以指定多个索引提示：

  ```
  SELECT * FROM t1 USE INDEX（i1）INORORE INDEX FOR ORDER BY（i2）ORDER BY a;
  ```

  在多个提示中命名相同的索引（即使在同一提示中）也不是错误：

  ```
  SELECT * FROM t1使用索引（i1）使用索引（i1，i1）;
  ```

  但是，它是混合错误`USE INDEX` 和`FORCE INDEX`同一个表：

  ```
  SELECT * FROM t1使用INDEX IN JOIN（i1）强制INDEX FOR JOIN（i2）;
  ```

如果索引提示不包含任何`FOR`子句，则提示的范围将应用于语句的所有部分。例如，以下提示：

```
IGNORE索引（i1）
```

等效于以下提示组合：

```
联接的索引（i1）
IGNORE INDEX FOR ORDER BY（i1）
分组依据（i1）的IGNORE索引
```

在MySQL 5.0中，没有`FOR`子句的提示作用域仅适用于行检索。若要在不存在`FOR`子句时使服务器使用此较旧的行为，请[`old`](server-administration.html#sysvar_old)在服务器启动时启用系统变量。请注意在复制设置中启用此变量。使用基于语句的二进制日志记录，对主服务器和从服务器使用不同的模式可能会导致复制错误。

当索引提示进行处理，它们被收集在由类型的单个列表（`USE`，`FORCE`， `IGNORE`）和范围（`FOR JOIN`，`FOR ORDER BY`，`FOR GROUP BY`）。例如：

```
选择*从t1
  USE INDEX（）IGNORE INDEX（i2）USE INDEX（i1）USE INDEX（i2）;
```

等效于：

```
选择*从t1
   USE INDEX（i1，i2）IGNORE INDEX（i2）;
```

然后按以下顺序将索引提示应用于每个范围：

1. `{USE|FORCE} INDEX`如果存在，则应用。（如果不是，则使用优化程序确定的索引集。）

2. `IGNORE INDEX`将应用于上一步的结果。例如，以下两个查询是等效的：

   ```
   选择*从t1使用索引（i1）忽略索引（i2）使用索引（i2）;
   
   选择*从t1使用索引（i1）;
   ```

对于`FULLTEXT`搜索，索引提示的工作方式如下：

- 对于自然语言模式搜索，将无提示地忽略索引提示。例如，`IGNORE INDEX(i1)`在没有警告的情况下被忽略，并且索引仍在使用。

- 对于布尔模式搜索，带有`FOR ORDER BY`或的索引提示将`FOR GROUP BY`被静默忽略。索引提示带有`FOR JOIN`或不带有`FOR`修饰符。与提示如何应用于非`FULLTEXT`搜索相反，该提示用于查询执行的所有阶段（查找行和检索，分组和排序）。即使为非`FULLTEXT`索引给出了提示，也是如此。

  例如，以下两个查询是等效的：

  ```
  选择*从t
    使用索引（index1）
    IGNORE INDEX（index1）排序依据
    分组依据的IGNORE索引（index1）
    在...布尔模式下...;
  
  选择*从t
    使用索引（index1）
    在...布尔模式下...;
  ```

### 8.9.5优化器成本模型



为了生成执行计划，优化器使用成本模型，该模型基于对查询执行期间发生的各种操作的成本进行估算。优化器具有一组内置的默认“ 成本常数 ”，可用于制定有关执行计划的决策。

优化器还有一个成本估算数据库，可在执行计划构建期间使用。这些估算值存储在系统数据库的`server_cost`和 `engine_cost`表中， `mysql`并且可以随时配置。这些表的目的是使得可以轻松调整优化器在尝试得出查询执行计划时使用的成本估算。

- [成本模型一般运作](optimization.html#cost-model-operation)
- [成本模型数据库](optimization.html#cost-model-database)
- [更改成本模型数据库](optimization.html#cost-model-database-modifications)

#### 成本模型一般运作

可配置的优化器成本模型的工作方式如下：

- 服务器在启动时将成本模型表读取到内存中，并在运行时使用内存中的值。`NULL`表中指定的任何非成本估算值都优先于相应的已编译默认成本常量。任何`NULL` 估计值都指示优化器使用编译后的默认值。
- 在运行时，服务器可以重新读取成本表。当动态加载存储引擎或[`FLUSH OPTIMIZER_COSTS`](sql-statements.html#flush-optimizer-costs) 执行语句时，会发生这种情况。
- 成本表使服务器管理员可以通过更改表中的条目轻松调整成本估算。通过将条目的费用设置为，也很容易恢复为默认值`NULL`。优化器使用内存中的成本值，因此对表所做的更改应 [`FLUSH OPTIMIZER_COSTS`](sql-statements.html#flush-optimizer-costs)生效。
- 客户会话开始时当前的内存中成本估算将在整个会话中应用，直到结束。特别是，如果服务器重新读取成本表，则任何更改的估算值仅适用于随后启动的会话。现有会话不受影响。
- 成本表特定于给定的服务器实例。服务器不会将成本表更改复制到复制从属服务器。

#### 成本模型数据库

优化器成本模型数据库由`mysql`系统数据库中的两个表组成，其中包含查询执行期间发生的操作的成本估算信息：

- `server_cost`：针对一般服务器操作的优化器成本估算
- `engine_cost`：针对特定存储引擎的运营的优化器成本估算

该`server_cost`表包含以下列：

- `cost_name`

  成本模型中使用的成本估算的名称。名称不区分大小写。如果服务器在读取此表时无法识别成本名称，则会向错误日志中写入警告。

- `cost_value`

  成本估算值。如果该值为non- `NULL`，则服务器将其用作成本。否则，它将使用默认估计值（编译值）。DBA可以通过更新此列来更改成本估算。如果服务器在读取此表时发现成本值无效（非正值），则会向错误日志中写入警告。

  要覆盖默认费用估算值（对于指定的条目`NULL`），请将费用设置为非`NULL`值。要恢复为默认值，请将值设置为`NULL`。然后执行[`FLUSH OPTIMIZER_COSTS`](sql-statements.html#flush-optimizer-costs)以告知服务器重新读取成本表。

- `last_update`

  最后一行的更新时间。

- `comment`

  与成本估算相关的描述性注释。DBA可以使用此列来提供有关为什么成本估算行存储特定值的信息。

该`server_cost`表的主键是该`cost_name`列，因此无法为任何成本估算创建多个条目。

服务器识别表的这些`cost_name` 值`server_cost`：

- `disk_temptable_create_cost`（预设40.0），`disk_temptable_row_cost`（预设1.0）

  在基于磁盘的存储引擎（`InnoDB`或`MyISAM`）中存储的内部创建的临时表的成本估算 。增加这些值会增加使用内部临时表的成本估算，并使优化器更喜欢较少使用它们的查询计划。有关此类表的信息，请参见 [第8.4.4节“ MySQL中的内部临时表使用”](optimization.html#internal-temporary-tables)。

  对于相比于相应的存储器参数的缺省值，这些磁盘参数较大的默认值（`memory_temptable_create_cost`， `memory_temptable_row_cost`）反映处理的基于磁盘的表的更高的成本。

- `key_compare_cost` （默认为0.1）

  

  

  比较记录键的成本。增加此值将导致查询计划比较多个键变得更加昂贵。例如，`filesort`与避免使用索引进行排序的查询计划相比，执行的查询计划 变得相对昂贵。

- `memory_temptable_create_cost`（默认2.0），`memory_temptable_row_cost` （默认0.2）

  `MEMORY`存储引擎中 内部创建的临时表的成本估算。增加这些值会增加使用内部临时表的成本估算，并使优化器更喜欢较少使用它们的查询计划。有关此类表的信息，请参见 [第8.4.4节“ MySQL中的内部临时表使用”](optimization.html#internal-temporary-tables)。

  与相应磁盘参数的默认值（）相比，这些内存参数的默认值较小`disk_temptable_create_cost`， `disk_temptable_row_cost`反映了处理基于内存的表的成本较低。

- `row_evaluate_cost` （默认0.2）

  评估记录条件的成本。与检查行数较少的查询计划相比，增加该值会导致检查许多行的查询计划变得更加昂贵。例如，与读取较少行的范围扫描相比，表扫描变得相对昂贵。

该`engine_cost`表包含以下列：

- `engine_name`

  此成本估算适用的存储引擎的名称。名称不区分大小写。如果值为 `default`，则适用于所有没有自己命名条目的存储引擎。如果服务器在读取该表时无法识别引擎名称，则会向错误日志中写入警告。

- `device_type`

  此费用估算适用的设备类型。该列旨在为不同的存储设备类型（例如，硬盘驱动器与固态驱动器）指定不同的成本估算。当前，此信息未使用，并且0是唯一允许的值。

- `cost_name`

  与`server_cost`表相同。

- `cost_value`

  与`server_cost`表相同。

- `last_update`

  与`server_cost`表相同。

- `comment`

  与`server_cost`表相同。

对于主键`engine_cost`表是包含（一个元组`cost_name`， `engine_name`， `device_type`）个列，所以它不可能在这些列中的值的任意组合来创建多个条目。

服务器识别表的这些`cost_name` 值`engine_cost`：

- `io_block_read_cost` （默认为1.0）

  从磁盘读取索引或数据块的成本。与读取较少磁盘块的查询计划相比，增加该值会使读取许多磁盘块的查询计划变得更加昂贵。例如，与读取较少块的范围扫描相比，表扫描变得相对昂贵。

- `memory_block_read_cost` （默认为1.0）

  与相似`io_block_read_cost`，但是代表从内存数据库缓冲区读取索引或数据块的成本。

如果`io_block_read_cost`和 `memory_block_read_cost`值不同，则执行计划可能会在同一查询的两次运行之间改变。假设内存访问的成本小于磁盘访问的成本。在这种情况下，在服务器启动之前，将数据读入缓冲池之前，您可能会获得与运行查询之后不同的计划，因为这样数据就会存储在内存中。

#### 更改成本模型数据库

对于希望从其默认值更改成本模型参数的DBA，请尝试将该值加倍或减半并测量效果。

`io_block_read_cost`和 `memory_block_read_cost`参数的 更改最有可能产生有价值的结果。这些参数值使数据访问方法的成本模型可以考虑从不同来源读取信息的成本。也就是说，从磁盘读取信息的成本与读取内存缓冲区中已经存在的信息的成本。例如，在所有其他条件都相同的情况下，将其设置 `io_block_read_cost`为大于 `memory_block_read_cost`会导致优化器更喜欢查询计划，该计划读取已保存在内存中的信息，而不是必须从磁盘读取的计划。

本示例说明如何更改以下项的默认值 `io_block_read_cost`：

```
更新mysql.engine_cost
  SET cost_value = 2.0
  其中cost_name ='io_block_read_cost';
FLUSH OPTIMIZER_COSTS;
```

此示例说明如何更改`io_block_read_cost`仅 `InnoDB`存储引擎的值 ：

```
插入mysql.engine_cost
  VALUES（“ InnoDB”，0，“ io_block_read_cost”，3.0，
  CURRENT_TIMESTAMP，“为InnoDB使用较慢的磁盘”）；
FLUSH OPTIMIZER_COSTS;
```

## 8.10缓冲和缓存

- [8.10.1 InnoDB缓冲池优化](optimization.html#innodb-buffer-pool-optimization)
- [8.10.2 MyISAM密钥缓存](optimization.html#myisam-key-cache)
- [8.10.3 MySQL查询缓存](optimization.html#query-cache)
- [8.10.4缓存准备好的语句和存储的程序](optimization.html#statement-caching)

MySQL使用多种策略将信息缓存在内存缓冲区中以提高性能。

### 8.10.1 InnoDB缓冲池优化



[`InnoDB`](innodb-storage-engine.html)维护一个称为[缓冲池](glossary.html#glos_buffer_pool)的存储区， 用于在内存中缓存数据和索引。了解 `InnoDB`缓冲池的工作方式，并利用其将经常访问的数据保留在内存中，是MySQL调优的重要方面。

有关`InnoDB`缓冲池内部工作的说明， 其LRU替换算法的概述以及常规配置信息，请参见[第14.5.1节“缓冲池”](innodb-storage-engine.html#innodb-buffer-pool)。

有关其他`InnoDB`缓冲池配置和调整信息，请参阅以下部分：

- [第14.8.3.4节“配置InnoDB缓冲池预取（预读）”](innodb-storage-engine.html#innodb-performance-read_ahead)
- [第14.8.3.5节“配置缓冲池刷新”](innodb-storage-engine.html#innodb-buffer-pool-flushing)
- [第14.8.3.3节“使缓冲池扫描具有抵抗力”](innodb-storage-engine.html#innodb-performance-midpoint_insertion)
- [第14.8.3.2节“配置多个缓冲池实例”](innodb-storage-engine.html#innodb-multiple-buffer-pools)
- [第14.8.3.6节“保存和恢复缓冲池状态”](innodb-storage-engine.html#innodb-preload-buffer-pool)
- [第14.8.3.1节“配置InnoDB缓冲池大小”](innodb-storage-engine.html#innodb-buffer-pool-resize)

### 8.10.2 MyISAM密钥缓存

- [8.10.2.1共享密钥缓存访问](optimization.html#shared-key-cache)
- [8.10.2.2多个键缓存](optimization.html#multiple-key-caches)
- [8.10.2.3中点插入策略](optimization.html#midpoint-insertion)
- [8.10.2.4索引预加载](optimization.html#index-preloading)
- [8.10.2.5密钥缓存块大小](optimization.html#key-cache-block-size)
- [8.10.2.6重构密钥缓存](optimization.html#key-cache-restructuring)



为了最小化磁盘I / O，`MyISAM`存储引擎采用了许多数据库管理系统所使用的策略。它使用缓存机制将最常访问的表块保留在内存中：

- 对于索引块，维护了一种称为键缓存（或 键缓冲区）的特殊结构 。该结构包含许多块缓冲区，在这些缓冲区中放置了最常用的索引块。
- 对于数据块，MySQL不使用特殊的缓存。相反，它依赖于本机操作系统文件系统缓存。

本节首先介绍`MyISAM`密钥缓存的基本操作 。然后讨论了可以提高关键高速缓存性能并使您更好地控制高速缓存操作的功能：

- 多个会话可以同时访问缓存。
- 您可以设置多个键高速缓存，并将表索引分配给特定的高速缓存。

要控制键高速缓存的大小，请使用 [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)系统变量。如果将此变量设置为零，则不使用键缓存。如果该[`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)值太小而无法分配最小数量的块缓冲区（8），则也不使用键高速缓存 。

当键高速缓存不可用时，仅使用操作系统提供的本机文件系统缓冲来访问索引文件。（换句话说，使用与表数据块相同的策略访问表索引块。）

索引块是对`MyISAM`索引文件的连续访问单元 。通常，索引块的大小等于索引B树的节点的大小。（索引在磁盘上使用B树数据结构表示。树底部的节点是叶节点。叶节点上方的节点是非叶节点。）

密钥高速缓存结构中的所有块缓冲区的大小均相同。该大小可以等于，大于或小于表索引块的大小。通常，这两个值之一是另一个的倍数。

当必须访问任何表索引块中的数据时，服务器首先检查键高速缓存的某些块缓冲区中是否可用。如果是这样，服务器将访问密钥缓存中的数据，而不是磁盘上的数据。也就是说，它从缓存中读取或写入缓存，而不是从磁盘读取或写入磁盘。否则，服务器将选择一个包含另一个表索引块的缓存块缓冲区，并用所需表索引块的副本替换那里的数据。一旦新的索引块位于缓存中，就可以访问索引数据。

如果碰巧选中了要替换的块，则该块被视为“ 脏”。”在这种情况下，之前被取代时，其内容被刷新到它所来自的表索引。

通常，服务器遵循LRU（最近最少使用）策略：选择要替换的块时，它会选择最近最少使用的索引块。为了使选择更加容易，密钥缓存模块将所有使用的块维护在按使用时间排序的特殊列表（LRU链）中。当访问一个块时，它是最近使用的块，并位于列表的末尾。当需要替换块时，列表开头的块是最近最少使用的块，成为驱逐的第一个候选者。

该`InnoDB`存储引擎还采用LRU算法来管理它的缓冲池。请参见 [第14.5.1节“缓冲池”](innodb-storage-engine.html#innodb-buffer-pool)。

#### 8.10.2.1共享密钥缓存访问

在满足以下条件的前提下，线程可以同时访问键高速缓存缓冲区：

- 多个会话可以访问未更新的缓冲区。
- 正在更新的缓冲区会导致需要使用它的会话等待更新完成。
- 多个会话可以发起导致缓存块替换的请求，只要它们彼此不干扰（也就是说，只要它们需要不同的索引块，从而导致替换不同的缓存块）即可。

对密钥缓存的共享访问使服务器可以显着提高吞吐量。

#### 8.10.2.2多个键缓存

对密钥缓存的共享访问可以提高性能，但不能完全消除会话之间的争用。他们仍在争夺管理对键高速缓存缓冲区的访问的控制结构。为了进一步减少密钥缓存访问争用，MySQL还提供了多个密钥缓存。此功能使您可以将不同的表索引分配给不同的键高速缓存。

如果有多个键缓存，则服务器必须知道在处理给定`MyISAM`表的查询时要使用哪个缓存 。默认情况下，所有 `MyISAM`表索引都缓存在默认键缓存中。要将表索引分配给特定的键高速缓存，请使用该[`CACHE INDEX`](sql-statements.html#cache-index) 语句（请参见[第13.7.6.2节“ CACHE INDEX语句”](sql-statements.html#cache-index)）。例如，从表中下面的语句受让人指标 `t1`，`t2`以及 `t3`向键缓存命名为 `hot_cache`：

```
mysql> CACHE INDEX t1, t2, t3 IN hot_cache;
+ --------- + -------------------- + ---------- + ------- ---
| 桌子 运算| Msg_type | Msg_text |
+ --------- + -------------------- + ---------- + ------- ---
| test.t1 | Assign_to_keycache | 状态| 好的
| test.t2 | Assign_to_keycache | 状态| 好的
| test.t3 | Assign_to_keycache | 状态| 好的
+ --------- + -------------------- + ---------- + ------- ---
```

[`CACHE INDEX`](sql-statements.html#cache-index)可以[`SET GLOBAL`](sql-statements.html#set-variable)通过使用参数设置语句设置其大小或使用服务器启动选项来创建语句中 引用的键高速缓存。例如：

```
mysql> SET GLOBAL keycache1.key_buffer_size=128*1024;
```

要销毁密钥缓存，请将其大小设置为零：

```
mysql> SET GLOBAL keycache1.key_buffer_size=0;
```

您无法销毁默认密钥缓存。这样做的任何尝试都会被忽略：

```
mysql> SET GLOBAL key_buffer_size = 0;

mysql> SHOW VARIABLES LIKE 'key_buffer_size';
+ ----------------- + --------- +
| 变量名| 价值|
+ ----------------- + --------- +
| key_buffer_size | 8384512 |
+ ----------------- + --------- +
```

关键高速缓存变量是具有名称和组件的结构化系统变量。对于 `keycache1.key_buffer_size`， `keycache1`是缓存变量名称，并且 [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)是缓存组件。有关用于引用结构化键高速缓存系统变量的语法的描述，请参见[第5.1.8.3节“结构化系统变量”](server-administration.html#structured-system-variables)。

默认情况下，表索引分配给服务器启动时创建的主（默认）键高速缓存。销毁键高速缓存时，分配给它的所有索引都将重新分配给默认键高速缓存。

对于繁忙的服务器，可以使用涉及三个关键缓存的策略：

- 一个“ 热 ”键高速占用分配给所有键高速缓冲空间的20％。将其用于大量用于搜索但未更新的表。
- 一个“ 冷 ”键高速占用分配给所有键高速缓冲空间的20％。将此缓存用于中等大小的，经过大量修改的表，例如临时表。
- 一个“ 温暖 ”键高速占用键高速缓冲空间的60％。将此用作默认键缓存，默认情况下将其用于所有其他表。

使用三个密钥缓存的好处之一是，对一个密钥缓存结构的访问不会阻止对其他密钥缓存结构的访问。访问分配给一个缓存的表的语句不会与访问分配给另一缓存的表的语句竞争。由于其他原因也会导致性能提升：

- 热缓存仅用于检索查询，因此永远不会修改其内容。因此，每当需要将索引块从磁盘中拉出时，就无需先清除用于替换的高速缓存块的内容。
- 对于分配给热缓存的索引，如果没有查询需要进行索引扫描，则很有可能与索引B树的非叶子节点相对应的索引块保留在缓存中。
- 当更新的节点位于高速缓存中并且不需要首先从磁盘读取时，对临时表执行最频繁的更新操作将更快地执行。如果临时表的索引大小与冷键高速缓存的大小相当，则更新节点位于高速缓存中的可能性非常高。

该[`CACHE INDEX`](sql-statements.html#cache-index)语句在表和键高速缓存之间建立关联，但是每次服务器重新启动时，关联都会丢失。如果要使该关联在每次服务器启动时都生效，则一种实现方法是使用选项文件：包括用于配置键高速缓存的变量设置和[`init_file`](server-administration.html#sysvar_init_file)用于命名包含[`CACHE INDEX`](sql-statements.html#cache-index)要执行的语句的文件的 系统变量。例如：

```
key_buffer_size = 4G
hot_cache.key_buffer_size = 2G
cold_cache.key_buffer_size = 2G
init_file = / path/ to/ data-directory/mysqld_init.sql
```

`mysqld_init.sql`每次服务器启动时都会执行 in语句。该文件每行应包含一个SQL语句。以下示例为`hot_cache`和 分别分配了几个表`cold_cache`：

```
缓存索引db1.t1，db1.t2，db2.t3在hot_cache中
缓存索引db1.t4，db2.t5，db2.t6 IN Cold_cache
```

#### 8.10.2.3中点插入策略

默认情况下，密钥缓存管理系统使用简单的LRU策略来选择要逐出的密钥缓存块，但它还支持一种更复杂的方法，称为 中点插入策略。

使用中点插入策略时，LRU链分为两部分：热子列表和热子列表。两个部分之间的划分点不是固定的，但是密钥缓存管理系统要注意热部分不要 “ 太短 ”，始终包含至少一部分 [`key_cache_division_limit`](server-administration.html#sysvar_key_cache_division_limit) 密钥缓存块。 [`key_cache_division_limit`](server-administration.html#sysvar_key_cache_division_limit)是结构化键缓存变量的组成部分，因此其值是可以为每个缓存设置的参数。

当将索引块从表中读取到键高速缓存中时，该索引块将置于热子列表的末尾。达到一定数量的点击次数（访问该块）后，它将被提升到热门子列表。目前，对于所有索引块而言，提升一个块（3）所需的命中数都是相同的。

提升到热子列表中的块位于列表的末尾。然后，该块在该子列表中循环。如果该块在子列表的开头停留了足够长的时间，它将被降级到热子列表。此时间由[`key_cache_age_threshold`](server-administration.html#sysvar_key_cache_age_threshold) 密钥缓存的组件的值确定 。

阈值规定，对于包含*`N`*块的键高速缓存，将在最后一次`*`N`* * key_cache_age_threshold / 100`匹配中未访问的热子列表开始处的块 移至热子列表的开始处。然后，它将成为第一个驱逐候选对象，因为要替换的块总是从温暖子列表的开头开始。

中点插入策略使您可以将更多有价值的块始终保留在缓存中。如果您更喜欢使用普通LRU策略，请将该[`key_cache_division_limit`](server-administration.html#sysvar_key_cache_division_limit) 值保留 为其默认值100。

当执行需要索引扫描的查询有效地将与有价值的高级B树节点相对应的所有索引块推出缓存时，中点插入策略有助于提高性能。为了避免这种情况，您必须使用[`key_cache_division_limit`](server-administration.html#sysvar_key_cache_division_limit)设置为小于100 的中点插入策略 。然后，在索引扫描操作期间，有价值的频繁命中的节点也会保留在热子列表中。

#### 8.10.2.4索引预加载

如果键高速缓存中有足够的块来容纳整个索引的块，或者至少包含与其非叶节点相对应的块，那么在开始使用键高速缓存之前，先将索引块预加载到索引中是有意义的。预加载使您能够以最有效的方式将表索引块放入键高速缓存缓冲区中：通过从磁盘顺序读取索引块。

在没有预加载的情况下，块仍会根据查询的需要放置在键高速缓存中。尽管这些块将保留在高速缓存中，但是由于所有块都有足够的缓冲区，所以它们是从磁盘中以随机顺序而不是顺序获取的。

要将索引预加载到缓存中，请使用以下 [`LOAD INDEX INTO CACHE`](sql-statements.html#load-index)语句。例如，以下语句预加载表`t1`和的索引的节点（索引块）`t2`：

```
mysql> LOAD INDEX INTO CACHE t1, t2 IGNORE LEAVES;
+ --------- + -------------- + ---------- + ---------- +
| 桌子 运算| Msg_type | Msg_text |
+ --------- + -------------- + ---------- + ---------- +
| test.t1 | preload_keys | 状态| 好的
| test.t2 | preload_keys | 状态| 好的
+ --------- + -------------- + ---------- + ---------- +
```

所述`IGNORE LEAVES`改性剂导致要预装只为索引的非叶结点的块。因此，显示的语句预加载来自的所有索引块`t1`，但仅加载来自 的非叶节点的块`t2`。

如果已使用[`CACHE INDEX`](sql-statements.html#cache-index)语句将索引分配给键高速缓存 ，则预加载会将索引块放入该高速缓存中。否则，索引将加载到默认键缓存中。

#### 8.10.2.5密钥缓存块大小

可以使用该[`key_cache_block_size`](server-administration.html#sysvar_key_cache_block_size) 变量为单个键高速缓存指定块缓冲区的大小 。这允许调整索引文件的I / O操作的性能。

当读取缓冲区的大小等于本机操作系统I / O缓冲区的大小时，可以实现I / O操作的最佳性能。但是，将关键节点的大小设置为等于I / O缓冲区的大小并不能始终确保最佳的整体性能。读取大叶节点时，服务器会提取大量不必要的数据，从而有效地防止读取其他叶节点。

要控制表的`.MYI` 索引文件中块的大小，请在服务器启动时`MyISAM`使用该 [`--myisam-block-size`](server-administration.html#option_mysqld_myisam-block-size)选项。

#### 8.10.2.6重构密钥缓存

密钥缓存可以随时通过更新其参数值进行重组。例如：

```
mysql> SET GLOBAL cold_cache.key_buffer_size=4*1024*1024;
```

如果您为[`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)或 [`key_cache_block_size`](server-administration.html#sysvar_key_cache_block_size)键高速缓存组件分配 的值与该组件的当前值不同，则服务器将破坏高速缓存的旧结构，并根据新值创建一个新的结构。如果缓存中包含任何脏块，则服务器会在销毁并重新创建缓存之前将它们保存到磁盘。如果您更改其他关键缓存参数，则不会发生重组。

重组键高速缓存时，服务器首先将所有脏缓冲区的内容刷新到磁盘。此后，缓存内容将不可用。但是，重组不会阻止需要使用分配给缓存的索引的查询。而是，服务器使用本机文件系统缓存直接访问表索引。文件系统缓存的效率不如使用键缓存，因此尽管执行查询，但可以预见其速度会变慢。重组缓存后，它可再次用于缓存分配给它的索引，并且不再对索引使用文件系统缓存。

### 8.10.3 MySQL查询缓存

- [8.10.3.1查询缓存如何运行](optimization.html#query-cache-operation)
- [8.10.3.2查询缓存SELECT选项](optimization.html#query-cache-in-select)
- [8.10.3.3查询缓存配置](optimization.html#query-cache-configuration)
- [8.10.3.4查询缓存状态和维护](optimization.html#query-cache-status-and-maintenance)



注意

从MySQL 5.7.20开始，查询缓存已弃用，并在MySQL 8.0中删除。

查询缓存将[`SELECT`](sql-statements.html#select)语句文本以及发送到客户端的相应结果存储 在一起。如果以后收到相同的语句，则服务器从查询缓存中检索结果，而不是再次解析并执行该语句。查询缓存在会话之间共享，因此可以响应另一个客户端发出的同一查询来发送一个客户端生成的结果集。

查询高速缓存在您的表不经常更改且服务器接收许多相同查询的环境中很有用。这是许多Web服务器基于数据库内容生成许多动态页面的典型情况。

查询缓存不返回陈旧数据。修改表后，将刷新查询缓存中的所有相关条目。

注意

查询缓存在您有多个[**mysqld**](programs.html#mysqld)服务器更新同一 `MyISAM`表的环境中不起作用。

在[第8.10.3.1节“查询缓存的运行方式”中](optimization.html#query-cache-operation)所述的条件下，查询缓存用于准备好的语句。

注意

分区表不支持查询缓存，而涉及分区表的查询将自动禁用查询缓存。无法为此类查询启用查询缓存。

随后是查询缓存的一些性能数据。这些结果是通过在具有2GB RAM和64MB查询缓存的Linux Alpha 2×500MHz系统上运行MySQL基准套件而产生的。

- 如果您正在执行的所有查询都很简单（例如从具有一行的表中选择一行），但是仍然不同，以致无法缓存查询，那么使查询缓存处于活动状态的开销为13％。这可以被认为是最坏的情况。在现实生活中，查询往往要复杂得多，因此开销通常会大大降低。
- 使用查询缓存，单行表中的单行搜索比没有查询时快238％。这可以视为接近缓存查询所期望的最小加速。

要在服务器启动时禁用查询缓存，请将[`query_cache_size`](server-administration.html#sysvar_query_cache_size)系统变量设置 为0。通过禁用查询缓存代码，不会有明显的开销。

查询缓存提供了显着提高性能的潜力，但不要假设它在所有情况下都可以这样做。使用某些查询缓存配置或服务器工作负载，您实际上可能会看到性能下降：

- 对于过大的查询缓存，请谨慎设置大小，这会增加维护缓存所需的开销，可能超出启用缓存的好处。通常，几十兆字节的大小是有益的。大小可能不会达到数百兆字节。
- 服务器工作负载对查询缓存效率有重大影响。几乎完全由固定的一组[`SELECT`](sql-statements.html#select) 语句组成的查询混合比启用频繁缓存的[`INSERT`](sql-statements.html#insert)语句导致缓存中的结果连续无效的混合更有可能从启用缓存中受益 。在某些情况下，一种解决方法是使用该 `SQL_NO_CACHE`选项来防止结果进入[`SELECT`](sql-statements.html#select)使用频繁修改表的语句甚至进入缓存 。（请参见 [第8.10.3.2节“查询缓存选择选项”](optimization.html#query-cache-in-select)。）

要验证启用查询缓存是否有益，请在启用和禁用缓存的情况下测试MySQL服务器的运行情况。然后定期重新测试，因为查询缓存效率可能随服务器工作负载的变化而变化。

#### 8.10.3.1查询缓存如何运行

注意

从MySQL 5.7.20开始，查询缓存已弃用，并在MySQL 8.0中删除。

本节描述查询高速缓存可操作时的工作方式。[第8.10.3.3节“查询缓存配置”](optimization.html#query-cache-configuration)，描述了如何控制它是否可操作。

在解析之前，将传入的查询与查询缓存中的查询进行比较，因此以下两个查询被查询缓存视为不同：

```
SELECT * FROM tbl_name
SELECT * FROMtbl_name
```

查询必须*完全相同*（逐字节），才能被视为相同。此外，由于其他原因，相同的查询字符串可能会被视为不同。使用不同数据库，不同协议版本或不同默认字符集的查询被视为不同查询，并分别进行缓存。

缓存不用于以下类型的查询：

- 作为外部查询的子查询的查询
- 在存储的函数，触发器或事件的主体内执行的查询

在从查询缓存中获取查询结果之前，MySQL将检查用户是否[`SELECT`](sql-statements.html#select)对所涉及的所有数据库和表具有 特权。如果不是这种情况，则不使用缓存的结果。

如果从查询缓存返回查询结果，则服务器将递增[`Qcache_hits`](server-administration.html#statvar_Qcache_hits) 状态变量，而不是`Com_select`。请参见 [第8.10.3.4节“查询缓存状态和维护”](optimization.html#query-cache-status-and-maintenance)。

如果表发生更改，则使用该表的所有缓存查询都将变为无效，并从缓存中删除。这包括使用`MERGE`映射到已更改表的表的查询。一个表可以被许多类型的语句，如被改变[`INSERT`](sql-statements.html#insert)， [`UPDATE`](sql-statements.html#update)， [`DELETE`](sql-statements.html#delete)， [`TRUNCATE TABLE`](sql-statements.html#truncate-table)， [`ALTER TABLE`](sql-statements.html#alter-table)， [`DROP TABLE`](sql-statements.html#drop-table)，或 [`DROP DATABASE`](sql-statements.html#drop-database)。

使用`InnoDB`表时，查询缓存也可在事务内工作 。

来自[`SELECT`](sql-statements.html#select)视图查询的结果被缓存。

查询缓存适用于`SELECT SQL_CALC_FOUND_ROWS ...`查询，并存储后续`SELECT FOUND_ROWS()`查询返回的值。 [`FOUND_ROWS()`](functions.html#function_found-rows)即使从缓存中提取了先前的查询，也将返回正确的值，因为找到的行数也存储在缓存中。该`SELECT FOUND_ROWS()`查询本身不能被缓存。

使用二进制协议使用[`mysql_stmt_prepare()`](connectors-apis.html#mysql-stmt-prepare)和 发出的预备语句[`mysql_stmt_execute()`](connectors-apis.html#mysql-stmt-execute)（请参见 [第27.7.7节“ C API预备语句”](connectors-apis.html#c-api-prepared-statements)）在缓存方面受到限制。与查询缓存中的语句进行比较是基于扩展`?`参数标记后的语句文本。该语句仅与使用二进制协议执行的其他缓存语句进行比较。也就是说，出于查询缓存的目的，使用二进制协议发布的预备语句不同于使用文本协议发布的预备语句（请参见 [第13.5节“预备语句”](sql-statements.html#sql-prepared-statements)）。

如果查询使用以下任何功能，则不能将其缓存：

- [`AES_DECRYPT()`](functions.html#function_aes-decrypt)
- [`AES_ENCRYPT()`](functions.html#function_aes-encrypt)
- [`BENCHMARK()`](functions.html#function_benchmark)
- [`CONNECTION_ID()`](functions.html#function_connection-id)
- [`CONVERT_TZ()`](functions.html#function_convert-tz)
- [`CURDATE()`](functions.html#function_curdate)
- [`CURRENT_DATE()`](functions.html#function_current-date)
- [`CURRENT_TIME()`](functions.html#function_current-time)
- [`CURRENT_TIMESTAMP()`](functions.html#function_current-timestamp)
- [`CURRENT_USER()`](functions.html#function_current-user)
- [`CURTIME()`](functions.html#function_curtime)
- [`DATABASE()`](functions.html#function_database)
- [`ENCRYPT()`](functions.html#function_encrypt) 一个参数
- [`FOUND_ROWS()`](functions.html#function_found-rows)
- [`GET_LOCK()`](functions.html#function_get-lock)
- [`IS_FREE_LOCK()`](functions.html#function_is-free-lock)
- [`IS_USED_LOCK()`](functions.html#function_is-used-lock)
- [`LAST_INSERT_ID()`](functions.html#function_last-insert-id)
- [`LOAD_FILE()`](functions.html#function_load-file)
- [`MASTER_POS_WAIT()`](functions.html#function_master-pos-wait)
- [`NOW()`](functions.html#function_now)
- [`PASSWORD()`](functions.html#function_password)
- [`RAND()`](functions.html#function_rand)
- [`RANDOM_BYTES()`](functions.html#function_random-bytes)
- [`RELEASE_ALL_LOCKS()`](functions.html#function_release-all-locks)
- [`RELEASE_LOCK()`](functions.html#function_release-lock)
- [`SLEEP()`](functions.html#function_sleep)
- [`SYSDATE()`](functions.html#function_sysdate)
- [`UNIX_TIMESTAMP()`](functions.html#function_unix-timestamp) 没有参数
- [`USER()`](functions.html#function_user)
- [`UUID()`](functions.html#function_uuid)
- [`UUID_SHORT()`](functions.html#function_uuid-short)

在以下情况下，也不会缓存查询：

- 它指的是用户定义的函数（UDF）或存储的函数。

- 它指的是用户变量或本地存储的程序变量。

- 它是指在表`mysql`， `INFORMATION_SCHEMA`或 `performance_schema`数据库。

- 它引用任何分区表。

- 它具有以下任何形式：

  ```
  选择...锁定共享模式
  选择...更新
  选择...进入文件...
  选择...进入转储文件...
  SELECT * FROM ... WHERE autoincrement_col为NULL
  ```

  不缓存最后一个形式，因为它用作获取最后一个插入ID值的ODBC解决方法。请参阅[第27章，*连接器和API*](connectors-apis.html)的连接器/ ODBC部分 。

  使用[`SERIALIZABLE`](innodb-storage-engine.html#isolevel_serializable)隔离级别的事务中的语句 也无法缓存，因为它们使用`LOCK IN SHARE MODE`锁定。

- 它使用`TEMPORARY`表。

- 它不使用任何表。

- 它生成警告。

- 用户对任何涉及的表都具有列级特权。

#### 8.10.3.2查询缓存SELECT选项

注意

从MySQL 5.7.20开始，查询缓存已弃用，并在MySQL 8.0中删除。

可以在[`SELECT`](sql-statements.html#select)语句中指定两个与查询缓存相关的选项 ：

- `SQL_CACHE`

  如果查询结果是可缓存的并且[`query_cache_type`](server-administration.html#sysvar_query_cache_type)系统变量的值为`ON`or ，则将缓存该查询结果 `DEMAND`。

- 

  `SQL_NO_CACHE`

  服务器不使用查询缓存。它既不检查查询缓存以查看结果是否已经缓存，也不缓存查询结果。

例子：

```
SELECT SQL_CACHE id，名称FROM客户；
SELECT SQL_NO_CACHE ID，名称FROM客户；
```

#### 8.10.3.3查询缓存配置

注意

从MySQL 5.7.20开始，查询缓存已弃用，并在MySQL 8.0中删除。

该[`have_query_cache`](server-administration.html#sysvar_have_query_cache)服务器系统变量指示查询缓存是否可用：

```
mysql> SHOW VARIABLES LIKE 'have_query_cache';
+ ------------------ + ------- +
| 变量名| 价值|
+ ------------------ + ------- +
| have_query_cache | 是的
+ ------------------ + ------- +
```

使用标准MySQL二进制文件时`YES`，即使禁用查询缓存，该值也始终 为。

其他几个系统变量控制查询缓存操作。这些可以在启动[**mysqld**](programs.html#mysqld)时在选项文件中或命令行中设置。查询缓存系统变量的名称都以开头 `query_cache_`。它们在[第5.1.7节“服务器系统变量”](server-administration.html#server-system-variables)中进行了简要描述，并在 此处提供了其他配置信息。

要设置查询缓存的大小，请设置 [`query_cache_size`](server-administration.html#sysvar_query_cache_size)系统变量。将其设置为0会禁用查询缓存，而将设置也会禁用[`query_cache_type=0`](server-administration.html#sysvar_query_cache_type)。默认情况下，查询缓存处于禁用状态。这是使用默认大小1M（默认 `query_cache_type`值为0）来实现的。

为了显着减少开销，[`query_cache_type=0`](server-administration.html#sysvar_query_cache_type)如果您不使用查询缓存，请使用启动服务器 。

注意

使用Windows配置向导安装或配置MySQL时，[`query_cache_size`](server-administration.html#sysvar_query_cache_size)将根据可用的不同配置类型自动为您配置默认值 。使用Windows配置向导时，由于选择了配置，因此可能启用了查询缓存（即，将其设置为非零值）。查询缓存也由[`query_cache_type`](server-administration.html#sysvar_query_cache_type)变量的设置控制 。配置完成后，检查`my.ini`文件中设置的这些变量的值 。

设置[`query_cache_size`](server-administration.html#sysvar_query_cache_size) 为非零值时，请记住查询缓存需要最小大小约为40KB才能分配其结构。（确切的大小取决于系统体系结构。）如果将值设置得太小，则会收到警告，如以下示例所示：

```
mysql> SET GLOBAL query_cache_size = 40000;
查询正常，影响0行，1警告（0.00秒）

mysql> SHOW WARNINGS\G
*************************** 1.行******************** *******
  等级：警告
   编码：1282
消息：查询高速缓存设置大小39936失败；
         新的查询缓存大小为0

mysql> SET GLOBAL query_cache_size = 41984;
查询正常，受影响的0行（0.00秒）

mysql> SHOW VARIABLES LIKE 'query_cache_size';
+ ------------------ + ------- +
| 变量名| 价值|
+ ------------------ + ------- +
| query_cache_size | 41984 |
+ ------------------ + ------- +
```

为了使查询缓存实际上能够保存任何查询结果，必须将其大小设置为更大：

```
mysql> SET GLOBAL query_cache_size = 1000000;
查询正常，受影响的0行（0.04秒）

mysql> SHOW VARIABLES LIKE 'query_cache_size';
+ ------------------ + -------- +
| 变量名| 价值|
+ ------------------ + -------- +
| query_cache_size | 999424 |
+ ------------------ + -------- +
设置1行（0.00秒）
```

该[`query_cache_size`](server-administration.html#sysvar_query_cache_size)值与最接近的1024字节块对齐。因此，报告的值可能与您分配的值不同。

如果查询缓存大小大于0，则 [`query_cache_type`](server-administration.html#sysvar_query_cache_type)变量会影响其工作方式。可以将此变量设置为以下值：

- 的值`0`或`OFF` 防止缓存或检索缓存的结果。
- 值`1`或`ON` 启用缓存，但以开头的语句除外 `SELECT SQL_NO_CACHE`。
- 的值`2`或 `DEMAND`导致仅缓存以开头的语句`SELECT SQL_CACHE`。

如果[`query_cache_size`](server-administration.html#sysvar_query_cache_size)为0，则还应该将[`query_cache_type`](server-administration.html#sysvar_query_cache_type)变量设置 为0。在这种情况下，服务器根本不会获取查询缓存互斥量，这意味着无法在运行时启用查询缓存，并且减少了查询执行的开销。

设置该`GLOBAL` [`query_cache_type`](server-administration.html#sysvar_query_cache_type)值可以确定更改后连接的所有客户端的查询缓存行为。各个客户端可以通过设置`SESSION` [`query_cache_type`](server-administration.html#sysvar_query_cache_type)值来控制其自己的连接的缓存行为 。例如，客户端可以禁止对自己的查询使用查询缓存，如下所示：

```
mysql> SET SESSION query_cache_type = OFF;
```

如果[`query_cache_type`](server-administration.html#sysvar_query_cache_type) 在服务器启动时（而不是在运行时使用 [`SET`](sql-statements.html#set-variable) 语句）设置，则仅允许数字值。

要控制可缓存的单个查询结果的最大大小，请设置 [`query_cache_limit`](server-administration.html#sysvar_query_cache_limit)系统变量。默认值为1MB。

注意不要将缓存的大小设置得太大。由于在更新过程中需要线程锁定高速缓存，因此您可能会看到高速缓存非常大的锁定争用问题。

注意

[`SET`](sql-statements.html#set-variable) 通过使用 命令行或配置文件中的选项，可以使用语句设置 在运行时为查询缓存指定的最大大小 。 `--maximum-query_cache_size=*`32M`*`

当要缓存查询时，其结果（发送到客户端的数据）将在结果检索期间存储在查询缓存中。因此，通常不会大批量处理数据。查询高速缓存分配块以按需存储此数据，因此当一个块被填充时，将分配一个新块。由于内存分配操作成本高昂（按时间排列），因此查询缓存将分配具有[`query_cache_min_res_unit`](server-administration.html#sysvar_query_cache_min_res_unit) 系统变量给定的最小大小的块 。执行查询时，将最后一个结果块修剪为实际数据大小，以便释放未使用的内存。根据服务器执行的查询类型，您可能会发现调整以下值会有所帮助 [`query_cache_min_res_unit`](server-administration.html#sysvar_query_cache_min_res_unit)：

- 默认值为 [`query_cache_min_res_unit`](server-administration.html#sysvar_query_cache_min_res_unit) 4KB。对于大多数情况，这应该足够了。
- 如果您有很多查询而结果却很少，则默认的块大小可能会导致内存碎片，这由大量的空闲块指示。由于内存不足，碎片会迫使查询缓存从缓存中删除查询（删除）。在这种情况下，减小的值 [`query_cache_min_res_unit`](server-administration.html#sysvar_query_cache_min_res_unit)。由于修剪而删除的空闲块和查询的数量由[`Qcache_free_blocks`](server-administration.html#statvar_Qcache_free_blocks)和 [`Qcache_lowmem_prunes`](server-administration.html#statvar_Qcache_lowmem_prunes) 状态变量的值给出 。
- 如果您的大多数查询都有较大的结果（请检查 [`Qcache_total_blocks`](server-administration.html#statvar_Qcache_total_blocks)和 [`Qcache_queries_in_cache`](server-administration.html#statvar_Qcache_queries_in_cache) 状态变量），则可以通过提高来提高性能 [`query_cache_min_res_unit`](server-administration.html#sysvar_query_cache_min_res_unit)。但是，请注意不要使其过大（请参阅上一项）。

#### 8.10.3.4查询缓存状态和维护

注意

从MySQL 5.7.20开始，查询缓存已弃用，并在MySQL 8.0中删除。

要检查您的MySQL服务器中是否存在查询缓存，请使用以下语句：

```
mysql> SHOW VARIABLES LIKE 'have_query_cache';
+ ------------------ + ------- +
| 变量名| 价值|
+ ------------------ + ------- +
| have_query_cache | 是的
+ ------------------ + ------- +
```

您可以对查询缓存进行碎片整理，以更好地利用该[`FLUSH QUERY CACHE`](sql-statements.html#flush-query-cache)语句的内存。该语句不会从缓存中删除任何查询。

该`RESET QUERY CACHE`语句从查询缓存中删除所有查询结果。该 [`FLUSH TABLES`](sql-statements.html#flush-tables)语句也这样做。

要监视查询缓存性能，请使用 [`SHOW STATUS`](sql-statements.html#show-status)来查看缓存状态变量：

```
mysql> SHOW STATUS LIKE 'Qcache%';
+ ------------------------- + -------- +
| 变量名| 价值|
+ ------------------------- + -------- +
| Qcache_free_blocks | 36 |
| Qcache_free_memory | 138488 |
| Qcache_hits | 79570 |
| Qcache_inserts | 27087 |
| Qcache_lowmem_prunes | 3114 |
| Qcache_not_cached | 22989 |
| Qcache_queries_in_cache | 415 |
| Qcache_total_blocks | 912 |
+ ------------------------- + -------- +
```

[第5.1.9节“服务器状态变量”](server-administration.html#server-status-variables) 中给出了对每个变量的描述 。这里描述了它们的一些用途。

[`SELECT`](sql-statements.html#select) 查询 总数由以下公式给出：

```
  Com_select
+ Qcache_hits
+解析器发现错误的查询
```

该`Com_select`值由以下公式给出：

```
  Qcache_inserts
+ Qcache_not_cached
+查询在列特权检查期间发现的错误
```

查询缓存使用可变长度的块，因此 [`Qcache_total_blocks`](server-administration.html#statvar_Qcache_total_blocks)并 [`Qcache_free_blocks`](server-administration.html#statvar_Qcache_free_blocks)可能指示查询缓存内存碎片。之后 [`FLUSH QUERY CACHE`](sql-statements.html#flush-query-cache)，仅剩一个空闲块。

每个缓存的查询至少需要两个块（一个用于查询文本，一个或多个用于查询结果）。同样，查询使用的每个表都需要一个块。但是，如果两个或多个查询使用同一张表，则只需要分配一个表块。

[`Qcache_lowmem_prunes`](server-administration.html#statvar_Qcache_lowmem_prunes)status变量 提供的信息 可以帮助您调整查询缓存的大小。它计算从缓存中删除的查询数，以释放内存以缓存新查询。查询缓存使用最近最少使用（LRU）策略来决定从缓存中删除哪些查询。[第8.10.3.3节“查询缓存配置”中](optimization.html#query-cache-configuration)给出了调整信息 。

### 8.10.4缓存准备好的语句和存储的程序



对于某些客户端可能在会话中多次执行的语句，服务器会将语句转换为内部结构并缓存该结构以在执行期间使用。缓存使服务器能够更有效地执行，因为它避免了会话期间再次需要使用该语句时所产生的转换开销。这些语句发生转换和缓存：

- 准备好的语句，包括在SQL级别处理的[`PREPARE`](sql-statements.html#prepare)语句（使用该语句）和使用二进制客户端/服务器协议（使用[`mysql_stmt_prepare()`](connectors-apis.html#mysql-stmt-prepare)C API函数）处理的语句 。所述 [`max_prepared_stmt_count`](server-administration.html#sysvar_max_prepared_stmt_count) 系统变量控制语句的服务器高速缓存的总数量。（所有会话中已准备好的语句总数）。
- 存储的程序（存储的过程和功能，触发器和事件）。在这种情况下，服务器将转换并缓存整个程序主体。该 [`stored_program_cache`](server-administration.html#sysvar_stored_program_cache)系统变量指示存储的程序每个会话的服务器缓存的大致数量。

服务器在每个会话的基础上为准备好的语句和存储的程序维护高速缓存。为一个会话缓存的语句不能被其他会话访问。会话结束时，服务器将丢弃为其缓存的所有语句。

当服务器使用缓存的内部语句结构时，必须注意该结构不会过时。语句所使用的对象可能发生元数据更改，从而导致当前对象定义与内部语句结构中表示的定义之间不匹配。DDL语句会发生元数据更改，例如创建，删除，更改，重命名或截断表，分析，优化或修复表的语句。表内容的更改（例如，使用[`INSERT`](sql-statements.html#insert)或 [`UPDATE`](sql-statements.html#update)）不会更改元数据，[`SELECT`](sql-statements.html#select)语句也不会更改。

这是问题的例证。假设客户准备以下语句：

```
从'SELECT * FROM t1'准备s1;
```

在`SELECT *`内部结构上表中的列的列表展开。如果使用修改了表中的列集`ALTER TABLE`，则prepared语句已过期。如果服务器在下次客户端执行时未检测到此更改`s1`，则prepared语句将返回错误的结果。

为了避免由准备好的语句引用的表或视图的元数据更改引起的问题，服务器将检测到这些更改，并在下次执行该语句时自动重新准备该语句。即，服务器重新解析该语句并重建内部结构。从表定义高速缓存中清除引用的表或视图之后，也会重新进行解析，这是隐式地为高速缓存中的新条目腾出空间，或者是由于导致的显式[`FLUSH TABLES`](sql-statements.html#flush-tables)。

同样，如果存储程序使用的对象发生更改，则服务器将重新解析程序中受影响的语句。

服务器还检测表达式中对象的元数据更改。这些可能会在陈述具体可用于存储程序，如`DECLARE CURSOR`或流量控制语句，如 [`IF`](sql-statements.html#if)， [`CASE`](sql-statements.html#case)和 [`RETURN`](sql-statements.html#return)。

为了避免重新解析整个存储的程序，服务器仅在需要时才重新解析程序中受影响的语句或表达式。例子：

- 假设表或视图的元数据已更改。对于`SELECT *`访问表或视图的程序，将进行重新解析，但对于`SELECT *`不访问表或视图的程序，则不会进行 重新解析。

- 当一条语句受到影响时，服务器将在可能的情况下仅部分对其进行重新分析。考虑以下 [`CASE`](sql-statements.html#case)语句：

  ```
  CASE case_expr
    WHENwhen_expr1 ...
    什么时候 when_expr2 ...
    什么时候 when_expr3 ...
    ...
  结束案例
  ```

  如果元数据更改仅影响，则将重新解析该表达式。 而其他表达式则无法解析。 `WHEN *`when_expr3`*`*`case_expr`*`WHEN`

重新解析使用默认的数据库和SQL模式，该模式对原始转换为内部格式有效。

服务器尝试最多进行三次重新解析。如果所有尝试均失败，则会发生错误。

重新解析是自动的，但是在一定程度上会降低准备好的语句和存储程序的性能。

对于准备好的语句，[`Com_stmt_reprepare`](server-administration.html#statvar_Com_xxx) 状态变量跟踪重新准备 的次数。

## 8.11优化锁定操作

- [8.11.1内部锁定方法](optimization.html#internal-locking)
- [8.11.2表锁定问题](optimization.html#table-locking)
- [8.11.3并发插入](optimization.html#concurrent-inserts)
- [8.11.4元数据锁定](optimization.html#metadata-locking)
- [8.11.5外部锁定](optimization.html#external-locking)

MySQL使用[锁定](glossary.html#glos_locking)管理表内容的争用 ：

- 内部锁定在MySQL服务器本身内部执行，以管理多个线程对表内容的争用。这种类型的锁定是内部锁定，因为它完全由服务器执行，并且不涉及其他程序。请参见 [第8.11.1节“内部锁定方法”](optimization.html#internal-locking)。
- 当服务器和其他程序锁定[`MyISAM`](storage-engines.html#myisam-storage-engine)表文件以相互协调哪个程序可以在何时访问表时，就会发生外部锁定。请参见[第8.11.5节“外部锁定”](optimization.html#external-locking)。

### 8.11.1内部锁定方法



本节讨论内部锁定。也就是说，在MySQL服务器内部执行锁定，以管理多个会话对表内容的争用。这种类型的锁定是内部锁定，因为它完全由服务器执行，并且不涉及其他程序。有关其他程序对MySQL文件执行的锁定，请参见[第8.11.5节“外部锁定”](optimization.html#external-locking)。

- [行级锁定](optimization.html#internal-row-level-locking)
- [表级锁定](optimization.html#internal-table-level-locking)
- [选择锁定类型](optimization.html#internal-locking-choices)

#### 行级锁定

MySQL 对表使用[行级锁定](glossary.html#glos_row_lock)，`InnoDB`以支持多个会话同时进行写访问，从而使其适用于多用户，高度并发和OLTP应用程序。

为了避免在单个表上执行多个并发写操作时出现 [死锁](glossary.html#glos_deadlock)，即使数据更改语句出现在事务的后面，也要`InnoDB`通过`SELECT ... FOR UPDATE`在每个事务的行组中发布一条语句来获取必要的锁，该语句针对每组预期被修改的行。如果事务修改或锁定了多个表，请在每个事务中以相同顺序发出适用的语句。死锁会影响性能，而不是代表严重错误，因为 死锁会`InnoDB`自动 [检测到](glossary.html#glos_deadlock_detection)死锁条件并回滚受影响的事务之一。

在高并发系统上，当多个线程等待相同的锁时，死锁检测会导致速度变慢。有时，禁用死锁检测并在[`innodb_lock_wait_timeout`](innodb-storage-engine.html#sysvar_innodb_lock_wait_timeout) 发生死锁时依靠设置进行事务回滚可能会更有效 。可以使用[`innodb_deadlock_detect`](innodb-storage-engine.html#sysvar_innodb_deadlock_detect) 配置选项禁用死锁检测 。

行级锁定的优点：

- 当不同的会话访问不同的行时，锁冲突减少。
- 回滚更改较少。
- 可以长时间锁定单个行。

#### 表级锁定

MySQL 对，和 表使用[表级锁定](glossary.html#glos_table_lock)`MyISAM`， 仅允许一个会话一次更新这些表。此锁定级别使这些存储引擎更适合于只读，只读或单用户应用程序。 `MEMORY``MERGE`

这些存储引擎通过始终在查询开始时一次请求所有需要的锁并始终以相同顺序锁定表来避免 [死锁](glossary.html#glos_deadlock)。权衡是该策略减少了并发性。其他要修改表的会话必须等待，直到当前数据更改语句完成为止。

表级锁定的优点：

- 所需的内存相对较少（行锁定需要锁定每行或每组行的内存）
- 在表的大部分上使用时非常快，因为只涉及一个锁。
- 如果您经常`GROUP BY` 对大部分数据进行操作，或者必须经常扫描整个表，则速度很快。

MySQL授予表写锁定，如下所示：

1. 如果表上没有锁，请在其上放置写锁。
2. 否则，将锁定请求放入写锁定队列中。

MySQL授予表读取锁，如下所示：

1. 如果表上没有写锁，请在其上放置一个读锁。
2. 否则，将锁定请求放入读取锁定队列中。

表更新的优先级高于表检索。因此，释放锁时，该锁可用于写锁队列中的请求，然后可用于读锁队列中的请求。这样可以确保即使表有大量活动，对表的更新也不会“ 饿死 ”[`SELECT`](sql-statements.html#select)。但是，如果表有许多更新，则 [`SELECT`](sql-statements.html#select)语句将等到没有更多更新。

有关更改读写优先级的信息，请参见[第8.11.2节“表锁定问题”](optimization.html#table-locking)。

您可以通过检查[`Table_locks_immediate`](server-administration.html#statvar_Table_locks_immediate)和 [`Table_locks_waited`](server-administration.html#statvar_Table_locks_waited)状态变量来分析系统上的表锁争用 ，分别指示可以立即授予表锁请求的次数和必须等待的次数：

```
mysql> SHOW STATUS LIKE 'Table%';
+ ----------------------- + --------- +
| 变量名| 价值|
+ ----------------------- + --------- +
| Table_locks_immediate | 1151552 |
| Table_locks_waited | 15324 |
+ ----------------------- + --------- +
```

性能架构锁定表还提供锁定信息。请参见 [第25.12.12节“性能模式锁定表”](performance-schema.html#performance-schema-lock-tables)。



该`MyISAM`存储引擎支持并发插入，减少读者和作者之间的竞争给定表：如果一个`MyISAM` 表有数据文件的中间没有空闲块，行总是在数据文件的末尾插入。在这种情况下，您可以自由混合表的并发 [`INSERT`](sql-statements.html#insert)和 [`SELECT`](sql-statements.html#select)语句， `MyISAM`而无需锁定。也就是说，您可以将行插入`MyISAM`同时其他客户端正在读取该表。从表的中间删除或更新的行可能会导致漏洞。如果有孔，将禁用并发插入，但是当所有孔都已填充新数据时，并发插入会自动重新启用。若要控制此行为，请使用 [`concurrent_insert`](server-administration.html#sysvar_concurrent_insert)系统变量。请参见[第8.11.3节“并发插入”](optimization.html#concurrent-inserts)。

如果使用显式获取表锁 [`LOCK TABLES`](sql-statements.html#lock-tables)，则可以请求一个 `READ LOCAL`锁而不是一个 `READ`锁，以使其他会话在锁定表时能够执行并发插入。

执行许多[`INSERT`](sql-statements.html#insert)和 [`SELECT`](sql-statements.html#select)操作上的表 `t1`时并发的插入是不可能的，你可以插入行到一个临时表 `temp_t1`，并从临时表中的行更新真正的表：

```
mysql> LOCK TABLES t1 WRITE, temp_t1 WRITE;
mysql> INSERT INTO t1 SELECT * FROM temp_t1;
mysql> DELETE FROM temp_t1;
mysql>UNLOCK TABLES;
```

#### 选择锁定类型

通常，在以下情况下，表锁优于行级锁：

- 该表的大多数语句均为读取。

- 该表的语句是读和写的混合，其中写是对单行的更新或删除，可通过一次按键读取来获取：

  ```
  UPDATE tbl_nameSET column= valueWHERE unique_key_col= key_value;
  从tbl_name哪里删除unique_key_col= key_value;
  ```

- [`SELECT`](sql-statements.html#select)与并发[`INSERT`](sql-statements.html#insert) 语句结合，很少有 [`UPDATE`](sql-statements.html#update)or [`DELETE`](sql-statements.html#delete)语句。

- `GROUP BY`无需任何编写程序即可对整个表进行 许多扫描或操作。

使用高级锁，您可以通过支持不同类型的锁来更轻松地调整应用程序，因为锁开销比行级锁要少。

行级锁定以外的选项：

- 版本控制（例如在MySQL中用于并发插入的版本控制），可以同时拥有一个编写者和多个阅读者。这意味着数据库或表根据访问开始的时间支持数据的不同视图。这个其他常见的术语是 “ 时间旅行， ” “ 上写副本， ” 或“ 按需复制。”
- 在许多情况下，按需复制优于行级锁定。但是，在最坏的情况下，与使用普通锁相比，它可以使用更多的内存。
- 除了使用行级锁，您还可以使用应用程序级锁，例如MySQL [`GET_LOCK()`](functions.html#function_get-lock)和 [`RELEASE_LOCK()`](functions.html#function_release-lock)MySQL 提供的锁 。这些是咨询锁，因此它们仅适用于相互协作的应用程序。请参见 [第12.14节“锁定功能”](functions.html#locking-functions)。

### 8.11.2表锁定问题



`InnoDB`表使用行级锁定，因此多个会话和应用程序可以同时读取和写入同一个表，而不会彼此等待或产生不一致的结果。对于此存储引擎，请避免使用该[`LOCK TABLES`](sql-statements.html#lock-tables)语句，因为它不提供任何额外的保护，而是减少了并发性。自动行级锁定使这些表适合于具有最重要数据的最繁忙的数据库，同时由于不需要锁定和解锁表，还简化了应用程序逻辑。因此， `InnoDB`存储引擎是MySQL中的默认引擎。

MySQL对所有存储引擎都使用表锁定（而不是页面锁定，行锁定或列锁定） `InnoDB`。锁定操作本身没有太多的开销。但是，由于一次只能有一个会话可以写入表，因此要与其他存储引擎一起获得最佳性能，请主要将它们用于经常查询且很少插入或更新的表。

- [性能方面的考虑有利于InnoDB](optimization.html#table-locking-innodb)
- [锁定性能问题的变通办法](optimization.html#table-locking-workarounds)

#### 性能方面的考虑有利于InnoDB

选择是使用表`InnoDB`还是使用其他存储引擎创建表时 ，请记住表锁定的以下缺点：

- 表锁定使许多会话可以同时从一个表读取，但是如果一个会话要写入一个表，它必须首先获得独占访问权限，这意味着它可能必须先等待其他会话才能完成对该表的访问。在更新期间，要访问此特定表的所有其他会话都必须等待，直到更新完成。
- 在会话等待期间，表锁定会导致问题，因为磁盘已满，会话开始之前需要释放可用空间。在这种情况下，所有要访问问题表的会话也将处于等待状态，直到有更多磁盘空间可用为止。
- 一个[`SELECT`](sql-statements.html#select)是需要很长的时间，从更新表的同时，使其他场次出现缓慢或无响应运行防止其他会话声明。当会话正在等待获取表的独占访问权以进行更新时，其他发出[`SELECT`](sql-statements.html#select)语句的会话 将在其后排队，从而即使对于只读会话也降低了并发性。

#### 锁定性能问题的变通办法

以下各项描述了避免或减少表锁定引起的争用的一些方法：

- 考虑在设置过程`InnoDB`中使用`CREATE TABLE ... ENGINE=INNODB`或`ALTER TABLE ... ENGINE=INNODB`将现有表用于将表切换到 存储引擎 。有关[*此存储引擎*](innodb-storage-engine.html)的更多详细信息[，](innodb-storage-engine.html)请参见 [第14章](innodb-storage-engine.html)。
- 优化[`SELECT`](sql-statements.html#select)语句以使其运行更快，以便它们在较短的时间内锁定表。您可能必须创建一些汇总表才能执行此操作。
- 启动[**mysqld的**](programs.html#mysqld)使用 [`--low-priority-updates`](server-administration.html#sysvar_low_priority_updates)。对于仅使用表级锁（如存储引擎 `MyISAM`，`MEMORY`和 `MERGE`），这给所有的语句更新（修改），比表低优先级 [`SELECT`](sql-statements.html#select)的语句。在这种情况下，[`SELECT`](sql-statements.html#select) 前面场景中的第二条语句将在该[`UPDATE`](sql-statements.html#update)语句之前执行，而不会等待第一条语句 [`SELECT`](sql-statements.html#select)完成。
- 要指定应以低优先级完成在特定连接中发布的所有更新，请将[`low_priority_updates`](server-administration.html#sysvar_low_priority_updates) 服务器系统变量设置为 等于1。
- 要给特定的[`INSERT`](sql-statements.html#insert)， [`UPDATE`](sql-statements.html#update)或 [`DELETE`](sql-statements.html#delete)语句降低优先级，请使用`LOW_PRIORITY` 属性。
- 要赋予特定[`SELECT`](sql-statements.html#select) 语句更高的优先级，请使用 `HIGH_PRIORITY`属性。请参见 [第13.2.9节“ SELECT语句”](sql-statements.html#select)。
- 在 系统变量的值较低的情况下 启动[**mysqld**](programs.html#mysqld)， [`max_write_lock_count`](server-administration.html#sysvar_max_write_lock_count)以强制MySQL [`SELECT`](sql-statements.html#select) 在对表进行特定次数的插入之后暂时提高所有等待表的语句的优先级。这允许 `READ`一定数量的锁之后的 `WRITE`锁。
- 如果您在混合[`SELECT`](sql-statements.html#select)和 [`DELETE`](sql-statements.html#delete)语句方面遇到问题 ，则 `LIMIT`可以选择 [`DELETE`](sql-statements.html#delete)。请参见 [第13.2.2节“ DELETE语句”](sql-statements.html#delete)。
- 使用`SQL_BUFFER_RESULT`with [`SELECT`](sql-statements.html#select)语句可以帮助缩短表锁定的持续时间。请参见 [第13.2.9节“ SELECT语句”](sql-statements.html#select)。
- 通过允许对一个表中的列运行查询，而将更新限制在另一个表中的列，将表内容拆分为单独的表可能会有所帮助。
- 您可以更改锁定代码 `mysys/thr_lock.c`以使用单个队列。在这种情况下，写锁和读锁将具有相同的优先级，这可能对某些应用程序有所帮助。

### 8.11.3并发插入



该`MyISAM`存储引擎支持并发插入，减少读者和作者之间的竞争给定表：如果一个`MyISAM`表已经在数据文件中没有孔（中删除的行），一个 [`INSERT`](sql-statements.html#insert)语句可以执行行添加到表的末尾同时该 [`SELECT`](sql-statements.html#select)语句正在从表中读取行。如果有多个 [`INSERT`](sql-statements.html#insert)语句，它们将与这些[`SELECT`](sql-statements.html#select)语句同时排队并按顺序执行 。并发的结果[`INSERT`](sql-statements.html#insert)可能不会立即可见。

所述[`concurrent_insert`](server-administration.html#sysvar_concurrent_insert)系统变量可以被设置为修改并发插入处理。默认情况下，该变量设置为`AUTO`（或1），并按上述方式处理并发插入。如果 [`concurrent_insert`](server-administration.html#sysvar_concurrent_insert)设置为 `NEVER`（或0），则禁用并发插入。如果将变量设置为`ALWAYS` （或2），则即使对于已删除行的表，也允许在表末尾进行并发插入。另请参见[`concurrent_insert`](server-administration.html#sysvar_concurrent_insert)系统变量的描述。

如果使用二进制日志，则并发插入将转换为`CREATE ... SELECT`或 [`INSERT ... SELECT`](sql-statements.html#insert-select)语句的普通插入。这样做是为了确保您可以通过在备份操作期间应用日志来重新创建表的精确副本。请参见[第5.4.4节“二进制日志”](server-administration.html#binary-log)。另外，对于那些语句，在从中选择的表上放置了一个读取锁，从而阻止了对该表的插入。结果是该表的并发插入也必须等待。

使用[`LOAD DATA`](sql-statements.html#load-data)，如果您指定 `CONCURRENT`的`MyISAM` 表满足并发插入的条件（也就是说，中间不包含空闲块），则其他会话可以在[`LOAD DATA`](sql-statements.html#load-data)执行时从表中检索数据。即使没有其他会话同时使用该表，使用该 `CONCURRENT`选项[`LOAD DATA`](sql-statements.html#load-data)也会影响性能 。

如果指定`HIGH_PRIORITY`，则[`--low-priority-updates`](server-administration.html#sysvar_low_priority_updates)使用该选项启动服务器时它将覆盖该选项的效果 。这还会导致不使用并发插入。

为[`LOCK TABLE`](sql-statements.html#lock-tables)，之间的差`READ LOCAL`并且`READ`是 `READ LOCAL`允许不冲突的 [`INSERT`](sql-statements.html#insert)语句（并发插入），而锁被保持来执行。但是，如果您要在持有锁的同时使用服务器外部的进程来操作数据库，则不能使用此功能。

### 8.11.4元数据锁定



MySQL使用元数据锁定来管理对数据库对象的并发访问并确保数据一致性。元数据锁定不仅适用于表，而且还适用于模式，存储程序（过程，函数，触发器，调度的事件），表空间，通过[`GET_LOCK()`](functions.html#function_get-lock)函数获取的用户锁 （请参见 [第12.14节“锁定函数”](functions.html#locking-functions)）和通过[函数](functions.html#locking-functions)获取的锁。[第28.3.1节“锁定服务”中](extending-mysql.html#locking-service)描述 [的锁定服务](extending-mysql.html#locking-service)。

性能模式 [`metadata_locks`](performance-schema.html#metadata-locks-table)表公开了元数据锁信息，这对于查看哪些会话持有锁，被阻止等待锁等很有用。有关详细信息，请参见[第25.12.12.1节“ metadata_locks表”](performance-schema.html#metadata-locks-table)。

元数据锁定确实涉及一些开销，随着查询量的增加而增加。多个查询尝试访问相同对象的情况越多，元数据争用就会增加。

元数据锁定不能代替表定义高速缓存，并且其互斥锁和锁定与互斥锁不同 `LOCK_open`。以下讨论提供了有关元数据锁定如何工作的一些信息。

- [元数据锁定获取](optimization.html#metadata-lock-acquisition)
- [元数据锁定释放](optimization.html#metadata-lock-release)

#### 元数据锁定获取

如果给定锁有多个等待者，则首先满足最高优先级的锁请求，但[`max_write_lock_count`](server-administration.html#sysvar_max_write_lock_count)系统变量除外 。写锁定请求比读锁定请求具有更高的优先级。但是，如果 [`max_write_lock_count`](server-administration.html#sysvar_max_write_lock_count)将其设置为某个较低的值（例如10），则如果已将读取锁定请求传递给了10个写入锁定请求，则读取锁定请求可能比挂起的写入锁定请求优先。通常，不会发生此现象，因为 [`max_write_lock_count`](server-administration.html#sysvar_max_write_lock_count)默认情况下它的值非常大。

语句不是一个接一个地获取元数据锁，并在此过程中执行死锁检测。

DML语句通常以在语句中提到表的顺序获取锁。

DDL语句[`LOCK TABLES`](sql-statements.html#lock-tables)和其他类似的语句尝试通过按名称顺序获取对显式命名的表的锁来减少并发DDL语句之间可能出现的死锁。对于隐式使用的表（例如，具有外键关系的表也必须被锁定），可以以不同的顺序获取锁。

例如，[`RENAME TABLE`](sql-statements.html#rename-table)一条DDL语句按名称顺序获取锁：

- 该[`RENAME TABLE`](sql-statements.html#rename-table)语句重命名`tbla`为其他名称，并重命名`tblc`为 `tbla`：

  ```
  将表tbla重命名为tbld，tblc重命名为tbla；
  ```

  声明获取元数据锁，按顺序上 `tbla`，`tblc`和 `tbld`（因为`tbld` 如下`tblc`的名称顺序排列）：

- 这个稍有不同的语句还重命名 `tbla`为其他名称，并重命名 `tblc`为`tbla`：

  ```
  将表tbla重命名为tblb，将tblc重命名为tbla；
  ```

  在这种情况下，该语句获取元数据锁，按顺序上`tbla`， `tblb`和`tblc` （因为`tblb`先于 `tblc`在名称顺序排列）：

这两个语句都按`tbla`和`tblc`顺序获得对和 的锁定，但是在剩余表名上的锁定是在之前还是之后获得的都不同`tblc`。

如以下示例所示，当多个事务同时执行时，元数据锁获取顺序可能会在操作结果上产生差异。

与两个表开始`x`和 `x_new`具有相同的结构。三个客户发出涉及这些表的语句：

客户1：

```
锁表x写，x_new写;
```

该语句按名称顺序在`x`和上请求并获取写锁定`x_new`。

客户2：

```
插入x值（1）;
```

该语句请求并阻塞以等待写入锁定 `x`。

客户3：

```
将表x重命名为x_old，将x_new重命名为x；
```

声明要求独占锁在名字顺序 `x`，`x_new`和 `x_old`，而是块等待上了锁 `x`。

客户1：

```
解锁表；
```

该语句释放对`x` 和的写锁`x_new`。`x`客户端3 的排他锁定请求 比客户端2的写锁定请求具有更高的优先级，因此客户端3在上获取其锁定`x`，然后在`x_new` 和上获取其锁定`x_old`，执行重命名并释放其锁定。客户端2然后获取其锁定 `x`，执行插入操作，然后释放其锁定。

锁定获取顺序导致在 [`RENAME TABLE`](sql-statements.html#rename-table)之前执行[`INSERT`](sql-statements.html#insert)。在 `x`进入时发生的插入是被命名表`x_new`时，客户端2日插入，并更名为`x`通过客户端3：

```
mysql> SELECT * FROM x;
+ ------ +
| 我
+ ------ +
| 1 |
+ ------ +

mysql> SELECT * FROM x_old;
空置（0.01秒）
```

现在，而不是首先命名表`x`，并 `new_x`具有相同的结构。同样，三个客户发出涉及这些表的语句：

客户1：

```
锁表x写，new_x写;
```

该语句按名称顺序在`new_x`和上请求并获取写锁定`x`。

客户2：

```
插入x值（1）;
```

该语句请求并阻塞以等待写入锁定 `x`。

客户3：

```
将表x重命名为old_x，将new_x重命名为x；
```

声明要求独占锁在名字顺序 `new_x`，`old_x`和 `x`，而是块等待上了锁 `new_x`。

客户1：

```
解锁表；
```

该语句释放对`x` 和的写锁`new_x`。对于`x`，唯一未决的请求是客户端2发出的，因此客户端2获取其锁，执行插入操作，然后释放该锁。对于 `new_x`，唯一待处理的请求是由客户端3进行的，客户端3被允许获取该锁（以及上的锁`old_x`）。`x`在Client 2插入完成并释放其锁定之前，重命名操作仍会阻止锁定。然后，客户端3获取对的锁定`x`，执行重命名，然后释放其锁定。

在这种情况下，锁定获取顺序导致在 [`INSERT`](sql-statements.html#insert)之前执行 [`RENAME TABLE`](sql-statements.html#rename-table)。将 `x`其插入到时原来是`x`，现在更名为 `old_x`通过重命名操作：

```
mysql> SELECT * FROM x;
空置（0.01秒）

mysql> SELECT * FROM old_x;
+ ------ +
| 我
+ ------ +
| 1 |
+ ------ +
```

如前例所示，如果并发语句中的锁获取顺序对应用程序的操作结果有所不同，则可以调整表名以影响锁获取的顺序。

#### 元数据锁定释放

为确保事务可序列化，服务器不得允许一个会话对在另一会话中未完成的显式或隐式启动的事务中使用的表执行数据定义语言（DDL）语句。服务器通过获取事务中使用的表上的元数据锁并推迟释放这些锁直到事务结束来实现此目的。表上的元数据锁可防止更改表的结构。这种锁定方法的含义是，一个事务内的一个事务正在使用的表在事务结束之前，不能由其他会话在DDL语句中使用。

该原理不仅适用于事务表，而且适用于非事务表。假设会话开始使用事务表`t`和非事务表的 事务 `nt`，如下所示：

```
开始交易；
选择*从t;
SELECT * FROM nt;
```

服务器在两者上都持有元数据锁`t` ，`nt`直到事务结束。如果另一个会话在任一表上尝试DDL或写锁定操作，它将阻塞，直到在事务结束释放元数据锁定为止。例如，如果第二个会话尝试执行以下任何操作，则它将阻塞：

```
删除表t;
ALTER TABLE t ...;
DROP TABLE nt;
ALTER TABLE nt ...;
锁表t ...写;
```

相同的行为适用于 [`LOCK TABLES ... READ`](sql-statements.html#lock-tables)。即，更新该表（事务性或非事务性）的显式或隐式启动的事务将`LOCK TABLES ... READ`对该表进行阻塞和阻塞。

如果服务器获取语法上有效但在执行过程中失败的语句的元数据锁，则它不会尽早释放该锁。锁定释放仍然推迟到事务结束，因为失败的语句将被写入二进制日志，并且锁定可以保护日志的一致性。

在自动提交模式下，每个语句实际上是一个完整的事务，因此为该语句获取的元数据锁仅保留到该语句的末尾。

[`PREPARE`](sql-statements.html#prepare)准备好语句后，即使在多语句事务中进行准备，也会释放语句 期间获取的元数据锁 。

### 8.11.5外部锁定



外部锁定是使用文件系统锁定来管理[`MyISAM`](storage-engines.html#myisam-storage-engine)多个进程对数据库表的争用。在无法将单个进程（例如MySQL服务器）假定为唯一需要访问表的进程的情况下，可以使用外部锁定。这里有些例子：

- 如果运行使用相同数据库目录的多个服务器（不建议），则每个服务器必须启用了外部锁定。

- 如果使用[**myisamchk**](programs.html#myisamchk)对表执行表维护操作 [`MyISAM`](storage-engines.html#myisam-storage-engine)，则必须确保服务器未运行，或者服务器已启用外部锁定，以便根据需要锁定表文件以与[**myisamchk**](programs.html#myisamchk)协作以 访问表。使用[**myisampack**](programs.html#myisampack)打包 [`MyISAM`](storage-engines.html#myisam-storage-engine)表也是如此 。

  如果服务器在启用了外部锁定的情况下运行，则可以随时使用[**myisamchk**](programs.html#myisamchk)进行读取操作，例如检查表。在这种情况下，如果服务器尝试更新[**myisamchk**](programs.html#myisamchk)正在使用的表 ，则服务器将等待[**myisamchk**](programs.html#myisamchk)完成后才能继续。

  如果使用[**myisamchk**](programs.html#myisamchk)进行诸如修复或优化表之类的写操作，或者使用 [**myisampack**](programs.html#myisampack)打包表，则 *必须*始终确保 [**mysqld**](programs.html#mysqld)服务器未使用该表。如果不停止[**mysqld**](programs.html#mysqld)，至少 在运行[**myisamchk**](programs.html#myisamchk)之前先执行 [**mysqladmin flush-tables**](programs.html#mysqladmin)。如果服务器和[**myisamchk**](programs.html#myisamchk)同时访问表，则 表*可能已损坏*。

启用外部锁定后，每个需要访问表的进程都将在继续访问表之前获取表文件的文件系统锁。如果无法获取所有必需的锁，则会阻止该进程访问表，直到获得锁为止（在当前持有锁的进程释放它们之后）。

外部锁定会影响服务器性能，因为服务器有时必须等待其他进程才能访问表。

如果您运行一台服务器来访问给定的数据目录（通常是这种情况），并且在服务器运行时不需要其他程序（例如[**myisamchk）**](programs.html#myisamchk)来修改表，则不需要外部锁定。如果仅 使用其他程序*读取*表，则不需要外部锁定，尽管 如果[**myisamchk**](programs.html#myisamchk)在读取表时服务器更改表，[**myisamchk**](programs.html#myisamchk)可能会报告警告 。

禁用外部锁定后，要使用 [**myisamchk**](programs.html#myisamchk)，必须在[**myisamchk**](programs.html#myisamchk)执行时停止服务器，或者在运行[**myisamchk**](programs.html#myisamchk)之前锁定并刷新表。（请参见[第8.12.1节“系统因素”](optimization.html#system-optimization)。）为避免此要求，请使用[`CHECK TABLE`](sql-statements.html#check-table) and [`REPAIR TABLE`](sql-statements.html#repair-table)语句检查和修复[`MyISAM`](storage-engines.html#myisam-storage-engine)表。

对于[**mysqld**](programs.html#mysqld)，外部锁定由[`skip_external_locking`](server-administration.html#sysvar_skip_external_locking)系统变量的值控制 。启用此变量后，将禁用外部锁定，反之亦然。默认情况下，外部锁定是禁用的。

可以使用[`--external-locking`](server-administration.html#option_mysqld_external-locking)或 [`--skip-external-locking`](server-administration.html#option_mysqld_external-locking) 选项在服务器启动时控制外部锁定的使用。

如果确实使用外部锁定选项来启用[`MyISAM`](storage-engines.html#myisam-storage-engine)来自许多MySQL进程的表更新 ，则必须确保满足以下条件：

- 不要对使用由另一个进程更新的表的查询使用查询缓存。
- 不要将[`delay_key_write`](server-administration.html#sysvar_delay_key_write)系统变量设置为启动服务器，也不要 对任何共享表`ALL`使用 `DELAY_KEY_WRITE=1`table选项。否则，可能会发生索引损坏。

满足这些条件的最简单方法是始终 [`--external-locking`](server-administration.html#option_mysqld_external-locking)与[`--delay-key-write=OFF`](server-administration.html#sysvar_delay_key_write)和 一起使用 [`--query-cache-size=0`](server-administration.html#sysvar_query_cache_size)。（默认情况下不会执行此操作，因为在许多设置中，混合使用上述选项会很有用。）

## 8.12优化MySQL服务器

- [8.12.1系统因素](optimization.html#system-optimization)
- [8.12.2优化磁盘I / O](optimization.html#disk-issues)
- [8.12.3使用符号链接](optimization.html#symbolic-links)
- [8.12.4优化内存使用](optimization.html#optimizing-memory)

本节讨论数据库服务器的优化技术，主要是处理系统配置而不是调整SQL语句。本节中的信息适用于想要确保所管理服务器之间的性能和可伸缩性的DBA。为开发人员构建包括设置数据库在内的安装脚本；以及自己运行MySQL进行开发，测试等工作的人们，他们希望最大限度地提高自己的生产力。

### 8.12.1系统因素



一些系统级因素会在很大程度上影响性能：



- 如果有足够的RAM，则可以删除所有交换设备。在某些情况下，即使您有可用内存，某些操作系统也会使用交换设备。

- 避免对[`MyISAM`](storage-engines.html#myisam-storage-engine)表进行外部锁定 。默认设置是禁用外部锁定。在 [`--external-locking`](server-administration.html#option_mysqld_external-locking)和 [`--skip-external-locking`](server-administration.html#option_mysqld_external-locking) 选项明确地启用和禁用外部锁定。

  只要仅运行一台服务器，禁用外部锁定就不会影响MySQL的功能。只要记住在运行[**myisamchk**](programs.html#myisamchk)之前关闭服务器（或锁定并刷新相关表）即可 。在某些系统上，必须禁用外部锁定，因为无论如何它都无法工作。

  唯一不能禁用外部锁定的情况是 对同一数据运行多个MySQL *服务器*（而非客户端），或者如果运行 [**myisamchk**](programs.html#myisamchk)来检查（而非修复）表而不通知服务器先刷新并锁定表。请注意，通常*不* 建议使用多个MySQL服务器并发访问同一数据，除非使用NDB Cluster。

  该[`LOCK TABLES`](sql-statements.html#lock-tables)和 [`UNLOCK TABLES`](sql-statements.html#lock-tables)语句使用内部锁定，所以你可以使用他们，即使外部锁定被禁用。

### 8.12.2优化磁盘I / O



本节介绍了可以将更多，更快的存储硬件投入数据库服务器时配置存储设备的方法。有关优化 `InnoDB`配置以提高I / O性能的信息，请参见[第8.5.8节“优化InnoDB磁盘I / O”](optimization.html#optimizing-innodb-diskio)。

- 磁盘寻道是巨大的性能瓶颈。当数据量开始增长到无法进行有效缓存时，此问题将变得更加明显。对于大型数据库，您或多或少地随机访问数据，可以确保至少需要读取一个磁盘并要写一些磁盘。为了最小化此问题，请使用寻道时间短的磁盘。

- 通过将文件符号链接到不同的磁盘或分割磁盘来增加可用磁盘心轴的数量（从而减少查找开销）：

  - 使用符号链接

    这意味着对于`MyISAM`表，您将索引文件和数据文件从它们在数据目录中的通常位置符号链接到另一个磁盘（也可能是带区的）。假设磁盘也没有用于其他目的，这将使查找和读取时间都更好。请参见 [第8.12.3节“使用符号链接”](optimization.html#symbolic-links)。

    `InnoDB`表 不支持使用符号链接 。但是，可以将`InnoDB`数据和日志文件放置在不同的物理磁盘上。有关更多信息，请参见[第8.5.8节“优化InnoDB磁盘I / O”](optimization.html#optimizing-innodb-diskio)。

  - 分条

    条带化意味着您有很多磁盘，并将第一个块放在第一个磁盘上，第二个块放在第二个磁盘上，*`N`*第-个块放在（）磁盘上，依此类推。这意味着，如果正常数据大小小于条带大小（或完全对齐），则性能会好得多。条带化非常依赖于操作系统和条带大小，因此请使用不同的条带大小对应用程序进行基准测试。请参见[第8.13.2节“使用自己的基准”](optimization.html#custom-benchmarks)。 `*`N`* MOD *`number_of_disks`*`

    剥离的速度差异 *非常*取决于参数。根据设置条带化参数和磁盘数量的方式，可能会获得数量级上的差异。您必须选择针对随机或顺序访问进行优化。

- 为了提高可靠性，您可能需要使用RAID 0 + 1（条带化和镜像），但是在这种情况下，需要2个 *`N`*驱动器来保存 *`N`*数据驱动器。如果您有足够的钱，这可能是最好的选择。但是，您可能还必须投资购买一些卷管理软件才能有效地处理它。

- 一个不错的选择是根据数据类型的关键程度来改变RAID级别。例如，存储可以在RAID 0磁盘上重新生成的次要数据，但将真正重要的数据（例如主机信息和日志）存储在RAID 0 + 1或RAID *`N`*磁盘上。*`N`*如果您需要进行多次写入操作，则由于更新奇偶校验位所需的时间，RAID 可能会成为问题。

- 您还可以为数据库使用的文件系统设置参数：

  如果您不需要知道上次访问文件的时间（这在数据库服务器上实际上没有用），则可以使用该`-o noatime` 选项挂载文件系统。这样就跳过了对文件系统上inode的最后访问时间的更新，从而避免了某些磁盘搜索。

  在许多操作系统上，可以通过使用`-o async`选项安装文件系统来设置文件系统以进行异步更新。如果您的计算机相当稳定，这将在不降低可靠性的情况下为您提供更好的性能。（此标志在Linux上默认为打开。）

#### 在MySQL中使用NFS

在考虑是否将NFS与MySQL一起使用时，您应该谨慎。根据操作系统和NFS版本的不同，潜在的问题包括：

- 放置在NFS卷上的MySQL数据和日志文件被锁定，无法使用。例如，在多个MySQL实例访问同一数据目录或由于断电而导致MySQL不正确关闭的情况下，可能会发生锁定问题。NFS版本4通过引入咨询和基于租约的锁定解决了潜在的锁定问题。但是，不建议在MySQL实例之间共享数据目录。
- 由于接收到的消息混乱或网络流量丢失而导致数据不一致。为避免此问题，请使用TCP和`hard`和 `intr`mount选项。
- 最大文件大小限制。NFS版本2客户端只能访问文件的最低2GB（带符号的32位偏移量）。NFS版本3客户端支持更大的文件（最大64位偏移）。支持的最大文件大小还取决于NFS服务器的本地文件系统。

与在此类环境之外使用NFS相比，在专业的SAN环境或其他存储系统中使用NFS往往会提供更高的可靠性。但是，SAN环境中的NFS可能比直接连接或总线连接的非旋转存储要慢。

如果选择使用NFS，则建议使用NFS版本4或更高版本，以及在部署到生产环境之前彻底测试NFS设置。

### 8.12.3使用符号链接

- [8.12.3.1在Unix上对数据库使用符号链接](optimization.html#symbolic-links-to-databases)
- [8.12.3.2在Unix上对MyISAM表使用符号链接](optimization.html#symbolic-links-to-tables)
- [8.12.3.3在Windows上对数据库使用符号链接](optimization.html#windows-symbolic-links)



您可以将数据库或表从数据库目录移动到其他位置，并用指向新位置的符号链接替换它们。例如，您可能需要这样做，以便将数据库移动到具有更多可用空间的文件系统中，或者通过将表分散到不同的磁盘来提高系统的速度。

对于`InnoDB`表，请按照[第14.6.1.2节“](innodb-storage-engine.html#innodb-create-table-external)在[外部创建表”中的](innodb-storage-engine.html#innodb-create-table-external)说明，使用语句的`DATA DIRECTORY`子句[`CREATE TABLE`](sql-statements.html#create-table)代替符号链接。此新功能是受支持的跨平台技术。

推荐的方法是将整个数据库目录符号链接到另一个磁盘。Symlink `MyISAM`表仅是万不得已的方法。

要确定数据目录的位置，请使用以下语句：

```
显示变量，例如“ datadir”；
```

#### 8.12.3.1在Unix上对数据库使用符号链接



在Unix上，符号链接数据库的方法是首先在某个具有可用空间的磁盘上创建一个目录，然后从MySQL数据目录创建到该目录的软链接。

```
壳> mkdir /dr1/databases/test
壳>ln -s /dr1/databases/test /path/to/datadir
```

MySQL不支持将一个目录链接到多个数据库。只要您不在数据库之间建立符号链接，就可以使用符号链接替换数据库目录。假设您`db1`在MySQL数据目录下有一个数据库 ，然后建立一个`db2`指向以下内容 的符号链接`db1`：

```
壳> 
壳>cd /path/to/datadirln -s db1 db2
```

其结果是，对于任何表`tbl_a`中 `db1`，也似乎是一个表 `tbl_a`中`db2`。如果一个客户端更新，`db1.tbl_a`而另一个客户端更新`db2.tbl_a`，则可能会出现问题。

#### 8.12.3.2在Unix上对MyISAM表使用符号链接



仅针对`MyISAM`表完全支持符号链接 。对于表用于其他存储引擎的文件，如果尝试使用符号链接，则可能会遇到奇怪的问题。对于`InnoDB`表，请使用[第14.6.1.2节“](innodb-storage-engine.html#innodb-create-table-external)在[外部创建表”中](innodb-storage-engine.html#innodb-create-table-external)说明的替代技术 。

不要在没有完全操作`realpath()`调用的系统上符号链接表。（Linux和Solaris支持`realpath()`）。要确定您的系统是否支持符号链接，请[`have_symlink`](server-administration.html#sysvar_have_symlink)使用以下语句检查系统变量的值：

```
显示变量，例如'have_symlink';
```

`MyISAM` 表 的符号链接的处理方式如下：

- 在数据目录中，始终具有表格式（`.frm`）文件，数据（`.MYD`）文件和索引（`.MYI`）文件。数据文件和索引文件可以移动到其他位置，并在数据目录中用符号链接替换。格式文件不能。

- 您可以将数据文件和索引文件独立地符号链接到不同的目录。

- 要指示正在运行的MySQL服务器执行符号链接，请使用`DATA DIRECTORY`和 `INDEX DIRECTORY`选项 [`CREATE TABLE`](sql-statements.html#create-table)。请参见 [第13.1.18节“ CREATE TABLE语句”](sql-statements.html#create-table)。或者，如果 [**mysqld**](programs.html#mysqld)未运行，则可以 从命令行使用**ln -s**手动完成符号链接。

  注意

  与`DATA DIRECTORY`和`INDEX DIRECTORY`选项中的一个或两个一起使用的路径可能不包括MySQL `data`目录。错误32167）

- [**myisamchk**](programs.html#myisamchk)不会用数据文件或索引文件替换符号链接。它直接在符号链接指向的文件上工作。在数据文件或索引文件所在的目录中创建任何临时文件。同样是真实的 [`ALTER TABLE`](sql-statements.html#alter-table)， [`OPTIMIZE TABLE`](sql-statements.html#optimize-table)和 [`REPAIR TABLE`](sql-statements.html#repair-table)语句。

- 注意

  删除使用符号链接的表时，符号 *链接和符号链接指向的文件都会被删除*。这是一个非常好的理由*不*运行 [**mysqld的**](programs.html#mysqld)作为`root` 操作系统用户或允许操作系统的用户将不得不MySQL数据库目录的写入权限。

- 如果使用[`ALTER TABLE ... RENAME`](sql-statements.html#alter-table)或重命名表， [`RENAME TABLE`](sql-statements.html#rename-table)并且没有将表移至另一个数据库，则数据库目录中的符号链接将重命名为新名称，并且数据文件和索引文件也将相应重命名。

- 如果使用 [`ALTER TABLE ... RENAME`](sql-statements.html#alter-table)或[`RENAME TABLE`](sql-statements.html#rename-table)将表移动到另一个数据库，则该表将移动到另一个数据库目录。如果表名更改，则新数据库目录中的符号链接将重命名为新名称，并且数据文件和索引文件也将相应重命名。

- 如果不使用符号链接，则使用该 选项启动 [**mysqld**](programs.html#mysqld)， [`--skip-symbolic-links`](server-administration.html#option_mysqld_symbolic-links)以确保没有人可以使用 [**mysqld**](programs.html#mysqld)删除或重命名数据目录之外的文件。

不支持这些表符号链接操作：

- [`ALTER TABLE`](sql-statements.html#alter-table)忽略 `DATA DIRECTORY`和`INDEX DIRECTORY`表选项。

- 如前所述，只有数据和索引文件可以是符号链接。该`.frm`文件绝 *不能*是符号链接。尝试执行此操作（例如，使一个表名成为另一表名的同义词）会产生错误的结果。假设您`db1`在MySQL数据目录下有一个数据库，`tbl1`在该数据库中有一个表，并且在该`db1`目录中建立了一个`tbl2`指向以下内容 的符号链接`tbl1`：

  ```
  外壳> 
  外壳> 
  外壳> 
  外壳>cd /path/to/datadir/db1ln -s tbl1.frm tbl2.frmln -s tbl1.MYD tbl2.MYDln -s tbl1.MYI tbl2.MYI
  ```

  如果一个线程读取`db1.tbl1`而另一个线程更新， 则会导致问题 `db1.tbl2`：

  - 查询缓存是“ 愚弄的 ”（无法得知`tbl1`尚未更新，因此它会返回过时的结果）。
  - `ALTER``tbl2`失败声明 。

#### 8.12.3.3在Windows上对数据库使用符号链接



在Windows上，符号链接可用于数据库目录。这样，您可以通过建立数据库目录的符号链接来将其放置在其他位置（例如，在其他磁盘上）。在Windows上使用数据库符号链接与在Unix上使用数据库符号链接类似，尽管建立链接的过程不同。

假设你要放置数据库目录中指定的数据库`mydb`的 `D:\data\mydb`。为此，请在MySQL数据目录中创建一个指向的符号链接 `D:\data\mydb`。但是，在创建符号链接之前`D:\data\mydb`，请根据需要通过创建目录来确保该 目录存在。如果您已经有一个`mydb`在数据目录中命名的数据库目录，请将其移至`D:\data`。否则，符号链接将无效。为避免出现问题，请在移动数据库目录时确保服务器未在运行。

在Windows上，可以使用**mklink**命令创建符号链接 。此命令需要管理特权。

1. 将位置更改为数据目录：

   ```
   C：\> cd \path\to\datadir
   ```

2. 在数据目录中，创建一个名为的符号链接，该链接 `mydb`指向数据库目录的位置：

   ```
   C：\> mklink /d mydb D:\data\mydb
   ```

之后，将在`mydb`中创建数据库中创建的 所有表 `D:\data\mydb`。

### 8.12.4优化内存使用

- [8.12.4.1 MySQL如何使用内存](optimization.html#memory-use)
- [8.12.4.2启用大页面支持](optimization.html#large-page-support)



#### 8.12.4.1 MySQL如何使用内存



MySQL分配缓冲区和高速缓存以提高数据库操作的性能。默认配置旨在允许MySQL服务器在具有大约512MB RAM的虚拟机上启动。您可以通过增加某些与缓存和缓冲区相关的系统变量的值来提高MySQL性能。您还可以修改默认配置，以在内存有限的系统上运行MySQL。

下表描述了MySQL使用内存的一些方式。如果适用，将引用相关的系统变量。有些项目是存储引擎或特定于功能的。

- 所述`InnoDB`缓冲器池是保持高速缓存的存储区域`InnoDB`表，索引，及其它辅助缓冲器中的数据。为了提高大容量读取操作的效率，缓冲池被分为多个[页面](glossary.html#glos_page) ，这些[页面](glossary.html#glos_page)可能包含多个行。为了提高缓存管理的效率，缓冲池被实现为页面的链接列表。使用[LRU](glossary.html#glos_lru)算法的变体，将很少使用的数据从缓存中老化掉 。有关更多信息，请参见[第14.5.1节“缓冲池”](innodb-storage-engine.html#innodb-buffer-pool)。

  缓冲池的大小对于系统性能很重要：

  - `InnoDB`使用`malloc()`操作在服务器启动时为整个缓冲池分配内存 。所述 [`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size) 系统变量定义缓冲池大小。通常，建议 [`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size) 值为系统内存的50％到75％。 [`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size) 服务器运行时可以动态配置。有关更多信息，请参见 [第14.8.3.1节“配置InnoDB缓冲池大小”](innodb-storage-engine.html#innodb-buffer-pool-resize)。
  - 在具有大量内存的系统上，可以通过将缓冲池划分为多个[缓冲池实例](glossary.html#glos_buffer_pool_instance)来提高并发性 。所述 [`innodb_buffer_pool_instances`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_instances) 系统变量定义缓冲池实例的数量。
  - 太小的缓冲池可能会导致过度搅动，因为从缓冲池中刷新页面只是在短时间内再次需要。
  - 缓冲池太大可能会由于争用内存而导致交换。

- 所有线程共享[`MyISAM`](storage-engines.html#myisam-storage-engine) 密钥缓冲区。的 [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size)系统变量决定其大小。

  对于`MyISAM`服务器打开的每个表，索引文件都会打开一次；对于访问该表的每个并发运行的线程，数据文件都会打开一次。对于每个并发线程，分配一个表结构，每个列的列结构以及大小的缓冲区 （其中最大行长，不计 列）。一 列需要五到八个字节加上数据的长度 。该 存储引擎维护用于内部使用一个额外的行缓冲。 `3 * *`N`*`*`N`*[`BLOB`](data-types.html#blob)[`BLOB`](data-types.html#blob)[`BLOB`](data-types.html#blob)`MyISAM`

- 所述[`myisam_use_mmap`](server-administration.html#sysvar_myisam_use_mmap) 系统变量可以被设置为1，使能对所有内存映射`MyISAM`表。

- 如果内部内存临时表太大（使用[`tmp_table_size`](server-administration.html#sysvar_tmp_table_size)和 [`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size) 系统变量确定 ），MySQL会自动将表从内存格式转换为磁盘格式。磁盘临时表使用[`internal_tmp_disk_storage_engine`](server-administration.html#sysvar_internal_tmp_disk_storage_engine) 系统变量定义的存储引擎 。您可以按照[第8.4.4节“ MySQL中的内部临时表使用”中](optimization.html#internal-temporary-tables)所述增加允许的临时表大小 。

  对于使用[`MEMORY`](storage-engines.html#memory-storage-engine)显式创建的表[`CREATE TABLE`](sql-statements.html#create-table)，只有 [`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size) 系统变量确定表可以增长到多大，并且不转换为磁盘格式。

- 在[MySQL性能模式](performance-schema.html)是在低级别监控MySQL服务器执行的功能。性能架构动态地增量分配内存，将其内存使用量扩展到实际服务器负载，而不是在服务器启动期间分配所需的内存。一旦分配了内存，就不会释放它，除非重新启动服务器。有关更多信息，请参见 [第25.17节“性能模式内存分配模型”](performance-schema.html#performance-schema-memory-model)。

- 服务器用来管理客户端连接的每个线程都需要一些特定于线程的空间。下表列出了这些内容以及哪些系统变量控制它们的大小：

  - 堆栈（[`thread_stack`](server-administration.html#sysvar_thread_stack)）
  - 连接缓冲区（[`net_buffer_length`](server-administration.html#sysvar_net_buffer_length)）
  - 结果缓冲区（[`net_buffer_length`](server-administration.html#sysvar_net_buffer_length)）

  连接缓冲区和结果缓冲区[`net_buffer_length`](server-administration.html#sysvar_net_buffer_length)均以等于字节的大小开头 ，但[`max_allowed_packet`](server-administration.html#sysvar_max_allowed_packet)根据需要动态扩展到 字节。[`net_buffer_length`](server-administration.html#sysvar_net_buffer_length)在每个SQL语句之后，结果缓冲区缩小为 字节。在运行语句时，还会分配当前语句字符串的副本。

  每个连接线程都使用内存来计算语句摘要。服务器[`max_digest_length`](server-administration.html#sysvar_max_digest_length)为每个会话分配 字节。请参见 [第25.10节“性能模式语句摘要”](performance-schema.html#performance-schema-statement-digests)。

- 所有线程共享相同的基本内存。

- 当不再需要线程时，分配给它的内存将释放并返回系统，除非该线程返回线程高速缓存。在这种情况下，内存将保持分配状态。

- 每个执行表顺序扫描的请求都分配一个读取缓冲区。的 [`read_buffer_size`](server-administration.html#sysvar_read_buffer_size)系统变量决定缓冲器大小。

- 当以任意顺序（例如，按照排序）读取行时， 可以分配一个 随机读取的缓冲区以避免磁盘查找。的 [`read_rnd_buffer_size`](server-administration.html#sysvar_read_rnd_buffer_size) 系统变量决定缓冲器大小。

- 所有联接都在一次通过中执行，并且大多数联接甚至都可以使用临时表来完成。大多数临时表是基于内存的哈希表。具有较大行长度（按所有列长度的总和计算）或包含[`BLOB`](data-types.html#blob)列的临时表 存储在磁盘上。

- 大多数执行排序的请求都根据结果集的大小分配一个排序缓冲区和两个临时文件零。请参见[第B.4.3.5节“ MySQL在哪里存储临时文件”](error-handling.html#temporary-files)。

- 几乎所有的解析和计算都是在线程本地的和可重用的内存池中完成的。小项目不需要内存开销，从而避免了正常的慢速内存分配和释放。内存仅分配给意外大的字符串。

- 对于具有[`BLOB`](data-types.html#blob) 列的每个表，将动态扩大缓冲区以读取更大的[`BLOB`](data-types.html#blob)值。如果您扫描表，则缓冲区将增大到 [`BLOB`](data-types.html#blob)最大值。

- MySQL需要用于表缓存的内存和描述符。所有使用中的表的处理程序结构都保存在表缓存中，并作为“ 先进先出 ”（FIFO）管理。所述 [`table_open_cache`](server-administration.html#sysvar_table_open_cache)系统变量定义初始表高速缓存大小; 请参见 [第8.4.3.1节“ MySQL如何打开和关闭表”](optimization.html#table-cache)。

  MySQL还需要用于表定义缓存的内存。所述 [`table_definition_cache`](server-administration.html#sysvar_table_definition_cache) 系统变量定义的表定义（距离的数量`.frm`可以存储在表中定义的高速缓存文件）。如果使用大量表，则可以创建大表定义缓存以加快表的打开速度。与表高速缓存不同，表定义高速缓存占用的空间更少，并且不使用文件描述符。

- 一条[`FLUSH TABLES`](sql-statements.html#flush-tables)语句或 [**mysqladmin flush-tables**](programs.html#mysqladmin)命令立即关闭所有未使用的表，并在当前执行的线程结束时将所有正在使用的表标记为关闭。这样可以有效释放大多数使用中的内存。[`FLUSH TABLES`](sql-statements.html#flush-tables)在关闭所有表之前不会返回。

- 服务器在内存中缓存信息的结果 [`GRANT`](sql-statements.html#grant)， [`CREATE USER`](sql-statements.html#create-user)， [`CREATE SERVER`](sql-statements.html#create-server)，和 [`INSTALL PLUGIN`](sql-statements.html#install-plugin)语句。该内存不能由相应的释放 [`REVOKE`](sql-statements.html#revoke)， [`DROP USER`](sql-statements.html#drop-user)， [`DROP SERVER`](sql-statements.html#drop-server)，和 [`UNINSTALL PLUGIN`](sql-statements.html#uninstall-plugin) 语句，所以执行该语句的许多情况下，这导致高速缓存，会出现在缓存内存使用的增加，除非它与释放服务器 [`FLUSH PRIVILEGES`](sql-statements.html#flush-privileges)。

**ps**和其他系统状态程序可能会报告[ **mysqld**](programs.html#mysqld)使用了大量内存。这可能是由于不同内存地址上的线程堆栈引起的。例如，Solaris的 **ps**版本将 堆栈之间未使用的内存计为已用内存。要验证这一点，请通过检查可用的交换 `swap -s`。我们 使用多个内存泄漏检测器（商用和开放源代码）测试[ **mysqld**](programs.html#mysqld)，因此应该没有内存泄漏。

##### 监视MySQL内存使用情况



以下示例演示了如何使用 [性能模式](performance-schema.html) 和[sys模式](sys-schema.html)来监视MySQL内存使用情况。

默认情况下，大多数性能架构内存检测是禁用的。可以通过更新`ENABLED`“性能模式” [`setup_instruments`](performance-schema.html#setup-instruments-table)表的列 来启用仪器 。内存仪器的名称形式为 ，其中为诸如或的值，并且 为仪器详细信息。 `memory/*`code_area`*/*`instrument_name`*`*`code_area`*`sql``innodb`*`instrument_name`*

1. 要查看可用的MySQL内存工具，请查询Performance Schema [`setup_instruments`](performance-schema.html#setup-instruments-table)表。以下查询返回所有代码区域的数百种内存工具。

   ```
   mysql> SELECT * FROM performance_schema.setup_instruments
          WHERE NAME LIKE '%memory%';
   ```

   您可以通过指定代码区域来缩小结果范围。例如，您可以`InnoDB`通过指定`innodb`为代码区域来将结果限制为 内存仪器。

   ```
   mysql> SELECT * FROM performance_schema.setup_instruments
          WHERE NAME LIKE '%memory/innodb%';
   + ------------------------------------------- + ----- ---- + ------- +
   | NAME | 已启用| 定时|
   + ------------------------------------------- + ----- ---- + ------- +
   | 内存/ innodb /自适应哈希索引| 否| 否|
   | 内存/ innodb / buf_buf_pool | 否| 否|
   | 内存/ innodb / dict_stats_bg_recalc_pool_t | 否| 否|
   | 内存/ innodb / dict_stats_index_map_t | 否| 否|
   | 内存/ innodb / dict_stats_n_diff_on_level | 否| 否|
   | 内存/ innodb /其他| 否| 否|
   | 内存/ innodb / row_log_buf | 否| 否|
   | 内存/ innodb / row_merge_sort | 否| 否|
   | 内存/ innodb / std | 否| 否|
   | 内存/ innodb / trx_sys_t :: rw_trx_ids | 否| 否|
   ...
   ```

   根据您的MySQL安装代码区域可能包括`performance_schema`， `sql`，`client`， `innodb`，`myisam`， `csv`，`memory`， `blackhole`， `archive`， `partition`，和其他人。

2. 要启用内存工具，请`performance-schema-instrument`在您的MySQL配置文件中添加一条 规则。例如，要启用所有内存工具，请将此规则添加到您的配置文件中，然后重新启动服务器：

   ```
   performance-schema-instrument ='内存/％=已计数'
   ```

   注意

   启动时启用内存工具可确保计算启动时发生的内存分配。

   重新启动服务器后，`ENABLED`性能架构[`setup_instruments`](performance-schema.html#setup-instruments-table) 表的 列应报告`YES`已启用的内存工具。对于内存工具`TIMED`，[`setup_instruments`](performance-schema.html#setup-instruments-table)表中的 列将 被忽略，因为内存操作未计时。

   ```
   mysql> SELECT * FROM performance_schema.setup_instruments
          WHERE NAME LIKE '%memory/innodb%';
   + ------------------------------------------- + ----- ---- + ------- +
   | NAME | 已启用| 定时|
   + ------------------------------------------- + ----- ---- + ------- +
   | 内存/ innodb /自适应哈希索引| 否| 否|
   | 内存/ innodb / buf_buf_pool | 否| 否|
   | 内存/ innodb / dict_stats_bg_recalc_pool_t | 否| 否|
   | 内存/ innodb / dict_stats_index_map_t | 否| 否|
   | 内存/ innodb / dict_stats_n_diff_on_level | 否| 否|
   | 内存/ innodb /其他| 否| 否|
   | 内存/ innodb / row_log_buf | 否| 否|
   | 内存/ innodb / row_merge_sort | 否| 否|
   | 内存/ innodb / std | 否| 否|
   | 内存/ innodb / trx_sys_t :: rw_trx_ids | 否| 否|
   ...
   ```

3. 查询存储仪器数据。在此示例中，在Performance Schema [`memory_summary_global_by_event_name`](performance-schema.html#memory-summary-tables) 表中查询存储仪器数据，该 表通过汇总数据 `EVENT_NAME`。该 `EVENT_NAME`是仪器的名称。

   以下查询返回`InnoDB`缓冲池的内存数据 。有关列的描述，请参见 [第25.12.15.9节“内存摘要表”](performance-schema.html#memory-summary-tables)。

   ```
   mysql> SELECT * FROM performance_schema.memory_summary_global_by_event_name
          WHERE EVENT_NAME LIKE 'memory/innodb/buf_buf_pool'\G
                     EVENT_NAME：内存/ innodb / buf_buf_pool
                    COUNT_ALLOC：1
                     COUNT_FREE：0
      SUM_NUMBER_OF_BYTES_ALLOC：137428992
       SUM_NUMBER_OF_BYTES_FREE：0
                 LOW_COUNT_USED：0
             CURRENT_COUNT_USED：1
                HIGH_COUNT_USED：1
       LOW_NUMBER_OF_BYTES_USED：0
   CURRENT_NUMBER_OF_BYTES_USED：137428992
      HIGH_NUMBER_OF_BYTES_USED：137428992
   ```

   可以使用[`sys`](sys-schema.html)架构 [`memory_global_by_current_bytes`](sys-schema.html#sys-memory-global-by-current-bytes) 表查询相同的基础数据 ，该表显示了服务器中全局的当前内存使用情况，并按分配类型进行了细分。

   ```
   mysql> SELECT * FROM sys.memory_global_by_current_bytes
          WHERE event_name LIKE 'memory/innodb/buf_buf_pool'\G
   *************************** 1.行******************** *******
          event_name：内存/ innodb / buf_buf_pool
       current_count：1
       current_alloc：131.06 MiB
   current_avg_alloc：131.06 MiB
          高计数：1
          high_alloc：131.06 MiB
      high_avg_alloc：131.06 MiB
   ```

   此[`sys`](sys-schema.html)架构查询`current_alloc`按代码区域汇总当前分配的内存（）：

   ```
   mysql> SELECT SUBSTRING_INDEX(event_name,'/',2) AS
          code_area, sys.format_bytes(SUM(current_alloc))
          AS current_alloc
          FROM sys.x$memory_global_by_current_bytes
          GROUP BY SUBSTRING_INDEX(event_name,'/',2)
          ORDER BY SUM(current_alloc) DESC;
   + --------------------------- + --------------- +
   | code_area | current_alloc |
   + --------------------------- + --------------- +
   | 内存/ innodb | 843.24 MiB |
   | 内存/性能模式| 81.29 MiB |
   | 内存/ mysys | 8.20 MiB |
   | 内存/ SQL | 2.47 MiB |
   | 内存/内存| 174.01 KiB |
   | 记忆/ myisam | 46.53 KiB |
   | 内存/黑洞| 512字节|
   | 内存/联合| 512字节|
   | 内存/ csv | 512字节|
   | 内存/ vio | 496字节|
   + --------------------------- + --------------- +
   ```

   有关[`sys`](sys-schema.html)模式的更多信息 ，请参见 [第26章，*MySQL sys模式*](sys-schema.html)。

#### 8.12.4.2启用大页面支持



某些硬件/操作系统体系结构支持的内存页大于默认值（通常为4KB）。此支持的实际实现取决于底层硬件和操作系统。由于减少了转换后备缓冲区（TLB）丢失，因此执行大量内存访问的应用程序可以通过使用大页面来提高性能。

在MySQL中，InnoDB可以使用大页为其缓冲池和其他内存池分配内存。

MySQL中大页面的标准用法尝试使用支持的最大大小，最大4MB。在Solaris下， “ 超大页面 ”功能允许使用最大256MB的页面。此功能可用于最新的SPARC平台。可以使用[`--super-large-pages`](server-administration.html#option_mysqld_super-large-pages)或 [`--skip-super-large-pages`](server-administration.html#option_mysqld_super-large-pages) 选项启用或禁用它 。

MySQL还支持大页面支持的Linux实现（在Linux中称为HugeTLB）。

在Linux上使用大页面之前，必须启用内核以支持大页面，并且有必要配置HugeTLB内存池。作为参考，HugeTBL API记录在 `Documentation/vm/hugetlbpage.txt`您的Linux源文件中。

默认情况下，某些最新系统（例如Red Hat Enterprise Linux）的内核似乎启用了大页面功能。要检查您的内核是否正确，请使用以下命令并查找包含“ huge ”的输出行 ：

```
外壳> cat /proc/meminfo | grep -i huge
HugePages_Total：0
HugePages_Free：0
HugePages_Rsvd：0
HugePages_Surp：0
大页面大小：4096 kB
```

nonempty命令输出表明存在大页面支持，但零值表明未配置任何页面供使用。

如果需要将内核重新配置为支持大页面，请查阅该`hugetlbpage.txt`文件以获取指示。

假设您的Linux内核启用了大页面支持，请使用以下命令将其配置为供MySQL使用。通常，将它们放在`rc`系统引导序列中执行的 文件或等效的启动文件中，以便命令在每次系统启动时执行。这些命令应在MySQL服务器启动之前的引导顺序中早执行。确保根据您的系统更改分配号和组号。

```
＃设置要使用的页数。
＃每个页面通常为2MB，因此值为20 = 40MB。
＃该命令实际上分配了内存，因此
＃内存必须可用。
回声20> / proc / sys / vm / nr_hugepages

＃设置允许访问的组号
＃内存（在这种情况下为102）。mysql用户必须是
＃个群组成员。
回声102> / proc / sys / vm / hugetlb_shm_group

＃增加每个段允许的shmem数量
＃（在这种情况下为12G）。
回声1560281088> / proc / sys / kernel / shmmax

＃增加共享内存总量。价值
＃是页数。在4KB /页时，4194304 = 16GB。
回声4194304> / proc / sys / kernel / shmall
```

对于MySQL使用，您通常希望的值 `shmmax`接近的值 `shmall`。

要验证大页面配置，请`/proc/meminfo`按照前面所述再次检查 。现在您应该看到一些非零值：

```
外壳> cat /proc/meminfo | grep -i huge
HugePages_Total：20
HugePages_Free：20
HugePages_Rsvd：0
HugePages_Surp：0
大页面大小：4096 kB
```

使用的最后一步 `hugetlb_shm_group`是为 `mysql`用户提供一个“ 无限 ” 的门锁限制值。可以通过编辑`/etc/security/limits.conf`或将以下命令添加到 [**mysqld_safe**](programs.html#mysqld-safe)脚本中来完成：

```
ulimit -l无限
```

在[**mysqld_safe中**](programs.html#mysqld-safe)添加**ulimit**命令 会导致 用户在切换到用户之前 将memlock限制设置为 。（这假定 [**mysqld_safe**](programs.html#mysqld-safe)由启动 。） `root``unlimited``mysql``root`

默认情况下，MySQL中的大页面支持处于禁用状态。要启用它，请使用[`--large-pages`](server-administration.html#option_mysqld_large-pages)选项启动服务器 。例如，您可以在服务器`my.cnf`文件中使用以下几行 ：

```
[mysqld]
大页面
```

使用此选项，`InnoDB`自动将大页用于其缓冲池和其他内存池。如果`InnoDB`不能执行此操作，则退回到传统内存的使用，并向错误日志写入警告：警告：使用常规内存池

要验证是否使用了大页面，请`/proc/meminfo`再次检查 ：

```
外壳> cat /proc/meminfo | grep -i huge
HugePages_Total：20
HugePages_Free：20
HugePages_Rsvd：2
HugePages_Surp：0
大页面大小：4096 kB
```

## 8.13评估效果（基准测试）

- [8.13.1测量表达式和函数的速度](optimization.html#select-benchmarking)
- [8.13.2使用自己的基准](optimization.html#custom-benchmarks)
- [8.13.3使用performance_schema衡量绩效](optimization.html#monitoring-performance-schema)



要衡量性能，请考虑以下因素：

- 无论您是在安静的系统上测量单个操作的速度，还是一段时间内一组操作（ “ 工作负载 ”）如何工作。通过简单的测试，您通常可以测试更改一个方面（配置设置，表上的索引集，查询中的SQL子句）如何影响性能。基准测试通常是长期运行且精心设计的性能测试，其结果可能会决定高级选择，例如硬件和存储配置，或升级到新MySQL版本的时间。
- 为了进行基准测试，有时您必须模拟繁重的数据库工作量才能获得准确的图像。
- 表现可能取决于许多不同的因素，以至于几个百分点的差异可能不是决定性的胜利。在不同的环境中进行测试时，结果可能会以相反的方式发生变化。
- 某些MySQL功能会根据工作负载来帮助或不帮助性能。为了完整起见，请始终在打开和关闭这些功能的情况下测试性能。尝试与每个工作负载的两个最重要的功能是 [MySQL查询缓存](optimization.html#query-cache)，以及 [适应性的散列索引](innodb-storage-engine.html#innodb-adaptive-hash)的`InnoDB`表。

本节从单个开发人员可以执行的简单直接的测量技术发展到需要更多专业知识来执行和解释结果的更复杂的技术。

### 8.13.1测量表达式和函数的速度

要测量特定MySQL表达式或函数的速度，请[`BENCHMARK()`](functions.html#function_benchmark)使用[**mysql**](programs.html#mysql)客户端程序调用该函数。其语法为 。返回值始终为零，但是[**mysql**](programs.html#mysql) 打印一行，显示大约执行该语句所花的时间。例如： [`BENCHMARK(*`loop_count`*,*`expr`*)`](functions.html#function_benchmark)

```
mysql> SELECT BENCHMARK(1000000,1+1);
+ ------------------------ +
| 基准（1000000,1 + 1）|
+ ------------------------ +
| 0 |
+ ------------------------ +
设置1行（0.32秒）
```

该结果是在奔腾II 400MHz系统上获得的。它表明MySQL可以在该系统上在0.32秒内执行1,000,000个简单加法表达式。

内置的MySQL函数通常经过高度优化，但是可能会有一些例外。 [`BENCHMARK()`](functions.html#function_benchmark)是一个很好的工具，用于确定某些功能是否对您的查询有问题。

### 8.13.2使用自己的基准



对您的应用程序和数据库进行基准测试，以找出瓶颈所在。在修复了一个瓶颈之后（或通过用“ 虚拟 ”模块替换它），您可以继续确定下一个瓶颈。即使您的应用程序的总体性能目前可以接受，您至少也应该为每个瓶颈制定一个计划，并决定如果有一天您确实需要额外的性能，该如何解决。

免费的基准测试套件是开放源代码数据库基准测试，可从[http://osdb.sourceforge.net/获得](http://osdb.sourceforge.net/)。

仅当系统负载很重时才发生问题是很常见的。我们有许多客户在生产中（经过测试）系统并遇到负载问题时与我们联系。在大多数情况下，性能问题可能是由于基本数据库设计问题（例如，表扫描在高负载下效果不佳）或操作系统或库问题引起的。在大多数情况下，如果系统尚未投入生产，这些问题将更容易解决。

为避免此类问题，请在可能的最坏负载下对整个应用程序进行基准测试：

- 该[**mysqlslap**](programs.html#mysqlslap)程序可以是用于模拟由多个客户端同时发出查询产生的高负载有帮助的。请参见[第4.5.8节“ **mysqlslap-**负载仿真客户端”](programs.html#mysqlslap)。
- 您还可以尝试通过https://launchpad.net/sysbench和 http://osdldbt.sourceforge.net/#dbt2获得的SysBench和DBT2等基准测试包 。

这些程序或软件包可以使系统崩溃，因此请确保仅在开发系统上使用它们。

### 8.13.3使用performance_schema衡量绩效



您可以查询`performance_schema`数据库中的表 以查看有关服务器及其正在运行的应用程序的性能特征的实时信息。参见 [第25章，](performance-schema.html)详细信息[ *MySQL性能模式*](performance-schema.html)。

## 8.14检查线程信息

- [8.14.1线程命令值](optimization.html#thread-commands)
- [8.14.2通用线程状态](optimization.html#general-thread-states)
- [8.14.3查询缓存线程状态](optimization.html#query-cache-thread-states)
- [8.14.4复制主线程状态](optimization.html#master-thread-states)
- [8.14.5复制从属I / O线程状态](optimization.html#slave-io-thread-states)
- [8.14.6复制从SQL线程状态](optimization.html#slave-sql-thread-states)
- [8.14.7复制从设备连接线程状态](optimization.html#slave-connection-thread-states)
- [8.14.8 NDB群集线程状态](optimization.html#mysql-cluster-thread-states)
- [8.14.9事件调度程序线程状态](optimization.html#event-scheduler-thread-states)



当您尝试确定MySQL服务器正在做什么时，检查进程列表（这是服务器中当前正在执行的线程集）可能会有所帮助。可以从以下来源获得过程列表信息：

- 该`SHOW [FULL] PROCESSLIST`语句： [第13.7.5.29节“ SHOW PROCESSLIST语句”](sql-statements.html#show-processlist)
- 该[`SHOW PROFILE`](sql-statements.html#show-profile)语句： [第13.7.5.31节“ SHOW PROFILES语句”](sql-statements.html#show-profiles)
- 该`INFORMATION_SCHEMA` [`PROCESSLIST`](information-schema.html#processlist-table)表： [第24.18，“该INFORMATION_SCHEMA PROCESSLIST表”](information-schema.html#processlist-table)
- 在[**中mysqladmin processlist的**](programs.html#mysqladmin)命令： [第4.5.2节“ **中mysqladmin** -客户端管理MySQL服务器”](programs.html#mysqladmin)
- 性能架构[`threads`](performance-schema.html#threads-table) 表，阶段表和锁定表： [第25.12.16节“性能架构杂项表”](performance-schema.html#performance-schema-miscellaneous-tables)， [第25.12.5节“性能架构阶段事件表”](performance-schema.html#performance-schema-stage-tables)， [第25.12.12节“性能架构锁定表”](performance-schema.html#performance-schema-lock-tables)。
- 该`sys`架构 [`processlist`](sys-schema.html#sys-processlist)视图，呈现从性能模式的信息 [`threads`](performance-schema.html#threads-table)在更方便的格式表：[第26.4.3.22，“ProcessList中和X $ PROCESSLIST意见”](sys-schema.html#sys-processlist)
- 该`sys`模式 [`session`](sys-schema.html#sys-session)认为，有关用户会话呈现信息（如 `sys`架构 [`processlist`](sys-schema.html#sys-processlist)视图，但后台进程滤除）： [第26.4.3.33，“会议和X $会话视图”](sys-schema.html#sys-session)

访问[`threads`](performance-schema.html#threads-table)不需要互斥，并且对服务器性能的影响最小。 [`INFORMATION_SCHEMA.PROCESSLIST`](information-schema.html#processlist-table)并且[`SHOW PROCESSLIST`](sql-statements.html#show-processlist)由于它们需要互斥量而 对性能造成负面影响。 [`threads`](performance-schema.html#threads-table)还显示有关后台线程，哪些信息 [`INFORMATION_SCHEMA.PROCESSLIST`](information-schema.html#processlist-table)和 [`SHOW PROCESSLIST`](sql-statements.html#show-processlist)没有。这意味着[`threads`](performance-schema.html#threads-table)可以用来监视其他线程信息源无法监视的活动。

您始终可以查看有关自己线程的信息。要查看有关其他帐户正在执行的线程的信息，您必须具有[`PROCESS`](security.html#priv_process)特权。

每个进程列表条目包含几条信息：

- `Id` 是与线程关联的客户端的连接标识符。

- `User`并`Host`指出与该线程关联的帐户。

- `db`是该线程的默认数据库，或者`NULL`如果未选择任何数据库。

- `Command`并`State` 指出线程在做什么。

  大多数状态对应于非常快速的操作。如果线程在给定状态下停留数秒钟，则可能存在需要调查的问题。

- `Time`指示线程处于其当前状态的时间。在某些情况下，线程的当前时间概念可能会更改：线程可以使用更改时间。对于在从属服务器上运行的，正在处理来自主服务器的事件的线程，线程时间设置为事件中找到的时间，从而反映了主服务器而非从属服务器上的当前时间。 [`SET TIMESTAMP = *`value`*`](sql-statements.html#set-variable)

- `Info`包含线程正在执行的语句的文本，或者`NULL`不执行的文本。默认情况下，此值仅包含语句的前100个字符。要查看完整的语句，请使用 [`SHOW FULL PROCESSLIST`](sql-statements.html#show-processlist)。

以下各节列出了可能的 `Command`值以及`State` 按类别分组的值。其中一些值的含义不言而喻。对于其他人，提供了附加描述。

### 8.14.1线程命令值



线程可以具有以下任何 `Command`值：

- `Binlog Dump`

  这是主服务器上的线程，用于将二进制日志内容发送到从服务器。

- `Change user`

  线程正在执行更改用户操作。

- `Close stmt`

  线程正在关闭准备好的语句。

- `Connect`

  复制从属服务器已连接到其主服务器。

- `Connect Out`

  复制从属服务器正在连接到其主服务器。

- `Create DB`

  线程正在执行创建数据库操作。

- `Daemon`

  该线程在服务器内部，而不是为客户端连接提供服务的线程。

- `Debug`

  该线程正在生成调试信息。

- `Delayed insert`

  该线程是延迟插入处理程序。

- `Drop DB`

  线程正在执行放置数据库操作。

- `Error`

- `Execute`

  线程正在执行准备好的语句。

- `Fetch`

  线程正在从执行准备好的语句中获取结果。

- `Field List`

  该线程正在检索表列的信息。

- `Init DB`

  线程正在选择默认数据库。

- `Kill`

  该线程正在杀死另一个线程。

- `Long Data`

  执行准备好的语句的结果是线程正在检索长数据。

- `Ping`

  线程正在处理服务器ping请求。

- `Prepare`

  该线程正在准备一个准备好的语句。

- `Processlist`

  该线程正在生成有关服务器线程的信息。

- `Query`

  线程正在执行一条语句。

- `Quit`

  线程正在终止。

- `Refresh`

  该线程是刷新表，日志或缓存，或者重置状态变量或复制服务器信息。

- `Register Slave`

  线程正在注册从属服务器。

- `Reset stmt`

  线程正在重置准备好的语句。

- `Set option`

  该线程正在设置或重置客户端语句执行选项。

- `Shutdown`

  线程正在关闭服务器。

- `Sleep`

  线程正在等待客户端向其发送新语句。

- `Statistics`

  该线程正在生成服务器状态信息。

- `Table Dump`

  线程正在将表内容发送到从属服务器。

- `Time`

  没用过。

### 8.14.2通用线程状态



下表描述了`State` 与常规查询处理而非更专门的活动（如复制）关联的线程值。其中许多仅用于发现服务器中的错误。

- `After create`

  当线程在创建表的函数的末尾创建一个表（包括内部临时表）时，就会发生这种情况。即使由于某些错误而无法创建表，也会使用此状态。

- `Analyzing`

  该线程正在计算`MyISAM`表键分布（例如for [`ANALYZE TABLE`](sql-statements.html#analyze-table)）。

- `checking permissions`

  线程正在检查服务器是否具有执行该语句所需的特权。

- `Checking table`

  线程正在执行表检查操作。

- `cleaning up`

  该线程已处理一个命令，并准备释放内存并重置某些状态变量。

- `closing tables`

  线程正在将更改后的表数据刷新到磁盘并关闭已使用的表。这应该是一个快速的操作。如果没有，请确认您没有完整的磁盘，并且磁盘使用率不是很高。

- `converting HEAP to ondisk`

  线程正在将内部临时表从 `MEMORY`表转换为磁盘表。

- `copy to tmp table`

  线程正在处理 [`ALTER TABLE`](sql-statements.html#alter-table)语句。在创建具有新结构的表之后但在将行复制到其中之前，将发生此状态。

  对于处于这种状态的线程，可以使用性能架构来获取有关复制操作进度的信息。请参见 [第25.12.5节“性能架构阶段事件表”](performance-schema.html#performance-schema-stage-tables)。

- `Copying to group table`

  如果语句具有不同的条件`ORDER BY`和 `GROUP BY`条件，则行将按组排序并复制到临时表中。

- `Copying to tmp table`

  服务器正在复制到内存中的临时表。

- `altering table`

  服务器正在执行就地服务 [`ALTER TABLE`](sql-statements.html#alter-table)。

- `Copying to tmp table on disk`

  服务器正在复制到磁盘上的临时表。临时结果集太大（请参见 [第8.4.4节“ MySQL中的内部临时表使用”](optimization.html#internal-temporary-tables)）。因此，线程正在将临时表从内存中更改为基于磁盘的格式，以节省内存。

- `Creating index`

  线程正在处理`ALTER TABLE ... ENABLE KEYS`一个`MyISAM`表。

- `Creating sort index`

  线程正在处理 [`SELECT`](sql-statements.html#select)使用内部临时表解析的。

- `creating table`

  该线程正在创建一个表。这包括创建临时表。

- `Creating tmp table`

  该线程正在内存或磁盘上创建一个临时表。如果该表是在内存中创建的，但后来又转换为磁盘表，则该操作期间的状态将为`Copying to tmp table on disk`。

- `committing alter table to storage engine`

  服务器已就地完成 [`ALTER TABLE`](sql-statements.html#alter-table)并提交结果。

- `deleting from main table`

  服务器正在执行多表删除的第一部分。它仅从第一个表中删除，并保存要从其他（参考）表中删除的列和偏移量。

- `deleting from reference tables`

  服务器正在执行多表删除的第二部分，并从其他表中删除匹配的行。

- `discard_or_import_tablespace`

  线程正在处理`ALTER TABLE ... DISCARD TABLESPACE`or `ALTER TABLE ... IMPORT TABLESPACE`语句。

- `end`

  这发生在结束，但的清理之前 [`ALTER TABLE`](sql-statements.html#alter-table)， [`CREATE VIEW`](sql-statements.html#create-view)， [`DELETE`](sql-statements.html#delete)， [`INSERT`](sql-statements.html#insert)， [`SELECT`](sql-statements.html#select)，或 [`UPDATE`](sql-statements.html#update)语句。

- `executing`

  该线程已开始执行一条语句。

- `Execution of init_command`

  线程正在使用`init_command`系统变量的值执行语句 。

- `freeing items`

  线程已执行命令。在此状态下完成的一些项目释放涉及查询缓存。此状态通常后跟`cleaning up`。

- `FULLTEXT initialization`

  服务器正在准备执行自然语言的全文本搜索。

- `init`

  这发生在初始化之前 [`ALTER TABLE`](sql-statements.html#alter-table)， [`DELETE`](sql-statements.html#delete)， [`INSERT`](sql-statements.html#insert)， [`SELECT`](sql-statements.html#select)，或者 [`UPDATE`](sql-statements.html#update)语句。服务器在此状态下采取的操作包括刷新二进制日志，`InnoDB`日志和一些查询缓存清除操作。

  对于`end`状态，可能发生以下操作：

  - 更改表中的数据后删除查询缓存条目
  - 将事件写入二进制日志
  - 释放内存缓冲区，包括用于blob的缓冲区

- `Killed`

  有人[`KILL`](sql-statements.html#kill) 向该线程发送了一条语句，下次检查kill标志时，它将中止。在MySQL的每个主要循环中都会检查该标志，但是在某些情况下，线程死亡仍然可能需要很短的时间。如果该线程被某个其他线程锁定，则杀死操作将在另一个线程释放其锁定后立即生效。

- `logging slow query`

  线程正在将一条语句写入慢速查询日志。

- `login`

  连接线程的初始状态，直到客户端已成功通过身份验证。

- `manage keys`

  服务器正在启用或禁用表索引。

- `NULL`

  该状态用于[`SHOW PROCESSLIST`](sql-statements.html#show-processlist)状态。

- `Opening tables`

  线程正在尝试打开表。除非有阻止打开的步骤，否则这应该是非常快速的过程。例如，一个[`ALTER TABLE`](sql-statements.html#alter-table)或一条 [`LOCK TABLE`](sql-statements.html#lock-tables)语句可以阻止在该语句完成之前打开表。还值得检查您的[`table_open_cache`](server-administration.html#sysvar_table_open_cache)价值是否足够大。

- `optimizing`

  服务器正在对查询执行初始优化。

- `preparing`

  此状态在查询优化期间发生。

- `Purging old relay logs`

  该线程正在删除不需要的中继日志文件。

- `query end`

  此状态在处理查询之后但在该`freeing items`状态之前发生 。

- `Receiving from client`

  服务器正在从客户端读取数据包。`Reading from net`在MySQL 5.7.8之前，此状态称为。

- `Removing duplicates`

  查询的使用 [`SELECT DISTINCT`](sql-statements.html#select)方式使得MySQL无法在早期阶段优化独特的操作。因此，MySQL需要一个额外的阶段来删除所有重复的行，然后再将结果发送给客户端。

- `removing tmp table`

  线程在处理[`SELECT`](sql-statements.html#select) 语句后正在删除内部临时表。如果未创建临时表，则不使用此状态。

- `rename`

  线程正在重命名表。

- `rename result table`

  该线程正在处理一条[`ALTER TABLE`](sql-statements.html#alter-table)语句，创建了新表，并对其进行了重命名以替换原始表。

- `Reopen tables`

  线程获得了该表的锁，但是在获得该锁后，该线程注意到基础表结构已更改。它释放了锁，关闭了表，并试图重新打开它。

- `Repair by sorting`

  修复代码正在使用某种排序来创建索引。

- `preparing for alter table`

  服务器正在准备执行就地 [`ALTER TABLE`](sql-statements.html#alter-table)。

- `Repair done`

  该线程已完成对`MyISAM`表的多线程修复 。

- `Repair with keycache`

  修复代码使用的是通过密钥缓存一对一地创建密钥。这比慢得多`Repair by sorting`。

- `Rolling back`

  线程正在回滚事务。

- `Saving state`

  对于`MyISAM`表操作（例如修复或分析），线程会将新表状态保存到`.MYI`文件头。状态包括诸如行数， `AUTO_INCREMENT`计数器和密钥分布之类的信息。

- `Searching rows for update`

  线程正在执行第一阶段以在更新所有匹配的行之前找到它们。如果 [`UPDATE`](sql-statements.html#update)更改了用于查找所涉及行的索引，则必须执行此操作。

- `Sending data`

  线程正在读取和处理[`SELECT`](sql-statements.html#select)语句的行 ，并将数据发送到客户端。因为在此状态下发生的操作往往会执行大量磁盘访问（读取），所以它通常是给定查询生命周期中运行时间最长的状态。

- `Sending to client`

  服务器正在将数据包写入客户端。`Writing to net`在MySQL 5.7.8之前，此状态称为。

- `setup`

  线程正在开始[`ALTER TABLE`](sql-statements.html#alter-table)操作。

- `Sorting for group`

  线程正在做某种排序以满足`GROUP BY`。

- `Sorting for order`

  线程正在执行某种排序以满足`ORDER BY`。

- `Sorting index`

  该线程正在对索引页进行排序，以在`MyISAM`表优化操作期间更有效地进行访问。

- `Sorting result`

  对于[`SELECT`](sql-statements.html#select)语句，这类似于`Creating sort index`，但是对于非临时表。

- `statistics`

  服务器正在计算统计信息以制定查询执行计划。如果线程长时间处于此状态，则服务器可能是磁盘绑定的，正在执行其他工作。

- `System lock`

  该线程已调用 `mysql_lock_tables()` ，并且线程状态尚未更新。这是一种非常普遍的状态，可能由于多种原因而发生。

  例如，线程将要请求或正在等待表的内部或外部系统锁定。[`InnoDB`](innodb-storage-engine.html)在执行期间等待表级锁定时， 可能会发生这种情况[`LOCK TABLES`](sql-statements.html#lock-tables)。如果此状态是由对外部锁的请求引起的，并且您没有使用正在访问同一 表的多个[**mysqld**](programs.html#mysqld)服务器，则[`MyISAM`](storage-engines.html#myisam-storage-engine)可以使用禁用外部系统锁 ，此状态表示线程正在请求该锁（不等待它） 。 [`--skip-external-locking`](server-administration.html#option_mysqld_external-locking) 选项。但是，默认情况下将禁用外部锁定，因此此选项可能无效。对于 [`SHOW PROFILE`](sql-statements.html#show-profile)

- `update`

  线程已准备好开始更新表。

- `Updating`

  线程正在搜索要更新的行，并且正在更新它们。

- `updating main table`

  服务器正在执行多表更新的第一部分。它仅更新第一个表，并保存列和偏移量以用于更新其他（参考）表。

- `updating reference tables`

  服务器正在执行多表更新的第二部分，并从其他表更新匹配的行。

- `User lock`

  该线程将要请求或正在等待通过[`GET_LOCK()`](functions.html#function_get-lock)调用请求的咨询锁 。对于 [`SHOW PROFILE`](sql-statements.html#show-profile)，此状态表示线程正在请求锁定（不等待它）。

- `User sleep`

  线程已调用 [`SLEEP()`](functions.html#function_sleep)。

- `Waiting for commit lock`

  [`FLUSH TABLES WITH READ LOCK`](sql-statements.html#flush-tables-with-read-lock) 正在等待提交锁。

- `Waiting for global read lock`

  [`FLUSH TABLES WITH READ LOCK`](sql-statements.html#flush-tables-with-read-lock) 正在等待全局读取锁定或[`read_only`](server-administration.html#sysvar_read_only)正在设置全局 系统变量。

- `Waiting for tables`

  该线程收到有关表的基础结构已更改的通知，它需要重新打开表以获取新的结构。但是，要重新打开该表，它必须等待，直到所有其他线程关闭了该表。

  这张告示，如果另一个线程使用的地方 [`FLUSH TABLES`](sql-statements.html#flush-tables)或有问题的表下面的语句之一： ， ， ， ， ，或 。 `FLUSH TABLES *`tbl_name`*`[`ALTER TABLE`](sql-statements.html#alter-table)[`RENAME TABLE`](sql-statements.html#rename-table)[`REPAIR TABLE`](sql-statements.html#repair-table)[`ANALYZE TABLE`](sql-statements.html#analyze-table)[`OPTIMIZE TABLE`](sql-statements.html#optimize-table)

- `Waiting for table flush`

  该线程正在执行[`FLUSH TABLES`](sql-statements.html#flush-tables)并正在等待所有线程关闭其表，或者该线程收到有关表的基础结构已更改的通知，并且需要重新打开表以获取新结构。但是，要重新打开该表，它必须等待，直到所有其他线程关闭了该表。

  这张告示，如果另一个线程使用的地方 [`FLUSH TABLES`](sql-statements.html#flush-tables)或有问题的表下面的语句之一： ， ， ， ， ，或 。 `FLUSH TABLES *`tbl_name`*`[`ALTER TABLE`](sql-statements.html#alter-table)[`RENAME TABLE`](sql-statements.html#rename-table)[`REPAIR TABLE`](sql-statements.html#repair-table)[`ANALYZE TABLE`](sql-statements.html#analyze-table)[`OPTIMIZE TABLE`](sql-statements.html#optimize-table)

- `Waiting for *`lock_type`* lock`

  服务器正在等待`THR_LOCK`从元数据锁定子系统获取 锁或锁，其中 *`lock_type`*指示了锁的类型。

  此状态表示正在等待 `THR_LOCK`：

  - `Waiting for table level lock`

  这些状态指示等待元数据锁定：

  - `Waiting for event metadata lock`
  - `Waiting for global read lock`
  - `Waiting for schema metadata lock`
  - `Waiting for stored function metadata lock`
  - `Waiting for stored procedure metadata lock`
  - `Waiting for table metadata lock`
  - `Waiting for trigger metadata lock`

  有关表锁定指示器的信息，请参见 [第8.11.1节“内部锁定方法”](optimization.html#internal-locking)。有关元数据锁定的信息，请参见[第8.11.4节“元数据锁定”](optimization.html#metadata-locking)。要查看哪些锁阻止了锁请求，请使用[第25.12.12节“性能模式锁表”中](performance-schema.html#performance-schema-lock-tables)介绍的 [性能模式锁表](performance-schema.html#performance-schema-lock-tables)。

- `Waiting on cond`

  线程正在等待条件变为真的一般状态。没有可用的特定状态信息。

- `Writing to net`

  服务器正在将数据包写入网络。`Sending to client`从MySQL 5.7.8开始，此状态被称为。

### 8.14.3查询缓存线程状态



这些线程状态与查询缓存关联（请参见 [第8.10.3节“ MySQL查询缓存”](optimization.html#query-cache)）。

- `checking privileges on cached query`

  服务器正在检查用户是否具有访问缓存的查询结果的特权。

- `checking query cache for query`

  服务器正在检查查询高速缓存中是否存在当前查询。

- `invalidating query cache entries`

  查询缓存条目被标记为无效，因为基础表已更改。

- `sending cached result to client`

  服务器正在从查询缓存中获取查询结果，并将其发送到客户端。

- `storing result in query cache`

  服务器将查询结果存储在查询缓存中。

- `Waiting for query cache lock`

  当会话正在等待获取查询缓存锁时，会发生此状态。对于需要执行某些查询缓存操作的任何语句（例如[`INSERT`](sql-statements.html#insert)或 [`DELETE`](sql-statements.html#delete)使查询缓存无效[`SELECT`](sql-statements.html#select)，查找缓存条目的[`RESET QUERY CACHE`](sql-statements.html#reset)等等）， 都可能发生这种情况 。

### 8.14.4复制主线程状态



以下列表显示了您可能在`State`主`Binlog Dump`线程的列中 看到的最常见状态。如果`Binlog Dump`在主服务器上看不到 线程，则表明复制未运行；也就是说，当前没有从站连接。

- `Finished reading one binlog; switching to next binlog`

  该线程已完成读取二进制日志文件，并正在打开下一个日志文件以发送到从属服务器。

- `Master has sent all binlog to slave; waiting for more updates`

  线程已从二进制日志中读取所有剩余的更新，并将其发送到从属服务器。线程现在处于空闲状态，等待由于主服务器上发生新更新而导致新事件出现在二进制日志中。

- `Sending binlog event to slave`

  二进制日志由*事件*组成，其中事件通常是更新以及一些其他信息。该线程已从二进制日志中读取一个事件，并将其发送到从属服务器。

- `Waiting to finalize termination`

  线程停止时发生的非常短暂的状态。

### 8.14.5复制从属I / O线程状态



以下列表显示了您在`State`从属服务器I / O线程列中看到的最常见状态 。此状态也会出现在由所`Slave_IO_State` 显示的列中[`SHOW SLAVE STATUS`](sql-statements.html#show-slave-status)，因此您可以使用该语句很好地了解正在发生的情况。

- `Checking master version`

  建立与主机的连接后，状态非常短暂。

- `Connecting to master`

  线程正在尝试连接到主服务器。

- `Queueing master event to the relay log`

  线程已读取事件，并将其复制到中继日志，以便SQL线程可以处理它。

- `Reconnecting after a failed binlog dump request`

  线程正在尝试重新连接到主服务器。

- `Reconnecting after a failed master event read`

  线程正在尝试重新连接到主服务器。再次建立连接后，状态变为 `Waiting for master to send event`。

- `Registering slave on master`

  建立与主机的连接后非常短暂地发生的状态。

- `Requesting binlog dump`

  建立与主机的连接后，状态非常短暂。线程从请求的二进制日志文件名和位置开始，向主服务器发送对其二进制日志内容的请求。

- `Waiting for its turn to commit`

  如果[`slave_preserve_commit_order`](replication.html#sysvar_slave_preserve_commit_order) 启用了从属线程正在等待较早的工作线程提交的状态 。

- `Waiting for master to send event`

  线程已连接到主服务器，正在等待二进制日志事件到达。如果主机空闲，这可能会持续很长时间。如果等待持续 [`slave_net_timeout`](replication.html#sysvar_slave_net_timeout)几秒钟，则会发生超时。此时，线程认为连接已断开，并尝试重新连接。

- `Waiting for master update`

  之前的初始状态`Connecting to master`。

- `Waiting for slave mutex on exit`

  在线程停止时短暂发生的状态。

- `Waiting for the slave SQL thread to free enough relay log space`

  您使用的是非零 [`relay_log_space_limit`](replication.html#sysvar_relay_log_space_limit) 值，并且中继日志已变得足够大，以致它们的合并大小超过了该值。I / O线程正在等待，直到SQL线程通过处理中继日志内容释放足够的空间，以便它可以删除某些中继日志文件。

- `Waiting to reconnect after a failed binlog dump request`

  如果二进制日志转储请求失败（由于断开连接），则线程在休眠时进入此状态，然后尝试定期重新连接。重试之间的间隔可以使用该[`CHANGE MASTER TO`](sql-statements.html#change-master-to)语句指定 。

- `Waiting to reconnect after a failed master event read`

  读取时发生错误（由于断开连接）。[`CHANGE MASTER TO`](sql-statements.html#change-master-to)在尝试重新连接之前，该线程处于休眠状态的语句所设置的秒数 （默认为60）。

### 8.14.6复制从SQL线程状态



以下列表显示了您可能在`State`从属服务器SQL线程的列中看到的最常见状态：

- `Killing slave`

  线程正在处理一条`STOP SLAVE` 语句。

- `Making temporary file (append) before replaying LOAD DATA INFILE`

  线程正在执行一条[`LOAD DATA`](sql-statements.html#load-data)语句，并将数据追加到一个临时文件中，该临时文件包含从站将从中读取行的数据。

- `Making temporary file (create) before replaying LOAD DATA INFILE`

  该线程正在执行一条[`LOAD DATA`](sql-statements.html#load-data)语句，并正在创建一个临时文件，其中包含从站将从中读取行的数据。仅当原始[`LOAD DATA`](sql-statements.html#load-data)语句是由运行低于MySQL 5.0.3的MySQL版本的主服务器记录的，才能遇到此状态 。

- `Reading event from the relay log`

  线程已从中继日志中读取事件，以便可以处理该事件。

- `Slave has read all relay log; waiting for more updates`

  该线程已经处理了中继日志文件中的所有事件，现在正在等待I / O线程将新事件写入中继日志。

- `Waiting for an event from Coordinator`

  使用多线程从属服务器（[`slave_parallel_workers`](replication.html#sysvar_slave_parallel_workers)大于1），一个从属工作线程正在等待协调器线程的事件。

- `Waiting for slave mutex on exit`

  线程停止时发生的非常短暂的状态。

- `Waiting for Slave Workers to free pending events`

  当Workers处理的事件的总大小超过[`slave_pending_jobs_size_max`](replication.html#sysvar_slave_pending_jobs_size_max) 系统变量的大小时，将发生此等待操作 。当大小降至此限制以下时，协调器将恢复调度。仅当[`slave_parallel_workers`](replication.html#sysvar_slave_parallel_workers)设置为大于0 时，才会出现此状态 。

- `Waiting for the next event in relay log`

  之前的初始状态`Reading event from the relay log`。

- `Waiting until MASTER_DELAY seconds after master executed event`

  SQL线程已读取事件，但正在等待从属延迟过去。延迟设置 `MASTER_DELAY`为 [`CHANGE MASTER TO`](sql-statements.html#change-master-to)。

`Info`SQL线程 的列也可能显示语句的文本。这表明线程已经从中继日志中读取了一个事件，从中提取了该语句，并且可能正在执行该事件。

### 8.14.7复制从设备连接线程状态



这些线程状态发生在复制从属服务器上，但与连接线程关联，而不与I / O或SQL线程关联。

- `Changing master`

  线程正在处理一条[`CHANGE MASTER TO`](sql-statements.html#change-master-to)语句。

- `Killing slave`

  线程正在处理一条`STOP SLAVE` 语句。

- `Opening master dump table`

  此状态在之后发生`Creating table from master dump`。

- `Reading master dump table data`

  此状态在之后发生`Opening master dump table`。

- `Rebuilding the index on master dump table`

  此状态在之后发生`Reading master dump table data`。

### 8.14.8 NDB群集线程状态



- `Committing events to binlog`

- `Opening mysql.ndb_apply_status`

- `Processing events`

  线程正在处理二进制日志记录的事件。

- `Processing events from schema table`

  该线程正在执行架构复制。

- `Shutting down`

- `Syncing ndb table schema operation and binlog`

  这用于为NDB提供正确的模式操作二进制日志。

- `Waiting for allowed to take ndbcluster global schema lock`

  线程正在等待权限以获取全局架构锁。

- `Waiting for event from ndbcluster`

  该服务器充当NDB群集中的SQL节点，并连接到群集管理节点。

- `Waiting for first event from ndbcluster`

- `Waiting for ndbcluster binlog update to reach current position`

- `Waiting for ndbcluster global schema lock`

  线程正在等待另一个线程持有的全局模式锁被释放。

- `Waiting for ndbcluster to start`

- `Waiting for schema epoch`

  线程正在等待架构时代（即全局检查点）。

### 8.14.9事件调度程序线程状态



这些状态发生在事件计划程序线程，为执行计划的事件而创建的线程或终止计划程序的线程中。

- `Clearing`

  调度程序线程或正在执行事件的线程正在终止，即将结束。

- `Initialized`

  调度程序线程或将执行事件的线程已被初始化。

- `Waiting for next activation`

  调度程序有一个非空事件队列，但是下次激活是在将来。

- `Waiting for scheduler to stop`

  线程已发出`SET GLOBAL event_scheduler=OFF`，正在等待调度程序停止。

- `Waiting on empty queue`

  调度程序的事件队列为空，正在休眠。

------

| [上一个](backup-and-recovery.html) | [向上]()         | [下一个](language-structure.html) |
| ---------------------------------- | ---------------- | --------------------------------- |
| 第7章备份和恢复                    | [家](index.html) | 第9章语言结构                     |