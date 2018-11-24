	
## [读取自定义配置文件](https://blog.csdn.net/qq_23167527/article/details/77977326 )
	import org.springframework.boot.context.properties.ConfigurationProperties;  
	import org.springframework.context.annotation.Configuration;  
	import org.springframework.stereotype.Component;  
	
	@Component  
	@ConfigurationProperties(prefix ="auth") 
	@PropertySource("classpath:/author/authorA.properties")//@PropertySource来指定自定义的资源目录
	//在启动类上取消掉@EnableConfigurationProperties注解，springboot1.5版本之后不需要@EnableConfigurationProperties注解了。
	public class MyWebConfig{  
	    private String name;  
	    private int age;  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public int getAge() {  
	        return age;  
	    }  
	    public void setAge(int age) {  
	        this.age = age;  
	    }  
	}  
	·
