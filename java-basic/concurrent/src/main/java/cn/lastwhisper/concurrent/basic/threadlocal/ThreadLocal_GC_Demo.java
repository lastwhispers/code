package cn.lastwhisper.concurrent.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal
 * @author lastwhisper
 */
public class ThreadLocal_GC_Demo {
    static volatile CountDownLatch latch = new CountDownLatch(1000);

    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.toString() + " is GC");
        }
    };

    public static class ParseDate implements Runnable {
        private int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (tl.get() == null) {
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
                        @Override
                        protected void finalize() throws Throwable {
                            System.out.println(this.toString() + " is GC");
                        }
                    });
                    System.out.println(Thread.currentThread().getId()+":create SimpleDataFormat");
                }
                Date t = tl.get().parse("2015-03-28 19:15:" + i % 60);
            } catch (ParseException e) {
                e.printStackTrace();
            }finally {
                latch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            pool.execute(new ParseDate(i));
        }

        latch.await();
        System.out.println("mission complete");
        tl = null;
        System.gc();
        System.out.println("first GC complete");
        // 设置tl时，清除ThreadLocalMap中无效对象
        tl = new ThreadLocal<>();
        latch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            pool.execute(new ParseDate(i));
        }
        latch.await();
        Thread.sleep(500);
        System.gc();
        System.out.println("second GC complete");
    }
}
