# 一、项目端口划分  
eureka 分布式注册中心模块  8761  
config 分布式配置中心模块  8082  
api-gateway zuul网关模块  8083  
order   订单业务模块      8081             
product 产品业务模块      8085                     
user    用户业务 模块     8084       
client 测试注册中心模块（暂时无用）  
# 二、集成sleuth和zipkin步骤  
1.添加依赖  
2.添加yml  

## 测试zipkin
1、启动eureka、config、order、product  
2、url：http://localhost:8081/order/create  
body：
```shell script
name:张三
phone:18868822111
address:北京西二旗
openid:ew3euwhd7sjw9diwkq
items:[{productId:"157875196366160022",productQuantity:2}]
```
