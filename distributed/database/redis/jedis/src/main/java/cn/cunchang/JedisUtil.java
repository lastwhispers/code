package cn.cunchang;

import cn.cunchang.common.Const;
import redis.clients.jedis.Jedis;

public class JedisUtil {

    public static Jedis getJedis() {
        return new Jedis(Const.IP, Const.PORT);
    }

}
