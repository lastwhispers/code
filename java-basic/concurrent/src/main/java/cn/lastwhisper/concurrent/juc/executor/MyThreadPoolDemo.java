package cn.lastwhisper.concurrent.juc.executor;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                //threadNum>maximumPoolSize+capacity+1会异常
                //new ThreadPoolExecutor.AbortPolicy()
                //threadNum>maximumPoolSize+capacity+1将任务回退到调用者
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //抛弃队列中等待最久的任务,然后把当前任务加入队列中尝试再次提交
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                //直接丢弃任务,不予任何处理也不抛出异常
                new ThreadPoolExecutor.DiscardPolicy()
        );
        //模拟10个用户来办理业务 没有用户就是来自外部的请求线程.
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        //threadPoolInit();
    }

    private static void threadPoolInit() {
        /*
         * 一池5个处理线程
         */
        //ExecutorService threadPool= Executors.newFixedThreadPool(5);
        /*
         * 一池一线程
         */
        //ExecutorService threadPool= Executors.newSingleThreadExecutor();
        /*
         * 一池N线程
         */
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //模拟10个用户来办理业务 没有用户就是来自外部的请求线程.
        try {
            for (int i = 1; i <= 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                try {
                    TimeUnit.MICROSECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
 
