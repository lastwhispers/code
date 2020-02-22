package cn.lastwhisper.lifecycle.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author lastwhisper
 */
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 在对象任何初始化之前调用
     *
     * @param bean
     * @param beanName
     * @return java.lang.Object
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization..." + beanName + "..." + bean);
        return bean;
    }

    /**
     * 在对象初始化之后调用
     *
     * @param bean
     * @param beanName
     * @return java.lang.Object
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization..." + beanName + "..." + bean);
        return bean;
    }
}
