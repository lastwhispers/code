package cn.cunchang.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;

/**
 * Driving的方法拦截器
 */

public class DrivingInterceptor implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;

        System.out.println("车辆放行前...");
        result= invocation.proceed();
        System.out.println("车辆放行后...");

        return result;
    }

}