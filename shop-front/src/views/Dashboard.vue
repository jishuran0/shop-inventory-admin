<template>
  <div class="dash-wrap">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="hover">
          <h3>商品总数</h3>
          <p style="font-size:26px;margin-top:10px">{{ goodsStore.goodsList.length }}</p>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <h3>管理员数量</h3>
          <p style="font-size:26px;margin-top:10px">{{ userTotal }}</p>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card shadow="hover">
          <h3>在售商品总数</h3>
          <p style="font-size:26px;margin-top:10px">{{ goodsStore.goodsList.length }}</p>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <h3>后台管理员数量</h3>
          <p style="font-size:26px;margin-top:10px">{{ userTotal }}</p>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useGoodsStore } from '../store/useGoods'
import { getUserAll } from '../api/user'
const goodsStore = useGoodsStore()
const userTotal = ref(0)

onMounted(async () => {
  await goodsStore.loadAllGoods()
  const res = await getUserAll()
  if(res.code === 200) userTotal.value = res.data.length
})
</script>