package com.desgin.pattern.behavioral.interpreter.springel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Create by lastwhisper on 2019/2/10
 */
public class SpringTest {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("100 * 2 + 400 * 1 + 66");
        int value = (int) expression.getValue();
        System.out.println(value);
    }
}
