<template>
  <el-card shadow="hover" style="border-radius:20px !important;">
    <!-- 搜索栏 -->
    <div class="search-bar" style="display:flex;gap:12px;align-items:center;margin-bottom:18px">
      <el-input v-model="searchParams.goodsName" placeholder="输入商品名字搜索" style="width:220px;"></el-input>
      <el-input v-model="searchParams.categoryName" placeholder="输入分类名字筛选" style="width:220px;"></el-input>
      <el-button type="primary" style="background:#3a7d5c;border:none" @click="handleMultiSearch">筛选查询</el-button>
      <el-button @click="resetSearch">清空筛选</el-button>
      <el-button type="success" style="background:#5a9976;border:none" @click="$router.push('/admin/goods/add')">添加新商品</el-button>
    </div>
    <!-- 商品表格 -->
    <el-table :data="goodsStore.goodsList" border style="width:100%">
      <el-table-column label="序号" type="index" width="70" align="center"/>
      <el-table-column label="商品名称" prop="goodsName"/>
      <el-table-column label="售价" prop="goodsPrice"/>
      <el-table-column label="库存数量" prop="goodsStock"/>
      <el-table-column label="商品描述" prop="goodsDesc"/>
      <el-table-column label="所属分类" prop="categoryName"/>
      <el-table-column label="操作" width="180" align="center">
        <template #default="scope">
          <el-button size="small" @click="openEditDialog(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 编辑弹窗 -->
    <el-dialog v-model="editVisible" title="编辑商品信息" width="600px" style="--el-dialog-border-radius:16px">
      <el-form :model="editForm" label-width="100px">
        <el-form-item label="商品名称">
          <el-input v-model="editForm.goodsName"></el-input>
        </el-form-item>
        <el-form-item label="商品价格">
          <el-input v-model.number="editForm.goodsPrice"></el-input>
        </el-form-item>
        <el-form-item label="库存">
          <el-input v-model.number="editForm.goodsStock"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="editForm.goodsDesc"></el-input>
        </el-form-item>
        <el-form-item label="所属分类">
          <el-select v-model="editForm.categoryId" placeholder="请选择分类" style="width:100%">
            <el-option
              v-for="item in cateOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="editVisible = false">取消</el-button>
        <el-button type="primary" style="background:#3a7d5c;border:none" @click="submitEdit">保存修改</el-button>
      </template>
    </el-dialog>
  </el-card>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useGoodsStore } from '../../store/useGoods'
import { multiSearchGoods, delGoods, updateGoods } from '../../api/goods'
import { getCateAll } from '../../api/category'

const router = useRouter()
const goodsStore = useGoodsStore()
const searchParams = ref({ goodsName: '', categoryName: '' })
const editVisible = ref(false)
const editForm = ref({
  id: null, goodsName: '', goodsPrice: 0, goodsStock: 0, goodsDesc: '', categoryId: 1
})
const cateOptions = ref([])

// 加载分类下拉
const loadCateOptions = async () => {
  const res = await getCateAll()
  if (res.code === 200) {
    cateOptions.value = res.data.map(cate => ({
      label: cate.categoryName,
      value: cate.id
    }))
  }
}

onMounted(() => {
  goodsStore.loadAllGoods()
  loadCateOptions()
})

// 多条件查询
const handleMultiSearch = async () => {
  const res = await multiSearchGoods(searchParams.value.goodsName, searchParams.value.categoryName)
  if (res.code === 200) {
    goodsStore.goodsList = res.data
    ElMessage.success('查询完成')
  }
}
// 重置搜索
const resetSearch = () => {
  searchParams.value = { goodsName: '', categoryName: '' }
  goodsStore.loadAllGoods()
}
// 打开编辑回填
const openEditDialog = (row) => {
  editForm.value = { ...row }
  editVisible.value = true
}
// 提交修改
const submitEdit = async () => {
  const res = await updateGoods(editForm.value)
  if (res.code === 200) {
    editVisible.value = false
    ElMessage.success('修改成功')
    goodsStore.loadAllGoods()
  }
}
// 删除商品
const handleDelete = async (id) => {
  try {
    await ElMessageBox.confirm('确定要删除该商品吗？','提示',{type:'warning'})
    const res = await delGoods(id)
    if(res.code === 200){
      ElMessage.success('删除成功')
      goodsStore.loadAllGoods()
    }else{
      ElMessage.error(res.msg || '删除失败')
    }
  } catch(err) {
    ElMessage.info('取消删除或请求异常')
  }
}
</script>