package cn.lastwhisper.jdk5.feature.collection;

import java.util.Arrays;
import java.util.List;

/**
 * 数组与集合的转换
 * @author lastwhisper
 */
public class ArraysAsList {
    public static void main(String[] args) {
        String[] stringArray = new String[3];
        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";

        List<String> stringList = Arrays.asList(stringArray);
        //
        stringList.set(0,"oneList");

    }
}
