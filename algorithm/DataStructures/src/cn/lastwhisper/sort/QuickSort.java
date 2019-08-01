package cn.lastwhisper.sort;

import cn.lastwhisper.ArrayUtil;

import java.util.Arrays;

/**
 * @author lastwhisper
 */
public class QuickSort {

    public static void main(String[] args) {
        //int[] arr = {-9, 78, 0, 23, -567, 70};
        //int[] arr = {-9, 78, 0, 23, 60, 70};
        //int[] arr = {9, 0, -567};

        int[] arr = ArrayUtil.generateArrByRandom(10000000);
        // 计算耗时
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");

        //System.out.println(Arrays.toString(arr));

    }

    /**
     * 快速排序
     *
     * @param arr
     * @param left
     * @param right
     * @return void
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right; // 右下标
        int temp;
        // 中轴
        int pivot = arr[(left + right) / 2];
        // while循环的目的：小于或等于pivot的放左边，大于或等于pivot的放右边
        while (l < r) {
            // 在pivot左边找比pivot大的值的下标l
            while (arr[l] < pivot) { //找到arr[1]=98 >pivot=0
                l = l + 1;
            }
            // 在pivot右边找比pivot小的值的下标r
            while (arr[r] > pivot) { //找到arr[4]=-567 < pivot=0
                r = r - 1;
            }
            // 如果l>=r说明pivot左右两边的值，已经满足：
            // 小于或等于pivot的放左边，大于或等于pivot的放右边
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后，发现arr[l] == pivot，r--，前移
            if (arr[l] == pivot) {
                r = r - 1;
            }

            // 如果交换完后，发现arr[r] == pivot，l++，后移
            if (arr[r] == pivot) {
                l = l + 1;
            }
        }
        // 如果l==r，必须l++，r--，否则会栈溢出
        if (l == r) {
            l = l + 1;
            r = r - 1;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
