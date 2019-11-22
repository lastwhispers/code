package cn.lastwhisper.jdk5.feature.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 数组与集合的转换
 * @author lastwhisper
 */
public class ArraysAsList {
    /**
     * 将String数组转为集合
     */
    @Test
    public void testStringArrAsList(){
        String[] stringArray = new String[3];
        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";

        List<String> stringList = Arrays.asList(stringArray);
        // 修改集合中的元素，数组会发生变化
        stringList.set(0,"oneList");
        System.out.println(stringArray[0]);
        // 修改数组中的元素，集合会发生变化
        stringArray[1] = "twoArr";
        System.out.println(stringList.get(1));
    }

    @Test
    public void testIntArrAsList(){
        int[] intArr = {1,2,3};
        String[] stringArr = {"1","2","3"};
        System.out.println(intArr.getClass()); //class [I
        System.out.println(stringArr.getClass()); //class [Ljava.lang.String
        List<int[]> intList = Arrays.asList(intArr);
        List<String> stringList = Arrays.asList(stringArr);

    }


}
