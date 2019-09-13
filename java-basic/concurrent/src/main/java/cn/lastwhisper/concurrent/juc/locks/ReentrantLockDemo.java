package cn.lastwhisper.concurrent.juc.locks;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁的公平性
 * http://www.imooc.com/article/288805?block_id=tuijian_wz
 * @author lastwhisper
 */
public class ReentrantLockDemo implements Runnable {
    //是否公平,公平时交替打印
    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
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
