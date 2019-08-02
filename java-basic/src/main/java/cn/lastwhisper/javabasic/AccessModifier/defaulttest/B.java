package cn.lastwhisper.javabasic.AccessModifier.defaulttest;

//import cn.lastwhisper.javabasic.AccessModifier.A1;

/**
 * @author lastwhisper
 */
public class B {
    public static void main(String[] args) {
        //A1 aObject = new A1();
        A aObject = new A();
        // default修饰符，同包可以访问，不同包不可以访问
        aObject.a = 10;
    }
}
