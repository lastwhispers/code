package cn.lastwhisper.concurrent.juc.locks;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：在读多写少的情景
 *  写写/读写 需要“互斥”
 *  读读 不需要互斥
 * @author lastwhisper
 */
public class TestReadWriteLock {
    // 互斥锁
    private static Lock lock = new ReentrantLock();
    // 读写锁
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    // 闭锁
    private static CountDownLatch latch = new CountDownLatch(20);
    // 共享数据
    private int value = 0;

    public static void main(String[] args) throws InterruptedException {

        TestReadWriteLock rwl = new TestReadWriteLock();
        // 读线程执行的主体
        Runnable readThread = new Runnable() {
            @Override
            public void run() {
                rwl.handleRead(readLock);
                //rwl.handleRead(lock);
                latch.countDown();
            }
        };
        // 写线程执行的主体
        Runnable writeThread = new Runnable() {
            @Override
            public void run() {
                rwl.handleWrite(writeLock, new Random().nextInt());
                //rwl.handleWrite(lock, new Random().nextInt());
                latch.countDown();
            }
        };
        Instant start = Instant.now();
        // 20次读写操作
        for (int i = 0; i < 18; i++) {
            new Thread(readThread).start();
        }
        for (int i = 18; i < 20; i++) {
            new Thread(writeThread).start();
        }
        latch.await();
        Instant end = Instant.now();
        // 读写锁耗时：504 (18个读线程100;2个写线程400;线程上下文切换4)
        // 互斥锁耗时：2212 (18个读线程1800;2个写线程400;线程上下文切换12)
        System.out.println("20次读写耗时：" + Duration.between(start, end).toMillis());
    }

    // 读取文件
    public Object handleRead(Lock lock) {
        lock.lock();
        try {
            // 模拟读业务耗时
            Thread.sleep(100);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return value;
    }

    // 写入文件
    public void handleWrite(Lock lock, int writeVal) {
        lock.lock();
        try {
            // 模拟写业务耗时
            Thread.sleep(200);
            value = writeVal;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}