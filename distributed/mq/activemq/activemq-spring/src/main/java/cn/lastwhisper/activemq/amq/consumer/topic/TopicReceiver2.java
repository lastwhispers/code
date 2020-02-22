package cn.lastwhisper.activemq.amq.consumer.topic;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 主题消息消费者2
 * 
 * @author Jastar·Wang
 * @date 2018年4月4日
 * @since 1.0
 */
@Component
public class TopicReceiver2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("TopicReceiver2接收到消息:" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
