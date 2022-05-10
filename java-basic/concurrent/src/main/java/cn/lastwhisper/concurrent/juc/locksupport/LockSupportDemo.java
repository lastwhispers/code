package cn.lastwhisper.concurrent.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * on 2020/11/10 23:17
 */
public class LockSupportDemo {

    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
//        synchronizedWaitNotify();
//        lockAwaitSignal();
        lockSupport();
    }

    private static void lockSupport() {
        Thread a = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "come in" + System.currentTimeMillis());
            LockSupport.park(); //wait 被阻塞，等待通知等待放行，它要通过需要许可证
            System.out.println(Thread.currentThread().getName() + "\t" + "---被唤醒--" + System.currentTimeMillis());
        }, "a");
        a.start();

        //暂停几秒钟线程
      /*  try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        Thread b = new Thread(() -> {
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t" + "---被唤醒--");

        }, "b");
        b.start();
    }

    private static void lockAwaitSignal() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "----come in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "----被唤醒");
            } finally {
                lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t" + "----通知---");
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }

    /**
     * synchronized/wait/notifyAll
     */
    private static void synchronizedWaitNotify() {
        new Thread(() -> {
            //暂停几秒钟线程
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t" + "---come in--");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "-----被唤醒-----");
            }

        }, "A").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t" + "---通知---");
            }
        }, "B").start();
    }
}