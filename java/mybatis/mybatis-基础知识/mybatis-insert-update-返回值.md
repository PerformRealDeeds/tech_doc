默认情况下，mybatis的update操作返回值是记录的 matched 的条数，并不是影响的记录条数。 
如果希望操作明确的返回受影响的记录条数，我们对我们的数据库连接配置稍做修改，添加 `useAffectedRows` 字段

    url:jdbc:mysql://localhost:3306/mindspan?useAffectedRows=true
## 参考
* https://www.cnblogs.com/jpfss/p/8918315.html
* https://blog.csdn.net/weixin_39703170/article/details/79829835