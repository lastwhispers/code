package cn.lastwhisper.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author ：
 * @date：2019/7/14
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class UnsafeInstance {
    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        int j=1;
        reflectGetUnsafe().loadFence();
        int i= 0;
    }
}
