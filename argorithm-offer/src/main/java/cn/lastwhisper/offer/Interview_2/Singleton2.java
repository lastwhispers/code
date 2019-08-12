package cn.lastwhisper.offer.Interview_2;

/**
 * 懒汉式之同步锁
 * @author lastwhisper
 */
public class Singleton2 {
    // 类属性，所有引用共享一个地址
    private static Singleton2 instance;

    private Singleton2() {

    }
    //
    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
