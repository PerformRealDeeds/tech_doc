[中文文档网址](http://prestodb-china.com/docs/current/index.html)

[英文文档网址](https://prestodb.io/docs/current/index.html)

[presto jdbc驱动](https://prestodb.io/docs/current/installation/jdbc.html)

Presto是一个分布式SQL查询引擎，用于查询分布在一个或多个不同数据源中的大数据集。**presto 不是数据库。**

presto可执行jar


### 逻辑运算符中的null
|a	 |    b	 | a AND b | a OR b|
| -  |    -  |    -    |  -    |  
|TRUE | TRUE    | TRUE	| TRUE       |
|TRUE | FALSE  |  FALSE	 | TRUE  |
|TRUE | NULL  |  NULL	 | TRUE  |
|FALSE | TRUE  |  FALSE	 | TRUE  |
|FALSE | FALSE  |  	FALSE|	FALSE  |
|FALSE | NULL  |  FALSE	 | NULL  |
|NULL | TRUE  |  NULL	 | TRUE  |
|NULL | FALSE  |  FALSE	 |NULL  |
|NULL | NULL  |  NULL	 | NULL  |

概括：
null and false => false,否则null and 任意值都是null
null or true => ture,否则null and 任意值都是null

### BETWEEN
SELECT 3 BETWEEN 2 AND 6;  <==>  SELECT 3 >= 2 AND 3 <= 6;
SELECT NULL BETWEEN 2 AND 4; => null
SELECT 2 BETWEEN NULL AND 6; => null

### is null /  is not null
select NULL IS NULL; => true
SELECT 3.0 IS NULL; => false

###  较大值 /  较小值
支持类型： DOUBLE, BIGINT, VARCHAR, TIMESTAMP, TIMESTAMP WITH TIME ZONE, DATE

greatest(value1, value2) 两者中的大值

least(value1, value2) 两者中的小值
一个参数是null就返回null

### 条件表达式
CASE expression
    WHEN value THEN result
    [ WHEN ... ]
    [ ELSE result ]
END

CASE
    WHEN condition THEN result
    [ WHEN ... ]
    [ ELSE result ]
END

if(condition, true_value)
如果 condition 为真，返回 true_value ； 否则返回空， true_value 不进行计算。

if(condition, true_value, false_value)
如果 condition 为真，返回 true_value ； 否则计算并返回 false_value 。

# presto连接kudu
[github参考链接](https://github.com/MartinWeindel/presto-kudu)

* kudu.client.master-addresses=localhost # kudu的master地址在clouder上看 端口通常默认是7051
* 修改/etc/config.properties 注释掉hive







