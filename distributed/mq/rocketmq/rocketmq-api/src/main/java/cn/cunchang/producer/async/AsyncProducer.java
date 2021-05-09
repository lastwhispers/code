package cn.cunchang.producer.async;

import cn.cunchang.contants.Const;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

public class AsyncProducer {
    /**
     * 异步消息通常用在对响应时间敏感的业务场景，即发送端不能容忍长时间地等待Broker的响应。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("test_quick_producer_name");
        // 设置NameServer的地址
        producer.setNamesrvAddr(Const.NAMESRV_ADDR_SINGLE);
        // 启动Producer实例
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 100;
        // 根据消息数量实例化倒计时计算器
        final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);
        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("test_quick_topic",
                    "TagA",
                    "OrderID"+index,
                    "Hello RocketMQ AsyncProducer".getBytes(RemotingHelper.DEFAULT_CHARSET));
            // SendCallback接收异步返回结果的回调
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d onSuccess %s %n", index,
                            sendResult.getMsgId());
                }
                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d onException %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        // 等待5s
        countDownLatch.await(5, TimeUnit.SECONDS);
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
