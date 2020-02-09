package cn.lastwhisper.rabbitmq.api.confirm;

import cn.lastwhisper.rabbitmq.common.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Producer {


    public static void main(String[] args) throws Exception {
        //1 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Constant.ip);
        connectionFactory.setPort(Constant.port);
        connectionFactory.setVirtualHost("/");

        //2 获取C	onnection
        Connection connection = connectionFactory.newConnection();

        //3 通过Connection创建一个新的Channel
        Channel channel = connection.createChannel();


        //4 指定我们的消息投递模式: 消息的确认模式
        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.save";

        //5 发送一条消息
        String msg = "Hello RabbitMQ Send confirm message!";
        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        //6 添加一个确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("-------no ack!-----------");
            }

            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("-------ack!-----------");
            }
        });


    }
}
