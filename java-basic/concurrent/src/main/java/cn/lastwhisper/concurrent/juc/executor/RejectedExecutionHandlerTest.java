package cn.lastwhisper.concurrent.juc.executor;

import java.util.concurrent.*;

public class RejectedExecutionHandlerTest {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                //1、AbortPolicy()策略，当前threadNum>maximumPoolSize+capacity+1会异常
                //new ThreadPoolExecutor.AbortPolicy()
                //2、CallerRunsPolicy()策略，threadNum>maximumPoolSize+capacity+1任务将由调用者自己执行
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //3、DiscardOldestPolicy()策略，抛弃队列中等待最久的任务,然后把当前任务加入队列中尝试再次提交
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                //4、DiscardPolicy()策略直接丢弃任务,不予任何处理也不抛出异常
//                new ThreadPoolExecutor.DiscardPolicy()
                // 5、自定义策略
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + " is discard");
                    }
                }
        );
        //模拟10个用户来办理业务 每个用户就是来自外部的请求线程.
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
    }

}
 
