package cn.monitor4all.springbootwebsocket.model;


public class ChatMessage {
    // 消息类型
    private MessageType type;
    // 消息内容
    private String content;
    // 发送者
    private String sender;

    public enum MessageType {
        CHAT,//消息
        JOIN,//加入
        LEAVE//离开
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
