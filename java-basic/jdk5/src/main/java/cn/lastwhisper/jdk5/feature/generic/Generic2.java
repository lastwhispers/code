package cn.lastwhisper.jdk5.feature.generic;

import java.util.Arrays;

/**
 * 自定义泛型模板
 * @author lastwhisper
 */
public class Generic2 {
    public static void main(String[] args) {

        // 泛型推断的一般原则，用于返回泛型参数中的交集，且泛型参数必须为引用类型
        // 3 自动装箱 Integer 5 自动装箱Integer 所以 推断出Integer
        Integer i = add(3, 3);
        //Float f = add(3, 3.5);
        // 3 自动装箱Integer str1 String 共有的交集，都是Object
        Object o = add(3, "asdasdasd");

        swap(new String[]{"a", "b", "c", "d"}, 2, 3);
        //swap(new int[]{1, 2, 3, 4}, 2, 3);//只有引用类型才能作为泛型方法的实际参数

        // 泛型练习题
        Object obj = "字符串";
        String str = convert(obj);

        String[] strings = new String[10];
        fillArray(strings, obj);
        System.out.println(Arrays.toString(strings));


    }

    // 1.编写一个泛型方法，自动将Object类型的对象转换成其他类型。
    private static <T> T convert(Object obj) {
        return (T) obj;
    }

    // 2.定义一个方法，可以将任意类型的数组中的所有元素填充为相应类型的某个对象。
    private static <T> void fillArray(T[] arr, T obj) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = obj;
        }
    }

    // 采用自定泛型方法的方式打印出任意参数化类型的集合中的所有内容。
    private static <T> void printArray(T[] t) {
        for (int i = 0; i < t.length; i++) {
            System.out.println("ele[" + i + "] = " + t[i]);
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static <T> T add(T x, T y) {
        return null;
    }

}
