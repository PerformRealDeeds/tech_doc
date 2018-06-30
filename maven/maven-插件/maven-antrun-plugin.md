删除target/classes/org/slf4j/impl,这个是假的.

     <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-antrun-plugin</artifactId>
        <executions>
          <execution>
            <phase>process-classes</phase>
            <goals>
             <goal>run</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <tasks>
            <echo>Removing slf4j-api's dummy StaticLoggerBinder and StaticMarkerBinder</echo>
            <delete dir="target/classes/org/slf4j/impl"/>
          </tasks>
        </configuration>
      </plugin>

可以用resoucr