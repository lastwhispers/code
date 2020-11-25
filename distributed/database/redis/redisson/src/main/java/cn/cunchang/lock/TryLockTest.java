package cn.cunchang.lock;

import cn.cunchang.RedissonUtil;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class TryLockTest {

    static long doSomethingTime = 20L;
    // 锁租约时间
    static long leaseTime = 20L;

    public static void main(String[] args) {
        RedissonClient redisson = RedissonUtil.getRedissonClient();
        RLock rLock = redisson.getLock("myLock");

        try {
            rLock.tryLock(0L,leaseTime, TimeUnit.SECONDS);
            System.out.println("lock");
            for (int i = 1; i <= doSomethingTime; i++) {
                Thread.sleep(1000L);
                System.out.println("do something " + i + " 秒...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rLock.isHeldByCurrentThread() && rLock.isLocked()) {
                System.out.println("unlock");
                rLock.unlock();
            }
            redisson.shutdown();
        }

    }

}
