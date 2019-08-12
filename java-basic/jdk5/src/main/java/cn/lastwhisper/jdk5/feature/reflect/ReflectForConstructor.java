package cn.lastwhisper.jdk5.feature.reflect;

import java.lang.reflect.Constructor;

/**
 * 反射之Constructor类
 *  Constructor类代表某个类中的一个构造方法
 * @author lastwhisper
 */
public class ReflectForConstructor {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("java.lang.String");
        // 1. 得到某个类所有的构造方法
        Constructor[] constructors = clazz.getConstructors();
        // 2. 得到某一个构造方法
        // 得到String的以StringBuffer为参数的构造函数
        Constructor<String> constructor = clazz.getConstructor(StringBuffer.class);
        // 3. 创建实例对象
        // 3.1 通常方式
        String str1 = new String(new StringBuffer("new String"));
        // 3.2 反射方式  constructor.newInstance与clazz.newInstance
        String str2 = constructor.newInstance(new StringBuffer("constructor.newInstance String"));
    }
}
