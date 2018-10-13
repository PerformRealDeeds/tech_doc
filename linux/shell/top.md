
## top
 **man top**有参数的详细含义。
 常用的：%Cpu(s)中 us+ni用户进程占用的cpu时间之和。 
 
    us, user:time runing un-niced user process
    ni, nice:time running niced user proceses
    sy, system: time runin
  
## top后按大写M按照内存使用率排序,按大写P按照CPU使用率排序

## [博客参考](https://blog.csdn.net/u011183653/article/details/19489603)

## top cpu使用率
top显示的cpu使用率是上次屏幕更新后的一个周期内的cpu使用时间除以周期时间，如果是多线程，那么cpu使用率可能超过100%。按H切换到多线程统计模式。