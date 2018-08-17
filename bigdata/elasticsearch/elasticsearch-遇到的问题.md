##  curl 'localhost:9200'
curl在windows cmd中要使用双引号.

## es不能存储 yyyy-MM-dd dd:hh:ss的时间
`localhost:9200/doc_nm?pretty`可以看索引的类型
javabean中的是yyyy-MM-dd的字符串,存入后就变成日期了.

## https://www.cnblogs.com/Creator/p/3722408.html

## es日期   

## Date mapping issue - Invalid format … malformed
`ElasticsearchReposity<DO,Integer>`第二个泛型应该和DO中的@Id属性的类型一致.


[es date](https://www.elastic.co/guide/en/elasticsearch/reference/current/date.html)

es用long代表date, 从mysql中读取的datetime或者timestamp在DO中用 `java.sql.Timestamp` 代表日期, 给ES的bean的日期类型也用`java.sql.Timestamp`表示.

    Timestamp.valueOf(LocalDateTime.parse("2018-07-22T13:05:23"));


---

## Fielddata is disabled on text fields by default. set fielddata=true on [id] in order to load fielddata in memory by uninverting the inverted index. Note that this can however use significant memory. Alternatively use a keyword field instead.

    withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))

id是String,不要使用这个排序



## error:failed to load elasticsearch nodes : None of the configured nodes are available: {127.0.0.1}{127.0.0.1:9300}]
/config/elasticsearch.xml中`cluster.name` es集群的名字用默认的，改成别的要在spring-boot配置中指定。

## [don't run elasticsearch as root.](https://blog.csdn.net/mengfei86/article/details/51210093)

因为安全问题elasticsearch 不让用root用户直接运行，所以要创建新用户

第一步：liunx创建新用户  adduser XXX    然后给创建的用户加密码 passwd XXX    输入两次密码。

第二步：切换刚才创建的用户 su XXX  然后执行elasticsearch  会显示Permission denied 权限不足。

第三步：给新建的XXX赋权限，chmod 777 *  这个不行，因为这个用户本身就没有权限，肯定自己不能给自己付权限。所以要用root用户登录付权限。

第四步：root给XXX赋权限，chown -R XXX /你的elasticsearch安装目录。

然后执行成功。

## hive 写 es时 org.elasticsearch.hadoop.rest.EsHadoopInvalidRequest: Found unrecoverable error [XXX:9200] returned Bad Request(400) - failed to parse [XXX]; Bailing out..
https://blog.csdn.net/dpnice/article/details/80269853

删除、重建索引

或者在新的索引下建表，  同一个索引（库）下不能有两个同名不同类型的字段。
