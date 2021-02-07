package cn.cunchang.simple.sync;

import cn.cunchang.contants.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class SyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer();

        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        producer.setVipChannelEnabled(false);
        // 生产者的组名
        producer.setProducerGroup("test_quick_producer_name");

        producer.start();

        for (int i = 0; i < 5; i++) {
            //	1、创建消息
            Message message = new Message("test_quick_topic",    //	主题
                    "TagA", //	标签，用于消息过滤
                    "key" + i,    // 	用户自定义的key ,用于唯一的标识
                    ("Hello RocketMQ" + i).getBytes());    //	消息内容实体（byte[]）
            // 2、发送消息
            SendResult result = producer.send(message);
            System.out.println("发送消息--- MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
        }
        producer.shutdown();

    }
}
