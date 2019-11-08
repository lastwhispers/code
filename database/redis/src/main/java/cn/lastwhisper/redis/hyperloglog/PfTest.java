package cn.lastwhisper.redis.hyperloglog;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author lastwhisper
 */
public class PfTest {

    /**
     * HyperLoglog的统计误差
     */
    @Test
    public void test1() {
        Jedis jedis = new Jedis("192.168.108.129", 6379);
        for (int i = 0; i < 1000; i++) {
            jedis.pfadd("uv", "user" + i);
            // 出现误差
            long total = jedis.pfcount("uv");
            if (total != i + 1) {
                System.out.printf("%d %d\n", total, i + 1);//99 100
                break;
            }
        }
        jedis.close();
    }

    /**
     * HyperLoglog 一万次的误差情况
     */
    @Test
    public void test2() {
        Jedis jedis = new Jedis("192.168.108.129", 6379);
        for (int i = 0; i < 10000; i++) {
            jedis.pfadd("uv", "user" + i);
        }
        long total = jedis.pfcount("uv");
        System.out.printf("%d %d\n", 10000, total);//10000 10064
        jedis.close();
    }

}
