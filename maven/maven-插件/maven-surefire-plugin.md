# [maven测试插件](https://maven.apache.org/surefire/maven-surefire-plugin/index.html)
surefire: 一定成功

## 目标
| Goal          | Description                                                                                                                             |
| ------------- | --------------------------------------------------------------------------------------------------------------------------------------- |
| `surefire:help` | Display help information on maven-surefire-plugin.Call `mvn surefire:help -Ddetail=true -Dgoal=<goal-name>` to display parameter details. |
| `surefire:test` | Run tests using Surefire.                                                                                                               |

## 使用
        <project>
          ...
          <build>
            <!-- To define the plugin version in your parent POM -->
            <pluginManagement>
              <plugins>
                <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-surefire-plugin</artifactId>
                  <version>2.22.0</version>
                </plugin>
                ...
              </plugins>
            </pluginManagement>
            <!-- To use the plugin goals in your POM or parent POM -->
            <plugins>
              <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.0</version>
              </plugin>
              ...
            </plugins>
          </build>
          ...
        </project>

        