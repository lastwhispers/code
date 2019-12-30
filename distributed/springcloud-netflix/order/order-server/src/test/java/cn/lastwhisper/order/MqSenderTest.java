package cn.lastwhisper.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 发送MQ消息
 * @author lastwhisper
 * @date 2019/10/31
 */
public class MqSenderTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void testMqSend() {
        amqpTemplate.convertAndSend("myQueue", "now :" + new Date());
    }

    @Test
    public void testMqSendOrder() {
        amqpTemplate.convertAndSend("myOrder", "computer","now :" + new Date());
    }

}
