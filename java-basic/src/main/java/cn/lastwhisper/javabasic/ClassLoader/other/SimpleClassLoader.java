package cn.lastwhisper.javabasic.ClassLoader.other;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * @author lastwhisper
 * @desc
 */
public class SimpleClassLoader extends ClassLoader {
    // 需要加载class文件的目录
    private String classDir;

    // 无参的构造方法，用于class.newInstance()构造对象使用
    public SimpleClassLoader() {
    }

    public SimpleClassLoader(String classDir) {
        this.classDir = classDir;
    }

    // 实现类的加载规则
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPathFile = classDir + "\\" + name + ".class";
        System.out.println("classPathFile:" + classPathFile);
        try {
            FileInputStream fis = new FileInputStream(classPathFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int bytes = -1;
            while ((bytes = fis.read()) != -1) {
                bos.write(bytes);
            }
            byte[] classByte = bos.toByteArray();
            //将字节流变成一个class
            return defineClass(name, classByte, 0, classByte.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = new SimpleClassLoader("E:\\idea\\IdeaProject\\code\\java-basic\\src\\main\\java\\cn\\lastwhisper\\javabasic\\ClassLoader\\other")
                .loadClass("cn.lastwhisper.javabasic.ClassLoader.other.ClassLoaderAttachment");
        System.out.println("ClassLoaderAttachment classloader" + ClassLoaderAttachment.class.getClassLoader());
        Object attachment = clazz.newInstance();
        System.out.println(clazz.getClassLoader());
        System.out.println("attachment instanceof  ClassLoaderAttachment:" + (attachment instanceof ClassLoaderAttachment));
    }
}
