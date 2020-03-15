package cn.lastwhisper.jdk5.feature.collection;

import cn.lastwhisper.jdk5.feature.reflect.ReflectUtils;
import org.junit.Assert;

import java.util.ArrayList;

/**
 * 测试ArrayList的扩容机制
 * @author lastwhisper
 * @date 2019/10/24
 */
public class ArrayListGrowTest {
    /**
     * 创建ArrayList不指定初始容量，第一次add时直接扩容到DEFAULT_CAPACITY=10
     *  所以如果能确定ArrayList的大小一定要指定初始容量
     *
     * add逻辑：
     * ensureCapacityInternal——确保内部需要的容量够用minCapacity=size + 1
     *  calculateCapacity——判断ArrayList是否是第一次创建且未指定初始容量，如果是minCapacity=DEFAULT_CAPACITY
     *  ensureExplicitCapacity——minCapacity>elementData.length，扩容1.5*elementData.length
     */
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

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
        arrayList.add(12);


        elementData = (Object[]) ReflectUtils.getValue(arrayList, "elementData");
        Assert.assertEquals(15, elementData.length);
    }
}
