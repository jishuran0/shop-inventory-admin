import { defineStore } from 'pinia'
export const useUserStore = defineStore('user', {
  state() {
    return {
      username: localStorage.getItem('username') || '',
      isLogin: !!localStorage.getItem('token'),
      token: localStorage.getItem('token') || ''
    }
  },
  actions: {
    // token存储
    loginSet(name, token) {
      this.username = name
      this.token = token
      this.isLogin = true
      localStorage.setItem('username', name)
      localStorage.setItem('token', token)
    },
    logoutClear() {
      this.username = ''
      this.token = ''
      this.isLogin = false
      localStorage.removeItem('username')
      localStorage.removeItem('token')
    }
  }
})