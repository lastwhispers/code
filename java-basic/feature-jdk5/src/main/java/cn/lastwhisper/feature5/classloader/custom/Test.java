package cn.lastwhisper.feature5.classloader.custom;

/**
 * 用于测试
 * @author lastwhisper
 */
public class Test {

    public static void main(String[] args) throws Exception {
        ClassLoader1 classLoader1 = new ClassLoader1("D:\\code\\GitRepository\\JavaNotes\\java-basic\\jdk5\\target\\classes\\cn\\lastwhisper\\jdk5\\feature\\classloader\\custom\\");
        Class<?> clazz1 = classLoader1.loadClass("cn.lastwhisper.feature5.classloader.DemoObj");
        //Class<?> clazz1 = classLoader1.loadClass("DemoObj");
        Object obj = clazz1.newInstance();
        System.out.println(obj.getClass().getClassLoader());
    }

}
