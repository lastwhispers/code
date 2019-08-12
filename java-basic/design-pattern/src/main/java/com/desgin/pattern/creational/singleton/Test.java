package com.desgin.pattern.creational.singleton;

/**
 * Create by lastwhisper on 2019/1/25
 */
public class Test {
    public static void main(String[] args) throws Exception {
        /*反射测试*/
//        Class objectClass = HungrySingleton.class;
//        Class objectClass  = StaticInnerClassSingleton.class;
//        Class objectClass = LazySingleton.class;

        /*枚举反射测试*/
        //Class objectClass = EnumInstance.class;
        //
        //Constructor constructor = objectClass.getDeclaredConstructor(String.class,int.class);
        //constructor.setAccessible(true);
        //EnumInstance newInstance = (EnumInstance) constructor.newInstance("gaojun",123456);

//        LazySingleton instance = LazySingleton.getInstance();
//        System.out.println("getInstance()的lazySingleton："+instance.lazySingleton);

//        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
//        StaticInnerClassSingleton newInstance = (StaticInnerClassSingleton) constructor.newInstance();
//        HungrySingleton instance = HungrySingleton.getInstance();
//        HungrySingleton newInstance = (HungrySingleton) constructor.newInstance();
//        System.out.println(instance);
//        System.out.println(newInstance);
//        System.out.println(instance == newInstance);
        //枚举类型
//        EnumInstance instance = EnumInstance.getInstance();
//        instance.printTest();
    }
}
