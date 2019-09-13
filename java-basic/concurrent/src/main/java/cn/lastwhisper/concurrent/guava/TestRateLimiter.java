package cn.lastwhisper.concurrent.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Guava的RateLimiter限流基于令牌桶算法
 * @author lastwhisper
 */
public class TestRateLimiter {
    private static RateLimiter limiter = RateLimiter.create(3);

    public static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            limiter.acquire();
            new Thread(new Task()).start();
        }
    }
}
