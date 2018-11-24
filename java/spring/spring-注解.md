# 注解
## @Autowired 自动装配，将容器中的bean依赖注入到成员变量

	 @Autowired
	 public ApplicationContext ac; // 获取容器的引用
## @Value 将properties中的值注入到引用或者类型中
 
	@Value("${msg}")
	private String msg;

