package cn.lastwhisper.learn8.util.collection;

import cn.lastwhisper.learn8.common.ReflectUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList
 * @author lastwhisper
 * @date 2020/4/12
 */
@Slf4j
public class ArrayListTest {

    /**
     *  ArrayList初始化
     */
    @Test
    public void testInitBug() {
        // Arrays.asList 返回的是具体的类型
        List<String> list = Arrays.asList("hello,world");
        Object[] objArray = list.toArray();
        log.info(objArray.getClass().getSimpleName());//Object[]
        // objArray 元素的类型是 String，储存 Object 就会报错，
        // 因为 jvm 不清楚你存储的 Object 真实类型是不是 String
        objArray[0] = new Object();
    }

    /**
     *  ArrayList扩容机制
     *
     * 创建ArrayList不指定初始容量，第一次add时直接扩容到DEFAULT_CAPACITY=10
     *  所以如果能确定ArrayList的大小一定要指定初始容量
     *
     * add逻辑：
     * ensureCapacityInternal——确保内部需要的容量够用minCapacity=size + 1
     *  calculateCapacity——判断ArrayList是否是第一次创建且未指定初始容量，如果是minCapacity=DEFAULT_CAPACITY
     *  ensureExplicitCapacity——minCapacity>elementData.length，扩容1.5*elementData.length
     */
    @Test
    public void testGrow() {
        List<Integer> arrayList = new ArrayList<>();

        Object[] elementData = (Object[]) ReflectUtils.getValue(arrayList, "elementData");
        Assert.assertEquals(0, elementData.length);

        // 此处应该扩容 0->10
        arrayList.add(1);
        elementData = (Object[]) ReflectUtils.getValue(arrayList, "elementData");
        Assert.assertEquals(10, elementData.length);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        // 此处应该扩容 10->15
        arrayList.add(11);

        elementData = (Object[]) ReflectUtils.getValue(arrayList, "elementData");
        Assert.assertEquals(15, elementData.length);
    }


    /**
     * 测试ArrayList在创建迭代器后,使用iterator.remove()单线程修改
     *
     * fail-fast
     *
     * 一般都是用增强for循环遍历集合，增强for只是个语法糖，本质上还是迭代器遍历。
     *  modCount和expectedModCount其实就是个版本号，通过modCount和expectedModCount能感知到ArrayList是否被并发读写，
     *  ArrayList是非线程安全的，并发读写极有可能出错，于是JDK直接提供了快速失败机制，省的你出现奇怪的错误。
     *
     *  通过这个快速失败机制也能看出来，JDK就是要告诉你ArrayList不能并发读写，就算你能容忍并发读写带来的错误也不行，
     *  人家的设计理念就不允许你这么做。
     */
    @Test
    public void testFailFast() {
        List<String> arrayList = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
            add("d");
        }};

        // java.util.ConcurrentModificationException
        //for (String data : arrayList) {
        //    if (data.equals("a")) {
        //        arrayList.remove("a");
        //    }
        //    System.out.println(data);
        //}

        // java.util.ConcurrentModificationException
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


}
