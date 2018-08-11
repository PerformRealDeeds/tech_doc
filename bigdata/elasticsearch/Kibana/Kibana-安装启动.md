## [Kibana-安装启动] (https://www.elastic.co/downloads/kibana)
1. 下载并解压缩Kibana
2.  在编辑器中打开config / kibana.yml 设置elasticsearch.url为指向您的Elasticsearch实例
3.  运行`bin/kibana`（或`bin\kibana.bat`在Windows上）
4.  将浏览器指向`http//localhost：5601`   注意：如果是从远程访问，要修改`config/kibana.yml`下的`server.host: "your_local_ip"`
5. 关闭kibana后台， ps ux | grep /bin/node 然后kill -s 9 pid

   注意： kibana官网文档没有以服务启动的方法，考虑使用 `nohup bin/kibana &`