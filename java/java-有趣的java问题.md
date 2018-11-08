
[去投资银行面试会遇到的10个Java问题](http://www.importnew.com/29199.html)

## java中双检锁为什么要加上volatile关键字！
http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
https://blog.csdn.net/null_ruzz/article/details/72530826



# java.lang.UnsupportedOperationException: This code should have never made it into slf4j-api.jar
	SLF4J: Class path contains multiple SLF4J bindings.
	SLF4J: Found binding in [jar:file:/D:/WorkSpaces/maven-repo/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
	SLF4J: Found binding in [jar:file:/D:/WorkSpaces/maven-repo/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLogge
	rBinder.class]
	SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
	Exception in thread "main" java.lang.ExceptionInInitializerError
		at org.slf4j.LoggerFactory.bind(LoggerFactory.java:150)
		at org.slf4j.LoggerFactory.performInitialization(LoggerFactory.java:124)
		at org.slf4j.LoggerFactory.getILoggerFactory(LoggerFactory.java:412)
		at org.slf4j.LoggerFactory.getLogger(LoggerFactory.java:357)
		at org.apache.logging.slf4j.SLF4JLoggerContext.getLogger(SLF4JLoggerContext.java:39)
		at org.apache.commons.logging.LogFactory$Log4jLog.<init>(LogFactory.java:204)
		at org.apache.commons.logging.LogFactory$Log4jDelegate.createLog(LogFactory.java:166)
		at org.apache.commons.logging.LogFactory.getLog(LogFactory.java:109)
		at org.apache.commons.logging.LogFactory.getLog(LogFactory.java:99)
		at org.springframework.boot.SpringApplication.<clinit>(SpringApplication.java:198)
		at com.github.wxiaoqi.security.center.CenterBootstrap.main(CenterBootstrap.java:17)
	Caused by: java.lang.UnsupportedOperationException: This code should have never made it into slf4j-api.jar
		at org.slf4j.impl.StaticLoggerBinder.<init>(StaticLoggerBinder.java:63)
		at org.slf4j.impl.StaticLoggerBinder.<clinit>(StaticLoggerBinder.java:44)
		... 11 more
		
[SLF4J的扩展机制](https://blog.csdn.net/kisimple/article/details/38664717)是找类路径下的org.slf4j.impl.StaticLoggerBinder.class,但是这个类不能是slf4j-api自己的，看上面的日志，先加载自己包中的StaticLoggerBinder类就报错了。因为它的构造函数是

	    private StaticLoggerBinder() {
	        throw new UnsupportedOperationException("This code should have never made it into slf4j-api.jar");
	    }

解决方法：
1. 在pom中添加loback的依赖，让slf4j先找到logback中的StaticLoggerBinder

		<dependency>
				<groupId>ch.qos.logback</groupId>
				<artifactId>logback-classic</artifactId>
				<version>1.2.3</version>
		</dependency>
2. 不使用 slf4j-api 1.7.25版本了，这个版本里有StaticLoggerBinder类
       <dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>1.7.25</version>
		</dependency>
