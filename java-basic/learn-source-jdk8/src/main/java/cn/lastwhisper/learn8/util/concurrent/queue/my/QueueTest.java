package cn.lastwhisper.learn8.util.concurrent.queue.my;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author lastwhisper
 * @date 2020/4/18
 */
public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        Queue<String> blockingQueue = new LinkedBlockingQueue<>(3);
        System.out.println("/*----------------capacity=3---------------*/");
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("thread name:" + Thread.currentThread().getName() +
                "，put a、b、c三个元素");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread name:" + Thread.currentThread().getName() +
                            "，5秒后执行take操作");
                    TimeUnit.SECONDS.sleep(5);
                    blockingQueue.take();
                    System.out.println("thread name:" + Thread.currentThread().getName() +
                            "，take操作执行，take a，并唤醒put操作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("thread name:" + Thread.currentThread().getName()
                + "，开始执行put操作，队满一直阻塞");
        blockingQueue.put("d");
        System.out.println("thread name:" + Thread.currentThread().getName()
                + "，put操作被唤醒，put d");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        System.out.println("thread name:" + Thread.currentThread().getName()
                + "，take操作执行，take b c d");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread name:" + Thread.currentThread().getName() +
                            "，5秒后执行put操作");
                    TimeUnit.SECONDS.sleep(5);
                    blockingQueue.put("d");
                    System.out.println("thread name:" + Thread.currentThread().getName() +
                            "，put操作执行，put d，并唤醒take操作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("thread name:" + Thread.currentThread().getName()
                + "，开始执行take操作，队空一直阻塞");
        blockingQueue.take();
        System.out.println("thread name:" + Thread.currentThread().getName()
                + "，take操作被唤醒，take d");

    }


}
