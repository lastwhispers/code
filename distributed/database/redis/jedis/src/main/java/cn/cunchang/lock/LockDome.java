package cn.cunchang.lock;


import redis.clients.jedis.Jedis;

import java.util.Collections;
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
     * @param jedis Redis客户端
     */
    public static void wrongGetLock1(Jedis jedis) {
        Long result = jedis.setnx("lockKey", "lockValue");
        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // wrong: 若在程序死循环，无法释放锁，将发生死锁
            // do something
        } finally {
            jedis.del("lockKey");
            jedis.close();
        }
    }

    /**
     * 处理wrongGetLock1的问题
     * <p>
     * 问题：死锁
     * 解决：lua或原生set
     *
     * @param jedis      Redis客户端
     * @param expireTime 超期时间
     */
    public static void wrongGetLock2(Jedis jedis, int expireTime) {
        Long result = jedis.setnx("lockKey", "lockValue");
        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // wrong: setnx+expire非原子操作（若程序在这里突然所属进程崩溃、机器宕机等，无法释放锁，将发生死锁）
            jedis.expire("lockKey", expireTime);
            // do something
        } finally {
            jedis.del("lockKey");
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
     * 解决：value设置随机数
     *
     * @param jedis      Redis客户端
     * @param expireTime 超期时间
     */
    public static void wrongGetLock3(Jedis jedis, int expireTime) {
        Object result;

        result = jedis.eval(SETNX_EXPIRE_SCRIPT, 3, "lockKey", "lockValue", Integer.toString(expireTime));

        // 参数三 NX : 只在键不存在时，才对键进行设置操作
        // 参数四
        //       PX : 将键的过期时间设置为 milliseconds 毫秒
        //       EX : 将键的过期时间设置为 seconds 秒

//        result = jedis.set("lockKey", "lockValue", "NX", "EX", expireTime);

        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // do something
            // wrong ：
            // do something 执行超过锁过期时间，锁过期释放
            // do something 执行完毕后，del key，导致锁错误释放
        } finally {
            jedis.del("lockKey");
            jedis.close();
        }
    }

    /**
     * 问题：client1超时后锁过期释放，client2获取锁后，client1执行完成，将client2的锁释放，导致锁错误释放
     * 解决：value设置随机数
     *
     * @param jedis      Redis客户端
     * @param expireTime 超期时间
     */
    public static void wrongGetLock4(Jedis jedis, int expireTime) {
        String randomId = UUID.randomUUID().toString();
        Object result = jedis.set("lockKey", randomId, "NX", "EX", expireTime);

        if (Objects.isNull(result)) {
            throw new RuntimeException("操作太频繁，等一分钟在操作");
        }
        try {
            // do something

        } finally {

            // 判断加锁与解锁是不是同一个客户端，防止误解锁
//            if (randomId.equals(jedis.get("lockKey"))) {
//                jedis.del("lockKey");
//            }
            // wrong ：无法保证原子性

            jedis.eval(RELEASE_SCRIPT, Collections.singletonList("lockKey"), Collections.singletonList(randomId));
            jedis.close();
        }
    }

}