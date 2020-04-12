package cn.lastwhisper.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 事件体
 */
public class BlackListEvent extends ApplicationEvent {

    private final String address;
    private final String content;

    public BlackListEvent(Object source, String address, String content) {
        super(source);
        this.address = address;
        this.content = content;
    }

    // accessor and other methods...

    public String getAddress() {
        return address;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "BlackListEvent{" +
                "address='" + address + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}