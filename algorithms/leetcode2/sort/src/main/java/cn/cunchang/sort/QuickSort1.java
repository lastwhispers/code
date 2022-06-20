package cn.cunchang.sort;

import java.util.Arrays;

/**
 * @author lastwhisper
 */
public class QuickSort1 {
    public static void main(String[] args) {
//        int[] arr = {2, 10, 8, 1000};
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        int[] arr = {-9, 78, 0, 23, 60, 70};
//        int[] arr = {9, 0, -567};
        int[] arr = {49, 38, 65, 97, 76, 13, 27};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivotIdx = partition(arr, left, right);
        // 此时分区pivotIdx左边值小于等于arr[pivotIdx]，分区右边大于arr[pivotIdx]
        // 对[left,pivotIdx - 1]再次分区
        sort(arr, left, pivotIdx - 1);
        // 对[pivotIdx + 1,right]再次分区
        sort(arr, pivotIdx + 1, right);
    }

    /**
     * @param nums  待分区数组
     * @param left  待分区左边界
     * @param right 待分区右边界
     * @return pivotIdx 中轴索引
     */
    private static int partition(int[] nums, int left, int right) {
        // 选最后一个值作为中轴
        int pivot = nums[left];
        // 所依right要减一
        int start = left + 1, end = right;
        while (start <= end) {
            // 寻找左边大于pivot
            while (start <= end && nums[start] <= pivot) {
                start++;
            }
            // 寻找右边小于等于pivot
            while (start <= end && nums[end] > pivot) {
                end--;
            }
            if (start < end) {
                swap(nums, start, end);
            }
        }
        // 此时[left+1,start-1]的值一定小于pivot=nums[left]
        // left一定大于等于start，所以将start-1与left交互
        swap(nums, start - 1, left);
        return start - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
