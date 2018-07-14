# [Failed to read schema document 'http://code.alibabatech.com/schema/dubbo/dubbo.xsd'问题解决方法](https://blog.csdn.net/wabiaozia/article/details/50491700)
1.在本地maven仓库中找到dubbo.jar,把META-INF/dubbo.xsd复制到某个目录下
2.在Eclipse中 Window -> Preference -> XML Catalog -> add 加入 

    key: path\to\dubbo.xsd
    value:http://code.alibabatech.com/schema/dubbo/dubbo.xsd
