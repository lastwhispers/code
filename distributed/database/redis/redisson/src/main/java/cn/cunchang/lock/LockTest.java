package cn.cunchang.lock;

import cn.cunchang.RedissonUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class LockTest {

    static long doSomethingTime = 20;

    static long watchDogTime = 10 * 1000;

    /**
     * 不设置过期时间有watch dog续租
     *
     * @param args
     */
    public static void main(String[] args) {
        RedissonClient redisson = RedissonUtil.getRedissonClient(watchDogTime);
        RLock lock = redisson.getLock("myLock");
        try {
            lock.lock(); //  死锁
//            lock.lock(10, TimeUnit.SECONDS);
            System.out.println("lock");
            for (int i = 1; i <= doSomethingTime; i++) {
                Thread.sleep(1000L);
                System.out.println("do something " + i + " 秒...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread() && lock.isLocked()) {
                int i = 10/0;// 制造一个异常无法释放锁
                System.out.println("unlock");
                lock.unlock();
            }
            redisson.shutdown();
        }

    }

}
