package cn.cunchang.listener;

import cn.cunchang.message.PayResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PayNoticeConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @StreamListener(MessageChannel.PAY_NOTICE_INPUT)
    public void payNoticeInputOnMessage(@Payload PayResultMsg message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
