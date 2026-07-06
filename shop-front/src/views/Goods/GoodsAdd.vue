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
import { addGoods } from '../../api/goods'
import { getCateAll } from '../../api/category'
const router = useRouter()
const form = ref({
  goodsName: '',
  goodsPrice: 0,
  goodsStock: 0,
  goodsDesc: '',
  categoryId: ''
})
const cateList = ref([])
const loadCate = async () => {
  const res = await getCateAll()
  if(res.code === 200) cateList.value = res.data
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
</script>