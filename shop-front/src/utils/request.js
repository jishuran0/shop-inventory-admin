import axios from 'axios'
import { ElMessage, ElLoading } from 'element-plus'
let loadingInstance = null

const service = axios.create({
  baseURL: 'http://localhost:8088',
  timeout: 12000
})
// 请求拦截
service.interceptors.request.use((config) => {
  loadingInstance = ElLoading.service({ text: '加载中...' })
  const token = localStorage.getItem('token')
  if (token) config.headers.Authorization = `Bearer ${token}`
  return config
})
// 响应拦截
service.interceptors.response.use(
  (res) => {
    loadingInstance?.close()
    return res.data
  },
  (error) => {
    loadingInstance?.close()
    if (error.response?.status === 401) {
      ElMessage.error('登录已失效，请重新登录')
      localStorage.removeItem('token')
      location.href = '/login'
    } else if (error.response?.status === 404) {
      ElMessage.error('接口不存在')
    } else if (error.response?.status === 500) {
      ElMessage.error('服务器异常')
    } else {
      ElMessage.error('网络请求失败')
    }
    return Promise.reject(error)
  }
)
export default service