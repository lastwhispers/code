package cn.lastwhisper.reflect;

import java.lang.reflect.Array;

/**
 * 反射操作数据
 * @author lastwhisper
 */
public class ReflectToArray {

    public static void main(String[] args) {
        int[] a1 = new int[]{1, 2, 3};
        String str1 = "123";
        printObject(a1);
        printObject(str1);
    }

    public static void printObject(Object object) {
        if (object.getClass().isArray()) {
            int length = Array.getLength(object);
            for (int i = 0; i < length; i++) {
                Object arr = Array.get(object, i);
                System.out.println(arr);
            }
        } else {
            System.out.println(object);
        }
    }
}
