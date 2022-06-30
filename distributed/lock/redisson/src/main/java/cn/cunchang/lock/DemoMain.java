package cn.cunchang.lock;

import cn.cunchang.RedissonUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.CountDownLatch;

public class DemoMain {

    private volatile static boolean flag = false;

    public static void main(String[] args) throws Exception {

        RedissonClient redisson = RedissonUtil.getRedissonClient();

        CountDownLatch countDownLatch = new CountDownLatch(3);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                RLock lock = redisson.getLock("myLock");
                try {
                    lock.lock();
                    System.out.println("t1 lock!!!+id:" + Thread.currentThread().getId());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (flag) {
                        int i = 1;
                    }
                    System.out.println("t1 debug!!!+id:" + Thread.currentThread().getId());
                } finally {
                    lock.unlock();
                    countDownLatch.countDown();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                RLock lock = redisson.getLock("myLock");
                try {
                    lock.lock();
                    System.out.println("t2 lock!!!+id:" + Thread.currentThread().getId());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (flag) {
                        int i = 1;
                    }
                    System.out.println("t2 debug!!!+id:" + Thread.currentThread().getId());
                } finally {
                    lock.unlock();
                    countDownLatch.countDown();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                RLock lock = redisson.getLock("myLock");
                try {
                    lock.lock();
                    System.out.println("t2 lock!!!+id:" + Thread.currentThread().getId());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (flag) {
                        int i = 1;
                    }
                    System.out.println("t2 debug!!!+id:" + Thread.currentThread().getId());
                } finally {
                    lock.unlock();
                    countDownLatch.countDown();
                }
            }
        });


        thread1.start();
        thread2.start();
        thread3.start();

        countDownLatch.await();
        redisson.shutdown();
    }

}