---

## 删除项目中的其它文件
Maven Clean Plugin默认删除target,可以配置删除额外的目录或者文件.

    <build>
      
        <plugin>
            <artifactId>maven-clean-plugin</artifactId>
            <version>3.1.0</version>
            <configuration>
            <filesets>
                <fileset>
                <directory>some/relative/path</directory>
                <includes>
                    <include>**/*.tmp</include>
                    <include>**/*.log</include>
                </includes>
                <excludes>
                    <exclude>**/important.log</exclude>
                    <exclude>**/another-important.log</exclude>
                </excludes>
                <followSymlinks>false</followSymlinks>
                </fileset>
            </filesets>
            </configuration>
        </plugin>
        
    </build>

    <directory>some/relative/path</directory> <==>  <directory>${basedir}some/relative/path</directory>


## 跳过clean
    <build>

        <plugin>
        <artifactId>maven-clean-plugin</artifactId>
        <version>3.1.0</version>
        <configuration>
            <skip>true</skip>
        </configuration>
        </plugin>

    </build>

    mvn clean -Dmaven.clean.skip=true

## 忽略错误

    <build>

        <plugin>
        <artifactId>maven-clean-plugin</artifactId>
        <version>3.1.0</version>
        <configuration>
            <failOnError>false</failOnError>
        </configuration>
        </plugin>

    </build>

    mvn clean -Dmaven.clean.failOnError=false



    