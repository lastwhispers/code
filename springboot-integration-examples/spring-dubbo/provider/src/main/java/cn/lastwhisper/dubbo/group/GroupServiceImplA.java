package cn.lastwhisper.dubbo.group;

import cn.lastwhisper.dubbo.DemoService;

import java.util.Arrays;
import java.util.List;

public class GroupServiceImplA implements DemoService {
    public String sayHello(String name) {
        System.out.println("Hello group A " + name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello group A " + name;
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