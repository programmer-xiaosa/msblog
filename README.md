<p align="center"><a href="https://blog.arnasoft.site/" target="_blank" rel="noopener noreferrer"><img width="234" src="https://qarabala-video-save.oss-cn-beijing.aliyuncs.com/02477e9e-b4da-4306-acb0-9564e20f4440.png" alt="logo"></a></p>

## 一、项目介绍

 `Msblog`  项目就是完成一个完整的前后端分离的博客项目，包含服务端接口API，管理后台以及部署上线流程。这个项目主要介绍使用 `SpringBoot2.6  `  开发一套完整的 RESTful 风格服务端接口 API 和使用 `Vue2` 开发管理后台。

### 1.1 项目展示

- 前端线上展示地址：http://124.220.96.197:8091/#/home

  - 登录页面

  ![](C:\Users\musa\Desktop\msblog\截图\login.png)

  - 用户列表

  ![](C:\Users\musa\Desktop\msblog\截图\admin_list.png)

  - 用户新增

  ![](C:\Users\musa\Desktop\msblog\截图\admin_add.png)

  - 用户数据excel导出

  ![](C:\Users\musa\Desktop\msblog\截图\admin_excel_dao.png)

  - 角色列表

  ![](C:\Users\musa\Desktop\msblog\截图\role_list.png)

  - 角色新增和编辑

  ![](C:\Users\musa\Desktop\msblog\截图\role_add.png)

  ![](C:\Users\musa\Desktop\msblog\截图\admin_edit.png)

  - 菜单管理

  ![](C:\Users\musa\Desktop\msblog\截图\menu_list_edit_icon.png)

  - 博文管理-分类模块

  ![](C:\Users\musa\Desktop\msblog\截图\article_category_list.png)

  ![](C:\Users\musa\Desktop\msblog\截图\article_category_add.png)

  - 博文管理-标签模块

  ![](C:\Users\musa\Desktop\msblog\截图\article_tag_list.png)

  - 设置模块

  ![](C:\Users\musa\Desktop\msblog\截图\setting.png)

## 二、使用项目

### 2.1 克隆项目（后端工程）

```
# 克隆项目代码

git clone git@github.com:programmer-xiaosa/msblog.git
```

### 2.2 项目架构

`msblog-springboot` 后端代码

- msblog-springboot  maven 父工程，统一管理依赖版本，聚合其他子模块
- ms-blog-pojo 子模块，存放实体类
- ms-blog-common 子模块，存放公共类，例如：工具类，常量类，异常类等
- ms-blog-server 子模块，存放管理后台和前端界面功能

```
├─ms-blog-common
│  └─src
│      └─main
│          └─java
│              └─com
│                  └─arnasoft
│                      ├─constant 常量类
│                      ├─context 上下文
│                      ├─enumeration 枚举
│                      ├─exception 全局异常类
│                      ├─json 序列化器和反序列化器
│                      ├─properties 配置类
│                      ├─result 返回值类
│                      └─utils 工具类
├─ms-blog-pojo 实体类
│  └─src
│      └─main
│          └─java
│              └─com
│                  └─arnasoft
│                      ├─dto 数据传输对象
│                      ├─entity 实体类
│                      └─vo 将业务对象转换为 VO（值对象），用于展示给用户，实现前后端数据分离
└─ms-blog-server
    └─src
        ├─main
        │  ├─java
        │  │  └─com
        │  │      └─arnasoft
        │  │          ├─annotation 自定义注解
        │  │          ├─aspect 自定义切面，实现公共字段自动填充处理逻辑
        │  │          ├─config 配置
        │  │          │  └─sa_token  Sa-Token 权限认证工具类 
        │  │          ├─controller 控制器
        │  │          │  └─admin 后台控制模块
        				 └─h5 微信H5网页控制模块（待开发）
        				 └─App 移动端App控制模块（待开发）
        				 └─miniProgram 小程序控制模块（待开发）
        				 └─PC 前端网页控制模块（待开发）
        │  │          ├─handler 全局异常处理器（处理项目中抛出的业务异常）
        │  │          ├─interceptor 拦截器 (jwt令牌校验拦截器...)
        │  │          ├─mapper mapper层（定义接口规则）
        │  │          └─service 业务层
        │  │              └─impl 实现类
        │  └─resources 静态文件
        │      ├─doc 数据库表文档
        │      ├─mapper mapper层（定义数据库访问规则）
        │      ├─SQL 数据库初始化文件
        │      └─template 导入导出模板
        └─test 测试
```

### 2.3 创建数据库

