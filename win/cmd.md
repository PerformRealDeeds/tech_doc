## 进入cmd的方法
* 资源管理器中的路径中输入cmd进入cmd,路径是当前路径
* 开始菜单输入cmd
* win+R打开运行，输入cmd
* cmd(管理员) 开始菜单右键->命令提示符(管理员)
---

    netsh wlan show profile                                          #查看wlan名称
    netsh wlan show profile name="wifi名称" key=clear                #查看wlan密码


    pause                                                            #暂停

   ping -t ip                                                        #不停地访问指定ip,ctrl+c后统计发送信息
   
## [win查看被占用的端口](https://blog.csdn.net/zz657114506/article/details/52926762)
1. win键 + r，输入cmd进入DOS命令窗口
2. 根据端口号查程序的进程号 

        netstat -ano | findstr 占用端口号（8080）  #  linux  netstat -anp | grep 80 
3. 根据程序的进程号查看具体的程序名称 

        tasklist -V | findstr 进程号（9500） # win takslist -V 显示详细信息
4. 强制、递归 删除本程序及其子进程 
        taskkill -f -t -im 进程名(javaw.exe)

        netstat -help
        -a            显示所有连接和侦听端口。
        -b            显示在创建每个连接或侦听端口时涉及的可执行程序。
                        在某些情况下，已知可执行程序承载多个独立的
                        组件，这些情况下，显示创建连接或侦听端口时涉
                        及的组件序列。此情况下，可执行程序的名称
                        位于底部[]中，它调用的组件位于顶部，直至达
                        到 TCP/IP。注意，此选项可能很耗时，并且在您没有
                        足够权限时可能失败。
        -e            显示以太网统计。此选项可以与 -s 选项结合使用。
        -f            显示外部地址的完全限定域名(FQDN)。
        -n            以数字形式显示地址和端口号。
        -o            显示拥有的与每个连接关联的进程 ID。
        -p proto      显示 proto 指定的协议的连接；proto 可以是下列任
                        何一个: TCP、UDP、TCPv6 或 UDPv6。如果与 -s 选
                        项一起用来显示每个协议的统计，proto 可以是下列任
                        何一个: IP、IPv6、ICMP、ICMPv6、TCP、TCPv6、UDP
                        或 UDPv6。
        -r            显示路由表。
        -s            显示每个协议的统计。默认情况下，显示
                        IP、IPv6、ICMP、ICMPv6、TCP、TCPv6、UDP 和 UDPv6
                        的统计；-p 选项可用于指定默认的子网。
        -t            显示当前连接卸载状态。
        interval      重新显示选定的统计，各个显示间暂停的间隔秒数。
                        按 CTRL+C 停止重新显示统计。如果省略，则 netstat
                        将打印当前的配置信息一次。

## Windows命令行查看文件的MD5
        certutil -hashfile yourfilename.ext MD5
        certutil -hashfile yourfilename.ext SHA1
        certutil -hashfile yourfilename.ext SHA256


## 

## ping   应用层命令
目的: 查看网络层ip的连通性 

同时可以查看域名对映的ip

例子: ping baidu.com


## tracert
TTL(Time To Live): IP包被路由器丢弃之前允许通过的最大网段数量

例子: tracert  baidu.com

## arp 
目的: 查看目的ip对应的mac地址 网络层只认识ip,数据链路层只认识mac

原理:目的ip和源ip同网段时, 发送广播,请求目的ip发送目的mac给源ip;    目的ip和源ip不在同一个网段时,发送数据帧给网关.

## 反向arp
通过mac获取ip

例子:DHCP的过程,无盘工作站

## osi 7层协议
物理层: 网卡
数据链路层: 交换机   mac地址
网络层:路由器   ip地址