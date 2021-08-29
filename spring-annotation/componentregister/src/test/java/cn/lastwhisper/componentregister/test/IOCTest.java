package cn.lastwhisper.componentregister.test;

import cn.lastwhisper.componentregister.bean.Blue;
import cn.lastwhisper.componentregister.bean.Person;
import cn.lastwhisper.componentregister.bean.RainBow;
import cn.lastwhisper.componentregister.config.MainConfigScan;
import cn.lastwhisper.componentregister.config.MainConfigRegister;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.Buffer;
import java.util.Map;

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
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigRegister.class);
        // 获取的是FactoryBean.getObject创建的对象
        Object factoryBean = applicationContext.getBean("colorFactoryBean");
        Object factoryBean2 = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean的类型：" + factoryBean.getClass());
        System.out.println(factoryBean == factoryBean2? "单实例" : "多实例");

        Object factoryBean3 = applicationContext.getBean("&colorFactoryBean");
        System.out.println("bean的类型：" + factoryBean3.getClass());
    }

    /**
     * 测试Import
     */
    @Test
    public void test04() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigRegister.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
        System.out.println(applicationContext.getBean(RainBow.class));
    }

    /**
     * 测试Conditional
     *
     * @param
     * @return void
     */
    @Test
    public void test03() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigRegister.class);
//        String[] definitionNames = applicationContext.getBeanDefinitionNames();
//        for (String name : definitionNames) {
//            System.out.println(name);
//        }
        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        for (Map.Entry<String, Person> entry : personMap.entrySet()) {
            System.out.println(entry);
        }
    }

    /**
     * 测试组件作用域与懒加载
     */
    @Test
    public void test02() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigRegister.class);
        System.out.println("IOC容器启动完毕");
//        Object person = applicationContext.getBean("person");
//        Object person2 = applicationContext.getBean("person");
//        System.out.println(person == person2 ? "单实例" : "多实例");
    }

    /**
     * 测试组件扫描规则是否生效
     *
     * @param
     * @return void
     */
    @Test
    public void test01() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigScan.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.printf("definitionName:%s\n",name);
        }
    }

}
