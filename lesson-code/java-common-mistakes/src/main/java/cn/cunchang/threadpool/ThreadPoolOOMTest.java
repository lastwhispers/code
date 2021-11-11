package cn.cunchang.threadpool;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 线程池oom
 *
 * @author cunchang
 * @date 2021/7/13 1:49 上午
 */
@Slf4j
public class ThreadPoolOOMTest {

    public static void main(String[] args) throws InterruptedException {
        //  Executors.newFixedThreadPool，固定线程，队列无限大，导致oom
//        newFixedThreadPool_oom();

        //  Executors.newCachedThreadPool，无限创建线程，导致oom
//        newCachedThreadPool_oom();

    }

    /**
     * -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
     * <p>
     * 任务较多并且执行较慢的话，队列可能会快速积压，导致oom
     *
     * @throws InterruptedException
     */
    public static void newFixedThreadPool_oom() throws InterruptedException {

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        printStats(threadPool);
        for (int i = 0; i < 100000000; i++) {
            threadPool.execute(() -> {
                String payload = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                }
                log.info(payload);
            });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    /**
     * @throws InterruptedException
     */
    public static void newCachedThreadPool_oom() throws InterruptedException {

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        printStats(threadPool);
        for (int i = 0; i < 100000000; i++) {
            threadPool.execute(() -> {
                String payload = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                }
                log.info(payload);
            });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }


    private static void printStats(ThreadPoolExecutor threadPool) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("=========================");
            log.info("Pool Size: {}", threadPool.getPoolSize());
            log.info("Active Threads: {}", threadPool.getActiveCount());
            log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());

            log.info("=========================");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
