package cn.cunchang.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface MessageChannel {

    // 输入型消息通道
    String PAY_NOTICE_OUTPUT = "pay-notice-output";

    @Output(PAY_NOTICE_OUTPUT)
    SubscribableChannel payNoticeOutput();

}
