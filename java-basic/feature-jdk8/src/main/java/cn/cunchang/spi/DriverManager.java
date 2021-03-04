package cn.cunchang.spi;


import java.util.*;

/**
 * spi-core: 是提供给用户使用的核心jar文件,
 * 同样依赖于interface项目, 用户使用时需要引入spi-core.jar和厂商具体实现的jar
 * <p>
 * 提供者注册 API ( Provider Registration API)，这是提供者用来注册实现的
 * DriverManager.registerDriver是提供者注册API
 * <p>
 * 服务访问 API (Service Access API)，这是客户端用来获取服务的实例
 * DriverManager.getConnection 是服务访问 API
 *
 * @author lastwhisper
 */
public class DriverManager {

    public static Driver driver;

    static {
        ServiceLoader<Driver> services = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = services.iterator();
        List<Driver> driverList = new ArrayList<>();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            driverList.add(driver);
        }
        if (driverList.size() < 1) {
            throw new IllegalStateException("未找到 Driver 的实现");
        }
        if (driverList.size() > 1) {
            throw new IllegalStateException("找到多个 Driver 的实现: " + driverList);
        }
        driver = driverList.get(0);
    }

    public static Driver getDriver() {
        if (Objects.isNull(driver)) {
            throw new IllegalStateException("未找到 Driver 的实现");
        }
        return driver;
    }

}
