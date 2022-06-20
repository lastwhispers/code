package cn.lastwhisper.atguigu.sort;

import java.util.Arrays;

/**
 * @author cn.lastwhisper
 */
@Deprecated
public class QuickSort {

    public static void main(String[] args) {
        //int[] arr = {-9, 78, 0, 23, -567, 70};
        //int[] arr = {-9, 78, 0, 23, 60, 70};
        //int[] arr = {9, 0, -567};
        int[] arr = {49, 38, 65, 97, 76, 13, 27};

        //int[] arr = ArrayUtil.generateArrByRandom(10000000);//耗时：1519 ms
        // 计算耗时
        long start = System.currentTimeMillis();
        //quickSort(arr, 0, arr.length - 1);
        qSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");

        System.out.println(Arrays.toString(arr));
    }

    public static void qSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        //定义俩指针 用于移动
        int left = head;
        int right = tail;
        int pivot = arr[head]; //基准值，也可以arr[(head + tail) / 2]

        while (left <= right) {
            while (arr[left] < pivot) { //左指针先走，找到大于等于基准数的停止
                ++left;
            }
            while (arr[right] > pivot) { //右指针后走，找到小于等于基准数的停止
                --right;
            }
            if (left < right) {
                //交换arr[left]和arr[right]的位置
                int t = arr[left];
                arr[left] = arr[right];
                arr[right] = t;
                //继续遍历
                ++left;
                --right;
            } else if (left == right) {
                //遍历完，错开两指针，用于退出循环
                ++left;
                //break;
            }
        }

        qSort(arr, head, right);
        qSort(arr, left, tail);
    }


    /**
     * 快速排序,选取数组中间的值
     * int pivot = arr[(left + right) / 2];
     *
     * @param arr
     * @param left
     * @param right
     * @return void
     */
    public static void quickSort1(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right; // 右下标
        int temp;
        // 中轴
        int pivot = arr[(left + right) / 2];
        // while循环的目的：小于或等于pivot的放左边，大于或等于pivot的放右边
        while (l < r) {
            // 在pivot左边找比pivot大的值的下标l
            while (arr[l] < pivot) { //找到arr[1]=98 >pivot=0
                l++;
            }
            // 在pivot右边找比pivot小的值的下标r
            while (arr[r] > pivot) { //找到arr[4]=-567 < pivot=0
                r--;
            }
            // 如果l>=r说明pivot左右两边的值，已经满足：
            // 小于或等于pivot的放左边，大于或等于pivot的放右边 ——递归结束条件
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果交换完后，发现arr[l] == pivot，r--，前移 退出循环
            if (arr[l] == pivot) {
                r--;
            }

            // 如果交换完后，发现arr[r] == pivot，l++，后移 退出循环
            if (arr[r] == pivot) {
                l++;
            }
        }

        // 第二步

        // 如果l==r，必须l++，r--，否则会栈溢出
        if (l == r) {
            l = l + 1;
            r = r - 1;
        }
        // 向左递归
        if (left < r) {
            quickSort1(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort1(arr, l, right);
        }
    }
}
