package cn.cunchang.pipeline;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * 流水线
 * @author cunchang
 */
public class PipelineTest {
    //没有使用pipieline的情况下
    @Test
    public void testWithoutPipeline() {
        try (Jedis jedis = new Jedis("192.168.108.129", 6379)) {
            long start = System.currentTimeMillis();
            for (int i = 1; i <= 10000; i++) {
                jedis.hset("hashKey-" + i, "field-" + i, "value-" + i);
            }
            long end = System.currentTimeMillis();
            System.out.println("耗时：" + (end - start));//耗时：5393
            jedis.flushAll();//清空数据库，方便后续测试
        }
    }

    //使用pipeline的情况下
    @Test
    public void testPipeline() {
        try (Jedis jedis = new Jedis("192.168.108.129", 6379)){
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                Pipeline pipeline = jedis.pipelined();
                for (int j = i * 100; j < (i + 1) * 100; j++) {
                    pipeline.hset("hashKey-" + j, "field-" + j, "value-" + j);
                }
                pipeline.syncAndReturnAll();
            }
            long end = System.currentTimeMillis();
            System.out.println("耗时：" + (end - start)); // 耗时：108
            jedis.flushAll();//清空数据库，方便后续测试
        }
    }
}
