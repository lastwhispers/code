package cn.lastwhisper.rabbitmq.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lastwhisper
 * @date 2019/12/11
 */
public class Fanout {
    /**
     * 分列模式模式，消息生产者
     */
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("finout/applicationContext-rabbitmq-producer.xml");
        RabbitTemplate rabbitTemplate =
                (RabbitTemplate) context.getBean("rabbitTemplate");
        rabbitTemplate.convertAndSend("exchange.fanout_test", "", "分列模式");
        ((ClassPathXmlApplicationContext) context).close();
    }

}
