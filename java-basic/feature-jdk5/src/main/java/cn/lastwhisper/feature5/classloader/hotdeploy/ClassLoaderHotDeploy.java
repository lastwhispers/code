package cn.lastwhisper.feature5.classloader.hotdeploy;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 代码热部署
 * @author lastwhisper
 */
public class ClassLoaderHotDeploy {


    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String path = MyClassLoader.class.getResource("").getPath();
                System.out.println(path);
                String className = "cn.lastwhisper.jdk5.feature.classloader.hotdeploy.HotCodeTest";

                Set<String> set = new HashSet<>();
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
    private String path;
    // 用于标记这些name的类是先由自身加载的
    private Set<String> useMyClassLoader;

    public MyClassLoader(String path, Set<String> useMyClassLoader) {
        this.path = path;
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

    private byte[] getClassByte(String name) {
        String className = name.substring(name.lastIndexOf('.') + 1, name.length()) + ".class";
        try {
            FileInputStream fileInputStream = new FileInputStream(path + className);
            byte[] buffer = new byte[1024];
            int length = 0;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((length = fileInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }
}
