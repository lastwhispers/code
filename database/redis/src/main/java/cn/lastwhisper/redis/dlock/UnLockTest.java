package cn.lastwhisper.redis.dlock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * 分布式锁解锁正确实例与错误实例
 * @author lastwhisper
 */
public class UnLockTest {
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * lua脚本释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        // 首先获取锁对应的value值，检查是否与requestId相等，如果相等则删除锁（解锁）
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * 直接使用jedis.del()方法删除锁，这种不先判断锁的拥有者而直接解锁的方式，
     * 会导致任何客户端都可以随时进行解锁，即使这把锁不是它的。
     */
    public static void wrongReleaseLock1(Jedis jedis, String lockKey) {
        jedis.del(lockKey);
    }

    /**
     * 无法保证原子性
     */
    public static void wrongReleaseLock2(Jedis jedis, String lockKey, String requestId) {
        // 判断加锁与解锁是不是同一个客户端
        if (requestId.equals(jedis.get(lockKey))) {
            // 若在此时，这把锁突然不是这个客户端的，则会误解锁
            jedis.del(lockKey);
        }

    }

}

