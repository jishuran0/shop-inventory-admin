import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
const Dashboard = () => import('../views/Dashboard.vue')
const GoodsList = () => import('../views/Goods/GoodsList.vue')
const GoodsAdd = () => import('../views/Goods/GoodsAdd.vue')
const CateList = () => import('../views/Category/CateList.vue')
const UserList = () => import('../views/User/UserList.vue')
const Layout = () => import('../components/Layout/SideMenu.vue')

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login, meta: { title: '登录', noAuth: true } },
  { path: '/register', component: Register, meta: { title: '注册', noAuth: true } }, 
  {
    path: '/admin',
    component: Layout,
    children: [
      { path: 'dashboard', component: Dashboard, meta: { title: '数据首页' } },
      { path: 'goods/list', component: GoodsList, meta: { title: '商品列表' } },
      { path: 'goods/add', component: GoodsAdd, meta: { title: '新增商品' } },
      { path: 'category/list', component: CateList, meta: { title: '商品分类' } },
      { path: 'user/list', component: UserList, meta: { title: '用户管理' } }
    ]
  }
]
const router = createRouter({
  history: createWebHistory(),
  routes
})
// localStorage判断登录
router.beforeEach((to, from, next) => {
  document.title = to.meta.title || '电商后台管理'
  const token = localStorage.getItem('token')
  if (to.path === '/login' || to.path === '/register') {
    next()
  } else if (!token) {
    next('/login')
  } else {
    next()
  }
})
export default router