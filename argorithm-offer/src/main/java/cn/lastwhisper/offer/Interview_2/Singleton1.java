package cn.lastwhisper.offer.Interview_2;

/**
 * 恶汉式之类加载时初始化
 * @author lastwhisper
 */
public class Singleton1 {
    // 类属性，所有引用共享一个地址
    private static Singleton1 instance = new Singleton1();

    private Singleton1() {
    }
    public static Singleton1 getInstance() {
        return instance;
    }
}
