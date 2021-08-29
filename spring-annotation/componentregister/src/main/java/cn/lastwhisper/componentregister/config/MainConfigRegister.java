package cn.lastwhisper.componentregister.config;

import cn.lastwhisper.componentregister.bean.Color;
import cn.lastwhisper.componentregister.bean.ColorFactoryBean;
import cn.lastwhisper.componentregister.bean.Person;
import cn.lastwhisper.componentregister.bean.Red;
import cn.lastwhisper.componentregister.condition.LinuxCondition;
import cn.lastwhisper.componentregister.condition.MacCondition;
import cn.lastwhisper.componentregister.condition.WindowsCondition;
import cn.lastwhisper.componentregister.filter.MyTypeFilter;
import cn.lastwhisper.componentregister.register.MyImportBeanDefinitionRegistrar;
import cn.lastwhisper.componentregister.selector.MyImportSelector;
import org.springframework.context.annotation.*;

/**
 * @author lastwhisper
 */

//@Configuration
//@ComponentScan(excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
//})
@ComponentScan(value = "cn.lastwhisper")
//@Conditional({WindowsCondition.class})
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfigRegister {
    /**
     *  Scope取值：prototype、singleton、request、session。默认是singleton
     *      prototype：多实例，IOC容器启动时并不会创建对象，只有获取时才会创建。懒加载
     *      singleton：单实例，IOC容器启动时创建对象放入IOC容器中。
     *      request：一次请求创建一个实例（需要web环境）
     *      session：一个session创建一个实例（需要web环境）
     *
     *  懒加载：当IOC容器启动时不创建对象，第一次使用时才创建。
     *      singleton默认不是懒加载，加上@Lazy变成懒加载。
     *      prototype是懒加载
     */
    @Scope("singleton")
//    @Scope("prototype")
//    @Lazy // 通过初始化容器，查看是否输出"Person对象创建完毕"
    @Bean("person")
    public Person person() {
//        System.out.println("Person对象创建完毕");
        return new Person("tom", 22);
    }

    /**
     * 注解@Conditional({Condition.class})： 按条件判断，满足条件给容器注册bean
     *
     *  System.out.println(System.getProperty("os.name").toLowerCase());
     *
     * 现在下面的两个bean注册到IOC容器是要条件的：
     *  1.如果系统是windows，给容器注册("bill")
     *  2.如果系统是linux，给容器注册("linus")
     *  3.如果系统是mac，给容器注册("jobs")
     */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person windows() {
        return new Person("Bill Gates", 66);
    }

    /**
     * -Dos.name 变成linux
     */
    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person linux() {
        return new Person("linus", 45);
    }


    @Conditional({MacCondition.class})
    @Bean("jobs")
    public Person jobs() {
        return new Person("Steve Jobs", 45);
    }

    /**
     *  给容器中注册组件的几种方式：
     *  1）、组件扫描+组件注册注解 @Controller、@Service、@Repository、@Component（自己写的类）
     *
     *  2）、@Bean（应用场景：注册第三方包里面的组件，如连接池等）
     *
     *  3）、@Import（快速给容器中注册一个组件；应用场景：注册第三方包里面的组件，如连接池等）
     *      （1）、@Import(要注册到容器的组件)：容器会自动注册这个组件，id是全限定名
     *      （2）、ImportSelector：返回需要注册的组件的全限定名
     *      （3）、ImportBeanDefinitionRegistrar：手动注册bean到容器
     *
     *  4）、使用Spring提供的FactoryBean（应用场景：与其他框架整合使用，比如mybatis的SqlSessionFactoryBean）
     *      Spring 中有两种类型的Bean，一种是普通Bean，另一种是工厂Bean 即 FactoryBean
     *      （1）、默认获取的是getObject返回的对象
     *      （2）、要获取FactoryBean本身，我们需要给id前面加上一个“&”符号：&colorFactoryBean
     *
     *
     */
    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }

}
