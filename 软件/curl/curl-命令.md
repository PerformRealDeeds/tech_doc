 `curl -XPUT "localhost:9200/index_nm?pretty"`

     `curl -XPOST "localhost:9200/test/user/1" -d "{\"user_name\":\"xiaoming\"}"`  
      windows下 json内部数据要用转义双引号,并用双引号包着json数据
      
*      `curl -XGET ‘http://localhost:9200/{index}/{type}/{id}’`
*     `curl -XDELETE http://localhost:9200/{index}`   [官网文档](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/_deleting_documents.html)
