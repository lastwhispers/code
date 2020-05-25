package cn.lastwhisper.learn8.util.concurrent.queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingQueue
 * @author lastwhisper
 * @date 2020/4/17
 */
@Slf4j
public class LinkedBlockingQueueTest {

    LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);

    // 抛异常
    @Test
    public void testThrowException() {
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        try {
            System.out.println(blockingQueue.add("x"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        try {
            //当阻塞队列空时,再往队列Remove元素时候回抛出NoSuchElementException
            System.out.println(blockingQueue.remove());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 特殊值
    @Test
    public void testReturnBool() {
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // 插入方法,成功返回true 失败返回false
        System.out.println(blockingQueue.offer("x"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //移除方法,成功返回元素,队列里面没有就返回null
        System.out.println(blockingQueue.poll());
    }

    // 一直阻塞
    @Test
    public void testBlock() throws InterruptedException {
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // 当阻塞队列满时,生产者继续往队列里面put元素,队列会一直阻塞直到put数据or响应中断退出
        blockingQueue.offer("x");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //当阻塞队列空时,消费者试图从队列take元素,队列会一直阻塞消费者线程直到队列可用.
        System.out.println(blockingQueue.take());
    }

    // 超时退出
    @Test
    public void testIncorruptible() throws InterruptedException {
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        System.out.println(blockingQueue.size());
        // 当阻塞队列满时,队列会阻塞生产者线程一定时间,超过后限时后生产者线程就会退出
        blockingQueue.offer("x", 2L, TimeUnit.SECONDS);

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.size());
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
    }


}
