## hive timestamp加减小时
hive2.2中没有直接加减timestamp的函数，需要转成unix_timestamp,操作后在转成timestamp

当前时间加8个小时
    
        select from_unixtime(unix_timestamp(current_timestamp) + 8*3600);



