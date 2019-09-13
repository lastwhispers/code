package cn.lastwhisper.concurrent.juc.aqs.s02;

import cn.lastwhisper.concurrent.juc.aqs.s04.MLock;

import java.util.concurrent.locks.Lock;

/**
 * @author lastwhisper
 */
public class Main {
    private static int m = 0;
    private static Lock lock = new MLock();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                synchronized (Main.class) {
                    for (int j = 0; j < 100; j++) {
                        m++;
                    }
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();//等待所有线程运行结束

        System.out.println(m);
    }
}
