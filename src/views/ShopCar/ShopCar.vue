<template>
  <div class="Shopcar-page">
    <div class="container m-top-20">
      <div class="cart">
        <table>
          <thead>
            <tr>
              <th width="120">
                <el-checkbox v-model="cartStore.isAll" @change="handleAllCheck" :disabled="!cartStore.isEditMode" />
              </th>
              <th width="400">商品信息</th>
              <th width="220">价格</th>
              <th width="180">数量</th>
              <th width="180">小计</th>
              <th width="140">操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in cartStore.cartList" :key="item.skuId">
              <td>
                <el-checkbox v-model="item.selected" @change="(selected) => handleCheck(item.skuId, selected)"
                  :disabled="!cartStore.isEditMode" />
              </td>
              <td>
                <div class="goods">

                  <img :src="item.picture" alt="" @click="pushShow(item.skuId)" />

                  <div>
                    <p class="name ellipsis">{{ item.name }}</p>
                  </div>
                </div>
              </td>
              <td class="tc">&yen;{{ item.price }}</td>
              <td class="tc">
                <el-input-number :min="1" :max="1" v-model="item.count"
                  @input="(count) => handleChangeCount(item.skuId, count)" :disabled="!cartStore.isEditMode" />
              </td>
              <td class="tc">
                &yen;{{ (item.price * item.count).toFixed(2) }}
              </td>
              <td class="tc">
                <el-popconfirm title="确认删除吗?" confirm-button-text="确认" cancel-button-text="取消"
                  @confirm="() => deleteCart(item)" :disabled="!cartStore.isEditMode">
                  <template #reference>
                    <el-button type="danger" round :disabled="!cartStore.isEditMode">
                      <i class="el-icon-delete"></i>
                    </el-button>
                  </template>
                </el-popconfirm>
              </td>
            </tr>
            <tr v-if="!cartStore.cartList.length">
              <td colspan="6">
                <el-empty description="购物车为空">
                  <el-button type="primary" @click="$router.push('/HomePage')" round>随便逛逛</el-button>
                </el-empty>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <el-divider>没有更多宝贝了</el-divider>
      <div class="action">
        <div class="batch">
          共 {{ cartStore.allCount }} 件商品，已选择
          {{ cartStore.selectedCount }} 件，商品合计：
          <span class="red">¥{{ cartStore.selectedPrice.toFixed(2) }}</span>
        </div>
        <div class="total">
          <el-button v-if="!cartStore.isEditMode" size="large" type="primary" @click="handleEdit" round>
            编辑
          </el-button>

          <el-button v-if="cartStore.isEditMode" size="large" type="primary" @click="handleSave" round>
            保存更改
          </el-button>

          <el-button v-if="!cartStore.isEditMode" size="large" type="primary" @click="handleCheckout()" round>
            下单结算
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "./ShopCar.css";
import axiosRequest from '../axiosRequset';
import { Message } from "element-ui"; // 引入提示框组件

