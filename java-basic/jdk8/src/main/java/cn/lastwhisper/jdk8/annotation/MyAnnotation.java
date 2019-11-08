package cn.lastwhisper.jdk8.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Repeatable(MyAnnotations.class)//指定重复注解的容器
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER})//可以修饰的目标
@Retention(RetentionPolicy.RUNTIME)//生命周期
public @interface MyAnnotation {
    String value() default "java";
}