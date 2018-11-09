## 查询mysql执行语句历史
set global general_log='on'; -- 启动通用查询日志.包括mysql服务器收到的每条独立查询
set global log_output='table'; -- 查询历史输出到mysql表
select * from mysql.general_log;
select cast(argument as char(1000)) from mysql.general_log; -- 把blob转成char
set global general_log='off'; -- 注意: 最后要关闭

## mysql版本
select version();

## 怀疑并发线程影响了查询,用 `show [full] processlist;` 或者 `select * from information_schema.processlist;` 显示进程的状态

## 权限
* show grants for test_usr; -- 查看 test_usr的权限
* grant select,insert on db.* to test_usr@'%' ; -- 授予权限
* GRANT ALL ON db1.* TO 'jeffrey'@'localhost';
* grant all on newdb.* to newuser@localhost  identified by 'password';

## 查找表
show tables;
show tables like '%special_name%';
