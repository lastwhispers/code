package cn.lastwhisper.sort;

import cn.lastwhisper.ArrayUtil;

import java.util.Arrays;

/**
 * @author lastwhisper
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1};
        //insertSort1(arr); // 插入排序算法实现步骤

        int[] arr = ArrayUtil.generateArrByRandom(100000);
        // 计算耗时
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");

        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序O(n^2)
     *  insertVal < arr[insertIndex降序
     *  insertVal > arr[insertIndex升序
     * @param arr
     * @return void
     */
    public static void insertSort(int[] arr) {
        int insertVal;
        int insertIndex;
        // 有序列表[0]；[1...arr.length]无序列表
        for (int i = 1; i < arr.length; i++) {
            // insertVal的意义：缓存无序列表的第一个数，这个数也是待插入有序列表的数
            insertVal = arr[i];
            // insertIndex的意义：记录有序列表的最后一个数的下标
            insertIndex = i - 1;
            // [insertIndex+1...i-1]后移到[insertIndex+2...i]（从前往后移动）
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // insertIndex == i - 1; 说明数组并未后移，无需赋值
            if (insertIndex != i - 1) {
                // arr[insertIndex + 1]为待插入位置
                arr[insertIndex + 1] = insertVal;
            }
        }
    }

    /**
     * 插入排序的实现步骤
     *
     * @param arr
     * @return void
     */
    public static void insertSort1(int[] arr) {
        //  第1轮
        //  有序列表{101}，无序列表{34, 119, 1}
        // {101, 34, 119, 1}

        // insertVal的意义：缓存无序列表的第一个数，这个数也是待插入有序列表的数
        int insertVal = arr[1];
        // insertIndex的意义：记录有序列表的最后一个数的下标
        int insertIndex = 1 - 1;
        // insertIndex >= 0的意义：防止数组越界；
        // insertVal < arr[insertIndex]的意义：找到insertVal要插入位置的前一个位置
        // 整个while循环的意义：数组覆盖后移，找到待插入位置的前一个位置
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            // 第1轮原数组：{101, 34, 119, 1}
            // arr[1]=arr[0] {101, 101, 119, 1}
            arr[insertIndex + 1] = arr[insertIndex];
            //继续向前
            insertIndex--;
        }
        // arr[0]=insertVal {34, 101, 119, 1}
        arr[insertIndex + 1] = insertVal;

        System.out.printf("第1轮插入，数组：%s\n", Arrays.toString(arr));

        // 第2轮
        insertVal = arr[2];
        insertIndex = 2 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.printf("第2轮插入，数组：%s\n", Arrays.toString(arr));

        // 第3轮
        insertVal = arr[3];
        insertIndex = 3 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            // 第三轮原数组：{34, 101, 119, 1}
            // arr[3]=arr[2] {34, 101, 119, 119}
            // arr[2]=arr[1] {34, 101, 101, 119}
            // arr[1]=arr[0] {34, 34, 101, 119}
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.printf("第3轮插入，数组：%s\n", Arrays.toString(arr));
    }
}
