package cn.cunchang.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

public class TulingLogInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
//        System.out.println(getClass() + "调用方法前");
        System.out.println("执行目标方法【" + invocation.getMethod().getName() + "】的<环绕通知-前>,入参" + Arrays.asList(invocation.getArguments()));
        Object ret = invocation.proceed();
//        System.out.println(getClass() + "调用方法后");
        System.out.println("执行目标方法【" + invocation.getMethod().getName() + "】的<环绕通知-后>,入参" + Arrays.asList(invocation.getArguments()));
        return ret;
    }

}