package cn.lastwhisper.learn8.util.concurrent.queue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue
 * @author lastwhisper
 * @date 2020/4/17
 */
@Slf4j
public class DelayQueueTest {

    static class Product implements Runnable {

        private final BlockingQueue<? super DelayedDTO> queue;

        public Product(BlockingQueue<? super DelayedDTO> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                log.info("begin put");
                long beginTime = System.currentTimeMillis();
                queue.put(new DelayedDTO(System.currentTimeMillis() + 2000L, beginTime));//延迟 2 秒执行
                queue.put(new DelayedDTO(System.currentTimeMillis() + 5000L, beginTime));//延迟 5 秒执行
                queue.put(new DelayedDTO(System.currentTimeMillis() + 1000L * 10, beginTime));//延迟 10 秒执行
                log.info("end put");
            } catch (InterruptedException e) {
                log.error("" + e);
            }
        }
    }

    static class Consumer implements Runnable {

        private final BlockingQueue<DelayedDTO> queue;

        public Consumer(BlockingQueue<DelayedDTO> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                log.info("Consumer begin");
                queue.take().run();
                queue.take().run();
                queue.take().run();
                log.info("Consumer end");
            } catch (InterruptedException e) {
                log.error("" + e);
            }
        }
    }

    @Data
    static class DelayedDTO implements Delayed {
        Long s;
        Long beginTime;

        public DelayedDTO(Long s, Long beginTime) {
            this.s = s;
            this.beginTime = beginTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(s - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        public void run() {
            log.info("延迟了{}秒钟才执行", (System.currentTimeMillis() - beginTime) / 1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<DelayedDTO> q = new DelayQueue<>();
        DelayQueueTest.Product p = new DelayQueueTest.Product(q);
        DelayQueueTest.Consumer c = new DelayQueueTest.Consumer(q);
        new Thread(c).start();
        new Thread(p).start();

    }

}
