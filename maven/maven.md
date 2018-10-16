Maven本质上是一个插件执行框架.

---
 javax.tools.JavaCompiler


---
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
* [maven插件](https://maven.apache.org/plugins/)
* [pom](http://maven.apache.org/pom.html)
* [mirror配置](http://maven.apache.org/guides/mini/guide-mirror-settings.html)


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

        <profile>      
             <id>jdk-1.7</id>      
              <activation>      
                   <activeByDefault>true</activeByDefault>      
                   <jdk>1.7</jdk>      
               </activation>      
         <properties>      
        <maven.compiler.source>1.7</maven.compiler.source>      
        <maven.compiler.target>1.7</maven.compiler.target>      
        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>             
        </properties>      
        </profile>    

设置完成后，右键项目->maven->update project,这样每次新建maven项目都默认为jdk1.7版本了  


---
## 常用命令
* mvn clean install -DskipTests
  *  -DskipTests:不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下。
  *  -Dmaven.test.skip=true，不执行测试用例，也不编译测试用例类。

----



## 生命周期
 三套生命周期

　　Maven定义了三套生命周期：clean、default、site，每个生命周期都包含了一些阶段（phase）。三套生命周期相互独立，但各个生命周期中的phase却是有顺序的，且后面的phase依赖于前面的phase。执行某个phase时，其前面的phase会依顺序执行，但不会触发另外两套生命周期中的任何phase。

1. clean生命周期

        pre-clean    ：执行清理前的工作；
        clean    ：清理上一次构建生成的所有文件；
        post-clean    ：执行清理后的工作
2. default生命周期 

default生命周期是最核心的，它包含了构建项目时真正需要执行的所有步骤。

        validate
        initialize
        generate-sources
        process-sources
        generate-resources
        process-resources    ：复制和处理资源文件到target目录，准备打包；
        compile    ：编译项目的源代码；
        process-classes
        generate-test-sources
        process-test-sources
        generate-test-resources
        process-test-resources
        test-compile    ：编译测试源代码；
        process-test-classes
        test    ：运行测试代码；
        prepare-package
        package    ：打包成jar或者war或者其他格式的分发包；
        pre-integration-test
        integration-test
        post-integration-test
        verify
        install    ：将打好的包安装到本地仓库，供其他项目使用；
        deploy    ：将打好的包安装到远程仓库，供其他项目使用；
3. site生命周期

        pre-site
        site    ：生成项目的站点文档；
        post-site
        site-deploy    ：发布生成的站点文档




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
---
## maven插件
[maven-assembly-plugin例子](https://maven.apache.org/plugins/maven-assembly-plugin/examples/single/filtering-some-distribution-files.html)

## maven 同路径同类名的类重复
module-c依赖module-a和module-b, a和b有相同路径，相同类名的类，在c中引用这个类的归属模块（例如org.module.Repeat类在a和b的模块中）时，取决于声明a或者b的顺序，先声明的类先被ClassLoader加载。 
模块a中类A依赖同包类B的一个方法method-x，模块b有同路径同类名的类B，但是没有method-x，如果模块b中的类B先加载，就会导致模块a中的类A出错，会报java.lang.NoSuchMethodError的异常。解决方法：排除模块b或者声明模块a。
通常不会有这种情况，因为路径中的模块名通常不一样。通常是同一个jar的不同版本。

## [maven 建立有META-INF/services的jar包](https://stackoverflow.com/questions/17531625/how-to-include-a-config-file-in-the-meta-inf-services-folder-of-a-jar-using-ma)
Java SPI用到。
Create a new source folder with the location `src/main/resources`, then create your `META-INF/services` folder in there and drop in your FQCN file. This should copy them into the jar file automatically. So you'll have:

    Project
    | src
    | | main
    |   | java
    |     | [your source code]
    |   | resources
    |     | META-INF
    |       | services
    |         | [your service files]

## [mavnen默认生命周期绑定](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference)

    Clean Lifecycle Bindings
    clean	clean:clean

    Default Lifecycle Bindings - Packaging ejb / ejb3 / jar / par / rar / war
    process-resources	resources:resources
    compile	                compiler:compile
    process-test-resources	resources:testResources
    test-compile	        compiler:testCompile
    test	                surefire:test
    package	                ejb:ejb or ejb3:ejb3 or jar:jar or par:par or rar:rar or war:war
    install	                install:install
    deploy	                deploy:deploy



    
