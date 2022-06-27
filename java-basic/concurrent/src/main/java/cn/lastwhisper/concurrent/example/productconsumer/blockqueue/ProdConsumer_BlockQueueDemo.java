package cn.lastwhisper.concurrent.example.productconsumer.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用阻塞队列
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 * @author lastwhisper
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        Resource resource = new Resource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println("生产线程启动");
            try {
                resource.product();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "product").start();

        new Thread(() -> {
            System.out.println("消费线程启动");
            try {
                resource.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "consumer").start();
        //休眠五秒
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.stop();
    }
}

class Resource {

    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<Integer> blockingQueue;

    public Resource(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void product() throws InterruptedException {
        Integer data;
        boolean result;
        while (FLAG) {
            data = atomicInteger.incrementAndGet();
            result = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列data:" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列data:" + data + "失败");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumer() throws InterruptedException {
        Integer val;
        while (FLAG) {
            val = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (val == null) {
                System.out.println(Thread.currentThread().getName() + "\t 超过两秒没收到消息");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列value：" + val + "成功");
        }
    }

    public void stop() {
        this.FLAG = false;
    }
}