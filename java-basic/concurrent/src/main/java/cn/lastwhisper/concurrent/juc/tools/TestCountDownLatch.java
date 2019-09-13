package cn.lastwhisper.concurrent.juc.tools;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁是一种同步方法，可以延迟线程的进度直到其到达终止状态。
 * 闭锁可以用来确保某些活动直到其他活动都完成才继续执行：
 * @author lastwhisper
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        CountDownLatchDemo cdl = new CountDownLatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(cdl).start();
        }
        try {
            //等待所有线程执行完毕
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }
}

class CountDownLatchDemo implements Runnable {
    private CountDownLatch latch;

    public CountDownLatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 1000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                //线程完成后，进行减一
                latch.countDown();
            }

        }
    }
}