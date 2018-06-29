
[csdn博客](https://blog.csdn.net/fighterandknight/article/details/51200470)

[csdn博客](https://blog.csdn.net/danchu/article/details/70238002)

[csdn博客](https://blog.csdn.net/zghwaicsdn/article/details/50957474)

[深入理解CGLIB动态代理机制](https://www.jianshu.com/p/9a61af393e41?from=timeline&isappinstalled=0)


---
jdk动态代理

[jdk动态代理](https://www.cnblogs.com/MOBIN/p/5597215.html)

[深入理解JDK动态代理机制](https://www.jianshu.com/p/471c80a7e831)

    class HWInvocationHandler implements InvocationHandler{
            //目标对象
            private Object target;
            public HWInvocationHandler(Object target){
                this.target = target;
            }
            //proxy参数是jvm动态生成的代理类**对象**,比如$Proxy0
            @Override 
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("------插入前置通知代码-------------");
                //执行相应的目标方法
                Object rs = method.invoke(target,args);
                System.out.println("------插入后置处理代码-------------");
                return rs;
            }
        }

    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    IHello  ihello = (IHello) Proxy.newProxyInstance (IHello.class.getClassLoader(),  //加载接口的类加载器
                  new Class[]{IHello.class},      //一组接口
                  new HWInvocationHandler(new MyHello())); //自定义的InvocationHandler    mybatis是动态代理的方法
          ihello.sayHello();

动态生成的代理类 继承了java.lang.reflect.Proxy

    public final class $Proxy0 extends Proxy implements IHello

    //接口代理方法 super.h是java.lang.reflect.Proxy的常量InvocationHandler invoke包含一些代理类逻辑
     public final void sayHello() throws  {
         try {
             super.h.invoke(this, m3, (Object[])null);
         } catch (RuntimeException | Error var2) {
             throw var2;
         } catch (Throwable var3) {
             throw new UndeclaredThrowableException(var3);
         }
     }
---


|代理方式   |   实现  |  优点 |   缺点 |   特点 |
|  -       |-       |-       |-      |-       |
|JDK静态代理|  代理类与委托类实现同一接口，并且在代理类中需要硬编码接口  |  实现简单，容易理解    |代理类需要硬编码接口，在实际应用中可能会导致重复编码，浪费存储空间并且效率很低      |好像没啥特点
|JDK动态代理 |   代理类与委托类实现同一接口，主要是通过代理类实现InvocationHandler并重写invoke方法来进行动态代理的，在invoke方法中将对方法进行增强处理  |   不需要硬编码接口，代码复用率高  |   只能够代理实现了接口的委托类 |    底层使用反射机制进行方法的调用 |
|CGLIB动态代理|    代理类将委托类作为自己的父类并为其中的非final委托方法创建两个方法，一个是与委托方法签名相同的方法，它在方法中会通过super调用委托方法；另一个是代理类独有的方法。在代理方法中，它会判断是否存在实现了MethodInterceptor接口的对象，若存在则将调用intercept方法对委托方法进行代理 |   可以在运行时对类或者是接口进行增强操作，且委托类无需实现接口   |  不能对final类以及final方法进行代理  |  底层将方法全部存入一个数组中，通过数组索引直接进行方法调用


代理的目的：
1.控制对象的访问
2.不修改源码，实现方法增强

代理模式 vs 装饰模式
同：都不修改源代码，JDK动态代理和装饰模式都要实现同样的接口。
异：
1. 装饰模式侧重于增加对象的行为或者责任，如java中的io: 

     InputStream bio = new BufferedInputStream(new ByteArrayInputStream(null));
3. 代理模式不增加接口