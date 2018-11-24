## java path
目的：在任意目录下执行JAVA_HOME\bin下的命令，不用输入绝对路径

不把`C:\Java\jdk1.7.0\bin`加入windows变量中，javac、java、javadoc命令等需要使用绝对路径，如C:\Java\jdk1.7.0\bin\java

windows设置java path的方法：
```
Windows 7:
From the desktop, right click the Computer icon.
Choose Properties from the context menu.
Click the Advanced system settings link.
Click Environment Variables. In the section System Variables, find the PATH environment variable and select it. Click Edit. If the PATH environment variable does not exist, click New.
In the Edit System Variable (or New System Variable) window, specify the value of the PATH environment variable. Click OK. Close all remaining windows by clicking OK.
```

linux设置java path的方法：

For bash, edit the startup file (~/.bashrc):
```
PATH=/usr/local/jdk1.7.0/bin:$PATH
export PATH
```


---

## java classpath
目的：告诉jdk工具和java应用第三方库和用户自定义class文件的路径

类路径默认值是`.`,即当前目录。

### 设置java classpath
类似设置java path

### java classpath 优先级
* The default value, ".", meaning that user class files are all the class files in the current directory (or under it, if in a package).
* The value of the CLASSPATH environment variable, which overrides the default value.
* The value of the -cp or -classpath command line option, which overrides both the default value and the CLASSPATH value. ## 不要使用CLASSPATH环境变量！ 不要让程序紧耦合到环境变量，一旦它变化，程序有极大可能报错。
```
保证classpath环境变量是空的
Verify classpath
//Windows
c:/> echo %CLASSPATH% 
输出：%CLASSPATH% 
 
//Linux/Unix
$ echo $CLASSPATH
输出： CLASSPATH: Undefined variable error (Solaris or Linux) 
```
* The JAR archive specified by the -jar option, which overrides all other values. If this option is used, all user classes must come from the specified archive.
  
### 注意
1. 类路径下的通配符*只匹配 .jar或者.JAR。如 mydir/*只匹配mydir下的jar包。要想匹配路径下的class文件，需要使用 `mydir:mydir/* or mydir/*:mydir`,前者先加载class文件和资源，后者先加载jar包。
2. 不会递归搜索子目录。
3. 加载同一个目录下的jar包时不确定的，同一个机器上的不同时刻都可能影响顺序，不应该依赖jar包的加载顺序。
4. java.class.path是一个系统属性。

### 一些例子
* java -classpath C:\java\MyClasses\myclasses.jar utility.myapp.Cool  // Cool类文件必须在myclasses.jar中的utility.myapp目录下，并且utility是myclasses.jar下的一个顶级目录
* java -classpath C:\java\MyClasses;C:\java\OtherClasses ... //指定多个类路径

-------------------
## 打印java类路径 参考spring-boot ApplicationEnvironmentPreparedEvent代码

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader instanceof URLClassLoader) {
			for (URL url : ((URLClassLoader) classLoader).getURLs()) {
				System.out.println(url);
			}
		}

	 




Read More:
## 参考
[java官网参考](https://docs.oracle.com/javase/tutorial/essential/environment/paths.html)
[java官网 如何找class文件](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/classpath.html)
[stackoverflow 形象的例子](https://stackoverflow.com/questions/2396493/what-is-a-classpath)

bing搜索 java classpath
