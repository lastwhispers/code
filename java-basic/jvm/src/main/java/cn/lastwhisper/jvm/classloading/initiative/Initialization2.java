package cn.lastwhisper.jvm.classloading.initiative;

import java.lang.reflect.Constructor;

/**
 * 主动引用触发初始化、演示二
 * @author lastwhisper
 */
public class Initialization2 {
    public static void main(String[] args) {
        // 使用java.lang.reflect包的方法对类进行反射调用的时候，
        // 如果类没有进行过初始化，则需要先触发其初始化。

        try {
            // 使用Class.forName();也行，不要使用对象.class。
            Class<SubClass> clazz = SubClass.class;
            Constructor<SubClass> constructor = clazz.getConstructor();
            constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
