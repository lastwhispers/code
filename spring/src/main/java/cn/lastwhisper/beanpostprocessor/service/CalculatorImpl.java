package cn.lastwhisper.beanpostprocessor.service;

import org.springframework.stereotype.Component;

/**
 * @author lastwhisper
 */
@Component
public class CalculatorImpl implements Calculator {
    public int add(int a, int b) {
        int result = a + b;
        return result;
    }
}
