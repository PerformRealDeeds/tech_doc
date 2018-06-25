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