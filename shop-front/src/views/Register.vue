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
        <h2 align="center" style="margin:0 0 35px;color:#1f382c;font-weight:700;font-size:28px;">商城管理员注册</h2>

        <el-form :model="regForm" label-width="75px">
          <el-form-item label="用户名" style="margin-bottom:22px;">
            <el-input 
              v-model="regForm.username" 
              placeholder="设置登录账号"
              :style="{ '--el-input-border-radius':'12px' }"
              size="large"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" style="margin-bottom:30px;">
            <el-input 
              v-model="regForm.password" 
              show-password 
              placeholder="设置登录密码"
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
            @click="submitReg"
          >完成注册</el-button>

          <div style="text-align:center;margin-top:30px;font-size:16px;">
            <span style="color:#587568;">已有账号？</span>
            <el-link type="primary" style="--el-link-color:#4a7c60;margin-left:8px;font-size:16px;" @click="$router.push('/login')">去登录</el-link>
          </div>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { addUser } from '../api/user'
const router = useRouter()
const regForm = ref({ username: '', password: '' })

const submitReg = async () => {
  if (!regForm.value.username || !regForm.value.password) {
    ElMessage.warning('账号和密码不能为空')
    return
  }
  const res = await addUser(regForm.value)
  if (res.code === 200) {
    ElMessage.success('注册成功，请登录')
    router.push('/login')
  }
}
</script>