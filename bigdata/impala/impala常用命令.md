## 启动impala-shell
impala-shell -i one_ip_addr

## [刷新元数据](https://www.cloudera.com/documentation/enterprise/5-8-x/topics/impala_refresh.html#refresh)
用hive建表后,需要在impala中用`INVALIDATE METADATA`刷新元数据才可以看到hive新建的表.

