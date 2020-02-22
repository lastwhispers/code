package cn.lastwhisper.rocketmq.quickstart.async;

import cn.lastwhisper.rocketmq.contants.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {

    public static void main(String[] args) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("test_async_producer_name");

        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);

        producer.start();

        for (int i = 0; i < 5; i++) {
            //	1、创建消息
            Message message = new Message("test_quick_topic",    //	主题
                    "TagA", //	标签
                    "key" + i,    // 	用户自定义的key ,唯一的标识
                    ("Hello RocketMQ" + i).getBytes());    //	消息内容实体（byte[]）
            // 2、发送消息
            //SendResult sr = producer.send(message);
            //System.err.println("消息发出：" + sr);
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.err.println("msgId: " + sendResult.getMsgId() + ", status: " + sendResult.getSendStatus());
                }

                @Override
                public void onException(Throwable e) {
                    e.printStackTrace();
                    System.err.println("------发送失败");
                }
            });

        }

        //producer.shutdown();

    }
}
