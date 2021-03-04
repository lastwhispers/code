package cn.lastwhisper.feature5.proxy.aopframework;

import cn.lastwhisper.feature5.proxy.aop.Advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类
 * @author lastwhisper
 */
public class ProxyFactoryBean {

    public Object getProxy(final Object target, final Advice advice) {

        Object proxyClass = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        advice.beforeMethod(method);
                        Object obj = method.invoke(target, args);
                        advice.afterMethod(method);
                        return obj;
                    }
                });
        return proxyClass;
    }

}
