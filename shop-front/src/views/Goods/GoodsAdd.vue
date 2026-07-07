<template>
  <el-card title="上架商品" style="width:750px;margin:40px auto">
    <el-form :model="form" label-width="100px">
      <el-form-item label="商品名称">
        <el-input v-model="form.goodsName" placeholder="填写商品名字"></el-input>
      </el-form-item>
      <el-form-item label="售价">
        <el-input v-model="form.goodsPrice" type="number" placeholder="输入售卖价格"></el-input>
      </el-form-item>
      <el-form-item label="库存数量">
        <el-input v-model="form.goodsStock" type="number" placeholder="填写库存件数"></el-input>
      </el-form-item>
      <el-form-item label="商品描述">
        <el-input v-model="form.goodsDesc" type="textarea" :rows="3" placeholder="简单介绍商品特点"></el-input>
      </el-form-item>
      <el-form-item label="所属分类">
        <el-select v-model="form.categoryId" placeholder="选择分类" style="width:100%">
          <el-option
            v-for="item in cateList"
            :key="item.id"
            :label="item.categoryName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="warning" @click="$refs.uploadRef.click()">批量导入</el-button>
        <input type="file" ref="uploadRef" accept=".xlsx,.xls" style="display:none" @change="handleImportExcel" />
        <el-button type="primary" @click="submitAdd">确认新增</el-button>
        <el-button @click="$router.push('/admin/goods/list')">返回列表</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { addGoods, batchAddGoods } from '../../api/goods'
import { getCateAll } from '../../api/category'
import * as XLSX from 'xlsx'

const router = useRouter()
const form = ref({
  goodsName: '',
  goodsPrice: 0,
  goodsStock: 0,
  goodsDesc: '',
  categoryId: ''
})
const cateList = ref([])
const categoryMap = ref({})

const loadCate = async () => {
  const res = await getCateAll()
  if(res.code === 200) {
    cateList.value = res.data
    const map = {}
    res.data.forEach(cate => {
      map[cate.categoryName] = cate.id
    })
    categoryMap.value = map
  }
}
onMounted(loadCate)

const submitAdd = async () => {
  if(!form.value.goodsName){
    ElMessage.warning('请填写商品名称')
    return
  }
  if(!form.value.categoryId){
    ElMessage.warning('请选择所属分类')
    return
  }
  form.value.createTime = new Date()
  form.value.updateTime = new Date()
  const res = await addGoods(form.value)
  if(res.code === 200){
    ElMessage.success('商品上架成功')
    router.push('/admin/goods/list')
  }
}

// 批量导入
const handleImportExcel = async (event) => {
  const file = event.target.files[0]
  if (!file) return

  const reader = new FileReader()
  reader.onload = async (e) => {
    try {
      const data = new Uint8Array(e.target.result)
      const workbook = XLSX.read(data, { type: 'array' })
      const sheet = workbook.Sheets[workbook.SheetNames[0]]
      const json = XLSX.utils.sheet_to_json(sheet)

      const goodsList = json.map(row => {
        let categoryId = row['分类ID'] || row['categoryId']
        if (!categoryId) {
          const cateName = row['所属分类'] || row['categoryName'] || ''
          categoryId = categoryMap.value[cateName.trim()] || 1
        }
        return {
          goodsName: row['商品名称'] || row['goodsName'] || '',
          goodsPrice: parseFloat(row['售价'] || row['goodsPrice']) || 0,
          goodsStock: parseInt(row['库存数量'] || row['goodsStock']) || 0,
          goodsDesc: row['商品描述'] || row['goodsDesc'] || '',
          categoryId: parseInt(categoryId) || 1
        }
      })

      const res = await batchAddGoods(goodsList)
      if (res.code === 200) {
        ElMessage.success(`成功导入 ${goodsList.length} 条商品数据`)
        router.push('/admin/goods/list')
      } else {
        ElMessage.error(res.msg || '导入失败')
      }
    } catch (err) {
      ElMessage.error('文件解析失败，请检查Excel格式')
    }
  }
  reader.readAsArrayBuffer(file)
  event.target.value = ''
}
</script>