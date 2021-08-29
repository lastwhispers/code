package cn.cunchang;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;

public class RedissonUtil {

    public static RedissonClient getRedissonClient() {
        Config config = new Config();
        try {
            config.setCodec((Codec) Class.forName("org.redisson.codec.JsonJacksonCodec").newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

    public static RedissonClient getRedissonClient(long watchDogTime) {
        Config config = new Config();
        config.setLockWatchdogTimeout(watchDogTime);
//        try {
//            config.setCodec((Codec) Class.forName("org.redisson.codec.JsonJacksonCodec").newInstance());
//        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

}
