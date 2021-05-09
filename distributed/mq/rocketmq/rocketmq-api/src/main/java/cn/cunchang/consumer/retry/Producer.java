package cn.cunchang.consumer.retry;

import cn.cunchang.contants.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {

    public static void main(String[] args) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("test_quick_ms_consumer_name");

        producer.setNamesrvAddr(Const.NAMESRV_ADDR_MASTER_SLAVE);

        producer.start();

        for (int i = 0; i < 1; i++) {
            //	1、创建消息
            Message message = new Message("test_quick_topic",    //	主题
                    "TagA", //	标签
                    "key" + i,    // 	用户自定义的key ,唯一的标识
                    ("Hello RocketMQ" + i).getBytes());    //	消息内容实体（byte[]）
            // 2、发送消息
            SendResult sr = producer.send(message);
            System.err.println("消息发出：" + sr);
        }
        producer.shutdown();

    }
}
