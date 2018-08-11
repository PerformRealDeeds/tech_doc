[装配文件格式](https://maven.apache.org/plugins/maven-assembly-plugin/assembly.html#class_dependencySet)






# assembly例子 将hive-jdbc依赖包打入一个jar中
1. 在pom中加入hive-jdbc依赖
2. 在pom中加入assembly配置
    <plugins>
        <plugin>
          <artifactId>maven-assembly-plugin</artifactId>
          <version>3.1.0</version>
          <configuration>
            <descriptorRefs>
              <descriptorRef>jar-with-dependencies</descriptorRef>
            </descriptorRefs>
          </configuration>
          <executions>
            <execution>
              <id>make-assembly</id> <!-- this is used for inheritance merges -->
              <phase>package</phase> <!-- bind to the packaging phase -->
              <goals>
                <goal>single</goal>
              </goals>
            </execution>
          </executions>
        </plugin>
      </plugins>


## 过滤文件
<assembly xmlns="http://maven.apache.org/ASSEMBLY/2.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/ASSEMBLY/2.0.0 http://maven.apache.org/xsd/assembly-2.0.0.xsd">
  <id>distribution</id>
  <formats>
    <format>jar</format>
  </formats>
  <fileSets>
    <fileSet>
      <directory>${basedir}</directory>
      <includes>
        <include>*.txt</include>
      </includes>
      <excludes>
        <exclude>README.txt</exclude>
        <exclude>NOTICE.txt</exclude>
      </excludes>
    </fileSet>
  </fileSets>
</assembly>



