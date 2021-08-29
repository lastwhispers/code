package cn.cunchang;

import cn.cunchang.common.Const;
import redis.clients.jedis.Jedis;

public class JedisUtil {

    public static Jedis getJedis() {
        // 默认db0，可以设置
        Jedis jedis = new Jedis(Const.IP, Const.PORT);
//        jedis.select(1);
        return jedis;
    }

}
