package cn.lastwhisper.springbootwebsocket.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author lastwhisper
 * @desc
 */
public class MyHandler extends TextWebSocketHandler {
    /**
     * 测试服务器向客户端推送消息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Integer uid = (Integer) session.getAttributes().get("uid");
        System.out.println("uid=" + uid + " 获取到消息 >> " + message.getPayload());

        session.sendMessage(new TextMessage("消息已收到"));
        if ("10".equals(message.getPayload())) {
            for (int i = 0; i < 10; i++) {
                session.sendMessage(new TextMessage("消息 -> " + i));
                Thread.sleep(100);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Integer uid = (Integer) session.getAttributes().get("uid");
        session.sendMessage(new TextMessage(uid + ", 你好！欢迎连接到ws服务"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("断开连接！");
    }
}
