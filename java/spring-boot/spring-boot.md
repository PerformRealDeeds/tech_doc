## 自动重启:
1.在pom中加入spring-boot-devtools的依赖

    <dependencies>
     <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <optional>true</optional>
     </dependency>
    </dependencies>
2.注意一些资源的修改比如静态assets，视图模板不需要重启应用