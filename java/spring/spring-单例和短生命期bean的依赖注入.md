## [短生命期bean(如request)注入单例bean](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans-factory-scopes-other)
如果没有`<aop:scoped-proxy/>`,那么userPreferences只会在单例bean userManager初始化后注入一次，userManager操作的一直是同一个session bean.

```
<bean id="userPreferences" class="com.something.UserPreferences" scope="session">
    <aop:scoped-proxy/>
</bean>

<bean id="userManager" class="com.something.UserManager">
    <property name="userPreferences" ref="userPreferences"/>
</bean>
```


