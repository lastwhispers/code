package cn.lastwhisper.xmlannotation.test;

import cn.lastwhisper.xmlannotation.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        // 由于是XML配置方式，对应的Spring容器是ClassPathXmlApplicationContext,传入配置文件告知Spring去哪读取配置信息
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        // 从容器中获取Person
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }
}