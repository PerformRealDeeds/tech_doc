## 为什么要进行对象的拷贝？
不想更新以前的对象A，想传递一个值相等的另外的一个对象B给外面，B更新后不影响A.

## js 深拷贝
1.  JSON.parse(JSON.stringify(data)) 