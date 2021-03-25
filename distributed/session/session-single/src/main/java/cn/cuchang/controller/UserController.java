package cn.cuchang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserController {

    // http://localhost:8080/user/login?username=admin
    @GetMapping("/login")
    public String login(@RequestParam String username, HttpSession session) {
        //账号密码正确
        session.setAttribute("login_user", username);
        return "登录成功";
    }

    @GetMapping("/info")
    public String info(HttpSession session) {
        return "当前登录的是：" + session.getAttribute("login_user") + " || sessionId:" + session.getId();
    }

}
