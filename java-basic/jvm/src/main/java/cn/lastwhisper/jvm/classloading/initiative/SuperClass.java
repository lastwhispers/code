package cn.lastwhisper.jvm.classloading.initiative;

/**
 * @author lastwhisper
 */
public class SuperClass {
    public static int value = 123;

    static {
        System.out.println("SuperClass Static code init!");
    }

    public SuperClass() {
        System.out.println("SuperClass constructor init! ");
    }

    public static int getValue() {
        return value;
    }

    public static void setValue(int value) {
        SuperClass.value = value;
    }
}

