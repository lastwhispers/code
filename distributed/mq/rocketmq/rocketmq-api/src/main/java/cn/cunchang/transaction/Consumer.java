package cn.cunchang.transaction;

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

public class Consumer {

    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transaction_producer_name_test");
        consumer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("transaction_producer_topic_test", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                aVoid(msgs);
                for (MessageExt msg : msgs) {
                    try {
                        String topic = msg.getTopic();
                        String tags = msg.getTags();
                        String keys = msg.getKeys();

                        String msgBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        System.err.println("收到事务消息--- topic: " + topic + ",tags: " + tags + ", keys: " + keys + ",body: " + msgBody);
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
        System.out.printf("tx Consumer Started.%n");
    }

    public static synchronized void aVoid(List<MessageExt> msgs){
        System.out.printf("\npull 到 %d 条消息",msgs.size());
    }

}
