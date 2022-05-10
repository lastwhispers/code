package cn.cunchang.producer;

import cn.cunchang.message.MessageChannel;
import cn.cunchang.message.PayResultMsg;
import org.apache.rocketmq.common.message.MessageConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageChannel messageChannel;

    /**
     * http://127.0.0.1:18080/producer/send
     *
     * @return
     */
    @GetMapping("/send")
    public boolean send() {
        // <2>创建 Message

        String payNo = UUID.randomUUID().toString();

        PayResultMsg payResultMsg = new PayResultMsg();
        payResultMsg.setPayNo(payNo);
        payResultMsg.setPayAccNo("10086");
        payResultMsg.setRevAccNo("10010");
        payResultMsg.setAmount(1000000L);
        payResultMsg.setResult(1);

        // <3>创建 Spring Message 对象
        Message<PayResultMsg> message = MessageBuilder.withPayload(payResultMsg)
                .setHeader(MessageConst.PROPERTY_TAGS, "pay_notice_tag") // 设置 Tag
                .setHeader(MessageConst.PROPERTY_KEYS, payNo) // 设置 key，方便在控制台查找。
                .build();
        System.out.println("topic:pay-result,msg:"+message);
        // <4>发送消息
        boolean sendResult = messageChannel.payNoticeOutput().send(message);
        logger.info("send,topic:pay-result,msg:{},sendResult:{}",message,sendResult);
        return sendResult;
    }

    @GetMapping("/send_delay")
    public boolean sendDelay() {
        // 创建 Message
        PayResultMsg payResultMsg = new PayResultMsg();
        payResultMsg.setPayNo(UUID.randomUUID().toString());
        payResultMsg.setPayAccNo("10086");
        payResultMsg.setRevAccNo("10010");
        payResultMsg.setAmount(1000000L);
        payResultMsg.setResult(1);

        // <3>创建 Spring Message 对象
        Message<PayResultMsg> message = MessageBuilder.withPayload(payResultMsg)
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, "3") // 设置延迟级别为3，10 秒后消费。
                .build();
        // 发送消息
        boolean sendResult = messageChannel.payNoticeOutput().send(message);
        logger.info("sendDelay,topic:pay-result,msg:{},sendResult:{}",message,sendResult);
        return sendResult;
    }


}
