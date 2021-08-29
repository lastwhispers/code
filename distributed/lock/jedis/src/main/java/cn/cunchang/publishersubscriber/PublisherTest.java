package cn.cunchang.publishersubscriber;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author cunchang
 */
public class PublisherTest {
    //发布
    public static void main(String[] args){
        Jedis jedis = new Jedis("192.168.108.130" , 6379);
        jedis.publish("aliTV" , "I am xuyinan");
        jedis.publish("googleTV" , "My age is 27");
    }
}
