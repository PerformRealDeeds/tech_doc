## Tomcat部署的三种方式
1. 将打包好的web_pro.war包放入webapps，启动/bin/startup.bat, 在浏览器输入localhost:8080/web_pro
2. **推荐** 在\conf\Catalina\localhost下新建一个web_pro.xml,加入

       <Context  docBase="D:\Program Files\web_pro" reloadable="true" />
    **path属性失效**, xml文件名相当于path。 localhost:8080/a.xml访问应用,a.xml修改为b.xml不用重启tomcat,访问路径变为localhost:8080/b
    后续步骤同1
如果webapps下还有项目，**随后**也会按照字母顺序启动
如果webapps下有同名的web项目文件目录,则不启动

3. 打开tomcat下conf/server.xml，在Host标签之间输入项目配置信息

        <Context path="/web_pro" docBase="D:/path/to/web_pro" reloadable="true" />
    path生效，path改为"/a"后，浏览器localhost:8080/a访问应用
4.    删除原 webapps/ROOT 目录下的所有文件，将应用下的所有文件和文件夹复制到webapps/ROOT文件夹下。  这时浏览器不用输入/web_pro，localhost:8080就可以访问应用了。因为Tomcat默认访问ROOT目录下的应用。
       **推荐** 或者不删除webapps/ROOT,在\conf\Catalina\localhost新建ROOT.xml文件，可以达到同样的效果localhost:8080不加路径直接访问自己的应用。 
       
    <Context  docBase="D:\Program Files\web_pro" reloadable="true" />

### 对于方法2和方法3的参数：
* path:浏览器访问时的路径名
* docBase:web项目的WebRoot所在的路径
* reloadble:设定项目有改动时，tomcat是否重新加载该项目

       <Context path="/web_pro"  docBase="D:\Program Files\spring-mvc-showcase1" reloadable="true" />

---





