package cn.cunchang;

import cn.cunchang.listener.MessageChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(MessageChannel.class)
@SpringBootApplication
public class RocketmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqConsumerApplication.class, args);
    }

}
