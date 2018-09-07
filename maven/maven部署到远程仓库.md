在pom.xml中加入`distributionManagement`配置
注意：id是setting.xml server的id，用来登录远程仓库，url是远程部署的地址。
      `distributionManagement`只能写在pom中，不能写在setting.xml中

        <!--定义snapshots库和releases库的nexus地址-->
        <distributionManagement>
            <repository>
                <id>nexus-releases</id>
                <url>http://172.17.103.59:8081/nexus/content/repositories/releases/</url>
            </repository>
            <snapshotRepository>
                <id>nexus-snapshots</id>
                <url>http://172.17.103.59:8081/nexus/content/repositories/snapshots/</url>
            </snapshotRepository>
        </distributionManagement>


[参考博客](https://blog.csdn.net/aitangyong/article/details/53332091)
[参考官网](http://maven.apache.org/pom.html)
