package cn.lastwhisper.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author lastwhisper
 */
//@Aspect//切面类
//@Component
public class ExecutionAspectConfig {
    /**
     *  第一节：匹配方法：execution()
     *
     *  匹配cn.lastwhisper.service包下任何返回值、类名、方法名、参数,且由public修饰的方法
     * @Pointcut("execution(public * cn.lastwhisper.service.*.*(..))")
     * 1、匹配包
     *  匹配cn.lastwhisper.service包下类名后缀为"Service"的类,任何返回值、方法名、参数,且由public修饰的方法
     * @Pointcut("execution(public * cn.lastwhisper.service.*Service.*(..))")
     *  匹配cn.lastwhisper.service及其子包
     * @Pointcut("execution(public * cn.lastwhisper.service..*Service.*(..))")
     * 2、匹配返回值
     *  匹配cn.lastwhisper.service及其子包下Service类中的任何参数,返回值为String的方法
     * @Pointcut("execution(public String cn.lastwhisper.service..*Service.*(..))")
     * 3、匹配参数
     *  匹配cn.lastwhisper.service及其子包Service类中的任何返回值,只有一个参数的方法
     * @Pointcut("execution(public * cn.lastwhisper.service..*Service.*(*))")
     *  匹配cn.lastwhisper.service及其子包Service类中的任何返回值,只有无参数的方法
     * @Pointcut("execution(public * cn.lastwhisper.service..*Service.*())")
     * 4、匹配异常
     * 匹配cn.lastwhisper.service包下任何返回值、类名、方法名、参数,且由public修饰的抛出IllegalAccessException的方法
     * @Pointcut("execution(public * cn.lastwhisper.service.*.*(..) throws java.lang.IllegalAccessException)")
     */
    @Pointcut("execution(public * cn.lastwhisper.service.*.*(..) throws java.lang.IllegalAccessException)")
    public void matchCondition() {

    }

    @Before("matchCondition()")
    public void before() {
        System.out.println("");
        System.out.println("##before method");
    }

}
