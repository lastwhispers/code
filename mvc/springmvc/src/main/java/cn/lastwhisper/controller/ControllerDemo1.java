package cn.lastwhisper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 处理器映射器 DispatcherServlet.initHandlerMappings
 * @author lastwhisper
 */
@Controller
public class ControllerDemo1 {

    /**
     * 处理请求的控制器方法
     * @return
     */
    @RequestMapping("hello")
    public String sayHello() {
        System.out.println("控制器方法执行了");
        return "success";
    }

    @RequestMapping("hello2")
    public String sayHello2() {
        System.out.println("控制器方法执行了 2");
        return "success";
    }

}
