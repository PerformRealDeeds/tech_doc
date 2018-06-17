

## [maven 设置编译版本为java8 设置字符集为utf8](https://maven.apache.org/plugins/maven-compiler-plugin/examples/set-compiler-source-and-target.html)
    <project>  
    
        ...  
    
        <properties>  
            <maven.compiler.source>1.8</maven.compiler.source>  
            <maven.compiler.target>1.8</maven.compiler.target>  
            <project.build.sourceEncoding>utf-8</project.build.sourceEncoding>  
        </properties>  
    
        ...  
    
    </project>  

或者

    <project>
      [...]
      <build>
        [...]
        <plugins>
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.7.0</version>
            <configuration>
              <source>1.8</source>
              <target>1.8</target>
            </configuration>
          </plugin>
        </plugins>
        [...]
      </build>
      [...]
    </project>

soucre和target的参数实际传给了javac 命令。
