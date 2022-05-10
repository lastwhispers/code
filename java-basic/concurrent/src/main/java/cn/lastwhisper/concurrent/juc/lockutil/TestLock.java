package cn.lastwhisper.concurrent.juc.lockutil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用于解决多线程安全性问题的方式：
 * 1.synchronize：隐式锁JVM指令集底层支持的锁
 *  1.同步代码块
 *  2.同步方法
 * 2.Lock：显式锁，JDK实现的锁，需要手动lock、unlock
 * @author lastwhisper
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "一号窗口").start();
        new Thread(ticket, "二号窗口").start();
        new Thread(ticket, "三号窗口").start();
    }
}

class Ticket implements Runnable {
    private int tick = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (tick > 0) {
                    Thread.sleep(50);//放大线程安全性问题
                    System.out.println(Thread.currentThread().getName() + " 剩余票数：" + --tick);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}

