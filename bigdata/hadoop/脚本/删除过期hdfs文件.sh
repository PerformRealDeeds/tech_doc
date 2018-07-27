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
# 缺点:对于每一个要删除的文件都要启动一个jvm,启动和销毁开销太大 可以用hadoop rm  file1 file2 ... 执行很快,需要把所有要删除的文件拼成一个字符串
hadoop fs -ls -h /hive/warehouse/qianzhui_*.db/*/*.txt | \
awk -v due_date=$due_date \
       'BEGIN{total=0;} \
       $7<due_date && $9!=""{print "删除"$9" 最后修改日期:"$7; system("hadoop fs -rm $9"); total=total+1;} \
       END{printf "\n删除文件总数:%d\n", total}'
       

# 复杂点的可以参考: https://www.cnblogs.com/mengrennwpu/p/6114505.html



# 统计要删除文件的总数
hadoop fs -ls -h /hive/warehouse/*.db/*/*.txt | \
awk -v due_date=$due_date \
       'BEGIN{total=0;} \
       $7<due_date && $9!=""{print "删除"$9" 最后修改日期:"$7;  total=total+1;} \
       END{printf "\n删除文件总数:%d\n", total}'

# 建议用这段代码删除hdfs文件
# 要删除的hadoop文件的路径字符串, 用空格隔开
# [参考](http://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/FileSystemShell.html)
du_date=$(date -d "now - 90 day" +%F)  #90天前
to_delete_file=$(hadoop fs -ls -h /hive/warehouse/your.db/*/your.suffix | \
                 awk -v due_date=$due_date \
                 'BEGIN{file_path=""} \
                 $7<due_date && $9!=""{filepath=filepath" "$9} \
                 END{printf "%s", file_path}')
if[ "$to_delete_file"x = ""x ]
then 
echo "要删除的文件是空"
else 
      echo "要删除的文件"
      hadoop fs -ls -h $to_delete_file
      echo "开始删除"
      hadoop fs -rm $to_delete_file
fi

