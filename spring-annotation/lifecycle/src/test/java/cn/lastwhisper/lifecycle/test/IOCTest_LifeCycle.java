package cn.lastwhisper.lifecycle.test;

import cn.lastwhisper.lifecycle.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lastwhisper
 */
public class IOCTest_LifeCycle {
    @Test
    public void test01() {
        // 初始化容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("bean容器初始化...");
        //applicationContext.getBean("car");
        //applicationContext.getBean("cat");
        //applicationContext.getBean("dog");
        // 关闭容器
        applicationContext.close();
    }
}
