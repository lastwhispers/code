
package cn.cunchang.v1;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        
        Subject subject = new SubjectImpl();
        Subject proxy = (Subject) Proxy
                .newProxyInstance(
                        subject.getClass().getClassLoader(),
                        subject.getClass().getInterfaces(),
                        new ProxyInvocationHandler(subject));

        proxy.sayHello();
    }
}