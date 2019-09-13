package cn.lastwhisper.concurrent.juc.executor;

import java.util.concurrent.*;

/**
 * 自定义线程池的拒绝策略
 * @author lastwhisper
 */
public class TestRejectedExecutionHandler {
    public static class MyTask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+":Thread ID:"+Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        ExecutorService pool = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString()+" is discard");
            }
        });
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            pool.submit(myTask);
            Thread.sleep(10);
        }
    }
}
