package cn.lastwhisper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 基于注解配置控制器
 *   实现接收浏览器的异步请求，并返回json
 * @author lastwhisper
 */
@Controller()
@RequestMapping("springmvc")
public class ControllerDemo4 {

    /**
     * 使用response对象的getWriter()获取流对象，用流输出
     */
    @RequestMapping("testajax")
    public void sayHello(HttpServletResponse response) throws Exception {
        System.out.println("接收到了请求");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(" {\"id\":\"1\",\"username\":\"泰斯特\",\"age\":18}");
    }

}
