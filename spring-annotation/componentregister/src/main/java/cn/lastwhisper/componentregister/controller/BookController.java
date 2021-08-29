package cn.lastwhisper.componentregister.controller;

import cn.lastwhisper.componentregister.bean.Red;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Bean("myRed")
    public Red red(){
        return new Red();
    }

}
