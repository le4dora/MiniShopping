import Vue from 'vue'
import App from './App.vue'
//引入VueRouter
import VueRouter from 'vue-router'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.config.productionTip = false

Vue.use(ElementUI);
//应用插件
Vue.use(VueRouter)
Vue.use(VueAxios, axios);


axios.interceptors.request.use((config) => {
  config.headers.Authorization = window.sessionStorage.getItem('token');
  console.log(config);
  return config;
})
new Vue({
  router:router,
  store,
  render: h => h(App)
}).$mount('#app')
