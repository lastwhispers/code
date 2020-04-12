package cn.lastwhisper.spring.event;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lastwhisper
 */
public class IOCTest_Event {

    /**
     * 测试Spring事件
     */
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(EventConfig.class);
        System.out.println("容器初始化...");
        EmailService emailService = applicationContext.getBean(EmailService.class);
        emailService.sendEmail("john.doe@example.org","hello world");
        emailService.sendEmail("blacklist@example.org","hello world");

        applicationContext.close();
    }

}
