package cn.lastwhisper.sort;


import cn.lastwhisper.util.ArrayUtil;

import java.util.Arrays;

/**
 * 快排
 *
 *  整个快速排序的核心是分区（partition），分区的目的是传入一个数组和选定的一个元素，把所有小于那个元素的其他元素放在左边，大于的放在右边。
 *  元素的选择一般有如下几种：
 *     永远选择第一个元素
 *     永远选择最后一个元素
 *     随机选择元素
 *     取中间值
 * @author lastwhisper
 * @date 2020/3/15
 */
public class QuickSort {


    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = partition(arr, left, right);
        sort(arr, left, mid - 1);
        sort(arr, mid + 1, right);
    }

    /**
     * 选取最后的元素，把所有小于那个元素的其他元素放在左边，大于的放在右边
     *
     *  7, 3, 2, 8, 1, 9, 5, 4, 6 ==》
     *      4, 3, 2, 5, 1, 6, 8, 7, 9
     *
     * @param arr 待排序数组
     * @return int 返回数组
     */
    private static int partition(int[] arr, int leftBound, int rightBound) {
        int i = leftBound;
        int pivot = arr[rightBound];
        int j = rightBound - 1;//-1是因为最后一个元素以及选择了
        while (i <= j) {
            while (i <= j && arr[i] <= pivot) {
                i++;
            }
            while (i <= j && arr[j] > pivot) {
                j--;
            }
            if (i < j) swap(arr, i, j);
        }
        swap(arr, i, rightBound);
        System.out.println(Arrays.toString(arr));
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {7, 3, 2, 8, 1, 9, 5, 4, 6};
        //int arr[] = {7, 3, 2, 8, 1, 9, 5, 4, 6, 0};//为什么left++和right--条件里面要加 left <= right 限定
        //int arr[] = {7, 3, 2, 8, 1, 9, 5, 4, 6, 10};
        //int arr[] = {7, 3, 2, 6, 8, 1, 9, 5, 4, 6, 10, 6, 6}; // 为什么不取等 arr[right] > pivot
        //int arr[] = {4, 6}; //小bug测试 // 为什么while (left <= right)里面要取等
        //sort(arr);
        sort(ArrayUtil.generateArrByRandom(10));
        //sort(arr,0,5);
        //System.err.println(Arrays.toString(arr));
    }
}
