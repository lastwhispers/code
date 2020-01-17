package cn.lastwhisper.cache.ha.hystrix.command;

import cn.lastwhisper.cache.ha.http.HttpClientUtils;
import cn.lastwhisper.cache.ha.model.ProductInfo;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

/**
 * 获取单条商品信息的command
 * @author lastwhisper
 * @date 2019/12/19
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("GetProductInfoCommand");
    private Long productId;
    /*
     * @HystrixCommand(
            fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),//超时降级
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//同意使用断路器来熔断请求
                    //timeInMilliseconds/numBuckets内,最小请求数
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    //断路器打开后的休眠时间窗口,休眠结束后,断路器进入"半打开"状态

                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                    //断路器打开的错误比条件(在timeInMilliseconds内,请求超过requestVolumeThreshold前提下,错误比超60,开启断路器)
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            },
            threadPoolProperties = {
                    // 线程池参数
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                    //滚动时间窗口长度,被分成多个numBuckets,该参数需要被numBuckets整除
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12")
            })
     *
     */

    /**
     * 首先线程池10-20不代表并发能力10-20，如果一个接口的tp99为50ms，1个线程1s可以处理20请求，
     *  10个线程就可以处理200请求每秒。10个实例就是tps为2000。
     * 所以具体配置如何取决于接口性能和设置的超时时间等因素。
     */
    public GetProductInfoCommand(Long productId) {
        /*
         * ThreadPoolKey==GroupKey 某服务
         *      CommandKey  某服务下的接口
         */
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup"))
                .andCommandKey(GETTER_KEY)
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetProductInfoPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                                .withCoreSize(10) //核心线程数
                                .withMaxQueueSize(8) //最大等待队列
                        //.withQueueSizeRejectionThreshold(15)//等待队列大小，
                )
                // 如果withMaxQueueSize<withQueueSizeRejectionThreshold，那么取的是withMaxQueueSize，反之，取得是withQueueSizeRejectionThreshold
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(true) //启动断路器（默认true）
                        .withCircuitBreakerRequestVolumeThreshold(30) //10秒内的请求滑动窗口
                        .withCircuitBreakerErrorThresholdPercentage(40) //异常请求比例（默认50）
                        .withCircuitBreakerSleepWindowInMilliseconds(3000) //断路器开启后的睡眠窗口
                        .withExecutionTimeoutInMilliseconds(20000) //超时降级时间
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(30)//信号量隔离fallback函数
                )
        );
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        System.out.println("调用GetProductInfoCommand，查询商品数据，productId=" + productId);

        if (productId.equals(-1L)) {
            throw new Exception();
        }

        if (productId.equals(-2L)) {
            Thread.sleep(3000);
        }

        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        return JSONObject.parseObject(response, ProductInfo.class);
    }

    // 降级方法
    @Override
    protected ProductInfo getFallback() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setName("降级商品");
        return productInfo;
    }

    // 测试request cache开启
    //@Override
    //protected String getCacheKey() {
    //    return "product_info_" + productId;
    //}

    /**
     * Allow the cache to be flushed for this object.
     * @param id
     *     argument that would normally be passed to the command
     */
    public static void flushCache(Long id) {
        HystrixRequestCache.getInstance(GETTER_KEY,
                HystrixConcurrencyStrategyDefault.getInstance()).clear(Long.toString(id));
    }
}
