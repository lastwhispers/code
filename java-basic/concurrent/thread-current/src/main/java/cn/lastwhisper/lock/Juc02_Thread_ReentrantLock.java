package cn.lastwhisper.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Juc02_Thread_ReentrantLock {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void reentrantLock(){
        String threadName = Thread.currentThread().getName();
        //默认创建的是独占锁，排它锁；同一时刻读或者写只允许一个线程获取锁
        lock.lock();
        log.info("Thread:{},第一次加锁",threadName);
            lock.lock();
            log.info("Thread:{},第二次加锁",threadName);
            lock.unlock();
            log.info("Thread:{},第一次解锁",threadName);
        lock.unlock();
        log.info("Thread:{},第二次解锁",threadName);
    }

    public static void main(String[] args) {
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock();
            }
        },"t0");

        t0.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock();
            }
        },"t1");

        t1.start();
    }

}
