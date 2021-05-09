package cn.cunchang.producer.sync;

import cn.cunchang.contants.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class SyncProducer {

    /**
     * 这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
     * @param args
     * @throws MQClientException
     * @throws RemotingException
     * @throws MQBrokerException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer();
        // 设置NameServer的地址
        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        // 生产者的组名
        producer.setProducerGroup("test_quick_producer_name");
        // 启动Producer实例
        producer.start();

        for (int i = 0; i < 5; i++) {
            //	1、创建消息
            Message message = new Message("test_quick_topic",    //	主题
                    "TagA", //	标签，用于消息过滤
                    "key" + i,    // 	用户自定义的key ,用于唯一的标识
                    ("Hello RocketMQ SyncProducer" + i).getBytes());    //	消息内容实体（byte[]）
            // 2、发送消息
            SendResult result = producer.send(message);
            System.out.println("发送消息--- MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();

    }
}
