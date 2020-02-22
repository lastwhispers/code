package cn.lastwhisper.rocketmq.quickstart.sync;

import cn.lastwhisper.rocketmq.contants.Const;
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


    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_quick_consumer_name");

        consumer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        consumer.subscribe("test_quick_topic", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                MessageExt me = msgs.get(0);
                try {
                    String topic = me.getTopic();
                    String tags = me.getTags();
                    String keys = me.getKeys();

                    // 模拟消息消费失败异常
					if(keys.equals("key1")) {
						System.err.println("消息消费失败..");
						int a = 1/0;
					}

                    String msgBody = new String(me.getBody(), RemotingHelper.DEFAULT_CHARSET);
                    System.err.println("topic: " + topic + ",tags: " + tags + ", keys: " + keys + ",body: " + msgBody);
                } catch (Exception e) {
                    e.printStackTrace();
                    // 消息重发次数
					int recousumeTimes = me.getReconsumeTimes();
					System.err.println("recousumeTimes: " + recousumeTimes);
					if(recousumeTimes == 3) {
                        /*
                         * 对于broke消息重发到一定次数的时候
                         * 记录日志....
                         * 做补偿处理
                         */
                        System.out.println("消息无法消费 msid="+me.getMsgId());
						return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
					}

                    // broke消息重发
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.err.println("consumer start...");

    }
}
