package cn.lastwhisper.jvm.classloader;

/**
 * 加载、解析、初始化
 * @author lastwhisper
 */
public class ClassLifeCycle {
    public static void main(String[] args) throws ClassNotFoundException {
        // 类.class会加载，不会执行“链接”、“初始化”的步骤，因为AppClassLoad.loadClass的resolve=false
        // 验证：DemoObj的类构造器未执行
        // Class<?> clazz1 = DemoObj.class;

        // 通过Class.forName会执行加载、链接、初始化
        // 验证：DemoObj的类构造器执行了
        Class<?> clazz2 = Class.forName("cn.lastwhisper.classloader.DemoObj");
    }
}
