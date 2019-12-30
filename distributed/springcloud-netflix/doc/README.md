一、项目端口划分  
eureka 分布式注册中心模块  8761  
config 分布式配置中心模块  8082  
api-gateway zuul网关模块  8083  
order   订单业务模块      8081             
product 产品业务模块      8085                     
user    用户业务 模块     8084       
client 测试注册中心模块（暂时无用）  
二、集成sleuth和zipkin步骤  
1.添加依赖  
2.添加yml  
