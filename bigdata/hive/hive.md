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