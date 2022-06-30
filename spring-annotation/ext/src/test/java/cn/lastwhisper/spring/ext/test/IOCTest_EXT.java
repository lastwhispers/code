package cn.lastwhisper.spring.ext.test;

import cn.lastwhisper.spring.ext.ExtConfig;
import cn.lastwhisper.spring.ext.bean.Blue;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lastwhisper
 */
public class IOCTest_EXT {

    /**
     * 测试Spring事件
     * 查询Spring容器启动流程
     *
     * @param
     * @return void
     */
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        System.out.println("容器初始化...");

        applicationContext.publishEvent(new ApplicationEvent("发布事件"){});

        Blue blue = (Blue) applicationContext.getBean("hello");
        System.out.println("blue:"+blue);

        applicationContext.close();
    }

}
