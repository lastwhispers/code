package cn.lastwhisper.activemq.amq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 队列消息生产者
 * 
 * @author Jastar·Wang
 * @date 2018年4月4日
 * @since 1.0
 */
@Component
public class QueueSender {

	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;

	/**
	 * 发送一条消息到指定的队列（目标）
	 * 
	 * @param queueName
	 *            队列名称
	 * @param message
	 *            消息内容
	 */
	public void send(String queueName, final String message) {
		jmsTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				// 其他的还有createXXXMessage(...)
				return session.createTextMessage(message);
			}
		});
	}

}
