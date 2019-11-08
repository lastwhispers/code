package cn.lastwhisper.springvalidated.controller;

import cn.lastwhisper.springvalidated.bean.Foo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FooController {

    @RequestMapping("/foo")
    public String foo(@Validated Foo foo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                //...
            }
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/drink")
    public String drink(@Validated({Foo.Adult.class}) Foo foo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                // fieldError
            }
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/live")
    public String live(@Validated Foo foo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                //...
            }
            return "fail";
        }
        return "success";
    }

}