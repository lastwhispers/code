package cn.lastwhisper.concurrent.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * @author lastwhisper
 */
public class SpinLockDemo {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 上锁
    public void lock() {
        Thread thread = Thread.currentThread();
        // 自旋
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    // 解锁
    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLock = new SpinLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                System.out.println(Thread.currentThread().getName()+"\t 获取锁");
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 搞事情");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName()+"\t 释放锁");
                }
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new Runnable() {
            @Override
            public void run() {

                spinLock.lock();
                System.out.println(Thread.currentThread().getName()+"\t 获取锁");
                System.out.println(Thread.currentThread().getName()+"\t 搞事情");
                spinLock.unlock();
                System.out.println(Thread.currentThread().getName()+"\t 释放锁");
            }
        }, "t2").start();
    }
}
