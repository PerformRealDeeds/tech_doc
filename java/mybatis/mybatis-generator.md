## 安装eclipse mybatis-generator插件
1. 从[eclipse mybatis-generator插件](https://github.com/mybatis/generator/releases)下载 org.mybatis.generator.eclipse.site*.zip
2. eclipse -> Help -> install new software -> add -> archive (选择下载的zip包) -> next(把contact all update sites during install ... 去掉) -> 一直next.
3. 选择工程 new -> other -> 输入my模糊搜索 -> mybatis generator 
4. [参考官网配置文件](http://www.mybatis.org/generator/quickstart.html)
5. [参考2](http://www.mybatis.org/generator/configreference/xmlconfig.html)



## 遇到的问题 The content of element type "context" must match "(property*,plugin*,commentGenerator?
配置文件 generatorConfig.xml 里面的context的子元素必须按照**它给出的顺序**，如错误提示的match“……”部分。 当然也可能是你xml文件有错（这个容易检查出来）