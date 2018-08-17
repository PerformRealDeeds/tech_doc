## 
    格式: hive  -hiveconf hive.execution.engine=spark \
               -hiveconf spark.executor.instances=5 \
               -hiveconf spark.executor.memory=2g \
               -hiveconf spark.executor.cores=2 \
               -hiveconf mapreduce.job.queuename=your_name \
               -hiveconf mapreduce.reduece.java.opts=-Xmx1024m \
               -hiveconf hive.exec.dynamic.partition=true \
               -hiveconf hive.exec.dynamic.partition.mode=nonstrict \
               -hiveconf hive.exec.max.dynamic.partitions.pernode=10000 \
               -hiveconf hive.exec.max.dynamic.paritions=100000 \
               -v -d var1=${var1} -f your_file.sql

##  hive sql中的变量
    set hivevar:EYDT=2018-01-01


 hive  -hiveconf hive.execution.engine=spark \
       -hiveconf mapreduce.job.queuename=group1 \
       -hiveconf mapreduce.job.queuename=zhangce \
       -hiveconf spark.executor.instances=1 \
       -hiveconf spark.executor.memory=1g \
       -hiveconf spark.executor.cores=1 \
    -v 



