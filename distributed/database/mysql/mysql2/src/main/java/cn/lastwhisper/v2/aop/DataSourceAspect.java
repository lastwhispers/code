package cn.lastwhisper.v2.aop;

import cn.lastwhisper.v2.annotation.DataSource;
import cn.lastwhisper.v2.db.DbContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author lastwhisper
 */
public class DataSourceAspect {

    public void before(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(DataSource.class);

        String source = dataSource.value();
        System.out.println(source);
        // 设置数据源
        DbContextHolder.set(source);
    }

    public void after() {
        DbContextHolder.clear();
    }

}
