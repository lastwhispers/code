package cn.lastwhisper.validated.controller;

import cn.lastwhisper.validated.bean.DiyBean;
import cn.lastwhisper.validated.bean.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
public class FooController {

    @Autowired
    private Validator validator;

    /**
     * http://localhost:8080/foo?name=xujingfeng&email=000&age=19
     *
     */
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
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                //...
            }
            return "fail";
        }
        return "success";
    }

    /**
     * 自定义校验
     *  http://localhost:8080/diyValidate?flag1=%22a%20b%22
     */
    @RequestMapping("/diyValidate")
    @ResponseBody
    public String diyValidate(@Validated DiyBean diyBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                //...
            }
            return "fail";
        }

        return "success";
    }

    /**
     * 手动校验
     */
    @RequestMapping("/validate")
    public String validate() {
        Foo foo = new Foo();
        foo.setAge(22);
        foo.setEmail("000");

        Set<ConstraintViolation<Foo>> set = validator.validate(foo);
        for (ConstraintViolation<Foo> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }

        return "success";
    }

}