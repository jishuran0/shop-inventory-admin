import { defineStore } from 'pinia'
import { getGoodsAll } from '../api/goods'
export const useGoodsStore = defineStore('goods', {
  state() {
    return {
      goodsList: []
    }
  },
  actions: {
    // 加载所有商品存入全局状态
    async loadAllGoods() {
      const res = await getGoodsAll()
      if (res.code === 200) {
        this.goodsList = res.data
      }
    }
  }
})