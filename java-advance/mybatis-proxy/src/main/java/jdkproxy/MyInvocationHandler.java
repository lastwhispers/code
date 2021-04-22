package jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by shfq on 2016/10/16.
 */
public class MyInvocationHandler implements InvocationHandler {
    // 被代理的对象
    private MyInterface myInterface;
    public MyInvocationHandler(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        // proxy 是代理对象，不过在 invoke 方法中好像一般不会用到它
        System.out.println("before " + method.getName() + "()");
        // 通过反射调用被代理对象的 method
        Object returnValue = null;
        try {
            returnValue = method.invoke(myInterface, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("after " + method.getName() + "()");
        System.out.println();
        return returnValue;
    }

    public static void main(String[] args) {
        // 被代理的对象
        MyInterface myInterface = new MyTest();
        InvocationHandler invocationHandler = new MyInvocationHandler(myInterface);
        // 代理对象
        Object myProxy = Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(), new Class[] {MyInterface.class}, invocationHandler);
//        MyInterface myProxy = (MyInterface) Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(), new Class[] {MyInterface.class}, invocationHandler);
//        myProxy.doSomething1();
//        myProxy.doSomething2();
//        myProxy.doSomething3();
        System.out.println("");

    }
}
