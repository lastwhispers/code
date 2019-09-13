package cn.lastwhisper.dubbo.stub;

import cn.lastwhisper.dubbo.DemoService;

import java.util.Arrays;
import java.util.List;

public class StubServiceImpl implements DemoService {
    public String sayHello(String name) {
        System.out.println("Hello stub " + name);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello-provider-stub " + name;
    }

    @Override
    public String sayHello2(String name) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> mergerTest(String message) {
        String str = "Group A =" + message;
        return Arrays.asList(str);
    }
}