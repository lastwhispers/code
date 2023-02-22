package cn.cunchang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * http://localhost:8080/user/login?username=admin&password=123456
     * <p>
     * 与前端配合，登录成功后，cookie记录token
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        //账号密码正确
        String key = "token_" + UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(key, username, 3600, TimeUnit.SECONDS);
        return key;
    }

    /**
     * http://localhost:8080/user/current
     *
     * @param token
     * @return
     */
    @GetMapping("/current")
    public String current(@RequestHeader String token) {
        return "当前登录的是：" + stringRedisTemplate.opsForValue().get(token);
    }

    /**
     * http://localhost:8080/user/invalidate
     *
     * @param token
     * @return
     */
    @GetMapping("/invalidate")
    public String invalidate(@RequestHeader String token) {
        System.out.println("失效前 sessionId:" + token);
        stringRedisTemplate.delete(token);
        System.out.println("失效后 sessionId:" + null);
        System.out.println("--------------------------------");
        return "session已失效" + " || sessionId:" + token;
    }
}
