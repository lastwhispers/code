package cn.lastwhisper.concurrent.juc.aqs.mylock2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 正确
 * 独占锁
 * @author lastwhisper
 */
public class MLock implements Lock {
    private Sync sync = new Sync();

    // 静态内部类，自定义同步器
    private class Sync extends AbstractQueuedSynchronizer {
        // 当状态为0的时候获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());// 设置互斥锁
                return true;
            }
            return false;
        }
        // 释放锁，将状态设置为0
        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            if (!isHeldExclusively()) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            // 拿到当前排他线程是不是和当前线程一样，如果一样才释放
            return getExclusiveOwnerThread() == Thread.currentThread();
        }


    }

    @Override
    public void lock() {
        sync.acquire(1);
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
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
