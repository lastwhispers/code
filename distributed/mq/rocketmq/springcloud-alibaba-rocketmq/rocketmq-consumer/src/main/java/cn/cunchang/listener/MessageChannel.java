package cn.cunchang.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MessageChannel {

    // 输入型消息通道
    String PAY_NOTICE_INPUT = "pay-notice-input";

    @Input(PAY_NOTICE_INPUT)
    SubscribableChannel payNoticeInput();

}
