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
 * 主题消息生产者
 * 
 * @author Jastar·Wang
 * @date 2018年4月4日
 * @since 1.0
 */
@Component
public class TopicSender {

	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTemplate;

	/**
	 * 发送一条消息到指定的队列（目标）
	 * 
	 * @param topicName
	 *            队列名称
	 * @param message
	 *            消息内容
	 */
	public void send(String topicName, final String message) {
		jmsTemplate.send(topicName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				// 其他的还有createXXXMessage(...)
				return session.createTextMessage(message);
			}
		});
	}

}
