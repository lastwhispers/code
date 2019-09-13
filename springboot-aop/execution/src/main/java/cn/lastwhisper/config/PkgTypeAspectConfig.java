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
public class PkgTypeAspectConfig {

    /**
     * 第二节：匹配包/类型：within
     *
     * // 匹配ProductService类里头的所有方法
     * @Pointcut("within(cn.lastwhisper.service.ProductService)")
     * // 匹配cn.lastwhisper.service.sub包下所有类的方法
     * @Pointcut("within(cn.lastwhisper.service.sub.*)")
     */
    @Pointcut("within(cn.lastwhisper.service.sub.*)")
    public void matchType() {
    }

    @Before("matchType()")
    public void before() {
        System.out.println("");
        System.out.println("###before");
    }
}
