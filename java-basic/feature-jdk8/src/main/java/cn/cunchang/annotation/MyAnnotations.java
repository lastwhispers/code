package cn.cunchang.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})//可以修饰的目标
@Retention(RetentionPolicy.RUNTIME)//生命周期
public @interface MyAnnotations {
    MyAnnotation[] value();// 重复注解的容器
}
