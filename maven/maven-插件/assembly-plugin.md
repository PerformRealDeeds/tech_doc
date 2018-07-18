[装配文件格式](https://maven.apache.org/plugins/maven-assembly-plugin/assembly.html#class_dependencySet)






# assembly例子
1. 将依赖打入同一个jar包

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



