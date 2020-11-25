package cn.cunchang.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GoodController8 {
    @Value("${server.port}")
    private String serverPort;

    private String LOCK_KEY = "seckillLock";

    @Autowired
    private RedissonClient redisson;

    /**
     * 解决了7的问题 <p/>
     * - watch dog
     * <p>
     * 产生的问题：
     *
     * @return
     */
    @GetMapping("/buy_goods")
    public String buyGoods() {
        RLock rLock = redisson.getLock(LOCK_KEY);
        try {
            rLock.lock();

            RBucket<Integer> bucket = redisson.getBucket("goods:001");
            Integer result = bucket.get();

            int goodsNumber = result == null ? 0 : result;
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                bucket.set(realNumber);
                System.out.println(" 你已经成功秒杀商品，此时还剩余： " + realNumber + " 件 " + " \t 服务器端口 : " + serverPort);
                return " 你已经成功秒杀商品，此时还剩余： " + realNumber + " 件 " + " \t 服务器端口 : " + serverPort;
            } else {
                System.out.println(" 商品已经售罄 / 活动结束 / 调用超时，欢迎下次光临 " + " \t 服务器端口 : " + serverPort);
            }
            return " 商品已经售罄 / 活动结束 / 调用超时，欢迎下次光临 " + " \t 服务器端口 : " + serverPort;
        } finally {
            // 极端情况下会出现问题
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }
}

