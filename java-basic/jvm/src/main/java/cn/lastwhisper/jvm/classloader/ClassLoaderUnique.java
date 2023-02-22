package cn.lastwhisper.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与instanceof关键字演示
 *
 * @author lastwhisper
 */
public class ClassLoaderUnique {

    /**
     * 对于任意一个类，都需要由加载它的类加载器和这个类本身一同确立
     * 其在Java虚拟机中的唯一性，每一个类加载器，都有一个独立的名称空间。
     * 即，两个类来自同一个Class文件，被同一个虚拟机加载，只要类加载器不同，两个类就不相等。
     * 不相等包括：equals()、isAssignableFrom()、isInstance()、instanceof
     *
     * @author lastwhisper
     */
    public static void main(String[] args) throws Exception {
        // myClassLoader的父类加载是appclassload，
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    // 类文件如果在当前项目下，就由自己加载，否则由父类加载器加载
                    // 比如DemoObj就在当前项目下，java.lang.Object就不在当前项目下
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    int ignore = is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Class<?> clazz = myClassLoader.loadClass("cn.lastwhisper.classloader.DemoObj");

        Object obj = clazz.newInstance();

        // obj由自定义的类加载器加载、DemoObj由系统类加载器加载的。
        System.out.println(obj instanceof DemoObj);

        System.out.println(obj.getClass() == DemoObj.class);
    }
}
