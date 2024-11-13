<template>
  <body id = "poster">
  <el-form class="login_container" label-position="left" label-width="0px">

    <h3 class="login_title">
      登录
      <el-button @click="toRegister()">立即注册</el-button>

    </h3>
    <el-form-item label="">
      <el-input type="text" v-model="loginForm.account" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>

    <el-form-item label="">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>


    <el-form-item style="width: 100%;">
      <el-button type="primary" @click="Login()" style="background-color: #2c3e50;border: none;width: 100%">登录</el-button>


    </el-form-item>
  </el-form>


  </body>
</template>

<script>


export default {
  name: 'Login',
  data() {
    return {
      loginForm: {
        account: '',
        password: '',
      }
    }
  },
  methods: {
    Login() {
      this.axios.post('http://localhost:8081/user/login', this.loginForm).then(response => {
        let data = response.data;
        // console.log(response)
        // console.log(data)
        if (data.data === "Login Success") {
          this.$message({
            message: '登录成功',
            type: 'success'
          });
          this.$router.push('/Home');
        }

      })

    },
    toRegister() {
      this.$router.push('/Register');
    }
  }
}
</script>


<style>

#poster{
  background-position: center;
  width: 100%;
  height: 100%;
  background-size: cover;
  position: fixed;
}
body{
  margin: 0;
  padding: 0;
}
.login_container{
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  padding: 35px 35px 15px 35px;
  width: 350px;
  background: #fff;
  border:1px solid #eaeaee;
  box-shadow:0 0 25px #cac6c6;

}

</style>
