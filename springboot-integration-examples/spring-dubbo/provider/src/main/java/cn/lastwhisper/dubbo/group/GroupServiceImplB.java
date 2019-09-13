package cn.lastwhisper.dubbo.group;

import cn.lastwhisper.dubbo.DemoService;

import java.util.Arrays;
import java.util.List;

public class GroupServiceImplB implements DemoService {
    public String sayHello(String name) {
        System.out.println("Hello group B " + name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello group B " + name;
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
        String str = "Group B =" + message;
        return Arrays.asList(str);
    }
}