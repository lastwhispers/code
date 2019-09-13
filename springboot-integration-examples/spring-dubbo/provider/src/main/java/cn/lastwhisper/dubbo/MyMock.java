package cn.lastwhisper.dubbo;

import java.util.List;

/**
 * @author lastwhisper
 */
public class MyMock implements DemoService {

    @Override
    public String sayHello(String name) {
        return "抱歉，订单人数过多，请稍后重试";
    }

    @Override
    public String sayHello2(String name) {
        return "抱歉，订单人数过多，请稍后重试";
    }

    @Override
    public List<String> mergerTest(String message) {
        return null;
    }
}
