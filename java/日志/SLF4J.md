* SLF4J(Simple logging Facade for Java)不是一个真正的日志实现，而是一个抽象层（ abstraction layer），它允许你在后台使用任意一个日志类库。
* SLF4J有占位符(place holder)的功能


## SL4J配合logback使用




## SLF4J 原理：  建议打断点逐步执行
通过ClassLoader.getResources("org/slf4j/impl/StaticLoggerBinder.class")找到实现了SL4j接口的实现库，如logback和log4j的jar里面都有org.slf4j.impl.StaticLoggerBinder，如果类路径没有org.slf4j.impl.StaticLoggerBinder类，就会打印异常。

    Logger logger = LoggerFactory.getLogger(TestLogback.class);

调用栈：

      LoggerFactory.findPossibleStaticLoggerBinderPathSet() line: 307	
      LoggerFactory.bind() line: 146	
      LoggerFactory.performInitialization() line: 124	
      LoggerFactory.getILoggerFactory() line: 412	
      LoggerFactory.getLogger(String) line: 357	
      LoggerFactory.getLogger(Class<?>) line: 383	

LoggerFactory.findPossibleStaticLoggerBinderPathSet()的实现：

    private static String STATIC_LOGGER_BINDER_PATH ="org/slf4j/impl/StaticLoggerBinder.class";    
    static Set<URL> findPossibleStaticLoggerBinderPathSet() {
      // use Set instead of list in order to deal with bug #138
      // LinkedHashSet appropriate here because it preserves insertion order
      // during iteration
      Set<URL> staticLoggerBinderPathSet = new LinkedHashSet<URL>();
      try {
        ClassLoader loggerFactoryClassLoader = LoggerFactory.class.getClassLoader();
        Enumeration<URL> paths;
        if (loggerFactoryClassLoader == null) {
          paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
        } else {
          paths = loggerFactoryClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);
        }
        while (paths.hasMoreElements()) {
          URL path = paths.nextElement();
          staticLoggerBinderPathSet.add(path);
        }
      } catch (IOException ioe) {
        Util.report("Error getting resources from path", ioe);
      }
      return staticLoggerBinderPathSet;
    }

绑定的实现: StaticLoggerBinder.getSingleton() ##

    private final static void bind() {
          try {
              Set<URL> staticLoggerBinderPathSet = null;
              // skip check under android, see also
              // http://jira.qos.ch/browse/SLF4J-328
              if (!isAndroid()) {
                  staticLoggerBinderPathSet = findPossibleStaticLoggerBinderPathSet();
                  reportMultipleBindingAmbiguity(staticLoggerBinderPathSet);
              }
              // the next line does the binding
              StaticLoggerBinder.getSingleton();
StaticLoggerBinder.getSingleton();没有报错就是实现了绑定。 单例实现在logback或者log4j实现，有实现保证单例。




