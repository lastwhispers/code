package cn.lastwhisper.concurrent.basic;

import java.util.concurrent.TimeUnit;

/**
 *  死锁是指两个或者以上的进程在执行过程中,
 *      因争夺资源而造成的一种相互等待的现象,
 *      若无外力干涉那他们都将无法推进下去
 *      jps -l
 *      jstack jid
 * @author lastwhisper
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLock(lockA, lockB)).start();
        new Thread(new HoldLock(lockB, lockA)).start();
    }
}

class HoldLock implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 获取lockA");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 获取lockB");
            }
        }
    }

}
