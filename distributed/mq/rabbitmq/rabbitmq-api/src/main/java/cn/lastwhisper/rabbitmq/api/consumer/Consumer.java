package cn.lastwhisper.rabbitmq.api.consumer;

import cn.lastwhisper.rabbitmq.common.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {


    public static void main(String[] args) throws Exception {


        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Constant.ip);
        connectionFactory.setPort(Constant.port);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        String exchangeName = "test_consumer_exchange";
        String routingKey = "consumer.#";
        String queueName = "test_consumer_queue";

        channel.exchangeDeclare(exchangeName, "topic", true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        //channel.basicConsume(queueName, true, new MyConsumer(channel));
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println("-----------consume message----------");
                System.err.println("consumerTag: " + consumerTag);
                System.err.println("envelope: " + envelope);
                System.err.println("properties: " + properties);
                System.err.println("body: " + new String(body));
            }
        });


    }
}
