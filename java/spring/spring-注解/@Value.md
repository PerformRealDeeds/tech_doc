## 数组

    @Value("${some.key:one,two,three}")
    private String[] stringArrayWithDefaults;
    
    @Value("${some.key:1,2,3}")
    private int[] intArrayWithDefaults;



## 参考
* https://www.baeldung.com/spring-value-defaults
* 