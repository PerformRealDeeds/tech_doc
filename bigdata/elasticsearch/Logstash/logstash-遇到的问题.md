## ArgumentError:Setting "" hasn't been registered
原因: 改栋`config/logstash.yml`的`node.name`参数发生错误, node.name: 后面要接一个空格再接参数值. 注意看例子! 例子中有空格.

## hive jdbc驱动包依赖别的包,启动后报java.lang.NoClassDefFoundError错误
新建一个空maven工程,在pom中依赖hive-jdbc插件，使用assembly插件把依赖打入tar.gz包。 在logstash/config/jvm.options中加入java

## 