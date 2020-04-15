package cn.lastwhisper.feature5.proxy.aop;

import java.lang.reflect.Method;

/**
 * 性能监控
 * @author lastwhisper
 */
public class MonitorAdvice implements Advice {

    private long startTime;
    private long endTime;

    public void beforeMethod(Method method) {
        startTime = System.nanoTime();
        System.out.println(method.getDeclaringClass() + "." + method.getName() + "函数执行开始.");
    }

    public void afterMethod(Method method) {
        endTime = System.nanoTime();
        System.out.println(method.getDeclaringClass() + "." + method.getName() + "函数执行结束. 耗时：" + (endTime - startTime));
    }


}
