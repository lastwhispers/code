│	  
├─quickstart 快速入门  
│  
├─api rabbitmq client api学习  
│	│  
│    ├─exchange 三种交换器  
│    │   
│    ├─message 消息  
│    │  
│    ├─confirm 生产者确认消息机制  
│    │   
│    ├─returnlistener 生产者消息不可达机制  
│    │  
│    ├─consumer 自定消费者  
│    │   
│    ├─limit 消费者限流  
│    │  
│    ├─ack 消费者ack、nack和重回队列机制  
│    │  
│    └─dlx 死信队列  
│   
└─

# 1. quickstart
快速入门项目
# 2. 生产端confirm机制
## 2.1 Confirm消息确认机制的概念
指生产者投递消息后，如果Broker收到消息，则会给生产者一个应答。
生产者进行接收应答，用来确认这条消息是否正常发送到Broker。
是消息可靠性投递的核心保障。

## 2.2 确认机制的流程图

![在这里插入图片描述](https://img-blog.csdnimg.cn/20181231233125448.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTIyMTE2MDM=,size_16,color_FFFFFF,t_70)

发送消息与监听应答的消息是异步操作。

## 2.3 开启confirm核心代码

在channel开启确认模式：`channel.confirmSelect();`
在channel添加监听：`channel.addConfirmListener(ConfirmListener listener);` 返回监听成功和失败的结果，对具体结果进行相应的处理（重新发送、记录日志等待后续处理等）

# 3. 生产端Return机制

用于处理一些不可路由的消息。

## 3.1 return消息机制的流程图

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190101002146316.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTIyMTE2MDM=,size_16,color_FFFFFF,t_70)

消息的生产者通过制定Exchange和RoutingKey，把消息投递到某一个队列中，消费者监听队列，进行消费。

但在一些情况下，发送消息时，Exchange不存在或RoutingKey路由不到，Return Listener就会监听这种不可达的消息，然后进行处理。

## 3.2 开启return核心代码

关键配置项：`Mandatory`：true，监听器会接收到路由不可达的消息，然后进行处理；false，Broker会自动删除该消息。默认是false。

添加ReturnListener

```java
channel.addReturnListener(new ReturnListener());
```

# 4. 消费端限流

## 4.1 消费端限流的概念

当巨量消息瞬间全部推送时，单个客户端无法同时处理这些数据，服务器容易故障。因此要进行消费端限流

RabbitMQ提供了一种**Qos（服务质量保证）功能**，消费端在**非自动确认**前提下，如果一定数目的消息未被确认前（通过consume或者channel设置Qos值），不进行消费新消息。

## 4.2 开启限流核心代码

```java
DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 手动ack签收
                channel.basicAck(envelope.getDeliveryTag(), false); // 不批量签收
            }
        };
/*
* prefetchSize：消息限制大小，一般为0，不做限制。
* prefetchCount：一次处理消息的个数，一般设置为1
* global：一般为false。true，在channel级别做限制；false，在consumer级别做限制
*/
channel.basicQos(0, 1, false);
//限流:autoAck需设置为false, 关闭自动签收
channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
```

消费端必须手工签收，不然无法消费消息

# 5. 消费端ack与重回队列机制

消费端手工签收消息时，消费 成功后，进行ack确认。由于业务异常，会调用NACK拒绝确认，在进行NACK时可以将消息重回队列，进行重试。

一般在实际应用中，会关闭重回队列。

# 6. TTL队列

TTL：Time To Live，生存时间。
可以指定消息的过期时间。
可以指定队列的过期时间，从消息入队列开始计算，超过了队列的超时时间设置，那么消息会自动清除。

# 7. 死信队列

DLX：Dead-Letter-Exchange
当消息在队列中变成死信时，能被重新publish到另一个Exchange，该Exchange就是DLX。

死信队列产生的情况：

1. 消息被拒绝（`basic.reject/ basic.nack`）并且`requeue=false`（没有重回队列）

2. 消息TTL过期

3. 队列达到最大长度

死信队列的设置：

1. 正常声明交换机，队列并绑定，需要在队列上设置一个参数：arguments.put("x-dead-letter-exchange", "dlx.exchange");
2. 声明死信队列的Exchange和Queue，然后进行绑定：
   Exchange: dlx.exchange
   Queue: dlx.queue
   RoutingKey: #
3. 在消息过期、requeue、队列达到最大长度时（即为死信），消息会发送到指定的dlx.exchange交换机上，消费者会监听该交换机所绑定的死信队列。