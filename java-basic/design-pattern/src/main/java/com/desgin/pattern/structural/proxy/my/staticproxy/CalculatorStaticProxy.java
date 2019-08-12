package com.desgin.pattern.structural.proxy.my.staticproxy;

/**
 * 静态代理
 * @author lastwhisper
 */
public class CalculatorStaticProxy {
    // 被代理类
    private Calculator calculator;
    // 代理类通过构造器接收被代理类
    public CalculatorStaticProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    public int proxyAdd(int a, int b) {
        System.out.println("proxy代理，add日志开始记录");
        int result = calculator.add(a, b);
        System.out.println("proxy代理，add日志结束，已记录到数据库中");
        return result;
    }

    public int proxySubtract(int a, int b) {
        System.out.println("proxy代理，subtract日志开始记录");
        int result = calculator.subtract(a, b);
        System.out.println("proxy代理，subtract日志结束，已记录到数据库中");
        return result;
    }
}
