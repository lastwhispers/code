package cn.lastwhisper.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author cunchang
 * @date 2022/4/29 12:24 AM
 */
public class UnSafeCreateTest {

    public static void main(String[] args) throws Exception{
        // method 1
        Class<Unsafe> unsafeClass = Unsafe.class;
        Constructor<Unsafe> constructor = unsafeClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Unsafe unsafe1 = constructor.newInstance();
        System.out.println(unsafe1);

        // method2
        Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe2 = (Unsafe) theUnsafe.get(null);
        System.out.println(unsafe2);
    }

}
