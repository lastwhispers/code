package cn.cunchang.lock;

import cn.cunchang.RedissonUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class LockTest {

    static long doSomethingTime = 20;
    /**
     *
     * 每隔 leaseRenewalTime/3 就会进行续租，每次续租 leaseRenewalTime 毫秒
     */
    static long leaseRenewalTime = 9 * 1000;

    /**
     * lock一直获取锁，直到能获取
     *
     */
    public static void main(String[] args) {
        RedissonClient redisson = RedissonUtil.getRedissonClient(leaseRenewalTime);
        RLock lock = redisson.getLock("myLock");
        RBucket<Object> rBucket = redisson.getBucket("myLock");
        try {
            lock.lock();
            long ttl = rBucket.remainTimeToLive();
            System.out.println("lock | lockkey:myLock,ttl:" + ttl);
            for (int i = 1; i <= doSomethingTime; i++) {
                Thread.sleep(995L);
                ttl = rBucket.remainTimeToLive();
                System.out.println("do something " + i + " 秒 | lockkey:myLock,ttl:" + ttl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread() && lock.isLocked()) {
                lock.unlock();
                long ttl = rBucket.remainTimeToLive();
                System.out.println("unlock lockkey:myLock,ttl:" + ttl);
            }
            redisson.shutdown();
        }
    }

}
