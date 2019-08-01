package cn.lastwhisper.componentregister.test;

import cn.lastwhisper.componentregister.config.MainConfig;
import cn.lastwhisper.componentregister.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lastwhisper
 */
public class IOCTest {

    /**
     * 测试FactoryBean
     *
     * @param
     * @return void
     */
    @Test
    public void test05() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

        Object factoryBean = applicationContext.getBean("colorFactoryBean");
        Object factoryBean2 = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean的类型：" + factoryBean.getClass());
        System.out.println(factoryBean == factoryBean2);

        Object factoryBean3 = applicationContext.getBean("&colorFactoryBean");
        System.out.println("bean的类型：" + factoryBean3.getClass());
    }

    /**
     * 测试Import
     *
     * @param
     * @return void
     */
    @Test
    public void test04() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    /**
     * 测试Conditional
     *
     * @param
     * @return void
     */
    @Test
    public void test03() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    /**
     * 测试组件作用域与懒加载
     *
     * @param
     * @return void
     */
    @Test
    public void test02() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        System.out.println("IOC容器启动完毕");
        Object person = applicationContext.getBean("person");
        Object person2 = applicationContext.getBean("person");
        System.out.println(person == person2);
    }

    /**
     * 测试组件扫描规则是否生效
     *
     * @param
     * @return void
     */
    @Test
    public void test01() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

}
