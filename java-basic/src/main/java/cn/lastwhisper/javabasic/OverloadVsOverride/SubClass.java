package cn.lastwhisper.javabasic.OverloadVsOverride;

/**
 * 子类
 * @author lastwhisper
 */
public class SubClass extends SuperClass {

    @Override
    protected int overrideFun(int a, int b) {// private throws Exception
        if (b == 0) {
            return Integer.MAX_VALUE;
        }
        return a / b;
    }

    public static void main(String[] args) {

        SuperClass superClass = new SuperClass();
        System.out.println("父类 " + superClass.overrideFun(5, 0));

        superClass = new SubClass();
        System.out.println("子类 " + superClass.overrideFun(5, 6));
    }

}
