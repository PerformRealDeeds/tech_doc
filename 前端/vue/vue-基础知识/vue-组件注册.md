
## [vue组件嵌套组件](https://blog.csdn.net/lidysun/article/details/80609682)
```
<template>
  <div>
      <p class="p1">Hello ,This is {{page_name}} now</p>
      <test/>
      <!-- 3.使用 -->
      <hello></hello>
  </div>
</template>
<script>
// 1.引入
import HelloWorld from '@/components/HelloWorld'

export default {
  name: 'Googs',
  data () {
    return {
        page_name: 'Googs Page address'
    }
  },
  // 2.注册
  components:{
    'test':{
        template:'<p>test</p>'
    },
    'Hello':HelloWorld
  }
}
</script>
<style scoped>
.p1{color:red}
</style>
```
[参考博客](https://blog.csdn.net/gao531162436/article/details/78732114)