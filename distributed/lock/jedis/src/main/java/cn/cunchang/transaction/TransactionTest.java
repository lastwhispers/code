package cn.cunchang.transaction;

import cn.cunchang.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * redis事务测试
 * @author cunchang
 */
public class TransactionTest {

    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getJedis();
        String userId = "abc";
        String key = buildKey(userId);
        jedis.setnx(key, String.valueOf(5)); // setnx 做初始化
        System.out.println(doubleAccount(jedis, userId));
        jedis.close();
    }
    /**
     * 乐观锁：watch
     *
     */
    public static int doubleAccount(Jedis jedis, String userId) {
        String key = buildKey(userId);
        while (true) {
            jedis.watch(key);
            int value = Integer.parseInt(jedis.get(key));
            value *= 2; // 加倍
            Transaction tx = jedis.multi();
            tx.set(key, String.valueOf(value));
            List<Object> res = tx.exec();
            if (res != null) {
                break; // 成功了
            }
        }
        return Integer.parseInt(jedis.get(key)); // 重新获取余额
    }

    public static String buildKey(String userId) {
        return String.format("account_{}", userId);
    }
}
