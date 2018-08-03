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

es用long代表date, 从mysql中读取的datetime或者timestamp在DO中用 `java.sql.Timestamp` 代表日期, 出给ES的bean的日期类型也用`java.sql.Timestamp`表示.

