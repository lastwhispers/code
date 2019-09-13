package cn.lastwhisper.interview.SwapTwoInteger;

import java.lang.reflect.Field;

public class Swap2 {

    public static void swap(Integer value1, Integer value2) throws Exception {

        // 反射获取value属性对象
        Field declaredField = Integer.class.getDeclaredField("value");
        declaredField.setAccessible(true);
        // 一定不能这么写(对于-128~127内的整数是从缓存中取的)
        // Integer val = value1.intValue();
        // 不从缓存中取，在堆上新创建。
        Integer val = new Integer(value1.intValue());
        declaredField.set(value1, value2);
        declaredField.set(value2, val);
    }

    public static void main(String[] args) throws Exception {

        Integer firstValue = 50;
        Integer anotherValue = 100;
        System.out.println("交换前：firstValue=" + firstValue + "  anotherValue=" + anotherValue);
        swap(firstValue, anotherValue);
        System.out.println("交换后：firstValue=" + firstValue + "  anotherValue=" + anotherValue);

    }

}
