## 安装
1. 上[maven官网](https://maven.apache.org/download.cgi)下载压缩包
2. 添加bin目录到环境变量
   * 新建MAVEN_HOME变量,值是maven的根目录
   * 在path变量中加入%MAVEN_HOME%\bin
3. 在cmd中输入mvn -v 有正确输出说明maven安装正确


---
## 配置
修改%MAVEN_HOME%\conf\settings.xml中的配置
1.  localRepository : /path/to/local/repo
2.  [搭建私服](https://blog.csdn.net/wild46cat/article/details/73697109)

---
## 资料
* 《《Maven实战》》
* [maven官网](https://maven.apache.org/)
* [pom](http://maven.apache.org/pom.html)


---
## 可执行jar配置和依赖入jar包
[可执行jar配置和依赖入jar包](http://blog.51cto.com/coffee/2091717)

    <build>
        <finalName>your_file_name</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.0.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>chap2.ProducerDemo</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <!-- this is used for inheritance merges -->
                        <phase>package</phase>
                        <!-- bind to the packaging phase -->
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

---
## 常用命令
* mvn clean install -DskipTests
  *  -DskipTests:不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下。
  *  -Dmaven.test.skip=true，不执行测试用例，也不编译测试用例类。





## 生命周期





## maven pom配置
##  配置maven 使用的jdk版本

        <properties>
            <java-version>1.8</java-version>
        </properties>

       <build>
            <plugins>
                  <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.7.0</version>
                    <configuration>
                      <source>${java-version}</source>
                      <target>${java-version}</target>
                    </configuration>
                  </plugin>
            </plugins>           
        </build>
