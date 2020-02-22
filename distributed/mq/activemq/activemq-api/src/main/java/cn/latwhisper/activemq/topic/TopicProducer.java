package cn.latwhisper.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import cn.latwhisper.activemq.Constant;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 主题消息-发送（生产）者
 * <p>
 * ClassName: TopicProducer
 * </p>
 * <p>
 * Copyright: (c)2017 JASTAR·WANG,All rights reserved.
 * </p>
 * 
 * @author Jastar·Wang
 * @date 2017-11-15
 */
public class TopicProducer {

	// 连接工厂（在AMQ中由ActiveMQConnectionFactory实现）
	private ConnectionFactory connectionFactory;

	// 连接对象
	private Connection connection;

	// 会话对象
	private Session session;

	// 消息目的地（对于点对点模型，是Queue对象；对于发布订阅模型，是Topic对象；它们都继承或实现了该接口）
	private Destination destination;

	// 消息发送（生产）者
	private MessageProducer messageProducer;

	public static void main(String[] args) {
		TopicProducer producer = new TopicProducer();
		producer.doSend();
	}

	public void doSend() {
		try {
			/**
			 * 1.创建连接工厂<br>
			 * 构造函数有多个重载，默认连接本地MQ服务器，也可以手动设置用户名、密码、连接地址信息<br>
			 * new ActiveMQConnectionFactory(userName, password, brokerURL)
			 */
			connectionFactory = new ActiveMQConnectionFactory(Constant.BROKER_URL);

			/**
			 * 2.创建连接
			 */
			connection = connectionFactory.createConnection();

			/**
			 * 3.启动连接
			 */
			connection.start();

			/**
			 * 4.创建会话<br>
			 * param1:是否支持事务，若为true，则会忽略第二个参数，默认为SESSION_TRANSACTED<br>
			 * param2:确认消息模式，若第一个参数为false时，该参数有以下几种状态<br>
			 * -Session.AUTO_ACKNOWLEDGE：自动确认。客户端发送和接收消息不需要做额外的工作，即使接收端发生异常，
			 * 也会被当作正常发送成功 <br>
			 * -Session.CLIENT_ACKNOWLEDGE：客户端确认。客户端接收到消息后，必须调用message.
			 * acknowledge() 方法给予收到反馈，JMS服务器才会把该消息当做发送成功，并删除<br>
			 * -Session.DUPS_OK_ACKNOWLEDGE：副本确认。一旦接收端应用程序的方法调用从处理消息处返回，
			 * 会话对象就会确认消息的接收，而且允许重复确认。
			 */
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

			/**
			 * 5.创建（发送）消息目的地，【点对点模型和发布订阅模型发送的唯一不同就是这里】
			 */
			destination = session.createTopic(Constant.TOPIC_NAME);

			/**
			 * 6.创建一个消息生产者，并指定目的地
			 */
			messageProducer = session.createProducer(destination);
			/**
			 * 其他操作： 设置生产者的生产模式，默认为持久化<br>
			 * 参数有以下两种状态：<br>
			 * -DeliveryMode.NON_PERSISTENT：消息不持久化，消息被消费之后或者超时之后将从队列中删除
			 * -DeliveryMode.PERSISTENT：消息会持久化，即使接收端消费消息之后仍然会保存
			 */
			messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			/**
			 * 其他操作：设置消息的存活时间（单位：毫秒）
			 */
			messageProducer.setTimeToLive(60000);

			/**
			 * 7.创建文本消息<br>
			 * 此外，还有多种类型的消息如对象，字节……都可以通过session.createXXXMessage()方法创建
			 */
			TextMessage message = session
					.createTextMessage("send topic content");

			/**
			 * 8. 发送
			 */
			messageProducer.send(message);

			System.out.println("消息发送完成！");
			/**
			 * 如果有事务操作也可以提交事务
			 */
			session.commit();

			/**
			 * 9.关闭生产者对象（即使关闭了程序也在运行）
			 */
			messageProducer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					/**
					 * 10.关闭连接（将会关闭程序）
					 */
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
