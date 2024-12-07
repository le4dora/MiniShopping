<template>
    <el-container>
        <el-header  style="height: 70px;">
            <el-row style="width: 100%;" >
                <!-- 左侧Logo部分 -->
                <el-col :span="2" style="display: flex; align-items: center; justify-content: center;">
                    <img src="./static/img/广工logo.png" alt="Logo" style="height: 60px;  "/>
                </el-col>
                <!-- 菜单部分 -->
                <el-col :span="17" >
                    <el-menu
                        :default-active="activeIndex"
                        class="el-menu-demo"
                        mode="horizontal"
                        @select="handleSelect"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b"
                        router
                        style="display: flex; justify-content: space-between; margin-left: 40px;"
                        >
                        <el-menu-item index="/Homepage/Products" style="flex: 1; text-align: center;">商品首页</el-menu-item>
                        <el-menu-item index="/Homepage/ShopCar"   style="flex: 1; text-align: center;">购物车</el-menu-item>
                        <el-menu-item index="/Homepage/about"  style="flex: 1; text-align: center;">个人中心</el-menu-item>
                    </el-menu>
                </el-col>
                <!-- 右侧应用名称部分 -->
                <el-col :span="5" style="text-align: right;">
                    <span style="color: burlywood; font-size: 24px;padding-right: 40px; font-weight: 700;">工大二手交易平台</span>
                </el-col>
            </el-row>
        </el-header>
        <el-main style="padding: 0; height: 100%; display: flex;">
            <router-view style="flex: 1;"></router-view>
        </el-main>
    </el-container>
</template>

<script>
export default {
    data() {
        return {
            activeIndex: '',
        };
    },
    mounted() {
        this.setActiveIndexFromUrl();  // 初始设置
    },
    watch: {
        // 监听路由变化，回退时触发
        $route(to, from) {
            this.setActiveIndexFromUrl();
        }
    },
    methods: {
         // 从 URL 获取路径并更新 activeIndex
        setActiveIndexFromUrl() {
            const path = this.$route.path;  // 获取当前路由的路径
            if (path.includes('Products')) {
                this.activeIndex = '/Homepage/Products';
            } else if (path.includes('ShopCar')) {
                this.activeIndex = '/Homepage/ShopCar';
            } else if (path.includes('about')) {
                this.activeIndex = '/Homepage/about';
            } else {
                this.activeIndex = '/Homepage/Products';
            }
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        }
    }
}
</script>

<style>

</style>


