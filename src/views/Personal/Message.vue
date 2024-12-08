<template>
  <div> <!-- 通过一个 div 包裹所有内容 -->
    <el-descriptions class="margin-top" title="个人信息" :column="2" border style="background-color: white">
      <template slot="extra">
        <el-row class="demo-avatar demo-basic">
          <el-col :span="12">
            <!-- 头像下拉菜单 -->
            <el-dropdown @command="handleCommand">
              <el-button type="text" style="padding: 0;">
                <el-avatar :size="35" :src="circleUrl" style="margin-top: 30px"></el-avatar>
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="edit">修改信息</el-dropdown-item>
                <el-dropdown-item command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </el-row>
      </template>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          昵称
        </template>
        {{ user.nickname }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone"></i>
          手机号
        </template>
        {{ user.phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-male"></i>
          性别
        </template>
        {{ user.gender }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-location-outline"></i>
          收货地址
        </template>
        {{ user.address }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-message"></i>
          邮箱
        </template>
        {{ user.email }}
      </el-descriptions-item>
    </el-descriptions>

    <!-- 修改信息的对话框 -->
    <el-dialog title="个人资料" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="昵称" :label-width="formLabelWidth">
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-select v-model="form.gender" placeholder="请选择性别">
            <el-option label="男" value="male"></el-option>
            <el-option label="女" value="female"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="收货地址" :label-width="formLabelWidth">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleSave">确 定</el-button>
      </div>
    </el-dialog>
  </div> <!-- 结束 div -->
</template>

<script>
import instance from "@/request";

export default {
  name: "Personal",
  data() {
    return {
      circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      user: [],
      dialogFormVisible: false,
      form: {
        nickname: '',
        gender: '',
        phone: '',
        address: '',
        password: '',
        email: ''
      },
      formLabelWidth: '120px'
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    // 获取用户数据
    fetchData() {
      instance.get('/user/userInfo').then(response => {
        this.user = response.data.data;
        // 初始化表单数据
        this.form.nickname = this.user.nickname;
        this.form.gender = this.user.gender;
        this.form.phone = this.user.phone;
        this.form.address = this.user.address;
        this.form.email = this.user.email;
      });
    },
    // 下拉菜单点击事件
    handleCommand(command) {
      if (command === 'edit') {
        this.dialogFormVisible = true;
      }
      if (command === 'logout'){
        localStorage.removeItem('token');
        this.$message.success('退出成功')
        this.$router.push('/Login');
      }
    },
    // 保存修改信息
    handleSave() {
      // 保存表单数据，可以调用 API 进行提交
      instance.put('/user/update', this.form).then(response => {

      })
      this.dialogFormVisible = false;
      console.log('保存的表单数据:', this.form);
    }
  }
};
</script>

<style scoped>
</style>
