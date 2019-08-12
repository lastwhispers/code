package com.desgin.pattern.structural.proxy.my.dynamicproxy;

/**
 * @author lastwhisper
 */
public class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        int result = a + b;
        return result;
    }

    @Override
    public int subtract(int a, int b) {
        int result = a - b;
        return result;
    }
}
