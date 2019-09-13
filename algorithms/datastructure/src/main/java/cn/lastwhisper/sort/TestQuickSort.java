package cn.lastwhisper.sort;

import cn.lastwhisper.ArrayUtil;

import java.util.Arrays;

/**
 * @author lastwhisper
 */
public class TestQuickSort {
    public static void main(String[] args) {
        //int[] arr = {49, 38, 65, 97, 76, 13, 27};
        //int[] arr = {-9, 78, 0, 23, -567, 70};
        int[] arr = ArrayUtil.generateArrByRandom(10);//耗时：1519 ms
        // 计算耗时
        long start = System.currentTimeMillis();
        //quickSort(arr, 0, arr.length - 1);
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");

        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        //定义俩指针 用于移动
        int l = head;
        int r = tail;
        int pivot = arr[head];//基准值，也可以arr[(head + tail) / 2]

        while (l < r) {
            // 从左到右，找大于等于基准数的位置
            while (arr[l] < pivot)
                l++;
            // 从右到左，找小于等于基准数的位置
            while (arr[r] > pivot)
                r--;

            if (l < r) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
                l++;
                r--;
            } else if (l == r)  {
                l++;
            }
        }
        quickSort(arr, head, r);
        quickSort(arr, l, tail);
    }
}
