package cn.lastwhisper.offer.Interview_2;

/**
 * 懒汉式之静态内部类
 * @author lastwhisper
 */
public class Singleton4 {
    private Singleton4() {
    }

    private static class InnerClass {
        private static Singleton4 singleton = new Singleton4();
    }

    private static Singleton4 getInstance() {
        return InnerClass.singleton;
    }
}
