package cn.cunchang;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonUtil {

    public static RedissonClient getRedissonClient() {
        Config config = new Config();
//        config.setLockWatchdogTimeout(30 * 1000);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        // 2. Create Redisson instance
        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

    public static RedissonClient getRedissonClient(long watchDogTime) {
        Config config = new Config();
        config.setLockWatchdogTimeout(watchDogTime);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        // 2. Create Redisson instance
        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

}
