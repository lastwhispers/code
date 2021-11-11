package cn.cunchang.schedule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务触发spring batch job
 * @author cunchang
 * @date 2021/9/16 3:33 下午
 */
@RestController
public class EntryXxljob {

    @GetMapping("/test")
    public String test() {


        return "success";
    }

}
