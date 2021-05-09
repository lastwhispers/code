package cn.cunchang.producer.oneway;

import cn.cunchang.contants.Const;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OnewayProducer {
    /**
     * 这种方式主要用在不特别关心发送结果的场景，例如日志发送。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer();
        // 设置NameServer的地址
        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        // 生产者的组名
        producer.setProducerGroup("test_quick_producer_name");
        // 启动Producer实例
        producer.start();

        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("test_quick_topic" /* Topic */,
                "TagA" /* Tag */,
                ("Hello RocketMQ OnewayProducer" +
                    i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);
        }
        producer.shutdown();
    }
}