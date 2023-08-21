package cn.lastwhisper.jvm.classloader.hotdeploy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 代码热部署
 *
 * @author lastwhisper
 */
public class ClassLoaderHotDeploy {

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String path = ClassLoaderHotDeploy.class.getResource("").getPath();
                System.out.println(path);
                // 这里一定要用全限定名，因为一个类唯一标志就是全限定名
                String className = "cn.lastwhisper.classloader.hotdeploy.HotCodeTest";

                Set<String> set = new HashSet<String>();
                set.add(className);

                MyClassLoader myClassLoader = new MyClassLoader(path, set);

                try {
                    Object object = myClassLoader.loadClass(className).newInstance();
                    object.getClass().getMethod("printVersion").invoke(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, 0, 2000);
    }

}

class MyClassLoader extends ClassLoader {
    // 用于读取.class文件的路径
    private String classPath;
    // 用于标记这些name的类是先由自身加载的
    private Set<String> useMyClassLoader;

    public MyClassLoader(String classPath, Set<String> useMyClassLoader) {
        this.classPath = classPath;
        this.useMyClassLoader = useMyClassLoader;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (c == null && useMyClassLoader.contains(name)) {
            c = findClass(name);
            if (c != null) {
                return c;
            }
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) {
        //根据文件系统路径加载class文件，并返回byte数组
        byte[] classBytes = getClassByte(name);
        //调用ClassLoader提供的方法，将二进制数组转换成Class类的实例
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] getClassByte(String className) {
        String filePath = classPath +  className.substring(className.lastIndexOf('.') + 1, className.length()) + ".class";
        try (InputStream is = Files.newInputStream(new File(filePath).toPath())) {
            byte[] data = new byte[is.available()];
            is.read(data);
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
