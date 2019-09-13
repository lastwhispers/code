package cn.lastwhisper.interview.OverloadVsOverride;

/**
 * 父类
 * @author lastwhisper
 */
public class SuperClass {
    protected int overrideFun(int a, int b) throws ArithmeticException {
        int divisor = a / b;
        return divisor;
    }
}

