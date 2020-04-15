package com.desgin.advance.decorator_proxy;

/**
 * 泰森
 * @author lastwhisper
 * @date 2020/4/12
 */
public class BoxerImpl implements Boxer {

    private String name;

    @Override
    public void fight() {
        hook();
        duck();
        straight();
    }

    private void duck() {
        System.out.println("拳手：闪避");
    }

    private void hook() {
        System.out.println("拳手：勾拳");
    }

    private void straight() {
        System.out.println("拳手：直拳");
    }
}
