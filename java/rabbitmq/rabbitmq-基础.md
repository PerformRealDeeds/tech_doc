## java 操作mq的代码 参见
https://github.com/rabbitmq/rabbitmq-tutorials/tree/master/java


## worker处理和确认前一个任务完成后才会分派新的任务
int prefetchCount = 1;
channel.basicQos(prefetchCount);

     
## 启动rabbitmq 管理界面
	rabbitmq-plugins enable rabbitmq_management
浏览器访问 http://127.0.0.1:15672 ，	输入默认账号: guest   密码: guest

## 停止服务
 rabbitmqctl stop
 
 