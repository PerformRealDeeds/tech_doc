[stackoverflow参考](https://stackoverflow.com/questions/359494/which-equals-operator-vs-should-be-used-in-javascript-comparisons)
## 相同
都会比较类型。
## 区别
比较引用或者值的类型不同时，`===`和`!==`不会转换类型直接返回false，`==`和`!=`转换类型，值相同时返回true，值不同时返回false
## 结论
通常使用`===`和`!==`，不要使用`==`和`!=`

## 一些有趣的比较
```
'' == '0'           // false
0 == ''             // true
0 == '0'            // true

false == 'false'    // false
false == '0'        // true

false == undefined  // false
false == null       // false
null == undefined   // true

' \t\r\n ' == 0     // true
```


```
var a = [1,2,3];
var b = [1,2,3];

var c = { x: 1, y: 2 };
var d = { x: 1, y: 2 };

var e = "text";
var f = "te" + "xt";

a == b            // false
a === b           // false

c == d            // false
c === d           // false

e == f            // true
e === f           // true
```
Here the == operator is checking the values of the two objects and returning true, but the === is seeing that they're not the same type and returning false. Which one is correct? That really depends on what you're trying to compare. My advice is to bypass the question entirely and just **don't use the String constructor** to create string objects.




## 和java中的==、equals()的区别
java中的==比较的是两个基本数据类型值是否相等，或者两个引用是否是一个对象。
Object的默认equals方法是比较两个引用是否指向同一个内存地址。String的重写了Object方法，变为比较字符串值了。
java的==更像js中的===