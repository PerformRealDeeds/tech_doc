## 将api包deploy后，只能在自己机器上从nexus私服拉包，别人的机器上不能下载包
原因： 发布api jar包到项目库时，要把父母项目也要deploy，不然别人下载api包时，解析不到父母项目的包，就不往下走了，表现为只能下载api依赖的一些pom文件，不能下载jar包。
解决：从父母项目deploy，不能只在api包下deploy。

##resources filetering不生效
解决方法： 要看看eclipse中effective pom中`maven-resources-plugin`下`delimiter`下的值，有时是@，这种时候@key@才会被替换，${不会被替换}。
原因：spring-boot-starter-parent 中把maven-resources-plugin中delimiters设置成只有@,同时设置了<useDefaultDelimiters>false导致默认的maven配置不生效。

解决方法：改用@，或者用下面的配置替换

        <delimiters>
            <delimiter>${*}</delimiter>
            <delimiter>@</delimiter>
        </delimiters>
        
经验： eclipse中effective pom和官网的配置文档是解决问题的好方法。

[官网参考](https://maven.apache.org/plugins/maven-resources-plugin/resources-mojo.html)
    <delimiters>
    Set of delimiters for expressions to filter within the resources. These delimiters are specified in the form beginToken*endToken. If no * is given, the delimiter is assumed to be the same for start and end.So, the default filtering delimiters might be specified as:

    <delimiters>
      <delimiter>${*}</delimiter>
      <delimiter>@</delimiter>
    </delimiters>

    Since the @ delimiter is the same on both ends, we don't need to specify @*@ (though we can).

    <useDefaultDelimiters>
 	Use default delimiters in addition to custom delimiters, if any.
    Default value is: true. 

## resource + pforile 排除标签失效
现场：
    <build>
        ...
        <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
        </resource>
        </resources>
        ...
    </build>

   <profiles>
         <profile>
            <resources>
                <resource>
                    <directory>src/main/resources</directory>
                    <exclude>application*.properties</exclude>
                    <exclude>logback-spring.xml</exclude>
                </resource>
                </resources>
        </profile>
   </profiles>

在effective pom中有效的pom是

        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <exclude>application*.properties</exclude>
                <exclude>logback-spring.xml</exclude>
            </resource>
        </resources>

在target目录下 application*.properties是存在的

原因：

解决方法：
resource都写在profile中




