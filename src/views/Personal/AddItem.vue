<template>
  <el-form :model="form" ref="form" label-width="120px" class="demo-ruleForm">
    <el-form-item label="商品名称" prop="name" :rules="[{ required: true, message: '请输入商品名称', trigger: 'blur' }]">
      <el-input v-model="form.name" placeholder="请输入商品名称"></el-input>
    </el-form-item>

    <el-form-item label="商品分类" prop="category" :rules="[{ required: true, message: '请选择商品分类', trigger: 'change' }]">
      <el-select v-model="form.category" placeholder="请选择商品分类">
        <el-option label="学习用品" value="electronics"></el-option>
        <el-option label="电子产品" value="clothing"></el-option>
        <el-option label="服装服饰" value="food"></el-option>
        <el-option label="生活用品" value="home"></el-option>
        <el-option label="体育器材" value="home"></el-option>
        <el-option label="食品饮料" value="home"></el-option>
        <el-option label="健康药品" value="home"></el-option>
        <el-option label="艺术用品" value="home"></el-option>
        <el-option label="书籍杂志" value="home"></el-option>
        <el-option label="其他用品" value="home"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="商品价格" prop="price" :rules="[{ required: true, message: '请输入商品价格', trigger: 'blur' }]">
      <el-input v-model="form.price" placeholder="请输入商品价格" type="number"></el-input>
    </el-form-item>

    <el-form-item label="库存数量" prop="stock" :rules="[{ required: true, message: '请输入商品库存数量', trigger: 'blur' }]">
      <el-input v-model="form.stock" placeholder="请输入商品库存数量" type="number"></el-input>
    </el-form-item>

    <el-form-item label="商品描述" prop="description" :rules="[{ required: true, message: '请输入商品描述', trigger: 'blur' }]">
      <el-input type="textarea" v-model="form.description" placeholder="请输入商品描述"></el-input>
    </el-form-item>

    <el-form-item label="商品图片" prop="image" :rules="[{ required: true, message: '请上传商品图片', trigger: 'change' }]">
      <el-upload
          class="upload-demo"
          drag
          action="/upload"
          :on-success="handleImageSuccess"
          :before-upload="beforeImageUpload"
          :file-list="fileList"
      >
        <el-button slot="trigger" size="small" type="primary">点击上传</el-button>
        <el-button slot="tip" size="small" type="text" >只能上传图片</el-button>
      </el-upload>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm">发布商品</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import instance from "@/request";

export default {
  data() {
    return {
      form: {
        name: '',
        category: '',
        price: '',
        stock: '',
        description: '',
        image: '',
      },
      fileList: [],
    };
  },
  methods: {
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          // 表单数据通过验证后，执行提交操作
          console.log('提交的数据:', this.form);
          this.postProductData();
        } else {
          console.log('表单验证失败');
          return false;
        }
      });
    },
    // 上传图片成功的回调
    handleImageSuccess(response, file, fileList) {
      // 假设返回的 response 中有图片 URL
      this.form.image = response.imageUrl;
    },
    // 上传图片之前的钩子，做一些自定义的校验
    beforeImageUpload(file) {
      const isImage = file.type.startsWith('image/');
      if (!isImage) {
        this.$message.error('只能上传图片文件');
      }
      const isLt2M = file.size / 1024 / 1024 < 2; // 限制图片大小小于2MB
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB');
      }
      return isImage && isLt2M;
    },
    // 发布商品的 API 调用
    postProductData() {
      // 模拟发送请求，这里你需要根据实际情况修改 API 路径
      instance.post('/products', this.form)
          .then(response => {
            if (response.data.success) {
              this.$message.success('商品发布成功');
            } else {
              this.$message.error('商品发布失败');
            }
          })
          .catch(error => {
            this.$message.error('网络错误，请稍后再试');
            console.error(error);
          });
    },
  },
};
</script>

<style scoped>
.upload-demo i {
  font-size: 28px;
  color: #409EFF;
}
</style>
