package cn.lastwhisper.atguigu.sort;

import java.util.Arrays;

/**
 * @author cn.lastwhisper
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
//        int[] arr = ArrayUtil.generateArrByRandom(10000000);// 1443 ms
        // 计算耗时
        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序
     * 分
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param right 右边索引
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid); // 向左分
            mergeSort(arr, mid + 1, right); // 向右分
            merge(arr, left, mid, right);// 治
        }
    }

    /**
     * 治
     *
     * @param arr   排序的原始数组 arr[left...mid] arr[mid+1...right]
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     */
    public static void merge(int[] arr, int left, int mid, int right) {
        //开辟额外大集合，设置指针
        int[] temp = new int[right - left + 1];

        int i = left; //左边有序序列的初始索引
        int j = mid + 1; // 右边有序序列的初始化序索引
        int tempIdx = 0; // 指向temp数组的当前位置

        // 1）、将左右两边（有序）的数据按照规则填充到temp数组，任意一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                // 左小于右，将左数组第i个元素copy到temp数组的第t个位置
                temp[tempIdx++] = arr[i++];
            } else {
                // 右小于或等于左，将右数组第j个元素copy到temp数组的第t个位置
                temp[tempIdx++] = arr[j++];
            }
        }

        // 2）、把有剩余数据的一边的数据依次填充到temp
        while (i <= mid) {// 说明 右数组还有剩余元素
            temp[tempIdx++] = arr[i++];
        }
        while (j <= right) {// 说明 右数组还有剩余元素
            temp[tempIdx++] = arr[j++];
        }

        // 3）、将temp数组的数据copy到arr，注意从本次数组起始位置left，开始复制
        for (int k = left; k < temp.length; k++) {
            arr[k] = temp[k];
        }
    }
}
