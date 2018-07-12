## 查询mysql执行语句历史
set global general_log='on'; -- 启动通用查询日志.包括mysql服务器收到的每条独立查询
set global log_output='table'; -- 查询历史输出到mysql表
select * from mysql.general_log;
select cast(argument as char(1000)) from mysql.general_log; -- 把blob转成char
set global general_log='off'; -- 注意: 最后要关闭

## mysql版本
select version();







