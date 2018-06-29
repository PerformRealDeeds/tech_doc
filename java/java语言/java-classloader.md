[classloader文档](https://www.cnblogs.com/doit8791/p/5820037.html)

---
## 获取classLoader
*    Thread.currentThread.getContextClassLoader();
*    getClass().getClassLoader();
*    ClassLoader.getSystemClassLoader();

---
### classloader获取属性文件

               Properties prop = new Properties(); 
               InputStream in = Thread.currentThread.getContextClassLoader().getResourceAsStream("/test.properties"); //带/表示所有类路径下，不带/表示改class目录下
               try { 
                   prop.load(in); 
                   param1 = prop.getProperty("initYears1").trim(); 
                   param2 = prop.getProperty("initYears2").trim(); 
               } catch (IOException e) { 
                   e.printStackTrace(); 
               } 
