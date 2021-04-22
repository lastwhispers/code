package cn.lastwhisper.equals;

import cn.lastwhisper.reflect.ReflectPoint;
import org.junit.Test;

import java.util.*;

/**
 * hashcode导致内存泄漏
 * hashcode比较对象是否相等
 * @author lastwhisper
 */
public class HashCode {
    /**
     *
     * 内存泄漏memory leak ：是指程序在申请内存后，无法释放已申请的内存空间，
     *    一次内存泄漏似乎不会有大的影响，但内存泄漏堆积后的后果就是内存溢出。
     * 各种内存泄漏 https://www.cnblogs.com/panxuejun/p/5883044.html
     */
    static class Person {
        private Integer id;
        private String name;

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        // 重写了对象的equals规则，一定要重写hashcode规则
        //@Override
        //public boolean equals(Object obj) {
        //    if (this == obj) return true;
        //    if (obj == null || obj.getClass() != getClass()) return false;
        //    Person person = (Person) obj;
        //    return id == person.id;
        //}
        //
        //@Override
        //public int hashCode() {
        //    return Objects.hash(id);
        //}
    }

    /**
     * 思考一个问题，不重写hashcode和equals，这两个对象相同吗？
     * //重写对象的equals方法，不重写hashcode方法导致的问题
     */
    @Test
    public void testOverrideEquals() {
        Person p1 = new Person(1, "张三");
        Person p2 = new Person(1, "张三");
        System.out.println(p1.equals(p2));//true

        Map<Person, String> map = new HashMap<Person, String>();
        map.put(p1, "张三");
        System.out.println(map.get(p2));

    }

    /**
     * 重写对象的equals、hashcode方法
     * 不要修改对象的属性，因为修改对象的属性，会导致hashcode发生变化
     * 从而无法使用remove真的移除集合中的对象，对象被集合强引用，导致内存泄漏
     */
    @Test
    public void testHashCodeToOOM() {
        Set<ReflectPoint> set = new HashSet<ReflectPoint>();
        ReflectPoint pt1 = new ReflectPoint(1, 1);
        ReflectPoint pt2 = new ReflectPoint(2, 2);
        ReflectPoint pt3 = new ReflectPoint(1, 1);

        set.add(pt1);
        set.add(pt2);
        set.add(pt3);//重写hashcode发现重复
        set.add(pt1);//内存地址一样发现重复

        // 对象重写hashcode方法，添加到集合中后不要修改对象的属性值
        pt1.setX(2);
        set.remove(pt1);
        // 1. 没有重写hashcode size=3。
        // 2. 重写hashcode size=2。
        // 3. 在2的基础上修改对象的属性值，再移除对象，size=2，内存泄漏。
        System.out.println(set.size());
    }

}


