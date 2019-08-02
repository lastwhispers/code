package cn.lastwhisper.jvm.classloading.passive;

/**
 * @author lastwhisper
 */
public class ConstClass {
    public static final String HELLOWORLD = "hello world";

    static {
        System.out.println("ConstClass init!");
    }
}
