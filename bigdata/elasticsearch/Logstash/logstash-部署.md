## [logstash 安装使用](https://www.elastic.co/downloads/logstash)
1. 下载解压 Logstash  执行 `logstash -e "input{ stdin{} } output{ stdout{} }"`看看是否可用
2. 准备配置文件logstash-test.conf 
3. 运行 `bin/logstash -f logstash.conf`  后台运行时使用 nohup 命令 & 





## [logstash 读取文件到控制台](https://blog.csdn.net/laoyang360/article/details/51842744)

log_test.conf
 
    input {
    file {
    path=> [ "d:/test/company1.txt",
    "d:/test/company2.txt" ]
    #codec=>multiline {
    # pattern => "^\s"
    # what=>"previous"
    #}
    type=>"probe_log"  #类型名称
    # tags=>["XX.XX.XX.XX"]
    }
    }

    ###output to console
    output {

    stdout { codec => json_lines }
    }

在win cmd中执行`logstash -f ./test/log_test.conf` ,当输入中的path的文件有追加,最自动在控制台打印增加的日志,一旦文件减少,会重新在控制台打印所有的数据.

#### [logstash mysql->es](https://segmentfault.com/a/1190000011061797#articleHeader6)
mysql.conf

    input {
        jdbc {
            jdbc_driver_library => "C:/Elastic/logstash-6.0.0-beta2/lib/mysqldriver/mysql-connector-java-5.1.44-bin.jar"
            jdbc_driver_class => "com.mysql.jdbc.Driver"
            jdbc_connection_string => "jdbc:mysql://es:3306/forelk?autoReconnect=true&useSSL=false"
            jdbc_user => "root"
            jdbc_password => "123qweASD"
            schedule => "* * * * *"
            jdbc_default_timezone => "Asia/Shanghai"
            statement => "SELECT * FROM elktable;"
        }
    }
    output {
        elasticsearch {
            index => "elkdb"
            document_type => "elktable"
            document_id => "%{elkid}"
            hosts => ["es:9200"]
        }
    }

执行`bin\logstash -f config\mysql.conf`, logstash会间歇执行sql

