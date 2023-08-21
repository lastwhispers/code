package cn.cunchang.guava.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Guava的RateLimiter限流基于令牌桶算法
 * @author lastwhisper
 */
public class TestRateLimiter {

    /**
     * 单机全局限流器(限制QPS为1)（放入拦截器，对HTTP接口限流）
     */
    private static RateLimiter limiter = RateLimiter.create(1.0);

    /**
     * 固定10个线程，模拟十个用户并发
     */
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    static class Task implements Runnable {
        @Override
        public void run() {
            limiter.acquire(); // 对应web项目，就是在业务代码之前执行（拦截器、AOP）
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            pool.submit(new Task());
        }
    }


}
