# 1. hystrixcommand使用

1. HystrixCommand 单值 同步 异步
2. HystrixObservableCommand 多行值 同步 异步

rxjava 异步响应式编程

# 2. 资源隔离策略

**线程池**

​	有超时时间限制（适合网络请求）

​	线程池执行command，将结果tomcat线程。通过线程池的核心线程数、等待队列、拒绝策略，以及fallback方法，保护tomcat线程。

**信号量**

​	无超时时间限制（适合执行本地复杂的算法，本地算法不能死循环，不然无法释放当前tomcat线程，其他tomcat线程也无法进入）

​	通过信号量放行tomcat线程执行command，其他线程走fallback。

ThreadPoolKey==GroupKey 某服务
    CommandKey  某服务下的接口

# 3. request cache

一次请求内的缓存，id=1,3,5,3，只有最后一个3是从request cache中拿的，下次再请求id=1,3,5，都是重新获取的。

允许在一次请求中清空缓存

# 4. fallback

fallback可以使用信号量进行保护，超过信号量和队列直接reject

# 5. circuit breaker

requestVolumeThreshold(30) //10秒内的请求滑动窗口
errorThresholdPercentage(40) //异常请求比例（默认50）
sleepWindowInMilliseconds(3000) //断路器开启后的睡眠窗口

断路器状态：close、half-open、open

# 6. 总结

1、hystrix内部工作原理：8大执行步骤和流程
2、资源隔离：你如果有很多个依赖服务，高可用性，先做资源隔离，任何一个依赖服务的故障不会导致你的服务的资源耗尽，不会崩溃
3、请求缓存：对于一个request context内的多个相同command，使用request cache，提升性能
4、熔断：基于短路器，采集各种异常事件，报错，超时，reject，短路，熔断，一定时间范围内就不允许访问了，直接降级，自动恢复的机制
5、降级：报错，超时，reject，熔断，降级，服务提供容错的机制
6、限流：在你的服务里面，通过线程池，或者信号量，限制对某个后端的服务或资源的访问量，避免从你的服务这里过去太多的流量，打死某个资源
7、超时：避免某个依赖服务性能过差，导致大量的线程hang住去调用那个服务，会导致你的服务本身性能也比较差