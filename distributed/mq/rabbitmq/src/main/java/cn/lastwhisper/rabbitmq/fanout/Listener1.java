package cn.lastwhisper.rabbitmq.fanout;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lastwhisper
 * @date 2019/12/11
 */
public class Listener1 {
    /**
     *分列模式，消息消费者
     */
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("finout/applicationContext-rabbitmq-consumer.xml");
    }

}
