| Chapter 8 Optimization           |      |                                 |
| :------------------------------- | ---- | ------------------------------- |
| [Prev](backup-and-recovery.html) |      | [Next](language-structure.html) |

------

# Chapter 8 Optimization

**Table of Contents**

- [8.1 Optimization Overview](optimization.html#optimize-overview)

- [8.2 Optimizing SQL Statements](optimization.html#statement-optimization)

  [8.2.1 Optimizing SELECT Statements](optimization.html#select-optimization)[8.2.2 Optimizing Subqueries, Derived Tables, and View References](optimization.html#subquery-optimization)[8.2.3 Optimizing INFORMATION_SCHEMA Queries](optimization.html#information-schema-optimization)[8.2.4 Optimizing Data Change Statements](optimization.html#data-change-optimization)[8.2.5 Optimizing Database Privileges](optimization.html#permission-optimization)[8.2.6 Other Optimization Tips](optimization.html#miscellaneous-optimization-tips)

- [8.3 Optimization and Indexes](optimization.html#optimization-indexes)

  [8.3.1 How MySQL Uses Indexes](optimization.html#mysql-indexes)[8.3.2 Primary Key Optimization](optimization.html#primary-key-optimization)[8.3.3 Foreign Key Optimization](optimization.html#foreign-key-optimization)[8.3.4 Column Indexes](optimization.html#column-indexes)[8.3.5 Multiple-Column Indexes](optimization.html#multiple-column-indexes)[8.3.6 Verifying Index Usage](optimization.html#verifying-index-usage)[8.3.7 InnoDB and MyISAM Index Statistics Collection](optimization.html#index-statistics)[8.3.8 Comparison of B-Tree and Hash Indexes](optimization.html#index-btree-hash)[8.3.9 Use of Index Extensions](optimization.html#index-extensions)[8.3.10 Optimizer Use of Generated Column Indexes](optimization.html#generated-column-index-optimizations)[8.3.11 Indexed Lookups from TIMESTAMP Columns](optimization.html#timestamp-lookups)

- [8.4 Optimizing Database Structure](optimization.html#optimizing-database-structure)

  [8.4.1 Optimizing Data Size](optimization.html#data-size)[8.4.2 Optimizing MySQL Data Types](optimization.html#optimize-data-types)[8.4.3 Optimizing for Many Tables](optimization.html#optimize-multi-tables)[8.4.4 Internal Temporary Table Use in MySQL](optimization.html#internal-temporary-tables)[8.4.5 Limits on Number of Databases and Tables](optimization.html#database-count-limit)[8.4.6 Limits on Table Size](optimization.html#table-size-limit)[8.4.7 Limits on Table Column Count and Row Size](optimization.html#column-count-limit)

- [8.5 Optimizing for InnoDB Tables](optimization.html#optimizing-innodb)

  [8.5.1 Optimizing Storage Layout for InnoDB Tables](optimization.html#optimizing-innodb-storage-layout)[8.5.2 Optimizing InnoDB Transaction Management](optimization.html#optimizing-innodb-transaction-management)[8.5.3 Optimizing InnoDB Read-Only Transactions](optimization.html#innodb-performance-ro-txn)[8.5.4 Optimizing InnoDB Redo Logging](optimization.html#optimizing-innodb-logging)[8.5.5 Bulk Data Loading for InnoDB Tables](optimization.html#optimizing-innodb-bulk-data-loading)[8.5.6 Optimizing InnoDB Queries](optimization.html#optimizing-innodb-queries)[8.5.7 Optimizing InnoDB DDL Operations](optimization.html#optimizing-innodb-ddl-operations)[8.5.8 Optimizing InnoDB Disk I/O](optimization.html#optimizing-innodb-diskio)[8.5.9 Optimizing InnoDB Configuration Variables](optimization.html#optimizing-innodb-configuration-variables)[8.5.10 Optimizing InnoDB for Systems with Many Tables](optimization.html#optimizing-innodb-many-tables)

- [8.6 Optimizing for MyISAM Tables](optimization.html#optimizing-myisam)

  [8.6.1 Optimizing MyISAM Queries](optimization.html#optimizing-queries-myisam)[8.6.2 Bulk Data Loading for MyISAM Tables](optimization.html#optimizing-myisam-bulk-data-loading)[8.6.3 Optimizing REPAIR TABLE Statements](optimization.html#repair-table-optimization)

- [8.7 Optimizing for MEMORY Tables](optimization.html#optimizing-memory-tables)

- [8.8 Understanding the Query Execution Plan](optimization.html#execution-plan-information)

  [8.8.1 Optimizing Queries with EXPLAIN](optimization.html#using-explain)[8.8.2 EXPLAIN Output Format](optimization.html#explain-output)[8.8.3 Extended EXPLAIN Output Format](optimization.html#explain-extended)[8.8.4 Obtaining Execution Plan Information for a Named Connection](optimization.html#explain-for-connection)[8.8.5 Estimating Query Performance](optimization.html#estimating-performance)

- [8.9 Controlling the Query Optimizer](optimization.html#controlling-optimizer)

  [8.9.1 Controlling Query Plan Evaluation](optimization.html#controlling-query-plan-evaluation)[8.9.2 Switchable Optimizations](optimization.html#switchable-optimizations)[8.9.3 Optimizer Hints](optimization.html#optimizer-hints)[8.9.4 Index Hints](optimization.html#index-hints)[8.9.5 The Optimizer Cost Model](optimization.html#cost-model)

- [8.10 Buffering and Caching](optimization.html#buffering-caching)

  [8.10.1 InnoDB Buffer Pool Optimization](optimization.html#innodb-buffer-pool-optimization)[8.10.2 The MyISAM Key Cache](optimization.html#myisam-key-cache)[8.10.3 The MySQL Query Cache](optimization.html#query-cache)[8.10.4 Caching of Prepared Statements and Stored Programs](optimization.html#statement-caching)

- [8.11 Optimizing Locking Operations](optimization.html#locking-issues)

  [8.11.1 Internal Locking Methods](optimization.html#internal-locking)[8.11.2 Table Locking Issues](optimization.html#table-locking)[8.11.3 Concurrent Inserts](optimization.html#concurrent-inserts)[8.11.4 Metadata Locking](optimization.html#metadata-locking)[8.11.5 External Locking](optimization.html#external-locking)

- [8.12 Optimizing the MySQL Server](optimization.html#optimizing-server)

  [8.12.1 System Factors](optimization.html#system-optimization)[8.12.2 Optimizing Disk I/O](optimization.html#disk-issues)[8.12.3 Using Symbolic Links](optimization.html#symbolic-links)[8.12.4 Optimizing Memory Use](optimization.html#optimizing-memory)

- [8.13 Measuring Performance (Benchmarking)](optimization.html#optimize-benchmarking)

  [8.13.1 Measuring the Speed of Expressions and Functions](optimization.html#select-benchmarking)[8.13.2 Using Your Own Benchmarks](optimization.html#custom-benchmarks)[8.13.3 Measuring Performance with performance_schema](optimization.html#monitoring-performance-schema)

- [8.14 Examining Thread Information](optimization.html#thread-information)

  [8.14.1 Thread Command Values](optimization.html#thread-commands)[8.14.2 General Thread States](optimization.html#general-thread-states)[8.14.3 Query Cache Thread States](optimization.html#query-cache-thread-states)[8.14.4 Replication Master Thread States](optimization.html#master-thread-states)[8.14.5 Replication Slave I/O Thread States](optimization.html#slave-io-thread-states)[8.14.6 Replication Slave SQL Thread States](optimization.html#slave-sql-thread-states)[8.14.7 Replication Slave Connection Thread States](optimization.html#slave-connection-thread-states)[8.14.8 NDB Cluster Thread States](optimization.html#mysql-cluster-thread-states)[8.14.9 Event Scheduler Thread States](optimization.html#event-scheduler-thread-states)



This chapter explains how to optimize MySQL performance and provides examples. Optimization involves configuring, tuning, and measuring performance, at several levels. Depending on your job role (developer, DBA, or a combination of both), you might optimize at the level of individual SQL statements, entire applications, a single database server, or multiple networked database servers. Sometimes you can be proactive and plan in advance for performance, while other times you might troubleshoot a configuration or code issue after a problem occurs. Optimizing CPU and memory usage can also improve scalability, allowing the database to handle more load without slowing down.

## 8.1 Optimization Overview

Database performance depends on several factors at the database level, such as tables, queries, and configuration settings. These software constructs result in CPU and I/O operations at the hardware level, which you must minimize and make as efficient as possible. As you work on database performance, you start by learning the high-level rules and guidelines for the software side, and measuring performance using wall-clock time. As you become an expert, you learn more about what happens internally, and start measuring things such as CPU cycles and I/O operations.

Typical users aim to get the best database performance out of their existing software and hardware configurations. Advanced users look for opportunities to improve the MySQL software itself, or develop their own storage engines and hardware appliances to expand the MySQL ecosystem.

- [Optimizing at the Database Level](optimization.html#optimize-database-level)
- [Optimizing at the Hardware Level](optimization.html#optimize-hardware-level)
- [Balancing Portability and Performance](optimization.html#optimize-portability-performance)

### Optimizing at the Database Level

The most important factor in making a database application fast is its basic design:

- Are the tables structured properly? In particular, do the columns have the right data types, and does each table have the appropriate columns for the type of work? For example, applications that perform frequent updates often have many tables with few columns, while applications that analyze large amounts of data often have few tables with many columns.

- Are the right [indexes](optimization.html#optimization-indexes) in place to make queries efficient?

- Are you using the appropriate storage engine for each table, and taking advantage of the strengths and features of each storage engine you use? In particular, the choice of a transactional storage engine such as `InnoDB` or a nontransactional one such as `MyISAM` can be very important for performance and scalability.

  Note

  `InnoDB` is the default storage engine for new tables. In practice, the advanced `InnoDB` performance features mean that `InnoDB` tables often outperform the simpler `MyISAM` tables, especially for a busy database.

- Does each table use an appropriate row format? This choice also depends on the storage engine used for the table. In particular, compressed tables use less disk space and so require less disk I/O to read and write the data. Compression is available for all kinds of workloads with `InnoDB` tables, and for read-only `MyISAM` tables.

- Does the application use an appropriate [locking strategy](optimization.html#locking-issues)? For example, by allowing shared access when possible so that database operations can run concurrently, and requesting exclusive access when appropriate so that critical operations get top priority. Again, the choice of storage engine is significant. The `InnoDB` storage engine handles most locking issues without involvement from you, allowing for better concurrency in the database and reducing the amount of experimentation and tuning for your code.

- Are all [memory areas used for caching](optimization.html#buffering-caching) sized correctly? That is, large enough to hold frequently accessed data, but not so large that they overload physical memory and cause paging. The main memory areas to configure are the `InnoDB` buffer pool, the `MyISAM` key cache, and the MySQL query cache.

### Optimizing at the Hardware Level

Any database application eventually hits hardware limits as the database becomes more and more busy. A DBA must evaluate whether it is possible to tune the application or reconfigure the server to avoid these [bottlenecks](glossary.html#glos_bottleneck), or whether more hardware resources are required. System bottlenecks typically arise from these sources:

- Disk seeks. It takes time for the disk to find a piece of data. With modern disks, the mean time for this is usually lower than 10ms, so we can in theory do about 100 seeks a second. This time improves slowly with new disks and is very hard to optimize for a single table. The way to optimize seek time is to distribute the data onto more than one disk.
- Disk reading and writing. When the disk is at the correct position, we need to read or write the data. With modern disks, one disk delivers at least 10–20MB/s throughput. This is easier to optimize than seeks because you can read in parallel from multiple disks.
- CPU cycles. When the data is in main memory, we must process it to get our result. Having large tables compared to the amount of memory is the most common limiting factor. But with small tables, speed is usually not the problem.
- Memory bandwidth. When the CPU needs more data than can fit in the CPU cache, main memory bandwidth becomes a bottleneck. This is an uncommon bottleneck for most systems, but one to be aware of.

### Balancing Portability and Performance



To use performance-oriented SQL extensions in a portable MySQL program, you can wrap MySQL-specific keywords in a statement within `/*! */` comment delimiters. Other SQL servers ignore the commented keywords. For information about writing comments, see [Section 9.6, “Comment Syntax”](language-structure.html#comments).

## 8.2 Optimizing SQL Statements

- [8.2.1 Optimizing SELECT Statements](optimization.html#select-optimization)
- [8.2.2 Optimizing Subqueries, Derived Tables, and View References](optimization.html#subquery-optimization)
- [8.2.3 Optimizing INFORMATION_SCHEMA Queries](optimization.html#information-schema-optimization)
- [8.2.4 Optimizing Data Change Statements](optimization.html#data-change-optimization)
- [8.2.5 Optimizing Database Privileges](optimization.html#permission-optimization)
- [8.2.6 Other Optimization Tips](optimization.html#miscellaneous-optimization-tips)



The core logic of a database application is performed through SQL statements, whether issued directly through an interpreter or submitted behind the scenes through an API. The tuning guidelines in this section help to speed up all kinds of MySQL applications. The guidelines cover SQL operations that read and write data, the behind-the-scenes overhead for SQL operations in general, and operations used in specific scenarios such as database monitoring.

### 8.2.1 Optimizing SELECT Statements

- [8.2.1.1 WHERE Clause Optimization](optimization.html#where-optimization)
- [8.2.1.2 Range Optimization](optimization.html#range-optimization)
- [8.2.1.3 Index Merge Optimization](optimization.html#index-merge-optimization)
- [8.2.1.4 Engine Condition Pushdown Optimization](optimization.html#condition-pushdown-optimization)
- [8.2.1.5 Index Condition Pushdown Optimization](optimization.html#index-condition-pushdown-optimization)
- [8.2.1.6 Nested-Loop Join Algorithms](optimization.html#nested-loop-joins)
- [8.2.1.7 Nested Join Optimization](optimization.html#nested-join-optimization)
- [8.2.1.8 Outer Join Optimization](optimization.html#outer-join-optimization)
- [8.2.1.9 Outer Join Simplification](optimization.html#outer-join-simplification)
- [8.2.1.10 Multi-Range Read Optimization](optimization.html#mrr-optimization)
- [8.2.1.11 Block Nested-Loop and Batched Key Access Joins](optimization.html#bnl-bka-optimization)
- [8.2.1.12 Condition Filtering](optimization.html#condition-filtering)
- [8.2.1.13 IS NULL Optimization](optimization.html#is-null-optimization)
- [8.2.1.14 ORDER BY Optimization](optimization.html#order-by-optimization)
- [8.2.1.15 GROUP BY Optimization](optimization.html#group-by-optimization)
- [8.2.1.16 DISTINCT Optimization](optimization.html#distinct-optimization)
- [8.2.1.17 LIMIT Query Optimization](optimization.html#limit-optimization)
- [8.2.1.18 Function Call Optimization](optimization.html#function-optimization)
- [8.2.1.19 Row Constructor Expression Optimization](optimization.html#row-constructor-optimization)
- [8.2.1.20 Avoiding Full Table Scans](optimization.html#table-scan-avoidance)



Queries, in the form of [`SELECT`](sql-statements.html#select) statements, perform all the lookup operations in the database. Tuning these statements is a top priority, whether to achieve sub-second response times for dynamic web pages, or to chop hours off the time to generate huge overnight reports.

Besides [`SELECT`](sql-statements.html#select) statements, the tuning techniques for queries also apply to constructs such as [`CREATE TABLE...AS SELECT`](sql-statements.html#create-table-select), [`INSERT INTO...SELECT`](sql-statements.html#insert-select), and `WHERE` clauses in [`DELETE`](sql-statements.html#delete) statements. Those statements have additional performance considerations because they combine write operations with the read-oriented query operations.

NDB Cluster supports a join pushdown optimization whereby a qualifying join is sent in its entirety to NDB Cluster data nodes, where it can be distributed among them and executed in parallel. For more information about this optimization, see [Conditions for NDB pushdown joins](mysql-cluster.html#ndb_join_pushdown-conditions).

The main considerations for optimizing queries are:

- To make a slow `SELECT ... WHERE` query faster, the first thing to check is whether you can add an [index](glossary.html#glos_index). Set up indexes on columns used in the `WHERE` clause, to speed up evaluation, filtering, and the final retrieval of results. To avoid wasted disk space, construct a small set of indexes that speed up many related queries used in your application.

  Indexes are especially important for queries that reference different tables, using features such as [joins](glossary.html#glos_join) and [foreign keys](glossary.html#glos_foreign_key). You can use the [`EXPLAIN`](sql-statements.html#explain) statement to determine which indexes are used for a [`SELECT`](sql-statements.html#select). See [Section 8.3.1, “How MySQL Uses Indexes”](optimization.html#mysql-indexes) and [Section 8.8.1, “Optimizing Queries with EXPLAIN”](optimization.html#using-explain).

- Isolate and tune any part of the query, such as a function call, that takes excessive time. Depending on how the query is structured, a function could be called once for every row in the result set, or even once for every row in the table, greatly magnifying any inefficiency.

- Minimize the number of [full table scans](glossary.html#glos_full_table_scan) in your queries, particularly for big tables.

- Keep table statistics up to date by using the [`ANALYZE TABLE`](sql-statements.html#analyze-table) statement periodically, so the optimizer has the information needed to construct an efficient execution plan.

- Learn the tuning techniques, indexing techniques, and configuration parameters that are specific to the storage engine for each table. Both `InnoDB` and `MyISAM` have sets of guidelines for enabling and sustaining high performance in queries. For details, see [Section 8.5.6, “Optimizing InnoDB Queries”](optimization.html#optimizing-innodb-queries) and [Section 8.6.1, “Optimizing MyISAM Queries”](optimization.html#optimizing-queries-myisam).

- You can optimize single-query transactions for `InnoDB` tables, using the technique in [Section 8.5.3, “Optimizing InnoDB Read-Only Transactions”](optimization.html#innodb-performance-ro-txn).

- Avoid transforming the query in ways that make it hard to understand, especially if the optimizer does some of the same transformations automatically.

- If a performance issue is not easily solved by one of the basic guidelines, investigate the internal details of the specific query by reading the [`EXPLAIN`](sql-statements.html#explain) plan and adjusting your indexes, `WHERE` clauses, join clauses, and so on. (When you reach a certain level of expertise, reading the [`EXPLAIN`](sql-statements.html#explain) plan might be your first step for every query.)

- Adjust the size and properties of the memory areas that MySQL uses for caching. With efficient use of the `InnoDB` [buffer pool](glossary.html#glos_buffer_pool), `MyISAM` key cache, and the MySQL query cache, repeated queries run faster because the results are retrieved from memory the second and subsequent times.

- Even for a query that runs fast using the cache memory areas, you might still optimize further so that they require less cache memory, making your application more scalable. Scalability means that your application can handle more simultaneous users, larger requests, and so on without experiencing a big drop in performance.

- Deal with locking issues, where the speed of your query might be affected by other sessions accessing the tables at the same time.

#### 8.2.1.1 WHERE Clause Optimization



This section discusses optimizations that can be made for processing `WHERE` clauses. The examples use [`SELECT`](sql-statements.html#select) statements, but the same optimizations apply for `WHERE` clauses in [`DELETE`](sql-statements.html#delete) and [`UPDATE`](sql-statements.html#update) statements.

Note

Because work on the MySQL optimizer is ongoing, not all of the optimizations that MySQL performs are documented here.

You might be tempted to rewrite your queries to make arithmetic operations faster, while sacrificing readability. Because MySQL does similar optimizations automatically, you can often avoid this work, and leave the query in a more understandable and maintainable form. Some of the optimizations performed by MySQL follow:

- Removal of unnecessary parentheses:

  ```
     ((a AND b) AND c OR (((a AND b) AND (c AND d))))
  -> (a AND b AND c) OR (a AND b AND c AND d)
  ```

- Constant folding:

  ```
     (a<b AND b=c) AND a=5
  -> b>5 AND b=c AND a=5
  ```

- Constant condition removal:

  ```
     (b>=5 AND b=5) OR (b=6 AND 5=5) OR (b=7 AND 5=6)
  -> b=5 OR b=6
  ```

- Constant expressions used by indexes are evaluated only once.

- [`COUNT(*)`](functions.html#function_count) on a single table without a `WHERE` is retrieved directly from the table information for `MyISAM` and `MEMORY` tables. This is also done for any `NOT NULL` expression when used with only one table.

- Early detection of invalid constant expressions. MySQL quickly detects that some [`SELECT`](sql-statements.html#select) statements are impossible and returns no rows.

- `HAVING` is merged with `WHERE` if you do not use `GROUP BY` or aggregate functions ([`COUNT()`](functions.html#function_count), [`MIN()`](functions.html#function_min), and so on).

- For each table in a join, a simpler `WHERE` is constructed to get a fast `WHERE` evaluation for the table and also to skip rows as soon as possible.

- All constant tables are read first before any other tables in the query. A constant table is any of the following:

  - An empty table or a table with one row.
  - A table that is used with a `WHERE` clause on a `PRIMARY KEY` or a `UNIQUE` index, where all index parts are compared to constant expressions and are defined as `NOT NULL`.

  All of the following tables are used as constant tables:

  ```
  SELECT * FROM t WHERE primary_key=1;
  SELECT * FROM t1,t2
    WHERE t1.primary_key=1 AND t2.primary_key=t1.id;
  ```

- The best join combination for joining the tables is found by trying all possibilities. If all columns in `ORDER BY` and `GROUP BY` clauses come from the same table, that table is preferred first when joining.

- If there is an `ORDER BY` clause and a different `GROUP BY` clause, or if the `ORDER BY` or `GROUP BY` contains columns from tables other than the first table in the join queue, a temporary table is created.

- If you use the `SQL_SMALL_RESULT` modifier, MySQL uses an in-memory temporary table.

- Each table index is queried, and the best index is used unless the optimizer believes that it is more efficient to use a table scan. At one time, a scan was used based on whether the best index spanned more than 30% of the table, but a fixed percentage no longer determines the choice between using an index or a scan. The optimizer now is more complex and bases its estimate on additional factors such as table size, number of rows, and I/O block size.

- In some cases, MySQL can read rows from the index without even consulting the data file. If all columns used from the index are numeric, only the index tree is used to resolve the query.

- Before each row is output, those that do not match the `HAVING` clause are skipped.

Some examples of queries that are very fast:

```
SELECT COUNT(*) FROM tbl_name;

SELECT MIN(key_part1),MAX(key_part1) FROM tbl_name;

SELECT MAX(key_part2) FROM tbl_name
  WHERE key_part1=constant;

SELECT ... FROM tbl_name
  ORDER BY key_part1,key_part2,... LIMIT 10;

SELECT ... FROM tbl_name
  ORDER BY key_part1 DESC, key_part2 DESC, ... LIMIT 10;
```

MySQL resolves the following queries using only the index tree, assuming that the indexed columns are numeric:

```
SELECT key_part1,key_part2 FROM tbl_name WHERE key_part1=val;

SELECT COUNT(*) FROM tbl_name
  WHERE key_part1=val1 AND key_part2=val2;

SELECT key_part2 FROM tbl_name GROUP BY key_part1;
```

The following queries use indexing to retrieve the rows in sorted order without a separate sorting pass:

```
SELECT ... FROM tbl_name
  ORDER BY key_part1,key_part2,... ;

SELECT ... FROM tbl_name
  ORDER BY key_part1 DESC, key_part2 DESC, ... ;
```

#### 8.2.1.2 Range Optimization

The [`range`](optimization.html#jointype_range) access method uses a single index to retrieve a subset of table rows that are contained within one or several index value intervals. It can be used for a single-part or multiple-part index. The following sections describe conditions under which the optimizer uses range access.

- [Range Access Method for Single-Part Indexes](optimization.html#range-access-single-part)
- [Range Access Method for Multiple-Part Indexes](optimization.html#range-access-multi-part)
- [Equality Range Optimization of Many-Valued Comparisons](optimization.html#equality-range-optimization)
- [Range Optimization of Row Constructor Expressions](optimization.html#row-constructor-range-optimization)
- [Limiting Memory Use for Range Optimization](optimization.html#range-optimization-memory-use)

##### Range Access Method for Single-Part Indexes

For a single-part index, index value intervals can be conveniently represented by corresponding conditions in the `WHERE` clause, denoted as range conditions rather than “intervals.”

The definition of a range condition for a single-part index is as follows:

- For both `BTREE` and `HASH` indexes, comparison of a key part with a constant value is a range condition when using the [`=`](functions.html#operator_equal), [`<=>`](functions.html#operator_equal-to), [`IN()`](functions.html#operator_in), [`IS NULL`](functions.html#operator_is-null), or [`IS NOT NULL`](functions.html#operator_is-not-null) operators.
- Additionally, for `BTREE` indexes, comparison of a key part with a constant value is a range condition when using the [`>`](functions.html#operator_greater-than), [`<`](functions.html#operator_less-than), [`>=`](functions.html#operator_greater-than-or-equal), [`<=`](functions.html#operator_less-than-or-equal), [`BETWEEN`](functions.html#operator_between), [`!=`](functions.html#operator_not-equal), or [`<>`](functions.html#operator_not-equal) operators, or [`LIKE`](functions.html#operator_like) comparisons if the argument to [`LIKE`](functions.html#operator_like) is a constant string that does not start with a wildcard character.
- For all index types, multiple range conditions combined with [`OR`](functions.html#operator_or) or [`AND`](functions.html#operator_and) form a range condition.

“Constant value” in the preceding descriptions means one of the following:

- A constant from the query string
- A column of a [`const`](optimization.html#jointype_const) or [`system`](optimization.html#jointype_system) table from the same join
- The result of an uncorrelated subquery
- Any expression composed entirely from subexpressions of the preceding types

Here are some examples of queries with range conditions in the `WHERE` clause:

```
SELECT * FROM t1
  WHERE key_col > 1
  AND key_col < 10;

SELECT * FROM t1
  WHERE key_col = 1
  OR key_col IN (15,18,20);

SELECT * FROM t1
  WHERE key_col LIKE 'ab%'
  OR key_col BETWEEN 'bar' AND 'foo';
```

Some nonconstant values may be converted to constants during the optimizer constant propagation phase.

MySQL tries to extract range conditions from the `WHERE` clause for each of the possible indexes. During the extraction process, conditions that cannot be used for constructing the range condition are dropped, conditions that produce overlapping ranges are combined, and conditions that produce empty ranges are removed.

Consider the following statement, where `key1` is an indexed column and `nonkey` is not indexed:

```
SELECT * FROM t1 WHERE
  (key1 < 'abc' AND (key1 LIKE 'abcde%' OR key1 LIKE '%b')) OR
  (key1 < 'bar' AND nonkey = 4) OR
  (key1 < 'uux' AND key1 > 'z');
```

The extraction process for key `key1` is as follows:

1. Start with original `WHERE` clause:

   ```
   (key1 < 'abc' AND (key1 LIKE 'abcde%' OR key1 LIKE '%b')) OR
   (key1 < 'bar' AND nonkey = 4) OR
   (key1 < 'uux' AND key1 > 'z')
   ```

2. Remove `nonkey = 4` and `key1 LIKE '%b'` because they cannot be used for a range scan. The correct way to remove them is to replace them with `TRUE`, so that we do not miss any matching rows when doing the range scan. Replacing them with `TRUE` yields:

   ```
   (key1 < 'abc' AND (key1 LIKE 'abcde%' OR TRUE)) OR
   (key1 < 'bar' AND TRUE) OR
   (key1 < 'uux' AND key1 > 'z')
   ```

3. Collapse conditions that are always true or false:

   - `(key1 LIKE 'abcde%' OR TRUE)` is always true
   - `(key1 < 'uux' AND key1 > 'z')` is always false

   Replacing these conditions with constants yields:

   ```
   (key1 < 'abc' AND TRUE) OR (key1 < 'bar' AND TRUE) OR (FALSE)
   ```

   Removing unnecessary `TRUE` and `FALSE` constants yields:

   ```
   (key1 < 'abc') OR (key1 < 'bar')
   ```

4. Combining overlapping intervals into one yields the final condition to be used for the range scan:

   ```
   (key1 < 'bar')
   ```

In general (and as demonstrated by the preceding example), the condition used for a range scan is less restrictive than the `WHERE` clause. MySQL performs an additional check to filter out rows that satisfy the range condition but not the full `WHERE` clause.

The range condition extraction algorithm can handle nested [`AND`](functions.html#operator_and)/[`OR`](functions.html#operator_or) constructs of arbitrary depth, and its output does not depend on the order in which conditions appear in `WHERE` clause.

MySQL does not support merging multiple ranges for the [`range`](optimization.html#jointype_range) access method for spatial indexes. To work around this limitation, you can use a [`UNION`](sql-statements.html#union) with identical [`SELECT`](sql-statements.html#select) statements, except that you put each spatial predicate in a different [`SELECT`](sql-statements.html#select).

##### Range Access Method for Multiple-Part Indexes

Range conditions on a multiple-part index are an extension of range conditions for a single-part index. A range condition on a multiple-part index restricts index rows to lie within one or several key tuple intervals. Key tuple intervals are defined over a set of key tuples, using ordering from the index.

For example, consider a multiple-part index defined as `key1(*`key_part1`*, *`key_part2`*, *`key_part3`*)`, and the following set of key tuples listed in key order:

```
key_part1  key_part2  key_part3
  NULL       1          'abc'
  NULL       1          'xyz'
  NULL       2          'foo'
   1         1          'abc'
   1         1          'xyz'
   1         2          'abc'
   2         1          'aaa'
```

The condition `*`key_part1`* = 1` defines this interval:

```
(1,-inf,-inf) <= (key_part1,key_part2,key_part3) < (1,+inf,+inf)
```

The interval covers the 4th, 5th, and 6th tuples in the preceding data set and can be used by the range access method.

By contrast, the condition `*`key_part3`* = 'abc'` does not define a single interval and cannot be used by the range access method.

The following descriptions indicate how range conditions work for multiple-part indexes in greater detail.

- For `HASH` indexes, each interval containing identical values can be used. This means that the interval can be produced only for conditions in the following form:

  ```
      key_part1 cmp const1
  AND key_part2 cmp const2
  AND ...
  AND key_partN cmp constN;
  ```

  Here, *`const1`*, *`const2`*, … are constants, *`cmp`* is one of the [`=`](functions.html#operator_equal), [`<=>`](functions.html#operator_equal-to), or [`IS NULL`](functions.html#operator_is-null) comparison operators, and the conditions cover all index parts. (That is, there are *`N`* conditions, one for each part of an *`N`*-part index.) For example, the following is a range condition for a three-part `HASH` index:

  ```
  key_part1 = 1 AND key_part2 IS NULL AND key_part3 = 'foo'
  ```

  For the definition of what is considered to be a constant, see [Range Access Method for Single-Part Indexes](optimization.html#range-access-single-part).

- For a `BTREE` index, an interval might be usable for conditions combined with [`AND`](functions.html#operator_and), where each condition compares a key part with a constant value using [`=`](functions.html#operator_equal), [`<=>`](functions.html#operator_equal-to), [`IS NULL`](functions.html#operator_is-null), [`>`](functions.html#operator_greater-than), [`<`](functions.html#operator_less-than), [`>=`](functions.html#operator_greater-than-or-equal), [`<=`](functions.html#operator_less-than-or-equal), [`!=`](functions.html#operator_not-equal), [`<>`](functions.html#operator_not-equal), [`BETWEEN`](functions.html#operator_between), or [`LIKE '*`pattern`*'`](functions.html#operator_like) (where `'*`pattern`*'` does not start with a wildcard). An interval can be used as long as it is possible to determine a single key tuple containing all rows that match the condition (or two intervals if [`<>`](functions.html#operator_not-equal) or [`!=`](functions.html#operator_not-equal) is used).

  The optimizer attempts to use additional key parts to determine the interval as long as the comparison operator is [`=`](functions.html#operator_equal), [`<=>`](functions.html#operator_equal-to), or [`IS NULL`](functions.html#operator_is-null). If the operator is [`>`](functions.html#operator_greater-than), [`<`](functions.html#operator_less-than), [`>=`](functions.html#operator_greater-than-or-equal), [`<=`](functions.html#operator_less-than-or-equal), [`!=`](functions.html#operator_not-equal), [`<>`](functions.html#operator_not-equal), [`BETWEEN`](functions.html#operator_between), or [`LIKE`](functions.html#operator_like), the optimizer uses it but considers no more key parts. For the following expression, the optimizer uses [`=`](functions.html#operator_equal) from the first comparison. It also uses [`>=`](functions.html#operator_greater-than-or-equal) from the second comparison but considers no further key parts and does not use the third comparison for interval construction:

  ```
  key_part1 = 'foo' AND key_part2 >= 10 AND key_part3 > 10
  ```

  The single interval is:

  ```
  ('foo',10,-inf) < (key_part1,key_part2,key_part3) < ('foo',+inf,+inf)
  ```

  It is possible that the created interval contains more rows than the initial condition. For example, the preceding interval includes the value `('foo', 11, 0)`, which does not satisfy the original condition.

- If conditions that cover sets of rows contained within intervals are combined with [`OR`](functions.html#operator_or), they form a condition that covers a set of rows contained within the union of their intervals. If the conditions are combined with [`AND`](functions.html#operator_and), they form a condition that covers a set of rows contained within the intersection of their intervals. For example, for this condition on a two-part index:

  ```
  (key_part1 = 1 AND key_part2 < 2) OR (key_part1 > 5)
  ```

  The intervals are:

  ```
  (1,-inf) < (key_part1,key_part2) < (1,2)
  (5,-inf) < (key_part1,key_part2)
  ```

  In this example, the interval on the first line uses one key part for the left bound and two key parts for the right bound. The interval on the second line uses only one key part. The `key_len` column in the [`EXPLAIN`](sql-statements.html#explain) output indicates the maximum length of the key prefix used.

  In some cases, `key_len` may indicate that a key part was used, but that might be not what you would expect. Suppose that *`key_part1`* and *`key_part2`* can be `NULL`. Then the `key_len` column displays two key part lengths for the following condition:

  ```
  key_part1 >= 1 AND key_part2 < 2
  ```

  But, in fact, the condition is converted to this:

  ```
  key_part1 >= 1 AND key_part2 IS NOT NULL
  ```

For a description of how optimizations are performed to combine or eliminate intervals for range conditions on a single-part index, see [Range Access Method for Single-Part Indexes](optimization.html#range-access-single-part). Analogous steps are performed for range conditions on multiple-part indexes.

##### Equality Range Optimization of Many-Valued Comparisons

Consider these expressions, where *`col_name`* is an indexed column:

```
col_name IN(val1, ..., valN)
col_name = val1 OR ... OR col_name = valN
```

Each expression is true if *`col_name`* is equal to any of several values. These comparisons are equality range comparisons (where the “range” is a single value). The optimizer estimates the cost of reading qualifying rows for equality range comparisons as follows:

- If there is a unique index on *`col_name`*, the row estimate for each range is 1 because at most one row can have the given value.
- Otherwise, any index on *`col_name`* is nonunique and the optimizer can estimate the row count for each range using dives into the index or index statistics.



With index dives, the optimizer makes a dive at each end of a range and uses the number of rows in the range as the estimate. For example, the expression `*`col_name`* IN (10, 20, 30)` has three equality ranges and the optimizer makes two dives per range to generate a row estimate. Each pair of dives yields an estimate of the number of rows that have the given value.

Index dives provide accurate row estimates, but as the number of comparison values in the expression increases, the optimizer takes longer to generate a row estimate. Use of index statistics is less accurate than index dives but permits faster row estimation for large value lists.

The [`eq_range_index_dive_limit`](server-administration.html#sysvar_eq_range_index_dive_limit) system variable enables you to configure the number of values at which the optimizer switches from one row estimation strategy to the other. To permit use of index dives for comparisons of up to *`N`* equality ranges, set [`eq_range_index_dive_limit`](server-administration.html#sysvar_eq_range_index_dive_limit) to *`N`* + 1. To disable use of statistics and always use index dives regardless of *`N`*, set [`eq_range_index_dive_limit`](server-administration.html#sysvar_eq_range_index_dive_limit) to 0.

To update table index statistics for best estimates, use [`ANALYZE TABLE`](sql-statements.html#analyze-table).

Even under conditions when index dives would otherwise be used, they are skipped for queries that satisfy all these conditions:

- A single-index `FORCE INDEX` index hint is present. The idea is that if index use is forced, there is nothing to be gained from the additional overhead of performing dives into the index.
- The index is nonunique and not a `FULLTEXT` index.
- No subquery is present.
- No `DISTINCT`, `GROUP BY`, or `ORDER BY` clause is present.

Those dive-skipping conditions apply only for single-table queries. Index dives are not skipped for multiple-table queries (joins).

##### Range Optimization of Row Constructor Expressions

The optimizer is able to apply the range scan access method to queries of this form:

```
SELECT ... FROM t1 WHERE ( col_1, col_2 ) IN (( 'a', 'b' ), ( 'c', 'd' ));
```

Previously, for range scans to be used, it was necessary to write the query as:

```
SELECT ... FROM t1 WHERE ( col_1 = 'a' AND col_2 = 'b' )
OR ( col_1 = 'c' AND col_2 = 'd' );
```

For the optimizer to use a range scan, queries must satisfy these conditions:

- Only [`IN()`](functions.html#operator_in) predicates are used, not [`NOT IN()`](functions.html#operator_not-in).
- On the left side of the [`IN()`](functions.html#operator_in) predicate, the row constructor contains only column references.
- On the right side of the [`IN()`](functions.html#operator_in) predicate, row constructors contain only runtime constants, which are either literals or local column references that are bound to constants during execution.
- On the right side of the [`IN()`](functions.html#operator_in) predicate, there is more than one row constructor.

For more information about the optimizer and row constructors, see [Section 8.2.1.19, “Row Constructor Expression Optimization”](optimization.html#row-constructor-optimization)

##### Limiting Memory Use for Range Optimization

To control the memory available to the range optimizer, use the [`range_optimizer_max_mem_size`](server-administration.html#sysvar_range_optimizer_max_mem_size) system variable:

- A value of 0 means “no limit.”

- With a value greater than 0, the optimizer tracks the memory consumed when considering the range access method. If the specified limit is about to be exceeded, the range access method is abandoned and other methods, including a full table scan, are considered instead. This could be less optimal. If this happens, the following warning occurs (where *`N`* is the current [`range_optimizer_max_mem_size`](server-administration.html#sysvar_range_optimizer_max_mem_size) value):

  ```
  Warning    3170    Memory capacity of N bytes for
                     'range_optimizer_max_mem_size' exceeded. Range
                     optimization was not done for this query.
  ```

- For [`UPDATE`](sql-statements.html#update) and [`DELETE`](sql-statements.html#delete) statements, if the optimizer falls back to a full table scan and the [`sql_safe_updates`](server-administration.html#sysvar_sql_safe_updates) system variable is enabled, an error occurs rather than a warning because, in effect, no key is used to determine which rows to modify. For more information, see [Using Safe-Updates Mode (--safe-updates)](programs.html#safe-updates).

For individual queries that exceed the available range optimization memory and for which the optimizer falls back to less optimal plans, increasing the [`range_optimizer_max_mem_size`](server-administration.html#sysvar_range_optimizer_max_mem_size) value may improve performance.

To estimate the amount of memory needed to process a range expression, use these guidelines:

- For a simple query such as the following, where there is one candidate key for the range access method, each predicate combined with [`OR`](functions.html#operator_or) uses approximately 230 bytes:

  ```
  SELECT COUNT(*) FROM t
  WHERE a=1 OR a=2 OR a=3 OR .. . a=N;
  ```

- Similarly for a query such as the following, each predicate combined with [`AND`](functions.html#operator_and) uses approximately 125 bytes:

  ```
  SELECT COUNT(*) FROM t
  WHERE a=1 AND b=1 AND c=1 ... N;
  ```

- For a query with [`IN()`](functions.html#operator_in) predicates:

  ```
  SELECT COUNT(*) FROM t
  WHERE a IN (1,2, ..., M) AND b IN (1,2, ..., N);
  ```

  Each literal value in an [`IN()`](functions.html#operator_in) list counts as a predicate combined with [`OR`](functions.html#operator_or). If there are two [`IN()`](functions.html#operator_in) lists, the number of predicates combined with [`OR`](functions.html#operator_or) is the product of the number of literal values in each list. Thus, the number of predicates combined with [`OR`](functions.html#operator_or) in the preceding case is *`M`* × *`N`*.

Before 5.7.11, the number of bytes per predicate combined with [`OR`](functions.html#operator_or) was higher, approximately 700 bytes.

#### 8.2.1.3 Index Merge Optimization



The Index Merge access method retrieves rows with multiple [`range`](optimization.html#jointype_range) scans and merges their results into one. This access method merges index scans from a single table only, not scans across multiple tables. The merge can produce unions, intersections, or unions-of-intersections of its underlying scans.

Example queries for which Index Merge may be used:

```
SELECT * FROM tbl_name WHERE key1 = 10 OR key2 = 20;

SELECT * FROM tbl_name
  WHERE (key1 = 10 OR key2 = 20) AND non_key = 30;

SELECT * FROM t1, t2
  WHERE (t1.key1 IN (1,2) OR t1.key2 LIKE 'value%')
  AND t2.key1 = t1.some_col;

SELECT * FROM t1, t2
  WHERE t1.key1 = 1
  AND (t2.key1 = t1.some_col OR t2.key2 = t1.some_col2);
```

Note

The Index Merge optimization algorithm has the following known limitations:

- If your query has a complex `WHERE` clause with deep [`AND`](functions.html#operator_and)/[`OR`](functions.html#operator_or) nesting and MySQL does not choose the optimal plan, try distributing terms using the following identity transformations:

  ```
  (x AND y) OR z => (x OR z) AND (y OR z)
  (x OR y) AND z => (x AND z) OR (y AND z)
  ```

- Index Merge is not applicable to full-text indexes.

In [`EXPLAIN`](sql-statements.html#explain) output, the Index Merge method appears as [`index_merge`](optimization.html#jointype_index_merge) in the `type` column. In this case, the `key` column contains a list of indexes used, and `key_len` contains a list of the longest key parts for those indexes.

The Index Merge access method has several algorithms, which are displayed in the `Extra` field of [`EXPLAIN`](sql-statements.html#explain) output:

- `Using intersect(...)`
- `Using union(...)`
- `Using sort_union(...)`

The following sections describe these algorithms in greater detail. The optimizer chooses between different possible Index Merge algorithms and other access methods based on cost estimates of the various available options.

Use of Index Merge is subject to the value of the `index_merge`, `index_merge_intersection`, `index_merge_union`, and `index_merge_sort_union` flags of the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable. See [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations). By default, all those flags are `on`. To enable only certain algorithms, set `index_merge` to `off`, and enable only such of the others as should be permitted.

- [Index Merge Intersection Access Algorithm](optimization.html#index-merge-intersection)
- [Index Merge Union Access Algorithm](optimization.html#index-merge-union)
- [Index Merge Sort-Union Access Algorithm](optimization.html#index-merge-sort-union)

##### Index Merge Intersection Access Algorithm

This access algorithm is applicable when a `WHERE` clause is converted to several range conditions on different keys combined with [`AND`](functions.html#operator_and), and each condition is one of the following:

- An *`N`*-part expression of this form, where the index has exactly *`N`* parts (that is, all index parts are covered):

  ```
  key_part1 = const1 AND key_part2 = const2 ... AND key_partN = constN
  ```

- Any range condition over the primary key of an `InnoDB` table.

Examples:

```
SELECT * FROM innodb_table
  WHERE primary_key < 10 AND key_col1 = 20;

SELECT * FROM tbl_name
  WHERE key1_part1 = 1 AND key1_part2 = 2 AND key2 = 2;
```

The Index Merge intersection algorithm performs simultaneous scans on all used indexes and produces the intersection of row sequences that it receives from the merged index scans.

If all columns used in the query are covered by the used indexes, full table rows are not retrieved ([`EXPLAIN`](sql-statements.html#explain) output contains `Using index` in `Extra` field in this case). Here is an example of such a query:

```
SELECT COUNT(*) FROM t1 WHERE key1 = 1 AND key2 = 1;
```

If the used indexes do not cover all columns used in the query, full rows are retrieved only when the range conditions for all used keys are satisfied.

If one of the merged conditions is a condition over the primary key of an `InnoDB` table, it is not used for row retrieval, but is used to filter out rows retrieved using other conditions.

##### Index Merge Union Access Algorithm

The criteria for this algorithm are similar to those for the Index Merge intersection algorithm. The algorithm is applicable when the table's `WHERE` clause is converted to several range conditions on different keys combined with [`OR`](functions.html#operator_or), and each condition is one of the following:

- An *`N`*-part expression of this form, where the index has exactly *`N`* parts (that is, all index parts are covered):

  ```
  key_part1 = const1 AND key_part2 = const2 ... AND key_partN = constN
  ```

- Any range condition over a primary key of an `InnoDB` table.

- A condition for which the Index Merge intersection algorithm is applicable.

Examples:

```
SELECT * FROM t1
  WHERE key1 = 1 OR key2 = 2 OR key3 = 3;

SELECT * FROM innodb_table
  WHERE (key1 = 1 AND key2 = 2)
     OR (key3 = 'foo' AND key4 = 'bar') AND key5 = 5;
```

##### Index Merge Sort-Union Access Algorithm

This access algorithm is applicable when the `WHERE` clause is converted to several range conditions combined by [`OR`](functions.html#operator_or), but the Index Merge union algorithm is not applicable.

Examples:

```
SELECT * FROM tbl_name
  WHERE key_col1 < 10 OR key_col2 < 20;

SELECT * FROM tbl_name
  WHERE (key_col1 > 10 OR key_col2 = 20) AND nonkey_col = 30;
```

The difference between the sort-union algorithm and the union algorithm is that the sort-union algorithm must first fetch row IDs for all rows and sort them before returning any rows.

#### 8.2.1.4 Engine Condition Pushdown Optimization



This optimization improves the efficiency of direct comparisons between a nonindexed column and a constant. In such cases, the condition is “pushed down” to the storage engine for evaluation. This optimization can be used only by the [`NDB`](mysql-cluster.html) storage engine.

For NDB Cluster, this optimization can eliminate the need to send nonmatching rows over the network between the cluster's data nodes and the MySQL server that issued the query, and can speed up queries where it is used by a factor of 5 to 10 times over cases where condition pushdown could be but is not used.

Suppose that an NDB Cluster table is defined as follows:

```
CREATE TABLE t1 (
    a INT,
    b INT,
    KEY(a)
) ENGINE=NDB;
```

Condition pushdown can be used with queries such as the one shown here, which includes a comparison between a nonindexed column and a constant:

```
SELECT a, b FROM t1 WHERE b = 10;
```

The use of condition pushdown can be seen in the output of [`EXPLAIN`](sql-statements.html#explain):

```
mysql> EXPLAIN SELECT a,b FROM t1 WHERE b = 10\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: t1
         type: ALL
possible_keys: NULL
          key: NULL
      key_len: NULL
          ref: NULL
         rows: 10
        Extra: Using where with pushed condition
```

However, condition pushdown *cannot* be used with either of these two queries:

```
SELECT a,b FROM t1 WHERE a = 10;
SELECT a,b FROM t1 WHERE b + 1 = 10;
```

Condition pushdown is not applicable to the first query because an index exists on column `a`. (An index access method would be more efficient and so would be chosen in preference to condition pushdown.) Condition pushdown cannot be employed for the second query because the comparison involving the nonindexed column `b` is indirect. (However, condition pushdown could be applied if you were to reduce `b + 1 = 10` to `b = 9` in the `WHERE` clause.)

Condition pushdown may also be employed when an indexed column is compared with a constant using a `>` or `<` operator:

```
mysql> EXPLAIN SELECT a, b FROM t1 WHERE a < 2\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: t1
         type: range
possible_keys: a
          key: a
      key_len: 5
          ref: NULL
         rows: 2
        Extra: Using where with pushed condition
```

Other supported comparisons for condition pushdown include the following:

- `*`column`* [NOT] LIKE *`pattern`*`

  *`pattern`* must be a string literal containing the pattern to be matched; for syntax, see [Section 12.7.1, “String Comparison Functions and Operators”](functions.html#string-comparison-functions).

- `*`column`* IS [NOT] NULL`

- `*`column`* IN (*`value_list`*)`

  Each item in the *`value_list`* must be a constant, literal value.

- `*`column`* BETWEEN *`constant1`* AND *`constant2`*`

  *`constant1`* and *`constant2`* must each be a constant, literal value.

In all of the cases in the preceding list, it is possible for the condition to be converted into the form of one or more direct comparisons between a column and a constant.

Engine condition pushdown is enabled by default. To disable it at server startup, set the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable. For example, in a `my.cnf` file, use these lines:

```
[mysqld]
optimizer_switch=engine_condition_pushdown=off
```

At runtime, disable condition pushdown like this:

```
SET optimizer_switch='engine_condition_pushdown=off';
```

**Limitations.** Engine condition pushdown is subject to the following limitations:

- Condition pushdown is supported only by the [`NDB`](mysql-cluster.html) storage engine.
- Columns may be compared with constants only; however, this includes expressions which evaluate to constant values.
- Columns used in comparisons cannot be of any of the [`BLOB`](data-types.html#blob) or [`TEXT`](data-types.html#blob) types. This exclusion extends to [`JSON`](data-types.html#json), [`BIT`](data-types.html#bit-type), and [`ENUM`](data-types.html#enum) columns as well.
- A string value to be compared with a column must use the same collation as the column.
- Joins are not directly supported; conditions involving multiple tables are pushed separately where possible. Use extended [`EXPLAIN`](sql-statements.html#explain) output to determine which conditions are actually pushed down. See [Section 8.8.3, “Extended EXPLAIN Output Format”](optimization.html#explain-extended).

#### 8.2.1.5 Index Condition Pushdown Optimization

Index Condition Pushdown (ICP) is an optimization for the case where MySQL retrieves rows from a table using an index. Without ICP, the storage engine traverses the index to locate rows in the base table and returns them to the MySQL server which evaluates the `WHERE` condition for the rows. With ICP enabled, and if parts of the `WHERE` condition can be evaluated by using only columns from the index, the MySQL server pushes this part of the `WHERE` condition down to the storage engine. The storage engine then evaluates the pushed index condition by using the index entry and only if this is satisfied is the row read from the table. ICP can reduce the number of times the storage engine must access the base table and the number of times the MySQL server must access the storage engine.

Applicability of the Index Condition Pushdown optimization is subject to these conditions:

- ICP is used for the [`range`](optimization.html#jointype_range), [`ref`](optimization.html#jointype_ref), [`eq_ref`](optimization.html#jointype_eq_ref), and [`ref_or_null`](optimization.html#jointype_ref_or_null) access methods when there is a need to access full table rows.
- ICP can be used for [`InnoDB`](innodb-storage-engine.html) and [`MyISAM`](storage-engines.html#myisam-storage-engine) tables, including partitioned `InnoDB` and `MyISAM` tables.
- For `InnoDB` tables, ICP is used only for secondary indexes. The goal of ICP is to reduce the number of full-row reads and thereby reduce I/O operations. For `InnoDB` clustered indexes, the complete record is already read into the `InnoDB` buffer. Using ICP in this case does not reduce I/O.
- ICP is not supported with secondary indexes created on virtual generated columns. `InnoDB` supports secondary indexes on virtual generated columns.
- Conditions that refer to subqueries cannot be pushed down.
- Conditions that refer to stored functions cannot be pushed down. Storage engines cannot invoke stored functions.
- Triggered conditions cannot be pushed down. (For information about triggered conditions, see [Section 8.2.2.3, “Optimizing Subqueries with the EXISTS Strategy”](optimization.html#subquery-optimization-with-exists).)

To understand how this optimization works, first consider how an index scan proceeds when Index Condition Pushdown is not used:

1. Get the next row, first by reading the index tuple, and then by using the index tuple to locate and read the full table row.
2. Test the part of the `WHERE` condition that applies to this table. Accept or reject the row based on the test result.

Using Index Condition Pushdown, the scan proceeds like this instead:

1. Get the next row's index tuple (but not the full table row).
2. Test the part of the `WHERE` condition that applies to this table and can be checked using only index columns. If the condition is not satisfied, proceed to the index tuple for the next row.
3. If the condition is satisfied, use the index tuple to locate and read the full table row.
4. Test the remaining part of the `WHERE` condition that applies to this table. Accept or reject the row based on the test result.

[`EXPLAIN`](sql-statements.html#explain) output shows `Using index condition` in the `Extra` column when Index Condition Pushdown is used. It does not show `Using index` because that does not apply when full table rows must be read.

Suppose that a table contains information about people and their addresses and that the table has an index defined as `INDEX (zipcode, lastname, firstname)`. If we know a person's `zipcode` value but are not sure about the last name, we can search like this:

```
SELECT * FROM people
  WHERE zipcode='95054'
  AND lastname LIKE '%etrunia%'
  AND address LIKE '%Main Street%';
```

MySQL can use the index to scan through people with `zipcode='95054'`. The second part (`lastname LIKE '%etrunia%'`) cannot be used to limit the number of rows that must be scanned, so without Index Condition Pushdown, this query must retrieve full table rows for all people who have `zipcode='95054'`.

With Index Condition Pushdown, MySQL checks the `lastname LIKE '%etrunia%'` part before reading the full table row. This avoids reading full rows corresponding to index tuples that match the `zipcode` condition but not the `lastname` condition.

Index Condition Pushdown is enabled by default. It can be controlled with the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable by setting the `index_condition_pushdown` flag:

```
SET optimizer_switch = 'index_condition_pushdown=off';
SET optimizer_switch = 'index_condition_pushdown=on';
```

See [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations).

#### 8.2.1.6 Nested-Loop Join Algorithms



MySQL executes joins between tables using a nested-loop algorithm or variations on it.

- [Nested-Loop Join Algorithm](optimization.html#nested-loop-join-algorithm)
- [Block Nested-Loop Join Algorithm](optimization.html#block-nested-loop-join-algorithm)

##### Nested-Loop Join Algorithm

A simple nested-loop join (NLJ) algorithm reads rows from the first table in a loop one at a time, passing each row to a nested loop that processes the next table in the join. This process is repeated as many times as there remain tables to be joined.

Assume that a join between three tables `t1`, `t2`, and `t3` is to be executed using the following join types:

```
Table   Join Type
t1      range
t2      ref
t3      ALL
```

If a simple NLJ algorithm is used, the join is processed like this:

```
for each row in t1 matching range {
  for each row in t2 matching reference key {
    for each row in t3 {
      if row satisfies join conditions, send to client
    }
  }
}
```

Because the NLJ algorithm passes rows one at a time from outer loops to inner loops, it typically reads tables processed in the inner loops many times.

##### Block Nested-Loop Join Algorithm

A Block Nested-Loop (BNL) join algorithm uses buffering of rows read in outer loops to reduce the number of times that tables in inner loops must be read. For example, if 10 rows are read into a buffer and the buffer is passed to the next inner loop, each row read in the inner loop can be compared against all 10 rows in the buffer. This reduces by an order of magnitude the number of times the inner table must be read.

MySQL join buffering has these characteristics:

- Join buffering can be used when the join is of type [`ALL`](optimization.html#jointype_all) or [`index`](optimization.html#jointype_index) (in other words, when no possible keys can be used, and a full scan is done, of either the data or index rows, respectively), or [`range`](optimization.html#jointype_range). Use of buffering is also applicable to outer joins, as described in [Section 8.2.1.11, “Block Nested-Loop and Batched Key Access Joins”](optimization.html#bnl-bka-optimization).
- A join buffer is never allocated for the first nonconstant table, even if it would be of type [`ALL`](optimization.html#jointype_all) or [`index`](optimization.html#jointype_index).
- Only columns of interest to a join are stored in its join buffer, not whole rows.
- The [`join_buffer_size`](server-administration.html#sysvar_join_buffer_size) system variable determines the size of each join buffer used to process a query.
- One buffer is allocated for each join that can be buffered, so a given query might be processed using multiple join buffers.
- A join buffer is allocated prior to executing the join and freed after the query is done.

For the example join described previously for the NLJ algorithm (without buffering), the join is done as follows using join buffering:

```
for each row in t1 matching range {
  for each row in t2 matching reference key {
    store used columns from t1, t2 in join buffer
    if buffer is full {
      for each row in t3 {
        for each t1, t2 combination in join buffer {
          if row satisfies join conditions, send to client
        }
      }
      empty join buffer
    }
  }
}

if buffer is not empty {
  for each row in t3 {
    for each t1, t2 combination in join buffer {
      if row satisfies join conditions, send to client
    }
  }
}
```

If *`S`* is the size of each stored `t1`, `t2` combination in the join buffer and *`C`* is the number of combinations in the buffer, the number of times table `t3` is scanned is:

```
(S * C)/join_buffer_size + 1
```

The number of `t3` scans decreases as the value of [`join_buffer_size`](server-administration.html#sysvar_join_buffer_size) increases, up to the point when [`join_buffer_size`](server-administration.html#sysvar_join_buffer_size) is large enough to hold all previous row combinations. At that point, no speed is gained by making it larger.

#### 8.2.1.7 Nested Join Optimization

The syntax for expressing joins permits nested joins. The following discussion refers to the join syntax described in [Section 13.2.9.2, “JOIN Clause”](sql-statements.html#join).

The syntax of *`table_factor`* is extended in comparison with the SQL Standard. The latter accepts only *`table_reference`*, not a list of them inside a pair of parentheses. This is a conservative extension if we consider each comma in a list of *`table_reference`* items as equivalent to an inner join. For example:

```
SELECT * FROM t1 LEFT JOIN (t2, t3, t4)
                 ON (t2.a=t1.a AND t3.b=t1.b AND t4.c=t1.c)
```

Is equivalent to:

```
SELECT * FROM t1 LEFT JOIN (t2 CROSS JOIN t3 CROSS JOIN t4)
                 ON (t2.a=t1.a AND t3.b=t1.b AND t4.c=t1.c)
```

In MySQL, `CROSS JOIN` is syntactically equivalent to `INNER JOIN`; they can replace each other. In standard SQL, they are not equivalent. `INNER JOIN` is used with an `ON` clause; `CROSS JOIN` is used otherwise.

In general, parentheses can be ignored in join expressions containing only inner join operations. Consider this join expression:

```
t1 LEFT JOIN (t2 LEFT JOIN t3 ON t2.b=t3.b OR t2.b IS NULL)
   ON t1.a=t2.a
```

After removing parentheses and grouping operations to the left, that join expression transforms into this expression:

```
(t1 LEFT JOIN t2 ON t1.a=t2.a) LEFT JOIN t3
    ON t2.b=t3.b OR t2.b IS NULL
```

Yet, the two expressions are not equivalent. To see this, suppose that the tables `t1`, `t2`, and `t3` have the following state:

- Table `t1` contains rows `(1)`, `(2)`
- Table `t2` contains row `(1,101)`
- Table `t3` contains row `(101)`

In this case, the first expression returns a result set including the rows `(1,1,101,101)`, `(2,NULL,NULL,NULL)`, whereas the second expression returns the rows `(1,1,101,101)`, `(2,NULL,NULL,101)`:

```
mysql> SELECT *
       FROM t1
            LEFT JOIN
            (t2 LEFT JOIN t3 ON t2.b=t3.b OR t2.b IS NULL)
            ON t1.a=t2.a;
+------+------+------+------+
| a    | a    | b    | b    |
+------+------+------+------+
|    1 |    1 |  101 |  101 |
|    2 | NULL | NULL | NULL |
+------+------+------+------+

mysql> SELECT *
       FROM (t1 LEFT JOIN t2 ON t1.a=t2.a)
            LEFT JOIN t3
            ON t2.b=t3.b OR t2.b IS NULL;
+------+------+------+------+
| a    | a    | b    | b    |
+------+------+------+------+
|    1 |    1 |  101 |  101 |
|    2 | NULL | NULL |  101 |
+------+------+------+------+
```

In the following example, an outer join operation is used together with an inner join operation:

```
t1 LEFT JOIN (t2, t3) ON t1.a=t2.a
```

That expression cannot be transformed into the following expression:

```
t1 LEFT JOIN t2 ON t1.a=t2.a, t3
```

For the given table states, the two expressions return different sets of rows:

```
mysql> SELECT *
       FROM t1 LEFT JOIN (t2, t3) ON t1.a=t2.a;
+------+------+------+------+
| a    | a    | b    | b    |
+------+------+------+------+
|    1 |    1 |  101 |  101 |
|    2 | NULL | NULL | NULL |
+------+------+------+------+

mysql> SELECT *
       FROM t1 LEFT JOIN t2 ON t1.a=t2.a, t3;
+------+------+------+------+
| a    | a    | b    | b    |
+------+------+------+------+
|    1 |    1 |  101 |  101 |
|    2 | NULL | NULL |  101 |
+------+------+------+------+
```

Therefore, if we omit parentheses in a join expression with outer join operators, we might change the result set for the original expression.

More exactly, we cannot ignore parentheses in the right operand of the left outer join operation and in the left operand of a right join operation. In other words, we cannot ignore parentheses for the inner table expressions of outer join operations. Parentheses for the other operand (operand for the outer table) can be ignored.

The following expression:

```
(t1,t2) LEFT JOIN t3 ON P(t2.b,t3.b)
```

Is equivalent to this expression for any tables `t1,t2,t3` and any condition `P` over attributes `t2.b` and `t3.b`:

```
t1, t2 LEFT JOIN t3 ON P(t2.b,t3.b)
```

Whenever the order of execution of join operations in a join expression (*`joined_table`*) is not from left to right, we talk about nested joins. Consider the following queries:

```
SELECT * FROM t1 LEFT JOIN (t2 LEFT JOIN t3 ON t2.b=t3.b) ON t1.a=t2.a
  WHERE t1.a > 1

SELECT * FROM t1 LEFT JOIN (t2, t3) ON t1.a=t2.a
  WHERE (t2.b=t3.b OR t2.b IS NULL) AND t1.a > 1
```

Those queries are considered to contain these nested joins:

```
t2 LEFT JOIN t3 ON t2.b=t3.b
t2, t3
```

In the first query, the nested join is formed with a left join operation. In the second query, it is formed with an inner join operation.

In the first query, the parentheses can be omitted: The grammatical structure of the join expression will dictate the same order of execution for join operations. For the second query, the parentheses cannot be omitted, although the join expression here can be interpreted unambiguously without them. In our extended syntax, the parentheses in `(t2, t3)` of the second query are required, although theoretically the query could be parsed without them: We still would have unambiguous syntactical structure for the query because `LEFT JOIN` and `ON` play the role of the left and right delimiters for the expression `(t2,t3)`.

The preceding examples demonstrate these points:

- For join expressions involving only inner joins (and not outer joins), parentheses can be removed and joins evaluated left to right. In fact, tables can be evaluated in any order.
- The same is not true, in general, for outer joins or for outer joins mixed with inner joins. Removal of parentheses may change the result.



Queries with nested outer joins are executed in the same pipeline manner as queries with inner joins. More exactly, a variation of the nested-loop join algorithm is exploited. Recall the algorithm by which the nested-loop join executes a query (see [Section 8.2.1.6, “Nested-Loop Join Algorithms”](optimization.html#nested-loop-joins)). Suppose that a join query over 3 tables `T1,T2,T3` has this form:

```
SELECT * FROM T1 INNER JOIN T2 ON P1(T1,T2)
                 INNER JOIN T3 ON P2(T2,T3)
  WHERE P(T1,T2,T3)
```

Here, `P1(T1,T2)` and `P2(T3,T3)` are some join conditions (on expressions), whereas `P(T1,T2,T3)` is a condition over columns of tables `T1,T2,T3`.

The nested-loop join algorithm would execute this query in the following manner:

```
FOR each row t1 in T1 {
  FOR each row t2 in T2 such that P1(t1,t2) {
    FOR each row t3 in T3 such that P2(t2,t3) {
      IF P(t1,t2,t3) {
         t:=t1||t2||t3; OUTPUT t;
      }
    }
  }
}
```

The notation `t1||t2||t3` indicates a row constructed by concatenating the columns of rows `t1`, `t2`, and `t3`. In some of the following examples, `NULL` where a table name appears means a row in which `NULL` is used for each column of that table. For example, `t1||t2||NULL` indicates a row constructed by concatenating the columns of rows `t1` and `t2`, and `NULL` for each column of `t3`. Such a row is said to be `NULL`-complemented.



Now consider a query with nested outer joins:

```
SELECT * FROM T1 LEFT JOIN
              (T2 LEFT JOIN T3 ON P2(T2,T3))
              ON P1(T1,T2)
  WHERE P(T1,T2,T3)
```

For this query, modify the nested-loop pattern to obtain:

```
FOR each row t1 in T1 {
  BOOL f1:=FALSE;
  FOR each row t2 in T2 such that P1(t1,t2) {
    BOOL f2:=FALSE;
    FOR each row t3 in T3 such that P2(t2,t3) {
      IF P(t1,t2,t3) {
        t:=t1||t2||t3; OUTPUT t;
      }
      f2=TRUE;
      f1=TRUE;
    }
    IF (!f2) {
      IF P(t1,t2,NULL) {
        t:=t1||t2||NULL; OUTPUT t;
      }
      f1=TRUE;
    }
  }
  IF (!f1) {
    IF P(t1,NULL,NULL) {
      t:=t1||NULL||NULL; OUTPUT t;
    }
  }
}
```

In general, for any nested loop for the first inner table in an outer join operation, a flag is introduced that is turned off before the loop and is checked after the loop. The flag is turned on when for the current row from the outer table a match from the table representing the inner operand is found. If at the end of the loop cycle the flag is still off, no match has been found for the current row of the outer table. In this case, the row is complemented by `NULL` values for the columns of the inner tables. The result row is passed to the final check for the output or into the next nested loop, but only if the row satisfies the join condition of all embedded outer joins.

In the example, the outer join table expressed by the following expression is embedded:

```
(T2 LEFT JOIN T3 ON P2(T2,T3))
```

For the query with inner joins, the optimizer could choose a different order of nested loops, such as this one:

```
FOR each row t3 in T3 {
  FOR each row t2 in T2 such that P2(t2,t3) {
    FOR each row t1 in T1 such that P1(t1,t2) {
      IF P(t1,t2,t3) {
         t:=t1||t2||t3; OUTPUT t;
      }
    }
  }
}
```

For queries with outer joins, the optimizer can choose only such an order where loops for outer tables precede loops for inner tables. Thus, for our query with outer joins, only one nesting order is possible. For the following query, the optimizer evaluates two different nestings. In both nestings, `T1` must be processed in the outer loop because it is used in an outer join. `T2` and `T3` are used in an inner join, so that join must be processed in the inner loop. However, because the join is an inner join, `T2` and `T3` can be processed in either order.

```
SELECT * T1 LEFT JOIN (T2,T3) ON P1(T1,T2) AND P2(T1,T3)
  WHERE P(T1,T2,T3)
```

One nesting evaluates `T2`, then `T3`:

```
FOR each row t1 in T1 {
  BOOL f1:=FALSE;
  FOR each row t2 in T2 such that P1(t1,t2) {
    FOR each row t3 in T3 such that P2(t1,t3) {
      IF P(t1,t2,t3) {
        t:=t1||t2||t3; OUTPUT t;
      }
      f1:=TRUE
    }
  }
  IF (!f1) {
    IF P(t1,NULL,NULL) {
      t:=t1||NULL||NULL; OUTPUT t;
    }
  }
}
```

The other nesting evaluates `T3`, then `T2`:

```
FOR each row t1 in T1 {
  BOOL f1:=FALSE;
  FOR each row t3 in T3 such that P2(t1,t3) {
    FOR each row t2 in T2 such that P1(t1,t2) {
      IF P(t1,t2,t3) {
        t:=t1||t2||t3; OUTPUT t;
      }
      f1:=TRUE
    }
  }
  IF (!f1) {
    IF P(t1,NULL,NULL) {
      t:=t1||NULL||NULL; OUTPUT t;
    }
  }
}
```

When discussing the nested-loop algorithm for inner joins, we omitted some details whose impact on the performance of query execution may be huge. We did not mention so-called “pushed-down” conditions. Suppose that our `WHERE` condition `P(T1,T2,T3)` can be represented by a conjunctive formula:

```
P(T1,T2,T2) = C1(T1) AND C2(T2) AND C3(T3).
```

In this case, MySQL actually uses the following nested-loop algorithm for the execution of the query with inner joins:

```
FOR each row t1 in T1 such that C1(t1) {
  FOR each row t2 in T2 such that P1(t1,t2) AND C2(t2)  {
    FOR each row t3 in T3 such that P2(t2,t3) AND C3(t3) {
      IF P(t1,t2,t3) {
         t:=t1||t2||t3; OUTPUT t;
      }
    }
  }
}
```

You see that each of the conjuncts `C1(T1)`, `C2(T2)`, `C3(T3)` are pushed out of the most inner loop to the most outer loop where it can be evaluated. If `C1(T1)` is a very restrictive condition, this condition pushdown may greatly reduce the number of rows from table `T1` passed to the inner loops. As a result, the execution time for the query may improve immensely.

For a query with outer joins, the `WHERE` condition is to be checked only after it has been found that the current row from the outer table has a match in the inner tables. Thus, the optimization of pushing conditions out of the inner nested loops cannot be applied directly to queries with outer joins. Here we must introduce conditional pushed-down predicates guarded by the flags that are turned on when a match has been encountered.

Recall this example with outer joins:

```
P(T1,T2,T3)=C1(T1) AND C(T2) AND C3(T3)
```

For that example, the nested-loop algorithm using guarded pushed-down conditions looks like this:

```
FOR each row t1 in T1 such that C1(t1) {
  BOOL f1:=FALSE;
  FOR each row t2 in T2
      such that P1(t1,t2) AND (f1?C2(t2):TRUE) {
    BOOL f2:=FALSE;
    FOR each row t3 in T3
        such that P2(t2,t3) AND (f1&&f2?C3(t3):TRUE) {
      IF (f1&&f2?TRUE:(C2(t2) AND C3(t3))) {
        t:=t1||t2||t3; OUTPUT t;
      }
      f2=TRUE;
      f1=TRUE;
    }
    IF (!f2) {
      IF (f1?TRUE:C2(t2) && P(t1,t2,NULL)) {
        t:=t1||t2||NULL; OUTPUT t;
      }
      f1=TRUE;
    }
  }
  IF (!f1 && P(t1,NULL,NULL)) {
      t:=t1||NULL||NULL; OUTPUT t;
  }
}
```

In general, pushed-down predicates can be extracted from join conditions such as `P1(T1,T2)` and `P(T2,T3)`. In this case, a pushed-down predicate is guarded also by a flag that prevents checking the predicate for the `NULL`-complemented row generated by the corresponding outer join operation.

Access by key from one inner table to another in the same nested join is prohibited if it is induced by a predicate from the `WHERE` condition.

#### 8.2.1.8 Outer Join Optimization



Outer joins include `LEFT JOIN` and `RIGHT JOIN`.

MySQL implements an `*`A`* LEFT JOIN *`B`* *`join_specification`*` as follows:

- Table *`B`* is set to depend on table *`A`* and all tables on which *`A`* depends.
- Table *`A`* is set to depend on all tables (except *`B`*) that are used in the `LEFT JOIN` condition.
- The `LEFT JOIN` condition is used to decide how to retrieve rows from table *`B`*. (In other words, any condition in the `WHERE` clause is not used.)
- All standard join optimizations are performed, with the exception that a table is always read after all tables on which it depends. If there is a circular dependency, an error occurs.
- All standard `WHERE` optimizations are performed.
- If there is a row in *`A`* that matches the `WHERE` clause, but there is no row in *`B`* that matches the `ON` condition, an extra *`B`* row is generated with all columns set to `NULL`.
- If you use `LEFT JOIN` to find rows that do not exist in some table and you have the following test: `*`col_name`* IS NULL` in the `WHERE` part, where *`col_name`* is a column that is declared as `NOT NULL`, MySQL stops searching for more rows (for a particular key combination) after it has found one row that matches the `LEFT JOIN` condition.

The `RIGHT JOIN` implementation is analogous to that of `LEFT JOIN` with the table roles reversed. Right joins are converted to equivalent left joins, as described in [Section 8.2.1.9, “Outer Join Simplification”](optimization.html#outer-join-simplification).

For a `LEFT JOIN`, if the `WHERE` condition is always false for the generated `NULL` row, the `LEFT JOIN` is changed to an inner join. For example, the `WHERE` clause would be false in the following query if `t2.column1` were `NULL`:

```
SELECT * FROM t1 LEFT JOIN t2 ON (column1) WHERE t2.column2=5;
```

Therefore, it is safe to convert the query to an inner join:

```
SELECT * FROM t1, t2 WHERE t2.column2=5 AND t1.column1=t2.column1;
```



Now the optimizer can use table `t2` before table `t1` if doing so would result in a better query plan. To provide a hint about the table join order, use `STRAIGHT_JOIN`; see [Section 13.2.9, “SELECT Statement”](sql-statements.html#select). However, `STRAIGHT_JOIN` may prevent indexes from being used because it disables semijoin transformations; see [Section 8.2.2.1, “Optimizing Subqueries, Derived Tables, and View References with Semijoin Transformations”](optimization.html#semijoins).

#### 8.2.1.9 Outer Join Simplification

Table expressions in the `FROM` clause of a query are simplified in many cases.

At the parser stage, queries with right outer join operations are converted to equivalent queries containing only left join operations. In the general case, the conversion is performed such that this right join:

```
(T1, ...) RIGHT JOIN (T2, ...) ON P(T1, ..., T2, ...)
```

Becomes this equivalent left join:

```
(T2, ...) LEFT JOIN (T1, ...) ON P(T1, ..., T2, ...)
```

All inner join expressions of the form `T1 INNER JOIN T2 ON P(T1,T2)` are replaced by the list `T1,T2`, `P(T1,T2)` being joined as a conjunct to the `WHERE` condition (or to the join condition of the embedding join, if there is any).

When the optimizer evaluates plans for outer join operations, it takes into consideration only plans where, for each such operation, the outer tables are accessed before the inner tables. The optimizer choices are limited because only such plans enable outer joins to be executed using the nested-loop algorithm.

Consider a query of this form, where `R(T2)` greatly narrows the number of matching rows from table `T2`:

```
SELECT * T1 LEFT JOIN T2 ON P1(T1,T2)
  WHERE P(T1,T2) AND R(T2)
```

If the query is executed as written, the optimizer has no choice but to access the less-restricted table `T1` before the more-restricted table `T2`, which may produce a very inefficient execution plan.

Instead, MySQL converts the query to a query with no outer join operation if the `WHERE` condition is null-rejected. (That is, it converts the outer join to an inner join.) A condition is said to be null-rejected for an outer join operation if it evaluates to `FALSE` or `UNKNOWN` for any `NULL`-complemented row generated for the operation.



Thus, for this outer join:

```
T1 LEFT JOIN T2 ON T1.A=T2.A
```

Conditions such as these are null-rejected because they cannot be true for any `NULL`-complemented row (with `T2` columns set to `NULL`):

```
T2.B IS NOT NULL
T2.B > 3
T2.C <= T1.C
T2.B < 2 OR T2.C > 1
```

Conditions such as these are not null-rejected because they might be true for a `NULL`-complemented row:

```
T2.B IS NULL
T1.B < 3 OR T2.B IS NOT NULL
T1.B < 3 OR T2.B > 3
```

The general rules for checking whether a condition is null-rejected for an outer join operation are simple:

- It is of the form `A IS NOT NULL`, where `A` is an attribute of any of the inner tables
- It is a predicate containing a reference to an inner table that evaluates to `UNKNOWN` when one of its arguments is `NULL`
- It is a conjunction containing a null-rejected condition as a conjunct
- It is a disjunction of null-rejected conditions

A condition can be null-rejected for one outer join operation in a query and not null-rejected for another. In this query, the `WHERE` condition is null-rejected for the second outer join operation but is not null-rejected for the first one:

```
SELECT * FROM T1 LEFT JOIN T2 ON T2.A=T1.A
                 LEFT JOIN T3 ON T3.B=T1.B
  WHERE T3.C > 0
```

If the `WHERE` condition is null-rejected for an outer join operation in a query, the outer join operation is replaced by an inner join operation.

For example, in the preceding query, the second outer join is null-rejected and can be replaced by an inner join:

```
SELECT * FROM T1 LEFT JOIN T2 ON T2.A=T1.A
                 INNER JOIN T3 ON T3.B=T1.B
  WHERE T3.C > 0
```

For the original query, the optimizer evaluates only plans compatible with the single table-access order `T1,T2,T3`. For the rewritten query, it additionally considers the access order `T3,T1,T2`.

A conversion of one outer join operation may trigger a conversion of another. Thus, the query:

```
SELECT * FROM T1 LEFT JOIN T2 ON T2.A=T1.A
                 LEFT JOIN T3 ON T3.B=T2.B
  WHERE T3.C > 0
```

Is first converted to the query:

```
SELECT * FROM T1 LEFT JOIN T2 ON T2.A=T1.A
                 INNER JOIN T3 ON T3.B=T2.B
  WHERE T3.C > 0
```

Which is equivalent to the query:

```
SELECT * FROM (T1 LEFT JOIN T2 ON T2.A=T1.A), T3
  WHERE T3.C > 0 AND T3.B=T2.B
```

The remaining outer join operation can also be replaced by an inner join because the condition `T3.B=T2.B` is null-rejected. This results in a query with no outer joins at all:

```
SELECT * FROM (T1 INNER JOIN T2 ON T2.A=T1.A), T3
  WHERE T3.C > 0 AND T3.B=T2.B
```

Sometimes the optimizer succeeds in replacing an embedded outer join operation, but cannot convert the embedding outer join. The following query:

```
SELECT * FROM T1 LEFT JOIN
              (T2 LEFT JOIN T3 ON T3.B=T2.B)
              ON T2.A=T1.A
  WHERE T3.C > 0
```

Is converted to:

```
SELECT * FROM T1 LEFT JOIN
              (T2 INNER JOIN T3 ON T3.B=T2.B)
              ON T2.A=T1.A
  WHERE T3.C > 0
```

That can be rewritten only to the form still containing the embedding outer join operation:

```
SELECT * FROM T1 LEFT JOIN
              (T2,T3)
              ON (T2.A=T1.A AND T3.B=T2.B)
  WHERE T3.C > 0
```

Any attempt to convert an embedded outer join operation in a query must take into account the join condition for the embedding outer join together with the `WHERE` condition. In this query, the `WHERE` condition is not null-rejected for the embedded outer join, but the join condition of the embedding outer join `T2.A=T1.A AND T3.C=T1.C` is null-rejected:

```
SELECT * FROM T1 LEFT JOIN
              (T2 LEFT JOIN T3 ON T3.B=T2.B)
              ON T2.A=T1.A AND T3.C=T1.C
  WHERE T3.D > 0 OR T1.D > 0
```

Consequently, the query can be converted to:

```
SELECT * FROM T1 LEFT JOIN
              (T2, T3)
              ON T2.A=T1.A AND T3.C=T1.C AND T3.B=T2.B
  WHERE T3.D > 0 OR T1.D > 0
```

#### 8.2.1.10 Multi-Range Read Optimization



Reading rows using a range scan on a secondary index can result in many random disk accesses to the base table when the table is large and not stored in the storage engine's cache. With the Disk-Sweep Multi-Range Read (MRR) optimization, MySQL tries to reduce the number of random disk access for range scans by first scanning the index only and collecting the keys for the relevant rows. Then the keys are sorted and finally the rows are retrieved from the base table using the order of the primary key. The motivation for Disk-sweep MRR is to reduce the number of random disk accesses and instead achieve a more sequential scan of the base table data.

The Multi-Range Read optimization provides these benefits:

- MRR enables data rows to be accessed sequentially rather than in random order, based on index tuples. The server obtains a set of index tuples that satisfy the query conditions, sorts them according to data row ID order, and uses the sorted tuples to retrieve data rows in order. This makes data access more efficient and less expensive.
- MRR enables batch processing of requests for key access for operations that require access to data rows through index tuples, such as range index scans and equi-joins that use an index for the join attribute. MRR iterates over a sequence of index ranges to obtain qualifying index tuples. As these results accumulate, they are used to access the corresponding data rows. It is not necessary to acquire all index tuples before starting to read data rows.

The MRR optimization is not supported with secondary indexes created on virtual generated columns. `InnoDB` supports secondary indexes on virtual generated columns.

The following scenarios illustrate when MRR optimization can be advantageous:

Scenario A: MRR can be used for `InnoDB` and `MyISAM` tables for index range scans and equi-join operations.

1. A portion of the index tuples are accumulated in a buffer.
2. The tuples in the buffer are sorted by their data row ID.
3. Data rows are accessed according to the sorted index tuple sequence.

Scenario B: MRR can be used for [`NDB`](mysql-cluster.html) tables for multiple-range index scans or when performing an equi-join by an attribute.

1. A portion of ranges, possibly single-key ranges, is accumulated in a buffer on the central node where the query is submitted.
2. The ranges are sent to the execution nodes that access data rows.
3. The accessed rows are packed into packages and sent back to the central node.
4. The received packages with data rows are placed in a buffer.
5. Data rows are read from the buffer.

When MRR is used, the `Extra` column in [`EXPLAIN`](sql-statements.html#explain) output shows `Using MRR`.

`InnoDB` and `MyISAM` do not use MRR if full table rows need not be accessed to produce the query result. This is the case if results can be produced entirely on the basis on information in the index tuples (through a [covering index](glossary.html#glos_covering_index)); MRR provides no benefit.

Two [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable flags provide an interface to the use of MRR optimization. The `mrr` flag controls whether MRR is enabled. If `mrr` is enabled (`on`), the `mrr_cost_based` flag controls whether the optimizer attempts to make a cost-based choice between using and not using MRR (`on`) or uses MRR whenever possible (`off`). By default, `mrr` is `on` and `mrr_cost_based` is `on`. See [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations).

For MRR, a storage engine uses the value of the [`read_rnd_buffer_size`](server-administration.html#sysvar_read_rnd_buffer_size) system variable as a guideline for how much memory it can allocate for its buffer. The engine uses up to [`read_rnd_buffer_size`](server-administration.html#sysvar_read_rnd_buffer_size) bytes and determines the number of ranges to process in a single pass.

#### 8.2.1.11 Block Nested-Loop and Batched Key Access Joins



In MySQL, a Batched Key Access (BKA) Join algorithm is available that uses both index access to the joined table and a join buffer. The BKA algorithm supports inner join, outer join, and semijoin operations, including nested outer joins. Benefits of BKA include improved join performance due to more efficient table scanning. Also, the Block Nested-Loop (BNL) Join algorithm previously used only for inner joins is extended and can be employed for outer join and semijoin operations, including nested outer joins.

The following sections discuss the join buffer management that underlies the extension of the original BNL algorithm, the extended BNL algorithm, and the BKA algorithm. For information about semijoin strategies, see [Section 8.2.2.1, “Optimizing Subqueries, Derived Tables, and View References with Semijoin Transformations”](optimization.html#semijoins)

- [Join Buffer Management for Block Nested-Loop and Batched Key Access Algorithms](optimization.html#join-buffer-management)
- [Block Nested-Loop Algorithm for Outer Joins and Semijoins](optimization.html#bnl-optimization)
- [Batched Key Access Joins](optimization.html#bka-optimization)
- [Optimizer Hints for Block Nested-Loop and Batched Key Access Algorithms](optimization.html#bnl-bka-optimizer-hints)

##### Join Buffer Management for Block Nested-Loop and Batched Key Access Algorithms

MySQL can employ join buffers to execute not only inner joins without index access to the inner table, but also outer joins and semijoins that appear after subquery flattening. Moreover, a join buffer can be effectively used when there is an index access to the inner table.

The join buffer management code slightly more efficiently utilizes join buffer space when storing the values of the interesting row columns: No additional bytes are allocated in buffers for a row column if its value is `NULL`, and the minimum number of bytes is allocated for any value of the [`VARCHAR`](data-types.html#char) type.

The code supports two types of buffers, regular and incremental. Suppose that join buffer `B1` is employed to join tables `t1` and `t2` and the result of this operation is joined with table `t3` using join buffer `B2`:

- A regular join buffer contains columns from each join operand. If `B2` is a regular join buffer, each row *`r`* put into `B2` is composed of the columns of a row *`r1`* from `B1` and the interesting columns of a matching row *`r2`* from table `t3`.
- An incremental join buffer contains only columns from rows of the table produced by the second join operand. That is, it is incremental to a row from the first operand buffer. If `B2` is an incremental join buffer, it contains the interesting columns of the row *`r2`* together with a link to the row *`r1`* from `B1`.

Incremental join buffers are always incremental relative to a join buffer from an earlier join operation, so the buffer from the first join operation is always a regular buffer. In the example just given, the buffer `B1` used to join tables `t1` and `t2` must be a regular buffer.

Each row of the incremental buffer used for a join operation contains only the interesting columns of a row from the table to be joined. These columns are augmented with a reference to the interesting columns of the matched row from the table produced by the first join operand. Several rows in the incremental buffer can refer to the same row *`r`* whose columns are stored in the previous join buffers insofar as all these rows match row *`r`*.

Incremental buffers enable less frequent copying of columns from buffers used for previous join operations. This provides a savings in buffer space because in the general case a row produced by the first join operand can be matched by several rows produced by the second join operand. It is unnecessary to make several copies of a row from the first operand. Incremental buffers also provide a savings in processing time due to the reduction in copying time.

The `block_nested_loop` and `batched_key_access` flags of the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable control how the optimizer uses the Block Nested-Loop and Batched Key Access join algorithms. By default, `block_nested_loop` is `on` and `batched_key_access` is `off`. See [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations). Optimizer hints may also be applied; see [Optimizer Hints for Block Nested-Loop and Batched Key Access Algorithms](optimization.html#bnl-bka-optimizer-hints).

For information about semijoin strategies, see [Section 8.2.2.1, “Optimizing Subqueries, Derived Tables, and View References with Semijoin Transformations”](optimization.html#semijoins)

##### Block Nested-Loop Algorithm for Outer Joins and Semijoins



The original implementation of the MySQL BNL algorithm is extended to support outer join and semijoin operations.

When these operations are executed with a join buffer, each row put into the buffer is supplied with a match flag.

If an outer join operation is executed using a join buffer, each row of the table produced by the second operand is checked for a match against each row in the join buffer. When a match is found, a new extended row is formed (the original row plus columns from the second operand) and sent for further extensions by the remaining join operations. In addition, the match flag of the matched row in the buffer is enabled. After all rows of the table to be joined have been examined, the join buffer is scanned. Each row from the buffer that does not have its match flag enabled is extended by `NULL` complements (`NULL` values for each column in the second operand) and sent for further extensions by the remaining join operations.

The `block_nested_loop` flag of the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable controls how the optimizer uses the Block Nested-Loop algorithm. By default, `block_nested_loop` is `on`. See [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations). Optimizer hints may also be applied; see [Optimizer Hints for Block Nested-Loop and Batched Key Access Algorithms](optimization.html#bnl-bka-optimizer-hints).

In [`EXPLAIN`](sql-statements.html#explain) output, use of BNL for a table is signified when the `Extra` value contains `Using join buffer (Block Nested Loop)` and the `type` value is [`ALL`](optimization.html#jointype_all), [`index`](optimization.html#jointype_index), or [`range`](optimization.html#jointype_range).

Some cases involving the combination of one or more subqueries with one or more left joins, particularly those returning many rows, may use BNL even though it is not ideal in such instances. This is a known issue which is fixed in MySQL 8.0. If upgrading MySQL is not immediately feasible for you, you may wish to disable BNL in the meantime by setting [`optimizer_switch='block_nested_loop=off'`](server-administration.html#sysvar_optimizer_switch) or employing the [`NO_BNL`](optimization.html#optimizer-hints-table-level) optimizer hint to let the optimizer choose a better plan, using one or more index hints (see [Section 8.9.4, “Index Hints”](optimization.html#index-hints)), or some combination of these, to improve the performance of such queries.

For information about semijoin strategies, see [Section 8.2.2.1, “Optimizing Subqueries, Derived Tables, and View References with Semijoin Transformations”](optimization.html#semijoins)

##### Batched Key Access Joins



MySQL implements a method of joining tables called the Batched Key Access (BKA) join algorithm. BKA can be applied when there is an index access to the table produced by the second join operand. Like the BNL join algorithm, the BKA join algorithm employs a join buffer to accumulate the interesting columns of the rows produced by the first operand of the join operation. Then the BKA algorithm builds keys to access the table to be joined for all rows in the buffer and submits these keys in a batch to the database engine for index lookups. The keys are submitted to the engine through the Multi-Range Read (MRR) interface (see [Section 8.2.1.10, “Multi-Range Read Optimization”](optimization.html#mrr-optimization)). After submission of the keys, the MRR engine functions perform lookups in the index in an optimal way, fetching the rows of the joined table found by these keys, and starts feeding the BKA join algorithm with matching rows. Each matching row is coupled with a reference to a row in the join buffer.

When BKA is used, the value of [`join_buffer_size`](server-administration.html#sysvar_join_buffer_size) defines how large the batch of keys is in each request to the storage engine. The larger the buffer, the more sequential access will be to the right hand table of a join operation, which can significantly improve performance.

For BKA to be used, the `batched_key_access` flag of the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable must be set to `on`. BKA uses MRR, so the `mrr` flag must also be `on`. Currently, the cost estimation for MRR is too pessimistic. Hence, it is also necessary for `mrr_cost_based` to be `off` for BKA to be used. The following setting enables BKA:

```
mysql> SET optimizer_switch='mrr=on,mrr_cost_based=off,batched_key_access=on';
```

There are two scenarios by which MRR functions execute:

- The first scenario is used for conventional disk-based storage engines such as [`InnoDB`](innodb-storage-engine.html) and [`MyISAM`](storage-engines.html#myisam-storage-engine). For these engines, usually the keys for all rows from the join buffer are submitted to the MRR interface at once. Engine-specific MRR functions perform index lookups for the submitted keys, get row IDs (or primary keys) from them, and then fetch rows for all these selected row IDs one by one by request from BKA algorithm. Every row is returned with an association reference that enables access to the matched row in the join buffer. The rows are fetched by the MRR functions in an optimal way: They are fetched in the row ID (primary key) order. This improves performance because reads are in disk order rather than random order.
- The second scenario is used for remote storage engines such as [`NDB`](mysql-cluster.html). A package of keys for a portion of rows from the join buffer, together with their associations, is sent by a MySQL Server (SQL node) to NDB Cluster data nodes. In return, the SQL node receives a package (or several packages) of matching rows coupled with corresponding associations. The BKA join algorithm takes these rows and builds new joined rows. Then a new set of keys is sent to the data nodes and the rows from the returned packages are used to build new joined rows. The process continues until the last keys from the join buffer are sent to the data nodes, and the SQL node has received and joined all rows matching these keys. This improves performance because fewer key-bearing packages sent by the SQL node to the data nodes means fewer round trips between it and the data nodes to perform the join operation.

With the first scenario, a portion of the join buffer is reserved to store row IDs (primary keys) selected by index lookups and passed as a parameter to the MRR functions.

There is no special buffer to store keys built for rows from the join buffer. Instead, a function that builds the key for the next row in the buffer is passed as a parameter to the MRR functions.

In [`EXPLAIN`](sql-statements.html#explain) output, use of BKA for a table is signified when the `Extra` value contains `Using join buffer (Batched Key Access)` and the `type` value is [`ref`](optimization.html#jointype_ref) or [`eq_ref`](optimization.html#jointype_eq_ref).

##### Optimizer Hints for Block Nested-Loop and Batched Key Access Algorithms

In addition to using the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable to control optimizer use of the BNL and BKA algorithms session-wide, MySQL supports optimizer hints to influence the optimizer on a per-statement basis. See [Section 8.9.3, “Optimizer Hints”](optimization.html#optimizer-hints).

To use a BNL or BKA hint to enable join buffering for any inner table of an outer join, join buffering must be enabled for all inner tables of the outer join.

#### 8.2.1.12 Condition Filtering

In join processing, prefix rows are those rows passed from one table in a join to the next. In general, the optimizer attempts to put tables with low prefix counts early in the join order to keep the number of row combinations from increasing rapidly. To the extent that the optimizer can use information about conditions on rows selected from one table and passed to the next, the more accurately it can compute row estimates and choose the best execution plan.

Without condition filtering, the prefix row count for a table is based on the estimated number of rows selected by the `WHERE` clause according to whichever access method the optimizer chooses. Condition filtering enables the optimizer to use other relevant conditions in the `WHERE` clause not taken into account by the access method, and thus improve its prefix row count estimates. For example, even though there might be an index-based access method that can be used to select rows from the current table in a join, there might also be additional conditions for the table in the `WHERE` clause that can filter (further restrict) the estimate for qualifying rows passed to the next table.

A condition contributes to the filtering estimate only if:

- It refers to the current table.
- It depends on a constant value or values from earlier tables in the join sequence.
- It was not already taken into account by the access method.

In [`EXPLAIN`](sql-statements.html#explain) output, the `rows` column indicates the row estimate for the chosen access method, and the `filtered` column reflects the effect of condition filtering. `filtered` values are expressed as percentages. The maximum value is 100, which means no filtering of rows occurred. Values decreasing from 100 indicate increasing amounts of filtering.

The prefix row count (the number of rows estimated to be passed from the current table in a join to the next) is the product of the `rows` and `filtered` values. That is, the prefix row count is the estimated row count, reduced by the estimated filtering effect. For example, if `rows` is 1000 and `filtered` is 20%, condition filtering reduces the estimated row count of 1000 to a prefix row count of 1000 × 20% = 1000 × .2 = 200.

Consider the following query:

```
SELECT *
  FROM employee JOIN department ON employee.dept_no = department.dept_no
  WHERE employee.first_name = 'John'
  AND employee.hire_date BETWEEN '2018-01-01' AND '2018-06-01';
```

Suppose that the data set has these characteristics:

- The `employee` table has 1024 rows.

- The `department` table has 12 rows.

- Both tables have an index on `dept_no`.

- The `employee` table has an index on `first_name`.

- 8 rows satisfy this condition on `employee.first_name`:

  ```
  employee.first_name = 'John'
  ```

- 150 rows satisfy this condition on `employee.hire_date`:

  ```
  employee.hire_date BETWEEN '2018-01-01' AND '2018-06-01'
  ```

- 1 row satisfies both conditions:

  ```
  employee.first_name = 'John'
  AND employee.hire_date BETWEEN '2018-01-01' AND '2018-06-01'
  ```

Without condition filtering, [`EXPLAIN`](sql-statements.html#explain) produces output like this:

```
+----+------------+--------+------------------+---------+---------+------+----------+
| id | table      | type   | possible_keys    | key     | ref     | rows | filtered |
+----+------------+--------+------------------+---------+---------+------+----------+
| 1  | employee   | ref    | name,h_date,dept | name    | const   | 8    | 100.00   |
| 1  | department | eq_ref | PRIMARY          | PRIMARY | dept_no | 1    | 100.00   |
+----+------------+--------+------------------+---------+---------+------+----------+
```

For `employee`, the access method on the `name` index picks up the 8 rows that match a name of `'John'`. No filtering is done (`filtered` is 100%), so all rows are prefix rows for the next table: The prefix row count is `rows` × `filtered` = 8 × 100% = 8.

With condition filtering, the optimizer additionally takes into account conditions from the `WHERE` clause not taken into account by the access method. In this case, the optimizer uses heuristics to estimate a filtering effect of 16.31% for the [`BETWEEN`](functions.html#operator_between) condition on `employee.hire_date`. As a result, [`EXPLAIN`](sql-statements.html#explain) produces output like this:

```
+----+------------+--------+------------------+---------+---------+------+----------+
| id | table      | type   | possible_keys    | key     | ref     | rows | filtered |
+----+------------+--------+------------------+---------+---------+------+----------+
| 1  | employee   | ref    | name,h_date,dept | name    | const   | 8    | 16.31    |
| 1  | department | eq_ref | PRIMARY          | PRIMARY | dept_no | 1    | 100.00   |
+----+------------+--------+------------------+---------+---------+------+----------+
```

Now the prefix row count is `rows` × `filtered` = 8 × 16.31% = 1.3, which more closely reflects actual data set.

Normally, the optimizer does not calculate the condition filtering effect (prefix row count reduction) for the last joined table because there is no next table to pass rows to. An exception occurs for [`EXPLAIN`](sql-statements.html#explain): To provide more information, the filtering effect is calculated for all joined tables, including the last one.

To control whether the optimizer considers additional filtering conditions, use the `condition_fanout_filter` flag of the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable (see [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations)). This flag is enabled by default but can be disabled to suppress condition filtering (for example, if a particular query is found to yield better performance without it).

If the optimizer overestimates the effect of condition filtering, performance may be worse than if condition filtering is not used. In such cases, these techniques may help:

- If a column is not indexed, index it so that the optimizer has some information about the distribution of column values and can improve its row estimates.

- Change the join order. Ways to accomplish this include join-order optimizer hints (see [Section 8.9.3, “Optimizer Hints”](optimization.html#optimizer-hints)), `STRAIGHT_JOIN` immediately following the `SELECT`, and the `STRAIGHT_JOIN` join operator.

- Disable condition filtering for the session:

  ```
  SET optimizer_switch = 'condition_fanout_filter=off';
  ```

#### 8.2.1.13 IS NULL Optimization



MySQL can perform the same optimization on *`col_name`* [`IS NULL`](functions.html#operator_is-null) that it can use for *`col_name`* `=` *`constant_value`*. For example, MySQL can use indexes and ranges to search for `NULL` with [`IS NULL`](functions.html#operator_is-null).

Examples:

```
SELECT * FROM tbl_name WHERE key_col IS NULL;

SELECT * FROM tbl_name WHERE key_col <=> NULL;

SELECT * FROM tbl_name
  WHERE key_col=const1 OR key_col=const2 OR key_col IS NULL;
```

If a `WHERE` clause includes a *`col_name`* [`IS NULL`](functions.html#operator_is-null) condition for a column that is declared as `NOT NULL`, that expression is optimized away. This optimization does not occur in cases when the column might produce `NULL` anyway (for example, if it comes from a table on the right side of a `LEFT JOIN`).

MySQL can also optimize the combination `*`col_name`* = *`expr`* OR *`col_name`* IS NULL`, a form that is common in resolved subqueries. [`EXPLAIN`](sql-statements.html#explain) shows [`ref_or_null`](optimization.html#jointype_ref_or_null) when this optimization is used.

This optimization can handle one [`IS NULL`](functions.html#operator_is-null) for any key part.

Some examples of queries that are optimized, assuming that there is an index on columns `a` and `b` of table `t2`:

```
SELECT * FROM t1 WHERE t1.a=expr OR t1.a IS NULL;

SELECT * FROM t1, t2 WHERE t1.a=t2.a OR t2.a IS NULL;

SELECT * FROM t1, t2
  WHERE (t1.a=t2.a OR t2.a IS NULL) AND t2.b=t1.b;

SELECT * FROM t1, t2
  WHERE t1.a=t2.a AND (t2.b=t1.b OR t2.b IS NULL);

SELECT * FROM t1, t2
  WHERE (t1.a=t2.a AND t2.a IS NULL AND ...)
  OR (t1.a=t2.a AND t2.a IS NULL AND ...);
```

[`ref_or_null`](optimization.html#jointype_ref_or_null) works by first doing a read on the reference key, and then a separate search for rows with a `NULL` key value.

The optimization can handle only one [`IS NULL`](functions.html#operator_is-null) level. In the following query, MySQL uses key lookups only on the expression `(t1.a=t2.a AND t2.a IS NULL)` and is not able to use the key part on `b`:

```
SELECT * FROM t1, t2
  WHERE (t1.a=t2.a AND t2.a IS NULL)
  OR (t1.b=t2.b AND t2.b IS NULL);
```

#### 8.2.1.14 ORDER BY Optimization



This section describes when MySQL can use an index to satisfy an `ORDER BY` clause, the `filesort` operation used when an index cannot be used, and execution plan information available from the optimizer about `ORDER BY`.

An `ORDER BY` with and without `LIMIT` may return rows in different orders, as discussed in [Section 8.2.1.17, “LIMIT Query Optimization”](optimization.html#limit-optimization).

- [Use of Indexes to Satisfy ORDER BY](optimization.html#order-by-index-use)
- [Use of filesort to Satisfy ORDER BY](optimization.html#order-by-filesort)
- [Influencing ORDER BY Optimization](optimization.html#order-by-optimizer-control)
- [ORDER BY Execution Plan Information Available](optimization.html#order-by-diagnostic-information)

##### Use of Indexes to Satisfy ORDER BY

In some cases, MySQL may use an index to satisfy an `ORDER BY` clause and avoid the extra sorting involved in performing a `filesort` operation.

The index may also be used even if the `ORDER BY` does not match the index exactly, as long as all unused portions of the index and all extra `ORDER BY` columns are constants in the `WHERE` clause. If the index does not contain all columns accessed by the query, the index is used only if index access is cheaper than other access methods.

Assuming that there is an index on `(*`key_part1`*, *`key_part2`*)`, the following queries may use the index to resolve the `ORDER BY` part. Whether the optimizer actually does so depends on whether reading the index is more efficient than a table scan if columns not in the index must also be read.

- In this query, the index on `(*`key_part1`*, *`key_part2`*)` enables the optimizer to avoid sorting:

  ```
  SELECT * FROM t1
    ORDER BY key_part1, key_part2;
  ```

  However, the query uses `SELECT *`, which may select more columns than *`key_part1`* and *`key_part2`*. In that case, scanning an entire index and looking up table rows to find columns not in the index may be more expensive than scanning the table and sorting the results. If so, the optimizer probably will not use the index. If `SELECT *` selects only the index columns, the index will be used and sorting avoided.

  If `t1` is an `InnoDB` table, the table primary key is implicitly part of the index, and the index can be used to resolve the `ORDER BY` for this query:

  ```
  SELECT pk, key_part1, key_part2 FROM t1
    ORDER BY key_part1, key_part2;
  ```

- In this query, *`key_part1`* is constant, so all rows accessed through the index are in *`key_part2`* order, and an index on `(*`key_part1`*, *`key_part2`*)` avoids sorting if the `WHERE` clause is selective enough to make an index range scan cheaper than a table scan:

  ```
  SELECT * FROM t1
    WHERE key_part1 = constant
    ORDER BY key_part2;
  ```

- In the next two queries, whether the index is used is similar to the same queries without `DESC` shown previously:

  ```
  SELECT * FROM t1
    ORDER BY key_part1 DESC, key_part2 DESC;
  
  SELECT * FROM t1
    WHERE key_part1 = constant
    ORDER BY key_part2 DESC;
  ```

- In the next two queries, *`key_part1`* is compared to a constant. The index will be used if the `WHERE` clause is selective enough to make an index range scan cheaper than a table scan:

  ```
  SELECT * FROM t1
    WHERE key_part1 > constant
    ORDER BY key_part1 ASC;
  
  SELECT * FROM t1
    WHERE key_part1 < constant
    ORDER BY key_part1 DESC;
  ```

- In the next query, the `ORDER BY` does not name *`key_part1`*, but all rows selected have a constant *`key_part1`* value, so the index can still be used:

  ```
  SELECT * FROM t1
    WHERE key_part1 = constant1 AND key_part2 > constant2
    ORDER BY key_part2;
  ```

In some cases, MySQL *cannot* use indexes to resolve the `ORDER BY`, although it may still use indexes to find the rows that match the `WHERE` clause. Examples:

- The query uses `ORDER BY` on different indexes:

  ```
  SELECT * FROM t1 ORDER BY key1, key2;
  ```

- The query uses `ORDER BY` on nonconsecutive parts of an index:

  ```
  SELECT * FROM t1 WHERE key2=constant ORDER BY key1_part1, key1_part3;
  ```

- The query mixes `ASC` and `DESC`:

  ```
  SELECT * FROM t1 ORDER BY key_part1 DESC, key_part2 ASC;
  ```

- The index used to fetch the rows differs from the one used in the `ORDER BY`:

  ```
  SELECT * FROM t1 WHERE key2=constant ORDER BY key1;
  ```

- The query uses `ORDER BY` with an expression that includes terms other than the index column name:

  ```
  SELECT * FROM t1 ORDER BY ABS(key);
  SELECT * FROM t1 ORDER BY -key;
  ```

- The query joins many tables, and the columns in the `ORDER BY` are not all from the first nonconstant table that is used to retrieve rows. (This is the first table in the [`EXPLAIN`](sql-statements.html#explain) output that does not have a [`const`](optimization.html#jointype_const) join type.)

- The query has different `ORDER BY` and `GROUP BY` expressions.

- There is an index on only a prefix of a column named in the `ORDER BY` clause. In this case, the index cannot be used to fully resolve the sort order. For example, if only the first 10 bytes of a [`CHAR(20)`](data-types.html#char) column are indexed, the index cannot distinguish values past the 10th byte and a `filesort` is needed.

- The index does not store rows in order. For example, this is true for a `HASH` index in a `MEMORY` table.

Availability of an index for sorting may be affected by the use of column aliases. Suppose that the column `t1.a` is indexed. In this statement, the name of the column in the select list is `a`. It refers to `t1.a`, as does the reference to `a` in the `ORDER BY`, so the index on `t1.a` can be used:

```
SELECT a FROM t1 ORDER BY a;
```

In this statement, the name of the column in the select list is also `a`, but it is the alias name. It refers to `ABS(a)`, as does the reference to `a` in the `ORDER BY`, so the index on `t1.a` cannot be used:

```
SELECT ABS(a) AS a FROM t1 ORDER BY a;
```

In the following statement, the `ORDER BY` refers to a name that is not the name of a column in the select list. But there is a column in `t1` named `a`, so the `ORDER BY` refers to `t1.a` and the index on `t1.a` can be used. (The resulting sort order may be completely different from the order for `ABS(a)`, of course.)

```
SELECT ABS(a) AS b FROM t1 ORDER BY a;
```



By default, MySQL sorts `GROUP BY *`col1`*, *`col2`*, ...` queries as if you also included `ORDER BY *`col1`*, *`col2`*, ...` in the query. If you include an explicit `ORDER BY` clause that contains the same column list, MySQL optimizes it away without any speed penalty, although the sorting still occurs.

If a query includes `GROUP BY` but you want to avoid the overhead of sorting the result, you can suppress sorting by specifying `ORDER BY NULL`. For example:

```
INSERT INTO foo
SELECT a, COUNT(*) FROM bar GROUP BY a ORDER BY NULL;
```

The optimizer may still choose to use sorting to implement grouping operations. `ORDER BY NULL` suppresses sorting of the result, not prior sorting done by grouping operations to determine the result.



Note

`GROUP BY` implicitly sorts by default (that is, in the absence of `ASC` or `DESC` designators for `GROUP BY` columns). However, relying on implicit `GROUP BY` sorting (that is, sorting in the absence of `ASC` or `DESC` designators) or explicit sorting for `GROUP BY` (that is, by using explicit `ASC` or `DESC` designators for `GROUP BY` columns) is deprecated. To produce a given sort order, provide an `ORDER BY` clause.

##### Use of filesort to Satisfy ORDER BY



If an index cannot be used to satisfy an `ORDER BY` clause, MySQL performs a `filesort` operation that reads table rows and sorts them. A `filesort` constitutes an extra sorting phase in query execution.

To obtain memory for `filesort` operations, the optimizer allocates a fixed amount of [`sort_buffer_size`](server-administration.html#sysvar_sort_buffer_size) bytes up front. Individual sessions can change the session value of this variable as desired to avoid excessive memory use, or to allocate more memory as necessary.

A `filesort` operation uses temporary disk files as necessary if the result set is too large to fit in memory. Some types of queries are particularly suited to completely in-memory `filesort` operations. For example, the optimizer can use `filesort` to efficiently handle in memory, without temporary files, the `ORDER BY` operation for queries (and subqueries) of the following form:

```
SELECT ... FROM single_table ... ORDER BY non_index_column [DESC] LIMIT [M,]N;
```

Such queries are common in web applications that display only a few rows from a larger result set. Examples:

```
SELECT col1, ... FROM t1 ... ORDER BY name LIMIT 10;
SELECT col1, ... FROM t1 ... ORDER BY RAND() LIMIT 15;
```

##### Influencing ORDER BY Optimization

For slow `ORDER BY` queries for which `filesort` is not used, try lowering the [`max_length_for_sort_data`](server-administration.html#sysvar_max_length_for_sort_data) system variable to a value that is appropriate to trigger a `filesort`. (A symptom of setting the value of this variable too high is a combination of high disk activity and low CPU activity.)

To increase `ORDER BY` speed, check whether you can get MySQL to use indexes rather than an extra sorting phase. If this is not possible, try the following strategies:

- Increase the [`sort_buffer_size`](server-administration.html#sysvar_sort_buffer_size) variable value. Ideally, the value should be large enough for the entire result set to fit in the sort buffer (to avoid writes to disk and merge passes), but at minimum the value must be large enough to accommodate 15 tuples. (Up to 15 temporary disk files are merged and there must be room in memory for at least one tuple per file.)

  Take into account that the size of column values stored in the sort buffer is affected by the [`max_sort_length`](server-administration.html#sysvar_max_sort_length) system variable value. For example, if tuples store values of long string columns and you increase the value of [`max_sort_length`](server-administration.html#sysvar_max_sort_length), the size of sort buffer tuples increases as well and may require you to increase [`sort_buffer_size`](server-administration.html#sysvar_sort_buffer_size). For column values calculated as a result of string expressions (such as those that invoke a string-valued function), the `filesort` algorithm cannot tell the maximum length of expression values, so it must allocate [`max_sort_length`](server-administration.html#sysvar_max_sort_length) bytes for each tuple.

  To monitor the number of merge passes (to merge temporary files), check the [`Sort_merge_passes`](server-administration.html#statvar_Sort_merge_passes) status variable.

- Increase the [`read_rnd_buffer_size`](server-administration.html#sysvar_read_rnd_buffer_size) variable value so that more rows are read at a time.

- Change the [`tmpdir`](server-administration.html#sysvar_tmpdir) system variable to point to a dedicated file system with large amounts of free space. The variable value can list several paths that are used in round-robin fashion; you can use this feature to spread the load across several directories. Separate the paths by colon characters (`:`) on Unix and semicolon characters (`;`) on Windows. The paths should name directories in file systems located on different *physical* disks, not different partitions on the same disk.

##### ORDER BY Execution Plan Information Available

With [`EXPLAIN`](sql-statements.html#explain) (see [Section 8.8.1, “Optimizing Queries with EXPLAIN”](optimization.html#using-explain)), you can check whether MySQL can use indexes to resolve an `ORDER BY` clause:

- If the `Extra` column of [`EXPLAIN`](sql-statements.html#explain) output does not contain `Using filesort`, the index is used and a `filesort` is not performed.
- If the `Extra` column of [`EXPLAIN`](sql-statements.html#explain) output contains `Using filesort`, the index is not used and a `filesort` is performed.

In addition, if a `filesort` is performed, optimizer trace output includes a `filesort_summary` block. For example:

```
"filesort_summary": {
  "rows": 100,
  "examined_rows": 100,
  "number_of_tmp_files": 0,
  "sort_buffer_size": 25192,
  "sort_mode": "<sort_key, packed_additional_fields>"
}
```

The `sort_mode` value provides information about the contents of tuples in the sort buffer:

- `<sort_key, rowid>`: This indicates that sort buffer tuples are pairs that contain the sort key value and row ID of the original table row. Tuples are sorted by sort key value and the row ID is used to read the row from the table.
- `<sort_key, additional_fields>`: This indicates that sort buffer tuples contain the sort key value and columns referenced by the query. Tuples are sorted by sort key value and column values are read directly from the tuple.
- `<sort_key, packed_additional_fields>`: Like the previous variant, but the additional columns are packed tightly together instead of using a fixed-length encoding.

[`EXPLAIN`](sql-statements.html#explain) does not distinguish whether the optimizer does or does not perform a `filesort` in memory. Use of an in-memory `filesort` can be seen in optimizer trace output. Look for `filesort_priority_queue_optimization`. For information about the optimizer trace, see [MySQL Internals: Tracing the Optimizer](https://dev.mysql.com/doc/internals/en/optimizer-tracing.html).

#### 8.2.1.15 GROUP BY Optimization



The most general way to satisfy a `GROUP BY` clause is to scan the whole table and create a new temporary table where all rows from each group are consecutive, and then use this temporary table to discover groups and apply aggregate functions (if any). In some cases, MySQL is able to do much better than that and avoid creation of temporary tables by using index access.

The most important preconditions for using indexes for `GROUP BY` are that all `GROUP BY` columns reference attributes from the same index, and that the index stores its keys in order (as is true, for example, for a `BTREE` index, but not for a `HASH` index). Whether use of temporary tables can be replaced by index access also depends on which parts of an index are used in a query, the conditions specified for these parts, and the selected aggregate functions.

There are two ways to execute a `GROUP BY` query through index access, as detailed in the following sections. The first method applies the grouping operation together with all range predicates (if any). The second method first performs a range scan, and then groups the resulting tuples.

In MySQL, `GROUP BY` is used for sorting, so the server may also apply `ORDER BY` optimizations to grouping. However, relying on implicit or explicit `GROUP BY` sorting is deprecated. See [Section 8.2.1.14, “ORDER BY Optimization”](optimization.html#order-by-optimization).

- [Loose Index Scan](optimization.html#loose-index-scan)
- [Tight Index Scan](optimization.html#tight-index-scan)

##### Loose Index Scan



The most efficient way to process `GROUP BY` is when an index is used to directly retrieve the grouping columns. With this access method, MySQL uses the property of some index types that the keys are ordered (for example, `BTREE`). This property enables use of lookup groups in an index without having to consider all keys in the index that satisfy all `WHERE` conditions. This access method considers only a fraction of the keys in an index, so it is called a Loose Index Scan. When there is no `WHERE` clause, a Loose Index Scan reads as many keys as the number of groups, which may be a much smaller number than that of all keys. If the `WHERE` clause contains range predicates (see the discussion of the [`range`](optimization.html#jointype_range) join type in [Section 8.8.1, “Optimizing Queries with EXPLAIN”](optimization.html#using-explain)), a Loose Index Scan looks up the first key of each group that satisfies the range conditions, and again reads the smallest possible number of keys. This is possible under the following conditions:

- The query is over a single table.
- The `GROUP BY` names only columns that form a leftmost prefix of the index and no other columns. (If, instead of `GROUP BY`, the query has a `DISTINCT` clause, all distinct attributes refer to columns that form a leftmost prefix of the index.) For example, if a table `t1` has an index on `(c1,c2,c3)`, Loose Index Scan is applicable if the query has `GROUP BY c1, c2`. It is not applicable if the query has `GROUP BY c2, c3` (the columns are not a leftmost prefix) or `GROUP BY c1, c2, c4` (`c4` is not in the index).
- The only aggregate functions used in the select list (if any) are [`MIN()`](functions.html#function_min) and [`MAX()`](functions.html#function_max), and all of them refer to the same column. The column must be in the index and must immediately follow the columns in the `GROUP BY`.
- Any other parts of the index than those from the `GROUP BY` referenced in the query must be constants (that is, they must be referenced in equalities with constants), except for the argument of [`MIN()`](functions.html#function_min) or [`MAX()`](functions.html#function_max) functions.
- For columns in the index, full column values must be indexed, not just a prefix. For example, with `c1 VARCHAR(20), INDEX (c1(10))`, the index uses only a prefix of `c1` values and cannot be used for Loose Index Scan.

If Loose Index Scan is applicable to a query, the [`EXPLAIN`](sql-statements.html#explain) output shows `Using index for group-by` in the `Extra` column.

Assume that there is an index `idx(c1,c2,c3)` on table `t1(c1,c2,c3,c4)`. The Loose Index Scan access method can be used for the following queries:

```
SELECT c1, c2 FROM t1 GROUP BY c1, c2;
SELECT DISTINCT c1, c2 FROM t1;
SELECT c1, MIN(c2) FROM t1 GROUP BY c1;
SELECT c1, c2 FROM t1 WHERE c1 < const GROUP BY c1, c2;
SELECT MAX(c3), MIN(c3), c1, c2 FROM t1 WHERE c2 > const GROUP BY c1, c2;
SELECT c2 FROM t1 WHERE c1 < const GROUP BY c1, c2;
SELECT c1, c2 FROM t1 WHERE c3 = const GROUP BY c1, c2;
```

The following queries cannot be executed with this quick select method, for the reasons given:

- There are aggregate functions other than [`MIN()`](functions.html#function_min) or [`MAX()`](functions.html#function_max):

  ```
  SELECT c1, SUM(c2) FROM t1 GROUP BY c1;
  ```

- The columns in the `GROUP BY` clause do not form a leftmost prefix of the index:

  ```
  SELECT c1, c2 FROM t1 GROUP BY c2, c3;
  ```

- The query refers to a part of a key that comes after the `GROUP BY` part, and for which there is no equality with a constant:

  ```
  SELECT c1, c3 FROM t1 GROUP BY c1, c2;
  ```

  Were the query to include `WHERE c3 = *`const`*`, Loose Index Scan could be used.

The Loose Index Scan access method can be applied to other forms of aggregate function references in the select list, in addition to the [`MIN()`](functions.html#function_min) and [`MAX()`](functions.html#function_max) references already supported:

- [`AVG(DISTINCT)`](functions.html#function_avg), [`SUM(DISTINCT)`](functions.html#function_sum), and [`COUNT(DISTINCT)`](functions.html#function_count) are supported. [`AVG(DISTINCT)`](functions.html#function_avg) and [`SUM(DISTINCT)`](functions.html#function_sum) take a single argument. [`COUNT(DISTINCT)`](functions.html#function_count) can have more than one column argument.
- There must be no `GROUP BY` or `DISTINCT` clause in the query.
- The Loose Index Scan limitations described previously still apply.

Assume that there is an index `idx(c1,c2,c3)` on table `t1(c1,c2,c3,c4)`. The Loose Index Scan access method can be used for the following queries:

```
SELECT COUNT(DISTINCT c1), SUM(DISTINCT c1) FROM t1;

SELECT COUNT(DISTINCT c1, c2), COUNT(DISTINCT c2, c1) FROM t1;
```

##### Tight Index Scan

A Tight Index Scan may be either a full index scan or a range index scan, depending on the query conditions.

When the conditions for a Loose Index Scan are not met, it still may be possible to avoid creation of temporary tables for `GROUP BY` queries. If there are range conditions in the `WHERE` clause, this method reads only the keys that satisfy these conditions. Otherwise, it performs an index scan. Because this method reads all keys in each range defined by the `WHERE` clause, or scans the whole index if there are no range conditions, it is called a Tight Index Scan. With a Tight Index Scan, the grouping operation is performed only after all keys that satisfy the range conditions have been found.

For this method to work, it is sufficient that there be a constant equality condition for all columns in a query referring to parts of the key coming before or in between parts of the `GROUP BY` key. The constants from the equality conditions fill in any “gaps” in the search keys so that it is possible to form complete prefixes of the index. These index prefixes then can be used for index lookups. If the `GROUP BY` result requires sorting, and it is possible to form search keys that are prefixes of the index, MySQL also avoids extra sorting operations because searching with prefixes in an ordered index already retrieves all the keys in order.

Assume that there is an index `idx(c1,c2,c3)` on table `t1(c1,c2,c3,c4)`. The following queries do not work with the Loose Index Scan access method described previously, but still work with the Tight Index Scan access method.

- There is a gap in the `GROUP BY`, but it is covered by the condition `c2 = 'a'`:

  ```
  SELECT c1, c2, c3 FROM t1 WHERE c2 = 'a' GROUP BY c1, c3;
  ```

- The `GROUP BY` does not begin with the first part of the key, but there is a condition that provides a constant for that part:

  ```
  SELECT c1, c2, c3 FROM t1 WHERE c1 = 'a' GROUP BY c2, c3;
  ```

#### 8.2.1.16 DISTINCT Optimization



`DISTINCT` combined with `ORDER BY` needs a temporary table in many cases.

Because `DISTINCT` may use `GROUP BY`, learn how MySQL works with columns in `ORDER BY` or `HAVING` clauses that are not part of the selected columns. See [Section 12.20.3, “MySQL Handling of GROUP BY”](functions.html#group-by-handling).

In most cases, a `DISTINCT` clause can be considered as a special case of `GROUP BY`. For example, the following two queries are equivalent:

```
SELECT DISTINCT c1, c2, c3 FROM t1
WHERE c1 > const;

SELECT c1, c2, c3 FROM t1
WHERE c1 > const GROUP BY c1, c2, c3;
```

Due to this equivalence, the optimizations applicable to `GROUP BY` queries can be also applied to queries with a `DISTINCT` clause. Thus, for more details on the optimization possibilities for `DISTINCT` queries, see [Section 8.2.1.15, “GROUP BY Optimization”](optimization.html#group-by-optimization).

When combining `LIMIT *`row_count`*` with `DISTINCT`, MySQL stops as soon as it finds *`row_count`* unique rows.

If you do not use columns from all tables named in a query, MySQL stops scanning any unused tables as soon as it finds the first match. In the following case, assuming that `t1` is used before `t2` (which you can check with [`EXPLAIN`](sql-statements.html#explain)), MySQL stops reading from `t2` (for any particular row in `t1`) when it finds the first row in `t2`:

```
SELECT DISTINCT t1.a FROM t1, t2 where t1.a=t2.a;
```

#### 8.2.1.17 LIMIT Query Optimization



If you need only a specified number of rows from a result set, use a `LIMIT` clause in the query, rather than fetching the whole result set and throwing away the extra data.

MySQL sometimes optimizes a query that has a `LIMIT *`row_count`*` clause and no `HAVING` clause:

- If you select only a few rows with `LIMIT`, MySQL uses indexes in some cases when normally it would prefer to do a full table scan.

- If you combine `LIMIT *`row_count`*` with `ORDER BY`, MySQL stops sorting as soon as it has found the first *`row_count`* rows of the sorted result, rather than sorting the entire result. If ordering is done by using an index, this is very fast. If a filesort must be done, all rows that match the query without the `LIMIT` clause are selected, and most or all of them are sorted, before the first *`row_count`* are found. After the initial rows have been found, MySQL does not sort any remainder of the result set.

  One manifestation of this behavior is that an `ORDER BY` query with and without `LIMIT` may return rows in different order, as described later in this section.

- If you combine `LIMIT *`row_count`*` with `DISTINCT`, MySQL stops as soon as it finds *`row_count`* unique rows.

- In some cases, a `GROUP BY` can be resolved by reading the index in order (or doing a sort on the index), then calculating summaries until the index value changes. In this case, `LIMIT *`row_count`*` does not calculate any unnecessary `GROUP BY` values.

- As soon as MySQL has sent the required number of rows to the client, it aborts the query unless you are using `SQL_CALC_FOUND_ROWS`. In that case, the number of rows can be retrieved with `SELECT FOUND_ROWS()`. See [Section 12.15, “Information Functions”](functions.html#information-functions).

  

- `LIMIT 0` quickly returns an empty set. This can be useful for checking the validity of a query. It can also be employed to obtain the types of the result columns within applications that use a MySQL API that makes result set metadata available. With the [**mysql**](programs.html#mysql) client program, you can use the [`--column-type-info`](programs.html#option_mysql_column-type-info) option to display result column types.

- If the server uses temporary tables to resolve a query, it uses the `LIMIT *`row_count`*` clause to calculate how much space is required.

- If an index is not used for `ORDER BY` but a `LIMIT` clause is also present, the optimizer may be able to avoid using a merge file and sort the rows in memory using an in-memory `filesort` operation.

If multiple rows have identical values in the `ORDER BY` columns, the server is free to return those rows in any order, and may do so differently depending on the overall execution plan. In other words, the sort order of those rows is nondeterministic with respect to the nonordered columns.

One factor that affects the execution plan is `LIMIT`, so an `ORDER BY` query with and without `LIMIT` may return rows in different orders. Consider this query, which is sorted by the `category` column but nondeterministic with respect to the `id` and `rating` columns:

```
mysql> SELECT * FROM ratings ORDER BY category;
+----+----------+--------+
| id | category | rating |
+----+----------+--------+
|  1 |        1 |    4.5 |
|  5 |        1 |    3.2 |
|  3 |        2 |    3.7 |
|  4 |        2 |    3.5 |
|  6 |        2 |    3.5 |
|  2 |        3 |    5.0 |
|  7 |        3 |    2.7 |
+----+----------+--------+
```

Including `LIMIT` may affect order of rows within each `category` value. For example, this is a valid query result:

```
mysql> SELECT * FROM ratings ORDER BY category LIMIT 5;
+----+----------+--------+
| id | category | rating |
+----+----------+--------+
|  1 |        1 |    4.5 |
|  5 |        1 |    3.2 |
|  4 |        2 |    3.5 |
|  3 |        2 |    3.7 |
|  6 |        2 |    3.5 |
+----+----------+--------+
```

In each case, the rows are sorted by the `ORDER BY` column, which is all that is required by the SQL standard.

If it is important to ensure the same row order with and without `LIMIT`, include additional columns in the `ORDER BY` clause to make the order deterministic. For example, if `id` values are unique, you can make rows for a given `category` value appear in `id` order by sorting like this:

```
mysql> SELECT * FROM ratings ORDER BY category, id;
+----+----------+--------+
| id | category | rating |
+----+----------+--------+
|  1 |        1 |    4.5 |
|  5 |        1 |    3.2 |
|  3 |        2 |    3.7 |
|  4 |        2 |    3.5 |
|  6 |        2 |    3.5 |
|  2 |        3 |    5.0 |
|  7 |        3 |    2.7 |
+----+----------+--------+

mysql> SELECT * FROM ratings ORDER BY category, id LIMIT 5;
+----+----------+--------+
| id | category | rating |
+----+----------+--------+
|  1 |        1 |    4.5 |
|  5 |        1 |    3.2 |
|  3 |        2 |    3.7 |
|  4 |        2 |    3.5 |
|  6 |        2 |    3.5 |
+----+----------+--------+
```

#### 8.2.1.18 Function Call Optimization



MySQL functions are tagged internally as deterministic or nondeterministic. A function is nondeterministic if, given fixed values for its arguments, it can return different results for different invocations. Examples of nondeterministic functions: [`RAND()`](functions.html#function_rand), [`UUID()`](functions.html#function_uuid).

If a function is tagged nondeterministic, a reference to it in a `WHERE` clause is evaluated for every row (when selecting from one table) or combination of rows (when selecting from a multiple-table join).

MySQL also determines when to evaluate functions based on types of arguments, whether the arguments are table columns or constant values. A deterministic function that takes a table column as argument must be evaluated whenever that column changes value.

Nondeterministic functions may affect query performance. For example, some optimizations may not be available, or more locking might be required. The following discussion uses [`RAND()`](functions.html#function_rand) but applies to other nondeterministic functions as well.

Suppose that a table `t` has this definition:

```
CREATE TABLE t (id INT NOT NULL PRIMARY KEY, col_a VARCHAR(100));
```

Consider these two queries:

```
SELECT * FROM t WHERE id = POW(1,2);
SELECT * FROM t WHERE id = FLOOR(1 + RAND() * 49);
```

Both queries appear to use a primary key lookup because of the equality comparison against the primary key, but that is true only for the first of them:

- The first query always produces a maximum of one row because [`POW()`](functions.html#function_pow) with constant arguments is a constant value and is used for index lookup.
- The second query contains an expression that uses the nondeterministic function [`RAND()`](functions.html#function_rand), which is not constant in the query but in fact has a new value for every row of table `t`. Consequently, the query reads every row of the table, evaluates the predicate for each row, and outputs all rows for which the primary key matches the random value. This might be zero, one, or multiple rows, depending on the `id` column values and the values in the [`RAND()`](functions.html#function_rand) sequence.

The effects of nondeterminism are not limited to [`SELECT`](sql-statements.html#select) statements. This [`UPDATE`](sql-statements.html#update) statement uses a nondeterministic function to select rows to be modified:

```
UPDATE t SET col_a = some_expr WHERE id = FLOOR(1 + RAND() * 49);
```

Presumably the intent is to update at most a single row for which the primary key matches the expression. However, it might update zero, one, or multiple rows, depending on the `id` column values and the values in the [`RAND()`](functions.html#function_rand) sequence.

The behavior just described has implications for performance and replication:

- Because a nondeterministic function does not produce a constant value, the optimizer cannot use strategies that might otherwise be applicable, such as index lookups. The result may be a table scan.
- `InnoDB` might escalate to a range-key lock rather than taking a single row lock for one matching row.
- Updates that do not execute deterministically are unsafe for replication.

The difficulties stem from the fact that the [`RAND()`](functions.html#function_rand) function is evaluated once for every row of the table. To avoid multiple function evaluations, use one of these techniques:

- Move the expression containing the nondeterministic function to a separate statement, saving the value in a variable. In the original statement, replace the expression with a reference to the variable, which the optimizer can treat as a constant value:

  ```
  SET @keyval = FLOOR(1 + RAND() * 49);
  UPDATE t SET col_a = some_expr WHERE id = @keyval;
  ```

- Assign the random value to a variable in a derived table. This technique causes the variable to be assigned a value, once, prior to its use in the comparison in the `WHERE` clause:

  ```
  SET optimizer_switch = 'derived_merge=off';
  UPDATE t, (SELECT @keyval := FLOOR(1 + RAND() * 49)) AS dt
  SET col_a = some_expr WHERE id = @keyval;
  ```

As mentioned previously, a nondeterministic expression in the `WHERE` clause might prevent optimizations and result in a table scan. However, it may be possible to partially optimize the `WHERE` clause if other expressions are deterministic. For example:

```
SELECT * FROM t WHERE partial_key=5 AND some_column=RAND();
```

If the optimizer can use `partial_key` to reduce the set of rows selected, [`RAND()`](functions.html#function_rand) is executed fewer times, which diminishes the effect of nondeterminism on optimization.

#### 8.2.1.19 Row Constructor Expression Optimization



Row constructors permit simultaneous comparisons of multiple values. For example, these two statements are semantically equivalent:

```
SELECT * FROM t1 WHERE (column1,column2) = (1,1);
SELECT * FROM t1 WHERE column1 = 1 AND column2 = 1;
```

In addition, the optimizer handles both expressions the same way.

The optimizer is less likely to use available indexes if the row constructor columns do not cover the prefix of an index. Consider the following table, which has a primary key on `(c1, c2, c3)`:

```
CREATE TABLE t1 (
  c1 INT, c2 INT, c3 INT, c4 CHAR(100),
  PRIMARY KEY(c1,c2,c3)
);
```

In this query, the `WHERE` clause uses all columns in the index. However, the row constructor itself does not cover an index prefix, with the result that the optimizer uses only `c1` (`key_len=4`, the size of `c1`):

```
mysql> EXPLAIN SELECT * FROM t1
       WHERE c1=1 AND (c2,c3) > (1,1)\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: t1
   partitions: NULL
         type: ref
possible_keys: PRIMARY
          key: PRIMARY
      key_len: 4
          ref: const
         rows: 3
     filtered: 100.00
        Extra: Using where
```

In such cases, rewriting the row constructor expression using an equivalent nonconstructor expression may result in more complete index use. For the given query, the row constructor and equivalent nonconstructor expressions are:

```
(c2,c3) > (1,1)
c2 > 1 OR ((c2 = 1) AND (c3 > 1))
```

Rewriting the query to use the nonconstructor expression results in the optimizer using all three columns in the index (`key_len=12`):

```
mysql> EXPLAIN SELECT * FROM t1
       WHERE c1 = 1 AND (c2 > 1 OR ((c2 = 1) AND (c3 > 1)))\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: t1
   partitions: NULL
         type: range
possible_keys: PRIMARY
          key: PRIMARY
      key_len: 12
          ref: NULL
         rows: 3
     filtered: 100.00
        Extra: Using where
```

Thus, for better results, avoid mixing row constructors with [`AND`](functions.html#operator_and)/[`OR`](functions.html#operator_or) expressions. Use one or the other.

Under certain conditions, the optimizer can apply the range access method to [`IN()`](functions.html#operator_in) expressions that have row constructor arguments. See [Range Optimization of Row Constructor Expressions](optimization.html#row-constructor-range-optimization).

#### 8.2.1.20 Avoiding Full Table Scans



The output from [`EXPLAIN`](sql-statements.html#explain) shows [`ALL`](optimization.html#jointype_all) in the `type` column when MySQL uses a [full table scan](glossary.html#glos_full_table_scan) to resolve a query. This usually happens under the following conditions:

- The table is so small that it is faster to perform a table scan than to bother with a key lookup. This is common for tables with fewer than 10 rows and a short row length.
- There are no usable restrictions in the `ON` or `WHERE` clause for indexed columns.
- You are comparing indexed columns with constant values and MySQL has calculated (based on the index tree) that the constants cover too large a part of the table and that a table scan would be faster. See [Section 8.2.1.1, “WHERE Clause Optimization”](optimization.html#where-optimization).
- You are using a key with low cardinality (many rows match the key value) through another column. In this case, MySQL assumes that by using the key it probably will do many key lookups and that a table scan would be faster.

For small tables, a table scan often is appropriate and the performance impact is negligible. For large tables, try the following techniques to avoid having the optimizer incorrectly choose a table scan:

- Use `ANALYZE TABLE *`tbl_name`*` to update the key distributions for the scanned table. See [Section 13.7.2.1, “ANALYZE TABLE Statement”](sql-statements.html#analyze-table).

- Use `FORCE INDEX` for the scanned table to tell MySQL that table scans are very expensive compared to using the given index:

  ```
  SELECT * FROM t1, t2 FORCE INDEX (index_for_column)
    WHERE t1.col_name=t2.col_name;
  ```

  See [Section 8.9.4, “Index Hints”](optimization.html#index-hints).

- Start [**mysqld**](programs.html#mysqld) with the [`--max-seeks-for-key=1000`](server-administration.html#sysvar_max_seeks_for_key) option or use `SET max_seeks_for_key=1000` to tell the optimizer to assume that no key scan causes more than 1,000 key seeks. See [Section 5.1.7, “Server System Variables”](server-administration.html#server-system-variables).

### 8.2.2 Optimizing Subqueries, Derived Tables, and View References

- [8.2.2.1 Optimizing Subqueries, Derived Tables, and View References with Semijoin Transformations](optimization.html#semijoins)
- [8.2.2.2 Optimizing Subqueries with Materialization](optimization.html#subquery-materialization)
- [8.2.2.3 Optimizing Subqueries with the EXISTS Strategy](optimization.html#subquery-optimization-with-exists)
- [8.2.2.4 Optimizing Derived Tables and View References with Merging or Materialization](optimization.html#derived-table-optimization)



The MySQL query optimizer has different strategies available to evaluate subqueries:

- For `IN` (or `=ANY`) subqueries, the optimizer has these choices:
  - Semijoin
  - Materialization
  - `EXISTS` strategy
- For `NOT IN` (or `<>ALL`) subqueries, the optimizer has these choices:
  - Materialization
  - `EXISTS` strategy

For derived tables, the optimizer has these choices (which also apply to view references):

- Merge the derived table into the outer query block
- Materialize the derived table to an internal temporary table

The following discussion provides more information about the preceding optimization strategies.

Note

A limitation on [`UPDATE`](sql-statements.html#update) and [`DELETE`](sql-statements.html#delete) statements that use a subquery to modify a single table is that the optimizer does not use semijoin or materialization subquery optimizations. As a workaround, try rewriting them as multiple-table [`UPDATE`](sql-statements.html#update) and [`DELETE`](sql-statements.html#delete) statements that use a join rather than a subquery.

#### 8.2.2.1 Optimizing Subqueries, Derived Tables, and View References with Semijoin Transformations



A semijoin is a preparation-time transformation that enables multiple execution strategies such as table pullout, duplicate weedout, first match, loose scan, and materialization. The optimizer uses semijoin strategies to improve subquery execution, as described in this section.

For an inner join between two tables, the join returns a row from one table as many times as there are matches in the other table. But for some questions, the only information that matters is whether there is a match, not the number of matches. Suppose that there are tables named `class` and `roster` that list classes in a course curriculum and class rosters (students enrolled in each class), respectively. To list the classes that actually have students enrolled, you could use this join:

```
SELECT class.class_num, class.class_name
FROM class INNER JOIN roster
WHERE class.class_num = roster.class_num;
```

However, the result lists each class once for each enrolled student. For the question being asked, this is unnecessary duplication of information.

Assuming that `class_num` is a primary key in the `class` table, duplicate suppression is possible by using [`SELECT DISTINCT`](sql-statements.html#select), but it is inefficient to generate all matching rows first only to eliminate duplicates later.

The same duplicate-free result can be obtained by using a subquery:

```
SELECT class_num, class_name
FROM class
WHERE class_num IN (SELECT class_num FROM roster);
```

Here, the optimizer can recognize that the `IN` clause requires the subquery to return only one instance of each class number from the `roster` table. In this case, the query can use a semijoin; that is, an operation that returns only one instance of each row in `class` that is matched by rows in `roster`.

Outer join and inner join syntax is permitted in the outer query specification, and table references may be base tables, derived tables, or view references.

In MySQL, a subquery must satisfy these criteria to be handled as a semijoin:

- It must be an `IN` (or `=ANY`) subquery that appears at the top level of the `WHERE` or `ON` clause, possibly as a term in an [`AND`](functions.html#operator_and) expression. For example:

  ```
  SELECT ...
  FROM ot1, ...
  WHERE (oe1, ...) IN (SELECT ie1, ... FROM it1, ... WHERE ...);
  ```

  Here, `ot_*`i`*` and `it_*`i`*` represent tables in the outer and inner parts of the query, and `oe_*`i`*` and `ie_*`i`*` represent expressions that refer to columns in the outer and inner tables.

- It must be a single [`SELECT`](sql-statements.html#select) without [`UNION`](sql-statements.html#union) constructs.

- It must not contain a `GROUP BY` or `HAVING` clause.

- It must not be implicitly grouped (it must contain no aggregate functions).

- It must not have `ORDER BY` with `LIMIT`.

- The statement must not use the `STRAIGHT_JOIN` join type in the outer query.

  

- The `STRAIGHT_JOIN` modifier must not be present.

  

- The number of outer and inner tables together must be less than the maximum number of tables permitted in a join.

The subquery may be correlated or uncorrelated. `DISTINCT` is permitted, as is `LIMIT` unless `ORDER BY` is also used.

If a subquery meets the preceding criteria, MySQL converts it to a semijoin and makes a cost-based choice from these strategies:

- Convert the subquery to a join, or use table pullout and run the query as an inner join between subquery tables and outer tables. Table pullout pulls a table out from the subquery to the outer query.

  

- Duplicate Weedout: Run the semijoin as if it was a join and remove duplicate records using a temporary table.

  

- FirstMatch: When scanning the inner tables for row combinations and there are multiple instances of a given value group, choose one rather than returning them all. This "shortcuts" scanning and eliminates production of unnecessary rows.

  

- LooseScan: Scan a subquery table using an index that enables a single value to be chosen from each subquery's value group.

  

- Materialize the subquery into an indexed temporary table that is used to perform a join, where the index is used to remove duplicates. The index might also be used later for lookups when joining the temporary table with the outer tables; if not, the table is scanned. For more information about materialization, see [Section 8.2.2.2, “Optimizing Subqueries with Materialization”](optimization.html#subquery-materialization).

  

Each of these strategies can be enabled or disabled using the following [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable flags:

- The `semijoin` flag controls whether semijoins are used.
- If `semijoin` is enabled, the `firstmatch`, `loosescan`, `duplicateweedout`, and `materialization` flags enable finer control over the permitted semijoin strategies.
- If the `duplicateweedout` semijoin strategy is disabled, it is not used unless all other applicable strategies are also disabled.
- If `duplicateweedout` is disabled, on occasion the optimizer may generate a query plan that is far from optimal. This occurs due to heuristic pruning during greedy search, which can be avoided by setting [`optimizer_prune_level=0`](server-administration.html#sysvar_optimizer_prune_level).

These flags are enabled by default. See [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations).

The optimizer minimizes differences in handling of views and derived tables. This affects queries that use the `STRAIGHT_JOIN` modifier and a view with an `IN` subquery that can be converted to a semijoin. The following query illustrates this because the change in processing causes a change in transformation, and thus a different execution strategy:

```
CREATE VIEW v AS
SELECT *
FROM t1
WHERE a IN (SELECT b
           FROM t2);

SELECT STRAIGHT_JOIN *
FROM t3 JOIN v ON t3.x = v.a;
```

The optimizer first looks at the view and converts the `IN` subquery to a semijoin, then checks whether it is possible to merge the view into the outer query. Because the `STRAIGHT_JOIN` modifier in the outer query prevents semijoin, the optimizer refuses the merge, causing derived table evaluation using a materialized table.

[`EXPLAIN`](sql-statements.html#explain) output indicates the use of semijoin strategies as follows:

- Semijoined tables show up in the outer select. For extended [`EXPLAIN`](sql-statements.html#explain) output, the text displayed by a following [`SHOW WARNINGS`](sql-statements.html#show-warnings) shows the rewritten query, which displays the semijoin structure. (See [Section 8.8.3, “Extended EXPLAIN Output Format”](optimization.html#explain-extended).) From this you can get an idea about which tables were pulled out of the semijoin. If a subquery was converted to a semijoin, you will see that the subquery predicate is gone and its tables and `WHERE` clause were merged into the outer query join list and `WHERE` clause.
- Temporary table use for Duplicate Weedout is indicated by `Start temporary` and `End temporary` in the `Extra` column. Tables that were not pulled out and are in the range of [`EXPLAIN`](sql-statements.html#explain) output rows covered by `Start temporary` and `End temporary` have their `rowid` in the temporary table.
- `FirstMatch(*`tbl_name`*)` in the `Extra` column indicates join shortcutting.
- `LooseScan(*`m`*..*`n`*)` in the `Extra` column indicates use of the LooseScan strategy. *`m`* and *`n`* are key part numbers.
- Temporary table use for materialization is indicated by rows with a `select_type` value of `MATERIALIZED` and rows with a `table` value of `<subquery*`N`*>`.

#### 8.2.2.2 Optimizing Subqueries with Materialization



The optimizer uses materialization to enable more efficient subquery processing. Materialization speeds up query execution by generating a subquery result as a temporary table, normally in memory. The first time MySQL needs the subquery result, it materializes that result into a temporary table. Any subsequent time the result is needed, MySQL refers again to the temporary table. The optimizer may index the table with a hash index to make lookups fast and inexpensive. The index contains unique values to eliminate duplicates and make the table smaller.

Subquery materialization uses an in-memory temporary table when possible, falling back to on-disk storage if the table becomes too large. See [Section 8.4.4, “Internal Temporary Table Use in MySQL”](optimization.html#internal-temporary-tables).

If materialization is not used, the optimizer sometimes rewrites a noncorrelated subquery as a correlated subquery. For example, the following `IN` subquery is noncorrelated (*`where_condition`* involves only columns from `t2` and not `t1`):

```
SELECT * FROM t1
WHERE t1.a IN (SELECT t2.b FROM t2 WHERE where_condition);
```

The optimizer might rewrite this as an `EXISTS` correlated subquery:

```
SELECT * FROM t1
WHERE EXISTS (SELECT t2.b FROM t2 WHERE where_condition AND t1.a=t2.b);
```

Subquery materialization using a temporary table avoids such rewrites and makes it possible to execute the subquery only once rather than once per row of the outer query.

For subquery materialization to be used in MySQL, the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable `materialization` flag must be enabled. (See [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations).) With the `materialization` flag enabled, materialization applies to subquery predicates that appear anywhere (in the select list, `WHERE`, `ON`, `GROUP BY`, `HAVING`, or `ORDER BY`), for predicates that fall into any of these use cases:

- The predicate has this form, when no outer expression *`oe_i`* or inner expression *`ie_i`* is nullable. *`N`* is 1 or larger.

  ```
  (oe_1, oe_2, ..., oe_N) [NOT] IN (SELECT ie_1, i_2, ..., ie_N ...)
  ```

- The predicate has this form, when there is a single outer expression *`oe`* and inner expression *`ie`*. The expressions can be nullable.

  ```
  oe [NOT] IN (SELECT ie ...)
  ```

- The predicate is `IN` or `NOT IN` and a result of `UNKNOWN` (`NULL`) has the same meaning as a result of `FALSE`.

The following examples illustrate how the requirement for equivalence of `UNKNOWN` and `FALSE` predicate evaluation affects whether subquery materialization can be used. Assume that *`where_condition`* involves columns only from `t2` and not `t1` so that the subquery is noncorrelated.

This query is subject to materialization:

```
SELECT * FROM t1
WHERE t1.a IN (SELECT t2.b FROM t2 WHERE where_condition);
```

Here, it does not matter whether the `IN` predicate returns `UNKNOWN` or `FALSE`. Either way, the row from `t1` is not included in the query result.

An example where subquery materialization is not used is the following query, where `t2.b` is a nullable column:

```
SELECT * FROM t1
WHERE (t1.a,t1.b) NOT IN (SELECT t2.a,t2.b FROM t2
                          WHERE where_condition);
```

The following restrictions apply to the use of subquery materialization:

- The types of the inner and outer expressions must match. For example, the optimizer might be able to use materialization if both expressions are integer or both are decimal, but cannot if one expression is integer and the other is decimal.
- The inner expression cannot be a [`BLOB`](data-types.html#blob).

Use of [`EXPLAIN`](sql-statements.html#explain) with a query provides some indication of whether the optimizer uses subquery materialization:

- Compared to query execution that does not use materialization, `select_type` may change from `DEPENDENT SUBQUERY` to `SUBQUERY`. This indicates that, for a subquery that would be executed once per outer row, materialization enables the subquery to be executed just once.
- For extended [`EXPLAIN`](sql-statements.html#explain) output, the text displayed by a following [`SHOW WARNINGS`](sql-statements.html#show-warnings) includes `materialize` and `materialized-subquery`.

#### 8.2.2.3 Optimizing Subqueries with the EXISTS Strategy



Certain optimizations are applicable to comparisons that use the `IN` (or `=ANY`) operator to test subquery results. This section discusses these optimizations, particularly with regard to the challenges that `NULL` values present. The last part of the discussion suggests how you can help the optimizer.

Consider the following subquery comparison:

```
outer_expr IN (SELECT inner_expr FROM ... WHERE subquery_where)
```

MySQL evaluates queries “from outside to inside.” That is, it first obtains the value of the outer expression *`outer_expr`*, and then runs the subquery and captures the rows that it produces.

A very useful optimization is to “inform” the subquery that the only rows of interest are those where the inner expression *`inner_expr`* is equal to *`outer_expr`*. This is done by pushing down an appropriate equality into the subquery's `WHERE` clause to make it more restrictive. The converted comparison looks like this:

```
EXISTS (SELECT 1 FROM ... WHERE subquery_where AND outer_expr=inner_expr)
```

After the conversion, MySQL can use the pushed-down equality to limit the number of rows it must examine to evaluate the subquery.

More generally, a comparison of *`N`* values to a subquery that returns *`N`*-value rows is subject to the same conversion. If *`oe_i`* and *`ie_i`* represent corresponding outer and inner expression values, this subquery comparison:

```
(oe_1, ..., oe_N) IN
  (SELECT ie_1, ..., ie_N FROM ... WHERE subquery_where)
```

Becomes:

```
EXISTS (SELECT 1 FROM ... WHERE subquery_where
                          AND oe_1 = ie_1
                          AND ...
                          AND oe_N = ie_N)
```

For simplicity, the following discussion assumes a single pair of outer and inner expression values.

The conversion just described has its limitations. It is valid only if we ignore possible `NULL` values. That is, the “pushdown” strategy works as long as both of these conditions are true:

- *`outer_expr`* and *`inner_expr`* cannot be `NULL`.

- You need not distinguish `NULL` from `FALSE` subquery results. If the subquery is a part of an [`OR`](functions.html#operator_or) or [`AND`](functions.html#operator_and) expression in the `WHERE` clause, MySQL assumes that you do not care. Another instance where the optimizer notices that `NULL` and `FALSE` subquery results need not be distinguished is this construct:

  ```
  ... WHERE outer_expr IN (subquery)
  ```

  In this case, the `WHERE` clause rejects the row whether `IN (*`subquery`*)` returns `NULL` or `FALSE`.

When either or both of those conditions do not hold, optimization is more complex.

Suppose that *`outer_expr`* is known to be a non-`NULL` value but the subquery does not produce a row such that *`outer_expr`* = *`inner_expr`*. Then `*`outer_expr`* IN (SELECT ...)` evaluates as follows:

- `NULL`, if the [`SELECT`](sql-statements.html#select) produces any row where *`inner_expr`* is `NULL`
- `FALSE`, if the [`SELECT`](sql-statements.html#select) produces only non-`NULL` values or produces nothing

In this situation, the approach of looking for rows with `*`outer_expr`* = *`inner_expr`*` is no longer valid. It is necessary to look for such rows, but if none are found, also look for rows where *`inner_expr`* is `NULL`. Roughly speaking, the subquery can be converted to something like this:

```
EXISTS (SELECT 1 FROM ... WHERE subquery_where AND
        (outer_expr=inner_expr OR inner_expr IS NULL))
```

The need to evaluate the extra [`IS NULL`](functions.html#operator_is-null) condition is why MySQL has the [`ref_or_null`](optimization.html#jointype_ref_or_null) access method:

```
mysql> EXPLAIN
       SELECT outer_expr IN (SELECT t2.maybe_null_key
                             FROM t2, t3 WHERE ...)
       FROM t1;
*************************** 1. row ***************************
           id: 1
  select_type: PRIMARY
        table: t1
...
*************************** 2. row ***************************
           id: 2
  select_type: DEPENDENT SUBQUERY
        table: t2
         type: ref_or_null
possible_keys: maybe_null_key
          key: maybe_null_key
      key_len: 5
          ref: func
         rows: 2
        Extra: Using where; Using index
...
```

The [`unique_subquery`](optimization.html#jointype_unique_subquery) and [`index_subquery`](optimization.html#jointype_index_subquery) subquery-specific access methods also have “or `NULL`” variants.

The additional `OR ... IS NULL` condition makes query execution slightly more complicated (and some optimizations within the subquery become inapplicable), but generally this is tolerable.

The situation is much worse when *`outer_expr`* can be `NULL`. According to the SQL interpretation of `NULL` as “unknown value,” `NULL IN (SELECT *`inner_expr`* ...)` should evaluate to:

- `NULL`, if the [`SELECT`](sql-statements.html#select) produces any rows
- `FALSE`, if the [`SELECT`](sql-statements.html#select) produces no rows

For proper evaluation, it is necessary to be able to check whether the [`SELECT`](sql-statements.html#select) has produced any rows at all, so `*`outer_expr`* = *`inner_expr`*` cannot be pushed down into the subquery. This is a problem because many real world subqueries become very slow unless the equality can be pushed down.

Essentially, there must be different ways to execute the subquery depending on the value of *`outer_expr`*.

The optimizer chooses SQL compliance over speed, so it accounts for the possibility that *`outer_expr`* might be `NULL`:

- If *`outer_expr`* is `NULL`, to evaluate the following expression, it is necessary to execute the [`SELECT`](sql-statements.html#select) to determine whether it produces any rows:

  ```
  NULL IN (SELECT inner_expr FROM ... WHERE subquery_where)
  ```

  It is necessary to execute the original [`SELECT`](sql-statements.html#select) here, without any pushed-down equalities of the kind mentioned previously.

- On the other hand, when *`outer_expr`* is not `NULL`, it is absolutely essential that this comparison:

  ```
  outer_expr IN (SELECT inner_expr FROM ... WHERE subquery_where)
  ```

  Be converted to this expression that uses a pushed-down condition:

  ```
  EXISTS (SELECT 1 FROM ... WHERE subquery_where AND outer_expr=inner_expr)
  ```

  Without this conversion, subqueries will be slow.

To solve the dilemma of whether or not to push down conditions into the subquery, the conditions are wrapped within “trigger” functions. Thus, an expression of the following form:

```
outer_expr IN (SELECT inner_expr FROM ... WHERE subquery_where)
```

Is converted into:

```
EXISTS (SELECT 1 FROM ... WHERE subquery_where
                          AND trigcond(outer_expr=inner_expr))
```

More generally, if the subquery comparison is based on several pairs of outer and inner expressions, the conversion takes this comparison:

```
(oe_1, ..., oe_N) IN (SELECT ie_1, ..., ie_N FROM ... WHERE subquery_where)
```

And converts it to this expression:

```
EXISTS (SELECT 1 FROM ... WHERE subquery_where
                          AND trigcond(oe_1=ie_1)
                          AND ...
                          AND trigcond(oe_N=ie_N)
       )
```

Each `trigcond(*`X`*)` is a special function that evaluates to the following values:

- *`X`* when the “linked” outer expression *`oe_i`* is not `NULL`
- `TRUE` when the “linked” outer expression *`oe_i`* is `NULL`

Note

Trigger functions are *not* triggers of the kind that you create with [`CREATE TRIGGER`](sql-statements.html#create-trigger).

Equalities that are wrapped within `trigcond()` functions are not first class predicates for the query optimizer. Most optimizations cannot deal with predicates that may be turned on and off at query execution time, so they assume any `trigcond(*`X`*)` to be an unknown function and ignore it. Triggered equalities can be used by those optimizations:

- Reference optimizations: `trigcond(*`X`*=*`Y`* [OR *`Y`* IS NULL])` can be used to construct [`ref`](optimization.html#jointype_ref), [`eq_ref`](optimization.html#jointype_eq_ref), or [`ref_or_null`](optimization.html#jointype_ref_or_null) table accesses.
- Index lookup-based subquery execution engines: `trigcond(*`X`*=*`Y`*)` can be used to construct [`unique_subquery`](optimization.html#jointype_unique_subquery) or [`index_subquery`](optimization.html#jointype_index_subquery) accesses.
- Table-condition generator: If the subquery is a join of several tables, the triggered condition is checked as soon as possible.

When the optimizer uses a triggered condition to create some kind of index lookup-based access (as for the first two items of the preceding list), it must have a fallback strategy for the case when the condition is turned off. This fallback strategy is always the same: Do a full table scan. In [`EXPLAIN`](sql-statements.html#explain) output, the fallback shows up as `Full scan on NULL key` in the `Extra` column:

```
mysql> EXPLAIN SELECT t1.col1,
       t1.col1 IN (SELECT t2.key1 FROM t2 WHERE t2.col2=t1.col2) FROM t1\G
*************************** 1. row ***************************
           id: 1
  select_type: PRIMARY
        table: t1
        ...
*************************** 2. row ***************************
           id: 2
  select_type: DEPENDENT SUBQUERY
        table: t2
         type: index_subquery
possible_keys: key1
          key: key1
      key_len: 5
          ref: func
         rows: 2
        Extra: Using where; Full scan on NULL key
```

If you run [`EXPLAIN`](sql-statements.html#explain) followed by [`SHOW WARNINGS`](sql-statements.html#show-warnings), you can see the triggered condition:

```
*************************** 1. row ***************************
  Level: Note
   Code: 1003
Message: select `test`.`t1`.`col1` AS `col1`,
         <in_optimizer>(`test`.`t1`.`col1`,
         <exists>(<index_lookup>(<cache>(`test`.`t1`.`col1`) in t2
         on key1 checking NULL
         where (`test`.`t2`.`col2` = `test`.`t1`.`col2`) having
         trigcond(<is_not_null_test>(`test`.`t2`.`key1`))))) AS
         `t1.col1 IN (select t2.key1 from t2 where t2.col2=t1.col2)`
         from `test`.`t1`
```

The use of triggered conditions has some performance implications. A `NULL IN (SELECT ...)` expression now may cause a full table scan (which is slow) when it previously did not. This is the price paid for correct results (the goal of the trigger-condition strategy is to improve compliance, not speed).

For multiple-table subqueries, execution of `NULL IN (SELECT ...)` is particularly slow because the join optimizer does not optimize for the case where the outer expression is `NULL`. It assumes that subquery evaluations with `NULL` on the left side are very rare, even if there are statistics that indicate otherwise. On the other hand, if the outer expression might be `NULL` but never actually is, there is no performance penalty.

To help the query optimizer better execute your queries, use these suggestions:

- Declare a column as `NOT NULL` if it really is. This also helps other aspects of the optimizer by simplifying condition testing for the column.

- If you need not distinguish a `NULL` from `FALSE` subquery result, you can easily avoid the slow execution path. Replace a comparison that looks like this:

  ```
  outer_expr IN (SELECT inner_expr FROM ...)
  ```

  with this expression:

  ```
  (outer_expr IS NOT NULL) AND (outer_expr IN (SELECT inner_expr FROM ...))
  ```

  Then `NULL IN (SELECT ...)` is never evaluated because MySQL stops evaluating [`AND`](functions.html#operator_and) parts as soon as the expression result is clear.

  Another possible rewrite:

  ```
  EXISTS (SELECT inner_expr FROM ...
          WHERE inner_expr=outer_expr)
  ```

  This would apply when you need not distinguish `NULL` from `FALSE` subquery results, in which case you may actually want `EXISTS`.

The `subquery_materialization_cost_based` flag of the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable enables control over the choice between subquery materialization and `IN`-to-`EXISTS` subquery transformation. See [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations).

#### 8.2.2.4 Optimizing Derived Tables and View References with Merging or Materialization



The optimizer can handle derived table references using two strategies (which also apply to view references):

- Merge the derived table into the outer query block
- Materialize the derived table to an internal temporary table

Example 1:

```
SELECT * FROM (SELECT * FROM t1) AS derived_t1;
```

With merging of the derived table `derived_t1`, that query is executed similar to:

```
SELECT * FROM t1;
```

Example 2:

```
SELECT *
  FROM t1 JOIN (SELECT t2.f1 FROM t2) AS derived_t2 ON t1.f2=derived_t2.f1
  WHERE t1.f1 > 0;
```

With merging of the derived table `derived_t2`, that query is executed similar to:

```
SELECT t1.*, t2.f1
  FROM t1 JOIN t2 ON t1.f2=t2.f1
  WHERE t1.f1 > 0;
```

With materialization, `derived_t1` and `derived_t2` are each treated as a separate table within their respective queries.

The optimizer handles derived tables and view references the same way: It avoids unnecessary materialization whenever possible, which enables pushing down conditions from the outer query to derived tables and produces more efficient execution plans. (For an example, see [Section 8.2.2.2, “Optimizing Subqueries with Materialization”](optimization.html#subquery-materialization).)

If merging would result in an outer query block that references more than 61 base tables, the optimizer chooses materialization instead.

The optimizer propagates an `ORDER BY` clause in a derived table or view reference to the outer query block if these conditions are all true:

- The outer query is not grouped or aggregated.
- The outer query does not specify `DISTINCT`, `HAVING`, or `ORDER BY`.
- The outer query has this derived table or view reference as the only source in the `FROM` clause.

Otherwise, the optimizer ignores the `ORDER BY` clause.

The following means are available to influence whether the optimizer attempts to merge derived tables and view references into the outer query block:

- The `derived_merge` flag of the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable can be used, assuming that no other rule prevents merging. See [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations). By default, the flag is enabled to permit merging. Disabling the flag prevents merging and avoids [`ER_UPDATE_TABLE_USED`](error-handling.html#error_er_update_table_used) errors.

  The `derived_merge` flag also applies to views that contain no `ALGORITHM` clause. Thus, if an [`ER_UPDATE_TABLE_USED`](error-handling.html#error_er_update_table_used) error occurs for a view reference that uses an expression equivalent to the subquery, adding `ALGORITHM=TEMPTABLE` to the view definition prevents merging and takes precedence over the `derived_merge` value.

- It is possible to disable merging by using in the subquery any constructs that prevent merging, although these are not as explicit in their effect on materialization. Constructs that prevent merging are the same for derived tables and view references:

  

  - Aggregate functions ([`SUM()`](functions.html#function_sum), [`MIN()`](functions.html#function_min), [`MAX()`](functions.html#function_max), [`COUNT()`](functions.html#function_count), and so forth)
  - `DISTINCT`
  - `GROUP BY`
  - `HAVING`
  - `LIMIT`
  - [`UNION`](sql-statements.html#union) or [`UNION ALL`](sql-statements.html#union)
  - Subqueries in the select list
  - Assignments to user variables
  - Refererences only to literal values (in this case, there is no underlying table)

The `derived_merge` flag also applies to views that contain no `ALGORITHM` clause. Thus, if an [`ER_UPDATE_TABLE_USED`](error-handling.html#error_er_update_table_used) error occurs for a view reference that uses an expression equivalent to the subquery, adding `ALGORITHM=TEMPTABLE` to the view definition prevents merging and takes precedence over the current `derived_merge` value.

If the optimizer chooses the materialization strategy rather than merging for a derived table, it handles the query as follows:

- The optimizer postpones derived table materialization until its contents are needed during query execution. This improves performance because delaying materialization may result in not having to do it at all. Consider a query that joins the result of a derived table to another table: If the optimizer processes that other table first and finds that it returns no rows, the join need not be carried out further and the optimizer can completely skip materializing the derived table.
- During query execution, the optimizer may add an index to a derived table to speed up row retrieval from it.

Consider the following [`EXPLAIN`](sql-statements.html#explain) statement, for a [`SELECT`](sql-statements.html#select) query that contains a derived table:

```
EXPLAIN SELECT * FROM (SELECT * FROM t1) AS derived_t1;
```

The optimizer avoids materializing the derived table by delaying it until the result is needed during [`SELECT`](sql-statements.html#select) execution. In this case, the query is not executed (because it occurs in an [`EXPLAIN`](sql-statements.html#explain) statement), so the result is never needed.

Even for queries that are executed, delay of derived table materialization may enable the optimizer to avoid materialization entirely. When this happens, query execution is quicker by the time needed to perform materialization. Consider the following query, which joins the result of a derived table to another table:

```
SELECT *
  FROM t1 JOIN (SELECT t2.f1 FROM t2) AS derived_t2
          ON t1.f2=derived_t2.f1
  WHERE t1.f1 > 0;
```

If the optimization processes `t1` first and the `WHERE` clause produces an empty result, the join must necessarily be empty and the derived table need not be materialized.

For cases when a derived table requires materialization, the optimizer may add an index to the materialized table to speed up access to it. If such an index enables [`ref`](optimization.html#jointype_ref) access to the table, it can greatly reduce amount of data read during query execution. Consider the following query:

```
SELECT *
 FROM t1 JOIN (SELECT DISTINCT f1 FROM t2) AS derived_t2
         ON t1.f1=derived_t2.f1;
```

The optimizer constructs an index over column `f1` from `derived_t2` if doing so would enable use of [`ref`](optimization.html#jointype_ref) access for the lowest cost execution plan. After adding the index, the optimizer can treat the materialized derived table the same as a regular table with an index, and it benefits similarly from the generated index. The overhead of index creation is negligible compared to the cost of query execution without the index. If [`ref`](optimization.html#jointype_ref) access would result in higher cost than some other access method, the optimizer creates no index and loses nothing.

For optimizer trace output, a merged derived table or view reference is not shown as a node. Only its underlying tables appear in the top query's plan.

### 8.2.3 Optimizing INFORMATION_SCHEMA Queries



Applications that monitor databases may make frequent use of `INFORMATION_SCHEMA` tables. Certain types of queries for `INFORMATION_SCHEMA` tables can be optimized to execute more quickly. The goal is to minimize file operations (for example, scanning a directory or opening a table file) to collect the information that makes up these dynamic tables.

Note

Comparison behavior for database and table names in `INFORMATION_SCHEMA` queries might differ from what you expect. For details, see [Section 10.8.7, “Using Collation in INFORMATION_SCHEMA Searches”](charset.html#charset-collation-information-schema).

**1) Try to use constant lookup values for database and table names in the `WHERE` clause**

You can take advantage of this principle as follows:

- To look up databases or tables, use expressions that evaluate to a constant, such as literal values, functions that return a constant, or scalar subqueries.
- Avoid queries that use a nonconstant database name lookup value (or no lookup value) because they require a scan of the data directory to find matching database directory names.
- Within a database, avoid queries that use a nonconstant table name lookup value (or no lookup value) because they require a scan of the database directory to find matching table files.

This principle applies to the `INFORMATION_SCHEMA` tables shown in the following table, which shows the columns for which a constant lookup value enables the server to avoid a directory scan. For example, if you are selecting from [`TABLES`](information-schema.html#tables-table), using a constant lookup value for `TABLE_SCHEMA` in the `WHERE` clause enables a data directory scan to be avoided.

| Table                                                        | Column to specify to avoid data directory scan | Column to specify to avoid database directory scan |
| :----------------------------------------------------------- | :--------------------------------------------- | :------------------------------------------------- |
| [`COLUMNS`](information-schema.html#columns-table)           | `TABLE_SCHEMA`                                 | `TABLE_NAME`                                       |
| [`KEY_COLUMN_USAGE`](information-schema.html#key-column-usage-table) | `TABLE_SCHEMA`                                 | `TABLE_NAME`                                       |
| [`PARTITIONS`](information-schema.html#partitions-table)     | `TABLE_SCHEMA`                                 | `TABLE_NAME`                                       |
| [`REFERENTIAL_CONSTRAINTS`](information-schema.html#referential-constraints-table) | `CONSTRAINT_SCHEMA`                            | `TABLE_NAME`                                       |
| [`STATISTICS`](information-schema.html#statistics-table)     | `TABLE_SCHEMA`                                 | `TABLE_NAME`                                       |
| [`TABLES`](information-schema.html#tables-table)             | `TABLE_SCHEMA`                                 | `TABLE_NAME`                                       |
| [`TABLE_CONSTRAINTS`](information-schema.html#table-constraints-table) | `TABLE_SCHEMA`                                 | `TABLE_NAME`                                       |
| [`TRIGGERS`](information-schema.html#triggers-table)         | `EVENT_OBJECT_SCHEMA`                          | `EVENT_OBJECT_TABLE`                               |
| [`VIEWS`](information-schema.html#views-table)               | `TABLE_SCHEMA`                                 | `TABLE_NAME`                                       |

The benefit of a query that is limited to a specific constant database name is that checks need be made only for the named database directory. Example:

```
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'test';
```

Use of the literal database name `test` enables the server to check only the `test` database directory, regardless of how many databases there might be. By contrast, the following query is less efficient because it requires a scan of the data directory to determine which database names match the pattern `'test%'`:

```
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA LIKE 'test%';
```

For a query that is limited to a specific constant table name, checks need be made only for the named table within the corresponding database directory. Example:

```
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'test' AND TABLE_NAME = 't1';
```

Use of the literal table name `t1` enables the server to check only the files for the `t1` table, regardless of how many tables there might be in the `test` database. By contrast, the following query requires a scan of the `test` database directory to determine which table names match the pattern `'t%'`:

```
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'test' AND TABLE_NAME LIKE 't%';
```

The following query requires a scan of the database directory to determine matching database names for the pattern `'test%'`, and for each matching database, it requires a scan of the database directory to determine matching table names for the pattern `'t%'`:

```
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'test%' AND TABLE_NAME LIKE 't%';
```

**2) Write queries that minimize the number of table files that must be opened**

For queries that refer to certain `INFORMATION_SCHEMA` table columns, several optimizations are available that minimize the number of table files that must be opened. Example:

```
SELECT TABLE_NAME, ENGINE FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'test';
```

In this case, after the server has scanned the database directory to determine the names of the tables in the database, those names become available with no further file system lookups. Thus, `TABLE_NAME` requires no files to be opened. The `ENGINE` (storage engine) value can be determined by opening the table's `.frm` file, without touching other table files such as the `.MYD` or `.MYI` file.

Some values, such as `INDEX_LENGTH` for `MyISAM` tables, require opening the `.MYD` or `.MYI` file as well.

The file-opening optimization types are denoted thus:

- `SKIP_OPEN_TABLE`: Table files do not need to be opened. The information has already become available within the query by scanning the database directory.
- `OPEN_FRM_ONLY`: Only the table's `.frm` file need be opened.
- `OPEN_TRIGGER_ONLY`: Only the table's `.TRG` file need be opened.
- `OPEN_FULL_TABLE`: The unoptimized information lookup. The `.frm`, `.MYD`, and `.MYI` files must be opened.



The following list indicates how the preceding optimization types apply to `INFORMATION_SCHEMA` table columns. For tables and columns not named, none of the optimizations apply.

- [`COLUMNS`](information-schema.html#columns-table): `OPEN_FRM_ONLY` applies to all columns

- [`KEY_COLUMN_USAGE`](information-schema.html#key-column-usage-table): `OPEN_FULL_TABLE` applies to all columns

- [`PARTITIONS`](information-schema.html#partitions-table): `OPEN_FULL_TABLE` applies to all columns

- [`REFERENTIAL_CONSTRAINTS`](information-schema.html#referential-constraints-table): `OPEN_FULL_TABLE` applies to all columns

- [`STATISTICS`](information-schema.html#statistics-table):

  | Column          | Optimization type |
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

- [`TABLES`](information-schema.html#tables-table):

  | Column            | Optimization type |
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

- [`TABLE_CONSTRAINTS`](information-schema.html#table-constraints-table): `OPEN_FULL_TABLE` applies to all columns

- [`TRIGGERS`](information-schema.html#triggers-table): `OPEN_TRIGGER_ONLY` applies to all columns

- [`VIEWS`](information-schema.html#views-table):

  | Column                 | Optimization type |
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

**3) Use [`EXPLAIN`](sql-statements.html#explain) to determine whether the server can use `INFORMATION_SCHEMA` optimizations for a query**

This applies particularly for `INFORMATION_SCHEMA` queries that search for information from more than one database, which might take a long time and impact performance. The `Extra` value in [`EXPLAIN`](sql-statements.html#explain) output indicates which, if any, of the optimizations described earlier the server can use to evaluate `INFORMATION_SCHEMA` queries. The following examples demonstrate the kinds of information you can expect to see in the `Extra` value.

```
mysql> EXPLAIN SELECT TABLE_NAME FROM INFORMATION_SCHEMA.VIEWS WHERE
       TABLE_SCHEMA = 'test' AND TABLE_NAME = 'v1'\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: VIEWS
         type: ALL
possible_keys: NULL
          key: TABLE_SCHEMA,TABLE_NAME
      key_len: NULL
          ref: NULL
         rows: NULL
        Extra: Using where; Open_frm_only; Scanned 0 databases
```

Use of constant database and table lookup values enables the server to avoid directory scans. For references to `VIEWS.TABLE_NAME`, only the `.frm` file need be opened.

```
mysql> EXPLAIN SELECT TABLE_NAME, ROW_FORMAT FROM INFORMATION_SCHEMA.TABLES\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: TABLES
         type: ALL
possible_keys: NULL
          key: NULL
      key_len: NULL
          ref: NULL
         rows: NULL
        Extra: Open_full_table; Scanned all databases
```

No lookup values are provided (there is no `WHERE` clause), so the server must scan the data directory and each database directory. For each table thus identified, the table name and row format are selected. `TABLE_NAME` requires no further table files to be opened (the `SKIP_OPEN_TABLE` optimization applies). `ROW_FORMAT` requires all table files to be opened (`OPEN_FULL_TABLE` applies). [`EXPLAIN`](sql-statements.html#explain) reports `OPEN_FULL_TABLE` because it is more expensive than `SKIP_OPEN_TABLE`.

```
mysql> EXPLAIN SELECT TABLE_NAME, TABLE_TYPE FROM INFORMATION_SCHEMA.TABLES
       WHERE TABLE_SCHEMA = 'test'\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: TABLES
         type: ALL
possible_keys: NULL
          key: TABLE_SCHEMA
      key_len: NULL
          ref: NULL
         rows: NULL
        Extra: Using where; Open_frm_only; Scanned 1 database
```

No table name lookup value is provided, so the server must scan the `test` database directory. For the `TABLE_NAME` and `TABLE_TYPE` columns, the `SKIP_OPEN_TABLE` and `OPEN_FRM_ONLY` optimizations apply, respectively. [`EXPLAIN`](sql-statements.html#explain) reports `OPEN_FRM_ONLY` because it is more expensive.

```
mysql> EXPLAIN SELECT B.TABLE_NAME
       FROM INFORMATION_SCHEMA.TABLES AS A, INFORMATION_SCHEMA.COLUMNS AS B
       WHERE A.TABLE_SCHEMA = 'test'
       AND A.TABLE_NAME = 't1'
       AND B.TABLE_NAME = A.TABLE_NAME\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: A
         type: ALL
possible_keys: NULL
          key: TABLE_SCHEMA,TABLE_NAME
      key_len: NULL
          ref: NULL
         rows: NULL
        Extra: Using where; Skip_open_table; Scanned 0 databases
*************************** 2. row ***************************
           id: 1
  select_type: SIMPLE
        table: B
         type: ALL
possible_keys: NULL
          key: NULL
      key_len: NULL
          ref: NULL
         rows: NULL
        Extra: Using where; Open_frm_only; Scanned all databases;
               Using join buffer
```

For the first [`EXPLAIN`](sql-statements.html#explain) output row: Constant database and table lookup values enable the server to avoid directory scans for `TABLES` values. References to `TABLES.TABLE_NAME` require no further table files.

For the second [`EXPLAIN`](sql-statements.html#explain) output row: All [`COLUMNS`](information-schema.html#columns-table) table values are `OPEN_FRM_ONLY` lookups, so `COLUMNS.TABLE_NAME` requires the `.frm` file to be opened.

```
mysql> EXPLAIN SELECT * FROM INFORMATION_SCHEMA.COLLATIONS\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: COLLATIONS
         type: ALL
possible_keys: NULL
          key: NULL
      key_len: NULL
          ref: NULL
         rows: NULL
        Extra:
```

In this case, no optimizations apply because [`COLLATIONS`](information-schema.html#collations-table) is not one of the `INFORMATION_SCHEMA` tables for which optimizations are available.

### 8.2.4 Optimizing Data Change Statements

- [8.2.4.1 Optimizing INSERT Statements](optimization.html#insert-optimization)
- [8.2.4.2 Optimizing UPDATE Statements](optimization.html#update-optimization)
- [8.2.4.3 Optimizing DELETE Statements](optimization.html#delete-optimization)



This section explains how to speed up data change statements: [`INSERT`](sql-statements.html#insert), [`UPDATE`](sql-statements.html#update), and [`DELETE`](sql-statements.html#delete). Traditional OLTP applications and modern web applications typically do many small data change operations, where concurrency is vital. Data analysis and reporting applications typically run data change operations that affect many rows at once, where the main considerations is the I/O to write large amounts of data and keep indexes up-to-date. For inserting and updating large volumes of data (known in the industry as ETL, for “extract-transform-load”), sometimes you use other SQL statements or external commands, that mimic the effects of [`INSERT`](sql-statements.html#insert), [`UPDATE`](sql-statements.html#update), and [`DELETE`](sql-statements.html#delete) statements.

#### 8.2.4.1 Optimizing INSERT Statements



To optimize insert speed, combine many small operations into a single large operation. Ideally, you make a single connection, send the data for many new rows at once, and delay all index updates and consistency checking until the very end.

The time required for inserting a row is determined by the following factors, where the numbers indicate approximate proportions:

- Connecting: (3)
- Sending query to server: (2)
- Parsing query: (2)
- Inserting row: (1 × size of row)
- Inserting indexes: (1 × number of indexes)
- Closing: (1)

This does not take into consideration the initial overhead to open tables, which is done once for each concurrently running query.

The size of the table slows down the insertion of indexes by log *`N`*, assuming B-tree indexes.

You can use the following methods to speed up inserts:

- If you are inserting many rows from the same client at the same time, use [`INSERT`](sql-statements.html#insert) statements with multiple `VALUES` lists to insert several rows at a time. This is considerably faster (many times faster in some cases) than using separate single-row [`INSERT`](sql-statements.html#insert) statements. If you are adding data to a nonempty table, you can tune the [`bulk_insert_buffer_size`](server-administration.html#sysvar_bulk_insert_buffer_size) variable to make data insertion even faster. See [Section 5.1.7, “Server System Variables”](server-administration.html#server-system-variables).
- When loading a table from a text file, use [`LOAD DATA`](sql-statements.html#load-data). This is usually 20 times faster than using [`INSERT`](sql-statements.html#insert) statements. See [Section 13.2.6, “LOAD DATA Statement”](sql-statements.html#load-data).
- Take advantage of the fact that columns have default values. Insert values explicitly only when the value to be inserted differs from the default. This reduces the parsing that MySQL must do and improves the insert speed.
- See [Section 8.5.5, “Bulk Data Loading for InnoDB Tables”](optimization.html#optimizing-innodb-bulk-data-loading) for tips specific to `InnoDB` tables.
- See [Section 8.6.2, “Bulk Data Loading for MyISAM Tables”](optimization.html#optimizing-myisam-bulk-data-loading) for tips specific to `MyISAM` tables.

#### 8.2.4.2 Optimizing UPDATE Statements



An update statement is optimized like a [`SELECT`](sql-statements.html#select) query with the additional overhead of a write. The speed of the write depends on the amount of data being updated and the number of indexes that are updated. Indexes that are not changed do not get updated.

Another way to get fast updates is to delay updates and then do many updates in a row later. Performing multiple updates together is much quicker than doing one at a time if you lock the table.

For a `MyISAM` table that uses dynamic row format, updating a row to a longer total length may split the row. If you do this often, it is very important to use [`OPTIMIZE TABLE`](sql-statements.html#optimize-table) occasionally. See [Section 13.7.2.4, “OPTIMIZE TABLE Statement”](sql-statements.html#optimize-table).

#### 8.2.4.3 Optimizing DELETE Statements



The time required to delete individual rows in a `MyISAM` table is exactly proportional to the number of indexes. To delete rows more quickly, you can increase the size of the key cache by increasing the [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) system variable. See [Section 5.1.1, “Configuring the Server”](server-administration.html#server-configuration).

To delete all rows from a `MyISAM` table, `TRUNCATE TABLE *`tbl_name`*` is faster than `DELETE FROM *`tbl_name`*`. Truncate operations are not transaction-safe; an error occurs when attempting one in the course of an active transaction or active table lock. See [Section 13.1.34, “TRUNCATE TABLE Statement”](sql-statements.html#truncate-table).

### 8.2.5 Optimizing Database Privileges



The more complex your privilege setup, the more overhead applies to all SQL statements. Simplifying the privileges established by [`GRANT`](sql-statements.html#grant) statements enables MySQL to reduce permission-checking overhead when clients execute statements. For example, if you do not grant any table-level or column-level privileges, the server need not ever check the contents of the `tables_priv` and `columns_priv` tables. Similarly, if you place no resource limits on any accounts, the server does not have to perform resource counting. If you have a very high statement-processing load, consider using a simplified grant structure to reduce permission-checking overhead.

### 8.2.6 Other Optimization Tips



This section lists a number of miscellaneous tips for improving query processing speed:

- If your application makes several database requests to perform related updates, combining the statements into a stored routine can help performance. Similarly, if your application computes a single result based on several column values or large volumes of data, combining the computation into a UDF (user-defined function) can help performance. The resulting fast database operations are then available to be reused by other queries, applications, and even code written in different programming languages. See [Section 23.2, “Using Stored Routines”](stored-objects.html#stored-routines) and [Section 28.4, “Adding Functions to MySQL”](extending-mysql.html#adding-functions) for more information.

- To fix any compression issues that occur with `ARCHIVE` tables, use [`OPTIMIZE TABLE`](sql-statements.html#optimize-table). See [Section 15.5, “The ARCHIVE Storage Engine”](storage-engines.html#archive-storage-engine).

- If possible, classify reports as “live” or as “statistical”, where data needed for statistical reports is created only from summary tables that are generated periodically from the live data.

- If you have data that does not conform well to a rows-and-columns table structure, you can pack and store data into a [`BLOB`](data-types.html#blob) column. In this case, you must provide code in your application to pack and unpack information, but this might save I/O operations to read and write the sets of related values.

- With Web servers, store images and other binary assets as files, with the path name stored in the database rather than the file itself. Most Web servers are better at caching files than database contents, so using files is generally faster. (Although you must handle backups and storage issues yourself in this case.)

- If you need really high speed, look at the low-level MySQL interfaces. For example, by accessing the MySQL [`InnoDB`](innodb-storage-engine.html) or [`MyISAM`](storage-engines.html#myisam-storage-engine) storage engine directly, you could get a substantial speed increase compared to using the SQL interface.

  Similarly, for databases using the [`NDBCLUSTER`](mysql-cluster.html) storage engine, you may wish to investigate possible use of the NDB API (see [MySQL NDB Cluster API Developer Guide](https://dev.mysql.com/doc/ndbapi/en/)).

- Replication can provide a performance benefit for some operations. You can distribute client retrievals among replication servers to split up the load. To avoid slowing down the master while making backups, you can make backups using a slave server. See [Chapter 16, *Replication*](replication.html).

## 8.3 Optimization and Indexes

- [8.3.1 How MySQL Uses Indexes](optimization.html#mysql-indexes)
- [8.3.2 Primary Key Optimization](optimization.html#primary-key-optimization)
- [8.3.3 Foreign Key Optimization](optimization.html#foreign-key-optimization)
- [8.3.4 Column Indexes](optimization.html#column-indexes)
- [8.3.5 Multiple-Column Indexes](optimization.html#multiple-column-indexes)
- [8.3.6 Verifying Index Usage](optimization.html#verifying-index-usage)
- [8.3.7 InnoDB and MyISAM Index Statistics Collection](optimization.html#index-statistics)
- [8.3.8 Comparison of B-Tree and Hash Indexes](optimization.html#index-btree-hash)
- [8.3.9 Use of Index Extensions](optimization.html#index-extensions)
- [8.3.10 Optimizer Use of Generated Column Indexes](optimization.html#generated-column-index-optimizations)
- [8.3.11 Indexed Lookups from TIMESTAMP Columns](optimization.html#timestamp-lookups)



The best way to improve the performance of [`SELECT`](sql-statements.html#select) operations is to create indexes on one or more of the columns that are tested in the query. The index entries act like pointers to the table rows, allowing the query to quickly determine which rows match a condition in the `WHERE` clause, and retrieve the other column values for those rows. All MySQL data types can be indexed.

Although it can be tempting to create an indexes for every possible column used in a query, unnecessary indexes waste space and waste time for MySQL to determine which indexes to use. Indexes also add to the cost of inserts, updates, and deletes because each index must be updated. You must find the right balance to achieve fast queries using the optimal set of indexes.

### 8.3.1 How MySQL Uses Indexes



Indexes are used to find rows with specific column values quickly. Without an index, MySQL must begin with the first row and then read through the entire table to find the relevant rows. The larger the table, the more this costs. If the table has an index for the columns in question, MySQL can quickly determine the position to seek to in the middle of the data file without having to look at all the data. This is much faster than reading every row sequentially.

Most MySQL indexes (`PRIMARY KEY`, `UNIQUE`, `INDEX`, and `FULLTEXT`) are stored in [B-trees](glossary.html#glos_b_tree). Exceptions: Indexes on spatial data types use R-trees; `MEMORY` tables also support [hash indexes](glossary.html#glos_hash_index); `InnoDB` uses inverted lists for `FULLTEXT` indexes.

In general, indexes are used as described in the following discussion. Characteristics specific to hash indexes (as used in `MEMORY` tables) are described in [Section 8.3.8, “Comparison of B-Tree and Hash Indexes”](optimization.html#index-btree-hash).

MySQL uses indexes for these operations:

- To find the rows matching a `WHERE` clause quickly.

- To eliminate rows from consideration. If there is a choice between multiple indexes, MySQL normally uses the index that finds the smallest number of rows (the most [selective](glossary.html#glos_selectivity) index).

- If the table has a multiple-column index, any leftmost prefix of the index can be used by the optimizer to look up rows. For example, if you have a three-column index on `(col1, col2, col3)`, you have indexed search capabilities on `(col1)`, `(col1, col2)`, and `(col1, col2, col3)`. For more information, see [Section 8.3.5, “Multiple-Column Indexes”](optimization.html#multiple-column-indexes).

- To retrieve rows from other tables when performing joins. MySQL can use indexes on columns more efficiently if they are declared as the same type and size. In this context, [`VARCHAR`](data-types.html#char) and [`CHAR`](data-types.html#char) are considered the same if they are declared as the same size. For example, `VARCHAR(10)` and `CHAR(10)` are the same size, but `VARCHAR(10)` and `CHAR(15)` are not.

  For comparisons between nonbinary string columns, both columns should use the same character set. For example, comparing a `utf8` column with a `latin1` column precludes use of an index.

  Comparison of dissimilar columns (comparing a string column to a temporal or numeric column, for example) may prevent use of indexes if values cannot be compared directly without conversion. For a given value such as `1` in the numeric column, it might compare equal to any number of values in the string column such as `'1'`, `' 1'`, `'00001'`, or `'01.e1'`. This rules out use of any indexes for the string column.

- To find the [`MIN()`](functions.html#function_min) or [`MAX()`](functions.html#function_max) value for a specific indexed column *`key_col`*. This is optimized by a preprocessor that checks whether you are using `WHERE *`key_part_N`* = *`constant`*` on all key parts that occur before *`key_col`* in the index. In this case, MySQL does a single key lookup for each [`MIN()`](functions.html#function_min) or [`MAX()`](functions.html#function_max) expression and replaces it with a constant. If all expressions are replaced with constants, the query returns at once. For example:

  ```
  SELECT MIN(key_part2),MAX(key_part2)
    FROM tbl_name WHERE key_part1=10;
  ```

- To sort or group a table if the sorting or grouping is done on a leftmost prefix of a usable index (for example, `ORDER BY *`key_part1`*, *`key_part2`*`). If all key parts are followed by `DESC`, the key is read in reverse order. See [Section 8.2.1.14, “ORDER BY Optimization”](optimization.html#order-by-optimization), and [Section 8.2.1.15, “GROUP BY Optimization”](optimization.html#group-by-optimization).

- In some cases, a query can be optimized to retrieve values without consulting the data rows. (An index that provides all the necessary results for a query is called a [covering index](glossary.html#glos_covering_index).) If a query uses from a table only columns that are included in some index, the selected values can be retrieved from the index tree for greater speed:

  ```
  SELECT key_part3 FROM tbl_name
    WHERE key_part1=1
  ```

Indexes are less important for queries on small tables, or big tables where report queries process most or all of the rows. When a query needs to access most of the rows, reading sequentially is faster than working through an index. Sequential reads minimize disk seeks, even if not all the rows are needed for the query. See [Section 8.2.1.20, “Avoiding Full Table Scans”](optimization.html#table-scan-avoidance) for details.

### 8.3.2 Primary Key Optimization



The primary key for a table represents the column or set of columns that you use in your most vital queries. It has an associated index, for fast query performance. Query performance benefits from the `NOT NULL` optimization, because it cannot include any `NULL` values. With the `InnoDB` storage engine, the table data is physically organized to do ultra-fast lookups and sorts based on the primary key column or columns.

If your table is big and important, but does not have an obvious column or set of columns to use as a primary key, you might create a separate column with auto-increment values to use as the primary key. These unique IDs can serve as pointers to corresponding rows in other tables when you join tables using foreign keys.

### 8.3.3 Foreign Key Optimization



If a table has many columns, and you query many different combinations of columns, it might be efficient to split the less-frequently used data into separate tables with a few columns each, and relate them back to the main table by duplicating the numeric ID column from the main table. That way, each small table can have a primary key for fast lookups of its data, and you can query just the set of columns that you need using a join operation. Depending on how the data is distributed, the queries might perform less I/O and take up less cache memory because the relevant columns are packed together on disk. (To maximize performance, queries try to read as few data blocks as possible from disk; tables with only a few columns can fit more rows in each data block.)

### 8.3.4 Column Indexes



The most common type of index involves a single column, storing copies of the values from that column in a data structure, allowing fast lookups for the rows with the corresponding column values. The B-tree data structure lets the index quickly find a specific value, a set of values, or a range of values, corresponding to operators such as `=`, `>`, `≤`, `BETWEEN`, `IN`, and so on, in a `WHERE` clause.

The maximum number of indexes per table and the maximum index length is defined per storage engine. See [Chapter 14, *The InnoDB Storage Engine*](innodb-storage-engine.html), and [Chapter 15, *Alternative Storage Engines*](storage-engines.html). All storage engines support at least 16 indexes per table and a total index length of at least 256 bytes. Most storage engines have higher limits.

For additional information about column indexes, see [Section 13.1.14, “CREATE INDEX Statement”](sql-statements.html#create-index).

- [Index Prefixes](optimization.html#column-indexes-prefix)
- [FULLTEXT Indexes](optimization.html#column-indexes-fulltext)
- [Spatial Indexes](optimization.html#column-indexes-spatial)
- [Indexes in the MEMORY Storage Engine](optimization.html#column-indexes-memory-storage-engine)

#### Index Prefixes



With `*`col_name`*(*`N`*)` syntax in an index specification for a string column, you can create an index that uses only the first *`N`* characters of the column. Indexing only a prefix of column values in this way can make the index file much smaller. When you index a [`BLOB`](data-types.html#blob) or [`TEXT`](data-types.html#blob) column, you *must* specify a prefix length for the index. For example:

```
CREATE TABLE test (blob_col BLOB, INDEX(blob_col(10)));
```

Prefixes can be up to 1000 bytes long (767 bytes for `InnoDB` tables, unless you have [`innodb_large_prefix`](innodb-storage-engine.html#sysvar_innodb_large_prefix) set).

Note

Prefix limits are measured in bytes, whereas the prefix length in [`CREATE TABLE`](sql-statements.html#create-table), [`ALTER TABLE`](sql-statements.html#alter-table), and [`CREATE INDEX`](sql-statements.html#create-index) statements is interpreted as number of characters for nonbinary string types ([`CHAR`](data-types.html#char), [`VARCHAR`](data-types.html#char), [`TEXT`](data-types.html#blob)) and number of bytes for binary string types ([`BINARY`](data-types.html#binary-varbinary), [`VARBINARY`](data-types.html#binary-varbinary), [`BLOB`](data-types.html#blob)). Take this into account when specifying a prefix length for a nonbinary string column that uses a multibyte character set.

If a search term exceeds the index prefix length, the index is used to exclude non-matching rows, and the remaining rows are examined for possible matches.

For additional information about index prefixes, see [Section 13.1.14, “CREATE INDEX Statement”](sql-statements.html#create-index).

#### FULLTEXT Indexes



`FULLTEXT` indexes are used for full-text searches. Only the [`InnoDB`](innodb-storage-engine.html) and [`MyISAM`](storage-engines.html#myisam-storage-engine) storage engines support `FULLTEXT` indexes and only for [`CHAR`](data-types.html#char), [`VARCHAR`](data-types.html#char), and [`TEXT`](data-types.html#blob) columns. Indexing always takes place over the entire column and column prefix indexing is not supported. For details, see [Section 12.9, “Full-Text Search Functions”](functions.html#fulltext-search).

Optimizations are applied to certain kinds of `FULLTEXT` queries against single `InnoDB` tables. Queries with these characteristics are particularly efficient:

- `FULLTEXT` queries that only return the document ID, or the document ID and the search rank.
- `FULLTEXT` queries that sort the matching rows in descending order of score and apply a `LIMIT` clause to take the top N matching rows. For this optimization to apply, there must be no `WHERE` clauses and only a single `ORDER BY` clause in descending order.
- `FULLTEXT` queries that retrieve only the `COUNT(*)` value of rows matching a search term, with no additional `WHERE` clauses. Code the `WHERE` clause as `WHERE MATCH(*`text`*) AGAINST ('*`other_text`*')`, without any `> 0` comparison operator.

For queries that contain full-text expressions, MySQL evaluates those expressions during the optimization phase of query execution. The optimizer does not just look at full-text expressions and make estimates, it actually evaluates them in the process of developing an execution plan.

An implication of this behavior is that [`EXPLAIN`](sql-statements.html#explain) for full-text queries is typically slower than for non-full-text queries for which no expression evaluation occurs during the optimization phase.

[`EXPLAIN`](sql-statements.html#explain) for full-text queries may show `Select tables optimized away` in the `Extra` column due to matching occurring during optimization; in this case, no table access need occur during later execution.

#### Spatial Indexes



You can create indexes on spatial data types. `MyISAM` and `InnoDB` support R-tree indexes on spatial types. Other storage engines use B-trees for indexing spatial types (except for `ARCHIVE`, which does not support spatial type indexing).

#### Indexes in the MEMORY Storage Engine



The `MEMORY` storage engine uses `HASH` indexes by default, but also supports `BTREE` indexes.

### 8.3.5 Multiple-Column Indexes



MySQL can create composite indexes (that is, indexes on multiple columns). An index may consist of up to 16 columns. For certain data types, you can index a prefix of the column (see [Section 8.3.4, “Column Indexes”](optimization.html#column-indexes)).

MySQL can use multiple-column indexes for queries that test all the columns in the index, or queries that test just the first column, the first two columns, the first three columns, and so on. If you specify the columns in the right order in the index definition, a single composite index can speed up several kinds of queries on the same table.

A multiple-column index can be considered a sorted array, the rows of which contain values that are created by concatenating the values of the indexed columns.

Note

As an alternative to a composite index, you can introduce a column that is “hashed” based on information from other columns. If this column is short, reasonably unique, and indexed, it might be faster than a “wide” index on many columns. In MySQL, it is very easy to use this extra column:

```
SELECT * FROM tbl_name
  WHERE hash_col=MD5(CONCAT(val1,val2))
  AND col1=val1 AND col2=val2;
```

Suppose that a table has the following specification:

```
CREATE TABLE test (
    id         INT NOT NULL,
    last_name  CHAR(30) NOT NULL,
    first_name CHAR(30) NOT NULL,
    PRIMARY KEY (id),
    INDEX name (last_name,first_name)
);
```

The `name` index is an index over the `last_name` and `first_name` columns. The index can be used for lookups in queries that specify values in a known range for combinations of `last_name` and `first_name` values. It can also be used for queries that specify just a `last_name` value because that column is a leftmost prefix of the index (as described later in this section). Therefore, the `name` index is used for lookups in the following queries:

```
SELECT * FROM test WHERE last_name='Jones';

SELECT * FROM test
  WHERE last_name='Jones' AND first_name='John';

SELECT * FROM test
  WHERE last_name='Jones'
  AND (first_name='John' OR first_name='Jon');

SELECT * FROM test
  WHERE last_name='Jones'
  AND first_name >='M' AND first_name < 'N';
```

However, the `name` index is *not* used for lookups in the following queries:

```
SELECT * FROM test WHERE first_name='John';

SELECT * FROM test
  WHERE last_name='Jones' OR first_name='John';
```

Suppose that you issue the following [`SELECT`](sql-statements.html#select) statement:

```
SELECT * FROM tbl_name
  WHERE col1=val1 AND col2=val2;
```

If a multiple-column index exists on `col1` and `col2`, the appropriate rows can be fetched directly. If separate single-column indexes exist on `col1` and `col2`, the optimizer attempts to use the Index Merge optimization (see [Section 8.2.1.3, “Index Merge Optimization”](optimization.html#index-merge-optimization)), or attempts to find the most restrictive index by deciding which index excludes more rows and using that index to fetch the rows.



If the table has a multiple-column index, any leftmost prefix of the index can be used by the optimizer to look up rows. For example, if you have a three-column index on `(col1, col2, col3)`, you have indexed search capabilities on `(col1)`, `(col1, col2)`, and `(col1, col2, col3)`.

MySQL cannot use the index to perform lookups if the columns do not form a leftmost prefix of the index. Suppose that you have the [`SELECT`](sql-statements.html#select) statements shown here:

```
SELECT * FROM tbl_name WHERE col1=val1;
SELECT * FROM tbl_name WHERE col1=val1 AND col2=val2;

SELECT * FROM tbl_name WHERE col2=val2;
SELECT * FROM tbl_name WHERE col2=val2 AND col3=val3;
```

If an index exists on `(col1, col2, col3)`, only the first two queries use the index. The third and fourth queries do involve indexed columns, but do not use an index to perform lookups because `(col2)` and `(col2, col3)` are not leftmost prefixes of `(col1, col2, col3)`.

### 8.3.6 Verifying Index Usage

Always check whether all your queries really use the indexes that you have created in the tables. Use the [`EXPLAIN`](sql-statements.html#explain) statement, as described in [Section 8.8.1, “Optimizing Queries with EXPLAIN”](optimization.html#using-explain).

### 8.3.7 InnoDB and MyISAM Index Statistics Collection

Storage engines collect statistics about tables for use by the optimizer. Table statistics are based on value groups, where a value group is a set of rows with the same key prefix value. For optimizer purposes, an important statistic is the average value group size.

MySQL uses the average value group size in the following ways:

- To estimate how many rows must be read for each [`ref`](optimization.html#jointype_ref) access

- To estimate how many rows a partial join will produce; that is, the number of rows that an operation of this form will produce:

  ```
  (...) JOIN tbl_name ON tbl_name.key = expr
  ```

As the average value group size for an index increases, the index is less useful for those two purposes because the average number of rows per lookup increases: For the index to be good for optimization purposes, it is best that each index value target a small number of rows in the table. When a given index value yields a large number of rows, the index is less useful and MySQL is less likely to use it.

The average value group size is related to table cardinality, which is the number of value groups. The [`SHOW INDEX`](sql-statements.html#show-index) statement displays a cardinality value based on *`N/S`*, where *`N`* is the number of rows in the table and *`S`* is the average value group size. That ratio yields an approximate number of value groups in the table.

For a join based on the `<=>` comparison operator, `NULL` is not treated differently from any other value: `NULL <=> NULL`, just as `*`N`* <=> *`N`*` for any other *`N`*.

However, for a join based on the `=` operator, `NULL` is different from non-`NULL` values: `*`expr1`* = *`expr2`*` is not true when *`expr1`* or *`expr2`* (or both) are `NULL`. This affects [`ref`](optimization.html#jointype_ref) accesses for comparisons of the form `*`tbl_name.key`* = *`expr`*`: MySQL will not access the table if the current value of *`expr`* is `NULL`, because the comparison cannot be true.

For `=` comparisons, it does not matter how many `NULL` values are in the table. For optimization purposes, the relevant value is the average size of the non-`NULL` value groups. However, MySQL does not currently enable that average size to be collected or used.

For `InnoDB` and `MyISAM` tables, you have some control over collection of table statistics by means of the [`innodb_stats_method`](innodb-storage-engine.html#sysvar_innodb_stats_method) and [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method) system variables, respectively. These variables have three possible values, which differ as follows:

- When the variable is set to `nulls_equal`, all `NULL` values are treated as identical (that is, they all form a single value group).

  If the `NULL` value group size is much higher than the average non-`NULL` value group size, this method skews the average value group size upward. This makes index appear to the optimizer to be less useful than it really is for joins that look for non-`NULL` values. Consequently, the `nulls_equal` method may cause the optimizer not to use the index for [`ref`](optimization.html#jointype_ref) accesses when it should.

- When the variable is set to `nulls_unequal`, `NULL` values are not considered the same. Instead, each `NULL` value forms a separate value group of size 1.

  If you have many `NULL` values, this method skews the average value group size downward. If the average non-`NULL` value group size is large, counting `NULL` values each as a group of size 1 causes the optimizer to overestimate the value of the index for joins that look for non-`NULL` values. Consequently, the `nulls_unequal` method may cause the optimizer to use this index for [`ref`](optimization.html#jointype_ref) lookups when other methods may be better.

- When the variable is set to `nulls_ignored`, `NULL` values are ignored.

If you tend to use many joins that use `<=>` rather than `=`, `NULL` values are not special in comparisons and one `NULL` is equal to another. In this case, `nulls_equal` is the appropriate statistics method.

The [`innodb_stats_method`](innodb-storage-engine.html#sysvar_innodb_stats_method) system variable has a global value; the [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method) system variable has both global and session values. Setting the global value affects statistics collection for tables from the corresponding storage engine. Setting the session value affects statistics collection only for the current client connection. This means that you can force a table's statistics to be regenerated with a given method without affecting other clients by setting the session value of [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method).

To regenerate `MyISAM` table statistics, you can use any of the following methods:

- Execute [**myisamchk --stats_method=\*`method_name`\* --analyze**](programs.html#myisamchk)
- Change the table to cause its statistics to go out of date (for example, insert a row and then delete it), and then set [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method) and issue an [`ANALYZE TABLE`](sql-statements.html#analyze-table) statement

Some caveats regarding the use of [`innodb_stats_method`](innodb-storage-engine.html#sysvar_innodb_stats_method) and [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method):

- You can force table statistics to be collected explicitly, as just described. However, MySQL may also collect statistics automatically. For example, if during the course of executing statements for a table, some of those statements modify the table, MySQL may collect statistics. (This may occur for bulk inserts or deletes, or some [`ALTER TABLE`](sql-statements.html#alter-table) statements, for example.) If this happens, the statistics are collected using whatever value [`innodb_stats_method`](innodb-storage-engine.html#sysvar_innodb_stats_method) or [`myisam_stats_method`](server-administration.html#sysvar_myisam_stats_method) has at the time. Thus, if you collect statistics using one method, but the system variable is set to the other method when a table's statistics are collected automatically later, the other method will be used.
- There is no way to tell which method was used to generate statistics for a given table.
- These variables apply only to `InnoDB` and `MyISAM` tables. Other storage engines have only one method for collecting table statistics. Usually it is closer to the `nulls_equal` method.

### 8.3.8 Comparison of B-Tree and Hash Indexes



Understanding the B-tree and hash data structures can help predict how different queries perform on different storage engines that use these data structures in their indexes, particularly for the `MEMORY` storage engine that lets you choose B-tree or hash indexes.

- [B-Tree Index Characteristics](optimization.html#btree-index-characteristics)
- [Hash Index Characteristics](optimization.html#hash-index-characteristics)

#### B-Tree Index Characteristics



A B-tree index can be used for column comparisons in expressions that use the [`=`](functions.html#operator_equal), [`>`](functions.html#operator_greater-than), [`>=`](functions.html#operator_greater-than-or-equal), [`<`](functions.html#operator_less-than), [`<=`](functions.html#operator_less-than-or-equal), or [`BETWEEN`](functions.html#operator_between) operators. The index also can be used for [`LIKE`](functions.html#operator_like) comparisons if the argument to [`LIKE`](functions.html#operator_like) is a constant string that does not start with a wildcard character. For example, the following [`SELECT`](sql-statements.html#select) statements use indexes:

```
SELECT * FROM tbl_name WHERE key_col LIKE 'Patrick%';
SELECT * FROM tbl_name WHERE key_col LIKE 'Pat%_ck%';
```

In the first statement, only rows with `'Patrick' <= *`key_col`* < 'Patricl'` are considered. In the second statement, only rows with `'Pat' <= *`key_col`* < 'Pau'` are considered.

The following [`SELECT`](sql-statements.html#select) statements do not use indexes:

```
SELECT * FROM tbl_name WHERE key_col LIKE '%Patrick%';
SELECT * FROM tbl_name WHERE key_col LIKE other_col;
```

In the first statement, the [`LIKE`](functions.html#operator_like) value begins with a wildcard character. In the second statement, the [`LIKE`](functions.html#operator_like) value is not a constant.

If you use `... LIKE '%*`string`*%'` and *`string`* is longer than three characters, MySQL uses the Turbo Boyer-Moore algorithm to initialize the pattern for the string and then uses this pattern to perform the search more quickly.



A search using `*`col_name`* IS NULL` employs indexes if *`col_name`* is indexed.

Any index that does not span all [`AND`](functions.html#operator_and) levels in the `WHERE` clause is not used to optimize the query. In other words, to be able to use an index, a prefix of the index must be used in every [`AND`](functions.html#operator_and) group.

The following `WHERE` clauses use indexes:

```
... WHERE index_part1=1 AND index_part2=2 AND other_column=3

    /* index = 1 OR index = 2 */
... WHERE index=1 OR A=10 AND index=2

    /* optimized like "index_part1='hello'" */
... WHERE index_part1='hello' AND index_part3=5

    /* Can use index on index1 but not on index2 or index3 */
... WHERE index1=1 AND index2=2 OR index1=3 AND index3=3;
```

These `WHERE` clauses do *not* use indexes:

```
    /* index_part1 is not used */
... WHERE index_part2=1 AND index_part3=2

    /*  Index is not used in both parts of the WHERE clause  */
... WHERE index=1 OR A=10

    /* No index spans all rows  */
... WHERE index_part1=1 OR index_part2=10
```

Sometimes MySQL does not use an index, even if one is available. One circumstance under which this occurs is when the optimizer estimates that using the index would require MySQL to access a very large percentage of the rows in the table. (In this case, a table scan is likely to be much faster because it requires fewer seeks.) However, if such a query uses `LIMIT` to retrieve only some of the rows, MySQL uses an index anyway, because it can much more quickly find the few rows to return in the result.

#### Hash Index Characteristics



Hash indexes have somewhat different characteristics from those just discussed:

- They are used only for equality comparisons that use the `=` or `<=>` operators (but are *very* fast). They are not used for comparison operators such as `<` that find a range of values. Systems that rely on this type of single-value lookup are known as “key-value stores”; to use MySQL for such applications, use hash indexes wherever possible.
- The optimizer cannot use a hash index to speed up `ORDER BY` operations. (This type of index cannot be used to search for the next entry in order.)
- MySQL cannot determine approximately how many rows there are between two values (this is used by the range optimizer to decide which index to use). This may affect some queries if you change a `MyISAM` or `InnoDB` table to a hash-indexed `MEMORY` table.
- Only whole keys can be used to search for a row. (With a B-tree index, any leftmost prefix of the key can be used to find rows.)

### 8.3.9 Use of Index Extensions



[`InnoDB`](innodb-storage-engine.html) automatically extends each secondary index by appending the primary key columns to it. Consider this table definition:

```
CREATE TABLE t1 (
  i1 INT NOT NULL DEFAULT 0,
  i2 INT NOT NULL DEFAULT 0,
  d DATE DEFAULT NULL,
  PRIMARY KEY (i1, i2),
  INDEX k_d (d)
) ENGINE = InnoDB;
```

This table defines the primary key on columns `(i1, i2)`. It also defines a secondary index `k_d` on column `(d)`, but internally `InnoDB` extends this index and treats it as columns `(d, i1, i2)`.

The optimizer takes into account the primary key columns of the extended secondary index when determining how and whether to use that index. This can result in more efficient query execution plans and better performance.

The optimizer can use extended secondary indexes for `ref`, `range`, and `index_merge` index access, for Loose Index Scan access, for join and sorting optimization, and for [`MIN()`](functions.html#function_min)/[`MAX()`](functions.html#function_max) optimization.

The following example shows how execution plans are affected by whether the optimizer uses extended secondary indexes. Suppose that `t1` is populated with these rows:

```
INSERT INTO t1 VALUES
(1, 1, '1998-01-01'), (1, 2, '1999-01-01'),
(1, 3, '2000-01-01'), (1, 4, '2001-01-01'),
(1, 5, '2002-01-01'), (2, 1, '1998-01-01'),
(2, 2, '1999-01-01'), (2, 3, '2000-01-01'),
(2, 4, '2001-01-01'), (2, 5, '2002-01-01'),
(3, 1, '1998-01-01'), (3, 2, '1999-01-01'),
(3, 3, '2000-01-01'), (3, 4, '2001-01-01'),
(3, 5, '2002-01-01'), (4, 1, '1998-01-01'),
(4, 2, '1999-01-01'), (4, 3, '2000-01-01'),
(4, 4, '2001-01-01'), (4, 5, '2002-01-01'),
(5, 1, '1998-01-01'), (5, 2, '1999-01-01'),
(5, 3, '2000-01-01'), (5, 4, '2001-01-01'),
(5, 5, '2002-01-01');
```

Now consider this query:

```
EXPLAIN SELECT COUNT(*) FROM t1 WHERE i1 = 3 AND d = '2000-01-01'
```

The execution plan depends on whether the extended index is used.

When the optimizer does not consider index extensions, it treats the index `k_d` as only `(d)`. [`EXPLAIN`](sql-statements.html#explain) for the query produces this result:

```
mysql> EXPLAIN SELECT COUNT(*) FROM t1 WHERE i1 = 3 AND d = '2000-01-01'\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: t1
         type: ref
possible_keys: PRIMARY,k_d
          key: k_d
      key_len: 4
          ref: const
         rows: 5
        Extra: Using where; Using index
```

When the optimizer takes index extensions into account, it treats `k_d` as `(d, i1, i2)`. In this case, it can use the leftmost index prefix `(d, i1)` to produce a better execution plan:

```
mysql> EXPLAIN SELECT COUNT(*) FROM t1 WHERE i1 = 3 AND d = '2000-01-01'\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: t1
         type: ref
possible_keys: PRIMARY,k_d
          key: k_d
      key_len: 8
          ref: const,const
         rows: 1
        Extra: Using index
```

In both cases, `key` indicates that the optimizer will use secondary index `k_d` but the [`EXPLAIN`](sql-statements.html#explain) output shows these improvements from using the extended index:

- `key_len` goes from 4 bytes to 8 bytes, indicating that key lookups use columns `d` and `i1`, not just `d`.
- The `ref` value changes from `const` to `const,const` because the key lookup uses two key parts, not one.
- The `rows` count decreases from 5 to 1, indicating that `InnoDB` should need to examine fewer rows to produce the result.
- The `Extra` value changes from `Using where; Using index` to `Using index`. This means that rows can be read using only the index, without consulting columns in the data row.

Differences in optimizer behavior for use of extended indexes can also be seen with [`SHOW STATUS`](sql-statements.html#show-status):

```
FLUSH TABLE t1;
FLUSH STATUS;
SELECT COUNT(*) FROM t1 WHERE i1 = 3 AND d = '2000-01-01';
SHOW STATUS LIKE 'handler_read%'
```

The preceding statements include [`FLUSH TABLES`](sql-statements.html#flush-tables) and [`FLUSH STATUS`](sql-statements.html#flush-status) to flush the table cache and clear the status counters.

Without index extensions, [`SHOW STATUS`](sql-statements.html#show-status) produces this result:

```
+-----------------------+-------+
| Variable_name         | Value |
+-----------------------+-------+
| Handler_read_first    | 0     |
| Handler_read_key      | 1     |
| Handler_read_last     | 0     |
| Handler_read_next     | 5     |
| Handler_read_prev     | 0     |
| Handler_read_rnd      | 0     |
| Handler_read_rnd_next | 0     |
+-----------------------+-------+
```

With index extensions, [`SHOW STATUS`](sql-statements.html#show-status) produces this result. The [`Handler_read_next`](server-administration.html#statvar_Handler_read_next) value decreases from 5 to 1, indicating more efficient use of the index:

```
+-----------------------+-------+
| Variable_name         | Value |
+-----------------------+-------+
| Handler_read_first    | 0     |
| Handler_read_key      | 1     |
| Handler_read_last     | 0     |
| Handler_read_next     | 1     |
| Handler_read_prev     | 0     |
| Handler_read_rnd      | 0     |
| Handler_read_rnd_next | 0     |
+-----------------------+-------+
```

The `use_index_extensions` flag of the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable permits control over whether the optimizer takes the primary key columns into account when determining how to use an `InnoDB` table's secondary indexes. By default, `use_index_extensions` is enabled. To check whether disabling use of index extensions will improve performance, use this statement:

```
SET optimizer_switch = 'use_index_extensions=off';
```

Use of index extensions by the optimizer is subject to the usual limits on the number of key parts in an index (16) and the maximum key length (3072 bytes).

### 8.3.10 Optimizer Use of Generated Column Indexes

MySQL supports indexes on generated columns. For example:

```
CREATE TABLE t1 (f1 INT, gc INT AS (f1 + 1) STORED, INDEX (gc));
```

The generated column, `gc`, is defined as the expression `f1 + 1`. The column is also indexed and the optimizer can take that index into account during execution plan construction. In the following query, the `WHERE` clause refers to `gc` and the optimizer considers whether the index on that column yields a more efficient plan:

```
SELECT * FROM t1 WHERE gc > 9;
```

The optimizer can use indexes on generated columns to generate execution plans, even in the absence of direct references in queries to those columns by name. This occurs if the `WHERE`, `ORDER BY`, or `GROUP BY` clause refers to an expression that matches the definition of some indexed generated column. The following query does not refer directly to `gc` but does use an expression that matches the definition of `gc`:

```
SELECT * FROM t1 WHERE f1 + 1 > 9;
```

The optimizer recognizes that the expression `f1 + 1` matches the definition of `gc` and that `gc` is indexed, so it considers that index during execution plan construction. You can see this using [`EXPLAIN`](sql-statements.html#explain):

```
mysql> EXPLAIN SELECT * FROM t1 WHERE f1 + 1 > 9\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: t1
   partitions: NULL
         type: range
possible_keys: gc
          key: gc
      key_len: 5
          ref: NULL
         rows: 1
     filtered: 100.00
        Extra: Using index condition
```

In effect, the optimizer has replaced the expression `f1 + 1` with the name of the generated column that matches the expression. That is also apparent in the rewritten query available in the extended [`EXPLAIN`](sql-statements.html#explain) information displayed by [`SHOW WARNINGS`](sql-statements.html#show-warnings):

```
mysql> SHOW WARNINGS\G
*************************** 1. row ***************************
  Level: Note
   Code: 1003
Message: /* select#1 */ select `test`.`t1`.`f1` AS `f1`,`test`.`t1`.`gc`
         AS `gc` from `test`.`t1` where (`test`.`t1`.`gc` > 9)
```

The following restrictions and conditions apply to the optimizer's use of generated column indexes:

- For a query expression to match a generated column definition, the expression must be identical and it must have the same result type. For example, if the generated column expression is `f1 + 1`, the optimizer will not recognize a match if the query uses `1 + f1`, or if `f1 + 1` (an integer expression) is compared with a string.

- The optimization applies to these operators: [`=`](functions.html#operator_equal), [`<`](functions.html#operator_less-than), [`<=`](functions.html#operator_less-than-or-equal), [`>`](functions.html#operator_greater-than), [`>=`](functions.html#operator_greater-than-or-equal), [`BETWEEN`](functions.html#operator_between), and [`IN()`](functions.html#operator_in).

  For operators other than [`BETWEEN`](functions.html#operator_between) and [`IN()`](functions.html#operator_in), either operand can be replaced by a matching generated column. For [`BETWEEN`](functions.html#operator_between) and [`IN()`](functions.html#operator_in), only the first argument can be replaced by a matching generated column, and the other arguments must have the same result type. [`BETWEEN`](functions.html#operator_between) and [`IN()`](functions.html#operator_in) are not yet supported for comparisons involving JSON values.

- The generated column must be defined as an expression that contains at least a function call or one of the operators mentioned in the preceding item. The expression cannot consist of a simple reference to another column. For example, `gc INT AS (f1) STORED` consists only of a column reference, so indexes on `gc` are not considered.

- For comparisons of strings to indexed generated columns that compute a value from a JSON function that returns a quoted string, [`JSON_UNQUOTE()`](functions.html#function_json-unquote) is needed in the column definition to remove the extra quotes from the function value. (For direct comparison of a string to the function result, the JSON comparator handles quote removal, but this does not occur for index lookups.) For example, instead of writing a column definition like this:

  ```
  doc_name TEXT AS (JSON_EXTRACT(jdoc, '$.name')) STORED
  ```

  Write it like this:

  ```
  doc_name TEXT AS (JSON_UNQUOTE(JSON_EXTRACT(jdoc, '$.name'))) STORED
  ```

  With the latter definition, the optimizer can detect a match for both of these comparisons:

  ```
  ... WHERE JSON_EXTRACT(jdoc, '$.name') = 'some_string' ...
  ... WHERE JSON_UNQUOTE(JSON_EXTRACT(jdoc, '$.name')) = 'some_string' ...
  ```

  Without [`JSON_UNQUOTE()`](functions.html#function_json-unquote) in the column definition, the optimizer detects a match only for the first of those comparisons.

- If the optimizer fails to choose the desired index, an index hint can be used to force the optimizer to make a different choice.

### 8.3.11 Indexed Lookups from TIMESTAMP Columns



Temporal values are stored in [`TIMESTAMP`](data-types.html#datetime) columns as UTC values, and values inserted into and retrieved from [`TIMESTAMP`](data-types.html#datetime) columns are converted between the session time zone and UTC. (This is the same type of conversion performed by the [`CONVERT_TZ()`](functions.html#function_convert-tz) function. If the session time zone is UTC, there is effectively no time zone conversion.)



Due to conventions for local time zone changes such as Daylight Saving Time (DST), conversions between UTC and non-UTC time zones are not one-to-one in both directions. UTC values that are distinct may not be distinct in another time zone. The following example shows distinct UTC values that become identical in a non-UTC time zone:

```
mysql> CREATE TABLE tstable (ts TIMESTAMP);
mysql> SET time_zone = 'UTC'; -- insert UTC values
mysql> INSERT INTO tstable VALUES
       ('2018-10-28 00:30:00'),
       ('2018-10-28 01:30:00');
mysql> SELECT ts FROM tstable;
+---------------------+
| ts                  |
+---------------------+
| 2018-10-28 00:30:00 |
| 2018-10-28 01:30:00 |
+---------------------+
mysql> SET time_zone = 'MET'; -- retrieve non-UTC values
mysql> SELECT ts FROM tstable;
+---------------------+
| ts                  |
+---------------------+
| 2018-10-28 02:30:00 |
| 2018-10-28 02:30:00 |
+---------------------+
```

Note

To use named time zones such as `'MET'` or `'Europe/Amsterdam'`, the time zone tables must be properly set up. For instructions, see [Section 5.1.13, “MySQL Server Time Zone Support”](server-administration.html#time-zone-support).

You can see that the two distinct UTC values are the same when converted to the `'MET'` time zone. This phenomenon can lead to different results for a given [`TIMESTAMP`](data-types.html#datetime) column query, depending on whether the optimizer uses an index to execute the query.

Suppose that a query selects values from the table shown earlier using a `WHERE` clause to search the `ts` column for a single specific value such as a user-provided timestamp literal:

```
SELECT ts FROM tstable
WHERE ts = 'literal';
```

Suppose further that the query executes under these conditions:

- The session time zone is not UTC and has a DST shift. For example:

  ```
  SET time_zone = 'MET';
  ```

- Unique UTC values stored in the [`TIMESTAMP`](data-types.html#datetime) column are not unique in the session time zone due to DST shifts. (The example shown earlier illustrates how this can occur.)

- The query specifies a search value that is within the hour of entry into DST in the session time zone.

Under those conditions, the comparison in the `WHERE` clause occurs in different ways for nonindexed and indexed lookups and leads to different results:

- If there is no index or the optimizer cannot use it, comparisons occur in the session time zone. The optimizer performs a table scan in which it retrieves each `ts` column value, converts it from UTC to the session time zone, and compares it to the search value (also interpreted in the session time zone):

  ```
  mysql> SELECT ts FROM tstable
         WHERE ts = '2018-10-28 02:30:00';
  +---------------------+
  | ts                  |
  +---------------------+
  | 2018-10-28 02:30:00 |
  | 2018-10-28 02:30:00 |
  +---------------------+
  ```

  Because the stored `ts` values are converted to the session time zone, it is possible for the query to return two timestamp values that are distinct as UTC values but equal in the session time zone: One value that occurs before the DST shift when clocks are changed, and one value that was occurs after the DST shift.

- If there is a usable index, comparisons occur in UTC. The optimizer performs an index scan, first converting the search value from the session time zone to UTC, then comparing the result to the UTC index entries:

  ```
  mysql> ALTER TABLE tstable ADD INDEX (ts);
  mysql> SELECT ts FROM tstable
         WHERE ts = '2018-10-28 02:30:00';
  +---------------------+
  | ts                  |
  +---------------------+
  | 2018-10-28 02:30:00 |
  +---------------------+
  ```

  In this case, the (converted) search value is matched only to index entries, and because the index entries for the distinct stored UTC values are also distinct, the search value can match only one of them.

Due to different optimizer operation for nonindexed and indexed lookups, the query produces different results in each case. The result from the nonindexed lookup returns all values that match in the session time zone. The indexed lookup cannot do so:

- It is performed within the storage engine, which knows only about UTC values.
- For the two distinct session time zone values that map to the same UTC value, the indexed lookup matches only the corresponding UTC index entry and returns only a single row.

In the preceding discussion, the data set stored in `tstable` happens to consist of distinct UTC values. In such cases, all index-using queries of the form shown match at most one index entry.

If the index is not `UNIQUE`, it is possible for the table (and the index) to store multiple instances of a given UTC value. For example, the `ts` column might contain multiple instances of the UTC value `'2018-10-28 00:30:00'`. In this case, the index-using query would return each of them (converted to the MET value `'2018-10-28 02:30:00'` in the result set). It remains true that index-using queries match the converted search value to a single value in the UTC index entries, rather than matching multiple UTC values that convert to the search value in the session time zone.

If it is important to return all `ts` values that match in the session time zone, the workaround is to suppress use of the index with an `IGNORE INDEX` hint:

```
mysql> SELECT ts FROM tstable
       IGNORE INDEX (ts)
       WHERE ts = '2018-10-28 02:30:00';
+---------------------+
| ts                  |
+---------------------+
| 2018-10-28 02:30:00 |
| 2018-10-28 02:30:00 |
+---------------------+
```

The same lack of one-to-one mapping for time zone conversions in both directions occurs in other contexts as well, such as conversions performed with the [`FROM_UNIXTIME()`](functions.html#function_from-unixtime) and [`UNIX_TIMESTAMP()`](functions.html#function_unix-timestamp) functions. See [Section 12.6, “Date and Time Functions”](functions.html#date-and-time-functions).

## 8.4 Optimizing Database Structure

- [8.4.1 Optimizing Data Size](optimization.html#data-size)
- [8.4.2 Optimizing MySQL Data Types](optimization.html#optimize-data-types)
- [8.4.3 Optimizing for Many Tables](optimization.html#optimize-multi-tables)
- [8.4.4 Internal Temporary Table Use in MySQL](optimization.html#internal-temporary-tables)
- [8.4.5 Limits on Number of Databases and Tables](optimization.html#database-count-limit)
- [8.4.6 Limits on Table Size](optimization.html#table-size-limit)
- [8.4.7 Limits on Table Column Count and Row Size](optimization.html#column-count-limit)

In your role as a database designer, look for the most efficient way to organize your schemas, tables, and columns. As when tuning application code, you minimize I/O, keep related items together, and plan ahead so that performance stays high as the data volume increases. Starting with an efficient database design makes it easier for team members to write high-performing application code, and makes the database likely to endure as applications evolve and are rewritten.

### 8.4.1 Optimizing Data Size



Design your tables to minimize their space on the disk. This can result in huge improvements by reducing the amount of data written to and read from disk. Smaller tables normally require less main memory while their contents are being actively processed during query execution. Any space reduction for table data also results in smaller indexes that can be processed faster.

MySQL supports many different storage engines (table types) and row formats. For each table, you can decide which storage and indexing method to use. Choosing the proper table format for your application can give you a big performance gain. See [Chapter 14, *The InnoDB Storage Engine*](innodb-storage-engine.html), and [Chapter 15, *Alternative Storage Engines*](storage-engines.html).

You can get better performance for a table and minimize storage space by using the techniques listed here:

- [Table Columns](optimization.html#data-size-table-columns)
- [Row Format](optimization.html#data-size-row-format)
- [Indexes](optimization.html#data-size-indexes)
- [Joins](optimization.html#data-size-joins)
- [Normalization](optimization.html#data-size-normalization)

#### Table Columns

- Use the most efficient (smallest) data types possible. MySQL has many specialized types that save disk space and memory. For example, use the smaller integer types if possible to get smaller tables. [`MEDIUMINT`](data-types.html#integer-types) is often a better choice than [`INT`](data-types.html#integer-types) because a [`MEDIUMINT`](data-types.html#integer-types) column uses 25% less space.
- Declare columns to be `NOT NULL` if possible. It makes SQL operations faster, by enabling better use of indexes and eliminating overhead for testing whether each value is `NULL`. You also save some storage space, one bit per column. If you really need `NULL` values in your tables, use them. Just avoid the default setting that allows `NULL` values in every column.

#### Row Format

- `InnoDB` tables are created using the `DYNAMIC` row format by default. To use a row format other than `DYNAMIC`, configure [`innodb_default_row_format`](innodb-storage-engine.html#sysvar_innodb_default_row_format), or specify the `ROW_FORMAT` option explicitly in a [`CREATE TABLE`](sql-statements.html#create-table) or [`ALTER TABLE`](sql-statements.html#alter-table) statement.

  The compact family of row formats, which includes `COMPACT`, `DYNAMIC`, and `COMPRESSED`, decreases row storage space at the cost of increasing CPU use for some operations. If your workload is a typical one that is limited by cache hit rates and disk speed it is likely to be faster. If it is a rare case that is limited by CPU speed, it might be slower.

  The compact family of row formats also optimizes [`CHAR`](data-types.html#char) column storage when using a variable-length character set such as `utf8mb3` or `utf8mb4`. With `ROW_FORMAT=REDUNDANT`, `CHAR(*`N`*)` occupies *`N`* × the maximum byte length of the character set. Many languages can be written primarily using single-byte `utf8` characters, so a fixed storage length often wastes space. With the compact family of rows formats, `InnoDB` allocates a variable amount of storage in the range of *`N`* to *`N`* × the maximum byte length of the character set for these columns by stripping trailing spaces. The minimum storage length is *`N`* bytes to facilitate in-place updates in typical cases. For more information, see [Section 14.11, “InnoDB Row Formats”](innodb-storage-engine.html#innodb-row-format).

- To minimize space even further by storing table data in compressed form, specify `ROW_FORMAT=COMPRESSED` when creating `InnoDB` tables, or run the [**myisampack**](programs.html#myisampack) command on an existing `MyISAM` table. (`InnoDB` compressed tables are readable and writable, while `MyISAM` compressed tables are read-only.)

- For `MyISAM` tables, if you do not have any variable-length columns ([`VARCHAR`](data-types.html#char), [`TEXT`](data-types.html#blob), or [`BLOB`](data-types.html#blob) columns), a fixed-size row format is used. This is faster but may waste some space. See [Section 15.2.3, “MyISAM Table Storage Formats”](storage-engines.html#myisam-table-formats). You can hint that you want to have fixed length rows even if you have [`VARCHAR`](data-types.html#char) columns with the [`CREATE TABLE`](sql-statements.html#create-table) option `ROW_FORMAT=FIXED`.

#### Indexes

- The primary index of a table should be as short as possible. This makes identification of each row easy and efficient. For `InnoDB` tables, the primary key columns are duplicated in each secondary index entry, so a short primary key saves considerable space if you have many secondary indexes.
- Create only the indexes that you need to improve query performance. Indexes are good for retrieval, but slow down insert and update operations. If you access a table mostly by searching on a combination of columns, create a single composite index on them rather than a separate index for each column. The first part of the index should be the column most used. If you *always* use many columns when selecting from the table, the first column in the index should be the one with the most duplicates, to obtain better compression of the index.
- If it is very likely that a long string column has a unique prefix on the first number of characters, it is better to index only this prefix, using MySQL's support for creating an index on the leftmost part of the column (see [Section 13.1.14, “CREATE INDEX Statement”](sql-statements.html#create-index)). Shorter indexes are faster, not only because they require less disk space, but because they also give you more hits in the index cache, and thus fewer disk seeks. See [Section 5.1.1, “Configuring the Server”](server-administration.html#server-configuration).

#### Joins

- In some circumstances, it can be beneficial to split into two a table that is scanned very often. This is especially true if it is a dynamic-format table and it is possible to use a smaller static format table that can be used to find the relevant rows when scanning the table.
- Declare columns with identical information in different tables with identical data types, to speed up joins based on the corresponding columns.
- Keep column names simple, so that you can use the same name across different tables and simplify join queries. For example, in a table named `customer`, use a column name of `name` instead of `customer_name`. To make your names portable to other SQL servers, consider keeping them shorter than 18 characters.

#### Normalization

- Normally, try to keep all data nonredundant (observing what is referred to in database theory as third normal form). Instead of repeating lengthy values such as names and addresses, assign them unique IDs, repeat these IDs as needed across multiple smaller tables, and join the tables in queries by referencing the IDs in the join clause.
- If speed is more important than disk space and the maintenance costs of keeping multiple copies of data, for example in a business intelligence scenario where you analyze all the data from large tables, you can relax the normalization rules, duplicating information or creating summary tables to gain more speed.

### 8.4.2 Optimizing MySQL Data Types

- [8.4.2.1 Optimizing for Numeric Data](optimization.html#optimize-numeric)
- [8.4.2.2 Optimizing for Character and String Types](optimization.html#optimize-character)
- [8.4.2.3 Optimizing for BLOB Types](optimization.html#optimize-blob)
- [8.4.2.4 Using PROCEDURE ANALYSE](optimization.html#procedure-analyse)

#### 8.4.2.1 Optimizing for Numeric Data



- For unique IDs or other values that can be represented as either strings or numbers, prefer numeric columns to string columns. Since large numeric values can be stored in fewer bytes than the corresponding strings, it is faster and takes less memory to transfer and compare them.
- If you are using numeric data, it is faster in many cases to access information from a database (using a live connection) than to access a text file. Information in the database is likely to be stored in a more compact format than in the text file, so accessing it involves fewer disk accesses. You also save code in your application because you can avoid parsing the text file to find line and column boundaries.

#### 8.4.2.2 Optimizing for Character and String Types



For character and string columns, follow these guidelines:

- Use binary collation order for fast comparison and sort operations, when you do not need language-specific collation features. You can use the [`BINARY`](functions.html#operator_binary) operator to use binary collation within a particular query.
- When comparing values from different columns, declare those columns with the same character set and collation wherever possible, to avoid string conversions while running the query.
- For column values less than 8KB in size, use binary `VARCHAR` instead of `BLOB`. The `GROUP BY` and `ORDER BY` clauses can generate temporary tables, and these temporary tables can use the `MEMORY` storage engine if the original table does not contain any `BLOB` columns.
- If a table contains string columns such as name and address, but many queries do not retrieve those columns, consider splitting the string columns into a separate table and using join queries with a foreign key when necessary. When MySQL retrieves any value from a row, it reads a data block containing all the columns of that row (and possibly other adjacent rows). Keeping each row small, with only the most frequently used columns, allows more rows to fit in each data block. Such compact tables reduce disk I/O and memory usage for common queries.
- When you use a randomly generated value as a primary key in an `InnoDB` table, prefix it with an ascending value such as the current date and time if possible. When consecutive primary values are physically stored near each other, `InnoDB` can insert and retrieve them faster.
- See [Section 8.4.2.1, “Optimizing for Numeric Data”](optimization.html#optimize-numeric) for reasons why a numeric column is usually preferable to an equivalent string column.

#### 8.4.2.3 Optimizing for BLOB Types



- When storing a large blob containing textual data, consider compressing it first. Do not use this technique when the entire table is compressed by `InnoDB` or `MyISAM`.
- For a table with several columns, to reduce memory requirements for queries that do not use the BLOB column, consider splitting the BLOB column into a separate table and referencing it with a join query when needed.
- Since the performance requirements to retrieve and display a BLOB value might be very different from other data types, you could put the BLOB-specific table on a different storage device or even a separate database instance. For example, to retrieve a BLOB might require a large sequential disk read that is better suited to a traditional hard drive than to an [SSD device](glossary.html#glos_ssd).
- See [Section 8.4.2.2, “Optimizing for Character and String Types”](optimization.html#optimize-character) for reasons why a binary `VARCHAR` column is sometimes preferable to an equivalent BLOB column.
- Rather than testing for equality against a very long text string, you can store a hash of the column value in a separate column, index that column, and test the hashed value in queries. (Use the `MD5()` or `CRC32()` function to produce the hash value.) Since hash functions can produce duplicate results for different inputs, you still include a clause `AND *`blob_column`* = *`long_string_value`*` in the query to guard against false matches; the performance benefit comes from the smaller, easily scanned index for the hashed values.

#### 8.4.2.4 Using PROCEDURE ANALYSE

```
ANALYSE([*`max_elements`*[,*`max_memory`*]])
```



Note

`PROCEDURE ANALYSE()` is deprecated as of MySQL 5.7.18, and is removed in MySQL 8.0.

`ANALYSE()` examines the result from a query and returns an analysis of the results that suggests optimal data types for each column that may help reduce table sizes. To obtain this analysis, append `PROCEDURE ANALYSE` to the end of a [`SELECT`](sql-statements.html#select) statement:

```
SELECT ... FROM ... WHERE ... PROCEDURE ANALYSE([max_elements,[max_memory]])
```

For example:

```
SELECT col1, col2 FROM table1 PROCEDURE ANALYSE(10, 2000);
```

The results show some statistics for the values returned by the query, and propose an optimal data type for the columns. This can be helpful for checking your existing tables, or after importing new data. You may need to try different settings for the arguments so that `PROCEDURE ANALYSE()` does not suggest the [`ENUM`](data-types.html#enum) data type when it is not appropriate.

The arguments are optional and are used as follows:

- *`max_elements`* (default 256) is the maximum number of distinct values that `ANALYSE()` notices per column. This is used by `ANALYSE()` to check whether the optimal data type should be of type [`ENUM`](data-types.html#enum); if there are more than *`max_elements`* distinct values, then [`ENUM`](data-types.html#enum) is not a suggested type.
- *`max_memory`* (default 8192) is the maximum amount of memory that `ANALYSE()` should allocate per column while trying to find all distinct values.

A `PROCEDURE` clause is not permitted in a [`UNION`](sql-statements.html#union) statement.

### 8.4.3 Optimizing for Many Tables

- [8.4.3.1 How MySQL Opens and Closes Tables](optimization.html#table-cache)
- [8.4.3.2 Disadvantages of Creating Many Tables in the Same Database](optimization.html#creating-many-tables)



Some techniques for keeping individual queries fast involve splitting data across many tables. When the number of tables runs into the thousands or even millions, the overhead of dealing with all these tables becomes a new performance consideration.

#### 8.4.3.1 How MySQL Opens and Closes Tables



When you execute a [**mysqladmin status**](programs.html#mysqladmin) command, you should see something like this:

```
Uptime: 426 Running threads: 1 Questions: 11082
Reloads: 1 Open tables: 12
```

The `Open tables` value of 12 can be somewhat puzzling if you have fewer than 12 tables.

MySQL is multithreaded, so there may be many clients issuing queries for a given table simultaneously. To minimize the problem with multiple client sessions having different states on the same table, the table is opened independently by each concurrent session. This uses additional memory but normally increases performance. With `MyISAM` tables, one extra file descriptor is required for the data file for each client that has the table open. (By contrast, the index file descriptor is shared between all sessions.)

The [`table_open_cache`](server-administration.html#sysvar_table_open_cache) and [`max_connections`](server-administration.html#sysvar_max_connections) system variables affect the maximum number of files the server keeps open. If you increase one or both of these values, you may run up against a limit imposed by your operating system on the per-process number of open file descriptors. Many operating systems permit you to increase the open-files limit, although the method varies widely from system to system. Consult your operating system documentation to determine whether it is possible to increase the limit and how to do so.

[`table_open_cache`](server-administration.html#sysvar_table_open_cache) is related to [`max_connections`](server-administration.html#sysvar_max_connections). For example, for 200 concurrent running connections, specify a table cache size of at least `200 * *`N`*`, where *`N`* is the maximum number of tables per join in any of the queries which you execute. You must also reserve some extra file descriptors for temporary tables and files.

Make sure that your operating system can handle the number of open file descriptors implied by the [`table_open_cache`](server-administration.html#sysvar_table_open_cache) setting. If [`table_open_cache`](server-administration.html#sysvar_table_open_cache) is set too high, MySQL may run out of file descriptors and exhibit symptoms such as refusing connections or failing to perform queries.

Also take into account that the `MyISAM` storage engine needs two file descriptors for each unique open table. For a partitioned `MyISAM` table, two file descriptors are required for each partition of the opened table. (When `MyISAM` opens a partitioned table, it opens every partition of this table, whether or not a given partition is actually used. See [MyISAM and partition file descriptor usage](partitioning.html#partitioning-limitations-myisam-file-descriptors).) To increase the number of file descriptors available to MySQL, set the [`open_files_limit`](server-administration.html#sysvar_open_files_limit) system variable. See [Section B.4.2.17, “File Not Found and Similar Errors”](error-handling.html#not-enough-file-handles).

The cache of open tables is kept at a level of [`table_open_cache`](server-administration.html#sysvar_table_open_cache) entries. The server autosizes the cache size at startup. To set the size explicitly, set the [`table_open_cache`](server-administration.html#sysvar_table_open_cache) system variable at startup. MySQL may temporarily open more tables than this to execute queries, as described later in this section.

MySQL closes an unused table and removes it from the table cache under the following circumstances:

- When the cache is full and a thread tries to open a table that is not in the cache.
- When the cache contains more than [`table_open_cache`](server-administration.html#sysvar_table_open_cache) entries and a table in the cache is no longer being used by any threads.
- When a table-flushing operation occurs. This happens when someone issues a [`FLUSH TABLES`](sql-statements.html#flush-tables) statement or executes a [**mysqladmin flush-tables**](programs.html#mysqladmin) or [**mysqladmin refresh**](programs.html#mysqladmin) command.

When the table cache fills up, the server uses the following procedure to locate a cache entry to use:

- Tables not currently in use are released, beginning with the table least recently used.
- If a new table must be opened, but the cache is full and no tables can be released, the cache is temporarily extended as necessary. When the cache is in a temporarily extended state and a table goes from a used to unused state, the table is closed and released from the cache.

A `MyISAM` table is opened for each concurrent access. This means the table needs to be opened twice if two threads access the same table or if a thread accesses the table twice in the same query (for example, by joining the table to itself). Each concurrent open requires an entry in the table cache. The first open of any `MyISAM` table takes two file descriptors: one for the data file and one for the index file. Each additional use of the table takes only one file descriptor for the data file. The index file descriptor is shared among all threads.

If you are opening a table with the `HANDLER *`tbl_name`* OPEN` statement, a dedicated table object is allocated for the thread. This table object is not shared by other threads and is not closed until the thread calls `HANDLER *`tbl_name`* CLOSE` or the thread terminates. When this happens, the table is put back in the table cache (if the cache is not full). See [Section 13.2.4, “HANDLER Statement”](sql-statements.html#handler).

To determine whether your table cache is too small, check the [`Opened_tables`](server-administration.html#statvar_Opened_tables) status variable, which indicates the number of table-opening operations since the server started:

```
mysql> SHOW GLOBAL STATUS LIKE 'Opened_tables';
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| Opened_tables | 2741  |
+---------------+-------+
```

If the value is very large or increases rapidly, even when you have not issued many [`FLUSH TABLES`](sql-statements.html#flush-tables) statements, increase the [`table_open_cache`](server-administration.html#sysvar_table_open_cache) value at server startup.

#### 8.4.3.2 Disadvantages of Creating Many Tables in the Same Database



If you have many `MyISAM` tables in the same database directory, open, close, and create operations are slow. If you execute [`SELECT`](sql-statements.html#select) statements on many different tables, there is a little overhead when the table cache is full, because for every table that has to be opened, another must be closed. You can reduce this overhead by increasing the number of entries permitted in the table cache.

### 8.4.4 Internal Temporary Table Use in MySQL



In some cases, the server creates internal temporary tables while processing statements. Users have no direct control over when this occurs.

The server creates temporary tables under conditions such as these:

- Evaluation of [`UNION`](sql-statements.html#union) statements, with some exceptions described later.
- Evaluation of some views, such those that use the `TEMPTABLE` algorithm, [`UNION`](sql-statements.html#union), or aggregation.
- Evaluation of derived tables (see [Section 13.2.10.8, “Derived Tables”](sql-statements.html#derived-tables)).
- Tables created for subquery or semijoin materialization (see [Section 8.2.2, “Optimizing Subqueries, Derived Tables, and View References”](optimization.html#subquery-optimization)).
- Evaluation of statements that contain an `ORDER BY` clause and a different `GROUP BY` clause, or for which the `ORDER BY` or `GROUP BY` contains columns from tables other than the first table in the join queue.
- Evaluation of `DISTINCT` combined with `ORDER BY` may require a temporary table.
- For queries that use the `SQL_SMALL_RESULT` modifier, MySQL uses an in-memory temporary table, unless the query also contains elements (described later) that require on-disk storage.
- To evaluate [`INSERT ... SELECT`](sql-statements.html#insert-select) statements that select from and insert into the same table, MySQL creates an internal temporary table to hold the rows from the [`SELECT`](sql-statements.html#select), then inserts those rows into the target table. See [Section 13.2.5.1, “INSERT ... SELECT Statement”](sql-statements.html#insert-select).
- Evaluation of multiple-table [`UPDATE`](sql-statements.html#update) statements.
- Evaluation of [`GROUP_CONCAT()`](functions.html#function_group-concat) or [`COUNT(DISTINCT)`](functions.html#function_count) expressions.

To determine whether a statement requires a temporary table, use [`EXPLAIN`](sql-statements.html#explain) and check the `Extra` column to see whether it says `Using temporary` (see [Section 8.8.1, “Optimizing Queries with EXPLAIN”](optimization.html#using-explain)). `EXPLAIN` will not necessarily say `Using temporary` for derived or materialized temporary tables.

Some query conditions prevent the use of an in-memory temporary table, in which case the server uses an on-disk table instead:

- Presence of a [`BLOB`](data-types.html#blob) or [`TEXT`](data-types.html#blob) column in the table. This includes user-defined variables having a string value because they are treated as [`BLOB`](data-types.html#blob) or [`TEXT`](data-types.html#blob) columns, depending on whether their value is a binary or nonbinary string, respectively.
- Presence of any string column with a maximum length larger than 512 (bytes for binary strings, characters for nonbinary strings) in the [`SELECT`](sql-statements.html#select) list, if [`UNION`](sql-statements.html#union) or [`UNION ALL`](sql-statements.html#union) is used.
- The [`SHOW COLUMNS`](sql-statements.html#show-columns) and [`DESCRIBE`](sql-statements.html#describe) statements use `BLOB` as the type for some columns, thus the temporary table used for the results is an on-disk table.

The server does not use a temporary table for [`UNION`](sql-statements.html#union) statements that meet certain qualifications. Instead, it retains from temporary table creation only the data structures necessary to perform result column typecasting. The table is not fully instantiated and no rows are written to or read from it; rows are sent directly to the client. The result is reduced memory and disk requirements, and smaller delay before the first row is sent to the client because the server need not wait until the last query block is executed. [`EXPLAIN`](sql-statements.html#explain) and optimizer trace output reflects this execution strategy: The `UNION RESULT` query block is not present because that block corresponds to the part that reads from the temporary table.

These conditions qualify a `UNION` for evaluation without a temporary table:

- The union is `UNION ALL`, not `UNION` or `UNION DISTINCT`.
- There is no global `ORDER BY` clause.
- The union is not the top-level query block of an `{INSERT | REPLACE} ... SELECT ...` statement.

#### Internal Temporary Table Storage Engine

An internal temporary table can be held in memory and processed by the `MEMORY` storage engine, or stored on disk by the `InnoDB` or `MyISAM` storage engine.

If an internal temporary table is created as an in-memory table but becomes too large, MySQL automatically converts it to an on-disk table. The maximum size for in-memory temporary tables is defined by the [`tmp_table_size`](server-administration.html#sysvar_tmp_table_size) or [`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size) value, whichever is smaller. This differs from `MEMORY` tables explicitly created with [`CREATE TABLE`](sql-statements.html#create-table). For such tables, only the [`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size) variable determines how large a table can grow, and there is no conversion to on-disk format.

The [`internal_tmp_disk_storage_engine`](server-administration.html#sysvar_internal_tmp_disk_storage_engine) variable defines the storage engine the server uses to manage on-disk internal temporary tables. Permitted values are `INNODB` (the default) and `MYISAM`.

Note

When using [`internal_tmp_disk_storage_engine=INNODB`](server-administration.html#sysvar_internal_tmp_disk_storage_engine), queries that generate on-disk internal temporary tables that exceed [`InnoDB` row or column limits](innodb-storage-engine.html#innodb-limits) return Row size too large or Too many columns errors. The workaround is to set [`internal_tmp_disk_storage_engine`](server-administration.html#sysvar_internal_tmp_disk_storage_engine) to `MYISAM`.

When an internal temporary table is created in memory or on disk, the server increments the [`Created_tmp_tables`](server-administration.html#statvar_Created_tmp_tables) value. When an internal temporary table is created on disk, the server increments the [`Created_tmp_disk_tables`](server-administration.html#statvar_Created_tmp_disk_tables) value. If too many internal temporary tables are created on disk, consider increasing the [`tmp_table_size`](server-administration.html#sysvar_tmp_table_size) and [`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size) settings.

#### Internal Temporary Table Storage Format

In-memory temporary tables are managed by the `MEMORY` storage engine, which uses fixed-length row format. `VARCHAR` and `VARBINARY` column values are padded to the maximum column length, in effect storing them as `CHAR` and `BINARY` columns.

On-disk temporary tables are managed by the `InnoDB` or `MyISAM` storage engine (depending on the [`internal_tmp_disk_storage_engine`](server-administration.html#sysvar_internal_tmp_disk_storage_engine) setting). Both engines store temporary tables using dynamic-width row format. Columns take only as much storage as needed, which reduces disk I/O, space requirements, and processing time compared to on-disk tables that use fixed-length rows.

For statements that initially create an internal temporary table in memory, then convert it to an on-disk table, better performance might be achieved by skipping the conversion step and creating the table on disk to begin with. The [`big_tables`](server-administration.html#sysvar_big_tables) variable can be used to force disk storage of internal temporary tables.

### 8.4.5 Limits on Number of Databases and Tables



MySQL has no limit on the number of databases. The underlying file system may have a limit on the number of directories.

MySQL has no limit on the number of tables. The underlying file system may have a limit on the number of files that represent tables. Individual storage engines may impose engine-specific constraints. `InnoDB` permits up to 4 billion tables.

### 8.4.6 Limits on Table Size



The effective maximum table size for MySQL databases is usually determined by operating system constraints on file sizes, not by MySQL internal limits. For up-to-date information operating system file size limits, refer to the documentation specific to your operating system.

Windows users, please note that FAT and VFAT (FAT32) are *not* considered suitable for production use with MySQL. Use NTFS instead.

If you encounter a full-table error, there are several reasons why it might have occurred:

- The disk might be full.

- You are using `InnoDB` tables and have run out of room in an `InnoDB` tablespace file. The maximum tablespace size is also the maximum size for a table. For tablespace size limits, see [Section 14.23, “InnoDB Limits”](innodb-storage-engine.html#innodb-limits).

  Generally, partitioning of tables into multiple tablespace files is recommended for tables larger than 1TB in size.

- You have hit an operating system file size limit. For example, you are using `MyISAM` tables on an operating system that supports files only up to 2GB in size and you have hit this limit for the data file or index file.

- You are using a `MyISAM` table and the space required for the table exceeds what is permitted by the internal pointer size. `MyISAM` permits data and index files to grow up to 256TB by default, but this limit can be changed up to the maximum permissible size of 65,536TB (2567 − 1 bytes).

  If you need a `MyISAM` table that is larger than the default limit and your operating system supports large files, the [`CREATE TABLE`](sql-statements.html#create-table) statement supports `AVG_ROW_LENGTH` and `MAX_ROWS` options. See [Section 13.1.18, “CREATE TABLE Statement”](sql-statements.html#create-table). The server uses these options to determine how large a table to permit.

  If the pointer size is too small for an existing table, you can change the options with [`ALTER TABLE`](sql-statements.html#alter-table) to increase a table's maximum permissible size. See [Section 13.1.8, “ALTER TABLE Statement”](sql-statements.html#alter-table).

  ```
  ALTER TABLE tbl_name MAX_ROWS=1000000000 AVG_ROW_LENGTH=nnn;
  ```

  You have to specify `AVG_ROW_LENGTH` only for tables with [`BLOB`](data-types.html#blob) or [`TEXT`](data-types.html#blob) columns; in this case, MySQL cannot optimize the space required based only on the number of rows.

  To change the default size limit for `MyISAM` tables, set the [`myisam_data_pointer_size`](server-administration.html#sysvar_myisam_data_pointer_size), which sets the number of bytes used for internal row pointers. The value is used to set the pointer size for new tables if you do not specify the `MAX_ROWS` option. The value of [`myisam_data_pointer_size`](server-administration.html#sysvar_myisam_data_pointer_size) can be from 2 to 7. A value of 4 permits tables up to 4GB; a value of 6 permits tables up to 256TB.

  You can check the maximum data and index sizes by using this statement:

  ```
  SHOW TABLE STATUS FROM db_name LIKE 'tbl_name';
  ```

  You also can use [**myisamchk -dv /path/to/table-index-file**](programs.html#myisamchk). See [Section 13.7.5, “SHOW Statements”](sql-statements.html#show), or [Section 4.6.3, “**myisamchk** — MyISAM Table-Maintenance Utility”](programs.html#myisamchk).

  Other ways to work around file-size limits for `MyISAM` tables are as follows:

  - If your large table is read only, you can use [**myisampack**](programs.html#myisampack) to compress it. [**myisampack**](programs.html#myisampack) usually compresses a table by at least 50%, so you can have, in effect, much bigger tables. [**myisampack**](programs.html#myisampack) also can merge multiple tables into a single table. See [Section 4.6.5, “**myisampack** — Generate Compressed, Read-Only MyISAM Tables”](programs.html#myisampack).
  - MySQL includes a `MERGE` library that enables you to handle a collection of `MyISAM` tables that have identical structure as a single `MERGE` table. See [Section 15.7, “The MERGE Storage Engine”](storage-engines.html#merge-storage-engine).

- You are using the `MEMORY` (`HEAP`) storage engine; in this case you need to increase the value of the [`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size) system variable. See [Section 5.1.7, “Server System Variables”](server-administration.html#server-system-variables).

### 8.4.7 Limits on Table Column Count and Row Size

This section describes limits on the number of columns in tables and the size of individual rows.

- [Column Count Limits](optimization.html#column-count-limits)
- [Row Size Limits](optimization.html#row-size-limits)

#### Column Count Limits



MySQL has hard limit of 4096 columns per table, but the effective maximum may be less for a given table. The exact column limit depends on several factors:

- The maximum row size for a table constrains the number (and possibly size) of columns because the total length of all columns cannot exceed this size. See [Row Size Limits](optimization.html#row-size-limits).
- The storage requirements of individual columns constrain the number of columns that fit within a given maximum row size. Storage requirements for some data types depend on factors such as storage engine, storage format, and character set. See [Section 11.7, “Data Type Storage Requirements”](data-types.html#storage-requirements).
- Storage engines may impose additional restrictions that limit table column count. For example, [`InnoDB`](innodb-storage-engine.html) has a limit of 1017 columns per table. See [Section 14.23, “InnoDB Limits”](innodb-storage-engine.html#innodb-limits). For information about other storage engines, see [Chapter 15, *Alternative Storage Engines*](storage-engines.html).
- Each table has an `.frm` file that contains the table definition. The definition affects the content of this file in ways that may affect the number of columns permitted in the table. See [Limits Imposed by .frm File Structure](sql-statements.html#limits-frm-file).

#### Row Size Limits



The maximum row size for a given table is determined by several factors:

- The internal representation of a MySQL table has a maximum row size limit of 65,535 bytes, even if the storage engine is capable of supporting larger rows. [`BLOB`](data-types.html#blob) and [`TEXT`](data-types.html#blob) columns only contribute 9 to 12 bytes toward the row size limit because their contents are stored separately from the rest of the row.

- The maximum row size for an `InnoDB` table, which applies to data stored locally within a database page, is slightly less than half a page for 4KB, 8KB, 16KB, and 32KB [`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size) settings. For example, the maximum row size is slightly less than 8KB for the default 16KB `InnoDB` page size. For 64KB pages, the maximum row size is slightly less than 16KB. See [Section 14.23, “InnoDB Limits”](innodb-storage-engine.html#innodb-limits).

  If a row containing [variable-length columns](glossary.html#glos_variable_length_type) exceeds the `InnoDB` maximum row size, `InnoDB` selects variable-length columns for external off-page storage until the row fits within the `InnoDB` row size limit. The amount of data stored locally for variable-length columns that are stored off-page differs by row format. For more information, see [Section 14.11, “InnoDB Row Formats”](innodb-storage-engine.html#innodb-row-format).

- Different storage formats use different amounts of page header and trailer data, which affects the amount of storage available for rows.

  - For information about `InnoDB` row formats, see [Section 14.11, “InnoDB Row Formats”](innodb-storage-engine.html#innodb-row-format).
  - For information about `MyISAM` storage formats, see [Section 15.2.3, “MyISAM Table Storage Formats”](storage-engines.html#myisam-table-formats).

##### Row Size Limit Examples

- The MySQL maximum row size limit of 65,535 bytes is demonstrated in the following `InnoDB` and `MyISAM` examples. The limit is enforced regardless of storage engine, even though the storage engine may be capable of supporting larger rows.

  ```
  mysql> CREATE TABLE t (a VARCHAR(10000), b VARCHAR(10000),
         c VARCHAR(10000), d VARCHAR(10000), e VARCHAR(10000),
         f VARCHAR(10000), g VARCHAR(6000)) ENGINE=InnoDB CHARACTER SET latin1;
  ERROR 1118 (42000): Row size too large. The maximum row size for the used
  table type, not counting BLOBs, is 65535. This includes storage overhead,
  check the manual. You have to change some columns to TEXT or BLOBs
  ```

  ```
  mysql> CREATE TABLE t (a VARCHAR(10000), b VARCHAR(10000),
         c VARCHAR(10000), d VARCHAR(10000), e VARCHAR(10000),
         f VARCHAR(10000), g VARCHAR(6000)) ENGINE=MyISAM CHARACTER SET latin1;
  ERROR 1118 (42000): Row size too large. The maximum row size for the used
  table type, not counting BLOBs, is 65535. This includes storage overhead,
  check the manual. You have to change some columns to TEXT or BLOBs
  ```

  In the following `MyISAM` example, changing a column to [`TEXT`](data-types.html#blob) avoids the 65,535-byte row size limit and permits the operation to succeed because [`BLOB`](data-types.html#blob) and [`TEXT`](data-types.html#blob) columns only contribute 9 to 12 bytes toward the row size.

  ```
  mysql> CREATE TABLE t (a VARCHAR(10000), b VARCHAR(10000),
         c VARCHAR(10000), d VARCHAR(10000), e VARCHAR(10000),
         f VARCHAR(10000), g TEXT(6000)) ENGINE=MyISAM CHARACTER SET latin1;
  Query OK, 0 rows affected (0.02 sec)
  ```

  The operation succeeds for an `InnoDB` table because changing a column to [`TEXT`](data-types.html#blob) avoids the MySQL 65,535-byte row size limit, and `InnoDB` off-page storage of variable-length columns avoids the `InnoDB` row size limit.

  ```
  mysql> CREATE TABLE t (a VARCHAR(10000), b VARCHAR(10000),
         c VARCHAR(10000), d VARCHAR(10000), e VARCHAR(10000),
         f VARCHAR(10000), g TEXT(6000)) ENGINE=InnoDB CHARACTER SET latin1;
  Query OK, 0 rows affected (0.02 sec)
  ```

- Storage for variable-length columns includes length bytes, which are counted toward the row size. For example, a [`VARCHAR(255) CHARACTER SET utf8mb3`](data-types.html#char) column takes two bytes to store the length of the value, so each value can take up to 767 bytes.

  The statement to create table `t1` succeeds because the columns require 32,765 + 2 bytes and 32,766 + 2 bytes, which falls within the maximum row size of 65,535 bytes:

  ```
  mysql> CREATE TABLE t1
         (c1 VARCHAR(32765) NOT NULL, c2 VARCHAR(32766) NOT NULL)
         ENGINE = InnoDB CHARACTER SET latin1;
  Query OK, 0 rows affected (0.02 sec)
  ```

  The statement to create table `t2` fails because, although the column length is within the maximum length of 65,535 bytes, two additional bytes are required to record the length, which causes the row size to exceed 65,535 bytes:

  ```
  mysql> CREATE TABLE t2
         (c1 VARCHAR(65535) NOT NULL)
         ENGINE = InnoDB CHARACTER SET latin1;
  ERROR 1118 (42000): Row size too large. The maximum row size for the used
  table type, not counting BLOBs, is 65535. This includes storage overhead,
  check the manual. You have to change some columns to TEXT or BLOBs
  ```

  Reducing the column length to 65,533 or less permits the statement to succeed.

  ```
  mysql> CREATE TABLE t2
         (c1 VARCHAR(65533) NOT NULL)
         ENGINE = InnoDB CHARACTER SET latin1;
  Query OK, 0 rows affected (0.01 sec)
  ```

- For [`MyISAM`](storage-engines.html#myisam-storage-engine) tables, `NULL` columns require additional space in the row to record whether their values are `NULL`. Each `NULL` column takes one bit extra, rounded up to the nearest byte.

  The statement to create table `t3` fails because [`MyISAM`](storage-engines.html#myisam-storage-engine) requires space for `NULL` columns in addition to the space required for variable-length column length bytes, causing the row size to exceed 65,535 bytes:

  ```
  mysql> CREATE TABLE t3
         (c1 VARCHAR(32765) NULL, c2 VARCHAR(32766) NULL)
         ENGINE = MyISAM CHARACTER SET latin1;
  ERROR 1118 (42000): Row size too large. The maximum row size for the used
  table type, not counting BLOBs, is 65535. This includes storage overhead,
  check the manual. You have to change some columns to TEXT or BLOBs
  ```

  For information about [`InnoDB`](innodb-storage-engine.html) `NULL` column storage, see [Section 14.11, “InnoDB Row Formats”](innodb-storage-engine.html#innodb-row-format).

- `InnoDB` restricts row size (for data stored locally within the database page) to slightly less than half a database page for 4KB, 8KB, 16KB, and 32KB [`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size) settings, and to slightly less than 16KB for 64KB pages.

  The statement to create table `t4` fails because the defined columns exceed the row size limit for a 16KB `InnoDB` page.

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
  ERROR 1118 (42000): Row size too large (> 8126). Changing some columns to TEXT or BLOB or using
  ROW_FORMAT=DYNAMIC or ROW_FORMAT=COMPRESSED may help. In current row format, BLOB prefix of 768
  bytes is stored inline.
  ```

## 8.5 Optimizing for InnoDB Tables

- [8.5.1 Optimizing Storage Layout for InnoDB Tables](optimization.html#optimizing-innodb-storage-layout)
- [8.5.2 Optimizing InnoDB Transaction Management](optimization.html#optimizing-innodb-transaction-management)
- [8.5.3 Optimizing InnoDB Read-Only Transactions](optimization.html#innodb-performance-ro-txn)
- [8.5.4 Optimizing InnoDB Redo Logging](optimization.html#optimizing-innodb-logging)
- [8.5.5 Bulk Data Loading for InnoDB Tables](optimization.html#optimizing-innodb-bulk-data-loading)
- [8.5.6 Optimizing InnoDB Queries](optimization.html#optimizing-innodb-queries)
- [8.5.7 Optimizing InnoDB DDL Operations](optimization.html#optimizing-innodb-ddl-operations)
- [8.5.8 Optimizing InnoDB Disk I/O](optimization.html#optimizing-innodb-diskio)
- [8.5.9 Optimizing InnoDB Configuration Variables](optimization.html#optimizing-innodb-configuration-variables)
- [8.5.10 Optimizing InnoDB for Systems with Many Tables](optimization.html#optimizing-innodb-many-tables)



[`InnoDB`](innodb-storage-engine.html) is the storage engine that MySQL customers typically use in production databases where reliability and concurrency are important. `InnoDB` is the default storage engine in MySQL. This section explains how to optimize database operations for `InnoDB` tables.

### 8.5.1 Optimizing Storage Layout for InnoDB Tables

- Once your data reaches a stable size, or a growing table has increased by tens or some hundreds of megabytes, consider using the `OPTIMIZE TABLE` statement to reorganize the table and compact any wasted space. The reorganized tables require less disk I/O to perform full table scans. This is a straightforward technique that can improve performance when other techniques such as improving index usage or tuning application code are not practical.

  `OPTIMIZE TABLE` copies the data part of the table and rebuilds the indexes. The benefits come from improved packing of data within indexes, and reduced fragmentation within the tablespaces and on disk. The benefits vary depending on the data in each table. You may find that there are significant gains for some and not for others, or that the gains decrease over time until you next optimize the table. This operation can be slow if the table is large or if the indexes being rebuilt do not fit into the buffer pool. The first run after adding a lot of data to a table is often much slower than later runs.

- In `InnoDB`, having a long `PRIMARY KEY` (either a single column with a lengthy value, or several columns that form a long composite value) wastes a lot of disk space. The primary key value for a row is duplicated in all the secondary index records that point to the same row. (See [Section 14.6.2.1, “Clustered and Secondary Indexes”](innodb-storage-engine.html#innodb-index-types).) Create an `AUTO_INCREMENT` column as the primary key if your primary key is long, or index a prefix of a long `VARCHAR` column instead of the entire column.

- Use the [`VARCHAR`](data-types.html#char) data type instead of [`CHAR`](data-types.html#char) to store variable-length strings or for columns with many `NULL` values. A [`CHAR(*`N`*)`](data-types.html#char) column always takes *`N`* characters to store data, even if the string is shorter or its value is `NULL`. Smaller tables fit better in the buffer pool and reduce disk I/O.

  When using `COMPACT` row format (the default `InnoDB` format) and variable-length character sets, such as `utf8` or `sjis`, [`CHAR(*`N`*)`](data-types.html#char) columns occupy a variable amount of space, but still at least *`N`* bytes.

- For tables that are big, or contain lots of repetitive text or numeric data, consider using `COMPRESSED` row format. Less disk I/O is required to bring data into the buffer pool, or to perform full table scans. Before making a permanent decision, measure the amount of compression you can achieve by using `COMPRESSED` versus `COMPACT` row format.

### 8.5.2 Optimizing InnoDB Transaction Management

To optimize `InnoDB` transaction processing, find the ideal balance between the performance overhead of transactional features and the workload of your server. For example, an application might encounter performance issues if it commits thousands of times per second, and different performance issues if it commits only every 2-3 hours.

- The default MySQL setting `AUTOCOMMIT=1` can impose performance limitations on a busy database server. Where practical, wrap several related data change operations into a single transaction, by issuing `SET AUTOCOMMIT=0` or a `START TRANSACTION` statement, followed by a `COMMIT` statement after making all the changes.

  `InnoDB` must flush the log to disk at each transaction commit if that transaction made modifications to the database. When each change is followed by a commit (as with the default autocommit setting), the I/O throughput of the storage device puts a cap on the number of potential operations per second.

- Alternatively, for transactions that consist only of a single [`SELECT`](sql-statements.html#select) statement, turning on `AUTOCOMMIT` helps `InnoDB` to recognize read-only transactions and optimize them. See [Section 8.5.3, “Optimizing InnoDB Read-Only Transactions”](optimization.html#innodb-performance-ro-txn) for requirements.

- Avoid performing rollbacks after inserting, updating, or deleting huge numbers of rows. If a big transaction is slowing down server performance, rolling it back can make the problem worse, potentially taking several times as long to perform as the original data change operations. Killing the database process does not help, because the rollback starts again on server startup.

  To minimize the chance of this issue occurring:

  - Increase the size of the [buffer pool](glossary.html#glos_buffer_pool) so that all the data change changes can be cached rather than immediately written to disk.
  - Set [`innodb_change_buffering=all`](innodb-storage-engine.html#sysvar_innodb_change_buffering) so that update and delete operations are buffered in addition to inserts.
  - Consider issuing `COMMIT` statements periodically during the big data change operation, possibly breaking a single delete or update into multiple statements that operate on smaller numbers of rows.

  To get rid of a runaway rollback once it occurs, increase the buffer pool so that the rollback becomes CPU-bound and runs fast, or kill the server and restart with [`innodb_force_recovery=3`](innodb-storage-engine.html#sysvar_innodb_force_recovery), as explained in [Section 14.19.2, “InnoDB Recovery”](innodb-storage-engine.html#innodb-recovery).

  This issue is expected to be infrequent with the default setting [`innodb_change_buffering=all`](innodb-storage-engine.html#sysvar_innodb_change_buffering), which allows update and delete operations to be cached in memory, making them faster to perform in the first place, and also faster to roll back if needed. Make sure to use this parameter setting on servers that process long-running transactions with many inserts, updates, or deletes.

- If you can afford the loss of some of the latest committed transactions if a crash occurs, you can set the [`innodb_flush_log_at_trx_commit`](innodb-storage-engine.html#sysvar_innodb_flush_log_at_trx_commit) parameter to 0. `InnoDB` tries to flush the log once per second anyway, although the flush is not guaranteed. Also, set the value of [`innodb_support_xa`](innodb-storage-engine.html#sysvar_innodb_support_xa) to 0, which will reduce the number of disk flushes due to synchronizing on disk data and the binary log.

  Note

  [`innodb_support_xa`](innodb-storage-engine.html#sysvar_innodb_support_xa) is deprecated and will be removed in a future release. As of MySQL 5.7.10, `InnoDB` support for two-phase commit in XA transactions is always enabled and disabling [`innodb_support_xa`](innodb-storage-engine.html#sysvar_innodb_support_xa) is no longer permitted.

- When rows are modified or deleted, the rows and associated [undo logs](glossary.html#glos_undo_log) are not physically removed immediately, or even immediately after the transaction commits. The old data is preserved until transactions that started earlier or concurrently are finished, so that those transactions can access the previous state of modified or deleted rows. Thus, a long-running transaction can prevent `InnoDB` from purging data that was changed by a different transaction.

- When rows are modified or deleted within a long-running transaction, other transactions using the [`READ COMMITTED`](innodb-storage-engine.html#isolevel_read-committed) and [`REPEATABLE READ`](innodb-storage-engine.html#isolevel_repeatable-read) isolation levels have to do more work to reconstruct the older data if they read those same rows.

- When a long-running transaction modifies a table, queries against that table from other transactions do not make use of the [covering index](glossary.html#glos_covering_index) technique. Queries that normally could retrieve all the result columns from a secondary index, instead look up the appropriate values from the table data.

  If secondary index pages are found to have a `PAGE_MAX_TRX_ID` that is too new, or if records in the secondary index are delete-marked, `InnoDB` may need to look up records using a clustered index.

### 8.5.3 Optimizing InnoDB Read-Only Transactions

`InnoDB` can avoid the overhead associated with setting up the [transaction ID](glossary.html#glos_transaction_id) (`TRX_ID` field) for transactions that are known to be read-only. A transaction ID is only needed for a [transaction](glossary.html#glos_transaction) that might perform write operations or [locking reads](glossary.html#glos_locking_read) such as `SELECT ... FOR UPDATE`. Eliminating unnecessary transaction IDs reduces the size of internal data structures that are consulted each time a query or data change statement constructs a [read view](glossary.html#glos_read_view).

`InnoDB` detects read-only transactions when:

- The transaction is started with the [`START TRANSACTION READ ONLY`](sql-statements.html#commit) statement. In this case, attempting to make changes to the database (for `InnoDB`, `MyISAM`, or other types of tables) causes an error, and the transaction continues in read-only state:

  ```
  ERROR 1792 (25006): Cannot execute statement in a READ ONLY transaction.
  ```

  You can still make changes to session-specific temporary tables in a read-only transaction, or issue locking queries for them, because those changes and locks are not visible to any other transaction.

- The [`autocommit`](server-administration.html#sysvar_autocommit) setting is turned on, so that the transaction is guaranteed to be a single statement, and the single statement making up the transaction is a “non-locking” [`SELECT`](sql-statements.html#select) statement. That is, a `SELECT` that does not use a `FOR UPDATE` or `LOCK IN SHARED MODE` clause.

- The transaction is started without the `READ ONLY` option, but no updates or statements that explicitly lock rows have been executed yet. Until updates or explicit locks are required, a transaction stays in read-only mode.

Thus, for a read-intensive application such as a report generator, you can tune a sequence of `InnoDB` queries by grouping them inside [`START TRANSACTION READ ONLY`](sql-statements.html#commit) and [`COMMIT`](sql-statements.html#commit), or by turning on the [`autocommit`](server-administration.html#sysvar_autocommit) setting before running the `SELECT` statements, or simply by avoiding any data change statements interspersed with the queries.

For information about [`START TRANSACTION`](sql-statements.html#commit) and [`autocommit`](server-administration.html#sysvar_autocommit), see [Section 13.3.1, “START TRANSACTION, COMMIT, and ROLLBACK Statements”](sql-statements.html#commit).

Note

Transactions that qualify as auto-commit, non-locking, and read-only (AC-NL-RO) are kept out of certain internal `InnoDB` data structures and are therefore not listed in [`SHOW ENGINE INNODB STATUS`](sql-statements.html#show-engine) output.

### 8.5.4 Optimizing InnoDB Redo Logging

Consider the following guidelines for optimizing redo logging:

- Make your redo log files big, even as big as the [buffer pool](glossary.html#glos_buffer_pool). When `InnoDB` has written the redo log files full, it must write the modified contents of the buffer pool to disk in a [checkpoint](glossary.html#glos_checkpoint). Small redo log files cause many unnecessary disk writes. Although historically big redo log files caused lengthy recovery times, recovery is now much faster and you can confidently use large redo log files.

  The size and number of redo log files are configured using the [`innodb_log_file_size`](innodb-storage-engine.html#sysvar_innodb_log_file_size) and [`innodb_log_files_in_group`](innodb-storage-engine.html#sysvar_innodb_log_files_in_group) configuration options. For information about modifying an existing redo log file configuration, see [Changing the Number or Size of InnoDB Redo Log Files](innodb-storage-engine.html#innodb-redo-log-file-reconfigure).

- Consider increasing the size of the [log buffer](glossary.html#glos_log_buffer). A large log buffer enables large [transactions](glossary.html#glos_transaction) to run without a need to write the log to disk before the transactions [commit](glossary.html#glos_commit). Thus, if you have transactions that update, insert, or delete many rows, making the log buffer larger saves disk I/O. Log buffer size is configured using the [`innodb_log_buffer_size`](innodb-storage-engine.html#sysvar_innodb_log_buffer_size) configuration option.

- Configure the [`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size) configuration option to avoid “read-on-write”. This option defines the write-ahead block size for the redo log. Set [`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size) to match the operating system or file system cache block size. Read-on-write occurs when redo log blocks are not entirely cached to the operating system or file system due to a mismatch between write-ahead block size for the redo log and operating system or file system cache block size.

  Valid values for [`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size) are multiples of the `InnoDB` log file block size (2n). The minimum value is the `InnoDB` log file block size (512). Write-ahead does not occur when the minimum value is specified. The maximum value is equal to the [`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size) value. If you specify a value for [`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size) that is larger than the [`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size) value, the [`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size) setting is truncated to the [`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size) value.

  Setting the [`innodb_log_write_ahead_size`](innodb-storage-engine.html#sysvar_innodb_log_write_ahead_size) value too low in relation to the operating system or file system cache block size results in read-on-write. Setting the value too high may have a slight impact on `fsync` performance for log file writes due to several blocks being written at once.

### 8.5.5 Bulk Data Loading for InnoDB Tables



These performance tips supplement the general guidelines for fast inserts in [Section 8.2.4.1, “Optimizing INSERT Statements”](optimization.html#insert-optimization).

- When importing data into `InnoDB`, turn off autocommit mode, because it performs a log flush to disk for every insert. To disable autocommit during your import operation, surround it with [`SET autocommit`](sql-statements.html#commit) and [`COMMIT`](sql-statements.html#commit) statements:

  ```
  SET autocommit=0;
  ... SQL import statements ...
  COMMIT;
  ```

  The [**mysqldump**](programs.html#mysqldump) option [`--opt`](programs.html#option_mysqldump_opt) creates dump files that are fast to import into an `InnoDB` table, even without wrapping them with the [`SET autocommit`](sql-statements.html#commit) and [`COMMIT`](sql-statements.html#commit) statements.

- If you have `UNIQUE` constraints on secondary keys, you can speed up table imports by temporarily turning off the uniqueness checks during the import session:

  ```
  SET unique_checks=0;
  ... SQL import statements ...
  SET unique_checks=1;
  ```

  For big tables, this saves a lot of disk I/O because `InnoDB` can use its change buffer to write secondary index records in a batch. Be certain that the data contains no duplicate keys.

- If you have `FOREIGN KEY` constraints in your tables, you can speed up table imports by turning off the foreign key checks for the duration of the import session:

  ```
  SET foreign_key_checks=0;
  ... SQL import statements ...
  SET foreign_key_checks=1;
  ```

  For big tables, this can save a lot of disk I/O.

- Use the multiple-row [`INSERT`](sql-statements.html#insert) syntax to reduce communication overhead between the client and the server if you need to insert many rows:

  ```
  INSERT INTO yourtable VALUES (1,2), (5,5), ...;
  ```

  This tip is valid for inserts into any table, not just `InnoDB` tables.

- When doing bulk inserts into tables with auto-increment columns, set [`innodb_autoinc_lock_mode`](innodb-storage-engine.html#sysvar_innodb_autoinc_lock_mode) to 2 instead of the default value 1. See [Section 14.6.1.6, “AUTO_INCREMENT Handling in InnoDB”](innodb-storage-engine.html#innodb-auto-increment-handling) for details.

- When performing bulk inserts, it is faster to insert rows in `PRIMARY KEY` order. `InnoDB` tables use a [clustered index](glossary.html#glos_clustered_index), which makes it relatively fast to use data in the order of the `PRIMARY KEY`. Performing bulk inserts in `PRIMARY KEY` order is particularly important for tables that do not fit entirely within the buffer pool.

- For optimal performance when loading data into an `InnoDB` `FULLTEXT` index, follow this set of steps:

  1. Define a column `FTS_DOC_ID` at table creation time, of type `BIGINT UNSIGNED NOT NULL`, with a unique index named `FTS_DOC_ID_INDEX`. For example:

     ```
     CREATE TABLE t1 (
     FTS_DOC_ID BIGINT unsigned NOT NULL AUTO_INCREMENT,
     title varchar(255) NOT NULL DEFAULT '',
     text mediumtext NOT NULL,
     PRIMARY KEY (`FTS_DOC_ID`)
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
     CREATE UNIQUE INDEX FTS_DOC_ID_INDEX on t1(FTS_DOC_ID);
     ```

  2. Load the data into the table.

  3. Create the `FULLTEXT` index after the data is loaded.

  Note

  When adding `FTS_DOC_ID` column at table creation time, ensure that the `FTS_DOC_ID` column is updated when the `FULLTEXT` indexed column is updated, as the `FTS_DOC_ID` must increase monotonically with each [`INSERT`](sql-statements.html#insert) or [`UPDATE`](sql-statements.html#update). If you choose not to add the `FTS_DOC_ID` at table creation time and have `InnoDB` manage DOC IDs for you, `InnoDB` will add the `FTS_DOC_ID` as a hidden column with the next [`CREATE FULLTEXT INDEX`](sql-statements.html#create-index) call. This approach, however, requires a table rebuild which will impact performance.

### 8.5.6 Optimizing InnoDB Queries

To tune queries for `InnoDB` tables, create an appropriate set of indexes on each table. See [Section 8.3.1, “How MySQL Uses Indexes”](optimization.html#mysql-indexes) for details. Follow these guidelines for `InnoDB` indexes:

- Because each `InnoDB` table has a [primary key](glossary.html#glos_primary_key) (whether you request one or not), specify a set of primary key columns for each table, columns that are used in the most important and time-critical queries.
- Do not specify too many or too long columns in the primary key, because these column values are duplicated in each secondary index. When an index contains unnecessary data, the I/O to read this data and memory to cache it reduce the performance and scalability of the server.
- Do not create a separate [secondary index](glossary.html#glos_secondary_index) for each column, because each query can only make use of one index. Indexes on rarely tested columns or columns with only a few different values might not be helpful for any queries. If you have many queries for the same table, testing different combinations of columns, try to create a small number of [concatenated indexes](glossary.html#glos_concatenated_index) rather than a large number of single-column indexes. If an index contains all the columns needed for the result set (known as a [covering index](glossary.html#glos_covering_index)), the query might be able to avoid reading the table data at all.
- If an indexed column cannot contain any `NULL` values, declare it as `NOT NULL` when you create the table. The optimizer can better determine which index is most effective to use for a query, when it knows whether each column contains `NULL` values.
- You can optimize single-query transactions for `InnoDB` tables, using the technique in [Section 8.5.3, “Optimizing InnoDB Read-Only Transactions”](optimization.html#innodb-performance-ro-txn).

### 8.5.7 Optimizing InnoDB DDL Operations

- Many DDL operations on tables and indexes (`CREATE`, `ALTER`, and `DROP` statements) can be performed online. See [Section 14.13, “InnoDB and Online DDL”](innodb-storage-engine.html#innodb-online-ddl) for details.
- Online DDL support for adding secondary indexes means that you can generally speed up the process of creating and loading a table and associated indexes by creating the table without secondary indexes, then adding secondary indexes after the data is loaded.
- Use [`TRUNCATE TABLE`](sql-statements.html#truncate-table) to empty a table, not `DELETE FROM *`tbl_name`*`. Foreign key constraints can make a `TRUNCATE` statement work like a regular `DELETE` statement, in which case a sequence of commands like [`DROP TABLE`](sql-statements.html#drop-table) and [`CREATE TABLE`](sql-statements.html#create-table) might be fastest.
- Because the primary key is integral to the storage layout of each `InnoDB` table, and changing the definition of the primary key involves reorganizing the whole table, always set up the primary key as part of the [`CREATE TABLE`](sql-statements.html#create-table) statement, and plan ahead so that you do not need to `ALTER` or `DROP` the primary key afterward.

### 8.5.8 Optimizing InnoDB Disk I/O



If you follow best practices for database design and tuning techniques for SQL operations, but your database is still slow due to heavy disk I/O activity, consider these disk I/O optimizations. If the Unix `top` tool or the Windows Task Manager shows that the CPU usage percentage with your workload is less than 70%, your workload is probably disk-bound.

- Increase buffer pool size

  When table data is cached in the `InnoDB` buffer pool, it can be accessed repeatedly by queries without requiring any disk I/O. Specify the size of the buffer pool with the [`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size) option. This memory area is important enough that it is typically recommended that [`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size) is configured to 50 to 75 percent of system memory. For more information see, [Section 8.12.4.1, “How MySQL Uses Memory”](optimization.html#memory-use).

- Adjust the flush method

  In some versions of GNU/Linux and Unix, flushing files to disk with the Unix `fsync()` call (which `InnoDB` uses by default) and similar methods is surprisingly slow. If database write performance is an issue, conduct benchmarks with the [`innodb_flush_method`](innodb-storage-engine.html#sysvar_innodb_flush_method) parameter set to `O_DSYNC`.

- Use a noop or deadline I/O scheduler with native AIO on Linux

  `InnoDB` uses the asynchronous I/O subsystem (native AIO) on Linux to perform read-ahead and write requests for data file pages. This behavior is controlled by the [`innodb_use_native_aio`](innodb-storage-engine.html#sysvar_innodb_use_native_aio) configuration option, which is enabled by default. With native AIO, the type of I/O scheduler has greater influence on I/O performance. Generally, noop and deadline I/O schedulers are recommended. Conduct benchmarks to determine which I/O scheduler provides the best results for your workload and environment. For more information, see [Section 14.8.7, “Using Asynchronous I/O on Linux”](innodb-storage-engine.html#innodb-linux-native-aio).

- Use direct I/O on Solaris 10 for x86_64 architecture

  When using the `InnoDB` storage engine on Solaris 10 for x86_64 architecture (AMD Opteron), use direct I/O for `InnoDB`-related files to avoid degradation of `InnoDB` performance. To use direct I/O for an entire UFS file system used for storing `InnoDB`-related files, mount it with the `forcedirectio` option; see `mount_ufs(1M)`. (The default on Solaris 10/x86_64 is *not* to use this option.) To apply direct I/O only to `InnoDB` file operations rather than the whole file system, set [`innodb_flush_method = O_DIRECT`](innodb-storage-engine.html#sysvar_innodb_flush_method). With this setting, `InnoDB` calls `directio()` instead of `fcntl()` for I/O to data files (not for I/O to log files).

- Use raw storage for data and log files with Solaris 2.6 or later

  When using the `InnoDB` storage engine with a large [`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size) value on any release of Solaris 2.6 and up and any platform (sparc/x86/x64/amd64), conduct benchmarks with `InnoDB` data files and log files on raw devices or on a separate direct I/O UFS file system, using the `forcedirectio` mount option as described previously. (It is necessary to use the mount option rather than setting [`innodb_flush_method`](innodb-storage-engine.html#sysvar_innodb_flush_method) if you want direct I/O for the log files.) Users of the Veritas file system VxFS should use the `convosync=direct` mount option.

  Do not place other MySQL data files, such as those for `MyISAM` tables, on a direct I/O file system. Executables or libraries *must not* be placed on a direct I/O file system.

- Use additional storage devices

  Additional storage devices could be used to set up a RAID configuration. For related information, see [Section 8.12.2, “Optimizing Disk I/O”](optimization.html#disk-issues).

  Alternatively, `InnoDB` tablespace data files and log files can be placed on different physical disks. For more information, refer to the following sections:

  - [Section 14.8.1, “InnoDB Startup Configuration”](innodb-storage-engine.html#innodb-init-startup-configuration)
  - [Section 14.6.1.2, “Creating Tables Externally”](innodb-storage-engine.html#innodb-create-table-external)
  - [Creating a General Tablespace](innodb-storage-engine.html#general-tablespaces-creating)
  - [Section 14.6.1.4, “Moving or Copying InnoDB Tables”](innodb-storage-engine.html#innodb-migration)

- Consider non-rotational storage

  Non-rotational storage generally provides better performance for random I/O operations; and rotational storage for sequential I/O operations. When distributing data and log files across rotational and non-rotational storage devices, consider the type of I/O operations that are predominantly performed on each file.

  Random I/O-oriented files typically include [file-per-table](glossary.html#glos_file_per_table) and [general tablespace](glossary.html#glos_general_tablespace) data files, [undo tablespace](glossary.html#glos_undo_tablespace) files, and [temporary tablespace](glossary.html#glos_temporary_tablespace) files. Sequential I/O-oriented files include `InnoDB` [system tablespace](glossary.html#glos_system_tablespace) files (due to [doublewrite buffering](glossary.html#glos_doublewrite_buffer) and [change buffering](glossary.html#glos_change_buffer)) and log files such as [binary log](glossary.html#glos_binary_log) files and [redo log](glossary.html#glos_redo_log) files.

  Review settings for the following configuration options when using non-rotational storage:

  - [`innodb_checksum_algorithm`](innodb-storage-engine.html#sysvar_innodb_checksum_algorithm)

    The `crc32` option uses a faster checksum algorithm and is recommended for fast storage systems.

  - [`innodb_flush_neighbors`](innodb-storage-engine.html#sysvar_innodb_flush_neighbors)

    Optimizes I/O for rotational storage devices. Disable it for non-rotational storage or a mix of rotational and non-rotational storage.

  - [`innodb_io_capacity`](innodb-storage-engine.html#sysvar_innodb_io_capacity)

    The default setting of 200 is generally sufficient for a lower-end non-rotational storage device. For higher-end, bus-attached devices, consider a higher setting such as 1000.

  - [`innodb_io_capacity_max`](innodb-storage-engine.html#sysvar_innodb_io_capacity_max)

    The default value of 2000 is intended for workloads that use non-rotational storage. For a high-end, bus-attached non-rotational storage device, consider a higher setting such as 2500.

  - [`innodb_log_compressed_pages`](innodb-storage-engine.html#sysvar_innodb_log_compressed_pages)

    If redo logs are on non-rotational storage, consider disabling this option to reduce logging. See [Disable logging of compressed pages](optimization.html#innodb-disable-log-compressed-pages).

  - [`innodb_log_file_size`](innodb-storage-engine.html#sysvar_innodb_log_file_size)

    If redo logs are on non-rotational storage, configure this option to maximize caching and write combining.

  - [`innodb_page_size`](innodb-storage-engine.html#sysvar_innodb_page_size)

    Consider using a page size that matches the internal sector size of the disk. Early-generation SSD devices often have a 4KB sector size. Some newer devices have a 16KB sector size. The default `InnoDB` page size is 16KB. Keeping the page size close to the storage device block size minimizes the amount of unchanged data that is rewritten to disk.

  - [`binlog_row_image`](replication.html#sysvar_binlog_row_image)

    If binary logs are on non-rotational storage and all tables have primary keys, consider setting this option to `minimal` to reduce logging.

  Ensure that TRIM support is enabled for your operating system. It is typically enabled by default.

- Increase I/O capacity to avoid backlogs

  If throughput drops periodically because of `InnoDB` [checkpoint](glossary.html#glos_checkpoint) operations, consider increasing the value of the [`innodb_io_capacity`](innodb-storage-engine.html#sysvar_innodb_io_capacity) configuration option. Higher values cause more frequent [flushing](glossary.html#glos_flush), avoiding the backlog of work that can cause dips in throughput.

- Lower I/O capacity if flushing does not fall behind

  If the system is not falling behind with `InnoDB` [flushing](glossary.html#glos_flush) operations, consider lowering the value of the [`innodb_io_capacity`](innodb-storage-engine.html#sysvar_innodb_io_capacity) configuration option. Typically, you keep this option value as low as practical, but not so low that it causes periodic drops in throughput as mentioned in the preceding bullet. In a typical scenario where you could lower the option value, you might see a combination like this in the output from [`SHOW ENGINE INNODB STATUS`](sql-statements.html#show-engine):

  - History list length low, below a few thousand.
  - Insert buffer merges close to rows inserted.
  - Modified pages in buffer pool consistently well below [`innodb_max_dirty_pages_pct`](innodb-storage-engine.html#sysvar_innodb_max_dirty_pages_pct) of the buffer pool. (Measure at a time when the server is not doing bulk inserts; it is normal during bulk inserts for the modified pages percentage to rise significantly.)
  - `Log sequence number - Last checkpoint` is at less than 7/8 or ideally less than 6/8 of the total size of the `InnoDB` [log files](glossary.html#glos_log_file).

- Store system tablespace files on Fusion-io devices

  You can take advantage of a doublewrite buffer-related I/O optimization by storing system tablespace files (“ibdata files”) on Fusion-io devices that support atomic writes. In this case, doublewrite buffering ([`innodb_doublewrite`](innodb-storage-engine.html#sysvar_innodb_doublewrite)) is automatically disabled and Fusion-io atomic writes are used for all data files. This feature is only supported on Fusion-io hardware and is only enabled for Fusion-io NVMFS on Linux. To take full advantage of this feature, an [`innodb_flush_method`](innodb-storage-engine.html#sysvar_innodb_flush_method) setting of `O_DIRECT` is recommended.

  Note

  Because the doublewrite buffer setting is global, doublewrite buffering is also disabled for data files residing on non-Fusion-io hardware.

- Disable logging of compressed pages

  When using the `InnoDB` table [compression](glossary.html#glos_compression) feature, images of re-compressed [pages](glossary.html#glos_page) are written to the [redo log](glossary.html#glos_redo_log) when changes are made to compressed data. This behavior is controlled by [`innodb_log_compressed_pages`](innodb-storage-engine.html#sysvar_innodb_log_compressed_pages), which is enabled by default to prevent corruption that can occur if a different version of the `zlib` compression algorithm is used during recovery. If you are certain that the `zlib` version will not change, disable [`innodb_log_compressed_pages`](innodb-storage-engine.html#sysvar_innodb_log_compressed_pages) to reduce redo log generation for workloads that modify compressed data.

### 8.5.9 Optimizing InnoDB Configuration Variables

Different settings work best for servers with light, predictable loads, versus servers that are running near full capacity all the time, or that experience spikes of high activity.

Because the `InnoDB` storage engine performs many of its optimizations automatically, many performance-tuning tasks involve monitoring to ensure that the database is performing well, and changing configuration options when performance drops. See [Section 14.17, “InnoDB Integration with MySQL Performance Schema”](innodb-storage-engine.html#innodb-performance-schema) for information about detailed `InnoDB` performance monitoring.

The main configuration steps you can perform include:

- Enabling `InnoDB` to use high-performance memory allocators on systems that include them. See [Section 14.8.4, “Configuring the Memory Allocator for InnoDB”](innodb-storage-engine.html#innodb-performance-use_sys_malloc).
- Controlling the types of data change operations for which `InnoDB` buffers the changed data, to avoid frequent small disk writes. See [Configuring Change Buffering](innodb-storage-engine.html#innodb-change-buffer-configuration). Because the default is to buffer all types of data change operations, only change this setting if you need to reduce the amount of buffering.
- Turning the adaptive hash indexing feature on and off using the [`innodb_adaptive_hash_index`](innodb-storage-engine.html#sysvar_innodb_adaptive_hash_index) option. See [Section 14.5.3, “Adaptive Hash Index”](innodb-storage-engine.html#innodb-adaptive-hash) for more information. You might change this setting during periods of unusual activity, then restore it to its original setting.
- Setting a limit on the number of concurrent threads that `InnoDB` processes, if context switching is a bottleneck. See [Section 14.8.5, “Configuring Thread Concurrency for InnoDB”](innodb-storage-engine.html#innodb-performance-thread_concurrency).
- Controlling the amount of prefetching that `InnoDB` does with its read-ahead operations. When the system has unused I/O capacity, more read-ahead can improve the performance of queries. Too much read-ahead can cause periodic drops in performance on a heavily loaded system. See [Section 14.8.3.4, “Configuring InnoDB Buffer Pool Prefetching (Read-Ahead)”](innodb-storage-engine.html#innodb-performance-read_ahead).
- Increasing the number of background threads for read or write operations, if you have a high-end I/O subsystem that is not fully utilized by the default values. See [Section 14.8.6, “Configuring the Number of Background InnoDB I/O Threads”](innodb-storage-engine.html#innodb-performance-multiple_io_threads).
- Controlling how much I/O `InnoDB` performs in the background. See [Section 14.8.8, “Configuring InnoDB I/O Capacity”](innodb-storage-engine.html#innodb-configuring-io-capacity). You might scale back this setting if you observe periodic drops in performance.
- Controlling the algorithm that determines when `InnoDB` performs certain types of background writes. See [Section 14.8.3.5, “Configuring Buffer Pool Flushing”](innodb-storage-engine.html#innodb-buffer-pool-flushing). The algorithm works for some types of workloads but not others, so might turn off this setting if you observe periodic drops in performance.
- Taking advantage of multicore processors and their cache memory configuration, to minimize delays in context switching. See [Section 14.8.9, “Configuring Spin Lock Polling”](innodb-storage-engine.html#innodb-performance-spin_lock_polling).
- Preventing one-time operations such as table scans from interfering with the frequently accessed data stored in the `InnoDB` buffer cache. See [Section 14.8.3.3, “Making the Buffer Pool Scan Resistant”](innodb-storage-engine.html#innodb-performance-midpoint_insertion).
- Adjusting log files to a size that makes sense for reliability and crash recovery. `InnoDB` log files have often been kept small to avoid long startup times after a crash. Optimizations introduced in MySQL 5.5 speed up certain steps of the crash [recovery](glossary.html#glos_crash_recovery) process. In particular, scanning the [redo log](glossary.html#glos_redo_log) and applying the redo log are faster due to improved algorithms for memory management. If you have kept your log files artificially small to avoid long startup times, you can now consider increasing log file size to reduce the I/O that occurs due recycling of redo log records.
- Configuring the size and number of instances for the `InnoDB` buffer pool, especially important for systems with multi-gigabyte buffer pools. See [Section 14.8.3.2, “Configuring Multiple Buffer Pool Instances”](innodb-storage-engine.html#innodb-multiple-buffer-pools).
- Increasing the maximum number of concurrent transactions, which dramatically improves scalability for the busiest databases. See [Section 14.6.7, “Undo Logs”](innodb-storage-engine.html#innodb-undo-logs).
- Moving purge operations (a type of garbage collection) into a background thread. See [Section 14.8.10, “Purge Configuration”](innodb-storage-engine.html#innodb-purge-configuration). To effectively measure the results of this setting, tune the other I/O-related and thread-related configuration settings first.
- Reducing the amount of switching that `InnoDB` does between concurrent threads, so that SQL operations on a busy server do not queue up and form a “traffic jam”. Set a value for the [`innodb_thread_concurrency`](innodb-storage-engine.html#sysvar_innodb_thread_concurrency) option, up to approximately 32 for a high-powered modern system. Increase the value for the [`innodb_concurrency_tickets`](innodb-storage-engine.html#sysvar_innodb_concurrency_tickets) option, typically to 5000 or so. This combination of options sets a cap on the number of threads that `InnoDB` processes at any one time, and allows each thread to do substantial work before being swapped out, so that the number of waiting threads stays low and operations can complete without excessive context switching.

### 8.5.10 Optimizing InnoDB for Systems with Many Tables

- If you have configured [non-persistent optimizer statistics](innodb-storage-engine.html#innodb-statistics-estimation) (a non-default configuration), `InnoDB` computes index [cardinality](glossary.html#glos_cardinality) values for a table the first time that table is accessed after startup, instead of storing such values in the table. This step can take significant time on systems that partition the data into many tables. Since this overhead only applies to the initial table open operation, to “warm up” a table for later use, access it immediately after startup by issuing a statement such as `SELECT 1 FROM *`tbl_name`* LIMIT 1`.

  Optimizer statistics are persisted to disk by default, enabled by the [`innodb_stats_persistent`](innodb-storage-engine.html#sysvar_innodb_stats_persistent) configuration option. For information about persistent optimizer statistics, see [Section 14.8.11.1, “Configuring Persistent Optimizer Statistics Parameters”](innodb-storage-engine.html#innodb-persistent-stats).

## 8.6 Optimizing for MyISAM Tables

- [8.6.1 Optimizing MyISAM Queries](optimization.html#optimizing-queries-myisam)
- [8.6.2 Bulk Data Loading for MyISAM Tables](optimization.html#optimizing-myisam-bulk-data-loading)
- [8.6.3 Optimizing REPAIR TABLE Statements](optimization.html#repair-table-optimization)



The [`MyISAM`](storage-engines.html#myisam-storage-engine) storage engine performs best with read-mostly data or with low-concurrency operations, because table locks limit the ability to perform simultaneous updates. In MySQL, [`InnoDB`](innodb-storage-engine.html) is the default storage engine rather than `MyISAM`.

### 8.6.1 Optimizing MyISAM Queries

Some general tips for speeding up queries on `MyISAM` tables:

- To help MySQL better optimize queries, use [`ANALYZE TABLE`](sql-statements.html#analyze-table) or run [**myisamchk --analyze**](programs.html#myisamchk) on a table after it has been loaded with data. This updates a value for each index part that indicates the average number of rows that have the same value. (For unique indexes, this is always 1.) MySQL uses this to decide which index to choose when you join two tables based on a nonconstant expression. You can check the result from the table analysis by using `SHOW INDEX FROM *`tbl_name`*` and examining the `Cardinality` value. [**myisamchk --description --verbose**](programs.html#myisamchk) shows index distribution information.

- To sort an index and data according to an index, use [**myisamchk --sort-index --sort-records=1**](programs.html#myisamchk) (assuming that you want to sort on index 1). This is a good way to make queries faster if you have a unique index from which you want to read all rows in order according to the index. The first time you sort a large table this way, it may take a long time.

- Try to avoid complex [`SELECT`](sql-statements.html#select) queries on `MyISAM` tables that are updated frequently, to avoid problems with table locking that occur due to contention between readers and writers.

- `MyISAM` supports concurrent inserts: If a table has no free blocks in the middle of the data file, you can [`INSERT`](sql-statements.html#insert) new rows into it at the same time that other threads are reading from the table. If it is important to be able to do this, consider using the table in ways that avoid deleting rows. Another possibility is to run [`OPTIMIZE TABLE`](sql-statements.html#optimize-table) to defragment the table after you have deleted a lot of rows from it. This behavior is altered by setting the [`concurrent_insert`](server-administration.html#sysvar_concurrent_insert) variable. You can force new rows to be appended (and therefore permit concurrent inserts), even in tables that have deleted rows. See [Section 8.11.3, “Concurrent Inserts”](optimization.html#concurrent-inserts).

- For `MyISAM` tables that change frequently, try to avoid all variable-length columns ([`VARCHAR`](data-types.html#char), [`BLOB`](data-types.html#blob), and [`TEXT`](data-types.html#blob)). The table uses dynamic row format if it includes even a single variable-length column. See [Chapter 15, *Alternative Storage Engines*](storage-engines.html).

- It is normally not useful to split a table into different tables just because the rows become large. In accessing a row, the biggest performance hit is the disk seek needed to find the first byte of the row. After finding the data, most modern disks can read the entire row fast enough for most applications. The only cases where splitting up a table makes an appreciable difference is if it is a `MyISAM` table using dynamic row format that you can change to a fixed row size, or if you very often need to scan the table but do not need most of the columns. See [Chapter 15, *Alternative Storage Engines*](storage-engines.html).

- Use `ALTER TABLE ... ORDER BY *`expr1`*, *`expr2`*, ...` if you usually retrieve rows in `*`expr1`*, *`expr2`*, ...` order. By using this option after extensive changes to the table, you may be able to get higher performance.

- If you often need to calculate results such as counts based on information from a lot of rows, it may be preferable to introduce a new table and update the counter in real time. An update of the following form is very fast:

  ```
  UPDATE tbl_name SET count_col=count_col+1 WHERE key_col=constant;
  ```

  This is very important when you use MySQL storage engines such as `MyISAM` that has only table-level locking (multiple readers with single writers). This also gives better performance with most database systems, because the row locking manager in this case has less to do.

- Use [`OPTIMIZE TABLE`](sql-statements.html#optimize-table) periodically to avoid fragmentation with dynamic-format `MyISAM` tables. See [Section 15.2.3, “MyISAM Table Storage Formats”](storage-engines.html#myisam-table-formats).

- Declaring a `MyISAM` table with the `DELAY_KEY_WRITE=1` table option makes index updates faster because they are not flushed to disk until the table is closed. The downside is that if something kills the server while such a table is open, you must ensure that the table is okay by running the server with the [`myisam_recover_options`](server-administration.html#sysvar_myisam_recover_options) system variable set, or by running [**myisamchk**](programs.html#myisamchk) before restarting the server. (However, even in this case, you should not lose anything by using `DELAY_KEY_WRITE`, because the key information can always be generated from the data rows.)

- Strings are automatically prefix- and end-space compressed in `MyISAM` indexes. See [Section 13.1.14, “CREATE INDEX Statement”](sql-statements.html#create-index).

- You can increase performance by caching queries or answers in your application and then executing many inserts or updates together. Locking the table during this operation ensures that the index cache is only flushed once after all updates. You can also take advantage of MySQL's query cache to achieve similar results; see [Section 8.10.3, “The MySQL Query Cache”](optimization.html#query-cache).

### 8.6.2 Bulk Data Loading for MyISAM Tables



These performance tips supplement the general guidelines for fast inserts in [Section 8.2.4.1, “Optimizing INSERT Statements”](optimization.html#insert-optimization).

- For a `MyISAM` table, you can use concurrent inserts to add rows at the same time that [`SELECT`](sql-statements.html#select) statements are running, if there are no deleted rows in middle of the data file. See [Section 8.11.3, “Concurrent Inserts”](optimization.html#concurrent-inserts).

- With some extra work, it is possible to make [`LOAD DATA`](sql-statements.html#load-data) run even faster for a `MyISAM` table when the table has many indexes. Use the following procedure:

  1. Execute a [`FLUSH TABLES`](sql-statements.html#flush-tables) statement or a [**mysqladmin flush-tables**](programs.html#mysqladmin) command.
  2. Use [**myisamchk --keys-used=0 -rq \*`/path/to/db/tbl_name`\***](programs.html#myisamchk) to remove all use of indexes for the table.
  3. Insert data into the table with [`LOAD DATA`](sql-statements.html#load-data). This does not update any indexes and therefore is very fast.
  4. If you intend only to read from the table in the future, use [**myisampack**](programs.html#myisampack) to compress it. See [Section 15.2.3.3, “Compressed Table Characteristics”](storage-engines.html#compressed-format).
  5. Re-create the indexes with [**myisamchk -rq \*`/path/to/db/tbl_name`\***](programs.html#myisamchk). This creates the index tree in memory before writing it to disk, which is much faster than updating the index during [`LOAD DATA`](sql-statements.html#load-data) because it avoids lots of disk seeks. The resulting index tree is also perfectly balanced.
  6. Execute a [`FLUSH TABLES`](sql-statements.html#flush-tables) statement or a [**mysqladmin flush-tables**](programs.html#mysqladmin) command.

  [`LOAD DATA`](sql-statements.html#load-data) performs the preceding optimization automatically if the `MyISAM` table into which you insert data is empty. The main difference between automatic optimization and using the procedure explicitly is that you can let [**myisamchk**](programs.html#myisamchk) allocate much more temporary memory for the index creation than you might want the server to allocate for index re-creation when it executes the [`LOAD DATA`](sql-statements.html#load-data) statement.

  You can also disable or enable the nonunique indexes for a `MyISAM` table by using the following statements rather than [**myisamchk**](programs.html#myisamchk). If you use these statements, you can skip the [`FLUSH TABLES`](sql-statements.html#flush-tables) operations:

  ```
  ALTER TABLE tbl_name DISABLE KEYS;
  ALTER TABLE tbl_name ENABLE KEYS;
  ```

- To speed up [`INSERT`](sql-statements.html#insert) operations that are performed with multiple statements for nontransactional tables, lock your tables:

  ```
  LOCK TABLES a WRITE;
  INSERT INTO a VALUES (1,23),(2,34),(4,33);
  INSERT INTO a VALUES (8,26),(6,29);
  ...
  UNLOCK TABLES;
  ```

  This benefits performance because the index buffer is flushed to disk only once, after all [`INSERT`](sql-statements.html#insert) statements have completed. Normally, there would be as many index buffer flushes as there are [`INSERT`](sql-statements.html#insert) statements. Explicit locking statements are not needed if you can insert all rows with a single [`INSERT`](sql-statements.html#insert).

  Locking also lowers the total time for multiple-connection tests, although the maximum wait time for individual connections might go up because they wait for locks. Suppose that five clients attempt to perform inserts simultaneously as follows:

  - Connection 1 does 1000 inserts
  - Connections 2, 3, and 4 do 1 insert
  - Connection 5 does 1000 inserts

  If you do not use locking, connections 2, 3, and 4 finish before 1 and 5. If you use locking, connections 2, 3, and 4 probably do not finish before 1 or 5, but the total time should be about 40% faster.

  [`INSERT`](sql-statements.html#insert), [`UPDATE`](sql-statements.html#update), and [`DELETE`](sql-statements.html#delete) operations are very fast in MySQL, but you can obtain better overall performance by adding locks around everything that does more than about five successive inserts or updates. If you do very many successive inserts, you could do a [`LOCK TABLES`](sql-statements.html#lock-tables) followed by an [`UNLOCK TABLES`](sql-statements.html#lock-tables) once in a while (each 1,000 rows or so) to permit other threads to access table. This would still result in a nice performance gain.

  [`INSERT`](sql-statements.html#insert) is still much slower for loading data than [`LOAD DATA`](sql-statements.html#load-data), even when using the strategies just outlined.

- To increase performance for `MyISAM` tables, for both [`LOAD DATA`](sql-statements.html#load-data) and [`INSERT`](sql-statements.html#insert), enlarge the key cache by increasing the [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) system variable. See [Section 5.1.1, “Configuring the Server”](server-administration.html#server-configuration).

### 8.6.3 Optimizing REPAIR TABLE Statements



[`REPAIR TABLE`](sql-statements.html#repair-table) for `MyISAM` tables is similar to using [**myisamchk**](programs.html#myisamchk) for repair operations, and some of the same performance optimizations apply:

- [**myisamchk**](programs.html#myisamchk) has variables that control memory allocation. You may be able to its improve performance by setting these variables, as described in [Section 4.6.3.6, “myisamchk Memory Usage”](programs.html#myisamchk-memory).
- For [`REPAIR TABLE`](sql-statements.html#repair-table), the same principle applies, but because the repair is done by the server, you set server system variables instead of [**myisamchk**](programs.html#myisamchk) variables. Also, in addition to setting memory-allocation variables, increasing the [`myisam_max_sort_file_size`](server-administration.html#sysvar_myisam_max_sort_file_size) system variable increases the likelihood that the repair will use the faster filesort method and avoid the slower repair by key cache method. Set the variable to the maximum file size for your system, after checking to be sure that there is enough free space to hold a copy of the table files. The free space must be available in the file system containing the original table files.

Suppose that a [**myisamchk**](programs.html#myisamchk) table-repair operation is done using the following options to set its memory-allocation variables:

```
--key_buffer_size=128M --myisam_sort_buffer_size=256M
--read_buffer_size=64M --write_buffer_size=64M
```

Some of those [**myisamchk**](programs.html#myisamchk) variables correspond to server system variables:

| [**myisamchk**](programs.html#myisamchk) Variable | System Variable                                              |
| :------------------------------------------------ | :----------------------------------------------------------- |
| `key_buffer_size`                                 | [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) |
| `myisam_sort_buffer_size`                         | [`myisam_sort_buffer_size`](server-administration.html#sysvar_myisam_sort_buffer_size) |
| `read_buffer_size`                                | [`read_buffer_size`](server-administration.html#sysvar_read_buffer_size) |
| `write_buffer_size`                               | none                                                         |

Each of the server system variables can be set at runtime, and some of them ([`myisam_sort_buffer_size`](server-administration.html#sysvar_myisam_sort_buffer_size), [`read_buffer_size`](server-administration.html#sysvar_read_buffer_size)) have a session value in addition to a global value. Setting a session value limits the effect of the change to your current session and does not affect other users. Changing a global-only variable ([`key_buffer_size`](server-administration.html#sysvar_key_buffer_size), [`myisam_max_sort_file_size`](server-administration.html#sysvar_myisam_max_sort_file_size)) affects other users as well. For [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size), you must take into account that the buffer is shared with those users. For example, if you set the [**myisamchk**](programs.html#myisamchk) `key_buffer_size` variable to 128MB, you could set the corresponding [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) system variable larger than that (if it is not already set larger), to permit key buffer use by activity in other sessions. However, changing the global key buffer size invalidates the buffer, causing increased disk I/O and slowdown for other sessions. An alternative that avoids this problem is to use a separate key cache, assign to it the indexes from the table to be repaired, and deallocate it when the repair is complete. See [Section 8.10.2.2, “Multiple Key Caches”](optimization.html#multiple-key-caches).

Based on the preceding remarks, a [`REPAIR TABLE`](sql-statements.html#repair-table) operation can be done as follows to use settings similar to the [**myisamchk**](programs.html#myisamchk) command. Here a separate 128MB key buffer is allocated and the file system is assumed to permit a file size of at least 100GB.

```
SET SESSION myisam_sort_buffer_size = 256*1024*1024;
SET SESSION read_buffer_size = 64*1024*1024;
SET GLOBAL myisam_max_sort_file_size = 100*1024*1024*1024;
SET GLOBAL repair_cache.key_buffer_size = 128*1024*1024;
CACHE INDEX tbl_name IN repair_cache;
LOAD INDEX INTO CACHE tbl_name;
REPAIR TABLE tbl_name ;
SET GLOBAL repair_cache.key_buffer_size = 0;
```

If you intend to change a global variable but want to do so only for the duration of a [`REPAIR TABLE`](sql-statements.html#repair-table) operation to minimally affect other users, save its value in a user variable and restore it afterward. For example:

```
SET @old_myisam_sort_buffer_size = @@GLOBAL.myisam_max_sort_file_size;
SET GLOBAL myisam_max_sort_file_size = 100*1024*1024*1024;
REPAIR TABLE tbl_name ;
SET GLOBAL myisam_max_sort_file_size = @old_myisam_max_sort_file_size;
```

The system variables that affect [`REPAIR TABLE`](sql-statements.html#repair-table) can be set globally at server startup if you want the values to be in effect by default. For example, add these lines to the server `my.cnf` file:

```
[mysqld]
myisam_sort_buffer_size=256M
key_buffer_size=1G
myisam_max_sort_file_size=100G
```

These settings do not include [`read_buffer_size`](server-administration.html#sysvar_read_buffer_size). Setting [`read_buffer_size`](server-administration.html#sysvar_read_buffer_size) globally to a large value does so for all sessions and can cause performance to suffer due to excessive memory allocation for a server with many simultaneous sessions.

## 8.7 Optimizing for MEMORY Tables



Consider using `MEMORY` tables for noncritical data that is accessed often, and is read-only or rarely updated. Benchmark your application against equivalent `InnoDB` or `MyISAM` tables under a realistic workload, to confirm that any additional performance is worth the risk of losing data, or the overhead of copying data from a disk-based table at application start.

For best performance with `MEMORY` tables, examine the kinds of queries against each table, and specify the type to use for each associated index, either a B-tree index or a hash index. On the [`CREATE INDEX`](sql-statements.html#create-index) statement, use the clause `USING BTREE` or `USING HASH`. B-tree indexes are fast for queries that do greater-than or less-than comparisons through operators such as `>` or `BETWEEN`. Hash indexes are only fast for queries that look up single values through the `=` operator, or a restricted set of values through the `IN` operator. For why `USING BTREE` is often a better choice than the default `USING HASH`, see [Section 8.2.1.20, “Avoiding Full Table Scans”](optimization.html#table-scan-avoidance). For implementation details of the different types of `MEMORY` indexes, see [Section 8.3.8, “Comparison of B-Tree and Hash Indexes”](optimization.html#index-btree-hash).

## 8.8 Understanding the Query Execution Plan

- [8.8.1 Optimizing Queries with EXPLAIN](optimization.html#using-explain)
- [8.8.2 EXPLAIN Output Format](optimization.html#explain-output)
- [8.8.3 Extended EXPLAIN Output Format](optimization.html#explain-extended)
- [8.8.4 Obtaining Execution Plan Information for a Named Connection](optimization.html#explain-for-connection)
- [8.8.5 Estimating Query Performance](optimization.html#estimating-performance)

Depending on the details of your tables, columns, indexes, and the conditions in your `WHERE` clause, the MySQL optimizer considers many techniques to efficiently perform the lookups involved in an SQL query. A query on a huge table can be performed without reading all the rows; a join involving several tables can be performed without comparing every combination of rows. The set of operations that the optimizer chooses to perform the most efficient query is called the “query execution plan”, also known as the [`EXPLAIN`](sql-statements.html#explain) plan. Your goals are to recognize the aspects of the [`EXPLAIN`](sql-statements.html#explain) plan that indicate a query is optimized well, and to learn the SQL syntax and indexing techniques to improve the plan if you see some inefficient operations.

### 8.8.1 Optimizing Queries with EXPLAIN



The [`EXPLAIN`](sql-statements.html#explain) statement provides information about how MySQL executes statements:

- [`EXPLAIN`](sql-statements.html#explain) works with [`SELECT`](sql-statements.html#select), [`DELETE`](sql-statements.html#delete), [`INSERT`](sql-statements.html#insert), [`REPLACE`](sql-statements.html#replace), and [`UPDATE`](sql-statements.html#update) statements.
- When [`EXPLAIN`](sql-statements.html#explain) is used with an explainable statement, MySQL displays information from the optimizer about the statement execution plan. That is, MySQL explains how it would process the statement, including information about how tables are joined and in which order. For information about using [`EXPLAIN`](sql-statements.html#explain) to obtain execution plan information, see [Section 8.8.2, “EXPLAIN Output Format”](optimization.html#explain-output).
- When [`EXPLAIN`](sql-statements.html#explain) is used with `FOR CONNECTION *`connection_id`*` rather than an explainable statement, it displays the execution plan for the statement executing in the named connection. See [Section 8.8.4, “Obtaining Execution Plan Information for a Named Connection”](optimization.html#explain-for-connection).
- For [`SELECT`](sql-statements.html#select) statements, [`EXPLAIN`](sql-statements.html#explain) produces additional execution plan information that can be displayed using [`SHOW WARNINGS`](sql-statements.html#show-warnings). See [Section 8.8.3, “Extended EXPLAIN Output Format”](optimization.html#explain-extended).
- [`EXPLAIN`](sql-statements.html#explain) is useful for examining queries involving partitioned tables. See [Section 22.3.5, “Obtaining Information About Partitions”](partitioning.html#partitioning-info).
- The `FORMAT` option can be used to select the output format. `TRADITIONAL` presents the output in tabular format. This is the default if no `FORMAT` option is present. `JSON` format displays the information in JSON format.

With the help of [`EXPLAIN`](sql-statements.html#explain), you can see where you should add indexes to tables so that the statement executes faster by using indexes to find rows. You can also use [`EXPLAIN`](sql-statements.html#explain) to check whether the optimizer joins the tables in an optimal order. To give a hint to the optimizer to use a join order corresponding to the order in which the tables are named in a [`SELECT`](sql-statements.html#select) statement, begin the statement with `SELECT STRAIGHT_JOIN` rather than just [`SELECT`](sql-statements.html#select). (See [Section 13.2.9, “SELECT Statement”](sql-statements.html#select).) However, `STRAIGHT_JOIN` may prevent indexes from being used because it disables semijoin transformations. See [Section 8.2.2.1, “Optimizing Subqueries, Derived Tables, and View References with Semijoin Transformations”](optimization.html#semijoins).

The optimizer trace may sometimes provide information complementary to that of [`EXPLAIN`](sql-statements.html#explain). However, the optimizer trace format and content are subject to change between versions. For details, see [MySQL Internals: Tracing the Optimizer](https://dev.mysql.com/doc/internals/en/optimizer-tracing.html).

If you have a problem with indexes not being used when you believe that they should be, run [`ANALYZE TABLE`](sql-statements.html#analyze-table) to update table statistics, such as cardinality of keys, that can affect the choices the optimizer makes. See [Section 13.7.2.1, “ANALYZE TABLE Statement”](sql-statements.html#analyze-table).

Note

[`EXPLAIN`](sql-statements.html#explain) can also be used to obtain information about the columns in a table. [`EXPLAIN *`tbl_name`*`](sql-statements.html#explain) is synonymous with `DESCRIBE *`tbl_name`*` and `SHOW COLUMNS FROM *`tbl_name`*`. For more information, see [Section 13.8.1, “DESCRIBE Statement”](sql-statements.html#describe), and [Section 13.7.5.5, “SHOW COLUMNS Statement”](sql-statements.html#show-columns).

### 8.8.2 EXPLAIN Output Format

The [`EXPLAIN`](sql-statements.html#explain) statement provides information about how MySQL executes statements. [`EXPLAIN`](sql-statements.html#explain) works with [`SELECT`](sql-statements.html#select), [`DELETE`](sql-statements.html#delete), [`INSERT`](sql-statements.html#insert), [`REPLACE`](sql-statements.html#replace), and [`UPDATE`](sql-statements.html#update) statements.

[`EXPLAIN`](sql-statements.html#explain) returns a row of information for each table used in the [`SELECT`](sql-statements.html#select) statement. It lists the tables in the output in the order that MySQL would read them while processing the statement. MySQL resolves all joins using a nested-loop join method. This means that MySQL reads a row from the first table, and then finds a matching row in the second table, the third table, and so on. When all tables are processed, MySQL outputs the selected columns and backtracks through the table list until a table is found for which there are more matching rows. The next row is read from this table and the process continues with the next table.

[`EXPLAIN`](sql-statements.html#explain) output includes partition information. Also, for [`SELECT`](sql-statements.html#select) statements, [`EXPLAIN`](sql-statements.html#explain) generates extended information that can be displayed with [`SHOW WARNINGS`](sql-statements.html#show-warnings) following the [`EXPLAIN`](sql-statements.html#explain) (see [Section 8.8.3, “Extended EXPLAIN Output Format”](optimization.html#explain-extended)).

Note

In older MySQL releases, partition and extended information was produced using [`EXPLAIN PARTITIONS`](sql-statements.html#explain) and [`EXPLAIN EXTENDED`](sql-statements.html#explain). Those syntaxes are still recognized for backward compatibility but partition and extended output is now enabled by default, so the `PARTITIONS` and `EXTENDED` keywords are superfluous and deprecated. Their use results in a warning, and they will be removed from [`EXPLAIN`](sql-statements.html#explain) syntax in a future MySQL release.

You cannot use the deprecated `PARTITIONS` and `EXTENDED` keywords together in the same [`EXPLAIN`](sql-statements.html#explain) statement. In addition, neither of these keywords can be used together with the `FORMAT` option.

Note

MySQL Workbench has a Visual Explain capability that provides a visual representation of [`EXPLAIN`](sql-statements.html#explain) output. See [Tutorial: Using Explain to Improve Query Performance](https://dev.mysql.com/doc/workbench/en/wb-tutorial-visual-explain-dbt3.html).

- [EXPLAIN Output Columns](optimization.html#explain-output-columns)
- [EXPLAIN Join Types](optimization.html#explain-join-types)
- [EXPLAIN Extra Information](optimization.html#explain-extra-information)
- [EXPLAIN Output Interpretation](optimization.html#explain-output-interpretation)

#### EXPLAIN Output Columns

This section describes the output columns produced by [`EXPLAIN`](sql-statements.html#explain). Later sections provide additional information about the [`type`](optimization.html#explain-join-types) and [`Extra`](optimization.html#explain-extra-information) columns.

Each output row from [`EXPLAIN`](sql-statements.html#explain) provides information about one table. Each row contains the values summarized in [Table 8.1, “EXPLAIN Output Columns”](optimization.html#explain-output-column-table), and described in more detail following the table. Column names are shown in the table's first column; the second column provides the equivalent property name shown in the output when `FORMAT=JSON` is used.



**Table 8.1 EXPLAIN Output Columns**

| Column                                                     | JSON Name       | Meaning                                        |
| :--------------------------------------------------------- | :-------------- | :--------------------------------------------- |
| [`id`](optimization.html#explain_id)                       | `select_id`     | The `SELECT` identifier                        |
| [`select_type`](optimization.html#explain_select_type)     | None            | The `SELECT` type                              |
| [`table`](optimization.html#explain_table)                 | `table_name`    | The table for the output row                   |
| [`partitions`](optimization.html#explain_partitions)       | `partitions`    | The matching partitions                        |
| [`type`](optimization.html#explain_type)                   | `access_type`   | The join type                                  |
| [`possible_keys`](optimization.html#explain_possible_keys) | `possible_keys` | The possible indexes to choose                 |
| [`key`](optimization.html#explain_key)                     | `key`           | The index actually chosen                      |
| [`key_len`](optimization.html#explain_key_len)             | `key_length`    | The length of the chosen key                   |
| [`ref`](optimization.html#explain_ref)                     | `ref`           | The columns compared to the index              |
| [`rows`](optimization.html#explain_rows)                   | `rows`          | Estimate of rows to be examined                |
| [`filtered`](optimization.html#explain_filtered)           | `filtered`      | Percentage of rows filtered by table condition |
| [`Extra`](optimization.html#explain_extra)                 | None            | Additional information                         |



Note

JSON properties which are `NULL` are not displayed in JSON-formatted `EXPLAIN` output.

- `id` (JSON name: `select_id`)

  The [`SELECT`](sql-statements.html#select) identifier. This is the sequential number of the [`SELECT`](sql-statements.html#select) within the query. The value can be `NULL` if the row refers to the union result of other rows. In this case, the `table` column shows a value like `<union*`M`*,*`N`*>` to indicate that the row refers to the union of the rows with `id` values of *`M`* and *`N`*.

- `select_type` (JSON name: none)

  The type of [`SELECT`](sql-statements.html#select), which can be any of those shown in the following table. A JSON-formatted `EXPLAIN` exposes the `SELECT` type as a property of a `query_block`, unless it is `SIMPLE` or `PRIMARY`. The JSON names (where applicable) are also shown in the table.

  | `select_type` Value                                      | JSON Name                    | Meaning                                                      |
  | :------------------------------------------------------- | :--------------------------- | :----------------------------------------------------------- |
  | `SIMPLE`                                                 | None                         | Simple [`SELECT`](sql-statements.html#select) (not using [`UNION`](sql-statements.html#union) or subqueries) |
  | `PRIMARY`                                                | None                         | Outermost [`SELECT`](sql-statements.html#select)             |
  | [`UNION`](sql-statements.html#union)                     | None                         | Second or later [`SELECT`](sql-statements.html#select) statement in a [`UNION`](sql-statements.html#union) |
  | `DEPENDENT UNION`                                        | `dependent` (`true`)         | Second or later [`SELECT`](sql-statements.html#select) statement in a [`UNION`](sql-statements.html#union), dependent on outer query |
  | `UNION RESULT`                                           | `union_result`               | Result of a [`UNION`](sql-statements.html#union).            |
  | [`SUBQUERY`](optimization.html#optimizer-hints-subquery) | None                         | First [`SELECT`](sql-statements.html#select) in subquery     |
  | `DEPENDENT SUBQUERY`                                     | `dependent` (`true`)         | First [`SELECT`](sql-statements.html#select) in subquery, dependent on outer query |
  | `DERIVED`                                                | None                         | Derived table                                                |
  | `MATERIALIZED`                                           | `materialized_from_subquery` | Materialized subquery                                        |
  | `UNCACHEABLE SUBQUERY`                                   | `cacheable` (`false`)        | A subquery for which the result cannot be cached and must be re-evaluated for each row of the outer query |
  | `UNCACHEABLE UNION`                                      | `cacheable` (`false`)        | The second or later select in a [`UNION`](sql-statements.html#union) that belongs to an uncacheable subquery (see `UNCACHEABLE SUBQUERY`) |

  `DEPENDENT` typically signifies the use of a correlated subquery. See [Section 13.2.10.7, “Correlated Subqueries”](sql-statements.html#correlated-subqueries).

  `DEPENDENT SUBQUERY` evaluation differs from `UNCACHEABLE SUBQUERY` evaluation. For `DEPENDENT SUBQUERY`, the subquery is re-evaluated only once for each set of different values of the variables from its outer context. For `UNCACHEABLE SUBQUERY`, the subquery is re-evaluated for each row of the outer context.

  Cacheability of subqueries differs from caching of query results in the query cache (which is described in [Section 8.10.3.1, “How the Query Cache Operates”](optimization.html#query-cache-operation)). Subquery caching occurs during query execution, whereas the query cache is used to store results only after query execution finishes.

  When you specify `FORMAT=JSON` with `EXPLAIN`, the output has no single property directly equivalent to `select_type`; the `query_block` property corresponds to a given `SELECT`. Properties equivalent to most of the `SELECT` subquery types just shown are available (an example being `materialized_from_subquery` for `MATERIALIZED`), and are displayed when appropriate. There are no JSON equivalents for `SIMPLE` or `PRIMARY`.

  The `select_type` value for non-[`SELECT`](sql-statements.html#select) statements displays the statement type for affected tables. For example, `select_type` is `DELETE` for [`DELETE`](sql-statements.html#delete) statements.

- `table` (JSON name: `table_name`)

  The name of the table to which the row of output refers. This can also be one of the following values:

  - `<union*`M`*,*`N`*>`: The row refers to the union of the rows with `id` values of *`M`* and *`N`*.
  - `<derived*`N`*>`: The row refers to the derived table result for the row with an `id` value of *`N`*. A derived table may result, for example, from a subquery in the `FROM` clause.
  - `<subquery*`N`*>`: The row refers to the result of a materialized subquery for the row with an `id` value of *`N`*. See [Section 8.2.2.2, “Optimizing Subqueries with Materialization”](optimization.html#subquery-materialization).

- `partitions` (JSON name: `partitions`)

  The partitions from which records would be matched by the query. The value is `NULL` for nonpartitioned tables. See [Section 22.3.5, “Obtaining Information About Partitions”](partitioning.html#partitioning-info).

- `type` (JSON name: `access_type`)

  The join type. For descriptions of the different types, see [`EXPLAIN` Join Types](optimization.html#explain-join-types).

- `possible_keys` (JSON name: `possible_keys`)

  The `possible_keys` column indicates the indexes from which MySQL can choose to find the rows in this table. Note that this column is totally independent of the order of the tables as displayed in the output from [`EXPLAIN`](sql-statements.html#explain). That means that some of the keys in `possible_keys` might not be usable in practice with the generated table order.

  If this column is `NULL` (or undefined in JSON-formatted output), there are no relevant indexes. In this case, you may be able to improve the performance of your query by examining the `WHERE` clause to check whether it refers to some column or columns that would be suitable for indexing. If so, create an appropriate index and check the query with [`EXPLAIN`](sql-statements.html#explain) again. See [Section 13.1.8, “ALTER TABLE Statement”](sql-statements.html#alter-table).

  To see what indexes a table has, use `SHOW INDEX FROM *`tbl_name`*`.

- `key` (JSON name: `key`)

  The `key` column indicates the key (index) that MySQL actually decided to use. If MySQL decides to use one of the `possible_keys` indexes to look up rows, that index is listed as the key value.

  It is possible that `key` will name an index that is not present in the `possible_keys` value. This can happen if none of the `possible_keys` indexes are suitable for looking up rows, but all the columns selected by the query are columns of some other index. That is, the named index covers the selected columns, so although it is not used to determine which rows to retrieve, an index scan is more efficient than a data row scan.

  For `InnoDB`, a secondary index might cover the selected columns even if the query also selects the primary key because `InnoDB` stores the primary key value with each secondary index. If `key` is `NULL`, MySQL found no index to use for executing the query more efficiently.

  To force MySQL to use or ignore an index listed in the `possible_keys` column, use `FORCE INDEX`, `USE INDEX`, or `IGNORE INDEX` in your query. See [Section 8.9.4, “Index Hints”](optimization.html#index-hints).

  For `MyISAM` tables, running [`ANALYZE TABLE`](sql-statements.html#analyze-table) helps the optimizer choose better indexes. For `MyISAM` tables, [**myisamchk --analyze**](programs.html#myisamchk) does the same. See [Section 13.7.2.1, “ANALYZE TABLE Statement”](sql-statements.html#analyze-table), and [Section 7.6, “MyISAM Table Maintenance and Crash Recovery”](backup-and-recovery.html#myisam-table-maintenance).

- `key_len` (JSON name: `key_length`)

  The `key_len` column indicates the length of the key that MySQL decided to use. The value of `key_len` enables you to determine how many parts of a multiple-part key MySQL actually uses. If the `key` column says `NULL`, the `key_len` column also says `NULL`.

  Due to the key storage format, the key length is one greater for a column that can be `NULL` than for a `NOT NULL` column.

- `ref` (JSON name: `ref`)

  The `ref` column shows which columns or constants are compared to the index named in the `key` column to select rows from the table.

  If the value is `func`, the value used is the result of some function. To see which function, use [`SHOW WARNINGS`](sql-statements.html#show-warnings) following [`EXPLAIN`](sql-statements.html#explain) to see the extended [`EXPLAIN`](sql-statements.html#explain) output. The function might actually be an operator such as an arithmetic operator.

- `rows` (JSON name: `rows`)

  The `rows` column indicates the number of rows MySQL believes it must examine to execute the query.

  For [`InnoDB`](innodb-storage-engine.html) tables, this number is an estimate, and may not always be exact.

- `filtered` (JSON name: `filtered`)

  The `filtered` column indicates an estimated percentage of table rows that will be filtered by the table condition. The maximum value is 100, which means no filtering of rows occurred. Values decreasing from 100 indicate increasing amounts of filtering. `rows` shows the estimated number of rows examined and `rows` × `filtered` shows the number of rows that will be joined with the following table. For example, if `rows` is 1000 and `filtered` is 50.00 (50%), the number of rows to be joined with the following table is 1000 × 50% = 500.

- `Extra` (JSON name: none)

  This column contains additional information about how MySQL resolves the query. For descriptions of the different values, see [`EXPLAIN` Extra Information](optimization.html#explain-extra-information).

  There is no single JSON property corresponding to the `Extra` column; however, values that can occur in this column are exposed as JSON properties, or as the text of the `message` property.

#### EXPLAIN Join Types

The `type` column of [`EXPLAIN`](sql-statements.html#explain) output describes how tables are joined. In JSON-formatted output, these are found as values of the `access_type` property. The following list describes the join types, ordered from the best type to the worst:

- [`system`](optimization.html#jointype_system)

  

  

  

  The table has only one row (= system table). This is a special case of the [`const`](optimization.html#jointype_const) join type.

- [`const`](optimization.html#jointype_const)

  

  

  

  The table has at most one matching row, which is read at the start of the query. Because there is only one row, values from the column in this row can be regarded as constants by the rest of the optimizer. [`const`](optimization.html#jointype_const) tables are very fast because they are read only once.

  [`const`](optimization.html#jointype_const) is used when you compare all parts of a `PRIMARY KEY` or `UNIQUE` index to constant values. In the following queries, *`tbl_name`* can be used as a [`const`](optimization.html#jointype_const) table:

  ```
  SELECT * FROM tbl_name WHERE primary_key=1;
  
  SELECT * FROM tbl_name
    WHERE primary_key_part1=1 AND primary_key_part2=2;
  ```

- [`eq_ref`](optimization.html#jointype_eq_ref)

  

  

  One row is read from this table for each combination of rows from the previous tables. Other than the [`system`](optimization.html#jointype_system) and [`const`](optimization.html#jointype_const) types, this is the best possible join type. It is used when all parts of an index are used by the join and the index is a `PRIMARY KEY` or `UNIQUE NOT NULL` index.

  [`eq_ref`](optimization.html#jointype_eq_ref) can be used for indexed columns that are compared using the `=` operator. The comparison value can be a constant or an expression that uses columns from tables that are read before this table. In the following examples, MySQL can use an [`eq_ref`](optimization.html#jointype_eq_ref) join to process *`ref_table`*:

  ```
  SELECT * FROM ref_table,other_table
    WHERE ref_table.key_column=other_table.column;
  
  SELECT * FROM ref_table,other_table
    WHERE ref_table.key_column_part1=other_table.column
    AND ref_table.key_column_part2=1;
  ```

- [`ref`](optimization.html#jointype_ref)

  

  

  All rows with matching index values are read from this table for each combination of rows from the previous tables. [`ref`](optimization.html#jointype_ref) is used if the join uses only a leftmost prefix of the key or if the key is not a `PRIMARY KEY` or `UNIQUE` index (in other words, if the join cannot select a single row based on the key value). If the key that is used matches only a few rows, this is a good join type.

  [`ref`](optimization.html#jointype_ref) can be used for indexed columns that are compared using the `=` or `<=>` operator. In the following examples, MySQL can use a [`ref`](optimization.html#jointype_ref) join to process *`ref_table`*:

  ```
  SELECT * FROM ref_table WHERE key_column=expr;
  
  SELECT * FROM ref_table,other_table
    WHERE ref_table.key_column=other_table.column;
  
  SELECT * FROM ref_table,other_table
    WHERE ref_table.key_column_part1=other_table.column
    AND ref_table.key_column_part2=1;
  ```

- [`fulltext`](optimization.html#jointype_fulltext)

  

  

  The join is performed using a `FULLTEXT` index.

- [`ref_or_null`](optimization.html#jointype_ref_or_null)

  

  

  This join type is like [`ref`](optimization.html#jointype_ref), but with the addition that MySQL does an extra search for rows that contain `NULL` values. This join type optimization is used most often in resolving subqueries. In the following examples, MySQL can use a [`ref_or_null`](optimization.html#jointype_ref_or_null) join to process *`ref_table`*:

  ```
  SELECT * FROM ref_table
    WHERE key_column=expr OR key_column IS NULL;
  ```

  See [Section 8.2.1.13, “IS NULL Optimization”](optimization.html#is-null-optimization).

- [`index_merge`](optimization.html#jointype_index_merge)

  

  

  This join type indicates that the Index Merge optimization is used. In this case, the `key` column in the output row contains a list of indexes used, and `key_len` contains a list of the longest key parts for the indexes used. For more information, see [Section 8.2.1.3, “Index Merge Optimization”](optimization.html#index-merge-optimization).

- [`unique_subquery`](optimization.html#jointype_unique_subquery)

  

  

  This type replaces [`eq_ref`](optimization.html#jointype_eq_ref) for some `IN` subqueries of the following form:

  ```
  value IN (SELECT primary_key FROM single_table WHERE some_expr)
  ```

  [`unique_subquery`](optimization.html#jointype_unique_subquery) is just an index lookup function that replaces the subquery completely for better efficiency.

- [`index_subquery`](optimization.html#jointype_index_subquery)

  

  

  This join type is similar to [`unique_subquery`](optimization.html#jointype_unique_subquery). It replaces `IN` subqueries, but it works for nonunique indexes in subqueries of the following form:

  ```
  value IN (SELECT key_column FROM single_table WHERE some_expr)
  ```

- [`range`](optimization.html#jointype_range)

  

  

  Only rows that are in a given range are retrieved, using an index to select the rows. The `key` column in the output row indicates which index is used. The `key_len` contains the longest key part that was used. The `ref` column is `NULL` for this type.

  [`range`](optimization.html#jointype_range) can be used when a key column is compared to a constant using any of the [`=`](functions.html#operator_equal), [`<>`](functions.html#operator_not-equal), [`>`](functions.html#operator_greater-than), [`>=`](functions.html#operator_greater-than-or-equal), [`<`](functions.html#operator_less-than), [`<=`](functions.html#operator_less-than-or-equal), [`IS NULL`](functions.html#operator_is-null), [`<=>`](functions.html#operator_equal-to), [`BETWEEN`](functions.html#operator_between), [`LIKE`](functions.html#operator_like), or [`IN()`](functions.html#operator_in) operators:

  ```
  SELECT * FROM tbl_name
    WHERE key_column = 10;
  
  SELECT * FROM tbl_name
    WHERE key_column BETWEEN 10 and 20;
  
  SELECT * FROM tbl_name
    WHERE key_column IN (10,20,30);
  
  SELECT * FROM tbl_name
    WHERE key_part1 = 10 AND key_part2 IN (10,20,30);
  ```

- [`index`](optimization.html#jointype_index)

  

  

  The `index` join type is the same as [`ALL`](optimization.html#jointype_all), except that the index tree is scanned. This occurs two ways:

  - If the index is a covering index for the queries and can be used to satisfy all data required from the table, only the index tree is scanned. In this case, the `Extra` column says `Using index`. An index-only scan usually is faster than [`ALL`](optimization.html#jointype_all) because the size of the index usually is smaller than the table data.
  - A full table scan is performed using reads from the index to look up data rows in index order. `Uses index` does not appear in the `Extra` column.

  MySQL can use this join type when the query uses only columns that are part of a single index.

- [`ALL`](optimization.html#jointype_all)

  

  

  A full table scan is done for each combination of rows from the previous tables. This is normally not good if the table is the first table not marked [`const`](optimization.html#jointype_const), and usually *very* bad in all other cases. Normally, you can avoid [`ALL`](optimization.html#jointype_all) by adding indexes that enable row retrieval from the table based on constant values or column values from earlier tables.

#### EXPLAIN Extra Information

The `Extra` column of [`EXPLAIN`](sql-statements.html#explain) output contains additional information about how MySQL resolves the query. The following list explains the values that can appear in this column. Each item also indicates for JSON-formatted output which property displays the `Extra` value. For some of these, there is a specific property. The others display as the text of the `message` property.

If you want to make your queries as fast as possible, look out for `Extra` column values of `Using filesort` and `Using temporary`, or, in JSON-formatted `EXPLAIN` output, for `using_filesort` and `using_temporary_table` properties equal to `true`.

- `Child of '*`table`*' pushed join@1` (JSON: `message` text)

  This table is referenced as the child of *`table`* in a join that can be pushed down to the NDB kernel. Applies only in NDB Cluster, when pushed-down joins are enabled. See the description of the [`ndb_join_pushdown`](mysql-cluster.html#sysvar_ndb_join_pushdown) server system variable for more information and examples.

- `const row not found` (JSON property: `const_row_not_found`)

  For a query such as `SELECT ... FROM *`tbl_name`*`, the table was empty.

- `Deleting all rows` (JSON property: `message`)

  For [`DELETE`](sql-statements.html#delete), some storage engines (such as [`MyISAM`](storage-engines.html#myisam-storage-engine)) support a handler method that removes all table rows in a simple and fast way. This `Extra` value is displayed if the engine uses this optimization.

- `Distinct` (JSON property: `distinct`)

  MySQL is looking for distinct values, so it stops searching for more rows for the current row combination after it has found the first matching row.

- `FirstMatch(*`tbl_name`*)` (JSON property: `first_match`)

  The semijoin FirstMatch join shortcutting strategy is used for *`tbl_name`*.

- `Full scan on NULL key` (JSON property: `message`)

  This occurs for subquery optimization as a fallback strategy when the optimizer cannot use an index-lookup access method.

- `Impossible HAVING` (JSON property: `message`)

  The `HAVING` clause is always false and cannot select any rows.

- `Impossible WHERE` (JSON property: `message`)

  The `WHERE` clause is always false and cannot select any rows.

- `Impossible WHERE noticed after reading const tables` (JSON property: `message`)

  MySQL has read all [`const`](optimization.html#jointype_const) (and [`system`](optimization.html#jointype_system)) tables and notice that the `WHERE` clause is always false.

- `LooseScan(*`m`*..*`n`*)` (JSON property: `message`)

  The semijoin LooseScan strategy is used. *`m`* and *`n`* are key part numbers.

- `No matching min/max row` (JSON property: `message`)

  No row satisfies the condition for a query such as `SELECT MIN(...) FROM ... WHERE *`condition`*`.

- `no matching row in const table` (JSON property: `message`)

  For a query with a join, there was an empty table or a table with no rows satisfying a unique index condition.

- `No matching rows after partition pruning` (JSON property: `message`)

  For [`DELETE`](sql-statements.html#delete) or [`UPDATE`](sql-statements.html#update), the optimizer found nothing to delete or update after partition pruning. It is similar in meaning to `Impossible WHERE` for [`SELECT`](sql-statements.html#select) statements.

- `No tables used` (JSON property: `message`)

  The query has no `FROM` clause, or has a `FROM DUAL` clause.

  For [`INSERT`](sql-statements.html#insert) or [`REPLACE`](sql-statements.html#replace) statements, [`EXPLAIN`](sql-statements.html#explain) displays this value when there is no [`SELECT`](sql-statements.html#select) part. For example, it appears for `EXPLAIN INSERT INTO t VALUES(10)` because that is equivalent to `EXPLAIN INSERT INTO t SELECT 10 FROM DUAL`.

- `Not exists` (JSON property: `message`)

  MySQL was able to do a `LEFT JOIN` optimization on the query and does not examine more rows in this table for the previous row combination after it finds one row that matches the `LEFT JOIN` criteria. Here is an example of the type of query that can be optimized this way:

  ```
  SELECT * FROM t1 LEFT JOIN t2 ON t1.id=t2.id
    WHERE t2.id IS NULL;
  ```

  Assume that `t2.id` is defined as `NOT NULL`. In this case, MySQL scans `t1` and looks up the rows in `t2` using the values of `t1.id`. If MySQL finds a matching row in `t2`, it knows that `t2.id` can never be `NULL`, and does not scan through the rest of the rows in `t2` that have the same `id` value. In other words, for each row in `t1`, MySQL needs to do only a single lookup in `t2`, regardless of how many rows actually match in `t2`.

- `Plan isn't ready yet` (JSON property: none)

  This value occurs with [`EXPLAIN FOR CONNECTION`](optimization.html#explain-for-connection) when the optimizer has not finished creating the execution plan for the statement executing in the named connection. If execution plan output comprises multiple lines, any or all of them could have this `Extra` value, depending on the progress of the optimizer in determining the full execution plan.

- `Range checked for each record (index map: *`N`*)` (JSON property: `message`)

  MySQL found no good index to use, but found that some of indexes might be used after column values from preceding tables are known. For each row combination in the preceding tables, MySQL checks whether it is possible to use a [`range`](optimization.html#jointype_range) or [`index_merge`](optimization.html#jointype_index_merge) access method to retrieve rows. This is not very fast, but is faster than performing a join with no index at all. The applicability criteria are as described in [Section 8.2.1.2, “Range Optimization”](optimization.html#range-optimization), and [Section 8.2.1.3, “Index Merge Optimization”](optimization.html#index-merge-optimization), with the exception that all column values for the preceding table are known and considered to be constants.

  Indexes are numbered beginning with 1, in the same order as shown by [`SHOW INDEX`](sql-statements.html#show-index) for the table. The index map value *`N`* is a bitmask value that indicates which indexes are candidates. For example, a value of `0x19` (binary 11001) means that indexes 1, 4, and 5 will be considered.

- `Scanned *`N`* databases` (JSON property: `message`)

  This indicates how many directory scans the server performs when processing a query for `INFORMATION_SCHEMA` tables, as described in [Section 8.2.3, “Optimizing INFORMATION_SCHEMA Queries”](optimization.html#information-schema-optimization). The value of *`N`* can be 0, 1, or `all`.

- `Select tables optimized away` (JSON property: `message`)

  The optimizer determined 1) that at most one row should be returned, and 2) that to produce this row, a deterministic set of rows must be read. When the rows to be read can be read during the optimization phase (for example, by reading index rows), there is no need to read any tables during query execution.

  The first condition is fulfilled when the query is implicitly grouped (contains an aggregate function but no `GROUP BY` clause). The second condition is fulfilled when one row lookup is performed per index used. The number of indexes read determines the number of rows to read.

  Consider the following implicitly grouped query:

  ```
  SELECT MIN(c1), MIN(c2) FROM t1;
  ```

  Suppose that `MIN(c1)` can be retrieved by reading one index row and `MIN(c2)` can be retrieved by reading one row from a different index. That is, for each column `c1` and `c2`, there exists an index where the column is the first column of the index. In this case, one row is returned, produced by reading two deterministic rows.

  This `Extra` value does not occur if the rows to read are not deterministic. Consider this query:

  ```
  SELECT MIN(c2) FROM t1 WHERE c1 <= 10;
  ```

  Suppose that `(c1, c2)` is a covering index. Using this index, all rows with `c1 <= 10` must be scanned to find the minimum `c2` value. By contrast, consider this query:

  ```
  SELECT MIN(c2) FROM t1 WHERE c1 = 10;
  ```

  In this case, the first index row with `c1 = 10` contains the minimum `c2` value. Only one row must be read to produce the returned row.

  For storage engines that maintain an exact row count per table (such as `MyISAM`, but not `InnoDB`), this `Extra` value can occur for `COUNT(*)` queries for which the `WHERE` clause is missing or always true and there is no `GROUP BY` clause. (This is an instance of an implicitly grouped query where the storage engine influences whether a deterministic number of rows can be read.)

- `Skip_open_table`, `Open_frm_only`, `Open_full_table` (JSON property: `message`)

  These values indicate file-opening optimizations that apply to queries for `INFORMATION_SCHEMA` tables, as described in [Section 8.2.3, “Optimizing INFORMATION_SCHEMA Queries”](optimization.html#information-schema-optimization).

  - `Skip_open_table`: Table files do not need to be opened. The information has already become available within the query by scanning the database directory.
  - `Open_frm_only`: Only the table's `.frm` file need be opened.
  - `Open_full_table`: The unoptimized information lookup. The `.frm`, `.MYD`, and `.MYI` files must be opened.

- `Start temporary`, `End temporary` (JSON property: `message`)

  This indicates temporary table use for the semijoin Duplicate Weedout strategy.

- `unique row not found` (JSON property: `message`)

  For a query such as `SELECT ... FROM *`tbl_name`*`, no rows satisfy the condition for a `UNIQUE` index or `PRIMARY KEY` on the table.

- `Using filesort` (JSON property: `using_filesort`)

  MySQL must do an extra pass to find out how to retrieve the rows in sorted order. The sort is done by going through all rows according to the join type and storing the sort key and pointer to the row for all rows that match the `WHERE` clause. The keys then are sorted and the rows are retrieved in sorted order. See [Section 8.2.1.14, “ORDER BY Optimization”](optimization.html#order-by-optimization).

- `Using index` (JSON property: `using_index`)

  The column information is retrieved from the table using only information in the index tree without having to do an additional seek to read the actual row. This strategy can be used when the query uses only columns that are part of a single index.

  For `InnoDB` tables that have a user-defined clustered index, that index can be used even when `Using index` is absent from the `Extra` column. This is the case if `type` is [`index`](optimization.html#jointype_index) and `key` is `PRIMARY`.

- `Using index condition` (JSON property: `using_index_condition`)

  Tables are read by accessing index tuples and testing them first to determine whether to read full table rows. In this way, index information is used to defer (“push down”) reading full table rows unless it is necessary. See [Section 8.2.1.5, “Index Condition Pushdown Optimization”](optimization.html#index-condition-pushdown-optimization).

- `Using index for group-by` (JSON property: `using_index_for_group_by`)

  Similar to the `Using index` table access method, `Using index for group-by` indicates that MySQL found an index that can be used to retrieve all columns of a `GROUP BY` or `DISTINCT` query without any extra disk access to the actual table. Additionally, the index is used in the most efficient way so that for each group, only a few index entries are read. For details, see [Section 8.2.1.15, “GROUP BY Optimization”](optimization.html#group-by-optimization).

- `Using join buffer (Block Nested Loop)`, `Using join buffer (Batched Key Access)` (JSON property: `using_join_buffer`)

  Tables from earlier joins are read in portions into the join buffer, and then their rows are used from the buffer to perform the join with the current table. `(Block Nested Loop)` indicates use of the Block Nested-Loop algorithm and `(Batched Key Access)` indicates use of the Batched Key Access algorithm. That is, the keys from the table on the preceding line of the [`EXPLAIN`](sql-statements.html#explain) output are buffered, and the matching rows are fetched in batches from the table represented by the line in which `Using join buffer` appears.

  In JSON-formatted output, the value of `using_join_buffer` is always either one of `Block Nested Loop` or `Batched Key Access`.

  For more information about these algorithms, see [Block Nested-Loop Join Algorithm](optimization.html#block-nested-loop-join-algorithm), and [Batched Key Access Joins](optimization.html#bka-optimization).

- `Using MRR` (JSON property: `message`)

  Tables are read using the Multi-Range Read optimization strategy. See [Section 8.2.1.10, “Multi-Range Read Optimization”](optimization.html#mrr-optimization).

- `Using sort_union(...)`, `Using union(...)`, `Using intersect(...)` (JSON property: `message`)

  These indicate the particular algorithm showing how index scans are merged for the [`index_merge`](optimization.html#jointype_index_merge) join type. See [Section 8.2.1.3, “Index Merge Optimization”](optimization.html#index-merge-optimization).

- `Using temporary` (JSON property: `using_temporary_table`)

  To resolve the query, MySQL needs to create a temporary table to hold the result. This typically happens if the query contains `GROUP BY` and `ORDER BY` clauses that list columns differently.

- `Using where` (JSON property: `attached_condition`)

  A `WHERE` clause is used to restrict which rows to match against the next table or send to the client. Unless you specifically intend to fetch or examine all rows from the table, you may have something wrong in your query if the `Extra` value is not `Using where` and the table join type is [`ALL`](optimization.html#jointype_all) or [`index`](optimization.html#jointype_index).

  `Using where` has no direct counterpart in JSON-formatted output; the `attached_condition` property contains any `WHERE` condition used.

- `Using where with pushed condition` (JSON property: `message`)

  This item applies to [`NDB`](mysql-cluster.html) tables *only*. It means that NDB Cluster is using the Condition Pushdown optimization to improve the efficiency of a direct comparison between a nonindexed column and a constant. In such cases, the condition is “pushed down” to the cluster's data nodes and is evaluated on all data nodes simultaneously. This eliminates the need to send nonmatching rows over the network, and can speed up such queries by a factor of 5 to 10 times over cases where Condition Pushdown could be but is not used. For more information, see [Section 8.2.1.4, “Engine Condition Pushdown Optimization”](optimization.html#condition-pushdown-optimization).

- `Zero limit` (JSON property: `message`)

  The query had a `LIMIT 0` clause and cannot select any rows.

#### EXPLAIN Output Interpretation

You can get a good indication of how good a join is by taking the product of the values in the `rows` column of the [`EXPLAIN`](sql-statements.html#explain) output. This should tell you roughly how many rows MySQL must examine to execute the query. If you restrict queries with the [`max_join_size`](server-administration.html#sysvar_max_join_size) system variable, this row product also is used to determine which multiple-table [`SELECT`](sql-statements.html#select) statements to execute and which to abort. See [Section 5.1.1, “Configuring the Server”](server-administration.html#server-configuration).

The following example shows how a multiple-table join can be optimized progressively based on the information provided by [`EXPLAIN`](sql-statements.html#explain).

Suppose that you have the [`SELECT`](sql-statements.html#select) statement shown here and that you plan to examine it using [`EXPLAIN`](sql-statements.html#explain):

```
EXPLAIN SELECT tt.TicketNumber, tt.TimeIn,
               tt.ProjectReference, tt.EstimatedShipDate,
               tt.ActualShipDate, tt.ClientID,
               tt.ServiceCodes, tt.RepetitiveID,
               tt.CurrentProcess, tt.CurrentDPPerson,
               tt.RecordVolume, tt.DPPrinted, et.COUNTRY,
               et_1.COUNTRY, do.CUSTNAME
        FROM tt, et, et AS et_1, do
        WHERE tt.SubmitTime IS NULL
          AND tt.ActualPC = et.EMPLOYID
          AND tt.AssignedPC = et_1.EMPLOYID
          AND tt.ClientID = do.CUSTNMBR;
```

For this example, make the following assumptions:

- The columns being compared have been declared as follows.

  | Table | Column       | Data Type  |
  | :---- | :----------- | :--------- |
  | `tt`  | `ActualPC`   | `CHAR(10)` |
  | `tt`  | `AssignedPC` | `CHAR(10)` |
  | `tt`  | `ClientID`   | `CHAR(10)` |
  | `et`  | `EMPLOYID`   | `CHAR(15)` |
  | `do`  | `CUSTNMBR`   | `CHAR(15)` |

- The tables have the following indexes.

  | Table | Index                    |
  | :---- | :----------------------- |
  | `tt`  | `ActualPC`               |
  | `tt`  | `AssignedPC`             |
  | `tt`  | `ClientID`               |
  | `et`  | `EMPLOYID` (primary key) |
  | `do`  | `CUSTNMBR` (primary key) |

- The `tt.ActualPC` values are not evenly distributed.

Initially, before any optimizations have been performed, the [`EXPLAIN`](sql-statements.html#explain) statement produces the following information:

```
table type possible_keys key  key_len ref  rows  Extra
et    ALL  PRIMARY       NULL NULL    NULL 74
do    ALL  PRIMARY       NULL NULL    NULL 2135
et_1  ALL  PRIMARY       NULL NULL    NULL 74
tt    ALL  AssignedPC,   NULL NULL    NULL 3872
           ClientID,
           ActualPC
      Range checked for each record (index map: 0x23)
```

Because `type` is [`ALL`](optimization.html#jointype_all) for each table, this output indicates that MySQL is generating a Cartesian product of all the tables; that is, every combination of rows. This takes quite a long time, because the product of the number of rows in each table must be examined. For the case at hand, this product is 74 × 2135 × 74 × 3872 = 45,268,558,720 rows. If the tables were bigger, you can only imagine how long it would take.

One problem here is that MySQL can use indexes on columns more efficiently if they are declared as the same type and size. In this context, [`VARCHAR`](data-types.html#char) and [`CHAR`](data-types.html#char) are considered the same if they are declared as the same size. `tt.ActualPC` is declared as `CHAR(10)` and `et.EMPLOYID` is `CHAR(15)`, so there is a length mismatch.

To fix this disparity between column lengths, use [`ALTER TABLE`](sql-statements.html#alter-table) to lengthen `ActualPC` from 10 characters to 15 characters:

```
mysql> ALTER TABLE tt MODIFY ActualPC VARCHAR(15);
```

Now `tt.ActualPC` and `et.EMPLOYID` are both `VARCHAR(15)`. Executing the [`EXPLAIN`](sql-statements.html#explain) statement again produces this result:

```
table type   possible_keys key     key_len ref         rows    Extra
tt    ALL    AssignedPC,   NULL    NULL    NULL        3872    Using
             ClientID,                                         where
             ActualPC
do    ALL    PRIMARY       NULL    NULL    NULL        2135
      Range checked for each record (index map: 0x1)
et_1  ALL    PRIMARY       NULL    NULL    NULL        74
      Range checked for each record (index map: 0x1)
et    eq_ref PRIMARY       PRIMARY 15      tt.ActualPC 1
```

This is not perfect, but is much better: The product of the `rows` values is less by a factor of 74. This version executes in a couple of seconds.

A second alteration can be made to eliminate the column length mismatches for the `tt.AssignedPC = et_1.EMPLOYID` and `tt.ClientID = do.CUSTNMBR` comparisons:

```
mysql> ALTER TABLE tt MODIFY AssignedPC VARCHAR(15),
                      MODIFY ClientID   VARCHAR(15);
```

After that modification, [`EXPLAIN`](sql-statements.html#explain) produces the output shown here:

```
table type   possible_keys key      key_len ref           rows Extra
et    ALL    PRIMARY       NULL     NULL    NULL          74
tt    ref    AssignedPC,   ActualPC 15      et.EMPLOYID   52   Using
             ClientID,                                         where
             ActualPC
et_1  eq_ref PRIMARY       PRIMARY  15      tt.AssignedPC 1
do    eq_ref PRIMARY       PRIMARY  15      tt.ClientID   1
```

At this point, the query is optimized almost as well as possible. The remaining problem is that, by default, MySQL assumes that values in the `tt.ActualPC` column are evenly distributed, and that is not the case for the `tt` table. Fortunately, it is easy to tell MySQL to analyze the key distribution:

```
mysql> ANALYZE TABLE tt;
```

With the additional index information, the join is perfect and [`EXPLAIN`](sql-statements.html#explain) produces this result:

```
table type   possible_keys key     key_len ref           rows Extra
tt    ALL    AssignedPC    NULL    NULL    NULL          3872 Using
             ClientID,                                        where
             ActualPC
et    eq_ref PRIMARY       PRIMARY 15      tt.ActualPC   1
et_1  eq_ref PRIMARY       PRIMARY 15      tt.AssignedPC 1
do    eq_ref PRIMARY       PRIMARY 15      tt.ClientID   1
```

The `rows` column in the output from [`EXPLAIN`](sql-statements.html#explain) is an educated guess from the MySQL join optimizer. Check whether the numbers are even close to the truth by comparing the `rows` product with the actual number of rows that the query returns. If the numbers are quite different, you might get better performance by using `STRAIGHT_JOIN` in your [`SELECT`](sql-statements.html#select) statement and trying to list the tables in a different order in the `FROM` clause. (However, `STRAIGHT_JOIN` may prevent indexes from being used because it disables semijoin transformations. See [Section 8.2.2.1, “Optimizing Subqueries, Derived Tables, and View References with Semijoin Transformations”](optimization.html#semijoins).)

It is possible in some cases to execute statements that modify data when [`EXPLAIN SELECT`](sql-statements.html#explain) is used with a subquery; for more information, see [Section 13.2.10.8, “Derived Tables”](sql-statements.html#derived-tables).

### 8.8.3 Extended EXPLAIN Output Format

For [`SELECT`](sql-statements.html#select) statements, the [`EXPLAIN`](sql-statements.html#explain) statement produces extra (“extended”) information that is not part of [`EXPLAIN`](sql-statements.html#explain) output but can be viewed by issuing a [`SHOW WARNINGS`](sql-statements.html#show-warnings) statement following [`EXPLAIN`](sql-statements.html#explain). The `Message` value in [`SHOW WARNINGS`](sql-statements.html#show-warnings) output displays how the optimizer qualifies table and column names in the [`SELECT`](sql-statements.html#select) statement, what the [`SELECT`](sql-statements.html#select) looks like after the application of rewriting and optimization rules, and possibly other notes about the optimization process.

The extended information displayable with a [`SHOW WARNINGS`](sql-statements.html#show-warnings) statement following [`EXPLAIN`](sql-statements.html#explain) is produced only for [`SELECT`](sql-statements.html#select) statements. [`SHOW WARNINGS`](sql-statements.html#show-warnings) displays an empty result for other explainable statements ([`DELETE`](sql-statements.html#delete), [`INSERT`](sql-statements.html#insert), [`REPLACE`](sql-statements.html#replace), and [`UPDATE`](sql-statements.html#update)).

Note

In older MySQL releases, extended information was produced using [`EXPLAIN EXTENDED`](sql-statements.html#explain). That syntax is still recognized for backward compatibility but extended output is now enabled by default, so the `EXTENDED` keyword is superfluous and deprecated. Its use results in a warning, and it will be removed from [`EXPLAIN`](sql-statements.html#explain) syntax in a future MySQL release.

Here is an example of extended [`EXPLAIN`](sql-statements.html#explain) output:

```
mysql> EXPLAIN
       SELECT t1.a, t1.a IN (SELECT t2.a FROM t2) FROM t1\G
*************************** 1. row ***************************
           id: 1
  select_type: PRIMARY
        table: t1
         type: index
possible_keys: NULL
          key: PRIMARY
      key_len: 4
          ref: NULL
         rows: 4
     filtered: 100.00
        Extra: Using index
*************************** 2. row ***************************
           id: 2
  select_type: SUBQUERY
        table: t2
         type: index
possible_keys: a
          key: a
      key_len: 5
          ref: NULL
         rows: 3
     filtered: 100.00
        Extra: Using index
2 rows in set, 1 warning (0.00 sec)

mysql> SHOW WARNINGS\G
*************************** 1. row ***************************
  Level: Note
   Code: 1003
Message: /* select#1 */ select `test`.`t1`.`a` AS `a`,
         <in_optimizer>(`test`.`t1`.`a`,`test`.`t1`.`a` in
         ( <materialize> (/* select#2 */ select `test`.`t2`.`a`
         from `test`.`t2` where 1 having 1 ),
         <primary_index_lookup>(`test`.`t1`.`a` in
         <temporary table> on <auto_key>
         where ((`test`.`t1`.`a` = `materialized-subquery`.`a`))))) AS `t1.a
         IN (SELECT t2.a FROM t2)` from `test`.`t1`
1 row in set (0.00 sec)
```

Because the statement displayed by [`SHOW WARNINGS`](sql-statements.html#show-warnings) may contain special markers to provide information about query rewriting or optimizer actions, the statement is not necessarily valid SQL and is not intended to be executed. The output may also include rows with `Message` values that provide additional non-SQL explanatory notes about actions taken by the optimizer.

The following list describes special markers that can appear in the extended output displayed by [`SHOW WARNINGS`](sql-statements.html#show-warnings):

- `<auto_key>`

  An automatically generated key for a temporary table.

- `<cache>(*`expr`*)`

  The expression (such as a scalar subquery) is executed once and the resulting value is saved in memory for later use. For results consisting of multiple values, a temporary table may be created and you will see `<temporary table>` instead.

- `<exists>(*`query fragment`*)`

  The subquery predicate is converted to an `EXISTS` predicate and the subquery is transformed so that it can be used together with the `EXISTS` predicate.

- `<in_optimizer>(*`query fragment`*)`

  This is an internal optimizer object with no user significance.

- `<index_lookup>(*`query fragment`*)`

  The query fragment is processed using an index lookup to find qualifying rows.

- `<if>(*`condition`*, *`expr1`*, *`expr2`*)`

  If the condition is true, evaluate to *`expr1`*, otherwise *`expr2`*.

- `<is_not_null_test>(*`expr`*)`

  A test to verify that the expression does not evaluate to `NULL`.

- `<materialize>(*`query fragment`*)`

  Subquery materialization is used.

- ``materialized-subquery`.*`col_name`*`

  A reference to the column *`col_name`* in an internal temporary table materialized to hold the result from evaluating a subquery.

- `<primary_index_lookup>(*`query fragment`*)`

  The query fragment is processed using a primary key lookup to find qualifying rows.

- `<ref_null_helper>(*`expr`*)`

  This is an internal optimizer object with no user significance.

- `/* select#*`N`* */ *`select_stmt`*`

  The `SELECT` is associated with the row in non-extended [`EXPLAIN`](sql-statements.html#explain) output that has an `id` value of *`N`*.

- `*`outer_tables`* semi join (*`inner_tables`*)`

  A semijoin operation. *`inner_tables`* shows the tables that were not pulled out. See [Section 8.2.2.1, “Optimizing Subqueries, Derived Tables, and View References with Semijoin Transformations”](optimization.html#semijoins).

- `<temporary table>`

  This represents an internal temporary table created to cache an intermediate result.

When some tables are of [`const`](optimization.html#jointype_const) or [`system`](optimization.html#jointype_system) type, expressions involving columns from these tables are evaluated early by the optimizer and are not part of the displayed statement. However, with `FORMAT=JSON`, some [`const`](optimization.html#jointype_const) table accesses are displayed as a [`ref`](optimization.html#jointype_ref) access that uses a const value.

### 8.8.4 Obtaining Execution Plan Information for a Named Connection

To obtain the execution plan for an explainable statement executing in a named connection, use this statement:

```
EXPLAIN [options] FOR CONNECTION connection_id;
```

[`EXPLAIN FOR CONNECTION`](optimization.html#explain-for-connection) returns the [`EXPLAIN`](sql-statements.html#explain) information that is currently being used to execute a query in a given connection. Because of changes to data (and supporting statistics) it may produce a different result from running [`EXPLAIN`](sql-statements.html#explain) on the equivalent query text. This difference in behavior can be useful in diagnosing more transient performance problems. For example, if you are running a statement in one session that is taking a long time to complete, using [`EXPLAIN FOR CONNECTION`](optimization.html#explain-for-connection) in another session may yield useful information about the cause of the delay.

*`connection_id`* is the connection identifier, as obtained from the `INFORMATION_SCHEMA` [`PROCESSLIST`](information-schema.html#processlist-table) table or the [`SHOW PROCESSLIST`](sql-statements.html#show-processlist) statement. If you have the [`PROCESS`](security.html#priv_process) privilege, you can specify the identifier for any connection. Otherwise, you can specify the identifier only for your own connections.

If the named connection is not executing a statement, the result is empty. Otherwise, `EXPLAIN FOR CONNECTION` applies only if the statement being executed in the named connection is explainable. This includes [`SELECT`](sql-statements.html#select), [`DELETE`](sql-statements.html#delete), [`INSERT`](sql-statements.html#insert), [`REPLACE`](sql-statements.html#replace), and [`UPDATE`](sql-statements.html#update). (However, `EXPLAIN FOR CONNECTION` does not work for prepared statements, even prepared statements of those types.)

If the named connection is executing an explainable statement, the output is what you would obtain by using `EXPLAIN` on the statement itself.

If the named connection is executing a statement that is not explainable, an error occurs. For example, you cannot name the connection identifier for your current session because `EXPLAIN` is not explainable:

```
mysql> SELECT CONNECTION_ID();
+-----------------+
| CONNECTION_ID() |
+-----------------+
|             373 |
+-----------------+
1 row in set (0.00 sec)

mysql> EXPLAIN FOR CONNECTION 373;
ERROR 1889 (HY000): EXPLAIN FOR CONNECTION command is supported
only for SELECT/UPDATE/INSERT/DELETE/REPLACE
```

The `Com_explain_other` status variable indicates the number of [`EXPLAIN FOR CONNECTION`](sql-statements.html#explain) statements executed.

### 8.8.5 Estimating Query Performance



In most cases, you can estimate query performance by counting disk seeks. For small tables, you can usually find a row in one disk seek (because the index is probably cached). For bigger tables, you can estimate that, using B-tree indexes, you need this many seeks to find a row: `log(*`row_count`*) / log(*`index_block_length`* / 3 * 2 / (*`index_length`* + *`data_pointer_length`*)) + 1`.

In MySQL, an index block is usually 1,024 bytes and the data pointer is usually four bytes. For a 500,000-row table with a key value length of three bytes (the size of [`MEDIUMINT`](data-types.html#integer-types)), the formula indicates `log(500,000)/log(1024/3*2/(3+4)) + 1` = `4` seeks.

This index would require storage of about 500,000 * 7 * 3/2 = 5.2MB (assuming a typical index buffer fill ratio of 2/3), so you probably have much of the index in memory and so need only one or two calls to read data to find the row.

For writes, however, you need four seek requests to find where to place a new index value and normally two seeks to update the index and write the row.

The preceding discussion does not mean that your application performance slowly degenerates by log *`N`*. As long as everything is cached by the OS or the MySQL server, things become only marginally slower as the table gets bigger. After the data gets too big to be cached, things start to go much slower until your applications are bound only by disk seeks (which increase by log *`N`*). To avoid this, increase the key cache size as the data grows. For `MyISAM` tables, the key cache size is controlled by the [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) system variable. See [Section 5.1.1, “Configuring the Server”](server-administration.html#server-configuration).

## 8.9 Controlling the Query Optimizer

- [8.9.1 Controlling Query Plan Evaluation](optimization.html#controlling-query-plan-evaluation)
- [8.9.2 Switchable Optimizations](optimization.html#switchable-optimizations)
- [8.9.3 Optimizer Hints](optimization.html#optimizer-hints)
- [8.9.4 Index Hints](optimization.html#index-hints)
- [8.9.5 The Optimizer Cost Model](optimization.html#cost-model)



MySQL provides optimizer control through system variables that affect how query plans are evaluated, switchable optimizations, optimizer and index hints, and the optimizer cost model.

### 8.9.1 Controlling Query Plan Evaluation



The task of the query optimizer is to find an optimal plan for executing an SQL query. Because the difference in performance between “good” and “bad” plans can be orders of magnitude (that is, seconds versus hours or even days), most query optimizers, including that of MySQL, perform a more or less exhaustive search for an optimal plan among all possible query evaluation plans. For join queries, the number of possible plans investigated by the MySQL optimizer grows exponentially with the number of tables referenced in a query. For small numbers of tables (typically less than 7 to 10) this is not a problem. However, when larger queries are submitted, the time spent in query optimization may easily become the major bottleneck in the server's performance.

A more flexible method for query optimization enables the user to control how exhaustive the optimizer is in its search for an optimal query evaluation plan. The general idea is that the fewer plans that are investigated by the optimizer, the less time it spends in compiling a query. On the other hand, because the optimizer skips some plans, it may miss finding an optimal plan.

The behavior of the optimizer with respect to the number of plans it evaluates can be controlled using two system variables:

- The [`optimizer_prune_level`](server-administration.html#sysvar_optimizer_prune_level) variable tells the optimizer to skip certain plans based on estimates of the number of rows accessed for each table. Our experience shows that this kind of “educated guess” rarely misses optimal plans, and may dramatically reduce query compilation times. That is why this option is on (`optimizer_prune_level=1`) by default. However, if you believe that the optimizer missed a better query plan, this option can be switched off (`optimizer_prune_level=0`) with the risk that query compilation may take much longer. Note that, even with the use of this heuristic, the optimizer still explores a roughly exponential number of plans.
- The [`optimizer_search_depth`](server-administration.html#sysvar_optimizer_search_depth) variable tells how far into the “future” of each incomplete plan the optimizer should look to evaluate whether it should be expanded further. Smaller values of [`optimizer_search_depth`](server-administration.html#sysvar_optimizer_search_depth) may result in orders of magnitude smaller query compilation times. For example, queries with 12, 13, or more tables may easily require hours and even days to compile if [`optimizer_search_depth`](server-administration.html#sysvar_optimizer_search_depth) is close to the number of tables in the query. At the same time, if compiled with [`optimizer_search_depth`](server-administration.html#sysvar_optimizer_search_depth) equal to 3 or 4, the optimizer may compile in less than a minute for the same query. If you are unsure of what a reasonable value is for [`optimizer_search_depth`](server-administration.html#sysvar_optimizer_search_depth), this variable can be set to 0 to tell the optimizer to determine the value automatically.

### 8.9.2 Switchable Optimizations



The [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable enables control over optimizer behavior. Its value is a set of flags, each of which has a value of `on` or `off` to indicate whether the corresponding optimizer behavior is enabled or disabled. This variable has global and session values and can be changed at runtime. The global default can be set at server startup.

To see the current set of optimizer flags, select the variable value:

```
mysql> SELECT @@optimizer_switch\G
*************************** 1. row ***************************
@@optimizer_switch: index_merge=on,index_merge_union=on,
                    index_merge_sort_union=on,
                    index_merge_intersection=on,
                    engine_condition_pushdown=on,
                    index_condition_pushdown=on,
                    mrr=on,mrr_cost_based=on,
                    block_nested_loop=on,batched_key_access=off,
                    materialization=on,semijoin=on,loosescan=on,
                    firstmatch=on,duplicateweedout=on,
                    subquery_materialization_cost_based=on,
                    use_index_extensions=on,
                    condition_fanout_filter=on,derived_merge=on
```

To change the value of [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch), assign a value consisting of a comma-separated list of one or more commands:

```
SET [GLOBAL|SESSION] optimizer_switch='command[,command]...';
```

Each *`command`* value should have one of the forms shown in the following table.

| Command Syntax         | Meaning                                         |
| :--------------------- | :---------------------------------------------- |
| `default`              | Reset every optimization to its default value   |
| `*`opt_name`*=default` | Set the named optimization to its default value |
| `*`opt_name`*=off`     | Disable the named optimization                  |
| `*`opt_name`*=on`      | Enable the named optimization                   |

The order of the commands in the value does not matter, although the `default` command is executed first if present. Setting an *`opt_name`* flag to `default` sets it to whichever of `on` or `off` is its default value. Specifying any given *`opt_name`* more than once in the value is not permitted and causes an error. Any errors in the value cause the assignment to fail with an error, leaving the value of [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) unchanged.

The following list describes the permissible *`opt_name`* flag names, grouped by optimization strategy:

- Batched Key Access Flags

  - `batched_key_access` (default `off`)

    Controls use of BKA join algorithm.

  For `batched_key_access` to have any effect when set to `on`, the `mrr` flag must also be `on`. Currently, the cost estimation for MRR is too pessimistic. Hence, it is also necessary for `mrr_cost_based` to be `off` for BKA to be used.

  For more information, see [Section 8.2.1.11, “Block Nested-Loop and Batched Key Access Joins”](optimization.html#bnl-bka-optimization).

- Block Nested-Loop Flags

  - `block_nested_loop` (default `on`)

    Controls use of BNL join algorithm.

  For more information, see [Section 8.2.1.11, “Block Nested-Loop and Batched Key Access Joins”](optimization.html#bnl-bka-optimization).

- Condition Filtering Flags

  - `condition_fanout_filter` (default `on`)

    Controls use of condition filtering.

  For more information, see [Section 8.2.1.12, “Condition Filtering”](optimization.html#condition-filtering).

- Derived Table Merging Flags

  - `derived_merge` (default `on`)

    Controls merging of derived tables and views into outer query block.

  The `derived_merge` flag controls whether the optimizer attempts to merge derived tables and view references into the outer query block, assuming that no other rule prevents merging; for example, an `ALGORITHM` directive for a view takes precedence over the `derived_merge` setting. By default, the flag is `on` to enable merging.

  For more information, see [Section 8.2.2.4, “Optimizing Derived Tables and View References with Merging or Materialization”](optimization.html#derived-table-optimization).

- Engine Condition Pushdown Flags

  - `engine_condition_pushdown` (default `on`)

    Controls engine condition pushdown.

  For more information, see [Section 8.2.1.4, “Engine Condition Pushdown Optimization”](optimization.html#condition-pushdown-optimization).

- Index Condition Pushdown Flags

  - `index_condition_pushdown` (default `on`)

    Controls index condition pushdown.

  For more information, see [Section 8.2.1.5, “Index Condition Pushdown Optimization”](optimization.html#index-condition-pushdown-optimization).

- Index Extensions Flags

  - `use_index_extensions` (default `on`)

    Controls use of index extensions.

  For more information, see [Section 8.3.9, “Use of Index Extensions”](optimization.html#index-extensions).

- Index Merge Flags

  - `index_merge` (default `on`)

    Controls all Index Merge optimizations.

  - `index_merge_intersection` (default `on`)

    Controls the Index Merge Intersection Access optimization.

  - `index_merge_sort_union` (default `on`)

    Controls the Index Merge Sort-Union Access optimization.

  - `index_merge_union` (default `on`)

    Controls the Index Merge Union Access optimization.

  For more information, see [Section 8.2.1.3, “Index Merge Optimization”](optimization.html#index-merge-optimization).

- Multi-Range Read Flags

  - `mrr` (default `on`)

    Controls the Multi-Range Read strategy.

  - `mrr_cost_based` (default `on`)

    Controls use of cost-based MRR if `mrr=on`.

  For more information, see [Section 8.2.1.10, “Multi-Range Read Optimization”](optimization.html#mrr-optimization).

- Semijoin Flags

  - `semijoin` (default `on`)

    Controls all semijoin strategies.

  - `duplicateweedout` (default `on`)

    Controls the semijoin Duplicate Weedout strategy.

  - `firstmatch` (default `on`)

    Controls the semijoin FirstMatch strategy.

  - `loosescan` (default `on`)

    Controls the semijoin LooseScan strategy (not to be confused with Loose Index Scan for `GROUP BY`).

  The `semijoin`, `firstmatch`, `loosescan`, and `duplicateweedout` flags enable control over semijoin strategies. The `semijoin` flag controls whether semijoins are used. If it is set to `on`, the `firstmatch` and `loosescan` flags enable finer control over the permitted semijoin strategies.

  If the `duplicateweedout` semijoin strategy is disabled, it is not used unless all other applicable strategies are also disabled.

  If `semijoin` and `materialization` are both `on`, semijoins also use materialization where applicable. These flags are `on` by default.

  For more information, see [Section 8.2.2.1, “Optimizing Subqueries, Derived Tables, and View References with Semijoin Transformations”](optimization.html#semijoins).

- Subquery Materialization Flags

  - `materialization` (default `on`)

    Controls materialization (including semijoin materialization).

  - `subquery_materialization_cost_based` (default `on`)

    Use cost-based materialization choice.

  The `materialization` flag controls whether subquery materialization is used. If `semijoin` and `materialization` are both `on`, semijoins also use materialization where applicable. These flags are `on` by default.

  The `subquery_materialization_cost_based` flag enables control over the choice between subquery materialization and `IN`-to-`EXISTS` subquery transformation. If the flag is `on` (the default), the optimizer performs a cost-based choice between subquery materialization and `IN`-to-`EXISTS` subquery transformation if either method could be used. If the flag is `off`, the optimizer chooses subquery materialization over `IN`-to-`EXISTS` subquery transformation.

  For more information, see [Section 8.2.2, “Optimizing Subqueries, Derived Tables, and View References”](optimization.html#subquery-optimization).

When you assign a value to [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch), flags that are not mentioned keep their current values. This makes it possible to enable or disable specific optimizer behaviors in a single statement without affecting other behaviors. The statement does not depend on what other optimizer flags exist and what their values are. Suppose that all Index Merge optimizations are enabled:

```
mysql> SELECT @@optimizer_switch\G
*************************** 1. row ***************************
@@optimizer_switch: index_merge=on,index_merge_union=on,
                    index_merge_sort_union=on,
                    index_merge_intersection=on,
                    engine_condition_pushdown=on,
                    index_condition_pushdown=on,
                    mrr=on,mrr_cost_based=on,
                    block_nested_loop=on,batched_key_access=off,
                    materialization=on,semijoin=on,loosescan=on,
                    firstmatch=on,
                    subquery_materialization_cost_based=on,
                    use_index_extensions=on,
                    condition_fanout_filter=on
```

If the server is using the Index Merge Union or Index Merge Sort-Union access methods for certain queries and you want to check whether the optimizer will perform better without them, set the variable value like this:

```
mysql> SET optimizer_switch='index_merge_union=off,index_merge_sort_union=off';

mysql> SELECT @@optimizer_switch\G
*************************** 1. row ***************************
@@optimizer_switch: index_merge=on,index_merge_union=off,
                    index_merge_sort_union=off,
                    index_merge_intersection=on,
                    engine_condition_pushdown=on,
                    index_condition_pushdown=on,
                    mrr=on,mrr_cost_based=on,
                    block_nested_loop=on,batched_key_access=off,
                    materialization=on,semijoin=on,loosescan=on,
                    firstmatch=on,
                    subquery_materialization_cost_based=on,
                    use_index_extensions=on,
                    condition_fanout_filter=on
```

### 8.9.3 Optimizer Hints



One means of control over optimizer strategies is to set the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable (see [Section 8.9.2, “Switchable Optimizations”](optimization.html#switchable-optimizations)). Changes to this variable affect execution of all subsequent queries; to affect one query differently from another, it is necessary to change [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) before each one.

another way to control the optimizer is by using optimizer hints, which can be specified within individual statements. Because optimizer hints apply on a per-statement basis, they provide finer control over statement execution plans than can be achieved using [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch). For example, you can enable an optimization for one table in a statement and disable the optimization for a different table. Hints within a statement take precedence over [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) flags.

Examples:

```
SELECT /*+ NO_RANGE_OPTIMIZATION(t3 PRIMARY, f2_idx) */ f1
  FROM t3 WHERE f1 > 30 AND f1 < 33;
SELECT /*+ BKA(t1) NO_BKA(t2) */ * FROM t1 INNER JOIN t2 WHERE ...;
SELECT /*+ NO_ICP(t1, t2) */ * FROM t1 INNER JOIN t2 WHERE ...;
SELECT /*+ SEMIJOIN(FIRSTMATCH, LOOSESCAN) */ * FROM t1 ...;
EXPLAIN SELECT /*+ NO_ICP(t1) */ * FROM t1 WHERE ...;
```

Note

The [**mysql**](programs.html#mysql) client by default strips comments from SQL statements sent to the server (including optimizer hints) until MySQL 5.7.7, when it was changed to pass optimizer hints to the server. To ensure that optimizer hints are not stripped if you are using an older version of the [**mysql**](programs.html#mysql) client with a version of the server that understands optimizer hints, invoke [**mysql**](programs.html#mysql) with the [`--comments`](programs.html#option_mysql_comments) option.

Optimizer hints, described here, differ from index hints, described in [Section 8.9.4, “Index Hints”](optimization.html#index-hints). Optimizer and index hints may be used separately or together.

- [Optimizer Hint Overview](optimization.html#optimizer-hints-overview)
- [Optimizer Hint Syntax](optimization.html#optimizer-hints-syntax)
- [Table-Level Optimizer Hints](optimization.html#optimizer-hints-table-level)
- [Index-Level Optimizer Hints](optimization.html#optimizer-hints-index-level)
- [Subquery Optimizer Hints](optimization.html#optimizer-hints-subquery)
- [Statement Execution Time Optimizer Hints](optimization.html#optimizer-hints-execution-time)
- [Optimizer Hints for Naming Query Blocks](optimization.html#optimizer-hints-query-block-naming)

#### Optimizer Hint Overview

Optimizer hints apply at different scope levels:

- Global: The hint affects the entire statement
- Query block: The hint affects a particular query block within a statement
- Table-level: The hint affects a particular table within a query block
- Index-level: The hint affects a particular index within a table

The following table summarizes the available optimizer hints, the optimizer strategies they affect, and the scope or scopes at which they apply. More details are given later.



**Table 8.2 Optimizer Hints Available**

| Hint Name                                                    | Description                                                  | Applicable Scopes  |
| :----------------------------------------------------------- | :----------------------------------------------------------- | :----------------- |
| [`BKA`](optimization.html#optimizer-hints-table-level), [`NO_BKA`](optimization.html#optimizer-hints-table-level) | Affects Batched Key Access join processing                   | Query block, table |
| [`BNL`](optimization.html#optimizer-hints-table-level), [`NO_BNL`](optimization.html#optimizer-hints-table-level) | Affects Block Nested-Loop join processing                    | Query block, table |
| [`MAX_EXECUTION_TIME`](optimization.html#optimizer-hints-execution-time) | Limits statement execution time                              | Global             |
| [`MRR`](optimization.html#optimizer-hints-index-level), [`NO_MRR`](optimization.html#optimizer-hints-index-level) | Affects Multi-Range Read optimization                        | Table, index       |
| [`NO_ICP`](optimization.html#optimizer-hints-index-level)    | Affects Index Condition Pushdown optimization                | Table, index       |
| [`NO_RANGE_OPTIMIZATION`](optimization.html#optimizer-hints-index-level) | Affects range optimization                                   | Table, index       |
| [`QB_NAME`](optimization.html#optimizer-hints-query-block-naming) | Assigns name to query block                                  | Query block        |
| [`SEMIJOIN`](optimization.html#optimizer-hints-subquery), [`NO_SEMIJOIN`](optimization.html#optimizer-hints-subquery) | Affects semijoin strategies                                  | Query block        |
| [`SUBQUERY`](optimization.html#optimizer-hints-subquery)     | Affects materialization, `IN`-to-`EXISTS` subquery stratgies | Query block        |



Disabling an optimization prevents the optimizer from using it. Enabling an optimization means the optimizer is free to use the strategy if it applies to statement execution, not that the optimizer necessarily will use it.

#### Optimizer Hint Syntax

MySQL supports comments in SQL statements as described in [Section 9.6, “Comment Syntax”](language-structure.html#comments). Optimizer hints must be specified within `/*+ ... */` comments. That is, optimizer hints use a variant of `/* ... */` C-style comment syntax, with a `+` character following the `/*` comment opening sequence. Examples:

```
/*+ BKA(t1) */
/*+ BNL(t1, t2) */
/*+ NO_RANGE_OPTIMIZATION(t4 PRIMARY) */
/*+ QB_NAME(qb2) */
```

Whitespace is permitted after the `+` character.

The parser recognizes optimizer hint comments after the initial keyword of [`SELECT`](sql-statements.html#select), [`UPDATE`](sql-statements.html#update), [`INSERT`](sql-statements.html#insert), [`REPLACE`](sql-statements.html#replace), and [`DELETE`](sql-statements.html#delete) statements. Hints are permitted in these contexts:

- At the beginning of query and data change statements:

  ```
  SELECT /*+ ... */ ...
  INSERT /*+ ... */ ...
  REPLACE /*+ ... */ ...
  UPDATE /*+ ... */ ...
  DELETE /*+ ... */ ...
  ```

- At the beginning of query blocks:

  ```
  (SELECT /*+ ... */ ... )
  (SELECT ... ) UNION (SELECT /*+ ... */ ... )
  (SELECT /*+ ... */ ... ) UNION (SELECT /*+ ... */ ... )
  UPDATE ... WHERE x IN (SELECT /*+ ... */ ...)
  INSERT ... SELECT /*+ ... */ ...
  ```

- In hintable statements prefaced by [`EXPLAIN`](sql-statements.html#explain). For example:

  ```
  EXPLAIN SELECT /*+ ... */ ...
  EXPLAIN UPDATE ... WHERE x IN (SELECT /*+ ... */ ...)
  ```

  The implication is that you can use [`EXPLAIN`](sql-statements.html#explain) to see how optimizer hints affect execution plans. Use [`SHOW WARNINGS`](sql-statements.html#show-warnings) immediately after [`EXPLAIN`](sql-statements.html#explain) to see how hints are used. The extended `EXPLAIN` output displayed by a following [`SHOW WARNINGS`](sql-statements.html#show-warnings) indicates which hints were used. Ignored hints are not displayed.

A hint comment may contain multiple hints, but a query block cannot contain multiple hint comments. This is valid:

```
SELECT /*+ BNL(t1) BKA(t2) */ ...
```

But this is invalid:

```
SELECT /*+ BNL(t1) */ /* BKA(t2) */ ...
```

When a hint comment contains multiple hints, the possibility of duplicates and conflicts exists. The following general guidelines apply. For specific hint types, additional rules may apply, as indicated in the hint descriptions.

- Duplicate hints: For a hint such as `/*+ MRR(idx1) MRR(idx1) */`, MySQL uses the first hint and issues a warning about the duplicate hint.
- Conflicting hints: For a hint such as `/*+ MRR(idx1) NO_MRR(idx1) */`, MySQL uses the first hint and issues a warning about the second conflicting hint.

Query block names are identifiers and follow the usual rules about what names are valid and how to quote them (see [Section 9.2, “Schema Object Names”](language-structure.html#identifiers)).

Hint names, query block names, and strategy names are not case-sensitive. References to table and index names follow the usual identifier case sensitivity rules (see [Section 9.2.3, “Identifier Case Sensitivity”](language-structure.html#identifier-case-sensitivity)).

#### Table-Level Optimizer Hints

Table-level hints affect use of the Block Nested-Loop (BNL) and Batched Key Access (BKA) join-processing algorithms (see [Section 8.2.1.11, “Block Nested-Loop and Batched Key Access Joins”](optimization.html#bnl-bka-optimization)). These hint types apply to specific tables, or all tables in a query block.

Syntax of table-level hints:

```
hint_name([@query_block_name] [tbl_name [, tbl_name] ...])
hint_name([tbl_name@query_block_name [, tbl_name@query_block_name] ...])
```

The syntax refers to these terms:

- *`hint_name`*: These hint names are permitted:

  - [`BKA`](optimization.html#optimizer-hints-table-level), [`NO_BKA`](optimization.html#optimizer-hints-table-level): Enable or disable BKA for the specified tables.
  - [`BNL`](optimization.html#optimizer-hints-table-level), [`NO_BNL`](optimization.html#optimizer-hints-table-level): Enable or disable BNL for the specified tables.

  Note

  To use a BNL or BKA hint to enable join buffering for any inner table of an outer join, join buffering must be enabled for all inner tables of the outer join.

- *`tbl_name`*: The name of a table used in the statement. The hint applies to all tables that it names. If the hint names no tables, it applies to all tables of the query block in which it occurs.

  If a table has an alias, hints must refer to the alias, not the table name.

  Table names in hints cannot be qualified with schema names.

- *`query_block_name`*: The query block to which the hint applies. If the hint includes no leading `@*`query_block_name`*`, the hint applies to the query block in which it occurs. For `*`tbl_name`*@*`query_block_name`*` syntax, the hint applies to the named table in the named query block. To assign a name to a query block, see [Optimizer Hints for Naming Query Blocks](optimization.html#optimizer-hints-query-block-naming).

Examples:

```
SELECT /*+ NO_BKA(t1, t2) */ t1.* FROM t1 INNER JOIN t2 INNER JOIN t3;
SELECT /*+ NO_BNL() BKA(t1) */ t1.* FROM t1 INNER JOIN t2 INNER JOIN t3;
```

A table-level hint applies to tables that receive records from previous tables, not sender tables. Consider this statement:

```
SELECT /*+ BNL(t2) */ FROM t1, t2;
```

If the optimizer chooses to process `t1` first, it applies a Block Nested-Loop join to `t2` by buffering the rows from `t1` before starting to read from `t2`. If the optimizer instead chooses to process `t2` first, the hint has no effect because `t2` is a sender table.

#### Index-Level Optimizer Hints

Index-level hints affect which index-processing strategies the optimizer uses for particular tables or indexes. These hint types affect use of Index Condition Pushdown (ICP), Multi-Range Read (MRR), and range optimizations (see [Section 8.2.1, “Optimizing SELECT Statements”](optimization.html#select-optimization)).

Syntax of index-level hints:

```
hint_name([@query_block_name] tbl_name [index_name [, index_name] ...])
hint_name(tbl_name@query_block_name [index_name [, index_name] ...])
```

The syntax refers to these terms:

- *`hint_name`*: These hint names are permitted:

  - [`MRR`](optimization.html#optimizer-hints-index-level), [`NO_MRR`](optimization.html#optimizer-hints-index-level): Enable or disable MRR for the specified table or indexes. MRR hints apply only to `InnoDB` and `MyISAM` tables.

  - [`NO_ICP`](optimization.html#optimizer-hints-index-level): Disable ICP for the specified table or indexes. By default, ICP is a candidate optimization strategy, so there is no hint for enabling it.

  - [`NO_RANGE_OPTIMIZATION`](optimization.html#optimizer-hints-index-level): Disable index range access for the specified table or indexes. This hint also disables Index Merge and Loose Index Scan for the table or indexes. By default, range access is a candidate optimization strategy, so there is no hint for enabling it.

    This hint may be useful when the number of ranges may be high and range optimization would require many resources.

- *`tbl_name`*: The table to which the hint applies.

- *`index_name`*: The name of an index in the named table. The hint applies to all indexes that it names. If the hint names no indexes, it applies to all indexes in the table.

  To refer to a primary key, use the name `PRIMARY`. To see the index names for a table, use [`SHOW INDEX`](sql-statements.html#show-index).

- *`query_block_name`*: The query block to which the hint applies. If the hint includes no leading `@*`query_block_name`*`, the hint applies to the query block in which it occurs. For `*`tbl_name`*@*`query_block_name`*` syntax, the hint applies to the named table in the named query block. To assign a name to a query block, see [Optimizer Hints for Naming Query Blocks](optimization.html#optimizer-hints-query-block-naming).

Examples:

```
SELECT /*+ MRR(t1) */ * FROM t1 WHERE f2 <= 3 AND 3 <= f3;
SELECT /*+ NO_RANGE_OPTIMIZATION(t3 PRIMARY, f2_idx) */ f1
  FROM t3 WHERE f1 > 30 AND f1 < 33;
INSERT INTO t3(f1, f2, f3)
  (SELECT /*+ NO_ICP(t2) */ t2.f1, t2.f2, t2.f3 FROM t1,t2
   WHERE t1.f1=t2.f1 AND t2.f2 BETWEEN t1.f1
   AND t1.f2 AND t2.f2 + 1 >= t1.f1 + 1);
```

#### Subquery Optimizer Hints

Subquery hints affect whether to use semijoin transformations and which semijoin strategies to permit, and, when semijoins are not used, whether to use subquery materialization or `IN`-to-`EXISTS` transformations. For more information about these optimizations, see [Section 8.2.2, “Optimizing Subqueries, Derived Tables, and View References”](optimization.html#subquery-optimization).

Syntax of hints that affect semijoin strategies:

```
hint_name([@query_block_name] [strategy [, strategy] ...])
```

The syntax refers to these terms:

- *`hint_name`*: These hint names are permitted:

  - [`SEMIJOIN`](optimization.html#optimizer-hints-subquery), [`NO_SEMIJOIN`](optimization.html#optimizer-hints-subquery): Enable or disable the named semijoin strategies.

- *`strategy`*: A semijoin strategy to be enabled or disabled. These strategy names are permitted: `DUPSWEEDOUT`, `FIRSTMATCH`, `LOOSESCAN`, `MATERIALIZATION`.

  For [`SEMIJOIN`](optimization.html#optimizer-hints-subquery) hints, if no strategies are named, semijoin is used if possible based on the strategies enabled according to the [`optimizer_switch`](server-administration.html#sysvar_optimizer_switch) system variable. If strategies are named but inapplicable for the statement, `DUPSWEEDOUT` is used.

  For [`NO_SEMIJOIN`](optimization.html#optimizer-hints-subquery) hints, if no strategies are named, semijoin is not used. If strategies are named that rule out all applicable strategies for the statement, `DUPSWEEDOUT` is used.

If one subquery is nested within another and both are merged into a semijoin of an outer query, any specification of semijoin strategies for the innermost query are ignored. [`SEMIJOIN`](optimization.html#optimizer-hints-subquery) and [`NO_SEMIJOIN`](optimization.html#optimizer-hints-subquery) hints can still be used to enable or disable semijoin transformations for such nested subqueries.

If `DUPSWEEDOUT` is disabled, on occasion the optimizer may generate a query plan that is far from optimal. This occurs due to heuristic pruning during greedy search, which can be avoided by setting [`optimizer_prune_level=0`](server-administration.html#sysvar_optimizer_prune_level).

Examples:

```
SELECT /*+ NO_SEMIJOIN(@subq1 FIRSTMATCH, LOOSESCAN) */ * FROM t2
  WHERE t2.a IN (SELECT /*+ QB_NAME(subq1) */ a FROM t3);
SELECT /*+ SEMIJOIN(@subq1 MATERIALIZATION, DUPSWEEDOUT) */ * FROM t2
  WHERE t2.a IN (SELECT /*+ QB_NAME(subq1) */ a FROM t3);
```

Syntax of hints that affect whether to use subquery materialization or `IN`-to-`EXISTS` transformations:

```
SUBQUERY([@query_block_name] strategy)
```

The hint name is always [`SUBQUERY`](optimization.html#optimizer-hints-subquery).

For [`SUBQUERY`](optimization.html#optimizer-hints-subquery) hints, these *`strategy`* values are permitted: `INTOEXISTS`, `MATERIALIZATION`.

Examples:

```
SELECT id, a IN (SELECT /*+ SUBQUERY(MATERIALIZATION) */ a FROM t1) FROM t2;
SELECT * FROM t2 WHERE t2.a IN (SELECT /*+ SUBQUERY(INTOEXISTS) */ a FROM t1);
```

For semijoin and [`SUBQUERY`](optimization.html#optimizer-hints-subquery) hints, a leading `@*`query_block_name`*` specifies the query block to which the hint applies. If the hint includes no leading `@*`query_block_name`*`, the hint applies to the query block in which it occurs. To assign a name to a query block, see [Optimizer Hints for Naming Query Blocks](optimization.html#optimizer-hints-query-block-naming).

If a hint comment contains multiple subquery hints, the first is used. If there are other following hints of that type, they produce a warning. Following hints of other types are silently ignored.

#### Statement Execution Time Optimizer Hints

The [`MAX_EXECUTION_TIME`](optimization.html#optimizer-hints-execution-time) hint is permitted only for [`SELECT`](sql-statements.html#select) statements. It places a limit *`N`* (a timeout value in milliseconds) on how long a statement is permitted to execute before the server terminates it:

```
MAX_EXECUTION_TIME(N)
```

Example with a timeout of 1 second (1000 milliseconds):

```
SELECT /*+ MAX_EXECUTION_TIME(1000) */ * FROM t1 INNER JOIN t2 WHERE ...
```

The [`MAX_EXECUTION_TIME(*`N`*)`](optimization.html#optimizer-hints-execution-time) hint sets a statement execution timeout of *`N`* milliseconds. If this option is absent or *`N`* is 0, the statement timeout established by the [`max_execution_time`](server-administration.html#sysvar_max_execution_time) system variable applies.

The [`MAX_EXECUTION_TIME`](optimization.html#optimizer-hints-execution-time) hint is applicable as follows:

- For statements with multiple `SELECT` keywords, such as unions or statements with subqueries, [`MAX_EXECUTION_TIME`](optimization.html#optimizer-hints-execution-time) applies to the entire statement and must appear after the first [`SELECT`](sql-statements.html#select).
- It applies to read-only [`SELECT`](sql-statements.html#select) statements. Statements that are not read only are those that invoke a stored function that modifies data as a side effect.
- It does not apply to [`SELECT`](sql-statements.html#select) statements in stored programs and is ignored.

#### Optimizer Hints for Naming Query Blocks

Table-level, index-level, and subquery optimizer hints permit specific query blocks to be named as part of their argument syntax. To create these names, use the [`QB_NAME`](optimization.html#optimizer-hints-query-block-naming) hint, which assigns a name to the query block in which it occurs:

```
QB_NAME(name)
```

[`QB_NAME`](optimization.html#optimizer-hints-query-block-naming) hints can be used to make explicit in a clear way which query blocks other hints apply to. They also permit all non-query block name hints to be specified within a single hint comment for easier understanding of complex statements. Consider the following statement:

```
SELECT ...
  FROM (SELECT ...
  FROM (SELECT ... FROM ...)) ...
```

[`QB_NAME`](optimization.html#optimizer-hints-query-block-naming) hints assign names to query blocks in the statement:

```
SELECT /*+ QB_NAME(qb1) */ ...
  FROM (SELECT /*+ QB_NAME(qb2) */ ...
  FROM (SELECT /*+ QB_NAME(qb3) */ ... FROM ...)) ...
```

Then other hints can use those names to refer to the appropriate query blocks:

```
SELECT /*+ QB_NAME(qb1) MRR(@qb1 t1) BKA(@qb2) NO_MRR(@qb3t1 idx1, id2) */ ...
  FROM (SELECT /*+ QB_NAME(qb2) */ ...
  FROM (SELECT /*+ QB_NAME(qb3) */ ... FROM ...)) ...
```

The resulting effect is as follows:

- [`MRR(@qb1 t1)`](optimization.html#optimizer-hints-index-level) applies to table `t1` in query block `qb1`.
- [`BKA(@qb2)`](optimization.html#optimizer-hints-table-level) applies to query block `qb2`.
- [`NO_MRR(@qb3 t1 idx1, id2)`](optimization.html#optimizer-hints-index-level) applies to indexes `idx1` and `idx2` in table `t1` in query block `qb3`.

Query block names are identifiers and follow the usual rules about what names are valid and how to quote them (see [Section 9.2, “Schema Object Names”](language-structure.html#identifiers)). For example, a query block name that contains spaces must be quoted, which can be done using backticks:

```
SELECT /*+ BKA(@`my hint name`) */ ...
  FROM (SELECT /*+ QB_NAME(`my hint name`) */ ...) ...
```

If the [`ANSI_QUOTES`](server-administration.html#sqlmode_ansi_quotes) SQL mode is enabled, it is also possible to quote query block names within double quotation marks:

```
SELECT /*+ BKA(@"my hint name") */ ...
  FROM (SELECT /*+ QB_NAME("my hint name") */ ...) ...
```

### 8.9.4 Index Hints



Index hints give the optimizer information about how to choose indexes during query processing. Index hints, described here, differ from optimizer hints, described in [Section 8.9.3, “Optimizer Hints”](optimization.html#optimizer-hints). Index and optimizer hints may be used separately or together.

Index hints apply only to [`SELECT`](sql-statements.html#select) and [`UPDATE`](sql-statements.html#update) statements.

Index hints are specified following a table name. (For the general syntax for specifying tables in a [`SELECT`](sql-statements.html#select) statement, see [Section 13.2.9.2, “JOIN Clause”](sql-statements.html#join).) The syntax for referring to an individual table, including index hints, looks like this:

```
tbl_name [[AS] alias] [index_hint_list]

index_hint_list:
    index_hint [index_hint] ...

index_hint:
    USE {INDEX|KEY}
      [FOR {JOIN|ORDER BY|GROUP BY}] ([index_list])
  | {IGNORE|FORCE} {INDEX|KEY}
      [FOR {JOIN|ORDER BY|GROUP BY}] (index_list)

index_list:
    index_name [, index_name] ...
```

The `USE INDEX (*`index_list`*)` hint tells MySQL to use only one of the named indexes to find rows in the table. The alternative syntax `IGNORE INDEX (*`index_list`*)` tells MySQL to not use some particular index or indexes. These hints are useful if [`EXPLAIN`](sql-statements.html#explain) shows that MySQL is using the wrong index from the list of possible indexes.

The `FORCE INDEX` hint acts like `USE INDEX (*`index_list`*)`, with the addition that a table scan is assumed to be *very* expensive. In other words, a table scan is used only if there is no way to use one of the named indexes to find rows in the table.

Each hint requires index names, not column names. To refer to a primary key, use the name `PRIMARY`. To see the index names for a table, use the [`SHOW INDEX`](sql-statements.html#show-index) statement or the [`INFORMATION_SCHEMA.STATISTICS`](information-schema.html#statistics-table) table.

An *`index_name`* value need not be a full index name. It can be an unambiguous prefix of an index name. If a prefix is ambiguous, an error occurs.

Examples:

```
SELECT * FROM table1 USE INDEX (col1_index,col2_index)
  WHERE col1=1 AND col2=2 AND col3=3;

SELECT * FROM table1 IGNORE INDEX (col3_index)
  WHERE col1=1 AND col2=2 AND col3=3;
```

The syntax for index hints has the following characteristics:

- It is syntactically valid to omit *`index_list`* for `USE INDEX`, which means “use no indexes.” Omitting *`index_list`* for `FORCE INDEX` or `IGNORE INDEX` is a syntax error.

- You can specify the scope of an index hint by adding a `FOR` clause to the hint. This provides more fine-grained control over optimizer selection of an execution plan for various phases of query processing. To affect only the indexes used when MySQL decides how to find rows in the table and how to process joins, use `FOR JOIN`. To influence index usage for sorting or grouping rows, use `FOR ORDER BY` or `FOR GROUP BY`.

- You can specify multiple index hints:

  ```
  SELECT * FROM t1 USE INDEX (i1) IGNORE INDEX FOR ORDER BY (i2) ORDER BY a;
  ```

  It is not an error to name the same index in several hints (even within the same hint):

  ```
  SELECT * FROM t1 USE INDEX (i1) USE INDEX (i1,i1);
  ```

  However, it is an error to mix `USE INDEX` and `FORCE INDEX` for the same table:

  ```
  SELECT * FROM t1 USE INDEX FOR JOIN (i1) FORCE INDEX FOR JOIN (i2);
  ```

If an index hint includes no `FOR` clause, the scope of the hint is to apply to all parts of the statement. For example, this hint:

```
IGNORE INDEX (i1)
```

is equivalent to this combination of hints:

```
IGNORE INDEX FOR JOIN (i1)
IGNORE INDEX FOR ORDER BY (i1)
IGNORE INDEX FOR GROUP BY (i1)
```

In MySQL 5.0, hint scope with no `FOR` clause was to apply only to row retrieval. To cause the server to use this older behavior when no `FOR` clause is present, enable the [`old`](server-administration.html#sysvar_old) system variable at server startup. Take care about enabling this variable in a replication setup. With statement-based binary logging, having different modes for the master and slaves might lead to replication errors.

When index hints are processed, they are collected in a single list by type (`USE`, `FORCE`, `IGNORE`) and by scope (`FOR JOIN`, `FOR ORDER BY`, `FOR GROUP BY`). For example:

```
SELECT * FROM t1
  USE INDEX () IGNORE INDEX (i2) USE INDEX (i1) USE INDEX (i2);
```

is equivalent to:

```
SELECT * FROM t1
   USE INDEX (i1,i2) IGNORE INDEX (i2);
```

The index hints then are applied for each scope in the following order:

1. `{USE|FORCE} INDEX` is applied if present. (If not, the optimizer-determined set of indexes is used.)

2. `IGNORE INDEX` is applied over the result of the previous step. For example, the following two queries are equivalent:

   ```
   SELECT * FROM t1 USE INDEX (i1) IGNORE INDEX (i2) USE INDEX (i2);
   
   SELECT * FROM t1 USE INDEX (i1);
   ```

For `FULLTEXT` searches, index hints work as follows:

- For natural language mode searches, index hints are silently ignored. For example, `IGNORE INDEX(i1)` is ignored with no warning and the index is still used.

- For boolean mode searches, index hints with `FOR ORDER BY` or `FOR GROUP BY` are silently ignored. Index hints with `FOR JOIN` or no `FOR` modifier are honored. In contrast to how hints apply for non-`FULLTEXT` searches, the hint is used for all phases of query execution (finding rows and retrieval, grouping, and ordering). This is true even if the hint is given for a non-`FULLTEXT` index.

  For example, the following two queries are equivalent:

  ```
  SELECT * FROM t
    USE INDEX (index1)
    IGNORE INDEX (index1) FOR ORDER BY
    IGNORE INDEX (index1) FOR GROUP BY
    WHERE ... IN BOOLEAN MODE ... ;
  
  SELECT * FROM t
    USE INDEX (index1)
    WHERE ... IN BOOLEAN MODE ... ;
  ```

### 8.9.5 The Optimizer Cost Model



To generate execution plans, the optimizer uses a cost model that is based on estimates of the cost of various operations that occur during query execution. The optimizer has a set of compiled-in default “cost constants” available to it to make decisions regarding execution plans.

The optimizer also has a database of cost estimates to use during execution plan construction. These estimates are stored in the `server_cost` and `engine_cost` tables in the `mysql` system database and are configurable at any time. The intent of these tables is to make it possible to easily adjust the cost estimates that the optimizer uses when it attempts to arrive at query execution plans.

- [Cost Model General Operation](optimization.html#cost-model-operation)
- [The Cost Model Database](optimization.html#cost-model-database)
- [Making Changes to the Cost Model Database](optimization.html#cost-model-database-modifications)

#### Cost Model General Operation

The configurable optimizer cost model works like this:

- The server reads the cost model tables into memory at startup and uses the in-memory values at runtime. Any non-`NULL` cost estimate specified in the tables takes precedence over the corresponding compiled-in default cost constant. Any `NULL` estimate indicates to the optimizer to use the compiled-in default.
- At runtime, the server may reread the cost tables. This occurs when a storage engine is dynamically loaded or when a [`FLUSH OPTIMIZER_COSTS`](sql-statements.html#flush-optimizer-costs) statement is executed.
- Cost tables enable server administrators to easily adjust cost estimates by changing entries in the tables. It is also easy to revert to a default by setting an entry's cost to `NULL`. The optimizer uses the in-memory cost values, so changes to the tables should be followed by [`FLUSH OPTIMIZER_COSTS`](sql-statements.html#flush-optimizer-costs) to take effect.
- The in-memory cost estimates that are current when a client session begins apply throughout that session until it ends. In particular, if the server rereads the cost tables, any changed estimates apply only to subsequently started sessions. Existing sessions are unaffected.
- Cost tables are specific to a given server instance. The server does not replicate cost table changes to replication slaves.

#### The Cost Model Database

The optimizer cost model database consists of two tables in the `mysql` system database that contain cost estimate information for operations that occur during query execution:

- `server_cost`: Optimizer cost estimates for general server operations
- `engine_cost`: Optimizer cost estimates for operations specific to particular storage engines

The `server_cost` table contains these columns:

- `cost_name`

  The name of a cost estimate used in the cost model. The name is not case-sensitive. If the server does not recognize the cost name when it reads this table, it writes a warning to the error log.

- `cost_value`

  The cost estimate value. If the value is non-`NULL`, the server uses it as the cost. Otherwise, it uses the default estimate (the compiled-in value). DBAs can change a cost estimate by updating this column. If the server finds that the cost value is invalid (nonpositive) when it reads this table, it writes a warning to the error log.

  To override a default cost estimate (for an entry that specifies `NULL`), set the cost to a non-`NULL` value. To revert to the default, set the value to `NULL`. Then execute [`FLUSH OPTIMIZER_COSTS`](sql-statements.html#flush-optimizer-costs) to tell the server to reread the cost tables.

- `last_update`

  The time of the last row update.

- `comment`

  A descriptive comment associated with the cost estimate. DBAs can use this column to provide information about why a cost estimate row stores a particular value.

The primary key for the `server_cost` table is the `cost_name` column, so it is not possible to create multiple entries for any cost estimate.

The server recognizes these `cost_name` values for the `server_cost` table:

- `disk_temptable_create_cost` (default 40.0), `disk_temptable_row_cost` (default 1.0)

  The cost estimates for internally created temporary tables stored in a disk-based storage engine (either `InnoDB` or `MyISAM`). Increasing these values increases the cost estimate of using internal temporary tables and makes the optimizer prefer query plans with less use of them. For information about such tables, see [Section 8.4.4, “Internal Temporary Table Use in MySQL”](optimization.html#internal-temporary-tables).

  The larger default values for these disk parameters compared to the default values for the corresponding memory parameters (`memory_temptable_create_cost`, `memory_temptable_row_cost`) reflects the greater cost of processing disk-based tables.

- `key_compare_cost` (default 0.1)

  

  

  The cost of comparing record keys. Increasing this value causes a query plan that compares many keys to become more expensive. For example, a query plan that performs a `filesort` becomes relatively more expensive compared to a query plan that avoids sorting by using an index.

- `memory_temptable_create_cost` (default 2.0), `memory_temptable_row_cost` (default 0.2)

  The cost estimates for internally created temporary tables stored in the `MEMORY` storage engine. Increasing these values increases the cost estimate of using internal temporary tables and makes the optimizer prefer query plans with less use of them. For information about such tables, see [Section 8.4.4, “Internal Temporary Table Use in MySQL”](optimization.html#internal-temporary-tables).

  The smaller default values for these memory parameters compared to the default values for the corresponding disk parameters (`disk_temptable_create_cost`, `disk_temptable_row_cost`) reflects the lesser cost of processing memory-based tables.

- `row_evaluate_cost` (default 0.2)

  The cost of evaluating record conditions. Increasing this value causes a query plan that examines many rows to become more expensive compared to a query plan that examines fewer rows. For example, a table scan becomes relatively more expensive compared to a range scan that reads fewer rows.

The `engine_cost` table contains these columns:

- `engine_name`

  The name of the storage engine to which this cost estimate applies. The name is not case-sensitive. If the value is `default`, it applies to all storage engines that have no named entry of their own. If the server does not recognize the engine name when it reads this table, it writes a warning to the error log.

- `device_type`

  The device type to which this cost estimate applies. The column is intended for specifying different cost estimates for different storage device types, such as hard disk drives versus solid state drives. Currently, this information is not used and 0 is the only permitted value.

- `cost_name`

  Same as in the `server_cost` table.

- `cost_value`

  Same as in the `server_cost` table.

- `last_update`

  Same as in the `server_cost` table.

- `comment`

  Same as in the `server_cost` table.

The primary key for the `engine_cost` table is a tuple comprising the (`cost_name`, `engine_name`, `device_type`) columns, so it is not possible to create multiple entries for any combination of values in those columns.

The server recognizes these `cost_name` values for the `engine_cost` table:

- `io_block_read_cost` (default 1.0)

  The cost of reading an index or data block from disk. Increasing this value causes a query plan that reads many disk blocks to become more expensive compared to a query plan that reads fewer disk blocks. For example, a table scan becomes relatively more expensive compared to a range scan that reads fewer blocks.

- `memory_block_read_cost` (default 1.0)

  Similar to `io_block_read_cost`, but represents the cost of reading an index or data block from an in-memory database buffer.

If the `io_block_read_cost` and `memory_block_read_cost` values differ, the execution plan may change between two runs of the same query. Suppose that the cost for memory access is less than the cost for disk access. In that case, at server startup before data has been read into the buffer pool, you may get a different plan than after the query has been run because then the data will be in memory.

#### Making Changes to the Cost Model Database

For DBAs who wish to change the cost model parameters from their defaults, try doubling or halving the value and measuring the effect.

Changes to the `io_block_read_cost` and `memory_block_read_cost` parameters are most likely to yield worthwhile results. These parameter values enable cost models for data access methods to take into account the costs of reading information from different sources; that is, the cost of reading information from disk versus reading information already in a memory buffer. For example, all other things being equal, setting `io_block_read_cost` to a value larger than `memory_block_read_cost` causes the optimizer to prefer query plans that read information already held in memory to plans that must read from disk.

This example shows how to change the default value for `io_block_read_cost`:

```
UPDATE mysql.engine_cost
  SET cost_value = 2.0
  WHERE cost_name = 'io_block_read_cost';
FLUSH OPTIMIZER_COSTS;
```

This example shows how to change the value of `io_block_read_cost` only for the `InnoDB` storage engine:

```
INSERT INTO mysql.engine_cost
  VALUES ('InnoDB', 0, 'io_block_read_cost', 3.0,
  CURRENT_TIMESTAMP, 'Using a slower disk for InnoDB');
FLUSH OPTIMIZER_COSTS;
```

## 8.10 Buffering and Caching

- [8.10.1 InnoDB Buffer Pool Optimization](optimization.html#innodb-buffer-pool-optimization)
- [8.10.2 The MyISAM Key Cache](optimization.html#myisam-key-cache)
- [8.10.3 The MySQL Query Cache](optimization.html#query-cache)
- [8.10.4 Caching of Prepared Statements and Stored Programs](optimization.html#statement-caching)

MySQL uses several strategies that cache information in memory buffers to increase performance.

### 8.10.1 InnoDB Buffer Pool Optimization



[`InnoDB`](innodb-storage-engine.html) maintains a storage area called the [buffer pool](glossary.html#glos_buffer_pool) for caching data and indexes in memory. Knowing how the `InnoDB` buffer pool works, and taking advantage of it to keep frequently accessed data in memory, is an important aspect of MySQL tuning.

For an explanation of the inner workings of the `InnoDB` buffer pool, an overview of its LRU replacement algorithm, and general configuration information, see [Section 14.5.1, “Buffer Pool”](innodb-storage-engine.html#innodb-buffer-pool).

For additional `InnoDB` buffer pool configuration and tuning information, see these sections:

- [Section 14.8.3.4, “Configuring InnoDB Buffer Pool Prefetching (Read-Ahead)”](innodb-storage-engine.html#innodb-performance-read_ahead)
- [Section 14.8.3.5, “Configuring Buffer Pool Flushing”](innodb-storage-engine.html#innodb-buffer-pool-flushing)
- [Section 14.8.3.3, “Making the Buffer Pool Scan Resistant”](innodb-storage-engine.html#innodb-performance-midpoint_insertion)
- [Section 14.8.3.2, “Configuring Multiple Buffer Pool Instances”](innodb-storage-engine.html#innodb-multiple-buffer-pools)
- [Section 14.8.3.6, “Saving and Restoring the Buffer Pool State”](innodb-storage-engine.html#innodb-preload-buffer-pool)
- [Section 14.8.3.1, “Configuring InnoDB Buffer Pool Size”](innodb-storage-engine.html#innodb-buffer-pool-resize)

### 8.10.2 The MyISAM Key Cache

- [8.10.2.1 Shared Key Cache Access](optimization.html#shared-key-cache)
- [8.10.2.2 Multiple Key Caches](optimization.html#multiple-key-caches)
- [8.10.2.3 Midpoint Insertion Strategy](optimization.html#midpoint-insertion)
- [8.10.2.4 Index Preloading](optimization.html#index-preloading)
- [8.10.2.5 Key Cache Block Size](optimization.html#key-cache-block-size)
- [8.10.2.6 Restructuring a Key Cache](optimization.html#key-cache-restructuring)



To minimize disk I/O, the `MyISAM` storage engine exploits a strategy that is used by many database management systems. It employs a cache mechanism to keep the most frequently accessed table blocks in memory:

- For index blocks, a special structure called the key cache (or key buffer) is maintained. The structure contains a number of block buffers where the most-used index blocks are placed.
- For data blocks, MySQL uses no special cache. Instead it relies on the native operating system file system cache.

This section first describes the basic operation of the `MyISAM` key cache. Then it discusses features that improve key cache performance and that enable you to better control cache operation:

- Multiple sessions can access the cache concurrently.
- You can set up multiple key caches and assign table indexes to specific caches.

To control the size of the key cache, use the [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) system variable. If this variable is set equal to zero, no key cache is used. The key cache also is not used if the [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) value is too small to allocate the minimal number of block buffers (8).

When the key cache is not operational, index files are accessed using only the native file system buffering provided by the operating system. (In other words, table index blocks are accessed using the same strategy as that employed for table data blocks.)

An index block is a contiguous unit of access to the `MyISAM` index files. Usually the size of an index block is equal to the size of nodes of the index B-tree. (Indexes are represented on disk using a B-tree data structure. Nodes at the bottom of the tree are leaf nodes. Nodes above the leaf nodes are nonleaf nodes.)

All block buffers in a key cache structure are the same size. This size can be equal to, greater than, or less than the size of a table index block. Usually one these two values is a multiple of the other.

When data from any table index block must be accessed, the server first checks whether it is available in some block buffer of the key cache. If it is, the server accesses data in the key cache rather than on disk. That is, it reads from the cache or writes into it rather than reading from or writing to disk. Otherwise, the server chooses a cache block buffer containing a different table index block (or blocks) and replaces the data there by a copy of required table index block. As soon as the new index block is in the cache, the index data can be accessed.

If it happens that a block selected for replacement has been modified, the block is considered “dirty.” In this case, prior to being replaced, its contents are flushed to the table index from which it came.

Usually the server follows an LRU (Least Recently Used) strategy: When choosing a block for replacement, it selects the least recently used index block. To make this choice easier, the key cache module maintains all used blocks in a special list (LRU chain) ordered by time of use. When a block is accessed, it is the most recently used and is placed at the end of the list. When blocks need to be replaced, blocks at the beginning of the list are the least recently used and become the first candidates for eviction.

The `InnoDB` storage engine also uses an LRU algorithm, to manage its buffer pool. See [Section 14.5.1, “Buffer Pool”](innodb-storage-engine.html#innodb-buffer-pool).

#### 8.10.2.1 Shared Key Cache Access

Threads can access key cache buffers simultaneously, subject to the following conditions:

- A buffer that is not being updated can be accessed by multiple sessions.
- A buffer that is being updated causes sessions that need to use it to wait until the update is complete.
- Multiple sessions can initiate requests that result in cache block replacements, as long as they do not interfere with each other (that is, as long as they need different index blocks, and thus cause different cache blocks to be replaced).

Shared access to the key cache enables the server to improve throughput significantly.

#### 8.10.2.2 Multiple Key Caches

Shared access to the key cache improves performance but does not eliminate contention among sessions entirely. They still compete for control structures that manage access to the key cache buffers. To reduce key cache access contention further, MySQL also provides multiple key caches. This feature enables you to assign different table indexes to different key caches.

Where there are multiple key caches, the server must know which cache to use when processing queries for a given `MyISAM` table. By default, all `MyISAM` table indexes are cached in the default key cache. To assign table indexes to a specific key cache, use the [`CACHE INDEX`](sql-statements.html#cache-index) statement (see [Section 13.7.6.2, “CACHE INDEX Statement”](sql-statements.html#cache-index)). For example, the following statement assigns indexes from the tables `t1`, `t2`, and `t3` to the key cache named `hot_cache`:

```
mysql> CACHE INDEX t1, t2, t3 IN hot_cache;
+---------+--------------------+----------+----------+
| Table   | Op                 | Msg_type | Msg_text |
+---------+--------------------+----------+----------+
| test.t1 | assign_to_keycache | status   | OK       |
| test.t2 | assign_to_keycache | status   | OK       |
| test.t3 | assign_to_keycache | status   | OK       |
+---------+--------------------+----------+----------+
```

The key cache referred to in a [`CACHE INDEX`](sql-statements.html#cache-index) statement can be created by setting its size with a [`SET GLOBAL`](sql-statements.html#set-variable) parameter setting statement or by using server startup options. For example:

```
mysql> SET GLOBAL keycache1.key_buffer_size=128*1024;
```

To destroy a key cache, set its size to zero:

```
mysql> SET GLOBAL keycache1.key_buffer_size=0;
```

You cannot destroy the default key cache. Any attempt to do this is ignored:

```
mysql> SET GLOBAL key_buffer_size = 0;

mysql> SHOW VARIABLES LIKE 'key_buffer_size';
+-----------------+---------+
| Variable_name   | Value   |
+-----------------+---------+
| key_buffer_size | 8384512 |
+-----------------+---------+
```

Key cache variables are structured system variables that have a name and components. For `keycache1.key_buffer_size`, `keycache1` is the cache variable name and [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) is the cache component. See [Section 5.1.8.3, “Structured System Variables”](server-administration.html#structured-system-variables), for a description of the syntax used for referring to structured key cache system variables.

By default, table indexes are assigned to the main (default) key cache created at the server startup. When a key cache is destroyed, all indexes assigned to it are reassigned to the default key cache.

For a busy server, you can use a strategy that involves three key caches:

- A “hot” key cache that takes up 20% of the space allocated for all key caches. Use this for tables that are heavily used for searches but that are not updated.
- A “cold” key cache that takes up 20% of the space allocated for all key caches. Use this cache for medium-sized, intensively modified tables, such as temporary tables.
- A “warm” key cache that takes up 60% of the key cache space. Employ this as the default key cache, to be used by default for all other tables.

One reason the use of three key caches is beneficial is that access to one key cache structure does not block access to the others. Statements that access tables assigned to one cache do not compete with statements that access tables assigned to another cache. Performance gains occur for other reasons as well:

- The hot cache is used only for retrieval queries, so its contents are never modified. Consequently, whenever an index block needs to be pulled in from disk, the contents of the cache block chosen for replacement need not be flushed first.
- For an index assigned to the hot cache, if there are no queries requiring an index scan, there is a high probability that the index blocks corresponding to nonleaf nodes of the index B-tree remain in the cache.
- An update operation most frequently executed for temporary tables is performed much faster when the updated node is in the cache and need not be read from disk first. If the size of the indexes of the temporary tables are comparable with the size of cold key cache, the probability is very high that the updated node is in the cache.

The [`CACHE INDEX`](sql-statements.html#cache-index) statement sets up an association between a table and a key cache, but the association is lost each time the server restarts. If you want the association to take effect each time the server starts, one way to accomplish this is to use an option file: Include variable settings that configure your key caches, and an [`init_file`](server-administration.html#sysvar_init_file) system variable that names a file containing [`CACHE INDEX`](sql-statements.html#cache-index) statements to be executed. For example:

```
key_buffer_size = 4G
hot_cache.key_buffer_size = 2G
cold_cache.key_buffer_size = 2G
init_file=/path/to/data-directory/mysqld_init.sql
```

The statements in `mysqld_init.sql` are executed each time the server starts. The file should contain one SQL statement per line. The following example assigns several tables each to `hot_cache` and `cold_cache`:

```
CACHE INDEX db1.t1, db1.t2, db2.t3 IN hot_cache
CACHE INDEX db1.t4, db2.t5, db2.t6 IN cold_cache
```

#### 8.10.2.3 Midpoint Insertion Strategy

By default, the key cache management system uses a simple LRU strategy for choosing key cache blocks to be evicted, but it also supports a more sophisticated method called the midpoint insertion strategy.

When using the midpoint insertion strategy, the LRU chain is divided into two parts: a hot sublist and a warm sublist. The division point between two parts is not fixed, but the key cache management system takes care that the warm part is not “too short,” always containing at least [`key_cache_division_limit`](server-administration.html#sysvar_key_cache_division_limit) percent of the key cache blocks. [`key_cache_division_limit`](server-administration.html#sysvar_key_cache_division_limit) is a component of structured key cache variables, so its value is a parameter that can be set per cache.

When an index block is read from a table into the key cache, it is placed at the end of the warm sublist. After a certain number of hits (accesses of the block), it is promoted to the hot sublist. At present, the number of hits required to promote a block (3) is the same for all index blocks.

A block promoted into the hot sublist is placed at the end of the list. The block then circulates within this sublist. If the block stays at the beginning of the sublist for a long enough time, it is demoted to the warm sublist. This time is determined by the value of the [`key_cache_age_threshold`](server-administration.html#sysvar_key_cache_age_threshold) component of the key cache.

The threshold value prescribes that, for a key cache containing *`N`* blocks, the block at the beginning of the hot sublist not accessed within the last `*`N`* * key_cache_age_threshold / 100` hits is to be moved to the beginning of the warm sublist. It then becomes the first candidate for eviction, because blocks for replacement always are taken from the beginning of the warm sublist.

The midpoint insertion strategy enables you to keep more-valued blocks always in the cache. If you prefer to use the plain LRU strategy, leave the [`key_cache_division_limit`](server-administration.html#sysvar_key_cache_division_limit) value set to its default of 100.

The midpoint insertion strategy helps to improve performance when execution of a query that requires an index scan effectively pushes out of the cache all the index blocks corresponding to valuable high-level B-tree nodes. To avoid this, you must use a midpoint insertion strategy with the [`key_cache_division_limit`](server-administration.html#sysvar_key_cache_division_limit) set to much less than 100. Then valuable frequently hit nodes are preserved in the hot sublist during an index scan operation as well.

#### 8.10.2.4 Index Preloading

If there are enough blocks in a key cache to hold blocks of an entire index, or at least the blocks corresponding to its nonleaf nodes, it makes sense to preload the key cache with index blocks before starting to use it. Preloading enables you to put the table index blocks into a key cache buffer in the most efficient way: by reading the index blocks from disk sequentially.

Without preloading, the blocks are still placed into the key cache as needed by queries. Although the blocks will stay in the cache, because there are enough buffers for all of them, they are fetched from disk in random order, and not sequentially.

To preload an index into a cache, use the [`LOAD INDEX INTO CACHE`](sql-statements.html#load-index) statement. For example, the following statement preloads nodes (index blocks) of indexes of the tables `t1` and `t2`:

```
mysql> LOAD INDEX INTO CACHE t1, t2 IGNORE LEAVES;
+---------+--------------+----------+----------+
| Table   | Op           | Msg_type | Msg_text |
+---------+--------------+----------+----------+
| test.t1 | preload_keys | status   | OK       |
| test.t2 | preload_keys | status   | OK       |
+---------+--------------+----------+----------+
```

The `IGNORE LEAVES` modifier causes only blocks for the nonleaf nodes of the index to be preloaded. Thus, the statement shown preloads all index blocks from `t1`, but only blocks for the nonleaf nodes from `t2`.

If an index has been assigned to a key cache using a [`CACHE INDEX`](sql-statements.html#cache-index) statement, preloading places index blocks into that cache. Otherwise, the index is loaded into the default key cache.

#### 8.10.2.5 Key Cache Block Size

It is possible to specify the size of the block buffers for an individual key cache using the [`key_cache_block_size`](server-administration.html#sysvar_key_cache_block_size) variable. This permits tuning of the performance of I/O operations for index files.

The best performance for I/O operations is achieved when the size of read buffers is equal to the size of the native operating system I/O buffers. But setting the size of key nodes equal to the size of the I/O buffer does not always ensure the best overall performance. When reading the big leaf nodes, the server pulls in a lot of unnecessary data, effectively preventing reading other leaf nodes.

To control the size of blocks in the `.MYI` index file of `MyISAM` tables, use the [`--myisam-block-size`](server-administration.html#option_mysqld_myisam-block-size) option at server startup.

#### 8.10.2.6 Restructuring a Key Cache

A key cache can be restructured at any time by updating its parameter values. For example:

```
mysql> SET GLOBAL cold_cache.key_buffer_size=4*1024*1024;
```

If you assign to either the [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) or [`key_cache_block_size`](server-administration.html#sysvar_key_cache_block_size) key cache component a value that differs from the component's current value, the server destroys the cache's old structure and creates a new one based on the new values. If the cache contains any dirty blocks, the server saves them to disk before destroying and re-creating the cache. Restructuring does not occur if you change other key cache parameters.

When restructuring a key cache, the server first flushes the contents of any dirty buffers to disk. After that, the cache contents become unavailable. However, restructuring does not block queries that need to use indexes assigned to the cache. Instead, the server directly accesses the table indexes using native file system caching. File system caching is not as efficient as using a key cache, so although queries execute, a slowdown can be anticipated. After the cache has been restructured, it becomes available again for caching indexes assigned to it, and the use of file system caching for the indexes ceases.

### 8.10.3 The MySQL Query Cache

- [8.10.3.1 How the Query Cache Operates](optimization.html#query-cache-operation)
- [8.10.3.2 Query Cache SELECT Options](optimization.html#query-cache-in-select)
- [8.10.3.3 Query Cache Configuration](optimization.html#query-cache-configuration)
- [8.10.3.4 Query Cache Status and Maintenance](optimization.html#query-cache-status-and-maintenance)



Note

The query cache is deprecated as of MySQL 5.7.20, and is removed in MySQL 8.0.

The query cache stores the text of a [`SELECT`](sql-statements.html#select) statement together with the corresponding result that was sent to the client. If an identical statement is received later, the server retrieves the results from the query cache rather than parsing and executing the statement again. The query cache is shared among sessions, so a result set generated by one client can be sent in response to the same query issued by another client.

The query cache can be useful in an environment where you have tables that do not change very often and for which the server receives many identical queries. This is a typical situation for many Web servers that generate many dynamic pages based on database content.

The query cache does not return stale data. When tables are modified, any relevant entries in the query cache are flushed.

Note

The query cache does not work in an environment where you have multiple [**mysqld**](programs.html#mysqld) servers updating the same `MyISAM` tables.

The query cache is used for prepared statements under the conditions described in [Section 8.10.3.1, “How the Query Cache Operates”](optimization.html#query-cache-operation).

Note

The query cache is not supported for partitioned tables, and is automatically disabled for queries involving partitioned tables. The query cache cannot be enabled for such queries.

Some performance data for the query cache follows. These results were generated by running the MySQL benchmark suite on a Linux Alpha 2×500MHz system with 2GB RAM and a 64MB query cache.

- If all the queries you are performing are simple (such as selecting a row from a table with one row), but still differ so that the queries cannot be cached, the overhead for having the query cache active is 13%. This could be regarded as the worst case scenario. In real life, queries tend to be much more complicated, so the overhead normally is significantly lower.
- Searches for a single row in a single-row table are 238% faster with the query cache than without it. This can be regarded as close to the minimum speedup to be expected for a query that is cached.

To disable the query cache at server startup, set the [`query_cache_size`](server-administration.html#sysvar_query_cache_size) system variable to 0. By disabling the query cache code, there is no noticeable overhead.

The query cache offers the potential for substantial performance improvement, but do not assume that it will do so under all circumstances. With some query cache configurations or server workloads, you might actually see a performance decrease:

- Be cautious about sizing the query cache excessively large, which increases the overhead required to maintain the cache, possibly beyond the benefit of enabling it. Sizes in tens of megabytes are usually beneficial. Sizes in the hundreds of megabytes might not be.
- Server workload has a significant effect on query cache efficiency. A query mix consisting almost entirely of a fixed set of [`SELECT`](sql-statements.html#select) statements is much more likely to benefit from enabling the cache than a mix in which frequent [`INSERT`](sql-statements.html#insert) statements cause continual invalidation of results in the cache. In some cases, a workaround is to use the `SQL_NO_CACHE` option to prevent results from even entering the cache for [`SELECT`](sql-statements.html#select) statements that use frequently modified tables. (See [Section 8.10.3.2, “Query Cache SELECT Options”](optimization.html#query-cache-in-select).)

To verify that enabling the query cache is beneficial, test the operation of your MySQL server with the cache enabled and disabled. Then retest periodically because query cache efficiency may change as server workload changes.

#### 8.10.3.1 How the Query Cache Operates

Note

The query cache is deprecated as of MySQL 5.7.20, and is removed in MySQL 8.0.

This section describes how the query cache works when it is operational. [Section 8.10.3.3, “Query Cache Configuration”](optimization.html#query-cache-configuration), describes how to control whether it is operational.

Incoming queries are compared to those in the query cache before parsing, so the following two queries are regarded as different by the query cache:

```
SELECT * FROM tbl_name
Select * from tbl_name
```

Queries must be *exactly* the same (byte for byte) to be seen as identical. In addition, query strings that are identical may be treated as different for other reasons. Queries that use different databases, different protocol versions, or different default character sets are considered different queries and are cached separately.

The cache is not used for queries of the following types:

- Queries that are a subquery of an outer query
- Queries executed within the body of a stored function, trigger, or event

Before a query result is fetched from the query cache, MySQL checks whether the user has [`SELECT`](sql-statements.html#select) privilege for all databases and tables involved. If this is not the case, the cached result is not used.

If a query result is returned from query cache, the server increments the [`Qcache_hits`](server-administration.html#statvar_Qcache_hits) status variable, not `Com_select`. See [Section 8.10.3.4, “Query Cache Status and Maintenance”](optimization.html#query-cache-status-and-maintenance).

If a table changes, all cached queries that use the table become invalid and are removed from the cache. This includes queries that use `MERGE` tables that map to the changed table. A table can be changed by many types of statements, such as [`INSERT`](sql-statements.html#insert), [`UPDATE`](sql-statements.html#update), [`DELETE`](sql-statements.html#delete), [`TRUNCATE TABLE`](sql-statements.html#truncate-table), [`ALTER TABLE`](sql-statements.html#alter-table), [`DROP TABLE`](sql-statements.html#drop-table), or [`DROP DATABASE`](sql-statements.html#drop-database).

The query cache also works within transactions when using `InnoDB` tables.

The result from a [`SELECT`](sql-statements.html#select) query on a view is cached.

The query cache works for `SELECT SQL_CALC_FOUND_ROWS ...` queries and stores a value that is returned by a following `SELECT FOUND_ROWS()` query. [`FOUND_ROWS()`](functions.html#function_found-rows) returns the correct value even if the preceding query was fetched from the cache because the number of found rows is also stored in the cache. The `SELECT FOUND_ROWS()` query itself cannot be cached.

Prepared statements that are issued using the binary protocol using [`mysql_stmt_prepare()`](connectors-apis.html#mysql-stmt-prepare) and [`mysql_stmt_execute()`](connectors-apis.html#mysql-stmt-execute) (see [Section 27.7.7, “C API Prepared Statements”](connectors-apis.html#c-api-prepared-statements)), are subject to limitations on caching. Comparison with statements in the query cache is based on the text of the statement after expansion of `?` parameter markers. The statement is compared only with other cached statements that were executed using the binary protocol. That is, for query cache purposes, prepared statements issued using the binary protocol are distinct from prepared statements issued using the text protocol (see [Section 13.5, “Prepared Statements”](sql-statements.html#sql-prepared-statements)).

A query cannot be cached if it uses any of the following functions:

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
- [`ENCRYPT()`](functions.html#function_encrypt) with one parameter
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
- [`UNIX_TIMESTAMP()`](functions.html#function_unix-timestamp) with no parameters
- [`USER()`](functions.html#function_user)
- [`UUID()`](functions.html#function_uuid)
- [`UUID_SHORT()`](functions.html#function_uuid-short)

A query also is not cached under these conditions:

- It refers to user-defined functions (UDFs) or stored functions.

- It refers to user variables or local stored program variables.

- It refers to tables in the `mysql`, `INFORMATION_SCHEMA`, or `performance_schema` database.

- It refers to any partitioned tables.

- It is of any of the following forms:

  ```
  SELECT ... LOCK IN SHARE MODE
  SELECT ... FOR UPDATE
  SELECT ... INTO OUTFILE ...
  SELECT ... INTO DUMPFILE ...
  SELECT * FROM ... WHERE autoincrement_col IS NULL
  ```

  The last form is not cached because it is used as the ODBC workaround for obtaining the last insert ID value. See the Connector/ODBC section of [Chapter 27, *Connectors and APIs*](connectors-apis.html).

  Statements within transactions that use [`SERIALIZABLE`](innodb-storage-engine.html#isolevel_serializable) isolation level also cannot be cached because they use `LOCK IN SHARE MODE` locking.

- It uses `TEMPORARY` tables.

- It does not use any tables.

- It generates warnings.

- The user has a column-level privilege for any of the involved tables.

#### 8.10.3.2 Query Cache SELECT Options

Note

The query cache is deprecated as of MySQL 5.7.20, and is removed in MySQL 8.0.

Two query cache-related options may be specified in [`SELECT`](sql-statements.html#select) statements:

- `SQL_CACHE`

  The query result is cached if it is cacheable and the value of the [`query_cache_type`](server-administration.html#sysvar_query_cache_type) system variable is `ON` or `DEMAND`.

- 

  `SQL_NO_CACHE`

  The server does not use the query cache. It neither checks the query cache to see whether the result is already cached, nor does it cache the query result.

Examples:

```
SELECT SQL_CACHE id, name FROM customer;
SELECT SQL_NO_CACHE id, name FROM customer;
```

#### 8.10.3.3 Query Cache Configuration

Note

The query cache is deprecated as of MySQL 5.7.20, and is removed in MySQL 8.0.

The [`have_query_cache`](server-administration.html#sysvar_have_query_cache) server system variable indicates whether the query cache is available:

```
mysql> SHOW VARIABLES LIKE 'have_query_cache';
+------------------+-------+
| Variable_name    | Value |
+------------------+-------+
| have_query_cache | YES   |
+------------------+-------+
```

When using a standard MySQL binary, this value is always `YES`, even if query caching is disabled.

Several other system variables control query cache operation. These can be set in an option file or on the command line when starting [**mysqld**](programs.html#mysqld). The query cache system variables all have names that begin with `query_cache_`. They are described briefly in [Section 5.1.7, “Server System Variables”](server-administration.html#server-system-variables), with additional configuration information given here.

To set the size of the query cache, set the [`query_cache_size`](server-administration.html#sysvar_query_cache_size) system variable. Setting it to 0 disables the query cache, as does setting [`query_cache_type=0`](server-administration.html#sysvar_query_cache_type). By default, the query cache is disabled. This is achieved using a default size of 1M, with a default for `query_cache_type` of 0.

To reduce overhead significantly, start the server with [`query_cache_type=0`](server-administration.html#sysvar_query_cache_type) if you will not be using the query cache.

Note

When using the Windows Configuration Wizard to install or configure MySQL, the default value for [`query_cache_size`](server-administration.html#sysvar_query_cache_size) will be configured automatically for you based on the different configuration types available. When using the Windows Configuration Wizard, the query cache may be enabled (that is, set to a nonzero value) due to the selected configuration. The query cache is also controlled by the setting of the [`query_cache_type`](server-administration.html#sysvar_query_cache_type) variable. Check the values of these variables as set in your `my.ini` file after configuration has taken place.

When you set [`query_cache_size`](server-administration.html#sysvar_query_cache_size) to a nonzero value, keep in mind that the query cache needs a minimum size of about 40KB to allocate its structures. (The exact size depends on system architecture.) If you set the value too small, you'll get a warning, as in this example:

```
mysql> SET GLOBAL query_cache_size = 40000;
Query OK, 0 rows affected, 1 warning (0.00 sec)

mysql> SHOW WARNINGS\G
*************************** 1. row ***************************
  Level: Warning
   Code: 1282
Message: Query cache failed to set size 39936;
         new query cache size is 0

mysql> SET GLOBAL query_cache_size = 41984;
Query OK, 0 rows affected (0.00 sec)

mysql> SHOW VARIABLES LIKE 'query_cache_size';
+------------------+-------+
| Variable_name    | Value |
+------------------+-------+
| query_cache_size | 41984 |
+------------------+-------+
```

For the query cache to actually be able to hold any query results, its size must be set larger:

```
mysql> SET GLOBAL query_cache_size = 1000000;
Query OK, 0 rows affected (0.04 sec)

mysql> SHOW VARIABLES LIKE 'query_cache_size';
+------------------+--------+
| Variable_name    | Value  |
+------------------+--------+
| query_cache_size | 999424 |
+------------------+--------+
1 row in set (0.00 sec)
```

The [`query_cache_size`](server-administration.html#sysvar_query_cache_size) value is aligned to the nearest 1024 byte block. The value reported may therefore be different from the value that you assign.

If the query cache size is greater than 0, the [`query_cache_type`](server-administration.html#sysvar_query_cache_type) variable influences how it works. This variable can be set to the following values:

- A value of `0` or `OFF` prevents caching or retrieval of cached results.
- A value of `1` or `ON` enables caching except of those statements that begin with `SELECT SQL_NO_CACHE`.
- A value of `2` or `DEMAND` causes caching of only those statements that begin with `SELECT SQL_CACHE`.

If [`query_cache_size`](server-administration.html#sysvar_query_cache_size) is 0, you should also set [`query_cache_type`](server-administration.html#sysvar_query_cache_type) variable to 0. In this case, the server does not acquire the query cache mutex at all, which means that the query cache cannot be enabled at runtime and there is reduced overhead in query execution.

Setting the `GLOBAL` [`query_cache_type`](server-administration.html#sysvar_query_cache_type) value determines query cache behavior for all clients that connect after the change is made. Individual clients can control cache behavior for their own connection by setting the `SESSION` [`query_cache_type`](server-administration.html#sysvar_query_cache_type) value. For example, a client can disable use of the query cache for its own queries like this:

```
mysql> SET SESSION query_cache_type = OFF;
```

If you set [`query_cache_type`](server-administration.html#sysvar_query_cache_type) at server startup (rather than at runtime with a [`SET`](sql-statements.html#set-variable) statement), only the numeric values are permitted.

To control the maximum size of individual query results that can be cached, set the [`query_cache_limit`](server-administration.html#sysvar_query_cache_limit) system variable. The default value is 1MB.

Be careful not to set the size of the cache too large. Due to the need for threads to lock the cache during updates, you may see lock contention issues with a very large cache.

Note

You can set the maximum size that can be specified for the query cache at runtime with the [`SET`](sql-statements.html#set-variable) statement by using the `--maximum-query_cache_size=*`32M`*` option on the command line or in the configuration file.

When a query is to be cached, its result (the data sent to the client) is stored in the query cache during result retrieval. Therefore the data usually is not handled in one big chunk. The query cache allocates blocks for storing this data on demand, so when one block is filled, a new block is allocated. Because memory allocation operation is costly (timewise), the query cache allocates blocks with a minimum size given by the [`query_cache_min_res_unit`](server-administration.html#sysvar_query_cache_min_res_unit) system variable. When a query is executed, the last result block is trimmed to the actual data size so that unused memory is freed. Depending on the types of queries your server executes, you might find it helpful to tune the value of [`query_cache_min_res_unit`](server-administration.html#sysvar_query_cache_min_res_unit):

- The default value of [`query_cache_min_res_unit`](server-administration.html#sysvar_query_cache_min_res_unit) is 4KB. This should be adequate for most cases.
- If you have a lot of queries with small results, the default block size may lead to memory fragmentation, as indicated by a large number of free blocks. Fragmentation can force the query cache to prune (delete) queries from the cache due to lack of memory. In this case, decrease the value of [`query_cache_min_res_unit`](server-administration.html#sysvar_query_cache_min_res_unit). The number of free blocks and queries removed due to pruning are given by the values of the [`Qcache_free_blocks`](server-administration.html#statvar_Qcache_free_blocks) and [`Qcache_lowmem_prunes`](server-administration.html#statvar_Qcache_lowmem_prunes) status variables.
- If most of your queries have large results (check the [`Qcache_total_blocks`](server-administration.html#statvar_Qcache_total_blocks) and [`Qcache_queries_in_cache`](server-administration.html#statvar_Qcache_queries_in_cache) status variables), you can increase performance by increasing [`query_cache_min_res_unit`](server-administration.html#sysvar_query_cache_min_res_unit). However, be careful to not make it too large (see the previous item).

#### 8.10.3.4 Query Cache Status and Maintenance

Note

The query cache is deprecated as of MySQL 5.7.20, and is removed in MySQL 8.0.

To check whether the query cache is present in your MySQL server, use the following statement:

```
mysql> SHOW VARIABLES LIKE 'have_query_cache';
+------------------+-------+
| Variable_name    | Value |
+------------------+-------+
| have_query_cache | YES   |
+------------------+-------+
```

You can defragment the query cache to better utilize its memory with the [`FLUSH QUERY CACHE`](sql-statements.html#flush-query-cache) statement. The statement does not remove any queries from the cache.

The `RESET QUERY CACHE` statement removes all query results from the query cache. The [`FLUSH TABLES`](sql-statements.html#flush-tables) statement also does this.

To monitor query cache performance, use [`SHOW STATUS`](sql-statements.html#show-status) to view the cache status variables:

```
mysql> SHOW STATUS LIKE 'Qcache%';
+-------------------------+--------+
| Variable_name           | Value  |
+-------------------------+--------+
| Qcache_free_blocks      | 36     |
| Qcache_free_memory      | 138488 |
| Qcache_hits             | 79570  |
| Qcache_inserts          | 27087  |
| Qcache_lowmem_prunes    | 3114   |
| Qcache_not_cached       | 22989  |
| Qcache_queries_in_cache | 415    |
| Qcache_total_blocks     | 912    |
+-------------------------+--------+
```

Descriptions of each of these variables are given in [Section 5.1.9, “Server Status Variables”](server-administration.html#server-status-variables). Some uses for them are described here.

The total number of [`SELECT`](sql-statements.html#select) queries is given by this formula:

```
  Com_select
+ Qcache_hits
+ queries with errors found by parser
```

The `Com_select` value is given by this formula:

```
  Qcache_inserts
+ Qcache_not_cached
+ queries with errors found during the column-privileges check
```

The query cache uses variable-length blocks, so [`Qcache_total_blocks`](server-administration.html#statvar_Qcache_total_blocks) and [`Qcache_free_blocks`](server-administration.html#statvar_Qcache_free_blocks) may indicate query cache memory fragmentation. After [`FLUSH QUERY CACHE`](sql-statements.html#flush-query-cache), only a single free block remains.

Every cached query requires a minimum of two blocks (one for the query text and one or more for the query results). Also, every table that is used by a query requires one block. However, if two or more queries use the same table, only one table block needs to be allocated.

The information provided by the [`Qcache_lowmem_prunes`](server-administration.html#statvar_Qcache_lowmem_prunes) status variable can help you tune the query cache size. It counts the number of queries that have been removed from the cache to free up memory for caching new queries. The query cache uses a least recently used (LRU) strategy to decide which queries to remove from the cache. Tuning information is given in [Section 8.10.3.3, “Query Cache Configuration”](optimization.html#query-cache-configuration).

### 8.10.4 Caching of Prepared Statements and Stored Programs



For certain statements that a client might execute multiple times during a session, the server converts the statement to an internal structure and caches that structure to be used during execution. Caching enables the server to perform more efficiently because it avoids the overhead of reconverting the statement should it be needed again during the session. Conversion and caching occurs for these statements:

- Prepared statements, both those processed at the SQL level (using the [`PREPARE`](sql-statements.html#prepare) statement) and those processed using the binary client/server protocol (using the [`mysql_stmt_prepare()`](connectors-apis.html#mysql-stmt-prepare) C API function). The [`max_prepared_stmt_count`](server-administration.html#sysvar_max_prepared_stmt_count) system variable controls the total number of statements the server caches. (The sum of the number of prepared statements across all sessions.)
- Stored programs (stored procedures and functions, triggers, and events). In this case, the server converts and caches the entire program body. The [`stored_program_cache`](server-administration.html#sysvar_stored_program_cache) system variable indicates the approximate number of stored programs the server caches per session.

The server maintains caches for prepared statements and stored programs on a per-session basis. Statements cached for one session are not accessible to other sessions. When a session ends, the server discards any statements cached for it.

When the server uses a cached internal statement structure, it must take care that the structure does not go out of date. Metadata changes can occur for an object used by the statement, causing a mismatch between the current object definition and the definition as represented in the internal statement structure. Metadata changes occur for DDL statements such as those that create, drop, alter, rename, or truncate tables, or that analyze, optimize, or repair tables. Table content changes (for example, with [`INSERT`](sql-statements.html#insert) or [`UPDATE`](sql-statements.html#update)) do not change metadata, nor do [`SELECT`](sql-statements.html#select) statements.

Here is an illustration of the problem. Suppose that a client prepares this statement:

```
PREPARE s1 FROM 'SELECT * FROM t1';
```

The `SELECT *` expands in the internal structure to the list of columns in the table. If the set of columns in the table is modified with `ALTER TABLE`, the prepared statement goes out of date. If the server does not detect this change the next time the client executes `s1`, the prepared statement will return incorrect results.

To avoid problems caused by metadata changes to tables or views referred to by the prepared statement, the server detects these changes and automatically reprepares the statement when it is next executed. That is, the server reparses the statement and rebuilds the internal structure. Reparsing also occurs after referenced tables or views are flushed from the table definition cache, either implicitly to make room for new entries in the cache, or explicitly due to [`FLUSH TABLES`](sql-statements.html#flush-tables).

Similarly, if changes occur to objects used by a stored program, the server reparses affected statements within the program.

The server also detects metadata changes for objects in expressions. These might be used in statements specific to stored programs, such as `DECLARE CURSOR` or flow-control statements such as [`IF`](sql-statements.html#if), [`CASE`](sql-statements.html#case), and [`RETURN`](sql-statements.html#return).

To avoid reparsing entire stored programs, the server reparses affected statements or expressions within a program only as needed. Examples:

- Suppose that metadata for a table or view is changed. Reparsing occurs for a `SELECT *` within the program that accesses the table or view, but not for a `SELECT *` that does not access the table or view.

- When a statement is affected, the server reparses it only partially if possible. Consider this [`CASE`](sql-statements.html#case) statement:

  ```
  CASE case_expr
    WHEN when_expr1 ...
    WHEN when_expr2 ...
    WHEN when_expr3 ...
    ...
  END CASE
  ```

  If a metadata change affects only `WHEN *`when_expr3`*`, that expression is reparsed. *`case_expr`* and the other `WHEN` expressions are not reparsed.

Reparsing uses the default database and SQL mode that were in effect for the original conversion to internal form.

The server attempts reparsing up to three times. An error occurs if all attempts fail.

Reparsing is automatic, but to the extent that it occurs, diminishes prepared statement and stored program performance.

For prepared statements, the [`Com_stmt_reprepare`](server-administration.html#statvar_Com_xxx) status variable tracks the number of repreparations.

## 8.11 Optimizing Locking Operations

- [8.11.1 Internal Locking Methods](optimization.html#internal-locking)
- [8.11.2 Table Locking Issues](optimization.html#table-locking)
- [8.11.3 Concurrent Inserts](optimization.html#concurrent-inserts)
- [8.11.4 Metadata Locking](optimization.html#metadata-locking)
- [8.11.5 External Locking](optimization.html#external-locking)

MySQL manages contention for table contents using [locking](glossary.html#glos_locking):

- Internal locking is performed within the MySQL server itself to manage contention for table contents by multiple threads. This type of locking is internal because it is performed entirely by the server and involves no other programs. See [Section 8.11.1, “Internal Locking Methods”](optimization.html#internal-locking).
- External locking occurs when the server and other programs lock [`MyISAM`](storage-engines.html#myisam-storage-engine) table files to coordinate among themselves which program can access the tables at which time. See [Section 8.11.5, “External Locking”](optimization.html#external-locking).

### 8.11.1 Internal Locking Methods



This section discusses internal locking; that is, locking performed within the MySQL server itself to manage contention for table contents by multiple sessions. This type of locking is internal because it is performed entirely by the server and involves no other programs. For locking performed on MySQL files by other programs, see [Section 8.11.5, “External Locking”](optimization.html#external-locking).

- [Row-Level Locking](optimization.html#internal-row-level-locking)
- [Table-Level Locking](optimization.html#internal-table-level-locking)
- [Choosing the Type of Locking](optimization.html#internal-locking-choices)

#### Row-Level Locking

MySQL uses [row-level locking](glossary.html#glos_row_lock) for `InnoDB` tables to support simultaneous write access by multiple sessions, making them suitable for multi-user, highly concurrent, and OLTP applications.

To avoid [deadlocks](glossary.html#glos_deadlock) when performing multiple concurrent write operations on a single `InnoDB` table, acquire necessary locks at the start of the transaction by issuing a `SELECT ... FOR UPDATE` statement for each group of rows expected to be modified, even if the data change statements come later in the transaction. If transactions modify or lock more than one table, issue the applicable statements in the same order within each transaction. Deadlocks affect performance rather than representing a serious error, because `InnoDB` automatically [detects](glossary.html#glos_deadlock_detection) deadlock conditions and rolls back one of the affected transactions.

On high concurrency systems, deadlock detection can cause a slowdown when numerous threads wait for the same lock. At times, it may be more efficient to disable deadlock detection and rely on the [`innodb_lock_wait_timeout`](innodb-storage-engine.html#sysvar_innodb_lock_wait_timeout) setting for transaction rollback when a deadlock occurs. Deadlock detection can be disabled using the [`innodb_deadlock_detect`](innodb-storage-engine.html#sysvar_innodb_deadlock_detect) configuration option.

Advantages of row-level locking:

- Fewer lock conflicts when different sessions access different rows.
- Fewer changes for rollbacks.
- Possible to lock a single row for a long time.

#### Table-Level Locking

MySQL uses [table-level locking](glossary.html#glos_table_lock) for `MyISAM`, `MEMORY`, and `MERGE` tables, permitting only one session to update those tables at a time. This locking level makes these storage engines more suitable for read-only, read-mostly, or single-user applications.

These storage engines avoid [deadlocks](glossary.html#glos_deadlock) by always requesting all needed locks at once at the beginning of a query and always locking the tables in the same order. The tradeoff is that this strategy reduces concurrency; other sessions that want to modify the table must wait until the current data change statement finishes.

Advantages of table-level locking:

- Relatively little memory required (row locking requires memory per row or group of rows locked)
- Fast when used on a large part of the table because only a single lock is involved.
- Fast if you often do `GROUP BY` operations on a large part of the data or must scan the entire table frequently.

MySQL grants table write locks as follows:

1. If there are no locks on the table, put a write lock on it.
2. Otherwise, put the lock request in the write lock queue.

MySQL grants table read locks as follows:

1. If there are no write locks on the table, put a read lock on it.
2. Otherwise, put the lock request in the read lock queue.

Table updates are given higher priority than table retrievals. Therefore, when a lock is released, the lock is made available to the requests in the write lock queue and then to the requests in the read lock queue. This ensures that updates to a table are not “starved” even when there is heavy [`SELECT`](sql-statements.html#select) activity for the table. However, if there are many updates for a table, [`SELECT`](sql-statements.html#select) statements wait until there are no more updates.

For information on altering the priority of reads and writes, see [Section 8.11.2, “Table Locking Issues”](optimization.html#table-locking).

You can analyze the table lock contention on your system by checking the [`Table_locks_immediate`](server-administration.html#statvar_Table_locks_immediate) and [`Table_locks_waited`](server-administration.html#statvar_Table_locks_waited) status variables, which indicate the number of times that requests for table locks could be granted immediately and the number that had to wait, respectively:

```
mysql> SHOW STATUS LIKE 'Table%';
+-----------------------+---------+
| Variable_name         | Value   |
+-----------------------+---------+
| Table_locks_immediate | 1151552 |
| Table_locks_waited    | 15324   |
+-----------------------+---------+
```

The Performance Schema lock tables also provide locking information. See [Section 25.12.12, “Performance Schema Lock Tables”](performance-schema.html#performance-schema-lock-tables).



The `MyISAM` storage engine supports concurrent inserts to reduce contention between readers and writers for a given table: If a `MyISAM` table has no free blocks in the middle of the data file, rows are always inserted at the end of the data file. In this case, you can freely mix concurrent [`INSERT`](sql-statements.html#insert) and [`SELECT`](sql-statements.html#select) statements for a `MyISAM` table without locks. That is, you can insert rows into a `MyISAM` table at the same time other clients are reading from it. Holes can result from rows having been deleted from or updated in the middle of the table. If there are holes, concurrent inserts are disabled but are enabled again automatically when all holes have been filled with new data. To control this behavior, use the [`concurrent_insert`](server-administration.html#sysvar_concurrent_insert) system variable. See [Section 8.11.3, “Concurrent Inserts”](optimization.html#concurrent-inserts).

If you acquire a table lock explicitly with [`LOCK TABLES`](sql-statements.html#lock-tables), you can request a `READ LOCAL` lock rather than a `READ` lock to enable other sessions to perform concurrent inserts while you have the table locked.

To perform many [`INSERT`](sql-statements.html#insert) and [`SELECT`](sql-statements.html#select) operations on a table `t1` when concurrent inserts are not possible, you can insert rows into a temporary table `temp_t1` and update the real table with the rows from the temporary table:

```
mysql> LOCK TABLES t1 WRITE, temp_t1 WRITE;
mysql> INSERT INTO t1 SELECT * FROM temp_t1;
mysql> DELETE FROM temp_t1;
mysql> UNLOCK TABLES;
```

#### Choosing the Type of Locking

Generally, table locks are superior to row-level locks in the following cases:

- Most statements for the table are reads.

- Statements for the table are a mix of reads and writes, where writes are updates or deletes for a single row that can be fetched with one key read:

  ```
  UPDATE tbl_name SET column=value WHERE unique_key_col=key_value;
  DELETE FROM tbl_name WHERE unique_key_col=key_value;
  ```

- [`SELECT`](sql-statements.html#select) combined with concurrent [`INSERT`](sql-statements.html#insert) statements, and very few [`UPDATE`](sql-statements.html#update) or [`DELETE`](sql-statements.html#delete) statements.

- Many scans or `GROUP BY` operations on the entire table without any writers.

With higher-level locks, you can more easily tune applications by supporting locks of different types, because the lock overhead is less than for row-level locks.

Options other than row-level locking:

- Versioning (such as that used in MySQL for concurrent inserts) where it is possible to have one writer at the same time as many readers. This means that the database or table supports different views for the data depending on when access begins. Other common terms for this are “time travel,” “copy on write,” or “copy on demand.”
- Copy on demand is in many cases superior to row-level locking. However, in the worst case, it can use much more memory than using normal locks.
- Instead of using row-level locks, you can employ application-level locks, such as those provided by [`GET_LOCK()`](functions.html#function_get-lock) and [`RELEASE_LOCK()`](functions.html#function_release-lock) in MySQL. These are advisory locks, so they work only with applications that cooperate with each other. See [Section 12.14, “Locking Functions”](functions.html#locking-functions).

### 8.11.2 Table Locking Issues



`InnoDB` tables use row-level locking so that multiple sessions and applications can read from and write to the same table simultaneously, without making each other wait or producing inconsistent results. For this storage engine, avoid using the [`LOCK TABLES`](sql-statements.html#lock-tables) statement, because it does not offer any extra protection, but instead reduces concurrency. The automatic row-level locking makes these tables suitable for your busiest databases with your most important data, while also simplifying application logic since you do not need to lock and unlock tables. Consequently, the `InnoDB` storage engine is the default in MySQL.

MySQL uses table locking (instead of page, row, or column locking) for all storage engines except `InnoDB`. The locking operations themselves do not have much overhead. But because only one session can write to a table at any one time, for best performance with these other storage engines, use them primarily for tables that are queried often and rarely inserted into or updated.

- [Performance Considerations Favoring InnoDB](optimization.html#table-locking-innodb)
- [Workarounds for Locking Performance Issues](optimization.html#table-locking-workarounds)

#### Performance Considerations Favoring InnoDB

When choosing whether to create a table using `InnoDB` or a different storage engine, keep in mind the following disadvantages of table locking:

- Table locking enables many sessions to read from a table at the same time, but if a session wants to write to a table, it must first get exclusive access, meaning it might have to wait for other sessions to finish with the table first. During the update, all other sessions that want to access this particular table must wait until the update is done.
- Table locking causes problems when a session is waiting because the disk is full and free space needs to become available before the session can proceed. In this case, all sessions that want to access the problem table are also put in a waiting state until more disk space is made available.
- A [`SELECT`](sql-statements.html#select) statement that takes a long time to run prevents other sessions from updating the table in the meantime, making the other sessions appear slow or unresponsive. While a session is waiting to get exclusive access to the table for updates, other sessions that issue [`SELECT`](sql-statements.html#select) statements will queue up behind it, reducing concurrency even for read-only sessions.

#### Workarounds for Locking Performance Issues

The following items describe some ways to avoid or reduce contention caused by table locking:

- Consider switching the table to the `InnoDB` storage engine, either using `CREATE TABLE ... ENGINE=INNODB` during setup, or using `ALTER TABLE ... ENGINE=INNODB` for an existing table. See [Chapter 14, *The InnoDB Storage Engine*](innodb-storage-engine.html) for more details about this storage engine.
- Optimize [`SELECT`](sql-statements.html#select) statements to run faster so that they lock tables for a shorter time. You might have to create some summary tables to do this.
- Start [**mysqld**](programs.html#mysqld) with [`--low-priority-updates`](server-administration.html#sysvar_low_priority_updates). For storage engines that use only table-level locking (such as `MyISAM`, `MEMORY`, and `MERGE`), this gives all statements that update (modify) a table lower priority than [`SELECT`](sql-statements.html#select) statements. In this case, the second [`SELECT`](sql-statements.html#select) statement in the preceding scenario would execute before the [`UPDATE`](sql-statements.html#update) statement, and would not wait for the first [`SELECT`](sql-statements.html#select) to finish.
- To specify that all updates issued in a specific connection should be done with low priority, set the [`low_priority_updates`](server-administration.html#sysvar_low_priority_updates) server system variable equal to 1.
- To give a specific [`INSERT`](sql-statements.html#insert), [`UPDATE`](sql-statements.html#update), or [`DELETE`](sql-statements.html#delete) statement lower priority, use the `LOW_PRIORITY` attribute.
- To give a specific [`SELECT`](sql-statements.html#select) statement higher priority, use the `HIGH_PRIORITY` attribute. See [Section 13.2.9, “SELECT Statement”](sql-statements.html#select).
- Start [**mysqld**](programs.html#mysqld) with a low value for the [`max_write_lock_count`](server-administration.html#sysvar_max_write_lock_count) system variable to force MySQL to temporarily elevate the priority of all [`SELECT`](sql-statements.html#select) statements that are waiting for a table after a specific number of inserts to the table occur. This permits `READ` locks after a certain number of `WRITE` locks.
- If you have problems with mixed [`SELECT`](sql-statements.html#select) and [`DELETE`](sql-statements.html#delete) statements, the `LIMIT` option to [`DELETE`](sql-statements.html#delete) may help. See [Section 13.2.2, “DELETE Statement”](sql-statements.html#delete).
- Using `SQL_BUFFER_RESULT` with [`SELECT`](sql-statements.html#select) statements can help to make the duration of table locks shorter. See [Section 13.2.9, “SELECT Statement”](sql-statements.html#select).
- Splitting table contents into separate tables may help, by allowing queries to run against columns in one table, while updates are confined to columns in a different table.
- You could change the locking code in `mysys/thr_lock.c` to use a single queue. In this case, write locks and read locks would have the same priority, which might help some applications.

### 8.11.3 Concurrent Inserts



The `MyISAM` storage engine supports concurrent inserts to reduce contention between readers and writers for a given table: If a `MyISAM` table has no holes in the data file (deleted rows in the middle), an [`INSERT`](sql-statements.html#insert) statement can be executed to add rows to the end of the table at the same time that [`SELECT`](sql-statements.html#select) statements are reading rows from the table. If there are multiple [`INSERT`](sql-statements.html#insert) statements, they are queued and performed in sequence, concurrently with the [`SELECT`](sql-statements.html#select) statements. The results of a concurrent [`INSERT`](sql-statements.html#insert) may not be visible immediately.

The [`concurrent_insert`](server-administration.html#sysvar_concurrent_insert) system variable can be set to modify the concurrent-insert processing. By default, the variable is set to `AUTO` (or 1) and concurrent inserts are handled as just described. If [`concurrent_insert`](server-administration.html#sysvar_concurrent_insert) is set to `NEVER` (or 0), concurrent inserts are disabled. If the variable is set to `ALWAYS` (or 2), concurrent inserts at the end of the table are permitted even for tables that have deleted rows. See also the description of the [`concurrent_insert`](server-administration.html#sysvar_concurrent_insert) system variable.

If you are using the binary log, concurrent inserts are converted to normal inserts for `CREATE ... SELECT` or [`INSERT ... SELECT`](sql-statements.html#insert-select) statements. This is done to ensure that you can re-create an exact copy of your tables by applying the log during a backup operation. See [Section 5.4.4, “The Binary Log”](server-administration.html#binary-log). In addition, for those statements a read lock is placed on the selected-from table such that inserts into that table are blocked. The effect is that concurrent inserts for that table must wait as well.

With [`LOAD DATA`](sql-statements.html#load-data), if you specify `CONCURRENT` with a `MyISAM` table that satisfies the condition for concurrent inserts (that is, it contains no free blocks in the middle), other sessions can retrieve data from the table while [`LOAD DATA`](sql-statements.html#load-data) is executing. Use of the `CONCURRENT` option affects the performance of [`LOAD DATA`](sql-statements.html#load-data) a bit, even if no other session is using the table at the same time.

If you specify `HIGH_PRIORITY`, it overrides the effect of the [`--low-priority-updates`](server-administration.html#sysvar_low_priority_updates) option if the server was started with that option. It also causes concurrent inserts not to be used.

For [`LOCK TABLE`](sql-statements.html#lock-tables), the difference between `READ LOCAL` and `READ` is that `READ LOCAL` permits nonconflicting [`INSERT`](sql-statements.html#insert) statements (concurrent inserts) to execute while the lock is held. However, this cannot be used if you are going to manipulate the database using processes external to the server while you hold the lock.

### 8.11.4 Metadata Locking



MySQL uses metadata locking to manage concurrent access to database objects and to ensure data consistency. Metadata locking applies not just to tables, but also to schemas, stored programs (procedures, functions, triggers, scheduled events), tablespaces, user locks acquired with the [`GET_LOCK()`](functions.html#function_get-lock) function (see [Section 12.14, “Locking Functions”](functions.html#locking-functions)), and locks acquired with the locking service described in [Section 28.3.1, “The Locking Service”](extending-mysql.html#locking-service).

The Performance Schema [`metadata_locks`](performance-schema.html#metadata-locks-table) table exposes metadata lock information, which can be useful for seeing which sessions hold locks, are blocked waiting for locks, and so forth. For details, see [Section 25.12.12.1, “The metadata_locks Table”](performance-schema.html#metadata-locks-table).

Metadata locking does involve some overhead, which increases as query volume increases. Metadata contention increases the more that multiple queries attempt to access the same objects.

Metadata locking is not a replacement for the table definition cache, and its mutexes and locks differ from the `LOCK_open` mutex. The following discussion provides some information about how metadata locking works.

- [Metadata Lock Acquisition](optimization.html#metadata-lock-acquisition)
- [Metadata Lock Release](optimization.html#metadata-lock-release)

#### Metadata Lock Acquisition

If there are multiple waiters for a given lock, the highest-priority lock request is satisfied first, with an exception related to the [`max_write_lock_count`](server-administration.html#sysvar_max_write_lock_count) system variable. Write lock requests have higher priority than read lock requests. However, if [`max_write_lock_count`](server-administration.html#sysvar_max_write_lock_count) is set to some low value (say, 10), read lock requests may be preferred over pending write lock requests if the read lock requests have already been passed over in favor of 10 write lock requests. Normally this behavior does not occur because [`max_write_lock_count`](server-administration.html#sysvar_max_write_lock_count) by default has a very large value.

Statements acquire metadata locks one by one, not simultaneously, and perform deadlock detection in the process.

DML statements normally acquire locks in the order in which tables are mentioned in the statement.

DDL statements, [`LOCK TABLES`](sql-statements.html#lock-tables), and other similar statements try to reduce the number of possible deadlocks between concurrent DDL statements by acquiring locks on explicitly named tables in name order. Locks might be acquired in a different order for implicitly used tables (such as tables in foreign key relationships that also must be locked).

For example, [`RENAME TABLE`](sql-statements.html#rename-table) is a DDL statement that acquires locks in name order:

- This [`RENAME TABLE`](sql-statements.html#rename-table) statement renames `tbla` to something else, and renames `tblc` to `tbla`:

  ```
  RENAME TABLE tbla TO tbld, tblc TO tbla;
  ```

  The statement acquires metadata locks, in order, on `tbla`, `tblc`, and `tbld` (because `tbld` follows `tblc` in name order):

- This slightly different statement also renames `tbla` to something else, and renames `tblc` to `tbla`:

  ```
  RENAME TABLE tbla TO tblb, tblc TO tbla;
  ```

  In this case, the statement acquires metadata locks, in order, on `tbla`, `tblb`, and `tblc` (because `tblb` precedes `tblc` in name order):

Both statements acquire locks on `tbla` and `tblc`, in that order, but differ in whether the lock on the remaining table name is acquired before or after `tblc`.

Metadata lock acquisition order can make a difference in operation outcome when multiple transactions execute concurrently, as the following example illustrates.

Begin with two tables `x` and `x_new` that have identical structure. Three clients issue statements that involve these tables:

Client 1:

```
LOCK TABLE x WRITE, x_new WRITE;
```

The statement requests and acquires write locks in name order on `x` and `x_new`.

Client 2:

```
INSERT INTO x VALUES(1);
```

The statement requests and blocks waiting for a write lock on `x`.

Client 3:

```
RENAME TABLE x TO x_old, x_new TO x;
```

The statement requests exclusive locks in name order on `x`, `x_new`, and `x_old`, but blocks waiting for the lock on `x`.

Client 1:

```
UNLOCK TABLES;
```

The statement releases the write locks on `x` and `x_new`. The exclusive lock request for `x` by Client 3 has higher priority than the write lock request by Client 2, so Client 3 acquires its lock on `x`, then also on `x_new` and `x_old`, performs the renaming, and releases its locks. Client 2 then acquires its lock on `x`, performs the insert, and releases its lock.

Lock acquisition order results in the [`RENAME TABLE`](sql-statements.html#rename-table) executing before the [`INSERT`](sql-statements.html#insert). The `x` into which the insert occurs is the table that was named `x_new` when Client 2 issued the insert and was renamed to `x` by Client 3:

```
mysql> SELECT * FROM x;
+------+
| i    |
+------+
|    1 |
+------+

mysql> SELECT * FROM x_old;
Empty set (0.01 sec)
```

Now begin instead with tables named `x` and `new_x` that have identical structure. Again, three clients issue statements that involve these tables:

Client 1:

```
LOCK TABLE x WRITE, new_x WRITE;
```

The statement requests and acquires write locks in name order on `new_x` and `x`.

Client 2:

```
INSERT INTO x VALUES(1);
```

The statement requests and blocks waiting for a write lock on `x`.

Client 3:

```
RENAME TABLE x TO old_x, new_x TO x;
```

The statement requests exclusive locks in name order on `new_x`, `old_x`, and `x`, but blocks waiting for the lock on `new_x`.

Client 1:

```
UNLOCK TABLES;
```

The statement releases the write locks on `x` and `new_x`. For `x`, the only pending request is by Client 2, so Client 2 acquires its lock, performs the insert, and releases the lock. For `new_x`, the only pending request is by Client 3, which is permitted to acquire that lock (and also the lock on `old_x`). The rename operation still blocks for the lock on `x` until the Client 2 insert finishes and releases its lock. Then Client 3 acquires the lock on `x`, performs the rename, and releases its lock.

In this case, lock acquisition order results in the [`INSERT`](sql-statements.html#insert) executing before the [`RENAME TABLE`](sql-statements.html#rename-table). The `x` into which the insert occurs is the original `x`, now renamed to `old_x` by the rename operation:

```
mysql> SELECT * FROM x;
Empty set (0.01 sec)

mysql> SELECT * FROM old_x;
+------+
| i    |
+------+
|    1 |
+------+
```

If order of lock acquisition in concurrent statements makes a difference to an application in operation outcome, as in the preceding example, you may be able to adjust the table names to affect the order of lock acquisition.

#### Metadata Lock Release

To ensure transaction serializability, the server must not permit one session to perform a data definition language (DDL) statement on a table that is used in an uncompleted explicitly or implicitly started transaction in another session. The server achieves this by acquiring metadata locks on tables used within a transaction and deferring release of those locks until the transaction ends. A metadata lock on a table prevents changes to the table's structure. This locking approach has the implication that a table that is being used by a transaction within one session cannot be used in DDL statements by other sessions until the transaction ends.

This principle applies not only to transactional tables, but also to nontransactional tables. Suppose that a session begins a transaction that uses transactional table `t` and nontransactional table `nt` as follows:

```
START TRANSACTION;
SELECT * FROM t;
SELECT * FROM nt;
```

The server holds metadata locks on both `t` and `nt` until the transaction ends. If another session attempts a DDL or write lock operation on either table, it blocks until metadata lock release at transaction end. For example, a second session blocks if it attempts any of these operations:

```
DROP TABLE t;
ALTER TABLE t ...;
DROP TABLE nt;
ALTER TABLE nt ...;
LOCK TABLE t ... WRITE;
```

The same behavior applies for The [`LOCK TABLES ... READ`](sql-statements.html#lock-tables). That is, explicitly or implicitly started transactions that update any table (transactional or nontransactional) will block and be blocked by `LOCK TABLES ... READ` for that table.

If the server acquires metadata locks for a statement that is syntactically valid but fails during execution, it does not release the locks early. Lock release is still deferred to the end of the transaction because the failed statement is written to the binary log and the locks protect log consistency.

In autocommit mode, each statement is in effect a complete transaction, so metadata locks acquired for the statement are held only to the end of the statement.

Metadata locks acquired during a [`PREPARE`](sql-statements.html#prepare) statement are released once the statement has been prepared, even if preparation occurs within a multiple-statement transaction.

### 8.11.5 External Locking



External locking is the use of file system locking to manage contention for [`MyISAM`](storage-engines.html#myisam-storage-engine) database tables by multiple processes. External locking is used in situations where a single process such as the MySQL server cannot be assumed to be the only process that requires access to tables. Here are some examples:

- If you run multiple servers that use the same database directory (not recommended), each server must have external locking enabled.

- If you use [**myisamchk**](programs.html#myisamchk) to perform table maintenance operations on [`MyISAM`](storage-engines.html#myisam-storage-engine) tables, you must either ensure that the server is not running, or that the server has external locking enabled so that it locks table files as necessary to coordinate with [**myisamchk**](programs.html#myisamchk) for access to the tables. The same is true for use of [**myisampack**](programs.html#myisampack) to pack [`MyISAM`](storage-engines.html#myisam-storage-engine) tables.

  If the server is run with external locking enabled, you can use [**myisamchk**](programs.html#myisamchk) at any time for read operations such a checking tables. In this case, if the server tries to update a table that [**myisamchk**](programs.html#myisamchk) is using, the server will wait for [**myisamchk**](programs.html#myisamchk) to finish before it continues.

  If you use [**myisamchk**](programs.html#myisamchk) for write operations such as repairing or optimizing tables, or if you use [**myisampack**](programs.html#myisampack) to pack tables, you *must* always ensure that the [**mysqld**](programs.html#mysqld) server is not using the table. If you do not stop [**mysqld**](programs.html#mysqld), at least do a [**mysqladmin flush-tables**](programs.html#mysqladmin) before you run [**myisamchk**](programs.html#myisamchk). Your tables *may become corrupted* if the server and [**myisamchk**](programs.html#myisamchk) access the tables simultaneously.

With external locking in effect, each process that requires access to a table acquires a file system lock for the table files before proceeding to access the table. If all necessary locks cannot be acquired, the process is blocked from accessing the table until the locks can be obtained (after the process that currently holds the locks releases them).

External locking affects server performance because the server must sometimes wait for other processes before it can access tables.

External locking is unnecessary if you run a single server to access a given data directory (which is the usual case) and if no other programs such as [**myisamchk**](programs.html#myisamchk) need to modify tables while the server is running. If you only *read* tables with other programs, external locking is not required, although [**myisamchk**](programs.html#myisamchk) might report warnings if the server changes tables while [**myisamchk**](programs.html#myisamchk) is reading them.

With external locking disabled, to use [**myisamchk**](programs.html#myisamchk), you must either stop the server while [**myisamchk**](programs.html#myisamchk) executes or else lock and flush the tables before running [**myisamchk**](programs.html#myisamchk). (See [Section 8.12.1, “System Factors”](optimization.html#system-optimization).) To avoid this requirement, use the [`CHECK TABLE`](sql-statements.html#check-table) and [`REPAIR TABLE`](sql-statements.html#repair-table) statements to check and repair [`MyISAM`](storage-engines.html#myisam-storage-engine) tables.

For [**mysqld**](programs.html#mysqld), external locking is controlled by the value of the [`skip_external_locking`](server-administration.html#sysvar_skip_external_locking) system variable. When this variable is enabled, external locking is disabled, and vice versa. External locking is disabled by default.

Use of external locking can be controlled at server startup by using the [`--external-locking`](server-administration.html#option_mysqld_external-locking) or [`--skip-external-locking`](server-administration.html#option_mysqld_external-locking) option.

If you do use external locking option to enable updates to [`MyISAM`](storage-engines.html#myisam-storage-engine) tables from many MySQL processes, you must ensure that the following conditions are satisfied:

- Do not use the query cache for queries that use tables that are updated by another process.
- Do not start the server with the [`delay_key_write`](server-administration.html#sysvar_delay_key_write) system variable set to `ALL` or use the `DELAY_KEY_WRITE=1` table option for any shared tables. Otherwise, index corruption can occur.

The easiest way to satisfy these conditions is to always use [`--external-locking`](server-administration.html#option_mysqld_external-locking) together with [`--delay-key-write=OFF`](server-administration.html#sysvar_delay_key_write) and [`--query-cache-size=0`](server-administration.html#sysvar_query_cache_size). (This is not done by default because in many setups it is useful to have a mixture of the preceding options.)

## 8.12 Optimizing the MySQL Server

- [8.12.1 System Factors](optimization.html#system-optimization)
- [8.12.2 Optimizing Disk I/O](optimization.html#disk-issues)
- [8.12.3 Using Symbolic Links](optimization.html#symbolic-links)
- [8.12.4 Optimizing Memory Use](optimization.html#optimizing-memory)

This section discusses optimization techniques for the database server, primarily dealing with system configuration rather than tuning SQL statements. The information in this section is appropriate for DBAs who want to ensure performance and scalability across the servers they manage; for developers constructing installation scripts that include setting up the database; and people running MySQL themselves for development, testing, and so on who want to maximize their own productivity.

### 8.12.1 System Factors



Some system-level factors can affect performance in a major way:



- If you have enough RAM, you could remove all swap devices. Some operating systems use a swap device in some contexts even if you have free memory.

- Avoid external locking for [`MyISAM`](storage-engines.html#myisam-storage-engine) tables. The default is for external locking to be disabled. The [`--external-locking`](server-administration.html#option_mysqld_external-locking) and [`--skip-external-locking`](server-administration.html#option_mysqld_external-locking) options explicitly enable and disable external locking.

  Disabling external locking does not affect MySQL's functionality as long as you run only one server. Just remember to take down the server (or lock and flush the relevant tables) before you run [**myisamchk**](programs.html#myisamchk). On some systems it is mandatory to disable external locking because it does not work, anyway.

  The only case in which you cannot disable external locking is when you run multiple MySQL *servers* (not clients) on the same data, or if you run [**myisamchk**](programs.html#myisamchk) to check (not repair) a table without telling the server to flush and lock the tables first. Note that using multiple MySQL servers to access the same data concurrently is generally *not* recommended, except when using NDB Cluster.

  The [`LOCK TABLES`](sql-statements.html#lock-tables) and [`UNLOCK TABLES`](sql-statements.html#lock-tables) statements use internal locking, so you can use them even if external locking is disabled.

### 8.12.2 Optimizing Disk I/O



This section describes ways to configure storage devices when you can devote more and faster storage hardware to the database server. For information about optimizing an `InnoDB` configuration to improve I/O performance, see [Section 8.5.8, “Optimizing InnoDB Disk I/O”](optimization.html#optimizing-innodb-diskio).

- Disk seeks are a huge performance bottleneck. This problem becomes more apparent when the amount of data starts to grow so large that effective caching becomes impossible. For large databases where you access data more or less randomly, you can be sure that you need at least one disk seek to read and a couple of disk seeks to write things. To minimize this problem, use disks with low seek times.

- Increase the number of available disk spindles (and thereby reduce the seek overhead) by either symlinking files to different disks or striping the disks:

  - Using symbolic links

    This means that, for `MyISAM` tables, you symlink the index file and data files from their usual location in the data directory to another disk (that may also be striped). This makes both the seek and read times better, assuming that the disk is not used for other purposes as well. See [Section 8.12.3, “Using Symbolic Links”](optimization.html#symbolic-links).

    Symbolic links are not supported for use with `InnoDB` tables. However, it is possible to place `InnoDB` data and log files on different physical disks. For more information, see [Section 8.5.8, “Optimizing InnoDB Disk I/O”](optimization.html#optimizing-innodb-diskio).

  - Striping

    Striping means that you have many disks and put the first block on the first disk, the second block on the second disk, and the *`N`*-th block on the (`*`N`* MOD *`number_of_disks`*`) disk, and so on. This means if your normal data size is less than the stripe size (or perfectly aligned), you get much better performance. Striping is very dependent on the operating system and the stripe size, so benchmark your application with different stripe sizes. See [Section 8.13.2, “Using Your Own Benchmarks”](optimization.html#custom-benchmarks).

    The speed difference for striping is *very* dependent on the parameters. Depending on how you set the striping parameters and number of disks, you may get differences measured in orders of magnitude. You have to choose to optimize for random or sequential access.

- For reliability, you may want to use RAID 0+1 (striping plus mirroring), but in this case, you need 2 × *`N`* drives to hold *`N`* drives of data. This is probably the best option if you have the money for it. However, you may also have to invest in some volume-management software to handle it efficiently.

- A good option is to vary the RAID level according to how critical a type of data is. For example, store semi-important data that can be regenerated on a RAID 0 disk, but store really important data such as host information and logs on a RAID 0+1 or RAID *`N`* disk. RAID *`N`* can be a problem if you have many writes, due to the time required to update the parity bits.

- You can also set the parameters for the file system that the database uses:

  If you do not need to know when files were last accessed (which is not really useful on a database server), you can mount your file systems with the `-o noatime` option. That skips updates to the last access time in inodes on the file system, which avoids some disk seeks.

  On many operating systems, you can set a file system to be updated asynchronously by mounting it with the `-o async` option. If your computer is reasonably stable, this should give you better performance without sacrificing too much reliability. (This flag is on by default on Linux.)

#### Using NFS with MySQL

You should be cautious when considering whether to use NFS with MySQL. Potential issues, which vary by operating system and NFS version, include the following:

- MySQL data and log files placed on NFS volumes becoming locked and unavailable for use. Locking issues may occur in cases where multiple instances of MySQL access the same data directory or where MySQL is shut down improperly, due to a power outage, for example. NFS version 4 addresses underlying locking issues with the introduction of advisory and lease-based locking. However, sharing a data directory among MySQL instances is not recommended.
- Data inconsistencies introduced due to messages received out of order or lost network traffic. To avoid this issue, use TCP with `hard` and `intr` mount options.
- Maximum file size limitations. NFS Version 2 clients can only access the lowest 2GB of a file (signed 32 bit offset). NFS Version 3 clients support larger files (up to 64 bit offsets). The maximum supported file size also depends on the local file system of the NFS server.

Using NFS within a professional SAN environment or other storage system tends to offer greater reliability than using NFS outside of such an environment. However, NFS within a SAN environment may be slower than directly attached or bus-attached non-rotational storage.

If you choose to use NFS, NFS Version 4 or later is recommended, as is testing your NFS setup thoroughly before deploying into a production environment.

### 8.12.3 Using Symbolic Links

- [8.12.3.1 Using Symbolic Links for Databases on Unix](optimization.html#symbolic-links-to-databases)
- [8.12.3.2 Using Symbolic Links for MyISAM Tables on Unix](optimization.html#symbolic-links-to-tables)
- [8.12.3.3 Using Symbolic Links for Databases on Windows](optimization.html#windows-symbolic-links)



You can move databases or tables from the database directory to other locations and replace them with symbolic links to the new locations. You might want to do this, for example, to move a database to a file system with more free space or increase the speed of your system by spreading your tables to different disks.

For `InnoDB` tables, use the `DATA DIRECTORY` clause of the [`CREATE TABLE`](sql-statements.html#create-table) statement instead of symbolic links, as explained in [Section 14.6.1.2, “Creating Tables Externally”](innodb-storage-engine.html#innodb-create-table-external). This new feature is a supported, cross-platform technique.

The recommended way to do this is to symlink entire database directories to a different disk. Symlink `MyISAM` tables only as a last resort.

To determine the location of your data directory, use this statement:

```
SHOW VARIABLES LIKE 'datadir';
```

#### 8.12.3.1 Using Symbolic Links for Databases on Unix



On Unix, the way to symlink a database is first to create a directory on some disk where you have free space and then to create a soft link to it from the MySQL data directory.

```
shell> mkdir /dr1/databases/test
shell> ln -s /dr1/databases/test /path/to/datadir
```

MySQL does not support linking one directory to multiple databases. Replacing a database directory with a symbolic link works as long as you do not make a symbolic link between databases. Suppose that you have a database `db1` under the MySQL data directory, and then make a symlink `db2` that points to `db1`:

```
shell> cd /path/to/datadir
shell> ln -s db1 db2
```

The result is that, for any table `tbl_a` in `db1`, there also appears to be a table `tbl_a` in `db2`. If one client updates `db1.tbl_a` and another client updates `db2.tbl_a`, problems are likely to occur.

#### 8.12.3.2 Using Symbolic Links for MyISAM Tables on Unix



Symlinks are fully supported only for `MyISAM` tables. For files used by tables for other storage engines, you may get strange problems if you try to use symbolic links. For `InnoDB` tables, use the alternative technique explained in [Section 14.6.1.2, “Creating Tables Externally”](innodb-storage-engine.html#innodb-create-table-external) instead.

Do not symlink tables on systems that do not have a fully operational `realpath()` call. (Linux and Solaris support `realpath()`). To determine whether your system supports symbolic links, check the value of the [`have_symlink`](server-administration.html#sysvar_have_symlink) system variable using this statement:

```
SHOW VARIABLES LIKE 'have_symlink';
```

The handling of symbolic links for `MyISAM` tables works as follows:

- In the data directory, you always have the table format (`.frm`) file, the data (`.MYD`) file, and the index (`.MYI`) file. The data file and index file can be moved elsewhere and replaced in the data directory by symlinks. The format file cannot.

- You can symlink the data file and the index file independently to different directories.

- To instruct a running MySQL server to perform the symlinking, use the `DATA DIRECTORY` and `INDEX DIRECTORY` options to [`CREATE TABLE`](sql-statements.html#create-table). See [Section 13.1.18, “CREATE TABLE Statement”](sql-statements.html#create-table). Alternatively, if [**mysqld**](programs.html#mysqld) is not running, symlinking can be accomplished manually using **ln -s** from the command line.

  Note

  The path used with either or both of the `DATA DIRECTORY` and `INDEX DIRECTORY` options may not include the MySQL `data` directory. (Bug #32167)

- [**myisamchk**](programs.html#myisamchk) does not replace a symlink with the data file or index file. It works directly on the file to which the symlink points. Any temporary files are created in the directory where the data file or index file is located. The same is true for the [`ALTER TABLE`](sql-statements.html#alter-table), [`OPTIMIZE TABLE`](sql-statements.html#optimize-table), and [`REPAIR TABLE`](sql-statements.html#repair-table) statements.

- Note

  When you drop a table that is using symlinks, *both the symlink and the file to which the symlink points are dropped*. This is an extremely good reason *not* to run [**mysqld**](programs.html#mysqld) as the `root` operating system user or permit operating system users to have write access to MySQL database directories.

- If you rename a table with [`ALTER TABLE ... RENAME`](sql-statements.html#alter-table) or [`RENAME TABLE`](sql-statements.html#rename-table) and you do not move the table to another database, the symlinks in the database directory are renamed to the new names and the data file and index file are renamed accordingly.

- If you use [`ALTER TABLE ... RENAME`](sql-statements.html#alter-table) or [`RENAME TABLE`](sql-statements.html#rename-table) to move a table to another database, the table is moved to the other database directory. If the table name changed, the symlinks in the new database directory are renamed to the new names and the data file and index file are renamed accordingly.

- If you are not using symlinks, start [**mysqld**](programs.html#mysqld) with the [`--skip-symbolic-links`](server-administration.html#option_mysqld_symbolic-links) option to ensure that no one can use [**mysqld**](programs.html#mysqld) to drop or rename a file outside of the data directory.

These table symlink operations are not supported:

- [`ALTER TABLE`](sql-statements.html#alter-table) ignores the `DATA DIRECTORY` and `INDEX DIRECTORY` table options.

- As indicated previously, only the data and index files can be symbolic links. The `.frm` file must *never* be a symbolic link. Attempting to do this (for example, to make one table name a synonym for another) produces incorrect results. Suppose that you have a database `db1` under the MySQL data directory, a table `tbl1` in this database, and in the `db1` directory you make a symlink `tbl2` that points to `tbl1`:

  ```
  shell> cd /path/to/datadir/db1
  shell> ln -s tbl1.frm tbl2.frm
  shell> ln -s tbl1.MYD tbl2.MYD
  shell> ln -s tbl1.MYI tbl2.MYI
  ```

  Problems result if one thread reads `db1.tbl1` and another thread updates `db1.tbl2`:

  - The query cache is “fooled” (it has no way of knowing that `tbl1` has not been updated, so it returns outdated results).
  - `ALTER` statements on `tbl2` fail.

#### 8.12.3.3 Using Symbolic Links for Databases on Windows



On Windows, symbolic links can be used for database directories. This enables you to put a database directory at a different location (for example, on a different disk) by setting up a symbolic link to it. Use of database symlinks on Windows is similar to their use on Unix, although the procedure for setting up the link differs.

Suppose that you want to place the database directory for a database named `mydb` at `D:\data\mydb`. To do this, create a symbolic link in the MySQL data directory that points to `D:\data\mydb`. However, before creating the symbolic link, make sure that the `D:\data\mydb` directory exists by creating it if necessary. If you already have a database directory named `mydb` in the data directory, move it to `D:\data`. Otherwise, the symbolic link will be ineffective. To avoid problems, make sure that the server is not running when you move the database directory.

On Windows, you can create a symlink using the **mklink** command. This command requires administrative privileges.

1. Change location into the data directory:

   ```
   C:\> cd \path\to\datadir
   ```

2. In the data directory, create a symlink named `mydb` that points to the location of the database directory:

   ```
   C:\> mklink /d mydb D:\data\mydb
   ```

After this, all tables created in the database `mydb` are created in `D:\data\mydb`.

### 8.12.4 Optimizing Memory Use

- [8.12.4.1 How MySQL Uses Memory](optimization.html#memory-use)
- [8.12.4.2 Enabling Large Page Support](optimization.html#large-page-support)



#### 8.12.4.1 How MySQL Uses Memory



MySQL allocates buffers and caches to improve performance of database operations. The default configuration is designed to permit a MySQL server to start on a virtual machine that has approximately 512MB of RAM. You can improve MySQL performance by increasing the values of certain cache and buffer-related system variables. You can also modify the default configuration to run MySQL on systems with limited memory.

The following list describes some of the ways that MySQL uses memory. Where applicable, relevant system variables are referenced. Some items are storage engine or feature specific.

- The `InnoDB` buffer pool is a memory area that holds cached `InnoDB` data for tables, indexes, and other auxiliary buffers. For efficiency of high-volume read operations, the buffer pool is divided into [pages](glossary.html#glos_page) that can potentially hold multiple rows. For efficiency of cache management, the buffer pool is implemented as a linked list of pages; data that is rarely used is aged out of the cache, using a variation of the [LRU](glossary.html#glos_lru) algorithm. For more information, see [Section 14.5.1, “Buffer Pool”](innodb-storage-engine.html#innodb-buffer-pool).

  The size of the buffer pool is important for system performance:

  - `InnoDB` allocates memory for the entire buffer pool at server startup, using `malloc()` operations. The [`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size) system variable defines the buffer pool size. Typically, a recommended [`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size) value is 50 to 75 percent of system memory. [`innodb_buffer_pool_size`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_size) can be configured dynamically, while the server is running. For more information, see [Section 14.8.3.1, “Configuring InnoDB Buffer Pool Size”](innodb-storage-engine.html#innodb-buffer-pool-resize).
  - On systems with a large amount of memory, you can improve concurrency by dividing the buffer pool into multiple [buffer pool instances](glossary.html#glos_buffer_pool_instance). The [`innodb_buffer_pool_instances`](innodb-storage-engine.html#sysvar_innodb_buffer_pool_instances) system variable defines the number of buffer pool instances.
  - A buffer pool that is too small may cause excessive churning as pages are flushed from the buffer pool only to be required again a short time later.
  - A buffer pool that is too large may cause swapping due to competition for memory.

- All threads share the [`MyISAM`](storage-engines.html#myisam-storage-engine) key buffer. The [`key_buffer_size`](server-administration.html#sysvar_key_buffer_size) system variable determines its size.

  For each `MyISAM` table the server opens, the index file is opened once; the data file is opened once for each concurrently running thread that accesses the table. For each concurrent thread, a table structure, column structures for each column, and a buffer of size `3 * *`N`*` are allocated (where *`N`* is the maximum row length, not counting [`BLOB`](data-types.html#blob) columns). A [`BLOB`](data-types.html#blob) column requires five to eight bytes plus the length of the [`BLOB`](data-types.html#blob) data. The `MyISAM` storage engine maintains one extra row buffer for internal use.

- The [`myisam_use_mmap`](server-administration.html#sysvar_myisam_use_mmap) system variable can be set to 1 to enable memory-mapping for all `MyISAM` tables.

- If an internal in-memory temporary table becomes too large (as determined using the [`tmp_table_size`](server-administration.html#sysvar_tmp_table_size) and [`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size) system variables), MySQL automatically converts the table from in-memory to on-disk format. On-disk temporary tables use the storage engine defined by the [`internal_tmp_disk_storage_engine`](server-administration.html#sysvar_internal_tmp_disk_storage_engine) system variable. You can increase the permissible temporary table size as described in [Section 8.4.4, “Internal Temporary Table Use in MySQL”](optimization.html#internal-temporary-tables).

  For [`MEMORY`](storage-engines.html#memory-storage-engine) tables explicitly created with [`CREATE TABLE`](sql-statements.html#create-table), only the [`max_heap_table_size`](server-administration.html#sysvar_max_heap_table_size) system variable determines how large a table can grow, and there is no conversion to on-disk format.

- The [MySQL Performance Schema](performance-schema.html) is a feature for monitoring MySQL server execution at a low level. The Performance Schema dynamically allocates memory incrementally, scaling its memory use to actual server load, instead of allocating required memory during server startup. Once memory is allocated, it is not freed until the server is restarted. For more information, see [Section 25.17, “The Performance Schema Memory-Allocation Model”](performance-schema.html#performance-schema-memory-model).

- Each thread that the server uses to manage client connections requires some thread-specific space. The following list indicates these and which system variables control their size:

  - A stack ([`thread_stack`](server-administration.html#sysvar_thread_stack))
  - A connection buffer ([`net_buffer_length`](server-administration.html#sysvar_net_buffer_length))
  - A result buffer ([`net_buffer_length`](server-administration.html#sysvar_net_buffer_length))

  The connection buffer and result buffer each begin with a size equal to [`net_buffer_length`](server-administration.html#sysvar_net_buffer_length) bytes, but are dynamically enlarged up to [`max_allowed_packet`](server-administration.html#sysvar_max_allowed_packet) bytes as needed. The result buffer shrinks to [`net_buffer_length`](server-administration.html#sysvar_net_buffer_length) bytes after each SQL statement. While a statement is running, a copy of the current statement string is also allocated.

  Each connection thread uses memory for computing statement digests. The server allocates [`max_digest_length`](server-administration.html#sysvar_max_digest_length) bytes per session. See [Section 25.10, “Performance Schema Statement Digests”](performance-schema.html#performance-schema-statement-digests).

- All threads share the same base memory.

- When a thread is no longer needed, the memory allocated to it is released and returned to the system unless the thread goes back into the thread cache. In that case, the memory remains allocated.

- Each request that performs a sequential scan of a table allocates a read buffer. The [`read_buffer_size`](server-administration.html#sysvar_read_buffer_size) system variable determines the buffer size.

- When reading rows in an arbitrary sequence (for example, following a sort), a random-read buffer may be allocated to avoid disk seeks. The [`read_rnd_buffer_size`](server-administration.html#sysvar_read_rnd_buffer_size) system variable determines the buffer size.

- All joins are executed in a single pass, and most joins can be done without even using a temporary table. Most temporary tables are memory-based hash tables. Temporary tables with a large row length (calculated as the sum of all column lengths) or that contain [`BLOB`](data-types.html#blob) columns are stored on disk.

- Most requests that perform a sort allocate a sort buffer and zero to two temporary files depending on the result set size. See [Section B.4.3.5, “Where MySQL Stores Temporary Files”](error-handling.html#temporary-files).

- Almost all parsing and calculating is done in thread-local and reusable memory pools. No memory overhead is needed for small items, thus avoiding the normal slow memory allocation and freeing. Memory is allocated only for unexpectedly large strings.

- For each table having [`BLOB`](data-types.html#blob) columns, a buffer is enlarged dynamically to read in larger [`BLOB`](data-types.html#blob) values. If you scan a table, the buffer grows as large as the largest [`BLOB`](data-types.html#blob) value.

- MySQL requires memory and descriptors for the table cache. Handler structures for all in-use tables are saved in the table cache and managed as “First In, First Out” (FIFO). The [`table_open_cache`](server-administration.html#sysvar_table_open_cache) system variable defines the initial table cache size; see [Section 8.4.3.1, “How MySQL Opens and Closes Tables”](optimization.html#table-cache).

  MySQL also requires memory for the table definition cache. The [`table_definition_cache`](server-administration.html#sysvar_table_definition_cache) system variable defines the number of table definitions (from `.frm` files) that can be stored in the table definition cache. If you use a large number of tables, you can create a large table definition cache to speed up the opening of tables. The table definition cache takes less space and does not use file descriptors, unlike the table cache.

- A [`FLUSH TABLES`](sql-statements.html#flush-tables) statement or [**mysqladmin flush-tables**](programs.html#mysqladmin) command closes all tables that are not in use at once and marks all in-use tables to be closed when the currently executing thread finishes. This effectively frees most in-use memory. [`FLUSH TABLES`](sql-statements.html#flush-tables) does not return until all tables have been closed.

- The server caches information in memory as a result of [`GRANT`](sql-statements.html#grant), [`CREATE USER`](sql-statements.html#create-user), [`CREATE SERVER`](sql-statements.html#create-server), and [`INSTALL PLUGIN`](sql-statements.html#install-plugin) statements. This memory is not released by the corresponding [`REVOKE`](sql-statements.html#revoke), [`DROP USER`](sql-statements.html#drop-user), [`DROP SERVER`](sql-statements.html#drop-server), and [`UNINSTALL PLUGIN`](sql-statements.html#uninstall-plugin) statements, so for a server that executes many instances of the statements that cause caching, there will be an increase in cached memory use unless it is freed with [`FLUSH PRIVILEGES`](sql-statements.html#flush-privileges).

**ps** and other system status programs may report that [**mysqld**](programs.html#mysqld) uses a lot of memory. This may be caused by thread stacks on different memory addresses. For example, the Solaris version of **ps** counts the unused memory between stacks as used memory. To verify this, check available swap with `swap -s`. We test [**mysqld**](programs.html#mysqld) with several memory-leakage detectors (both commercial and Open Source), so there should be no memory leaks.

##### Monitoring MySQL Memory Usage



The following example demonstrates how to use [Performance Schema](performance-schema.html) and [sys schema](sys-schema.html) to monitor MySQL memory usage.

Most Performance Schema memory instrumentation is disabled by default. Instruments can be enabled by updating the `ENABLED` column of the Performance Schema [`setup_instruments`](performance-schema.html#setup-instruments-table) table. Memory instruments have names in the form of `memory/*`code_area`*/*`instrument_name`*`, where *`code_area`* is a value such as `sql` or `innodb`, and *`instrument_name`* is the instrument detail.

1. To view available MySQL memory instruments, query the Performance Schema [`setup_instruments`](performance-schema.html#setup-instruments-table) table. The following query returns hundreds of memory instruments for all code areas.

   ```
   mysql> SELECT * FROM performance_schema.setup_instruments
          WHERE NAME LIKE '%memory%';
   ```

   You can narrow results by specifying a code area. For example, you can limit results to `InnoDB` memory instruments by specifying `innodb` as the code area.

   ```
   mysql> SELECT * FROM performance_schema.setup_instruments
          WHERE NAME LIKE '%memory/innodb%';
   +-------------------------------------------+---------+-------+
   | NAME                                      | ENABLED | TIMED |
   +-------------------------------------------+---------+-------+
   | memory/innodb/adaptive hash index         | NO      | NO    |
   | memory/innodb/buf_buf_pool                | NO      | NO    |
   | memory/innodb/dict_stats_bg_recalc_pool_t | NO      | NO    |
   | memory/innodb/dict_stats_index_map_t      | NO      | NO    |
   | memory/innodb/dict_stats_n_diff_on_level  | NO      | NO    |
   | memory/innodb/other                       | NO      | NO    |
   | memory/innodb/row_log_buf                 | NO      | NO    |
   | memory/innodb/row_merge_sort              | NO      | NO    |
   | memory/innodb/std                         | NO      | NO    |
   | memory/innodb/trx_sys_t::rw_trx_ids       | NO      | NO    |
   ...
   ```

   Depending on your MySQL installation, code areas may include `performance_schema`, `sql`, `client`, `innodb`, `myisam`, `csv`, `memory`, `blackhole`, `archive`, `partition`, and others.

2. To enable memory instruments, add a `performance-schema-instrument` rule to your MySQL configuration file. For example, to enable all memory instruments, add this rule to your configuration file and restart the server:

   ```
   performance-schema-instrument='memory/%=COUNTED'
   ```

   Note

   Enabling memory instruments at startup ensures that memory allocations that occur at startup are counted.

   After restarting the server, the `ENABLED` column of the Performance Schema [`setup_instruments`](performance-schema.html#setup-instruments-table) table should report `YES` for memory instruments that you enabled. The `TIMED` column in the [`setup_instruments`](performance-schema.html#setup-instruments-table) table is ignored for memory instruments because memory operations are not timed.

   ```
   mysql> SELECT * FROM performance_schema.setup_instruments
          WHERE NAME LIKE '%memory/innodb%';
   +-------------------------------------------+---------+-------+
   | NAME                                      | ENABLED | TIMED |
   +-------------------------------------------+---------+-------+
   | memory/innodb/adaptive hash index         | NO      | NO    |
   | memory/innodb/buf_buf_pool                | NO      | NO    |
   | memory/innodb/dict_stats_bg_recalc_pool_t | NO      | NO    |
   | memory/innodb/dict_stats_index_map_t      | NO      | NO    |
   | memory/innodb/dict_stats_n_diff_on_level  | NO      | NO    |
   | memory/innodb/other                       | NO      | NO    |
   | memory/innodb/row_log_buf                 | NO      | NO    |
   | memory/innodb/row_merge_sort              | NO      | NO    |
   | memory/innodb/std                         | NO      | NO    |
   | memory/innodb/trx_sys_t::rw_trx_ids       | NO      | NO    |
   ...
   ```

3. Query memory instrument data. In this example, memory instrument data is queried in the Performance Schema [`memory_summary_global_by_event_name`](performance-schema.html#memory-summary-tables) table, which summarizes data by `EVENT_NAME`. The `EVENT_NAME` is the name of the instrument.

   The following query returns memory data for the `InnoDB` buffer pool. For column descriptions, see [Section 25.12.15.9, “Memory Summary Tables”](performance-schema.html#memory-summary-tables).

   ```
   mysql> SELECT * FROM performance_schema.memory_summary_global_by_event_name
          WHERE EVENT_NAME LIKE 'memory/innodb/buf_buf_pool'\G
                     EVENT_NAME: memory/innodb/buf_buf_pool
                    COUNT_ALLOC: 1
                     COUNT_FREE: 0
      SUM_NUMBER_OF_BYTES_ALLOC: 137428992
       SUM_NUMBER_OF_BYTES_FREE: 0
                 LOW_COUNT_USED: 0
             CURRENT_COUNT_USED: 1
                HIGH_COUNT_USED: 1
       LOW_NUMBER_OF_BYTES_USED: 0
   CURRENT_NUMBER_OF_BYTES_USED: 137428992
      HIGH_NUMBER_OF_BYTES_USED: 137428992
   ```

   The same underlying data can be queried using the [`sys`](sys-schema.html) schema [`memory_global_by_current_bytes`](sys-schema.html#sys-memory-global-by-current-bytes) table, which shows current memory usage within the server globally, broken down by allocation type.

   ```
   mysql> SELECT * FROM sys.memory_global_by_current_bytes
          WHERE event_name LIKE 'memory/innodb/buf_buf_pool'\G
   *************************** 1. row ***************************
          event_name: memory/innodb/buf_buf_pool
       current_count: 1
       current_alloc: 131.06 MiB
   current_avg_alloc: 131.06 MiB
          high_count: 1
          high_alloc: 131.06 MiB
      high_avg_alloc: 131.06 MiB
   ```

   This [`sys`](sys-schema.html) schema query aggregates currently allocated memory (`current_alloc`) by code area:

   ```
   mysql> SELECT SUBSTRING_INDEX(event_name,'/',2) AS
          code_area, sys.format_bytes(SUM(current_alloc))
          AS current_alloc
          FROM sys.x$memory_global_by_current_bytes
          GROUP BY SUBSTRING_INDEX(event_name,'/',2)
          ORDER BY SUM(current_alloc) DESC;
   +---------------------------+---------------+
   | code_area                 | current_alloc |
   +---------------------------+---------------+
   | memory/innodb             | 843.24 MiB    |
   | memory/performance_schema | 81.29 MiB     |
   | memory/mysys              | 8.20 MiB      |
   | memory/sql                | 2.47 MiB      |
   | memory/memory             | 174.01 KiB    |
   | memory/myisam             | 46.53 KiB     |
   | memory/blackhole          | 512 bytes     |
   | memory/federated          | 512 bytes     |
   | memory/csv                | 512 bytes     |
   | memory/vio                | 496 bytes     |
   +---------------------------+---------------+
   ```

   For more information about [`sys`](sys-schema.html) schema, see [Chapter 26, *MySQL sys Schema*](sys-schema.html).

#### 8.12.4.2 Enabling Large Page Support



Some hardware/operating system architectures support memory pages greater than the default (usually 4KB). The actual implementation of this support depends on the underlying hardware and operating system. Applications that perform a lot of memory accesses may obtain performance improvements by using large pages due to reduced Translation Lookaside Buffer (TLB) misses.

In MySQL, large pages can be used by InnoDB, to allocate memory for its buffer pool and additional memory pool.

Standard use of large pages in MySQL attempts to use the largest size supported, up to 4MB. Under Solaris, a “super large pages” feature enables uses of pages up to 256MB. This feature is available for recent SPARC platforms. It can be enabled or disabled by using the [`--super-large-pages`](server-administration.html#option_mysqld_super-large-pages) or [`--skip-super-large-pages`](server-administration.html#option_mysqld_super-large-pages) option.

MySQL also supports the Linux implementation of large page support (which is called HugeTLB in Linux).

Before large pages can be used on Linux, the kernel must be enabled to support them and it is necessary to configure the HugeTLB memory pool. For reference, the HugeTBL API is documented in the `Documentation/vm/hugetlbpage.txt` file of your Linux sources.

The kernel for some recent systems such as Red Hat Enterprise Linux appear to have the large pages feature enabled by default. To check whether this is true for your kernel, use the following command and look for output lines containing “huge”:

```
shell> cat /proc/meminfo | grep -i huge
HugePages_Total:       0
HugePages_Free:        0
HugePages_Rsvd:        0
HugePages_Surp:        0
Hugepagesize:       4096 kB
```

The nonempty command output indicates that large page support is present, but the zero values indicate that no pages are configured for use.

If your kernel needs to be reconfigured to support large pages, consult the `hugetlbpage.txt` file for instructions.

Assuming that your Linux kernel has large page support enabled, configure it for use by MySQL using the following commands. Normally, you put these in an `rc` file or equivalent startup file that is executed during the system boot sequence, so that the commands execute each time the system starts. The commands should execute early in the boot sequence, before the MySQL server starts. Be sure to change the allocation numbers and the group number as appropriate for your system.

```
# Set the number of pages to be used.
# Each page is normally 2MB, so a value of 20 = 40MB.
# This command actually allocates memory, so this much
# memory must be available.
echo 20 > /proc/sys/vm/nr_hugepages

# Set the group number that is permitted to access this
# memory (102 in this case). The mysql user must be a
# member of this group.
echo 102 > /proc/sys/vm/hugetlb_shm_group

# Increase the amount of shmem permitted per segment
# (12G in this case).
echo 1560281088 > /proc/sys/kernel/shmmax

# Increase total amount of shared memory.  The value
# is the number of pages. At 4KB/page, 4194304 = 16GB.
echo 4194304 > /proc/sys/kernel/shmall
```

For MySQL usage, you normally want the value of `shmmax` to be close to the value of `shmall`.

To verify the large page configuration, check `/proc/meminfo` again as described previously. Now you should see some nonzero values:

```
shell> cat /proc/meminfo | grep -i huge
HugePages_Total:      20
HugePages_Free:       20
HugePages_Rsvd:        0
HugePages_Surp:        0
Hugepagesize:       4096 kB
```

The final step to make use of the `hugetlb_shm_group` is to give the `mysql` user an “unlimited” value for the memlock limit. This can be done either by editing `/etc/security/limits.conf` or by adding the following command to your [**mysqld_safe**](programs.html#mysqld-safe) script:

```
ulimit -l unlimited
```

Adding the **ulimit** command to [**mysqld_safe**](programs.html#mysqld-safe) causes the `root` user to set the memlock limit to `unlimited` before switching to the `mysql` user. (This assumes that [**mysqld_safe**](programs.html#mysqld-safe) is started by `root`.)

Large page support in MySQL is disabled by default. To enable it, start the server with the [`--large-pages`](server-administration.html#option_mysqld_large-pages) option. For example, you can use the following lines in the server `my.cnf` file:

```
[mysqld]
large-pages
```

With this option, `InnoDB` uses large pages automatically for its buffer pool and additional memory pool. If `InnoDB` cannot do this, it falls back to use of traditional memory and writes a warning to the error log: Warning: Using conventional memory pool

To verify that large pages are being used, check `/proc/meminfo` again:

```
shell> cat /proc/meminfo | grep -i huge
HugePages_Total:      20
HugePages_Free:       20
HugePages_Rsvd:        2
HugePages_Surp:        0
Hugepagesize:       4096 kB
```

## 8.13 Measuring Performance (Benchmarking)

- [8.13.1 Measuring the Speed of Expressions and Functions](optimization.html#select-benchmarking)
- [8.13.2 Using Your Own Benchmarks](optimization.html#custom-benchmarks)
- [8.13.3 Measuring Performance with performance_schema](optimization.html#monitoring-performance-schema)



To measure performance, consider the following factors:

- Whether you are measuring the speed of a single operation on a quiet system, or how a set of operations (a “workload”) works over a period of time. With simple tests, you usually test how changing one aspect (a configuration setting, the set of indexes on a table, the SQL clauses in a query) affects performance. Benchmarks are typically long-running and elaborate performance tests, where the results could dictate high-level choices such as hardware and storage configuration, or how soon to upgrade to a new MySQL version.
- For benchmarking, sometimes you must simulate a heavy database workload to get an accurate picture.
- Performance can vary depending on so many different factors that a difference of a few percentage points might not be a decisive victory. The results might shift the opposite way when you test in a different environment.
- Certain MySQL features help or do not help performance depending on the workload. For completeness, always test performance with those features turned on and turned off. The two most important features to try with each workload are the [MySQL query cache](optimization.html#query-cache), and the [adaptive hash index](innodb-storage-engine.html#innodb-adaptive-hash) for `InnoDB` tables.

This section progresses from simple and direct measurement techniques that a single developer can do, to more complicated ones that require additional expertise to perform and interpret the results.

### 8.13.1 Measuring the Speed of Expressions and Functions

To measure the speed of a specific MySQL expression or function, invoke the [`BENCHMARK()`](functions.html#function_benchmark) function using the [**mysql**](programs.html#mysql) client program. Its syntax is [`BENCHMARK(*`loop_count`*,*`expr`*)`](functions.html#function_benchmark). The return value is always zero, but [**mysql**](programs.html#mysql) prints a line displaying approximately how long the statement took to execute. For example:

```
mysql> SELECT BENCHMARK(1000000,1+1);
+------------------------+
| BENCHMARK(1000000,1+1) |
+------------------------+
|                      0 |
+------------------------+
1 row in set (0.32 sec)
```

This result was obtained on a Pentium II 400MHz system. It shows that MySQL can execute 1,000,000 simple addition expressions in 0.32 seconds on that system.

The built-in MySQL functions are typically highly optimized, but there may be some exceptions. [`BENCHMARK()`](functions.html#function_benchmark) is an excellent tool for finding out if some function is a problem for your queries.

### 8.13.2 Using Your Own Benchmarks



Benchmark your application and database to find out where the bottlenecks are. After fixing one bottleneck (or by replacing it with a “dummy” module), you can proceed to identify the next bottleneck. Even if the overall performance for your application currently is acceptable, you should at least make a plan for each bottleneck and decide how to solve it if someday you really need the extra performance.

A free benchmark suite is the Open Source Database Benchmark, available at http://osdb.sourceforge.net/.

It is very common for a problem to occur only when the system is very heavily loaded. We have had many customers who contact us when they have a (tested) system in production and have encountered load problems. In most cases, performance problems turn out to be due to issues of basic database design (for example, table scans are not good under high load) or problems with the operating system or libraries. Most of the time, these problems would be much easier to fix if the systems were not already in production.

To avoid problems like this, benchmark your whole application under the worst possible load:

- The [**mysqlslap**](programs.html#mysqlslap) program can be helpful for simulating a high load produced by multiple clients issuing queries simultaneously. See [Section 4.5.8, “**mysqlslap** — Load Emulation Client”](programs.html#mysqlslap).
- You can also try benchmarking packages such as SysBench and DBT2, available at https://launchpad.net/sysbench, and http://osdldbt.sourceforge.net/#dbt2.

These programs or packages can bring a system to its knees, so be sure to use them only on your development systems.

### 8.13.3 Measuring Performance with performance_schema



You can query the tables in the `performance_schema` database to see real-time information about the performance characteristics of your server and the applications it is running. See [Chapter 25, *MySQL Performance Schema*](performance-schema.html) for details.

## 8.14 Examining Thread Information

- [8.14.1 Thread Command Values](optimization.html#thread-commands)
- [8.14.2 General Thread States](optimization.html#general-thread-states)
- [8.14.3 Query Cache Thread States](optimization.html#query-cache-thread-states)
- [8.14.4 Replication Master Thread States](optimization.html#master-thread-states)
- [8.14.5 Replication Slave I/O Thread States](optimization.html#slave-io-thread-states)
- [8.14.6 Replication Slave SQL Thread States](optimization.html#slave-sql-thread-states)
- [8.14.7 Replication Slave Connection Thread States](optimization.html#slave-connection-thread-states)
- [8.14.8 NDB Cluster Thread States](optimization.html#mysql-cluster-thread-states)
- [8.14.9 Event Scheduler Thread States](optimization.html#event-scheduler-thread-states)



When you are attempting to ascertain what your MySQL server is doing, it can be helpful to examine the process list, which is the set of threads currently executing within the server. Process list information is available from these sources:

- The `SHOW [FULL] PROCESSLIST` statement: [Section 13.7.5.29, “SHOW PROCESSLIST Statement”](sql-statements.html#show-processlist)
- The [`SHOW PROFILE`](sql-statements.html#show-profile) statement: [Section 13.7.5.31, “SHOW PROFILES Statement”](sql-statements.html#show-profiles)
- The `INFORMATION_SCHEMA` [`PROCESSLIST`](information-schema.html#processlist-table) table: [Section 24.18, “The INFORMATION_SCHEMA PROCESSLIST Table”](information-schema.html#processlist-table)
- The [**mysqladmin processlist**](programs.html#mysqladmin) command: [Section 4.5.2, “**mysqladmin** — Client for Administering a MySQL Server”](programs.html#mysqladmin)
- The Performance Schema [`threads`](performance-schema.html#threads-table) table, stage tables, and lock tables: [Section 25.12.16, “Performance Schema Miscellaneous Tables”](performance-schema.html#performance-schema-miscellaneous-tables), [Section 25.12.5, “Performance Schema Stage Event Tables”](performance-schema.html#performance-schema-stage-tables), [Section 25.12.12, “Performance Schema Lock Tables”](performance-schema.html#performance-schema-lock-tables).
- The `sys` schema [`processlist`](sys-schema.html#sys-processlist) view, which presents information from the Performance Schema [`threads`](performance-schema.html#threads-table) table in a more accessible format: [Section 26.4.3.22, “The processlist and x$processlist Views”](sys-schema.html#sys-processlist)
- The `sys` schema [`session`](sys-schema.html#sys-session) view, which presents information about user sessions (like the `sys` schema [`processlist`](sys-schema.html#sys-processlist) view, but with background processes filtered out): [Section 26.4.3.33, “The session and x$session Views”](sys-schema.html#sys-session)

Access to [`threads`](performance-schema.html#threads-table) does not require a mutex and has minimal impact on server performance. [`INFORMATION_SCHEMA.PROCESSLIST`](information-schema.html#processlist-table) and [`SHOW PROCESSLIST`](sql-statements.html#show-processlist) have negative performance consequences because they require a mutex. [`threads`](performance-schema.html#threads-table) also shows information about background threads, which [`INFORMATION_SCHEMA.PROCESSLIST`](information-schema.html#processlist-table) and [`SHOW PROCESSLIST`](sql-statements.html#show-processlist) do not. This means that [`threads`](performance-schema.html#threads-table) can be used to monitor activity the other thread information sources cannot.

You can always view information about your own threads. To view information about threads being executed for other accounts, you must have the [`PROCESS`](security.html#priv_process) privilege.

Each process list entry contains several pieces of information:

- `Id` is the connection identifier for the client associated with the thread.

- `User` and `Host` indicate the account associated with the thread.

- `db` is the default database for the thread, or `NULL` if none is selected.

- `Command` and `State` indicate what the thread is doing.

  Most states correspond to very quick operations. If a thread stays in a given state for many seconds, there might be a problem that needs to be investigated.

- `Time` indicates how long the thread has been in its current state. The thread's notion of the current time may be altered in some cases: The thread can change the time with [`SET TIMESTAMP = *`value`*`](sql-statements.html#set-variable). For a thread running on a slave that is processing events from the master, the thread time is set to the time found in the events and thus reflects current time on the master and not the slave.

- `Info` contains the text of the statement being executed by the thread, or `NULL` if it is not executing one. By default, this value contains only the first 100 characters of the statement. To see the complete statements, use [`SHOW FULL PROCESSLIST`](sql-statements.html#show-processlist).

The following sections list the possible `Command` values, and `State` values grouped by category. The meaning for some of these values is self-evident. For others, additional description is provided.

### 8.14.1 Thread Command Values



A thread can have any of the following `Command` values:

- `Binlog Dump`

  This is a thread on a master server for sending binary log contents to a slave server.

- `Change user`

  The thread is executing a change-user operation.

- `Close stmt`

  The thread is closing a prepared statement.

- `Connect`

  A replication slave is connected to its master.

- `Connect Out`

  A replication slave is connecting to its master.

- `Create DB`

  The thread is executing a create-database operation.

- `Daemon`

  This thread is internal to the server, not a thread that services a client connection.

- `Debug`

  The thread is generating debugging information.

- `Delayed insert`

  The thread is a delayed-insert handler.

- `Drop DB`

  The thread is executing a drop-database operation.

- `Error`

- `Execute`

  The thread is executing a prepared statement.

- `Fetch`

  The thread is fetching the results from executing a prepared statement.

- `Field List`

  The thread is retrieving information for table columns.

- `Init DB`

  The thread is selecting a default database.

- `Kill`

  The thread is killing another thread.

- `Long Data`

  The thread is retrieving long data in the result of executing a prepared statement.

- `Ping`

  The thread is handling a server-ping request.

- `Prepare`

  The thread is preparing a prepared statement.

- `Processlist`

  The thread is producing information about server threads.

- `Query`

  The thread is executing a statement.

- `Quit`

  The thread is terminating.

- `Refresh`

  The thread is flushing table, logs, or caches, or resetting status variable or replication server information.

- `Register Slave`

  The thread is registering a slave server.

- `Reset stmt`

  The thread is resetting a prepared statement.

- `Set option`

  The thread is setting or resetting a client statement-execution option.

- `Shutdown`

  The thread is shutting down the server.

- `Sleep`

  The thread is waiting for the client to send a new statement to it.

- `Statistics`

  The thread is producing server-status information.

- `Table Dump`

  The thread is sending table contents to a slave server.

- `Time`

  Unused.

### 8.14.2 General Thread States



The following list describes thread `State` values that are associated with general query processing and not more specialized activities such as replication. Many of these are useful only for finding bugs in the server.

- `After create`

  This occurs when the thread creates a table (including internal temporary tables), at the end of the function that creates the table. This state is used even if the table could not be created due to some error.

- `Analyzing`

  The thread is calculating a `MyISAM` table key distributions (for example, for [`ANALYZE TABLE`](sql-statements.html#analyze-table)).

- `checking permissions`

  The thread is checking whether the server has the required privileges to execute the statement.

- `Checking table`

  The thread is performing a table check operation.

- `cleaning up`

  The thread has processed one command and is preparing to free memory and reset certain state variables.

- `closing tables`

  The thread is flushing the changed table data to disk and closing the used tables. This should be a fast operation. If not, verify that you do not have a full disk and that the disk is not in very heavy use.

- `converting HEAP to ondisk`

  The thread is converting an internal temporary table from a `MEMORY` table to an on-disk table.

- `copy to tmp table`

  The thread is processing an [`ALTER TABLE`](sql-statements.html#alter-table) statement. This state occurs after the table with the new structure has been created but before rows are copied into it.

  For a thread in this state, the Performance Schema can be used to obtain about the progress of the copy operation. See [Section 25.12.5, “Performance Schema Stage Event Tables”](performance-schema.html#performance-schema-stage-tables).

- `Copying to group table`

  If a statement has different `ORDER BY` and `GROUP BY` criteria, the rows are sorted by group and copied to a temporary table.

- `Copying to tmp table`

  The server is copying to a temporary table in memory.

- `altering table`

  The server is in the process of executing an in-place [`ALTER TABLE`](sql-statements.html#alter-table).

- `Copying to tmp table on disk`

  The server is copying to a temporary table on disk. The temporary result set has become too large (see [Section 8.4.4, “Internal Temporary Table Use in MySQL”](optimization.html#internal-temporary-tables)). Consequently, the thread is changing the temporary table from in-memory to disk-based format to save memory.

- `Creating index`

  The thread is processing `ALTER TABLE ... ENABLE KEYS` for a `MyISAM` table.

- `Creating sort index`

  The thread is processing a [`SELECT`](sql-statements.html#select) that is resolved using an internal temporary table.

- `creating table`

  The thread is creating a table. This includes creation of temporary tables.

- `Creating tmp table`

  The thread is creating a temporary table in memory or on disk. If the table is created in memory but later is converted to an on-disk table, the state during that operation will be `Copying to tmp table on disk`.

- `committing alter table to storage engine`

  The server has finished an in-place [`ALTER TABLE`](sql-statements.html#alter-table) and is committing the result.

- `deleting from main table`

  The server is executing the first part of a multiple-table delete. It is deleting only from the first table, and saving columns and offsets to be used for deleting from the other (reference) tables.

- `deleting from reference tables`

  The server is executing the second part of a multiple-table delete and deleting the matched rows from the other tables.

- `discard_or_import_tablespace`

  The thread is processing an `ALTER TABLE ... DISCARD TABLESPACE` or `ALTER TABLE ... IMPORT TABLESPACE` statement.

- `end`

  This occurs at the end but before the cleanup of [`ALTER TABLE`](sql-statements.html#alter-table), [`CREATE VIEW`](sql-statements.html#create-view), [`DELETE`](sql-statements.html#delete), [`INSERT`](sql-statements.html#insert), [`SELECT`](sql-statements.html#select), or [`UPDATE`](sql-statements.html#update) statements.

- `executing`

  The thread has begun executing a statement.

- `Execution of init_command`

  The thread is executing statements in the value of the `init_command` system variable.

- `freeing items`

  The thread has executed a command. Some freeing of items done during this state involves the query cache. This state is usually followed by `cleaning up`.

- `FULLTEXT initialization`

  The server is preparing to perform a natural-language full-text search.

- `init`

  This occurs before the initialization of [`ALTER TABLE`](sql-statements.html#alter-table), [`DELETE`](sql-statements.html#delete), [`INSERT`](sql-statements.html#insert), [`SELECT`](sql-statements.html#select), or [`UPDATE`](sql-statements.html#update) statements. Actions taken by the server in this state include flushing the binary log, the `InnoDB` log, and some query cache cleanup operations.

  For the `end` state, the following operations could be happening:

  - Removing query cache entries after data in a table is changed
  - Writing an event to the binary log
  - Freeing memory buffers, including for blobs

- `Killed`

  Someone has sent a [`KILL`](sql-statements.html#kill) statement to the thread and it should abort next time it checks the kill flag. The flag is checked in each major loop in MySQL, but in some cases it might still take a short time for the thread to die. If the thread is locked by some other thread, the kill takes effect as soon as the other thread releases its lock.

- `logging slow query`

  The thread is writing a statement to the slow-query log.

- `login`

  The initial state for a connection thread until the client has been authenticated successfully.

- `manage keys`

  The server is enabling or disabling a table index.

- `NULL`

  This state is used for the [`SHOW PROCESSLIST`](sql-statements.html#show-processlist) state.

- `Opening tables`

  The thread is trying to open a table. This is should be very fast procedure, unless something prevents opening. For example, an [`ALTER TABLE`](sql-statements.html#alter-table) or a [`LOCK TABLE`](sql-statements.html#lock-tables) statement can prevent opening a table until the statement is finished. It is also worth checking that your [`table_open_cache`](server-administration.html#sysvar_table_open_cache) value is large enough.

- `optimizing`

  The server is performing initial optimizations for a query.

- `preparing`

  This state occurs during query optimization.

- `Purging old relay logs`

  The thread is removing unneeded relay log files.

- `query end`

  This state occurs after processing a query but before the `freeing items` state.

- `Receiving from client`

  The server is reading a packet from the client. This state is called `Reading from net` prior to MySQL 5.7.8.

- `Removing duplicates`

  The query was using [`SELECT DISTINCT`](sql-statements.html#select) in such a way that MySQL could not optimize away the distinct operation at an early stage. Because of this, MySQL requires an extra stage to remove all duplicated rows before sending the result to the client.

- `removing tmp table`

  The thread is removing an internal temporary table after processing a [`SELECT`](sql-statements.html#select) statement. This state is not used if no temporary table was created.

- `rename`

  The thread is renaming a table.

- `rename result table`

  The thread is processing an [`ALTER TABLE`](sql-statements.html#alter-table) statement, has created the new table, and is renaming it to replace the original table.

- `Reopen tables`

  The thread got a lock for the table, but noticed after getting the lock that the underlying table structure changed. It has freed the lock, closed the table, and is trying to reopen it.

- `Repair by sorting`

  The repair code is using a sort to create indexes.

- `preparing for alter table`

  The server is preparing to execute an in-place [`ALTER TABLE`](sql-statements.html#alter-table).

- `Repair done`

  The thread has completed a multithreaded repair for a `MyISAM` table.

- `Repair with keycache`

  The repair code is using creating keys one by one through the key cache. This is much slower than `Repair by sorting`.

- `Rolling back`

  The thread is rolling back a transaction.

- `Saving state`

  For `MyISAM` table operations such as repair or analysis, the thread is saving the new table state to the `.MYI` file header. State includes information such as number of rows, the `AUTO_INCREMENT` counter, and key distributions.

- `Searching rows for update`

  The thread is doing a first phase to find all matching rows before updating them. This has to be done if the [`UPDATE`](sql-statements.html#update) is changing the index that is used to find the involved rows.

- `Sending data`

  The thread is reading and processing rows for a [`SELECT`](sql-statements.html#select) statement, and sending data to the client. Because operations occurring during this state tend to perform large amounts of disk access (reads), it is often the longest-running state over the lifetime of a given query.

- `Sending to client`

  The server is writing a packet to the client. This state is called `Writing to net` prior to MySQL 5.7.8.

- `setup`

  The thread is beginning an [`ALTER TABLE`](sql-statements.html#alter-table) operation.

- `Sorting for group`

  The thread is doing a sort to satisfy a `GROUP BY`.

- `Sorting for order`

  The thread is doing a sort to satisfy an `ORDER BY`.

- `Sorting index`

  The thread is sorting index pages for more efficient access during a `MyISAM` table optimization operation.

- `Sorting result`

  For a [`SELECT`](sql-statements.html#select) statement, this is similar to `Creating sort index`, but for nontemporary tables.

- `statistics`

  The server is calculating statistics to develop a query execution plan. If a thread is in this state for a long time, the server is probably disk-bound performing other work.

- `System lock`

  The thread has called `mysql_lock_tables()` and the thread state has not been updated since. This is a very general state that can occur for many reasons.

  For example, the thread is going to request or is waiting for an internal or external system lock for the table. This can occur when [`InnoDB`](innodb-storage-engine.html) waits for a table-level lock during execution of [`LOCK TABLES`](sql-statements.html#lock-tables). If this state is being caused by requests for external locks and you are not using multiple [**mysqld**](programs.html#mysqld) servers that are accessing the same [`MyISAM`](storage-engines.html#myisam-storage-engine) tables, you can disable external system locks with the [`--skip-external-locking`](server-administration.html#option_mysqld_external-locking) option. However, external locking is disabled by default, so it is likely that this option will have no effect. For [`SHOW PROFILE`](sql-statements.html#show-profile), this state means the thread is requesting the lock (not waiting for it).

- `update`

  The thread is getting ready to start updating the table.

- `Updating`

  The thread is searching for rows to update and is updating them.

- `updating main table`

  The server is executing the first part of a multiple-table update. It is updating only the first table, and saving columns and offsets to be used for updating the other (reference) tables.

- `updating reference tables`

  The server is executing the second part of a multiple-table update and updating the matched rows from the other tables.

- `User lock`

  The thread is going to request or is waiting for an advisory lock requested with a [`GET_LOCK()`](functions.html#function_get-lock) call. For [`SHOW PROFILE`](sql-statements.html#show-profile), this state means the thread is requesting the lock (not waiting for it).

- `User sleep`

  The thread has invoked a [`SLEEP()`](functions.html#function_sleep) call.

- `Waiting for commit lock`

  [`FLUSH TABLES WITH READ LOCK`](sql-statements.html#flush-tables-with-read-lock) is waiting for a commit lock.

- `Waiting for global read lock`

  [`FLUSH TABLES WITH READ LOCK`](sql-statements.html#flush-tables-with-read-lock) is waiting for a global read lock or the global [`read_only`](server-administration.html#sysvar_read_only) system variable is being set.

- `Waiting for tables`

  The thread got a notification that the underlying structure for a table has changed and it needs to reopen the table to get the new structure. However, to reopen the table, it must wait until all other threads have closed the table in question.

  This notification takes place if another thread has used [`FLUSH TABLES`](sql-statements.html#flush-tables) or one of the following statements on the table in question: `FLUSH TABLES *`tbl_name`*`, [`ALTER TABLE`](sql-statements.html#alter-table), [`RENAME TABLE`](sql-statements.html#rename-table), [`REPAIR TABLE`](sql-statements.html#repair-table), [`ANALYZE TABLE`](sql-statements.html#analyze-table), or [`OPTIMIZE TABLE`](sql-statements.html#optimize-table).

- `Waiting for table flush`

  The thread is executing [`FLUSH TABLES`](sql-statements.html#flush-tables) and is waiting for all threads to close their tables, or the thread got a notification that the underlying structure for a table has changed and it needs to reopen the table to get the new structure. However, to reopen the table, it must wait until all other threads have closed the table in question.

  This notification takes place if another thread has used [`FLUSH TABLES`](sql-statements.html#flush-tables) or one of the following statements on the table in question: `FLUSH TABLES *`tbl_name`*`, [`ALTER TABLE`](sql-statements.html#alter-table), [`RENAME TABLE`](sql-statements.html#rename-table), [`REPAIR TABLE`](sql-statements.html#repair-table), [`ANALYZE TABLE`](sql-statements.html#analyze-table), or [`OPTIMIZE TABLE`](sql-statements.html#optimize-table).

- `Waiting for *`lock_type`* lock`

  The server is waiting to acquire a `THR_LOCK` lock or a lock from the metadata locking subsystem, where *`lock_type`* indicates the type of lock.

  This state indicates a wait for a `THR_LOCK`:

  - `Waiting for table level lock`

  These states indicate a wait for a metadata lock:

  - `Waiting for event metadata lock`
  - `Waiting for global read lock`
  - `Waiting for schema metadata lock`
  - `Waiting for stored function metadata lock`
  - `Waiting for stored procedure metadata lock`
  - `Waiting for table metadata lock`
  - `Waiting for trigger metadata lock`

  For information about table lock indicators, see [Section 8.11.1, “Internal Locking Methods”](optimization.html#internal-locking). For information about metadata locking, see [Section 8.11.4, “Metadata Locking”](optimization.html#metadata-locking). To see which locks are blocking lock requests, use the Performance Schema lock tables described at [Section 25.12.12, “Performance Schema Lock Tables”](performance-schema.html#performance-schema-lock-tables).

- `Waiting on cond`

  A generic state in which the thread is waiting for a condition to become true. No specific state information is available.

- `Writing to net`

  The server is writing a packet to the network. This state is called `Sending to client` as of MySQL 5.7.8.

### 8.14.3 Query Cache Thread States



These thread states are associated with the query cache (see [Section 8.10.3, “The MySQL Query Cache”](optimization.html#query-cache)).

- `checking privileges on cached query`

  The server is checking whether the user has privileges to access a cached query result.

- `checking query cache for query`

  The server is checking whether the current query is present in the query cache.

- `invalidating query cache entries`

  Query cache entries are being marked invalid because the underlying tables have changed.

- `sending cached result to client`

  The server is taking the result of a query from the query cache and sending it to the client.

- `storing result in query cache`

  The server is storing the result of a query in the query cache.

- `Waiting for query cache lock`

  This state occurs while a session is waiting to take the query cache lock. This can happen for any statement that needs to perform some query cache operation, such as an [`INSERT`](sql-statements.html#insert) or [`DELETE`](sql-statements.html#delete) that invalidates the query cache, a [`SELECT`](sql-statements.html#select) that looks for a cached entry, [`RESET QUERY CACHE`](sql-statements.html#reset), and so forth.

### 8.14.4 Replication Master Thread States



The following list shows the most common states you may see in the `State` column for the master's `Binlog Dump` thread. If you see no `Binlog Dump` threads on a master server, this means that replication is not running; that is, that no slaves are currently connected.

- `Finished reading one binlog; switching to next binlog`

  The thread has finished reading a binary log file and is opening the next one to send to the slave.

- `Master has sent all binlog to slave; waiting for more updates`

  The thread has read all remaining updates from the binary logs and sent them to the slave. The thread is now idle, waiting for new events to appear in the binary log resulting from new updates occurring on the master.

- `Sending binlog event to slave`

  Binary logs consist of *events*, where an event is usually an update plus some other information. The thread has read an event from the binary log and is now sending it to the slave.

- `Waiting to finalize termination`

  A very brief state that occurs as the thread is stopping.

### 8.14.5 Replication Slave I/O Thread States



The following list shows the most common states you see in the `State` column for a slave server I/O thread. This state also appears in the `Slave_IO_State` column displayed by [`SHOW SLAVE STATUS`](sql-statements.html#show-slave-status), so you can get a good view of what is happening by using that statement.

- `Checking master version`

  A state that occurs very briefly, after the connection to the master is established.

- `Connecting to master`

  The thread is attempting to connect to the master.

- `Queueing master event to the relay log`

  The thread has read an event and is copying it to the relay log so that the SQL thread can process it.

- `Reconnecting after a failed binlog dump request`

  The thread is trying to reconnect to the master.

- `Reconnecting after a failed master event read`

  The thread is trying to reconnect to the master. When connection is established again, the state becomes `Waiting for master to send event`.

- `Registering slave on master`

  A state that occurs very briefly after the connection to the master is established.

- `Requesting binlog dump`

  A state that occurs very briefly, after the connection to the master is established. The thread sends to the master a request for the contents of its binary logs, starting from the requested binary log file name and position.

- `Waiting for its turn to commit`

  A state that occurs when the slave thread is waiting for older worker threads to commit if [`slave_preserve_commit_order`](replication.html#sysvar_slave_preserve_commit_order) is enabled.

- `Waiting for master to send event`

  The thread has connected to the master and is waiting for binary log events to arrive. This can last for a long time if the master is idle. If the wait lasts for [`slave_net_timeout`](replication.html#sysvar_slave_net_timeout) seconds, a timeout occurs. At that point, the thread considers the connection to be broken and makes an attempt to reconnect.

- `Waiting for master update`

  The initial state before `Connecting to master`.

- `Waiting for slave mutex on exit`

  A state that occurs briefly as the thread is stopping.

- `Waiting for the slave SQL thread to free enough relay log space`

  You are using a nonzero [`relay_log_space_limit`](replication.html#sysvar_relay_log_space_limit) value, and the relay logs have grown large enough that their combined size exceeds this value. The I/O thread is waiting until the SQL thread frees enough space by processing relay log contents so that it can delete some relay log files.

- `Waiting to reconnect after a failed binlog dump request`

  If the binary log dump request failed (due to disconnection), the thread goes into this state while it sleeps, then tries to reconnect periodically. The interval between retries can be specified using the [`CHANGE MASTER TO`](sql-statements.html#change-master-to) statement.

- `Waiting to reconnect after a failed master event read`

  An error occurred while reading (due to disconnection). The thread is sleeping for the number of seconds set by the [`CHANGE MASTER TO`](sql-statements.html#change-master-to) statement (default 60) before attempting to reconnect.

### 8.14.6 Replication Slave SQL Thread States



The following list shows the most common states you may see in the `State` column for a slave server SQL thread:

- `Killing slave`

  The thread is processing a `STOP SLAVE` statement.

- `Making temporary file (append) before replaying LOAD DATA INFILE`

  The thread is executing a [`LOAD DATA`](sql-statements.html#load-data) statement and is appending the data to a temporary file containing the data from which the slave will read rows.

- `Making temporary file (create) before replaying LOAD DATA INFILE`

  The thread is executing a [`LOAD DATA`](sql-statements.html#load-data) statement and is creating a temporary file containing the data from which the slave will read rows. This state can only be encountered if the original [`LOAD DATA`](sql-statements.html#load-data) statement was logged by a master running a version of MySQL lower than MySQL 5.0.3.

- `Reading event from the relay log`

  The thread has read an event from the relay log so that the event can be processed.

- `Slave has read all relay log; waiting for more updates`

  The thread has processed all events in the relay log files, and is now waiting for the I/O thread to write new events to the relay log.

- `Waiting for an event from Coordinator`

  Using the multithreaded slave ([`slave_parallel_workers`](replication.html#sysvar_slave_parallel_workers) is greater than 1), one of the slave worker threads is waiting for an event from the coordinator thread.

- `Waiting for slave mutex on exit`

  A very brief state that occurs as the thread is stopping.

- `Waiting for Slave Workers to free pending events`

  This waiting action occurs when the total size of events being processed by Workers exceeds the size of the [`slave_pending_jobs_size_max`](replication.html#sysvar_slave_pending_jobs_size_max) system variable. The Coordinator resumes scheduling when the size drops below this limit. This state occurs only when [`slave_parallel_workers`](replication.html#sysvar_slave_parallel_workers) is set greater than 0.

- `Waiting for the next event in relay log`

  The initial state before `Reading event from the relay log`.

- `Waiting until MASTER_DELAY seconds after master executed event`

  The SQL thread has read an event but is waiting for the slave delay to lapse. This delay is set with the `MASTER_DELAY` option of [`CHANGE MASTER TO`](sql-statements.html#change-master-to).

The `Info` column for the SQL thread may also show the text of a statement. This indicates that the thread has read an event from the relay log, extracted the statement from it, and may be executing it.

### 8.14.7 Replication Slave Connection Thread States



These thread states occur on a replication slave but are associated with connection threads, not with the I/O or SQL threads.

- `Changing master`

  The thread is processing a [`CHANGE MASTER TO`](sql-statements.html#change-master-to) statement.

- `Killing slave`

  The thread is processing a `STOP SLAVE` statement.

- `Opening master dump table`

  This state occurs after `Creating table from master dump`.

- `Reading master dump table data`

  This state occurs after `Opening master dump table`.

- `Rebuilding the index on master dump table`

  This state occurs after `Reading master dump table data`.

### 8.14.8 NDB Cluster Thread States



- `Committing events to binlog`

- `Opening mysql.ndb_apply_status`

- `Processing events`

  The thread is processing events for binary logging.

- `Processing events from schema table`

  The thread is doing the work of schema replication.

- `Shutting down`

- `Syncing ndb table schema operation and binlog`

  This is used to have a correct binary log of schema operations for NDB.

- `Waiting for allowed to take ndbcluster global schema lock`

  The thread is waiting for permission to take a global schema lock.

- `Waiting for event from ndbcluster`

  The server is acting as an SQL node in an NDB Cluster, and is connected to a cluster management node.

- `Waiting for first event from ndbcluster`

- `Waiting for ndbcluster binlog update to reach current position`

- `Waiting for ndbcluster global schema lock`

  The thread is waiting for a global schema lock held by another thread to be released.

- `Waiting for ndbcluster to start`

- `Waiting for schema epoch`

  The thread is waiting for a schema epoch (that is, a global checkpoint).

### 8.14.9 Event Scheduler Thread States



These states occur for the Event Scheduler thread, threads that are created to execute scheduled events, or threads that terminate the scheduler.

- `Clearing`

  The scheduler thread or a thread that was executing an event is terminating and is about to end.

- `Initialized`

  The scheduler thread or a thread that will execute an event has been initialized.

- `Waiting for next activation`

  The scheduler has a nonempty event queue but the next activation is in the future.

- `Waiting for scheduler to stop`

  The thread issued `SET GLOBAL event_scheduler=OFF` and is waiting for the scheduler to stop.

- `Waiting on empty queue`

  The scheduler's event queue is empty and it is sleeping.

------

| [Prev](backup-and-recovery.html) | [Up]()             | [Next](language-structure.html) |
| -------------------------------- | ------------------ | ------------------------------- |
| Chapter 7 Backup and Recovery    | [Home](index.html) | Chapter 9 Language Structure    |