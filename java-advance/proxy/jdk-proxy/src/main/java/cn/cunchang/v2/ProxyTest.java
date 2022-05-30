package cn.cunchang.v2;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Subject proxy = (Subject) Proxy
                .newProxyInstance(
                        ProxyTest.class.getClassLoader(),
                        new Class[]{Subject.class},
                        new ProxyInvocationHandler());

        proxy.sayHello();
        
    }
}