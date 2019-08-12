package com.desgin.pattern.creational.singleton;

import java.io.*;

/**
 * 序列化攻击测试
 * Create by lastwhisper on 2019/1/26
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        EnumInstance instance = EnumInstance.getInstance();
        instance.setData(new Object());
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\singleton_file"));
        oos.writeObject(instance);

        File file = new File("E:\\singleton_file");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        EnumInstance newInstance = (EnumInstance)ois.readObject();

        System.out.println(instance.getData());
        System.out.println(newInstance.getData());
        System.out.println(instance.getData() == newInstance.getData());
    }
}
//        System.out.println(instance);
//        System.out.println(newInstance);
//        System.out.println(instance == newInstance);
