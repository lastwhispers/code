package cn.lastwhisper.jdk5.feature.proxy.myproxy;

import java.lang.reflect.Method;

public interface MyInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
