package cn.lastwhisper.springbootwebsocket.config;

import cn.lastwhisper.springbootwebsocket.handler.MyHandler;
import cn.lastwhisper.springbootwebsocket.interceptor.MyHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author lastwhisper
 * @desc
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyHandshakeInterceptor myHandshakeInterceptor;

    /**
     * ws://localhost:9501/ws
     * http://www.websocket-test.com/
     * http://coolaf.com/tool/chattest
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(myHandler(), "/ws")
                .setAllowedOrigins("*")
                .addInterceptors(myHandshakeInterceptor);
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }
}
