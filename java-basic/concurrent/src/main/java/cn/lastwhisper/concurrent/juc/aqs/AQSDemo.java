package cn.lastwhisper.concurrent.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * on 2020/11/11 18:08
 */
public class AQSDemo {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        //带入一个银行办理业务的案例来模拟我们的AQS如何进行线程的管理和通知唤醒机制

        // 3个线程模拟3个银行网点，受理窗口办理业务的客户

        // A 顾客就是每一个顾客，此时受理窗口没有任何人，A可以直接办理
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("----------A thread come in");
                //暂停几秒钟线程
                try {
                    TimeUnit.MINUTES.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }, "A").start();

        //第二个顾客，第2个线程 ---》，由于受理业务的窗口只有一个（只能一个线程持有锁），些时B只能等待
        //进入候客区
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("----------B thread come in");
            } finally {
                lock.unlock();
            }
        }, "B").start();

        //第三个顾客，第3个线程 ---》，由于受理业务的窗口只有一个（只能一个线程持有锁），些时C只能等待
        //进入候客区
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("----------C thread come in");
            } finally {
                lock.unlock();
            }
        }, "C").start();

    }
}