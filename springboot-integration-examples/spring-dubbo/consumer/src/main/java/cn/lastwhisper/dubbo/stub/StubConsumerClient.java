package cn.lastwhisper.dubbo.stub;

import cn.lastwhisper.dubbo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class StubConsumerClient {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-stub-consumer.xml");
        context.start();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.next();
            DemoService demoService = (DemoService) context.getBean("consumerService"); // 获取远程服务代理

            String hello = demoService.sayHello(message); // 执行远程方法
            System.out.println(hello); // 显示调用结果
        }
    }
}