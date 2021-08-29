package cn.cunchang.lock;

import cn.cunchang.RedissonUtil;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class MultiLockTest {

    static long doSomethingTime = 20;

    static long watchDogTime = 10 * 1000;

    /**
     * 不设置过期时间有watch dog续租
     *
     * @param args
     */
    public static void main(String[] args) {
        RedissonClient redissonInstance1 = RedissonUtil.getRedissonClient(watchDogTime);
        RedissonClient redissonInstance2 = RedissonUtil.getRedissonClient(watchDogTime);
        RedissonClient redissonInstance3 = RedissonUtil.getRedissonClient(watchDogTime);

        RLock lock1 = redissonInstance1.getLock("lock1");
        RLock lock2 = redissonInstance2.getLock("lock2");
        RLock lock3 = redissonInstance3.getLock("lock3");

        RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);
        try {
            // 同时加锁：lock1 lock2 lock3
            // 所有的锁都上锁成功才算成功。
            lock.lock();
//            rLock.lock(10, TimeUnit.SECONDS);
            System.out.println("lock -->lock1 lock2 lock3");
            for (int i = 1; i <= doSomethingTime; i++) {
                Thread.sleep(1000L);
                System.out.println("do something " + i + " 秒...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // lock.isHeldByCurrentThread() =》java.lang.UnsupportedOperationException
            // lock.isLocked() =》java.lang.UnsupportedOperationException
            System.out.println("unlock -->lock1 lock2 lock3");
            lock.unlock();
            redissonInstance1.shutdown();
            redissonInstance2.shutdown();
            redissonInstance3.shutdown();
        }

    }

}
