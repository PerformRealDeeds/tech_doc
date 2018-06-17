# spark资料
[spark官网](http://spark.apache.org/)
《Spark快速大数据分析》

# 本地安装spark
1. 从[spark官网](http://spark.apache.org/downloads.html)下载spark-1.2.0-bin-hadoop2.4.tgz.
2. 安装java jdk8, cmd中执行java -version和javac -version确保两个命令输出都是java8
3. 从[scala官网](https://www.scala-lang.org/download/)下载scala安装包，本地安装
4. 进入spark解压目录/bin下,启动spark-shell.cmd









## 驱动器节点和执行器节点, 主节点和工作节点
Spark 文档中始终使用驱动器节点和执行器节点的概念来描述执行 Spark
应用的进程。 而主节点（master）和工作节点（worker）的概念则被用来
分别表述集群管理器中的中心化的部分和分布式的部分。 这些概念很容易
混淆，所以要格外小心。例如， Hadoop YARN 会启动一个叫作资源管理器
（Resource Manager）的主节点守护进程，以及一系列叫作节点管理器（Node
Manager）的工作节点守护进程。 而在 YARN 的工作节点上， Spark 不仅可
以运行执行器进程，还可以运行驱动器进程。







