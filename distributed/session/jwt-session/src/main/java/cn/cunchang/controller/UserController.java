package cn.cunchang.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static cn.cunchang.config.LoginIntercepter.*;

@RequestMapping("/user")
@RestController
public class UserController {

    /**
     * http://localhost:8080/user/login?username=admin&password=123456
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);
        String token = JWT.create()
                .withClaim(CURRENT_USER, username)
                .withClaim(UID, 1)
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                .sign(algorithm);
        return token;
    }

    /**
     * http://localhost:8080/user/current
     *
     * @param username
     * @return
     */
    @GetMapping("/current")
    public String current(@RequestAttribute String username) {
        return "当前登录的是：" + username;
    }


    /**
     * http://localhost:8080/user/invalidate
     *
     *  jwt无法使session失效
     *
     * @return
     */
    @GetMapping("/invalidate")
    public void invalidate() {

    }


}
