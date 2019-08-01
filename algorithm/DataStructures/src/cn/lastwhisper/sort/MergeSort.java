package cn.lastwhisper.sort;

import cn.lastwhisper.ArrayUtil;

/**
 * @author lastwhisper
 */
public class MergeSort {

    public static void main(String[] args) {
        //int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arr = ArrayUtil.generateArrByRandom(10000000);
        // 计算耗时
        long start = System.currentTimeMillis();
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");

        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序
     * 分
     *
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param right 右边索引
     * @param temp 中转数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        int mid;
        if (left < right) {
            mid = (left + right) / 2;
            // 向左分
            mergeSort(arr, left, mid, temp);
            // 向右分
            mergeSort(arr, mid + 1, right, temp);
            // 治
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 治
     *
     * @param arr 排序的原始数组 arr[left...mid] arr[mid+1...right]
     * @param left 左边有序序列的初始索引
     * @param mid  中间索引
     * @param right 右边索引
     * @param temp 中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //左边有序序列的初始索引
        int j = mid + 1; // 右边有序序列的初始化序索引
        int t = 0; // 指向temp数组的当前位置

        // 1）、将左右两边（有序）的数据按照规则填充到temp数组，任意一边处理完毕为止
        while (i <= mid && j <= right) {

            if (arr[i] < arr[j]) {
                // 左小于右，将左数组第i个元素copy到temp数组的第t个位置
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                // 右小于或等于左，将右数组第j个元素copy到temp数组的第t个位置
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // 2）、把有剩余数据的一边的数据依次填充到temp
        while (i <= mid) {
            // 说明 右数组还有剩余元素
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            // 说明 右数组还有剩余元素
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // 3）、将temp数组的数据copy到arr（并不是每次都copy所有）
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) { // 0 1;2 3;0 3
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }
}
