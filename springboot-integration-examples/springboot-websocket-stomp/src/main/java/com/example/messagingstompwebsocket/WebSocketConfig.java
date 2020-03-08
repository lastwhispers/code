package com.example.messagingstompwebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// 使一个简单的基于内存的message broker的问候消息回客户机在目的地前缀/topic
		config.enableSimpleBroker("/topic");
		// /app前缀为消息绑定方法注释@MessageMapping. 这个前缀将用于定义所有消息映射
		// 例如,/app/hello端点的吗GreetingController.greeting()映射到处理方法
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 注册/gs-guide-websocket端点,使SockJS后备选项,这样交替传输可以使用如果WebSocket不可用。
		// SockJS客户端将尝试连接/gs-guide-websocket
		registry.addEndpoint("/gs-guide-websocket").withSockJS();
	}

}
