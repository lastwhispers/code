package cn.cunchang.lock;

import cn.cunchang.RedissonUtil;
import org.redisson.RedissonRedLock;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedLockDemo {

    static long doSomethingTime = 10;

    static long watchDogTime = 9 * 1000;

    /**
     * 不设置过期时间有watch dog续租
     *
     * @param args
     */
    public static void main(String[] args) {
        // 5个完全互相独立，不存在主从复制或者其他集群协调机制的 Redis 实例
        RedissonClient redissonInstance1 = RedissonUtil.getRedissonClient(watchDogTime);
        RedissonClient redissonInstance2 = RedissonUtil.getRedissonClient(watchDogTime);
        RedissonClient redissonInstance3 = RedissonUtil.getRedissonClient(watchDogTime);
        RedissonClient redissonInstance4 = RedissonUtil.getRedissonClient(watchDogTime);
        RedissonClient redissonInstance5 = RedissonUtil.getRedissonClient(watchDogTime);

        RLock lock1 = redissonInstance1.getLock("lock1");
        RLock lock2 = redissonInstance2.getLock("lock2");
        RLock lock3 = redissonInstance3.getLock("lock3");
        RLock lock4 = redissonInstance4.getLock("lock4");
        RLock lock5 = redissonInstance5.getLock("lock5");

        RBucket<Object> rBucket1 = redissonInstance1.getBucket("lock1");
        RBucket<Object> rBucket2 = redissonInstance1.getBucket("lock2");
        RBucket<Object> rBucket3 = redissonInstance1.getBucket("lock3");
        RBucket<Object> rBucket4 = redissonInstance1.getBucket("lock4");
        RBucket<Object> rBucket5 = redissonInstance1.getBucket("lock5");

        RedissonRedLock lock = new RedissonRedLock(lock1, lock2, lock3, lock4, lock5);
        try {
            // 同时加锁：lock1, lock2, lock3, lock4, lock5
            // 红锁在(N/2 +1)个节点上加锁成功就算成功。
            System.out.println("pre lock -->" +
                    "\tlock1,ttl:" + rBucket1.remainTimeToLive() +
                    "\tlock2,ttl:" + rBucket2.remainTimeToLive() +
                    "\tlock3,ttl:" + rBucket3.remainTimeToLive() +
                    "\tlock4,ttl:" + rBucket4.remainTimeToLive() +
                    "\tlock5,ttl:" + rBucket5.remainTimeToLive()
            );
            boolean falg = lock.tryLock();
            if (!falg) {
                System.out.println("lock fail");
                return;
            }
            System.out.println("lock success -->" +
                    "\tlock1,ttl:" + rBucket1.remainTimeToLive() +
                    "\tlock2,ttl:" + rBucket2.remainTimeToLive() +
                    "\tlock3,ttl:" + rBucket3.remainTimeToLive() +
                    "\tlock4,ttl:" + rBucket4.remainTimeToLive() +
                    "\tlock5,ttl:" + rBucket5.remainTimeToLive()
            );
            for (int i = 1; i <= doSomethingTime; i++) {
                Thread.sleep(995L);
                System.out.println("do something " + i + " 秒 | " +
                        "\tlock1,ttl:" + rBucket1.remainTimeToLive() +
                        "\tlock2,ttl:" + rBucket2.remainTimeToLive() +
                        "\tlock3,ttl:" + rBucket3.remainTimeToLive() +
                        "\tlock4,ttl:" + rBucket4.remainTimeToLive() +
                        "\tlock5,ttl:" + rBucket5.remainTimeToLive()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // lock.isHeldByCurrentThread() =》java.lang.UnsupportedOperationException
            // lock.isLocked() =》java.lang.UnsupportedOperationException
            System.out.println("unlock -->" +
                    "\tlock1,ttl:" + rBucket1.remainTimeToLive() +
                    "\tlock2,ttl:" + rBucket2.remainTimeToLive() +
                    "\tlock3,ttl:" + rBucket3.remainTimeToLive() +
                    "\tlock4,ttl:" + rBucket4.remainTimeToLive() +
                    "\tlock5,ttl:" + rBucket5.remainTimeToLive()
            );
            lock.unlock();
            redissonInstance1.shutdown();
            redissonInstance2.shutdown();
            redissonInstance3.shutdown();
            redissonInstance4.shutdown();
            redissonInstance5.shutdown();
        }
    }


}
