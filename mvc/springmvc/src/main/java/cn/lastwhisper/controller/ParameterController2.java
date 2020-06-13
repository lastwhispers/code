package cn.lastwhisper.controller;

import cn.lastwhisper.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @author lastwhisper
 * @date 2020/6/3
 */
@Controller
@RequestMapping("param2")
public class ParameterController2 {

    @RequestMapping("test1")
    @ResponseBody
    public String testParam1(List<Object> list) {
        for (Object o : list) {
            System.out.println(o);
        }
        return "success";
    }

    @RequestMapping("test2")
    @ResponseBody
    public String testParam2(@RequestBody List<Object> list) {
        for (Object o : list) {
            System.out.println(o);
        }
        return "success";
    }
}


