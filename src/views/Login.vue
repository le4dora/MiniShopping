<!--<template>-->
<!--  <body id = "poster">-->
<!--  <el-form class="login_container" label-position="left" label-width="0px">-->

<!--    <h3 class="login_title">-->
<!--      登录-->
<!--    </h3>-->
<!--    <el-form-item label="">-->
<!--      <el-input type="text" v-model="loginForm.phone" auto-complete="off" placeholder="手机"></el-input>-->
<!--    </el-form-item>-->

<!--    <el-form-item label="">-->
<!--      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>-->
<!--    </el-form-item>-->


<!--    <el-form-item style="width: 100%;">-->
<!--      <el-button type="primary" @click="Login()" style="background-color: #2c3e50;border: none;">登录</el-button>-->
<!--      <el-button @click="toRegister()" style="background-color: #f6efef;border: none;">立即注册</el-button>-->

<!--    </el-form-item>-->

<!--  </el-form>-->


<!--  </body>-->
<!--</template>-->

<!--<script>-->


<!--import instance from "@/request";-->

<!--export default {-->
<!--  name: 'Login',-->
<!--  data() {-->
<!--    return {-->
<!--      loginForm: {-->
<!--        phone: '',-->
<!--        password: '',-->
<!--      }-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    Login() {-->
<!--      instance.post('/user/login', this.loginForm).then(response => {-->
<!--        let data = response.data;-->
<!--        console.log(data)-->
<!--        if (data.data != null) {-->
<!--          console.log("成功")-->
<!--          console.log(data.data)-->
<!--          localStorage.setItem("token", data.data);-->
<!--          this.$router.push('/Homepage')-->
<!--        }-->
<!--      })-->

<!--    },-->
<!--    toRegister() {-->
<!--      this.$router.push('/Register');-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->


<!--<style>-->

<!--#poster {-->
<!--  background-position: center;-->
<!--  width: 100%;-->
<!--  height: 100%;-->
<!--  background-size: cover;-->
<!--  position: fixed;-->
<!--}-->

<!--body {-->
<!--  margin: 0;-->
<!--  padding: 0;-->
<!--}-->

<!--.login_container {-->
<!--  border-radius: 15px;-->
<!--  background-clip: padding-box;-->
<!--  margin: 90px auto;-->
<!--  padding: 35px 35px 15px 35px;-->
<!--  width: 350px;-->
<!--  background: #fff;-->
<!--  border: 1px solid #eaeaee;-->
<!--  box-shadow: 0 0 25px #cac6c6;-->

<!--}-->

<!--</style>-->


<template>
  <body id="poster">
  <el-form class="login_container" label-position="left" label-width="0px">
    <h3 class="login_title">登录</h3>

    <el-form-item label="">
      <el-input type="text" v-model="loginForm.phone" auto-complete="off" placeholder="手机" class="input-field"></el-input>
    </el-form-item>

    <el-form-item label="">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码" class="input-field"></el-input>
    </el-form-item>

    <el-form-item style="width: 100%;">
      <el-button type="primary" @click="Login()" class="btn-login">登录</el-button>
      <el-button @click="toRegister()" class="btn-register">立即注册</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
import instance from "@/request";

export default {
  name: 'Login',
  data() {
    return {
      loginForm: {
        phone: '',
        password: '',
      }
    }
  },
  methods: {
    Login() {
      instance.post('/user/login', this.loginForm).then(response => {
        let data = response.data;
        console.log(data)
        if (data.data != null) {
          console.log("成功")
          console.log(data.data)
          localStorage.setItem("token", data.data);
          this.$router.push('/Homepage')
        }
      })
    },
    toRegister() {
      this.$router.push('/Register');
    }
  }
}
</script>

<style scoped>
/* 背景设置 */
#poster {
  background: linear-gradient(to bottom, rgba(44, 62, 80, 0.8), rgba(44, 62, 80, 0.6)), url('../../public/e818e371445cf160debb0f0f149803f7.jpeg');
  background-position: center;
  background-size: cover;
  height: 100%;
  width: 100%;
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* 去除body的默认边距 */
body {
  margin: 0;
  padding: 0;
}

/* 登录容器样式 */
.login_container {
  border-radius: 15px;
  padding: 35px 35px 15px 35px;
  width: 350px;
  background: #fff;
  border: 1px solid #eaeaee;
  box-shadow: 0 0 25px rgba(0, 0, 0, 0.1);
  animation: fadeIn 0.8s ease-out;
}

/* 标题样式 */
.login_title {
  font-size: 30px;
  font-weight: 600;
  color: #2c3e50;
  text-align: center;
  margin-bottom: 30px;
  font-family: 'Arial', sans-serif;
}

/* 输入框样式 */
.input-field {
  border-radius: 10px;
  background-color: #f5f5f5;
  border: 1px solid #e0e0e0;
  transition: border-color 0.3s ease;
  padding: 10px;
  font-size: 16px;
  color: #333;
}

.input-field:focus {
  border-color: #3498db;
}

/* 按钮样式 */
.btn-login {
  width: 100%;
  height: 45px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 10px;
  font-size: 16px;
  transition: all 0.3s ease;
  margin-bottom: 10px;
}

.btn-login:hover {
  background-color: #2980b9;
}

.btn-register {
  width: 100%;
  height: 45px;
  background-color: #f6efef;
  color: #2c3e50;
  border: none;
  border-radius: 10px;
  font-size: 16px;
  transition: all 0.3s ease;
}

.btn-register:hover {
  background-color: #ecf0f1;
  color: #3498db;
}

/* 动画效果 */
@keyframes fadeIn {
  0% {
    opacity: 0;
    transform: translateY(-20px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
