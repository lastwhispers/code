package cn.lastwhisper.javabasic.ClassLoader;

import java.lang.reflect.Field;
import java.util.Vector;

/**
 * 获取ClassLoader下面加载的类
 * @author lastwhisper
 */
public class ClassLoaderObject {
    /**
     * @author lastwhisper
     */
    public static void main(String[] args) throws Exception {

        Field f = ClassLoader.class.getDeclaredField("classes");
        f.setAccessible(true);
        Vector classes = (Vector) f.get(ClassLoader.getSystemClassLoader());
        for (Object object : classes) {
            System.out.println(object);
        }

    }
}
