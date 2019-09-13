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
public class ObjectAspectConfig {
    /**
     * 第三节：匹配对象：this()/bean()/target()
     *
     * // 匹配AOP代理的目标对象的类型为Loggable的对象,即LogService对象
     * @Pointcut("this(cn.lastwhisper.log.Loggable)")
     * // 匹配AOP代理的目标对象的类型为Loggable的对象,即LogService对象
     * @Pointcut("target(cn.lastwhisper.log.Loggable)")
     *
     * 在没有Introduction时,this和target相同
     * 在有Introduction时,
     *  this 可以拦截 DeclareParents(Introduction)
     *  target 不拦截 DeclareParents(Introduction)
     *
     * // 只匹配logService方法
     * @Pointcut("bean(logService)")
     * //匹配所有以Service结尾的bean里头的方法
     * @Pointcut("bean(*Service)")
     */
    @Pointcut("bean(logService)")
    public void matchCondition() {
    }

    @Before("matchCondition()")
    public void before() {
        System.out.println("");
        System.out.println("###before");
    }

}
