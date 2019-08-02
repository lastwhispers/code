package cn.lastwhisper.javabasic.AccessModifier.publictest;

/**
 * @author lastwhisper
 */
public class B {
    public static void main(String[] args) {
        A aObject = new A();
        // public访问修饰，在任何类中都可以访问
        aObject.a = 10;
    }
}
