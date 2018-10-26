## js设置默认参数
### 方法1
    function example(name,age){
    name=name||'貂蝉';
    age=age||21;
    alert('你好！我是'+name+'，今年'+age+'岁。');
    }
    
    // js中false的判断：false,0,'',undefined,null,NaN
    function example(name,age){
    if(!name){name='貂蝉';} 
    if(!age){age=21;}
    alert('你好！我是'+name+'，今年'+age+'岁。');
    }

### 方法2

    function example(){
    vara = arguments[0] ? arguments[0] : 1;//设置第一个参数的默认值为1
    varb = arguments[1] ? arguments[1] : 2;//设置第二个参数的默认值为2
    returna+b;
    }

### 方法3
    function example(setting){
    vardefaultSetting={
        name:'小红',
        age:'30',
        sex:'女',
        phone:'100866',
        QQ:'100866',
        birthday:'1949.10.01'
    };
    $.extend(defaultSetting,settings);
    varmessage='姓名：'+defaultSetting.name
    +'，性别：'+defaultSetting.sex
    +'，年龄：'+defaultSetting.age
    +'，电话：'+defaultSetting.phone
    +'，QQ：'+defaultSetting.QQ
    +'，生日：'+defaultSetting.birthday
    +'。';
    alert(message);

## 参考
[js中对函数设置默认参数值的3种方法](https://blog.csdn.net/judyge/article/details/51882644)


