package cn.cuchang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserController {

    /**
     * spring-session 与 tomcat-session 机制相同
     * request headers Cookie: JSESSIONID=2BB312D30D6F1246412D081615906D3B; SESSION=ZWJmNjliNDEtZWYxNy00NDg5LWJkZTktMjVkZGRlMzg3NjRl
     * response headers Set-Cookie: SESSION=MzE2ZmE1YzMtMzQ0ZC00ZDQ1LWJlYTgtN2NiZGNmMWVlODk4; Path=/; HttpOnly; SameSite=Lax
     *
     */

    /**
     * http://localhost:8080/user/login?username=admin&password=123456
     *
     * @param username
     * @param session
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        //账号密码正确
        session.setAttribute("login_user", username);
        return "登录成功" + " || sessionId:" + session.getId();
    }


    /**
     * http://localhost:8080/user/current
     *
     * @param session
     * @return
     */
    @GetMapping("/current")
    public String current(HttpSession session) {
        return "当前登录的是：" + session.getAttribute("login_user") + " || sessionId:" + session.getId();
    }


    /**
     * http://localhost:8080/user/invalidate
     *
     * @param session
     * @return
     */
    @GetMapping("/invalidate")
    public String invalidate(HttpSession session) {
        System.out.println("失效前 sessionId:" + session.getId());
        session.invalidate();
        System.out.println("失效后 sessionId:" + session.getId());
        System.out.println("--------------------------------");
        return "session已失效" + " || sessionId:" + session.getId();
    }


}
