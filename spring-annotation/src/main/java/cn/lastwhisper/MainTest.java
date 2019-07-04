package cn.lastwhisper;

import cn.lastwhisper.bean.Person;
import cn.lastwhisper.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        // 1.读取xml
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Bean.xml");
        //Person person = (Person) applicationContext.getBean("person");
        //System.out.println(person);
        // 2.读取配置类
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);

        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (int i = 0; i < namesForType.length; i++) {
            String s = namesForType[i];
            System.out.println(s);
        }
    }
}