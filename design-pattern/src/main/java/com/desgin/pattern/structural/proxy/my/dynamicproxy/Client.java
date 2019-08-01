package com.desgin.pattern.structural.proxy.my.dynamicproxy;

/**
 * @author lastwhisper
 */
public class Client {
    public static void main(String[] args) {
        Calculator calculator = (Calculator) new CalculatorDynamicProxy(new CalculatorImpl()).getProxy();
        System.out.println(calculator.add(1, 2));
    }
}
