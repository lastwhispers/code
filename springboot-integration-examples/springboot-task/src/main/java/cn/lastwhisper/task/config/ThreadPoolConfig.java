package cn.lastwhisper.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 * @author lastwhisper
 * @date 2020/5/23
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("myThreadPool")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(1);
        threadPool.setMaxPoolSize(1);
        threadPool.setKeepAliveSeconds(30);
        threadPool.setQueueCapacity(1000);
        threadPool.setAllowCoreThreadTimeOut(true);
        threadPool.setAwaitTerminationSeconds(10);
        // 丢弃任务，不抛出异常
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        threadPool.initialize();
        return threadPool;
    }




}