[schedule 语法](https://www.cnblogs.com/ycyzharry/p/7598337.html)

MINUTE  HOUR  DOM  MONTH  DOW
这个字段就好比cron定时任务语法（只有较小的差别），具体来说，每行由5个被TAB或空格分割的字段组成。

其中每个字段除了可以使用取值范围内的值外，还能使用一些特殊的字符。
*     匹配范围内所有值
M-N   匹配M~N范围内所有值
M-N/X 或者 */X   在指定M~N范围内或整个有效区间内每隔X构建一次 
A,B,...,Z        匹配多个值




## csv -> es 配置
    input {
    file {
        path => "/Users/karnag/Downloads/siren201703.csv"
        start_position => "beginning"
        sincedb_path => "/dev/null"
    }
    }
    filter {
    csv {
        separator => ","
        #Date,Open,High,Low,Close,Volume (BTC),Volume (Currency),Weighted Price
        columns => ["SIREN", "NIC", "L1_NORMALISEE", "L2_NORMALISEE", "L3_NORMALISEE", "L4_NORMALISEE", "L5_NORMALISEE", "L6_NORMALISEE", "L7_NORMALISEE", "L1_DECLAREE", "L2_DECLAREE", "L3_DECLAREE", "L4_DECLAREE", "L5_DECLAREE", "L6_DECLAREE", "L7_DECLAREE", "NUMVOIE", "INDREP", "TYPVOIE", "LIBVOIE", "CODPOS", "CEDEX", "RPET", "LIBREG", "DEPET", "ARRONET", "CTONET", "COMET", "LIBCOM", "DU", "TU", "UU", "EPCI", "TCD", "ZEMET", "SIEGE", "ENSEIGNE", "IND_PUBLIPO", "DIFFCOM", "AMINTRET", "NATETAB", "LIBNATETAB", "APET700", "LIBAPET", "DAPET", "TEFET", "LIBTEFET", "EFETCENT", "DEFET", "ORIGINE", "DCRET", "DDEBACT", "ACTIVNAT", "LIEUACT", "ACTISURF", "SAISONAT", "MODET", "PRODET", "PRODPART", "AUXILT", "NOMEN_LONG", "SIGLE", "NOM", "PRENOM", "CIVILITE", "RNA", "NICSIEGE", "RPEN", "DEPCOMEN", "ADR_MAIL", "NJ", "LIBNJ", "APEN700", "LIBAPEN", "DAPEN", "APRM", "ESS", "DATEESS", "TEFEN", "LIBTEFEN", "EFENCENT", "DEFEN", "CATEGORIE", "DCREN", "AMINTREN", "MONOACT", "MODEN", "PRODEN", "ESAANN", "TCA", "ESAAPEN", "ESASEC1N", "ESASEC2N", "ESASEC3N", "ESASEC4N", "VMAJ", "VMAJ1", "VMAJ2", "VMAJ3", "DATEMAJ"]
    }
    }
    output {
    elasticsearch {
        hosts => "http://192.168.10.19:8080/"
        index => "siren"
    }
    stdout {}
    }



## hive -> es 
### 1. 加入hive jdbc依赖包(mysql jdbc没有依赖包,hive jdbc有很多依赖包,在maven引入hive-jdbc后把所有的依赖都打入一个单独的jar中), 在`confg/jvm.options`中加入依赖包的目录
    # hive jdbc驱动 目录 (含hive jdbc驱动依赖)
    -Djava.ext.dirs=../lib/hive_jdbc_lib
### 2.hive-to-es.conf配置
单输入单输出

        input {
            jdbc {
                # 因为在-Djava.ext.dirs指定了hive jdbc包,随便写个参数就好
                jdbc_driver_library => "/path/to/hive-jdbc-2.2.0.jar"
                jdbc_driver_class => "org.apache.hive.jdbc.HiveDriver"
                # hive jdbc格式参考(https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Clients)
                jdbc_connection_string => "jdbc:hive2://ip:10000/default;;?hive.execution.engine=spark"
                jdbc_user => "usr_nm"
                jdbc_password => ""
                # 语法参考 [schedule 语法](https://www.cnblogs.com/ycyzharry/p/7598337.html)    0 */12 * * * 表示每天每隔12个小时整点执行一次
                schedule => "* * * * *"
                jdbc_default_timezone => "Asia/Shanghai"
                # hive必须写列名,不然默认 tbl.col  会多表名.
                statement => "SELECT * FROM db.tbl;"
            }
        }
        output {
            elasticsearch {
                index => "dbNm"
                document_type => "tblNm"
                document_id => "%{idCol}"
                hosts => ["ip:9200"]
            }
        }

多输入多输出:

     input 
     {
        jdbc
        {
            #因为在-Djava.ext.dirs指定了hive jdbc包,随便写个参数就好
            jdbc_driver_library => "/path/to/hive-jdbc-2.2.0.jar"
            jdbc_driver_class => "org.apache.hive.jdbc.HiveDriver"
            ## hive jdbc格式参考(https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Clients)
            jdbc_connection_string => "jdbc:hive2://ip:10000/default;;?hive.execution.engine=spark"
            jdbc_user => "usr_nm"
            jdbc_password => ""
            // 语法参考 [schedule 语法](https://www.cnblogs.com/ycyzharry/p/7598337.html)    * */12 * * * 表示每天每隔12个小时执行一次
            schedule => "* * * * *"
            jdbc_default_timezone => "Asia/Shanghai"
            ## hive必须写列名,不然默认 tbl.col  会多表名.
            statement => "SELECT * FROM db.tbl1;"
            type => "tbl1"
        }

        jdbc
        {
            #因为在-Djava.ext.dirs指定了hive jdbc包,随便写个参数就好
            jdbc_driver_library => "/path/to/hive-jdbc-2.2.0.jar"
            jdbc_driver_class => "org.apache.hive.jdbc.HiveDriver"
            ## hive jdbc格式参考(https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Clients)
            jdbc_connection_string => "jdbc:hive2://ip:10000/default;;?hive.execution.engine=spark"
            jdbc_user => "usr_nm"
            jdbc_password => ""
            // 语法参考 [schedule 语法](https://www.cnblogs.com/ycyzharry/p/7598337.html)    * */12 * * * 表示每天每隔12个小时执行一次
            schedule => "* * * * *"
            jdbc_default_timezone => "Asia/Shanghai"
            ## hive必须写列名,不然默认 tbl.col  会多表名.
            statement => "SELECT * FROM db.tbl2;"
            type => "tbl2"
        }

        jdbc
        {
            #因为在-Djava.ext.dirs指定了hive jdbc包,随便写个参数就好
            jdbc_driver_library => "/path/to/hive-jdbc-2.2.0.jar"
            jdbc_driver_class => "org.apache.hive.jdbc.HiveDriver"
            ## hive jdbc格式参考(https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Clients)
            jdbc_connection_string => "jdbc:hive2://ip:10000/default;;?hive.execution.engine=spark"
            jdbc_user => "usr_nm"
            jdbc_password => ""
            // 语法参考 [schedule 语法](https://www.cnblogs.com/ycyzharry/p/7598337.html)    * */12 * * * 表示每天每隔12个小时执行一次
            schedule => "* * * * *"
            jdbc_default_timezone => "Asia/Shanghai"
            ## hive必须写列名,不然默认 tbl.col  会多表名.
            statement => "SELECT * FROM db.tbl3;"
            type => "tbl3"
        }

    }

    output 
    {
        if [type] == "tbl1"
        {
            elasticsearch 
            {
                index => "db"
                document_type => "tbl1"
                document_id => "%{idCol}"
                hosts => ["ip:9200"]
            }
        }
        else if [type] == "tbl2"
        {
            elasticsearch 
            {
                index => "db"
                document_type => "tbl2"
                document_id => "%{idCol}"
                hosts => ["ip:9200"]
            }
        }
        else if [type] == "tbl3"
        {
            elasticsearch 
            {
                index => "db"
                document_type => "tbl3"
                document_id => "%{idCol}"
                hosts => ["ip:9200"]
            }
        }
    }

* [参考1](https://www.cnblogs.com/moonlightL/p/7760512.html)

