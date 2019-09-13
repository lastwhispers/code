package cn.lastwhisper.dubbo;

import java.util.List;

/**
 * @author lastwhisper
 */
public class MyStub implements DemoService {

    // 注入Proxy的构造函数
    private final DemoService demoService;

    public MyStub(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) {
        System.out.println("sayHello ");//被调用直接打印
        if ("123".equals(name)) {
            return "抱歉该值无法接受";//返回
        } else {
            name = "sayHello " + name;
            return this.demoService.sayHello(name);//返回
        }
    }

    @Override
    public String sayHello2(String name) {
        System.out.println("sayHello2");
        return this.sayHello2(name);
    }

    @Override
    public List<String> mergerTest(String message) {
        System.out.println("mergerTest");
        return this.mergerTest(message);
    }


}
