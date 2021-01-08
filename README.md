# 仿QQ聊天系统

## 技术选型
* 包管理工具
  * 这里使用的是gradle 6.8做外部包管理工具(因为gradle书写简洁，比maven的xml好配置)
* 外部包
  * mysql-connector-java
    * 用于数据库连接
    * version 8.0.15
  * mybatis
    * 用于数据库jdbc操作封装
    * version 3.5.6
  * lombok
    * 一些缩写注解语法糖
    * version 1.18.8
  * gson
    * 用于解析json格式数据进行网络通信
    * version 2.8.6
* 数据库
  * mysql 5.4

## 主要实现功能
* 用户登陆注册
* 搜索服务器用户进行单对单聊天

## 项目 UML 设计图
* **用户**

* **连接信息**
  * id
  * ip
  * 连接时间
  * 连接是否存在
  * userId
