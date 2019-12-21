package cn.lastwhisper.springbootaop.core.aop;

import cn.lastwhisper.springbootaop.core.common.JoinPointParesUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 *
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "aop.log.service", havingValue = "true")
public class ServiceBeforeLog {

    @Pointcut("execution(* cn.lastwhisper.springbootaop.service.*Service.*(..))")
    public void service() {
    }

    @Before("service()")
    public void service(JoinPoint point) {
        log.info(String.format("%s", JoinPointParesUtils.getMethodInfo(point)));
    }

}
