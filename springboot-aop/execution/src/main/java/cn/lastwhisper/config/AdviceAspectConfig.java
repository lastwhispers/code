package cn.lastwhisper.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author lastwhisper
 */
@Aspect//切面类
@Component
public class AdviceAspectConfig {
    /**
     * 5种Advice：
     * 	1：@Before，前置通知；
     * 	2：@After(finally),后置通知，方法执行完成之后(不论是否有异常)；
     * 	3：@AfterReturning，返回通知，成功执行之后执行(有异常终止)；
     * 	4：@AfterThrowing，异常通知，抛出异常之后执行(有异常执行)；
     * 	5：@Around : 环绕通知(包含@Before、@After、@AfterReturning、@AfterThrowing)；
     * Advice中的参数及结果绑定
     *
     */

    /******pointcut 用于配合 Advice示例********/

    @Pointcut("@annotation(cn.lastwhisper.anno.AdminOnly) && within(cn.lastwhisper..*)")
    public void matchAnno() {
    }

    @Pointcut("execution(* *..find*(Long)) && within(cn.lastwhisper..*) ")
    public void matchLongArg() {
    }

    @Pointcut("execution(public * cn.lastwhisper.service..*Service.*(..) throws java.lang.IllegalAccessException) && within(cn.lastwhisper..*)")
    public void matchException() {
    }

    @Pointcut("execution(String cn.lastwhisper..*.*(..)) && within(cn.lastwhisper..*)")
    public void matchReturn() {
    }

    /******advice********/

    //@After("matchAnno()") //方法执行完成之后(不论是否有异常)
    //@After("matchException()") //方法执行完成之后(不论是否有异常)
    //@AfterThrowing("matchException()") //抛出异常之后执行
    //@AfterThrowing("matchAnno()") //不抛异常不执行
    //public void after(){
    //    System.out.println("###after");
    //}

    //@AfterReturning(value ="matchReturn()",returning = "result") // 获取方法的返回值
    //public void after(Object result){
    //    System.out.println("###after returning:"+result);
    //}

    //@Around("matchAnno()") //测试@Around正常情况
    //@Around("matchException()")// 测试@Around出现异常
    //public Object after(ProceedingJoinPoint pjp) {
    //    System.out.println("###before");
    //    Object result = null;
    //    try {
    //        result = pjp.proceed(pjp.getArgs());
    //        System.out.println("###after returning:" + result);
    //    } catch (Throwable e) {
    //        System.out.println("###catch ex");
    //        //throw
    //        e.printStackTrace();
    //    } finally {
    //        System.out.println("###finally");
    //    }
    //    return result;
    //}

    // 使用@Before校验参数
    @Before("matchLongArg() && args(productId)")
    public void before(Long productId){
        System.out.println("###before,get args:"+productId);
    }

}
