package cn.cunchang.publishersubscriber;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author cunchang
 */
public class SubscriberTest {
    //订阅
    public static void main(String[] args){
        Jedis jedis = new Jedis("192.168.108.130" , 6379);
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("receive channel ["+channel+"] message ["+message+"]");
            }
        } , "aliTV" , "googleTV");
    }
}
