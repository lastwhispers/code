package cn.cunchang;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

/**
 * @author cunchang
 * @date 2022/5/23 11:25 AM
 */
@Service
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在对象任何初始化之前调用
     * @param bean ioc容器中的每一个Bean对象
     * @param beanName Bean对象对应的name
     * @return java.lang.Object
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LifeCycleBean) {
            System.out.println("==== 3.BeanPostProcessor.postProcessBeforeInitialization: " + beanName);
        }
        return bean;
    }

    /**
     * 在对象初始化之后调用
     *
     * @param bean ioc容器中的每一个Bean对象
     * @param beanName Bean对象对应的name
     * @return java.lang.Object
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LifeCycleBean) {
            System.out.println("==== 7.BeanPostProcessor.postProcessAfterInitialization: " + beanName);
        }
        return bean;
    }

}