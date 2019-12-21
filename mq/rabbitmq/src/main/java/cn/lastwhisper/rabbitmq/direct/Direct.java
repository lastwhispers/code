package cn.lastwhisper.rabbitmq.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lastwhisper
 * @date 2019/12/11
 */
public class Direct {
    /**
     * 直连模式，消息生产者
     */
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("direct/applicationContext-rabbitmq-producer.xml");
        RabbitTemplate rabbitTemplate =
                (RabbitTemplate) context.getBean("rabbitTemplate");
        rabbitTemplate.convertAndSend("", "queue.test", "直接模式");
        ((ClassPathXmlApplicationContext) context).close();
    }

}
