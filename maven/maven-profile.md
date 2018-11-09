## mavne profile

Profile 如何配置
可以在两个位置配置profile：settings.xml 和 pom.xml
settings.xml里定义的profile是全局的，对所有的项目都可用，在里面定义的配置项也稍微少了些，只能定义远程服务器的信息和属性信息(<repositories>,<pluginRepositories>, <properties>)。这些信息在pom.xml里也是可以定义的。

pom.xml里可以定义的配置如下：

            <repositories>
            <pluginRepositories>
            <dependencies>
            <plugins>
            <properties>
            <modules>
            <reporting>
            <dependencyManagement>
            <distributionManagement>
            以及build下的：
                <defaultGoal>
                <resources>
                <testResources>
                <finalName>


## 例子：dev环境打包是排除一些配置文件
<profiles>
        <profile>
            <id>dev</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <build>
                <resources>
                    <resource>
                        <directory>src/main/resources</directory>
                        <excludes>
                            <exclude>config.*.properties</exclude>
                            <exclude>log4j-*.xml</exclude>
                        </excludes>
                    </resource>
                </resources>
            </build>
        </profile>
</profiles>

## 参考
[使用 Maven Profile 和 Filtering 打各种环境的包](https://segmentfault.com/a/1190000003908040)



