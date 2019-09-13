package cn.lastwhisper.jdk5.feature.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lastwhisper
 */
public class TestList {

    public static class modification extends Thread {
        private List<String> arrayList;

        public modification(List<String> arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public void run() {
            // 需要同步机制
            Iterator<String> iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                String data = iterator.next();
                if ("a".equals(data)) {
                    iterator.remove();//Exception in thread "Thread-1" java.util.ConcurrentModificationException
                }
                System.out.print(Thread.currentThread().getId() + " " + data + "\n");
            }
        }
    }

    // 测试ArrayList在创建迭代器后,使用iterator.remove()并发修改
    @Test
    public void testThreadFailFast() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        new modification(arrayList).start();
        new modification(arrayList).start();
        new modification(arrayList).start();
    }

    // 测试ArrayList在创建迭代器后,使用iterator.remove()单线程修改
    // fail-fast
    @Test
    public void testArrayList() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        //for (String data : arrayList) {
        //    if (data.equals("a")) {
        //        arrayList.remove("a");
        //    }
        //    System.out.println(data);
        //}

        //Iterator<String> iterator = arrayList.iterator();
        //while (iterator.hasNext()) {
        //    String data = iterator.next();
        //    if (data.equals("a")) {
        //        arrayList.remove("a");
        //    }
        //    System.out.println(data);
        //}

        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String data = iterator.next();
            if (data.equals("a")) {
                iterator.remove();
            }
            System.out.println(data);
        }
        System.out.println("size=" + arrayList.size());
    }

    @Test
    public void testLinkedList() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");

        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            String data = iterator.next();
            if (data.equals("a")) {
                linkedList.remove("a");
            }
            System.out.println(data);
        }
        System.out.println("size=" + linkedList.size());
    }

}
