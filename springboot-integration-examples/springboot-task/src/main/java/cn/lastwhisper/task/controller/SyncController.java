package cn.lastwhisper.task.controller;

import cn.lastwhisper.task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 同步阻塞式
 */
@RestController
public class SyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/hello")
    public String hello() {
        String threadName = Thread.currentThread().getName();
        long threadId = Thread.currentThread().getId();
        System.out.println(getClass()+" threadName:" + threadName + " , threadId:" + threadId);
        asyncService.hello();
        return "success";
    }
}
