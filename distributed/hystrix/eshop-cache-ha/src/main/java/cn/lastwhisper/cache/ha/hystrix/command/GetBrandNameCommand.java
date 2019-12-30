package cn.lastwhisper.cache.ha.hystrix.command;

import cn.lastwhisper.cache.ha.local.BrandCache;
import com.netflix.hystrix.*;

/**
 * 获取品牌名称的command
 * @author lastwhisper
 * @date 2019/12/20
 */
public class GetBrandNameCommand extends HystrixCommand<String> {

    private Long brandId;

    public GetBrandNameCommand(Long brandId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetBrandNameGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetBrandNameCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetBrandNamePool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(15)
                        .withQueueSizeRejectionThreshold(10))
                // 设置HystrixCommand.getFallback()最大允许的并发请求数量，默认值是10，
                // 也是通过semaphore信号量的机制去限流，如果超出了这个最大值，那么直接被reject
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(15))
        );
        this.brandId = brandId;
    }


    @Override
    protected String run() throws Exception {
        // 调用一个品牌服务的接口
        // 如果调用失败了，报错了，那么就会去调用fallback降级机制
        throw new Exception();
    }

    // 降级方法
    @Override
    protected String getFallback() {
        System.out.println("从本地缓存获取过期的品牌数据，brandId=" + brandId);
        return BrandCache.getBrandName(brandId);
    }
}
