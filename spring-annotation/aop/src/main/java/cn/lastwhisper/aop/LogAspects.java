package cn.lastwhisper.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 切面类
 * @author lastwhisper
 */
@Aspect//告诉Spring这是一个切面类
public class LogAspects {
    // 抽取公共的切点表达式
    // 本类引用：pointCut()
    // 外部类引用：全限定名.pointCut()
    @Pointcut("execution(public int cn.lastwhisper.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    // 前置通知：在目标方法运行之前运行
    @Before(value = "pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "开始...参数列表是：{" + joinPoint.getArgs() + "}");
    }

    // 后置通知：在目标方法运行之后运行
    @After(value = "pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "结束...参数列表是：{" + joinPoint.getArgs() + "}");
    }

    // JoinPoint一定要放到方法参数列表第一位
    // 返回通知：在目标方法执行返回之后运行
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "正常返回...运行结果为：{" + result + "}");
    }

    // 异常通知：在目标方法出现异常之后运行
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature().getName() + "异常返回...异常信息为：{" + exception + "}");
    }
}
