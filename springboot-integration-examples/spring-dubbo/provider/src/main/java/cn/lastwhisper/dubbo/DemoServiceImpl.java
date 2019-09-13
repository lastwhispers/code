package cn.lastwhisper.dubbo;

import java.util.List;

public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        System.out.println("Hello " + name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name;
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
        return null;
    }
}