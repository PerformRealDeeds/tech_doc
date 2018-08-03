# 索引
* 创建索引 `curl -XPUT "localhost:9200/index_nm?pretty"`
* 索引数据(插入数据) 
     `curl -XPOST "localhost:9200/test/user/1" -d "{\"user_name\":\"xiaoming\"}"`  
      windows下 json内部数据要用转义双引号,并用双引号包着json数据
      
* 获取索引     `curl -XGET ‘http://localhost:9200/{index}/{type}/{id}’`
* 删除索引    `curl -XDELETE http://localhost:9200/{index}`   [官网文档](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/_deleting_documents.html)
* 查看索引结构    浏览器 `localhost:9200/index_name/_mapping?pretty`

## curl localhost:9200/_cat

# 数据
## 删除数据
简单删除(根据id)

    curl -XDELETE 'http://localhost:9200/{index}/{type}/{id}?timeout=5m'

按照查询条件删除数据

    curl -XPOST 'localhost:9200/{index}/{type}/_delete_by_query?pretty' -d'
    {
    "query": { 
        "query_string": {
        "message": "some message"
        }
    }
    }'

