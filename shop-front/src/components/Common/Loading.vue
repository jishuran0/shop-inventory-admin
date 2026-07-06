<template>
  <div style="width:420px;margin:120px auto;">
    <el-card shadow="hover">
      <h2 align="center" style="margin-bottom:25px">商城后台登录</h2>
      <el-form :model="loginForm" label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="loginForm.username" placeholder="admin"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="loginForm.password" show-password placeholder="123456"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width:100%" @click="submitLogin">登录系统</el-button>
        </el-form-item>
      </el-form>
      <!-- 修复注册跳转区域 -->
      <div style="text-align:center;margin-top:12px">
        <span>没有管理员账号？</span>
        <el-link type="primary" @click="$router.push('/register')">立即注册</el-link>
      </div>
    </el-card>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../store/useUser'
import { ElMessage } from 'element-plus'

const router = useRouter()
const userStore = useUserStore()
const loginForm = ref({
  username: 'admin',
  password: '123456'
})

const submitLogin = () => {
  userStore.loginSet(loginForm.value.username)
  ElMessage.success('登录成功')
  router.push('/admin/dashboard')
}
</script>