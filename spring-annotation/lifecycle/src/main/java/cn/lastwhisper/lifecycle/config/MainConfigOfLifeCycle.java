package cn.lastwhisper.lifecycle.config;

import cn.lastwhisper.lifecycle.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 容器管理bean的生命周期，容器在bean进行到当前生命周期时，调用自定义的初始化与销毁方法。
 *
 * bean的生命周期：创建——》初始化——》销毁
 * 创建（构造器）：
 *      单实例：容器启动时进行创建对象
 *      多实例：容器启动时并不会创建对象，使用时才会创建
 * 初始化：
 *      对象创建完成，并赋值好，进行初始化
 * 销毁
 *      单实例：容器关闭时进行销毁
 *      多实例：容器不会管理多实例bean，不会调用销毁方法
 *
 * 1）指定初始化和销毁方法
 *      通过@Bean(initMethod = "init", destroyMethod = "destroy")指定初始化和销毁方法
 *      相当于xml中的配置：init-method="init" destroy-method="destroy"
 * 2）通过bean实现InitializingBean（定义初始化逻辑）、DisposableBean（定义销毁逻辑）
 *
 * 3）JSR250规范
 *      *@PostConstruct：在bean创建完成并且属性赋值完成，来执行初始化方法
 *      *@PreDestroy：在容器销毁bean之前调用
 * 4）BeanPostProcessor：对所有bean的后置处理器，在bean初始化前后进行处理
 *     该接口有两个方法：
 *      postProcessBeforeInitialization：在对象任何初始化（initMethod、afterPropertiesSet）之前调用
 *      postProcessAfterInitialization：在对象初始化之后调用
 *
 *   BeanPostProcessor原理：
 *      AbstractAutowireCapableBeanFactory.doCreateBean{
 *           populateBean(beanName, mbd, instanceWrapper); // 给对象中的字段赋值
 *           initializeBean{
 *               applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);// 调用postProcessBeforeInitialization处理器
 *               invokeInitMethods(beanName, wrappedBean, mbd); // 调用对象的初始化方法
 *               applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);//调用postProcessAfterInitialization处理器
 *           }
 *      }
 *
 * Spring底层BeanPostProcessor的使用：
 *   AsyncAnnotationBeanPostProcessor 处理@Async注解的方法
 *   ApplicationContextAwareProcessor 处理实现ApplicationContextAware的类
 *   BeanValidationPostProcessor 处理JSR-303数据校验
 *   InitDestroyAnnotationBeanPostProcessor 处理@PostConstruct和@PreDestroy注解
 *   AutowiredAnnotationBeanPostProcessor 处理@Autowire注解
 *
 * @author lastwhisper
 */
@ComponentScan("cn.lastwhisper.lifecycle.bean")
@Configuration
public class MainConfigOfLifeCycle {

    //@Scope("singleton")
    ////@Scope("prototype")
    //@Bean(initMethod = "init", destroyMethod = "destroy")
    //public Car car() {
    //    return new Car();
    //}
}
