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
public class AnnoAspectConfig {
    /**
     * 第五节：匹配注解：@target()、@args()、@within()、@annotation()
     *
     * 1、简介
     * @annotation()匹配方法级别
     * @within()和@target()匹配类级别
     *  within和target在Spring环境下是一样的
     *  不在Spring环境下,within要求注解RetentionPolicy.CLASS,target要求注解RetentionPolicy.RUNTIME
     * @args()匹配参数级别
     *  注意：前面都加了@
     *
     * 2、实例
     * // 匹配方法标注有AdminOnly的注解的方法
     * @Pointcut("@annotation(cn.lastwhisper.anno.AdminOnly)") //method级别
     * // 匹配类标注有NeedSecured的注解,且RetentionPolicy.CLASS
     * @Pointcut("@within(cn.lastwhisper.anno.NeedSecured) &&within(cn.lastwhisper..*)")
     * // 匹配类标注有NeedSecured的注解,且RetentionPolicy.RUNTIME
     * @Pointcut("@target(cn.lastwhisper.anno.NeedSecured) &&within(cn.lastwhisper..*)")
     * // 匹配方法的参数类型为,被NeedSecured注解修饰的类的类型
     * // 如LogService的方法annoArg的入参为Product,Product被@NeedSecured标注
     * @Pointcut("@args(cn.lastwhisper.anno.NeedSecured) &&within(cn.lastwhisper..*)")
     */
    @Pointcut("@args(cn.lastwhisper.anno.NeedSecured) && within(cn.lastwhisper..*)")
    public void matchAnno() {}

    @Before("matchAnno()")
    public void before() {
        System.out.println("");
        System.out.println("##before method");
    }

}
