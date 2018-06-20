[prestol连接kudu](https://github.com/MartinWeindel/presto-kudu)


-----
#kudu 命令
* 查看kudu表
    *    kudu table list your_master_address


---

# Impala：
启动impala命令行: 

    impala-shell –i 10.75.201.142

## 建impala外部表（kudu已经存在的表）

    Create external table tbl1
    Stored as kudu
    Tblproperties(
    'kudu.table_name'='kudu_tbl_name'
    );

可以多个外部表映射同一张kudu表，curd这些表对kudu表生效。

Kudu修改表结构后impala外部表不会跟着更新，impala需要重新建表才会看到kudu的表结构修改。



Kudu不支持的数据类型
1.	DECIMAL, CHAR, VARCHAR, DATE, and complex types such as ARRAY are not supported.
2.	Type and nullability of existing columns cannot be changed by altering the table.\
3.	Columns with DOUBLE, FLOAT, or BOOL types are not allowed as part of a primary key definition.\



类型	              |   Kudu是否支持 |	Kudu java api是否支持 |	Impala是否支持|
  -                   |     -         |                       - |             -|
 Int8	              |   是	      |     是	           |            是     
 Int16	              |   是	      |     是	           |            是
 Int32	              |   是	      |     是	           |            是
 Int64	              |   是	      |     **否**         |            是
 binary	              |   是	      |     是	           |           **否**
 bool	              |   是	      |     是	           |            是
 float	              |   是	      |     是	           |            是
 double	              |   **否**	  |     **否**         |            
 Decimal	          |   是	      |     是	           |            是
 Unixtime_micros	  |   是	      |     否	           |            
 String	              |   是	      |     是	           |            是
			
			
			
			
			
			
10.75.201.142:8051  测试环境 kudu web ui


Kudu建库：可以使用impala建的库; 
Kudu建表：要指定库名，否则kudu表和hive的库同级别

Kudu建表zc_test.test_kudu_type后，hadoop fs –ls /hive/warehouse/zc_test.db/test_kudu_type有目录，但是hdfs显示无数据，而用impala建外部表映射到kudu表查看显示有数据。


## Impala创建kudu表

1.普通建表语句

    CREATE TABLE zc_test.my_first_table
    (
      id BIGINT,
      name STRING,
      PRIMARY KEY(id)
    )
    PARTITION BY HASH PARTITIONS 3
    STORED AS KUDU;
注意：
* impala内部kudu表必须设置partition，并且分区数>1;
* impala内部kudu表的表名带前缀impala::db:tbl, 可以通过 *kudu table list your_master_address* 查看 或者 在impala-shell 中通过 *describe formatted zc_test.impala_kudu_tbl*
* presto查询带“:”的表报错，对于impala建的kudu内部表可以用 *ALTER TABLE impala_name SET TBLPROPERTIES('kudu.table_name' ='不带冒号的表名')* 去掉冒号。




2.create table as select 

    CREATE TABLE new_table
    PRIMARY KEY (ts, name)
    PARTITION BY HASH(name) PARTITIONS 8
    STORED AS KUDU
    AS SELECT ts, name, value FROM old_table;

3.分区表

    CREATE TABLE cust_behavior (
    _id BIGINT PRIMARY KEY,
    salary STRING,
    edu_level INT,
    usergender STRING,
    `group` STRING,
    city STRING,
    postcode STRING,
    last_purchase_price FLOAT,
    last_purchase_date BIGINT,
    category STRING,
    sku STRING,
    rating INT,
    fulfilled_date BIGINT
    )
    PARTITION BY RANGE (_id)
    (
        PARTITION VALUES < 1439560049342,
        PARTITION 1439560049342 <= VALUES < 1439566253755,
        PARTITION 1439566253755 <= VALUES < 1439572458168,
        PARTITION 1439572458168 <= VALUES < 1439578662581,
        PARTITION 1439578662581 <= VALUES < 1439584866994,
        PARTITION 1439584866994 <= VALUES < 1439591071407,
        PARTITION 1439591071407 <= VALUES
    )
    STORED AS KUDU;
4.表的重命名


## kudu分区

1.PARTITION BY RANGE

    CREATE TABLE customers (
      state STRING,
      name STRING,
      purchase_count int,
      PRIMARY KEY (state, name)
    )
    PARTITION BY RANGE (state)
    (
      PARTITION VALUE = 'al',
      PARTITION VALUE = 'ak',
      PARTITION VALUE = 'ar',
      -- ... etc ...
      PARTITION VALUE = 'wv',
      PARTITION VALUE = 'wy'
    )
    STORED AS KUDU;

如果按照唯一的主键分区，会造成新插的数据全部在一个tablet中，写负载重。

2.PARTITION BY HASH
分区主键最多出现一次 HASH(a), HASH(b) √     HASH(a,b) √   HASH(a), HASH(a,b) ×
PARTITION BY HASH不指定主键，默认按照所有主键的hash分区。

    CREATE TABLE cust_behavior (
      id BIGINT,
      sku STRING,
      salary STRING,
      edu_level INT,
      usergender STRING,
      `group` STRING,
      city STRING,
      postcode STRING,
      last_purchase_price FLOAT,
      last_purchase_date BIGINT,
      category STRING,
      rating INT,
      fulfilled_date BIGINT,
      PRIMARY KEY (id, sku)
    )
    PARTITION BY HASH PARTITIONS 16
    STORED AS KUDU;

数据均匀增加。
如果要查询一个sku值，要扫描全部16个tablet。

3.PARTITION BY HASH AND RANGE

    CREATE TABLE cust_behavior (
      id BIGINT,
      sku STRING,
      salary STRING,
      edu_level INT,
      usergender STRING,
      `group` STRING,
      city STRING,
      postcode STRING,
      last_purchase_price FLOAT,
      last_purchase_date BIGINT,
      category STRING,
      rating INT,
      fulfilled_date BIGINT,
      PRIMARY KEY (id, sku)
    )
    PARTITION BY HASH (id) PARTITIONS 4,
    RANGE (sku)
    (
      PARTITION VALUES < 'g',
      PARTITION 'g' <= VALUES < 'o',
      PARTITION 'o' <= VALUES < 'u',
      PARTITION 'u' <= VALUES
    )
    STORED AS KUDU;

上面的表共有4*4个分区。 
优点：查询sku值快一些，只用查询部分tablet

4.Multiple PARTITION BY HASH Definitions

CREATE TABLE cust_behavior (
  id BIGINT,
  sku STRING,
  salary STRING,
  edu_level INT,
  usergender STRING,
  `group` STRING,
  city STRING,
  postcode STRING,
  last_purchase_price FLOAT,
  last_purchase_date BIGINT,
  category STRING,
  rating INT,
  fulfilled_date BIGINT,
  PRIMARY KEY (id, sku)
)
PARTITION BY HASH (id) PARTITIONS 4,
             HASH (sku) PARTITIONS 4
STORED AS KUDU;

共有4*4个桶，与 HASH (id, sku) PARTITIONS 16 的区别是， 查询sku值时后者要扫描16个tablet，而前者扫描4个tablet.

## kudu插入

1. impala单条插入  启动开销大
2. impala批量插入  性能较好    impala根据集群设置，set batch_size=10000; batch_size更大意味着更大的内存
3. impala文件插入 
   INSERT INTO my_kudu_table
   SELECT * FROM legacy_data_import_table;
4. C++ or Java API 插入     插入kudu表后impala直接可见
5. upsert = insert + update

        INSERT INTO my_first_table VALUES (99, "sarah");
        UPSERT INTO my_first_table VALUES (99, "zoe");
        -- the current value of the row is 'zoe'

## kudu更新
语法：
    UPDATE my_first_table SET name="bob" where id = 3;
impala中update只能用于kudu表。
update多条：

    UPDATE my_first_table SET name="bob" where age > 10;

## kudu删除
    DELETE FROM my_first_table WHERE id < 3;
    DELETE c FROM my_second_table c, stock_symbols s WHERE c.name = s.symbol;
    DELETE FROM my_first_table WHERE id < 3;

## crud失败
impala中的INSERT, UPDATE, 和 DELETE 不是事务操作。


## 修改表的属性
1.重命名映射
    ALTER TABLE my_table RENAME TO my_new_table;

无论是内部表还是外部表，impala中的 alter rename 只是修改映射，底层的kudu表的表名不变。 这样做是为了
防止直接访问kudu表的应用报错。 可以在bash中通过kudu table list 10.75.201.142

2.impala内部表重命名底层kudu表
ALTER TABLE my_first_table
SET TBLPROPERTIES('kudu.table_name' = 'zc_test.my_first_table1');
执行后impala表明不变，底层kudu表名变化。

3.impala外部表重新映射底层kudu表
ALTER TABLE my_external_table_
SET TBLPROPERTIES('kudu.table_name' = 'some_other_kudu_table');

4.修改kudu表master地址
ALTER TABLE my_table
SET TBLPROPERTIES('kudu.master_addresses' = 'kudu-new-master.example.com:7051');

5.内部表改为外部表
    ALTER TABLE my_table SET TBLPROPERTIES('EXTERNAL' = 'TRUE');

6.drop impala内部表会删除底层kudu表和所有数据， drop impala外部表会删除impala和kudu的映射，底层的kudu表和数据不变。

## 注意事项
1. 建kudu表必须指定主键
2. 不能更新主键
3. impala中的!= 和 like不会推到kudu执行，由impala的扫描模式决定。 impala中的operators =, <=, '\<', '\>', >=, BETWEEN, or IN会推到kudup评估，只返回给impala符合条件的结果集。
4. impalad的insert,update,delete不是事务操作，不会回滚。
5. 单个单个表的并行度由单个表tablets的数量决定。

# kudu表设计
## 主键
1. kudu表的主键是聚集索引，非空，不能是boolean,float,double. 
2. 建完kudu表后不能修改主键。
3. kudu没有自增主键。


## 编码
kudu基于字段类型编码数据。

| Column Type               | Encoding                      | Default |
|      -                    |      -                        |   -     |
int8, int16, int32        |	plain, bitshuffle, run length	| bitshuffle
int64, unixtime_micros	  | plain, bitshuffle, run length	| bitshuffle
float, double, decimal	  |  plain, bitshuffle	            |  bitshuffle
bool	                  |  plain, run length              |	run length
string, binary	          |  plain, prefix, dictionary	    |  dictionary

* plain： 原始数据格式。 例如：int32 存的是固定32位小端整数。
* bigshuffle: LZ4压缩
* runlength:  存储值和重复的次数。 例子：boolean
* dictionary: string和binary类型中重复次数多的值会被存储成dictionary，如果值的独立性比较高，就会按照plain存储。
* prefix: 前缀编码。

## 分区
### 范围分区
* 可以动态添加，删除


### hash分区
1. 适合随机访问，随机写，避免热点和不均衡的数据分布

### schema修改
* Rename the table
* Rename primary key columns
* Rename, add, or drop non-primary key columns
* Add and drop range partitions



# kudu命令
* kudu cluster ksck <master_addresses> [-checksum_cache_blocks] [-checksum_scan] [-checksum_scan_concurrency=<concurrency>] [-nochecksum_snapshot] [-checksum_timeout_sec=<sec>] [-color=<color>] [-noconsensus] [-tables=<tables>] [-tablets=<tablets>] [-verbose]  检查master健康状况 <>必填  []表示可省略
*  

#impala

------

# 问题
环境：impala2.9  hive2.2
impala可以读hive建的ods表，不能读hive建的orc表。
presto不能读impala建的kudu表。


* presto能读kudu jdbc建立的kudu表吗？  能
* presto能读impala建立的kudu 内部表吗？ 不能。  presto0.189 kudu1.4 impala     

* presto0.189 datagrid2018.1.4 用 jdbc delte或者insert hive orc表执行完后会卡主，其实已经执行完毕。

* 在preto中 hive orc表和kudu jdbc表可以join


[kudu java jdbc操作kudu](https://github.com/cloudera/kudu/tree/master/examples/java/java-example)

[impala java jdbc操作kudu]



### impala导出kudu数据成txt文件 适用于T+1

    impala-shell -B --output_delimiter '\001' -i kudu_demo_ip -f your_file -o your_output_file



### impala直接



## [spark读kudu](https://www.jianshu.com/p/ef1a621fc6ea)
1. pom依赖

  <!-- https://mvnrepository.com/artifact/org.apache.kudu/kudu-client -->
        <dependency>
            <groupId>org.apache.kudu</groupId>
            <artifactId>kudu-client</artifactId>
            <version>1.5.0-cdh5.13.1</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.kudu/kudu-client-tools -->
        <dependency>
            <groupId>org.apache.kudu</groupId>
            <artifactId>kudu-client-tools</artifactId>
            <version>1.5.0-cdh5.13.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.kudu/kudu-spark2 -->
        <dependency>
            <groupId>org.apache.kudu</groupId>
            <artifactId>kudu-spark2_2.11</artifactId>
            <version>1.6.0</version>
        </dependency>
version看环境装的版本
2. 
    SparkConf conf=new SparkConf().setAppName("your.app.name").setMaster("yarn");
    SparkContext sparkContext=new SparkContext(conf);
    SparkSession sparkSession = sparkContext.builder().sparkContext(sparkContext).getOrCreate();
    List<StructField> fields = Arrays.asList(
            DataTypes.createStructField("key", DataTypes.StringType, true),
            DataTypes.createStructField("value", DataTypes.StringType, true));
    StructType schema = DataTypes.createStructType(fields);
    Dataset ds =  sparkSession.read().format("org.apache.kudu.spark.kudu").
            schema(schema).option("kudu.master","10.1.0.20:7051").option("kudu.table","TestKudu").load();
    ds.createOrReplaceTempView("abc");
    sparkSession.sql("select * from abc").show();
3. 打包执行 
maven 依赖打成一个jar, 程序打成一个jar。  因为依赖通常不变，程序经常变。

    spark2-submit --master yarn --jars dependency.jar --class packge.path.classname your.jar








