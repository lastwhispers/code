package cn.lastwhisper.spring.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /**
     * https://docs.spring.io/spring/docs/5.1.8.RELEASE/spring-framework-reference/core.html#context-functionality-events
     * 使用@EventListener注解ApplicationListener实现
     *
     */
    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event) {
        System.out.println("UserService。。监听到的事件：" + event);
    }
}