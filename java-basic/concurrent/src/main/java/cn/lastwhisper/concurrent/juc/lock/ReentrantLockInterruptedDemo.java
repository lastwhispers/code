package cn.lastwhisper.concurrent.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lastwhisper
 */
public class ReentrantLockInterruptedDemo {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 come in");
                try {
                    TimeUnit.MINUTES.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }, "A");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("t2 come in");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("t2线程被人中断");
            } finally {
                lock.unlock();
            }
        }, "B");
        t2.start();

        Thread.sleep(1000);
        t2.interrupt();
        Thread.sleep(1000);
    }
}
