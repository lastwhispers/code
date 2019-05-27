package com.desgin.pattern.creational.singleton;

/**
 * Create by lastwhisper on 2019/1/25
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;
    private static boolean flag = true;

    private LazySingleton() {
//        if (lazySingleton != null) {
//            throw new RuntimeException("单例构造器禁止反射调用");
//        }
//        if (flag) {
//            flag = false;
//        } else {
//            throw new RuntimeException("单例构造器禁止反射调用");
//        }
    }

    public synchronized static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

//    public static void main(String[] args) throws Exception {
//        Class objectClass = LazySingleton.class;
//        Constructor constructor = objectClass.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        LazySingleton o1 = LazySingleton.getInstance();
//        //修改flag=true
//        Field flag = o1.getClass().getDeclaredField("flag");
//        flag.setAccessible(true);
//        flag.set(o1,true);
//
//        LazySingleton o2 = (LazySingleton) constructor.newInstance();
//
//        System.out.println(o1);
//        System.out.println(o2);
//        System.out.println(o1 == o2);
//    }
}
