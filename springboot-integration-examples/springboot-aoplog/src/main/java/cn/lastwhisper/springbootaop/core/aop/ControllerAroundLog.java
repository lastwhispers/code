package cn.lastwhisper.springbootaop.core.aop;

import cn.lastwhisper.springbootaop.core.common.JoinPointParesUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Stream;

/**
 *
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "aop.log.controller", havingValue = "true")
public class ControllerAroundLog {

    @Pointcut("execution(* cn.lastwhisper.springbootaop.controller.*Controller.*(..))")
    public void controllerExecution() {
    }

    /**
     * 切点表达式无法注入，局限性比较大，不如使用注解灵活，同时可以在注解里面添加一些备注
     */
    @Pointcut("@annotation(cn.lastwhisper.springbootaop.core.annotation.LogOperation)")
    public void controllerAnnotation() {
    }

    @Around("controllerAnnotation()")
    public Object controller(ProceedingJoinPoint pjp) throws Throwable {
        // 前置处理
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        long count = Stream.of(signature.getMethod().getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType() == RequestMapping.class)
                .count();
        String requestPath = count >= 1 ? signature.getMethod().getAnnotation(RequestMapping.class).value()[0] : "";
        String info = String.format("request 请求路径:[%s] | %s", requestPath, JoinPointParesUtils.getMethodInfo(pjp));
        log.info(info);
        // 方法执行
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throw new Throwable(throwable);
        }
        // 后置处理
        log.info("response {}", JSONObject.toJSON(result));

        return result;
    }


}
