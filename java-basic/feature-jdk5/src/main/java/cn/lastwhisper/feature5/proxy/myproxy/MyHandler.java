package cn.lastwhisper.feature5.proxy.myproxy;

import java.lang.reflect.Method;

public class MyHandler implements MyInvocationHandler {
    private Man man;

    public MyHandler(Man man) {
        this.man = man;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(man, null);
        after();
        return invoke;
    }

    void before(){
        System.out.println("before");
    }
    void after(){
        System.out.println("after");
    }
}
