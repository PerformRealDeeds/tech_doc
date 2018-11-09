## 在spring中使用定时器
1. To enable the support for scheduling tasks and the @Scheduled annotation in Spring – we can use the Java enable-style annotation:
```
@Configuration
@EnableScheduling
public class SpringConfig {
    ...
}
```
Or we can do the same in XML:
```
<task:annotation-driven>
```
2. Schedule a Task at Fixed Delay 会等待上一个周期的任务完成才会才是下一个周期。

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        System.out.println(
        "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

3. Schedule a Task at a Fixed Rate 不会等待上一个任务完成
Let’s not execute a task at a fixed interval of time:

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        System.out.println(
        "Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

4.Schedule a Task with Initial Delay

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {
    
        long now = System.currentTimeMillis() / 1000;
        System.out.println(
        "Fixed rate task with one second initial delay - " + now);
    }

5. Schedule a Task using Cron Expressions
    
    @Scheduled(cron = "0 15 10 15 * ?")
    public void scheduleTaskUsingCronExpression() {
    
        long now = System.currentTimeMillis() / 1000;
        System.out.println(
        "schedule tasks using cron jobs - " + now);
    }

6. 使用spring参数，在properties文件中配置
     @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")

[The @Scheduled Annotation in Spring](https://www.baeldung.com/spring-scheduled-tasks)



