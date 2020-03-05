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
public class ArgsAspectConfig {

    /**
     * 第四节：匹配参数：args()
     *
     * // 匹配cn.lastwhisper.service包下任意类,只有一个参数且参数类型为Long的方法
     * @Pointcut("args(Long) && within(cn.lastwhisper.service.*  )")
     * // 匹配cn.lastwhisper.service包下任意类,第一个参数类型为Long,其他参数任意或无的方法
     * @Pointcut("args(Long,..) && within(cn.lastwhisper.service.*)")
     * // 匹配cn.lastwhisper.service包下任意类,第一个参数类型为Long,第二个参数类型为String的方法
     * // 顺序不能颠倒,因为方法的重载
     * @Pointcut("args(Long,String) && within(cn.lastwhisper.service.*)")
     */
    @Pointcut("args(Long,..) && within(cn.lastwhisper.service.*)")
    public void matchArgs() {}

    @Before("matchArgs()")
    public void before() {
        System.out.println("");
        System.out.println("##before method");
    }

}
