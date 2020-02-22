
package cn.lastwhisper.activemq.amq.consumer.queue;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 队列消息消费者1
 * 
 * @author Jastar·Wang
 * @date 2018年4月4日
 * @since 1.0
 */
@Component
public class QueueReceiver1 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			/*
			 * 实际项目中拿到String类型的message(通常是JSON字符串)之后，会进行反序列化成对象，做进一步的处理
			 */
			System.out.println("QueueReceiver1接收到消息:" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
