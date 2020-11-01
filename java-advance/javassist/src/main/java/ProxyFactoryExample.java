import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

/**
 * Created by shfq on 2016/10/17.
 */
public class ProxyFactoryExample {
    public void foo() throws Exception {
        System.out.println("Foo method executed.");
    }

    public static void main(String[] args) throws Throwable {
        ProxyFactory factory = new ProxyFactory(); // 代理工厂
//        factory.setSuperclass(ProxyFactoryExample.class); // 设置父类
        MethodHandler tracingMethodHandler = new MethodHandler() {
            public Object invoke(Object self, Method thisMethod,
                                 Method proceed, Object[] args) throws Throwable {
                return proceed.invoke(self, args);
            }
        };
        // 创建代理对象 第一个参数代表的是调用构造方法创建代理对象时所需的参数类型，在这里我们的构造方法不需要参数，所以传一个空的 class 类型数组，即: new Class[0] 。
        // 第二个参数代表的是具体的参数值，在这里我们需要参数，所以传一个空的 Object 类型数组，即: new Object[0]。第三个参数是 MethodHandler 对象，需要把代理对象和 MethodHandler 对象关联在一起。
        Object proxyObj =  factory.create(
                new Class[0], new Object[0], tracingMethodHandler);

//        proxyObj.foo();
        proxyObj.equals("");
        System.out.println("");
    }
}
