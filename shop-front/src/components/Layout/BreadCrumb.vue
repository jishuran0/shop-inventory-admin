<template>
  <el-breadcrumb separator="/" style="font-size:15px;">
    <el-breadcrumb-item style="color:#c6d8d0;">系统首页</el-breadcrumb-item>
    <el-breadcrumb-item style="color:#ffffff;font-weight:500;">{{ pageName }}</el-breadcrumb-item>
  </el-breadcrumb>
</template>
<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
const route = useRoute()
const pageName = computed(()=>{
  if(route.path.includes('dashboard')) return '首页总览'
  if(route.path.includes('goods/list')) return '全部商品'
  if(route.path.includes('goods/add')) return '上架商品'
  if(route.path.includes('category')) return '商品分类'
  if(route.path.includes('user')) return '管理员账号'
  return ''
})
</script>