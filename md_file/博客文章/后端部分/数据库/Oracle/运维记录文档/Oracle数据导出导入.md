# Orace数据导入导出操作





### 导入导出

```sql
一、创建逻辑目录，该命令不会在操作系统创建真正的目录，最好以system等管理员创建。
        create directory dpdata as '/opt';
二、查看管理理员目录（同时查看操作系统是否存在，因为Oracle并不关心该目录是否存在，如果不存在，则出错）
        select * from dba_directories;
三、给scott用户赋予在指定目录的操作权限，最好以system等管理员赋予。
        grant read,write on directory dpdata1 to scott;
 
四、导出数据
1)按用户导
        expdp scott/tiger@orcl schemas=scott dumpfile=expdp.dmp DIRECTORY=dpdata1;
2)并行进程parallel
        expdp scott/tiger@orcl directory=dpdata1 dumpfile=scott3.dmp parallel=40 job_name=scott3
3)按表名导
        expdp scott/tiger@orcl TABLES=emp,dept dumpfile=expdp.dmp DIRECTORY=dpdata1;
4)按查询条件导
        expdp scott/tiger@orcl directory=dpdata1 dumpfile=expdp.dmp Tables=emp query='WHERE deptno=20';
5)按表空间导
        expdp system/manager DIRECTORY=dpdata1 DUMPFILE=tablespace.dmp TABLESPACES=temp,example;
6)导整个
        expdp system/manager DIRECTORY=dpdata1 DUMPFILE=full.dmp FULL=y;
 
五、还原数据
1)导到指定用户下
        impdp scott/tiger DIRECTORY=dpdata1 DUMPFILE=expdp.dmp SCHEMAS=scott;
2)改变表的owner
        impdp system/manager DIRECTORY=dpdata1 DUMPFILE=expdp.dmp TABLES=scott.dept REMAP_SCHEMA=scott:system;
3)导入表空间
        impdp system/manager DIRECTORY=dpdata1 DUMPFILE=tablespace.dmp TABLESPACES=example;
4)导入数据库
        impdb system/manager DIRECTORY=dump_dir DUMPFILE=full.dmp FULL=y;
5)追加数据
        impdp system/manager DIRECTORY=dpdata1 DUMPFILE=expdp.dmp SCHEMAS=system TABLE_EXISTS_ACTION
六、参数说明-----导入impdp
1、TABBLE_EXISTS_ACTION={SKIP | APPEND | TRUNCATE | FRPLACE }
当设置该选项为SKIP时,导入作业会跳过已存在表处理下一个对象;当设置为APPEND时,会追加数据,为TRUNCATE时,导入作业会截断表,然后为其追加新数据;当设置为REPLACE时,导入作业会删除已存在表,重建表病追加数据,注意,TRUNCATE选项不适用与簇表和NETWORK_LINK选项；
2、REMAP_SCHEMA
该选项用于将源方案的所有对象装载到目标方案中：REMAP_SCHEMA=source_schema:target_schema
3、REMAP_TABLESPACE
将源表空间的所有对象导入到目标表空间中：REMAP_TABLESPACE=source_tablespace:target:tablespace
4、REMAP_DATAFILE
该选项用于将源数据文件名转变为目标数据文件名,在不同平台之间搬移表空间时可能需要该选项.
REMAP_DATAFIEL=source_datafie:target_datafile
 
七、参数说明-----导出expdp
1、CONTENT：该选项用于指定要导出的内容.默认值为ALL
CONTENT={ALL | DATA_ONLY | METADATA_ONLY}
当设置CONTENT为ALL 时,将导出对象定义及其所有数据.为DATA_ONLY时,只导出对象数据,为METADATA_ONLY时,只导出对象定义
2、DIRECTORY：指定转储文件和日志文件所在的目录：DIRECTORY=directory_object
3、EXCLUDE：该选项用于指定执行操作时释放要排除对象类型或相关对象
        EXCLUDE=object_type[:name_clause] [,….]
        Object_type用于指定要排除的对象类型,name_clause用于指定要排除的具体对象.EXCLUDE和INCLUDE不能同时使用
        Expdp scott/tiger DIRECTORY=dump DUMPFILE=a.dup EXCLUDE=VIEW
4、INCLUDE：导出时包含指定的类型
        (例:INCLUDE=TABLE_DATA,
             INCLUDE=TABLE:"LIKE 'TAB%'"
             INCLUDE=TABLE:”NOT LIKE ‘TAB%’”…)
             EXCLUDE:导出时排除的数据类型(例:EXCLUDE=TABLE:EMP)
5、FILESIZE：指定导出文件的最大尺寸,默认为0,(表示文件尺寸没有限制)(单位为bytes).
6、JOB_NAME：此次导出进程使用的名称,方便跟踪查询(可选)
7、FLASHBACK_SCN：指定导出特定SCN时刻的表数据
        FLASHBACK_SCN=scn_value：Scn_value用于标识SCN值.FLASHBACK_SCN和FLASHBACK_TIME不能同时使用
Expdp scott/tiger DIRECTORY=dump DUMPFILE=a.dmp
        FLASHBACK_SCN=358523
8、FLASHBACK_TIME：指定导出特定时间点的表数据：FLASHBACK_TIME=“TO_TIMESTAMP(time_value)”
        Expdp scott/tiger DIRECTORY=dump DUMPFILE=a.dmp FLASHBACK_TIME=“TO_TIMESTAMP(’25-08-2004 14:35:00’,’DD-MM-YYYY HH24:MI:SS’)”
9、TABLESPACE：指定一个表空间导出.
10、QUERY=[schema.] [table_name:] query_clause
        Schema用于指定方案名,table_name用于指定表名,query_clause用于指定条件限制子句.QUERY选项不能与CONNECT=METADATA_ONLY,EXTIMATE_ONLY,TRANSPORT_TABLESPACES等选项同时使用.
        Expdp scott/tiger directory=dump dumpfiel=a.dmp Tables=emp query=’WHERE deptno=20’
 
11、PARALLEL：并行操作： 指定执行导出操作的并行进程个数,默认值为1
您可以通过PARALLEL 参数为导出使用一个以上的线程来显著地加速作业。每个线程创建一个单独的转储文件，因此参数dumpfile 应当拥有和并行度一样多的项目。您可以指定通配符作为文件名，而不是显式地输入各个文件名，例如：
expdp ananda/abc123 tables=CASES directory=DPDATA1 dumpfile=expCASES_%U.dmp parallel=4 job_name=Cases_Export
注意:dumpfile 参数拥有一个通配符%U，它指示文件将按需要创建，格式将为expCASES_nn.dmp，其中nn 从01 开始，然后按需要向上增加。
在并行模式下，状态屏幕将显示四个工作进程。（在默认模式下，只有一个进程是可见的）所有的工作进程同步取出数据，并在状态屏幕上显示它们的进度。
分离访问数据文件和转储目录文件的输入/输出通道是很重要的。否则，与维护Data Pump 作业相关的开销可能超过并行线程的效益，并因此而降低性能。并行方式只有在表的数量多于并行值并且表很大时才是有效的。

关于DIRECOTRY参数：
这个目录需要有sysdba用户创建，具体语法见本文最前面，创建完毕后，授权给其他用户使用，read ,write权限，然后创建物理目录
说到这里，有必要说明一下DB建立完毕后，系统有几个缺省的目录

SQL> select * from dba_directories;
OWNER                          DIRECTORY_NAME
------------------------------ ------------------------------
DIRECTORY_PATH
--------------------------------------------------------------------------------
SYS                            ORACLE_OCM_CONFIG_DIR
F:\app\Roman\product\11.2.0\dbhome_1/ccr/state
SYS                            DATA_PUMP_DIR
F:\app\Roman/admin/orcl/dpdump/

OWNER                          DIRECTORY_NAME
------------------------------ ------------------------------
DIRECTORY_PATH
--------------------------------------------------------------------------------
SYS                            XMLDIR
c:\ade\aime_dadvfh0169\oracle/rdbms/xml
DATA_PUMP_DIR这个目录，是系统缺省目录，如不单独指定目录，dmp文件会在这里，但默认这个目录其他用户是没有权限的


```


