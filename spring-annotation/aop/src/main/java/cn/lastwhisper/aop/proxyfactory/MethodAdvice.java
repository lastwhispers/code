package cn.lastwhisper.aop.proxyfactory;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class MethodAdvice implements AfterReturningAdvice, MethodBeforeAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("-------afterReturning");
    }


    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("-------before");

    }
}