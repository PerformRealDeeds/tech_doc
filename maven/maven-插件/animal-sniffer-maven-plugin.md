作用：用新版本的jdk编写的代码是否能在旧版本jdk上运行。

依赖：

        <!-- https://mvnrepository.com/artifact/org.codehaus.mojo/animal-sniffer-maven-plugin -->
        <dependency>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>animal-sniffer-maven-plugin</artifactId>
            <version>1.17</version>
        </dependency>

[官网文档](http://www.mojohaus.org/animal-sniffer/)




slf4j 1.7.25中用到这个插件

    <build>
       ...
        <pluginManagement>
          <plugins>
            <plugin>
              <groupId>org.codehaus.mojo</groupId>
              <artifactId>animal-sniffer-maven-plugin</artifactId>
              <version>1.14</version>
              <configuration>
                <signature>
                  <groupId>org.codehaus.mojo.signature</groupId>
                  <artifactId>java15</artifactId>
                  <version>1.0</version>
                </signature>
              </configuration>
            </plugin>
          </plugins>
        </pluginManagement>
    </build>



     <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>animal-sniffer-maven-plugin</artifactId>
        <configuration>
          <!-- Signatures cannot be determined and will error unless excluded.  This is isolated to
               code otherwise already marked for removal in the module artifact. -->
          <ignores>
            <ignore>org.slf4j.impl.StaticMDCBinder</ignore>
            <ignore>org.slf4j.impl.StaticLoggerBinder</ignore>
            <ignore>org.slf4j.impl.StaticMarkerBinder</ignore>
          </ignores>
        </configuration>
      </plugin>