package cn.cunchang.batch;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * BatchMessageChannel
 *
 * @author jianzhu
 * @date 2021/3/19 18:47
 */
public interface BatchMessageChannel {

    /**
     * ================================================== Manager
     */

    // 输出型消息通道，发送消息：Manager -> Workers
    String DAILY_ORDER_MANAGER_OUTPUT = "daily-order-manager-output";

    @Output(DAILY_ORDER_MANAGER_OUTPUT)
    MessageChannel dailyOrderOutgoingRequestsToWorkers();

    String CHECK_MANAGER_OUTPUT = "check-manager-output";

    @Output(CHECK_MANAGER_OUTPUT)
    MessageChannel checkOutgoingRequestsToWorkers();

    /**
     * ================================================== Worker
     */

    // 输入型消息通道，接收 Manager 消息
    String DAILY_ORDER_WORKER_INPUT = "daily-order-worker-input";

    @Input(DAILY_ORDER_WORKER_INPUT)
    SubscribableChannel dailyOrderIncomingRequestsFromManager();

    String CHECK_WORKER_INPUT = "check-worker-input";

    @Input(CHECK_WORKER_INPUT)
    SubscribableChannel checkIncomingRequestsFromManager();

}
