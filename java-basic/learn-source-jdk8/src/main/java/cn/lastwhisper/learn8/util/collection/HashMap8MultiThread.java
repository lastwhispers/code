package cn.lastwhisper.learn8.util.collection;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * HashMap 在多线程环境成环
 * @author lastwhisper
 * @date 2020/4/14
 */
public class HashMap8MultiThread {

    // 多线程共享的HashMap
    static HashMap<String, String> shareMap = new HashMap<>();

    static class AddTask implements Runnable {
        int start = 0;
        CountDownLatch latch;

        public AddTask(int start, CountDownLatch latch) {
            this.start = start;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                for (int i = start; i < 100000; i += 2) {
                    shareMap.put(Integer.toString(i), Integer.toBinaryString(i));
                }
            } finally {
                latch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(new AddTask(0, latch));
        Thread t2 = new Thread(new AddTask(1, latch));
        t1.start();
        t2.start();

        try {
            latch.await();
            System.out.println(shareMap.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
