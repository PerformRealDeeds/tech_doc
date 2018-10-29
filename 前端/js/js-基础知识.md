## js 真假值
在JavaScript中，Truthy(真值)指的是在Boolean上下文中转换后的值为真的值。*所有值都是真值*，除非它们被定义为 falsy (即， 除了false，0，“”，null，undefined和NaN 外)。
JavaScript 在Boolean上下文中使用强制类型转换（coercion）。
JavaScript中的真值示例如下（将被转换为true类型，if 后的代码段将被执行）：
```
if (true)
if ({})
if ([])
if (42)
if ("foo")
if (new Date())
if (-42)
if (3.14)
if (-3.14)
if (Infinity)
if (-Infinity)
```


