package cn.cunchang.spring;

import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.math.BigDecimal;

/**
 * <dependency>
 *             <groupId>org.springframework</groupId>
 *             <artifactId>spring-expression</artifactId>
 *             <version>5.2.7.RELEASE</version>
 *         </dependency>
 *
 * @author kaisui
 * @description
 * @date 2022/11/16
 */
public class SpringELTest {

    // spring el解析表达式
    @Test
    public void test1() {
        ExpressionParser parser = new SpelExpressionParser();
        BigDecimal ratio = new BigDecimal("1.1");
        String compareExpression = "1<ratio && ratio<1.2".replace("ratio",ratio.toString());
        System.out.println(parser.parseExpression(compareExpression).getValue(Boolean.class));
    }

    // spring el关联spring上下文
    @Test
    public void test2() {
        ExpressionParser parser = new SpelExpressionParser();
        System.out.println(parser.parseExpression("").getValue(Boolean.class));
    }
}
