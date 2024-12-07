import axios from "axios";


const instance = axios.create({
    headers: {
        'Access-Control-Allow-Origin': '*'
    },
    baseURL: 'http://localhost:8081',
    timeout: 10000,
});

instance.defaults.headers.post['content-type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
instance.defaults.headers.get['content-type'] = 'application/json;charset=UTF-8';
instance.defaults.headers.put['content-type'] = 'application/json;charset=UTF-8';

// 添加请求拦截器
instance.interceptors.request.use(
    config => {
        // 在发送请求之前做些什么
        const token = localStorage.getItem('token'); // 从localStorage中获取token
        // console.log(token);
        if (token) {
            config.headers.Authorization = token; // 将token添加到请求头中
        }
        return config;
    },
    error => {
        // 对请求错误做些什么
        return Promise.reject(error);
    }
);

export default instance;