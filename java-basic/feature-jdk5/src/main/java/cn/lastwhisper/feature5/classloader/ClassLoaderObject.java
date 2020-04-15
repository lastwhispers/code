package cn.lastwhisper.feature5.classloader;

import java.util.Iterator;
import java.util.Vector;

/**
 * 获取ClassLoader下面加载的类
 *
 * java -verbose:class Main 获取JVM启动时加载的所有类
 *
 * @author lastwhisper
 */
public class ClassLoaderObject {
    /**
     * @author lastwhisper
     */
    private static Iterator list(ClassLoader CL) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Class CL_class = CL.getClass();
        while (CL_class != java.lang.ClassLoader.class) {
            CL_class = CL_class.getSuperclass();
        }
        java.lang.reflect.Field ClassLoader_classes_field = CL_class
                .getDeclaredField("classes");
        ClassLoader_classes_field.setAccessible(true);
        Vector classes = (Vector) ClassLoader_classes_field.get(CL);
        return classes.iterator();
    }


    public static void main(String[] args) throws Exception {
        ClassLoader myCL = Thread.currentThread().getContextClassLoader();
        while (myCL != null) {
            System.out.println("ClassLoader: " + myCL);
            for (Iterator iter = list(myCL); iter.hasNext(); ) {
                System.out.println("\t" + iter.next());
            }
            myCL = myCL.getParent();
        }
    }
}
