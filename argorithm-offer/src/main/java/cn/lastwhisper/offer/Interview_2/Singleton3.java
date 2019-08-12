package cn.lastwhisper.offer.Interview_2;

/**
 * 懒汉式之DCL
 * @author lastwhisper
 */
public class Singleton3 {
    // 类属性，所有引用共享一个地址
    // volatile保证“可见性”、不保证“原子性”“有序性”
    private static volatile Singleton3 instance;

    private Singleton3() {

    }

    private static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
