# win10下启动D:\Program\zookeeper-3.4.10\bin\zkServer.cmd 闪退
1.打开zkServer.cmd,在文件末尾加入pause

    D:\Program\zookeeper-3.4.10\bin>call "C:\Program Files\Java\jdk1.8.0_171"\bin\java "-Dzookeeper.log.dir=D:\Program\zookeeper-3.4.10\bin\.." "-Dzookeeper.root.logger=INFO,CONSOLE" -cp "D:\Program\zookeeper-3.4.10\bin\..\build\classes;D:\Program\zookeeper-3.4.10\bin\..\build\lib\*;D:\Program\zookeeper-3.4.10\bin\..\*;D:\Program\zookeeper-3.4.10\bin\..\lib\*;D:\Program\zookeeper-3.4.10\bin\..\conf" org.apache.zookeeper.server.quorum.QuorumPeerMain "D:\Program\zookeeper-3.4.10\bin\..\conf\zoo.cfg"
    2018-07-04 21:09:42,266 [myid:] - INFO  [main:QuorumPeerConfig@134] - Reading configuration from: D:\Program\zookeeper-3.4.10\bin\..\conf\zoo.cfg
    2018-07-04 21:09:42,269 [myid:] - ERROR [main:QuorumPeerMain@85] - Invalid config, exiting abnormally
    org.apache.zookeeper.server.quorum.QuorumPeerConfig$ConfigException: Error processing D:\Program\zookeeper-3.4.10\bin\..\conf\zoo.cfg
            at org.apache.zookeeper.server.quorum.QuorumPeerConfig.parse(QuorumPeerConfig.java:154)
            at org.apache.zookeeper.server.quorum.QuorumPeerMain.initializeAndRun(QuorumPeerMain.java:101)
            at org.apache.zookeeper.server.quorum.QuorumPeerMain.main(QuorumPeerMain.java:78)
    Caused by: java.lang.IllegalArgumentException: D:\Program\zookeeper-3.4.10\bin\..\conf\zoo.cfg file is missing
            at org.apache.zookeeper.server.quorum.QuorumPeerConfig.parse(QuorumPeerConfig.java:138)
            ... 2 more
    Invalid config, exiting abnormally

    D:\Program\zookeeper-3.4.10\bin>endlocal

    D:\Program\zookeeper-3.4.10\bin>pause
    请按任意键继续. . .

可以看到少配置文件,`D:\Program\zookeeper-3.4.10\bin\..\conf\zoo.cfg file is missing`
把官方的`zoo_sample.cfg`复制一份,重命名为zoo.cfg.
