import type {AxiosInstance, AxiosResponse} from 'axios'
import axios from 'axios'

// 创建 axios 实例
const service: AxiosInstance = axios.create({
  baseURL: '/api', // 基础URL
  timeout: 15000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 这里可以添加token等认证信息
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse) => {
    const res = response.data
    // 这里可以根据后端约定的状态码判断请求是否成功
    return res
  },
  (error) => {
    // 统一的错误处理
    console.error('请求错误：', error)
    return Promise.reject(error)
  }
)

export default service