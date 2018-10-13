# 索引
* 创建索引(库) `curl -XPUT "localhost:9200/index_nm?pretty"`
* 索引数据(插入数据) 
     `curl -XPOST "localhost:9200/test/user/1" -d "{\"user_name\":\"xiaoming\"}"`  
      windows下 json内部数据要用转义双引号,并用双引号包着json数据
      
* 获取索引     `curl -XGET ‘http://localhost:9200/{index}/{type}/{id}’`
* 删除索引    `curl -XDELETE http://localhost:9200/{index}`   [官网文档](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/_deleting_documents.html)
* 查看索引结构    浏览器 `localhost:9200/index_name/_mapping?pretty`
* 查看es所有的索引： `curl -XGET 127.0.0.1:9200/_cat/indices`
## curl localhost:9200/_cat

# 数据
## 删除数据
简单删除(根据id)

    curl -XDELETE 'http://localhost:9200/{index}/{type}/{id}?timeout=5m'

按照查询条件删除数据  数据量很大时，执行很慢，cpu使用率很高
```
kinbana
    curl -XPOST 'localhost:9200/{index}/{type}/_delete_by_query?pretty' -d'
    {
    "query": { 
        "query_string": {
        "message": "some message"
        }
    }
    }'
curl
 curl -XPOST "http:ip:9200/db/table/delete_by_query?conflicts=proceed" -H 'Content-Type: application/json' -d'
 {
     "query":{
         "match_all":{}
     }
 }'
```

## [删除类型(类似数据库的表))](https://blog.csdn.net/youzhouliu/article/details/79940729)

    POST edemo/test/_delete_by_query?conflicts=proceed
    {
    "query": {
        "match_all": {}
    }
    }


