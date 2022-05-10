package cn.lastwhisper.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author cunchang
 * @date 2022/4/29 12:24 AM
 */
public class UnSafeOpArrayTest {

    public static void main(String[] args) throws Exception{
        Class<Unsafe> unsafeClass = Unsafe.class;
        Constructor<Unsafe> constructor = unsafeClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Unsafe unsafe = constructor.newInstance();

        Integer[] integers = new Integer[10];
        // 打印数组的原始值
        System.out.println(Arrays.toString(integers));
        // 获取Integer数组在内存中的固定的偏移量
        long arrayBaseOffset = unsafe.arrayBaseOffset(Integer[].class);
        System.out.println(unsafe.arrayIndexScale(Integer[].class));
        System.out.println(unsafe.arrayIndexScale(double[].class));
        // 将数组中第一个元素的更新为100
        unsafe.putObject(integers, arrayBaseOffset, 100);
        // 将数组中第五个元素更新为50  注意 引用类型占用4个字节，所以内存地址 需要 4 * 4 = 16
        unsafe.putObject(integers, arrayBaseOffset + 16, 50);
        // 打印更新后的值
        System.out.println(Arrays.toString(integers));
    }

}
