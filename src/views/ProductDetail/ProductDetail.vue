<template>
    <el-container style="min-width: 600px; margin: 20px auto;">
    <!-- 左侧：商品图片 -->
        <el-aside width="20%" 
        style="padding: 10px 20px; border-right: 1px solid #ddd;background-color: white;border-radius: 8px;
        display: flex; justify-content: center; align-items: center;">
            <img 
                :src="productDetails.product_pic"
                alt="商品图片" 
                style="width: 100%; height: auto; object-fit: contain; border-radius: 8px;"
            />
        </el-aside>

    <!-- 右侧：商品详情信息 -->
        <el-main style="padding: 20px 40px;">
            <el-row>
                <el-col :span="24">
                    <h2 style="font-size: 24px; font-weight: bold; color: #333; display: flex; align-items: center;">
                        {{ productDetails.product_name }}
                    </h2>
                    <!-- <img 
                        src="https://via.placeholder.com/40" 
                        alt="卖家头像" 
                        style="border-radius: 50%; margin-right: 10px;vertical-align: middle;"
                    /> -->
                    <!-- <span style="font-size: 16px; color: #999;vertical-align: middle;">{{ productDetails.product_name }}</span> -->
                </el-col>
            </el-row>

            <!-- 商品价格 -->
            <el-row style="margin-top: 20px;">
                <el-col :span="24">
                    <div style="font-size: 22px; font-weight: bold; color: #ff4d4f;">￥{{ productDetails.price }}</div>
                </el-col>
            </el-row>

            <!-- 商品描述 -->
            <el-row style="margin-top: 20px;">
                <el-col :span="24">
                    <p style="font-size: 16px; color: #555; white-space: pre-line; word-wrap: break-word;">
                        商品详情：{{ productDetails.description }}
                    </p>
                </el-col>
            </el-row>

            <!-- 产品状态 -->
            <el-row style="margin-top: 20px;">
                <el-col :span="24">
                    <div style="font-size: 16px; color: #888;">产品状态：{{ productDetails.state }}</div>
                </el-col>
            </el-row>

            <!-- 上架时间
            <el-row style="margin-top: 10px;">
                <el-col :span="24">
                    <div style="font-size: 16px; color: #888;">添加商品时间：{{ productDetails.create_time }}</div>
                </el-col>
            </el-row> -->

            <!-- 加入购物车按钮 -->
            <el-row style="margin-top: 30px;">
                <el-col :span="24">
                    <el-button type="primary" style="width: auto; padding: 10px 20px;" @click="addToCart">
                            加入购物车
                    </el-button>
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
    import './ProductDetail.css'
    // 引入配置好的 axios 实例
    import axiosRequest from '../axiosRequset';
    export default {
        data() {
            return {
                productDetails: null, // 用于存储商品详情
            };
        },
        created() {
        // 获取当前URL中的id参数
            const id = this.$route.query.id;
            if (id) {
            // 发起axios请求
            this.fetchProductDetails(id);
            }
        },
        methods: {
        // 请求商品详情
        fetchProductDetails(id) {
            axiosRequest.get(`/product/detail?id=${id}`)   
            .then(response => {
            // 成功获取数据后，将数据存入productDetails
                this.productDetails = response.data.data;
            console.log(response);
            })
            .catch(error => {
            // 处理请求失败的情况
            console.error("Error fetching product details:", error);
            });
        },
            // 加入购物车
            addToCart() {
                this.$message.success('商品已加入购物车');
            }
        }
    }
</script>

