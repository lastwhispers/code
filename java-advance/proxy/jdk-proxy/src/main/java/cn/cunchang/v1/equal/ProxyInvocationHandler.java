package cn.cunchang.v1.equal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 定义动态代理调用处理器
 */
public class ProxyInvocationHandler implements InvocationHandler {
    /**
     * 被代理类，我们自己定义的成员变量，不要也可以
     */
    private Object target;

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * @param proxy  代理对象，jdk给你生成的对象
     * @param method 被代理类的方法
     * @param args   被代理类方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理调用处理器 before");
        Object obj = method.invoke(target, args);
        System.out.println("进入代理调用处理器 fater");
        return obj;
    }

}