package cn.lastwhisper.concurrent.juc.lockutil;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁的公平性
 * @author lastwhisper
 */
public class ReentrantLockDemo implements Runnable {
    //是否公平,公平时交替打印
    private final ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for(;;){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args){
        ReentrantLockDemo lockDemo = new ReentrantLockDemo();
        new Thread(lockDemo,"A").start();
        new Thread(lockDemo,"B").start();
    }
}
