package cn.lastwhisper.concurrent.juc.blockingqueue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *
 * @author lastwhisper
 */
public class ArrayBlockingQueueDemo {
    ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

    // 超时退出
    @Test
    public void test4() throws InterruptedException {
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        // 当阻塞队列满时,队列会阻塞生产者线程一定时间,超过后限时后生产者线程就会退出
        blockingQueue.offer("x", 2L, TimeUnit.SECONDS);

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
    }

    // 一直阻塞
    @Test
    public void test3() throws InterruptedException {
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

    // 特殊值
    @Test
    public void test2() {
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

    // 抛异常
    @Test
    public void test1() {
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //当阻塞队列满时,再往队列里面add插入元素会抛IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("x"));

        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //当阻塞队列空时,再往队列Remove元素时候回抛出NoSuchElementException
        //System.out.println(blockingQueue.remove());
    }


}
