package cn.lastwhisper.jvm.classloader.loadorder;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 *
 * @author lastwhisper
 */
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private final String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        /**
         * 类加载实际执行的方法
         */
        @Override
        public Class<?> findClass(String className) {
            byte[] bytes = loadByte(className);
            return defineClass(className, bytes, 0, bytes.length);
        }

        private byte[] loadByte(String className) {
            String filePath = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
            try (InputStream is = Files.newInputStream(new File(filePath).toPath())) {
                byte[] data = new byte[is.available()];
                is.read(data);
                return data;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoaderTest = new MyClassLoader("/Users/kaisui/workspace/my/code/java-basic/feature-jdk5/target/classes/");
        // 注意先使用loadClass，该类会被appCL加载，后使用findClass会被MyCL加载。
        // 注意先使用findClass，该类会被MyCL加载，再使用loadClass会发现该类已经被MyCL加载了，就不向上委派了。
        Class<?> clazz1 = myClassLoaderTest.loadClass("cn.lastwhisper.classloader.DemoObj");// 走appCL
        Class<?> clazz2 = myClassLoaderTest.findClass("cn.lastwhisper.classloader.DemoObj");// 走MyCL
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1.getClassLoader());
        System.out.println(clazz2.getClassLoader());
    }

}
