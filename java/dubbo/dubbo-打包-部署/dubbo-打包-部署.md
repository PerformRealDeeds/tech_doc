参考dubbo2.5.6,dubbo代码`git clone -b dubbo-2.5.6  https://github.com/ap^Che/incubator-dubbo.git dubbo-2.5.6`

提供者的pom的build

        <build>
        <plugins>
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
                                    <!-- 解压dubbo.jar,取出META-INF/assembly/下的启动和停止shell脚本放到本项目/dubbo下  -->
                                    <outputDirectory>${project.build.directory}/dubbo</outputDirectory>
                                    <includes>META-INF/assembly/**</includes>
                                </artifactItem>
                            </artifactItems>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <descriptor>src/main/assembly/assembly.xml</descriptor>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

装配文件assembly.xml [assemblyp配置](https://maven.apache.org/plugins/maven-assembly-plugin/assembly.html#class_dependencySet)

    <assembly>
        <id>assembly</id>
        <formats>
            <format>tar.gz</format>
        </formats>
        <includeBaseDirectory>true</includeBaseDirectory>
        <fileSets>
            <fileSet>
                <directory>${project.build.directory}/dubbo/META-INF/assembly/bin</directory>
                <outputDirectory>bin</outputDirectory>
                <fileMode>0755</fileMode>
            </fileSet>
            <fileSet>
                <directory>src/main/assembly/conf</directory>
                <outputDirectory>conf</outputDirectory>
                <fileMode>0644</fileMode>
            </fileSet>
        </fileSets>
        <dependencySets>
            <dependencySet>
                <outputDirectory>lib</outputDirectory>
            </dependencySet>
        </dependencySets>
    </assembly>

        useProjectAttachments	boolean	Determines whether the attached artifacts produced during the current project's build should be included in this dependency set. (Since 2.2-beta-1) 
    Default value is: false.