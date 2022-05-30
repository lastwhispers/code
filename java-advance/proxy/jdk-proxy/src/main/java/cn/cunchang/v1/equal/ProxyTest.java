
package cn.cunchang.v1.equal;

public class ProxyTest {
    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Subject subject = new SubjectImpl();
//        Subject proxy = (Subject) Proxy
//                .newProxyInstance(
//                        subject.getClass().getClassLoader(),
//                        subject.getClass().getInterfaces(),
//                        new ProxyInvocationHandler(subject));

        $Proxy0 $Proxy0 = new $Proxy0(new ProxyInvocationHandler(subject));
        $Proxy0.sayHello();

    }
}