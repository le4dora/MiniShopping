<!--<template>-->
<!--  <el-container style="height: 100vh">-->



<!--    <el-aside width="200px" style="background-color: #545c64">-->

<!--      <el-menu-->
<!--          background-color="#545c64"-->
<!--          text-color="#fff"-->
<!--          active-text-color="#ffd04b"-->
<!--          router-->
<!--      >-->

<!--        <el-menu-item index="/Homepage/Personal/Message" >-->

<!--          <template slot="title">-->
<!--            <i class="el-icon-user"></i>-->
<!--            <span>个人资料</span>-->
<!--          </template>-->

<!--        </el-menu-item>-->

<!--        <el-menu-item index="/HomePage/Personal/MyOrder" >-->
<!--          <template slot="title">-->
<!--            <i class="el-icon-s-order"></i>-->
<!--            <span>我的订单</span>-->
<!--          </template>-->
<!--        </el-menu-item>-->

<!--        <el-menu-item index="/HomePage/Personal/AddItem">-->
<!--          <template slot="title">-->
<!--            <i class="el-icon-sell"></i>-->
<!--            <span>发布商品</span>-->
<!--          </template>-->
<!--        </el-menu-item>-->

<!--      </el-menu>-->

<!--    </el-aside>-->



<!--    <el-main><router-view></router-view></el-main>-->
<!--  </el-container>-->





<!--</template>-->



<template>
  <el-container style="height: 100vh; background-color: #f5f5f5;">
    <el-aside width="220px" style="background-color: #333; color: #fff; padding: 20px 0; box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);">
      <el-menu
          background-color="#333"
          text-color="#fff"
          active-text-color="#ffd04b"
          router
          :collapse="false"
          style="border: none; padding-top: 20px;"
      >
        <el-menu-item index="/Homepage/Personal/Message">
          <template slot="title">
            <i class="el-icon-user" style="font-size: 18px; margin-right: 10px;"></i>
            <span>个人资料</span>
          </template>
        </el-menu-item>

        <el-menu-item index="/Homepage/Personal/MyOrder">
          <template slot="title">
            <i class="el-icon-s-order" style="font-size: 18px; margin-right: 10px;"></i>
            <span>我的订单</span>
          </template>
        </el-menu-item>

        <el-menu-item index="/Homepage/Personal/AddItem">
          <template slot="title">
            <i class="el-icon-sell" style="font-size: 18px; margin-right: 10px;"></i>
            <span>发布商品</span>
          </template>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-main style="background-color: #fff; padding: 20px; overflow-y: auto;">
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'AppLayout',
};
</script>

<style scoped>
.el-menu-item:hover {
  background-color: #444;
  transition: background-color 0.3s ease;
}

.el-menu-item.is-active {
  background-color: #ffd04b !important;
  color: #333;
  font-weight: bold;
}

.el-menu-item.is-active span {
  color: #333 !important;
}


.el-menu-item .el-icon {
  font-size: 20px;
  margin-right: 10px;
}


.el-main {
  padding: 20px;
  background-color: #fafafa;
  border-radius: 8px;
}


.el-menu-item {
  transition: background-color 0.3s ease;
}
</style>

