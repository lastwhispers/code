package com.desgin.mashibing.strategy;

//排序类
public class DataSort {
    public static void sort(Object[] objs) {
        for (int i = 0; i < objs.length - 1; i++) {
            for (int j = i + 1; j < objs.length; j++) {
                MyComparable c1 = (MyComparable) objs[i];
                MyComparable c2 = (MyComparable) objs[j];
                if (c1.compareTo(c2) > 0) {
                    Object temp = objs[i];
                    objs[i] = objs[j];
                    objs[j] = temp;
                }
            }
        }
    }
}