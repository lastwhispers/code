
- quickstart 快速入门，生产者发送消息到 broker，broker push 消息到消费者
- product.sync 生产者发送同步消息
- product.async 生产者发送异步消息
- product.oneway 生产者发送单向消息
- consumer.pull consumer pull broker
- consumer.push broker push consumer
- consumer.broadcast 消费者广播模式消费
- consumer.retry 消费者重试消息
- ordermsg 生产者发送有序消息  
  - 消息消费时能按照发送的顺序来消费。例如：一个订单产生了三条消息分别是订单创建、订单付款、订单完成。消费时要按照这个顺序消费才能有意义
- schedulemsg 生产者发送延时消息  
  - 消息消费时能按照发送时设置的延长的时间来消费。例如：电商里，提交了一个订单就可以发送一个延时消息，1h后去检查这个订单的状态，如果还是未付款就取消订单释放库存。  
  - rocketmq 的延时消息只能按照18个 level 进行延时，不能指定时间
- batch 发送批量消息
- filter broker 端进行的消息过滤
- transaction 事务消息
  - 事务消息共有三种状态，提交状态、回滚状态、中间状态
    - TransactionStatus.CommitTransaction: 提交事务，它允许消费者消费此消息。 
    - TransactionStatus.RollbackTransaction: 回滚事务，它代表该消息将被删除，不允许被消费。
    - TransactionStatus.Unknown: 中间状态，它代表需要检查消息队列来确定状态。
  - 








