[sqoop官方文档](http://sqoop.apache.org/docs/1.4.6/SqoopUserGuide.html#_using_command_aliases)

#清空mysql

    sh sqoop eval \
    --connect "jdbc:mysql://url:3306:/db?useUnicode=true&   characterEncoding=utf-8"
    --username usr  \
    --password psd \
    -e "truncate db.tbl";

# hive(text表)->mysql

    sh sqoop export \
    -D mapred.job.queue.name=queue1 \
    -D sqoop.export.records.per.statement=100 \
    -D sqoop.export.statements.per.transaction=60 \
    --connect "jdbc:mysql://url:3306/db" \
    --username usr \
    --password psd \
    --table tbl \
    --columns col1,col2,col3 \
    --update-key id \
    --update-mode allowinsert \
    --fields-terminated-by '\001' \
    --export-idr "hdfs://hive/warehouse/db/tbl" \
    --input-null-string '\\N' \
    --input-null-non-string '\\N' \
    -m 5;

columns:mysql中的列名，按照hive建表的顺序写
update-mode allowinsert:等效于mysql的replace
input-null-string:hive中以\\N表示关系数据库中的null
-m,--num-mappers <n>:mapreduce的并行数
--update-mode <mode>:updateonly (default) and allowinsert.
可选：
--validate 	Enable validation of data copied, supports single table copy only.  验证hive表的行数和mysql表的行数是否一致，适合全量导出，不适合增量导出。

sqoop使用多行insert，insert into tables values() values(),(),()... 默认一个insert语句100条记录，每100个语句当成一个事务。

# 列出mysql的库名
sh sqoop list-databases \
    --connect "jdbc:mysql://url:3306/db" \
    --username usr \
    --password psd 

# 列出mysql的表
sh sqoop list-tables \
    --connect "jdbc:mysql://url:3306/db" \
    --username usr \
    --password psd 


sqoop help # 显示帮助信息
sqoop version # 显示版本
