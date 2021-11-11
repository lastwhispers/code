package cn.cunchang.threadpool;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 *
 * @author cunchang
 * @date 2021/7/14 6:34 下午
 */
@Slf4j
public class ThreadPoolExpansiveTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("总条数：20,成功条数："+right());;
    }

    /**
     * 线程池最大线程扩容太晚，导致任务丢弃
     *
     * @return
     * @throws InterruptedException
     */
    public static int right() throws InterruptedException {
        //使用一个计数器跟踪完成的任务数
        AtomicInteger atomicInteger = new AtomicInteger();
        //创建一个具有2个核心线程、5个最大线程，使用容量为10的ArrayBlockingQueue阻塞队列作为工
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2, 5,
                5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").get(),
                new ThreadPoolExecutor.AbortPolicy());
        //threadPool.allowCoreThreadTimeOut(true);
//        printStats(threadPool);
        //每隔1秒提交一次，一共提交20次任务
        IntStream.rangeClosed(1, 20).forEach(i -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int id = atomicInteger.incrementAndGet();
            try {

                threadPool.submit(() -> {
                    log.info("{} started", id);
                    //每个任务耗时10秒
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    log.info("{} finished", id);
                });
            } catch (Exception ex) {
                //提交出现异常的话，打印出错信息并为计数器减一
                log.error("error submitting task {}", id, ex);
                atomicInteger.decrementAndGet();
            }
        });

        TimeUnit.SECONDS.sleep(60);
        return atomicInteger.intValue();
    }


}
