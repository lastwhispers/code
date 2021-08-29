package cn.cunchang.sentinel;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author cunchang
 */
public class SentinelTest {
    private JedisSentinelPool sentinelPool;

    // 准备数据
    @Before
    public void before() {
        Set<String> sentinelSet = new HashSet<String>() {{
            add("192.168.108.131:26379");
            add("192.168.108.131:26380");
            add("192.168.108.131:26381");
        }};
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        String masterName = "mymaster";
        int timeout = 30_000;    //jedis连接sentinel的超时时间
        sentinelPool = new JedisSentinelPool(
                masterName, sentinelSet, poolConfig, timeout);

    }
    /**
     * 测试故障转移
     */
    @Test
    public void failover() {
        int count = 0;
        while (true) {
            count++;
            Jedis jedis = sentinelPool.getResource();
            try {
                int index = new Random().nextInt(10000);
                String key = "k-" + index;
                String value = "v-" + index;
                jedis.set(key, value);
                if(count%100==0){
                    System.out.println("key：" + key + "\tvalue：" + jedis.get(key));
                }
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (jedis != null) jedis.close();
            }
        }
    }


}
