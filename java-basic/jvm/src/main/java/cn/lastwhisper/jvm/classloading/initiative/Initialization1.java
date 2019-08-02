package cn.lastwhisper.jvm.classloading.initiative;

/**
 * 主动引用触发初始化、演示一
 * @author lastwhisper
 */
public class Initialization1 {
    // 遇到new、getstatic、putstatic或invokestatic这4条字节码指令时，
    // 如果类没有进行过初始化，则需要先触发其初始化。
    public static void main(String[] args) {
        // 1.new字节码指令
        //SubClass subClass = new SubClass();

        // 2.getstatic字节码指令
        // 被final修饰、已在编译期把结果放入常量池的静态字段除外
        int subvalue = SubClass.subvalue;

        // 3.setstatic字节码指令
        // 被final修饰、已在编译期把结果放入常量池的静态字段除外
        //SubClass.subvalue = 789;

        // 4.invokestatic字节码指令
        //SubClass.getSubvalue();
    }
}
