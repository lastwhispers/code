package cn.lastwhisper.feature5.classloader;

/**
 * 隐式加载与显示加载
 * @author lastwhisper
 */
public class LoadDiff {
    public static void main(String[] args) throws ClassNotFoundException {
        // 在SpringIOC中,为了加快初始化速度,大量使用了延迟加载技术,使用ClassLoader不会执行“链接”、“初始化”的步骤。
        ClassLoader classLoader = DemoObj.class.getClassLoader();
        // 连接Mysql，注册驱动com.mysql.jdbc.Driver
        //Class<?> clazz = Class.forName("cn.lastwhisper.jdk5.feature.classloader.DemoObj");
    }
}
