[hive官网文档](https://cwiki.apache.org/confluence/display/Hive/Tutorial)

hive 分隔符默认值
| 类型 | 默认分隔符        |
| ---- | ----------------- |
| 字段 | ASCII 001(ctrl-A) |
| 行   | \n                |

hive字段分隔符默认 ASCII 001(ctrl-A), 行分隔符\n.
hive行分隔符不能修改，因为行分隔符取决于hadoop.

## 动态分区设置
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict; 
SET hive.exec.max.dynamic.partitions=10000;
SET hive.exec.max.dynamic.partitions.pernode=1000;

set hive.execution.engine=spark;
set spark.app.name=your_app_name;
set spark.executor.memory=5g;
set spark.executor.cores=1;
set spark.executor.instances=15;
set spark.drive.extraJavaOptions=-XX:+UseG1GC
set spark.executor.extraJavaOptions=-XX:+UseG1GC

## hive order by null
* order by col [asc] : null 放在最前面,让null放在最后面的方法:
        * order by col nulls last
        * order by nvl(col,一个最大的值)
* order by col desc : null 默认放在末尾,让null放在最前面的方法:
        * order by col desc




## 开启update delete [参考](https://blog.csdn.net/suijiarui/article/details/51174406)
set hive.txn.manager=org.apache.hadoop.hive.ql.lockmgr.DbTxnManager;

* 必须分桶不排序, orc格式, 设置事务属性
* update单列,不支持

        create table student(
        id int,
        name String,
        sex varchar(2),
        birthday varchar(10),
        major varchar(1)
        )
        clustered by (id) into 2 buckets 
        stored as orc 
        TBLPROPERTIES('transactional'='true');


## [hive jdbc格式](https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Clients)
具体参考官网
Connection URL Format
The HiveServer2 URL is a string with the following syntax:

        jdbc:hive2://<host1>:<port1>,<host2>:<port2>/dbName;initFile=<file>;sess_var_list?hive_conf_list#hive_var_list

where

<host1>:<port1>,<host2>:<port2> is a server instance or a comma separated list of server instances to connect to (if dynamic service discovery is enabled). If empty, the embedded server will be used.
dbName is the name of the initial database.
<file> is the path of init script file (Hive 2.2.0 and later). This script file is written with SQL statements which will be executed automatically after connection. This option can be empty. 
sess_var_list is a semicolon separated list of key=value pairs of session variables (e.g., user=foo;password=bar).
hive_conf_list is a semicolon separated list of key=value pairs of Hive configuration variables for this session
hive_var_list is a semicolon separated list of key=value pairs of Hive variables for this session.