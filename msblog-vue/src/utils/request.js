import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import router from '@/router'
import { getStorage } from '@/utils/auth'

axios.defaults.withCredentials = true// Cookie跨域
// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['token'] = getStorage('token')
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
// 响应拦截器
service.interceptors.response.use(
  async(res) => {
    // 未设置状态码则默认成功状态
    const code = res.data.code
    const errCode = res.data.errCode
    // 获取错误信息
    if (code === 0) {
      if (errCode === 10007) {
        // try {
        //   store.dispatch('user/logout').then(() => {
        //     router.replace({ path: '/login' })
        //   })
        // } catch (error) {
        //   console.error(error)
        // }
        Message({
          message: '登录状态已过期，请退出重新登录',
          type: 'error'
        })
        await store.dispatch('user/resetStorage')
        router.replace({ path: '/login' })
        return Promise.reject(new Error(res.data.msg))
      } else {
        Message({
          message: res.data.msg,
          type: 'error'
        })
        return Promise.reject(new Error(res.data.msg))
      }
    } else {
      return res.data
    }
    //  else if (code === 500 || code === 400) {
    //   Message({
    //     message: res.data.msg,
    //     type: 'error'
    //   })
    //   return Promise.reject(new Error(res.data.msg))
    // } else if (code !== 200) {
    //   Notification.error({
    //     title: res.data.msg
    //   })
    //   return Promise.reject('error')
    // }
  },
  (error) => {
    console.log('err' + error)
    let { message } = error
    if (message === 'Network Error') {
      message = '后端接口连接异常'
    } else if (message.includes('timeout')) {
      message = '系统接口请求超时'
    } else if (message.includes('Request failed with status code')) {
      message = '系统接口' + message.substr(message.length - 3) + '异常'
    }
    Message({
      message: message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
