<template>
  <el-card title="后台管理员账号管理" style="border-radius:20px !important;">
    <div style="margin-bottom:15px">
      <el-button type="primary" style="background:#3a7d5c;border:none" @click="openAddUser">新增管理员</el-button>
    </div>
    <el-table :data="userList" border style="width:100%">
      <el-table-column label="序号" type="index" width="80" align="center"/>
      <el-table-column label="用户名" prop="username" align="center"/>
      <el-table-column label="登录密码" prop="password" align="center"/>
      <el-table-column label="操作" width="180" align="center">
        <template #default="scope">
          <el-button size="small" @click="openEditUser(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelUser(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="userDialogVisible" title="用户编辑" width="500px" style="--el-dialog-border-radius:16px">
      <el-form :model="userForm" label-width="90px">
        <el-form-item label="用户名">
          <el-input v-model="userForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="userForm.password"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="userDialogVisible=false">取消</el-button>
        <el-button type="primary" style="background:#3a7d5c;border:none" @click="submitUser">保存</el-button>
      </template>
    </el-dialog>
  </el-card>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getUserAll, addUser, updateUser, delUser as delUserApi } from '../../api/user'
const userList = ref([])
const userDialogVisible = ref(false)
const userForm = ref({ id: null, username: '', password: '' })
const isEdit = ref(false)

// 加载用户列表
const loadUserList = async () => {
  const res = await getUserAll()
  if(res.code === 200) userList.value = res.data
}
onMounted(() => loadUserList())

// 打开新增弹窗
const openAddUser = () => {
  userForm.value = { id: null, username: '', password: '' }
  isEdit.value = false
  userDialogVisible.value = true
}
// 打开编辑弹窗
const openEditUser = (row) => {
  userForm.value = { ...row }
  isEdit.value = true
  userDialogVisible.value = true
}
// 提交保存新增/编辑
const submitUser = async () => {
  let res
  if(isEdit.value){
    res = await updateUser(userForm.value)
  }else{
    res = await addUser(userForm.value)
  }
  if(res.code === 200){
    userDialogVisible.value = false
    ElMessage.success(isEdit.value ? '修改成功' : '新增成功')
    loadUserList()
  }else{
    ElMessage.error(res.msg || '操作失败')
  }
}
// 删除
const handleDelUser = async (id) => {
  try {
    await ElMessageBox.confirm('确定删除该管理员账号？', '提示', { type: 'warning' })
    const res = await delUserApi(id)
    if (res.code === 200) {
      ElMessage.success('删除成功')
      loadUserList()
    } else {
      ElMessage.error(res.msg || '删除失败')
    }
  } catch (err) {
    ElMessage.info('取消删除或请求异常')
  }
}
</script>