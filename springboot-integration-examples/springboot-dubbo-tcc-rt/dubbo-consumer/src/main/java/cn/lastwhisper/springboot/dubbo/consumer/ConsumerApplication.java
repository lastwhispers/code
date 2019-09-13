package cn.lastwhisper.springboot.dubbo.consumer;

import cn.lastwhisper.springboot.dubbo.consumer.transaction.TransactionConsumer;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDubboConfiguration
public class ConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
        //QuickstartConsumer quickstartConsumer = (QuickstartConsumer) context.getBean("quickstartConsumer");
        //quickstartConsumer.sendMessage("你好");

        TransactionConsumer consumer = (TransactionConsumer) context.getBean("transactionConsumer");
        consumer.sendMessage("123");
    }

}
