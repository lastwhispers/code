package cn.lastwhisper.dubbo.controller;

import cn.lastwhisper.dubbo.DemoService;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.Future;

public class ConsumerControllerClient {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml");
        context.start();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String message = scanner.next();
            DemoService demoService = (DemoService) context.getBean("consumerService"); // 获取远程服务代理

            for (int i = 0; i < 10; i++) {
                demoService.sayHello(message+i);
            }
        }
    }
}