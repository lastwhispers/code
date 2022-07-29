package cn.cunchang.aop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * Driving的前置增强类
 */
public class DrivingBeforeAdvice implements MethodBeforeAdvice{

    @Override
    public void before(Method method, Object[] args, Object obj) throws Throwable {
        //得到切点的信息
        System.out.println("要增强的是："+obj.getClass()+"类 ---"+method.getName()+"方法");
        System.out.println("【前置增强】做好行驶前的准备工作...");
    }

}