启动项目前一定要在创建好 msblog 数据库，如果你还没安装上数据库，请点击[MySQL 下载](https://dev.mysql.com/downloads/mysql/)，安装完请初始化 `resources` 目录下的 |——`SQL` 文件。以下是执行数据库命令：

```
# 登录数据库

mysql -uroot -p (回车然后输入你的本机数据库密码)

# 创建 msblog 数据库

CREATE DATABASE IF NOT EXISTS msblog DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2.4 前端工程

 `msblog-vue`  前端工程

```
├─src
│  ├─api 接口
│  ├─assets 静态文件夹
│  ├─components 组件
│  ├─icons svg图标
│  ├─layout 公共组件
│  ├─router 路由模块
│  ├─store 状态管理
│  ├─styles 样式
│  ├─utils 工具
│  └─views
│      ├─blog 文章管理模块
│      │  ├─article 文章
│      │  ├─category 分类
│      │  ├─comments 评论（待开发）
│      │  └─tags 标签
│      ├─home 首页
│      ├─login 登录
│      ├─permission 权限模块
│      │  ├─admin 用户
│      │  ├─menus 菜单
│      │  └─roles 角色
│      └─setting 系统设置
└─tests 单元测试
```

### 2.5 启动项目

以下是启动服务端项目的操作命令：

```
# 进入项目根目录

cd msblog-vue

# 安装依赖包

npm install

# 启动 msblog-vue 项目

npm run dev
```

API 端口默认是 ` 9528`，打开浏览器输入回车：`http://localhost: 9528` 可以看到浏览器返回数据

### 2.6 接口文档

[http://124.220.96.197:8090/doc.html](http://124.220.96.197:8090/doc.html)

接口文档，在 postman 测试接口

Postman 下载地址：[https://www.postman.com/downloads/](https://www.postman.com/downloads/)

### 2.7 已开发的功能

- 登录功能

- 用户列表

  - 搜索用户
  - 新增用户
  - 编辑用户
  - 删除用户
  - 密码修改
  - 用户导出（通过excel形式保存到本地）

- 角色列表

  - 搜索角色
  - 新增角色
  - 编辑角色
  - 删除角色（系统内置用户不可删）

- 菜单列表

  - 新增菜单
  - 编辑菜单
  - 删除菜单

- 博文管理

  - 分类管理
    - 单条新增
    - 编辑分类
    - 批量导入（通过Excel模板批量新增）
    - 删除分类
    - 搜索分类
  - 标签管理
    - 单条新增
    - 编辑标签
    - 批量导入（通过Excel模板批量新增）
    - 删除标签
    - 搜索标签
  - 文章管理
    - 新增文章
    - 编辑文章
    - 删除文章
    - 搜索文章

- 博客设置

  - 博客名称
  - 作者名
  - 文档库地址
  - GitHub 主页访问地址
  - Gitee 主页访问地址
  - B站 主页访问地址
  - 博客 LOGO
  - 作者头像
  - 介绍语

### 2.8 待开发的页面和功能

  - PC端（展示博客）
  - 小程序端（方向用户分享和使用）
  - 微信H5网页（独立开发移动端，学习怎么获取用户code，access_token，用户信息，jsjdk和微信支付，分享卡片制作）
  - App（使用uniapp或者flutter开发多端APP，到时候根据实际情况选一个）
  - 桌面端（采用Electron或者Flutter开发桌面端应用）

### 2.9 项目部署

小伙伴们可以使用 传统方式部署也可以使用 docker jenkins 自动化部署

## 三、FAQ

1. 没有yarn环境，npm 可以吗？

> 答：可以的，建议使用 yarn，yarn 比 npm 速度快，主要是安装版本统一。

2. npm 下载依赖包失败？

> 答：本项目采用的事vue-admin-template模板，建议使用 node版本是 v14.16.0

3. ... 更多问题请到 [Issues](https://github.com/programmer-xiaosa/msblog/issues)查阅，或者有问题请到 [Issues 提问](https://github.com/programmer-xiaosa/msblog/issues/new)。

## License

[MIT](https://github.com/lfb/nodejs-koa-blog/blob/master/LICENSE), by [programmer-xiaosa](https://github.com/programmer-xiaosa/msblog/commits?author=programmer-xiaosa)

喜欢或对你有帮助的话，请你点一个星星 <strong style='color:red;'>star</strong> 鼓励我，或者您有更好的建议和意见，请提出来告知我，可以留言 [Issues](https://github.com/programmer-xiaosa/msblog/issues/new)。希望能够帮助到你学习！Thanks

