## The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more than one time zone 
[参考](https://blog.csdn.net/qq_15653601/article/details/79940090)

    Caused by: com.mysql.cj.core.exceptions.InvalidConnectionAttributeException: The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.

        就是上面标红的SQL异常，我们需要在数据库 URL中设置serverTimezone属性：

    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB" +
            "?serverTimezone=GMT%2B8";
        这里的 GMT%2B8 代表是东八区。
        
原因:mysql8 时区没有设置
重现:mysql8 时区重置
在mysql中执行

    show variables like '%time_zone%';
    set global time_zone='+8:00';
原因: 为什么默认时区会报错?


## mysql datatime 2018-05-01 12:01:20 读取后 变成 2018-05-01 12:01:20.0   .0是怎么来的?

## mysql char(n) 存储长度小于n的数据没有空格
[mysql官网文档](https://dev.mysql.com/doc/refman/5.7/en/char.html)

The length of a CHAR column is fixed to the length that you declare when you create the table. The length can be any value from 0 to 255. When CHAR values are stored, they are right-padded with spaces to the specified length. When CHAR values are retrieved, trailing spaces are removed unless the PAD_CHAR_TO_FULL_LENGTH SQL mode is enabled.

    sql_mode的查看和设置
    查看sql_mode 的默认值
    show variables like 'sql_mode';
    select @@sql_mode;
    修改sql_mode的默认值
    set sql_mode='pad_char_to_full_length';
     可以同时设置多个值，中间用逗号隔开
    SET sql_mode='STRICT_ALL_TABLES,ERROR_FOR_DIVISION_BY_ZERO';

    ---------------------

    本文来自 waterxcfg304 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/waterxcfg304/article/details/37876755?utm_source=copy 