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
        active-text-color="#ffd04b"
        @select="handleSelect">
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
            <el-header style="height: 53px; background-color:white;">
            <div style="display: flex; align-items: center; justify-content: flex-start; width: 100%;">
                <el-input
                v-model="input"
                placeholder="请输入内容"
                style="flex: 1; margin-right: 10px;"  
                ></el-input>
                <el-button
                type="primary"
                icon="el-icon-search"
                style="white-space: nowrap;"
                @click="handleSearch"
                >
                搜索
                </el-button>
            </div>
            </el-header>
            <el-main style="border: 1px solid #ccc; border-radius: 10px;">
                <div class="productList">
                    <!-- 如果products数组为空，显示提示信息 -->
                    <div v-if="filteredProducts.length === 0" class="noProducts">
                        <p class="nothing">很抱歉，暂时没有您想要的商品!</p> 
                    </div>
                    <!-- 循环渲染商品列表 -->
                    <div class="product" v-for="(item, index) in filteredProducts" :key="index" @click="pushShow(item.id)">
                        <img :src="item.product_pic" alt="商品图片" class="productImage" />
                        <div class="productName">{{ item.product_name }}</div>
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
    // 引入配置好的 axios 实例
    import axiosRequest from '../axiosRequset';
    export default {
        data() {
            return {
                activeIndex: '',
                input: '',
                products: [],
                filteredProducts: []  // 用来保存过滤后的商品
            };
        },
        created() {
            // 页面加载时获取商品信息
            this.fetchProducts();
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            pushShow(id){
                this.$router.push({                //使用push路由跳转
                    path: `/Homepage/Productdetail/${id}`,  // 使用完整的路径   
                    query:{
                        id:id,   //现在没有后台接口，先用1代替一下
                    }
                })
            },
            handleSearch() {
                if (!this.input.trim()) {
                    this.$message.error('请输入搜索内容');  // 如果搜索框为空，弹出提示
                    return;
                }

                // 如果输入框不为空，发起 axios 请求
                axiosRequest.get(`/product/search?name=${this.input}`)
                    .then(response => {
                        console.log(response);
                        
                    // this.products = response.data.data;  // 更新商品列表
                    })
                    .catch(error => {
                    console.error(error);
                    this.$message.error('获取商品数据失败');  // 请求失败时弹出错误提示
                });
            },
            fetchProducts() {
                axiosRequest.get('/product')  // 发送 GET 请求
                    .then((response) => {
                    // 返回的数据是一个商品数组
                        this.products = response.data.data;
                        this.filteredProducts = response.data.data;
                    console.log(this.products);
                    })
                    .catch((error) => {
                    console.error('获取商品数据失败:', error);
                    });
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
        // 处理分类选择
            handleSelect(index) {
                let category = '';
                switch(index) {
                    case '1':
                    category = '学习用品';
                    break;
                    case '2':
                    category = '电子产品';
                    break;
                    case '3':
                    category = '服装服饰';
                    break;
                    case '4':
                    category = '生活用品';
                    break;
                    case '5':
                    category = '体育器材';
                    break;
                    case '6':
                    category = '食品饮料';
                    break;
                    case '7':
                    category = '健康药品';
                    break;
                    case '8':
                    category = '艺术用品';
                    break;
                    case '9':
                    category = '书籍杂志';
                    break;
                    case '10':
                    category = '其他商品';
                    break;
                    default:
                    category = '';
                    break;
                }
                // 过滤商品
                this.filteredProducts = this.products.filter(product => product.category === category);
            }
        }
    }
</script>
