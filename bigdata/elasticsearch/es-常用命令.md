#索引
* 获取索引
   
    curl -XGET ‘http://localhost:9200/{index}/{type}/{id}’
* 索引数据(插入数据)
  
    curl -XPOST ‘http://localhost:9200/{index}/{type}/{id}’-d'{“a”:”avalue”,”b”:”bvalue”}'
* 删除索引
  
    curl -XDELETE http://localhost:9200/{index}
* 查看索引结构

    localhost:9200/index_name/_mapping?pretty

## curl localhost:9200/_cat

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
