import request from '../utils/request'
// 查询所有管理员
export function getUserAll() {
  return request({ url: '/user/list', method: 'GET' })
}
// 根据ID查单个用户
export function getUserById(id) {
  return request({ url: `/user/${id}`, method: 'GET' })
}
// 新增用户（注册）
export function addUser(data) {
  return request({ url: '/user/add', method: 'POST', data })
}
// 修改用户
export function updateUser(data) {
  return request({ url: '/user/update', method: 'PUT', data })
}
// 删除用户
export function delUser(id) {
  return request({ url: `/user/delete/${id}`, method: 'DELETE' })
}
// 新增登录
export function userLogin(data) {
  return request({ url: '/user/login', method: 'POST', data })
}