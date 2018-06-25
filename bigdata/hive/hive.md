[hive官网文档](https://cwiki.apache.org/confluence/display/Hive/Tutorial)

hive 分隔符默认值
| 类型 | 默认分隔符        |
| ---- | ----------------- |
| 字段 | ASCII 001(ctrl-A) |
| 行   | \n                |

hive字段分隔符默认 ASCII 001(ctrl-A), 行分隔符\n.
hive行分隔符不能修改，因为行分隔符取决于hadoop.

动态分区设置
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict; 
SET hive.exec.max.dynamic.partitions=10000;
SET hive.exec.max.dynamic.partitions.pernode=1000;

