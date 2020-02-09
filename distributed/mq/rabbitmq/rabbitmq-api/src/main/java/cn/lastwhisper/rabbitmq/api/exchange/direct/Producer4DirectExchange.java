package cn.lastwhisper.rabbitmq.api.exchange.direct;

import cn.lastwhisper.rabbitmq.common.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer4DirectExchange {
    /**
     *
     */
    public static void main(String[] args) throws Exception {

        //1 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Constant.ip);
        connectionFactory.setPort(Constant.port);
        connectionFactory.setVirtualHost("/");

        //2 创建Connection
        Connection connection = connectionFactory.newConnection();
        //3 创建Channel
        Channel channel = connection.createChannel();
        //4 声明
        String exchangeName = "test_direct_exchange";
        String routingKey = "test.direct111";
        //5 发送

        String msg = "Hello World RabbitMQ 4  Direct Exchange Message 111 ... ";
        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());


        //channel.close();
        //connection.close();
    }

}
