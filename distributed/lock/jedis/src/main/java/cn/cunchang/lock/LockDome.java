package cn.cunchang.lock;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 分布式锁
 *
 * @author cunchang
 */
public class LockDome {


    /**
     * 问题：死锁
     * 解决：需要设置过期时间，自动释放锁
     *
     * @param jedis   Redis客户端
     * @param lockKey set指令的key
     */
    public static void wrongLock1(Jedis jedis, String lockKey) {
        Long result = jedis.setnx(lockKey, "lockValue");
        // 1 0
        if (result == 0L) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // wrong: 若在程序死循环，无法释放锁，将发生死锁
            // do something
        } finally {
            jedis.del(lockKey);
            jedis.close();
        }
    }

    /**
     * 处理wrongLock1的问题
     * <p>
     * 问题：非原子操作，死锁
     * 解决：
     * - 原生set
     * - lua
     * - 事务
     *
     * @param jedis      Redis客户端
     * @param lockKey    set指令的key
     * @param expireTime 超期时间
     */
    public static void wrongLock2(Jedis jedis, String lockKey, int expireTime) {
        Long result = jedis.setnx(lockKey, "lockValue");
        // 1 0
        if (result == 0L) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // wrong: setnx+expire非原子操作（若程序在这里突然所属进程崩溃、机器宕机等，无法释放锁，将发生死锁）
            jedis.expire(lockKey, expireTime);
            // do something
        } finally {
            jedis.del(lockKey);
            jedis.close();
        }
    }

    //设置锁的lua脚本
    static String SETNX_EXPIRE_SCRIPT = "if redis.call('setnx', KEYS[1], KEYS[2]) == 1 then\n"
            + "return redis.call('expire', KEYS[1], KEYS[3]);\n"
            + "end\n"
            + "return nil;";

    static String RELEASE_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then\n" +
            " return redis.call('del', KEYS[1]);\n" +
            " else return 0 end";

    static String SETNX_EXPIRE_SCRIPT2 =
            "if redis.call('setnx', KEYS[1], KEYS[2]) == 1 then return redis.call('expire', KEYS[1], KEYS[3]); end return nil;";

    static String RELEASE_SCRIPT2 =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]); else return 0 end";


    /**
     * 问题：锁错误释放
     * 解决：value设置随机数，获取lockkey对应的value，判断一下value是否相等
     *
     * @param jedis      Redis客户端
     * @param lockKey    set指令的key
     * @param expireTime 超期时间
     */
    public static void wrongLock3_1(Jedis jedis, String lockKey, int expireTime) {
        Object result = jedis.set(lockKey, "lockValue", "NX", "EX", expireTime);
        // OK null
        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // do something
            // wrong ：
            // do something 执行超过锁过期时间，锁过期释放
            // do something 执行完毕后，del key，导致锁错误释放
        } finally {
            jedis.del(lockKey);
            jedis.close();
        }
    }

    /**
     * 问题：锁错误释放
     * 解决：value设置随机数，获取lockkey对应的value，判断一下value是否相等
     *
     * @param jedis      Redis客户端
     * @param lockKey    set指令的key
     * @param expireTime 超期时间
     */
    public static void wrongLock3_2(Jedis jedis, String lockKey, int expireTime) {
        String SETNX_EXPIRE_SCRIPT = "if redis.call('setnx', KEYS[1], KEYS[2]) == 1 then\n"
                + "return redis.call('expire', KEYS[1], KEYS[3]);\n"
                + "end\n"
                + "return nil;";

        Object result = jedis.eval(SETNX_EXPIRE_SCRIPT, 3, lockKey, "lockValue", Integer.toString(expireTime));
        // 1 null
        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // do something
            // wrong ：
            // do something 执行超过锁过期时间，锁过期释放
            // do something 执行完毕后，del key，导致锁错误释放
        } finally {
            jedis.del(lockKey);
            jedis.close();
        }
    }

    /**
     * 问题：client1超时后锁过期释放，client2获取锁后，client1执行完成，将client2的锁释放，导致锁错误释放
     * 解决：value设置随机数
     *
     * @param jedis      Redis客户端
     * @param lockKey    set指令的key
     * @param expireTime 超期时间
     */
    public static void wrongLock4(Jedis jedis, String lockKey, int expireTime) {
        String randomId = UUID.randomUUID().toString();
        Object result = jedis.set(lockKey, randomId, "NX", "EX", expireTime);
        // OK null
        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // do something
        } finally {
            // 判断加锁与解锁是不是同一个客户端，防止误解锁
            if (randomId.equals(jedis.get(lockKey))) {
                // wrong ：无法保证原子性
                jedis.del(lockKey);
            }
            jedis.close();
        }
    }

    /**
     * 问题：equals和del不是原子操作
     * 解决：
     * - lua
     * - 事务
     *
     * @param jedis      Redis客户端
     * @param lockKey    set指令的key
     * @param expireTime 超期时间
     */
    public static void lock5_1(Jedis jedis, String lockKey, int expireTime) {
        String randomId = UUID.randomUUID().toString();
        Object result = jedis.set(lockKey, randomId, "NX", "EX", expireTime);
        // OK null
        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // do something
        } finally {
            // 判断加锁与解锁是不是同一个客户端，防止误解锁
            jedis.eval(RELEASE_SCRIPT, Collections.singletonList(lockKey), Collections.singletonList(randomId));
            jedis.close();
        }
    }

    /**
     * 问题：equals和del不是原子操作
     * 解决：
     * - lua
     * - 事务
     *
     * @param jedis      Redis客户端
     * @param lockKey    set指令的key
     * @param expireTime 超期时间
     */
    public static void lock5_2(Jedis jedis, String lockKey, int expireTime) {
        String randomId = UUID.randomUUID().toString();
        Object result = jedis.set(lockKey, randomId, "NX", "EX", expireTime);
        // OK null
        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // do something
        } finally {
            // 判断加锁与解锁是不是同一个客户端，防止误解锁
            // 通过watch+事务，实现乐观锁，尝试删除key；删除失败说明自己的锁已经过期
            while (true) {
                // watch监控key
                jedis.watch(lockKey);
                String redisLockValue = jedis.get(lockKey);
                if (randomId.equals(redisLockValue)) {
                    Transaction tx = jedis.multi();
                    tx.del(lockKey);
                    // 事务发现watch的key对应value发生变化，del会执行失败，就不会释放别人的锁了
                    List<Object> execResult = tx.exec();
                    if (execResult == null) {
                        continue;
                    }
                }
                jedis.unwatch();
                break;
            }
            jedis.close();
        }
    }

}