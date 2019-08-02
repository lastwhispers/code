package cn.lastwhisper.jvm.classloading.initiative;

/**
 * @author lastwhisper
 */
public class MethodHandleClass {

    static {
        System.out.println("MethodHandleClass Static code init!");
    }
    public MethodHandleClass() {
        System.out.println("MethodHandleClass constructor init!");
    }

    // REF_invokeStatic
    public static void testREF_invokeStatic(String str) {
        System.out.println(str);
    }

}
