package cn.lastwhisper.componentregister;

import cn.lastwhisper.componentregister.bean.Person;
import cn.lastwhisper.componentregister.config.MainConfigScan;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    /**
     * 测试读取配置类
     */
    @Test
    public void test2(){
        // 2.读取配置类
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigScan.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);

        // 找到Person Bean组件的名字
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String s : namesForType) {
            System.out.println(s);
        }
    }

    /**
     * 测试读取配置文件
     */
    @Test
    public void test1(){
        // 1.读取xml
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Bean.xml");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }

}