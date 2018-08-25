## 1. 安装nodejs
  设置淘宝镜像`npm config set registry https://registry.npm.taobao.org`
## 2. [clone elementUI的启动项目](https://github.com/ElementUI/element-starter)
## 3. 进入项目根目录
    npm run dev
    npm run build


----

## elementUI使用项目
* https://github.com/PanJiaChen/vue-element-admin
* https://github.com/yinjihuan/vue-elementui

## [内网使用elementUI](https://blog.csdn.net/m0_37893932/article/details/79460652)
参考[官网helloword](http://element-cn.eleme.io/#/zh-CN/component/installation),下载需要的css,js, 打开F12,单击按钮时会下载woff文件,把所有要下载的文件拷入内网中。

    <!DOCTYPE html>
    <html>
    <head>
      <meta charset="UTF-8">
      <!-- import CSS -->
      <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    </head>
    <body>
      <div id="app">
        <el-button @click="visible = true">Button</el-button>
        <el-dialog :visible.sync="visible" title="Hello world">
          <p>Try Element</p>
        </el-dialog>
      </div>
    </body>
      <!-- import Vue before Element -->
      <script src="https://unpkg.com/vue/dist/vue.js"></script>
      <!-- import JavaScript -->
      <script src="https://unpkg.com/element-ui/lib/index.js"></script>
      <script>
        new Vue({
          el: '#app',
          data: function() {
            return { visible: false }
          }
        })
      </script>
    </html>