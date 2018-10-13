-- 建表语句
select export_objects('','db.tbl');

-- 改表名  注意： rename后面不能带库名
alter table db.table rename to table1; 