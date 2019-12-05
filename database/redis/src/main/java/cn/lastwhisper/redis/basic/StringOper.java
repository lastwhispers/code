package cn.lastwhisper.redis.basic;

import redis.clients.jedis.Jedis;

/**
 * string数据结构的操作
 * @author lastwhisper
 * @date 2019/11/24
 */
public class StringOper {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // incr指令
        Long num = jedis.incr("num");
        System.out.println(num);
    }

}
