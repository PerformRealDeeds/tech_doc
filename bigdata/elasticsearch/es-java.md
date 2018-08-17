# [github spring-boot es例子](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-elasticsearch) 对es api的封装,更易用

## po
在po上写明索引(类似库名),类型(类似表名)

    @Document(indexName = "customer", type = "customer", shards = 1, replicas = 0, refreshInterval = "-1")
    public class Customer 

## mapper
String是id列的类型

    public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

## 多个条件搜索
Page<SomePo> findByAttr1AndAttr2AndAttr3BetweenAdnStartTmBetween(Sting attr1, Integer attr2, Integer attr3From, Integer attr3To, Long startTmFrom ,Long startTmTo)

* attr1 字符串
* attr2 整数
* attr3 范围
* startTm 范围 只接受long

null表示忽略这个条件，否则是多个条件的与