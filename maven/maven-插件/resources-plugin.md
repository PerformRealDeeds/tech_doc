[maven-resources-plugin官网文档](http://maven.apache.org/plugins/maven-resources-plugin/)

作用：复制工程资源到输出目录。
目标：
* `resources:resources`: 默认绑定到 process-resources周期，资源从`{ project.build.resources}` -> `{project.build.outputDirectory}` 
* `resources:testResources`: 默认绑定到 process-test-resources周期，资源从 `{project.build.testResources}`: -> `{project.build.testOutputDirectory}`
* `resources:copy-resources`


--- 
# 使用

## [指定读写文件的编码](http://maven.apache.org/plugins/maven-resources-plugin/examples/encoding.html)

最好最简单的方式如下：

    <project ...>
     ...
     <properties>
       <project.build.sourceEncoding>UTF-8</project.build.  sourceEncoding>
       ...
     </properties>
     ..
    </project>

maven-resources-plugin会自动使用上面的配置。

或者麻烦点：

    <project>
      ...
      <build>
        <plugins>
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-resources-plugin</artifactId>
            <version>3.1.0</version>
            <configuration>
              ...
              <encoding>UTF-8</encoding>
              ...
            </configuration>
          </plugin>
        </plugins>
        ...
      </build>
      ...
    </project>

---
## [自定义资源目录](http://maven.apache.org/plugins/maven-resources-plugin/examples/resource-directory.html)
maven默认的资源目录是src/main/resources.
自定义资源目录pom配置：

    <project>
     ...
     <build>
       ...
       <resources>
         <resource>
           <directory>[your folder here]</directory>
         </resource>
       </resources>
       ...
     </build>
     ...
    </project>

如果资源目录如下：

    Project
    |-- pom.xml
    `-- src
        `-- my-resources
pom配置如下：

     ...
       <resources>
         <resource>
           <directory>src/my-resources</directory>
         </resource>
       </resources>
       ...

多个资源目录：

    ..
     <resources>
       <resource>
         <directory>resource1</directory>
       </resource>
       <resource>
         <directory>resource2</directory>
       </resource>
       <resource>
         <directory>resource3</directory>
       </resource>
     </resources>
     ...

---
## [过滤](http://maven.apache.org/plugins/maven-resources-plugin/examples/filter.html)

${}被替换成值。

1. 设置`filtering`为true， 可以用maven自有的属性，或者properties中的属性，或者通过mvn -Dkey=val设置的属性替换${key}

        <project>
          ...
          <name>My Resources Plugin Practice Project</name>
          ...
          <build>
            ...
            <resources>
              <resource>
                <directory>src/main/resources</directory>
                <!-- filtering设置成true表示src/main/resources目录下的text文件中的${}会被替换成   值 -->
                <filtering>true</filtering>
              </resource>
              ...
            </resources>
            ...
          </build>
          ...
        </project>
1. 属性值放入一个文件中，

        <project>
          ...
          <name>My Resources Plugin Practice Project</name>
          ...
          <build>
            ...
            <filters>
              <filter>my-filter-values.properties</filter>
            </filters>
            ...
          </build>
          ...
        </project>
1. 一个目录想过滤，一个不想过滤

        <project>
          ...
          <build>
            ...
            <resources>
              <resource>
                <directory>src/main/resources-filtered</directory>
                <filtering>true</filtering>
              </resource>
              <resource>
                <directory>src/main/resources</directory>
              </resource>
              ...
            </resources>
            ...
          </build>
          ...
        </project>

src/main/resources中的${}不会被替换，src/main/resources-filtered中的${}会被替换

---

## 包含或者排除文件

Of course, we can also have both <includes> and <excludes> elements. For example, if we want to include all text files that does not contain the word "test" in their filename.

    <project>
      ...
      <name>My Resources Plugin Practice Project</name>
      ...
      <build>
        ...
        <resources>
          <resource>
            <directory>src/my-resources</directory>
            <includes>
              <include>**/*.txt</include>
            </includes>
            <excludes>
              <exclude>**/*test*.*</exclude>
            </excludes>
          </resource>
          ...
        </resources>
        ...
      </build>
      ...
    </project>

---

## 转义过滤

        <project>
          ...
          <build>
            <plugins>
              <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>3.1.0</version>
                <configuration>
                  ...
                  <escapeString>\</escapeString>
                  ...
                </configuration>
              </plugin>
            </plugins>
            ...
          </build>
          ...
        </project>

如果启用了filter, 那么\${java.home}不会被替换，而是变为${java.home};  ${java.home}就会被设置成值,C:\\Java\\jre1.8.0_172

---

## 复制资源

    <project>
      ...
      <build>
        <plugins>
          <plugin>
            <artifactId>maven-resources-plugin</artifactId>
            <version>3.1.0</version>
            <executions>
              <execution>
                <id>copy-resources</id>
                <!-- here the phase you need -->
                <phase>validate</phase>
                <goals>
                  <goal>copy-resources</goal>
                </goals>
                <configuration>
                  <outputDirectory>${basedir}/target/extra-resources</outputDirectory>
                  <resources>          
                    <resource>
                      <directory>src/non-packaged-resources</directory>
                      <filtering>true</filtering>
                    </resource>
                  </resources>              
                </configuration>            
              </execution>
            </executions>
          </plugin>
        </plugins>
        ...
      </build>
      ...
    </project>



