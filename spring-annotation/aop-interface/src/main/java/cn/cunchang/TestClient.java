package cn.cunchang;

import cn.cunchang.aop.EalyAopMainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cunchang
 * @date 2022/7/2 10:11 PM
 */
public class TestClient {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(EalyAopMainConfig.class);
        // 1、ProxyFactoryBean方式
//        Calculate calculateProxy = ctx.getBean("calculateProxy", Calculate.class);
//        calculateProxy.div(1, 1);

        // 2、AutoProxyCreator 后置处理器方式
        Calculate calculateProxy = ctx.getBean(Calculate.class);
        calculateProxy.div(1, 1);
    }
}
