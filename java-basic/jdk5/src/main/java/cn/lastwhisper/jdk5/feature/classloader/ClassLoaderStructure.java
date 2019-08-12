package cn.lastwhisper.jdk5.feature.classloader;

/**
 * 类加载器的结构、以及对应类加载器加载类的范围
 * @author lastwhisper
 */
public class ClassLoaderStructure {
    
    public static void main(String[] args) throws ClassNotFoundException {
        Object obj = new Object();
        System.out.println("Object的类加载器:"+obj.getClass().getClassLoader());

        Class<?> clazz = Class.forName("cn.lastwhisper.javabasic.ClassLoader.other.Hello");
        System.out.println("ext下的类加载器:"+clazz.getClassLoader());

        ClassLoaderStructure test01 = new ClassLoaderStructure();
        System.out.println("classpath下的类加载器:"+test01.getClass().getClassLoader());

        System.out.println("");

        System.out.println("classpath下的类加载器:"+test01.getClass().getClassLoader());
        System.out.println("classpath下的类加载器的父类加载器:"+test01.getClass().getClassLoader().getParent());
        System.out.println("classpath下的类加载器的父类加载器的父类加载器:"+test01.getClass().getClassLoader().getParent().getParent());

        // java.lang.ClassLoader

    }
}
