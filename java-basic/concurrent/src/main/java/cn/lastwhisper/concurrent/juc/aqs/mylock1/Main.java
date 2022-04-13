package cn.lastwhisper.concurrent.juc.aqs.mylock1;

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
                try {
                    lock.lock();
                    for (int j = 0; j < 10000; j++) m++;
                } finally {
                    lock.unlock();
                }

            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();//等待所有线程运行结束

        System.out.println(m);
    }
}
