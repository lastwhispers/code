package cn.cunchang.v3;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {

        AbstractProxy proxyInterface = (AbstractProxy) Proxy
                .newProxyInstance(
                        ProxyTest.class.getClassLoader(),
                        new Class[]{AbstractProxy.class},
                        new ProxyInvocationHandler());
        proxyInterface.sayHello();
        
    }
}