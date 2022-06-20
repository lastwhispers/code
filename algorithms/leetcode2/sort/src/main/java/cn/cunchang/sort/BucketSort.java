package cn.cunchang.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author cunchang
 * @date 2022/6/15 6:41 PM
 */
public class BucketSort {

    public static double[] bucketSort(double[] array) {
        //第一步：找出待排序的数组中最大和最小的元素
        double max = array[0];
        double min = array[0];
        for (double i : array) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        double d = max - min;
        //第二步：初始化桶
        int bucketNum = array.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<LinkedList<Double>>();
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Double>());
        }
        //第三步：遍历原始数组，将每个元素放入桶中
        for (int i = 0; i < array.length; i++) {
            // 定位元素属于第几个桶，是按照比例来定位：(array[i] - min) * (bucketNum-1) / d
            int num = (int) ((array[i] - min) * (bucketNum - 1) / d);
            bucketList.get(num).add(array[i]);
        }
        //第四步：对每个桶内的数据进行排序
        for (int i = 0; i < bucketList.size(); i++) {
            Collections.sort(bucketList.get(i));
        }
        //第五步：输入全部元素
        double[] sortArray = new double[array.length];
        int index = 0;
        for (LinkedList<Double> list : bucketList) {
            for (double element : list) {
                sortArray[index] = element;
                index++;
            }
        }
        return sortArray;
    }

}
