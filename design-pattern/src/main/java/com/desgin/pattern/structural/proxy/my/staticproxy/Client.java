package com.desgin.pattern.structural.proxy.my.staticproxy;

/**
 * @author lastwhisper
 */
public class Client {
    public static void main(String[] args) {
        CalculatorStaticProxy calculatorProxy = new CalculatorStaticProxy(new CalculatorImpl());
        int result = calculatorProxy.proxyAdd(5, 3);

        result = calculatorProxy.proxySubtract(5, 3);
    }
}
