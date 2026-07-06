// 价格保留两位小数
export const formatPrice = (num) => {
  // 空值容错
  return Number(num || 0).toFixed(2)
}
// 空值兜底
export const emptyFilter = (val) => {
  return val ?? '-'
}
// 日期格式化
export const formatDate = (time) => {
  if (!time) return '-'
  const date = new Date(time)
  const y = date.getFullYear()
  const m = String(date.getMonth() + 1).padStart(2, '0')
  const d = String(date.getDate()).padStart(2, '0')
  return `${y}-${m}-${d}`
}