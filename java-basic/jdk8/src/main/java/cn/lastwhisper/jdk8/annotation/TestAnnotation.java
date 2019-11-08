package cn.lastwhisper.jdk8.annotation;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 可重复的注解及可用于类型的注解
 */
public class TestAnnotation {

    //checker framework框架提供此注解
    private /*@NonNull*/ Object obj = null;

    @Test
    public void test1() throws NoSuchMethodException, SecurityException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");
        MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : mas) {
            System.out.println(myAnnotation.value());
        }
    }

    @MyAnnotation("Hello")
    @MyAnnotation("world")
    public void show(@MyAnnotation("abc") String str) {

    }
}