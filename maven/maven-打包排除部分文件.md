    <project>
      ...
      <name>My Resources Plugin Practice Project</name>
      ...
      <build>
        ...
        <resources>
          <resource>
            <directory>[your directory]</directory>
            <excludes>
              <exclude>[non-resource file #1]</exclude>
              <exclude>[non-resource file #2]</exclude>
              <exclude>[non-resource file #3]</exclude>
              ...
              <exclude>[non-resource file #n]</exclude>
            </excludes>
          </resource>
          ...
        </resources>
        ...
      </build>
      ...
    </project>

    
# 参考
[Including and excluding files and directories](https://maven.apache.org/plugins/maven-resources-plugin/examples/include-exclude.html)
[maven打包排除部分文件](https://blog.csdn.net/sundenskyqq/article/details/49930387)




