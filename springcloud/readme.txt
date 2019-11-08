1、为什么需要DTO(Data Transfer Object)，以OrderDTO为例。
由于接口的入参涉及到OrderDetail、OrderMaster，而OrderMaster与OrderDetail可能有一对多的关系，
所以需要一个DTO封装OrderDetail与OrderMaster的数据，作为Controller与Service的中转。
2、为什么需要VO？
（1）实体类与页面需要的数据很大程度不一致，需要封装VO数据进行返回
（2）安全性
3、为什么需要form？
接收接口入参，from转dto做不同级别的参数校验。
参数校验级别分为：
    (1)from入参json串不为空
    (2)from转dto时
4、为什么需要enum？
将一些表中的状态，比如订单（支付、未支付、等待支付）状态进行枚举表示，统一管理

https://www.cnblogs.com/qixuejia/p/4390086.html


一、项目：领域模型、vo、dto、po、domain、form、enums、utils
二、测试
三、日志：log4j、sfl4j、logback等日志区别，以及作用
四、valid、json转换的使用
五、常量，枚举的使用场景
