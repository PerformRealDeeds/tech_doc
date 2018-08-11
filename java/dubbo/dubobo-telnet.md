[dubbo telnet 官网文档](http://dubbo.apache.org/#!/docs/user/references/telnet.md?lang=zh-cn)

从 `2.0.5` 版本开始，dubbo 开始支持通过 telnet 命令来进行服务治理。

## 使用

    telnet localhost 20880


## 命令

### ls
ls: 显示服务列表
ls -l: 显示服务详细信息列表
ls XxxService: 显示服务的方法列表
ls -l XxxService: 显示服务的方法详细信息列表
### ps
ps: 显示服务端口列表
ps -l: 显示服务地址列表
ps 20880: 显示端口上的连接信息
ps -l 20880: 显示端口上的连接详细信息
### cd
cd XxxService: 改变缺省服务，当设置了缺省服务，凡是需要输入服务名作为参数的命令，都可以省略服务参数
cd /: 取消缺省服务
pwd
pwd: 显示当前缺省服务

### trace
trace XxxService: 跟踪 1 次服务任意方法的调用情况
trace XxxService 10: 跟踪 10 次服务任意方法的调用情况
trace XxxService xxxMethod: 跟踪 1 次服务方法的调用情况
trace XxxService xxxMethod 10: 跟踪 10 次服务方法的调用情况
### count
count XxxService: 统计 1 次服务任意方法的调用情况
count XxxService 10: 统计 10 次服务任意方法的调用情况
count XxxService xxxMethod: 统计 1 次服务方法的调用情况
count XxxService xxxMethod 10: 统计 10 次服务方法的调用情况
### invoke
invoke XxxService.xxxMethod({"prop": "value"}): 调用服务的方法
invoke xxxMethod({"prop": "value"}): 调用服务的方法(自动查找包含此方法的服务)
### status
status: 显示汇总状态，该状态将汇总所有资源的状态，当全部 OK 时则显示 OK，只要有一个 ERROR 则显示 ERROR，只要有一个 WARN 则显示 WARN
status -l: 显示状态列表
### log [1]
log debug: 修改 dubbo logger 的日志级别
log 100: 查看 file logger 的最后 100 字符的日志
help
help: 显示 telnet 命帮助信息
help xxx: 显示xxx命令的详细帮助信息
### clear
clear: 清除屏幕上的内容
clear 100: 清除屏幕上的指定行数的内容
### exit
exit: 退出当前 telnet 命令行

