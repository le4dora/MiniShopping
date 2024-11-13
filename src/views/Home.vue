<template>
  <el-container class="home-container">
    <el-header>后台
      <el-button type="info" @click="Logout">退出</el-button>
    </el-header>

    <el-container>
      <el-aside width="200px">
        <el-menu
            background-color="#333744"
            text-color="#fff"
            active-text-color="#ffd04b">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>导航一</span>
            </template>

            <el-menu-item index="1-4-1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>导航一</span>
              </template>
            </el-menu-item>

          </el-submenu>

        </el-menu>

      </el-aside>
      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="id" label="id" width="100">
          </el-table-column>
          <el-table-column prop="account" label="账号" width="150">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="150">
          </el-table-column>
          <el-table-column prop="gender" label="性别" width="100">
          </el-table-column>
          <el-table-column prop="age" label="年龄" width="100">
          </el-table-column>
          <el-table-column prop="address" label="地址" width="250">
          </el-table-column>
          <el-table-column prop="phone" label="电话" width="250">
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>

  </el-container>

</template>

<script>
export default {
  name: 'Home',
  methods: {
    Logout(){
      console.log('logout');
    }
  }
}
</script>


<style>
.home-container{
  height: 100vh;
}

.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #ffffff;
  font-size: 20px;
  >div{
    display: flex;
    align-items: center;
  }
  span{
    margin-left: 15px;
  }

}
.el-main {
  background-color: #eaedf1;
}
.el-aside{
  background-color: #333744;
}

</style>



