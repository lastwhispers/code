package cn.lastwhisper.javabasic.AccessModifier.privatetest;

/**
 * @author lastwhisper
 */
public class B {
    public static void main(String[] args) {
        A aObject = new A();
        // private访问修饰，脱离类无法访问
        //System.out.println(aObject.a);
        System.out.println(aObject.getA());

        B b = new B();
        C c = b.new C();

    }

     private class C{

        private class D{

        }
    }
}
