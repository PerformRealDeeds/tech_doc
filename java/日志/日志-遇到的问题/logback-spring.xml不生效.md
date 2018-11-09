## logback-spring.xml不生效,不输出日志到文件
问题描述：
1. spring-boot 2.0.3 + jdk1.8 + logback-spring
2. 在生产环境中log/目录下有日志文件，但是在测试环境中没有日志文件

解决步骤：
1. 对比生产环境和测试环境的logback-spring.xml文件，一致
2. java -jar xxx.jar --spring.config.location=file:../config/,file:../config/logback-spring.xml
    spring.config.location参数中并不是所有的配置文件都会加载，报错或者不存在的文件会被忽略。
    如果指定的文件存在，加载报错，应用会退出。
3. 网上找资料
   参考网址：
   http://ifeve.com/36583-2/
   https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/htmlsingle/#common-application-properties

问题原因：
1. spring.config.location只是查找application.properties文件，不会管指定目录下的其它配置文件
2. 指定logback-spring.xml的方法是用logging.config配置
    logging.config= # Location of the logging configuration file. For instance, `classpath:logback.xml` for Logback.
    java -jar xxx.jar --spring.config.location=file:../config/ \
                      --logging.config=file:./logback-spring.xml






