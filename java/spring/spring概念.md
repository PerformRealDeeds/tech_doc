* IoC(Inversion of Controll)控制反转: 在组件类中，不需要使用新的操作符来实例化依赖组件，而是在运行时由容器实例将依赖组件注入组件。它主要由两种形式：依赖查找(dependency lookup)(很少用了)和依赖注入(dependency injection)
* 扫描指定包下面的注解类，加入容器<context:component-scan base-package="com.my.package">

* 依赖注入有setter注入和构造函数注入。构造函数注入有两种缺点：
* * 无法处理循环依赖
* * 通过类型不能确定参数时需要指定index

bean的作用域:
* singleton 单例 无状态实例 同一时间服务于不同的请求
* prototype 原型 

循环依赖：
对于构造器参数的循环依赖，spring会报错，而setter参数的循环依赖可以正常工作。