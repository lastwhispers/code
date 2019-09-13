package cn.lastwhisper.concurrent.juc.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 集合类不安全的问题
 * @author lastwhisper
 */
public class ContainerNotSafeDemo {
    /**
     * 读写分离的思想,读和写不同的容器.
     * CopyOnWriteArrayList在add时,会先上锁,然后copy一个新arr
     *  将add的元素放入新arr里面,再将arr替换
     */
    public static void main(String[] args) {
        //listNotSafe();
        //setNotSafe();
        mapNotSafe();

    }

    private static void mapNotSafe() {
        //Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(generatorStr(), generatorStr());
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }

    private static void setNotSafe() {
        //Set<String> set= new CopyOnWriteArraySet<>();
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(generatorStr());
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        //List<String> list= new CopyOnWriteArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(generatorStr());
                System.out.println(list);//toString里面使用了迭代器
            }, String.valueOf(i)).start();
        }
    }

    public static String generatorStr() {
        return UUID.randomUUID().toString().substring(1, 8);
    }

}
