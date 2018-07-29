 ##日志
    debug=true  #核心Logger（包含嵌入式容器、hibernate、spring）会输出更多内容，但是你自己应用的日志并不会输出为DEBUG级别

    logging.file  # 设置文件，可以是绝对路径，也可以是相对路径。如：logging.file=log/my.log(相对)或者/log/my.log(绝对)  #默认情况下，Spring Boot将日志输出到控制台，不会写到日志文件。
    logging.path # 设置目录，会在该目录下创建spring.log文件，并写入日志内容，如：logging.path=/var/log
    # 如果只配置 logging.file，会在项目的当前路径下生成一个 xxx.log 日志文件。
    #如果只配置 logging.path，在 /var/log文件夹生成一个日志文件为 spring.log

# Allow Thymeleaf templates to be reloaded at dev time
spring.thymeleaf.cache: false
server.tomcat.access_log_enabled: true
server.tomcat.basedir: target/tomcat
