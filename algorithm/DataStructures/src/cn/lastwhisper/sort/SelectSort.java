package cn.lastwhisper.sort;

import cn.lastwhisper.ArrayUtil;

import java.util.Arrays;

/**
 * @author lastwhisper
 */
public class SelectSort {

    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1};
        //selectSort1(arr);
        //System.out.println(Arrays.toString(arr));

        int[] arr = ArrayUtil.generateArrByRandom(100000);
        // 计算耗时
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");
    }

    /**
     * 选择排序O(n^2)
     *
     * @param arr
     * @return void
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minIndex = i;
            // 找出[1+i...arr.length]范围内的最小值，和最小值索引
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            // 交换最小值与第i个数的位置
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    /**
     * 算法的实现步骤
     *
     * @param arr
     * @return void
     */
    public static void selectSort1(int[] arr) {
        // 第1轮 {101, 34, 119, 1}

        // 1、找最小值
        // 最小值
        int min = arr[0];
        // 最小值索引
        int minIndex = 0;
        // {101, 34, 119, 1} 我们假设待排序数组第一个元素就是最小值，即min=arr[0],minIndex=0
        // 经过遍历后我们发现 min=1,minIndex=3
        for (int j = 0 + 1; j < arr.length; j++) {
            // 找最小的数
            if (min > arr[j]) {
                min = arr[j];
                minIndex = j;
            }
        }
        // 2、交换。minIndex是最小值所在位置，min是最小值
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }

        System.out.printf("第1轮选择，数组：%s", Arrays.toString(arr));
    }

}
