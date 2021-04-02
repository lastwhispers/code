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
     * <p>
     * 浏览器每次请求都会携带cookie，服务器端重新设置cookie
     * request headers Cookie: JSESSIONID=2BB312D30D6F1246412D081615906D3B
     * response headers Set-Cookie: JSESSIONID=9A7E034F309B2B0616737CA9937030D7; Path=/; HttpOnly
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
