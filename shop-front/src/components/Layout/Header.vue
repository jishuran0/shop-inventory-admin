<template>
  <div class="header-user">
    <el-dropdown trigger="click">
      <span style="cursor:pointer;color:#ffffff;font-size:15px;font-weight:500;">{{ userStore.username }}</span>
      <template #dropdown>
        <el-dropdown-item 
          @click="handleLogout"
          style="color:#c9655c;font-size:14px;"
        >退出登录</el-dropdown-item>
      </template>
    </el-dropdown>
  </div>
</template>
<script setup>
import { useUserStore } from '../../store/useUser'
import { useRouter } from 'vue-router'
const userStore = useUserStore()
const router = useRouter()
const handleLogout = () => {
  userStore.logoutClear()
  router.push('/login')
}
</script>
<style scoped>
.header-user {
  display: flex;
  align-items: center;
}
:deep(.el-dropdown-menu) {
  border-radius: 12px;
  border: none;
  box-shadow: 0 3px 16px rgba(44, 62, 54, 0.12);
}
:deep(.el-dropdown-item:hover) {
  background-color: #f0f5f3;
  color: #3a7d5c;
}
</style>