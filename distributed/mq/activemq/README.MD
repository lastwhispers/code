ActiveMQ是一种开源的基于JMS（Java Message Servie）规范的一种消息中间件的实现。

ActiveMQ的两种消息传递类型

![img](https://img2018.cnblogs.com/blog/1468250/201907/1468250-20190720132250768-792851447.png)

（1）点对点传输，即一个生产者对应一个消费者，生产者向broke推送数据，数据存储在broke的一个队列中，当消费者接受该条队列里的数据。

（2）基于发布/订阅模式的传输，即根据订阅话题来接收相应数据，一个生产者可向多个消费者推送数据





参考：  
https://blog.csdn.net/qq_16313365/article/details/78543166  
https://blog.csdn.net/qq_16313365/article/details/79850055  

纯demo https://www.cnblogs.com/xiguadadage/p/11217604.html



