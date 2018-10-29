## js遍历数组
### 方法1
    for (var index = 0; index < myArray.length; index++) {
    console.log(myArray[index]);
    }
### 方法2
    myArray.forEach(function (value,index) {
    console.log(index+":"+value);
    });

缺点：不能使用break语句中断循环，也不能使用return语句返回到外层函数。另外，forEach方法是Array对象的方法，所以对于DOM的类数组，如NodeList对象，则不能遍历。

## 参考
[ES6-遍历数组](https://blog.csdn.net/qq_37860930/article/details/78529260)
