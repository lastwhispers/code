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
@ConditionalOnProperty(name = "aop.log.dao", havingValue = "true")
public class DaoBeforeLog {

    @Pointcut("execution(* cn.lastwhisper.springbootaop.mapper.*Mapper.*(..))")
    public void dao() {
    }

    @Before("dao()")
    public void repository(JoinPoint point) {
        log.info(String.format("%s", JoinPointParesUtils.getMethodInfo(point)));
    }

}
