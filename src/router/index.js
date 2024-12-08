import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import HomePage from '../views/HomePage/HomePage.vue'
import Products from '../views/Products/Products.vue'
import ShopCar from '../views/ShopCar/ShopCar.vue'
import ProductDetail from '../views/ProductDetail/ProductDetail.vue'
import Personal from "@/views/Personal.vue";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/Homepage',
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
      {
        path: 'Personal', //个人中心
        component: Personal,
        name:Personal,
        children:[{
          path: 'Message',
          component: ()=>import('@/views/Personal/Message.vue'),
        },
          {
            path: 'MyOrder',
            component:()=>import('@/views/Personal/MyOrder.vue')
          },
          {
            path: 'AddItem',
            component:()=>import('@/views/Personal/AddItem.vue')
          }]
      }

    ]
  },
    {
    path: '/Login',
    name: 'Login',
    component: Login
  },
  {
    path: '/Register',
    name: 'Register',
    component: Register
  },
]



const router = new VueRouter({
  routes
})
// 添加路由守卫
router.beforeEach((to, from, next) => {
  const whiteList =['/Login', '/Register','/Homepage',]
  const token = localStorage.getItem('token');

  if (token) { // 登录状态放行
    next();
  } else {
    if (whiteList.indexOf(to.path) !== -1 || to.path.startsWith('/Homepage')) {// 在白名单中，放行
      next();
    }else {// 不在白名单，重定向到登录页
      next('/Login');
    }
  }


})

export default router
