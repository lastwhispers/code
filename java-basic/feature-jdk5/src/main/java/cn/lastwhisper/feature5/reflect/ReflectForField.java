package cn.lastwhisper.feature5.reflect;

import java.lang.reflect.Field;

/**
 * 反射之Field类
 *  Field类代表某个类中的一个成员变量
 * @author lastwhisper
 */
public class ReflectForField {
    public static void main(String[] args) throws Exception {
        ReflectPoint pt1 = new ReflectPoint(10, 20);
        // 访问x
        Class<? extends ReflectPoint> clazz = pt1.getClass();
        Field fieldX = clazz.getField("x");//private、protected都看不到
        System.out.println(fieldX.get(pt1));
        // 访问y
        Field fieldY = clazz.getDeclaredField("y");
        fieldY.setAccessible(true);
        System.out.println(fieldY.get(pt1));



    }
}
