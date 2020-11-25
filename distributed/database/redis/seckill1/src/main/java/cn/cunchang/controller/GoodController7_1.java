package cn.cunchang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


//@RestController
public class GoodController7_1 {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String serverPort;

    private String LOCK_KEY = "seckillLock";

    private String DEL_SCRIPT = "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
            "then\n" +
            "    return redis.call(\"del\",KEYS[1])\n" +
            "else\n" +
            "    return 0\n" +
            "end";

    private DefaultRedisScript<List> getRedisScript;

    @PostConstruct
    public void init() {
        getRedisScript = new DefaultRedisScript<>();
        getRedisScript.setResultType(List.class);
        // 加载lua文本
        getRedisScript.setScriptText(DEL_SCRIPT);
        // 加载lua文件
//        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/DelLockScript.lua")));
    }

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
            List<String> keys = new ArrayList<>();
            keys.add(LOCK_KEY);
            List<String> args = new ArrayList<>();
            args.add(lockValue);
            stringRedisTemplate.execute(getRedisScript, keys, args);
        }
    }
}

