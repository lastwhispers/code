package cn.cunchang;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author cunchang
 * @date 2022/5/23 11:25 AM
 */
public class LifeCycleBean implements BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("==== 1.BeanFactoryAware.setBeanFactory: " + beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("==== 2.ApplicationContextAware.setApplicationContext: " + applicationContext);
    }

    // ==== 4.BeanPostProcessor.postProcessBeforeInitialization

    /**
     * 对象创建完成，并赋值之后调用
     * 由CommonAnnotationBeanPostProcessor处理, 相当于xml中的init-method配置
     */
    @PostConstruct
    public void initBean() {
        System.out.println("==== 4.CommonAnnotationBeanPostProcessor.postConstruct");
    }

    // 对象创建完成，并赋值之后调用
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("==== 5.InitializingBean.afterPropertiesSet");
    }

    public void initMethod() {
        System.out.println("==== 6.init-method");
    }

    // ==== 8.BeanPostProcessor.postProcessAfterInitialization

    /**
     * 容器销毁bean时调用
     * 由CommonAnnotationBeanPostProcessor处理, 相当于xml中的destroy-method配置
     */
    @PreDestroy
    public void destroyBean() {
        System.out.println("==== 8.CommonAnnotationBeanPostProcessor.preDestroy");
    }

    //  容器销毁bean时调用
    @Override
    public void destroy() throws Exception {
        System.out.println("==== 9.DisposableBean.destroy");
    }

    public void destroyMethod() {
        System.out.println("==== 10.destroy-method");
    }

}