package cn.lastwhisper.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * 创建代理类，并查看其方法列表
 * @author lastwhisper
 */
public class Proxy1 {
    public static void main(String[] args) throws Exception {
        // 代理类
        Class<?> proxyClass = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        // 构造方法
        Constructor<?>[] constructors = proxyClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        // 普通方法
        Method[] methods = proxyClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

}

