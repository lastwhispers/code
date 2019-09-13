package cn.lastwhisper.dubbo;

import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.Future;

public class ConsumerClient {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml");
        context.start();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String message = scanner.next();
            DemoService demoService = (DemoService) context.getBean("consumerService"); // 获取远程服务代理
            // 测试负载均衡
            //for (int i = 0; i < 10; i++) {
            //    String hello = demoService.sayHello(message+i); // 执行远程方法
            //    System.out.println(hello); // 显示调用结果
            //}

            // 测试同步调用 //关闭超时时间
            //Instant start = Instant.now();
            //demoService.sayHello(message);//业务3秒
            //Instant end = Instant.now();
            //demoService.sayHello2(message);//业务4秒
            //Instant end2 = Instant.now();
            ////sayHello执行完成耗时3127
            ////ayHello2执行完成耗时7129
            //System.out.println("sayHello执行完成耗时"+ Duration.between(start,end).toMillis());
            //System.out.println("sayHello2执行完成耗时"+ Duration.between(start,end2).toMillis());
            // 测试异步调用
            Instant start = Instant.now();
            demoService.sayHello(message);//业务3秒
            Future<String> sendFuture = RpcContext.getContext().getFuture();
            Instant end = Instant.now();
            demoService.sayHello2(message);//业务4秒
            Future<String> sendFuture2 = RpcContext.getContext().getFuture();
            Instant end2 = Instant.now();
            //sayHello执行完成耗时37
            //sayHello2执行完成耗时38
            System.out.println(sendFuture.get()+" sayHello执行完成耗时"+ Duration.between(start,end).toMillis());
            System.out.println(sendFuture2.get()+" sayHello2执行完成耗时"+ Duration.between(start,end2).toMillis());

        }
    }
}