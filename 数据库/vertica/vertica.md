创建临时表：
create local temporary table tmp_your_table
on commit preserver rows as
select *
from your table
where 1=2
segmented by hash(some_col) all nodes;


vertica 类型转换
* 字符串->日期 to_date('str','YYYY-MM-DD')

vertica 时间差
* 距某个时间的毫秒数 timestamp('ms'),date('1970-01-01',to_date('2018-01-01','YYYY-MM-DD'))



## vertica 字符串连接：
    select '1' || 'a' || 5;

## vertica 该表名  
    ALTER TABLE db.tbl_nm RENAME TO tbl_nm1; 
注意：在vertica中`rename to`后面的参数不能带库名。

