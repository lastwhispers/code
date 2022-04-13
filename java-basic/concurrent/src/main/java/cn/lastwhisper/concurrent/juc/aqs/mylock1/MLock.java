package cn.lastwhisper.concurrent.juc.aqs.mylock1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 正确
 *
 * @author lastwhisper
 */
public class MLock implements Lock {
    private volatile int i = 0;

    /**
     * t1进来i=1；
     * t2进来this.wait();
     * t3进来synchronized blocked
     */
    @Override
    public synchronized void lock() {
        while (i != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        i = 1;
    }

    @Override
    public synchronized void lockInterruptibly() throws InterruptedException {
        while (i != 0) {
            this.wait();
        }
        i = 1;
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    /**
     * 这里用notify就够了，因为只有一个线程wait
     */
    @Override
    public synchronized void unlock() {
        i = 0;
        this.notify();
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
