package cn.lastwhisper.concurrent.juc.lock;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多读一写集合是否线程安全
 * @author lastwhisper
 */
public class TestManyReadOneWriteCollection {
    // 读写锁
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    // 闭锁
    private static CountDownLatch latch = new CountDownLatch(21);

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    // 共享数据
    private ArrayList<Integer> collection = new ArrayList<>();

    {
        for (int i = 0; i < 20; i++) {
            collection.add(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        TestManyReadOneWriteCollection rwl = new TestManyReadOneWriteCollection();
        // 读线程执行的主体
        Runnable readThread = () -> {
            //System.out.println(rwl.handleReadLock(readLock));;
            System.out.println(rwl.handleRead());
            latch.countDown();
        };
        // 写线程执行的主体
        Runnable writeThread = () -> {
            //rwl.handleWriteLock(writeLock, new Random().nextInt(10000));
            rwl.handleWrite(new Random().nextInt(10000));
            latch.countDown();
        };

        // 多读一写
        for (int i = 0; i < 10; i++) {
            threadPool.execute(readThread);
        }
        for (int i = 0; i < 1; i++) {
            threadPool.execute(writeThread);
        }
        for (int i = 0; i < 10; i++) {
            threadPool.execute(readThread);
        }
        latch.await();
        threadPool.shutdown();
    }

    // 读取文件
    public String handleRead() {
        StringBuilder result= new StringBuilder();
        //Thread.sleep(9);
        for (Integer num : collection) {
            result.append(num);
        }
        return result.toString();
    }

    // 写入文件
    public void handleWrite(int writeVal) {
        //Thread.sleep(10);
        for (int i = 0; i < writeVal; i++) {
            collection.add(i);
        }
    }

    // 读取文件
    public String handleReadLock(Lock lock) {
        StringBuilder result= new StringBuilder();
        lock.lock();
        try {
            //Thread.sleep(9);
            for (Integer num : collection) {
                result.append(num);
            }
        } finally {
            lock.unlock();
        }
        return result.toString();
    }

    // 写入文件
    public void handleWriteLock(Lock lock, int writeVal) {
        lock.lock();
        try {
            //Thread.sleep(10);
            for (int i = 0; i < writeVal; i++) {
                collection.add(i);
            }
        } finally {
            lock.unlock();
        }
    }

}