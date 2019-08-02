package cn.lastwhisper.jvm.classloading.initiative;

/**
 * @author lastwhisper
 */
public class SubClass extends SuperClass {

    public static int subvalue = 456;

    static {
        System.out.println("SubClass Static code init!");
    }

    public SubClass() {
        System.out.println("SubClass constructor init! ");
    }

    public static int getSubvalue() {
        return subvalue;
    }

    public static void setSubvalue(int subvalue) {
        SubClass.subvalue = subvalue;
    }
}
