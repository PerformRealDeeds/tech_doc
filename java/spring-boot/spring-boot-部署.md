## 配置分离
1. 打包时排除配置文件 
注意：只能排除application*.properties和logback-spring.xml，用`spring.profiles.location`指定application*.properties所在的文件夹，用`logging.config=file:../config/logback-spring.xml`指定logback-spring.xml的路径，其它自定义properties暂时没找到使用类路径方式，只能打在jar包中，或者在java中使用绝对路径读取自定义配置文件

    mvn clean install -P prd -DskipTests   -DskipTests 
    <profile> <id>prd</id>
    <build>
    <resources> 
        <resource> 
            <directory>src/main/resources</directory> 
            <excludes> 
                <exclude>application*.properties</exclude> 
                <exclude>logback-spring.xml</exclude>
            </excludes>
        </resource> 
    </resources> 
    </build>
    </profile> 
1. java -jar your.jar  


java -jar xxx-abcd-0.0.1.SNAPSHOT.jar --spring.profiles.location=../config/ \
--logging.config=file:../config/logback-spring.xml