export default {
  name: "ShopCart",
  data() {
    return {
      cartStore: {
        cartList: [], // 初始化为空，后续从后台获取数据
        isAll: false,
        isEditMode: false, // 标识是否处于编辑模式
        allCount: 0,
        selectedCount: 0,
        selectedPrice: 0,
      }
    };
  },
  mounted() {
    this.getCartData(); // 组件加载时获取购物车数据
  },
  methods: {
    pushShow(id) {
      this.$router.push(`/Homepage/Productdetail/${id}?id=${id}`);
    },
    // 获取购物车数据（从后端接口获取）
    async getCartData() {
      try {
        const response = await axiosRequest.get("/user/cart"); // 后端接口地址
        console.log(response);

        if (response && response.data && response.data.code === 0) {
          const productIds = response.data.data; // 从 response.data.data 获取商品 ID 数组
          this.cartStore.cartList = []; // 清空购物车列表
          await this.fetchProductDetailsByIds(productIds); // 获取商品详细信息
          this.updateCartSummary(); // 更新购物车汇总
        } else {
          console.error("获取购物车数据失败");
          Message({
            message: "获取购物车数据失败",
            type: "error",
            center: true,
          });
        }
      } catch (error) {
        console.error("获取购物车数据请求失败", error);
        Message({
          message: "获取购物车数据失败，请稍后再试。",
          type: "error",
          center: true,
        });
      }
    },

    // 根据商品ID列表获取商品详细信息
    async fetchProductDetailsByIds(productIds) {
      // 清空购物车列表
      this.cartStore.cartList = [];

      for (const productId of productIds) {
        try {
          const response = await axiosRequest.get("/product/detail", {
            params: { id: productId }
          });
          if (response && response.data && response.data.code === 0) {
            const productDetail = response.data.data; // 假设后端返回的商品详情对象
            // 将商品详细信息添加到购物车项中
            this.cartStore.cartList.push({
              skuId: productDetail.id,
              name: productDetail.product_name,
              picture: productDetail.product_pic,
              price: parseFloat(productDetail.price), // 确保价格是数值类型
              count: 1, // 默认数量为1
              selected: false, // 默认未选中
              consumerId: productDetail.consumer_id // 用户ID信息
            });
          } else {
            console.error("获取商品详细信息失败", response.data.message);
            Message({
              message: "获取商品信息失败: " + response.data.message,
              type: "error",
              center: true,
            });
          }
        } catch (error) {
          console.error("请求失败", error);
          Message({
            message: "获取商品信息失败，请稍后再试。",
            type: "error",
            center: true,
          });
        }
      }
      // 所有请求完成后更新购物车汇总
      this.updateCartSummary();
    },

    // 更新购物车的汇总数据
    updateCartSummary() {
      this.cartStore.allCount = this.cartStore.cartList.length; // 购物车中商品的种类数
      this.cartStore.selectedCount = this.cartStore.cartList.filter(item => item.selected).length; // 选中的商品种类数
      this.cartStore.selectedPrice = this.cartStore.cartList.reduce(
        (sum, item) => (item.selected ? sum + item.count * item.price : sum),
        0
      );
    },

    // 全选操作
    handleAllCheck() {
      this.cartStore.cartList.forEach((item) => (item.selected = this.cartStore.isAll));
      this.updateCartSummary();
    },

    // 选择单个商品
    handleCheck(skuId, selected) {
      const item = this.cartStore.cartList.find((item) => item.skuId === skuId);
      if (item) item.selected = selected;
      this.cartStore.isAll = this.cartStore.cartList.every((item) => item.selected);
      this.updateCartSummary();
    },

    // 修改商品数量
    handleChangeCount(skuId, count) {
      const item = this.cartStore.cartList.find((item) => item.skuId === skuId);
      if (item) item.count = count;
      this.updateCartSummary();
    },

    // 删除商品
    deleteCart(item) {
      this.cartStore.cartList = this.cartStore.cartList.filter(
        (cartItem) => cartItem.skuId !== item.skuId
      );
      this.updateCartSummary();
    },

    // 开启编辑模式
    handleEdit() {
      this.cartStore.isEditMode = true;
    },

    // 保存更改
    handleSave() {
      // 获取所有还在购物车中的商品，无论是否选中
      let cartItemsInStore = []
      for (let value of this.cartStore.cartList) {
        cartItemsInStore.push(value.skuId)
      }
      console.log(cartItemsInStore);

      // 模拟保存操作：将购物车中的商品数据返回后端
      axiosRequest.put("/user/cart", { list: cartItemsInStore }).then(() => {

        console.log(cartItemsInStore);
        this.cartStore.isEditMode = false;
        this.updateCartSummary();
        Message.success("购物车保存成功！");
      })
        .catch(error => {
          console.error("保存失败", error);
          Message.error("保存失败，请重试！");
        });
    },

    // 跳转到结算页面
    handleCheckout() {
      if (this.cartStore.cartList.length === 0) {
        Message({
          message: "购物车中还没有添加宝贝哦，请先逛逛吧",
          type: "warning",
          center: true,
        });
      } else if (this.cartStore.selectedCount === 0) {
        Message({
          message: "还没有选择宝贝哦",
          type: "warning",
          center: true,
        });
      } else {
        // 跳转到结算页面
        this.$router.push("/checkout");
      }
    },
  }
};
</script>