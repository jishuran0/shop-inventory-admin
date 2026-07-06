import request from '../utils/request'
// 全部商品
export function getGoodsAll() {
  return request({ url: '/goods/list', method: 'GET' })
}
// 单名称模糊搜索
export function searchGoodsByName(name) {
  return request({ url: '/goods/search', method: 'GET', params: { name } })
}
// 双条件联合模糊查询
export function multiSearchGoods(goodsName, categoryName) {
  return request({
    url: '/goods/multiSearch',
    method: 'GET',
    params: { goodsName, categoryName }
  })
}
// 新增商品
export function addGoods(data) {
  return request({ url: '/goods/add', method: 'POST', data })
}
// 修改商品
export function updateGoods(data) {
  return request({ url: '/goods/update', method: 'PUT', data })
}
// 删除商品
export function delGoods(id) {
  return request({ url: `/goods/delete/${id}`, method: 'DELETE' })
}