package cn.lastwhisper.dubbo.version;

import cn.lastwhisper.dubbo.DemoService;

import java.util.Arrays;
import java.util.List;

public class VersionServiceImplA implements DemoService {
    public String sayHello(String name) {
        System.out.println("Hello group A version 1.0.0 " + name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello group A version 1.0.0" + name;
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