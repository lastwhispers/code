package cn.lastwhisper.feature5.proxy.aopframework;

import cn.lastwhisper.feature5.proxy.aop.Advice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author lastwhisper
 */
public class BeanFactory {
    Properties props = new Properties();

    public BeanFactory(InputStream is) {
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据name判断是否增强bean
     *
     * @param name
     * @return java.lang.Object
     */
    public Object getBean(String name) {
        String className = props.getProperty(name);
        Class<?> clazz;
        Object bean = null;
        try {
            clazz = Class.forName(className);
            bean = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        //
        if (bean instanceof ProxyFactoryBean) {
            ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) bean;
            Object target = null;
            Advice advice = null;
            try {
                target = Class.forName(props.getProperty(name + ".target")).newInstance();
                advice = (Advice) Class.forName(props.getProperty(name + ".advice")).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //proxyFactoryBean.setAdvice(advice);
            //proxyFactoryBean.setTarget(target);
            // 代理对象
            return proxyFactoryBean.getProxy(target,advice);
        }
        return bean;
    }

}
