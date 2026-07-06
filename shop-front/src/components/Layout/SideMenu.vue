<template>
  <el-container style="height: 100vh; width:100%;margin:0;padding:0;">
    <el-aside width="240px" style="background:#1a3024 !important">
      <div class="logo-box" style="height:78px;line-height:78px;text-align:center;color:#ffffff;font-size:21px;border-bottom:1px solid #284837;font-weight:700;letter-spacing:1px;">
        门店物资库存管理后台系统
      </div>
      <el-menu
        router
        text-color="#d1e4da"
        active-text-color="#ffffff"
        background="#1a3024"
        hover-background-color="#284837"
        active-background="#3a634c"
        :default-active="appStore.activeRoute"
        @select="appStore.setActiveRoute"
        style="padding:12px 0;border-right:none"
      >
        <el-menu-item index="/admin/dashboard">系统首页</el-menu-item>
        <el-sub-menu index="goods">
          <template #title>商品管理</template>
          <el-menu-item index="/admin/goods/list">全部商品</el-menu-item>
          <el-menu-item index="/admin/goods/add">上架新商品</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="/admin/category/list">商品分类</el-menu-item>
        <el-menu-item index="/admin/user/list">后台管理员</el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="
        height:66px;
        display:flex;
        align-items:center;
        justify-content:space-between;
        padding:0 34px;
        background:#ffffff !important;
        box-shadow:0 1px 4px rgba(44, 62, 54, 0.03);
      ">
        <BreadCrumb />
        <Header />
      </el-header>
      <el-main style="overflow:auto;min-height:calc(100vh - 66px)">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { useAppStore } from '../../store/useApp'
import Header from './Header.vue'
import BreadCrumb from './BreadCrumb.vue'
const appStore = useAppStore()
</script>

<style scoped>
:deep(.el-menu) {
  background-color: #1a3024 !important;
}
:deep(.el-sub-menu__wrap) {
  background-color: #1a3024 !important;
}
:deep(.el-sub-menu__inner) {
  background-color: #1a3024 !important;
}
:deep(.el-menu-item) {
  background: transparent !important;
}
:deep(.el-sub-menu__title) {
  background: transparent !important;
}
:deep(.el-menu-item:hover),
:deep(.el-sub-menu__title:hover) {
  background-color: #284837 !important;
}
:deep(.el-menu-item.is-active) {
  background-color: #3a634c !important;
}
</style>