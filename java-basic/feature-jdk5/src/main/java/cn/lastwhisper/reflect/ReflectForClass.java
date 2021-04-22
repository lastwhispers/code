package cn.lastwhisper.reflect;

import org.junit.Test;

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

    /**
     * 测试同一个类加载器，Class.forName拿到的Class是不是同一个
     * @throws Exception
     */
    @Test
    public void classforName() throws Exception {
        Class clazz1 = Class.forName("cn.lastwhisper.reflect.pojo.MyObject");
        Class clazz2 = Class.forName("cn.lastwhisper.reflect.pojo.MyObject");
        System.out.println(clazz1 == clazz2);
    }

    /**
     * 测试同一个Class对象反射创建目标对象时，是不是永远是同一个
     * @throws Exception
     */
    @Test
    public void reflectNewObj() throws Exception {
        Class clazz = Class.forName("cn.lastwhisper.reflect.pojo.MyObject");
        Object newObj1 = clazz.newInstance();
        Object newObj2 = clazz.newInstance();
        System.out.println(newObj1 == newObj2);
    }


}
