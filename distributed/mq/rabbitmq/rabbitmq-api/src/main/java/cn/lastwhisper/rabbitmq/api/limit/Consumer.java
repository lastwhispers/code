package cn.lastwhisper.rabbitmq.api.limit;

import cn.lastwhisper.rabbitmq.common.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Consumer {

	
	public static void main(String[] args) throws Exception {
		
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(Constant.ip);
		connectionFactory.setPort(Constant.port);
		connectionFactory.setVirtualHost("/");
		
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		
		String exchangeName = "test_qos_exchange";
		String queueName = "test_qos_queue";
		String routingKey = "qos.#";
		
		channel.exchangeDeclare(exchangeName, "topic", true, false, null);
		channel.queueDeclare(queueName, true, false, false, null);
		channel.queueBind(queueName, exchangeName, routingKey);
		
		/*
		 * prefetchSize：消息限制大小，一般为0，不做限制。
		 * prefetchCount：一次处理消息的个数，一般设置为1
		 * global：一般为false。true，在channel级别做限制；false，在consumer级别做限制
		 */
		channel.basicQos(0, 1, false);

		// 限流方式  第一件事就是 autoAck设置为 false
		channel.basicConsume(queueName, false, new MyConsumer(channel));
		
		
	}
}
