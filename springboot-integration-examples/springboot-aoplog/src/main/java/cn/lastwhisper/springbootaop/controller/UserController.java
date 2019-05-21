package cn.lastwhisper.springbootaop.controller;

import cn.lastwhisper.springbootaop.core.annotation.LogAnno;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lastwhisper
 * @desc
 * @email gaojun56@163.com
 */
@RestController
public class UserController {
    /**
     * @desc 这里为了方便直接在controller上进行aop日志记录，也可以放在service上。
     * @author lastwhisper
     * @Param
     * @return
     */
    @LogAnno(operateType = "添加用户")
    @RequestMapping(value = "/user/add")
    public void add() {
        System.out.println("向数据库中添加用户!!");
    }
}
