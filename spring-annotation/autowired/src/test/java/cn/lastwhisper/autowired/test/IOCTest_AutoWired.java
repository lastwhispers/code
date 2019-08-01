package cn.lastwhisper.autowired.test;

import cn.lastwhisper.autowired.bean.Boss;
import cn.lastwhisper.autowired.bean.Car;
import cn.lastwhisper.autowired.bean.Person;
import cn.lastwhisper.autowired.config.MainConfigOfAutoWired;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lastwhisper
 */
public class IOCTest_AutoWired {

    /**
     * 测试@Autowired的作用范围
     *
     * @param
     * @return void
     */
    @Test
    public void test02() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutoWired.class);
        System.out.println("容器初始化...");
        Boss boss = applicationContext.getBean(Boss.class);
        Car car = applicationContext.getBean(Car.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(boss);
        System.out.println(car);
        System.out.println(person);
    }

    /**
     * 测试@Autowired、@Resource、@Inject
     *
     * @param
     * @return void
     */
    @Test
    public void test01() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutoWired.class);
        System.out.println("容器初始化...");

        //BookService bookService = applicationContext.getBean(BookService.class);
        //System.out.println(bookService);

        //BookDao bookDao = applicationContext.getBean(BookDao.class);
        //System.out.println(bookDao);


        //printBeans(applicationContext);
    }

    private void printBeans(ApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

}
