package cn.lastwhisper.concurrent.juc.executor;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 一、线程池体系
 *  java.util.concurrent.Executor接口: 负责线程的使用与调度的根接口
 *          |--**.ExecutorService子接口: 线程池的主要接口
 *              |--**AbstractExecutorService抽象类
 *                  |--**ThreadPoolExecutor线程池的实现类
 *                  |--**ForkJoinPool Fork/Join任务窃取实现类
 *              |--**ScheduledExecutorService子接口: 负责线程的调度
 *                  |--**ScheduledThreadPoolExecutor 继承 ThreadPoolExecutor,实现 ScheduledExecutorService
 * 二、线程池创建工具类
 *  java.util.concurrent.Executors主要用于创建线程池
 *      ExecutorService newFixedThreadPool() : 创建固定大小的线程池
 *      ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 *      ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程
 *      ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。
 * 三、线程池原理
 *  java.util.concurrent.ThreadPoolExecutor
 *      1.构造函数：ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
 *            BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler)
 *        参数：
 *           corePoolSize：指定线程池中的线程数量
 *           maximumPoolSize：指定了线程池中的最大线程数量
 *           keepAliveTime：超过corePoolSize的空闲线程，在多长时间内会被销毁
 *           unit：keepAliveTime的单位
 *           workQueue：任务队列，被提交但尚未执行的任务
 *              SynchronousQueue：直接提交的队列
 *              ArrayBlockingQueue：有界的任务队列
 *              LinkedBlockingQueue：无界的任务队列
 *              PriorityBlockingQueue：优先任务队列
 *           threadFactory：线程工厂，用于创建线程，一般用默认即可
 *           handler：拒绝策略。任务太多来不及处理，如何拒绝任务
 *              AbortPolicy：直接抛出异常，阻止系统正常工作
 *              CallerRunsPolicy：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务
 *              DiscardOldestPolicy：丢弃最老的一个请求
 *              DiscardPolicy：丢弃无法处理的任务
 * @author lastwhisper
 */
public class TestThreadPool {
    // 计划任务线程池
    static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    // 普通的线程池
    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        TestThreadPool test = new TestThreadPool();
        //测试普通的线程池
        test.testFixedThreadPool();
        //测试计划任务线程池
        //test.testSchedule();
        //test.testScheduleAtFixedRate();
        //test.testScheduleWithFixedDelay();
    }

    private void testFixedThreadPool() throws Exception {
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = fixedThreadPool.submit(new MyTask(), new Integer(0));
            System.out.println(future.get());
        }
        fixedThreadPool.shutdown();
    }

    /**
     * 1.scheduleWithFixedDelay
     *
     * 2.构造器：
     *  scheduleWithFixedDelay(Runnable command,long initialDelay,long delay,TimeUnit unit);
     *      command任务体;
     *      initialDelay初始延时时间;
     *      period:等待时间;
     *      unit:时间单位;
     * 3.testScheduleWithFixedDelay不会等待执行体内的执行时间
     */
    private void testScheduleWithFixedDelay() throws Exception {
        scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(8000);
                    System.out.println(System.currentTimeMillis() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    /**
     * 1.scheduleAtFixedRate
     *  创建一个周期性任务，任务开始于给定的初始化延时。
     *  后续第一个任务会在initialDelay+1*period时执行，第二个任务会在initialDelay+2*period时执行，以此类推。
     * 2.构造器：
     *  scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit);
     *      command任务体;
     *      initialDelay初始延时时间;
     *      period:等待时间;
     *      unit:时间单位;
     * 3.scheduleAtFixedRate会等待执行体内的执行时间
     */
    private void testScheduleAtFixedRate() throws Exception {
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(8000);
                    System.out.println(System.currentTimeMillis() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    /**
     * 1.schedule:给定时间,对任务进行一次调度
     * 2.构造器：
     *  schedule(Callable<V> callable,long delay, TimeUnit unit)
     *  schedule(Runnable command,long delay, TimeUnit unit)
     *  参数意义：
     *      callable、command任务体;
     *      delay延时时间;
     *      unit:时间单位
     */
    private void testSchedule() throws ExecutionException, InterruptedException {
        // 给定时间,对任务进行一次调度
        Future<Integer> result = scheduledThreadPool.schedule(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int num = new Random().nextInt(100);//生成随机数
                System.out.println(Thread.currentThread().getName() + " : " + num);
                return num;
            }
        }, 1, TimeUnit.SECONDS);
        System.out.println("返回的计算结果：" + result.get());
        scheduledThreadPool.shutdown();
    }
}
