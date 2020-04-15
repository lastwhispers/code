package cn.lastwhisper.feature5.spi.spi_client;

import cn.lastwhisper.feature5.spi.spi_interface.People;

import java.util.ServiceLoader;

/**
 * 用来模拟用户测试, 依赖spi-core和spi-boy/spi-gril(至少一个实现,否则会报错)
 * @author lastwhisper
 */
public class Client {
    public static void main(String[] args) {
        ServiceLoader<People> serviceLoader = ServiceLoader.load(People.class);
        for (People people : serviceLoader) {
            people.sayHello();
        }
    }
}
