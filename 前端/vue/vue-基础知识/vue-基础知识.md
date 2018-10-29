## 表单
###  .trim
如果要自动过滤用户输入的首尾空白字符，可以给 v-model 添加 trim 修饰符：

    <input v-model.trim="msg">
### 回车事件
如果是原生的input，使用 @keyup.enter就可以，若是使用了element-ui，则要加上native限制符，因为element-ui把input进行了封装，原事件就不起作用了，代码如下：
<input v-model="form.name" placeholder="昵称" @keyup.enter="submit">
<el-input v-model="form.name" placeholder="昵称" @keyup.enter.native="submit"></el-input>
[参考](https://blog.csdn.net/lx_1024/article/details/79086979 )




