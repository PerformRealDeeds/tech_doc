## Unable to find a @SpringBootConfiguration, you need to use @ContextConfigura
    java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
        at org.springframework.util.Assert.state(Assert.java:73)
        at org.springframework.boot.test.context.SpringBootTestContextBootstrapper.getOrFindConfigurationClasses(SpringBootTestContextBootstrapper.java:240)
        at org.springframework.boot.test.context.SpringBootTestContextBootstrapper.processMergedContextConfiguration(SpringBootTestContextBootstrapper.java:153)
        at org.springframework.test.context.support.AbstractTestContextBootstrapper.buildMergedContextConfiguration(AbstractTestContextBootstrapper.java:395)
        at org.springframework.test.context.support.AbstractTestContextBootstrapper.buildDefaultMergedContextConfiguration(AbstractTestContextBootstrapper.java:312)
        at org.springframework.test.context.support.AbstractTestContextBootstrapper.buildMergedContextConfiguration(AbstractTestContextBootstrapper.java:265)
        at org.springframework.test.context.support.AbstractTestContextBootstrapper.buildTestContext(AbstractTestContextBootstrapper.java:108)
        at org.springframework.boot.test.context.SpringBootTestContextBootstrapper.buildTestContext(SpringBootTestContextBootstrapper.java:97)

[参考](https://stackoverflow.com/questions/39084491/unable-to-find-a-springbootconfiguration-when-doing-a-jpatest)

    Indeed, Spring Boot does set itself up for the most part. You can probably already get rid of a lot of the code you posted, especially in Application.

    I wish you had included the package names of all your classes, or at least the ones for Application and JpaTest. The thing about @DataJpaTest and a few other annotations is that they look for a @SpringBootConfiguration annotation in the current package, and if they cannot find it there, they traverse the package hierarchy until they find it.

    For example, if the fully qualified name for your test class was com.example.test.JpaTest and the one for your application was com.example.Application, then your test class would be able to find the @SpringBootApplication (and therein, the @SpringBootConfiguration).

    If the application resided in a different branch of the package hierarchy, however, like com.example.application.Application, it would not find it.
    
   
  
 
## - Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.thymeleaf.exceptions.TemplateInputException: Error resolving template "module/student/upload", template might not exist or might not be accessible by any of the configured Template Resolvers] with root causeorg.thymeleaf.exceptions.TemplateInputException: Error resolving template "module/student/upload", template might not exist or might not be accessible by any of the configured Template Resolvers
    @PostMapping("/upload")
    @ResponseBody
原因：漏掉了@ResponseBody，然后就认为返回房爷， @PostMapping要和@ResponseBody一起用。
