# spark资料
[spark官网](http://spark.apache.org/)
《Spark快速大数据分析》

# 本地安装spark
1. 从[spark官网](http://spark.apache.org/downloads.html)下载spark-1.2.0-bin-hadoop2.4.tgz.
2. 安装java jdk8, cmd中执行java -version和javac -version确保两个命令输出都是java8
3. 从[scala官网](https://www.scala-lang.org/download/)下载scala安装包，本地安装
4. 进入spark解压目录/bin下,启动spark-shell.cmd

##spark hello word -- word count

    // 创建一个Java版本的Spark Context
    SparkConf conf = new SparkConf().setAppName("wordCount");
    JavaSparkContext sc = new JavaSparkContext(conf);
    // 读取我们的输入数据
    JavaRDD<String> input = sc.textFile(inputFile);
    // 切分为单词
    JavaRDD<String> words = input.flatMap(
    new FlatMapFunction<String, String>() {
    public Iterable<String> call(String x) {
    return Arrays.asList(x.split(" "));
    }});
    // 转换为键值对并计数
    JavaPairRDD<String, Integer> counts = words.mapToPair(
    new PairFunction<String, String, Integer>(){
    public Tuple2<String, Integer> call(String x){
    return new Tuple2(x, 1);
    }}).reduceByKey(new Function2<Integer, Integer, Integer>(){
    public Integer call(Integer x, Integer y){ return x + y;}});
    // 将统计出来的单词总数存入一个文本文件，引发求值
    counts.saveAsTextFile(outputFile);





## 驱动器节点和执行器节点, 主节点和工作节点
Spark 文档中始终使用驱动器节点和执行器节点的概念来描述执行 Spark
应用的进程。 而主节点（master）和工作节点（worker）的概念则被用来
分别表述集群管理器中的中心化的部分和分布式的部分。 这些概念很容易
混淆，所以要格外小心。例如， Hadoop YARN 会启动一个叫作资源管理器
（Resource Manager）的主节点守护进程，以及一系列叫作节点管理器（Node
Manager）的工作节点守护进程。 而在 YARN 的工作节点上， Spark 不仅可
以运行执行器进程，还可以运行驱动器进程。

## spark提交jar
语法：
 
    spark-submit [options] <app jar | python file> [app options]
例子：

spark-submit \
   --master local \
   --class 
   --executor-memory 0.5G \
  --total-executor-cores 1 \
  ./work_count.jar \

## RDD编程   弹性分布式数据集（Resilient Distributed Dataset，简称 RDD）

## 表3-1：标准Java函数接口
函数名                      | 实现的方法 | 用途
| -                         |-         |-    |
`Function<T, R>`              | R call(T) | 接收一个输入值并返回一个输出值，用于类似 map() 和filter() 等操作中
`Function2<T1, T2, R>`        | R call(T1, T2) | 接收两个输入值并返回一个输出值，用于类似 aggregate()和 fold() 等操作中
`FlatMapFunction<T, R>`       | Iterable<R> call(T) | 接收一个输入值并返回任意个输出，用于类似 flatMap()这样的操作中

### 两种**创建RDD** 的方式：
1. 读取外部数据集，
2. 以及在驱动器程序中对一个集合进行并行化。

        JavaRDD<String> lines = sc.textFile("/path/to/README.md"); // 读取外部数据集
        JavaRDD<String> lines = sc.parallelize(Arrays.asList("pandas", "i like pandas")); // 在驱动器程序中对一个集合进行并行化

### RDD 支持两种操作： **转化操作和行动操作**。

RDD 的转化操作是返回一个新的 RDD 的操作，比如 map() 和 filter()，而行动操作则是向驱动器程序返回结果或把结果写入外部系统的操作， 会触发实际的计算，比如 count() 和 first()。
RDD 的转化操作都是 **惰性求值** 的。这意味着在被调用行动操作之前 Spark 不会开始计算。

### 常见RDD操作

#### 表3-2：对一个数据为{1, 2, 3, 3}的RDD进行基本的RDD转化操作

| 函数名                                      | 目的                                                                                  | 示例                      | 结果                  |
| ------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------- | --------------------- |
| `map()`                                     | 将函数应用于 RDD 中的每个元素，将返回值构成新的 RDD                                   | rdd.map(x => x + 1)       | {2, 3, 4, 4}          |
| `flatMap()`                                 | 将函数应用于 RDD 中的每个元素，将返回的迭代器的所有内容构成新的 RDD。通常用来切分单词 | rdd.flatMap(x => x.to(3)) | {1, 2, 3, 2, 3, 3, 3} |
| `filter()`                                  | 返回一个由通过传给 filter()的函数的元素组成的RDD                                      | rdd.filter(x => x != 1)   | {2, 3, 3}             |
| `distinct()`                                | 去重                                                                                  | rdd.distinct()            | {1, 2, 3}             |
| `sample(withReplacement, fraction, [seed])` | 对 RDD 采样，以及是否替换                                                             | rdd.sample(false, 0.5)    | 非确定的              |  |

#### 表3-3：对数据分别为{1, 2, 3}和{3, 4, 5}的RDD进行针对两个RDD的转化操作

| 函数名         | 目的                                      | 示例                        | 结果                        |
| -------------- | ----------------------------------------- | --------------------------- | --------------------------- |
| union()        | 生成一个包含两个 RDD 中所有元素的 RDD     | rdd.union(other)            | {1, 2, 3, 3, 4, 5}          |  |
| intersection() | 求两个 RDD 共同的元素的                   | RDD rdd.intersection(other) | {3}                         |  |
| subtract()     | 移除一个 RDD 中的内容（例如移除训练数据） | rdd.subtract(other)         | {1, 2}                      |  |
| cartesian()    | 与另一个 RDD 的笛卡儿积                   | rdd.cartesian(other)        | {(1, 3), (1, 4), ...(3, 5)} |  |

表3-4：对一个数据为{1, 2, 3, 3}的RDD进行基本的RDD行动操作
| 函数名                                     | 目的                                               | 示例                                                                                    | 结果                   |
| ------------------------------------------ | -------------------------------------------------- | --------------------------------------------------------------------------------------- | ---------------------- |
| `collect()`                                | 返回 RDD 中的所有元素                              | rdd.collect()                                                                           | {1, 2, 3, 3}           |
| `count()`                                  | RDD 中的元素个数                                   | rdd.count()                                                                             | 4                      |
| `countByValue()`                           | 各元素在 RDD 中出现的次数                          | rdd.countByValue()                                                                      | {(1, 1),(2, 1),(3, 2)} |
| `take(num)`                                | 从 RDD 中返回 num 个元素                           | rdd.take(2)                                                                             | {1, 2}                 |
| `top(num)`                                 | 从 RDD 中返回最前面的 num个元素                    | rdd.top(2)                                                                              | {3, 3}                 |
| `takeOrdered(num)(ordering)`               | 从 RDD 中按照提供的顺序返回最前面的 num 个元素     | rdd.takeOrdered(2)(myOrdering)                                                          | {3, 3}                 |
| `takeSample(withReplacement, num, [seed])` | 从 RDD 中返回任意一些元素                          | rdd.takeSample(false, 1)                                                                | 非确定的               |
| `reduce(func)`                             | 并 行 整 合 RDD 中 所 有 数 据（例如 sum）         | rdd.reduce((x, y) => x + y)                                                             | 9                      |
| `fold(zero)(func)`                         | 和 reduce() 一 样， 但 是 需 要提供初始值          | rdd.fold(0)((x, y) => x + y)                                                            | 9                      |
| `aggregate(zeroValue)(seqOp, combOp)`      | 和 reduce() 相 似， 但 是 通 常 返回不同类型的函数 | rdd.aggregate((0, 0))((x, y) =>(x._1 + y, x._2 + 1),(x, y) =>(x._1 + y._1, ._2 + y._2)) | (9,4)                  |
| `foreach(func)`                            | 对 RDD 中的每个元素使用给定的函数                  | rdd.foreach(func)                                                                       | 无                     |

### 键值对rdd操作

表4-1： Pair RDD的转化操作（以键值对集合{(1, 2), (3, 4), (3, 6)}为例）
| 函数名                                                                | 目的                                                                                                               | 示例                             | 结果                                       |
| --------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ | -------------------------------- | ------------------------------------------ |
| `reduceByKey(func)`                                                   | 合并具有相同键的值                                                                                                 | rdd.reduceByKey((x, y) => x + y) | {(1,2), (3,10)}                            |
| `groupByKey()`                                                        | 对具有相同键的值进行分组                                                                                           | rdd.groupByKey()                 | {(1, [2]),(3, [4,6])}                      |
| `combineByKey( createCombiner,mergeValue,mergeCombiners,partitioner)` | 使用不同的返回类型合并具有相同键的值                                                                               | 见例 4-12 到例 4-14。            |
| `mapValues(func)`                                                     | 对 pair RDD 中的每个值应用一个函数而不改变键                                                                       | rdd.mapValues(x => x+1)          | {(1,3), (3,5), (3,7)}                      |
| `flatMapValues(func)`                                                 | 对 pair RDD中的每个值应用一个返回迭代器的函数，然后对返回的每个元素都生成一个对应原键的键值对记录。 通常用于符号化 | rdd.flatMapValues(x => (x to 5)) | {(1,2), (1,3), (1,4), (1,5), (3,4), (3,5)} |
| `keys()`                                                              | 返回一个仅包含键的RDD                                                                                              | rdd.keys()                       | {1, 3,3}                                   |
| `values()`                                                            | 返回一个仅包含值的RDD                                                                                              | rdd.values()                     | {2, 4,6}                                   |
| `sortByKey()`                                                         | 返回一个根据键排序的RDD                                                                                            | rdd.sortByKey()                  | {(1,2), (3,4), (3,6)}                      |

#### 表4-2：针对两个pair RDD的转化操作（rdd = {(1, 2), (3, 4), (3, 6)}other = {(3, 9)}）

函数名 | 目的 | 示例 | 结果 |
| -    | -   | -    |  -   |
`subtractByKey` | 删掉 RDD 中键与 other RDD 中的键相同的元素|rdd.subtractByKey|(other) {(1, 2)}
`join` |对两个 RDD 进行内连接| rdd.join(other)| {(3, (4, 9)), (3,(6, 9))}
`rightOuterJoin` | 对两个 RDD 进行连接操作，确保第一个 RDD 的键必须存在（右外连接）|rdd.rightOuterJoin(other)  |{(3,(Some(4),9)),(3,(Some(6),9))}
`leftOuterJoin` |对两个 RDD 进行连接操作，确保第二个 RDD 的键必须存在（左外连接）|rdd.leftOuterJoin(other)| {(1,(2,None)), (3,(4,Some(9))), (3,(6,Some(9)))}
`cogroup` | 将两个 RDD 中拥有相同键的数据分组到一起 | rdd.cogroup(other) | {(1,([2],[])), (3,([4, 6],[9]))}

#### 表4-3： **Pair RDD的行动操作**（以键值对集合{(1, 2), (3, 4), (3, 6)}为例）

| 函数             | 描述                               | 示例               | 结果                       |
| ---------------- | ---------------------------------- | ------------------ | -------------------------- |
| `countByKey()`   | 对每个键对应的元素分别计数         | rdd.countByKey()   | {(1, 1), (3, 2)}           |
| `collectAsMap()` | 将结果以映射表的形式返回，以便查询 | rdd.collectAsMap() | Map{(1, 2), (3,4), (3, 6)} |
| `lookup(key)`    | 返回给定键对应的所有值             | rdd.lookup(3)      | [4, 6]                     |


### 并行度调优

每个 RDD 都有固定数目的分区，**分区数**决定了在 RDD 上执行操作时的**并行度**。

    sc.parallelize(data).reduceByKey((x, y) => x + y) // 默认并行度
    sc.parallelize(data).reduceByKey((x, y) => x + y) // 自定义并行度

### 数据分组


 groupByKey() 根据键k进行分组，返回[K, Iterable[V]]。
 groupBy()用于自定义键
 rdd.reduceByKey(func) 更高效，省略key的创建步骤 <==> rdd.groupByKey().mapValues(value => value.reduce(func))

### 4.3.3 pair rdd join

paid rdd join类似sql,有join,left join,right join 

### 4.3.4　数据排序

sortByKey()

### 4.4 Pair RDD的行动操作

表4-3： Pair RDD的行动操作（以键值对集合{(1, 2), (3, 4), (3, 6)}为例）

| 函数           | 描述                               | 示例               | 结果                       |
| -------------- | ---------------------------------- | ------------------ | -------------------------- |
| countByKey()   | 对每个键对应的元素分别计数         | rdd.countByKey()   | {(1, 1), (3, 2)}           |
| collectAsMap() | 将结果以映射表的形式返回，以便查询 | rdd.collectAsMap() | Map{(1, 2), (3,4), (3, 6)} |
| lookup(key)    | 返回给定键对应的所有值             | rdd.lookup(3)      | [4, 6]                     |

4.5　数据分区（进阶）
在分布式程序中，通信的代价是很大的，因此控制数据分布以获得最少的网络传输可以极大地提升整体性能。
`partitionBy`的使用

    val sc = new SparkContext(...)
    val userData = sc.sequenceFile[UserID, UserInfo]("hdfs://...")
                     .partitionBy(new HashPartitioner(100)) // 构造100个分区
                     .persist()








