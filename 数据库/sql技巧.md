## 多个业务表增量更新
1. 建立一个包含目标表所有字段的临时表
2. 把每个源表的记录插入到临时表，对于同一个id来说，每个业务源表就是一条记录
3. 按照id合并
    * 没有时间先后的取max
    * 一个业务表多条记录,有只想要1条记录: 用row_nubmer(),按照每个业务源表的更新时间排序,把符合要求的标记成1,其它的标记成0.如对于行1,只有业务表a有有效字段,业务表b的字段在临时表中是null, 业务表a的rn_a=1, 业务表b的rn_b=0, rn_a+rn_b=1,  找出所有rn之和是1的就是符合要求的行记录,接着按照上个方法处理.

## min(col_nm),max(col_nm) 会先排除col_nm中的null，再取最小值或者最大值。如果col_nm全是null，min或者max就是null

## select case when null<>'' then 1 else 0 end; ==> 0  
null和任何值（包括null）比较都是null，对于case when来说，只有true才会执行，false和null都不会执行。



## 对于中间值，可以不用nvl处理null