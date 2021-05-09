package cn.cunchang.consumer.pull;

import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

public class PullScheduleConsumer {

    public static void main(String[] args) throws MQClientException {
        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService("GroupName1");

        scheduleService.setMessageModel(MessageModel.CLUSTERING);
        scheduleService.registerPullTaskCallback("TopicTest", new PullTaskCallback() {

            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext context) {
                MQPullConsumer consumer = context.getPullConsumer();
                try {

                    long offset = consumer.fetchConsumeOffset(mq, false);
                    if (offset < 0)
                        offset = 0;

                    PullResult pullResult = consumer.pull(mq, "*", offset, 32);
                    System.out.printf("%s%n", offset + "\t" + mq + "\t" + pullResult);
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                    consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());

                    context.setPullNextDelayTimeMillis(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        scheduleService.start();
    }
}
