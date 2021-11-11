package cn.cunchang.threadpool;

import java.util.concurrent.*;

/**
 * @description 自定义激进线程池，在核心线程数满了后，新的任务来了，直接创建线程，直到达到最大线程数，此时再新来任务，此时再加入阻塞队列
 * @author: darren
 * @data: 2020-03-12 10:19
 */
public class ExtremeThreadPoolExecutor extends ThreadPoolExecutor {

    /**
     * 构造方法
     * @param corePoolSize 核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime 非核心线程数保留时长
     * @param unit 非核心线程数保留时长单位
     * @param blockQueueSize 阻塞队列长度
     */
    public ExtremeThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, int blockQueueSize) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new ExtremeBlockQueue(blockQueueSize), Executors.defaultThreadFactory(), new ExtremePolicy());

    }

    /**
     * 自定义阻塞队列
     * @param <Runnable>
     */
    static class ExtremeBlockQueue<Runnable> extends LinkedBlockingQueue<Runnable> {
        public ExtremeBlockQueue(int capacity) {
            super(capacity);
        }

        /**
         * 覆盖默认的offer方法，触发拒绝策略执行
         * @param runnable
         * @return
         */
        @Override
        public boolean offer(Runnable runnable) {
            return false;
        }

        /**
         * 拒绝策略触发后，真正的保存进阻塞队列
         * @param runnable
         * @return
         */
        public boolean extremeOffer(Runnable runnable) {
            return super.offer(runnable);
        }
    }

    /**
     * 自定义拒绝策略
     */
    static class ExtremePolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            //线程池非关闭
            if (!e.isShutdown()) {
                //真正入阻塞队列，若阻塞队列已满，则抛出RejectedExecutionException
                if (!((ExtremeBlockQueue)e.getQueue()).extremeOffer(r)) {
                    //可以真正进行触发策略的执行（不管是默认的，还是自定义的）
                    throw new RejectedExecutionException("Task " + r.toString() +
                            " rejected from " +
                            e.toString());
                }
            }
        }
    }
}