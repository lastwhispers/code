package cn.lastwhisper.jdk5.feature.proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 创建代理类的实例，并完善InvocationHandler
 * @author lastwhisper
 */
public class Proxy3 {
    public static void main(String[] args) throws Exception {
        // 查看生成的代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 原类
        Collection<String> list = new ArrayList<String>();
        // 代理类
        Collection proxyClass = (Collection) getProxy(list, new MonitorAdvice());
        proxyClass.add("1111");
        proxyClass.add("2222");
        proxyClass.add("3333");
        System.out.println(proxyClass.size());
    }

    private static Object getProxy(final Object target, final Advice advice) {
        // 目标增强类的类加载器（用于加载代理类）；
        // 目标增强类的接口（代理类实现接口）；
        // InvocationHandler执行增强代码和目标方法
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
