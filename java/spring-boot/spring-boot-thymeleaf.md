[thymeleaf官网](https://www.thymeleaf.org/)
[参考博客](https://blog.csdn.net/u014695188/article/details/52347318)

## 资源文件的约定目录结构 
* Maven的资源文件目录：/src/main/resources 
* spring-boot项目静态文件目录：/src/main/resources/static 
* spring-boot项目模板文件目录：/src/main/resources/templates 
* spring-boot静态首页的支持，即index.html放在以下目录结构会直接映射到应用的根目录下：

## thymeleaf前后缀
在spring-boot下，默认约定了Controller试图跳转中thymeleaf模板文件的的前缀prefix是”classpath:/templates/”,后缀suffix是”.html” 
这个在application.properties配置文件中是可以修改的。 
如下配置可以修改试图跳转的前缀和后缀

    spring.thymeleaf.prefix: /templates/  
    spring.thymeleaf.suffix: .html

## 依赖
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>


##  thymeleaf配置

    ########################################################
    ###THYMELEAF (ThymeleafAutoConfiguration)
    ########################################################
    #spring.thymeleaf.prefix=classpath:/templates/
    #spring.thymeleaf.suffix=.html
    #spring.thymeleaf.mode=HTML5
    #spring.thymeleaf.encoding=UTF-8
    #charset=<encoding> is added
    #spring.thymeleaf.content-type=text/html
    # set to false for hot refresh
    spring.thymeleaf.cache=false



## 开发时修改thymeleaf模板自动重新加载配置 

    # Allow Thymeleaf templates to be reloaded at dev time  
    spring.thymeleaf.cache: false  
    server.tomcat.access_log_enabled: true  
    server.tomcat.basedir: target/tomcat




## thymeleaf常用基础知识点 

1. 在html页面中引入thymeleaf命名空间，即<html xmlns:th=http://www.thymeleaf.org></html>，此时在html模板文件中动态的属性使用th:命名空间修饰 

2. 引用静态资源文件，比如CSS和JS文件，语法格式为“@{}”，如@{/js/blog/blog.js}会引入/static目录下的/js/blog/blog.js文件 


3. 访问spring-mvc中model的属性，语法格式为“${}”，如${user.id}可以获取model里的user对象的id属性 
 
4. 循环，在html的标签中，加入th:each=“value:${list}”形式的属性，如<span th:each=”user:${users}”></span>可以迭代users的数据 

5. 判断，在html标签中，加入th:if=”表达式”可以根据条件显示html元素 
<span th:if="${not #lists.isEmpty(blog.publishTime)}"> 
<span id="publishtime" th:text="${#dates.format(blog.publishTime, 'yyyy-MM-dd HH:mm:ss')}"></span> 
</span> 
以上代码表示若blog.publishTime时间不为空，则显示时间 

6. 时间的格式化
${#dates.format(blog.publishTime,'yyyy-MM-dd HH:mm:ss')} 
表示将时间格式化为”yyyy-MM-dd HH:mm:ss”格式化写法与Java格式化Date的写法是一致的。 

7. 字符串拼接，有两种形式 
比如拼接这样一个URL:/blog/delete/{blogId} 
第一种：th:href="'/blog/delete/' + ${blog.id }" 
第二种：th:href="${'/blog/delete/' + blog.id }" 