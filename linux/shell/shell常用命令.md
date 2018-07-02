    netstat -tunlp #显示tcp，udp的端口和进程等相关情况 
        -t:只显示tcp相关
        -u:只显示ucp相关
        -n:拒绝显示列名,尽量转数字 
        -l:仅显示在listen的  
        -p:显示相关程序

## 找命令的安装路径
1. which 命令
2. ll 步骤1的路径   

## [环境变量](https://www.cnblogs.com/aaronLinux/p/5837702.html)
$PATH：决定了shell将到哪些目录中寻找命令或程序，PATH的值是一系列目录，当您运行一个程序时，Linux在这些目录下进行搜寻编译链接。
　　编辑你的 PATH 声明，其格式为：
　　PATH=$PATH:<PATH 1>:<PATH 2>:<PATH 3>:------:<PATH N>
　　你可以自己加上指定的路径，中间用冒号隔开。环境变量更改后，在用户下次登陆时生效，如果想立刻生效，则可执行下面的语句：$ source .bash_profile
　　需要注意的是，最好不要把当前路径 “./” 放到 PATH 里，这样可能会受到意想不到的攻击。完成后，可以通过 $ echo $PATH 查看当前的搜索路径。这样定制后，就可以避免频繁的启动位于 shell 搜索的路径之外的程序了。
1. 可用 export 命令查看PATH值
2. 单独查看PATH环境变量，可用：echo $PATH
3. 添加PATH环境变量(临时)，可用：export PATH=/opt/STM/STLinux-2.3/devkit/sh4/bin:$PATH
4. 永久添加环境变量(影响当前用户) vim ~/.bashrc     export PATH="/opt/STM/STLinux-2.3/devkit/sh4/bin:$PATH"
5. 永久添加环境变量(影响所有用户) vim /etc/profile 在文档最后，添加:export PATH="/opt/STM/STLinux-2.3/devkit/sh4/bin:$PATH" 保存，退出，然后运行：source /etc/profile


 shopt | grep huponexit 后台进程在回话关闭后是否停止， off：不会停止后台线程

 ## Linux查看当前占用CPU或内存最多的几个进程
 * 方法1: 命令行输入top回车，然后按下大写M按照memory排序，按下大写P按照CPU排序。
 * 方法2: ps -aux | sort -k4nr | head -n 5
命令详解： 
1. head：-N可以指定显示的行数，默认显示10行。 
2. ps：参数a指代all——所有的进程，u指代userid——执行该进程的用户id，x指代显示所有程序，不以终端机来区分。ps -aux的输出格式如下：
USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root         1  0.0  0.0  19352  1308 ?        Ss   Jul29   0:00 /sbin/init
root         2  0.0  0.0      0     0 ?        S    Jul29   0:00 [kthreadd]
root         3  0.0  0.0      0     0 ?        S    Jul29   0:11 [migration/0]
3. sort -k4nr中（k代表从根据哪一个关键词排序，后面的数字4表示按照第四列排序；n指代numberic sort，根据其数值排序；r指代reverse，这里是指反向比较结果，输出时默认从小到大，反向后从大到小。）。本例中，可以看到%MEM在第4个位置，根据%MEM的数值进行由大到小的排序。-k3表示按照cpu占用率排序。 


