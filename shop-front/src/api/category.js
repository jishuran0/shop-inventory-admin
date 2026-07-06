import request from '../utils/request'
// 查询所有
export function getCateAll() {
  return request({ url: '/category/list', method: 'GET' })
}
// 新增分类
export function addCate(data) {
  return request({ url: '/category/add', method: 'POST', data })
}
// 修改分类
export function updateCate(data) {
  return request({ url: '/category/update', method: 'PUT', data })
}
// 删除分类
export function delCate(id) {
  return request({ url: `/category/delete/${id}`, method: 'DELETE' })
}