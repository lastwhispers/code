package cn.cunchang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


//@RestController
public class GoodController4 {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String serverPort;

    private String LOCK_KEY = "seckillLock";

    /**
     * 解决了3的问题
     * 产生的问题：加锁+过期，非原子操作
     * @return
     */
    @GetMapping("/buy_goods")
    public String buyGoods() {

        try {
            String lockValue = UUID.randomUUID().toString();
            //NX
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_KEY, lockValue);
            stringRedisTemplate.expire(LOCK_KEY,10L, TimeUnit.SECONDS);
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
        
