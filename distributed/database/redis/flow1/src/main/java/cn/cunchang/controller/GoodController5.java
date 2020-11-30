package cn.cunchang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


//@RestController
public class GoodController5 {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String serverPort;

    private String LOCK_KEY = "seckillLock";

    /**
     * 解决了4的问题 <p/>
     * 产生的问题：加锁解锁可能不是一个客户端
     *          A抢到锁后锁10秒过期，A业务执行了11秒（正常5~8秒突发情况），锁在第10秒过期后，
     *          B抢到锁，B业务执行了1秒，A释放锁，此时其实是把B拿到的锁释放了，B还在执行业务代码
     *          C抢到锁，B与C分布式锁的代码其实是在并行执行
     * @return
     */
    @GetMapping("/buy_goods")
    public String buyGoods() {

        try {
            String lockValue = UUID.randomUUID().toString();
            //NX+time
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_KEY, lockValue, 10L, TimeUnit.SECONDS);
            if (!flag) {
                return "抢锁失败！！！请再次尝试";
            }
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", realNumber + "");
                System.out.println(" 你已经成功秒杀商品，此时还剩余： " + realNumber + " 件 " + " \t 服务器端口 : " + serverPort);
                return " 你已经成功秒杀商品，此时还剩余： " + realNumber + " 件 " + " \t 服务器端口 : " + serverPort;
            } else {
                System.out.println(" 商品已经售罄 / 活动结束 / 调用超时，欢迎下次光临 " + " \t 服务器端口 : " + serverPort);
            }
            return " 商品已经售罄 / 活动结束 / 调用超时，欢迎下次光临 " + " \t 服务器端口 : " + serverPort;
        } finally {
            stringRedisTemplate.delete(LOCK_KEY);
        }
    }
}
        
