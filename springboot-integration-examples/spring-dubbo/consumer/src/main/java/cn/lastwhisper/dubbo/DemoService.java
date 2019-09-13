package cn.lastwhisper.dubbo;

import java.util.List;

/**
 * @author lastwhisper
 */
public interface DemoService {
    String sayHello(String name);
    String sayHello2(String name);

    List<String> mergerTest(String message);
}
