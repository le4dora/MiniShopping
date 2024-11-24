<template>
  <el-container style="height: 100vh">



    <el-aside width="200px" style="background-color: #545c64">

      <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          router
      >

        <el-menu-item index="/Homepage/Personal/Message" >

          <template slot="title">
            <i class="el-icon-user"></i>
            <span>个人资料</span>
          </template>

        </el-menu-item>

        <el-menu-item index="/HomePage/Personal/MyOrder" >
          <template slot="title">
            <i class="el-icon-s-order"></i>
            <span>我的订单</span>
          </template>
        </el-menu-item>

        <el-menu-item index="/HomePage/Personal/AddItem">
          <template slot="title">
            <i class="el-icon-sell"></i>
            <span>发布商品</span>
          </template>
        </el-menu-item>

      </el-menu>

    </el-aside>



    <el-main><router-view></router-view></el-main>
  </el-container>





</template>



<script>

</script>

<style>

</style>