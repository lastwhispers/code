package cn.lastwhisper.cache.ha.hystrix.command;

import cn.lastwhisper.cache.ha.local.LocationCache;
import com.netflix.hystrix.*;

/**
 * 获取城市名称的command
 * @author lastwhisper
 * @date 2019/12/19
 */
public class GetCityNameCommand extends HystrixCommand<String> {

    private Long cityId;

    /**
     * to use thread isolation
     * HystrixCommandProperties.Setter()
     *    .withExecutionIsolationStrategy(ExecutionIsolationStrategy.THREAD)
     * to use semaphore isolation
     * HystrixCommandProperties.Setter()
     *    .withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)
     * 通过信号量隔离进行限流
     */
    public GetCityNameCommand(Long cityId){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetCityNameCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetCityNamePool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(15)));
        this.cityId = cityId;
    }

    @Override
    protected String run() throws Exception {
        return LocationCache.getCityName(cityId);
    }

}
