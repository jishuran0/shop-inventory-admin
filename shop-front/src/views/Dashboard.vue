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
    <!-- ECharts 图表区域 -->
    <el-row :gutter="20" style="margin-top:20px">
      <el-col :span="12">
        <el-card shadow="hover" style="height:350px">
          <div ref="chartPieRef" style="width:100%;height:300px;"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover" style="height:350px">
          <div ref="chartBarRef" style="width:100%;height:300px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { onMounted, ref, nextTick, computed } from 'vue'
import { useGoodsStore } from '../store/useGoods'
import { getUserAll } from '../api/user'
import * as echarts from 'echarts'

const goodsStore = useGoodsStore()
const userTotal = ref(0)

// 图表 DOM 引用
const chartPieRef = ref(null)
const chartBarRef = ref(null)

// 按分类统计商品数量
const categoryStats = computed(() => {
  const map = {}
  goodsStore.goodsList.forEach(item => {
    const name = item.categoryName || '未分类'
    map[name] = (map[name] || 0) + 1
  })
  return map
})

const initCharts = () => {
  // 饼图：各分类商品占比
  const pieChart = echarts.init(chartPieRef.value)
  const pieData = Object.entries(categoryStats.value).map(([name, value]) => ({
    name,
    value
  }))
  pieChart.setOption({
    title: { text: '各分类商品占比', left: 'center', top: 5, textStyle: { fontSize: 14 } },
    tooltip: { trigger: 'item' },
    legend: { orient: 'vertical', left: 'left', top: 'center' },
    series: [{
      type: 'pie',
      radius: ['40%', '70%'],
      center: ['55%', '55%'],
      data: pieData.length ? pieData : [{ name: '暂无数据', value: 1 }],
      label: { formatter: '{b}\n{d}%' }
    }]
  })
  window.addEventListener('resize', () => pieChart.resize())

  // 柱状图：各分类商品数量
  const barChart = echarts.init(chartBarRef.value)
  const barData = Object.entries(categoryStats.value).sort((a, b) => b[1] - a[1])
  barChart.setOption({
    title: { text: '各分类商品数量', left: 'center', top: 5, textStyle: { fontSize: 14 } },
    tooltip: { trigger: 'axis' },
    xAxis: {
      type: 'category',
      data: barData.map(item => item[0]),
      axisLabel: { rotate: 20, interval: 0 }
    },
    yAxis: { type: 'value' },
    series: [{
      type: 'bar',
      data: barData.map(item => item[1]),
      itemStyle: { color: '#3a7d5c' },
      barWidth: '40%'
    }]
  })
  window.addEventListener('resize', () => barChart.resize())
}

onMounted(async () => {
  await goodsStore.loadAllGoods()
  const res = await getUserAll()
  if (res.code === 200) userTotal.value = res.data.length

  await nextTick()
  initCharts()
})
</script>