package cn.lastwhisper.devtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/devtools")
    public String hello(){
        return "hello";
    }
}
