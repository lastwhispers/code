package cn.lastwhisper.jdk5.feature.classloader.custom;

import java.io.*;

/**
 * 自定义类加载器并证明双亲委派机制
 *  以及解释loadClass、findClass、defineClass的功能
 *  resolveClass的功能以及resolveClass与forName的区别
 * @author lastwhisper
 */
public class ClassLoader1 extends ClassLoader {

    private String rootDir;

    public ClassLoader1(String rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * 重写loadClass会破坏双亲委派机制
     * 重写findClass不会破坏双亲委派机制
     *  不管重写loadClass还是findClass，都需要直接使用defineClass实现类加载
     *  resolveClass用于链接（加载、链接、初始化）
     * @param name
     * @return java.lang.Class<?>
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassData(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String className) {
        // 获取class文件的路径
        String path = classNameToPath(className);
        InputStream is = null;
        ByteArrayOutputStream out = null;
        try {
            is = new FileInputStream(new File(path));
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int b = 0;
            while ((b = is.read(buffer)) != -1) {
                out.write(buffer, 0, b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }
}
