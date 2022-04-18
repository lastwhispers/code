package cn.lastwhisper.concurrent.basic.Interrupted;

import java.util.concurrent.locks.LockSupport;

public class InterruptLockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 come in");
            LockSupport.park();
            System.out.println("t1 go out");
        }, "t1");
        t1.start();

        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(1000);
    }
}
