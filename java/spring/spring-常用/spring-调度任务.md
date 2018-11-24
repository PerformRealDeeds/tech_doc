# spring 配置调度任务

## 方法
1. 在SpringBoot启动类上加上`@EnableScheduling`,会启动一个后台执行器。

	package hello;
	
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.scheduling.annotation.EnableScheduling;
	
	@SpringBootApplication
	@EnableScheduling
	public class Application {
	
	    public static void main(String[] args) {
	        SpringApplication.run(Application.class);
	    }
	}
2. 在 spring容器管理的bean的方法上加上`@Scheduled`注解，被注解的方法会按照配置周期执行
	package hello;
	
	import java.text.SimpleDateFormat;
	import java.util.Date;
	
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.scheduling.annotation.Scheduled;
	import org.springframework.stereotype.Component;
	
	@Component
	public class ScheduledTasks {
	
	    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	    @Scheduled(fixedRate = 5000)
	    public void reportCurrentTime() {
	        log.info("The time is now {}", dateFormat.format(new Date()));
	    }
	}

3. 参数解释：
* fixedDelay=5000 ： 上一次执行方法完成后，等待5000ms，执行下一周期的方法；  即本周期的方法会等待上一次周期的方法完成。
* fixedRate=5000  : 每5000ms执行一次标记的方法，周期间没有关系，本周期**不会等待**上一周期的方法执行完成。
* corn


Cron表达式是一个字符串，字符串以5或6个空格隔开，分为6或7个域，每一个域代表一个含义，Cron有如下两种语法格式： 

Seconds Minutes Hours DayofMonth Month DayofWeek Year或 
Seconds Minutes Hours DayofMonth Month DayofWeek

每一个域可出现的字符如下： 
Seconds:可出现", - * /"四个字符，有效范围为0-59的整数 
Minutes:可出现", - * /"四个字符，有效范围为0-59的整数 
Hours:可出现", - * /"四个字符，有效范围为0-23的整数 
DayofMonth:可出现", - * / ? L W C"八个字符，有效范围为0-31的整数 
Month:可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc 
DayofWeek:可出现", - * / ? L C #"四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推 
Year:可出现", - * /"四个字符，有效范围为1970-2099年

“？”字符仅被用于天（月）和天（星期）两个子表达式，表示不指定值 

* 0 0 10,14,16 * * ? 每天上午10点，下午2点，4点 
* 0 0/30 9-17 * * ? 朝九晚五工作时间内每半小时 
* 0 0 12 ? * WED 表示每个星期三中午12点 
* "0 0 12 * * ?" 每天中午12点触发 
* "0 15 10 ? * *" 每天上午10:15触发 
* "0 15 10 * * ?" 每天上午10:15触发 
* "0 15 10 * * ? *" 每天上午10:15触发 
* "0 15 10 * * ? 2005" 2005年的每天上午10:15触发 
* "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发 
* "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 
* "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 
* "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 
* "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 
* "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 
* "0 15 10 15 * ?" 每月15日上午10:15触发 
* "0 15 10 L * ?" 每月最后一日的上午10:15触发 
* "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 
* "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发 
* "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
## 参考
* https://spring.io/guides/gs/scheduling-tasks/
* [cron表达式详解](http://www.cnblogs.com/linjiqin/archive/2013/07/08/3178452.html)

