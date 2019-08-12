package cn.lastwhisper.jdk5.feature.annotation;

import java.util.Arrays;

/**
 * jdk5新特性：注解
 * @author lastwhisper
 */
public class AnnotationTest {

    public static void main(String[] args) {
        if (UseAnnotation.class.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = UseAnnotation.class.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            System.out.println(Arrays.toString(annotation.arrayAttr()));
            System.out.println(annotation.annotation().value());
            System.out.println(annotation.clazz());
            System.out.println(annotation.enumLevel());
        }
    }

}
