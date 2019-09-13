package cn.lastwhisper.concurrent.juc.locks;

import java.util.concurrent.locks.LockSupport;

/**
 * 线程阻塞工具类
 * @author lastwhisper
 */
public class TestLockSupport {
    private static ChangObjectThread t1 = new ChangObjectThread("t1");
    private static ChangObjectThread t2 = new ChangObjectThread("t2");

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000);
        t2.start();
        LockSupport.unpark(t1);
        Thread.sleep(1000);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}

class ChangObjectThread extends Thread {
    final static Object lock = new Object();
    public ChangObjectThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("in " + getName());
            // sleep
            LockSupport.park();
            System.out.println("out " + getName());
        }
    }

}