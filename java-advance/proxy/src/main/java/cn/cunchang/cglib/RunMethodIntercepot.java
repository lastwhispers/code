package cn.cunchang.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class RunMethodIntercepot implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = methodProxy.invokeSuper(o, objects);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        return result;
    }

}