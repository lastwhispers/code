package cn.cunchang;

import cn.cunchang.message.MessageChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(MessageChannel.class)
@SpringBootApplication
public class RocketmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqProducerApplication.class, args);
    }

}
