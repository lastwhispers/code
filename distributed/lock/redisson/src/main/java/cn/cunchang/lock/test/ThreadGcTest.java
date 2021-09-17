package cn.cunchang.lock.test;

import cn.cunchang.RedissonUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class ThreadGcTest {

    /**
     * 线程不释放锁，线程被回收后，看门狗不续约
     */
    public static void main(String[] args) {
        stackSegement();

        try {
            // 等待stackSegement()执行完成
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 回收 new Thread
        System.gc();
        System.out.println("回收 new Thread!!!");


        try {
            Thread.sleep(10000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void stackSegement() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RedissonClient redisson = RedissonUtil.getRedissonClient();
                RLock lock = redisson.getLock("myLock");
                try {
                    boolean lockFlag;
                    // 无租锁时间，锁会自动续租
                    lockFlag = lock.tryLock();
                    if (lockFlag) {
                        System.out.println("拿到锁");
                    } else {
                        System.out.println("没拿到锁");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("new thread() run方法执行完毕!!!");
            }
        });
        thread.start();
    }

}
