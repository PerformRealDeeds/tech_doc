# [Failed to read schema document 'http://code.alibabatech.com/schema/dubbo/dubbo.xsd'问题解决方法](https://blog.csdn.net/wabiaozia/article/details/50491700)
1.在本地maven仓库中找到dubbo.jar,把META-INF/dubbo.xsd复制到某个目录下
2.在Eclipse中 Window -> Preference -> XML Catalog -> add 加入 

    key: path\to\dubbo.xsd
    value:http://code.alibabatech.com/schema/dubbo/dubbo.xsd

## Dubbo 报错 Exception in thread "main" java.lang.NoClassDefFoundError: org/I0Itec/zkclient/IZkStateList
添加依赖：

    <dependency>
        <groupId>com.101tec</groupId>
        <artifactId>zkclient</artifactId>
        <version>0.9</version>
    </dependency>

## dubbo报错Data length too large: 10710120
 修改提供方的dubbo配置，在dubbo.properties 中增加如下`dubbo.protocol.dubbo.payload=11557050`（默认为8M，即8388608）

 但是在spring-boot中设置`spring.dubbo.protocol.dubbo.payload=11557050`失效， 改成`spring.dubbo.protocol.payload=11557050`成功

 ## dubbo报错：java.lang.NumberFormatException: For input string: ":9090"

    spring.dubbo.registry.address=zookeeper://http://ip:2181
多写了http://,去掉后正常

    spring.dubbo.registry.address=zookeeper://ip:2181
