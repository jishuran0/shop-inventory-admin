<template>
  <div style="width:100vw;height:100vh;background:#f6f9f8;display:flex;align-items:center;justify-content:center;">
    <div style="width:450px;">
      <el-card
        shadow="never"
        style="
          border-radius: 20px !important;
          background: #ffffff;
          box-shadow: 0 4px 22px rgba(44, 62, 54, 0.05);
          padding: 40px 35px;
        "
      >
        <h2 align="center" style="margin:0 0 35px;color:#1f382c;font-weight:700;font-size:28px;">商城后台登录</h2>

        <el-form :model="loginForm" label-width="75px">
          <el-form-item label="账号" style="margin-bottom:22px;">
            <el-input 
              v-model="loginForm.username" 
              placeholder="admin"
              :style="{ '--el-input-border-radius':'12px' }"
              size="large"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" style="margin-bottom:30px;">
            <el-input 
              v-model="loginForm.password" 
              show-password 
              placeholder="123456"
              :style="{ '--el-input-border-radius':'12px' }"
              size="large"
            ></el-input>
          </el-form-item>

          <el-button
            type="primary"
            style="
              width:100%;
              height:54px;
              font-size:19px;
              background:#4a7c60;
              border:none;
              border-radius:14px !important;
              font-weight:500;
            "
            @click="submitLogin"
          >登录系统</el-button>

          <div style="text-align:center;margin-top:30px;font-size:16px;">
            <span style="color:#587568;">没有管理员账号？</span>
            <el-link type="primary" style="--el-link-color:#4a7c60;margin-left:8px;font-size:16px;" @click="$router.push('/register')">立即注册</el-link>
          </div>
        </el-form>
      </el-card>
    </div>
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