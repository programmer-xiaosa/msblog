<p align="center"><a href="https://blog.arnasoft.site/" target="_blank" rel="noopener noreferrer"><img width="234" src="https://qarabala-video-save.oss-cn-beijing.aliyuncs.com/02477e9e-b4da-4306-acb0-9564e20f4440.png" alt="logo"></a></p>

## 一、项目介绍

我们这个项目就是完成一个完整的前后端分离的博客项目，包含服务端接口API，管理后台，前端网站，以及部署上线流程。这个项目主要介绍使用 `SpringBoot2.6  `  开发一套完整的 RESTful 风格服务端接口 API 和使用 `Vue2` 开发管理后台和前端网站。

### 1.1 后端工程

`msblog-springboot` 后端代码

- msblog-springboot  maven 父工程，统一管理依赖版本，聚合其他子模块
- ms-blog-pojo 子模块，存放管理后台功能
- ms-blog-common 子模块，存放公共类，例如：工具类，常量类，异常类等
- ms-blog-server 子模块，存放前端界面功能

```
├─ms-blog-common 子模块（存放公共类）
│  └─src
│      └─main
│          └─java
│              └─com
│                  └─arnasoft
│                      ├─constant
│                      ├─context
│                      ├─enumeration
│                      ├─exception 全局异常捕获类
│                      ├─json
│                      ├─properties 
│                      ├─result 返回类
│                      └─utils 工具类
├─ms-blog-pojo
│  └─src
│      └─main
│          └─java
│              └─com
│                  └─arnasoft
│                      ├─dto 
│                      ├─entity 实体类
│                      └─vo
└─ms-blog-server
    └─src
        ├─main
        │  ├─java
        │  │  └─com
        │  │      └─arnasoft
        │  │          ├─annotation
        │  │          ├─aspect
        │  │          ├─config
        │  │          │  └─sa_token
        │  │          ├─controller
        │  │          │  └─admin
        │  │          ├─handler
        │  │          ├─interceptor
        │  │          ├─mapper
        │  │          └─service
        │  │              └─impl
        │  └─resources 静态文件夹
        │      ├─doc 文档
        │      ├─mapper 
        │      ├─SQL 数据库
        │      └─template Excel 导入导出模板
        └─test 测试
            └─java
                └─com
                    └─arnasoft
                        └─test 
```



### 1.2 前端工程

 msblog-vue2  前端代码

```
├─build
├─mock
├─public
├─src
│  ├─api 接口
│  ├─assets 静态文件夹
│  │  ├─404_images
│  │  ├─iconfont
│  │  ├─icons
│  │  │  └─svg
│  │  └─images
│  ├─components
│  │  ├─Breadcrumb
│  │  ├─Hamburger
│  │  ├─IconSelect
│  │  ├─SvgIcon
│  │  └─v-md-editor
│  ├─icons
│  │  └─svg
│  ├─layout
│  │  ├─components
│  │  │  └─Sidebar
│  │  └─mixin
│  ├─router 路由模块
│  ├─store 状态管理
│  │  └─modules
│  ├─styles 样式
│  ├─utils 工具
│  └─views
│      ├─blog 文章管理模块
│      │  ├─article 文章
│      │  ├─category 分类
│      │  ├─comments 评论
│      │  └─tags 标签
│      ├─home 首页
│      ├─login 登录
│      ├─permission 权限模块
│      │  ├─admin 用户
│      │  ├─menus 菜单
│      │  └─roles 角色
│      └─setting 系统设置
└─tests 单元测试
    └─unit
        ├─components
        └─utils
```

### 1.3 接口文档

[http://124.220.96.197:8090/doc.html](https://github.com/lfb/nodejs-koa-blog/blob/master/doc/admin.md)

### 1.4 项目展示

- 前端线上展示地址：http://124.220.96.197:8091/#/home

## 二、使用项目

### 2.1 克隆项目

首先使克隆项目，然后进入项目根目录使用命令安装包，最后命令启动项目，代码会根据模型自动创建数据库表的。

```
# 克隆项目代码

git clone git@github.com:programmer-xiaosa/msblog.git
```

### 2.2 项目架构

拉取代码下来后，简单说明一下项目架构，我们简单熟悉一下，目的是为了了解清楚每个文件夹有什么作用的，好的代码结构并不仅仅是为了看上去清晰，它更像是我们对一个系统的拆解和组装。

### 2.3 创建数据库

启动项目前一定要在创建好 boblog 数据库，如果你还没安装上数据库，请点击[MySQL 下载](https://dev.mysql.com/downloads/mysql/)，请在根目录下的 |——config/config.js 文件下修改您本地的数据库名字（boblog）和数据库密码 ( password )。以下是执行数据库命令：

```
# 登录数据库

mysql -uroot -p (回车然后输入你的本机数据库密码)

# 创建 msblog 数据库

CREATE DATABASE IF NOT EXISTS boblog DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2.4 启动项目

以下是启动服务端项目的操作命令：

```
# 进入项目根目录

cd msblog-vue

# 安装依赖包

npm install

# 启动 msblog-vue 项目

npm run dev
```

API 端口默认是 `8080`，打开浏览器输入回车：`http://localhost:5000` 可以看到浏览器返回数据，可以查看目录下的 ./app/api/v1 下的接口或者看 doc 目录下的 markdown 接口文档，在 postman 测试接口。

Postman 下载地址：[https://www.postman.com/downloads/](https://www.postman.com/downloads/)

## 三、FAQ

1. 没有yarn环境，npm 可以吗？

> 答：可以的，建议使用 yarn，yarn 比 npm 速度快，主要是安装版本统一。

3. ... 更多问题请到 [Issues](https://github.com/lfb/nodejs-koa-blog/issues)查阅，或者有问题请到 [Issues 提问](https://github.com/lfb/nodejs-koa-blog/issues/new)。

## License

[MIT](https://github.com/lfb/nodejs-koa-blog/blob/master/LICENSE), by [programmer-xiaosa](https://github.com/programmer-xiaosa/msblog/commits?author=programmer-xiaosa)

喜欢或对你有帮助的话，请你点一个星星 <strong style='color:red;'>star</strong> 鼓励我，或者您有更好的建议和意见，请提出来告知我，可以留言 [Issues](https://github.com/programmer-xiaosa/msblog/issues/new)。希望能够帮助到你学习！Thanks

