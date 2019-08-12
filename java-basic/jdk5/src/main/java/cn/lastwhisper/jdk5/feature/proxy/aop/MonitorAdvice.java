package cn.lastwhisper.jdk5.feature.proxy.aop;

import java.lang.reflect.Method;

/**
 * 性能监控
 * @author lastwhisper
 */
public class MonitorAdvice implements Advice {

    private static long startTime;
    private static long endTime;

    public void beforeMethod(Method method) {
        startTime = System.currentTimeMillis();
        System.out.println(method.getDeclaringClass()+"."+method.getName()+"函数执行开始.");
    }

    public void afterMethod(Method method) {
        endTime = System.currentTimeMillis();
        System.out.println(method.getDeclaringClass()+"."+method.getName()+"函数执行结束. 耗时："+(endTime-startTime));
    }



}
