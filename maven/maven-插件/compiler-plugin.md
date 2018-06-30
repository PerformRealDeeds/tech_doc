

---
## 设置javac的版本(Setting the -source and -target of the Java Compiler) [javac -source  -target](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html)
    <project>  
    
        ...  
    
        <properties>  
            <maven.compiler.source>1.8</maven.compiler.source>  
            <maven.compiler.target>1.8</maven.compiler.target>  
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


    