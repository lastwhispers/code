package cn.lastwhisper.jdk5.feature.collection;

import org.junit.Test;

import java.util.*;

/**
 * @author lastwhisper
 */
public class TestMap {

    static class Person {
        public Long id;
        public String name;

        public Person(Long id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    /*
     * JDK1.7
     *  为什么要用数组+链表？
     * 1、Object的hashcode太大
     * 2、为什么冲突后插到链表的头部？效率快
     * 3、为什么数组的大小是2^n？
     *      indexFor函数算下标 (范围 00001111)，hashcode高位怎么变都不影响结果
     *      &比%快
     * 4、hashmap的扩容机制
     *      在元素插入之前扩容
     *      扩容——》空间换时间
     *      put——addEntry——resize——transfer（transfer会导致倒序）
     *      transfer多线程扩容会出现循环链表
     *      transfer中的indexFor会将原链表中元素打散
     *
     * 5、getEntry
     *  if (e.hash == hash &&
     *          ((k = e.key) == key || (key != null && key.equals(k))))
     * 为什么重写equals方法，一定要重写hashcode方法
     * 对象做hashmap做key，要声明成final
     *
     * 6、public HashMap(int initialCapacity, float loadFactor)什么时候用？
     *  解决循环链表，不让hashmap扩容
     *
     * JDK1.8
     *  为什么要用数组+链表(红黑树)？
     *  新元素加到尾结点
     *  hashmap的扩容机制
     *
     *      在元素插入之后扩容
     *      put——resize
     *
     *
     */
    @Test
    public void testHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, null);
        /*
         * put方法
         *  1、key==null单独处理
         *      putForNullKey，null没有hash值，强制放到table[0]
         *  2、key已经存在覆盖value，并返回oldValue；
         *      不存在addEntry头插法，添加新value，返回null
         *
         */
        String a1 = hashMap.put("a", "old");
        String a2 = hashMap.put("a", "new");
        System.out.println(a1 + "\t" + a2);
        hashMap.get("a");
    }

    /**
     * 测试hashmap去集合重
     */
    @Test
    public void testHashList() {
        HashSet<List> set = new HashSet<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        List<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(2);

        set.add(list1);
        set.add(list2);
        set.add(list3);
        System.out.println(set.size());
    }

    /**
     * LinkedHashMap可以保证put的kv有序
     *
     */
    @Test
    public void testLinkedHashMap() {
        LinkedHashMap<Long, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1L, "1");
        linkedHashMap.put(4L, "4");
        linkedHashMap.put(3L, "3");
        linkedHashMap.put(7L, "7");
        for (Map.Entry<Long, String> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }

    /**
     * TreeMap可以保证put的kv有序
     *  按key实现的comparable接口的compareTo方法规则进行排序
     */
    @Test
    public void testTreeMap() {
        TreeMap<Long, String> treeMap = new TreeMap<>();
        treeMap.put(1L, "1");
        treeMap.put(4L, "4");
        treeMap.put(3L, "3");
        treeMap.put(7L, "7");
        for (Map.Entry<Long, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }

    /**
     * TreeMap的key一定要重写comparable接口
     *  因为在插入时key需要比较顺序
     */
    @Test
    public void testTreeMapKeyNoComparable() {
        //java.lang.ClassCastException:
        // Person cannot be cast to class java.lang.Comparable
        TreeMap<Person, Long> treeMap = new TreeMap<Person, Long>();
        Person tom = new Person(1L, "tom");
        treeMap.put(tom, 1L);
    }


}
