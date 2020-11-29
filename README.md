# PinXv 可视化冷链溯源平台_后端 hackathon2020_backend

![冯虚御风标题](https://www.fguohao.top/pinxv.png)

# 强烈建议大家移步我们的前端项目查看文档！！！！[https://github.com/pinxv/hackathon2020_frontend]

## 前言

2020年注定是不平凡的一年，新冠肆虐之下，我们团结协作，用智慧与勇气，在同疫情的斗争过程中取得了阶段性胜利。

但我们同样注意到，在面对疫情时，还有很多方面我们无法有效应对，频繁出现的生鲜食品携带新冠病毒案例让人感到焦虑。作为一个有社会责任感的团队，```PinXv```提出了利用信息技术解决生鲜食品冷链溯源与安全管理的解决方案，由此诞生了此项目。我们希望在这个项目的帮助下，我们能够有效控制冷链传播疫情的风险，从而取得抗击疫情的胜利。

## 项目文档

API文档[https://github.com/pinxv/hackathon2020_backend/blob/main/backend_API.pdf]

## 项目介绍

```PinXv_backend```是可视化冷链溯源平台的服务端项目，基于SpringBoot与Spring Data JPA实现

### 技术选型

|       说明       | 技术                  | 官网                                       |
| :--------------: | --------------------- | ------------------------------------------ |
|     Web框架      | SpringBoot            | https://spring.io/projects/spring-boot     |
|      数据库      | Mysql                 | https://www.mysql.com/                     |
|     持久化层     | Spring Data JPA       | https://spring.io/projects/spring-data-jpa |
|     爬虫框架     | selenium&ChromeDriver | https://www.seleniumhq.org                 |
| 简化对象封装工具 | lombok                | https://projectlombok.org/                 |
|      工具类      | HuTool                | https://hutool.cn/                         |
|     接口管理     | DocWay                | http://www.docway.net/                     |

### 组织结构

```
├─config:配置类
├─controller
├─dao
├─entity
├─enums
├─selenium:爬虫
├─service
│  └─serviceimpl
├─task:定时服务
├─util
└─vo
    ├─adminuser
    ├─cargo
    └─risklevel
```

## 环境要求

Mysql 5.7及以上

maven环境

安装有Chrome浏览器并在```/opt```目录下安装有chromedriver（请注意chromedriver的执行权限要开启）

## 搭建步骤

1、使用 ```git clone https://github.com/pinxv/hackathon2020_backend.git``` 命令将项目clone到本地

2、在数据库内执行根目录下的.sql脚本

3、将```application.yml```修改为自己数据库的配置

4、使用```mvn clean package```打包项目

5、进入target目录，使用```java -jar hackathon2020_backend-0.0.1-SNAPSHOT.jar```命令启动服务

## 许可证

[Apache License 2.0](https://github.com/pinxv/hackathon2020_backend/blob/main/LICENSE)

Copyright (c) 2020 PinXv