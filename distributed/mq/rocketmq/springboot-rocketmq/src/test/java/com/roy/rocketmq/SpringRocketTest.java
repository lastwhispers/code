package com.roy.rocketmq;

import com.alibaba.fastjson.TypeReference;
import com.roy.rocketmq.domain.OrderPaidEvent;
import com.roy.rocketmq.domain.ProductWithPayload;
import com.roy.rocketmq.domain.User;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：楼兰
 * @date ：Created in 2020/11/28
 * @description:
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRocketTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void sendMessageTest(){
        String springTopic="TestTopic";
        //发送字符消息
        SendResult sendResult = rocketMQTemplate.syncSend(springTopic, "Hello, World!");
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);

        sendResult = rocketMQTemplate.syncSend(springTopic, new User().setUserAge((byte) 18).setUserName("Kitty"));
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);

        sendResult = rocketMQTemplate.syncSend(springTopic, MessageBuilder.withPayload(
                new User().setUserAge((byte) 21).setUserName("Lester")).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build());
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);

        //发送对象消息
        rocketMQTemplate.asyncSend(springTopic, new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }
        });

        //发送指定TAG的消息
        rocketMQTemplate.convertAndSend(springTopic + ":tag0", "I'm from tag0");  // tag0 will not be consumer-selected
        System.out.printf("syncSend topic %s tag %s %n", springTopic, "tag0");
        rocketMQTemplate.convertAndSend(springTopic + ":tag1", "I'm from tag1");
        System.out.printf("syncSend topic %s tag %s %n", springTopic, "tag1");

        //同步发送消息并且返回一个String类型的结果。
        String replyString = rocketMQTemplate.sendAndReceive(springTopic, "request string", String.class);
        System.out.printf("send %s and receive %s %n", "request string", replyString);

        //同步发送消息并且返回一个Byte数组类型的结果。
        byte[] replyBytes = rocketMQTemplate.sendAndReceive(springTopic, MessageBuilder.withPayload("request byte[]").build(), byte[].class, 3000);
        System.out.printf("send %s and receive %s %n", "request byte[]", new String(replyBytes));

        //同步发送一个带hash参数的请求(排序消息)，并返回一个User类型的结果
        User requestUser = new User().setUserAge((byte) 9).setUserName("requestUserName");
        User replyUser = rocketMQTemplate.sendAndReceive(springTopic, requestUser, User.class, "order-id");
        System.out.printf("send %s and receive %s %n", requestUser, replyUser);
        //同步发送一个带延迟级别的消息(延迟消息)，并返回一个泛型结果
        ProductWithPayload<String> replyGenericObject = rocketMQTemplate.sendAndReceive(springTopic, "request generic",
                new TypeReference<ProductWithPayload<String>>() {
                }.getType(), 30000, 2);
        System.out.printf("send %s and receive %s %n", "request generic", replyGenericObject);

        //异步发送消息，返回String类型结果。
        rocketMQTemplate.sendAndReceive(springTopic, "request string", new RocketMQLocalRequestCallback<String>() {
            @Override public void onSuccess(String message) {
                System.out.printf("send %s and receive %s %n", "request string", message);
            }

            @Override public void onException(Throwable e) {
                e.printStackTrace();
            }
        });
        //异步发送消息，并返回一个User类型的结果。
        rocketMQTemplate.sendAndReceive(springTopic, new User().setUserAge((byte) 9).setUserName("requestUserName"), new RocketMQLocalRequestCallback<User>() {
            @Override public void onSuccess(User message) {
                System.out.printf("send user object and receive %s %n", message.toString());
            }

            @Override public void onException(Throwable e) {
                e.printStackTrace();
            }
        }, 5000);
        //发送批量消息
        List<Message> msgs = new ArrayList<Message>();
        for (int i = 0; i < 10; i++) {
            msgs.add(MessageBuilder.withPayload("Hello RocketMQ Batch Msg#" + i).
                    setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build());
        }

        SendResult sr = rocketMQTemplate.syncSend(springTopic, msgs, 60000);

        System.out.printf("--- Batch messages send result :" + sr);
    }
}
