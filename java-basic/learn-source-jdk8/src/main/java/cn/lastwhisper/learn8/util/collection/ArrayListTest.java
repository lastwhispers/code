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

    @Test
    public void testBatchInsert(){
        // 准备数据
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<3000000;i++){
            list.add(i);
        }

        // for 循环 使用add方法新增
        ArrayList<Integer> list2 = new ArrayList<>();
        long start1 = System.currentTimeMillis();
        for(int i=0;i<list.size();i++){
            list2.add(list.get(i));
        }
        log.info("单个 for 循环新增 300 w 个，耗时{}", System.currentTimeMillis()-start1);

        // 使用 addAll 方法批量新增
        ArrayList<Integer> list3 = new ArrayList<>();
        long start2 = System.currentTimeMillis();
        list3.addAll(list);
        log.info("批量新增 300 w 个，耗时{}", System.currentTimeMillis()-start2);
    }

    @Test
    public void testArrayToList(){
        Integer[] array = new Integer[]{1,2,3,4,5,6};
        List<Integer> list = Arrays.asList(array);

        // 修改数组的值，会直接影响 list
        log.info("数组被修改之前，集合第一个元素为：{}",list.get(0));
        array[0] = 10;
        log.info("数组被修改之前，集合第一个元素为：{}",list.get(0));

        // 使用 add、remove 等操作 list 的方法时，
        // 会报 UnsupportedOperationException 异常
        list.add(7);
    }

    @Test
    public void testListToArray(){
        List<Integer> list = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
        }};

        // 这样子无法转化，无参 toArray 返回的是 Object[],
        // 无法向下强转，编译无法通过，很麻烦
//    List<Integer> list2 = list.toArray();

        // 演示有参 toArray 方法，数组大小不够时，数组为 null 情况
        Integer[] array0 = new Integer[2];
        list.toArray(array0);
        log.info("toArray 数组大小不够，array0 数组[0] 值是{},数组[1] 值是{},",array0[0],array0[1]);

        Integer[] array1 = new Integer[list.size()];
        list.toArray(array1);
        log.info("toArray 数组大小正好，array1 数组[3] 值是{}",array1[3]);

        Integer[] array2 = new Integer[list.size()+2];
        list.toArray(array2);
        log.info("toArray 数组大小多了，array2 数组[3] 值是{}，数组[4] 值是{}",array2[3],array2[4]);
    }

    @Test
    public void testArrayParallel(){
        int size = 1 << 13;
        log.info(""+size);
        Integer[] array = new Integer[size+2];
        for(int i=0;i<size+1;i++){
            array[i] =i;
        }
        Arrays.parallelSort(array);
    }

    @Test
    public void testReverse(){
        List<String> list = new ArrayList<String>(){{
            add("10");
            add("20");
            add("30");
            add("40");
        }};
        log.info("反转之前："+ JSON.toJSONString(list));
        list = Lists.reverse(list);
        log.info("反转之后："+JSON.toJSONString(list));
    }

    @Test
    public void testPartition(){
        List<String> list = new ArrayList<String>(){{
            add("10");
            add("20");
            add("30");
            add("40");
        }};
        log.info("分组之前："+JSON.toJSONString(list));
        List<List<String>> list2 = Lists.partition(list,3);
        log.info("分组之后："+JSON.toJSONString(list2));
    }


}
