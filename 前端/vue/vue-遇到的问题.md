## vscode无法格式化vue文件
[参考](https://blog.csdn.net/u014054437/article/details/79980691)
文件->首选项->设置，搜索 vetur.format.defaultFormatter.html，将none修改为“js-beautify-html”

## vue 表单 重置表单值 
[文档](http://element-cn.eleme.io/#/zh-CN/component/form)
<el-form-item>的prop属性，在使用 `validate、resetFields` 方法的情况下，该属性是必填的	


    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="活动名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
    </el-form>

    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                alert('submit!');
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
        }