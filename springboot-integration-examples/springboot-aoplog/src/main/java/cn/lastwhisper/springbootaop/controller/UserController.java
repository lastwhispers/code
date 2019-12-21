package cn.lastwhisper.springbootaop.controller;

import cn.lastwhisper.springbootaop.core.annotation.LogOperation;
import cn.lastwhisper.springbootaop.form.LoginForm;
import cn.lastwhisper.springbootaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lastwhisper
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @LogOperation(value = "用户登录")
    @RequestMapping(value = "/user/login")
    public String add(LoginForm loginForm) {
        return userService.login(loginForm.getUsername(), loginForm.getPassword());
    }

}
