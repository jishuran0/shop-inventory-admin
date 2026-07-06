```markdown
# 🏪 shop-inventory-admin

> 前后端分离的全栈门店物资库存管理系统 · 基于 Vue3 + SpringBoot 3.2.5

![GitHub stars](https://img.shields.io/badge/⭐-欢迎Star-brightgreen?style=social)
![GitHub forks](https://img.shields.io/badge/🍴-欢迎Fork-blue?style=social)
![GitHub issues](https://img.shields.io/badge/🐛-Issues-red)
![GitHub license](https://img.shields.io/badge/📄-MIT-green)

---

## ✨ 功能亮点

- 🔐 **登录鉴权** — VueRouter 路由守卫 + Pinia 持久化存储登录态
- 📦 **三核心模块** — 管理员、商品、分类的完整增删改查
- 🔍 **高级检索** — 多条件联合搜索 + 分页展示
- 💬 **统一请求封装** — Axios 拦截器自动携带 Token 并统一处理异常
- ⚡ **性能优化** — 路由懒加载 + 通用组件复用
- 🎨 **清爽 UI** — ElementPlus + SCSS，适配后台管理场景

---

## 📂 项目目录结构

```bash
shop-inventory-admin/
├── shop-front/                # Vue3 前端项目
│   ├── src/
│   │   ├── api/               # API 接口封装
│   │   │   ├── category.js
│   │   │   ├── goods.js
│   │   │   └── user.js
│   │   ├── assets/            # 静态资源（CSS/图片）
│   │   ├── components/        # 公共组件（Layout/Loading等）
│   │   ├── router/            # 路由配置（含守卫）
│   │   ├── store/             # Pinia 状态管理（App/Goods/User）
│   │   ├── utils/             # 工具函数 + 请求封装
│   │   ├── views/             # 业务页面（Dashboard/Login/Register等）
│   │   ├── App.vue
│   │   └── main.js
│   ├── index.html
│   ├── package.json
│   └── vite.config.js
│
├── shop-backed/               # SpringBoot 后端项目
│   ├── src/main/java/com/shop/
│   │   ├── common/            # 统一返回结果封装
│   │   ├── config/            # 全局配置 & 异常处理器
│   │   ├── controller/        # 接口控制层（Category/Goods/User）
│   │   ├── domain/            # 实体类
│   │   ├── mapper/            # MyBatis 数据访问层
│   │   ├── service/           # 业务逻辑层（含 impl）
│   │   └── ShopBackedApplication.java
│   ├── src/main/resources/
│   │   ├── mybatis/           # MyBatis XML 映射文件
│   │   ├── sql/               # 数据库初始化脚本
│   │   └── application.yaml
│   └── pom.xml
│
├── .gitignore
└── README.md
```

---

## 🧰 技术栈

| 前端 | 后端 |
|------|------|
| Vue 3 | SpringBoot 3.2.5 |
| Vite | MyBatis |
| Pinia | MySQL 8.3 |
| Axios | Druid 连接池 |
| SCSS | Maven |
| ElementPlus | JDK 21 |

---

## ⚙️ 环境要求

- **前端**：Node.js 18+ / npm
- **后端**：JDK 21 / Maven 3.8+
- **数据库**：MySQL 8.3

---

## 🚀 本地启动教程

### 1️⃣ 前端（shop-front）

```bash
cd shop-front
npm install
npm install echarts xlsx
npm run dev
```

### 2️⃣ 后端（shop-backed）

```bash
# 1. 创建数据库 test_db，执行初始化脚本
#    shop-backed/src/main/resources/sql/init.sql

# 2. 修改 application.yaml 中的 MySQL 账号密码

# 3. 启动 SpringBoot 主类
ShopBackedApplication.java
```

---

## 📌 项目规范 & 优化

- ✅ 路由懒加载，提升首屏加载速度  
- ✅ 通用组件抽取（Layout、Loading 等），减少冗余代码  
- ✅ 请求/响应拦截器统一处理 Token 和异常提示  
- ✅ 后端精简目录，仅上传核心业务代码（配合 `.gitignore`）

---

## 🤝 贡献指南

欢迎提交 Issue 或 Pull Request，一起让这个库存系统更好用！

---

## 📄 许可证

[MIT](https://choosealicense.com/licenses/mit/) © your-name
```