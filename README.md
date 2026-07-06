# shop-inventory-admin 门店物资库存管理后台
前后端分离全栈库存管理系统，面向门店商品、分类、管理员权限做库存管控，配套完整前后端业务代码。

## 一、项目目录结构

shop-inventory-admin/
├─ shop-front # Vue3 前端项目
│ ├─ public
│ ├─ src
│ │ ├─ api # 接口请求文件
│ │ │ ├─ category.js
│ │ │ ├─ goods.js
│ │ │ └─ user.js
│ │ ├─ assets # 静态资源、全局样式
│ │ │ ├─ css
│ │ │ │ └─ global.scss
│ │ │ └─ images
│ │ ├─ components # 通用公共组件
│ │ │ ├─ Common
│ │ │ │ └─ Loading.vue
│ │ │ └─ Layout
│ │ │ ├─ BreadCrumb.vue
│ │ │ ├─ Header.vue
│ │ │ └─ SideMenu.vue
│ │ ├─ router # 路由配置
│ │ │ └─ index.js
│ │ ├─ store # Pinia 状态管理
│ │ │ ├─ useApp.js
│ │ │ ├─ useGoods.js
│ │ │ └─ useUser.js
│ │ ├─ utils # 工具函数、请求封装
│ │ │ ├─ format.js
│ │ │ └─ request.js
│ │ ├─ views # 页面视图
│ │ │ ├─ Category
│ │ │ │ └─ CateList.vue
│ │ │ ├─ Goods
│ │ │ │ ├─ GoodsAdd.vue
│ │ │ │ └─ GoodsList.vue
│ │ │ ├─ User
│ │ │ │ └─ UserList.vue
│ │ │ ├─ Dashboard.vue
│ │ │ ├─ Login.vue
│ │ │ └─ Register.vue
│ │ ├─ App.vue
│ │ └─ main.js
│ ├─ index.html
│ ├─ package.json
│ ├─ package-lock.json
│ └─ vite.config.js
│
├─ shop-backed # SpringBoot 后端项目
│ ├─ src
│ │ └─ main
│ │ ├─ java/com/shop
│ │ │ ├─ common # 统一返回结果封装
│ │ │ │ └─ Result.java
│ │ │ ├─ config # 全局配置、异常处理
│ │ │ │ └─ GlobalExceptionHandler.java
│ │ │ ├─ controller # 接口控制层
│ │ │ │ ├─ CategoryController.java
│ │ │ │ ├─ GoodsController.java
│ │ │ │ └─ UserController.java
│ │ │ ├─ domain # 实体类
│ │ │ │ ├─ Category.java
│ │ │ │ ├─ Goods.java
│ │ │ │ └─ User.java
│ │ │ ├─ mapper # MyBatis 数据访问层
│ │ │ │ ├─ CategoryMapper.java
│ │ │ │ ├─ GoodsMapper.java
│ │ │ │ └─ UserMapper.java
│ │ │ ├─ service # 业务逻辑层
│ │ │ │ ├─ impl
│ │ │ │ │ ├─ CategoryServiceImpl.java
│ │ │ │ │ ├─ GoodsServiceImpl.java
│ │ │ │ │ └─ UserServiceImpl.java
│ │ │ │ ├─ CategoryService.java
│ │ │ │ ├─ GoodsService.java
│ │ │ │ └─ UserService.java
│ │ │ └─ ShopBackedApplication.java
│ │ └─ resources
│ │ ├─ mybatis # MyBatis 映射文件
│ │ │ ├─ CategoryMapper.xml
│ │ │ ├─ GoodsMapper.xml
│ │ │ └─ UserMapper.xml
│ │ ├─ sql # 数据库初始化脚本
│ │ │ └─ init.sql
│ │ └─ application.yaml
│ └─ pom.xml
│
├─ .gitignore # 全局文件过滤配置
└─ README.md # 项目说明文档


## 二、技术栈
### 前端 shop-front
Vue3 + Vite + Pinia + Axios + SCSS + ElementPlus

### 后端 shop-backed
SpringBoot 3.2.5 + MyBatis + MySQL8.3 + Druid

## 三、环境要求
JDK21、Maven3.8+、Node18+、MySQL8.3

## 四、核心功能
1. 基于VueRouter路由守卫实现登录鉴权，Pinia持久化保存用户登录状态
2. 管理员、商品、商品分类三大业务模块完整增删改查
3. 支持多条件联合检索、分页展示、弹窗式新增/编辑表单
4. Axios全局统一封装请求、响应拦截，自动处理Token携带与接口异常提示
5. 前端路由懒加载，封装布局、加载框等通用组件，降低代码冗余

## 五、本地启动教程
### 前端启动
1. 进入 `shop-front` 目录
2. 安装项目依赖：`npm install`
3. 启动开发服务：`npm run dev`

### 后端启动
1. 数据库初始化脚本：`shop-backed/src/main/resources/sql/init.sql`
2. 本地MySQL创建 `test_db` 库，执行脚本生成业务数据表
3. 修改 `application.yaml` 中MySQL账号、密码
4. 运行 `ShopBackedApplication` 启动后端接口服务

## 六、项目优化与规范
1. 路由懒加载分割页面资源，提升页面首屏加载速度
2. 抽取通用业务组件，减少重复编码，提升复用性
3. 清理后端项目多余辅助文件，精简工程目录结构
4. 全局.gitignore配置，过滤依赖包、编译产物，仅上传核心业务代码