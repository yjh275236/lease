# 租赁管理系统

一个基于 Spring Boot 3 的现代化租赁管理系统，支持公寓、房间管理、租赁协议、预约看房等核心业务功能。

## 📋 项目简介

本项目是一个完整的租赁管理平台，采用前后端分离架构，提供管理后台和用户端两个应用。系统支持公寓信息管理、房间管理、租赁协议签订、预约看房、用户管理等核心功能。

## 🏗️ 项目架构

项目采用 Maven 多模块架构，主要包含以下模块：

- **common**: 公共模块，包含工具类、异常处理、配置类等通用功能
- **model**: 数据模型模块，包含实体类和枚举类
- **web**: Web 应用模块
  - **web-admin**: 管理后台应用（端口：8080）
  - **web-app**: 用户端应用（端口：8081）

## 🛠️ 技术栈

### 后端框架
- **Spring Boot**: 3.0.5
- **Java**: 17
- **MyBatis Plus**: 3.5.3.1（ORM 框架）
- **Spring Data Redis**: Redis 缓存支持

### 数据库
- **MySQL**: 关系型数据库
- **Redis**: 缓存数据库

### 安全与认证
- **JWT**: 0.11.2（JSON Web Token 身份认证）
- **EasyCaptcha**: 1.6.2（图形验证码）

### 文档与工具
- **Knife4j**: 4.1.0（API 文档工具，基于 Swagger）

### 第三方服务
- **MinIO**: 8.2.0（对象存储，用于文件上传）
- **阿里云短信**: 2.0.23（短信验证码服务）

## 📁 项目结构

```
lease-main/
├── common/                    # 公共模块
│   ├── constant/             # 常量定义
│   ├── exception/            # 异常处理
│   ├── login/                # 登录相关
│   ├── minio/                # MinIO 配置
│   ├── mybatisplus/          # MyBatis Plus 配置
│   ├── result/               # 统一返回结果
│   ├── sms/                  # 短信服务配置
│   └── utils/                # 工具类
├── model/                     # 数据模型模块
│   ├── entity/               # 实体类
│   └── enums/                # 枚举类
└── web/                       # Web 应用模块
    ├── web-admin/            # 管理后台
    │   ├── controller/      # 控制器
    │   ├── service/         # 服务层
    │   ├── mapper/          # 数据访问层
    │   └── resources/       # 配置文件
    └── web-app/             # 用户端应用
        ├── controller/      # 控制器
        ├── service/         # 服务层
        ├── mapper/          # 数据访问层
        └── resources/       # 配置文件
```

## 🎯 核心功能

### 管理后台（web-admin）
- **公寓管理**: 公寓信息维护、设施管理、标签管理、费用配置
- **房间管理**: 房间信息维护、房间属性配置、设施关联
- **租约管理**: 租赁协议管理、租期配置、支付类型管理
- **预约管理**: 看房预约审核与管理
- **用户管理**: 用户信息查询与管理
- **系统管理**: 系统用户管理、职位管理
- **文件上传**: 支持图片等文件上传到 MinIO

### 用户端（web-app）
- **公寓浏览**: 浏览公寓列表、查看公寓详情
- **房间查询**: 按条件查询房间、查看房间详情
- **预约看房**: 提交预约看房申请
- **浏览历史**: 记录用户浏览历史
- **用户中心**: 个人信息管理

## 🚀 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+
- MinIO（可选，用于文件存储）

### 配置说明

#### 1. 数据库配置
修改 `application.yml` 中的数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/lease?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2b8
    username: your_username
    password: your_password
```

#### 2. Redis 配置
```yaml
spring:
  data:
    redis:
      host: localhost
      port: 6379
      database: 0
```

#### 3. MinIO 配置（可选）
```yaml
minio:
  endpoint: http://localhost:9000
  access-key: your_access_key
  secret-key: your_secret_key
  bucket-name: lease
```

#### 4. 阿里云短信配置（可选）
```yaml
aliyun:
  sms:
    # 配置阿里云短信相关参数
```

### 运行项目

1. **克隆项目**
```bash
git clone <repository-url>
cd lease-main
```

2. **编译项目**
```bash
mvn clean install
```

3. **运行管理后台**
```bash
cd web/web-admin
mvn spring-boot:run
```
访问地址: http://localhost:8080
API 文档: http://localhost:8080/doc.html

4. **运行用户端应用**
```bash
cd web/web-app
mvn spring-boot:run
```
访问地址: http://localhost:8081
API 文档: http://localhost:8081/doc.html

## 📚 API 文档

项目集成了 Knife4j（Swagger），启动应用后可通过以下地址访问 API 文档：

- 管理后台: http://localhost:8080/doc.html
- 用户端: http://localhost:8081/doc.html

## 🗄️ 数据库设计

### 核心实体
- **公寓信息** (ApartmentInfo): 公寓基本信息、位置信息
- **房间信息** (RoomInfo): 房间号、租金等
- **租赁协议** (LeaseAgreement): 租约详情、承租人信息
- **预约信息** (ViewAppointment): 看房预约记录
- **用户信息** (UserInfo): 用户基本信息
- **浏览历史** (BrowsingHistory): 用户浏览记录

### 关联实体
- **设施信息** (FacilityInfo): 公寓/房间设施
- **标签信息** (LabelInfo): 公寓/房间标签
- **费用配置** (FeeKey/FeeValue): 费用类型和费用值
- **属性配置** (AttrKey/AttrValue): 房间属性
- **图片信息** (GraphInfo): 图片存储信息

## 🔐 安全特性

- JWT Token 身份认证
- 图形验证码验证
- 短信验证码支持（阿里云短信）
- 统一异常处理
- 接口权限拦截

## 📝 开发规范

- 采用 RESTful API 设计风格
- 统一返回结果封装（Result）
- 统一异常处理（GlobalExceptionHandler）
- MyBatis Plus 自动填充创建时间、更新时间
- 使用枚举类管理状态值

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

## 📄 许可证

本项目采用 [MIT License](LICENSE) 许可证。

## 👥 作者

YJH

---

如有问题或建议，请提交 Issue 或联系项目维护者。

