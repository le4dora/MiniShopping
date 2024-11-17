import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import HomePage from '../views/HomePage/HomePage.vue'
import Products from '../views/Products/Products.vue'
import ShopCar from '../views/ShopCar/ShopCar.vue'
import ProductDetail from '../views/ProductDetail/ProductDetail.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/Register',
    name: 'Register',
    component: Register
  },
  {
    path: '/Home',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/Homepage',
    name: 'Homepage',
    component: HomePage,
    children: [
      {
        path: '',//默认路由
        component: Products,  //默认展示商品页
        name:Products
      },
      {
        path: 'Products',//商品页路由
        component: Products,  //展示商品页
        name:Products
      },
      {
        path: 'Shopcar',//购物车路由
        component: ShopCar,  //购物车页
        name:ShopCar
      },
      {
        path: 'Productdetail/:id',//商品详情页路由
        component: ProductDetail,  //商品详情页
        name:ProductDetail
      },

    ]
  },
]

const router = new VueRouter({
  routes
})

export default router
