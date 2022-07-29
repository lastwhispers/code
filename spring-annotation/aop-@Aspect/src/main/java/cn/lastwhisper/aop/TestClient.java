package cn.lastwhisper.aop;

import cn.lastwhisper.aop.config.MainConfigOfAop;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAop.class);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(2, 1);

    }

}