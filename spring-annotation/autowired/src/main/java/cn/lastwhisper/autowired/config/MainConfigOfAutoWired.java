package cn.lastwhisper.autowired.config;

import cn.lastwhisper.autowired.bean.Car;
import cn.lastwhisper.autowired.bean.Person;
import cn.lastwhisper.autowired.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *  1）@Autowired 自动注入
 *  （1）默认按类型去容器找对应组件（bean）：applicationContext.getBean(BookDao.class)
 *  （2）当出现多个相同类型组件时，按属性名称（类名首字母小写）作为组件id去容器中查找
 *      applicationContext.getBean("bookDao")
 *  （3）@Qualifier("bookDao2")与@Autowired同时使用：指定使用组件id，而不是按属性名称
 *  （4）@Autowired(required = false)：没有找到待注入组件时，可以不注入
 *  （5）@Primary()：首选组件，同时可以使用@Qualifier指定组件id
 *
 *  2）Spring还支持使用@Resource(JSR250)和@Inject(JSR330)规范
 *  （1）@Resource：默认按属性名称进行自动装配
 *              支持设置属性名称name
 *              不支持@Qualifier、@Primary()、required = false
 *  （2）@Inject：需要导入javax.inject包
 *              支持@Qualifier、@Primary()
 *              不支持required = false、设置属性名称name等
 *  （3）@Autowired是Spring定义的，脱离了Spring无法使用。@Resource和@Inject是Java规范，可以脱离Spring使用。
 *
 *   AutowiredAnnotationBeanPostProcessor完成自动装配
 *
 *  3）@Autowired作用范围：属性、构造器、参数、方法。都是从容器中获取组件进行注入
 *  （1）方法：@Bean+方法参数，参数从容器中自动获取，可以省略@Autowired
 *  （2）构造器：如果组件只有一个有参构造器，参数从容器中自动获取，可以省略@Autowired
 *
 *  4）自定义组件想要使用Spring容器底层组件（ApplicationContext、StringValueResolver等）
 *      自定义组件需要实现对应的xxxAware接口，Spring在创建自定义组件对象时会调用接口规定的相关方法注入相关组件
 *      Aware将Spring一些底层组件注入到自定义的bean中。
 *      Aware能实现这样的功能是因为xxxProcessor，如：ApplicationContextAware——》ApplicationContextAwareProcessor
 * @author lastwhisper
 */
@Configuration
@ComponentScan({"cn.lastwhisper.autowired.controller", "cn.lastwhisper.autowired.service",
        "cn.lastwhisper.autowired.dao", "cn.lastwhisper.autowired.bean"})
public class MainConfigOfAutoWired {
    // @Primary() Antowired多个bean时首选bean
    @Primary()
    @Bean("bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setNowBookDaoLable(2);
        return bookDao;
    }
    // car参数从容器中自动获取，可以不写@Autowired
    @Bean
    public Person person(Car car){
        Person person = new Person();
        person.setCar(car);
        return person;
    }

}
