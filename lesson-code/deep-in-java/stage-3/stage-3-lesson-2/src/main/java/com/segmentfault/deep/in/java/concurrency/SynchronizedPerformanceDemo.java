package com.segmentfault.deep.in.java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

public class SynchronizedPerformanceDemo {

    public static void main(String[] args) {
        // 对比 Vector 和 ArrayList
        // 相同点：两者都使用数组（Array）作为存储，集合算法类似
        Vector vector = new Vector();
        ArrayList list = new ArrayList();

        doTest(10000, vector);
        doTest(10000, list);

        // 这里gc是为了防止上一次操作影响下一次
        System.gc();

        doTest(100000, vector);
        doTest(100000, list);

        System.gc();

    }


    private static void doTest(int count, List list) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.add(new Object());
        }
        long costTime = System.currentTimeMillis() - startTime;
        System.out.printf("%s add costing %d ms,avg %f\n", list.getClass().getName(), costTime,costTime / (count * 1.0));
    }

}
