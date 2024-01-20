# 数据加密实现实例
## 实现思路
1. 自定义脱敏的注释，包含：脱敏实类和脱敏字段
2. 使用拦截器在数据入库前加密
3. 使用拦截器在数据出库时解密
4. 可以使用mybatis的配置，配置两个插件，去引入自定义的拦截器，来处理数据加密和解密操作

## 数据脱敏演示
1. 下载源代码
2. 初始化sql
3. 修改配置文件的数据库连接配置
4. 运行com.ocean.angel.tool.controller.UserInfoControllerTest.save()方法，入库数据如下图：
5. 运行com.ocean.angel.tool.controller.UserInfoControllerTest.get(),查询数据，截图如下：

## 使用指南
1. 在返回的实体类上添加注解@SensitiveBean
2. 在返回的实体类上的加密字段添加@SensitiveField注解
3. 运行注解方法，去数据库查询查看插入的数据
