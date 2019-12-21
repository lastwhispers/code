package cn.lastwhisper.rabbitmq.direct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author Administrator
 */
public class MessageConsumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("接收到消息：" + new String(message.getBody()));
    }
}