package cn.cunchang.aop;

import cn.cunchang.Calculate;
import cn.cunchang.TulingCalculate;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * * Spring常见代理创建方式
 * * FactoryBean方式，创建单个动态代理： ProxyFactoryBean  1.指定续增强的接口:proxyInterfaces  2.指定需增强的实现类target   3.指定Advice、Advisor、Interceptor都行
 * * autoProxy方式：批量根据advisor自动创建
 * * BeanPostProcessor手动指定Advice方式  BeanNameAutoProxyCreator  1.指定Advice  2.可以使用正则来匹配 bean 的名字
 * * BeanPostProcessor自动扫描Advisor方式  DefaultAdvisorAutoProxyCreator  (不支持注解)、
 * *         AnnotationAwareAspectJAutoProxyCreator（注解）、
 * *         AspectJAwareAdvisorAutoProxyCreator（XML）
 *
 * @author cunchang
 * @date 2022/7/2 10:10 PM
 */
@Configuration
public class EalyAopMainConfig {

    // 被代理对象
    @Bean
    public Calculate tulingCalculate() {
        return new TulingCalculate();
    }

    // Advice 方式
    @Bean
    public TulingLogAdvice tulingLogAdvice() {
        return new TulingLogAdvice();
    }

    // Interceptor方式 ， 类似环绕通知
    @Bean
    public TulingLogInterceptor tulingLogInterceptor() {
        return new TulingLogInterceptor();
    }

    /**************************1、ProxyFactoryBean******************************/

    /**
     * FactoryBean方式单个： ProxyFactoryBean
     * <p>
     * 此中方法有个致命的问题，如果我们只能指定单一的Bean的AOP，
     * 如果多个Bean需要创建多个ProxyFactoryBean 。
     * 而且，我们看到，我们的拦截器的粒度只控制到了类级别，类中所有的方法都进行了拦截。
     * 接下来，我们看看怎么样只拦截特定的方法。
     */
//    @Bean
//    public ProxyFactoryBean calculateProxy() {
//        ProxyFactoryBean userService = new ProxyFactoryBean();
//        userService.setInterceptorNames("tulingLogAdvice", "tulingLogInterceptor");  // 根据指定的顺序执行
//        userService.setTarget(tulingCalculate());
//        return userService;
//    }

    /**************************1、ProxyFactoryBean******************************/


    /**************************2、ProxyFactoryBean+Advisor******************************/
    /**
     * Advisor 种类很多：
     * RegexpMethodPointcutAdvisor 按正则匹配类
     * NameMatchMethodPointcutAdvisor 按方法名匹配
     * DefaultBeanFactoryPointcutAdvisor xml解析的Advisor   <aop:before
     * InstantiationModelAwarePointcutAdvisorImpl  注解解析的advisor(@Before @After....)
     */
//    @Bean
//    public NameMatchMethodPointcutAdvisor tulingLogAspectAdvisor() {
//        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
//        // 通知(Advice)  ：是我们的通知类 没有带切点
//        // 通知者(Advisor)：是经过包装后的细粒度控制方式。 带了切点
//        advisor.setAdvice(tulingLogAdvice());
//        advisor.setMappedNames("div");
//        return advisor;
//    }

    /**
     * RegexpMethodPointcutAdvisor 按正则匹配类
     */
//    @Bean
//    public RegexpMethodPointcutAdvisor tulingLogAspectInterceptor() {
//        RegexpMethodPointcutAdvisor advisor=new RegexpMethodPointcutAdvisor();
//        advisor.setAdvice(tulingLogInterceptor());
//        advisor.setPattern("cn.cunchang.TulingCalculate.*");
//        return  advisor;
//    }

    /**
     * FactoryBean方式单个： ProxyFactoryBean
     * 控制粒度到方法
     * 问题：如果我们只能指定单一的Bean的AOP，
     * 如果多个Bean需要创建多个ProxyFactoryBean 。
     */
//    @Bean
//    public ProxyFactoryBean calculateProxy() {
//        ProxyFactoryBean userService = new ProxyFactoryBean();
//        userService.setInterceptorNames("tulingLogAspectAdvisor","tulingLogAspectInterceptor");
//        userService.setTarget(tulingCalculate());
//        return userService;
//    }

    /**************************2、ProxyFactoryBean+Advisor******************************/


    /**************************3、BeanPostProcessor手动指定Advice方式******************************/

    /**
     * autoProxy: BeanPostProcessor手动指定Advice方式  BeanNameAutoProxyCreator
     */
    @Bean
    public BeanNameAutoProxyCreator autoProxyCreator() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        //设置要创建代理的那些Bean的名字
        beanNameAutoProxyCreator.setBeanNames("tuling*");
        //设置拦截链名字(这些拦截器是有先后顺序的)
        beanNameAutoProxyCreator.setInterceptorNames("tulingLogAspectAdvisor");
        return beanNameAutoProxyCreator;
    }
    @Bean
    public NameMatchMethodPointcutAdvisor tulingLogAspectAdvisor() {
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        // 通知(Advice)  ：是我们的通知类 没有带切点
        // 通知者(Advisor)：是经过包装后的细粒度控制方式。 带了切点
        advisor.setAdvice(tulingLogAdvice());
        advisor.setMappedNames("div");
        return advisor;
    }

    /**
     * BeanPostProcessor自动扫描Advisor方式  DefaultAdvisorAutoProxyCreator
     * @return
     @Bean public DefaultAdvisorAutoProxyCreator autoProxyCreator() {
     return new DefaultAdvisorAutoProxyCreator();
     }
     */


    /**************************3、BeanPostProcessor手动指定Advice方式******************************/


}
