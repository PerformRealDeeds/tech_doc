
1、在setting.xml文件中增加用户名和密码配置（特别注意这里的ID）
```
<servers>
        <!-- 用于发布正式版本 -->
        <server>
            <id>maven-repository-releases</id>
            <username>admin</username>
            <password>admin123</password>
        </server>
        <!-- 用于发布快照版本 -->
        <server>
            <id>maven-repository-snapshots</id>
            <username>admin</username>
            <password>admin123</password>
        </server>
    </servers>
```


2、在项目的pom.xml中增加以下内容
```
<build>
        <plugins>
            <!-- 要将源码放上去，需要加入这个插件 -->
            <plugin>
                <artifactId>maven-source-plugin</artifactId>
                <version>2.1</version>
                <configuration>
                    <attach>true</attach>
                </configuration>
                <executions>
                    <execution>
                        <phase>compile</phase>
                        <goals>
                            <goal>jar</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

    <distributionManagement>
        <repository>
            <!-- 这里的ID要和setting的id一致 -->
            <id>maven-repository-releases</id>
            <url>http://ip:8081/nexus/content/repositories/thirdparty/</url>
        </repository>
        <!--这是打成快照版本的配置，如果不用这个snapshotRepository标签，打包失败，会报权限问题 -->
        <snapshotRepository>
            <id>maven-repository-snapshots</id>
            <url>http://ip:8081/nexus/content/repositories/thirdparty</url>
        </snapshotRepository>
    </distributionManagement>
```



3. 运行maven命令
   
        mvn clean deploy -DskipTests


注意： 发布api jar包到项目库时，要把父母项目也要deploy，不然别人下载api包时，解析不到父母项目的包，就不往下走了，表现为只能下载api依赖的一些pom文件，不能下载jar包。