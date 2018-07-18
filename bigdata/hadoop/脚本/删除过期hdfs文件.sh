#!/bin/bash
# 删除hdfs过期文件
# 输入: 日期偏移量 可选 默认7天前

#过期天数
due_days=${1:-7}

# 计算过期日期
due_date=$(date -d "now -$due_days day" +%Y-%m-%d)
echo "删除$due_date以前的文件"

# $7是文件最后修改日期  
# $9是文件路径  
# system()可以在awk新建一个子shell,执行shell命令
# total计算删除文件的个数
# BEGIN END在开始前,结束前执行一次
# awk -v 才可以把外部的变量传进去
hadoop fs -ls -h /hive/warehouse/qianzhui_*.db/*/*.txt | \
awk -v due_date=$due_date \
       'BEGIN{total=0;} \
       $7<due_date && $9!=""{print "删除"$9" 最后修改日期:"$7; system("hadoop fs -rm $9"); total=total+1;} \
       END{printf "\n删除文件总数:%d\n", total}'
       

# 复杂点的可以参考: https://www.cnblogs.com/mengrennwpu/p/6114505.html




hadoop fs -ls -h /hive/warehouse/*.db/*/*.txt | \
awk -v due_date=$due_date \
       'BEGIN{total=0;} \
       $7<due_date && $9!=""{print "删除"$9" 最后修改日期:"$7;  total=total+1;} \
       END{printf "\n删除文件总数:%d\n", total}'
  