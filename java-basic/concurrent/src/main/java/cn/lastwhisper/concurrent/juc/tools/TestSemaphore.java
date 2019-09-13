package cn.lastwhisper.concurrent.juc.tools;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 信号量：限定某几个线程访问同一资源
 * @author lastwhisper
 */
public class TestSemaphore {
    public static void main(String[] args) {
        // 3个车位
        Semaphore semaphore = new Semaphore(3);
        // 7辆车
        for (int i = 0; i < 7; i++) {
            SemaphoreDemo semaphoreDemo = new SemaphoreDemo(i, semaphore);
            new Thread(semaphoreDemo).start();
        }
    }
}

class SemaphoreDemo implements Runnable {
    private int num;
    private Semaphore semaphore;

    public SemaphoreDemo(int num, Semaphore semaphore) {
        this.num = num;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(num + " 号车辆,进入停车场,占用一个停车位");
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(num + " 号车辆,离开停车场");
        }
    }
}