package cn.cunchang.lock.real;

import redis.clients.jedis.Jedis;

import java.util.Objects;

public class LockUtil {


    /**
     * 问题：死锁
     * 解决：需要设置过期时间，自动释放锁
     *
     * @param jedis Redis客户端
     * @param lockKey set指令的key
     * @param codeBlock 代码块
     */
    public static void wrongLock1(Jedis jedis, String lockKey, CodeBlock codeBlock) {
        Long result = jedis.setnx(lockKey, "lockValue");
        if (result == 0L) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            codeBlock.operatingSharedResource();
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
    public static void wrongLock2(Jedis jedis, String lockKey, int expireTime, CodeBlock codeBlock) {
        Long result = jedis.setnx(lockKey, "lockValue");
        if (result == 0L) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // wrong: setnx+expire非原子操作（若程序在这里突然所属进程崩溃、机器宕机等，无法释放锁，将发生死锁）
            jedis.expire(lockKey, expireTime);
            codeBlock.operatingSharedResource();
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
     * @param expireTime 超期时间 秒
     */
    public static void wrongLock3_1(Jedis jedis, String lockKey, int expireTime, CodeBlock codeBlock) {
        Object result = jedis.set(lockKey, "lockValue", "NX", "EX", expireTime);

        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // do something
            // wrong ：
            // do something 执行超过锁过期时间，锁过期释放
            // do something 执行完毕后，del key，导致锁错误释放
            codeBlock.operatingSharedResource();
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
    public static void wrongLock3_2(Jedis jedis, String lockKey, int expireTime, CodeBlock codeBlock) {
        Object result = jedis.eval(SETNX_EXPIRE_SCRIPT, 3, lockKey, "lockValue", Integer.toString(expireTime));

        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // do something
            // wrong ：
            // do something 执行超过锁过期时间，锁过期释放
            // do something 执行完毕后，del key，导致锁错误释放
            codeBlock.operatingSharedResource();
        } finally {
            jedis.del(lockKey);
            jedis.close();
        }
    }

}
