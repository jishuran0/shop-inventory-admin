<template>
  <el-card title="商品分类管理" style="border-radius:20px !important;">
    <div style="margin-bottom:15px">
      <el-button type="primary" style="background:#3a7d5c;border:none" @click="openAdd">新建分类</el-button>
    </div>
    <el-table :data="cateList" border style="width:100%">
      <el-table-column label="序号" type="index" width="80" align="center"/>
      <el-table-column label="分类名称" prop="categoryName"/>
      <el-table-column label="操作" width="180" align="center">
        <template #default="scope">
          <el-button size="small" @click="openEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDel(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialogVisible" title="编辑分类" width="500px" style="--el-dialog-border-radius:16px">
      <el-form :model="form" label-width="90px">
        <el-form-item label="分类名称">
          <el-input v-model="form.categoryName"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" style="background:#3a7d5c;border:none" @click="submit">保存</el-button>
      </template>
    </el-dialog>
  </el-card>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getCateAll, addCate, updateCate, delCate } from '../../api/category'

const cateList = ref([])
const dialogVisible = ref(false)
const form = ref({id:null, categoryName:''})
const isEdit = ref(false)

const loadList = async () => {
  const res = await getCateAll()
  if(res.code === 200) cateList.value = res.data
}
onMounted(()=> loadList())

const openAdd = () => {
  form.value = {id:null, categoryName:''}
  isEdit.value = false
  dialogVisible.value = true
}
const openEdit = (row) => {
  form.value = {...row}
  isEdit.value = true
  dialogVisible.value = true
}
const submit = async () => {
  let res
  if(isEdit.value){
    res = await updateCate(form.value)
  }else{
    res = await addCate(form.value)
  }
  if(res.code === 200){
    dialogVisible.value = false
    ElMessage.success(isEdit.value ? '修改成功' : '新增成功')
    loadList()
  }else{
    ElMessage.error(res.msg || '操作失败')
  }
}

const handleDel = async (id) => {
  try {
    await ElMessageBox.confirm('确定删除该分类？', '提示', { type: 'warning' })
    const res = await delCate(id)
    if (res.code === 200) {
      ElMessage.success('删除成功')
      loadList()
    } else {
      ElMessage.error(res.msg || '删除失败')
    }
  } catch (err) {
    ElMessage.info('取消删除或请求异常')
  }
}
</script>