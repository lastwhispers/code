package cn.lastwhisper.reflect;

import java.lang.reflect.Method;

/**
 * 反射之Method类
 *  Method类代表某个类中的一个成员方法
 * @author lastwhisper
 */
public class ReflectForMethod {
    public static void main(String[] args) throws Exception {
        String str = "string";
        // 1. 获取String的charAt方法
        Class<?> clazz = Class.forName("java.lang.String");
        Method charAtMethod = clazz.getMethod("charAt", int.class);
        // 2. 调用方法
        // 2.1 通常方式 str.charAt(1);
        // 2.2 反射方式
        // 如果传递给Method对象的invoke()方法的第一个参数为null，说明该Method对象对应的是一个静态方法！
        System.out.println(charAtMethod.invoke(str, 1));

    }
}
