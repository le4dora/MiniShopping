import axios from 'axios';

// 创建 axios 实例
const instance = axios.create({
  baseURL: 'http://localhost:8080', //API 基础 URL
  timeout: 10000, // 请求超时时间
});

// 添加请求拦截器
instance.interceptors.request.use(
    (config) => {
        // 获取 token（假设 token 存储在 localStorage 中）
        const token = localStorage.getItem('token'); 
        console.log(token);
        if (token) {
          // 如果存在 token，将其添加到请求头中
            config.headers.Authorization =token;    
            console.log(config);
        }
        return config;
    },
    (error) => {
        // 请求错误时做些处理
        return Promise.reject(error);
    }
);

// 导出 axios 实例
export default instance;
