## [使用自动装配获取ApplicationContext](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-aware)
As of Spring 2.5, autowiring is another alternative to obtain a reference to the ApplicationContext. 不再建议用`ApplicationContextAware `获取ApplicationContext的引用,因为引入了Spring的接口,和Spring耦合了.

在被Spring容器管理的bean中加入ApplicaitonContext的属性

    @Autowired
    public ApplicationContext ac;