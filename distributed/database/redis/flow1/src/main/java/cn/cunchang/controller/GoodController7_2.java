package cn.cunchang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


//@RestController
public class GoodController7_2 {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String serverPort;

    private String LOCK_KEY = "seckillLock";

    /**
     * 解决了6的问题 <p/>
     * - lua <p/>
     * - 事务
     * <p>
     * 产生的问题：续租
     *
     * @return
     */
    @GetMapping("/buy_goods")
    public String buyGoods() {
        String lockValue = UUID.randomUUID().toString();
        try {
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
            while (true) {
                stringRedisTemplate.watch(LOCK_KEY); // 加事务，乐观锁
                if (lockValue.equalsIgnoreCase(stringRedisTemplate.opsForValue().get(LOCK_KEY))) {
                    stringRedisTemplate.setEnableTransactionSupport(true);
                    stringRedisTemplate.multi();
                    // 开始事务
                    stringRedisTemplate.delete(LOCK_KEY);
                    List<Object> list = stringRedisTemplate.exec();
                    if (list == null) {
                        // 如果等于 null ，就是没有删掉，删除失败，再回去 while 循环那再重新执行删除
                        continue;
                    }
                }
                // 如果删除成功，释放监控器，并且 breank 跳出当前循环
                stringRedisTemplate.unwatch();
                break;
            }
        }
    }
}

