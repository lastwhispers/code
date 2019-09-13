package cn.lastwhisper.concurrent.juc.aqs.s06;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 错误
 * @author lastwhisper
 */
public class Sync extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());// 设置互斥锁
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    @Override
    protected boolean isHeldExclusively() {
        return getState()==1;
    }


}
