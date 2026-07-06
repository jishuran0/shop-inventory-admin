import { defineStore } from 'pinia'
export const useAppStore = defineStore('app', {
  state() {
    return {
      activeRoute: '/dashboard', // 当前激活菜单路由
      pageLoading: false
    }
  },
  actions: {
    setActiveRoute(path) {
      this.activeRoute = path
    },
    setLoading(flag) {
      this.pageLoading = flag
    }
  }
})