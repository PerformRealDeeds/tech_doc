https://blog.csdn.net/u013063153/article/details/60757307


add jar file:///home/liuxiaowen/elasticsearch-hadoop-2.2.0-beta1/dist/elasticsearch-hadoop-hive-2.2.0-beta1.jar;


CREATE EXTERNAL TABLE zc_test.ust_voice (
id string,
col_str string,
col_int  int,
col_tm   timestamp
 )
STORED BY 'org.elasticsearch.hadoop.hive.EsStorageHandler'
TBLPROPERTIES(
'es.nodes' = '10.75.211.157:9200',
'es.index.auto.create' = 'true',
'es.resource' = 'ust/voice',
'es.mapping.id' = 'id',
'es.mapping.names' = 'col_str:colStr,col_int:colInt,col_tm:colTm');

insert into zc_test.ust_test select 1,"abc",55,current_timestamp;

insert into zc_test.ust_test select 2,"bcd";

add jar file:/root/app/zhangce/lib/elasticsearch-hadoop-hive-5.6.10.jar;




