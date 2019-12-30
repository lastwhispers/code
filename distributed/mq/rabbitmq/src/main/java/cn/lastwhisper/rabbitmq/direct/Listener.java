package cn.lastwhisper.rabbitmq.direct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lastwhisper
 * @date 2019/12/11
 */
public class Listener {
    /**
     * 直连模式，消息消费者
     */
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("direct/applicationContext-rabbitmq-consumer.xml");
    }

}
