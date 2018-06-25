## 本机安装多个jdk后，java -version 和 javac -version可能会显示不同的java版本
   解决：修改JAVA_HOME的路径


## java抽象类的抽象方法的访问级别
抽象方法不能声明为private，可以声明为default,protected,public.
下面是java的访问级别，如果抽象方法声明为default，就只能由同一个包中的子类重写，其它包的类继承会报错。
Access Levels  Modifier

  | Modifier    | Class | Package | Subclass | World |
  | ----------- | ----- | ------- | -------- | ----- |  
  | ublic       | Y     | Y       | Y        | Y     |
  | protected   | Y     | Y       | Y        | N     |
  | no modifier | Y     | Y       | N        | N     |
  | private     | Y     | N       | N        | N     |

## java接口方法的访问级别
不能用private,static修饰接口方法。 接口方法只能是public abstract，不写public还是public。
接口方法默认public abstract,从类的反编译文件查看。




