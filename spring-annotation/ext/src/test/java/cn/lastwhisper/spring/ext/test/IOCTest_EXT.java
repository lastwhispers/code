package cn.lastwhisper.spring.ext.test;

import cn.lastwhisper.spring.ext.ExtConfig;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lastwhisper
 */
public class IOCTest_EXT {


    /**
     * 测试Spring事件
     *
     * @param
     * @return void
     */
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        System.out.println("容器初始化...");

        applicationContext.publishEvent(new ApplicationEvent("发布事件"){});

        applicationContext.close();
    }

}
