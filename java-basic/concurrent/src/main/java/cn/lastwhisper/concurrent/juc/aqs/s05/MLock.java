package cn.lastwhisper.concurrent.juc.aqs.s05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 正确
 * @author lastwhisper
 */
public class MLock implements Lock {
    private volatile int i = 0;

    @Override
    public void lock() {
        synchronized (this) {
            while (i != 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i = 1;
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        synchronized (this) {
            i = 0;
            this.notify();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
