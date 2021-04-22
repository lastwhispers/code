package cn.lastwhisper.annotation;

// 自定义注解

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)//保留策略
@Target({ElementType.METHOD, ElementType.TYPE})//作用目标 // 为什么是TYPE，因为Class的父类是Type
public @interface MyAnnotation {
    // 八大基本数据类型、String、Class、Enum、注解类型对应的数组类型

    String color() default "green";

    String value();//value比较特殊

    int[] arrayAttr() default {1, 2, 3};

    Class<? extends Formatter> clazz() default Formatter.class;

    Level enumLevel() default Level.GOOD;

    MetaAnnotation annotation() default @MetaAnnotation("xxx");

}


interface Formatter {
}

@interface MetaAnnotation {
    String value();
}

enum Level {BAD, INDIFFERENT, GOOD}

