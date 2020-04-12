package cn.lastwhisper.spring.event;

import org.springframework.context.event.EventListener;

/**
 * 事件接收者
 */
public class BlackListNotifier {

    private String notificationAddress;

    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }

    @EventListener
    public void processBlackListEvent(BlackListEvent event) {
        System.out.println("接收到消息：" + event);
        // notify appropriate parties via notificationAddress...
    }
}