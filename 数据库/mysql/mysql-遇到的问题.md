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

