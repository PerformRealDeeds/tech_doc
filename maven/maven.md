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

## 获取某个jar中的部分文件
将dubbo.jar中META-INF/assembly/目录下的文件放到自己的工程目录下。
    <plugin>
    	<artifactId>maven-dependency-plugin</artifactId>
    	<executions>
    		<execution>
    			<id>unpack</id>
    			<phase>package</phase>
    			<goals>
    				<goal>unpack</goal>
    			</goals>
    			<configuration>
    				<artifactItems>
    					<artifactItem>
    						<groupId>com.alibaba</groupId>
    						<artifactId>dubbo</artifactId>
    						<version>${project.parent.version}</version>
    						<outputDirectory>${project.build.directory}/dubbo</outputDirectory>
    						<includes>META-INF/assembly/**</includes>
    					</artifactItem>
    				</artifactItems>
    			</configuration>
    		</execution>
    	</executions>
    </plugin>

## 修改Maven默认的JDK版本
1. 在maven的pom.xml中添加以下代码指定jdk版本

        <build>    
            <pluginManagement>    
                <plugins>    
                 <plugin>      
                    <groupId>org.apache.maven.plugins</groupId>      
                    <artifactId>maven-compiler-plugin</artifactId>      
                    <configuration>      
                        <source>1.7</source>      
                        <target>1.7</target>      
                    </configuration>      
                </plugin>      
               </plugins>    
            </pluginManagement>    
        </build>    

2. 在maven的安装目录找到settings.xml文件，在里面添加如下代码

        profile>      
             <id>jdk-1.7</id>      
              <activation>      
                   <activeByDefault>true</activeByDefault>      
                   <jdk>1.7</jdk>      
               </activation>      
         <properties>      
        maven.compiler.source>1.7</maven.compiler.source>      
        maven.compiler.target>1.7</maven.compiler.target>      
        maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>             
        </properties>      
        </profile>    

设置完成后，右键项目->maven->update project,这样每次新建maven项目都默认为jdk1.7版本了  


---
## 常用命令
* mvn clean install -DskipTests
  *  -DskipTests:不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下。
  *  -Dmaven.test.skip=true，不执行测试用例，也不编译测试用例类。

----


---
## 生命周期


---
## maven插件
[maven-assembly-plugin例子](https://maven.apache.org/plugins/maven-assembly-plugin/examples/single/filtering-some-distribution-files.html)





## maven 设置编译版本为java8 设置字符集为utf8
    <project>  
    
        ...  
    
        <properties>  
            <maven.compiler.source>1.8</maven.compiler.source>  
            <maven.compiler.target>1.8</maven.compiler.target>  
            <project.build.sourceEncoding>utf-8</project.build.sourceEncoding>  
        </properties>  
    
        ...  
    
    </project>  





    