package cn.lastwhisper.learn8.util.concurrent.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue
 * @author lastwhisper
 * @date 2020/4/17
 */
@Slf4j
public class SynchronousQueueTest {

    static class Product implements Runnable {

        private final BlockingQueue queue;

        public Product(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                log.info("begin put");
                queue.put("nihao");
                log.info("end put");
            } catch (InterruptedException e) {
            }
        }

    }

    static class Consumer implements Runnable {

        private final BlockingQueue queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                log.info("Consumer begin");
                String name = (String) queue.take();
                log.info("Consumer end :{}", name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue q = new SynchronousQueue(true);
        Product p = new Product(q);
        Consumer c = new Consumer(q);
        new Thread(c).start();
        log.info("sleeping");
        Thread.sleep(5000L);
        log.info("sleepEnd");
        new Thread(p).start();
    }


}
