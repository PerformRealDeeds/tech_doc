## 传入参数,条件过滤
    awk -v key=value '$7<key {print $9}'
 
必须单引号, 传入key变量, 当第7列的值<key时符合条件,输出第9列数据

## awk 向外部传参数
out_var=$(awk 'END {printf ""}')
## 执行shell命令
    awk '{cmd="rm "$0;system(cmd)}' a.txt   

## 行数统计 BEGIN END
awk 'BEGIN{print "地点\t";total=0;} {print $3;total = total+NR} END{printf "行数总计: %2d\n", total}' awk_test.txt

BEGIN开始执行一次 END结束执行一次

## 文档
[awk 教程](http://www.runoob.com/linux/linux-comm-awk.html)

## 取第n列
    awk '{print $5}'

## 利用awk的分隔符特性可以去掉指定字符
    awk -F '.db' '{print $1 $2}'  # 去掉每一行的.db字符

    