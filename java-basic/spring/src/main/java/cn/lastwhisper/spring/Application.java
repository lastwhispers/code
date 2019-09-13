package cn.lastwhisper.spring;

import cn.lastwhisper.spring.ioc.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    /**
     * BeanDefinitionRegistry
     *  |--registerBeanDefinition将BeanDefinition注册到BeanFactory的实现类DefaultListableBeanFactory的beanDefinitionMap中
     * DefaultListableBeanFactory
     *
     * BeanFactory
     * ApplicationContext
     *
     * AbstractBeanFactory
     *  |--getBean调用doGetBean；转换beanName、从缓存中加载实例、实例化Bean、检查parentBeanFactory、初始化依赖的Bean、创建Bean
     *
     * AOP原理
     * AbstractBeanFactory
     *  |--getBean——》doGetBean——》createBean
     * AbstractAutowireCapableBeanFactory
     *  |--createBean——》doCreateBean——》initializeBean——》
     *      pplyBeanPostProcessorsAfterInitialization——》postProcessAfterInitialization
     * BeanPostProcessor由AbstractAutoProxyCreator等类实现
     *  |--postProcessAfterInitialization
     * AbstractAutoProxyCreator
     *  |--postProcessAfterInitialization——》wrapIfNecessary——》createProxy创建代理——》getProxy
     * ProxyCreatorSupport
     *  |--createAopProxy()；
     *  |--AopProxyFactory是一个接口,默认使用DefaultAopProxyFactory实现
     * DefaultAopProxyFactory
     *  |--createAopProxy(AdvisedSupport config)在这个里面就有SpringAOP的选用策略,config可由用户自定义,控制选用策略
     *
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Person person = (Person) context.getBean("person");
        System.out.println(person);
    }


}
