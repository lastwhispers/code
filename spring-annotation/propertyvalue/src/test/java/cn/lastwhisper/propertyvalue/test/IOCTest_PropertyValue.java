package cn.lastwhisper.lifecycle.test;

import cn.lastwhisper.propertyvalue.bean.Person;
import cn.lastwhisper.propertyvalue.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author lastwhisper
 */
public class IOCTest_PropertyValue {

    @Test
    public void test01() {
        //1.创建IOC容器
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        System.out.println("容器初始化...");

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickname");
        System.out.println(property);

        //printBeans(applicationContext);
    }

    private void printBeans(ApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

}
