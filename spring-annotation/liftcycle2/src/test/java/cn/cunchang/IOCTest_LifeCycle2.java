package cn.cunchang;

import cn.cunchang.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lastwhisper
 */
public class IOCTest_LifeCycle2 {

    @Test
    public void test01() {
        // 初始化容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("bean容器初始化...");
        // 关闭容器
        applicationContext.close();
    }

}
