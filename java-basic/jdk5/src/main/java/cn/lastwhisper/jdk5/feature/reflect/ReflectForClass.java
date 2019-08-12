package cn.lastwhisper.jdk5.feature.reflect;

/**
 * 反射之Class对象
 * @author lastwhisper
 */
public class ReflectForClass {
    public static void main(String[] args) throws ClassNotFoundException {
        String str1 = "abc";
        Class<? extends String> cls1 = str1.getClass();
        Class<String> cls2 = String.class;
        Class<?> cls3 = Class.forName("java.lang.String");
        System.out.println(cls1 == cls2);
        System.out.println(cls1 == cls3);

        System.out.println(cls1.isPrimitive());
        System.out.println(int.class.isPrimitive());
        System.out.println(int.class == Integer.class);
        System.out.println(int.class == Integer.TYPE);
        System.out.println(int[].class.isPrimitive());
    }
}
