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
