package cn.jdbc;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

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

    public static List<Driver> driverList;

    static {
        registerDriver();
    }

    private static void registerDriver() {
        ServiceLoader<Driver> services = ServiceLoader.load(Driver.class);
        driverList = new ArrayList<>();
        for (Driver driver : services) {
            driverList.add(driver);
        }
    }


    public static Driver getDriver(String url,
                                   String user, String password) {
        if (Objects.isNull(driverList)) {
            throw new IllegalStateException("未找到 Driver 的实现");
        }
        System.out.println("找到" + driverList.size() + "个jdbc实现驱动");
        for (Driver driver : driverList) {
            if (driver.available(url, user, password)) {
                return driver;
            }
        }
        throw new IllegalStateException("未找到 Driver 的实现");
    }

}
