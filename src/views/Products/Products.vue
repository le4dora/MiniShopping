<template>
    <el-row class="tac" >
    <el-col :span="3" >
        <p style="text-align: center;font-weight: 700;">商品分类</p>
        <el-menu
        :default-active="activeIndex"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
            <el-menu-item index="1" style="text-align: center;">
                <i class="el-icon-shopping-bag-1"></i>
                <span slot="title">学习用品</span>
            </el-menu-item>
            <el-menu-item index="2" style="text-align: center;">
                <i class="el-icon-shopping-bag-1"></i>
                <span slot="title">电子产品</span>
            </el-menu-item>
            <el-menu-item index="3" style="text-align: center;">
                <i class="el-icon-shopping-bag-1"></i>
                <span slot="title">服装服饰</span>
            </el-menu-item>
            <el-menu-item index="4" style="text-align: center;">
                <i class="el-icon-shopping-bag-1"></i>
                <span slot="title">生活用品</span>
            </el-menu-item>
            <el-menu-item index="5" style="text-align: center;">
                <i class="el-icon-shopping-bag-1"></i>
                <span slot="title">体育器材</span>
            </el-menu-item>
            <el-menu-item index="6" style="text-align: center;">
                <i class="el-icon-shopping-bag-1"></i>
                <span slot="title">食品饮料</span>
            </el-menu-item>
            <el-menu-item index="7" style="text-align: center;">
                <i class="el-icon-shopping-bag-1"></i>
                <span slot="title">健康药品</span>
            </el-menu-item>
            <el-menu-item index="8" style="text-align: center;">
                <i class="el-icon-shopping-bag-1"></i>
                <span slot="title">艺术用品</span>
            </el-menu-item>
            <el-menu-item index="9" style="text-align: center;">
                <i class="el-icon-shopping-bag-1"></i>
                <span slot="title">书籍杂志</span>
            </el-menu-item>
            <el-menu-item index="10" style="text-align: center;">
                <i class="el-icon-shopping-bag-1"></i>
                <span slot="title">其他商品</span>
            </el-menu-item>
        </el-menu>
    </el-col>
    <el-col :span="21">
        <el-container>
            <el-header  style="height: 53px; background-color:white;">
                <el-input v-model="input" placeholder="请输入内容">
                </el-input><el-button type="primary" icon="el-icon-search" style="margin-left: 20px;">搜索</el-button>
            </el-header>
            <el-main style="border: 1px solid #ccc; border-radius: 10px;">
                <div class="productList">
                    <!-- 如果products数组为空，显示提示信息 -->
                    <div v-if="products.length === 0" class="noProducts">
                        <p class="nothing">很抱歉，暂时没有您想要的商品，请重新输入关键词!</p> 
                    </div>
                    <!-- 循环渲染商品列表 -->
                    <div class="product" v-for="(item, index) in products" :key="index" @click="pushShow()">
                        <img :src="item.image" alt="商品图片" class="productImage" />
                        <div class="productName">{{ item.name }}</div>
                        <div class="productPrice">{{ item.price }} 元</div>
                    </div>
                </div>
            </el-main>
        </el-container>
    </el-col>
    </el-row>
</template>

<script>
    import './Products.css'
    export default {
        data() {
            return {
                activeIndex: '',
                input: '',
                products: [
                    { name: '商品1', price: 100, image: 'https://via.placeholder.com/200' },
                    { name: '商品2', price: 200, image: 'https://via.placeholder.com/200' },
                    { name: '商品3', price: 300, image: 'https://via.placeholder.com/200' },
                    { name: '商品4', price: 150, image: 'https://via.placeholder.com/200' },
                    { name: '商品5', price: 250, image: 'https://via.placeholder.com/200' },
                ]
            };
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            pushShow(){
                this.$router.push({                //使用push路由跳转
                    path: '/Homepage/Productdetail/1',  // 使用完整的路径   
                    query:{
                        id:1,   //现在没有后台接口，先用1代替一下
                    }
                })
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>
