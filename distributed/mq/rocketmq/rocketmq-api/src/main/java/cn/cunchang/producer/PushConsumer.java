package cn.cunchang.producer;

import cn.cunchang.contants.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

public class PushConsumer {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();

        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);

        //CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，跳过历史消息
        //CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setConsumerGroup("test_quick_consumer_name");

        //设置consumer所订阅的Topic和Tag，*代表全部的Tag
        consumer.subscribe("test_quick_topic", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("\npull 到 %d 条消息",msgs.size());
                for (MessageExt msg : msgs) {
                    try {
                        String topic = msg.getTopic();
                        String tags = msg.getTags();
                        String keys = msg.getKeys();

                        String msgBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        System.err.println("消费消息--- topic: " + topic + ",tags: " + tags + ", keys: " + keys + ",body: " + msgBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // broke消息重发
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                //消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.err.println("consumer start...");

    }
}
