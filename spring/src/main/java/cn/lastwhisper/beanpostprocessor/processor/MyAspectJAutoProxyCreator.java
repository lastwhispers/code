package cn.lastwhisper.beanpostprocessor.processor;

import cn.lastwhisper.beanpostprocessor.service.Calculator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lastwhisper
 */
@Component //将自己加到SpringIOC容器中
public class MyAspectJAutoProxyCreator implements BeanPostProcessor {
    /**
     * 对象初始化之前
     *
     * @param bean
     * @param beanName
     * @return java.lang.Object
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 对象初始化之后
     *
     * @param bean
     * @param beanName
     * @return java.lang.Object
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        final Object obj = bean;
        //如果当前经过BeanPostProcessors的Bean是Calculator类型，我们就返回它的代理对象
        if (obj instanceof Calculator) {
            Object proxyObj = Proxy.newProxyInstance(this.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("日志——计算开始");
                    Object invoke = method.invoke(obj, args);
                    System.out.println("日志——计算结束");
                    return invoke;
                }
            });
            return proxyObj;
        }
        return obj;
    }
}
