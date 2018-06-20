# 文档
[官网文档](https://logback.qos.ch/manual/introduction.html)


# 介绍

logback和log4j的作者是同一个人。logback是作为log4j的替代者出现的。
logback的依赖：

* logback-classic.jar
    *   -- slf4j-api.jar  
    *   -- logback-core.jar


## hello world
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("chapters.introduction.HelloWorld2")    ;
        logger.debug("Hello world.");

        // print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
      }



# 2结构
* logback三个模块：
    * logback-core    : 其它两个模块的基础
    * logback-classic : 扩展了core，原生继承 SLF4J API
    * logback-access  ：整合了Servlet，提供了HTTP访问日志的功能

* logback三个主要的类：
    *  Logger     :  在classic模块
    *  Appender   :  在core模块
    *  Layout.    :  在core模块

logback采取了**家谱式**目录。例如："com.foo" 的logger是 "com.foo.Bar"的logger的父母。
 "java" 是 "java.util" 的父母，是 "java.util.Vector". 的祖先。

logger的根-- “ROOT”

    Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

### 2.1日志有效等级 和 指定等级

X.Y如果没有指定日志等级，默认继承父母X的有效等级。
指定等级会*覆盖*继承的有效等级。
例子：
|Logger  name |   Assigned level	| Effective level  |
| -     |  -   |  -                 | 
|root	|DEBUG    |DEBUG
X	    |INFO    |INFO
X.Y	    |none    |INFO
X.Y.Z	|ERROR    |ERROR

日志等级从低到高：TRACE < DEBUG < INFO <  WARN < ERROR.

logger的方法日志等级>=logger有效等级才会打印日志，否则不能打印日志。
例如：logger的有效等级是INFO，logger.dubug不会打印日志，logger.warn或者logger.error才会打印日志。


### 2.2 logger名字相同，对象引用相同

Logger x = LoggerFactory.getLogger("wombat"); 
Logger y = LoggerFactory.getLogger("wombat");
x和y是同一个对象的引用。

用类的全限定名做logger的名字是通常的做法。也可以自由设置logger的名字。

### 2.3 Appenders and Layouts
appender:关注日志输出的位置
layout:负责日志的格式

logback允许一个日志请求输出多个目的地。一个输出目的地被称为appender。目的地可以是 console, files, remote socket servers, to MySQL, PostgreSQL, Oracle and other databases, JMS, and remote UNIX Syslog daemons.
一个looger可以有多个appender.
appenders 默认继承上层logger日志等级。例如：控制台appender被加入root logger，那么所有日志请求都会在控制台打印。


appender附加标记设置为false时不继承上层logger的appender，否则则相反。

### 2.4 参数化日志

#### 2.4.1 消息参数构造的性能损失例子
不管debug可不可用：一次评估损失 + 一次参数构造性能损失(直接参数构建，性能损失大)

    logger.debug("Entry number: " + i + " is " + String.valueOf(entry[i]));

### 2.4.2 先判断再打印
logger不可用时不会有消息参数构造的性能损失：
* debug可用： 两次评估性能损失+参数构造性能损失(appender中优化执行参数构造)
* debug不可用： 一次评估新能损失

logger的debug不可用时不会有消息参数构造的性能损失，debug可用时有消息参数构造的性能损失。
另外，dubug可用是，有两次isDebugEnabled的调用， 这个也会有微小的性能损失（<1%）。

    if(logger.isDebugEnabled()) { 
      logger.debug("Entry number: " + i + " is "    + String.valueOf(entry[i]));
    }

### 2.4.3
最佳方法：只有评估后日志可以打印，才会有消息参数构造的性能损失。 
debug可用：  一次评估损失 + 参数构造性能损失(appender中优化执行)
debug不可用：一次评估损失 

    Object entry = new SomeObject(); 
    logger.debug("The entry is {}.", entry);

# 2.4.4
debug不可用时，方式2是方式1性能的30倍以上。

    1. logger.debug("The new entry is "+entry+".");
    2. logger.debug("The new entry is {}.", entry);

### 2.5 性能
消息参数构造性能损失：比如日志参数装换成string，字符串连接等。

#### 2.5.1 比较
    x.debug("Entry number: " + i + "is " + entry[i]);
上面的代码导致消息参数构造的性能损失，比如，把两个整数i和entry[i]转成字符串，然后连接。无论debug日志等级可不可用。

    x.debug("Entry number: {} is {}", i, entry[i]);
这用slf4j的参数化打印方式会有大幅度的性能提升。因为参数构建不在dubug方法中执行，而是在appender中。而且，appender格式化消息是被高度优化了。

在频繁的循环中加入被关闭的logger也会拖慢系统的运行。

### 2.5.2 判断是否打印
logger在被创建时就知道了自己的有效日志等级，打印日志时不用查询祖先。
如果祖先的日志等级修改了， 所有的后代都会知道这个更改。(观察者模式)


打印到本机文件系统一行日志通常花费9到12ms, 打印到远程数据库通常花费几ms。



