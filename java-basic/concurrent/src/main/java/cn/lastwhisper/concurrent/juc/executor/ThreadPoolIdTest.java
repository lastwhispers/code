package cn.lastwhisper.concurrent.juc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程池中的线程id
 * @author lastwhisper
 * @date 2020/2/28
 */
public class ThreadPoolIdTest {

    public static void main(String[] args) {
        // 线程池会复用线程，线程id会重复出现
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            pool.submit(() -> {
                System.err.println(Thread.currentThread().getId());
            });
        }

        pool.shutdown();
    }

}
