1. ERROR 3814: Join inner did not fit int memory\n (3814)
   处理:内存资源不足,重跑
   预防:并行sql数减少; 会话连接的内存设置数减少

## vertica 不能发现 veritca或者mysql的驱动包
将vertica和mysql的驱动包放入data-integration/lib下



