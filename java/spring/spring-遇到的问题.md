## 找不到元素 'beans' 的声明。
Caused by: org.xml.sax.SAXParseException; lineNumber: 2; columnNumber: 8; cvc-elt.1: 找不到元素 'beans' 的声明。

解决：在spring配置文件中声明正确的xsd，如
    <?xml version="1.0" encoding="UTF-8"?>
    <!-- 添加 DUBBO SCHEMA -->
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd 
            http://code.alibabatech.com/schema/dubbo
            http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

        ...
    </beans>



 在spring-beanas-5.0.7.RELEASE.jar下META-INF/spring.shcemas中有xsd的映射，即不用访问http,直接从spring jar中加载xsd







