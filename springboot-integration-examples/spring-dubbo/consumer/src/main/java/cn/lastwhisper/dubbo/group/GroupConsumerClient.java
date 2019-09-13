package cn.lastwhisper.dubbo.group;

import cn.lastwhisper.dubbo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class GroupConsumerClient {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-group-consumer.xml");
        context.start();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.next();
            DemoService demoService = (DemoService) context.getBean("consumerService"); // 获取远程服务代理

            String hello = demoService.sayHello(message ); // 执行远程方法
            List<String> strings = demoService.mergerTest(message);
            for (String str : strings) {
                System.out.println(str);
            }
            //System.out.println(hello); // 显示调用结果
        }
    }
}