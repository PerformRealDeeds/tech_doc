## es hello world
1. [Download and unzip Elasticsearch](https://www.elastic.co/downloads/elasticsearch)
2. Run `bin/elasticsearch` (or `bin\elasticsearch.bat` on Windows)
3. Run `curl http://localhost:9200/` or `Invoke-RestMethod http://localhost:9200` with PowerShell


## [启动es 已zip或者targz为例](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/zip-targz.html)
### 1. 前台运行
1. cd elasticsearch-5.6.10/  # 进入后当前目录是$ES_HOME
2. ./bin/elasticsearch #前台运行,日志打印到`stdout`,ctrl+C停止
3. curl localhost:9200 #验证
### 2. daemon运行

1. 启动  `cd $$ES_HOME`, 执行`./bin/elasticsearch -d -p pid`    # 最好先用前台运行的方式运行一下，看看是否有什么问题
   * -d: 指定时daemon
   * -p: 记录进程id的文件名 
2. curl localhost:9200 #验证
3. kill `cat pid`   # 执行后pid文件被删除
## 调整jvm内存
在`conf/jvm.topions`中设置jvm堆大小, 默认
    -Xms2g
    -Xmx2g


